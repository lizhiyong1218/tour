package com.lzy.tour.service;

import com.lzy.tour.model.Route;

public interface RouteService extends BaseService<Route>{
	/**
	 * 增加路线和详情
	 * @param route
	 * @throws Exception
	 */
	public void addRouteAndDetail(Route route) throws Exception;
	
	/**
	 * 查找路线和详情信息
	 * @param id
	 * @return
	 */
	public Route getRouteWithDetailByid(Integer id);
}
