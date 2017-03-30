package com.lzy.tour.model;

import java.io.Serializable;
import java.util.Date;

import com.lzy.tour.enums.CityEnum;
import com.lzy.tour.enums.StatusEnum;

public class Banner implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Integer id;

    private String title;
    
    private StatusEnum status;
    
    private Integer sort;

    private CityEnum city;
    
    private String linkUrl;

    private String picUrl;

    private Date startTime;

    private Date endTime;

    private Date createTime;

    private String createBy;
    
    private Date updateTime;

    private String updateBy;

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

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public CityEnum getCity() {
		return city;
	}

	public void setCity(CityEnum city) {
		this.city = city;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
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

	@Override
	public String toString() {
		return "Banner [id=" + id + ", title=" + title + ", status=" + status
				+ ", sort=" + sort + ", city=" + city + ", linkUrl=" + linkUrl
				+ ", picUrl=" + picUrl + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", createTime=" + createTime
				+ ", createBy=" + createBy + ", updateTime=" + updateTime
				+ ", updateBy=" + updateBy + "]";
	}
	 
}