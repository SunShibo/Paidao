package com.solland.paidao.service.impl;

import com.google.common.collect.Sets;
import com.solland.paidao.dao.NotificationDAO;
import com.solland.paidao.dao.UserDAO;
import com.solland.paidao.entity.NotificationDO;
import com.solland.paidao.entity.UserDO;
import com.solland.paidao.entity.bo.ActivityBO;
import com.solland.paidao.service.*;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * 用户
 * @author zhaojiafu
 *
 * 2016年1月6日 上午10:29:13
 */
@Service("notificationService")
public class NotificationServiceImpl implements NotificationService
{
	@Resource(name = "activityService")
    private ActivityService activityService ;

	@Resource(name = "commentService")
	private CommentService commentService ;

	@Resource(name = "heatService")
	private HeatService heatService ;

	@Resource
	NotificationDAO notificationDAO;


	@Override
	public void addNotificationForAllUser(int activityId, String type , int loginUserId , String content) {

		Set<Integer> allNeedToNoticeUserIds = this.getAllNeedToNoticeUserIds(activityId);
		this.addItems(allNeedToNoticeUserIds , activityId , loginUserId , type , content);
	}

	public void addNotificationForReplyComment (int activityId, String type , int loginUserId , String content , int toUserId) {
		Set<Integer> id = Sets.newHashSet(toUserId);
		this.addItems(id , activityId , loginUserId , type , content);
	}

	public Set<Integer> getAllNeedToNoticeUserIds (int activityId) {
		Set<Integer> idSet = Sets.newHashSet() ;

		List heatUserIds = heatService.getHeatUserIdList(activityId) ;
		List<Integer> commentUserIdList = commentService.getCommentUserIdList(activityId);

		if (CollectionUtils.isNotEmpty(heatUserIds)) {
			idSet.addAll(heatUserIds) ;
		}
		if (CollectionUtils.isNotEmpty(commentUserIdList)) {
			idSet.addAll(commentUserIdList) ;
		}

		return idSet ;
	}

	public void addItems (Set<Integer> ids , int activityId , int doUserId , String type , String content) {

		if (CollectionUtils.isNotEmpty(ids)) {

			for (int id : ids) {
				NotificationDO notifi = new NotificationDO() ;
				notifi.setActivityId(activityId);
				notifi.setDoUserId(doUserId);
				notifi.setNoticeStatus(NotificationDO.STATUS_NEW);
				notifi.setToUserId(id);
				notifi.setNoticeType(type);
				notifi.setContent(content);

				notificationDAO.insertNotification(notifi) ;
			}
		}
	}

	public int readNotification (int notificationId) {
		return notificationDAO.updateStatus(notificationId) ;
	}

	@Override
	public List<NotificationDO> getNotificationList(int loginUserId) {
		return notificationDAO.selectNotificationListByUserId(loginUserId);
	}
}

