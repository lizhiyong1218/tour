package com.lzy.tour.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.lzy.tour.common.ApiResponse;
import com.lzy.tour.enums.ResponseStatusEnum;

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
	
	private Logger logger=Logger.getLogger(UploadController.class);
	
	@ResponseBody
    @RequestMapping(value="/uploadImage",method=RequestMethod.POST)
    public ApiResponse uploadImage(HttpServletRequest request,@RequestParam("imagefile") MultipartFile file) throws IOException{
		ApiResponse apiResponse=new ApiResponse();
		apiResponse.setStatus(ResponseStatusEnum.SYSERR);
		try {
	       String fileName=file.getOriginalFilename();
	       logger.info("文件原名: " + fileName);
	       logger.info("文件名称: " + file.getName());
	       logger.info("文件长度: " + file.getSize());
	       logger.info("文件类型: " + file.getContentType());
	       if(file.isEmpty()){
	          logger.error("upload image--------------------------------->failed");
	          apiResponse.setMsg("文件为空");
	          return apiResponse;
	       }
	       String forlderPath=null;
	       Properties prop = new Properties();
	   	   InputStream is=this.getClass().getResourceAsStream("/config.properties"); 
	   	   try {
			   prop.load(is);
			   forlderPath = prop.getProperty("uploadFilePath").trim();
		   } catch (IOException e1) {
			   logger.error(e1);
		   }  
	       if(StringUtils.isEmpty(forlderPath)){
	    	   forlderPath="/uploadFiles/";
	       }
	       String uuid = UUID.randomUUID().toString().replaceAll("\\-", "");// 返回一个随机UUID。
	       String suffix = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf("."), fileName.length()) : null;
	       String newFileName = uuid + (suffix!=null?suffix:"");// 构成新文件名。
	       /**写入地址中*/
	       FileUtils.copyInputStreamToFile(file.getInputStream(), new File(forlderPath,newFileName));
	       /**返回文件在服务器中的地址，用于存储入库*/
	       String resultUrl = forlderPath+newFileName;
	       logger.info("upload image file result----------------------->"+resultUrl);
	       apiResponse.setStatus(ResponseStatusEnum.SUCCESS);
	       apiResponse.setData(resultUrl);	
		} catch (Exception e) {
			apiResponse.setMsg(e.getMessage());
			logger.error(e);
		}
		return apiResponse;
    }

}
