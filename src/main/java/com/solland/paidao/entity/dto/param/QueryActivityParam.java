package com.solland.paidao.entity.dto.param;

import com.solland.paidao.common.base.BaseModel;

/**
 * Created by sunshibo on 2016/4/8.
 */
public class QueryActivityParam extends BaseModel {

    private String longitude;	// 经度
    private String latitude;	// 纬度
    private int userId;


    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
