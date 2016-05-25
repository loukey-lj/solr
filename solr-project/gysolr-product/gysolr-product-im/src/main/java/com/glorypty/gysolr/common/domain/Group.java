package com.glorypty.gysolr.common.domain;

import java.util.ArrayList;
import java.util.List;

/*
 * 单个分组信息
 */
public class Group {
	private String groupName;
	private List<GroupDetail> groupDetailList = new ArrayList<GroupDetail>();
	
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public List<GroupDetail> getGroupDetailList() {
		return groupDetailList;
	}
	public void setGroupDetailList(List<GroupDetail> groupDetailList) {
		this.groupDetailList = groupDetailList;
	}
	
}