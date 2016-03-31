package com.solland.paidao.service;

import com.solland.paidao.entity.bo.UserBO;
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
	public UserBO login (LoginParam loginParam);

	/**
	 * 通过id获取登录对象
	 * @param userId
	 * @return
	 */
	public UserBO loginByIdNoPwd (int userId) ;
	
	/**
	 * 是否已登录
	 * @param loginId
	 * @return
	 */
//	public UserBO isLogin (String loginId);
}
