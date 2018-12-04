/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.wzdq.fengcai.dto;

import java.util.Date;

/**
 * 订单详情Entity
 * @author kk
 * @version 2018-11-26
 */
/**
 * @author Administrator
 *
 */
public class FOrderDetail {
	
	private String orderNo;		// 订单号
	private String goodsId;		// 商品ID
	private String userId;		// 用户id
	private String goodsName;		// 书籍名称
	private String goodsPrice;		// 单价
	private String goodsNum;		// 数量
	private String ratedStatus;		// 评价状态 0未评价 1已评价
	private String goodsImg;		// 图书封面
	private Date createDate; 
	
	
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	
	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	
	public String getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(String goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	
	public String getGoodsNum() {
		return goodsNum;
	}

	public void setGoodsNum(String goodsNum) {
		this.goodsNum = goodsNum;
	}
	
	public String getRatedStatus() {
		return ratedStatus;
	}

	public void setRatedStatus(String ratedStatus) {
		this.ratedStatus = ratedStatus;
	}
	
	public String getGoodsImg() {
		return goodsImg;
	}

	public void setGoodsImg(String goodsImg) {
		this.goodsImg = goodsImg;
	}
	
}