package com.solland.paidao.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.solland.paidao.entity.bo.UserBO;
import com.solland.paidao.entity.dto.ResultDTOBuilder;
import com.solland.paidao.service.ReportService;
import com.solland.paidao.util.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.solland.paidao.entity.ReportDO;
import com.solland.paidao.entity.dto.ResultDTO;
import com.solland.paidao.util.JsonUtils;
import com.solland.paidao.web.controller.base.BaseCotroller;

/**
 * 举报/跟随
 * @author zhaojiafu
 *
 * 2016年1月12日 下午3:50:25
 */
@Controller
@RequestMapping( value = "/report" )
public class ReportController extends BaseCotroller {
	@Resource( name = "reportService" )
	private ReportService reportService;
	
	/**
	 * 添加【举报】
	 * 2016年1月12日 下午3:55:15
	 * @author zhaojiafu
	 * @param response
	 * @param reportDO
	 */
	@RequestMapping( value = "/issueReport" )
	public void issueReport(HttpServletRequest request , HttpServletResponse response, ReportDO reportDO){
		String result = null;
		
		/* 1. 验证参数是否完整 */
		if(null == reportDO || StringUtils.isBlank(reportDO.getReportType())){
			result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001")) ;
			super.safeJsonPrint(response , result);
			return ;
		}

		UserBO loginUser = super.getLoginUser(request);
		reportDO.setUserId(loginUser.getId());
		
		/* 2. 执行添加【举报】*/
		reportService.addReport(reportDO);
		
		/* 3. 发送消息到客户端 */
		result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("")) ;
		super.safeJsonPrint(response , result);
	}
}
