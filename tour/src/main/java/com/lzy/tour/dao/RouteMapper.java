package com.lzy.tour.dao;

import java.util.List;
import java.util.Map;

import com.lzy.tour.model.Route;

public interface RouteMapper extends BaseMapper<Route>{
	
	public List<Route> getRouteAllInfoList(Map<String, Object> list);

}
