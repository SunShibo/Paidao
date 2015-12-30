package com.solland.paidao.dao;


import org.apache.ibatis.annotations.Param;

public interface TestDAO {
    int getCount(@Param("taskUsers")String test);
}
