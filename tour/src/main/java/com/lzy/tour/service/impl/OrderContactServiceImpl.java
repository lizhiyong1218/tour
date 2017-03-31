package com.lzy.tour.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lzy.tour.dao.BaseMapper;
import com.lzy.tour.dao.OrderContactMapper;
import com.lzy.tour.model.OrderContact;
import com.lzy.tour.service.OrderContactService;

@Service
public class OrderContactServiceImpl extends BaseServiceImpl<OrderContact>implements OrderContactService{
	@Resource
	private OrderContactMapper orderContactMapper;
	
	@Override
	protected BaseMapper<OrderContact> getMapper() {
		return orderContactMapper;
	}

}
