package com.wzdq.fengcai.dto;

import java.util.Date;
/**
 * 用户关系
 * @author Administrator
 *
 */
public class UserRelationDto extends Page{
	
	private String id;	//用户关系ID
	private String userName;	//用户账号（编号）
	private String realName;		//用户真实姓名
	private String superiorName;		//上级账号（编号）
	private String superiorRealName;	//上级真实姓名
	private String recomName;	//推荐人账号（编号）
	private String recomRealName;	//推荐人真实姓名
	private String area;	//区域（0：不分区，1：A区，2：B区）
	private String number;	//用户直接下级数量限制（2）默认0
	private String layer;	//平衡奖返现层数统计（返现到当前层）默认0
	private Date updateTime;		//更新时间
	private Date createTime;		//创建时间
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
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
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
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getLayer() {
		return layer;
	}
	public void setLayer(String layer) {
		this.layer = layer;
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
