package com.solland.paidao.util;

import java.util.List;

public class PageObject<E> {
	private List<E> datas;
	private int pageSize;
	private int offset;
	private int totalRecord;
	private int totalPage;
	private int pageNum ;
	private int prePage;//上一页
	private int nextPage;//下一页
	private int minPageNum;//最小页码
	private int maxPageNum;//最大页码
	private int pageSpanNum = 10;//页码跨度
	private int pageNumLocation = 6 ; //页码的居中位置,需要小于pageSpanNum

	/**
	 * @return the offset
	 */
	public int getOffset() {
		return offset;
	}

	/**
	 * @param offset
	 *            the offset to set
	 */
	public void setOffset(int offset) {
		this.offset = offset;
	}

	/**
	 * 分页中所存储的对象列表，使用了泛型
	 *
	 * @return 分页中的存储对象
	 */
	public List<E> getDatas() {
		return datas;
	}

	/**
	 * 分页中所存储的对象列表，使用了泛型
	 *
	 * @param datas
	 *            分页中的存储对象
	 */
	public void setDatas(List<E> datas) {
		this.datas = datas;
	}

	/**
	 * 每页显示多少条信息
	 *
	 * @return
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 设置每页显示的信息数
	 *
	 * @param pageSize
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 获取总记录数
	 *
	 * @return
	 */
	public int getTotalRecord() {
		return totalRecord;
	}

	/**
	 * 设置总记录数
	 *
	 * @param totalRecord
	 */
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	/**
	 * 获取总页数
	 *
	 * @return
	 */
	public int getTotalPage() {
		return totalPage;
	}

	/**
	 * 设置总页数
	 *
	 * @param totalPage
	 */
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPrePage() {
		prePage = (pageNum - 1) > 0 ? (pageNum - 1):1;
		return prePage;
	}

	public void setPrePage(int prePage) {
		this.prePage = prePage;
	}

	public int getNextPage() {
		nextPage = pageNum >= totalPage ? pageNum:(pageNum+1);
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	public int getMinPageNum() {

		if (totalPage <= pageSpanNum) {
			minPageNum = 1 ;
			return minPageNum;
		}
		if (pageNum <= pageNumLocation) {
			minPageNum = 1 ;
		}else {
			if (pageNum > (totalPage - (pageSpanNum -  pageNumLocation))) {
				minPageNum = totalPage - (pageSpanNum - 1); //最后一页
				return minPageNum ;
			}
			minPageNum =  pageNum - pageNumLocation + 1;
		}
		return minPageNum;

	}

	public int getMaxPageNum() {

		if (totalPage <= pageSpanNum) {
			maxPageNum = totalPage ;
			return maxPageNum;
		}
		if (pageNum > (totalPage - (pageSpanNum -  pageNumLocation))) {
			maxPageNum = totalPage ;
		}else {
			if (pageNum <= pageNumLocation) {
				maxPageNum = pageSpanNum ; //第一页
				return maxPageNum ;
			}
			maxPageNum =  pageNum + (pageSpanNum -  pageNumLocation) ;
		}
		return maxPageNum;
	}

}
