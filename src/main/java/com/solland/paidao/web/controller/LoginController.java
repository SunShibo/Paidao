package com.solland.paidao.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.solland.paidao.entity.dto.ResultDTO;
import com.solland.paidao.entity.dto.param.LoginParam;
import com.solland.paidao.service.impl.LoginServiceImpl;
import com.solland.paidao.util.JsonUtils;
import com.solland.paidao.web.controller.base.BaseCotroller;

/**
 * @author Shibo Sun
 * 登录controller
 */
@Controller
@RequestMapping("/view")
public class LoginController extends BaseCotroller {

	@Resource(name = "loginService")
	LoginServiceImpl loginService ;

	/**
	 * 登录
	 * @param response
	 * @param loginParam
	 */
    @RequestMapping( value = "/common/login")
    public void login(HttpServletResponse response, LoginParam loginParam){
		String json = null;
		
		if(null == loginParam){
			json = JsonUtils.getJsonString4JavaPOJO(new ResultDTO(false, "0", "参数不能为空!")) ;
			super.safeJsonPrint(response , json);
			
			return ;
		} else if(StringUtils.isEmpty(loginParam.getAccount())){
			json = JsonUtils.getJsonString4JavaPOJO(new ResultDTO(false, "0", "账号不能为空!")) ;
			super.safeJsonPrint(response , json);
			
			return ;
		} else if(StringUtils.isEmpty(loginParam.getPassword())){
			json = JsonUtils.getJsonString4JavaPOJO(new ResultDTO(false, "0", "密码不能为空!")) ;
			super.safeJsonPrint(response , json);
			
			return ;
		}

		boolean bool = loginService.login(loginParam);
		
		
		if(bool){
			json = JsonUtils.getJsonString4JavaPOJO(new ResultDTO(true , "1", "登录成功")) ;
		} else {
			json = JsonUtils.getJsonString4JavaPOJO(new ResultDTO(false , "0", "登录失败")) ;
		}
		
		super.safeJsonPrint(response , json);
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
