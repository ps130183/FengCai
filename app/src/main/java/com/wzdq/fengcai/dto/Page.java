package com.wzdq.fengcai.dto;

import java.util.Date;

/**
 * 分页
 * @author Administrator
 *
 */
public class Page {

	private int totalNum;//总条数
	private int totalPage;//总页数
	private int pageNum;//当前页
	private int limitNum;//当前页显示条数
	private int beginNum;//开始数
	private Date starTime;//开始时间
	private Date endTime; //结束时间
	
	
	
	public int getBeginNum() {
		return beginNum;
	}
	public void setBeginNum(int beginNum) {
		this.beginNum = beginNum;
	}
	public int getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
		//计算总页数
		if (totalNum % this.limitNum == 0) {
			this.totalPage =(totalNum /this.limitNum);
		}else{
			this.totalPage =(totalNum /this.limitNum)+1;
		}
		//判断当前页数是否超出
		if (this.pageNum>0 && this.pageNum<=this.totalPage) {
			this.beginNum=(this.pageNum-1)*this.limitNum;
		}else{
			this.beginNum=0;
		}
		
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getLimitNum() {
		return limitNum;
	}
	public void setLimitNum(int limitNum) {
		this.limitNum = limitNum;
	}
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
	
	
}
