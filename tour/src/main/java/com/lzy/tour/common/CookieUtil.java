package com.lzy.tour.common;

import java.util.Calendar;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;


public class CookieUtil {


	public static Cookie getCooie(HttpServletRequest request, String cookieName) {
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

	public static Cookie addCookie(HttpServletResponse resp, String cookieName, String cookieValue, String domain) {
		Cookie ck = new Cookie(cookieName, cookieValue);
		ck.setMaxAge(14 * 24 * 3600);
		ck.setPath("/");
		if (domain != null) {
			ck.setDomain(domain);
		}
		resp.addCookie(ck);
		return ck;
	}

	public static Cookie addTodayCookie(HttpServletResponse resp, String cookieName, String cookieValue) {
		Cookie cookie = new Cookie(cookieName, cookieValue);
		Calendar now = Calendar.getInstance();
		Calendar calendar = Calendar.getInstance();
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
				23, 59, 59);
		int intValue = Long.valueOf((calendar.getTime().getTime() - now.getTime().getTime()) / 1000).intValue();
		cookie.setMaxAge(intValue);
		cookie.setPath("/");
		resp.addCookie(cookie);
		return cookie;
	}
	
	public static Cookie addMaxAgeCookie(HttpServletResponse resp, String cookieName, String cookieValue){
		return addCookie(resp, cookieName, cookieValue, Integer.MAX_VALUE, null, null);
	}
	
	/**
	* @Title: addCookie
	* @Description: TODO
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
		}
		resp.addCookie(ck);
		return ck;
	}

}
