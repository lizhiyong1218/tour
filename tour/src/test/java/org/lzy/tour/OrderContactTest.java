package org.lzy.tour;
/**
 * 使用测试的时候,要将原来带的java ee5给remove掉,换成uer library j2ee
 * defaultRollback=true 不会往数据库中插入数据
 */

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lzy.tour.model.OrderContact;
import com.lzy.tour.service.OrderContactService;
 
public class OrderContactTest extends BaseTest {

	@Resource
	private OrderContactService orderContactService;
	
	@Test
	public void testSave() {
		for(int i=0;i<10;i++){
			OrderContact o=new OrderContact();
			o.setOrderId(i);
			o.setContactId(i+2);
			orderContactService.insert(o);
		}
	}
	
	@Test
	public void testDelete() {
		orderContactService.deleteById(3);
	}
	
	@Test
	public void testUpdate() {
		OrderContact o=new OrderContact();
		o.setId(1);
		o.setContactId(333);
		orderContactService.update(o); 
	}
	
	@Test
	public void testGetOneByid() {
		System.out.println(orderContactService.getOneById(1));
	}
	
	
	@Test
	public void testFindAll(){
		Map<String, Object> map=new HashMap<String, Object>();
		PageList<OrderContact> pagination = orderContactService.getPagination(map, new PageBounds(2, 2));
		System.out.println(pagination.getPaginator().getTotalCount());
		System.out.println(pagination);
	}
	
}
