package com.solland.paidao.service.impl;

import org.springframework.stereotype.Service;

import com.solland.paidao.service.LogoutService;
import com.solland.paidao.util.RedisUtil;

/**
 * 注销
 * @author zhaojiafu
 *
 * 2016年1月6日 下午12:23:01
 */
@Service("logoutService")
public class LogoutServiceImpl implements LogoutService {
	
	@Override
	public void logout (String loginId) {
		RedisUtil.del(loginId);
	}
}
