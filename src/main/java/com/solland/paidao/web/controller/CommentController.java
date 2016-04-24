package com.solland.paidao.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.solland.paidao.entity.bo.CommentBO;
import com.solland.paidao.entity.bo.UserBO;
import com.solland.paidao.entity.dto.ResultDTOBuilder;
import com.solland.paidao.util.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.solland.paidao.entity.CommentDO;
import com.solland.paidao.entity.dto.ResultDTO;
import com.solland.paidao.service.CommentService;
import com.solland.paidao.util.JsonUtils;
import com.solland.paidao.web.controller.base.BaseCotroller;

import java.util.List;

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
	@RequestMapping( value = "/issueComment" )
	public void issueComment(HttpServletRequest request , HttpServletResponse response, CommentDO commentDO) {
		String result = null; 
		
		/* 1. 验证参数是否完整 */
		if(null == commentDO){
			result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001")) ;
			super.safeJsonPrint(response , result);
			return ;
		}
		UserBO loginUser = super.getLoginUser(request);
		commentDO.setCriticId(loginUser.getId());
		/* 2. 执行添加【评论】*/
		commentService.insertComment(commentDO);
		
		/* 3. 发送消息到客户端 */
		result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("")) ;
		super.safeJsonPrint(response , result);
	}

	@RequestMapping( value = "/queryCommentList" )
	public void queryCommentList(HttpServletResponse response, Integer activityId) {
		/* 1. 验证参数是否完整 */
		if(activityId == null || activityId == 0){
			String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001")) ;
			super.safeJsonPrint(response , result);
			return ;
		}

		/* 2. 执行添加【评论】*/
		List<CommentBO> commentlist = commentService.getCommentlist(activityId);

		/* 3. 发送消息到客户端 */
		String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(commentlist)  , "yyyy-MM-dd HH:mm:ss") ;
		super.safeJsonPrint(response , result);
	}
}
