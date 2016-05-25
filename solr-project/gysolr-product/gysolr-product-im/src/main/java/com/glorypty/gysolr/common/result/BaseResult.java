/*
 *Project: gysolr-product-im
 *File: com.glorypty.gysolr.common.result.BaseResult.java <2016年5月13日>
 ****************************************************************
 * 版权所有@2016 国裕网络科技  保留所有权利.
 ***************************************************************/
package com.glorypty.gysolr.common.result;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author liujie
 * @Date 2016年5月13日 下午3:55:33
 * @version 1.0
 */
public class BaseResult<T> {
	private String desc;
	private String state;
    private Long total;
    
	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	private List<T> list = new ArrayList<T>();

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}
	

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
