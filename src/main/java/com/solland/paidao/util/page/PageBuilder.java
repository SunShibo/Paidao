package com.solland.paidao.util.page;

import java.util.List;

public class PageBuilder<T> {
	
	@SuppressWarnings("unchecked")
	public static <T> Page savePage(List<T> list , QueryObj query) {
		Page page = new Page() ;
		page.setDatas(list) ;
		page.setPageNum(query.getPageNum());
		page.setPageSize(query.getPageSize());
		return page ;
	}

}
