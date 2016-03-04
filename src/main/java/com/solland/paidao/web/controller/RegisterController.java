package com.solland.paidao.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.solland.paidao.entity.UserDO;
import com.solland.paidao.entity.dto.ResultDTO;
import com.solland.paidao.service.UserService;
import com.solland.paidao.service.impl.RegisterServiceImpl;
import com.solland.paidao.util.JsonUtils;
import com.solland.paidao.web.controller.base.BaseCotroller;

/**
 * 注册
 * @author zhaojiafu
 *
 * 2016年1月5日 下午6:39:40
 */
@Controller
@RequestMapping("/view")
public class RegisterController extends BaseCotroller {

	@Resource(name = "registerService")
	RegisterServiceImpl registerService ;

	@Autowired
	UserService userService ;

	/**
	 * 注册
	 * @param userDO
	 */
    @RequestMapping( value = "/common/register")
    public void register(HttpServletResponse response, UserDO userDO){

		// 验证参数是否合法
    	if(null == userDO){
			String json = JsonUtils.getJsonString4JavaPOJO(new ResultDTO(false , "0" , "参数不能为空!")) ;
    		super.safeJsonPrint(response , json);

    		return;
    	}
    	// 验证【账号】是否为空
    	if(StringUtils.isEmpty(userDO.getPhoneNumber())){
			String json = JsonUtils.getJsonString4JavaPOJO(new ResultDTO(false , "0" , "账号不能为空!")) ;
    		super.safeJsonPrint(response , json);

    		return;
    	}
    	// 验证【密码】是否为空
    	if(StringUtils.isEmpty(userDO.getPassword())){
			String json = JsonUtils.getJsonString4JavaPOJO(new ResultDTO(false , "0" , "密码不能为空!")) ;
    		super.safeJsonPrint(response , json);
    		
    		return;
    	}
    	// 判断【手机号】是否存在
    	if(userService.isExistsByMobileCode(userDO.getPhoneNumber())){
			String json = JsonUtils.getJsonString4JavaPOJO(new ResultDTO(false , "0" , "手机号已存在!")) ;
    		super.safeJsonPrint(response , json);
    		
    		return;
    	}

    	registerService.register(userDO);		// 执行【注册】

		String json = JsonUtils.getJsonString4JavaPOJO(new ResultDTO(true , "1" , "注册成功。")) ;
    	super.safeJsonPrint(response , json);
	}
}
