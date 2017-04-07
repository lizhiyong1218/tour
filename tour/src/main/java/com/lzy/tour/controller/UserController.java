package com.lzy.tour.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lzy.tour.enums.UserConstant;
import com.lzy.tour.model.User;
import com.lzy.tour.service.UserService;

@Controller
@RequestMapping("user")
public class UserController {

	private static Logger logger=Logger.getLogger(UserController.class);
	
	@Resource
	private UserService userService;
	
	@ResponseBody
	@RequestMapping("getUserInfo")
	public User getUserInfo(HttpServletRequest request){
		try {
			User user = (User) request.getSession().getAttribute(UserConstant.SESSION_USER);
			if(user!=null&&user.getId()!=null){
				return userService.getOneById(user.getId());
			}
		} catch (Exception e) {
			logger.error(e); 
		}
		return null;
	}
	
	@ResponseBody
	@RequestMapping("updUser")
	public boolean updUser(HttpServletRequest request,User user){
		if(user!=null&&user.getId()!=null){
			int update = userService.update(user);
			if(update>0){
				return true;
			}
		}
		return false;
	}
	
}
