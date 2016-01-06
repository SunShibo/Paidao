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
	
	// 验证【用户】是否已存在
	public boolean isExists(String username) ;
}
