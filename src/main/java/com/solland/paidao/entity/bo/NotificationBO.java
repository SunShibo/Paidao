package com.solland.paidao.entity.bo;

import com.solland.paidao.common.base.BaseModel;

import java.io.Serializable;
import java.util.Date;

/**
 * 通知DO
 */
public class NotificationBO extends BaseModel implements Serializable {

	private Integer id;		// ID
	private Integer activityId ; //活动id
	private String noticeType ; // 通知类型
	private String noticeStatus ; // 通知状态
	private Integer doUserId ; // 操作用户id
	private Integer toUserId ; //指向用户id
	private Date createTime ; //创建时间
	private String content ; //内容

	private String title ;
	private String description ;
	private int heatValue ;
	private String commentNum ;
	private String headPortrait ;
	private String mediaType ;
	private String mediaPaths ;
	private String thumbnailUrl ;
	private String position ;

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getHeatValue() {
		return heatValue;
	}

	public void setHeatValue(int heatValue) {
		this.heatValue = heatValue;
	}

	public String getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(String commentNum) {
		this.commentNum = commentNum;
	}

	public String getHeadPortrait() {
		return headPortrait;
	}

	public void setHeadPortrait(String headPortrait) {
		this.headPortrait = headPortrait;
	}

	public String getMediaType() {
		return mediaType;
	}

	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}

	public String getMediaPaths() {
		return mediaPaths;
	}

	public void setMediaPaths(String mediaPaths) {
		this.mediaPaths = mediaPaths;
	}

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
}
