package com.eypg.web.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.eypg.dao.Pagination;
import com.eypg.pojo.BuyHistoryJSON;
import com.eypg.pojo.IndexImg;
import com.eypg.pojo.Latestlottery;
import com.eypg.pojo.News;
import com.eypg.pojo.Product;
import com.eypg.pojo.ProductJSON;
import com.eypg.pojo.Spellbuyproduct;
import com.eypg.pojo.Spellbuyrecord;
import com.eypg.pojo.User;
import com.eypg.pojo.UserJSON;
import com.eypg.service.LatestlotteryService;
import com.eypg.service.NewsService;
import com.eypg.service.RecommendService;
import com.eypg.service.SpellbuyproductService;
import com.eypg.service.SpellbuyrecordService;
import com.eypg.service.UserService;
import com.eypg.util.ApplicationListenerImpl;
import com.eypg.util.DateUtil;
import com.eypg.util.MD5Util;
import com.eypg.util.MemCachedClientHelp;
import com.eypg.util.Struts2Utils;
import com.eypg.util.UserNameUtil;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("unused")
@Component("WebIndexAction")
public class WebIndexAction extends ActionSupport {
	
	private static final long serialVersionUID = -1247058159354574031L;
	@Autowired
	@Qualifier("spellbuyrecordService")
	private SpellbuyrecordService spellbuyrecordService;
	@Autowired
	@Qualifier("latestlotteryService")
	private LatestlotteryService latestlotteryService;
	@Autowired
	@Qualifier("spellbuyproductService")
	private SpellbuyproductService spellbuyproductService;
	@Autowired
	private NewsService newsService;
	@Autowired
	private RecommendService recommendService;
	@Autowired
	private UserService userService;
	
	private List<ProductJSON> hotProductList;
	private List<ProductJSON> newProductList;
	private List<ProductJSON> indexPopProductList;
	private List<ProductJSON> productList;
	private List<BuyHistoryJSON> buyHistoryJSONList;
	public static List<News> newsList;
	private BuyHistoryJSON buyHistoryJSON;
	private ProductJSON productJSON;
	private ProductJSON recommendJSON;
	private UserJSON userJSON;
	private Product product;
	private Spellbuyrecord spellbuyrecord;
	private Spellbuyproduct spellbuyproduct;
	private Latestlottery latestlottery;
	private List<Latestlottery> latestlotteryList;
	private User user;
	private String id;
	private int pageNo;
	private int pageSize = 20;
	private int pageCount;
	private int resultCount;
	private String startDate;
	private String endDate;
	private List<IndexImg> indexImgList;
	
	private String uid;
	private static List<ProductJSON> nowBuyProductList;
	private static List<ProductJSON> newRecordList;
	private static Long allBuyCount;
	private static Long nowDateByAllCount = System.currentTimeMillis();
	private static Long beginDateByAllCount;
	private static Long nowDateByNowBuyProduct = System.currentTimeMillis();
	private static Long beginDateByNowBuyProduct;
	private static Long nowDateByNewRecord = System.currentTimeMillis();
	private static Long beginDateByNewRecord;
	
	HttpServletRequest request = null;
	HttpServletResponse response = null;
	
