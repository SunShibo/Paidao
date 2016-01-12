package com.solland.paidao.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 关注/追随
 * @author zhaojiafu
 *
 * 2016年1月12日 下午2:57:30
 */
@SuppressWarnings("serial")
public class FollowDO implements Serializable {
	private int id;		// ID
	private int beFollowerId;		// 被关注人ID
	private int followerId;		// 追随者ID
	private Date createTime;		// 创建时间
	
	public FollowDO() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBeFollowerId() {
		return beFollowerId;
	}

	public void setBeFollowerId(int beFollowerId) {
		this.beFollowerId = beFollowerId;
	}

	public int getFollowerId() {
		return followerId;
	}

	public void setFollowerId(int followerId) {
		this.followerId = followerId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
