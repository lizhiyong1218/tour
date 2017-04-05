package com.lzy.tour.model;

import java.math.BigDecimal;
import java.util.Date;

import com.lzy.tour.enums.OrderStatusEnum;
import com.lzy.tour.enums.PayTypeEnum;

public class Order {
    private Integer id;
    /*订单编号*/
    private String orderNo;
    /*线路id*/
    private Integer routeId;
    /*用户id*/
    private Integer userId;
    /*订单状态*/
    private OrderStatusEnum orderStatus;
    /*中金额*/
    private BigDecimal totalPrice;
    /*支付方式*/
    private PayTypeEnum payType;
    /*支付单号*/
    private String payNo;
    /*创建时间*/
    private Date createTime;
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public OrderStatusEnum getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(OrderStatusEnum orderStatus) {
		this.orderStatus = orderStatus;
	}
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	public PayTypeEnum getPayType() {
		return payType;
	}
	public void setPayType(PayTypeEnum payType) {
		this.payType = payType;
	}
	public String getPayNo() {
		return payNo;
	}
	public void setPayNo(String payNo) {
		this.payNo = payNo;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Integer getRouteId() {
		return routeId;
	}
	public void setRouteId(Integer routeId) {
		this.routeId = routeId;
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", orderNo=" + orderNo + ", routeId="
				+ routeId + ", userId=" + userId + ", orderStatus="
				+ orderStatus + ", totalPrice=" + totalPrice + ", payType="
				+ payType + ", payNo=" + payNo + ", createTime=" + createTime
				+ "]";
	}
}