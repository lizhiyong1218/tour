package org.lzy.tour;
/**
 * 使用测试的时候,要将原来带的java ee5给remove掉,换成uer library j2ee
 * defaultRollback=true 不会往数据库中插入数据
 */

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lzy.tour.enums.OrderStatusEnum;
import com.lzy.tour.enums.PayTypeEnum;
import com.lzy.tour.model.Order;
import com.lzy.tour.service.OrderService;
 
public class OrderTest extends BaseTest {

	@Resource
	private OrderService orderService;
	
	@Test
	public void testSave() {
		for(int i=0;i<10;i++){
			Order o=new Order();
			o.setOrderNo("o"+i);
			o.setRouteId(i+10);
			o.setUserId(i);
			o.setOrderStatus(OrderStatusEnum.UNPAY);
			o.setTotalPrice(new BigDecimal(100));
			o.setPayType(PayTypeEnum.WEIXIN);
			o.setPayNo("ss");
			o.setCreateTime(new Date());
			orderService.insert(o);
		}
	}
	
	@Test
	public void testDelete() {
		orderService.deleteById(3);
	}
	
	@Test
	public void testUpdate() {
		Order o=new Order();
		o.setId(1);
		o.setOrderNo("zzz");
		orderService.update(o); 
	}
	
	@Test
	public void testGetOneByid() {
		System.out.println(orderService.getOneById(1));
	}
	
	
	@Test
	public void testFindAll(){
		Map<String, Object> map=new HashMap<String, Object>();
		PageList<Order> pagination = orderService.getPagination(map, new PageBounds(2, 2));
		System.out.println(pagination.getPaginator().getTotalCount());
		System.out.println(pagination);
	}
	
}
