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

	/**
	 * 验证【用户】是否已存在
	 */
	@Override
	public boolean isExists(String username) {
		// FIXME 这里通过手机号查找，注册的时候是用手机号注册的
		int count = userDAO.selectCountByUsername(username);
		
		if(0 < count){
			return true;
		} else {
			return false;
		}
	}
}
