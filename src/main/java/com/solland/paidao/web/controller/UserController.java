package com.solland.paidao.web.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.solland.paidao.entity.bo.UserBO;
import com.solland.paidao.service.impl.LoginServiceImpl;
import com.solland.paidao.util.*;
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
import com.solland.paidao.web.controller.base.BaseCotroller;
import sun.misc.BASE64Decoder;

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
		try {
			mailService.sendVerificationCodeForSignUp(userDO.getEmail() , code + "") ;
		} catch (Exception e) {
			log.error("[UserController signUp ]-邮件发送失败.验证码是{}" , code);
		}

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
			, HttpServletResponse response) {

		if (files == null || files.length != 1 ) {
			String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001")) ;
			super.safeJsonPrint(response , json);
			return ;
		}

		UserBO loginUser = super.getLoginUser(request);

		if (!files[0].isEmpty()) {
			try {
				String fileName = files[0].getOriginalFilename();
				String type = fileName.substring(fileName.lastIndexOf(".") + 1);
				String key = loginUser.getId() + "/" + "HeadPortrait" + "/" + System.currentTimeMillis();
				String url = ossManage.uploadFile(files[0].getInputStream(), key, type);
				if (userService.updateUserHeadPortrait(loginUser.getId() , url)) {
					JSONObject data = new JSONObject() ;
					data.put("url" , url) ;
					String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(data)) ;
					super.safeJsonPrint(response , json);
					super.updateLoginUserProfile(request);
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
			String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001")) ;
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

		if (files == null || files.length != 1 || StringUtils.isBlank(userId)) {
			log.error("[UserController-completeProfile] filed");
			String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001")) ;
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
					super.setCookie(response , SysConstants .CURRENT_LOGIN_ID , userBO.getUuid() , SysConstants.SEVEN_DAY_TIME) ;
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
			String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001")) ;
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
			String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001")) ;
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
			String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001")) ;
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

	@RequestMapping("/completeProfileForIOS")
	public void completeProfileForIOS( @RequestParam("file") String files,@RequestParam("nickname") String nickname ,
								 HttpServletResponse response, @RequestParam("userId") String userId,
									   @RequestParam("suffix") String suffix ) {

		if (StringUtils.isBlank(files) || StringUtils.isBlank(userId)) {
			log.error("[UserController-completeProfile] filed");
			String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001")) ;
			super.safeJsonPrint(response , json);
			return ;
		}
		try {
			byte[] buffer = new BASE64Decoder().decodeBuffer(files);
			InputStream is = new ByteArrayInputStream(buffer);
			String key = userId + "/" + "HeadPortrait" + "/" + System.currentTimeMillis();
			String url = ossManage.uploadFile(is, key, suffix);

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
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

		@RequestMapping("/updateNickName")
		public void updateNickName(HttpServletRequest request, HttpServletResponse response, String nickName ) {
			if (StringUtils.isBlank(nickName)) {
				String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001")) ;
				super.safeJsonPrint(response , json);
				return ;
			}
			UserBO loginUser = super.getLoginUser(request);
			UserDO updateObj = new UserDO() ;
			updateObj.setNickname(nickName);
			updateObj.setId(loginUser.getId());

			int result = userService.updateUserInfoByObj(updateObj) ;
			if (result > 0 ) {
				String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("")) ;
				super.safeJsonPrint(response , json);
				super.updateLoginUserProfile(request);
				return ;
			}
			String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0010014")) ;
			super.safeJsonPrint(response , json);
		}

	@RequestMapping("/updateGender")
	public void updateGender(HttpServletRequest request, HttpServletResponse response, String gender ) {
		if (StringUtils.isBlank(gender)) {
			String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001")) ;
			super.safeJsonPrint(response , json);
			return ;
		}
		if (!UserDO.GENDER_FEMALE.equals(gender) && !UserDO.GENDER_MALE.equals(gender)) { //检查参数有效性
			String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000003")) ;
			super.safeJsonPrint(response , json);
			return ;
		}
		UserBO loginUser = super.getLoginUser(request);
		UserDO updateObj = new UserDO() ;
		updateObj.setGender(gender);
		updateObj.setId(loginUser.getId());

		int result = userService.updateUserInfoByObj(updateObj) ;
		if (result > 0 ) {
			String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("")) ;
			super.updateLoginUserProfile(request);
			super.safeJsonPrint(response , json);
			return ;
		}
		String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0010014")) ;
		super.safeJsonPrint(response , json);
	}

	/**
	 * 用户修改密码
	 * @param request
	 * @param response
	 * @param oldPassword
	 * @param newPassword
     */
	@RequestMapping("/updatePasswordForSetting")
	public void updatePasswordForSetting (HttpServletRequest request, HttpServletResponse response, String oldPassword , String newPassword) {

		if (StringUtils.isBlank(oldPassword) || StringUtils.isBlank(newPassword))  {
			String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001")) ;
			super.safeJsonPrint(response , json);
			return ;
		}
		UserBO loginUser = super.getLoginUser(request);
		int rowNum = userService.updatePwdByOldPwd(loginUser.getId(), MD5Util.digest(oldPassword), MD5Util.digest(newPassword));
		if (rowNum > 0 ) {
			String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("")) ;
			super.safeJsonPrint(response , json);
			return ;
		}
		String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0010015")) ;
		super.safeJsonPrint(response , json);
	}

	@RequestMapping("/updateBirthday")
	public void updateBirthday (HttpServletRequest request, HttpServletResponse response, String birthday) {
		if (StringUtils.isBlank(birthday) )  {
			String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001")) ;
			super.safeJsonPrint(response , json);
			return ;
		}
		UserBO loginUser = super.getLoginUser(request);
		UserDO userDO = new UserDO() ;
		userDO.setId(loginUser.getId());

		Date birDate ;
		try {
			birDate = DateUtils.parseDate(birthday , DateUtils.DATE_PATTERN) ;
		} catch (Exception e) {
			String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0010016")) ;
			super.safeJsonPrint(response , json);
			return ;
		}

		userDO.setBirthday(birDate);
		int rowNum = userService.updateUserInfoByObj(userDO) ;
		if (rowNum > 0 ) {
			super.updateLoginUserProfile(request);
			String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("")) ;
			super.safeJsonPrint(response , json);
			return ;
		}
		String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0010014")) ;
		super.safeJsonPrint(response , json);
	}


	@RequestMapping("/updateCountry")
	public void updateCountry(HttpServletRequest request, HttpServletResponse response, String country) {
		if (StringUtils.isBlank(country) )  {
			String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001")) ;
			super.safeJsonPrint(response , json);
			return ;
		}

		UserBO loginUser = super.getLoginUser(request);
		UserDO userDO = new UserDO() ;
		userDO.setId(loginUser.getId());
		userDO.setCountry(country);
		int rowNum = userService.updateUserInfoByObj(userDO) ;
		if (rowNum > 0 ) {
			String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("")) ;
			super.safeJsonPrint(response , json);
			super.updateLoginUserProfile(request) ;
			return ;
		}
		String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0010014")) ;
		super.safeJsonPrint(response , json);
	}


}
