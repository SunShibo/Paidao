package com.solland.paidao.service;

/**
 * 密码
 * @author zhaojiafu
 *
 * 2016年1月7日 下午12:35:58
 */
public interface PasswordService {
	/**
	 * 根据【手机号】更新【密码】
	 * 2016年1月7日 下午12:36:20
	 * @author zhaojiafu
	 * @param mobileCode
	 */
	void updatePasswordByMobileCode(String mobileCode, String password);
}
