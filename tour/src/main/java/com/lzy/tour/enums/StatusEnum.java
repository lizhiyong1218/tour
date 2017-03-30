package com.lzy.tour.enums;

import org.apache.commons.lang.StringUtils;

/**
 * 状态（启用，暂停）
 * 
 */
public enum StatusEnum {
	ENABLED("ENABLED","正常"), //启用|上架
	DISABLED("DISABLED","停用"), //停用|已售
	DELETED("DELETED","删除");//已删除
	
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

	private StatusEnum(String key , String description){
		this.key =  key;
		this.description =  description;
	}
	public static StatusEnum getStatusByKey(String key) {
		if(StringUtils.isNotBlank(key)){
			StatusEnum[] ary = StatusEnum.values();
			for (int i = 0; i < ary.length; i++) {
				if (StringUtils.indexOf(ary[i].getKey(), key) >= 0) {
					return ary[i];
				}
			}
		}
		return null;
	}
}
