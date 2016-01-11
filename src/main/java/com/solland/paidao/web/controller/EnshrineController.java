package com.solland.paidao.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.solland.paidao.entity.EnshrineDO;
import com.solland.paidao.entity.dto.ResultDTO;
import com.solland.paidao.service.EnshrineService;
import com.solland.paidao.util.JsonUtils;
import com.solland.paidao.web.controller.base.BaseCotroller;

/**
 * 【收藏】
 * @author zhaojiafu
 *
 * 2016年1月11日 下午4:11:07
 */
@Controller
@RequestMapping( value = "/enshrine" )
public class EnshrineController extends BaseCotroller {
	@Resource( name = "enshrineService" )
	private EnshrineService enshrineService;
	
	/**
	 * 添加【收藏】
	 * 2016年1月11日 下午4:12:59
	 * @author zhaojiafu
	 * @param response
	 * @param enshrineDO
	 */
	@RequestMapping(value = "/insertEnshrine")
	public void insertEnshrine(HttpServletResponse response, EnshrineDO enshrineDO){
		String result = null;
		
		/* 1. 验证参数的完整性 */
		if(null == enshrineDO){
			result = JsonUtils.getJsonString4JavaPOJO(new ResultDTO(false, "0", "参数异常"));
			super.safeJsonPrint(response, result);
			
			return;
		}
		
		/* 2. 执行添加【收藏】 */
		enshrineService.insert(enshrineDO);

		/* 3. 返回消息到客户端 */
		result = JsonUtils.getJsonString4JavaPOJO(new ResultDTO("添加【收藏】成功。"));
		super.safeJsonPrint(response, result);
	}
}
