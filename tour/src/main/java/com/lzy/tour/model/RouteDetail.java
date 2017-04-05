package com.lzy.tour.model;

import java.util.Date;

import com.lzy.tour.enums.RouteStatusEnum;

/**
 * 路线详情
 *
 */
public class RouteDetail {
	/**/
    private Integer id;
    /*路线主表id*/
    private Integer routeId;
    /*路线状态*/
    private RouteStatusEnum routeStatus;
    /*开始时间*/
    private Date startTime;
    /*结束时间*/
    private Date endTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getRouteId() {
		return routeId;
	}
	public void setRouteId(Integer routeId) {
		this.routeId = routeId;
	}
	public RouteStatusEnum getRouteStatus() {
		return routeStatus;
	}
	public void setRouteStatus(RouteStatusEnum routeStatus) {
		this.routeStatus = routeStatus;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	@Override
	public String toString() {
		return "RouteDetail [id=" + id + ", routeId=" + routeId
				+ ", routeStatus=" + routeStatus + ", startTime=" + startTime
				+ ", endTime=" + endTime + "]";
	}
    
	
}