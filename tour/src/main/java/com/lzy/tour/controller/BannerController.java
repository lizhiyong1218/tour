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

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lzy.tour.common.ApiResponse;
import com.lzy.tour.common.Pagination;
import com.lzy.tour.enums.ResponseStatusEnum;
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
	
	private static Logger logger=Logger.getLogger(BannerController.class);
	
	@Resource
	private BannerService bannerService;

	@ApiOperation(value="获取首页banner",notes="获取banner",httpMethod="GET")
	@RequestMapping(value="/getIndexBanner",method = RequestMethod.GET)
	@ResponseBody
	public ApiResponse getIndexBanner(HttpServletRequest request,@ApiParam(value = "显示条数") @RequestParam Integer limit){
		ApiResponse apiResponse=new ApiResponse();
		apiResponse.setStatus(ResponseStatusEnum.SYSERR);
		try {
			Map<String, Object> paras=new HashMap<String, Object>();
			if(limit!=null&&limit>0){
				paras.put("limit", limit);
			}else{
				paras.put("limit", 5);
			}
			paras.put("sortOrderby", "asc");
			List<Banner> ads=bannerService.getAll(paras);
			apiResponse.setData(ads);
			apiResponse.setStatus(ResponseStatusEnum.SUCCESS);
		} catch (Exception e) {
			logger.error(e);
		}
		return apiResponse;
	}
	
	@ApiOperation(value="获取单个banner",notes="获取banner",httpMethod="GET")
	@RequestMapping(value="/manage/getBannerDetail/{id}",method = RequestMethod.GET)
	@ResponseBody
	public ApiResponse getBannerDetail(HttpServletRequest request,@ApiParam(value = "id",required=true) @PathVariable Integer id){
		ApiResponse apiResponse=new ApiResponse();
		apiResponse.setStatus(ResponseStatusEnum.SYSERR);
		try {
			Banner banner = bannerService.getOneById(id); 
			apiResponse.setData(banner);
			apiResponse.setStatus(ResponseStatusEnum.SUCCESS);
		} catch (Exception e) {
			apiResponse.setMsg(e.getMessage());
			logger.error(e);
		}
		return apiResponse;
	}
	
	@ApiOperation(value = "添加banner", notes = "添加banner,只需要传入的属性：title,sort,linkUrl,picUrl",httpMethod="POST")  
	@ResponseBody
	@RequestMapping(value="/manage/addBanner")
	public ApiResponse addBanner(HttpServletRequest request,@ApiParam(value = "banner" ,required=true ) @RequestBody Banner banner){
		ApiResponse apiResponse=new ApiResponse();
		apiResponse.setStatus(ResponseStatusEnum.SYSERR);
		try {
			if(banner!=null){
				banner.setId(null);
				banner.setCreateTime(new Date());
				banner.setStatus(StatusEnum.ENABLED);
				boolean res=bannerService.insert(banner)>0?true:false;
				if(res){
					apiResponse.setStatus(ResponseStatusEnum.SUCCESS);
					apiResponse.setData(res);				
				}
			}
		} catch (Exception e) {
			apiResponse.setMsg(e.getMessage());
			logger.error(e);
		}
		return apiResponse;
	}
	
	@ApiOperation(value = "修改banner", notes = "修改banner,只需要传入的属性：id,title,sort,linkUrl,picUrl",httpMethod="POST")  
	@ResponseBody
	@RequestMapping(value="/manage/updateBanner")
	public ApiResponse updateBanner(HttpServletRequest request,@ApiParam(value = "banner" ,required=true ) @RequestBody Banner banner){
		ApiResponse apiResponse=new ApiResponse();
		apiResponse.setStatus(ResponseStatusEnum.SYSERR);
		try {
			if(banner!=null&&banner.getId()!=null){
				banner.setUpdateTime(new Date());
				boolean res= bannerService.update(banner)>0?true:false;
				if(res){
					apiResponse.setStatus(ResponseStatusEnum.SUCCESS);
					apiResponse.setData(res);				
				}
			}else{
				apiResponse.setMsg("id为空");
			}
		} catch (Exception e) {
			apiResponse.setMsg(e.getMessage());
			logger.error(e);
		}
		return apiResponse;
	}
	
	@ApiOperation(value="获取后台分页banner",notes="获取后台分页banner",httpMethod="GET")
	@RequestMapping(value="/manage/getBannerList",method = RequestMethod.GET)
	@ResponseBody
	public ApiResponse getBannerList(HttpServletRequest request,@ApiParam(value = "第几页",required=true) @RequestParam Integer pageNum,
			@ApiParam(value = "每页记录数",required=true) @RequestParam Integer pageSize){
		ApiResponse apiResponse=new ApiResponse();
		apiResponse.setStatus(ResponseStatusEnum.SYSERR);
		try {
			Map<String, Object> paras=new HashMap<String, Object>();
			paras.put("createTimeOrderby", "desc");
			Pagination<Banner> pagination = bannerService.getPagination(paras, pageNum, pageSize);
			apiResponse.setStatus(ResponseStatusEnum.SUCCESS);
			apiResponse.setData(pagination);	
		} catch (Exception e) {
			apiResponse.setMsg(e.getMessage());
			logger.error(e);
		}
		return apiResponse;
	}
}
