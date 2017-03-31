package com.lzy.tour.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lzy.tour.dao.BaseMapper;
import com.lzy.tour.dao.ContactMapper;
import com.lzy.tour.model.Contact;
import com.lzy.tour.service.ContactService;

@Service
public class ContactServiceImpl extends BaseServiceImpl<Contact>implements ContactService{
	@Resource
	private ContactMapper contactMapper;
	
	@Override
	protected BaseMapper<Contact> getMapper() {
		return contactMapper;
	}

}
