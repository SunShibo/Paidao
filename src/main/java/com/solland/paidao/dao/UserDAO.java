package com.solland.paidao.dao;


import com.solland.paidao.entity.UserDO;

public interface UserDAO {
    int getCount(String test);

    // 注册
    int register(UserDO userDO) ;
    
    // 查询【密码】--登陆
    String login(UserDO userDO) ;	
}
