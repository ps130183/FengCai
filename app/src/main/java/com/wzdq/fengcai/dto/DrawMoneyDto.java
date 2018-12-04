package com.wzdq.fengcai.dto;

import java.util.Date;

/**
 * 申报员
 * @author Administrator
 *
 */
public class DrawMoneyDto extends Page{
	
	private String id;	//银行表ID
	private String userName;	//申报员编号（账号）用户账号（编号）
	private String cardHolder;		//持卡人,用户真实姓名
	private String bankNo;		//卡号
	private String phone;		//预留手机
	private String bank;	//银行名称
	private String money;	//提现金额
	private String state;	//状态(0：待审核，1：通过 2：拒绝)
	private String reason;	//拒绝原因
	private Date updateTime;		//更新时间
	private Date createTime;		//创建时间
	
	
	
	
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getCardHolder() {
		return cardHolder;
	}
	public void setCardHolder(String cardHolder) {
		this.cardHolder = cardHolder;
	}
	public String getBankNo() {
		return bankNo;
	}
	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
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