	public String index(){
		if(!ApplicationListenerImpl.sysConfigureAuth){
			Struts2Utils.renderHtml("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">" +
					"<html><head><title>授权过期 1元拍购开发中心</title><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /></head>" +
					"<body><div align=\"center\" style=\"color: #f60;font-family: arial,微软雅黑;font-size: 18px;margin-top: 180px;\">该系统授权已过期，请联系管理员重新授权！谢谢合作。<a href=\"http://www.51openos.com\">www.51openos.com</a></div></body></html>");
			return null;
		}
		
		/**
		 * 最新揭晓
		 */
		List<Latestlottery> objList =  latestlotteryService.indexWinningScroll();
		latestlotteryList = new ArrayList<Latestlottery>();
		for (int i = 0; i < objList.size(); i++) {
			latestlottery = new Latestlottery();
			latestlottery = objList.get(i);
			String userName = "";
			if(latestlottery.getUserName()!=null && !latestlottery.getUserName().equals("")){
				userName = latestlottery.getUserName();
			}else if(latestlottery.getBuyUser()!=null && !latestlottery.getBuyUser().equals("")){
				userName = latestlottery.getBuyUser();
				if(userName.indexOf("@")!=-1){
					String[] u = userName.split("@");
					String u1 = u[0].substring(0,2)+"***";
					userName = u1+"@"+u[1];
				}else{
					userName = userName.substring(0,4)+ "*** "+userName.substring(7);
				}
			}
			latestlottery.setBuyUser(userName);
			latestlotteryList.add(latestlottery);
		}
		
		/**
		 * 每日推荐
		 */
		List<Object []> objectList = recommendService.getRecommend();
		if(objectList!=null && objectList.size()>0){
			recommendJSON = new ProductJSON();
			product = (Product) objectList.get(0)[0];
			spellbuyproduct = (Spellbuyproduct) objectList.get(0)[1];
			recommendJSON.setCurrentBuyCount(spellbuyproduct.getSpellbuyCount());
			recommendJSON.setHeadImage(product.getHeadImage());
			recommendJSON.setProductId(spellbuyproduct.getSpellbuyProductId());
			recommendJSON.setProductName(product.getProductName());
			recommendJSON.setProductPrice(spellbuyproduct.getSpellbuyPrice());
			recommendJSON.setProductTitle(product.getProductTitle());
		}
		/**
		 * 新闻
		 */
		if(newsList==null){
			newsList = newsService.indexNews();
		}
		
		indexImgList = JSONArray.fromObject(ApplicationListenerImpl.indexImgAll);
		
		/**
		 * 即将揭晓
		 * @return
		 */
		Pagination datePage = spellbuyproductService.upcomingAnnounced(pageNo, 5);
		List<Object[]> dateList = (List<Object[]>) datePage.getList();
		productList = new ArrayList<ProductJSON>();
		for (int i = 0; i < dateList.size(); i++) {
			productJSON = new ProductJSON(); 
			product = (Product) dateList.get(i)[0];
			spellbuyproduct = (Spellbuyproduct) dateList.get(i)[1];
			productJSON.setCurrentBuyCount(spellbuyproduct.getSpellbuyCount());
			productJSON.setHeadImage(product.getHeadImage());
			productJSON.setProductId(spellbuyproduct.getSpellbuyProductId());
			productJSON.setProductName(product.getProductName());
			productJSON.setProductPrice(spellbuyproduct.getSpellbuyPrice());
			productJSON.setProductTitle(product.getProductTitle());
			productJSON.setProductStyle(product.getStyle());
			productList.add(productJSON);
		}
		return "index";
	}
	
	public void getIndexHotProductList(){
		/**
		 * 热门推荐
		 */
		Pagination hotPage = spellbuyrecordService.findHotProductList(1,8);
		List<Object[]> HotList = (List<Object[]>) hotPage.getList();
		hotProductList = new ArrayList<ProductJSON>();
		for (int i = 0; i < HotList.size(); i++) {
			productJSON = new ProductJSON(); 
			product = (Product) HotList.get(i)[0];
			spellbuyproduct = (Spellbuyproduct) HotList.get(i)[1];
			productJSON.setCurrentBuyCount(spellbuyproduct.getSpellbuyCount());
			productJSON.setHeadImage(product.getHeadImage());
			productJSON.setProductId(spellbuyproduct.getSpellbuyProductId());
			productJSON.setProductName(product.getProductName());
			productJSON.setProductPrice(spellbuyproduct.getSpellbuyPrice());
			productJSON.setProductTitle(product.getProductTitle());
			productJSON.setProductStyle(product.getStyle());
			hotProductList.add(productJSON);
		}
		Struts2Utils.renderJson(hotProductList);
	}
	
	public void getIndexNewProductList(){
		/**
		 * 新品上架
		 */
		Pagination phoneDigitalPage = spellbuyrecordService.indexNewProductList(pageNo,10);
		List<Object[]> phoneDigitalList = (List<Object[]>) phoneDigitalPage.getList();
		newProductList = new ArrayList<ProductJSON>();
		for (int i = 0; i < phoneDigitalList.size(); i++) {
			productJSON = new ProductJSON(); 
			product = (Product) phoneDigitalList.get(i)[0];
			spellbuyproduct = (Spellbuyproduct) phoneDigitalList.get(i)[1];
			productJSON.setCurrentBuyCount(spellbuyproduct.getSpellbuyCount());
			productJSON.setHeadImage(product.getHeadImage());
			productJSON.setProductId(spellbuyproduct.getSpellbuyProductId());
			productJSON.setProductName(product.getProductName());
			productJSON.setProductPrice(spellbuyproduct.getSpellbuyPrice());
			productJSON.setProductTitle(product.getProductTitle());
			productJSON.setProductStyle(product.getStyle());
			newProductList.add(productJSON);
		}
		Struts2Utils.renderJson(newProductList);
	}
	
