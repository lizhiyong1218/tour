/**  
* @Title: AdServiceImpl.java
* @Package com.lzy.tour.service.impl
* @author 李志勇  
* @date 2017年2月27日 下午11:50:38
* @version V1.0  
*/ 
package com.lzy.tour.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.lzy.tour.dao.BaseMapper;
import com.lzy.tour.dao.RouteDetailMapper;
import com.lzy.tour.dao.RouteMapper;
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
		if(route==null){
			return;
		}
		routeMapper.insertSelective(route);
		if(route.getId()!=null&&CollectionUtils.isNotEmpty(route.getRouteDetails())){
			List<RouteDetail> routeDetails = route.getRouteDetails();
			for (RouteDetail routeDetail : routeDetails) {
				routeDetail.setRouteId(route.getId());
			}
			routeDetailMapper.addBatchs(routeDetails);
		}
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
