package com.solland.paidao.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.solland.paidao.dao.InformDAO;
import com.solland.paidao.entity.InformDO;
import com.solland.paidao.service.InformService;

/**
 * 举报
 * @author zhaojiafu
 *
 * 2016年1月13日 下午4:26:00
 */
@Service( value = "informService" )
public class InformServiceImpl implements InformService {
	@Resource
	private InformDAO informDAO;

	@Override
	public void insertInform(InformDO informDO) {
		informDAO.insert(informDO);
	}

}