	public void getIndexPopProductList(){
		/**
		 * 人气排行
		 */
		Pagination ComputerOfficePage = spellbuyrecordService.indexHotProductList(pageNo,8);
		List<Object[]> ComputerOfficeList = (List<Object[]>) ComputerOfficePage.getList();
		indexPopProductList = new ArrayList<ProductJSON>();
		for (int i = 0; i < ComputerOfficeList.size(); i++) {
			productJSON = new ProductJSON(); 
			product = (Product) ComputerOfficeList.get(i)[0];
			spellbuyproduct = (Spellbuyproduct) ComputerOfficeList.get(i)[1];
			productJSON.setCurrentBuyCount(spellbuyproduct.getSpellbuyCount());
			productJSON.setHeadImage(product.getHeadImage());
			productJSON.setProductId(spellbuyproduct.getSpellbuyProductId());
			productJSON.setProductName(product.getProductName());
			productJSON.setProductPrice(spellbuyproduct.getSpellbuyPrice());
			productJSON.setProductTitle(product.getProductTitle());
			productJSON.setProductStyle(product.getStyle());
			indexPopProductList.add(productJSON);
		}
		Struts2Utils.renderJson(indexPopProductList);
	}
	
	public void getIndexUserInfo(){
		user = userService.findById(id);
		if(user!=null){
			userJSON = new UserJSON();
			userJSON.setUserBalance(user.getBalance());
			userJSON.setUserExperience(user.getExperience());
			userJSON.setUserFace(user.getFaceImg());
			userJSON.setUserId(user.getUserId().toString());
			if(user.getExperience()<10000){
				userJSON.setUserLevel(1);
				userJSON.setUserLevelName(ApplicationListenerImpl.sysConfigureJson.getShortName()+"小将");
			}else if(user.getExperience()<50000){
				userJSON.setUserLevel(2);
				userJSON.setUserLevelName(ApplicationListenerImpl.sysConfigureJson.getShortName()+"少将");
			}else if(user.getExperience()<100000){
				userJSON.setUserLevel(3);
				userJSON.setUserLevelName(ApplicationListenerImpl.sysConfigureJson.getShortName()+"中将");
			}else if(user.getExperience()<500000){
				userJSON.setUserLevel(4);
				userJSON.setUserLevelName(ApplicationListenerImpl.sysConfigureJson.getShortName()+"上将");
			}else if(user.getExperience()<1000000){
				userJSON.setUserLevel(5);
				userJSON.setUserLevelName(ApplicationListenerImpl.sysConfigureJson.getShortName()+"少校");
			}else if(user.getExperience()<2000000){
				userJSON.setUserLevel(6);
				userJSON.setUserLevelName(ApplicationListenerImpl.sysConfigureJson.getShortName()+"中校");
			}else if(user.getExperience()<5000000){
				userJSON.setUserLevel(7);
				userJSON.setUserLevelName(ApplicationListenerImpl.sysConfigureJson.getShortName()+"上校");
			}else if(user.getExperience()<10000000){
				userJSON.setUserLevel(7);
				userJSON.setUserLevelName(ApplicationListenerImpl.sysConfigureJson.getShortName()+"元帅");
			}
			userJSON.setUserName(UserNameUtil.userName(user));
			Struts2Utils.renderJson(userJSON);
		}
	}
	
