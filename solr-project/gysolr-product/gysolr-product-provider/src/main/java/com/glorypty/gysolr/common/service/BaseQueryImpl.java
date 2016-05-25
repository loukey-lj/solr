/*
 *Project: gysolr-product-provider
 *File: com.glorypty.gysolr.base.BaseSolrService.java <2016年5月14日>
 ****************************************************************
 * 版权所有@2016 国裕网络科技  保留所有权利.
 ***************************************************************/
package com.glorypty.gysolr.common.service;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;

import com.glorypty.gysolr.common.domain.BaseQuery;
import com.glorypty.gysolr.common.domain.Group;
import com.glorypty.gysolr.common.domain.GroupDetail;
import com.glorypty.gysolr.common.facade.BaseQueryFacade;
import com.glorypty.gysolr.common.result.BaseResult;
import com.glorypty.gysolr.common.result.GroupResult;
import com.glorypty.gysolr.common.util.CheckData;

/**
 *
 * @author liujie 
 * @Date 2016年5月14日 下午5:42:07
 * @version 1.0
 */
public class BaseQueryImpl<Entrty extends BaseQuery,Result extends BaseResult<Entrty>> implements BaseQueryFacade<Entrty, Result>{

	@Override
	public Result queryByWhere(Entrty t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result queryById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Class<Entrty> entityClass;
	
	public Class<Entrty> getEntityClass() {
		return entityClass;
	}

	public  BaseQueryImpl(){
	    Type t = getClass().getGenericSuperclass();
	    if(t instanceof ParameterizedType){
	        Type[] p = ((ParameterizedType)t).getActualTypeArguments();
	        entityClass = (Class<Entrty>)p[0];
	    }
	  }
	
	protected String success4Json(QueryResponse reponse, BaseResult<Entrty> sp) {
		success4List(reponse, sp);
		JSONObject json = JSONObject.fromObject(sp);
		JSONObject.toBean(json,sp.getClass());
		return json.toString();
	}
	
	protected BaseResult<Entrty> success4List(QueryResponse reponse, BaseResult<Entrty> sp) {
		List<Entrty> list = reponse.getBeans(entityClass);
		SolrDocumentList docList = reponse.getResults();
		sp.setList(list);
		sp.setTotal(docList.getNumFound());
		sp.setState("0");
		sp.setDesc("succcess");
		return sp;
		
	}
	
	protected BaseResult<Entrty> successGroup4List(QueryResponse reponse, GroupResult<Entrty> sp) {
		for (FacetField field : reponse.getFacetFields()) {
			if (field.getValues() != null) {
				Group g = new Group();
				for (FacetField.Count count : field.getValues()) {
					if (CheckData.isEmptyForString(count.getName())) {
						continue;
					} else {
						GroupDetail gd = new GroupDetail();
						gd.setDetailName(count.getName());
						gd.setDetailSize(count.getCount());
						g.getGroupDetailList().add(gd);
					}
				}
				if (!CheckData.isEmptyForList(g.getGroupDetailList())) {
					g.setGroupName(field.getName());
					sp.getGroups().add(g);
				}
			}
		}
		
		return success4List(reponse, sp);
	}
	
	protected String successGroup4Json(QueryResponse reponse, GroupResult<Entrty> sp) {
		successGroup4List(reponse, sp);
		return	success4Json(reponse, sp);
	}
	
	

}
