package com.lzy.tour.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lzy.tour.enums.RouteTypeEnum;
import com.lzy.tour.model.Route;
import com.lzy.tour.model.RouteDetail;
import com.lzy.tour.service.RouteDetailService;
import com.lzy.tour.service.RouteService;

@Controller
@RequestMapping("route")
public class RouteController {
	
	@Resource
	private RouteService routeService;
	@Resource
	private RouteDetailService routeDetailService;
	
	/**
	 * 首页路线列表
	 * @param request
	 * @param limit
	 * @param routeType
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getIndexRouteInfos")
	public List<RouteDetail> getIndexRouteInfos(HttpServletRequest request,Integer limit,String routeType){
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("routeType", RouteTypeEnum.getByKey(routeType));
		params.put("limit", limit);
		List<RouteDetail> frontRouteInfos = routeDetailService.getFrontRouteInfos(params);
		return frontRouteInfos;
	}
	
	/**
	 * 前台路线和详情信息
	 * @param request
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getFrontRouteDetail/{id}")
	public Route getFrontRouteDetail(HttpServletRequest request,@PathVariable Integer id){
		Route route = routeService.getRouteWithDetailByid(id);
		return route;
	}
	
	/**
	 * 获取我的路线
	 * @param request
	 * @param limit
	 * @param routeType
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getMyFrontRouteInfos")
	public List<RouteDetail> getMyFrontRouteInfos(HttpServletRequest request,Boolean endFlg){
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("endFlg", endFlg);
		List<RouteDetail> frontRouteInfos = routeDetailService.getMyFrontRouteInfos(params);
		return frontRouteInfos;
	}	
}
