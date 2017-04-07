package com.lzy.tour.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lzy.tour.enums.UserConstant;
import com.lzy.tour.model.Contact;
import com.lzy.tour.model.User;
import com.lzy.tour.service.ContactService;

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
	@ResponseBody
	@RequestMapping("getMyContacts")
	public List<Contact> getMyContacts(HttpServletRequest request){
		try {
			User user = (User) request.getSession().getAttribute(UserConstant.SESSION_USER);
			if(user!=null&&user.getId()!=null){
				Map<String, Object> params=new HashMap<String, Object>();
				params.put("userId", 1);
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
	@ResponseBody
	@RequestMapping("updContact")
	public boolean updContact(Contact contact){
		if(contact!=null&&contact.getId()!=null){
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
	@ResponseBody
	@RequestMapping("addContact")
	public boolean addContact(Contact contact){
		int update = contactService.insert(contact);
		if(update>0){
			return true;
		}
		return false;
	}
	
}
