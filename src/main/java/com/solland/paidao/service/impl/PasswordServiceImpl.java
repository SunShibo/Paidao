package com.solland.paidao.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.solland.paidao.dao.UserDAO;
import com.solland.paidao.entity.UserDO;
import com.solland.paidao.service.PasswordService;

/**
 * 密码
 * @author zhaojiafu
 *
 * 2016年1月7日 下午1:30:51
 */
@Service( value = "passwordService" )
public class PasswordServiceImpl implements PasswordService {
	@Resource
    private UserDAO userDAO;

	@Override
	public void updatePasswordByMobileCode(String mobileCode, String password) {
		UserDO userDO = new UserDO();
		userDO.setPhoneNumber(mobileCode);
		userDO.setPassword(password);
		
		userDAO.updatePasswordByMobileCode(userDO);
	}

}
