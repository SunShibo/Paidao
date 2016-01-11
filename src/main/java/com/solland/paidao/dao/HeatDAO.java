package com.solland.paidao.dao;

import com.solland.paidao.entity.HeatDO;

/**
 * 加热（点赞）
 * @author zhaojiafu
 *
 * 2016年1月11日 下午3:04:58
 */
public interface HeatDAO {
	/**
	 * 添加【加热】
	 * 2016年1月11日 下午3:05:42
	 * @author zhaojiafu
	 * @param heatDO
	 */
	void insert(HeatDO heatDO);
	
	/**
	 * 根据【活动ID】查询【加热】的数量
	 * 2016年1月11日 下午6:23:14
	 * @author zhaojiafu
	 * @param activityId
	 * @return
	 */
	int selectHeatCountByActivityId(int activityId);
}
