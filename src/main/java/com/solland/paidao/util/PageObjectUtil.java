package com.solland.paidao.util;

import java.util.List;

public class PageObjectUtil<T> {
	
	@SuppressWarnings("unchecked")
	public PageObject SavePageObject(int count,List<T> list,QueryObject queryInfo){
		PageObject page = new PageObject() ;
		int totalPage = 0 ;
		int pageSize = queryInfo.getPageSize() ;
		if(count%pageSize==0){
			totalPage = count/pageSize; 
		}else{
			totalPage = count/pageSize+1; 
		}
		page.setDatas(list) ;
		page.setOffset(queryInfo.getPageOffset()) ;
		page.setTotalPage(totalPage) ;
		page.setTotalRecord(count) ;
		page.setPageSize(pageSize) ;
		page.setPageNum(queryInfo.getPageNum());
		
		return page ;
	}
}
