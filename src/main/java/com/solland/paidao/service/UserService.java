package com.solland.paidao.service;

import java.util.List;

import org.springframework.web.multipart.MultipartRequest;

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
	 * 修改用户头像
	 * @param userId
	 * @param url
	 * @return
	 */
	public boolean updateUserHeadPortrait (String userId , String url ) ;
	
	/**
	 * 验证【手机号】是否已存在
	 * 2016年1月7日 下午3:24:00
	 * @author zhaojiafu
	 * @param mobileCode
	 * @return
	 */
	public boolean isExistsByMobileCode(String mobileCode) ;

	/**
	 * 检查邮箱是否存在
	 * @param email
	 * @return
	 */
	public boolean isExistsByEmail(String email) ;
	
	/**
	 * 根据【手机号】更新【用户】
	 * 2016年1月7日 下午3:00:56
	 * @author zhaojiafu
	 * @param userDO
	 */
	void updateUserByMobileCode(MultipartRequest multipartRequest, UserDO userDO, String projectRootPath);
	
	/**
	 * 查询【用户】列表
	 * 2016年1月12日 下午4:51:28
	 * @author zhaojiafu
	 * @param userDO
	 * @return
	 */
	public List<UserDO> selectUserList(UserDO userDO);

	/**
	 * 修改用户状态
	 * @param email
	 * @param status
	 * @return
	 */
	public int updateUserStatus(String email , String status) ;

	/**
	 * 完成用户信息
	 * @param userId
	 * @param url
	 * @param nickname
	 * @return
	 */
	public boolean completeProfile (String userId , String url , String nickname) ;
}
