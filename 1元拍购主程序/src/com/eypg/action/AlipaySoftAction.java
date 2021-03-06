package com.eypg.action;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.eypg.alipay.config.AlipayConfig;
import com.eypg.alipay.util.AlipayNotify;
import com.eypg.alipay.util.AlipaySubmit;
import com.eypg.pojo.Commissionpoints;
import com.eypg.pojo.Commissionquery;
import com.eypg.pojo.Consumerdetail;
import com.eypg.pojo.Consumetable;
import com.eypg.pojo.Latestlottery;
import com.eypg.pojo.Product;
import com.eypg.pojo.ProductCart;
import com.eypg.pojo.ProductJSON;
import com.eypg.pojo.Randomnumber;
import com.eypg.pojo.Spellbuyproduct;
import com.eypg.pojo.Spellbuyrecord;
import com.eypg.pojo.User;
import com.eypg.service.CommissionpointsService;
import com.eypg.service.CommissionqueryService;
import com.eypg.service.ConsumerdetailService;
import com.eypg.service.ConsumetableService;
import com.eypg.service.LatestlotteryService;
import com.eypg.service.ProductService;
import com.eypg.service.RandomnumberService;
import com.eypg.service.SpellbuyproductService;
import com.eypg.service.SpellbuyrecordService;
import com.eypg.service.UserService;
import com.eypg.tenpay.util.TenpayUtil;
import com.eypg.util.ApplicationListenerImpl;
import com.eypg.util.DateUtil;
import com.eypg.util.MD5Util;
import com.eypg.util.MemCachedClientHelp;
import com.eypg.util.NewLotteryUtil;
import com.eypg.util.RandomUtil;
import com.eypg.util.Struts2Utils;
import com.opensymphony.xwork2.ActionSupport;

public class AlipaySoftAction extends ActionSupport {

	private static final long serialVersionUID = -8970081284102469306L;
	
	RandomUtil randomUtil = new RandomUtil();
	@Autowired
	ConsumetableService consumetableService;
	@Autowired
	ConsumerdetailService consumerdetailService;
	@Autowired
	private SpellbuyrecordService spellbuyrecordService;
	@Autowired
	private RandomnumberService randomnumberService;
	@Autowired
	private UserService userService;
	@Autowired
	private SpellbuyproductService spellbuyproductService;
	@Autowired
	private LatestlotteryService latestlotteryService;
	@Autowired
	private ProductService productService;
	@Autowired
	private NewLotteryUtil newLotteryUtil;
	@Autowired
	CommissionqueryService commissionqueryService;
	@Autowired
	CommissionpointsService commissionpointsService;
	
	private User user;
	private String userId;
	private Consumetable consumetable;
	private ProductCart productCart;
	private Product product;
	private Spellbuyproduct spellbuyproduct;
	private Spellbuyrecord spellbuyrecord;
	private Randomnumber randomnumber;
	private Latestlottery latestlottery;
	private Consumerdetail consumerdetail;
	private List<ProductCart> productCartList;
	private List<ProductJSON> successCartList;
	private ProductJSON productJSON;
	private String paymentStatus;
	private Commissionquery commissionquery;
	private Commissionpoints commissionpoints;
	
	private Integer moneyCount;
	private String integral;
	
