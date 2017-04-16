package com.lzy.tour.enums;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * @ClassName: ResponseStatusEnum
 * @Description: 接口返回状态枚举 
 * @author 李志勇
 * @date 2017年4月16日 下午11:52:49
 *
 */
public enum ResponseStatusEnum {
	SYSERR("0","系统异常"),//系统异常
	SUCCESS("1","请求成功"), //请求成功
	NOTLOGIN("2","没有登录"), //没有登录
	NOAUTH("3","没有权限"),//没有权限
	;
	
	private String key;

	private String description;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	private ResponseStatusEnum(String key , String description){
		this.key =  key;
		this.description =  description;
	}
	public static ResponseStatusEnum getByKey(String key) {
		if(StringUtils.isNotBlank(key)){
			ResponseStatusEnum[] ary = ResponseStatusEnum.values();
			for (int i = 0; i < ary.length; i++) {
				if (StringUtils.indexOf(ary[i].getKey(), key) >= 0) {
					return ary[i];
				}
			}
		}
		return null;
	}
}
