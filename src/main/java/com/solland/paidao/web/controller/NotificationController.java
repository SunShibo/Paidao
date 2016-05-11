package com.solland.paidao.web.controller;

import com.solland.paidao.entity.NotificationDO;
import com.solland.paidao.entity.bo.UserBO;
import com.solland.paidao.entity.dto.ResultDTOBuilder;
import com.solland.paidao.service.NotificationService;
import com.solland.paidao.util.DateUtils;
import com.solland.paidao.util.JsonUtils;
import com.solland.paidao.web.controller.base.BaseCotroller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Shibo Sun
 * 用户controller
 */
@Controller
@RequestMapping("/notice")
public class NotificationController extends BaseCotroller {

	static final Logger log = LoggerFactory.getLogger(NotificationController.class);

	@Resource( name = "notificationService" )
	NotificationService notificationService;


	/**
	 * 获取通知列表
	 */
	@RequestMapping("/queryNotificationList")
	public void queryNotificationList(HttpServletRequest request, HttpServletResponse response) {
		UserBO loginUser = super.getLoginUser(request);
		List<NotificationDO> notificationList = notificationService.getNotificationList(loginUser.getId());
		String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(notificationList) , DateUtils.LONG_DATE_PATTERN) ;
		super.safeJsonPrint(response , json);
	}

}
