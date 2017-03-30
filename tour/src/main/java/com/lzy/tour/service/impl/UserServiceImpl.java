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

import com.lzy.tour.dao.BaseMapper;
import com.lzy.tour.dao.UserMapper;
import com.lzy.tour.model.User;
import com.lzy.tour.service.UserService;

/**
 * @ClassName: UserServiceImpl
 * @Description: 用户
 * @author 李志勇
 * @date 2017年2月27日 下午11:50:38
 *
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService{

	@Resource
	private UserMapper userMapper;
	
	@Override
	protected BaseMapper<User> getMapper() {
		return userMapper;
	}

}
