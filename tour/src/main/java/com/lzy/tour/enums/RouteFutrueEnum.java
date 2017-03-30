package com.lzy.tour.enums;

import org.apache.commons.lang.StringUtils;

/**
 * 
* @ClassName: RouteFutrueEnum
* @Description: 路线特色
* @author 李志勇
* @date 2017年3月30日 下午11:42:02
*
 */
public enum RouteFutrueEnum {
	WORLKING("WORLKING","徒步"), 
	COASTLINE("COASTLINE","海岸");
	
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

	private RouteFutrueEnum(String key , String description){
		this.key =  key;
		this.description =  description;
	}
	public static RouteFutrueEnum getStatusByKey(String key) {
		if(StringUtils.isNotBlank(key)){
			RouteFutrueEnum[] ary = RouteFutrueEnum.values();
			for (int i = 0; i < ary.length; i++) {
				if (StringUtils.indexOf(ary[i].getKey(), key) >= 0) {
					return ary[i];
				}
			}
		}
		return null;
	}
}
