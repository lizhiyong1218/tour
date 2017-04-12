package com.lzy.tour.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lzy.tour.common.CookieUtil;
import com.lzy.tour.enums.UserConstant;
import com.lzy.tour.model.User;
import com.lzy.tour.service.UserService;

@Api(description="用户接口")
@Controller
@RequestMapping("user")
public class UserController {

	private static Logger logger=Logger.getLogger(UserController.class);
	
	@Resource
	private UserService userService;
	
	@ApiOperation(value="获取个人信息",notes="获取个人信息，后台根据用户id查询,前台不需要传参",httpMethod="GET")
	@ResponseBody
	@RequestMapping("getUserInfo")
	public User getUserInfo(HttpServletRequest request){
		try {
			Integer userId=null;
			Cookie cooie = CookieUtil.getCooie(request, UserConstant.COOKIE_USER_ID);
			if(cooie!=null&&StringUtils.isNotBlank(cooie.getValue())){//有userid
				userId=Integer.parseInt(cooie.getValue());
			}
			if(userId==null){//TODO
				userId=1;
			}
			return userService.getOneById(userId);
		} catch (Exception e) {
			logger.error(e); 
		}
		return null;
	}
	
	@ApiOperation(value="修改个人信息",notes="修改个人信息，传入前端页面属性",httpMethod="GET")
	@ResponseBody
	@RequestMapping("updUser")
	public boolean updUser(HttpServletRequest request,@ApiParam(value = "user" ,required=true ) @RequestBody User user){
		Integer userId=null;
		Cookie cooie = CookieUtil.getCooie(request, UserConstant.COOKIE_USER_ID);
		if(cooie!=null&&StringUtils.isNotBlank(cooie.getValue())){//有userid
			userId=Integer.parseInt(cooie.getValue());
		}
		if(userId==null){//TODO
			userId=1;
		}
		if(user!=null){
			user.setId(userId);
			int update = userService.update(user);
			if(update>0){
				return true;
			}
		}
		return false;
	}
	
}