	//当前时间 yyyyMMddHHmmss
	private String currTime = TenpayUtil.getCurrTime();
	//8位日期
	private String strTime = currTime.substring(8, currTime.length());
	//四位随机数
	private String strRandom = TenpayUtil.buildRandom(4) + "";
	//10位序列号,可以自行调整。
	private String strReq = strTime + strRandom;
	Random random = new Random();
	HttpServletRequest request = null;
	HttpServletResponse response = null;
	
	
	public String goPay() throws UnsupportedEncodingException{
		request = Struts2Utils.getRequest();
		response = Struts2Utils.getResponse();
		boolean flag = false;
		//支付类型
		String payment_type = "1";
		//必填，不能修改
		//服务器异步通知页面路径
//		http://124.193.138.90/tenpay/notifyUrl.action
		String notify_url = AlipayConfig.notify_url;
		//需http://格式的完整路径，不能加?id=123这类自定义参数
		//页面跳转同步通知页面路径
//		http://124.193.138.90/tenpay/returnUrl.action
		String return_url =AlipayConfig.return_url;
		//需http://格式的完整路径，不能加?id=123这类自定义参数，不能写成http://localhost/
		//卖家支付宝帐户
		String seller_email = AlipayConfig.mail;
		//必填
		//商户订单号
		String out_trade_no = strReq;
		//商户网站订单系统中唯一订单号，必填
		//订单名称
		String subject = "软件授权费";
		//必填
		//付款金额
		String total_fee = "5000";
		//必填
		//订单描述
		String body = "软件授权费";
		//商品展示地址
		String show_url = ApplicationListenerImpl.sysConfigureJson.getWwwUrl()+"/buySoft/index.html";
		//需以http://开头的完整路径，例如：http://www.xxx.com/myorder.html
		//防钓鱼时间戳
		String anti_phishing_key = currTime;
		//若要使用请调用类文件submit中的query_timestamp函数
		//客户端的IP地址
//		String exter_invoke_ip = request.getRemoteAddr();
		String exter_invoke_ip = request.getHeader("X-Real-IP");
		//非局域网的外网IP地址，如：221.0.0.1
		
		//把请求参数打包成数组
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", "create_direct_pay_by_user");
        sParaTemp.put("partner", AlipayConfig.partner);
        sParaTemp.put("_input_charset", AlipayConfig.input_charset);
		sParaTemp.put("payment_type", payment_type);
		sParaTemp.put("notify_url", notify_url);
		sParaTemp.put("return_url", return_url);
		sParaTemp.put("seller_email", seller_email);
		sParaTemp.put("out_trade_no", out_trade_no);
		sParaTemp.put("subject", subject);
		sParaTemp.put("total_fee", total_fee);
		sParaTemp.put("body", body);
		sParaTemp.put("show_url", show_url);
		sParaTemp.put("anti_phishing_key", anti_phishing_key);
		sParaTemp.put("exter_invoke_ip", exter_invoke_ip);
		sParaTemp.put("extra_common_param", integral);
		
		
//		/**
//		 * 业务开始
//		 */
//		productCartList = new ArrayList<ProductCart>();
//		Cookie[] cookies = request.getCookies();
//		JSONArray array = null;
//		if(request.isRequestedSessionIdFromCookie()){
//			for (int i = 0; i < cookies.length; i++) {
//				Cookie cookie = cookies[i];
//				if(cookie.getName().equals("userId")){
//					userId = cookie.getValue();
////					if(userId!=null && !userId.equals("")){
////						user = userService.findById(userId);
////					}
//				}
//				if(cookie.getName().equals("products")){
//					String product = new StringUtil().getUTF8URLDecoder(cookie.getValue());
//					if(product!=null && !product.equals("")){
//						array = JSONArray.fromObject(product);
//					}
//				}
//			}
//		}
//		
//		if(StringUtil.isNotBlank(userId)){
//			Integer buyConut = 0;
//			Integer productCount = 0;
//			if(array!=null && !array.toString().equals("[{}]")){
//				for(int i=0;i<array.size();i++){
//					try {
//						JSONObject obj = (JSONObject) array.get(i);
//						productCart = new ProductCart();
//						List<Object []> proList = spellbuyproductService.findByProductId(Integer.parseInt(obj.getString("pId")));
//						product = (Product) proList.get(0)[0];
//						spellbuyproduct = (Spellbuyproduct) proList.get(0)[1];
//						if(spellbuyproduct.getSpStatus()==0){
//							Integer count = 0;
//							//当前拍购人数
//							Integer CurrentPrice = spellbuyproduct.getSpellbuyCount();
//							if((spellbuyproduct.getSpellbuyCount()+obj.getInt("num")) > spellbuyproduct.getSpellbuyPrice()){
//								count = spellbuyproduct.getSpellbuyPrice()-spellbuyproduct.getSpellbuyCount();
//							}else{
//								count = obj.getInt("num");
//							}
//							buyConut += count;
//							productCount ++;
//							productCart.setCount(count);
//							productCart.setHeadImage(product.getHeadImage());
////							productCart.setMoneyCount(buyConut);  
//							productCart.setProductCount(productCount);
//							productCart.setProductId(spellbuyproduct.getSpellbuyProductId());
//							productCart.setProductName(product.getProductName());
//							productCart.setProductPrice(spellbuyproduct.getSpellbuyPrice());
//							productCart.setProductTitle(product.getProductTitle());
//							productCart.setCurrentBuyCount(spellbuyproduct.getSpellbuyCount());
//							productCart.setProductPeriod(spellbuyproduct.getProductPeriod());
//							productCartList.add(productCart);
//							flag = true;
//						}
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//				}
//			}
////			moneyCount  财付通充值的钱 600 
////			buyConut    要花的钱   7
////			hidUseBalance    余额的钱 1   buyConut=moneyCount+hidUseBalance
//			if(flag){
//				try {
//					consumetable = new Consumetable();
//					double money = Double.parseDouble(String.valueOf(moneyCount));
//					consumetable.setBuyCount(buyConut); //要花的钱 7 
//					consumetable.setDate(DateUtil.DateTimeToStr(new Date()));
//					consumetable.setInterfaceType("aliPay");
//					consumetable.setMoney(money); //财付通充值的钱 6 
//					consumetable.setOutTradeNo(out_trade_no);
//					consumetable.setUserId(Integer.parseInt(userId));
//					consumetableService.add(consumetable);
//				} catch (Exception e) {
//					e.printStackTrace();
//					flag = false;
//				}
//			}else{
//				flag = false;
//				Struts2Utils.render("text/html", "<script>alert(\"购物车中有商品已经满员，请选择下一期！\");window.location.href=\"/mycart/index.html\";</script>","encoding:UTF-8");
//			}
//		}
		
		//建立请求
		String sHtmlText = AlipaySubmit.buildRequest(sParaTemp,"get","确认");
		System.out.println(sHtmlText);
//		if(flag){
			Struts2Utils.render("text/html", sHtmlText,"encoding:UTF-8");
//		}else{
//			Struts2Utils.render("text/html", "<script>alert(\"购物车中有商品已经满员，请该商品的选择下一期！\");window.location.href=\"/mycart/index.html\";</script>","encoding:UTF-8");
//		}
		return null;
	}
	
