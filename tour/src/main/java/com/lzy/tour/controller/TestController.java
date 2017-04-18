package com.lzy.tour.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class TestController {
	
	private static Logger logger=Logger.getLogger(TestController.class);
 
	@ResponseBody
	@RequestMapping("/testErr")
	public boolean testErr(HttpServletRequest request) throws Exception{
		logger.info("testErr");
		throw new Exception("已知的异常");
	}
	
}
