package com.solland.paidao.service;

import com.solland.paidao.entity.dto.param.LoginParam;

/**
 * 登录
 * @author zhaojiafu
 *
 * 2016年1月5日 下午7:15:44
 */
public interface LoginService {
	// 登录
	public boolean login (LoginParam loginParam);
}
