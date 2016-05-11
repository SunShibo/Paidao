package com.solland.paidao.entity;

import com.solland.paidao.common.base.BaseModel;

import java.io.Serializable;
import java.util.Date;

/**
 * 通知DO
 */
public class NotificationDO extends BaseModel implements Serializable {

	public static final String NOTICE_TYPE_HEAT = "heat" ;
	public static final String NOTICE_TYPE_COMMENT = "comment" ;

	public static final String STATUS_NEW = "new" ;
	public static final String STATUS_PASS = "pass" ;

	private Integer id;		// ID
	private Integer activityId ; //活动id
	private String noticeType ; // 通知类型
	private String noticeStatus ; // 通知状态
	private Integer doUserId ; // 操作用户id
	private Integer toUserId ; //指向用户id
	private Date createTime ; //创建时间
	private String content ; //内容

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getActivityId() {
		return activityId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	public String getNoticeType() {
		return noticeType;
	}

	public void setNoticeType(String noticeType) {
		this.noticeType = noticeType;
	}

	public String getNoticeStatus() {
		return noticeStatus;
	}

	public void setNoticeStatus(String noticeStatus) {
		this.noticeStatus = noticeStatus;
	}

	public Integer getDoUserId() {
		return doUserId;
	}

	public void setDoUserId(Integer doUserId) {
		this.doUserId = doUserId;
	}

	public Integer getToUserId() {
		return toUserId;
	}

	public void setToUserId(Integer toUserId) {
		this.toUserId = toUserId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
