package com.solland.paidao.entity;

import com.solland.paidao.common.base.BaseModel;

import java.util.Date;

/**
 * Created by sun on 2016/4/19.
 */
public class RemoveActivityDO extends BaseModel{

    private int id ;
    private Integer userId;
    private Integer activityId;
    private Date createTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
