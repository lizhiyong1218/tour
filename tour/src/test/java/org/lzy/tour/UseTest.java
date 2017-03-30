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
import org.springframework.beans.factory.annotation.Autowired;

import com.lzy.tour.dao.UserMapper;
import com.lzy.tour.enums.StatusEnum;
import com.lzy.tour.model.User;
import com.lzy.tour.service.UserService;
 
public class UseTest extends BaseTest {
	@Autowired 
	private UserMapper userMapper;
	@Resource
	private UserService userService;
	
	@Test
	public void testSave() {
		for(int i=0;i<1;i++){
			User user=new User();
			user.setUserName("lzy"+i);
			user.setSalt("ss");
			user.setPwd("ss");
			user.setNickName("名字"+i);
			user.setStatus(StatusEnum.ENABLED);
			user.setRole("normal");
			user.setPicUrl("ss");
			user.setSex("male");
			user.setPhone("123");
			user.setEmail("12@qq.com");
			user.setRegisterTime(new Date());
			
			user.setBackgroundUrl("b"+i);
			user.setWork("lo");
			user.setHobby("ss");
			user.setProficientRoute("zz");
			user.setBeanPalace("ssa");
			user.setIntroduction("zzs");
			
			userService.insert(user);
		}
	}
	
	@Test
	public void testDelete() {
		userMapper.deleteByPrimaryKey(3); 
	}
	
	@Test
	public void testUpdate() {
		User user=new User();
		user.setId(4);
		user.setUserName("发大发");
		userMapper.updateByPrimaryKeySelective(user); 
	}
	
	@Test
	public void testGetOneByid() {
		User user=userMapper.selectByPrimaryKey(5); 
		System.out.println(user);
	}
	
	
	@Test
	public void testFindAll(){
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("userName", "lzy4");
		List<User> list=userMapper.getAll(map);
		for (User user2 : list) {
			System.out.println(user2);
		}
	}
	
}
