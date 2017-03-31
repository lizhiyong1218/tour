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
import com.lzy.tour.model.Contact;
import com.lzy.tour.service.ContactService;
 
public class ContactTest extends BaseTest {

	@Resource
	private ContactService contactService;
	
	@Test
	public void testSave() {
		for(int i=0;i<10;i++){
			Contact contact=new Contact();
			contact.setIdCard("430"+i);
			contact.setNickName("n"+i);
			contact.setPhone("sss");
			contact.setUserId(5);
			contactService.insert(contact);
		}
	}
	
	@Test
	public void testDelete() {
		contactService.deleteById(3);
	}
	
	@Test
	public void testUpdate() {
		Contact contact=new Contact();
		contact.setId(1);
		contact.setIdCard("430");
		contact.setNickName("n");
		contact.setPhone("sss");
		contactService.update(contact); 
	}
	
	@Test
	public void testGetOneByid() {
		Contact o=contactService.getOneById(1);
		System.out.println(o);
	}
	
	
	@Test
	public void testFindAll(){
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("id", "2");
		PageList<Contact> pagination = contactService.getPagination(map, new PageBounds(2, 2));
		System.out.println(pagination.getPaginator().getTotalCount());
		System.out.println(pagination);
	}
	
}
