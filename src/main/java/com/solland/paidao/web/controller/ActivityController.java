package com.solland.paidao.web.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.solland.paidao.entity.bo.UserBO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.solland.paidao.entity.ActivityDO;
import com.solland.paidao.entity.bo.ActivityBO;
import com.solland.paidao.entity.dto.ResultDTO;
import com.solland.paidao.entity.dto.ResultDTOBuilder;
import com.solland.paidao.entity.dto.param.AddActivityParam;
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

	static final Logger log = LoggerFactory.getLogger(ActivityController.class);

	@Resource( name = "activityService" )
	private ActivityService activityService;

	
	/**
	 * 添加【活动】
	 * 2016年1月8日 下午3:16:48
	 * @author zhaojiafu
	 * @param response
	 * @param addActivityParam
	 */
	@RequestMapping( value = "/addActivity" )
	public void addActivity(HttpServletRequest request , HttpServletResponse response, AddActivityParam addActivityParam ,
							@RequestParam("file") CommonsMultipartFile[] files){

		if (files == null || files.length != 1 || addActivityParam == null ) {
			String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0010004")) ;
			super.safeJsonPrint(response , json);
			return ;
		}

		UserBO loginUser = super.getLoginUser(request);
		try {
			addActivityParam.setUserId(loginUser.getId());
			if (activityService.insertActivity(addActivityParam , files)) {
				String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("")) ;
				super.safeJsonPrint(response , json);
				return ;
			}
		} catch (IOException e) {
			log.error("[ActivityController-addActivity] failed, _userId_:{}, errMsg：{}", loginUser.getId(),e);
			e.printStackTrace();
		}

		String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0020001")) ;
		super.safeJsonPrint(response , json);
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
