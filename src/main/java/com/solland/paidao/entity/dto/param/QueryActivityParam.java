package com.solland.paidao.entity.dto.param;

import com.solland.paidao.common.base.BaseModel;

/**
 * Created by sunshibo on 2016/4/8.
 */
public class QueryActivityParam extends BaseModel{

    private Integer pageNum ;

    private Integer beginActivityId ;

    private String search ;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getBeginActivityId() {
        return beginActivityId;
    }

    public void setBeginActivityId(Integer beginActivityId) {
        this.beginActivityId = beginActivityId;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
