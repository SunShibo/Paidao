package com.solland.paidao.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.solland.paidao.entity.PraiseDO;
import com.solland.paidao.entity.dto.ResultDTO;
import com.solland.paidao.service.PraiseService;
import com.solland.paidao.util.JsonUtils;
import com.solland.paidao.web.controller.base.BaseCotroller;

/**
 * 赞
 * @author zhaojiafu
 *
 * 2016年1月7日 下午5:50:54
 */
@Controller
@RequestMapping( value = "/praise")
public class PraiseController extends BaseCotroller {
	@Resource(name = "praiseService")
	PraiseService praiseService;
	
	/**
	 * 添加【赞】
	 * 2016年1月7日 下午6:03:42
	 * @author zhaojiafu
	 * @param response
	 * @param praiseDO
	 */
	@RequestMapping( value = "/insert" )
	public void insert(HttpServletResponse response, PraiseDO praiseDO){
		String result = null;
		
		/* 1. 验证参数完整性 */
		if(null == praiseDO){
			result = JsonUtils.getJsonString4JavaPOJO(new ResultDTO(false, "0", "参数异常")) ;
			super.safeJsonPrint(response , result);
			
			return ;
		}
		
		/* 2. 添加【赞】 */
		praiseService.insert(praiseDO);
		
		/* 3. 发送验证码到客户端 */
		result = JsonUtils.getJsonString4JavaPOJO(new ResultDTO("添加【赞】成功。")) ;
		super.safeJsonPrint(response , result);
	}
}
