package com.wzdq.fengcai.dto;

import java.util.Date;

/**
 * 用户角色
 * @author Administrator
 *
 */
public class UserRoleDto extends Page{
	
	private String roleId;	//角色ID
	private String roleName;	//角色名称
	private String roleDesc;	//角色描述
	private Date updateTime;	//更新时间
	private Date createTime;	//创建时间
	
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleDesc() {
		return roleDesc;
	}
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
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
