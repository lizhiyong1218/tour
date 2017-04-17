package com.lzy.tour.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.lzy.tour.model.Contact;
import com.lzy.tour.model.User;
import com.lzy.tour.service.ContactService;

@Api(description="常用联系人接口")
@Controller
@RequestMapping("contact")
public class ContactController {
	
	private static Logger logger=Logger.getLogger(ContactController.class);
	
	@Resource
	private ContactService contactService;
	
	/**
	 * 获取我的联系人
	 * @param request
	 * @return
	 */
	@ApiOperation(value="获取我的联系人",notes="前台不用传参,后台获取用户id然后查询常用联系人",httpMethod="GET")
	@ResponseBody
	@RequestMapping("getMyContacts")
	public List<Contact> getMyContacts(HttpServletRequest request){
		try {
			Integer userId=null;
			Cookie cooie = CookieUtil.getCookie(request, UserConstant.COOKIE_USER_ID);
			if(cooie!=null&&StringUtils.isNotBlank(cooie.getValue())){//有userid
				userId=Integer.parseInt(cooie.getValue());
			}
			if(userId==null){//TODO
				userId=1;
			}
			if(userId!=null){
				Map<String, Object> params=new HashMap<String, Object>();
				params.put("userId", userId);
				return contactService.getAll(params);
			}
		} catch (Exception e) {
			logger.error(e); 
		}
		return null;
	}
	
	/**
	 * 修改联系人
	 * @param contact
	 * @return
	 */
	@ApiOperation(value = "修改联系人", notes = "修改常用联系人,前台需要传入的属性：id,nickName,phone,身份证idCard",httpMethod="POST")
	@ResponseBody
	@RequestMapping("updContact")
	public boolean updContact(@ApiParam(value = "contact" ,required=true ) @RequestBody Contact contact){
		if(contact!=null&&contact.getId()!=null){
			contact.setUserId(null);
			int update = contactService.update(contact);
			if(update>0){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 新增联系人
	 * @param contact
	 * @return
	 */
	@ApiOperation(value = "添加常用联系人", notes = "添加常用联系人,后台设置用户id,前台只需要传入的属性：nickName,phone,身份证idCard",httpMethod="POST") 
	@ResponseBody
	@RequestMapping("addContact")
	public boolean addContact(HttpServletRequest request,@ApiParam(value = "contact" ,required=true ) @RequestBody Contact contact){
		if(contact!=null){
			Integer userId=null;
			Cookie cooie = CookieUtil.getCookie(request, UserConstant.COOKIE_USER_ID);
			if(cooie!=null&&StringUtils.isNotBlank(cooie.getValue())){//有userid
				userId=Integer.parseInt(cooie.getValue());
			}
			if(userId==null){//TODO
				userId=1;
			}
			contact.setId(null);
			contact.setUserId(userId);
			int update = contactService.insert(contact);
			if(update>0){
				return true;
			}
		}
		return false;
	}
	
}
