package com.lzy.tour.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lzy.tour.common.CookieUtil;
import com.lzy.tour.enums.RouteTypeEnum;
import com.lzy.tour.enums.UserConstant;
import com.lzy.tour.model.Route;
import com.lzy.tour.model.RouteDetail;
import com.lzy.tour.service.RouteDetailService;
import com.lzy.tour.service.RouteService;

@Api(description="旅游线路接口")
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
	@ApiOperation(value="获取首页路线列表",notes="获取首页路线列表,传入路线类型和显示显示记录数",httpMethod="GET")
	@ResponseBody
	@RequestMapping("/getIndexRouteInfos")
	public List<RouteDetail> getIndexRouteInfos(HttpServletRequest request,@ApiParam(value = "显示条数",required=true) @RequestParam Integer limit,
			@ApiParam(value = "路线类型,类型描述见RouteTypeEnum",required=true) @RequestParam String routeType){
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("routeType", RouteTypeEnum.getByKey(routeType));
		params.put("limit", limit);
		List<RouteDetail> frontRouteInfos = routeDetailService.getFrontRouteInfos(params);
		return frontRouteInfos;
	}
	
	/**
	 * 前台详情页信息
	 * @param request
	 * @param id
	 * @return
	 */
	@ApiOperation(value="获取前台路线详情页信息",notes="传入路线id,返回的结果中包含路线基本信息和routeDetail表中的信息",httpMethod="GET")
	@ResponseBody
	@RequestMapping("/getFrontRouteDetail/{id}")
	public Route getFrontRouteDetail(HttpServletRequest request,@ApiParam(value = "路线id",required=true) @PathVariable Integer id){
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
	@ApiOperation(value="获取前台个人中心我的路线列表",notes="后台设置用户id，前台传入boolean类型参数endFlg，如果为false则查询已报名数据，如果为ture则查询已结束数据",httpMethod="GET")
	@ResponseBody
	@RequestMapping("/getMyFrontRouteInfos")
	public List<RouteDetail> getMyFrontRouteInfos(HttpServletRequest request, @ApiParam(value = "是否结束",required=true) @RequestParam Boolean endFlg){
		Integer userId=null;
		Cookie cooie = CookieUtil.getCooie(request, UserConstant.COOKIE_USER_ID);
		if(cooie!=null&&StringUtils.isNotBlank(cooie.getValue())){//有userid
			userId=Integer.parseInt(cooie.getValue());
		}
		if(userId==null){//TODO
			userId=1;
		}
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("endFlg", endFlg);
		params.put("userId", userId);
		List<RouteDetail> frontRouteInfos = routeDetailService.getMyFrontRouteInfos(params);
		return frontRouteInfos;
	}	
}
