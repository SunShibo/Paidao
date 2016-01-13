package com.solland.paidao.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.solland.paidao.entity.InformDO;
import com.solland.paidao.entity.dto.ResultDTO;
import com.solland.paidao.service.InformService;
import com.solland.paidao.util.JsonUtils;
import com.solland.paidao.web.controller.base.BaseCotroller;

/**
 * 举报/跟随
 * @author zhaojiafu
 *
 * 2016年1月12日 下午3:50:25
 */
@Controller
@RequestMapping( value = "/inform" )
public class InformController extends BaseCotroller {
	@Resource( name = "informService" )
	private InformService informService;
	
	/**
	 * 添加【举报】
	 * 2016年1月12日 下午3:55:15
	 * @author zhaojiafu
	 * @param response
	 * @param informDO
	 */
	@RequestMapping( value = "/insertInform" )
	public void insertInform(HttpServletResponse response, InformDO informDO){
		String result = null;
		
		/* 1. 验证参数是否完整 */
		if(null == informDO){
			result = JsonUtils.getJsonString4JavaPOJO(new ResultDTO(false, "0", "参数异常")) ;
			super.safeJsonPrint(response , result);
			
			return ;
		}
		
		/* 2. 执行添加【举报】*/
		informService.insertInform(informDO);
		
		/* 3. 发送消息到客户端 */
		result = JsonUtils.getJsonString4JavaPOJO(new ResultDTO("添加【举报】成功。")) ;
		super.safeJsonPrint(response , result);
	}
}
