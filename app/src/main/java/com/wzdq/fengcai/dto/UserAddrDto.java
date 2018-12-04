package com.wzdq.fengcai.dto;

import java.util.Date;

/**
 * 用户地址
 * @author Administrator
 *
 */
public class UserAddrDto {
	
	private String addrId;	//地址ID
	private String name;	//收件人
	private String phone;	//收件人手机号
	private String addrName;	//省市县
	private String detailedAddr;	//详细地址
	private UserDto userDto;	//用户ID
	private String isDefault;	//是否默认地址(0: 默认 1：不默认)
	private String state;	//地址状态（0：启用，1:假删）
	private Date updateTime;	//更新时间
	private Date createTime;	//创建时间
	
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getAddrId() {
		return addrId;
	}
	public void setAddrId(String addrId) {
		this.addrId = addrId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddrName() {
		return addrName;
	}
	public void setAddrName(String addrName) {
		this.addrName = addrName;
	}
	public String getDetailedAddr() {
		return detailedAddr;
	}
	public void setDetailedAddr(String detailedAddr) {
		this.detailedAddr = detailedAddr;
	}
	public UserDto getUserDto() {
		return userDto;
	}
	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}
	public String getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
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
