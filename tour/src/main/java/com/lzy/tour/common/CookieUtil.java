package com.lzy.tour.common;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.lzy.tour.common.crypto.AES;
import com.lzy.tour.enums.UserConstant;


public class CookieUtil {

	private final static Logger logger=Logger.getLogger(CookieUtil.class);
	
	public static Cookie getCookie(HttpServletRequest request, String cookieName) {
		Cookie[] cookies = request.getCookies();
		Cookie ck = null;
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookieName.equals(cookie.getName())) {
					ck = cookie;
					break;
				}
			}
		}
		return ck;
	}

	/**
	* @Title: addCookie
	* @Description: 添加cookie
	* @param resp
	* @param cookieName
	* @param cookieValue
	* @param maxAge
	* @param domain
	* @param path
	* @return:     
	* Cookie    
	* @throws
	*/ 
	public static Cookie addCookie(HttpServletResponse resp, String cookieName, String cookieValue,int maxAge, String domain,String path) {
		Cookie ck = new Cookie(cookieName, cookieValue);
		ck.setMaxAge(maxAge);
		if (StringUtils.isNotBlank(domain)) {
			ck.setDomain(domain);
		}
		if (StringUtils.isNotBlank(path)){
			ck.setPath(path);
		}else{
			ck.setPath("/");
		}
		resp.addCookie(ck);
		return ck;
	}
	
	public static Cookie addCookieForDays(HttpServletResponse resp, String cookieName, String cookieValue,int day, String domain,String path){
		int maxAge=day*24*60*60;
		return addCookie(resp, cookieName, cookieValue, maxAge, domain, path);
	}

	public static Integer getUserIdFromCookie(HttpServletRequest request) {
        Integer id=null;
        Cookie cookie = CookieUtil.getCookie(request, UserConstant.COOKIE_USER_ID);
		if(cookie!=null){
			String value = cookie.getValue();
			try {
				String decrypt = AES.decrypt(value, Constans.TOKEN_SALT);
				if(StringUtils.isNotEmpty(decrypt)&&decrypt.contains(Constans.TOKEN_PREFIX)){
					String str=decrypt.replaceAll(Constans.TOKEN_PREFIX, "");
					if(StringUtils.isNotBlank(str)){
						id=Integer.parseInt(str);
					}
				}
			} catch (Exception e) {
				logger.error("decrypt用户id异常",e);
			}
		}
		return id;
	}
}
