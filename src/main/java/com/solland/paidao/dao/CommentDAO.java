package com.solland.paidao.dao;

import com.solland.paidao.entity.CommentDO;

/**
 * 评论
 * @author zhaojiafu
 *
 * 2016年1月8日 下午7:44:22
 */
public interface CommentDAO {
	/**
	 * 添加【评论】
	 * 2016年1月8日 下午7:46:32
	 * @author zhaojiafu
	 * @param commentDO
	 */
	void insertComment(CommentDO commentDO);
}
