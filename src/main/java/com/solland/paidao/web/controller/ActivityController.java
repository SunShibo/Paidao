package com.solland.paidao.web.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.solland.paidao.entity.ActivityDO;
import com.solland.paidao.entity.bo.ActivityBO;
import com.solland.paidao.entity.dto.ResultDTO;
import com.solland.paidao.service.ActivityService;
import com.solland.paidao.util.JsonUtils;
import com.solland.paidao.web.controller.base.BaseCotroller;

/**
 * 活动
 * @author zhaojiafu
 *
 * 2016年1月8日 下午3:11:08
 */
@Controller
@RequestMapping( value = "/activity" )
public class ActivityController extends BaseCotroller {
	@Resource( name = "activityService" )
	private ActivityService activityService;
	
	/**
	 * 添加【活动】
	 * 2016年1月8日 下午3:16:48
	 * @author zhaojiafu
	 * @param response
	 * @param activityDO
	 */
	@RequestMapping( value = "/insertActivity" )
	public void insertActivity(HttpServletResponse response, ActivityDO activityDO){
		String result = null; 
				
		/* 1. 验证参数是否完整 */
		if(null == activityDO){
			result = JsonUtils.getJsonString4JavaPOJO(new ResultDTO(false, "0", "参数异常")) ;
			super.safeJsonPrint(response , result);
			
			return ;
		}
		
		/* 2. 执行添加【活动圈】*/
		activityService.insertActivity(activityDO);
		
		/* 3. 发送消息到客户端 */
		result = JsonUtils.getJsonString4JavaPOJO(new ResultDTO("添加【活动】成功。")) ;
		super.safeJsonPrint(response , result);
	}

	/**
	 * 根据【ID】更新【活动】
	 * 2016年1月8日 下午3:16:48
	 * @author zhaojiafu
	 * @param response
	 * @param activityDO
	 */
	@RequestMapping( value = "/updateActivityById" )
	public void updateActivityById(HttpServletResponse response, ActivityDO activityDO){
		String result = null; 
		
		/* 1. 验证参数是否完整 */
		if(null == activityDO){
			result = JsonUtils.getJsonString4JavaPOJO(new ResultDTO(false, "0", "参数异常")) ;
			super.safeJsonPrint(response , result);
			
			return ;
		}
		
		/* 2. 执行更新【活动】*/
		activityService.updateActivityById(activityDO);
		
		/* 3. 发送消息到客户端 */
		result = JsonUtils.getJsonString4JavaPOJO(new ResultDTO("更新【活动】成功。")) ;
		super.safeJsonPrint(response , result);
	}

	/**
	 * 根据【ID】删除【活动】
	 * 2016年1月8日 下午3:16:48
	 * @author zhaojiafu
	 * @param response
	 * @param activityDO
	 */
	@RequestMapping( value = "/deleteActivityById" )
	public void deleteActivityById(HttpServletResponse response, int id){
		String result = null; 
		
		/* 1. 执行删除【活动圈】*/
		activityService.deleteActivityById(id);
		
		/* 2. 发送消息到客户端 */
		result = JsonUtils.getJsonString4JavaPOJO(new ResultDTO("删除【活动】成功。")) ;
		super.safeJsonPrint(response , result);
	}

	/**
	 * 根据【ID】查询【活动】
	 * 2016年1月8日 下午3:16:48
	 * @author zhaojiafu
	 * @param response
	 * @param activityDO
	 *
	 * FIXME 这个方法有意义吗
	 * @sunshibo 对应于“paidao-1.4.1-D2图片查看1.png”和“paidao-1.4.1-D2图片查看2.png”
	 */
	@RequestMapping( value = "/selectActivityById" )
	public void selectActivityById(HttpServletResponse response, int id){
		String result = null; 
		
		/* 1. 执行查询【活动圈】*/
		ActivityDO activityDO = activityService.selectActivityById(id);
		
		/* 2. 发送消息到客户端 */
		result = JsonUtils.getJsonString4JavaPOJO(new ResultDTO(activityDO)) ;
		super.safeJsonPrint(response , result);
	}

	/**
	 * 查询【活动】的总记录数
	 * 2016年1月8日 下午3:16:48
	 * @author zhaojiafu
	 * @param response
	 * @param activityDO
	 */
	@RequestMapping( value = "/selectActivityTotalCount" )
	public void selectActivityTotalCount(HttpServletResponse response, ActivityDO activityDO){
		String result = null; 
		
		/* 1. 执行查询【活动圈】的总记录数 */
		int totalCount = activityService.selectActivityTotalCount(activityDO);
		
		/* 2. 发送消息到客户端 */
		result = JsonUtils.getJsonString4JavaPOJO(new ResultDTO(totalCount)) ;
		super.safeJsonPrint(response , result);
	}

	/**
	 * 查询【活动】列表
	 * 2016年1月8日 下午3:16:48
	 * @author zhaojiafu
	 * @param response
	 * @param activityDO
	 */
	@RequestMapping( value = "/selectActivityList" )
	public void selectActivityList(HttpServletResponse response, ActivityBO activityBO){
		String result = null; 
		
		/* 1. 执行查询【活动圈】的列表 */
		List<ActivityBO> activityList = activityService.selectActivityList(activityBO);
		
		/* 2. 发送消息到客户端 */
		result = JsonUtils.getJsonString4JavaPOJO(new ResultDTO(activityList)) ;
		super.safeJsonPrint(response , result);
	}
}
