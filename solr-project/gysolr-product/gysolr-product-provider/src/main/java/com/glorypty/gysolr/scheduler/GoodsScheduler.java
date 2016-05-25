/*
 *Project: gysolr-product-provider
 *File: com.glorypty.gysolr.scheduler.GoodsScheduler.java <2016年5月25日>
 ****************************************************************
 * 版权所有@2016 国裕网络科技  保留所有权利.
 ***************************************************************/
package com.glorypty.gysolr.scheduler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.glorypty.gymodule.centre.bussiness.sys.domain.SolrObjectTypeEnum;
import com.glorypty.gymodule.centre.bussiness.sys.domain.SolrQueue;
import com.glorypty.gymodule.centre.bussiness.sys.service.SolrQueueIService;
import com.glorypty.gymodule.product.bussiness.prd.service.GoodsIService;
import com.glorypty.gysolr.common.job.BaseJob;
import com.glorypty.gysolr.common.solr.SolrCoreCodeEnum;
import com.glorypty.gysolr.common.util.SerialUtil;
import com.glorypty.gysolr.product.domain.GoodsData;
import com.glorypty.gysolr.product.index.GoodsIndexService;

/**
 *
 * @author liujie 
 * @Date 2016年5月25日 下午3:12:01
 * @version 1.0
 */
@Component("goodsScheduler")
public class GoodsScheduler  extends BaseJob{
    @Autowired
    private SolrQueueIService queueIService;
    
    @Autowired
	private GoodsIService goodsIService;
    
    @Autowired
   	private GoodsIndexService goodsIndexService;

    @Override
	public void main() {
		String num =  SerialUtil.getSearialNum();
		if(num == null){
		    return ;	
		}
		//第一步获取goods相关联的数据
		SolrQueue solrQueue = new SolrQueue();
		solrQueue.setSolrCore(SolrCoreCodeEnum.GOODS.getIndex());
		solrQueue.setSerialNum(num);
		//查询商品ID
		solrQueue.setObjectType(SolrObjectTypeEnum.GOODS.getIndex());
		List<SolrQueue> queueGoods = queueIService.search(solrQueue);
		List<Long> successId = new ArrayList<Long>();
		List<Long> failedId = new ArrayList<Long>();
		if(queueGoods != null && queueGoods.size() > 0){
			
			for(SolrQueue queue : queueGoods){
				Long objectId = queue.getObjectId();
				Long id = queue.getId();
				GoodsData  data = new GoodsData();
				data.setId(objectId);
				List<GoodsData> list = goodsIService.getProduct4Solr(data);
				//是在售 更新索引
				if(list != null){
					data = list.get(0);
					if(goodsIndexService.indexEntrty(data)){
						successId.add(id);
					}else{
						failedId.add(id);
					}
					list.clear();
					list = null;
				}
				//不是在售了 删除索引
				else{
					if(goodsIndexService.deleteById(objectId)){
						successId.add(id);
					}else{
						failedId.add(id);
					}
				}
			}
			queueGoods.clear();
			queueGoods = null;
		}
		
		// 
		solrQueue.setObjectType(SolrObjectTypeEnum.SPU.getIndex());
		List<SolrQueue> queueSpu = queueIService.search(solrQueue);
		if(queueSpu != null && queueSpu.size() > 0){
			for(SolrQueue queue : queueSpu){
				Long objectId = queue.getObjectId();
				Long id = queue.getId();
				GoodsData  data = new GoodsData();
				data.setSpuId(objectId);
				List<GoodsData> list = goodsIService.getProduct4Solr(data);
				if(list != null){
					if(goodsIndexService.indexEntrtyList(list)){
						successId.add(id);
					}else{
						failedId.add(id);
					}
					list.clear();
					list = null;
				}else{
					successId.add(id);
				}
			}
			queueSpu.clear();
			queueSpu = null;
		}
		
		
		solrQueue.setObjectType(SolrObjectTypeEnum.SHOP.getIndex());
		List<SolrQueue> queueShop = queueIService.search(solrQueue);
		if(queueShop != null && queueShop.size() > 0){
			for(SolrQueue queue : queueShop){
				Long objectId = queue.getObjectId();
				Long id = queue.getId();
				GoodsData  data = new GoodsData();
				data.setShopId(objectId);
				List<GoodsData> list = goodsIService.getProduct4Solr(data);
				if(list != null){
					if(goodsIndexService.indexEntrtyList(list)){
						successId.add(id);
					}else{
						failedId.add(id);
					}
					list.clear();
					list = null;
				}else{
					successId.add(id);
				}
			}
			queueShop.clear();
			queueShop = null;
		}
		
		if(successId != null && successId.size() > 0){
			//删除 成功的
			queueIService.batchDelete(successId);
		}
		if(failedId != null && failedId.size() > 0){
			//更新失败的
			queueIService.batchUpdateFailTime(failedId);
		}
		
	}

}
