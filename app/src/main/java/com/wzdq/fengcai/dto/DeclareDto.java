package com.wzdq.fengcai.dto;

import java.util.Date;

/**
 * 申报员
 * @author Administrator
 *
 */
public class DeclareDto extends Page{
	
	private String declareId;	//申报员ID
	private String userName;	//申报员编号（账号）用户账号（编号）
	private String realName;		//申报员真实姓名,用户真实姓名
	private String userPhone;		//申报员手机号
	private String province;	//省
	private String city;	//城市
	private String district;	//地区
	private String state;	//状态（0：未审核，1：通过，2：拒绝）
	private Date updateTime;		//更新时间
	private Date createTime;		//创建时间
	public String getDeclareId() {
		return declareId;
	}
	public void setDeclareId(String declareId) {
		this.declareId = declareId;
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
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
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
