package com.solland.paidao.dao;


import com.solland.paidao.entity.UserDO;

public interface UserDAO {
    /**
     * getCount
     * @author zhaojiafu
     * @param test
     * @return
     */
    // FIXME zhaojiafu 使用文档注释，标注时间，作者，参数，返回结果
    int getCount(String test);

    // 注册
    int register(UserDO userDO) ;
    
    // 查询【密码】--登陆
    UserDO login(String username) ;	
    
    // 根据【用户名】查询【用户】的数量
    int selectCountByUsername(String username);
}
