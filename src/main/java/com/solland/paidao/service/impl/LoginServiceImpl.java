package com.solland.paidao.service.impl;

import javax.annotation.Resource;

import com.solland.paidao.util.MD5Util;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.solland.paidao.dao.UserDAO;
import com.solland.paidao.entity.bo.UserBO;
import com.solland.paidao.entity.dto.param.LoginParam;
import com.solland.paidao.service.LoginService;

import java.util.UUID;

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

	/**
	 * 登录，通过用户名和密码
	 * @param loginParam
	 * @return 返回用户记录
	 */
	public UserBO login(LoginParam loginParam) {
		UserBO login = userDAO.login(loginParam.getAccount(), MD5Util.digest(loginParam.getPassword()));
		if (login == null ) {
			return null ;
		}
		login.setUuid(UUID.randomUUID().toString());
		return login ;
	}

	public UserBO loginByIdNoPwd (int userId) {
		UserBO login = userDAO.loginById(userId);
		login.setUuid(UUID.randomUUID().toString());
		return login ;
	}
	/*@Override
	public UserBO isLogin(String loginId) {
		return (UserBO) RedisUtil.get(loginId) ;
	}*/
}
