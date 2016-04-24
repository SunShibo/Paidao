package com.solland.paidao.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 评论
 * @author zhaojiafu
 *
 * 2016年1月8日 下午7:24:30
 */
@SuppressWarnings("serial")
public class CommentDO implements Serializable {
	private int id;		// ID
	private int activityId;		// 活动ID
	private int criticId;		// 评论人ID
	private int replyerId;		// 被回复人ID
	private String content;		// 内容
	private Date createTime;	// 创建时间
	
	public CommentDO() {
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

	public int getCriticId() {
		return criticId;
	}

	public void setCriticId(int criticId) {
		this.criticId = criticId;
	}

	public int getReplyerId() {
		return replyerId;
	}

	public void setReplyerId(int replyerId) {
		this.replyerId = replyerId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
