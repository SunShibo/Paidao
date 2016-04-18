package com.solland.paidao.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.solland.paidao.entity.ActivityDO;
import com.solland.paidao.entity.bo.ActivityBO;
import com.solland.paidao.entity.bo.UserBO;
import com.solland.paidao.entity.dto.ResultDTOBuilder;
import com.solland.paidao.service.ActivityService;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.solland.paidao.entity.HeatDO;
import com.solland.paidao.entity.dto.ResultDTO;
import com.solland.paidao.service.HeatService;
import com.solland.paidao.util.JsonUtils;
import com.solland.paidao.web.controller.base.BaseCotroller;

import java.util.Date;
import java.util.List;

/**
 * 加热（点赞）
 * @author zhaojiafu
 *
 * 2016年1月11日 下午3:15:54
 */
@Controller
@RequestMapping( value = "/heat" )
public class HeatController extends BaseCotroller {

	@Resource( name = "heatService" )
	private HeatService heatService;

	@Resource( name = "activityService" )
	private ActivityService activityService;

	@RequestMapping( value = "/eventsHeatup" )
	public void eventsHeatup(HttpServletRequest request , HttpServletResponse response,  int activityId) {

		UserBO loginUser = super.getLoginUser(request);
		List<HeatDO> heatInfoByUidAndActivifyId = heatService.getHeatInfoByUidAndActivifyId(loginUser.getId(), activityId);
		if (heatInfoByUidAndActivifyId != null && heatInfoByUidAndActivifyId.size() > 0) { // 判断事件是否加热过
			String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0020004")) ;
			super.safeJsonPrint(response , json);
			return ;
		}

		ActivityBO activityDO = activityService.getActivityById(activityId); // 获取动态圈信息
		if (activityDO == null ) {
			String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001")) ;
			super.safeJsonPrint(response , json);
			return ;
		}
		HeatDO heatDO = new HeatDO() ;
		heatDO.setActivityId(activityId);
		heatDO.setCreateTime(new Date());
		heatDO.setHeatValue(1);
		heatDO.setIncreaseType(HeatDO.TYPE_USER_ADD);
		heatDO.setModifyTime(new Date());
		heatDO.setUserId(loginUser.getId());
		heatDO.setLastTimeHeatValue(activityDO.getHeatValue());
		heatDO.setAuthorId(activityDO.getUserId());
		heatService.insert(heatDO);

		activityService.updateHeatValue(activityId , activityDO.getHeatValue() + heatDO.getHeatValue()) ; // 修改热度值

		JSONObject jsonObject = new JSONObject() ;
		jsonObject.put("activityId" , activityId) ;
		jsonObject.put("heatValue" , activityDO.getHeatValue() + heatDO.getHeatValue()) ;

		String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(jsonObject)) ;
		super.safeJsonPrint(response , json);
		return ;
	}

	/**
	 * 添加【加热】
	 * 2016年1月11日 下午3:31:50
	 * @author zhaojiafu
	 * @param response
	 * @param heatDO
	 */
	@RequestMapping( value = "/insertHeat" )
	public void insertHeat(HttpServletResponse response, HeatDO heatDO){
		String result = null;
		
		/* 1. 验证参数的完整性 */
		if(null == heatDO){
			result = JsonUtils.getJsonString4JavaPOJO(new ResultDTO(false, "0", "参数异常"));
			super.safeJsonPrint(response, result);
			return;
		}
		
		/* 2. 执行添加【热度】 */
		heatService.insert(heatDO);

		/* 3. 返回消息到客户端 */
		result = JsonUtils.getJsonString4JavaPOJO(new ResultDTO("添加【热度】成功。"));
		super.safeJsonPrint(response, result);
	}
}
