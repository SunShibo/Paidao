package com.solland.paidao.dao;


import com.solland.paidao.entity.UserDO;

public interface UserDAO {
    int getCount(String test);

    int insert(UserDO userDO) ;
}
