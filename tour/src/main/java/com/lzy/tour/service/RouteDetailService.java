package com.lzy.tour.service;

import java.util.List;
import java.util.Map;

import com.lzy.tour.common.Pagination;
import com.lzy.tour.model.RouteDetail;

public interface RouteDetailService extends BaseService<RouteDetail>{
	/**
	 * 
	 * @Title: getFrontRouteInfos
	 * @Description: 获取首页路线列表
	 * @param map
	 * @return:     
	 * List<RouteDetail>    
	 * @throws
	 */
	public Pagination<RouteDetail> getFrontRouteInfos(Map<String, Object> map,Integer pagetNum,Integer pageSize);
	
	/**
	 * 我的路线列表
	 * @param map
	 * @return
	 */
	public List<RouteDetail> getMyFrontRouteInfos(Map<String, Object> map);
	
}
