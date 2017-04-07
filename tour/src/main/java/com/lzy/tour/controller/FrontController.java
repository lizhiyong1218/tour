package com.lzy.tour.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.foxinmy.weixin4j.exception.WeixinException;
import com.foxinmy.weixin4j.mp.WeixinProxy;
import com.foxinmy.weixin4j.mp.api.OauthApi;
import com.foxinmy.weixin4j.mp.model.OauthToken;
import com.foxinmy.weixin4j.util.Weixin4jConfigUtil;
import com.lzy.tour.common.CookieUtil;
import com.lzy.tour.enums.StatusEnum;
import com.lzy.tour.enums.UserConstant;
import com.lzy.tour.model.User;
import com.lzy.tour.service.UserService;

@Controller
@RequestMapping("front")
public class FrontController {
	
	private static Logger logger=Logger.getLogger(FrontController.class);
	
	@Resource
	private UserService userService;
	
	@RequestMapping("/frontPage")
	public String front(HttpServletRequest request,HttpServletResponse response){
		Cookie cooie = CookieUtil.getCooie(request, UserConstant.COOKIE_USER_ID);
		if(cooie!=null&&StringUtils.isNotBlank(cooie.getValue())){//有userid
			Integer id=Integer.parseInt(cooie.getValue());
			User user = userService.getOneById(id);
			if(user!=null){
				request.getSession().setAttribute(UserConstant.SESSION_USER, user);
				return indexPage();
			}
		}
		WeixinProxy weixinProxy=new WeixinProxy();
		OauthApi oauthApi=weixinProxy.getOauthApi();
		String redirectUri=Weixin4jConfigUtil.getValue("user.oauth.redirect.uri");
		if(StringUtils.isEmpty(redirectUri)){
			redirectUri="http://wangreid.vicp.cc/tour/front/authorDeal";
		}
		String userAuthorizationURL = oauthApi.getUserAuthorizationURL(redirectUri, "STATE", "snsapi_userinfo");
		logger.info(userAuthorizationURL);
		return "redirect:"+userAuthorizationURL;
	}
	
	@RequestMapping("/authorDeal")
	public String authorDeal(HttpServletRequest request,HttpServletResponse response){
		logger.info("authorDeal");
		WeixinProxy weixinProxy = new WeixinProxy();
		try {
			String code = request.getParameter("code");
			logger.info("code:"+code);
			if(StringUtils.isNotBlank(code)){
				OauthToken authorizationToken = weixinProxy.getOauthApi().getAuthorizationToken(code);
				logger.info(authorizationToken);
				if(authorizationToken!=null){
					Map<String, Object> params = new HashMap<String, Object>();
					params.put("openId", authorizationToken.getOpenId());
					User user = userService.getOneByModel(params);
					if(user==null){//系统中没有该用户，进行注册
						com.foxinmy.weixin4j.mp.model.User wxUser = weixinProxy.getUser(authorizationToken.getOpenId());
						logger.info(wxUser);
						if(wxUser!=null){
							user=new User();
							user.setOpenId(authorizationToken.getOpenId());
							user.setNickName(wxUser.getNickName());
							user.setStatus(StatusEnum.ENABLED);
							user.setRole("normal");
							user.setPicUrl(wxUser.getHeadimgurl());
							//值为1时是男性，值为2时是女性，值为0时是未知
							int gender = wxUser.getGender();
							String sex=null;
							if(gender==1){
								sex="male";
							}
							if(gender==2){
								sex="female";
							}
							user.setSex(sex);
							user.setRegisterTime(new Date());
							userService.insert(user);
						}else{
							logger.error("获取微信用户异常,openId:"+authorizationToken.getOpenId());
						}
					} 
					if(user!=null){//存cookie,session
						CookieUtil.addMaxAgeCookie(response, UserConstant.COOKIE_USER_ID, user.getId().toString());
						request.getSession().setAttribute(UserConstant.SESSION_USER, user);
					}
				}else{
					logger.error("获取authorizationToken异常,code:"+code);
				}
			}else{
				logger.error("网页授权code为空");
			}
		} catch (WeixinException e) {
			logger.error(e);
		}
		return indexPage();
	}
	
	@RequestMapping("/indexPage")
	public String indexPage(){
		logger.info("indexPage");
		return "indexPage";
	}
	
}
