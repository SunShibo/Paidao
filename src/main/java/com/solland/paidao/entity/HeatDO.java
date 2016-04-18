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

	public static final String TYPE_USER_ADD = "user_add" ;
	public static final String TYPE_SYSTEM_ADD = "system_add" ;
	public static final String TYPE_SYSTEM_SUBTRACTION = "system_subtractio" ;

	private int id;		// ID
	private int userId;		// 触发用户，（是谁增加的）
	private int heatValue;	// 增加的热度值
	private String increaseType;		// 加热的类型，（UA-用户加热、SA系统加热、SS-系统减热、）
	private Date createTime;		// 创建时间
	private Date modifyTime;		// 修改时间
	private int lastTimeHeatValue;	// 最后一次的热度值
	private int activityId;		// 活动ID
	private int authorId ;
	
	public HeatDO() {
		// TODO Auto-generated constructor stub
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getHeatValue() {
		return heatValue;
	}

	public void setHeatValue(int heatValue) {
		this.heatValue = heatValue;
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
}
