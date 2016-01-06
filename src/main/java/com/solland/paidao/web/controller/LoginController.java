package com.solland.paidao.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.solland.paidao.entity.bo.UserBO;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.solland.paidao.entity.UserDO;
import com.solland.paidao.entity.dto.ResultDTO;
import com.solland.paidao.entity.dto.param.LoginParam;
import com.solland.paidao.service.UserService;
import com.solland.paidao.service.impl.LoginServiceImpl;
import com.solland.paidao.systemConfig.Constants;
import com.solland.paidao.util.JsonUtils;
import com.solland.paidao.util.RedisUtil;
import com.solland.paidao.web.controller.base.BaseCotroller;

import java.util.UUID;

/**
 * @author Shibo Sun
 * 登录controller
 */
@Controller
@RequestMapping("/login")
public class LoginController extends BaseCotroller {

	@Resource(name = "loginService")
	LoginServiceImpl loginService ;
	
	@Resource(name = "userService")
	UserService userService ;

	/**
	 * 登录
	 * @param response
	 * @param loginParam
	 */
    @RequestMapping( value = "/signIn")
    public void signIn(HttpServletResponse response, LoginParam loginParam){

		/* 1. 验证参数是否完整 */
		if(null == loginParam || StringUtils.isEmpty(loginParam.getPassword()) || StringUtils.isEmpty(loginParam.getAccount())){
			String result = JsonUtils.getJsonString4JavaPOJO(new ResultDTO(false, "0", "参数异常")) ;
			super.safeJsonPrint(response , result);
			return ;
		}

		/* 2. 找到对应的账户记录 */
		UserBO userBO = loginService.login(loginParam);

		/* 3. 验证账户状态 */
		if (userBO == null ) { // 登录名活密码错误
			String result = JsonUtils.getJsonString4JavaPOJO(new ResultDTO(false, "0", "Access denied")) ;
			super.safeJsonPrint(response , result);
			return ;
		}
		if ( StringUtils.isEmpty(userBO.getStatus()) || UserDO.STATUS_FREEZE.equals(userBO.getStatus()) ) {   	// 验证登录状态
			String result = JsonUtils.getJsonString4JavaPOJO(new ResultDTO(false, "0", "账户存在异常，请联系客服！")) ;
			super.safeJsonPrint(response , result);
			return ;
		}

		/* 4. 登录业务 */
		String uuid = UUID.randomUUID().toString() ; //生成UUID
		userBO.setUuid(uuid); // 保存到BO对象，返回给移动端
		super.putSession(uuid , userBO); // 保存到缓存

		/* 5. 返回用户信息 */
		String result = JsonUtils.getJsonString4JavaPOJO(new ResultDTO<UserBO>(userBO)) ;
		super.safeJsonPrint(response , result);
	}

	/**
	 * 检查登录状态（长登录）
	 * @param request
	 * @param response
	 * @param loginId
	 * @param uniqueCode
	 */
	@RequestMapping( value = "/common/queryLoginStatus")
	public void queryLoginStatus (HttpServletResponse response, String loginId, String uniqueCode ){

		// 验证参数是否合法
		if ( StringUtils.isEmpty(loginId) || StringUtils.isEmpty(uniqueCode)) {
			String json = JsonUtils.getJsonString4JavaPOJO(new ResultDTO(false, "0", "参数不能为空!")) ;
			super.safeJsonPrint(response , json);
			
			return;
		}
		
		if(loginService.isLogin(loginId)){
			// FIXME zhaojiafu 这里需要返回用户信息（包括头像路径，用户名等等）
			String json = JsonUtils.getJsonString4JavaPOJO(new ResultDTO(true, "1", "已登录!")) ;
			super.safeJsonPrint(response , json);
			
			return;
		} else {
			String json = JsonUtils.getJsonString4JavaPOJO(new ResultDTO(false, "0", "未登录!")) ;
			super.safeJsonPrint(response , json);
			
			return;
		}
	}



}
