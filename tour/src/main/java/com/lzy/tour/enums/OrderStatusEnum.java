package com.lzy.tour.enums;

import org.apache.commons.lang.StringUtils;

/**
 * 
* @ClassName: RouteStatusEnum
* @Description: 订单状态 
* @author 李志勇
* @date 2017年3月30日 下午11:42:02
*
 */
public enum OrderStatusEnum {
	UNPAY("UNPAY","待支付"), 
	PAYED("PAYED","已支付"),
	TOREFUND("TOREFUND","待退款"),
	REFUNDED("REFUNDED","已退款"),
	FINISHED("FINISHED","已完成");
	
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

	private OrderStatusEnum(String key , String description){
		this.key =  key;
		this.description =  description;
	}
	public static OrderStatusEnum getStatusByKey(String key) {
		if(StringUtils.isNotBlank(key)){
			OrderStatusEnum[] ary = OrderStatusEnum.values();
			for (int i = 0; i < ary.length; i++) {
				if (StringUtils.indexOf(ary[i].getKey(), key) >= 0) {
					return ary[i];
				}
			}
		}
		return null;
	}
}
