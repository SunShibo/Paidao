package com.solland.paidao.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.solland.paidao.dao.UserDAO;
import com.solland.paidao.entity.UserDO;
import com.solland.paidao.service.RegisterService;

/**
 * 注册
 * @author zhaojiafu
 *
 * 2016年1月5日 下午6:11:20
 */
@Service("registerService")
@Transactional
public class RegisterServiceImpl implements RegisterService {
	@Resource
    private UserDAO userDAO;

	@Override
	public int register(UserDO userDO) {
		return userDAO.register(userDO);
	}

}
