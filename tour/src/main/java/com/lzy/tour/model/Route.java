package com.lzy.tour.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.lzy.tour.enums.RouteFutrueEnum;
import com.lzy.tour.enums.RouteTypeEnum;
import com.lzy.tour.enums.StatusEnum;

/**
 * 
* @ClassName: Route
* @Description: 线路 
* @author 李志勇
* @date 2017年3月30日 下午11:54:39
*
 */
public class Route implements Serializable{
	private static final long serialVersionUID = 1L;
	/**/
    private Integer id;
    /*标题*/
    private String title;
    /*城市*/
    private String city;
    /*状态*/
    private StatusEnum status;
    /*路线类型*/
    private RouteTypeEnum routeType;
    /*路线特色*/
    private RouteFutrueEnum routeFeature;
    /*封面图*/
    private String picUrl;
    /*发布时间*/
    private Date pubTime;
    /*出发地点*/
    private String startAddress;
    /*目的地*/
    private String endAddress;
    /*总人数*/
    private Integer totalPeople;
    /*最小成行人数*/
    private Integer minStartPeople;
    /*原价*/
    private BigDecimal originalPrice;
    /*当前价*/
    private BigDecimal currentPrice;
    /*详细行程*/
    private String detailDesc;
    /*费用说明*/
    private String priceDesc;
    /*活动准备*/
    private String prepareDesc;
    /*进群图片*/
    private String groupPicUrl;
    /*创建时间*/
    private Date createTime;
    /*创建人*/
    private String createBy;
    /*修改时间'*/
    private Date updateTime;
    /*修改人*/
    private String updateBy;
    
    /*路线详情*/
    private List<RouteDetail> routeDetails;
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public StatusEnum getStatus() {
		return status;
	}
	public void setStatus(StatusEnum status) {
		this.status = status;
	}
	public RouteTypeEnum getRouteType() {
		return routeType;
	}
	public void setRouteType(RouteTypeEnum routeType) {
		this.routeType = routeType;
	}
	public RouteFutrueEnum getRouteFeature() {
		return routeFeature;
	}
	public void setRouteFeature(RouteFutrueEnum routeFeature) {
		this.routeFeature = routeFeature;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public Date getPubTime() {
		return pubTime;
	}
	public void setPubTime(Date pubTime) {
		this.pubTime = pubTime;
	}
	public String getStartAddress() {
		return startAddress;
	}
	public void setStartAddress(String startAddress) {
		this.startAddress = startAddress;
	}
	public String getEndAddress() {
		return endAddress;
	}
	public void setEndAddress(String endAddress) {
		this.endAddress = endAddress;
	}
	public Integer getTotalPeople() {
		return totalPeople;
	}
	public void setTotalPeople(Integer totalPeople) {
		this.totalPeople = totalPeople;
	}
	public Integer getMinStartPeople() {
		return minStartPeople;
	}
	public void setMinStartPeople(Integer minStartPeople) {
		this.minStartPeople = minStartPeople;
	}
	public BigDecimal getOriginalPrice() {
		return originalPrice;
	}
	public void setOriginalPrice(BigDecimal originalPrice) {
		this.originalPrice = originalPrice;
	}
	public BigDecimal getCurrentPrice() {
		return currentPrice;
	}
	public void setCurrentPrice(BigDecimal currentPrice) {
		this.currentPrice = currentPrice;
	}
	public String getDetailDesc() {
		return detailDesc;
	}
	public void setDetailDesc(String detailDesc) {
		this.detailDesc = detailDesc;
	}
	public String getPriceDesc() {
		return priceDesc;
	}
	public void setPriceDesc(String priceDesc) {
		this.priceDesc = priceDesc;
	}
	public String getPrepareDesc() {
		return prepareDesc;
	}
	public void setPrepareDesc(String prepareDesc) {
		this.prepareDesc = prepareDesc;
	}
	public String getGroupPicUrl() {
		return groupPicUrl;
	}
	public void setGroupPicUrl(String groupPicUrl) {
		this.groupPicUrl = groupPicUrl;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public List<RouteDetail> getRouteDetails() {
		return routeDetails;
	}
	public void setRouteDetails(List<RouteDetail> routeDetails) {
		this.routeDetails = routeDetails;
	}
	@Override
	public String toString() {
		return "Route [id=" + id + ", title=" + title + ", city=" + city
				+ ", status=" + status  
				+ ", routeType=" + routeType + ", routeFeature=" + routeFeature
				+ ", picUrl=" + picUrl + ", pubTime=" + pubTime
				+ ", startAddress=" + startAddress + ", endAddress="
				+ endAddress + ", totalPeople=" + totalPeople
				+ ", minStartPeople=" + minStartPeople + ", originalPrice="
				+ originalPrice + ", currentPrice=" + currentPrice
				+ ", groupPicUrl=" + groupPicUrl + ", createTime=" + createTime
				+ ", createBy=" + createBy + ", updateTime=" + updateTime
				+ ", updateBy=" + updateBy + "]";
	}
	
}