	/**
	 * TA们正在拍购
	 * @return
	 */
	public String getNowBuyProduct(){
		nowDateByNowBuyProduct = System.currentTimeMillis();
		if(beginDateByNowBuyProduct==null){
			Pagination page = spellbuyrecordService.getNowBuyList(pageNo, pageSize);
			List<Object[]> newBuyList = (List<Object[]>) page.getList();
			nowBuyProductList = new ArrayList<ProductJSON>();
			for (int i = 0; i < newBuyList.size(); i++) {
				productJSON = new ProductJSON(); 
				product = (Product) newBuyList.get(i)[0];
				spellbuyrecord = (Spellbuyrecord) newBuyList.get(i)[1];
				user = (User) newBuyList.get(i)[2];
				spellbuyproduct = (Spellbuyproduct) newBuyList.get(i)[3];
				String userName = UserNameUtil.userName(user);
				productJSON.setBuyer(userName);
				productJSON.setUserId(String.valueOf(user.getUserId()));
				productJSON.setHeadImage(user.getFaceImg());
				productJSON.setProductId(spellbuyproduct.getSpellbuyProductId());
				productJSON.setProductName(product.getProductName());
				productJSON.setProductTitle(product.getProductTitle());
				nowBuyProductList.add(productJSON);
			}
			beginDateByNowBuyProduct = System.currentTimeMillis();
//			System.err.println("他们正在购执行SQL");
			Struts2Utils.renderJson(nowBuyProductList);
		}else{
			if((nowDateByNowBuyProduct-beginDateByNowBuyProduct)<5000){
//				System.err.println("他们正在购不执行");
				Struts2Utils.renderJson(nowBuyProductList);
			}else{
				beginDateByNowBuyProduct = System.currentTimeMillis();
				Pagination page = spellbuyrecordService.getNowBuyList(pageNo, pageSize);
				List<Object[]> newBuyList = (List<Object[]>) page.getList();
				nowBuyProductList = new ArrayList<ProductJSON>();
				for (int i = 0; i < newBuyList.size(); i++) {
					productJSON = new ProductJSON(); 
					product = (Product) newBuyList.get(i)[0];
					spellbuyrecord = (Spellbuyrecord) newBuyList.get(i)[1];
					user = (User) newBuyList.get(i)[2];
					spellbuyproduct = (Spellbuyproduct) newBuyList.get(i)[3];
					String userName = UserNameUtil.userName(user);
					productJSON.setBuyer(userName);
					productJSON.setUserId(String.valueOf(user.getUserId()));
					productJSON.setHeadImage(user.getFaceImg());
					productJSON.setProductId(spellbuyproduct.getSpellbuyProductId());
					productJSON.setProductName(product.getProductName());
					productJSON.setProductTitle(product.getProductTitle());
					nowBuyProductList.add(productJSON);
				}
//				System.err.println("他们正在购再执行SQL");
				Struts2Utils.renderJson(nowBuyProductList);
			}
		}
		return null;
	}
	
	public void getServerTime(){
		Date date = new Date();
		date = DateUtil.SDateTimeToDate("2014-09-23 16:14:29");
//		Struts2Utils.renderText((DateUtil.SDateTimeToDate(DateUtil.DateTimeToStr(new Date())).getTime()-date.getTime())+"");
		Struts2Utils.renderText(System.currentTimeMillis()+"");
	}
	
	public static void main(String[] args) {
		Date date = new Date();
//		2591723
		date = DateUtil.SDateTimeToDate("2014-10-23 15:42:47");
		System.err.println(date.getTime());
		System.err.println(DateUtil.SDateTimeToDate(DateUtil.DateTimeToStr(new Date())).getTime());
		System.err.println(DateUtil.SDateTimeToDate(DateUtil.DateTimeToStr(new Date())).getTime()-date.getTime());
		System.err.println((DateUtil.SDateTimeToDate(DateUtil.DateTimeToStr(new Date())).getTime()-date.getTime())/60);
		System.err.println(System.currentTimeMillis());
	}
	
