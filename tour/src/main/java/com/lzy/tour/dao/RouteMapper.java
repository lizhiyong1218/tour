package com.lzy.tour.dao;

import com.lzy.tour.model.Route;

public interface RouteMapper extends BaseMapper<Route>{
	/**
	 * 查找路线和详情信息
	 * @param id
	 * @return
	 */
	public Route getRouteWithDetailByid(Integer id);
}
