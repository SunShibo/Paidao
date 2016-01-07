package com.solland.paidao.web.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.solland.paidao.entity.dto.ResultDTO;
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
@RequestMapping( value = "/user")
public class PasswordController extends BaseCotroller {
	/**
	 * 发送【手机验证码】
	 * 以短信的形式发送【验证码】
	 * @param response
	 * @param mobileCode 手机号
	 */
	@RequestMapping( value = "/password/sendMobileCaptcha")
	public void sendMobileCaptcha(HttpServletResponse response, String mobileCode){
		/* 1. 验证参数是否完整  */
		/*if(!StringUtils.isMobile(mobileCode)){
			String result = JsonUtils.getJsonString4JavaPOJO(new ResultDTO(false, "0", "参数异常")) ;
			super.safeJsonPrint(response , result);
			return ;
		}
		@ 暂忽略，待 诗博 完善好配置文件后启用
		*/
		
		/* 2. 生成 4 位的验证码 */
		String captcha = StringUtils.UUIDGenerator().substring(0, 4);
		
//		super.putSession(mobileCode, null);		// test
		
		/* 3. 获取当前手机号对应的验证码发送规则 */
		@SuppressWarnings("unchecked")
		Map<String, Object> regulationMap = (Map<String, Object>) super.getSession(mobileCode);
		
		int count = 0;	// 次数
		Date expiresTime = null;	// 过期时间
		
		if(null == regulationMap){
			regulationMap = new HashMap<String, Object>();
			
			Date date = new Date();		// 当前时间
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.HOUR_OF_DAY, 24);		// 加 24 小时
			expiresTime = calendar.getTime();
			
			regulationMap.put("count", 1);
			regulationMap.put("expiresTime", expiresTime);
		} else {
			count = (Integer) regulationMap.get("count");
			count++;
			regulationMap.put("count", count);
			expiresTime = (Date) regulationMap.get("expiresTime");
		}
		
		/* 4. 同一个手机号一天之内最多可发送 5 条验证码短信！ */
		if((new Date().getTime() - expiresTime.getTime()) <= 0 && count > 5){
			String result = JsonUtils.getJsonString4JavaPOJO(new ResultDTO(false, "0", "同一个手机号一天之内最多可发送 5 条验证码短信！")) ;
			super.safeJsonPrint(response , result);
			return ;
		}
		
		/* 5. 将手机验证码发送规则放入 redis 中 */
		super.putSession(mobileCode, regulationMap);
		
		/* 6. 发送验证码到客户端 */
		String result = JsonUtils.getJsonString4JavaPOJO(new ResultDTO(captcha)) ;
		super.safeJsonPrint(response , result);
	}
}
