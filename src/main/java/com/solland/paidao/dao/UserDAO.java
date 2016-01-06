package com.solland.paidao.dao;


import com.solland.paidao.entity.UserDO;
import com.solland.paidao.entity.bo.UserBO;
import org.apache.ibatis.annotations.Param;

public interface UserDAO {

    /**
     * 登录
     * @param account
     * @return 返回该账户的用户信息
     */
    public UserBO login(@Param("account") String account , @Param("password") String password) ;

    // FIXME zhaojiafu 使用文档注释，标注时间，作者，参数，返回结果
    int getCount(String test);

    // 注册
    int register(UserDO userDO) ;
    
    // 根据【用户名】查询【用户】的数量
    int selectCountByUsername(String username);
}
