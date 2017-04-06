package com.lzy.tour.enums;

import org.apache.commons.lang.StringUtils;

/**
 * 
* @ClassName: RouteTypeEnum
* @Description: 路线类型 
* @author 李志勇
* @date 2017年3月30日 下午11:42:02
*
 */
public enum RouteTypeEnum {
	INLANDLONG("INLANDLONG","境内长线"), 
	INLANDSHORT("INLANDSHORT","境内短线"), 
	FOREIGNLONG("FOREIGNLONG","境外长线");
	
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

	private RouteTypeEnum(String key , String description){
		this.key =  key;
		this.description =  description;
	}
	public static RouteTypeEnum getByKey(String key) {
		if(StringUtils.isNotBlank(key)){
			RouteTypeEnum[] ary = RouteTypeEnum.values();
			for (int i = 0; i < ary.length; i++) {
				if (StringUtils.indexOf(ary[i].getKey(), key) >= 0) {
					return ary[i];
				}
			}
		}
		return null;
	}
}
