/**
 * 
 */
package org.lzy.tour;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lzy.tour.dao.BannerMapper;
import com.lzy.tour.enums.CityEnum;
import com.lzy.tour.enums.StatusEnum;
import com.lzy.tour.model.Banner;
import com.lzy.tour.service.BannerService;

/**
 * 
 * @ClassName: BannerTest
 * @author 李志勇
 * @date 2017年2月27日 下午11:10:57
 *
 */
public class BannerTest extends BaseTest{
	@Resource
	private BannerMapper bannerMapper;
	@Resource
	private BannerService bannerService;
	
	@Test
	public void testAdd(){
		Banner record=new Banner();
		record.setTitle("标题");
		record.setStatus(StatusEnum.ENABLED);
		record.setCity(CityEnum.SHENZHEN);
		record.setLinkUrl("http://www.baidu.com");
		record.setPicUrl("http://sss");
		record.setSort(1);
		record.setStartTime(new Date());
		record.setEndTime(new Date());
		record.setCreateTime(new Date());
		record.setCreateBy("test");
		bannerService.insert(record);
	}
	
	@Test
	public void testAdds(){
		for(int i=0;i<10;i++){
			Banner record=new Banner();
			record.setTitle("标题"+i);
			record.setStatus(StatusEnum.ENABLED);
			record.setCity(CityEnum.SHENZHEN);
			record.setSort(i+2);
			bannerService.insert(record);
		}
	}
	
	@Test
	public void testGetOneByid(){
		Banner para=new Banner();
		para.setId(1);
		Banner oneById = bannerService.getOneById(1);
		System.out.println(oneById);
	}
	
	@Test
	public void testGetOnebyModel(){
		Map<String, Object> paras=new HashMap<String, Object>();
		paras.put("id", 1);
		Banner oneByModel = bannerService.getOneByModel(paras);
		System.out.println(oneByModel);
	}
	
	
	@Test
	public void testPageList(){
		Banner banner=new Banner();
		banner.setId(2);
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("banner", banner);
		params.put("sortOrderby", "asc");
		PageBounds pageBounds=new PageBounds(1, 5);
		PageList<Banner> pageList = bannerMapper.getAll(params, pageBounds);
		System.out.println(pageList.getPaginator().getTotalCount()+":"+pageList.getPaginator().getTotalPages());
		for (Banner o : pageList) {
			System.out.println(o.getTitle()+":"+o.getSort());
		}
	}

}
