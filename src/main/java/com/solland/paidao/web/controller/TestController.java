package com.solland.paidao.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.solland.paidao.entity.TestDO;
import com.solland.paidao.service.TestServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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

		int a = 0;
		try {
			a = testService.getCount();
		} catch (Exception e) {
			e.printStackTrace();
		}
		safeJsonPrint(response, "a==>" + a) ;
	}

	@RequestMapping( value = "/insert/{name}")
	public void insert(HttpServletRequest request
			, HttpServletResponse response ,String name ){

		TestDO testDO = new TestDO() ;
		testDO.setName(name);
		testService.insert(testDO) ;
		safeJsonPrint(response, "result==>" + testDO.getId()) ;
	}





}
