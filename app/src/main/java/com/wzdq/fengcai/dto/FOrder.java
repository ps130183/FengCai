/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.wzdq.fengcai.dto;

import java.util.Date;
import java.util.List;

/**
 * 订单Entity
 * @author kk
 * @version 2018-11-22
 */
public class FOrder extends Page{
	
	private String orderNo;		// 订单号
	private String userId;		// 用户ID
	private String orderState;		// 订单状态（）
	private String goodsMoney;		// 商品总金额
	private String deliverType;		// 收货方式(	0:送货上门 1:自提)
	private String deliverMoney;		// 运费
	private String totalMoney;		// 订单总金额
	private String userName;		// 收件人名称
	private String userAddress;		// 收件人地址
	private String userPhone;		// 收件人手机号
	private String orderRemarks;		// 订单备注
	private String expressId;		// 快递公司ID
	private String expressNo;		// 快递号
	private Date receiveTime;		// 收货时间
	private Date deliveryTime;		// 发货时间
	private Date updateTime;		// 更新时间
	private Date createTime;		// 创建时间
	private List<FOrderDetail> orderDetailList;
	
	
	public List<FOrderDetail> getOrderDetailList() {
		return orderDetailList;
	}

	public void setOrderDetailList(List<FOrderDetail> orderDetailList) {
		this.orderDetailList = orderDetailList;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getOrderState() {
		return orderState;
	}

	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}
	
	public String getGoodsMoney() {
		return goodsMoney;
	}

	public void setGoodsMoney(String goodsMoney) {
		this.goodsMoney = goodsMoney;
	}
	
	public String getDeliverType() {
		return deliverType;
	}

	public void setDeliverType(String deliverType) {
		this.deliverType = deliverType;
	}
	
	public String getDeliverMoney() {
		return deliverMoney;
	}

	public void setDeliverMoney(String deliverMoney) {
		this.deliverMoney = deliverMoney;
	}
	
	public String getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(String totalMoney) {
		this.totalMoney = totalMoney;
	}
	
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	
	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	
	public String getOrderRemarks() {
		return orderRemarks;
	}

	public void setOrderRemarks(String orderRemarks) {
		this.orderRemarks = orderRemarks;
	}
	

	public String getExpressId() {
		return expressId;
	}

	public void setExpressId(String expressId) {
		this.expressId = expressId;
	}
	
	public String getExpressNo() {
		return expressNo;
	}

	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
	}

	public Date getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(Date receiveTime) {
		this.receiveTime = receiveTime;
	}

	public Date getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}