package com.solland.paidao.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.solland.paidao.dao.UserDAO;
import com.solland.paidao.entity.UserDO;
import com.solland.paidao.entity.dto.param.LoginParam;
import com.solland.paidao.service.LoginService;
import com.solland.paidao.systemConfig.Constants;
import com.solland.paidao.util.RedisUtil;
import com.solland.paidao.util.StringUtils;

/**
 * 登录
 * @author zhaojiafu
 *
 * 2016年1月5日 下午7:16:14
 */
@Service("loginService")
@Transactional
public class LoginServiceImpl implements LoginService {
	@Resource
    private UserDAO userDAO;

	@Override
	public boolean login(LoginParam loginParam) {
		UserDO userDO = new UserDO();
		userDO.setUsername(loginParam.getAccount());
		userDO.setPassword(loginParam.getPassword());
		
		String password = userDAO.login(userDO);
		
		if(loginParam.getPassword().equals(password)){
	        loginParam.setLoginId(StringUtils.UUIDGenerator());
	        
	        RedisUtil.set(loginParam , Constants.CURRENT_LOGINED_USER) ;
	        
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public boolean isLogin(String loginId) {
		LoginParam loginParam_server = (LoginParam) RedisUtil.get(Constants.CURRENT_LOGINED_USER) ;
		
		if(loginId.equals(loginParam_server.getLoginId())){
			return true;
		} else {
			return false;
		}
	}
}
