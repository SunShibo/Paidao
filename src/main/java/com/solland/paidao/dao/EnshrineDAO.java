package com.solland.paidao.dao;

import com.solland.paidao.entity.EnshrineDO;

/**
 * 收藏
 * @author zhaojiafu
 *
 * 2016年1月11日 下午3:53:04
 */
public interface EnshrineDAO {
	/**
	 * 添加【收藏】
	 * 2016年1月11日 下午3:53:51
	 * @author zhaojiafu
	 * @param enshrineDO
	 */
	void insert(EnshrineDO enshrineDO);
	
	/**
	 * 根据【活动ID】查询【收藏】的数量 
	 * 2016年1月11日 下午5:06:43
	 * @author zhaojiafu
	 * @param activityId
	 */
	int selectEnshrineCountByActivityId(int activityId);
}
