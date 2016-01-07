package com.solland.paidao.service;

import com.solland.paidao.entity.UserDO;

/**
 * 用户
 * @author zhaojiafu
 *
 * 2016年1月5日 下午6:02:33
 */
public interface UserService {
	// 添加【用户】
	public int insert (UserDO userDO);

	/**
	 * 验证【用户】是否已存在
	 * 2016年1月7日 下午3:23:31
	 * @author zhaojiafu
	 * @param username
	 * @return
	 */
	public boolean isExistsByUsername(String username) ;
	
	/**
	 * 验证【手机号】是否已存在
	 * 2016年1月7日 下午3:24:00
	 * @author zhaojiafu
	 * @param mobileCode
	 * @return
	 */
	public boolean isExistsByMobileCode(String mobileCode) ;
	
	/**
	 * 根据【手机号】更新【用户】
	 * 2016年1月7日 下午3:00:56
	 * @author zhaojiafu
	 * @param userDO
	 */
	void updateUserByMobileCode(UserDO userDO);
}
