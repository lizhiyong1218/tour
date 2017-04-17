package com.lzy.tour.controller;

import java.util.HashMap;
import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lzy.tour.common.ApiResponse;
import com.lzy.tour.common.Constans;
import com.lzy.tour.common.CookieUtil;
import com.lzy.tour.common.Pagination;
import com.lzy.tour.common.crypto.AES;
import com.lzy.tour.enums.LoginEnum;
import com.lzy.tour.enums.ResponseStatusEnum;
import com.lzy.tour.enums.RoleEnum;
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
			Cookie cooie = CookieUtil.getCookie(request, UserConstant.COOKIE_USER_ID);
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
		Cookie cooie = CookieUtil.getCookie(request, UserConstant.COOKIE_USER_ID);
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
	
	@ApiOperation(value="登录",notes="传入用户名和密码验证",httpMethod="GET")
	@RequestMapping("/login")
	@ResponseBody
	public ApiResponse login(HttpServletRequest request,HttpServletResponse response,
			@ApiParam(value = "用户名" ,required=true ) @RequestParam String userName,
			@ApiParam(value = "密码" ,required=true ) @RequestParam String password){
		ApiResponse apiResponse=new ApiResponse();
		apiResponse.setStatus(ResponseStatusEnum.SYSERR);
		LoginEnum loginEnum=null;
		try {
			Map<String, Object> params=new HashMap<String, Object>();
			params.put("userName", userName);
			User user = userService.getOneByModel(params);
			if(user==null){
				loginEnum = LoginEnum.NOTEXIST;
			}else{
				if(user.getRole()!=RoleEnum.ADMIN){
					loginEnum=LoginEnum.NOAUTH;
				}else{
					String encrypt = AES.encrypt(password.trim(), user.getSalt());
					if(encrypt.equals(user.getPwd())){
						loginEnum=LoginEnum.SUCCESS;
						String str=Constans.TOKEN_PREFIX+user.getId();
						String token= AES.encrypt(str, Constans.TOKEN_SALT);
						CookieUtil.addCookieForDays(response, UserConstant.COOKIE_USER_ID, token, 7, null, "/");
					}else{
						loginEnum=LoginEnum.PSWERR;
					}
				}
			}
			apiResponse.setStatus(ResponseStatusEnum.SUCCESS);
		} catch (Exception e) {
			loginEnum=LoginEnum.SYSERR;
			logger.error("登录异常",e);
		}
		apiResponse.setData(loginEnum);
		return apiResponse;
	}
	
	@ApiOperation(value="获取后台分页授权用户列表",notes="获取后台分页授权用户列表,传入分页信息",httpMethod="GET")
	@ResponseBody
	@RequestMapping("/manage/getUserList")
	public Pagination<User> getUserList(HttpServletRequest request,
			@ApiParam(value = "第几页",required=true) @RequestParam Integer pageNum,
			@ApiParam(value = "每页记录数",required=true) @RequestParam Integer pageSize){
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("role", RoleEnum.NORMAL);
		params.put("registerTime", "desc");
		Pagination<User> pagination = userService.getPagination(params, pageNum, pageSize);
		if(pagination!=null){
			return pagination;
		}
		return null;
	}
	
}
