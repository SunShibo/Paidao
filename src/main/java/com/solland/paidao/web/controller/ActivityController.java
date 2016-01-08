package com.solland.paidao.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.solland.paidao.entity.ActivityDO;
import com.solland.paidao.entity.dto.ResultDTO;
import com.solland.paidao.service.ActivityService;
import com.solland.paidao.util.JsonUtils;
import com.solland.paidao.web.controller.base.BaseCotroller;

/**
 * 活动圈
 * @author zhaojiafu
 *
 * 2016年1月8日 下午3:11:08
 */
@Controller
@RequestMapping( value = "/activity" )
public class ActivityController extends BaseCotroller {
//	@Resource( name = "activityService" )
	@Resource
	private ActivityService activityService;
	
	/**
	 * 添加【活动圈】
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
		result = JsonUtils.getJsonString4JavaPOJO(new ResultDTO("添加【活动圈】成功。")) ;
		super.safeJsonPrint(response , result);
	}
}
