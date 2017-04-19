package com.lzy.tour.interceptor.aop;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.lzy.tour.common.CookieUtil;
import com.lzy.tour.interceptor.exception.NotLoginException;

public class SecurityAspect {  
	
	private Logger logger=Logger.getLogger(SecurityAspect.class);
	
    public Object execute(ProceedingJoinPoint pjp) throws Throwable {  
        // 从切点上获取目标方法  
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();  
        Method method = methodSignature.getMethod();
        // 若目标方法忽略了安全性检查，则直接调用目标方法  
        //if (method.isAnnotationPresent(IgnoreSecurity.class)) {  
        //    return pjp.proceed();  
        //}  
        //检查 token 有效性  
        ServletRequestAttributes s=(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        Integer id= CookieUtil.getUserIdFromCookie(s.getRequest());
        if(id==null){
        	logger.error(method.getName());
        	throw new NotLoginException("验证失败");
        }
        // 调用目标方法  
        return pjp.proceed();  
    }  
}  