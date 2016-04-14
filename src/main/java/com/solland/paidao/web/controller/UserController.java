package com.solland.paidao.web.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.solland.paidao.entity.bo.UserBO;
import com.solland.paidao.service.impl.LoginServiceImpl;
import com.solland.paidao.util.MD5Util;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.solland.paidao.common.constants.SysConstants;
import com.solland.paidao.entity.UserDO;
import com.solland.paidao.entity.dto.ResultDTO;
import com.solland.paidao.entity.dto.ResultDTOBuilder;
import com.solland.paidao.service.MailService;
import com.solland.paidao.service.UserService;
import com.solland.paidao.service.impl.RegisterServiceImpl;
import com.solland.paidao.util.JsonUtils;
import com.solland.paidao.util.OssManage;
import com.solland.paidao.util.StringUtils;
import com.solland.paidao.web.controller.base.BaseCotroller;

/**
 * @author Shibo Sun
 * 用户controller
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseCotroller {

	static final Logger log = LoggerFactory.getLogger(UserController.class);

	@Resource( name = "userService" )
	UserService userService;

	@Resource(name = "registerService")
	RegisterServiceImpl registerService ;

	@Resource(name = "ossManage")
	OssManage ossManage ;

	@Resource(name = "mailService")
	MailService mailService ;

	@Resource(name = "loginService")
	LoginServiceImpl loginService ;

	/**
	 * 用户注册
	 * @param request
	 * @param response
	 * @param userDO
	 */
    @RequestMapping( value = "/signUp")
    public void signUp(HttpServletRequest request, HttpServletResponse response , UserDO userDO){
		// 验证参数是否合法
		if(null == userDO || StringUtils.isBlank(userDO.getEmail()) || StringUtils.isBlank(userDO.getPassword())){
			String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001" , "参数异常!")) ;
			super.safeJsonPrint(response , json);
			return;
		}
		// 验证邮箱正则
		if (!StringUtils.checkemail(userDO.getEmail())) {
			String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0010001" , "非法的邮箱格式!")) ;
			super.safeJsonPrint(response , json);
			return;
		}

		if (userDO.getEmail().equals("123456@qq.com")) { // FIXME 临时的注册邮箱
			// 注册成功返回用户id
			JSONObject data = new JSONObject() ;
			data.put("userId" , 29) ;
			String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(data.toString())) ;
			super.safeJsonPrint(response , json);
			return ;
		}
		// 判断【邮箱号】是否存在
		if(userService.isExistsByEmail(userDO.getEmail())) {
			String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0010002" , "邮箱已存在!")) ;
			super.safeJsonPrint(response , json);
			return;
		}

		registerService.register(userDO);
		// 发送邮件
		int code = (int)(Math.random() * 9000 + 1000) ;
		mailService.sendVerificationCodeForSignUp(userDO.getEmail() , code + "") ;

		Object obj = super.getPublicSession(userDO.getEmail() + "registerSendVerificationCode") ;
		if (obj != null && obj instanceof Map) {
			((Map) obj).put(code + "" , new Date()) ;
		} else {
			Map<String , Date> map = new HashMap<String, Date>() ;
			map.put(code + "", new Date()) ;
			super.putPublicSession(userDO.getEmail() + "registerSendVerificationCode" , map);
		}

		// 注册成功返回用户id
		JSONObject data = new JSONObject() ;
		data.put("userId" , userDO.getId()) ;
		String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(data.toString())) ;
		super.safeJsonPrint(response , json);
	}

	@RequestMapping("/uploadHeadPortrait")
	public void uploadHeadPortrait(@RequestParam("file") CommonsMultipartFile[] files,HttpServletRequest request
			, HttpServletResponse response, @RequestParam("userId") String userId) {

		if (files == null || files.length != 1 || StringUtils.isBlank(userId)) {
			String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0010004" , "参数异常!")) ;
			super.safeJsonPrint(response , json);
			return ;
		}

		if (!files[0].isEmpty()) {
			try {
				String fileName = files[0].getOriginalFilename();
				String type = fileName.substring(fileName.lastIndexOf(".") + 1);
				String key = "sunshibo/" + userId + "_" + "HeadPortrait" + "_" + System.currentTimeMillis();
				String url = ossManage.uploadFile(files[0].getInputStream(), key, type);
				System.out.println(">>>>>>>>>>>>>>>>>>>>>>" + url);
				if (userService.updateUserHeadPortrait(userId , url)) {
					String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(true)) ;
					super.safeJsonPrint(response , json);
				} else {
					String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0010003" , "上传失败！")) ;
					super.safeJsonPrint(response , json);
				}

				return ;
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("上传出错");
				String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000002" , "上传文件出错！")) ;
				super.safeJsonPrint(response , json);
				return ;
			}
		}

	}

	/**
	 * 发送验证码
	 */
	@RequestMapping( value = "/sendVerificationCode" )
	public void sendVerificationCode(HttpServletResponse response, String email ){

		if(StringUtils.isBlank(email)){
			String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001" , "参数异常!")) ;
			super.safeJsonPrint(response , json);
			return;
		}

		if(email.equals("123456@qq.com")){ // FIXME 测试临时邮箱
			String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("")) ;
			super.safeJsonPrint(response , json);
			return;
		}

		try {
			// 发送邮件
			int code = (int)(Math.random() * 9000 + 1000) ;
			mailService.sendVerificationCodeForSignUp(email , code + "") ; // 发送验证码

			// 存入redis
			Object obj = super.getPublicSession(email + "registerSendVerificationCode") ;
			if (obj != null && obj instanceof Map) {
                ((Map) obj).put(code + "" , new Date()) ;
                super.putPublicSession(email + "registerSendVerificationCode" , obj);
            } else {
                Map<String , Date> map = new HashMap<String, Date>() ;
                map.put(code + "", new Date()) ;
                super.putPublicSession(email + "registerSendVerificationCode" , map);
            }

			String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(true)) ;
			super.safeJsonPrint(response , json);
		} catch (Exception e) {
			e.printStackTrace();
			String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0010013" , "验证码发送失败!")) ;
			super.safeJsonPrint(response , json);
		}
	}

	/**
	 * 检查验证码
	 * @param email
	 * @param response
	 * @param verificationCode
	 */
	@RequestMapping("/checkVeriCodeForSignUp")
	public void checkVeriCodeForSignUp(@RequestParam("email") String email
			, HttpServletResponse response, @RequestParam("verificationCode") String verificationCode) {

		/* 1.检查基本的参数*/
		if(StringUtils.isBlank(email) || StringUtils.isBlank(verificationCode)){
			String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0010004" , "参数异常!")) ;
			super.safeJsonPrint(response , json);
			return ;
		}
		/* 2.验证缓存中的验证码数据*/
		if (verificationCode.equals("1887")) { // FIXME 这里是临时开发的验证码
			userService.updateUserStatus(email , UserDO.STATUS_NORMAL) ;
			String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("")) ;
			super.safeJsonPrint(response , json);
			return ;
		} else {
			Object obj = super.getPublicSession(email + "registerSendVerificationCode") ;
			if (obj != null && obj instanceof Map) {
				Date createTime = ((Map<String , Date>) obj).get(verificationCode + "") ;
				if (createTime != null && (System.currentTimeMillis() - createTime.getTime()) < SysConstants.MINUTE_TIME * 30) {
					userService.updateUserStatus(email , UserDO.STATUS_NORMAL) ;
					String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("")) ;
					super.safeJsonPrint(response , json);
					return ;
				}
			}
		}

		/* 3.验证失败返回结果*/
		String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0010008" , "验证码验证失败!")) ;
		super.safeJsonPrint(response , json);
	}

	@RequestMapping("/completeProfile")
	public void completeProfile( @RequestParam("file") CommonsMultipartFile[] files,@RequestParam("nickname") String nickname ,
								 HttpServletResponse response, @RequestParam("userId") String userId ) {

		log.error("[UserController-completeProfile] enter nickname:" + nickname + ".userId:" + userId);
		if (files == null || files.length != 1 || StringUtils.isBlank(userId)) {
			log.error("[UserController-completeProfile] filed");
			String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0010004" , "参数异常!")) ;
			super.safeJsonPrint(response , json);
			return ;
		}

		if (!files[0].isEmpty()) {
			try {
				String fileName = files[0].getOriginalFilename();
				String type = fileName.substring(fileName.lastIndexOf(".") + 1);
				String key = userId + "/" + "HeadPortrait" + "/" + System.currentTimeMillis();
				String url = ossManage.uploadFile(files[0].getInputStream(), key, type);

				if (userService.completeProfile(userId , url, nickname)) {
					/*  登录业务 */
					UserBO userBO = loginService.loginByIdNoPwd(Integer.parseInt(userId));
					String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(userBO)) ;

					super.safeJsonPrint(response , json);
					super.putLoginUser(userBO.getUuid(), userBO); // 保存到缓存
					super.setCookie(response , SysConstants.CURRENT_LOGIN_ID , userBO.getUuid() , SysConstants.SEVEN_DAY_TIME) ;
				} else {
					String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0010010", "保存用户信息失败!")) ;
					super.safeJsonPrint(response , json);
				}
			} catch (Exception e) {
				e.printStackTrace();
				String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0010010" , "保存用户信息失败!")) ;
				super.safeJsonPrint(response , json);
			}
		}
	}

	/**
	 * 重置密码发送有验证码
	 * @param response
	 * @param email
	 */
	@RequestMapping("/sendVeriCodeForResetPwd")
	public void sendVeriCodeForResetPwd( HttpServletResponse response, @RequestParam("email") String email ) {
		if (StringUtils.isBlank(email)) {
			String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0010004" , "参数异常!")) ;
			super.safeJsonPrint(response , json);
			return ;
		}

		// 发送邮件
		int code = (int)(Math.random() * 9000 + 1000) ;
		mailService.sendVeriCodeForResetPwd(email, code + "");

		Object obj = super.getPublicSession(email + "sendVeriCodeForResetPwd") ;
		if (obj != null && obj instanceof Map) {
			((Map) obj).put(code + "" , new Date()) ;
			super.putPublicSession(email + "sendVeriCodeForResetPwd" , obj);
		} else {
			Map<String , Date> map = new HashMap<String, Date>() ;
			map.put(code + "", new Date()) ;
			super.putPublicSession(email + "sendVeriCodeForResetPwd" , map);
		}

		String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("")) ;
		super.safeJsonPrint(response , json);

	}

	/**
	 * 检查重置密码的验证码
	 * @param email
	 * @param response
	 * @param verificationCode
	 */
	@RequestMapping("/checkVeriCodeForResetPwd")
	public void checkVeriCodeForResetPwd(@RequestParam("email") String email
			, HttpServletResponse response, @RequestParam("verificationCode") String verificationCode) {

		/* 1.检查基本的参数*/
		if(StringUtils.isBlank(email) || StringUtils.isBlank(verificationCode)){
			String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0010004" , "参数异常!")) ;
			super.safeJsonPrint(response , json);
			return ;
		}
		/* 2.验证缓存中的验证码数据*/
		Object obj = super.getPublicSession(email + "sendVeriCodeForResetPwd") ;
		if (obj != null && obj instanceof Map) {
			Date createTime = ((Map<String , Date>) obj).get(verificationCode + "") ;
			if (createTime != null && (System.currentTimeMillis() - createTime.getTime()) < SysConstants.MINUTE_TIME * 30) {
				String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("")) ;
				super.safeJsonPrint(response , json);
				return ;
			}
		}
		/* 3.验证失败返回结果*/
		String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0010008" , "验证码验证失败!")) ;
		super.safeJsonPrint(response , json);
	}

	@RequestMapping("/updatePwd")
	public void updatePwd(@RequestParam("email") String email, HttpServletResponse response,
						  @RequestParam("verificationCode") String verificationCode ,
						  @RequestParam("newPwd") String newPwd) {

		/* 1.检查基本的参数*/
		if(StringUtils.isBlank(email) || StringUtils.isBlank(verificationCode)){
			String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0010004" , "参数异常!")) ;
			super.safeJsonPrint(response , json);
			return ;
		}
		/* 2.验证缓存中的验证码数据*/
		Object obj = super.getPublicSession(email + "sendVeriCodeForResetPwd") ;
		if (obj != null && obj instanceof Map) {
			Date createTime = ((Map<String , Date>) obj).get(verificationCode + "") ;
			if (createTime != null && (System.currentTimeMillis() - createTime.getTime()) < SysConstants.MINUTE_TIME * 30) {

				if (userService.updatePwd(email , MD5Util.digest(newPwd))) {
					String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("")) ;
					super.safeJsonPrint(response , json);
				}else {
					String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0010011")) ;
					super.safeJsonPrint(response , json);
				}
				return ;
			}
		}
		/* 3.验证失败返回结果*/
		String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0010008" , "验证码验证失败!")) ;
		super.safeJsonPrint(response , json);
	}
}
