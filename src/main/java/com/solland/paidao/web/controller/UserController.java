package com.solland.paidao.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.solland.paidao.entity.dto.param.UserSignUpParam;
import com.solland.paidao.web.controller.base.BaseCotroller;

/**
 * @author Shibo Sun
 * 登录controller
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseCotroller {


	/**
	 * 用户注册
	 * @param request
	 * @param response
	 * @param userSignUpParam
	 */
    @RequestMapping( value = "/signUp")
    public void signIn(HttpServletRequest request, HttpServletResponse response , UserSignUpParam userSignUpParam){

	}

}