	/**
	 * 邀请
	 * @return
	 */
	public String referAuth(){
		HttpServletRequest request = Struts2Utils.getRequest();
		Cookie[] cookies = request.getCookies();
		if(request.isRequestedSessionIdFromCookie()){
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];
				if(cookie.getName().equals("userId")){
					uid = cookie.getValue();
//					if(uid!=null && !uid.equals("")){
//						
//						return "referAuthLogin";
//					}
				}
			}
		}
		return "referAuthLogin";
	}
	
	public String share(){
		request = Struts2Utils.getRequest();
		response = Struts2Utils.getResponse();
		
		if(request.isRequestedSessionIdFromCookie()){
			Cookie cookie = new Cookie("inviteId",uid); 
			cookie.setMaxAge(12*60*60);
			cookie.setPath("/");
			cookie.setDomain(ApplicationListenerImpl.sysConfigureJson.getDomain());
			response.addCookie(cookie);
		}
		
//		String ip = Struts2Utils.getRequest().getRemoteAddr();
		String ip = Struts2Utils.getRequest().getHeader("X-Real-IP");
		if(ip==null){
			ip = "127.0.0.1";
		}
		if(MemCachedClientHelp.getIMemcachedCache().get(uid)==null){
			user = userService.findById(uid);
			if(user!=null){
				Integer m = user.getCommissionPoints();
				m += ApplicationListenerImpl.sysConfigureJson.getInvite();
				user.setCommissionPoints(m);
				userService.add(user);
				MemCachedClientHelp.getIMemcachedCache().put(uid, 1,new Date(12*60*60*1000));
			}
		}
		Struts2Utils.render("text/html", "<script>window.location.href=\"/?share="+uid+"\";</script>","encoding:UTF-8");
		return null;
	}
	
	/**
	 * 全站拍购总人数
	 */
	public void getAllBuyCount(){
		nowDateByAllCount = System.currentTimeMillis();
		if(beginDateByAllCount==null){
			allBuyCount = Long.parseLong(spellbuyrecordService.getAllByCount().toString());
			beginDateByAllCount = System.currentTimeMillis();
//			System.err.println("全站拍购人数执行SQL");
			Struts2Utils.renderText(String.valueOf(allBuyCount));
		}else{
			if((nowDateByAllCount-beginDateByAllCount)<5000){
//				System.err.println("全站拍购人数不执行");
				Struts2Utils.renderText(String.valueOf(allBuyCount));
			}else{
				beginDateByAllCount = System.currentTimeMillis();
				allBuyCount = Long.parseLong(spellbuyrecordService.getAllByCount().toString());
//				System.err.println("全站拍购人数再执行SQL");
				Struts2Utils.renderText(String.valueOf(allBuyCount));
			}
		}
	}
	
	/**
	 * 最新100条拍购记录
	 * @return
	 */
	public String getNewRecord(){
		Pagination page = spellbuyrecordService.getNowBuyList(pageNo, 100);
		List<Object[]> newBuyList = (List<Object[]>) page.getList();
		productList = new ArrayList<ProductJSON>();
		for (int i = 0; i < newBuyList.size(); i++) {
			productJSON = new ProductJSON(); 
			product = (Product) newBuyList.get(i)[0];
			spellbuyrecord = (Spellbuyrecord) newBuyList.get(i)[1];
			user = (User) newBuyList.get(i)[2];
			spellbuyproduct = (Spellbuyproduct) newBuyList.get(i)[3];
			String userName = UserNameUtil.userName(user);
			productJSON.setBuyer(userName);
			productJSON.setUserId(String.valueOf(user.getUserId()));
			productJSON.setProductId(spellbuyproduct.getSpellbuyProductId());
			if(product.getProductName().length()>35){
				productJSON.setProductName(product.getProductName().substring(0,35)+"...");
			}else{
				productJSON.setProductName(product.getProductName());
			}
			productJSON.setProductPeriod(spellbuyproduct.getProductPeriod());
			productJSON.setBuyCount(spellbuyrecord.getBuyPrice());
			productJSON.setProductTitle(product.getProductTitle());
			productJSON.setBuyDate(spellbuyrecord.getBuyDate());
			productJSON.setProductStyle(String.valueOf(spellbuyrecord.getSpellbuyRecordId()));
			productList.add(productJSON);
		}
		return "newRecord";
	}
	
	public void getNewRecordAjax(){
		nowDateByNewRecord = System.currentTimeMillis();
		if(beginDateByNewRecord==null){
			Pagination page = spellbuyrecordService.getNowBuyAjaxList(pageNo, 100,Integer.parseInt(id));
			List<Object[]> newBuyList = (List<Object[]>) page.getList();
			newRecordList = new ArrayList<ProductJSON>();
			for (int i = 0; i < newBuyList.size(); i++) {
				productJSON = new ProductJSON(); 
				product = (Product) newBuyList.get(i)[0];
				spellbuyrecord = (Spellbuyrecord) newBuyList.get(i)[1];
				user = (User) newBuyList.get(i)[2];
				spellbuyproduct = (Spellbuyproduct) newBuyList.get(i)[3];
				String userName = UserNameUtil.userName(user);
				productJSON.setBuyer(userName);
				productJSON.setUserId(String.valueOf(user.getUserId()));
				productJSON.setProductId(spellbuyproduct.getSpellbuyProductId());
				if(product.getProductName().length()>35){
					productJSON.setProductName(product.getProductName().substring(0,35)+"...");
				}else{
					productJSON.setProductName(product.getProductName());
				}
				productJSON.setProductPeriod(spellbuyproduct.getProductPeriod());
				productJSON.setBuyCount(spellbuyrecord.getBuyPrice());
				productJSON.setProductTitle(product.getProductTitle());
				productJSON.setBuyDate(spellbuyrecord.getBuyDate());
				productJSON.setProductStyle(String.valueOf(spellbuyrecord.getSpellbuyRecordId()));
				newRecordList.add(productJSON);
			}
			beginDateByNewRecord = System.currentTimeMillis();
//			System.err.println("最新参与执行SQL");
			Struts2Utils.renderJson(newRecordList);
		}else{
			if((nowDateByNewRecord-beginDateByNewRecord)<5000){
//				System.err.println("最新参与不执行");
				Struts2Utils.renderJson(newRecordList);
			}else{
				beginDateByNewRecord = System.currentTimeMillis();
				Pagination page = spellbuyrecordService.getNowBuyAjaxList(pageNo, 100,Integer.parseInt(id));
				List<Object[]> newBuyList = (List<Object[]>) page.getList();
				newRecordList = new ArrayList<ProductJSON>();
				for (int i = 0; i < newBuyList.size(); i++) {
					productJSON = new ProductJSON(); 
					product = (Product) newBuyList.get(i)[0];
					spellbuyrecord = (Spellbuyrecord) newBuyList.get(i)[1];
					user = (User) newBuyList.get(i)[2];
					spellbuyproduct = (Spellbuyproduct) newBuyList.get(i)[3];
					String userName = UserNameUtil.userName(user);
					productJSON.setBuyer(userName);
					productJSON.setUserId(String.valueOf(user.getUserId()));
					productJSON.setProductId(spellbuyproduct.getSpellbuyProductId());
					if(product.getProductName().length()>35){
						productJSON.setProductName(product.getProductName().substring(0,35)+"...");
					}else{
						productJSON.setProductName(product.getProductName());
					}
					productJSON.setProductPeriod(spellbuyproduct.getProductPeriod());
					productJSON.setBuyCount(spellbuyrecord.getBuyPrice());
					productJSON.setProductTitle(product.getProductTitle());
					productJSON.setBuyDate(spellbuyrecord.getBuyDate());
					productJSON.setProductStyle(String.valueOf(spellbuyrecord.getSpellbuyRecordId()));
					newRecordList.add(productJSON);
				}
//				System.err.println("最新参与再执行SQL");
				Struts2Utils.renderJson(newRecordList);
			}
		}
	}
	
	/**
	 * 全站拍购记录查询 (有选择时间)
	 * @return
	 */
	
	public String getAllBuyRecord(){
		Pagination datePage = spellbuyrecordService.getAllBuyRecord(startDate,endDate, pageNo, pageSize);
		List<Object[]> dataList = (List<Object[]>) datePage.getList();
		buyHistoryJSONList = new ArrayList<BuyHistoryJSON>();
		for (int j = 0; j < dataList.size(); j++) {
			buyHistoryJSON = new BuyHistoryJSON(); 
			product = (Product) dataList.get(j)[0];
			spellbuyrecord = (Spellbuyrecord) dataList.get(j)[1];
			user = (User) dataList.get(j)[2];
			spellbuyproduct = (Spellbuyproduct) dataList.get(j)[3];
			buyHistoryJSON.setBuyCount(Long.parseLong(String.valueOf(spellbuyrecord.getBuyPrice())));
			buyHistoryJSON.setBuyStatus(spellbuyproduct.getSpStatus());
			buyHistoryJSON.setHistoryId(spellbuyrecord.getSpellbuyRecordId());
			buyHistoryJSON.setProductId(spellbuyproduct.getSpellbuyProductId());
			buyHistoryJSON.setProductImg(product.getHeadImage());
			buyHistoryJSON.setProductName(product.getProductName());
			buyHistoryJSON.setProductPeriod(spellbuyproduct.getProductPeriod());
			buyHistoryJSON.setProductTitle(product.getProductTitle());
			if(spellbuyproduct.getSpStatus()==1){
				latestlottery = (Latestlottery) latestlotteryService.getBuyHistoryByDetail(spellbuyproduct.getSpellbuyProductId()).get(0);
				buyHistoryJSON.setWinDate(latestlottery.getAnnouncedTime());
				buyHistoryJSON.setWinId(latestlottery.getRandomNumber());
				String userer = null;
				if(latestlottery.getUserName()!=null &&!latestlottery.getUserName().equals("")){
					userer = latestlottery.getUserName();
				}else{
					if(latestlottery.getBuyUser()!=null && !latestlottery.getBuyUser().equals("")){
						userer = latestlottery.getBuyUser();
						if(userer.indexOf("@")!=-1){
							String[] u = userer.split("@");
							String u1 = u[0].substring(0,2)+"***";
							userer = u1+"@"+u[1];
						}else{
							userer = userer.substring(0,4)+ "*** "+userer.substring(7); 
						}
					}
				}
				buyHistoryJSON.setWinUser(userer);
			}
			buyHistoryJSONList.add(buyHistoryJSON);
		}
		return "allBuyRecord";
	}
	
	
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Spellbuyrecord getSpellbuyrecord() {
		return spellbuyrecord;
	}


	public void setSpellbuyrecord(Spellbuyrecord spellbuyrecord) {
		this.spellbuyrecord = spellbuyrecord;
	}


	public Spellbuyproduct getSpellbuyproduct() {
		return spellbuyproduct;
	}


	public void setSpellbuyproduct(Spellbuyproduct spellbuyproduct) {
		this.spellbuyproduct = spellbuyproduct;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}

	public List<ProductJSON> getHotProductList() {
		return hotProductList;
	}

	public void setHotProductList(List<ProductJSON> hotProductList) {
		this.hotProductList = hotProductList;
	}

	public Latestlottery getLatestlottery() {
		return latestlottery;
	}

	public void setLatestlottery(Latestlottery latestlottery) {
		this.latestlottery = latestlottery;
	}

	public List<Latestlottery> getLatestlotteryList() {
		return latestlotteryList;
	}

	public void setLatestlotteryList(List<Latestlottery> latestlotteryList) {
		this.latestlotteryList = latestlotteryList;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getResultCount() {
		return resultCount;
	}

	public void setResultCount(int resultCount) {
		this.resultCount = resultCount;
	}

	public ProductJSON getProductJSON() {
		return productJSON;
	}

	public void setProductJSON(ProductJSON productJSON) {
		this.productJSON = productJSON;
	}

	public List<News> getNewsList() {
		return newsList;
	}

	public void setNewsList(List<News> newsList) {
		this.newsList = newsList;
	}

	public ProductJSON getRecommendJSON() {
		return recommendJSON;
	}

	public void setRecommendJSON(ProductJSON recommendJSON) {
		this.recommendJSON = recommendJSON;
	}
	
	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public List<ProductJSON> getProductList() {
		return productList;
	}

	public void setProductList(List<ProductJSON> productList) {
		this.productList = productList;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public List<BuyHistoryJSON> getBuyHistoryJSONList() {
		return buyHistoryJSONList;
	}

	public void setBuyHistoryJSONList(List<BuyHistoryJSON> buyHistoryJSONList) {
		this.buyHistoryJSONList = buyHistoryJSONList;
	}

	public BuyHistoryJSON getBuyHistoryJSON() {
		return buyHistoryJSON;
	}

	public void setBuyHistoryJSON(BuyHistoryJSON buyHistoryJSON) {
		this.buyHistoryJSON = buyHistoryJSON;
	}

	public List<IndexImg> getIndexImgList() {
		return indexImgList;
	}

	public void setIndexImgList(List<IndexImg> indexImgList) {
		this.indexImgList = indexImgList;
	}

	public List<ProductJSON> getNewProductList() {
		return newProductList;
	}

	public void setNewProductList(List<ProductJSON> newProductList) {
		this.newProductList = newProductList;
	}

	public void setIndexPopProductList(List<ProductJSON> indexPopProductList) {
		this.indexPopProductList = indexPopProductList;
	}

	public UserJSON getUserJSON() {
		return userJSON;
	}

	public void setUserJSON(UserJSON userJSON) {
		this.userJSON = userJSON;
	}


}
