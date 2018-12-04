package com.wzdq.fengcai.dto;

import java.util.Date;


/**
 * 用户资金记录（资产）
 * @author Administrator
 *
 */
public class MoneyRecordDto extends Page{
	
	private String userName;	//用户账号（编号）
	private String recordType;	//交易类型（例：充值，缴费，报单）
	private String moneyRecord;	//资金记录
	private String moneyTpye;	//币种（0：工资 1：购物 2：报单币 3：奖金币）
	private String flowDirection;	//资金流向（0：出账 1：入账）
	private String money;	//单次交易金额记录
	private Date updateTime;	//更新时间
	private Date createTime;	//创建时间
	
	
	
	public String getRecordType() {
		return recordType;
	}
	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getMoneyRecord() {
		return moneyRecord;
	}
	public void setMoneyRecord(String moneyRecord) {
		this.moneyRecord = moneyRecord;
	}
	public String getMoneyTpye() {
		return moneyTpye;
	}
	public void setMoneyTpye(String moneyTpye) {
		this.moneyTpye = moneyTpye;
	}
	public String getFlowDirection() {
		return flowDirection;
	}
	public void setFlowDirection(String flowDirection) {
		this.flowDirection = flowDirection;
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
