package com.solland.paidao.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 收藏
 * @author zhaojiafu
 *
 * 2016年1月11日 下午3:50:52
 */
@SuppressWarnings("serial")
public class EnshrineDO implements Serializable {
	private int id;		// ID
	private int activityId;		// 活动ID
	private int activityAuthorId;		// 活动发布者的ID
	private int collectorId;		// 收藏者ID
	private Date createTime;	// 创建时间
	
	public EnshrineDO() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getActivityId() {
		return activityId;
	}

	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}

	public int getActivityAuthorId() {
		return activityAuthorId;
	}

	public void setActivityAuthorId(int activityAuthorId) {
		this.activityAuthorId = activityAuthorId;
	}

	public int getCollectorId() {
		return collectorId;
	}

	public void setCollectorId(int collectorId) {
		this.collectorId = collectorId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
