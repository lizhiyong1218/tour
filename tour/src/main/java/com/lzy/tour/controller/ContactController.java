package com.lzy.tour.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lzy.tour.common.ApiResponse;
import com.lzy.tour.common.CookieUtil;
import com.lzy.tour.enums.ResponseStatusEnum;
import com.lzy.tour.model.Contact;
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
	public ApiResponse getMyContacts(HttpServletRequest request)throws Exception{
		ApiResponse apiResponse=new ApiResponse();
		apiResponse.setStatus(ResponseStatusEnum.SYSERR);
		try {
			Integer userId=CookieUtil.getUserIdFromCookie(request);
			if(userId!=null){
				Map<String, Object> params=new HashMap<String, Object>();
				params.put("userId", userId);
				apiResponse.setData(contactService.getAll(params));
				apiResponse.setStatus(ResponseStatusEnum.SUCCESS);
			}
		} catch (Exception e) {
			logger.error(e); 
			throw e;
		}
		return apiResponse;
	}
	
	/**
	 * 修改联系人
	 * @param contact
	 * @return
	 */
	@ApiOperation(value = "修改联系人", notes = "修改常用联系人,前台需要传入的属性：id,nickName,phone,身份证idCard",httpMethod="POST")
	@ResponseBody
	@RequestMapping("updContact")
	public ApiResponse updContact(@ApiParam(value = "contact" ,required=true ) @RequestBody Contact contact)throws Exception{
		ApiResponse apiResponse=new ApiResponse();
		apiResponse.setStatus(ResponseStatusEnum.SYSERR);
		if(contact!=null&&contact.getId()!=null){
			contact.setUserId(null);
			boolean res=contactService.update(contact)>0?true:false;
			if(res){
				apiResponse.setStatus(ResponseStatusEnum.SUCCESS);
				apiResponse.setData(res);				
			}
		}
		return apiResponse;
	}
	
	/**
	 * 新增联系人
	 * @param contact
	 * @return
	 */
	@ApiOperation(value = "添加常用联系人", notes = "添加常用联系人,后台设置用户id,前台只需要传入的属性：nickName,phone,身份证idCard",httpMethod="POST") 
	@ResponseBody
	@RequestMapping("addContact")
	public ApiResponse addContact(HttpServletRequest request,
			@ApiParam(value = "contact" ,required=true ) @RequestBody Contact contact) throws Exception{
		ApiResponse apiResponse=new ApiResponse();
		apiResponse.setStatus(ResponseStatusEnum.SYSERR);
		Integer userId=CookieUtil.getUserIdFromCookie(request);
		if(contact!=null&&userId!=null){
			contact.setId(null);
			contact.setUserId(userId);
			boolean res=contactService.insert(contact)>0?true:false;
			if(res){
				apiResponse.setStatus(ResponseStatusEnum.SUCCESS);
				apiResponse.setData(contact);				
			}
		}
		return apiResponse;
	}
	
}
