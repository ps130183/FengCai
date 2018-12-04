package com.wzdq.fengcai.dto;
/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */


import java.util.Date;

/**
 * 分润（分红）Entity
 * @author Administrator
 *
 */
public class ShareProfitDto{
	
	private static final long serialVersionUID = 1L;
	private String id;		// 用户id 
	private String parentId;		// 上级id
	private String userName;	//用户账号
	private String realName;	//真实姓名
	private String number;		// 下线人数限制2
	private String  backNumber;		// 返现次数（返现规则判定条件）
	
	private Date createTime;	//创建时间
	private Date updateTime;	//更新时间
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getBackNumber() {
		return backNumber;
	}
	public void setBackNumber(String backNumber) {
		this.backNumber = backNumber;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}