package com.solland.paidao.service;


import javax.annotation.Resource;

import com.solland.paidao.entity.TestDO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.solland.paidao.dao.TestDAO;

import java.util.HashMap;
import java.util.Map;

@Service("testService")
@Transactional
public class TestServiceImpl {

    @Resource
    private TestDAO testDAO;

    public int getCount(){
        return testDAO.getCount("") ;
    }

    public int insert (TestDO testDO) {
        return testDAO.insert(testDO) ;
    }

}
