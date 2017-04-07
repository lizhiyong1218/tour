package com.lzy.tour.model;

import java.io.Serializable;
import java.util.Date;

import com.lzy.tour.enums.RouteStatusEnum;

/**
 * 路线详情
 *
 */
public class RouteDetail implements Serializable{
	private static final long serialVersionUID = 1L;
	/**/
    private Integer id;
    /*路线主表id*/
    private Integer routeId;
    /*已经报名人数*/
    private Integer applyNum;
    /*路线状态*/
    private RouteStatusEnum routeStatus;
    /*开始时间*/
    private Date startTime;
    /*结束时间*/
    private Date endTime;
    
    private Route route;
    
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
	public Integer getApplyNum() {
		return applyNum;
	}
	public void setApplyNum(Integer applyNum) {
		this.applyNum = applyNum;
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
	
	public Route getRoute() {
		return route;
	}
	public void setRoute(Route route) {
		this.route = route;
	}
	@Override
	public String toString() {
		return "RouteDetail [id=" + id + ", routeId=" + routeId + ", applyNum="
				+ applyNum + ", routeStatus=" + routeStatus + ", startTime="
				+ startTime + ", endTime=" + endTime + ", route=" + route + "]";
	}
}