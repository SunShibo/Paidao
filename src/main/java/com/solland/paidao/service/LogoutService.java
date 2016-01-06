package com.solland.paidao.service;

/**
 * 注销/退出
 * @author zhaojiafu
 *
 * 2016年1月6日 下午12:20:47
 */
public interface LogoutService {
	/**
	 * 注销
	 * @param loginId 登录唯一标识
	 */
	public void logout (String loginId);
}
