/*
 *Project: gysolr-product-provider
 *File: com.glorypty.gysolr.product.index.GoodsIndex.java <2016年5月14日>
 ****************************************************************
 * 版权所有@2016 国裕网络科技  保留所有权利.
 ***************************************************************/
package com.glorypty.gysolr.product.index;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;

import com.glorypty.framework.util.PropertyUtil;
import com.glorypty.gymodule.product.bussiness.prd.service.GoodsIService;
import com.glorypty.gysolr.common.bulider.BaseBulider;
import com.glorypty.gysolr.common.service.BaseIndexImpl;
import com.glorypty.gysolr.common.solr.SolrCoreCodeEnum;
import com.glorypty.gysolr.common.solr.SolrServerFactory;
import com.glorypty.gysolr.product.bulider.GoodsBulider;
import com.glorypty.gysolr.product.domain.GoodsData;

/**
 *
 * @author liujie 
 * @Date 2016年5月14日 下午5:56:09
 * @version 1.0
 */
@org.springframework.stereotype.Service
public class GoodsIndexService extends BaseIndexImpl<GoodsData>{
	
	@Autowired
	private GoodsIService goodsIService;
    
	private static HttpSolrClient server = SolrServerFactory.getSearchServer(SolrCoreCodeEnum.GOODS);

	
	@Override
	public HttpSolrClient getHttpSolrClient() {
		return server;
	}

	@Override
	public List<GoodsData> getDataByPage(Integer page,Integer pageSize) {
		//设置查询条件
		GoodsData data = new GoodsData();
		data.setPage(page);
		data.setPageSize(pageSize);
		
		return goodsIService.getProduct4Solr(data);
	}
	
	@Override
	public Integer getTotal() {
		//设置查询条件
		GoodsData data = new GoodsData();
		return goodsIService.getProductTotal4Solr(data);
	}

	@Override
	public Integer getBathNum() {
		Integer bathNum = 0;
		try {
			String bathNumStr = PropertyUtil.getProperty("goods.batch.num");
			try {
				bathNum = Integer.parseInt(bathNumStr);
			} catch (Exception e) {
				bathNum = 10000;
			}
		} catch (Exception e) {
			bathNum = 10000;
		}
		return bathNum;
	}

	@Override
	public BaseBulider<GoodsData> getBulider(int start, int end,
			CountDownLatch startSignal) {
		return new GoodsBulider(start, end, startSignal,this);
	}

	@Override
	public Boolean indexEntrty(GoodsData entrty) {
		try {
			// 新建文档
			SolrInputDocument doc = new SolrInputDocument();
			doc.addField("id", entrty.getId());
			doc.addField("title", entrty.getTitle());
			doc.addField("shopId", entrty.getShopId());
			doc.addField("priceMarket", entrty.getPriceMarket());
			doc.addField("shopName", entrty.getShopName());
			doc.addField("sccj", entrty.getSccj());
			doc.addField("xsqy", entrty.getXsqy());
			doc.addField("jyfw", entrty.getJyfw());
			doc.addField("zygx", entrty.getZygx());
			doc.addField("pic", entrty.getPic());
			doc.addField("spuId", entrty.getSpuId());
			doc.addField("saleSize", entrty.getSaleSize());
			doc.addField("jx", entrty.getJx());
			doc.addField("pp", entrty.getPp());
			doc.addField("updateTime", entrty.getUpdateTime());
			doc.addField("frontCid", entrty.getFrontCid());
			doc.addField("shopCid", entrty.getShopCid());
			
			server.add(doc);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("input goods faild entrty:" + entrty);
			return false;
		}
	}
	
	public Boolean indexEntrtyList(List<GoodsData> entrtys) {
		try {
			// 新建文档
			List<SolrInputDocument> docs = new ArrayList<SolrInputDocument>();
			for(GoodsData entrty : entrtys){
				SolrInputDocument doc = new SolrInputDocument();
				doc.addField("id", entrty.getId());
				doc.addField("title", entrty.getTitle());
				doc.addField("shopId", entrty.getShopId());
				doc.addField("priceMarket", entrty.getPriceMarket());
				doc.addField("shopName", entrty.getShopName());
				doc.addField("sccj", entrty.getSccj());
				doc.addField("xsqy", entrty.getXsqy());
				doc.addField("jyfw", entrty.getJyfw());
				doc.addField("zygx", entrty.getZygx());
				doc.addField("pic", entrty.getPic());
				doc.addField("spuId", entrty.getSpuId());
				doc.addField("saleSize", entrty.getSaleSize());
				doc.addField("jx", entrty.getJx());
				doc.addField("pp", entrty.getPp());
				doc.addField("updateTime", entrty.getUpdateTime());
				doc.addField("frontCid", entrty.getFrontCid());
				doc.addField("shopCid", entrty.getShopCid());
				docs.add(doc);
			}
			server.add(docs);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("indexEntrtyList goods faild entrty:" );
			return false;
		}
	}

}
