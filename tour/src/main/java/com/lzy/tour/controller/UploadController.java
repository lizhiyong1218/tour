package com.lzy.tour.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * 
* @ClassName: UploadController
* @Description: 上传文件控制器 
* @author 李志勇
* @date 2017年4月13日 下午11:01:44
*
 */
@Controller
@RequestMapping("upload")
public class UploadController {
	
	
	/**
	 * 上传模版
	 * @param response
	 * @param request
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@RequestMapping(value = "/uploadImages")
	public Map<String, Object> upload(HttpServletResponse response,HttpServletRequest request) throws IllegalStateException, IOException{
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request; 
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
	   Properties prop = new Properties();
	   InputStream is=this.getClass().getResourceAsStream("/config.properties"); 
	   prop.load(is);  
	   String dirPath = prop.getProperty("uploadFilePath").trim();
		 File file = new File(dirPath);    
         if (!file.exists()) {    
              file.mkdirs();    
         }
         String fileName = null;
         for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {    
        	  MultipartFile mf = entity.getValue();    
        	  fileName = mf.getOriginalFilename();  
              String uuid = UUID.randomUUID().toString().replaceAll("\\-", "");// 返回一个随机UUID。
              String suffix = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf("."), fileName.length()) : null;
              String newFileName = uuid + (suffix!=null?suffix:"");// 构成新文件名。
              File uploadFile = new File(dirPath + newFileName);
              FileCopyUtils.copy(mf.getBytes(), uploadFile); 
        	  response.getWriter().print("上传模版"+fileName+"成功");
         }
       Map<String, Object> map=new HashMap<>();
       String realPath1 = request.getScheme()+"://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();  
       map.put("error", 0);  
       map.put("url", realPath1+"/uploadFiles/"+fileName); 
       return map;
	}

}
