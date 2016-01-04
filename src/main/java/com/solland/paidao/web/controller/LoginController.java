package com.solland.paidao.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.solland.paidao.entity.dto.ResultDTO;
import com.solland.paidao.entity.dto.param.LoginParam;
import com.solland.paidao.util.JsonUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.solland.paidao.service.TestServiceImpl;
import com.solland.paidao.web.controller.base.BaseCotroller;

/**
 * @author Shibo Sun
 * 登录controller
 */
@Controller
@RequestMapping("/login")
public class LoginController extends BaseCotroller {

	@Resource(name = "testService")
	TestServiceImpl testService ;

	/**
	 * 登录
	 * @param request
	 * @param response
	 */
    @RequestMapping( value = "/signIn")
    public void signIn(HttpServletRequest request, HttpServletResponse response , LoginParam loginParam){

		// 验证参数是否合法
		if (loginParam == null || StringUtils.isEmpty(loginParam.getAccount()) || StringUtils.isEmpty(loginParam.getPassword())) {
			String json = JsonUtils.getJsonString4JavaPOJO(new ResultDTO(false ,"" ,"参数不能为空!")) ;
			super.safeJsonPrint(response , json);
		}

	}

	/**
	 * 检查登录状态（长登录）
	 * @param request
	 * @param response
	 * @param loginId
	 * @param uniqueCode
	 */
	@RequestMapping( value = "/queryLoginStatus")
	public void queryLoginStatus (HttpServletRequest request, HttpServletResponse response ,String loginId  ,String uniqueCode ){

		// 验证参数是否合法
		if ( StringUtils.isEmpty(loginId) || StringUtils.isEmpty(uniqueCode)) {
			String json = JsonUtils.getJsonString4JavaPOJO(new ResultDTO(false ,"" ,"参数不能为空!")) ;
			super.safeJsonPrint(response , json);
		}

	}



}
