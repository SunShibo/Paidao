package com.solland.paidao.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.solland.paidao.dao.CommentDAO;
import com.solland.paidao.entity.CommentDO;
import com.solland.paidao.service.CommentService;

/**
 * 评论
 * @author zhaojiafu
 *
 * 2016年1月8日 下午7:47:49
 */
@Service( value = "commentService" )
public class CommentServiceImpl implements CommentService {
	@Resource
	private CommentDAO commentDAO;

	@Override
	public void insertComment(CommentDO commentDO) {
		commentDAO.insertComment(commentDO);
	}

}
