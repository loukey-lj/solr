/*
 *Project: gysolr-product-provider
 *File: com.glorypty.gysolr.product.index.CategoryIndex.java <2016年5月14日>
 ****************************************************************
 * 版权所有@2016 国裕网络科技  保留所有权利.
 ***************************************************************/
package com.glorypty.gysolr.category.index;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;

import com.glorypty.framework.util.PropertyUtil;
import com.glorypty.gymodule.centre.bussiness.sys.service.TreeNodeCentreIService;
import com.glorypty.gysolr.category.bulider.CategoryBulider;
import com.glorypty.gysolr.category.domain.CategoryData;
import com.glorypty.gysolr.common.bulider.BaseBulider;
import com.glorypty.gysolr.common.service.BaseIndexImpl;
import com.glorypty.gysolr.common.solr.SolrCoreCodeEnum;
import com.glorypty.gysolr.common.solr.SolrServerFactory;

/**
 *
 * @author liujie 
 * @Date 2016年5月14日 下午5:56:09
 * @version 1.0
 */
@org.springframework.stereotype.Service
public class CategoryIndexService extends BaseIndexImpl<CategoryData>{
	
	@Autowired
	private TreeNodeCentreIService centreIService;
    
	private static HttpSolrClient server = SolrServerFactory.getSearchServer(SolrCoreCodeEnum.CATEGORY);

	
	@Override
	public HttpSolrClient getHttpSolrClient() {
		return server;
	}

	@Override
	public List<CategoryData> getDataByPage(Integer page,Integer pageSize) {
		//设置查询条件
		/*CategoryData data = new CategoryData();
		data.setPage(page);
		data.setPageSize(pageSize);*/
		
		return centreIService.getMapForWeb();
	}
	
	@Override
	public Integer getTotal() {
		//设置查询条件
		return -1;
	}

	@Override
	public Integer getBathNum() {
		Integer bathNum = 0;
		try {
			String bathNumStr = PropertyUtil.getProperty("category.batch.num");
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
	public BaseBulider<CategoryData> getBulider(int start, int end,
			CountDownLatch startSignal) {
		return new CategoryBulider(start, end, startSignal,this);
	}

	@Override
	public Boolean indexEntrty(CategoryData entrty) {
		try {
			// 新建文档
			SolrInputDocument doc = new SolrInputDocument();
			doc.addField("frontId", entrty.getFrontId());
			doc.addField("backCategoryIds", entrty.getBackCategoryIds());
			doc.addField("frontPath", entrty.getFrontPath());
			
			server.add(doc);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("input Category faild entrty:" + entrty);
			return false;
		}
	}

}
