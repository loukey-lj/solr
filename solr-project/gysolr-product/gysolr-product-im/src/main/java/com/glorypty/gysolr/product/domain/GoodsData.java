/*
 *Project: gysolr-product-im
 *File: com.glorypty.gysolr.domain.GoodsData.java <2016年5月9日>
 ****************************************************************
 * 版权所有@2016 国裕网络科技  保留所有权利.
 ***************************************************************/
package com.glorypty.gysolr.product.domain;

import java.util.Date;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.beans.Field;

import com.glorypty.gysolr.common.domain.BaseQuery;


/**
 *
 * @author liujie 
 * @Date 2016年5月9日 下午8:06:12
 * @version 1.0
 */
public class GoodsData extends BaseQuery implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 主键 **/
	@Field
	private Long id;
	/** 商品名字 **/
	@Field
	private String title;
	/** 店铺id(供应商id) **/
	@Field
	private Long shopId;
	/** 商品销售价 **/
	@Field
	private Double priceMarket;
	/** 店铺名字 **/
	@Field
	private String shopName;
//	/** 规格 **/
//	private String spec;
	/** 生产商家 **/
	@Field
	private String sccj;
	/** 销售区域 **/
	@Field
	private String xsqy;
	/** 经营范围 **/
	@Field
	private String jyfw;
	/** 主要功效 **/
	@Field
	private String zygx;
	/** 商品图片 **/
	@Field
	private String pic;
	/** 标准库Id **/
	@Field
	private Long spuId;
	/** 销量**/
	@Field
	private Integer saleSize;
	/** 剂型 **/
	@Field
	private String jx;
	/** 品牌 **/
	@Field
	private String pp;
	/** 修改时间 **/
	@Field
	private Long updateTime;
	/** 前台分类 **/
	@Field
	private String frontCid;
	/** 店铺分类 **/
	@Field
	private String shopCid;
	
	private Date updateDate;
	
	/** 商品Id集合 **/
	private List<Long> goodsId;
	
	/** 可售状态enum:0下架1上架待审2在售3冻结4删除 */		
	private Integer statusSale;
	
	/** 排序属性 **/
	private GoodsOrderProperty  goodsOrderProperty = GoodsOrderProperty.DEFAULT;
	
	/** 排序顺序 **/
	private ORDER  orderBy = ORDER.asc;
	
	public ORDER getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(ORDER orderBy) {
		this.orderBy = orderBy;
	}
	public GoodsOrderProperty getGoodsOrderProperty() {
		return goodsOrderProperty;
	}
	public void setGoodsOrderProperty(GoodsOrderProperty goodsOrderProperty) {
		this.goodsOrderProperty = goodsOrderProperty;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public Integer getStatusSale() {
		return statusSale;
	}
	public void setStatusSale(Integer statusSale) {
		this.statusSale = statusSale;
	}
	public List<Long> getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(List<Long> goodsId) {
		this.goodsId = goodsId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Long getShopId() {
		return shopId;
	}
	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}
	public Double getPriceMarket() {
		return priceMarket;
	}
	public void setPriceMarket(Double priceMarket) {
		this.priceMarket = priceMarket;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
//	public String getSpec() {
//		return spec;
//	}
//	public void setSpec(String spec) {
//		this.spec = spec;
//	}
	public String getSccj() {
		return sccj;
	}
	public void setSccj(String sccj) {
		this.sccj = sccj;
	}
	public String getXsqy() {
		return xsqy;
	}
	public void setXsqy(String xsqy) {
		this.xsqy = xsqy;
	}
	public String getJyfw() {
		return jyfw;
	}
	public void setJyfw(String jyfw) {
		this.jyfw = jyfw;
	}
	public String getZygx() {
		return zygx;
	}
	public void setZygx(String zygx) {
		this.zygx = zygx;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public Long getSpuId() {
		return spuId;
	}
	public void setSpuId(Long spuId) {
		this.spuId = spuId;
	}
	public Integer getSaleSize() {
		return saleSize;
	}
	public void setSaleSize(Integer saleSize) {
		this.saleSize = saleSize;
	}
	public String getJx() {
		return jx;
	}
	public void setJx(String jx) {
		this.jx = jx;
	}
	public String getPp() {
		return pp;
	}
	public void setPp(String pp) {
		this.pp = pp;
	}
	public Long getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}
	public String getFrontCid() {
		return frontCid;
	}
	public void setFrontCid(String frontCid) {
		this.frontCid = frontCid;
	}
	public String getShopCid() {
		return shopCid;
	}
	public void setShopCid(String shopCid) {
		this.shopCid = shopCid;
	}
	@Override
	public String toString() {
		return "GoodsData [id=" + id + ", title=" + title + ", shopId="
				+ shopId + ", priceMarket=" + priceMarket + ", shopName="
				+ shopName + ", sccj=" + sccj + ", xsqy=" + xsqy + ", jyfw="
				+ jyfw + ", zygx=" + zygx + ", pic=" + pic + ", spuId=" + spuId
				+ ", saleSize=" + saleSize + ", jx=" + jx + ", pp=" + pp
				+ ", updateTime=" + updateTime + ", frontCid=" + frontCid
				+ ", shopCid=" + shopCid + ", goodsId=" + goodsId
				+ ", statusSale=" + statusSale + "]";
	}
	
}
