package com.lzy.tour.service.weixin.impl;

import com.lzy.tour.common.weixin.ConstantWeChat;
import com.lzy.tour.common.weixin.MessageUtil;
import com.lzy.tour.service.weixin.CoreService;

import java.util.ArrayList;
import java.util.Date;  
import java.util.List;
import java.util.Map;  
  






import javax.servlet.http.HttpServletRequest;  
  






import org.apache.log4j.Logger;  
import org.springframework.stereotype.Service;  
  






import com.lzy.tour.vo.message.resp.Article;
import com.lzy.tour.vo.message.resp.NewsMessage;
import com.lzy.tour.vo.message.resp.TextMessage;  
  
@Service("coreService")  
public class CoreServiceImpl implements CoreService{  
  
    public static Logger log = Logger.getLogger(CoreServiceImpl.class);  
      
    @Override  
    public String processRequest(HttpServletRequest request) {  
        String respMessage = null;  
        try {  
            // xml请求解析  
            Map<String, String> requestMap = MessageUtil.parseXml(request); 
            
            log.info(requestMap);
  
            // 发送方帐号（open_id）  
            String fromUserName = requestMap.get("FromUserName");  
            // 公众帐号  
            String toUserName = requestMap.get("ToUserName");  
            // 消息类型  
            String msgType = requestMap.get("MsgType");  
            
            String respContent = ""; 
            
            TextMessage textMessage = new TextMessage();  
            textMessage.setToUserName(fromUserName);  
            textMessage.setFromUserName(toUserName);  
            textMessage.setCreateTime(new Date().getTime());  
            textMessage.setMsgType(ConstantWeChat.RESP_MESSAGE_TYPE_TEXT);  
            textMessage.setFuncFlag(0);  
            
            
            // 创建图文消息  
            NewsMessage newsMessage = new NewsMessage();  
            newsMessage.setToUserName(fromUserName);  
            newsMessage.setFromUserName(toUserName);  
            newsMessage.setCreateTime(new Date().getTime());  
            newsMessage.setMsgType(ConstantWeChat.RESP_MESSAGE_TYPE_NEWS);  
            newsMessage.setFuncFlag(0);  
            List<Article> articleList = new ArrayList<Article>();  
            
            // 文本消息  
            if (msgType.equals(ConstantWeChat.REQ_MESSAGE_TYPE_TEXT)) {  
                // 接收用户发送的文本消息内容  
                String content = requestMap.get("Content");  
  
                if ("1".equals(content)) {  
                    textMessage.setContent("1是很好的");  
                    // 将文本消息对象转换成xml字符串  
                    respMessage = MessageUtil.textMessageToXml(textMessage);  
                }else if ("2".equals(content)){//单图文消息 
                	Article article = new Article();  
                    article.setTitle("我是一条单图文消息");  
                    article.setDescription("我是描述信息，哈哈哈哈哈哈哈。。。");  
                    article.setPicUrl("http://www.iteye.com/upload/logo/user/603624/2dc5ec35-073c-35e7-9b88-274d6b39d560.jpg");  
                    article.setUrl("http://tuposky.iteye.com");  
                    articleList.add(article);  
                    // 设置图文消息个数  
                    newsMessage.setArticleCount(articleList.size());  
                    // 设置图文消息包含的图文集合  
                    newsMessage.setArticles(articleList);  
                    // 将图文消息对象转换成xml字符串  
                    respMessage = MessageUtil.newsMessageToXml(newsMessage); 
                }else if ("3".equals(content)){//多图文消息
                	Article article1 = new Article();  
                    article1.setTitle("我是一条多图文消息");  
                    article1.setDescription("");  
                    article1.setPicUrl("http://wx.qlogo.cn/mmopen/Q3auHgzwzM7aaaDbYd794MS7JH2Syne9to8oNcLzukibcL1ELiaoE9x9ZriauCjPTQhr5xiaesb2KUtp2mkEbXT32gN0J2cMdcC81LuKfzG5rBQ/0");  
                    article1.setUrl("http://tuposky.iteye.com/blog/2008583");  
  
                    Article article2 = new Article();  
                    article2.setTitle("微信公众平台开发教程Java版（二）接口配置 ");  
                    article2.setDescription("");  
                    article2.setPicUrl("http://wx.qlogo.cn/mmopen/Q3auHgzwzM7aaaDbYd794MS7JH2Syne9to8oNcLzukibcL1ELiaoE9x9ZriauCjPTQhr5xiaesb2KUtp2mkEbXT32gN0J2cMdcC81LuKfzG5rBQ/0");  
                    article2.setUrl("http://tuposky.iteye.com/blog/2008655");  
  
                    Article article3 = new Article();  
                    article3.setTitle("微信公众平台开发教程Java版(三) 消息接收和发送");  
                    article3.setDescription("");  
                    article3.setPicUrl("http://wx.qlogo.cn/mmopen/Q3auHgzwzM7aaaDbYd794MS7JH2Syne9to8oNcLzukibcL1ELiaoE9x9ZriauCjPTQhr5xiaesb2KUtp2mkEbXT32gN0J2cMdcC81LuKfzG5rBQ/0");  
                    article3.setUrl("http://tuposky.iteye.com/blog/2017429");  
  
                    articleList.add(article1);  
                    articleList.add(article2);  
                    articleList.add(article3);  
                    newsMessage.setArticleCount(articleList.size());  
                    newsMessage.setArticles(articleList);  
                    respMessage = MessageUtil.newsMessageToXml(newsMessage);  
                }
            }else if(msgType.equals(ConstantWeChat.REQ_MESSAGE_TYPE_EVENT)){//事件消息
            	// 事件类型  
                String eventType = requestMap.get("Event");  
                if (eventType.equals(ConstantWeChat.EVENT_TYPE_SUBSCRIBE)) {//关注
                	StringBuilder builder=new StringBuilder();
                	builder.append("感谢您关注偶,这里会给您提供最新的公司资讯和公告！\n");
                	builder.append("您还可以回复下列数字，体验相应服务\n");
                	builder.append("1 回复文本信息").append("\n");  
                	builder.append("2  回复单图文消息").append("\n");  
                	builder.append("3  回复多图文消息").append("\n");
                	respContent=builder.toString();
                }else if(eventType.equals(ConstantWeChat.EVENT_TYPE_UNSUBSCRIBE)){//取消关注
                	
                }else if(eventType.equals(ConstantWeChat.EVENT_TYPE_CLICK)){//菜单点击
                	 // 事件KEY值，与创建自定义菜单时指定的KEY值对应  
                    String eventKey = requestMap.get("EventKey");  
                    // 自定义菜单点击事件  
                    if (eventKey.equals("11")) {  
                        respContent = "天气预报菜单项被点击！";  
                    } else if (eventKey.equals("12")) {  
                        respContent = "公交查询菜单项被点击！";  
                    } 
                }
                textMessage.setContent(respContent);  
                respMessage = MessageUtil.textMessageToXml(textMessage);	
            }
              
              
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return respMessage;  
    }  
  
  
}  