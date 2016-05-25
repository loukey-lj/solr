/*
 *Project: gysolr-product-provider
 *File: com.glorypty.gysolr.common.solr.SolrQueryUtil.java <2016年5月18日>
 ****************************************************************
 * 版权所有@2016 国裕网络科技  保留所有权利.
 ***************************************************************/
package com.glorypty.gysolr.common.solr;

import org.apache.solr.client.solrj.SolrQuery;

/**
 *
 * @author liujie 
 * @Date 2016年5月18日 下午5:28:50
 * @version 1.0
 */
public class SolrQueryUtil {
	
	public static void setStringQuery(SolrQuery query,String key,String value){
		if(value != null){
			value = value.trim();
			if(!"".equals(value)){
				query.setQuery(key + ":" + value);
			} 
		}
	}
	
	public static void setNumberQuery(SolrQuery query,String key,Number value){
		if(value != null && value.longValue() > 0){
			query.setQuery(key + ":" + value);
		}
	}
	
	public static void setQueryPage(SolrQuery query, Integer page, Integer pageSize) {
		if(page == null || page <= 0){
			page = 1;
		}
		if(pageSize == null || pageSize <= 0){
			pageSize = 10;
		}
		Integer start = (page -1 ) * pageSize;
		query.setStart(start);
		query.setRows(pageSize);
	}
  
	public static void setDoubleRangQuery(SolrQuery query,String key,Double start, Double end){
		if(start != null){
			if(end != null){
				if(start > end){
					query.setQuery(key + ":[" + 1 + " TO " + 0 + "]" );
				}else{
					query.setQuery(key + ":[" + start + " TO " + end + "]" );
				}
			}else{
				query.setQuery(key + ":[" + start + " TO *]" );
			}
		}else{
			if(end != null){
				query.setQuery(key + ":[* TO " + end + "]" );
			}
		}
	}
}
