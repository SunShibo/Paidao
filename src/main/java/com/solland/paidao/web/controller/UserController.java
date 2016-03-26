package com.solland.paidao.web.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.solland.paidao.common.constants.SysConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

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
		} catch (IOException e) {
			log.error("[UserController - signUp] 发送邮件失败 To:" + userDO.getEmail() + ". error:" + e);
		} catch (MessagingException e) {
			log.error("[UserController - signUp] 发送邮件失败 To:" + userDO.getEmail() + ". error:" + e);
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
		String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(userDO.getId())) ;
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
				String key = userId + "_" + "HeadPortrait" + "_" + System.currentTimeMillis();
				String url = ossManage.uploadFile(files[0].getInputStream(), key, type);

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
     * 根据【手机号】更新【用户】信息
     * 2016年1月7日 下午3:30:31
     * @author zhaojiafu
     * @param response
     * @param userDO
     */
    @RequestMapping(value = "updateUserByMobileCode" )
    public void updateUserByMobileCode(HttpServletRequest request, HttpServletResponse response, UserDO userDO){
    	String json = null;
    	
    	/* 1. 验证参数是否完整  */
    	if(null == userDO || StringUtils.isEmpty(userDO.getPhoneNumber())){
    		json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001" , "参数异常!")) ;
    		super.safeJsonPrint(response , json);

    		return;
    	}
    	
    	String projectRootPath = request.getSession().getServletContext().getRealPath("/");        // 项目目录
    	MultipartRequest multipartRequest = null;
    	
    	/* 3. 执行更新【用户】*/
    	userService.updateUserByMobileCode(multipartRequest, userDO, projectRootPath);
    	
    	/* 4. 返回提示信息到客户端 */
    	json = JsonUtils.getJsonString4JavaPOJO(new ResultDTO("更新【用户】信息成功。")) ;
		super.safeJsonPrint(response , json);

    }

    /**
     * 查询【用户】列表
     * 2016年1月12日 下午4:54:16
     * @author zhaojiafu
     */
    @RequestMapping( value = "/selectUserList" )
    public void selectUserList(HttpServletResponse response, UserDO userDO){
    	String json = null;
    	
    	/* 1. 验证参数是否完整  */
    	if(null == userDO){
    		json = JsonUtils.getJsonString4JavaPOJO(new ResultDTO(false , "0" , "参数异常!")) ;
    		super.safeJsonPrint(response , json);
    		return;
    	}

    	/* 2. 执行查询【用户】列表 */
    	List<UserDO> userList = userService.selectUserList(userDO);
    	
    	/* 3. 返回提示信息到客户端 */
    	json = JsonUtils.getJsonString4JavaPOJO(new ResultDTO(userList)) ;
		super.safeJsonPrint(response , json);
    }

	/**
	 * 发送验证码
	 */
	@RequestMapping( value = "/sendVerificationCode" )
	public void sendVerificationCode(HttpServletResponse response, String email ){
		// 发送邮件
		int code = (int)(Math.random() * 9000 + 1000) ;
		try {
			mailService.sendVerificationCodeForSignUp(email , code + "") ; // 发送验证码
		} catch (IOException e) {
			log.error("[UserController - signUp] 发送邮件失败 To:" + email + ". error:" + e);
			String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0010009" , "验证码发送失败！")) ;
			super.safeJsonPrint(response , json);
			return ;
		} catch (MessagingException e) {
			log.error("[UserController - signUp] 发送邮件失败 To:" + email + ". error:" + e);
			String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0010009" , "验证码发送失败！")) ;
			super.safeJsonPrint(response , json);
			return ;
		}

		// 存入redis
		Object obj = super.getPublicSession(email + "registerSendVerificationCode") ;
		if (obj != null && obj instanceof Map) {
			((Map) obj).put(code + "" , new Date()) ;
		} else {
			Map<String , Date> map = new HashMap<String, Date>() ;
			map.put(code + "", new Date()) ;
			super.putPublicSession(email + "registerSendVerificationCode" , map);
		}

		String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(true)) ;
		super.safeJsonPrint(response , json);
	}

	/**
	 * 验证验证码
	 * @param email
	 * @param response
	 * @param verificationCode
	 */
	@RequestMapping("/checkVeriCodeForSignUp")
	public void checkVeriCodeForSignUp(@RequestParam("email") String email
			, HttpServletResponse response, @RequestParam("verificationCode") String verificationCode) {

		if(StringUtils.isBlank(email) || StringUtils.isBlank(verificationCode)){
			String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0010004" , "参数异常!")) ;
			super.safeJsonPrint(response , json);
			return ;
		}

		Object obj = super.getPublicSession(email + "registerSendVerificationCode") ;
		if (obj != null && obj instanceof Map) {
			Date createTime = ((Map<String , Date>) obj).get(verificationCode + "") ;
			if (createTime != null && (System.currentTimeMillis() - createTime.getTime()) < SysConstants.MINUTE_TIME * 30) {
				userService.updateUserStatus(email , UserDO.STATUS_NORMAL) ;
				String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(true)) ;
				super.safeJsonPrint(response , json);
				return ;
			}
		}
		String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0010008" , "验证码验证失败!")) ;
		super.safeJsonPrint(response , json);
		return ;
	}

	@RequestMapping("/completeProfile")
	public void completeProfile( @RequestParam("file") CommonsMultipartFile[] files,@RequestParam("nickname") String nickname ,
								 HttpServletResponse response, @RequestParam("userId") String userId ) {
		if (files == null || files.length != 1 || StringUtils.isBlank(userId)) {
			String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0010004" , "参数异常!")) ;
			super.safeJsonPrint(response , json);
			return ;
		}

		if (!files[0].isEmpty()) {
			try {
				String fileName = files[0].getOriginalFilename();
				String type = fileName.substring(fileName.lastIndexOf(".") + 1);
				String key = userId + "_" + "HeadPortrait" + "_" + System.currentTimeMillis();
				String url = ossManage.uploadFile(files[0].getInputStream(), key, type);

				if (userService.completeProfile(userId , url, nickname)) {
					String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(true)) ;
					super.safeJsonPrint(response , json);
				} else {
					String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0010010" , "保存用户信息失败!")) ;
					super.safeJsonPrint(response , json);
				}

				return ;
			} catch (Exception e) {
				e.printStackTrace();
				String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0010010" , "保存用户信息失败!")) ;
				super.safeJsonPrint(response , json);
				return ;
			}
		}
	}
}
