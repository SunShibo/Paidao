package com.solland.paidao.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.solland.paidao.dao.ActivityDAO;
import com.solland.paidao.dao.HeatDAO;
import com.solland.paidao.entity.ActivityDO;
import com.solland.paidao.entity.HeatDO;
import com.solland.paidao.service.HeatService;

import java.util.List;

/**
 * 加热（点赞）
 * @author zhaojiafu
 *
 * 2016年1月11日 下午3:14:28
 */
@Service( value ="heatService" )
public class HeatServiceImpl implements HeatService {
	@Resource
	private HeatDAO heatDAO;
	@Resource
	private ActivityDAO activityDAO;

	@Override
	public void insert(HeatDO heatDO) {
		heatDAO.insert(heatDO);
	}

	@Override
	public List<HeatDO> getHeatInfoByUidAndActivifyId(int userId, int activityId) {
		return activityDAO.selectHeatByUidAndAid(userId , activityId) ;
	}


}
