package com.solland.paidao.dao;

import com.solland.paidao.entity.RemoveActivityDO;

import java.util.List;


public interface RemoveActivityDAO {


	int addRemoveItem(RemoveActivityDO removeActivityDO);

	List<RemoveActivityDO> selectRemoveListByUserId (int userId) ;
}
