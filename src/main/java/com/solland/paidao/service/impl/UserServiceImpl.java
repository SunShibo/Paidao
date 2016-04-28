package com.solland.paidao.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.solland.paidao.dao.UserDAO;
import com.solland.paidao.entity.UserDO;
import com.solland.paidao.service.UserService;
import com.solland.paidao.util.StringUtils;

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

	@Override
	public boolean updateUserHeadPortrait (int userId , String url ) {
		UserDO user = new UserDO() ;
		user.setId(userId);
		user.setHeadPortrait(url);
		return userDAO.updateUserHeadPortrait(user) == 0 ? false : true ;
	}

	@Override
	public boolean completeProfile (String userId , String url , String nickname) {
		UserDO user = new UserDO() ;
		user.setId(Integer.parseInt(userId));
		user.setHeadPortrait(url);
		user.setNickname(nickname);
		return userDAO.completeProfile(user) == 0 ? false : true ;
	}

	@Override
	public boolean isExistsByUsername(String username) {
		int count = userDAO.selectCountByUsername(username);
		
		if(0 < count){
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean isExistsByMobileCode(String mobileCode) {
		int count = userDAO.selectCountByMobileCode(mobileCode);
		
		if(0 == count){
			return false;
		} else {
			return true;
		}
	}


	@Override
	public int updateUserStatus(String email, String status) {
		UserDO userDO = new UserDO() ;
		userDO.setEmail(email);
		userDO.setStatus(status);
		return userDAO.updateStatusByEmail(userDO);
	}

	/**
	 * 检查邮箱是否存在
	 * @param email
	 * @return true存在， false不存在
	 */
	public boolean isExistsByEmail(String email) {
		return CollectionUtils.isNotEmpty(userDAO.selectUserByEmail(email)) ;
	}

	public boolean updatePwd (String email , String newPwd) {
		UserDO userDO = new UserDO() ;
		userDO.setEmail(email);
		userDO.setPassword(newPwd);
		return userDAO.updatePasswordByEmail(userDO) == 0 ? false : true ;
	}

	public int updateUserInfoByObj (UserDO userDO) {
		if (userDO == null || userDO.getId() == 0) {
			return 0 ;
		}
		return userDAO.updateUserInfo(userDO) ;
	}

	/**
	 * 通过旧密码修改新密码
	 * @param userId
	 * @param oldPwd
	 * @param newPwd
	 * @return
	 */
	public int updatePwdByOldPwd (int userId , String oldPwd , String newPwd) {
		return userDAO.updatePwdByOldPwd(userId , oldPwd , newPwd);
	}
}
