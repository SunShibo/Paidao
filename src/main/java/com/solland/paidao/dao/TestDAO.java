package com.solland.paidao.dao;


import org.apache.ibatis.annotations.Param;

import com.solland.paidao.entity.TestDO;

public interface TestDAO {
    int getCount(@Param("taskUsers")String test);

    int insert(TestDO testDO) ;
}
