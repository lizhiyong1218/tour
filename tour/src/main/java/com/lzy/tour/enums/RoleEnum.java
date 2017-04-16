package com.lzy.tour.enums;

import org.apache.commons.lang.StringUtils;

/**
 * 
* @ClassName: RoleEnum
* @Description: 角色 
* @author 李志勇
* @date 2017年4月15日 下午7:07:15
*
 */
public enum RoleEnum {
	NORMAL("NORMAL","正常"), //普通
	ADMIN("ADMIN","停用"); //管理员

	
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

	private RoleEnum(String key , String description){
		this.key =  key;
		this.description =  description;
	}
	public static RoleEnum getByKey(String key) {
		if(StringUtils.isNotBlank(key)){
			RoleEnum[] ary = RoleEnum.values();
			for (int i = 0; i < ary.length; i++) {
				if (StringUtils.indexOf(ary[i].getKey(), key) >= 0) {
					return ary[i];
				}
			}
		}
		return null;
	}
}
