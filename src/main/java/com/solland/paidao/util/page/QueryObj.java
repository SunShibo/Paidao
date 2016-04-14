package com.solland.paidao.util.page;

/**
 * Created by sunshibo on 2016/4/8.
 */
public class QueryObj {

    private Integer pageNum ;
    private Integer pageSize = 20;
    private Integer itemId ;
    private Integer pageOffset ;
    private String search ;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getPageOffset() {
        return (pageNum - 1) * pageSize;
    }

    public void setPageOffset(Integer pageOffset) {
        this.pageOffset = pageOffset;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
