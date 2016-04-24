package com.solland.paidao.service;

import java.io.IOException;
import java.util.List;

import com.solland.paidao.entity.ActivityDO;
import com.solland.paidao.entity.RemoveActivityDO;
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
	boolean insertActivity(AddActivityParam addActivityParam , CommonsMultipartFile[] files , CommonsMultipartFile[] thumbnail) throws IOException;


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
	public ActivityBO getActivityById (int activityId) ;

	/**
	 * 修改事件的热度值
	 * @param activityId
	 * @param heatValue
	 * @return
	 */
	public int updateHeatValue (int activityId , int heatValue) ;

	/**
	 * 移除动态圈
	 * @param removeActivityDO
	 */
	public int removeActivity (RemoveActivityDO removeActivityDO) ;

	/**
	 * 地图模式查看动态圈
	 * @param queryActivityParam
	 * @return
	 */
	public List<ActivityBO> getActivityListForMap (QueryActivityParam queryActivityParam) ;

}
