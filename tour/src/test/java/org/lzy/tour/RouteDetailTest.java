package org.lzy.tour;
/**
 * 使用测试的时候,要将原来带的java ee5给remove掉,换成uer library j2ee
 * defaultRollback=true 不会往数据库中插入数据
 */

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import com.lzy.tour.enums.RouteStatusEnum;
import com.lzy.tour.enums.RouteTypeEnum;
import com.lzy.tour.model.RouteDetail;
import com.lzy.tour.service.RouteDetailService;
 
public class RouteDetailTest extends BaseTest {
	 
	@Resource
	private RouteDetailService routeDetailService;
	
	@Test
	public void testSave() {
		for(int i=0;i<10;i++){
			RouteDetail route=new RouteDetail();
			route.setRouteId(i);
			route.setRouteStatus(RouteStatusEnum.ABLETOGO);
			route.setStartTime(new Date());
			route.setEndTime(new Date());
			routeDetailService.insert(route);
		}
	}
	
	@Test
	public void testDelete() {
		routeDetailService.deleteById(1);
	}
	
	@Test
	public void testUpdate() {
		RouteDetail record=new RouteDetail();
		record.setId(2);
		record.setRouteStatus(RouteStatusEnum.FULLED); 
		routeDetailService.update(record);
	}
	
	@Test
	public void testGetOneByid() {
		RouteDetail oneById = routeDetailService.getOneById(2);
		System.out.println(oneById);
	}
	
	
	@Test
	public void testFindAll(){
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("routeType", RouteTypeEnum.INLANDLONG);
		params.put("limit", 5);
		List<RouteDetail> list=routeDetailService.getAll(params);
		for (RouteDetail o : list) {
			System.out.println(o);
		}
	}
	
}
