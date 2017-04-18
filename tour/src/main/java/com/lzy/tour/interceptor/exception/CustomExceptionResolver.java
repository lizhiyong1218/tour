package com.lzy.tour.interceptor.exception;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import com.lzy.tour.common.ApiResponse;
import com.lzy.tour.enums.ResponseStatusEnum;

public class CustomExceptionResolver implements HandlerExceptionResolver {

	private static final Logger logger=Logger.getLogger(CustomExceptionResolver.class);
	
	@Override
	public ModelAndView resolveException(HttpServletRequest arg0,
			HttpServletResponse response, Object arg2, Exception ex) {
//		ModelAndView mv = new ModelAndView();             
//        /*  使用response返回    */  
//        response.setStatus(HttpStatus.OK.value()); //设置状态码  
//        response.setContentType(MediaType.APPLICATION_JSON_VALUE); //设置ContentType  
//        response.setCharacterEncoding("UTF-8"); //避免乱码  
//        response.setHeader("Cache-Control", "no-cache, must-revalidate");  
//        try {  
//            response.getWriter().write("{\"success\":false,\"msg\":\"" + ex.getMessage() + "\"}");  
//        } catch (IOException e) {  
//           logger.error("与客户端通讯异常:"+ e.getMessage(), e);  
//        }  
//
//        logger.debug("异常:" + ex.getMessage(), ex);  
//        return mv;
		
		ModelAndView mv = new ModelAndView();  
        /*  使用FastJson提供的FastJsonJsonView视图返回，不需要捕获异常   */  
        FastJsonJsonView view = new FastJsonJsonView();  
        ResponseStatusEnum status=ResponseStatusEnum.SYSERR;
        String msg=ex.getMessage();
        if(ex instanceof NotLoginException){
        	status=ResponseStatusEnum.NOTLOGIN;
        	msg=((NotLoginException)ex).getMsg();
        }
        Map<String, Object> attributes = new HashMap<String, Object>();  
        attributes.put("status", status);  
        attributes.put("msg", msg);  
        view.setAttributesMap(attributes);  
        mv.setView(view);   
        logger.debug("异常:" + ex.getMessage(), ex);  
        return mv; 
		
	}

}
