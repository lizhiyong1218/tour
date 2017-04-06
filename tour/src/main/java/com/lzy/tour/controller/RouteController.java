package com.lzy.tour.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lzy.tour.enums.RouteTypeEnum;
import com.lzy.tour.model.Route;
import com.lzy.tour.service.RouteService;

@Controller
@RequestMapping("route")
public class RouteController {
	
	@Resource
	private RouteService routeService;

	@ResponseBody
	@RequestMapping("getRoutes")
	public List<Route> getRoutes(HttpServletRequest request,Integer limit,String routeType){
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("routeType", RouteTypeEnum.getByKey(routeType));
		params.put("limit", limit);
		List<Route> list=routeService.getAll(params);
		return list;
	}
	
}
