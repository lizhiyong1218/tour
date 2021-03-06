package org.lzy.tour;
/**
 * 使用测试的时候,要将原来带的java ee5给remove掉,换成uer library j2ee
 * defaultRollback=true 不会往数据库中插入数据
 */

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import com.lzy.tour.dao.RouteMapper;
import com.lzy.tour.enums.RouteFutrueEnum;
import com.lzy.tour.enums.RouteStatusEnum;
import com.lzy.tour.enums.RouteTypeEnum;
import com.lzy.tour.enums.StatusEnum;
import com.lzy.tour.model.Route;
import com.lzy.tour.model.RouteDetail;
import com.lzy.tour.service.RouteService;
 
public class RouteTest extends BaseTest {
	 
	@Resource
	private RouteService routeService;
	@Resource
	private RouteMapper routeMapper;
	
	@Test
	public void testSave() {
		for(int i=0;i<10;i++){
			Route route=new Route();
			route.setTitle("rout"+i);
			route.setCity("shenzhen");
			route.setStatus(StatusEnum.ENABLED);
//			route.setRouteStatus(RouteStatusEnum.ABLETOGO);
			route.setRouteType(RouteTypeEnum.INLANDLONG);
			route.setRouteFeature(RouteFutrueEnum.WORLKING);
			route.setPicUrl("ssss");
			route.setPubTime(new Date());
//			route.setStartTime(new Date());
//			route.setEndTime(new Date());
			route.setStartAddress("但是");
			route.setEndAddress("aaaa");
			route.setTotalPeople(11);
			route.setMinStartPeople(1);
			route.setOriginalPrice(BigDecimal.ONE);
			route.setCurrentPrice(new BigDecimal((12.121)));
			route.setDetailDesc("detail");
			route.setPriceDesc("price");
			route.setPrepareDesc("prepare");
			route.setGroupPicUrl("group");
			route.setCreateTime(new Date());
			route.setCreateBy("aa");
			route.setUpdateTime(new Date());
			route.setUpdateBy("zz");
			routeService.insert(route);
		}
	}
	
	@Test
	public void testDelete() {
		routeService.deleteById(1);
	}
	
	@Test
	public void testUpdate() {
		Route record=new Route();
		record.setId(2);
		record.setDetailDesc("zzss");
		routeService.update(record);
	}
	
	@Test
	public void testGetOneByid() {
		Route oneById = routeService.getOneById(2);
		System.out.println(oneById);
	}
	
	
	@Test
	public void testFindAll(){
		Map<String, Object> map=new HashMap<String, Object>();
		List<Route> list=routeService.getAll(map);
		for (Route user2 : list) {
			System.out.println(user2);
		}
	}
	
	/**
	 * 新增路线和详情
	 */
	@Test
	public void testAddRouteAndDetail(){
		Route route=new Route();
		route.setTitle("rout");
		route.setCity("shenzhen");
		route.setStatus(StatusEnum.ENABLED);
		route.setRouteType(RouteTypeEnum.INLANDLONG);
		route.setRouteFeature(RouteFutrueEnum.WORLKING);
		route.setPicUrl("ssss");
		route.setPubTime(new Date());
		route.setStartAddress("但是");
		route.setEndAddress("aaaa");
		route.setTotalPeople(11);
		route.setMinStartPeople(1);
		route.setOriginalPrice(BigDecimal.ONE);
		route.setCurrentPrice(new BigDecimal((12.121)));
		route.setDetailDesc("detail");
		route.setPriceDesc("price");
		route.setPrepareDesc("prepare");
		route.setGroupPicUrl("group");
		route.setCreateTime(new Date());
		route.setCreateBy("aa");
		route.setUpdateTime(new Date());
		route.setUpdateBy("zz");
		
		List<RouteDetail> list=new ArrayList<RouteDetail>();
		for(int i=0;i<5;i++){
			RouteDetail detail=new RouteDetail();
			detail.setRouteStatus(RouteStatusEnum.ABLETOGO);
			detail.setStartTime(new Date());
			detail.setEndTime(new Date());
			list.add(detail);
		}
		route.setRouteDetails(list);
		
		try {
			routeService.addRouteAndDetail(route);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 查找路线和详情信息
	 */
	@Test
	public void testGetRouteWithDetail(){
		Route routeWithDetailByid = routeService.getRouteWithDetailByid(4);
		System.err.println(routeWithDetailByid);
		List<RouteDetail> routeDetails = routeWithDetailByid.getRouteDetails();
		for (RouteDetail routeDetail : routeDetails) {
			System.err.println(routeDetail);
		}
	}
	
}
