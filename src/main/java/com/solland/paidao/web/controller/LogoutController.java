package com.solland.paidao.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.solland.paidao.entity.dto.ResultDTO;
import com.solland.paidao.service.impl.LogoutServiceImpl;
import com.solland.paidao.util.JsonUtils;
import com.solland.paidao.web.controller.base.BaseCotroller;

/**
 * 注销/退出
 * @author zhaojiafu
 *
 * 2016年1月6日 下午12:16:27
 */
@Controller
@RequestMapping("/view")
public class LogoutController extends BaseCotroller {

	@Resource(name = "logoutService")
	LogoutServiceImpl logoutService ;
	
    /**
     * 注销
     * @param response
     * @param loginId 登录唯一标识
     * @param uniqueCode 设备唯一码
     */
	@RequestMapping( value = "/common/logout")
	public void logout (HttpServletResponse response, String loginId, String uniqueCode ){

		// 验证参数是否合法
		if ( StringUtils.isEmpty(loginId) || StringUtils.isEmpty(uniqueCode)) {
			String json = JsonUtils.getJsonString4JavaPOJO(new ResultDTO(false, "0", "参数不能为空!")) ;
			super.safeJsonPrint(response , json);
			
			return;
		}
		
		logoutService.logout(loginId);
		
		String json = JsonUtils.getJsonString4JavaPOJO(new ResultDTO(true, "1", "注销成功。")) ;
		super.safeJsonPrint(response , json);
	}



}
