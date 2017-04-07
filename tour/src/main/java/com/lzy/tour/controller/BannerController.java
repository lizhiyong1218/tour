/**  
* @Title: AdController.java
* @Package com.lzy.tour.controller
* @author 李志勇  
* @date 2017年2月27日 下午11:45:09
* @version V1.0  
*/ 
package com.lzy.tour.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
public class BannerController {
	
	@Resource
	private BannerService bannerService;
	
	@RequestMapping("/getIndexBanner")
	@ResponseBody
	private List<Banner> getIndexBanner(HttpServletRequest request,Integer limit){
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
	
}
