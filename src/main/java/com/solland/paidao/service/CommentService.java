package com.solland.paidao.service;

import com.solland.paidao.entity.CommentDO;

import java.util.List;

/**
 * 评论
 * @author zhaojiafu
 *
 * 2016年1月8日 下午7:44:22
 */
public interface CommentService {
	/**
	 * 添加【评论】
	 * 2016年1月8日 下午7:46:32
	 * @author zhaojiafu
	 * @param commentDO
	 */
	void insertComment(CommentDO commentDO);

	List<CommentDO> getCommentlist (int activityId) ;
}
