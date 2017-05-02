//package com.lzy.tour.interceptor.aop;
//
//import org.apache.log4j.Logger;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.converter.HttpMessageNotReadableException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.ResponseStatus;
//
//import com.lzy.tour.common.ApiResponse;
//import com.lzy.tour.enums.ResponseStatusEnum;
//
//@ControllerAdvice()
//public class ExceptionAdvice {
//	
//	private Logger logger=Logger.getLogger(ExceptionAdvice.class);
// 
//    /** 
//     * 400 - Bad Request 
//     */  
//	@ResponseBody
//    @ResponseStatus(HttpStatus.BAD_REQUEST)  
//    @ExceptionHandler(Exception.class)  
//    public ApiResponse handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {  
//        logger.error("参数解析失败", e);  
//        return new ApiResponse(ResponseStatusEnum.SYSERR, "参数解析失败:"+e.getMessage());  
//    }  
//	
////    /** 
////     * 400 - Bad Request 
////     */  
////    @ResponseStatus(HttpStatus.BAD_REQUEST)  
////    @ExceptionHandler(HttpMessageNotReadableException.class)  
////    public Response handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {  
////        logger.error("参数解析失败", e);  
////        return new Response().failure("could_not_read_json");  
////    }  
//  
////    /** 
////     * 405 - Method Not Allowed 
////     */  
////    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)  
////    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)  
////    public Response handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {  
////        logger.error("不支持当前请求方法", e);  
////        return new Response().failure("request_method_not_supported");  
////    }  
////  
////    /** 
////     * 415 - Unsupported Media Type 
////     */  
////    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)  
////    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)  
////    public Response handleHttpMediaTypeNotSupportedException(Exception e) {  
////        logger.error("不支持当前媒体类型", e);  
////        return new Response().failure("content_type_not_supported");  
////    }  
////  
////    /** 
////     * 500 - Internal Server Error 
////     */  
////    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)  
////    @ExceptionHandler(Exception.class)  
////    public Response handleException(Exception e) {  
////        logger.error("服务运行异常", e);  
////        return new Response().failure(e.getMessage());  
////    }  
//}