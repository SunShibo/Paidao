package com.solland.paidao.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.solland.paidao.service.TestServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.solland.paidao.web.controller.base.BaseCotroller;

/**
 * 测试controller
 */
@Controller
@RequestMapping("/test")
public class TestController extends BaseCotroller {

	@Resource(name = "testService")
	TestServiceImpl testService ;

    @RequestMapping( value = "/test/sunshibo")
    public void initTestPage(HttpServletRequest request
			, HttpServletResponse response ){

		int a = testService.getCount() ;
		safeJsonPrint(response, "a==>" + a) ;
	}



}
