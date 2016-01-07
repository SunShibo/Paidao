package com.solland.paidao.entity;

/**
 * 赞
 * @author zhaojiafu
 *
 * 2016年1月7日 下午5:28:58
 */
public class PraiseDO {
	private int id;		// ID
	private int bePraiseUserId;		// 被赞的用户ID
	private int doPraiseUserId;		// 点赞的用户ID
	private String createTime;		// 创建时间
	
	public PraiseDO() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBePraiseUserId() {
		return bePraiseUserId;
	}

	public void setBePraiseUserId(int bePraiseUserId) {
		this.bePraiseUserId = bePraiseUserId;
	}

	public int getDoPraiseUserId() {
		return doPraiseUserId;
	}

	public void setDoPraiseUserId(int doPraiseUserId) {
		this.doPraiseUserId = doPraiseUserId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
}
