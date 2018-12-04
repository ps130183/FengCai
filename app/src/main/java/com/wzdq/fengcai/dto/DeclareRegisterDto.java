package com.wzdq.fengcai.dto;

import java.util.Date;

/**
 * 报单申请注册用户
 * @author Administrator
 *
 */
public class DeclareRegisterDto extends Page{
	
	private String declareRegisterId;	//申报注册会员id
	private DeclareDto declareDto;	//申报员id
	private String recomName;	//推荐人账号
	private String recomRealName;	//推荐人真实姓名
	private String userName;	//注册用户账号
	private String realName;		//注册用户真实姓名
	private String userPhone;		//注册用户手机号
	private String superiorName;	//直接上级账号
	private String superiorRealName;	//直接上级真实姓名
	private String area;	//区域（0：不分区，1：A区，2：B区）
	private String money;	//申请金额
	private String state;	//状态（0：未审核，1：通过，2：拒绝）
	private Date updateTime;		//更新时间
	private Date createTime;		//创建时间
	public String getDeclareRegisterId() {
		return declareRegisterId;
	}
	public void setDeclareRegisterId(String declareRegisterId) {
		this.declareRegisterId = declareRegisterId;
	}
	public DeclareDto getDeclareDto() {
		return declareDto;
	}
	public void setDeclareDto(DeclareDto declareDto) {
		this.declareDto = declareDto;
	}
	public String getRecomName() {
		return recomName;
	}
	public void setRecomName(String recomName) {
		this.recomName = recomName;
	}
	public String getRecomRealName() {
		return recomRealName;
	}
	public void setRecomRealName(String recomRealName) {
		this.recomRealName = recomRealName;
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
	public String getSuperiorName() {
		return superiorName;
	}
	public void setSuperiorName(String superiorName) {
		this.superiorName = superiorName;
	}
	public String getSuperiorRealName() {
		return superiorRealName;
	}
	public void setSuperiorRealName(String superiorRealName) {
		this.superiorRealName = superiorRealName;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
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
