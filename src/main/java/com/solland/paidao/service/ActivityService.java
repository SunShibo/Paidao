package com.solland.paidao.service;

import java.util.List;

import com.solland.paidao.entity.ActivityDO;
import com.solland.paidao.entity.bo.ActivityBO;

/**
 * 活动圈
 * @author zhaojiafu
 *
 * 2016年1月8日 下午2:59:53
 */
public interface ActivityService {
	/**
	 * 添加【活动圈】
	 * 2016年1月8日 下午2:41:24
	 * @author zhaojiafu
	 * @param activityDO
	 */
	void insertActivity(ActivityDO activityDO);
	
	/**
	 * 根据【ID】更新【活动圈】
	 * 2016年1月8日 下午2:41:43
	 * @author zhaojiafu
	 * @param activityDO
	 */
	void updateActivityById(ActivityDO activityDO);
	
	/**
	 * 根据【ID】查询【活动圈】
	 * 2016年1月8日 下午2:44:14
	 * @author zhaojiafu
	 * @param activityDO
	 */
	ActivityDO selectActivityById(int id);

	/**
	 * 更加【ID】删除【活动圈】
	 * 2016年1月8日 下午3:03:59
	 * @author zhaojiafu
	 * @param id
	 */
	void deleteActivityById(int id);

	/**
	 * 查询【活动圈】的总记录数
	 * 2016年1月8日 下午2:46:01
	 * @author zhaojiafu
	 * @param activityDO
	 * @return
	 */
	int selectActivityTotalCount(ActivityDO activityDO);
	
	/**
	 * 查询【活动圈】列表
	 * 2016年1月8日 下午2:46:22
	 * @author zhaojiafu
	 * @param activityDO
	 * @return
	 */
	List<ActivityBO> selectActivityList(ActivityBO activityBO);
}
