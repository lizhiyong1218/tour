package com.lzy.tour.enums;

import org.apache.commons.lang.StringUtils;

/**
 * 
* @ClassName: LoginEnum
* @Description: 登录枚举 
* @author 李志勇
* @date 2017年4月15日 下午6:44:58
*
 */
public enum LoginEnum {
	SUCCESS("SUCCESS","登录成功"), //登录成功
	NOTEXIST("NOTEXIST","用户不存在"), //用户不存在
	PSWERR("PSWERR","密码错误"), //密码错误
	NOAUTH("NOAUTH","没有权限"),//没有权限
	SYSERR("SYSERR","系统异常"),//系统异常
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

	private LoginEnum(String key , String description){
		this.key =  key;
		this.description =  description;
	}
	public static LoginEnum getByKey(String key) {
		if(StringUtils.isNotBlank(key)){
			LoginEnum[] ary = LoginEnum.values();
			for (int i = 0; i < ary.length; i++) {
				if (StringUtils.indexOf(ary[i].getKey(), key) >= 0) {
					return ary[i];
				}
			}
		}
		return null;
	}
}
