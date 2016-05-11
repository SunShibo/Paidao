package com.solland.paidao.service;

import com.solland.paidao.entity.HeatDO;

import java.util.List;

/**
 * 加热（点赞）
 * @author zhaojiafu
 *
 * 2016年1月11日 下午3:04:58
 */
public interface HeatService {
	/**
	 * 添加【加热】
	 * 2016年1月11日 下午3:05:42
	 * @author zhaojiafu
	 * @param heatDO
	 */
	void insert(HeatDO heatDO);

	/**
	 * 获取热度信息 通过事件id + 用户id
	 * @param userId
	 * @param activityId
	 * @return
	 */
	List<HeatDO> getHeatInfoByUidAndActivifyId(int userId , int activityId) ;

	/**
	 * 获取加热用户id列表
	 * @param activityId
	 * @return
     */
	List<Integer> getHeatUserIdList (int activityId)  ;
}
