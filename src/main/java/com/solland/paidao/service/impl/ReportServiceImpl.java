package com.solland.paidao.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.solland.paidao.dao.ReportDAO;
import com.solland.paidao.entity.ReportDO;
import com.solland.paidao.service.ReportService;

/**
 * 举报
 * @author zhaojiafu
 *
 * 2016年1月13日 下午4:26:00
 */
@Service( value = "reportService" )
public class ReportServiceImpl implements ReportService {
	@Resource
	private ReportDAO informDAO;

	@Override
	public void addReport(ReportDO reportDO) {
		informDAO.insert(reportDO);
	}

}
