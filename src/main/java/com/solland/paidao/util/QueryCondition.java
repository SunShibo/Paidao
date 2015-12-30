package com.solland.paidao.util;

import java.util.Date;

/**
 * 查询条件
 * 
 * @author houzhiqing
 * 
 */
public class QueryCondition {

	/**
	 * 查询开始时间
	 */
	private Date beginTime;

	/**
	 * 查询结束时间
	 */
	private Date endTime;

	/**
	 * 区域名称
	 */
	private String areaName;
	/**
	 * 管理员用户Id
	 */
	private Integer userId;
	/**
	 * 客户姓名
	 */
	private String clientName;
	private String mobile;
	/**
	 * 产品名称
	 */
	private String productName;

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Override
	public String toString() {
		return "QueryCondition [areaName=" + areaName + ", beginTime="
				+ beginTime + ", clientName=" + clientName + ", endTime="
				+ endTime + ", mobile=" + mobile + ", productName="
				+ productName + ", userId=" + userId + "]";
	}

}
