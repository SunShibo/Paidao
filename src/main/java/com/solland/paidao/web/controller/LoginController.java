package com.solland.paidao.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.solland.paidao.entity.dto.param.LoginParam;
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

	}



}
