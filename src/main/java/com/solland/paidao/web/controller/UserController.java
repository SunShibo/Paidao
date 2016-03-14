package com.solland.paidao.web.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.solland.paidao.util.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartRequest;

import com.solland.paidao.entity.UserDO;
import com.solland.paidao.entity.dto.ResultDTO;
import com.solland.paidao.service.UserService;
import com.solland.paidao.service.impl.RegisterServiceImpl;
import com.solland.paidao.util.JsonUtils;
import com.solland.paidao.web.controller.base.BaseCotroller;

/**
 * @author Shibo Sun
 * 用户controller
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseCotroller {

	@Resource( name = "userService" )
	UserService userService;

	@Resource(name = "registerService")
	RegisterServiceImpl registerService ;

	/**
	 * 用户注册
	 * @param request
	 * @param response
	 * @param userDO
	 */
    @RequestMapping( value = "/signUp")
    public void signUp(HttpServletRequest request, HttpServletResponse response , UserDO userDO){
		// 验证参数是否合法
		if(null == userDO){
			String json = JsonUtils.getJsonString4JavaPOJO(new ResultDTO(false , "0" , "参数异常!")) ;
			super.safeJsonPrint(response , json);
			return;
		}
		// 验证【账号】是否为空
		if(StringUtils.isBlank(userDO.getEmail())) {
			String json = JsonUtils.getJsonString4JavaPOJO(new ResultDTO(false , "0" , "邮箱不能为空!")) ;
			super.safeJsonPrint(response , json);
			return;
		}
		// 验证邮箱正则
		if (!StringUtils.checkemail(userDO.getEmail())) {
			String json = JsonUtils.getJsonString4JavaPOJO(new ResultDTO(false , "0" , "非法的邮箱格式!")) ;
			super.safeJsonPrint(response , json);
			return;
		}
		// 验证【密码】是否为空
		if(StringUtils.isBlank(userDO.getPassword())) {
			String json = JsonUtils.getJsonString4JavaPOJO(new ResultDTO(false , "0" , "密码不能为空!")) ;
			super.safeJsonPrint(response , json);
			return;
		}
		// 判断【手机号】是否存在
		if(userService.isExistsByEmail(userDO.getEmail())) {
			String json = JsonUtils.getJsonString4JavaPOJO(new ResultDTO(false , "0" , "邮箱已存在!")) ;
			super.safeJsonPrint(response , json);
			return;
		}

		registerService.register(userDO);		// 执行【注册】

		String json = JsonUtils.getJsonString4JavaPOJO(new ResultDTO(true , "1" , "注册成功。")) ;
		super.safeJsonPrint(response , json);
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
    	if(null == userDO){
    		json = JsonUtils.getJsonString4JavaPOJO(new ResultDTO(false , "0" , "参数异常!")) ;
    		super.safeJsonPrint(response , json);

    		return;
    	}
    	/* 2. 验证手机号是否为空 */
    	if(StringUtils.isEmpty(userDO.getPhoneNumber())){
    		json = JsonUtils.getJsonString4JavaPOJO(new ResultDTO(false , "0" , "手机号不能为空!")) ;
    		super.safeJsonPrint(response , json);

    		return;
    	}
    	/* 2. 验证参数是否完整 */
		/*if(!StringUtils.isMobile(mobileCode)){
			String result = JsonUtils.getJsonString4JavaPOJO(new ResultDTO(false, "0", "手机号格式不正确！")) ;
			super.safeJsonPrint(response , result);
			return ;
		}
		FIXME 暂忽略，待 诗博 完善好配置文件后启用
		*/
    	
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

}
