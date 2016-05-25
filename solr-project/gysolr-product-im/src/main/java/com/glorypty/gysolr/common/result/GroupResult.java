/*
 *Project: gysolr-product-im
 *File: com.glorypty.gysolr.common.result.GroupResult.java <2016年5月19日>
 ****************************************************************
 * 版权所有@2016 国裕网络科技  保留所有权利.
 ***************************************************************/
package com.glorypty.gysolr.common.result;

import java.util.ArrayList;
import java.util.List;

import com.glorypty.gysolr.common.domain.Group;

/**
 *
 * @author liujie 
 * @Date 2016年5月19日 上午10:30:48
 * @version 1.0
 */
public class GroupResult<T>  extends  BaseResult<T>{
	/*
	 * 分组信息
	 */
	private List<Group> groups = new ArrayList<Group>();
	
	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}
}
