package com.solland.paidao.service;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.solland.paidao.dao.TestDAO;

@Service("testService")
@Transactional
public class TestServiceImpl {

    @Resource
    private TestDAO testDAO;

    public int getCount(){
        return testDAO.getCount("") ;
    }

}
