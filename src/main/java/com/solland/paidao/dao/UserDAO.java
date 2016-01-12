package com.solland.paidao.dao;


import com.solland.paidao.entity.UserDO;
import com.solland.paidao.entity.bo.UserBO;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * 用户
 * @author zhaojiafu
 *
 * 2016年1月7日 下午12:29:55
 */
public interface UserDAO {

    /**
     * 登录
     * @param account
     * @return 返回该账户的用户信息
     */
    public UserBO login(@Param("account") String account , @Param("password") String password) ;

    // FIXME zhaojiafu 使用文档注释，标注时间，作者，参数，返回结果
    int getCount(String test);

    /**
     * 注册
     * 2016年1月7日 下午12:34:12
     * @author zhaojiafu
     * @param userDO
     * @return
     */
    int register(UserDO userDO) ;

    /**
     * 根据【用户名】查询【用户】的数量
     * 2016年1月7日 下午12:32:58
     * @author zhaojiafu
     * @param username 用户名
     * @return 用户数
     */
    int selectCountByUsername(String username);
    
    /**
     * 根据【手机号】查询【用户数】
     * 2016年1月7日 下午3:17:09
     * @author zhaojiafu
     * @param mobileCode
     * @return 【用户数】
     */
    int selectCountByMobileCode(String mobileCode);

    /**
     * 根据【手机号】更新【密码】
     * 2016年1月7日 下午2:58:47
     * @author zhaojiafu
     * @param userDO
     */
    void updatePasswordByMobileCode(UserDO userDO);
    
    /**
     * 根据【手机号】更新【用户】信息
     * 2016年1月7日 下午2:58:23
     * @author zhaojiafu
     * @param userDO
     */
    void updateUserByMobileCode(UserDO userDO);
    
    /**
     * 查询【用户】列表
     * 2016年1月12日 下午4:49:54
     * @author zhaojiafu
     * @param userDO
     * @return
     */
    List<UserDO> selectUserList(UserDO userDO);
}
