/**  
* @Title: AdServiceImpl.java
* @Package com.lzy.tour.service.impl
* @author 李志勇  
* @date 2017年2月27日 下午11:50:38
* @version V1.0  
*/ 
package com.lzy.tour.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lzy.tour.dao.BaseMapper;
import com.lzy.tour.dao.RouteMapper;
import com.lzy.tour.model.Route;
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

	@Resource
	private RouteMapper routeMapper;
	
	@Override
	protected BaseMapper<Route> getMapper() {
		return routeMapper;
	}

}
