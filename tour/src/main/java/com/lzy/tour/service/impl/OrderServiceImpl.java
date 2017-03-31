package com.lzy.tour.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lzy.tour.dao.BaseMapper;
import com.lzy.tour.dao.OrderMapper;
import com.lzy.tour.model.Order;
import com.lzy.tour.service.OrderService;

@Service
public class OrderServiceImpl extends BaseServiceImpl<Order>implements OrderService{
	@Resource
	private OrderMapper orderMapper;
	
	@Override
	protected BaseMapper<Order> getMapper() {
		return orderMapper;
	}

}
