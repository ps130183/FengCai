package com.wzdq.fengcai.dto;

import java.util.Date;

/**
 * 用户资金（资产）
 * @author Administrator
 *
 */
public class UserMoneyDto {
	
	private String userName;	//用户账号（编号）
	private String salaryMoney;	//工资
	private String shopCur;	//购物币
	private String awardCur;	//奖金币
	private String billCur;	//报单币
	private String state;	//状态（0：启动 1：禁用）
	private Date updateTime;	//更新时间
	private Date createTime;	//创建时间
	
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getSalaryMoney() {
		return salaryMoney;
	}
	public void setSalaryMoney(String salaryMoney) {
		this.salaryMoney = salaryMoney;
	}
	public String getShopCur() {
		return shopCur;
	}
	public void setShopCur(String shopCur) {
		this.shopCur = shopCur;
	}
	public String getAwardCur() {
		return awardCur;
	}
	public void setAwardCur(String awardCur) {
		this.awardCur = awardCur;
	}
	public String getBillCur() {
		return billCur;
	}
	public void setBillCur(String billCur) {
		this.billCur = billCur;
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
