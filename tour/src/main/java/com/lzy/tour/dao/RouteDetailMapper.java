package com.lzy.tour.dao;

import java.util.List;
import java.util.Map;

import com.lzy.tour.model.RouteDetail;

public interface RouteDetailMapper extends BaseMapper<RouteDetail>{

	/**
	 * 
	 * @Title: getFrontRouteInfos
	 * @Description: 获取首页路线列表
	 * @param map
	 * @return:     
	 * List<RouteDetail>    
	 * @throws
	 */
	public List<RouteDetail> getFrontRouteInfos(Map<String, Object> map);
	
	/**
	 * 
	 * @Title: batchInsert
	 * @Description: 批量新增
	 * @param list:     
	 * void    
	 * @throws
	 */
	public void addBatchs(List<RouteDetail> list);
	
}
