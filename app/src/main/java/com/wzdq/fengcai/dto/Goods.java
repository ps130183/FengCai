/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.wzdq.fengcai.dto;

/**
 * 商品Entity
 * @author kk
 * @version 2018-11-20
 */
public class Goods extends Page{
	
	private String goodsId;		// 商品ID
	private String goodsSn;		// 商品编号
	private String goodsNo;		// 商品货号
	private String goodsName;		// 商品名称
	private String goodsImg;		// 商品图片
	private String goodsHead;		// goods_head
	private String goodsDesc;		// 商品详情
	private String goodsPrice;		// 商品原价
	private String salesPrice;		// 促销价格
	private String goodsStock;		// 商品总库存
	private String warnStock;		// 预警库存
	private String goodsTips;		// 促销信息
	private String isSale;		// 是否上架（0:不上架 1:上架）
	private String isBest;		// 是否精品（0:否 1:是）
	private String isHot;		// 是否热销（0:否 1:是）
	private String isNew;		// 是否新品（0:否 1:是）
	private String isRecom;		// 是否推荐
	private String goodsCatid;		// 商品分类ID
	private String goodsState;		// 商品状态（-1:违规 0:未审核 1:已审核）
	private String saleNum;		// 总销量
	private String saleTime;		// 上架时间
	private String visitNum;		// 访问次数
	private String appraiseNum;		// 评价数
	private String galley;		// 商品相册
	private String updateTime;		// 更新时间
	private String createTime;		// 创建时间
	

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	
	public String getGoodsSn() {
		return goodsSn;
	}

	public void setGoodsSn(String goodsSn) {
		this.goodsSn = goodsSn;
	}
	
	public String getGoodsNo() {
		return goodsNo;
	}

	public void setGoodsNo(String goodsNo) {
		this.goodsNo = goodsNo;
	}
	
	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	
	public String getGoodsImg() {
		return goodsImg;
	}

	public void setGoodsImg(String goodsImg) {
		this.goodsImg = goodsImg;
	}
	
	public String getGoodsHead() {
		return goodsHead;
	}

	public void setGoodsHead(String goodsHead) {
		this.goodsHead = goodsHead;
	}
	
	public String getGoodsDesc() {
		return goodsDesc;
	}

	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
	}
	
	public String getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(String goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	
	public String getSalesPrice() {
		return salesPrice;
	}

	public void setSalesPrice(String salesPrice) {
		this.salesPrice = salesPrice;
	}
	
	public String getGoodsStock() {
		return goodsStock;
	}

	public void setGoodsStock(String goodsStock) {
		this.goodsStock = goodsStock;
	}
	
	public String getWarnStock() {
		return warnStock;
	}

	public void setWarnStock(String warnStock) {
		this.warnStock = warnStock;
	}
	
	public String getGoodsTips() {
		return goodsTips;
	}

	public void setGoodsTips(String goodsTips) {
		this.goodsTips = goodsTips;
	}
	
	public String getIsSale() {
		return isSale;
	}

	public void setIsSale(String isSale) {
		this.isSale = isSale;
	}
	
	public String getIsBest() {
		return isBest;
	}

	public void setIsBest(String isBest) {
		this.isBest = isBest;
	}
	
	public String getIsHot() {
		return isHot;
	}

	public void setIsHot(String isHot) {
		this.isHot = isHot;
	}
	
	public String getIsNew() {
		return isNew;
	}

	public void setIsNew(String isNew) {
		this.isNew = isNew;
	}
	
	public String getIsRecom() {
		return isRecom;
	}

	public void setIsRecom(String isRecom) {
		this.isRecom = isRecom;
	}
	
	public String getGoodsCatid() {
		return goodsCatid;
	}

	public void setGoodsCatid(String goodsCatid) {
		this.goodsCatid = goodsCatid;
	}
	
	public String getGoodsState() {
		return goodsState;
	}

	public void setGoodsState(String goodsState) {
		this.goodsState = goodsState;
	}
	
	public String getSaleNum() {
		return saleNum;
	}

	public void setSaleNum(String saleNum) {
		this.saleNum = saleNum;
	}
	
	public String getSaleTime() {
		return saleTime;
	}

	public void setSaleTime(String saleTime) {
		this.saleTime = saleTime;
	}
	
	public String getVisitNum() {
		return visitNum;
	}

	public void setVisitNum(String visitNum) {
		this.visitNum = visitNum;
	}
	
	public String getAppraiseNum() {
		return appraiseNum;
	}

	public void setAppraiseNum(String appraiseNum) {
		this.appraiseNum = appraiseNum;
	}
	
	public String getGalley() {
		return galley;
	}

	public void setGalley(String galley) {
		this.galley = galley;
	}
	
	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
}