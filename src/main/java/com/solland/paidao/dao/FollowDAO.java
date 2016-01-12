package com.solland.paidao.dao;

import com.solland.paidao.entity.FollowDO;

/**
 * 关注/追随
 * @author zhaojiafu
 *
 * 2016年1月12日 下午3:39:35
 */
public interface FollowDAO {
	/**
	 * 添加【关注】
	 * 2016年1月12日 下午3:40:45
	 * @author zhaojiafu
	 * @param followDO
	 */
	void insertFollow(FollowDO followDO);
	
	/**
	 * 根据【关注人ID】查询【关注】的数量
	 * 2016年1月12日 下午4:23:44
	 * @author zhaojiafu
	 * @param followId
	 * @return
	 */
	int selectFollowCountByFollowId(int followId);

	/**
	 * 根据【被关注人ID】查询【关注】的数量
	 * 2016年1月12日 下午4:24:13
	 * @author zhaojiafu
	 * @param followId
	 * @return
	 */
	int selectFollowCountByBeFollowId(int followId);
}
