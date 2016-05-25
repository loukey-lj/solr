/*
 *Project: gysolr-product-provider
 *File: com.glorypty.gysolr.product.query.GoodsQueryService.java <2016年5月18日>
 ****************************************************************
 * 版权所有@2016 国裕网络科技  保留所有权利.
 ***************************************************************/
package com.glorypty.gysolr.product.query;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;

import com.glorypty.gysolr.common.result.BaseResult;
import com.glorypty.gysolr.common.service.BaseQueryImpl;
import com.glorypty.gysolr.common.solr.SolrCoreCodeEnum;
import com.glorypty.gysolr.common.solr.SolrQueryUtil;
import com.glorypty.gysolr.common.solr.SolrServerFactory;
import com.glorypty.gysolr.product.domain.GoodsData;
import com.glorypty.gysolr.product.domain.GoodsOrderProperty;

/**
 *
 * @author liujie 
 * @Date 2016年5月18日 下午4:19:20
 * @version 1.0
 */
public class GoodsQueryService extends BaseQueryImpl<GoodsData, BaseResult<GoodsData>>{
	
	private static HttpSolrClient server = SolrServerFactory.getSearchServer(SolrCoreCodeEnum.GOODS);

	public BaseResult<GoodsData> query4Index(GoodsData where) {
		//新建查询对象
		SolrQuery query = new SolrQuery();
		
		setQueryParam(where, query);
		
		
		query.setFields("id", "title", "shopId", "priceMarket","shopName",
					"sccj", "zygx", "pic");
		query.setFacet(true).addFacetField("jx").addFacetField("pp")
				.addFacetField("xsqy").setFacetMinCount(1)
				.setFacetLimit(100).setFacetMissing(true);
			
		BaseResult<GoodsData> sp = new BaseResult<GoodsData>();
		try {
			QueryResponse response = server.query(query);
			sp = success4List(response, sp);
		} catch (SolrServerException | IOException e) {
			sp.setDesc(e.getMessage());
			sp.setState("-1");
			sp.setList(new ArrayList<GoodsData>());
		}
		
		return sp;
	}
	
	public BaseResult<GoodsData> query4Shop(GoodsData where) {
		//新建查询对象
		SolrQuery query = new SolrQuery();
		
		setQueryParam(where, query);

		query.setFields("id", "title", "priceMarket", "pic");
		query.setFacet(true).addFacetField("jx").addFacetField("pp")
				.setFacetLimit(100).setFacetMissing(true);
		
		BaseResult<GoodsData> sp = new BaseResult<GoodsData>();
		try {
			QueryResponse response = server.query(query);
			sp = success4List(response, sp);
		} catch (SolrServerException | IOException e) {
			sp.setDesc(e.getMessage());
			sp.setState("-1");
			sp.setList(new ArrayList<GoodsData>());
		}
		
		return sp;
	}

	private void setQueryParam(GoodsData where, SolrQuery query) {
		//分页判断
		Integer page = where.getPage();
		Integer pageSize = where.getPageSize();
		SolrQueryUtil.setQueryPage(query, page, pageSize);
		
		//前台分类查询
		String frontCid = where.getFrontCid();
		Long intCid = Long.valueOf(frontCid);
		if(intCid != null){
			//根据intCid 查出 所以包含intCid 的前台节点 和与之 对应的后台分类结果
			//  然后根据 后台分类集合 q去查询商品
			String backIdStr = "";//TODO
			if(backIdStr != null){
				String[] backIdArr = backIdStr.split(" ");
				if(backIdArr != null && backIdArr.length > 0){
					StringBuffer buffer = new StringBuffer();
					for(String idStr : backIdArr){
							buffer.append("frontCid:").append(Long.valueOf(idStr)).append(" ");
						}
					query.setQuery((buffer.toString()).trim());
					}
				}
			}
		//店铺商品查询
		Long shopId = where.getShopId();
		SolrQueryUtil.setNumberQuery(query, "shopId", shopId);
		
		//地区查询
		String xsqy = where.getXsqy();
		SolrQueryUtil.setStringQuery(query, "xsqy", xsqy);
		
		//经营范围查询
		String jyfw = where.getJyfw();
		SolrQueryUtil.setStringQuery(query, "jyfw", jyfw);
		
		//品牌查询
		String pp = where.getPp();
		SolrQueryUtil.setStringQuery(query, "pp", pp);

		//剂型
		String jx = where.getJx();
		SolrQueryUtil.setStringQuery(query, "jx", jx);
		
		//店铺分类查询
		String shopCid = where.getShopCid();
		SolrQueryUtil.setStringQuery(query, "shopCid", shopCid);
		
		//关键字查询
		String title = where.getTitle();
		SolrQueryUtil.setStringQuery(query, "title", title);
		
		//价格区间查询
		Double start = where.getRangStart();
		Double end = where.getRangEnd();
		SolrQueryUtil.setDoubleRangQuery(query, "priceMarket", start, end);
		
		//排序
		GoodsOrderProperty goodsOrderProperty = where.getGoodsOrderProperty();
		if(goodsOrderProperty.getIndex() != 0){
			query.addSort(goodsOrderProperty.getCode(), where.getOrderBy());
		}
	}

		

}
