package com.solland.paidao.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 加热（点赞）
 * @author zhaojiafu
 *
 * 2016年1月11日 下午2:59:39
 */
@SuppressWarnings("serial")
public class HeatDO implements Serializable {
	private int id;		// ID
	private int triggerUserId;		// 触发用户，（是谁增加的）
	private int increaseHeatValue;	// 增加的热度值
	private String increaseType;		// 加热的类型，（用户加热、系统加热、系统减热、）
	private Date createTime;		// 创建时间
	private Date modifyTime;		// 修改时间
	private int lastTimeHeatValue;	// 最后一次的热度值
	private int activityId;		// 活动ID
	private int authorId;		// 作者id(发布动态圈的用户ID);
	
	public HeatDO() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTriggerUserId() {
		return triggerUserId;
	}

	public void setTriggerUserId(int triggerUserId) {
		this.triggerUserId = triggerUserId;
	}

	public int getIncreaseHeatValue() {
		return increaseHeatValue;
	}

	public void setIncreaseHeatValue(int increaseHeatValue) {
		this.increaseHeatValue = increaseHeatValue;
	}

	public String getIncreaseType() {
		return increaseType;
	}

	public void setIncreaseType(String increaseType) {
		this.increaseType = increaseType;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public int getLastTimeHeatValue() {
		return lastTimeHeatValue;
	}

	public void setLastTimeHeatValue(int lastTimeHeatValue) {
		this.lastTimeHeatValue = lastTimeHeatValue;
	}

	public int getActivityId() {
		return activityId;
	}

	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
}
