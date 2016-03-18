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
	public boolean updateUserHeadPortrait (String userId , String url ) {
		UserDO user = new UserDO() ;
		user.setId(Integer.parseInt(userId));
		user.setHeadPortrait(url);
		return userDAO.updateUserHeadPortrait(user) == 0 ? false : true ;
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
	public void updateUserByMobileCode(MultipartRequest multipartRequest, UserDO userDO, String projectRootPath) {
		if (null != multipartRequest) {
			List<MultipartFile> multipartFileList = multipartRequest.getFiles("fileToUpload");
			
			if(null == multipartFileList || 0 == multipartFileList.size()){
				return;
			}
			
			CommonsMultipartFile commonsMultipartFile = (CommonsMultipartFile) multipartFileList.get(0);

			if (null == commonsMultipartFile || 0 == commonsMultipartFile.getSize()) {
				return;
			}

			String headPortraitFolderAbsolutePath = projectRootPath + "upload/user/headPortrait/"; // 存储头像的绝对路径

			File headPortraitFolderFile = new File(headPortraitFolderAbsolutePath);
			if (!headPortraitFolderFile.exists()) {
				headPortraitFolderFile.mkdir(); // 创建【头像】文件夹
			}

			String originalFileName = commonsMultipartFile.getOriginalFilename();	// 获取【源文件名】
			String extensionName = originalFileName.substring(originalFileName.lastIndexOf(".")); // 获取【扩展名】
			String systemHeadPortraitName = StringUtils.UUIDGenerator(); // 生成【系统头像文件】的名字
			String systemHeadPortraitAbsolutePath = projectRootPath + "upload/user/headPortrait/"
					+ systemHeadPortraitName + extensionName;
			File celebrityImageFile = new File(systemHeadPortraitAbsolutePath); //

			try {
				commonsMultipartFile.transferTo(celebrityImageFile);
				// FileUtils.copyInputStreamToFile(commonsMultipartFile.getInputStream(),
				// celebrityImageFile);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		userDAO.updateUserByMobileCode(userDO);
	}
	
	@Override
	public List<UserDO> selectUserList(UserDO userDO) {
		return userDAO.selectUserList(userDO);
	}

	/**
	 * 检查邮箱是否存在
	 * @param email
	 * @return true存在， false不存在
	 */
	public boolean isExistsByEmail(String email) {
		return CollectionUtils.isNotEmpty(userDAO.selectUserByEmail(email)) ;
	}
}
