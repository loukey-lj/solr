package com.glorypty.gysolr.common.solr;



/**
 * solr core枚举
 * @author liujie<2016年5月17日>
 */
public enum SolrCoreCodeEnum{
	GOODS(0),
	CATEGORY(1);
	private int index;

	public int getIndex() {
		return index;
	}

	private SolrCoreCodeEnum(int index) {
		this.index = index;
	}
}
