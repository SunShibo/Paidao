package com.solland.paidao.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.solland.paidao.entity.FollowDO;
import com.solland.paidao.entity.dto.ResultDTO;
import com.solland.paidao.service.FollowService;
import com.solland.paidao.util.JsonUtils;
import com.solland.paidao.web.controller.base.BaseCotroller;

/**
 * 关注/跟随
 * @author zhaojiafu
 *
 * 2016年1月12日 下午3:50:25
 */
@Controller
@RequestMapping( value = "/follow" )
public class FollowController extends BaseCotroller {
	@Resource( name = "followService" )
	private FollowService followService;
	
	/**
	 * 添加【关注】
	 * 2016年1月12日 下午3:55:15
	 * @author zhaojiafu
	 * @param response
	 * @param followDO
	 */
	@RequestMapping( value = "/insertFollow" )
	public void insertFollow(HttpServletResponse response, FollowDO followDO){
		String result = null;
		
		/* 1. 验证参数是否完整 */
		if(null == followDO){
			result = JsonUtils.getJsonString4JavaPOJO(new ResultDTO(false, "0", "参数异常")) ;
			super.safeJsonPrint(response , result);
			
			return ;
		}
		
		/* 2. 执行添加【关注】*/
		followService.insertFollow(followDO);
		
		/* 3. 发送消息到客户端 */
		result = JsonUtils.getJsonString4JavaPOJO(new ResultDTO("添加【关注】成功。")) ;
		super.safeJsonPrint(response , result);
	}
}
