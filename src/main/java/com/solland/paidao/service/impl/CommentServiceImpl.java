package com.solland.paidao.service.impl;

import javax.annotation.Resource;

import com.solland.paidao.entity.bo.CommentBO;
import org.springframework.stereotype.Service;

import com.solland.paidao.dao.ActivityDAO;
import com.solland.paidao.dao.CommentDAO;
import com.solland.paidao.entity.ActivityDO;
import com.solland.paidao.entity.CommentDO;
import com.solland.paidao.service.CommentService;

import java.util.List;

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
	@Resource
	private ActivityDAO activityDAO;

	@Override
	public void insertComment(CommentDO commentDO) {
		commentDAO.insertComment(commentDO);

		int count = commentDAO.selectCommentCount(commentDO.getActivityId());

		ActivityDO activityDO = new ActivityDO();
		activityDO.setId(commentDO.getActivityId());
		activityDO.setCommentNum(count + 1);
		
		activityDAO.updateActivityById(activityDO);
	}

	@Override
	public List<CommentBO> getCommentlist(int activityId) {
		return commentDAO.selectCommentByActivityId(activityId);
	}

}
