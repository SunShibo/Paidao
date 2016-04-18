package com.solland.paidao.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.solland.paidao.entity.dto.param.QueryActivityParam;
import com.solland.paidao.util.page.Page;
import com.solland.paidao.util.page.PageBuilder;
import com.solland.paidao.util.page.QueryObj;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.solland.paidao.dao.ActivityDAO;
import com.solland.paidao.entity.ActivityDO;
import com.solland.paidao.entity.bo.ActivityBO;
import com.solland.paidao.entity.dto.param.AddActivityParam;
import com.solland.paidao.service.ActivityService;
import com.solland.paidao.util.DateUtils;
import com.solland.paidao.util.OssManage;

/**
 * 活动圈
 * @author zhaojiafu
 *
 * 2016年1月8日 下午3:10:17
 */
@Service( value = "activityService" )
public class ActivityServiceImpl implements ActivityService {

	@Resource
	ActivityDAO activityDAO;


	@Resource(name = "ossManage")
	OssManage ossManage ;

	@Override
	public boolean insertActivity(AddActivityParam addActivityParam, CommonsMultipartFile[] files) throws IOException {
		String fileName = files[0].getOriginalFilename();
		String type = fileName.substring(fileName.lastIndexOf(".") + 1);
		String key = addActivityParam.getUserId() + "/" + "Activity" + "/" + DateUtils.formatDate(DateUtils.DATE_PATTERN_PLAIN , new Date()) + "/" +System.currentTimeMillis() ;
		String url = ossManage.uploadFile(files[0].getInputStream(), key, type) ;

		addActivityParam.setMediaPaths(url);
		ActivityDO activityDO = new ActivityDO() ;

		BeanUtils.copyProperties(addActivityParam, activityDO) ;
		return activityDAO.insertActivity(activityDO) > 0 ? true : false ;
	}

	public Page<ActivityBO> getActivityPage (QueryActivityParam queryActivityParam , QueryObj queryObj) {
		Map<String , Object> map = new HashMap() ;
		map.put("offset" , queryObj.getPageOffset()) ;
		map.put("itemId" , queryObj.getLastItemId()) ;
		map.put("pageSize" , queryObj.getPageSize()) ;
		map.put("search" , queryObj.getSearch()) ;
		map.put("latitude" , queryActivityParam.getLatitude()) ;
		map.put("longitude" , queryActivityParam.getLongitude()) ;
		List<ActivityBO> activityList = activityDAO.selectActivityListPage(map);
		return PageBuilder.savePage(activityList , queryObj) ;
	}

	/**
	 * 修改事件的热度值
	 * @param activityId
	 * @param heatValue
	 * @return
	 */
	public int updateHeatValue (int activityId , int heatValue) {
		return activityDAO.updateHeatValue(activityId , heatValue);
	}

	public ActivityBO getActivityById (int userId) {
		return activityDAO.selectActivityById(userId) ;
	}


	@Override
	public void updateActivityById(ActivityDO activityDO) {
		activityDAO.updateActivityById(activityDO);
	}

	@Override
	public void deleteActivityById(int id) {
		activityDAO.deleteActivityById(id);
	}


}
