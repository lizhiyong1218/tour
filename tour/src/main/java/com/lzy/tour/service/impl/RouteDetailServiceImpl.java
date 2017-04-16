/**  
* @Title: AdServiceImpl.java
* @Package com.lzy.tour.service.impl
* @author 李志勇  
* @date 2017年2月27日 下午11:50:38
* @version V1.0  
*/ 
package com.lzy.tour.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.github.miemiedev.mybatis.paginator.domain.Paginator;
import com.lzy.tour.common.Constans;
import com.lzy.tour.common.Pagination;
import com.lzy.tour.dao.BaseMapper;
import com.lzy.tour.dao.RouteDetailMapper;
import com.lzy.tour.model.RouteDetail;
import com.lzy.tour.service.RouteDetailService;

@Service
public class RouteDetailServiceImpl extends BaseServiceImpl<RouteDetail> implements RouteDetailService{

	private static Logger logger=Logger.getLogger(RouteDetailServiceImpl.class);
	
	@Resource
	private RouteDetailMapper routeDetailMapper;
	
	@Override
	protected BaseMapper<RouteDetail> getMapper() {
		return routeDetailMapper;
	}

	@Override
	public Pagination<RouteDetail> getFrontRouteInfos(Map<String, Object> map,Integer pageNum,Integer pageSize) {
		try {
			if(pageNum==null||pageNum<1){
    			pageNum=1;
    		}
    		if(pageSize==null||pageSize<1){
    			pageSize=Constans.DEFAULT_PAGESIZE;
    		}
			PageBounds pageBouds=new PageBounds(pageNum, pageSize);
			PageList<RouteDetail> res = routeDetailMapper.getFrontRouteInfos(map,pageBouds);
			if(res!=null&&res.getPaginator()!=null){
				Paginator paginator = res.getPaginator();
				return new Pagination<RouteDetail>(pageNum, pageSize, paginator.getTotalCount(), res);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	@Override
	public List<RouteDetail> getMyFrontRouteInfos(Map<String, Object> map) {
		try {
			String userId = (String) map.get("userId");
			if(StringUtils.isNotBlank(userId)){
				return routeDetailMapper.getMyFrontRouteInfos(map);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

}
