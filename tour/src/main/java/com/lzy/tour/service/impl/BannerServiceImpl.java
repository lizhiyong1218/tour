/**  
* @Title: AdServiceImpl.java
* @Package com.lzy.tour.service.impl
* @author 李志勇  
* @date 2017年2月27日 下午11:50:38
* @version V1.0  
*/ 
package com.lzy.tour.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lzy.tour.dao.BannerMapper;
import com.lzy.tour.dao.BaseMapper;
import com.lzy.tour.model.Banner;
import com.lzy.tour.service.BannerService;

/**
 * @ClassName: BannerServiceImpl
 * @Description: 广告 
 * @author 李志勇
 * @date 2017年2月27日 下午11:50:38
 *
 */
@Service
public class BannerServiceImpl extends BaseServiceImpl<Banner> implements BannerService{

	@Resource
	private BannerMapper bannerMapper;
	
	@Override
	protected BaseMapper<Banner> getMapper() {
		return bannerMapper;
	}

}
