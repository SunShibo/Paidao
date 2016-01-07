package com.solland.paidao.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.solland.paidao.entity.UserDO;
import com.solland.paidao.entity.dto.ResultDTO;
import com.solland.paidao.entity.dto.param.UserSignUpParam;
import com.solland.paidao.service.UserService;
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

	/**
	 * 用户注册
	 * @param request
	 * @param response
	 * @param userSignUpParam
	 */
    @RequestMapping( value = "/signUp")
    public void signIn(HttpServletRequest request, HttpServletResponse response , UserSignUpParam userSignUpParam){

	}
    
    /**
     * 根据【手机号】更新【用户】信息
     * 2016年1月7日 下午3:30:31
     * @author zhaojiafu
     * @param response
     * @param userDO
     */
    @RequestMapping(value = "updateUserByMobileCode" )
    public void updateUserByMobileCode(HttpServletResponse response, UserDO userDO){
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
    	
    	/* 3. 执行更新【用户】*/
    	userService.updateUserByMobileCode(userDO);
    	
    	/* 4. 返回提示信息到客户端 */
    	json = JsonUtils.getJsonString4JavaPOJO(new ResultDTO("更新【用户】信息成功。")) ;
		super.safeJsonPrint(response , json);

    }

}
