package com.solland.paidao.entity;

import com.solland.paidao.common.base.BaseModel;

import java.util.Date;

/**
 * Created by sun on 2016/4/19.
 */
public class RemoveActivityDO extends BaseModel{

    private int id ;
    private Integer user_id;
    private Integer activity_id;
    private Date create_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getActivity_id() {
        return activity_id;
    }

    public void setActivity_id(Integer activity_id) {
        this.activity_id = activity_id;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
}
