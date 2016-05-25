/*
 *Project: gysolr-product-im
 *File: com.glorypty.gysolr.category.CategoryData.java <2016年5月18日>
 ****************************************************************
 * 版权所有@2016 国裕网络科技  保留所有权利.
 ***************************************************************/
package com.glorypty.gysolr.category.domain;

import java.io.Serializable;

import org.apache.solr.client.solrj.beans.Field;

import com.glorypty.gysolr.common.domain.BaseQuery;

/**
 *
 * @author liujie 
 * @Date 2016年5月18日 下午2:24:24
 * @version 1.0
 */
public class CategoryData extends BaseQuery implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/** 主键 **/
	@Field
	private Long frontId;
	/** 后台分类 多个以空格分开*/	
	@Field
	private String backCategoryIds;
	/** 前台分类路径**/
	@Field
	private String frontPath;
	
	public Long getFrontId() {
		return frontId;
	}
	public void setFrontId(Long frontId) {
		this.frontId = frontId;
	}
	public String getBackCategoryIds() {
		return backCategoryIds;
	}
	public void setBackCategoryIds(String backCategoryIds) {
		this.backCategoryIds = backCategoryIds;
	}
	public String getFrontPath() {
		return frontPath;
	}
	public void setFrontPath(String frontPath) {
		this.frontPath = frontPath;
	}

}
