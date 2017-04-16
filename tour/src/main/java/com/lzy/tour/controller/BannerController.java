/**  
* @Title: AdController.java
* @Package com.lzy.tour.controller
* @author 李志勇  
* @date 2017年2月27日 下午11:45:09
* @version V1.0  
*/ 
package com.lzy.tour.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lzy.tour.common.Pagination;
import com.lzy.tour.enums.StatusEnum;
import com.lzy.tour.model.Banner;
import com.lzy.tour.service.BannerService;

/**
 * @ClassName: AdController
 * @author 李志勇
 * @date 2017年2月27日 下午11:45:09
 *
 */
@Controller
@RequestMapping("banner")
@Api(description="banner接口")
public class BannerController {
	
	@Resource
	private BannerService bannerService;

	@ApiOperation(value="获取首页banner",notes="获取banner",httpMethod="GET")
	@RequestMapping(value="/getIndexBanner",method = RequestMethod.GET)
	@ResponseBody
	public List<Banner> getIndexBanner(HttpServletRequest request,@ApiParam(value = "显示条数") @RequestParam Integer limit){
		Map<String, Object> paras=new HashMap<String, Object>();
		if(limit!=null&&limit>0){
			paras.put("limit", limit);
		}else{
			paras.put("limit", 5);
		}
		paras.put("sortOrderby", "asc");
		List<Banner> ads=bannerService.getAll(paras);
		return ads;
	}
	
	@ApiOperation(value="获取单个banner",notes="获取banner",httpMethod="GET")
	@RequestMapping(value="/manage/getBannerDetail/{id}",method = RequestMethod.GET)
	@ResponseBody
	public Banner getBannerDetail(HttpServletRequest request,@ApiParam(value = "id",required=true) @PathVariable Integer id){
		Banner banner = bannerService.getOneById(id); 
		return banner;
	}
	
	@ApiOperation(value = "添加banner", notes = "添加banner,只需要传入的属性：title,sort,linkUrl,picUrl",httpMethod="POST")  
	@ResponseBody
	@RequestMapping(value="/manage/addBanner")
	public Boolean addBanner(HttpServletRequest request,@ApiParam(value = "banner" ,required=true ) @RequestBody Banner banner){
		if(banner!=null){
			banner.setId(null);
			banner.setCreateTime(new Date());
			banner.setStatus(StatusEnum.ENABLED);
			return bannerService.insert(banner)>0?true:false;
		}
		return false;
	}
	
	@ApiOperation(value = "修改banner", notes = "修改banner,只需要传入的属性：id,title,sort,linkUrl,picUrl",httpMethod="POST")  
	@ResponseBody
	@RequestMapping(value="/manage/updateBanner")
	public Boolean updateBanner(HttpServletRequest request,@ApiParam(value = "banner" ,required=true ) @RequestBody Banner banner){
		if(banner!=null&&banner.getId()!=null){
			banner.setUpdateTime(new Date());
			return bannerService.update(banner)>0?true:false;
		}
		return false;
	}
	
	@ApiOperation(value="获取后台分页banner",notes="获取后台分页banner",httpMethod="GET")
	@RequestMapping(value="/manage/getBannerList",method = RequestMethod.GET)
	@ResponseBody
	public Pagination<Banner> getBannerList(HttpServletRequest request,@ApiParam(value = "第几页",required=true) @RequestParam Integer pageNum,
			@ApiParam(value = "每页记录数",required=true) @RequestParam Integer pageSize){
		Map<String, Object> paras=new HashMap<String, Object>();
		paras.put("createTimeOrderby", "desc");
		Pagination<Banner> pagination = bannerService.getPagination(paras, pageNum, pageSize);
		return pagination;
	}
}
