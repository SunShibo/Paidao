package com.solland.paidao.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.solland.paidao.dao.EnshrineDAO;
import com.solland.paidao.entity.EnshrineDO;
import com.solland.paidao.service.EnshrineService;

/**
 * 【收藏】
 * @author zhaojiafu
 *
 * 2016年1月11日 下午4:08:41
 */
@Service( value = "enshrineService" )
public class EnshrineServiceImpl implements EnshrineService {
	@Resource
	private EnshrineDAO enshrineDAO;

	@Override
	public void insert(EnshrineDO enshrineDO) {
		enshrineDAO.insert(enshrineDO);
	}

}
