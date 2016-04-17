package com.solland.paidao.service;

import java.io.IOException;
import java.util.List;

import com.solland.paidao.entity.ActivityDO;
import com.solland.paidao.entity.bo.ActivityBO;
import com.solland.paidao.entity.dto.param.AddActivityParam;
import com.solland.paidao.entity.dto.param.QueryActivityParam;
import com.solland.paidao.util.page.Page;
import com.solland.paidao.util.page.QueryObj;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

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
	 * @param addActivityParam
	 */
	boolean insertActivity(AddActivityParam addActivityParam , CommonsMultipartFile[] files) throws IOException;
	
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
	 * @param id
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
	 * 获取动态圈分页列表
	 * @param queryActivityParam
	 * @param queryObj
	 * @return
	 */
	public Page<ActivityBO> getActivityPage (QueryActivityParam queryActivityParam , QueryObj queryObj) ;

	/**
	 * 获取动态信息通过id
	 * @param activityId
	 * @return
	 */
	public ActivityDO getActivityById (int activityId) ;

	/**
	 * 修改事件的热度值
	 * @param activityId
	 * @param heatValue
	 * @return
	 */
	public int updateHeatValue (int activityId , int heatValue) ;

}
