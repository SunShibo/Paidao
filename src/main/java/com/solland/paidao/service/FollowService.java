package com.solland.paidao.service;

import com.solland.paidao.entity.FollowDO;

/**
 * 关注/追随
 * @author zhaojiafu
 *
 * 2016年1月12日 下午3:39:35
 */
public interface FollowService {
	/**
	 * 添加【关注】
	 * 2016年1月12日 下午3:40:45
	 * @author zhaojiafu
	 * @param followDO
	 */
	void insertFollow(FollowDO followDO);
}
