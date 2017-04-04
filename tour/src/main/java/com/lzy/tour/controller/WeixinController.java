package com.lzy.tour.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.foxinmy.weixin4j.exception.WeixinException;
import com.foxinmy.weixin4j.http.weixin.ApiResult;
import com.foxinmy.weixin4j.http.weixin.XmlResult;
import com.foxinmy.weixin4j.model.Button;
import com.foxinmy.weixin4j.mp.WeixinProxy;
import com.foxinmy.weixin4j.mp.api.OauthApi;
import com.foxinmy.weixin4j.mp.model.OauthToken;
import com.foxinmy.weixin4j.mp.model.User;
import com.foxinmy.weixin4j.payment.WeixinPayProxy;
import com.foxinmy.weixin4j.payment.mch.MchPayRequest;
import com.foxinmy.weixin4j.payment.mch.Order;
import com.foxinmy.weixin4j.type.ButtonType;
import com.foxinmy.weixin4j.util.Consts;
import com.foxinmy.weixin4j.util.IOUtil;
import com.foxinmy.weixin4j.util.StringUtil;
import com.foxinmy.weixin4j.xml.ListsuffixResultDeserializer;
import com.foxinmy.weixin4j.xml.XmlStream;
import com.lzy.tour.common.weixin.SignUtil;

@Controller
@RequestMapping("/weixin")
public class WeixinController {
	
	private Logger logger=Logger.getLogger(WeixinController.class.getName());
	      
    @RequestMapping(value="/checkSignature",method = RequestMethod.GET)  
    public void get(HttpServletRequest request, HttpServletResponse response) {  
        // 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。  
        String signature = request.getParameter("signature");  
        // 时间戳  
        String timestamp = request.getParameter("timestamp");  
        // 随机数  
        String nonce = request.getParameter("nonce");  
        // 随机字符串  
        String echostr = request.getParameter("echostr");  
        PrintWriter out = null;  
        try {  
            out = response.getWriter();  
            // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，否则接入失败  
            if (SignUtil.checkSignature(signature, timestamp, nonce)) {  
                out.print(echostr);  
            }  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            out.close();  
            out = null;  
        }  
    }  
  
    @RequestMapping(method = RequestMethod.POST)  
    public void post(HttpServletRequest request, HttpServletResponse response) {  
        //暂时空着，在这里可处理用户请求  
    	
    	WeixinProxy weixinProxy = new WeixinProxy();
    }  
	
