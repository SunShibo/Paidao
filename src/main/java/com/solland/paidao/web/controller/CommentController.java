package com.solland.paidao.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.solland.paidao.entity.CommentDO;
import com.solland.paidao.entity.dto.ResultDTO;
import com.solland.paidao.service.CommentService;
import com.solland.paidao.util.JsonUtils;
import com.solland.paidao.web.controller.base.BaseCotroller;

/**
 * 评论
 * @author zhaojiafu
 *
 * 2016年1月8日 下午7:49:56
 */
@Controller
@RequestMapping( value = "/comment" )
public class CommentController extends BaseCotroller {
	@Resource( name = "commentService" )
	private CommentService commentService;
	
	/**
	 * 添加【评论】
	 * 2016年1月8日 下午7:51:41
	 * @author zhaojiafu
	 * @param response
	 * @param commentDO
	 */
	@RequestMapping( value = "/insertComment" )
	public void insertComment(HttpServletResponse response, CommentDO commentDO) {
		String result = null; 
		
		/* 1. 验证参数是否完整 */
		if(null == commentDO){
			result = JsonUtils.getJsonString4JavaPOJO(new ResultDTO(false, "0", "参数异常")) ;
			super.safeJsonPrint(response , result);
			
			return ;
		}
		
		/* 2. 执行添加【评论】*/
		commentService.insertComment(commentDO);
		
		/* 3. 发送消息到客户端 */
		result = JsonUtils.getJsonString4JavaPOJO(new ResultDTO("添加【评论】成功。")) ;
		super.safeJsonPrint(response , result);
	}
}