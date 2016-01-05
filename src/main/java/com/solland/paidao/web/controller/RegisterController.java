package com.solland.paidao.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.solland.paidao.entity.UserDO;
import com.solland.paidao.entity.dto.ResultDTO;
import com.solland.paidao.service.impl.RegisterServiceImpl;
import com.solland.paidao.util.JsonUtils;
import com.solland.paidao.web.controller.base.BaseCotroller;

/**
 * 注册
 * @author zhaojiafu
 *
 * 2016年1月5日 下午6:39:40
 */
@Controller
@RequestMapping("/view")
public class RegisterController extends BaseCotroller {

	@Resource(name = "registerService")
	RegisterServiceImpl registerService ;

	/**
	 * 注册
	 * @param userDO
	 */
    @RequestMapping( value = "/common/register")
    public void register(HttpServletResponse response, UserDO userDO){

		// 验证参数是否合法
    	if(null == userDO){
    		String json = JsonUtils.getJsonString4JavaPOJO(new ResultDTO(false , "0" , "参数不能为空!")) ;
    		super.safeJsonPrint(response , json);

    		return;
    	}
    	// 验证【账号】是否为空
    	if(StringUtils.isEmpty(userDO.getUsername())){
    		String json = JsonUtils.getJsonString4JavaPOJO(new ResultDTO(false , "0" , "账号不能为空!")) ;
    		super.safeJsonPrint(response , json);

    		return;
    	}
    	// 验证【密码】是否为空
    	if(StringUtils.isEmpty(userDO.getPassword())){
    		String json = JsonUtils.getJsonString4JavaPOJO(new ResultDTO(false , "0" , "密码不能为空!")) ;
    		super.safeJsonPrint(response , json);
    		
    		return;
    	}

    	registerService.insert(userDO);		// 执行【注册】
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