	@RequestMapping(value="/addMenu",produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public ApiResult addMenu(){
		 WeixinProxy weixinProxy = new WeixinProxy();
		 Button urlButton=new Button("测试跳转1", "http://localhost:8080/pure-web/weixin/getMenu.do", ButtonType.view);
		 Button clickButton=new Button("测试点击2", "testClick", ButtonType.click);
		 List<Button> buttons=new ArrayList<Button>();
		 buttons.add(urlButton);
		 buttons.add(clickButton);
		 try {
			ApiResult res = weixinProxy.createMenu(buttons);
			return res;
		 } catch (WeixinException e) {
			logger.error(e);
		 }
		 return null;
	}
	
	@RequestMapping(value="/getMenu",produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public List<Button> getMenu(){
		 WeixinProxy weixinProxy = new WeixinProxy();
		 try {
			 String token=weixinProxy.getTokenManager().getAccessToken();
			 logger.info("token:"+token);
			 List<Button> menu = weixinProxy.getMenu();
			 logger.info(menu);
			 return menu;
		} catch (WeixinException e) {
			logger.error(e);
		}
		 return null;
	}

	@RequestMapping(value="/delMenu",produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public ApiResult delMenu(){
		 WeixinProxy weixinProxy = new WeixinProxy();
		 try {
			ApiResult deleteMenu = weixinProxy.deleteMenu();
			logger.info(deleteMenu);
			return deleteMenu;
		} catch (WeixinException e) {
			logger.error(e);
		}
		 return null;
	}
	
	//------------------页面赋权，获取用户openid-------
	//获取用户权限code
	@RequestMapping(value="/getAutoTokenCode" )
	public void getAutoTokenCode(){
		 WeixinProxy weixinProxy = new WeixinProxy();
		 try {
			 String token=weixinProxy.getTokenManager().getAccessToken();
			 logger.info("token:"+token); 
			 String userAuthorizationURL = weixinProxy.getOauthApi().getUserAuthorizationURL("http://wangreid.vicp.cc/pure-web/weixin/getUserInfo.do", 
					 "STATE", "snsapi_userinfo");
			 logger.info(userAuthorizationURL);
			 
		} catch (WeixinException e) {
			logger.error(e);
		}
	}
	
	
	/**
	 * 通过code换取网页授权access_token
	 * @Title: getAccessToken
	 * @param @param request
	 * @param @param response
	 * @param @param model
	 * @param @return
	 * @return String
	 * @throws xinz
	 */
	@RequestMapping("getUserInfo")
	public String getUserInfo(HttpServletRequest request,HttpServletResponse response,Model model) {
		WeixinProxy weixinProxy = new WeixinProxy();
		try {
			String token=weixinProxy.getTokenManager().getAccessToken();
			logger.info("token:"+token);
			String code = request.getParameter("code");
			logger.info("code:"+code);
			if(StringUtils.isNotBlank(code)){
				OauthToken authorizationToken = weixinProxy.getOauthApi().getAuthorizationToken(code);
				logger.info(authorizationToken);
				User user = weixinProxy.getUser(authorizationToken.getOpenId());
				authorizationToken.getRefreshToken();
				logger.info(user);
				request.getSession().setAttribute("wxUser", user);
			}
		} catch (WeixinException e) {
			logger.error(e);
		}
		return "/userInfo"; 
	}
	
	public String refresh(HttpServletRequest request){
		WeixinProxy weixinProxy = new WeixinProxy();
		try {
			OauthApi oauthApi = weixinProxy.getOauthApi();
//			oauthApi.verifyAuthorizationToken(oauthToken, openId)
			OauthToken refreshAuthorizationToken = weixinProxy.getOauthApi().refreshAuthorizationToken("");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	
	//------------------微信支付相关-------
	@RequestMapping(value = "/getPrePayInfo")
	public void getPrePayInfo(ModelMap map){
		try {
			String openId = "oYOtks5RkHpzc9_05UVEOA717Mrg";
			String body = "支付描述,如订单费用";
			String outTradeNo = "18789913341";
			double totalFee = 1d; // 订单金额 单位元
			String notifyUrl = "http://wx.xxx.com/pay/notify";
			String createIp = "192.168.1.1";
			String attach = "支付时的附加信息,在回调时会原样带上,可为空";
			WeixinPayProxy weixinPayProxy=new WeixinPayProxy();
			// 发起一个JS支付请求,这里有个值得注意的地方：微信返回的预交易ID(payRequest.getPrePayId())是有2小时的时效性的，
			//超过2小时将不能重新发起支付，需重新生成一个`outTradeNo`订单号再次调用createJSPayRequest接口。
			//所以这里的`prePayId`有两种解决方案：1、每次发起支付都重新生成`outTradeNo`订单号，然后调用createJSPayRequest接口。
			//2、把`prePayId`缓存起来，然后通过：MchPayRequest payRequest = new JSAPIPayRequest(prePayId,weixinPayProxy.getPayAccount());
			//构建一个`MchPayRequest`支付对象。两种方式都有利有弊，请根据实际需求而定。
			MchPayRequest payRequest = weixinPayProxy.createJSPayRequest(openId, body, outTradeNo, totalFee, notifyUrl, createIp, attach);
			// 将支付JSON串放到request作用域
			map.put("JSPAY", payRequest.toRequestObject());
		} catch (WeixinException e) {
			logger.error(e);
		}
	}
	
	/**
	 * 微信获取prepay_id 同时下单
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getPrepayId2")
	@ResponseBody
	public  Map<String, Object> getPrepayId2(HttpServletRequest request, String sn) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		Order order = null;
		System.out.println("sn==========="+sn);
//		Member member = memberService.getCurrent();
//		if (order == null || !member.equals(order.getMember()) || order.getPaymentMethod() == null
//				|| order.getAmountPayable().compareTo(BigDecimal.ZERO) <= 0) {
//			System.out.println(ERROR_VIEW);
//			resultMap.put("error_message", ERROR_VIEW);
//			resultMap.put("success", 1);
//			return resultMap;
//		}
//		if (PaymentMethod.Method.online.equals(order.getPaymentMethod().getMethod())) {
//			if (orderService.isLocked(order, member, true)) {
//				System.out.println(Message.warn("shop.order.locked"));
//				resultMap.put("error_message", Message.warn("shop.order.locked"));
//				resultMap.put("success", 1);
//				return resultMap;
//			}

//			PaymentPlugin paymentPlugin = pluginService.getPaymentPlugin("wxpayPubPaymentPlugin");
//			if (paymentPlugin != null) {
//				if (paymentPlugin == null || !paymentPlugin.getIsEnabled()) {
//					System.out.println(ERROR_VIEW);
//					resultMap.put("error_message", ERROR_VIEW);
//					resultMap.put("success", 1);
//					return resultMap;
//				}

				// 添加支付记录
//				PaymentLog paymentLogtmp = paymentLogService.findBySn(order.getSn());
//				System.out.println("sn==========="+order.getSn());
//				System.out.println("paymentLogtmp==========="+paymentLogtmp);
//				if(paymentLogtmp==null){
//					PaymentLog paymentLog = new PaymentLog();
//					paymentLog.setSn(order.getSn());
//					paymentLog.setType(PaymentLog.Type.payment);
//					paymentLog.setStatus(PaymentLog.Status.wait);
//					paymentLog.setFee(paymentPlugin.calculateFee(order.getAmountPayable()));
//					paymentLog.setAmount(paymentPlugin.calculateAmount(order.getAmountPayable()));
//					paymentLog.setPaymentPluginId(paymentPlugin.getId());
//					paymentLog.setPaymentPluginName(paymentPlugin.getName());
//					paymentLog.setMember(member);
//					paymentLog.setOrder(order);
//					paymentLogService.save(paymentLog);
//				}

//				Map<String, String> paramMap = new HashMap<String, String>();
//				paramMap.put("out_trade_no", order.getSn());
//				paramMap.put("total_fee",
//						order.getAmount() != null ? paymentPlugin.calculateAmount(order.getAmountPayable())
//								.multiply(new BigDecimal("100")).toBigInteger().toString() : "0");
//				paramMap.put("openid", member.getOpenId());
//				String paramXms =CommonPayment.getPrepayIdParam(request, paramMap).toString();
//				String str = HttpRequest.sendPost(CommonWeChat.PAYMENT_GET_PREPAYID_URL, paramXms);
//				str = str.replaceAll("<![CDATA[|]]>", "");
//				SortedMap<String, String> dataMap = CommonPayment.xmlToMap(str);
//
//				SortedMap<String, String> data = new TreeMap<String, String>();
//				if (dataMap.get("return_code").equalsIgnoreCase("SUCCESS")
//						&& dataMap.get("result_code").equalsIgnoreCase("SUCCESS")) {
//
//					data.put("appId", CommonWeChat.APPID.trim());
//					data.put("timeStamp", Sha1Util.getTimeStamp().trim());
//					data.put("nonceStr", Sha1Util.getNonceStr().trim());
//					data.put("package", "prepay_id=" + dataMap.get("prepay_id").trim());
//					data.put("signType", CommonWeChat.SIGNTYPE.trim());
//					data.put("paySign", CommonPayment.getMD5Sign(data).trim());
//					resultMap.put("success", 0);
//					resultMap.put("resultData", data);
//				} else if (dataMap.get("return_code").equalsIgnoreCase("FAIL")) {
//					System.out.println(dataMap.get("return_msg"));
//					resultMap.put("error_message", dataMap.get("return_msg"));
//					resultMap.put("success", 1);
//				} else if (dataMap.get("result_code").equalsIgnoreCase("FAIL")) {
//					System.out.println(dataMap.get("err_code_des"));
//					resultMap.put("error_message", dataMap.get("err_code_des"));
//					resultMap.put("success", 1);
//				} else {
//					System.out.println(dataMap.get("数据有误"));
//					resultMap.put("error_message", "数据有误");
//					resultMap.put("success", 1);
//				}
//			} else {
//				System.out.println(ERROR_VIEW);
//				resultMap.put("error_message", ERROR_VIEW);
//				resultMap.put("success", 1);
//				return resultMap;
//			}
//		}

		return resultMap;
	}
	
	/**
     * 微信支付成功(前端)时的回调通知
     * 
     * @param inputStream
     *            订单回调
     * @return &ltxml&gt<br>
     *         &ltreturn_code&gtSUCCESS/FAIL&lt/return_code&gt<br>
     *         &ltreturn_msg&gt如非空,为错误 原因签名失败参数格式校验错误&lt/return_msg&gt<br>
     *         &lt/xml&gt
     * @throws IOException 
     * @see <a
     *      href="http://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_7">支付结果通知</a>
     */
    @RequestMapping("/order/notify")
    @ResponseBody
    public String payNotify(InputStream input) throws IOException {
    	WeixinPayProxy weixinPayProxy=new WeixinPayProxy();
    	//获取订单信息
        String content = StringUtil.newStringUtf8(IOUtil.toByteArray(input));
        Order order = ListsuffixResultDeserializer.deserialize(content,Order.class);
        logger.info("jsapi_notify_order_info:"+order);
        //验证签名
        String sign = order.getSign();
        String valid_sign = weixinPayProxy.getWeixinSignature().sign(order);
        logger.info("微信签名----->sign={},vaild_sign={},"+ sign+","+ valid_sign);
        if (!sign.equals(valid_sign)) {
            return XmlStream.toXML(new XmlResult(Consts.FAIL, "签名错误"));
        }
        // TODO 处理业务逻辑，如没有特殊要求可以考虑单独启一个线程去处理自己的业务，对于微信签名过后就可以返回success了。
        // 需要ajax的形式返回给微信，保证返回值能写到ResponseInputStream就行，Spring mvc使用 @ResponseBody注解，Servlet使用HttpServletResponse#getWrite#write
        return XmlStream.toXML(new XmlResult(Consts.SUCCESS, ""));
    }
	
}
