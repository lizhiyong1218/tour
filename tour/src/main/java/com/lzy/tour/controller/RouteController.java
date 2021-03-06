package com.lzy.tour.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lzy.tour.common.ApiResponse;
import com.lzy.tour.common.CookieUtil;
import com.lzy.tour.common.Pagination;
import com.lzy.tour.enums.ResponseStatusEnum;
import com.lzy.tour.enums.RouteTypeEnum;
import com.lzy.tour.model.Route;
import com.lzy.tour.model.RouteDetail;
import com.lzy.tour.service.RouteDetailService;
import com.lzy.tour.service.RouteService;

@Api(description="旅游线路接口")
@Controller
@RequestMapping("route")
public class RouteController {
	
	private static Logger logger=Logger.getLogger(RouteController.class);
	
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
	public ApiResponse getIndexRouteInfos(HttpServletRequest request,@ApiParam(value = "显示条数",required=true) @RequestParam Integer limit,
			@ApiParam(value = "路线类型,类型描述见RouteTypeEnum",required=true) @RequestParam String routeType)throws Exception{
		ApiResponse apiResponse=new ApiResponse();
		apiResponse.setStatus(ResponseStatusEnum.SYSERR);
		try {
			Map<String, Object> params=new HashMap<String, Object>();
			params.put("routeType", RouteTypeEnum.getByKey(routeType));
			Pagination<RouteDetail> pagination = routeDetailService.getFrontRouteInfos(params, 1, limit);
			if(pagination!=null){
				apiResponse.setStatus(ResponseStatusEnum.SUCCESS);
				apiResponse.setData(pagination.getRecordList());	
			}
		} catch (Exception e) {
			apiResponse.setMsg(e.getMessage());
			logger.error(e);
			throw e;
		}
		return apiResponse;
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
	public ApiResponse getFrontRouteDetail(HttpServletRequest request,@ApiParam(value = "路线id",required=true) @PathVariable Integer id)throws Exception{
		ApiResponse apiResponse=new ApiResponse();
		apiResponse.setStatus(ResponseStatusEnum.SYSERR);
		try {
			Route route = routeService.getRouteWithDetailByid(id);
			apiResponse.setStatus(ResponseStatusEnum.SUCCESS);
			apiResponse.setData(route);	
		} catch (Exception e) {
			apiResponse.setMsg(e.getMessage());
			logger.error(e);
			throw e;
		}
		return apiResponse;
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
	public ApiResponse getMyFrontRouteInfos(HttpServletRequest request, @ApiParam(value = "是否结束",required=true) @RequestParam Boolean endFlg)throws Exception{
		ApiResponse apiResponse=new ApiResponse();
		apiResponse.setStatus(ResponseStatusEnum.SYSERR);
		Integer userId=CookieUtil.getUserIdFromCookie(request);
		if(userId!=null){
			Map<String, Object> params=new HashMap<String, Object>();
			params.put("endFlg", endFlg);
			params.put("userId", userId);
			List<RouteDetail> frontRouteInfos = routeDetailService.getMyFrontRouteInfos(params);
			apiResponse.setStatus(ResponseStatusEnum.SUCCESS);
			apiResponse.setData(frontRouteInfos);
		}
		return apiResponse;
	}
	
	@ApiOperation(value = "添加路线", notes = "添加路线",httpMethod="POST")  
	@ResponseBody
	@RequestMapping(value="/manage/addRoute")
	public ApiResponse addRoute(HttpServletRequest request,@ApiParam(value = "route" ,required=true ) @RequestBody Route route)throws Exception{
		ApiResponse apiResponse=new ApiResponse();
		apiResponse.setStatus(ResponseStatusEnum.SYSERR);
		try {
			if(route!=null){
				try {
					routeService.addRouteAndDetail(route);
					apiResponse.setStatus(ResponseStatusEnum.SUCCESS);
					apiResponse.setData(true);	
				} catch (Exception e) {
					logger.error(e);
					throw e;
				}
			}
		} catch (Exception e) {
			apiResponse.setMsg(e.getMessage());
			logger.error(e);
			throw e;
		}
		return apiResponse;
	}
	
	@ApiOperation(value = "修改路线", notes = "修改路线",httpMethod="POST")  
	@ResponseBody
	@RequestMapping(value="/manage/updateRoute")
	public ApiResponse updateRoute(HttpServletRequest request,@ApiParam(value = "route" ,required=true ) @RequestBody Route route)throws Exception{
		ApiResponse apiResponse=new ApiResponse();
		apiResponse.setStatus(ResponseStatusEnum.SYSERR);
		try {
			if(route!=null&&route.getId()!=null){
				boolean res= routeService.update(route)>0?true:false; 
				if(res){
					apiResponse.setStatus(ResponseStatusEnum.SUCCESS);
					apiResponse.setData(res);				
				}
			}
		} catch (Exception e) {
			apiResponse.setMsg(e.getMessage());
			logger.error(e);
			throw e;
		}
		return apiResponse;
	}
	
	@ApiOperation(value="获取后台分页路线列表",notes="获取后台分页路线列表,传入路线类型和分页信息",httpMethod="GET")
	@ResponseBody
	@RequestMapping("/manage/getRouteList")
	public ApiResponse getRouteList(HttpServletRequest request,
			@ApiParam(value = "第几页",required=true) @RequestParam Integer pageNum,
			@ApiParam(value = "每页记录数",required=true) @RequestParam Integer pageSize,
			@ApiParam(value = "路线类型,类型描述见RouteTypeEnum",required=false) @RequestParam(required=false) String routeType)throws Exception{
		ApiResponse apiResponse=new ApiResponse();
		apiResponse.setStatus(ResponseStatusEnum.SYSERR);
		try {
			Map<String, Object> params=new HashMap<String, Object>();
			params.put("routeType", RouteTypeEnum.getByKey(routeType));
			Pagination<RouteDetail> pagination = routeDetailService.getFrontRouteInfos(params, pageNum, pageSize);
			apiResponse.setData(pagination);
			apiResponse.setStatus(ResponseStatusEnum.SUCCESS);
		} catch (Exception e) {
			apiResponse.setMsg(e.getMessage());
			logger.error(e);
			throw e;
		}
		return apiResponse;
	}
	
}
