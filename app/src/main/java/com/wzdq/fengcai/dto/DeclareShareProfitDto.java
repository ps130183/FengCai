package com.wzdq.fengcai.dto;

import java.util.Date;

/**
 * 报单进分润
 * @author Administrator
 *
 */
public class DeclareShareProfitDto extends Page{
	
	private String declareShareProfitId;	//报单分润ID
	private DeclareDto declareDto;	//申报员id
	private String userName;	//进分润用户账号
	private String realName;		//进分润用户真实姓名
	private String userPhone;		//进分润用户电话
	private String money;	//申请金额
	private String state;	//状态（0：未审核，1：通过，2：拒绝）
	private Date updateTime;		//更新时间
	private Date createTime;		//创建时间
	public String getDeclareShareProfitId() {
		return declareShareProfitId;
	}
	public void setDeclareShareProfitId(String declareShareProfitId) {
		this.declareShareProfitId = declareShareProfitId;
	}
	public DeclareDto getDeclareDto() {
		return declareDto;
	}
	public void setDeclareDto(DeclareDto declareDto) {
		this.declareDto = declareDto;
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
