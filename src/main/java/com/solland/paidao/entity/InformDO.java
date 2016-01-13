package com.solland.paidao.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 举报
 * @author zhaojiafu
 *
 * 2016年1月13日 下午4:04:06
 */
@SuppressWarnings("serial")
public class InformDO implements Serializable {
	private int id;		// ID
	private int activityId;		// 活动ID
	private int activityAuthorId;		// 活动的作者ID
	private int informerId;		// 举报人ID
	private Date createTime;	// 创建时间
	
	public InformDO() {
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

	public int getInformerId() {
		return informerId;
	}

	public void setInformerId(int informerId) {
		this.informerId = informerId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
