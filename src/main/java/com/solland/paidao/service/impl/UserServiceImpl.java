package com.solland.paidao.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.solland.paidao.dao.UserDAO;
import com.solland.paidao.entity.UserDO;
import com.solland.paidao.service.UserService;

/**
 * 用户
 * @author zhaojiafu
 *
 * 2016年1月6日 上午10:29:13
 */
@Service("userService")
public class UserServiceImpl implements UserService {
	@Resource
    private UserDAO userDAO;

	@Override
	public int insert(UserDO userDO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isExistsByUsername(String username) {
		int count = userDAO.selectCountByUsername(username);
		
		if(0 < count){
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean isExistsByMobileCode(String mobileCode) {
		int count = userDAO.selectCountByMobileCode(mobileCode);
		
		if(0 == count){
			return false;
		} else {
			return true;
		}
	}
	
	@Override
	public void updateUserByMobileCode(UserDO userDO) {
		userDAO.updateUserByMobileCode(userDO);
	}
}
