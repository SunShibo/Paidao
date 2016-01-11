package com.solland.paidao.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.solland.paidao.entity.HeatDO;
import com.solland.paidao.entity.dto.ResultDTO;
import com.solland.paidao.service.HeatService;
import com.solland.paidao.util.JsonUtils;
import com.solland.paidao.web.controller.base.BaseCotroller;

/**
 * 加热（点赞）
 * @author zhaojiafu
 *
 * 2016年1月11日 下午3:15:54
 */
@Controller
@RequestMapping( value = "/heat" )
public class HeatController extends BaseCotroller {
	@Resource( name = "heatService" )
	private HeatService heatService;

	/**
	 * 添加【加热】
	 * 2016年1月11日 下午3:31:50
	 * @author zhaojiafu
	 * @param response
	 * @param heatDO
	 */
	@RequestMapping( value = "/insertHeat" )
	public void insertHeat(HttpServletResponse response, HeatDO heatDO){
		String result = null;
		
		/* 1. 验证参数的完整性 */
		if(null == heatDO){
			result = JsonUtils.getJsonString4JavaPOJO(new ResultDTO(false, "0", "参数异常"));
			super.safeJsonPrint(response, result);
			
			return;
		}
		
		/* 2. 执行添加【热度】 */
		heatService.insert(heatDO);

		/* 3. 返回消息到客户端 */
		result = JsonUtils.getJsonString4JavaPOJO(new ResultDTO("添加【热度】成功。"));
		super.safeJsonPrint(response, result);
	}
}
