package com.lzy.tour.interceptor.aop;

import java.lang.reflect.Method;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.lzy.tour.common.Constans;
import com.lzy.tour.common.CookieUtil;
import com.lzy.tour.common.crypto.AES;
import com.lzy.tour.enums.UserConstant;
import com.lzy.tour.interceptor.exception.NotLoginException;

public class SecurityAspect {  
	  
    public Object execute(ProceedingJoinPoint pjp) throws Throwable {  
        // 从切点上获取目标方法  
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();  
        Method method = methodSignature.getMethod();
        System.err.println(method.getName());
        // 若目标方法忽略了安全性检查，则直接调用目标方法  
//        if (method.isAnnotationPresent(IgnoreSecurity.class)) {  
//            return pjp.proceed();  
//        }  
        //检查 token 有效性  
        String id=null;
        ServletRequestAttributes s=(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = s.getRequest();
        Cookie cookie = CookieUtil.getCookie(request, UserConstant.COOKIE_USER_ID);
		if(cookie!=null){
			String value = cookie.getValue();
			try {
				String decrypt = AES.decrypt(value, Constans.TOKEN_SALT);
				if(StringUtils.isNotEmpty(decrypt)&&decrypt.contains(Constans.TOKEN_PREFIX)){
					id=decrypt.replaceAll(Constans.TOKEN_PREFIX, "");
					System.err.println(id);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
        if(StringUtils.isEmpty(id)){
        	throw new NotLoginException("验证失败");
        }
        // 调用目标方法  
        return pjp.proceed();  
    }  
}  