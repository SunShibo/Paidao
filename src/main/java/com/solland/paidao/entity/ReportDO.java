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
public class ReportDO implements Serializable {

	public static final String TYPE_COMMENTS = "comment" ;
	public static final String TYPE_ACTIVITY = "activity" ;
	public static final String TYPE_PICTURE = "picture" ;


	private Integer id;		// ID
	private Integer activityId;		// 活动ID
	private Integer commentsId; //举报的评论id
	private Integer userId;		// 举报人ID
	private String content;		// 内容
	private Date createTime;	// 创建时间
	private String reportType; 	//举报类型
	
	public ReportDO() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	public Integer getCommentsId() {
		return commentsId;
	}

	public void setCommentsId(Integer commentsId) {
		this.commentsId = commentsId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
}
