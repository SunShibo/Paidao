package com.solland.paidao.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.solland.paidao.dao.PraiseDAO;
import com.solland.paidao.entity.PraiseDO;
import com.solland.paidao.service.PraiseService;

/**
 * 赞
 * @author zhaojiafu
 *
 * 2016年1月7日 下午5:48:20
 */
@Service( value = "praiseService")
public class PraiseServiceImpl implements PraiseService {
	@Resource
	private PraiseDAO praiseDAO;

	@Override
	public void insert(PraiseDO praiseDO) {
		praiseDAO.insert(praiseDO);
	}

}
