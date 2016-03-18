package com.solland.paidao.web.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.solland.paidao.entity.dto.ResultDTO;
import com.solland.paidao.service.PasswordService;
import com.solland.paidao.service.UserService;
import com.solland.paidao.util.JsonUtils;
import com.solland.paidao.util.StringUtils;
import com.solland.paidao.web.controller.base.BaseCotroller;

/**
 * 密码
 * @author zhaojiafu
 *
 * 2016年1月7日 上午11:05:20
 */
@Controller
@RequestMapping( value = "/password")
public class PasswordController extends BaseCotroller {
	@Resource(name = "passwordService")
	PasswordService passwordService ;
	
	@Autowired
	UserService userService ;
	
	/**
	 * 发送【短信验证码】
	 * 以短信的形式发送【验证码】
	 * @param response
	 * @param mobileCode 手机号
	 */
	@RequestMapping( value = "/sendSMSCaptcha")
	public void sendSMSCaptcha(HttpServletResponse response, String mobileCode){
//		String result = null;
//
//		/* 1. 验证参数是否完整  */
//		if(StringUtils.isEmpty(mobileCode)){
//			result = JsonUtils.getJsonString4JavaPOJO(new ResultDTO(false, "0", "参数异常")) ;
//			super.safeJsonPrint(response , result);
//			return ;
//		}
//		/* 2. 验证手机号格式是否正确 */
//		/*if(!StringUtils.isMobile(mobileCode)){
//			String result = JsonUtils.getJsonString4JavaPOJO(new ResultDTO(false, "0", "手机号格式不正确")) ;
//			super.safeJsonPrint(response , result);
//			return ;
//		}
//		FIXME 暂忽略，待 诗博 完善好配置文件后启用
//		*/
//		//
//		/* 3. 判断【手机号】是否存在 */
//    	if(!userService.isExistsByMobileCode(mobileCode)){
//    		result = JsonUtils.getJsonString4JavaPOJO(new ResultDTO(false , "0" , "手机号不存在!")) ;
//    		super.safeJsonPrint(response , result);
//
//    		return;
//    	}
//
//		String captcha = null;
//
////		super.putSession(mobileCode, null);		// test
//
//		/* 4. 获取当前手机号对应的验证码发送规则 */
//		Map<String, Object> smsMap = (Map<String, Object>) super.getSession(mobileCode);
//
//		int count = 0;	// 次数
//		Date expiresTime = null;	// 过期时间
//
//		if(null == smsMap){
//			smsMap = new HashMap<String, Object>();
//
//			Date date = new Date();		// 当前时间
//			Calendar calendar = Calendar.getInstance();		// 获取 Calendar 的对象
//			calendar.setTime(date);		// 设置“当前日期”
//			calendar.add(Calendar.HOUR_OF_DAY, 24);		// 在“当前日期”的基础上加 24 小时
//			expiresTime = calendar.getTime();
//
//			captcha = String.valueOf(RandomStringUtils.random(4, false, true));
//
//			smsMap.put("count", 1);
//			smsMap.put("expiresTime", expiresTime);
//			smsMap.put("captcha", captcha);
//		} else {
//			count = (Integer) smsMap.get("count");
//			count++;
//			smsMap.put("count", count);
//			expiresTime = (Date) smsMap.get("expiresTime");
//
//			captcha = (String) smsMap.get("captcha");
//		}
//
//		/* 5. 同一个手机号一天之内最多可发送 5 条验证码短信！ */
//		if((new Date().getTime() - expiresTime.getTime()) <= 0 && count > 5){
//			result = JsonUtils.getJsonString4JavaPOJO(new ResultDTO(false, "0", "同一个手机号一天之内最多可发送 5 条验证码短信！")) ;
//			super.safeJsonPrint(response , result);
//			return ;
//		}
//
//		/* 6. 将手机验证码发送规则放入 redis 中 */
//		super.putSession(mobileCode, smsMap);
//
//		/* 7. 发送验证码到客户端 */
//		result = JsonUtils.getJsonString4JavaPOJO(new ResultDTO(captcha)) ;
//		super.safeJsonPrint(response , result);
	}
	
	/**
	 * 根据【手机号】更新【密码】
	 * 2016年1月7日 下午12:37:44
	 * @author zhaojiafu
	 * @param response
	 * @param mobileCode
	 * FIXME zhaojiafu 为什么没有验证码
	 */
	@RequestMapping( value = "/updatePasswordByMobileCode" )
	public void updatePasswordByMobileCode(HttpServletResponse response, String mobileCode, String password, String captcha){
		String result = null;

		// TODO zhaojiafu 没有验证过验证码
		/* 1. 验证手机号是否完整 */
		if(StringUtils.isEmpty(mobileCode)){
			result = JsonUtils.getJsonString4JavaPOJO(new ResultDTO(false, "0", "手机号不可为空！")) ;
			super.safeJsonPrint(response , result);
			return ;
		}
		/* 2. 验证参数是否完整 */
		/*if(!StringUtils.isMobile(mobileCode)){
			String result = JsonUtils.getJsonString4JavaPOJO(new ResultDTO(false, "0", "手机号格式不正确！")) ;
			super.safeJsonPrint(response , result);
			return ;
		}
		FIXME 暂忽略，待 诗博 完善好配置文件后启用
		*/
		
		/* 3. 判断【手机号】是否存在 */
    	if(!userService.isExistsByMobileCode(mobileCode)){
    		result = JsonUtils.getJsonString4JavaPOJO(new ResultDTO(false , "0" , "手机号不存在!")) ;
    		super.safeJsonPrint(response , result);
    		
    		return;
    	}
    	
		/* 4. 验证【密码】是否为空 */
		if(org.apache.commons.lang.StringUtils.isEmpty(password)){
			result = JsonUtils.getJsonString4JavaPOJO(new ResultDTO(false, "0", "密码不可为空！")) ;
			super.safeJsonPrint(response , result);
			return ;
		}
		/* 5. 验证【密码】格式 */
		if(!StringUtils.checkPassword(password)){
			result = JsonUtils.getJsonString4JavaPOJO(new ResultDTO(false, "0", "密码格式不正确！")) ;
			super.safeJsonPrint(response , result);
			return ;
		}
		/* 6. 验证【验证码】是否为空 */
		if(org.apache.commons.lang.StringUtils.isEmpty(captcha)){
			result = JsonUtils.getJsonString4JavaPOJO(new ResultDTO(false, "0", "验证码不可为空！")) ;
			super.safeJsonPrint(response , result);
			
			return ;
		}
		
//		Map<String, Object> smsMap = (Map<String, Object>) super.getSession(mobileCode);
//		if(null != smsMap){
//			String captcha_server = (String) smsMap.get("captcha");
//
//			/* 6. 验证【验证码】是否正确 */
//			if(!captcha.equals(captcha_server)){
//				result = JsonUtils.getJsonString4JavaPOJO(new ResultDTO(false, "0", "验证码不正确！")) ;
//				super.safeJsonPrint(response , result);
//
//				return ;
//			}
//		}
		
		/* 7. 执行更新【密码】 */
		passwordService.updatePasswordByMobileCode(mobileCode, password);
		
		/* 8. 发送消息到客户端 */
		result = JsonUtils.getJsonString4JavaPOJO(new ResultDTO("更新【密码】成功。")) ;
		super.safeJsonPrint(response , result);
	}
}
