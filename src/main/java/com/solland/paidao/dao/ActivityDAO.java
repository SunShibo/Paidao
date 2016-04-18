package com.solland.paidao.dao;

import java.util.List;
import java.util.Map;

import com.solland.paidao.entity.ActivityDO;
import com.solland.paidao.entity.HeatDO;
import com.solland.paidao.entity.bo.ActivityBO;
import com.solland.paidao.entity.dto.param.AddActivityParam;
import org.apache.ibatis.annotations.Param;

/**
 * 活动圈
 * @author zhaojiafu
 *
 * 2016年1月8日 下午2:39:22
 */
public interface ActivityDAO {
	/**
	 * 添加【活动圈】
	 * 2016年1月8日 下午2:41:24
	 * @author zhaojiafu
	 * @param activityDO
	 */
	int insertActivity(ActivityDO activityDO);
	
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
	ActivityBO selectActivityById(int id);

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

	/**
	 * 通过map查找动态圈列表
	 * @param map
	 * @return
	 */
	List<ActivityBO> selectActivityListPage (Map<String , Object> map) ;

	/**
	 *  获取热度信息 通过事件id + 用户id
	 * @param userId
	 * @param activityId
	 * @return
	 */
	List<HeatDO> selectHeatByUidAndAid (@Param("userId")int userId , @Param("activityId")int activityId) ;

	/**
	 * 修改事件热度值
	 * @param activityId
	 * @param heatValue
	 * @return
	 */
	int updateHeatValue (@Param("activityId")int activityId , @Param("heatValue")int heatValue) ;

}
