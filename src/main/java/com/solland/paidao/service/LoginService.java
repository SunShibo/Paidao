package com.solland.paidao.service;

import com.solland.paidao.entity.UserDO;
import com.solland.paidao.entity.dto.param.LoginParam;

/**
 * 登录
 * @author zhaojiafu
 *
 * 2016年1月5日 下午7:15:44
 */
public interface LoginService {
	/**
	 * 验证登录
	 * @param loginParam
	 * @return
	 */
	public UserDO login (LoginParam loginParam);
	
	/**
	 * 是否已登录
	 * @param loginParam
	 * @return
	 */
	public boolean isLogin (String loginId);
}
