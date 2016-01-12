package com.solland.paidao.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.solland.paidao.dao.FollowDAO;
import com.solland.paidao.entity.FollowDO;
import com.solland.paidao.service.FollowService;

/**
 * 关注/跟随
 * @author zhaojiafu
 *
 * 2016年1月12日 下午3:47:56
 */
@Service( value = "followService" )
public class FollowServiceImpl implements FollowService {
	@Resource
	private FollowDAO followDAO;

	@Override
	public void insertFollow(FollowDO followDO) {
		followDAO.insertFollow(followDO);
	}

}
