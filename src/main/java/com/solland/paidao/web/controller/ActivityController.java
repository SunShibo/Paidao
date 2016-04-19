package com.solland.paidao.web.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.solland.paidao.entity.bo.UserBO;
import com.solland.paidao.entity.dto.param.QueryActivityParam;
import com.solland.paidao.util.StringUtils;
import com.solland.paidao.util.TranCharset;
import com.solland.paidao.util.page.Page;
import com.solland.paidao.util.page.QueryObj;
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
	 * 发布动态圈
	 * 2016年1月8日 下午3:16:48
	 * @author zhaojiafu
	 * @param response
	 * @param addActivityParam
	 */
	@RequestMapping( value = "/issueActivity" )
	public void issueActivity(HttpServletRequest request , HttpServletResponse response, AddActivityParam addActivityParam ,
							@RequestParam("file") CommonsMultipartFile[] files , @RequestParam(value="thumbnail", required=false) CommonsMultipartFile[] thumbnail) {


		if (files == null || files.length != 1 || addActivityParam == null ) {
			String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001")) ;
			super.safeJsonPrint(response , json);
			return ;
		}
		System.out.println(TranCharset.getEncoding(addActivityParam.getPosition()));

		UserBO loginUser = super.getLoginUser(request);
		try {
			addActivityParam.setUserId(loginUser.getId());
			if (activityService.insertActivity(addActivityParam , files , thumbnail)) {
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

	@RequestMapping( value = "/queryActivity" )
	public void queryActivity (HttpServletRequest request , HttpServletResponse response, QueryActivityParam queryActivityParam ,
							   QueryObj queryObj){

		if (queryActivityParam == null || queryObj == null || StringUtils.isBlank(queryActivityParam.getLatitude())
				|| StringUtils.isBlank(queryActivityParam.getLongitude()) || StringUtils.isBlank(queryActivityParam.getLatitude())) {
			String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001")) ;
			super.safeJsonPrint(response , json);
			return ;
		}
		Page<ActivityBO> activityPage = activityService.getActivityPage(queryActivityParam, queryObj);
		if (activityPage.getDatas() == null || activityPage.getDatas().size() == 0) {
			ResultDTO resultDTO = queryObj.getLastItemId() == null ? ResultDTOBuilder.failure("0020003") : ResultDTOBuilder.failure("0020002");
			String json = JsonUtils.getJsonString4JavaPOJO(resultDTO) ;
			super.safeJsonPrint(response , json);
			return ;
		}
		String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(activityPage)) ;
		super.safeJsonPrint(response , json);
	}

	@RequestMapping( value = "/queryActivityInfo" )
	public void queryActivityInfo (HttpServletRequest request , HttpServletResponse response, Integer activityId){

		if ( activityId == null || activityId == 0) {
			String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001")) ;
			super.safeJsonPrint(response , json);
			return ;
		}
		ActivityBO activity = activityService.getActivityById(activityId);
		if (activity == null ) { // 没有找到该事件
			String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0020005")) ;
			super.safeJsonPrint(response , json);
			return ;
		}
		String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(activity)) ;
		super.safeJsonPrint(response , json);
	}

	@RequestMapping( value = "/removeActivity")
	public void removeActivity (HttpServletRequest request , HttpServletResponse response, Integer activityId){

		if ( activityId == null || activityId == 0) {
			String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001")) ;
			super.safeJsonPrint(response , json);
			return ;
		}
		UserBO loginUser = super.getLoginUser(request);

		ActivityBO activity = activityService.getActivityById(activityId);
		if (activity == null ) { // 没有找到该事件
			String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0020005")) ;
			super.safeJsonPrint(response , json);
			return ;
		}
		String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(activity)) ;
		super.safeJsonPrint(response , json);
	}
}
