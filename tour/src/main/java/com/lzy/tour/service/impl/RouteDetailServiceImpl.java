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
import com.lzy.tour.dao.RouteDetailMapper;
import com.lzy.tour.model.RouteDetail;
import com.lzy.tour.service.RouteDetailService;

@Service
public class RouteDetailServiceImpl extends BaseServiceImpl<RouteDetail> implements RouteDetailService{

	@Resource
	private RouteDetailMapper routeDetailMapper;
	
	@Override
	protected BaseMapper<RouteDetail> getMapper() {
		return routeDetailMapper;
	}

}
