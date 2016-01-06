package com.solland.paidao.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.solland.paidao.dao.UserDAO;
import com.solland.paidao.entity.UserDO;
import com.solland.paidao.entity.dto.param.LoginParam;
import com.solland.paidao.service.LoginService;
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
	public UserDO login(LoginParam loginParam) {
		UserDO userDO = userDAO.login(loginParam.getAccount());
		
		if(null == userDO || org.apache.commons.lang.StringUtils.isEmpty(userDO.getPassword()) || !loginParam.getPassword().equals(userDO.getPassword())){
			return null;
		}
		
		String loginId = StringUtils.UUIDGenerator();
		
		userDO.setLoginId(loginId);
		
		RedisUtil.set(userDO , loginId) ;
		
		return userDO;
	}
	
	@Override
	public boolean isLogin(String loginId) {
		LoginParam loginParam_server = (LoginParam) RedisUtil.get(loginId) ;
		
		if(null == loginParam_server || null == loginParam_server.getLoginId() || !loginParam_server.getLoginId().equals(loginId)) {
			return false;
		} else {
			return true;
		}
	}
}
