package com.solland.paidao.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.solland.paidao.dao.ActivityDAO;
import com.solland.paidao.entity.ActivityDO;
import com.solland.paidao.service.ActivityService;

/**
 * 活动圈
 * @author zhaojiafu
 *
 * 2016年1月8日 下午3:10:17
 */
//@Service( value = "activityService" )
@Service("activityService")
public class ActivityServiceImpl implements ActivityService {
	@Resource
	ActivityDAO activityDAO;

	@Override
	public void insertActivity(ActivityDO activityDO) {
		activityDAO.insertActivity(activityDO);
	}

	@Override
	public void updateActivityById(ActivityDO activityDO) {
		activityDAO.updateActivityById(activityDO);
	}

	@Override
	public ActivityDO selectActivityById(ActivityDO activityDO) {
		return activityDAO.selectActivityById(activityDO);
	}

	@Override
	public void deleteActivityById(int id) {
		activityDAO.deleteActivityById(id);
	}

	@Override
	public int selectActivityTotalCount(ActivityDO activityDO) {
		return activityDAO.selectActivityTotalCount(activityDO);
	}

	@Override
	public ActivityDO selectActivityList(ActivityDO activityDO) {
		return activityDAO.selectActivityList(activityDO);
	}

}
