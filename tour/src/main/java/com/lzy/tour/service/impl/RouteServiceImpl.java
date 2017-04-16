/**  
* @Title: AdServiceImpl.java
* @Package com.lzy.tour.service.impl
* @author 李志勇  
* @date 2017年2月27日 下午11:50:38
* @version V1.0  
*/ 
package com.lzy.tour.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.lzy.tour.dao.BaseMapper;
import com.lzy.tour.dao.RouteDetailMapper;
import com.lzy.tour.dao.RouteMapper;
import com.lzy.tour.enums.RouteStatusEnum;
import com.lzy.tour.enums.StatusEnum;
import com.lzy.tour.model.Route;
import com.lzy.tour.model.RouteDetail;
import com.lzy.tour.service.RouteService;

/**
 * 
* @ClassName: RouteServiceImpl
* @Description: 线路 
* @author 李志勇
* @date 2017年3月31日 上午12:04:53
*
 */
@Service
public class RouteServiceImpl extends BaseServiceImpl<Route> implements RouteService{

	private static Logger logger=Logger.getLogger(RouteServiceImpl.class);
	
	@Resource
	private RouteMapper routeMapper;
	@Resource
	private RouteDetailMapper routeDetailMapper;
	
	@Override
	protected BaseMapper<Route> getMapper() {
		return routeMapper;
	}

	@Override
	public void addRouteAndDetail(Route route) throws Exception {
		if(route!=null&&CollectionUtils.isNotEmpty(route.getStartDates())&&route.getTotalDate()!=null&&route.getTotalDate()>0){
		}else{
			return;
		}
		Date createTime = new Date();
		route.setId(null);
		route.setStatus(StatusEnum.ENABLED);
		route.setCreateTime(createTime);
		routeMapper.insertSelective(route);
		if(route.getId()!=null){
			List<RouteDetail> routeDetails = new ArrayList<RouteDetail>();
			RouteDetail routeDetail=new RouteDetail();
			for(Date startTime:route.getStartDates()){
				Date endTime=getEndDate(startTime, route.getTotalDate());
				routeDetail.setRouteId(route.getId());
				routeDetail.setStartTime(startTime);
				routeDetail.setRouteStatus(RouteStatusEnum.APPLYING);
				routeDetail.setApplyNum(0);
				routeDetail.setEndTime(endTime);
				routeDetails.add(routeDetail);
			}
			routeDetailMapper.addBatchs(routeDetails);
		}
	}
	
	private static Date getEndDate(Date date,int day){
		Calendar c = Calendar.getInstance();
		c.setTime(date);//可以将date类型转为Calendar
		c.add(Calendar.DATE,day);
		Date endTime = c.getTime();
		return endTime;
	}

	@Override
	public Route getRouteWithDetailByid(Integer id) {
		try {
			return routeMapper.getRouteWithDetailByid(id);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

}
