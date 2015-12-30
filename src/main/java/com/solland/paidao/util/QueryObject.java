package com.solland.paidao.util;

import java.io.Serializable;

/**
 * @author sunshibo
 */
public class QueryObject implements Serializable {

	/** 排序方式: asc */
	public static final String ORDER_ASC = "ASC";
	/** 排序方式: desc */
	public static final String ORDER_DESC = "DESC";

	private static final long serialVersionUID = 961699786815859106L;

	/**
	 * 页码
	 */
	private Integer pageNum ;
	
	/**
	 *页容量 
	 */
	private Integer pageSize ;
	
	/**
	 *偏移量 起始的记录数 
	 */
	private Integer pageOffset ;

	/**
	 * 排序参数
	 */
	private String sort ;

	public QueryObject() {
		
	}

	public QueryObject(Integer pageNum, Integer pageSize) {
		this.pageNum = pageNum;
		this.pageSize = pageSize = 10; //初始化是10条
		this.pageOffset = (pageNum-1)*pageSize ;
	}
	
	public Integer getPageOffset() {
		if(pageOffset != null ){
			return pageOffset;
		}
		return (pageNum-1)*pageSize ;
	}

	public Integer getPageNum() {
		if (pageNum == null || pageNum == 0) {
			pageNum = 1 ; //对象为空的话初始化值
		}
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

	public void setPageOffset(Integer pageOffset) {
		this.pageOffset = pageOffset;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}
}
