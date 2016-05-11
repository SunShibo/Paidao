package com.solland.paidao.dao;

import com.solland.paidao.entity.NotificationDO;

import java.util.List;

/**
 * 通知
 *
 */
public interface NotificationDAO {

	/**
	 * 添加通知
	 */
	int insertNotification(NotificationDO notificationDOØ);


	List<NotificationDO> selectNotificationListByUserId (int userId) ;

	int updateStatus (int id) ;

}
