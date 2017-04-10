/**  
* @Title: AdController.java
* @Package com.lzy.tour.controller
* @author 李志勇  
* @date 2017年2月27日 下午11:45:09
* @version V1.0  
*/ 
package com.lzy.tour.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

	@ApiOperation(value="获取首页banner",notes="获取banner",httpMethod="GET",response = Banner.class,produces=MediaType.APPLICATION_JSON_VALUE)
//    @ApiImplicitParams({@ApiImplicitParam(name = "limit", value = "条数", required = true)
//    ,@ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
//    })
	@RequestMapping(value="/getIndexBanner",method = RequestMethod.GET)
	@ResponseBody
	public List<Banner> getIndexBanner(HttpServletRequest request,@ApiParam(value = "填写条数") @RequestParam Integer limit){
		System.err.println(request.getParameter("limit"));
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
	
	@ApiOperation(value = "添加banner", notes = "添加banner,只需要传入的属性：title,sort,linkUrl,picUrl",httpMethod="POST")  
	@ResponseBody
	@RequestMapping(value="/addBanner")
	public Boolean addBanner(HttpServletRequest request,@ApiParam(value = "banner" ,required=true ) @RequestBody Banner banner){
		if(banner!=null){
			banner.setId(null);
			int insert = bannerService.insert(banner);
			return insert>0?true:false;
		}
		return false;
	}
	
	
}