	public String returnUrl() throws UnsupportedEncodingException{
		request = Struts2Utils.getRequest();
		response = Struts2Utils.getResponse();
		boolean flag = false;
		//获取支付宝GET过来反馈信息
		Map<String,String> params = new HashMap<String,String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
//			valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}
		
		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
		//商户订单号
		String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
		//支付宝交易号
		String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
		//金额
		String total_fee = new String(request.getParameter("total_fee").getBytes("ISO-8859-1"),"UTF-8");
		//交易状态
		String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
		 integral = new String(request.getParameter("extra_common_param").getBytes("ISO-8859-1"),"UTF-8");
		
		//计算得出通知验证结果
		boolean verify_result = AlipayNotify.verify(params);
		if(verify_result){//验证成功
			//请在这里加上商户的业务逻辑程序代码

			//——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
			if(trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")){
				//判断该笔订单是否在商户网站中已经做过处理
					//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					//如果有做过处理，不执行商户的业务程序
				//即时到账处理业务开始
				//------------------------------
				try {
					String key = MD5Util.encode(trade_no);
					if(MemCachedClientHelp.getIMemcachedCache().get(key)==null){
						MemCachedClientHelp.getIMemcachedCache().put(key, "y",new Date(12*60*60*1000));
						
			
						//注意交易单不要重复处理
						//注意判断返回金额
						
						//------------------------------
						//即时到账处理业务完毕
						//------------------------------
						System.out.println("即时到帐付款成功");
						paymentStatus = "success";
						String s = UUID.randomUUID().toString().toUpperCase();
				        s = s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24);
				        String md5s = MD5Util.encode(s);
				        String softKey = s+ md5s;
						userId = softKey;
					}else{
						paymentStatus = "ok";
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			//该页面可做页面美工编辑
			System.out.println("验证成功<br />");
			//——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
			//////////////////////////////////////////////////////////////////////////////////////////
		}else{
			//该页面可做页面美工编辑
			System.out.println("验证失败");
			paymentStatus = "error";
		}
		return "success";
	}
	
	public String notifyUrl() throws Exception{
		request = Struts2Utils.getRequest();
		response = Struts2Utils.getResponse();
		//获取支付宝POST过来反馈信息
		Map<String,String> params = new HashMap<String,String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
			params.put(name, valueStr);
		}
		
		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
		//商户订单号

		String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
		//支付宝交易号
		String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
		String total_fee = new String(request.getParameter("total_fee").getBytes("ISO-8859-1"),"UTF-8");
		//交易状态
		String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
		
		String integral = new String(request.getParameter("extra_common_param").getBytes("ISO-8859-1"),"UTF-8");
		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
		if(AlipayNotify.verify(params)){//验证成功
			//////////////////////////////////////////////////////////////////////////////////////////
			//请在这里加上商户的业务逻辑程序代码

			//——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
			
			if(trade_status.equals("TRADE_FINISHED")){
				//判断该笔订单是否在商户网站中已经做过处理
					//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					//如果有做过处理，不执行商户的业务程序
				//即时到账处理业务开始
				//注意：
				//该种交易状态只在两种情况下出现
				//1、开通了普通即时到账，买家付款成功后。
				//2、开通了高级即时到账，从该笔交易成功时间算起，过了签约时的可退款时限（如：三个月以内可退款、一年以内可退款等）后。
			} else if (trade_status.equals("TRADE_SUCCESS")){
				//判断该笔订单是否在商户网站中已经做过处理
					//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					//如果有做过处理，不执行商户的业务程序
				
				//注意：
				//该种交易状态只在一种情况下出现——开通了高级即时到账，买家付款成功后。
			}

			//——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
				
			System.out.println("success");	//请不要修改或删除
			Struts2Utils.render("text/html", "success","encoding:UTF-8");
			//////////////////////////////////////////////////////////////////////////////////////////
		}else{//验证失败
			Struts2Utils.render("text/html", "fail","encoding:UTF-8");
			System.out.println("fail");
		}
		return null;
	}
	
	public String index(){
		integral = DateUtil.DateTimeToStr(new Date());
		return "index";
	}

	public Integer getMoneyCount() {
		return moneyCount;
	}

	public void setMoneyCount(Integer moneyCount) {
		this.moneyCount = moneyCount;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Consumetable getConsumetable() {
		return consumetable;
	}

	public void setConsumetable(Consumetable consumetable) {
		this.consumetable = consumetable;
	}

	public ProductCart getProductCart() {
		return productCart;
	}

	public void setProductCart(ProductCart productCart) {
		this.productCart = productCart;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Spellbuyproduct getSpellbuyproduct() {
		return spellbuyproduct;
	}

	public void setSpellbuyproduct(Spellbuyproduct spellbuyproduct) {
		this.spellbuyproduct = spellbuyproduct;
	}

	public Spellbuyrecord getSpellbuyrecord() {
		return spellbuyrecord;
	}

	public void setSpellbuyrecord(Spellbuyrecord spellbuyrecord) {
		this.spellbuyrecord = spellbuyrecord;
	}

	public Randomnumber getRandomnumber() {
		return randomnumber;
	}

	public void setRandomnumber(Randomnumber randomnumber) {
		this.randomnumber = randomnumber;
	}

	public Latestlottery getLatestlottery() {
		return latestlottery;
	}

	public void setLatestlottery(Latestlottery latestlottery) {
		this.latestlottery = latestlottery;
	}

	public Consumerdetail getConsumerdetail() {
		return consumerdetail;
	}

	public void setConsumerdetail(Consumerdetail consumerdetail) {
		this.consumerdetail = consumerdetail;
	}

	public List<ProductCart> getProductCartList() {
		return productCartList;
	}

	public void setProductCartList(List<ProductCart> productCartList) {
		this.productCartList = productCartList;
	}

	public List<ProductJSON> getSuccessCartList() {
		return successCartList;
	}

	public void setSuccessCartList(List<ProductJSON> successCartList) {
		this.successCartList = successCartList;
	}

	public ProductJSON getProductJSON() {
		return productJSON;
	}

	public void setProductJSON(ProductJSON productJSON) {
		this.productJSON = productJSON;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public Commissionquery getCommissionquery() {
		return commissionquery;
	}

	public void setCommissionquery(Commissionquery commissionquery) {
		this.commissionquery = commissionquery;
	}

	public Commissionpoints getCommissionpoints() {
		return commissionpoints;
	}

	public void setCommissionpoints(Commissionpoints commissionpoints) {
		this.commissionpoints = commissionpoints;
	}

	public String getIntegral() {
		return integral;
	}

	public void setIntegral(String integral) {
		this.integral = integral;
	}


}
