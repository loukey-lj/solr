/*
 *Project: gysolr-product-im
 *File: com.glorypty.gysolr.product.domain.GoodsOrderByEmue.java <2016年5月19日>
 ****************************************************************
 * 版权所有@2016 国裕网络科技  保留所有权利.
 ***************************************************************/
package com.glorypty.gysolr.product.domain;


/**
 *
 * @author liujie 
 * @Date 2016年5月19日 上午9:25:38
 * @version 1.0
 */
public enum GoodsOrderProperty {
    
	DEFAULT(0,"deault"),
	PRICE(1,"priceMarket"),
	SALESIZE(2,"saleSize"),
	UPDATETIME(3,"updateTime")
	;
	
    private int index;
    private String code;
   
    
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	private GoodsOrderProperty(Integer index, String code) {
		this.index = index;
		this.code = code;
	}
	
    
}
