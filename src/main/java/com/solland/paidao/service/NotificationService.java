package com.solland.paidao.service;

import com.solland.paidao.entity.NotificationDO;

import java.util.List;

/**
 * 用户
 * @author zhaojiafu
 *
 * 2016年1月5日 下午6:02:33
 */
public interface NotificationService {

	void addNotificationForAllUser(int activityId , String type , int loginUserId, String content) ;

	int readNotification (int notificationId) ;

	List<NotificationDO> getNotificationList (int loginUserId) ;

	void addNotificationForReplyComment (int activityId, String type , int loginUserId , String content , int toUserId) ;

	int delNotificationById (int notificaitonId) ;
}
