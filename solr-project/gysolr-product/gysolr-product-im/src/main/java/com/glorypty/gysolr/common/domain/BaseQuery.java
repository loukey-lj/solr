/*
 *Project: gysolr-product-im
 *File: com.glorypty.gysolr.domain.BaseQuery.java <2016年5月10日>
 ****************************************************************
 * 版权所有@2016 国裕网络科技  保留所有权利.
 ***************************************************************/
package com.glorypty.gysolr.common.domain;

/**
 *
 * @author liujie 
 * @Date 2016年5月10日 上午10:10:38
 * @version 1.0
 */
public class BaseQuery  implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer page = 1;
	private Integer pageSize;
	
	private Double rangStart;
	private Double rangEnd;
	
	
	
	/*public Integer getStartNum() {
		return startNum;
	}

	public void setStartNum(Integer startNum) {
		this.startNum = startNum;
	}*/
	
	public Double getRangStart() {
		return rangStart;
	}
	public void setRangStart(Double rangStart) {
		this.rangStart = rangStart;
	}
	public Double getRangEnd() {
		return rangEnd;
	}
	public void setRangEnd(Double rangEnd) {
		this.rangEnd = rangEnd;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

}
