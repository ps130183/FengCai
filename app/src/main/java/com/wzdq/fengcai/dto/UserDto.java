package com.wzdq.fengcai.dto;

import java.math.BigDecimal;
import java.util.Date;


/**
 * 用户
 * @author Administrator
 *
 */
public class UserDto extends Page{
	
	private String userId;	//用户ID
	private String userName;	//登录账号
	private String userPwd;		//登录密码
	private String userNo;		//用户编号
	private String recomName;	//推荐人账号（编号）
	private String recomRealName;	//推荐人真实姓名
	private String userSecret;	//交易安全码
	private UserRoleDto userRole;	//用户角色
	private String realName;	//真实姓名
	private String  identity;	//省份证号
	private String userSex;		//性别
	private String liveAddress;	//居住地
	private String nickName;	//昵称
	private Date brithday;		//生日
	private String userPhoto;	//头像
	private String userQQ;		//用户qq
	private String userWX;		//用户微信
	private String userPhone;	//用户手机
	private String userEmail;	//邮箱
	private String userScore;	//用户积分
	private String saleScore;	//历史消费积分
	private String lastIP;		//最后登录ip
	private BigDecimal userMoney;	//用户钱包
	private BigDecimal lockMoney;	//冻结金额
	private String userState;		//账号状态（0:未激活 1:停用 2:启用）
	private String payPwd;			//支付密码
	private String vip1;	//是否正式会员(0：不是，1：是)
	private String vip2;	//是否股东会员(0：不是，1：是)
	private String vip3;	//是否正式会员(0：不是，1：是)
	private String type;	//类型
	private Date starTime;		//开始时间
	private Date endTime;		//结束时间
	private Date updateTime;		//更新时间
	private Date activeTime;		//激活时间
	private Date createTime;		//创建时间
	
	
	
	public Date getStarTime() {
		return starTime;
	}
	public void setStarTime(Date starTime) {
		this.starTime = starTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Date getActiveTime() {
		return activeTime;
	}
	public void setActiveTime(Date activeTime) {
		this.activeTime = activeTime;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getVip1() {
		return vip1;
	}
	public void setVip1(String vip1) {
		this.vip1 = vip1;
	}
	public String getVip2() {
		return vip2;
	}
	public void setVip2(String vip2) {
		this.vip2 = vip2;
	}
	public String getVip3() {
		return vip3;
	}
	public void setVip3(String vip3) {
		this.vip3 = vip3;
	}
	public String getLiveAddress() {
		return liveAddress;
	}
	public void setLiveAddress(String liveAddress) {
		this.liveAddress = liveAddress;
	}
	public String getIdentity() {
		return identity;
	}
	public void setIdentity(String identity) {
		this.identity = identity;
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public String getUserSecret() {
		return userSecret;
	}
	public void setUserSecret(String userSecret) {
		this.userSecret = userSecret;
	}
	public UserRoleDto getUserRole() {
		return userRole;
	}
	public void setUserRole(UserRoleDto userRole) {
		this.userRole = userRole;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getUserSex() {
		return userSex;
	}
	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public Date getBrithday() {
		return brithday;
	}
	public void setBrithday(Date brithday) {
		this.brithday = brithday;
	}
	public String getUserPhoto() {
		return userPhoto;
	}
	public void setUserPhoto(String userPhoto) {
		this.userPhoto = userPhoto;
	}
	public String getUserQQ() {
		return userQQ;
	}
	public void setUserQQ(String userQQ) {
		this.userQQ = userQQ;
	}
	public String getUserWX() {
		return userWX;
	}
	public void setUserWX(String userWX) {
		this.userWX = userWX;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserScore() {
		return userScore;
	}
	public void setUserScore(String userScore) {
		this.userScore = userScore;
	}
	public String getSaleScore() {
		return saleScore;
	}
	public void setSaleScore(String saleScore) {
		this.saleScore = saleScore;
	}
	public String getLastIP() {
		return lastIP;
	}
	public void setLastIP(String lastIP) {
		this.lastIP = lastIP;
	}
	public BigDecimal getUserMoney() {
		return userMoney;
	}
	public void setUserMoney(BigDecimal userMoney) {
		this.userMoney = userMoney;
	}
	public BigDecimal getLockMoney() {
		return lockMoney;
	}
	public void setLockMoney(BigDecimal lockMoney) {
		this.lockMoney = lockMoney;
	}
	public String getUserState() {
		return userState;
	}
	public void setUserState(String userState) {
		this.userState = userState;
	}
	public String getPayPwd() {
		return payPwd;
	}
	public void setPayPwd(String payPwd) {
		this.payPwd = payPwd;
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
