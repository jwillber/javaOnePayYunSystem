package com.eypg.admin.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.struts2.ServletActionContext;
import org.jdesktop.swingx.util.OS;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eypg.action.IndexAction;
import com.eypg.action.RegisterAction;
import com.eypg.dao.Pagination;
import com.eypg.pojo.Applymention;
import com.eypg.pojo.Cardpassword;
import com.eypg.pojo.Consumerdetail;
import com.eypg.pojo.Consumetable;
import com.eypg.pojo.IndexImg;
import com.eypg.pojo.Latestlottery;
import com.eypg.pojo.News;
import com.eypg.pojo.OrderBean;
import com.eypg.pojo.Orderdetail;
import com.eypg.pojo.Orderdetailaddress;
import com.eypg.pojo.Product;
import com.eypg.pojo.ProductJSON;
import com.eypg.pojo.Productimage;
import com.eypg.pojo.Producttype;
import com.eypg.pojo.Recommend;
import com.eypg.pojo.ShareJSON;
import com.eypg.pojo.Shareimage;
import com.eypg.pojo.Shareinfo;
import com.eypg.pojo.Spellbuyproduct;
import com.eypg.pojo.Suggestion;
import com.eypg.pojo.SysConfigure;
import com.eypg.pojo.SysInfoBean;
import com.eypg.pojo.User;
import com.eypg.service.ApplymentionService;
import com.eypg.service.CardpasswordService;
import com.eypg.service.ConsumerdetailService;
import com.eypg.service.ConsumetableService;
import com.eypg.service.LatestlotteryService;
import com.eypg.service.NewsService;
import com.eypg.service.OrderdetailaddressService;
import com.eypg.service.ProductImageService;
import com.eypg.service.ProductService;
import com.eypg.service.ProducttypeService;
import com.eypg.service.RecommendService;
import com.eypg.service.ShareService;
import com.eypg.service.SpellbuyproductService;
import com.eypg.service.SpellbuyrecordService;
import com.eypg.service.SysConfigureService;
import com.eypg.service.UserService;
import com.eypg.util.ApplicationListenerImpl;
import com.eypg.util.Base64;
import com.eypg.util.CutImages;
import com.eypg.util.DateUtil;
import com.eypg.util.EmailUtil;
import com.eypg.util.MD5Util;
import com.eypg.util.PaginationUtil;
import com.eypg.util.Sampler;
import com.eypg.util.StringUtil;
import com.eypg.util.Struts2Utils;
import com.eypg.util.UploadImagesUtil;
import com.opensymphony.xwork2.ActionSupport;
import com.shcm.bean.SendResultBean;

@SuppressWarnings("unused")
@Component("AdminIndexAction")
public class AdminIndexAction extends ActionSupport{

	private static final long serialVersionUID = 5983843029308947750L;
	
	@Autowired
	private UserService userService;
	@Autowired
	private ProductService productService;
	@Autowired
	private SpellbuyproductService spellbuyproductService;
	@Autowired
	private ProducttypeService producttypeService;
	@Autowired
	private SpellbuyrecordService spellbuyrecordService;
	@Autowired
	private ProductImageService productImageService;
	@Autowired
	private NewsService newsService;
	@Autowired
	private ShareService shareService;
	@Autowired
	private LatestlotteryService latestlotteryService;
	@Autowired
	private ConsumerdetailService consumerdetailService;
	@Autowired
	private ConsumetableService consumetableService;
	@Autowired
	private CardpasswordService cardpasswordService;
	@Autowired
	private RecommendService recommendService;
	@Autowired
	private ApplymentionService applymentionService;
	@Autowired
	private OrderdetailaddressService orderdetailaddressService;
	@Autowired
	private SysConfigureService sysConfigureService;
	
	private Product product;
	private Spellbuyproduct spellbuyproduct;
	private Productimage productimage;
	private Producttype producttype; 
	private News news;
	private User user;
	private Shareinfo shareinfo;
	private Shareimage shareimage;
	private List<Producttype> productTypeList;
	private List<Producttype> productBrandList;
	private List<Product> productList;
	private List<Productimage> productimageList;
	private List<Shareimage> shareimageList;
	private List<News> newsList;
	private List<User> userList;
	private List<Latestlottery> latestlotteryList;
	private List<ShareJSON> ShareJSONList;
	private ShareJSON shareJSON;
	private Latestlottery latestlottery;
	private List<OrderBean> orderBeanList;
	private List<Cardpassword> cardpasswordList;
	private List<Applymention> applymentionList;
	private List<Orderdetailaddress> orderdetailaddressList;
	private SysConfigure sysConfigure;
	private List<IndexImg> indexImgList;
	private IndexImg indexImg;
	private List<Suggestion> suggestionList;
	private List<Orderdetail> orderdetailList;
	private Orderdetailaddress orderdetailaddress;
	
	private File myFile;
	private String myFileFileName;
	private String myFileContentType;
	private String imageFileName;
	private static final int BUFFER_SIZE = 100 * 1024 ;
	
	private int pageNo;
	private String pages;
	private String pageString;
	private int pageSize = 20;
	private int pageCount;
	private int resultCount;
	private String startDate;
	private String endDate;
	
	private String id;
	private String keyword;
	private String userName;
	private String pwd;
	private String message;
	private String userId;
	private String announcedTime;
	
	private String typeId;
	private String tId;
	private String typeName;
	private List<ProductJSON> productJSONList;
	private ProductJSON productJSON;
	
	private List<File> Filedata; // 默认的客户端文件对象,命名不符合java规范fileData  
    private List<String> FiledataFileName; // 客户端文件名  
    private List<String> imageContentType; // 客户端文件名类型  
    
    private String channelUrl;
    private String productType;
    private DefaultHttpClient httpClient;
	private HttpGet httpGet;
	private HttpPost httpPost;
	private String backUrl;
	private SysInfoBean sysInfoBean;
	
	//文件上传
	private static void copy(File src, File dst) {
        try {
           InputStream in = null ;
           OutputStream out = null ;
            try {                
               in = new BufferedInputStream( new FileInputStream(src), BUFFER_SIZE);
               out = new BufferedOutputStream( new FileOutputStream(dst), BUFFER_SIZE);
                byte [] buffer = new byte [BUFFER_SIZE];
                while (in.read(buffer) > 0 ) {
                   out.write(buffer);
               }
           } finally {
                if ( null != in) {
                   in.close();
               }
                if ( null != out) {
                   out.close();
               }
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
   }
	
	public String admin(){
		
		return "index";
	}

	public String index(){
		if(Struts2Utils.getSession().getAttribute("admin")!=null){
			if(pageNo==0){
				pageNo = 1;
			}
			if(pages!=null){
				pageNo = Integer.parseInt(pages.split("_")[1]);
			}
			if(id.equals("hot20")){
				Pagination hotPage = spellbuyrecordService.ProductByTypeIdList(typeId,"", "hot", pageNo, pageSize);
				List<Object[]> HotList = (List<Object[]>) hotPage.getList();
				productJSONList = new ArrayList<ProductJSON>();
				for (int i = 0; i < HotList.size(); i++) {
					productJSON = new ProductJSON(); 
					product = (Product) HotList.get(i)[0];
					spellbuyproduct = (Spellbuyproduct) HotList.get(i)[1];
					productJSON.setCurrentBuyCount(spellbuyproduct.getSpellbuyCount());
					productJSON.setHeadImage(product.getHeadImage());
					productJSON.setProductId(spellbuyproduct.getSpellbuyProductId());
					productJSON.setProductName(product.getProductName());
					productJSON.setProductPrice(spellbuyproduct.getSpellbuyPrice());
					productJSON.setSinglePrice(product.getSinglePrice());
					productJSON.setProductTitle(product.getProductTitle());
					productJSON.setProductPeriod(spellbuyproduct.getProductPeriod());
					productJSONList.add(productJSON);
				}
				resultCount = hotPage.getResultCount();
				if(typeId!=null && !typeId.equals("")){
					pageString = PaginationUtil.getPaginationHtml(resultCount, pageSize, pageNo, 2, 5, "/admin_back/index.action?id="+id+"&type="+typeId+"&pageNo=");
					pageString = pageString.replace(".html", "");
				}else{
					pageString = PaginationUtil.getPaginationHtml(resultCount, pageSize, pageNo, 2, 5, "/admin_back/index.action?id="+id+"&pageNo=");
					pageString = pageString.replace(".html", "");
				}
				return "spellbuyProductList";
			}else if(id.equals("date20")){
				Pagination datePage = spellbuyrecordService.ProductByTypeIdList(typeId,"", "date", pageNo, pageSize);
				List<Object[]> dateList = (List<Object[]>) datePage.getList();
				productJSONList = new ArrayList<ProductJSON>();
				for (int i = 0; i < dateList.size(); i++) {
					productJSON = new ProductJSON(); 
					product = (Product) dateList.get(i)[0];
					spellbuyproduct = (Spellbuyproduct) dateList.get(i)[1];
					productJSON.setCurrentBuyCount(spellbuyproduct.getSpellbuyCount());
					productJSON.setHeadImage(product.getHeadImage());
					productJSON.setProductId(spellbuyproduct.getSpellbuyProductId());
					productJSON.setProductName(product.getProductName());
					productJSON.setProductPrice(spellbuyproduct.getSpellbuyPrice());
					productJSON.setSinglePrice(product.getSinglePrice());
					productJSON.setProductTitle(product.getProductTitle());
					productJSON.setProductPeriod(spellbuyproduct.getProductPeriod());
					productJSONList.add(productJSON);
				}
				resultCount = datePage.getResultCount();
				if(typeId!=null && !typeId.equals("")){
					pageString = PaginationUtil.getPaginationHtml(resultCount, pageSize, pageNo, 2, 5, "/admin_back/index.action?id="+id+"&type="+typeId+"&pageNo=");
					pageString = pageString.replace(".html", "");
				}else{
					pageString = PaginationUtil.getPaginationHtml(resultCount, pageSize, pageNo, 2, 5, "/admin_back/index.action?id="+id+"&pageNo=");
					pageString = pageString.replace(".html", "");
				}
				return "spellbuyProductList";
			}else if(id.equals("price20")){
				Pagination pricePage = spellbuyrecordService.ProductByTypeIdList(typeId,"", "price", pageNo, pageSize);
				List<Object[]> priceList = (List<Object[]>) pricePage.getList();
				productJSONList = new ArrayList<ProductJSON>();
				for (int i = 0; i < priceList.size(); i++) {
					productJSON = new ProductJSON(); 
					product = (Product) priceList.get(i)[0];
					spellbuyproduct = (Spellbuyproduct) priceList.get(i)[1];
					productJSON.setCurrentBuyCount(spellbuyproduct.getSpellbuyCount());
					productJSON.setHeadImage(product.getHeadImage());
					productJSON.setProductId(spellbuyproduct.getSpellbuyProductId());
					productJSON.setProductName(product.getProductName());
					productJSON.setProductPrice(spellbuyproduct.getSpellbuyPrice());
					productJSON.setSinglePrice(product.getSinglePrice());
					productJSON.setProductTitle(product.getProductTitle());
					productJSON.setProductPeriod(spellbuyproduct.getProductPeriod());
					productJSONList.add(productJSON);
				}
				resultCount = pricePage.getResultCount();
				if(typeId!=null && !typeId.equals("")){
					pageString = PaginationUtil.getPaginationHtml(resultCount, pageSize, pageNo, 2, 5, "/admin_back/index.action?id="+id+"&type="+typeId+"&pageNo=");
					pageString = pageString.replace(".html", "");
				}else{
					pageString = PaginationUtil.getPaginationHtml(resultCount, pageSize, pageNo, 2, 5, "/admin_back/index.action?id="+id+"&pageNo=");
					pageString = pageString.replace(".html", "");
				}
				return "spellbuyProductList";
			}else if(id.equals("priceAsc20")){
				Pagination pricePage = spellbuyrecordService.ProductByTypeIdList(typeId,"", "priceAsc", pageNo, pageSize);
				List<Object[]> priceList = (List<Object[]>) pricePage.getList();
				productJSONList = new ArrayList<ProductJSON>();
				for (int i = 0; i < priceList.size(); i++) {
					productJSON = new ProductJSON(); 
					product = (Product) priceList.get(i)[0];
					spellbuyproduct = (Spellbuyproduct) priceList.get(i)[1];
					productJSON.setCurrentBuyCount(spellbuyproduct.getSpellbuyCount());
					productJSON.setHeadImage(product.getHeadImage());
					productJSON.setProductId(spellbuyproduct.getSpellbuyProductId());
					productJSON.setProductName(product.getProductName());
					productJSON.setProductPrice(spellbuyproduct.getSpellbuyPrice());
					productJSON.setSinglePrice(product.getSinglePrice());
					productJSON.setProductTitle(product.getProductTitle());
					productJSON.setProductPeriod(spellbuyproduct.getProductPeriod());
					productJSONList.add(productJSON);
				}
				resultCount = pricePage.getResultCount();
				if(typeId!=null && !typeId.equals("")){
					pageString = PaginationUtil.getPaginationHtml(resultCount, pageSize, pageNo, 2, 5, "/admin_back/index.action?id="+id+"&type="+typeId+"&pageNo=");
					pageString = pageString.replace(".html", "");
				}else{
					pageString = PaginationUtil.getPaginationHtml(resultCount, pageSize, pageNo, 2, 5, "/admin_back/index.action?id="+id+"&pageNo=");
					pageString = pageString.replace(".html", "");
				}
				return "spellbuyProductList";
			}
		}
		return "index_index";
	}
	
	public String upSpellbuyproduct(){
		if(Struts2Utils.getSession().getAttribute("admin")!=null){
			if(((User)Struts2Utils.getSession().getAttribute("admin")).getUserName().equals("admin")){
				product = productService.findById(id);
				spellbuyproduct = spellbuyproductService.findSpellbuyproductByStatus(product.getProductId());
				if(spellbuyproduct==null){
					int productPeriod = 0;
					Spellbuyproduct spellbuyproduct =  spellbuyproductService.findSpellbuyproductLastPeriod(product.getProductId());
					if(spellbuyproduct!=null){
						productPeriod = spellbuyproduct.getProductPeriod();
						productPeriod++;
					}else{
						productPeriod = 1;
					}
					if(productPeriod > 0){
						spellbuyproduct = new Spellbuyproduct();
						spellbuyproduct.setFkProductId(product.getProductId());
						spellbuyproduct.setProductPeriod(productPeriod);
						spellbuyproduct.setSpellbuyCount(0);
						spellbuyproduct.setSpellbuyEndDate(DateUtil.DateTimeToStr(new Date()));
						spellbuyproduct.setSpellbuyPrice(product.getProductPrice());
						spellbuyproduct.setSpSinglePrice(product.getSinglePrice());
						spellbuyproduct.setSpellbuyStartDate(DateUtil.DateTimeToStr(new Date()));
						spellbuyproduct.setSpStatus(0);
						if(product.getAttribute71().equals("hot")){
							spellbuyproduct.setSpellbuyType(8);
						}else{
							spellbuyproduct.setSpellbuyType(0);
						}
						spellbuyproductService.add(spellbuyproduct);
						product.setAttribute71(String.valueOf((productPeriod+1)));
					}
					
				}
				product.setStatus(1);
				productService.add(product);
				Struts2Utils.renderText("success");
			}else{
				Struts2Utils.renderText("test");
			}
		}
		return null;
	}
	
	/**
	 * 下架商品
	 */
	public void downSpellbuyproduct(){
		if(Struts2Utils.getSession().getAttribute("admin")!=null){
			if(((User)Struts2Utils.getSession().getAttribute("admin")).getUserName().equals("admin")){
				product = productService.findById(id);
				product.setStatus(2);
				productService.add(product);
				Struts2Utils.renderText("success");
			}else{
				Struts2Utils.renderText("test");
			}
		}
	}
	
	public void hotProduct(){
		if(Struts2Utils.getSession().getAttribute("admin")!=null){
			if(((User)Struts2Utils.getSession().getAttribute("admin")).getUserName().equals("admin")){
				try {
					product = productService.findById(id);
					product.setAttribute71("hot");
					productService.add(product);
					List<Spellbuyproduct> list = spellbuyproductService.findSpellbuyproductByProductIdIsStatus(Integer.parseInt(id));
					if(list!=null && list.size()>0){
						spellbuyproduct = list.get(0);
						spellbuyproduct.setSpellbuyType(8);
						spellbuyproductService.add(spellbuyproduct);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				Struts2Utils.renderText("success");
			}else{
				Struts2Utils.renderText("test");
			}
		}
	}
	
	public void downHotProduct(){
		if(Struts2Utils.getSession().getAttribute("admin")!=null){
			if(((User)Struts2Utils.getSession().getAttribute("admin")).getUserName().equals("admin")){
				try {
					product = productService.findById(id);
					product.setAttribute71("0");
					productService.add(product);
					List<Spellbuyproduct> list = spellbuyproductService.findSpellbuyproductByProductIdIsStatus(Integer.parseInt(id));
					if(list!=null && list.size()>0){
						spellbuyproduct = list.get(0);
						spellbuyproduct.setSpellbuyType(0);
						spellbuyproductService.add(spellbuyproduct);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				Struts2Utils.renderText("success");
			}else{
				Struts2Utils.renderText("test");
			}
		}
	}
	
	public String announcedProduct(){
		if(pageNo==0){
			pageNo = 1;
		}
		if(Struts2Utils.getSession().getAttribute("admin")!=null){
			Pagination hotPage = spellbuyproductService.announcedProduct(pageNo, pageSize);
			List<Object[]> HotList = (List<Object[]>) hotPage.getList();
			productJSONList = new ArrayList<ProductJSON>();
			for (int i = 0; i < HotList.size(); i++) {
				productJSON = new ProductJSON(); 
				product = (Product) HotList.get(i)[0];
				spellbuyproduct = (Spellbuyproduct) HotList.get(i)[1];
				productJSON.setCurrentBuyCount(product.getProductId()); //商品ID，方便上架新一期商品
				productJSON.setHeadImage(product.getHeadImage());
				productJSON.setProductId(spellbuyproduct.getSpellbuyProductId());
				productJSON.setProductName(product.getProductName());
				productJSON.setProductPrice(product.getProductPrice());
				productJSON.setSinglePrice(product.getSinglePrice());
				productJSON.setProductTitle(product.getProductTitle());
				productJSON.setProductPeriod(spellbuyproduct.getProductPeriod());
				productJSONList.add(productJSON);
			}
			resultCount = hotPage.getResultCount();
			if(typeId!=null && !typeId.equals("")){
				pageString = PaginationUtil.getPaginationHtml(resultCount, pageSize, pageNo, 2, 5, "/admin_back/announcedProduct.action?pageNo=");
				pageString = pageString.replace(".html", "");
			}else{
				pageString = PaginationUtil.getPaginationHtml(resultCount, pageSize, pageNo, 2, 5, "/admin_back/announcedProduct.action?pageNo=");
				pageString = pageString.replace(".html", "");
			}
			return "announcedProductList";
		}
		return "index_index";
	}
	
	public String login(){
		if(!ApplicationListenerImpl.sysConfigureAuth){
			Struts2Utils.renderHtml("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">" +
					"<html><head><title>授权过期 1元拍购开发中心</title><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /></head>" +
					"<body><div align=\"center\" style=\"color: #f60;font-family: arial,微软雅黑;font-size: 18px;margin-top: 180px;\">该系统授权已过期，请联系管理员重新授权！谢谢合作。<a href=\"http://www.51openos.com\">www.51openos.com</a></div></body></html>");
			return null;
		}
		if(StringUtil.isBlank(userName)){
			Struts2Utils.render("text/html", "<script>alert(\"用户名或密码错误！\");window.location.href=\"/admin_back/admin.html\";</script>","encoding:UTF-8");
		}
		user = userService.userByName(userName);
		//只有网站工作人员能能登录后台.
		if(user!=null && user.getUserType()!=null && "1".equals(user.getUserType())){
			String password = user.getUserPwd();
			if(StringUtil.isNotBlank(password) && password.equals(pwd)){
				Struts2Utils.getSession().setAttribute("admin", user);
				String ip = Struts2Utils.getRequest().getHeader("X-Real-IP");
				if(ip==null){
					ip = Struts2Utils.getRequest().getRemoteAddr();
				}
				String date = DateUtil.DateTimeToStr(new Date());
				user.setIpAddress(ip);
				String ipLocation = RegisterAction.seeker.getAddress(ip);
				user.setIpLocation(ipLocation);
				user.setOldIpAddress(user.getIpAddress()+"("+user.getIpLocation()+")");
				user.setOldDate(user.getNewDate());
				user.setNewDate(date);
				userService.add(user);
				Struts2Utils.render("text/html", "<script>window.location.href=\"/admin_back/main.action\";</script>","encoding:UTF-8");
				
			}else{
				Struts2Utils.render("text/html", "<script>alert(\"用户名或密码错误！\");window.location.href=\"/admin_back/admin.html\";</script>","encoding:UTF-8");
			}
		}else{
			Struts2Utils.render("text/html", "<script>alert(\"用户名或密码错误！\");window.location.href=\"/admin_back/admin.html\";</script>","encoding:UTF-8");
		}
		return null;
	}
	
	public String logOut(){
		Struts2Utils.getSession().removeAttribute("admin");
		return "index";
	}
	
	public String main(){
		if(Struts2Utils.getSession().getAttribute("admin")!=null){
			return "main";
		}
		return "index_index";
	}
	
	public void numberCount(){
		if(Struts2Utils.getSession().getAttribute("admin")!=null){
			Pagination page = latestlotteryService.LatestAnnounced(pageNo, pageSize);
			Pagination hotPage = spellbuyrecordService.ProductByTypeIdList(typeId,"", "hot", pageNo, pageSize);
			Pagination page2 = shareService.loadPageShare("new20",pageNo, pageSize);
			int userCount = userService.getCountUser();
			Long buyCount = Long.parseLong(spellbuyrecordService.getAllByCount().toString()); 
			int lotteryCount =  page.getResultCount();
			int productCount = hotPage.getResultCount();
			int shareCount = page2.getResultCount();
			message = userCount+"_"+buyCount+"_"+productCount+"_"+lotteryCount+"_"+shareCount;
			Struts2Utils.renderText(message);
		}
	}
	
	public String toAddProduct(){
		if(Struts2Utils.getSession().getAttribute("admin")!=null){
			productTypeList = producttypeService.queryAllProductType();
			productBrandList = producttypeService.listByBrand(null);
			return "addProduct";
		}
		return "index_index";
	}
	
	public String addProduct() throws Exception{
		if(Struts2Utils.getSession().getAttribute("admin")!=null){
			String productImgPath = "/productImg/show";
			productService.add(product);
			try {
				if(myFile!=null){
					myFileFileName = myFileFileName.substring(myFileFileName.lastIndexOf("."), myFileFileName.length());
					imageFileName = new Date().getTime()+ myFileFileName;
				       File imageFile = new File(UploadImagesUtil.getFolder(ServletActionContext.getServletContext().getRealPath(productImgPath)+"/", String.valueOf(product.getProductId()))+"/" + imageFileName);
				       copy(myFile, imageFile);
				       CutImages.equimultipleConvert(200, 200, imageFile, imageFile);
				       product.setHeadImage(productImgPath+"/"+String.valueOf(product.getProductId())+"/" + imageFileName);
			       }
			} catch (Exception e) {
				e.printStackTrace();
			}
	         product.setProductRealPrice(String.valueOf(product.getProductPrice()));
	         product.setStatus(0);
	         product.setAttribute71(String.valueOf(1));
	       productService.add(product);
			return "success";
		}
		return "index_index";
	}
	
	public String toUpdate(){
		if(Struts2Utils.getSession().getAttribute("admin")!=null){
			product = productService.findById(id);
			productTypeList = producttypeService.queryAllProductType();
			productBrandList = producttypeService.listByBrand(null);
			String queryString = Struts2Utils.getRequest().getQueryString();
			backUrl = queryString.substring(queryString.indexOf("backUrl=")+8, queryString.length());
			System.err.println(backUrl);
			return "addProduct";
		}
		return "index_index";
	}
	
	public void update() throws Exception{
		HttpServletRequest request = Struts2Utils.getRequest();
		HttpServletResponse response = Struts2Utils.getResponse();
		if(Struts2Utils.getSession().getAttribute("admin")!=null){
			if(((User)Struts2Utils.getSession().getAttribute("admin")).getUserName().equals("admin")){
				String productImgPath = "/productImg/show";
				if(myFile!=null){
					myFileFileName = myFileFileName.substring(myFileFileName.lastIndexOf("."), myFileFileName.length());
					imageFileName = new Date().getTime()+ myFileFileName;
				       File imageFile = new File(UploadImagesUtil.getFolder(ServletActionContext.getServletContext().getRealPath(productImgPath)+"/", String.valueOf(product.getProductId()))+"/" + imageFileName);
				       copy(myFile, imageFile);
				       CutImages.equimultipleConvert(200, 200, imageFile, imageFile);
				       product.setHeadImage(productImgPath+"/"+String.valueOf(product.getProductId())+"/" + imageFileName);
		        }
				spellbuyproduct = spellbuyproductService.findSpellbuyproductByStatus(product.getProductId());
				if(spellbuyproduct!=null){
					spellbuyproduct.setSpSinglePrice((product.getSinglePrice()));
					spellbuyproductService.add(spellbuyproduct);
				}
				productService.add(product);
				Struts2Utils.render("text/html", "<script>window.location.href=\""+backUrl+"\";</script>","encoding:UTF-8");
			}else{
				Struts2Utils.render("text/html", "<script>alert(\"测试用户无权修改！，请联系管理员！\");history.go(-1);</script>","encoding:UTF-8");
			}

//			return "success";
		}
//		return "index_index";
	}
	
	public void deleteProduct(){
		if(Struts2Utils.getSession().getAttribute("admin")!=null){
			if(((User)Struts2Utils.getSession().getAttribute("admin")).getUserName().equals("admin")){
				try {
					productService.delete(Integer.parseInt(id));
				} catch (Exception e) {
					e.printStackTrace();
				}
				Struts2Utils.render("text/html", "<script>window.location.href=\""+backUrl+"\";</script>","encoding:UTF-8");
			}else{
				Struts2Utils.render("text/html", "<script>alert(\"测试用户无权修改！，请联系管理员！\");history.go(-1);</script>","encoding:UTF-8");
			}
		}
	}
	
	public String toAddProductType(){
		if(Struts2Utils.getSession().getAttribute("admin")!=null){
			productTypeList = producttypeService.queryAllProductType();
			if(id!=null){
				producttype = producttypeService.findById(id);
			}
			return "addProductType";
		}
		return "index_index";
	}
	
	public String addProductType(){
		if(Struts2Utils.getSession().getAttribute("admin")!=null){
			if(producttype.getFtypeId()==null){
				producttype.setFtypeId("1000");
			}
			if(((User)Struts2Utils.getSession().getAttribute("admin")).getUserName().equals("admin")){
				producttype.setStypeId("0");
				producttype.setAttribute70("type");
				if(producttype.getTypeId()==null){
					if(producttypeService.findTypeByName(producttype.getTypeName())!=null){
						Struts2Utils.render("text/html", "<script>alert(\"该分类名称已经存在！\");history.go(-2);</script>","encoding:UTF-8");
						return null;
					}else{
						producttypeService.add(producttype);
					}
				}else{
					producttypeService.add(producttype);
				}
				return "addProductTypeSuccess";
			}else{
				Struts2Utils.render("text/html", "<script>alert(\"测试用户无权修改！，请联系管理员！\");history.go(-1);</script>","encoding:UTF-8");
				return null;
			}

		}
		return "index_index";
	}
	
	/**
	 * 商品类型列表
	 * @return
	 */
	public String productTypeList(){
		if(Struts2Utils.getSession().getAttribute("admin")!=null){
			productTypeList = producttypeService.queryAllProductType();
			return "productTypeList";
		}
		return "index_index";
	}
	
	/**
	 * 商品品牌列表
	 * @return
	 */
	public String productBrandList(){
		if(Struts2Utils.getSession().getAttribute("admin")!=null){
			productTypeList = producttypeService.queryAllProductType();
			productBrandList = producttypeService.listByBrand(null);
			return "productBrandList";
		}
		return "index_index";
	}
	
	public String toAddProductBrand(){
		if(Struts2Utils.getSession().getAttribute("admin")!=null){
			productTypeList = producttypeService.queryAllProductType();
			if(id!=null){
				producttype = producttypeService.findBrandById(id);
			}
			return "addProductBrand";
		}
		return "index_index";
	}
	
	public String addProductBrand(){
		if(Struts2Utils.getSession().getAttribute("admin")!=null){
			if(((User)Struts2Utils.getSession().getAttribute("admin")).getUserName().equals("admin")){
				if(producttype.getFtypeId()==null){
					producttype.setFtypeId("1000");
				}
				producttype.setStypeId("0");
				producttype.setAttribute70("brand");
				if(producttype.getTypeId()==null){
					if(producttypeService.findBrandByName(producttype.getTypeName())!=null){
						Struts2Utils.render("text/html", "<script>alert(\"该品牌名称已经存在！\");history.go(-2);</script>","encoding:UTF-8");
						return null;
					}else{
						producttypeService.add(producttype);
					}
				}else{
					producttypeService.add(producttype);
				}
				return "addProductBrandSuccess";
			}else{
				Struts2Utils.render("text/html", "<script>alert(\"测试用户无权修改！，请联系管理员！\");history.go(-1);</script>","encoding:UTF-8");
				return null;
			}
			
		}
		return "index_index";
	}
	
	public String productList(){
		if(Struts2Utils.getSession().getAttribute("admin")!=null){
			if(pageNo==0){
				pageNo = 1;
			}
			if(pages!=null){
				pageNo = Integer.parseInt(pages.split("_")[1]);
			}
			if(typeId==null){
				typeId = "";
			}
			if(keyword==null){
				keyword = "";
			}
			productTypeList = producttypeService.queryAllProductType();
			productBrandList = producttypeService.listByBrand(null);
			Pagination datePage = productService.searchProduct(typeId,keyword, pageNo, pageSize);
			productList = (List<Product>) datePage.getList();
			resultCount = datePage.getResultCount();
			pageString = PaginationUtil.getPaginationHtml(resultCount, pageSize, pageNo, 2, 5, "/admin_back/productList.action?typeId="+typeId+"&keyword="+keyword+"&pageNo=");
			pageString = pageString.replace(".html", "");
			return "productList";
		}
		return "index_index";
	}
	
	public String toAddProductImg(){
		if(Struts2Utils.getSession().getAttribute("admin")!=null){
			productimageList =  productImageService.findByProductId(id, "show");
			return "addProductImg";
		}
		return "index_index";
	}
	
	public String addProductImg() throws Exception{
			if(Struts2Utils.getSession().getAttribute("admin")!=null){
				if(((User)Struts2Utils.getSession().getAttribute("admin")).getUserName().equals("admin")){
					String productImgPath = "/productImg/imagezoom";
					long fileDateName = 0;
					String imagePath = null;
					if (Filedata == null || Filedata.size() == 0){  
			            return null;  
			        }  
					 for (int i = 0; i < Filedata.size(); ++i){
						 try {
							 myFile = Filedata.get(i);
							 	if(myFile!=null){
									myFileFileName = FiledataFileName.get(i).substring(FiledataFileName.get(i).lastIndexOf("."), FiledataFileName.get(i).length());
									fileDateName = new Date().getTime();
									imageFileName = fileDateName + myFileFileName;
									imagePath = UploadImagesUtil.getFolder(ServletActionContext.getServletContext().getRealPath(productImgPath)+"/", id)+"/";
								       File imageFile = new File(imagePath + imageFileName);
								       copy(myFile, imageFile);
								       CutImages.equimultipleConvert(310, 310, imageFile, new File(imagePath+fileDateName+"_mid"+myFileFileName));
								       CutImages.equimultipleConvert(40, 40, imageFile, new File(imagePath+fileDateName+"_small"+myFileFileName));
								       productimage = new Productimage();
								       productimage.setPiProductId(Integer.parseInt(id));
								       productimage.setImage(String.valueOf(fileDateName));
								       productimage.setImageType(myFileFileName);
								       productimage.setAttribute75("show");
								       productImageService.add(productimage);
						       }
						} catch (Exception e) {
							e.printStackTrace();
						}
			        }
					 Struts2Utils.render("text/html", "<script>alert(\"上传成功！\");history.go(-2);</script>","encoding:UTF-8");
				}else{
					Struts2Utils.render("text/html", "<script>alert(\"测试用户无权修改！，请联系管理员！\");history.go(-1);</script>","encoding:UTF-8");
				}
			}
		return null;
	}
	
	public void doDeleteProductImg(){
		if(Struts2Utils.getSession().getAttribute("admin")!=null){
			if(((User)Struts2Utils.getSession().getAttribute("admin")).getUserName().equals("admin")){
				try {
					productImageService.delete(Integer.parseInt(id));
				} catch (Exception e) {
					e.printStackTrace();
				}
				Struts2Utils.render("text/html", "<script>alert(\"删除成功！\");history.go(-2);</script>","encoding:UTF-8");
			}else{
				Struts2Utils.render("text/html", "<script>alert(\"测试用户无权修改！，请联系管理员！\");history.go(-1);</script>","encoding:UTF-8");
			}
		}
		
	}
	
	public String toAddSpellbuyProduct(){
		if(Struts2Utils.getSession().getAttribute("admin")!=null){
			return "addSpellbuyProduct";
		}
		return "index_index";
	}
	
	public String addSpellbuyProduct(){
		if(Struts2Utils.getSession().getAttribute("admin")!=null){
			return "spellbuyProductList";
		}
		return "index_index";
	}
	
	
	public String uploadImages() throws Exception{
//		if(Struts2Utils.getSession().getAttribute("admin")!=null){
//			if(((User)Struts2Utils.getSession().getAttribute("admin")).getUserName().equals("admin")){
				String productImgPath = "/productImg/detail";
				long fileDateName = 0;
				String imagePath = null;
				
				if (Filedata == null || Filedata.size() == 0){  
		            return null;
		        }  
				 for (int i = 0; i < Filedata.size(); ++i){
					 	myFile = Filedata.get(i);
					 	if(myFile!=null){
							myFileFileName = FiledataFileName.get(i).substring(FiledataFileName.get(i).lastIndexOf("."), FiledataFileName.get(i).length()).toLowerCase();
							fileDateName = new Date().getTime();
							imageFileName = fileDateName + myFileFileName;
							imagePath = UploadImagesUtil.getFolder(ServletActionContext.getServletContext().getRealPath(productImgPath)+"/",DateUtil.dateToStr(new Date()))+"/";
						       File imageFile = new File(imagePath + imageFileName);
						       copy(myFile, imageFile);
				       }
		        }
//			}else{
//				Struts2Utils.render("text/html", "<script>alert(\"测试用户无权修改！，请联系管理员！\");history.go(-1);</script>","encoding:UTF-8");
//			}

//		}
		 return null;
	}
	
	public String crawl(){
		if(Struts2Utils.getSession().getAttribute("admin")!=null){
			productTypeList = producttypeService.queryAllProductType();
			return "crawl";
		}
		return "index_index";
	}
	
	public String doCrawl() throws Exception{
		if(pages!=null && !pages.equals("")){
			String url = "";
			int page = Integer.parseInt(pages);
			for (int i = 1; i < page; i++) {
				try {
//					http://www.360buy.com/products/652-653-655-0-0-0-0-0-0-0-1-1-1.html
//					http://www.360buy.com/products/670-677-688-0-0-0-0-0-0-0-1-1-2.html
//					http://www.360buy.com/products/737-794-798-0-0-0-0-0-0-0-1-1-1-1-72-33.html
//					http://www.360buy.com/products/737-794-798-0-0-0-0-0-0-0-1-1-1-1-72-0.html
					url = channelUrl.substring(0,channelUrl.lastIndexOf("0-1-1-")+6);
					url += i+ channelUrl.substring(channelUrl.lastIndexOf("0-1-1-")+7,channelUrl.length());
					System.err.println(url);
					 Document document = Jsoup.parse(new URL(url), 60000);
					 Elements elements = null;
					 elements = document.select("div.plist > ul > li");
					 if(elements.size()==0){
						 elements = document.select("div#plist > ul > li");
					 }
					 for (Element element : elements) {
						 try {
							 String productImg = "";
							 String productName = "";
							 String productTitle= "";
							 int productPrice = 0;
							 int productRealPrice = 0;
							 String productDetail = "";
							 String headImage = "";
							 String Attribute_71 = "1";
							 String strImg = element.select("div.p-img > a > img").first().attr("src");
							 if(strImg.indexOf("360buyimg.com")!=-1){
								 productImg = strImg;
							 }else{
							 String strImg2 = element.select("div.p-img > a > img").first().attr("src2");
							 	productImg = strImg2;
							 }
							 productImg = productImg.replace(".com/n2/", ".com/n1/");
							 productName = element.select("div.p-name > a").text();
							 product = productService.findProductByName(productName);
							 if(product==null){
								 product = new Product();
								 String proUrl = element.select("div.p-name > a").attr("href");
								 System.err.println(proUrl);
								 File myFile = saveURLFile(productImg);
								 
								 String myFileFileName = myFile.getName();
								 product.setAttribute71("1");
								 product.setProductType(Integer.parseInt(productType));
						         product.setProductDetail(proUrl);
						         product.setProductName(productName);
						         product.setProductPrice(0);
						         product.setProductRealPrice(String.valueOf(0));
						         product.setProductTitle(productName);
						         product.setStatus(0);
						         productService.add(product);
								 String productImgPath = "/productImg/show";
									if(myFile!=null){
										myFileFileName = myFileFileName.substring(myFileFileName.lastIndexOf("."), myFileFileName.length());
										String imageFileName = new Date().getTime()+ myFileFileName;
									       File imageFile = new File(UploadImagesUtil.getFolder(ServletActionContext.getServletContext().getRealPath(productImgPath)+"/", String.valueOf(product.getProductId()))+"/" + imageFileName);
									       copy(myFile, imageFile);
									       CutImages.equimultipleConvert(200, 200, imageFile, imageFile);
									       product.setHeadImage(productImgPath+"/"+String.valueOf(product.getProductId())+"/" + imageFileName);
								       }
							         
						         productService.add(product);
								 
								 Document document2 = Jsoup.parse(new URL(proUrl), 60000);
//								 System.err.println(document2);
								 Elements element2 = document2.select("div.spec-items > ul > li");
								 int count = 0;
								 for (Element element3 : element2) {
									 try {
										 count ++;
											String img = element3.select("img").attr("src");
//											http://img14.360buyimg.com/n0/g9/M03/0D/12/rBEHaVCXVAsIAAAAAADw0WaBZaIAACptQFzb24AAPDp968.jpg
//											http://img14.360buyimg.com/n5/g9/M03/0D/12/rBEHaVCXVAsIAAAAAADw0WaBZaIAACptQFzb24AAPDp968.jpg

											img = img.replace(".com/n5/", ".com/n0/");
											System.err.println(img);
											productimage = new Productimage();
											String productImgPath2 = "/productImg/imagezoom";
											long fileDateName = 0;
											String imagePath = null;
											File myFile2 = saveURLFile(img);
											String myFileFileName2 = myFile2.getName();
											if(myFile2!=null){
												myFileFileName2 = myFileFileName2.substring(myFileFileName2.lastIndexOf("."), myFileFileName2.length());
												fileDateName = new Date().getTime();
												String imageFileName = fileDateName + myFileFileName;
												imagePath = UploadImagesUtil.getFolder(ServletActionContext.getServletContext().getRealPath(productImgPath2)+"/", String.valueOf(product.getProductId()))+"/";
											       File imageFile = new File(imagePath + imageFileName);
											       copy(myFile, imageFile);
											       CutImages.equimultipleConvert(310, 310, imageFile, new File(imagePath+fileDateName+"_mid"+myFileFileName));
											       CutImages.equimultipleConvert(40, 40, imageFile, new File(imagePath+fileDateName+"_small"+myFileFileName));
											       productimage.setPiProductId(product.getProductId());
											       productimage.setImage(String.valueOf(fileDateName));
											       productimage.setImageType(myFileFileName);
											       productimage.setAttribute75("show");
											       productImageService.add(productimage);
									       }
											if(count ==6){
												break;
											}
									} catch (Exception e) {
										e.printStackTrace();
									}
								 }
							 }
						} catch (Exception e) {
							e.printStackTrace();
						}
					 }
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		System.err.println("结束");
		Struts2Utils.renderText("结束");
		return null;
	}
	
	public String doCrawlShare() throws Exception{
		if(pages!=null && !pages.equals("")){
			String url = "";
			int page = Integer.parseInt(pages);
			for (int i = 1; i < page; i++) {
				try {
//					http://www.360buy.com/products/652-653-655-0-0-0-0-0-0-0-1-1-1.html
//					http://www.360buy.com/products/670-677-688-0-0-0-0-0-0-0-1-1-2.html
//					http://www.360buy.com/products/737-794-798-0-0-0-0-0-0-0-1-1-1-1-72-33.html
//					http://www.360buy.com/products/737-794-798-0-0-0-0-0-0-0-1-1-1-1-72-0.html
					url = channelUrl.substring(0,channelUrl.lastIndexOf("0-1-1-")+6);
					url += i+ channelUrl.substring(channelUrl.lastIndexOf("0-1-1-")+7,channelUrl.length());
					System.err.println(url);
					 Document document = Jsoup.parse(new URL(url), 60000);
					 Elements elements = null;
					 elements = document.select("div.plist > ul > li");
					 if(elements.size()==0){
						 elements = document.select("div#plist > ul > li");
					 }
					 for (Element element : elements) {
						 try {
							 String productImg = "";
							 String productName = "";
							 String productTitle= "";
							 int productPrice = 0;
							 int productRealPrice = 0;
							 String productDetail = "";
							 String headImage = "";
							 String Attribute_71 = "1";
							 String strImg = element.select("div.p-img > a > img").first().attr("src");
							 if(strImg.indexOf("360buyimg.com")!=-1){
								 productImg = strImg;
							 }else{
							 String strImg2 = element.select("div.p-img > a > img").first().attr("src2");
							 	productImg = strImg2;
							 }
							 productImg = productImg.replace(".com/n2/", ".com/n1/");
							 productName = element.select("div.p-name > a").text();
							 product = productService.findProductByName(productName);
							 if(product==null){
								 product = new Product();
								 String proUrl = element.select("div.p-name > a").attr("href");
								 System.err.println(proUrl);
								 File myFile = saveURLFile(productImg);
								 
								 String myFileFileName = myFile.getName();
								 product.setAttribute71("1");
								 product.setProductType(Integer.parseInt(productType));
						         product.setProductDetail(proUrl);
						         product.setProductName(productName);
						         product.setProductPrice(0);
						         product.setProductRealPrice(String.valueOf(0));
						         product.setProductTitle(productName);
						         product.setStatus(0);
						         productService.add(product);
								 String productImgPath = "/productImg/show";
									if(myFile!=null){
										myFileFileName = myFileFileName.substring(myFileFileName.lastIndexOf("."), myFileFileName.length());
										String imageFileName = new Date().getTime()+ myFileFileName;
									       File imageFile = new File(UploadImagesUtil.getFolder(ServletActionContext.getServletContext().getRealPath(productImgPath)+"/", String.valueOf(product.getProductId()))+"/" + imageFileName);
									       copy(myFile, imageFile);
									       CutImages.equimultipleConvert(200, 200, imageFile, imageFile);
									       product.setHeadImage(productImgPath+"/"+String.valueOf(product.getProductId())+"/" + imageFileName);
								       }
							         
						         productService.add(product);
								 
								 Document document2 = Jsoup.parse(new URL(proUrl), 60000);
//								 System.err.println(document2);
								 Elements element2 = document2.select("div.spec-items > ul > li");
								 int count = 0;
								 for (Element element3 : element2) {
									 try {
										 count ++;
											String img = element3.select("img").attr("src");
//											http://img14.360buyimg.com/n0/g9/M03/0D/12/rBEHaVCXVAsIAAAAAADw0WaBZaIAACptQFzb24AAPDp968.jpg
//											http://img14.360buyimg.com/n5/g9/M03/0D/12/rBEHaVCXVAsIAAAAAADw0WaBZaIAACptQFzb24AAPDp968.jpg

											img = img.replace(".com/n5/", ".com/n0/");
											System.err.println(img);
											productimage = new Productimage();
											String productImgPath2 = "/productImg/imagezoom";
											long fileDateName = 0;
											String imagePath = null;
											File myFile2 = saveURLFile(img);
											String myFileFileName2 = myFile2.getName();
											if(myFile2!=null){
												myFileFileName2 = myFileFileName2.substring(myFileFileName2.lastIndexOf("."), myFileFileName2.length());
												fileDateName = new Date().getTime();
												String imageFileName = fileDateName + myFileFileName;
												imagePath = UploadImagesUtil.getFolder(ServletActionContext.getServletContext().getRealPath(productImgPath2)+"/", String.valueOf(product.getProductId()))+"/";
											       File imageFile = new File(imagePath + imageFileName);
											       copy(myFile, imageFile);
											       CutImages.equimultipleConvert(310, 310, imageFile, new File(imagePath+fileDateName+"_mid"+myFileFileName));
											       CutImages.equimultipleConvert(40, 40, imageFile, new File(imagePath+fileDateName+"_small"+myFileFileName));
											       productimage.setPiProductId(product.getProductId());
											       productimage.setImage(String.valueOf(fileDateName));
											       productimage.setImageType(myFileFileName);
											       productimage.setAttribute75("show");
											       productImageService.add(productimage);
									       }
											if(count ==6){
												break;
											}
									} catch (Exception e) {
										e.printStackTrace();
									}
								 }
							 }
						} catch (Exception e) {
							e.printStackTrace();
						}
					 }
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		System.err.println("结束");
		Struts2Utils.renderText("结束");
		return null;
	}
	
	public static File saveURLFile(String fileUrl) throws MalformedURLException{
		File file = null;
		try{
			URL url = new URL(fileUrl);
			String myFileFileName = fileUrl;
			URLConnection connect = url.openConnection();
			   connect.connect();
			   InputStream input = connect.getInputStream();
			   myFileFileName = myFileFileName.substring(myFileFileName.lastIndexOf("."), myFileFileName.length());
			   String image = ServletActionContext.getServletContext().getRealPath("/uploadImages")+"/"+"imageTemp"+myFileFileName;
			   file = new File(image);
			//创建一个输出流
			   OutputStream out = new BufferedOutputStream(new FileOutputStream(file));
			//每次的写入大小为：
			   int length=1024*1024;
			   byte[] a = new  byte[length];
			//如果大小大于length继续循环，否则值为-1
			   while((length=input.read(a))>0){
			    out.write(a, 0, length);
			   }
			//别忘了关闭流
			   input.close();
			   out.close();
		  } catch (Exception e) {
			  e.printStackTrace();
		  }
		  return file;
	}
	
	public String toAddNews(){
		if(Struts2Utils.getSession().getAttribute("admin")!=null){
			return "addNews";
		}
		return "index_index";
	}
	
	public String addNews(){
		if(Struts2Utils.getSession().getAttribute("admin")!=null){
			if(((User)Struts2Utils.getSession().getAttribute("admin")).getUserName().equals("admin")){
				if(news.getPostDate()==null || news.getPostDate().equals("")){
					news.setPostDate(DateUtil.DateToStr(new Date()));
				}
				newsService.add(news);
				return "successNews";
			}else{
				Struts2Utils.render("text/html", "<script>alert(\"测试用户无权修改！，请联系管理员！\");history.go(-1);</script>","encoding:UTF-8");
				return null;
			}
			
		}
		return "index_index";
	}
	
	public String toUpdateNews(){
		if(Struts2Utils.getSession().getAttribute("admin")!=null){
			news = newsService.findById(id);
			return "addNews";
		}
		return "index_index";
	}
	
	public String updateNews(){
		if(Struts2Utils.getSession().getAttribute("admin")!=null){
			if(((User)Struts2Utils.getSession().getAttribute("admin")).getUserName().equals("admin")){
				newsService.add(news);
				return "successNews";
			}else{
				Struts2Utils.render("text/html", "<script>alert(\"测试用户无权修改！，请联系管理员！\");history.go(-1);</script>","encoding:UTF-8");
				return null;
			}
		}
		return "index_index";
	}
	
	public String deleteNews(){
		if(Struts2Utils.getSession().getAttribute("admin")!=null){
			if(((User)Struts2Utils.getSession().getAttribute("admin")).getUserName().equals("admin")){
				newsService.delete(Integer.parseInt(id));
				return "successNews";
			}else{
				Struts2Utils.render("text/html", "<script>alert(\"测试用户无权修改！，请联系管理员！\");history.go(-1);</script>","encoding:UTF-8");
			}
		}
		return "index_index";
	}
	
	public String newsList(){
		if(pageNo==0){
			pageNo = 1;
		}
		if(Struts2Utils.getSession().getAttribute("admin")!=null){
			Pagination datePage = newsService.newsList(pageNo, pageSize);
			newsList = (List<News>) datePage.getList();
			resultCount = datePage.getResultCount();
			pageString = PaginationUtil.getPaginationHtml(resultCount, pageSize, pageNo, 2, 5, "/admin_back/newsList.action?pageNo=");
			pageString = pageString.replace(".html", "");
			return "newsList";
		}
			
		return "index_index";
	}
	
	/**
	 * 更新首页新闻
	 */
	public String replaceNews(){
		if(Struts2Utils.getSession().getAttribute("admin")!=null){
			if(((User)Struts2Utils.getSession().getAttribute("admin")).getUserName().equals("admin")){
				IndexAction.newsList = newsService.indexNews();
				Struts2Utils.render("text/html", "<script>alert(\"更新新闻成功！\");window.location.href=\"/admin_back/newsList.action\";</script>","encoding:UTF-8");
				return null;
			}else{
				Struts2Utils.render("text/html", "<script>alert(\"测试用户无权修改！，请联系管理员！\");window.location.href=\"/admin_back/newsList.action\";</script>","encoding:UTF-8");
			}
			
		}
		return null;
	}
	
	public String indexImgAll(){
		if(Struts2Utils.getSession().getAttribute("admin")!=null){
			indexImgList = sysConfigureService.IndexImgAll();
			return "indexImgAll";
		}
		return "index_index";
	}
	
	public String toAddIndexImg(){
		if(Struts2Utils.getSession().getAttribute("admin")!=null){
			if(StringUtil.isNotBlank(id)){
				indexImg = sysConfigureService.findByIndexImgId(id);
			}
			return "toIndexImg";
		}
		return "index_index";
	}
	
	public void doAddIndexImg() throws Exception{
		if(Struts2Utils.getSession().getAttribute("admin")!=null){
			if(((User)Struts2Utils.getSession().getAttribute("admin")).getUserName().equals("admin")){
				String productImgPath = "/productImg/show";
				try {
					if(myFile!=null){
						myFileFileName = myFileFileName.substring(myFileFileName.lastIndexOf("."), myFileFileName.length());
						imageFileName = new Date().getTime()+ myFileFileName;
					       File imageFile = new File(UploadImagesUtil.getFolder(ServletActionContext.getServletContext().getRealPath(productImgPath)+"/", "indexImg")+"/" + imageFileName);
					       copy(myFile, imageFile);
					       CutImages.equimultipleConvert(992, 420, imageFile, imageFile);
					       indexImg.setProImg(productImgPath+"/"+"indexImg"+"/" + imageFileName);
			        }
				} catch (Exception e) {
					e.printStackTrace();
				}
				sysConfigureService.addIndexImg(indexImg);
				ApplicationListenerImpl.indexImgAll = sysConfigureService.initializationIndexImgAll();
				Struts2Utils.render("text/html", "<script>alert(\"操作成功！\");window.location.href=\"/admin_back/indexImgAll.action\";</script>","encoding:UTF-8");
			}else{
				Struts2Utils.render("text/html", "<script>alert(\"测试用户无权修改！，请联系管理员！\");history.go(-1);</script>","encoding:UTF-8");
			}
		}
	}
	
	public void delIndexImg(){
		if(Struts2Utils.getSession().getAttribute("admin")!=null){
			if(((User)Struts2Utils.getSession().getAttribute("admin")).getUserName().equals("admin")){
				sysConfigureService.delIndexImg(Integer.parseInt(id));
				ApplicationListenerImpl.indexImgAll = sysConfigureService.initializationIndexImgAll();
				Struts2Utils.render("text/html", "<script>alert(\"操作成功！\");window.location.href=\"/admin_back/indexImgAll.action\";</script>","encoding:UTF-8");
			}else{
				Struts2Utils.render("text/html", "<script>alert(\"测试用户无权修改！，请联系管理员！\");history.go(-1);</script>","encoding:UTF-8");
			}
		}
	}
	
	public String suggestion(){
		if(Struts2Utils.getSession().getAttribute("admin")!=null){
			if(pageNo==0){
				pageNo = 1;
			}
			Pagination page = sysConfigureService.suggestionList(pageNo, pageSize);
			suggestionList = (List<Suggestion>) page.getList();
			resultCount = page.getResultCount();
			pageString = PaginationUtil.getPaginationHtml(resultCount, pageSize, pageNo, 2, 5, "/admin_back/suggestion.action?pageNo=");
			pageString = pageString.replace(".html", "");
			return "suggestion";
		}
		return "index_index";
	}
	
	
	public String shareList(){
		if(pageNo==0){
			pageNo = 1;
		}
		if(Struts2Utils.getSession().getAttribute("admin")!=null){
			Pagination page = shareService.adminShareList(typeId,id, pageNo, pageSize);
			List<Object[]> pageList = (List<Object[]>) page.getList();
			ShareJSONList = new ArrayList<ShareJSON>();
			for (int i = 0; i < pageList.size(); i++) {
				shareJSON = new ShareJSON();
				shareinfo = (Shareinfo) pageList.get(i)[0];
				latestlottery = (Latestlottery) pageList.get(i)[1];
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
				shareJSON.setAnnouncedTime(latestlottery.getAnnouncedTime().substring(0,10));
				shareJSON.setReplyCount(shareinfo.getReplyCount());
				shareJSON.setReward(shareinfo.getReward());
				shareJSON.setShareContent(shareinfo.getShareContent());
				shareJSON.setShareDate(shareinfo.getShareDate());
				shareJSON.setShareTitle(shareinfo.getShareTitle());
				shareJSON.setUid(shareinfo.getUid());
				shareJSON.setUpCount(shareinfo.getUpCount());
				shareJSON.setUserName(userName);
				shareJSON.setStatus(shareinfo.getStatus());
				ShareJSONList.add(shareJSON);
			}
			
			resultCount = page.getResultCount();
			pageString = PaginationUtil.getPaginationHtml(resultCount, pageSize, pageNo, 2, 5, "/admin_back/shareList.action?id="+id+"&typeId="+typeId+"&pageNo=");
			pageString = pageString.replace(".html", "");
			return "shareList";
		}
		return "index_index";
	}
	
	/**
	 * 未发货订单
	 * @return
	 */
	public String latestList(){
		if(pageNo==0){
			pageNo = 1;
		}
		if(Struts2Utils.getSession().getAttribute("admin")!=null){
			Pagination page = latestlotteryService.LatestAnnounced(pageNo, pageSize);
			resultCount = page.getResultCount();
			List<Latestlottery> objList =  (List<Latestlottery>) page.getList();
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
			resultCount = page.getResultCount();
			pageString = PaginationUtil.getPaginationHtml(resultCount, pageSize, pageNo, 2, 5, "/admin_back/latestList.action?id="+id+"&pageNo=");
			pageString = pageString.replace(".html", "");
			return "latestList";
		}
		return "index_index";
	}
	
	public String toPostDeliver(){
		if(Struts2Utils.getSession().getAttribute("admin")!=null){
			latestlottery = latestlotteryService.findById(id);
			orderdetailaddress = latestlotteryService.orderdetailaddressFindByOrderdetailId(id);
			return "postDeliver";
		}
		return "index_index";
	}
	
	public String doPostDeliver(){
		if(Struts2Utils.getSession().getAttribute("admin")!=null){
			orderdetailaddressService.add(orderdetailaddress);
			latestlottery = latestlotteryService.findById(id);
			latestlottery.setStatus(3);
			latestlotteryService.add(latestlottery);
			return "postDeliverSuccess";
		}
		return "index_index";
	}
	
	public String toAddShare(){
		if(Struts2Utils.getSession().getAttribute("admin")!=null){
			return "addShare";
		}
		return "index_index";
	}
	
	public String addShare(){
		if(Struts2Utils.getSession().getAttribute("admin")!=null){
			if(((User)Struts2Utils.getSession().getAttribute("admin")).getUserName().equals("admin")){
				if(shareinfo.getShareDate()==null || shareinfo.getShareDate().equals("")){
					shareinfo.setShareDate(DateUtil.DateTimeToStr(new Date()));
				}
				shareinfo.setUpCount(0);
				shareinfo.setReplyCount(0);
				shareinfo.setReward(0);
				shareService.add(shareinfo);
				latestlottery = latestlotteryService.findById(String.valueOf(shareinfo.getProductId()));
				latestlottery.setShareStatus(0);
				latestlottery.setShareId(shareinfo.getUid());
				latestlotteryService.add(latestlottery);
				return "successShare";
			}else{
				Struts2Utils.render("text/html", "<script>alert(\"测试用户无权修改！，请联系管理员！\");history.go(-1);</script>","encoding:UTF-8");
				return null;
			}
		}
		return "index_index";
	}
	
	public String toAddShareImage(){
		if(Struts2Utils.getSession().getAttribute("admin")!=null){
			shareimageList = shareService.getShareimage(id);
			return "toAddShareImage";
		}
		return "index_index";
	}
	
	public String addShareImage() throws Exception{
		if(Struts2Utils.getSession().getAttribute("admin")!=null){
			if(((User)Struts2Utils.getSession().getAttribute("admin")).getUserName().equals("admin")){
				String productImgPath = "/UserPost";
				long fileDateName = 0;
				String biGImagePath = null;
				String smallImagePath = null;
				String listImgPath = null; 
				if (Filedata == null || Filedata.size() == 0){
		            return null;  
		        }  
				 for (int i = 0; i < Filedata.size(); ++i){
				 	myFile = Filedata.get(i);
				 	if(myFile!=null){
						myFileFileName = FiledataFileName.get(i).substring(FiledataFileName.get(i).lastIndexOf("."), FiledataFileName.get(i).length());
						fileDateName = new Date().getTime();
						imageFileName = fileDateName + myFileFileName;
						biGImagePath = ServletActionContext.getServletContext().getRealPath(productImgPath)+"/Big/";
						smallImagePath = ServletActionContext.getServletContext().getRealPath(productImgPath)+"/Small/";
						listImgPath = ServletActionContext.getServletContext().getRealPath(productImgPath)+"/220/";
					    File bigImageFile = new File(biGImagePath + imageFileName);
					    File smallImageFile = new File(smallImagePath + imageFileName);
					    File listImageFile = new File(smallImagePath + imageFileName);
					    copy(myFile, bigImageFile);
					    copy(myFile, smallImageFile);
					    copy(myFile, listImageFile);
					    CutImages.equimultipleConvert(560, 420, bigImageFile, new File(biGImagePath+fileDateName+myFileFileName));
					    CutImages.equimultipleConvert(220, 220, listImageFile, new File(listImgPath+fileDateName+myFileFileName));
					    CutImages.equimultipleConvert(100, 100, smallImageFile, new File(smallImagePath+fileDateName+myFileFileName));
					    shareimage = new Shareimage();
					    shareimage.setShareInfoId(Integer.parseInt(id));
					    shareimage.setImages(fileDateName+myFileFileName);
					    shareService.addShareImage(shareimage);
			       }
		        }
				 Struts2Utils.render("text/html", "<script>alert(\"上传成功！\");history.go(-2);</script>","encoding:UTF-8");
				 return null;
			}else{
				Struts2Utils.render("text/html", "<script>alert(\"测试用户无权修改！，请联系管理员！\");history.go(-1);</script>","encoding:UTF-8");
				return null;
			}
		}
		return "index_index";
	}
	
	public String toUpdateShare(){
		if(Struts2Utils.getSession().getAttribute("admin")!=null){
			shareimageList = shareService.getShareimage(id);
			shareinfo = shareService.findById(id);
			return "addShare";
		}
		return "index_index";
	}
	
	public String updateShare(){
		if(Struts2Utils.getSession().getAttribute("admin")!=null){
			if(((User)Struts2Utils.getSession().getAttribute("admin")).getUserName().equals("admin")){
				shareService.add(shareinfo);
				return "successShare";
			}else{
				Struts2Utils.render("text/html", "<script>alert(\"测试用户无权修改！，请联系管理员！\");history.go(-1);</script>","encoding:UTF-8");
				return null;
			}
			
		}
		return "index_index";
	}
	
	
	public String userListAll(){
		if(pageNo==0){
			pageNo = 1;
		}
		if(typeName==null){
			typeName = "";
		}
		if(Struts2Utils.getSession().getAttribute("admin")!=null){
			Pagination page = userService.adminUserList(typeId,keyword,typeName,pageNo, pageSize);
			userList = (List<User>) page.getList();
			
			resultCount = page.getResultCount();
			pageString = PaginationUtil.getPaginationHtml(resultCount, pageSize, pageNo, 2, 5, "/admin_back/userListAll.action?typeName="+typeName+"&pageNo=");
			pageString = pageString.replace(".html", "");
			return "userList";
		}
		return "index_index";
	}
	
	public String toAddUser(){
		if(Struts2Utils.getSession().getAttribute("admin")!=null){
			
			return "toAddUser";
		}
		return "index_index";
	}
	
	public String toUpdateUser(){
		if(Struts2Utils.getSession().getAttribute("admin")!=null){
			user = userService.findById(id);
			return "toAddUser";
		}
		return "index_index";
	}
	
	public void doUpdateUser(){
		if(Struts2Utils.getSession().getAttribute("admin")!=null){
			if(((User)Struts2Utils.getSession().getAttribute("admin")).getUserName().equals("admin")){
				userService.add(user);
				Struts2Utils.render("text/html", "<script>window.location.href=\""+backUrl+"\";</script>","encoding:UTF-8");
			}else{
				Struts2Utils.render("text/html", "<script>alert(\"测试用户无权修改！，请联系管理员！\");history.go(-1);</script>","encoding:UTF-8");
			}
		}
	}
	
	public String doAddUser(){
		if(Struts2Utils.getSession().getAttribute("admin")!=null){
			if(((User)Struts2Utils.getSession().getAttribute("admin")).getUserName().equals("admin")){
				if(StringUtil.isNotBlank(user.getMail())){
					User u = userService.userByName(user.getMail());
					if(u!=null){
						Struts2Utils.render("text/html", "<script>alert(\"该邮箱已被注册！\");history.go(-1);</script>","encoding:UTF-8");
						return null;
					}
				}
				if(StringUtil.isNotBlank(user.getPhone())){
					User u2 = userService.userByName(user.getPhone());
					if(u2!=null){
						Struts2Utils.render("text/html", "<script>alert(\"该手机已被注册！\");history.go(-1);</script>","encoding:UTF-8");
						return null;
					}
				}
				if(user.getCommissionBalance()==null){
					user.setCommissionBalance(0.00);
				}
				if(user.getCommissionCount()==null){
					user.setCommissionCount(0.00);
				}
				if(user.getCommissionMention()==null){
					user.setCommissionMention(0.00);
				}
				if(user.getCommissionPoints()==null){
					user.setCommissionPoints(0);
				}
				if(user.getExperience()==null){
					user.setExperience(0);
				}
				if(user.getBalance()==null){
					user.setBalance(0.00);
				}
				String date = DateUtil.DateTimeToStr(new Date());
				user.setOldDate(date);
				user.setFaceImg("/Images/defaultUserFace.png");
				userService.add(user);
				return "addUserSuccess";
			}else{
				Struts2Utils.render("text/html", "<script>alert(\"测试用户无权修改！，请联系管理员！\");history.go(-1);</script>","encoding:UTF-8");
				return null;
			}

		}
		return "index_index";
	}
	
	public String toUserConfigure(){
		if(Struts2Utils.getSession().getAttribute("admin")!=null){
			sysConfigure = ApplicationListenerImpl.sysConfigureJson;
			return "toUserConfigure";
		}
		return "index_index";
	}
	
	public String sysInfo(){
		if(Struts2Utils.getSession().getAttribute("admin")!=null){
//			HttpServletRequest request = Struts2Utils.getRequest();;
//			String path = request.getContextPath();
//			String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
//			ResourceBundle url = ResourceBundle.getBundle("config");
//			sysInfoBean = new SysInfoBean();
//			sysInfoBean.setAlipayKey(url.getString("alipayKey"));
//			sysInfoBean.setAlipayMail(url.getString("alipayMail"));
//			sysInfoBean.setAlipayPartner(url.getString("alipayPartner"));
//			sysInfoBean.setDomain(url.getString("domain"));
//			sysInfoBean.setIcp(url.getString("icp"));
//			sysInfoBean.setImg(url.getString("img"));
//			sysInfoBean.setKeyword(url.getString("keyword"));
//			sysInfoBean.setDescription(url.getString("description"));
//			sysInfoBean.setMailName(url.getString("mailName"));
//			sysInfoBean.setMailPwd(url.getString("mailPwd"));
//			sysInfoBean.setSaitName(url.getString("saitName"));
//			sysInfoBean.setSaitUrl(url.getString("www"));
//			sysInfoBean.setSkin(url.getString("skin"));
//			sysInfoBean.setTenpayKey(url.getString("tenpayKey"));
//			sysInfoBean.setTenpayPartner(url.getString("tenpayPartner"));
			
			sysConfigure = ApplicationListenerImpl.sysConfigureJson;
			
			return "sysInfo";
		}
		return "index_index";
		
	}
	
	public String setSysInfo() {
		if(Struts2Utils.getSession().getAttribute("admin")!=null){
			Properties properties = new Properties();  
	        try  
	        {  
	            OutputStream outputStream = new FileOutputStream(ServletActionContext.getServletContext().getRealPath("/WEB-INF/classes")+"/"+"config.properties");  
	            properties.setProperty("alipayKey", sysInfoBean.getAlipayKey());  
	            properties.setProperty("alipayMail", sysInfoBean.getAlipayMail());  
	            properties.setProperty("alipayPartner",sysInfoBean.getAlipayPartner());
	            properties.setProperty("domain",sysInfoBean.getDomain());  
	            properties.setProperty("icp",sysInfoBean.getIcp());
	            properties.setProperty("img",sysInfoBean.getImg());  
	            properties.setProperty("keyword",sysInfoBean.getKeyword());  
	            properties.setProperty("description",sysInfoBean.getDescription());  
	            properties.setProperty("mailName",sysInfoBean.getMailName());  
	            properties.setProperty("mailPwd",sysInfoBean.getMailPwd());  
	            properties.setProperty("saitName",sysInfoBean.getSaitName());  
	            properties.setProperty("www",sysInfoBean.getSaitUrl());
	            properties.setProperty("skin",sysInfoBean.getSkin());  
	            properties.setProperty("tenpayKey",sysInfoBean.getAlipayKey());  
	            properties.setProperty("tenpayPartner",sysInfoBean.getTenpayPartner());  
	            properties.setProperty("upfilefolder","productImg/");  
	            properties.store(outputStream, "author: service@1ypg.com");  
	            outputStream.close();  
	        }  
	        catch (IOException e)  
	        {  
	            e.printStackTrace();  
	        } 
			return "main";
		}
		return "index_index";
	}
	
	public void restartTomcat() throws IOException, InterruptedException{
		 String windowsPath  = "D:\\tomcat\\bin";
	        String linuxPath = "/usr/bin/ye";
	        List<String> cmd = new ArrayList<String>();
	        if (OS.isWindows()) {
	            cmd.add(windowsPath);
	        } else if (OS.isLinux()) {
	            cmd.add(linuxPath);
	        }
	        ProcessBuilder pb = new ProcessBuilder();
	        pb.command(cmd);
	        pb.redirectErrorStream(true);
	        Process process = pb.start();
	        int w = process.waitFor();
	        System.out.println("status:"+w);
		
	}
	
	public String orderList(){
		if(Struts2Utils.getSession().getAttribute("admin")!=null){
			if(pageNo==0){
				pageNo = 1;
			}
			Pagination page = consumetableService.orderList(userName, pageNo, pageSize);
			List<Consumetable> list = (List<Consumetable>) page.getList();
			orderBeanList = new ArrayList<OrderBean>();
			for (int i = 0; i < list.size(); i++) {
				OrderBean orderBean = new OrderBean();
				Consumetable consumetable = list.get(i);
				orderBean.setOutTradeNo(consumetable.getOutTradeNo());
				orderBean.setBuyCount(consumetable.getBuyCount());
				orderBean.setDate(consumetable.getDate());
				orderBean.setMoney(consumetable.getMoney());
				if(consumetable.getInterfaceType().equals("tenPay")){
					orderBean.setPayType("财付通");
				}else if(consumetable.getInterfaceType().equals("aliPay")){
					orderBean.setPayType("支付宝");
				}if(consumetable.getInterfaceType().equals("yeePay")){
					orderBean.setPayType("易宝支付");
				}else{
					orderBean.setPayType("余额支付");
				}
				orderBean.setUserId(consumetable.getUserId());
				orderBeanList.add(orderBean);
			}
			resultCount = page.getResultCount();
			if(StringUtil.isNotBlank(userName)){
				pageString = PaginationUtil.getPaginationHtml(resultCount, pageSize, pageNo, 2, 5, "/admin_back/orderList.action?userName="+userName+"&pageNo=");
			}else{
				pageString = PaginationUtil.getPaginationHtml(resultCount, pageSize, pageNo, 2, 5, "/admin_back/orderList.action?pageNo=");
			}
			pageString = pageString.replace(".html", "");
			return "orderList";
		}
		
		return "index_index";
	}
	
	public String orderInfo(){
		if(Struts2Utils.getSession().getAttribute("admin")!=null){
			if(pageNo==0){
				pageNo = 1;
			}
			Pagination page = consumerdetailService.orderInfo(id, pageNo, pageSize);
			List<Consumerdetail> list = (List<Consumerdetail>) page.getList();
			orderBeanList = new ArrayList<OrderBean>();
			for (Consumerdetail consumerdetail : list) {
				OrderBean orderBean = new OrderBean();
				orderBean.setBuyCount(consumerdetail.getBuyCount());
				orderBean.setMoney(consumerdetail.getBuyMoney());
				orderBean.setProductId(consumerdetail.getProductId());
				orderBean.setProductName(consumerdetail.getProductName());
				orderBean.setProductPeriod(consumerdetail.getProductPeriod()+"");
				orderBeanList.add(orderBean);
			}
			resultCount = page.getResultCount();
			pageString = PaginationUtil.getPaginationHtml(resultCount, pageSize, pageNo, 2, 5, "/admin_back/orderInfo.action?pageNo=");
			pageString = pageString.replace(".html", "");
			return "orderInfo";
		}
		return "index_index";
	}
	
	/**
	 * 卡密管理
	 * @return
	 */
	public String cardList(){
		if(Struts2Utils.getSession().getAttribute("admin")!=null){
			if(pageNo==0){
				pageNo = 1;
			}
			Pagination page = cardpasswordService.cardRechargeList(pageNo, pageSize);
			cardpasswordList = (List<Cardpassword>) page.getList();
			resultCount = page.getResultCount();
			pageString = PaginationUtil.getPaginationHtml(resultCount, pageSize, pageNo, 2, 5, "/admin_back/cardList.action?pageNo=");
			pageString = pageString.replace(".html", "");
			return "cardList";
		}
		return "index_index";
	}
	
	/**
	 * 首页推荐
	 */
	public void indexTop(){
		if(Struts2Utils.getSession().getAttribute("admin")!=null){
			if(((User)Struts2Utils.getSession().getAttribute("admin")).getUserName().equals("admin")){
				try {
					Recommend recommend = new Recommend();
					recommend.setDate(DateUtil.DateToStr(new Date()));
					recommend.setRecommendId(1);
					recommend.setSpellbuyProductId(Integer.parseInt(id));
					recommendService.add(recommend);
					Struts2Utils.renderText("success");
				} catch (Exception e) {
					e.printStackTrace();
					Struts2Utils.renderText("error");
				}
			}else{
				Struts2Utils.renderText("test");
			}
		}
		
		
	}
	
	public String toAddCard(){
		if(Struts2Utils.getSession().getAttribute("admin")!=null){
			return "addCard";
		}
		return "index_index";
	}
	
	/**
	 * 生成卡密
	 * @throws InterruptedException
	 */
	public void doCard() throws InterruptedException{
		if(Struts2Utils.getSession().getAttribute("admin")!=null){
			if(((User)Struts2Utils.getSession().getAttribute("admin")).getUserName().equals("admin")){
				if(StringUtil.isNotBlank(id) && StringUtil.isNotBlank(pwd)){
					List<Cardpassword> cardpasswordList = new ArrayList<Cardpassword>();
					for (int i = 0; i < Integer.parseInt(id); i++) {
						try {
							Thread.sleep(100);
							String s = UUID.randomUUID().toString().toUpperCase();
					        s = s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24);
					        String md5s = MD5Util.encode(s);
					        String key = s+ md5s;
					        Cardpassword cardpassword = new Cardpassword();
					        cardpassword.setCardPwd(md5s);
					        cardpassword.setDate(DateUtil.DateTimeToStr(new Date()));
					        cardpassword.setMoney(Double.parseDouble(pwd));
					        cardpassword.setRandomNo(s);
					        cardpasswordService.add(cardpassword);
					        cardpasswordList.add(cardpassword);
						} catch (Exception e) {
							Struts2Utils.renderText("error");
							e.printStackTrace();
						}
					}
					Struts2Utils.renderText("success");
				}
			}else{
				Struts2Utils.renderText("test");
			}
			
		}
	}
	
	/**
	 * 提现管理
	 * @return
	 */
	public String applymentionList(){
		if(Struts2Utils.getSession().getAttribute("admin")!=null){
			if(pageNo==0){
				pageNo = 1;
			}
			Pagination page = applymentionService.getApplymentionList(null, null, null, pageNo, pageSize);
			applymentionList =  (List<Applymention>) page.getList();
			resultCount = applymentionService.getApplymentionListByCount(null, null, null);
			pageString = PaginationUtil.getPaginationHtml(resultCount, pageSize, pageNo, 2, 5, "/admin_back/applymentionList.action?pageNo=");
			pageString = pageString.replace(".html", "");
			return "applymentionList";
		}
		return "index_index";
	}
	
	/**
	 * 发货管理
	 * @return
	 */
	public String orderdetailaddressList(){
		if(Struts2Utils.getSession().getAttribute("admin")!=null){
			if(pageNo==0){
				pageNo = 1;
			}
			Pagination page = orderdetailaddressService.orderdetailaddressList(pageNo, pageSize);
			orderdetailaddressList = (List<Orderdetailaddress>) page.getList();
			resultCount = page.getResultCount();
			pageString = PaginationUtil.getPaginationHtml(resultCount, pageSize, pageNo, 2, 5, "/admin_back/orderdetailaddressList.action?pageNo=");
			pageString = pageString.replace(".html", "");
			return "orderdetailaddressList";
		}
		return "index_index";
	}
	
	
	/**
	 * 在线升级
	 * @return
	 */
	public String lineUpdate(){
		if(Struts2Utils.getSession().getAttribute("admin")!=null){
			
			return "lineUpdate";
		}
		return "index_index";
		
		
	}
	
	/**
	 * QQ互联登录
	 * @return
	 */
	public String qqSetInfo(){
		if(Struts2Utils.getSession().getAttribute("admin")!=null){
			sysConfigure = ApplicationListenerImpl.sysConfigureJson;
			return "qqSetInfo";
		}
		return "index_index";
	}
	
	/**
	 * SEO设置
	 * @return
	 */
	public String toSeoSet(){
		if(Struts2Utils.getSession().getAttribute("admin")!=null){
			sysConfigure = ApplicationListenerImpl.sysConfigureJson;
			return "seoSet";
		}
		return "index_index";
		
	}
	
	/**
	 * 基本设置
	 * @return
	 */
	public String toBasicSet(){
		if(Struts2Utils.getSession().getAttribute("admin")!=null){
			sysConfigure = ApplicationListenerImpl.sysConfigureJson;
			return "toBasicSet";
		}
		return "index_index";
		
	}
	
	/**
	 * 邮箱设置
	 * @return
	 */
	public String toMailSet(){
		if(Struts2Utils.getSession().getAttribute("admin")!=null){
			sysConfigure = ApplicationListenerImpl.sysConfigureJson;
			return "toMailSet";
		}
		return "index_index";
		
	}
	
	/**
	 * 邮件测试
	 * @throws InterruptedException 
	 */
	public void doTestMail() throws InterruptedException{
		if(Struts2Utils.getSession().getAttribute("admin")!=null){
			if(((User)Struts2Utils.getSession().getAttribute("admin")).getUserName().equals("admin")){
				if(StringUtil.isBlank(ApplicationListenerImpl.sysConfigureJson.getMailName()) || StringUtil.isBlank(ApplicationListenerImpl.sysConfigureJson.getMailPwd())){
					Struts2Utils.render("text/html", "<script>alert(\"请先配置系统邮箱设置！\");history.go(-1);</script>","encoding:UTF-8");
				}
				String html = message;
				boolean flag = false;
				if(StringUtil.isNotBlank(userName)){
					String[] mailTo = userName.split(",");
					for (String string : mailTo) {
						try {
							flag = EmailUtil.sendEmail(ApplicationListenerImpl.sysConfigureJson.getMailName(), ApplicationListenerImpl.sysConfigureJson.getMailPwd(), string, tId, html);
						} catch (Exception e) {
							e.printStackTrace();
							Struts2Utils.render("text/html", "<script>alert(\""+string+"发送失败！\");history.go(-1);</script>","encoding:UTF-8");
						}
						Thread.sleep(1000);
					}
				}
				if(flag){
					Struts2Utils.render("text/html", "<script>alert(\"发送成功！\");history.go(-1);</script>","encoding:UTF-8");
				}
			}else{
				Struts2Utils.render("text/html", "<script>alert(\"测试用户无权修改！，请联系管理员！\");history.go(-1);</script>","encoding:UTF-8");
			}
		}
	}
	
	/**
	 * 短信设置
	 * @return
	 */
	public String toMessageSet(){
		if(Struts2Utils.getSession().getAttribute("admin")!=null){
			sysConfigure = ApplicationListenerImpl.sysConfigureJson;
			message = Sampler.getMesBalance();
			return "toMessageSet";
		}
		return "index_index";
		
	}
	
	public void testMessage() throws Exception{
		if(Struts2Utils.getSession().getAttribute("admin")!=null){
			if(((User)Struts2Utils.getSession().getAttribute("admin")).getUserName().equals("admin")){
				List<SendResultBean> sendList = Sampler.sendOnce(id, message);
				if(sendList != null){
					for(SendResultBean t:sendList){
						if(t.getResult() < 1){
//							System.out.println("发送提交失败: " + t.getErrMsg());
							Struts2Utils.renderText("发送失败，请联系管理员！");
							return;
						}
						Struts2Utils.renderText("发送成功!总数："+t.getTotal());
//						System.out.println("发送成功: 消息编号<" + t.getMsgId() + "> 总数<" + t.getTotal() + "> 余额<" + t.getRemain() + ">");
					}
				}
			}else{
				Struts2Utils.render("text/html", "<script>alert(\"测试用户无权修改！，请联系管理员！\");history.go(-1);</script>","encoding:UTF-8");
			}
		}

	}
	
	/**
	 * 支付设置
	 * @return
	 */
	public String toPaySet(){
		if(Struts2Utils.getSession().getAttribute("admin")!=null){
			sysConfigure = ApplicationListenerImpl.sysConfigureJson;
			return "toPaySet";
		}
		return "index_index";
		
	}
	
	/**
	 * 支付设置页面
	 * @return
	 */
	public String payInfo(){
		if(Struts2Utils.getSession().getAttribute("admin")!=null){
//			if(((User)Struts2Utils.getSession().getAttribute("admin")).getUserName().equals("admin")){
				sysConfigure = ApplicationListenerImpl.sysConfigureJson;
				return "payInfo";
//			}else{
//				Struts2Utils.render("text/html", "<script>alert(\"测试用户无权修改！，请联系管理员！\");history.go(-1);</script>","encoding:UTF-8");
//			}
		}
		return "index_index";
	}
	
	/**
	 * 添加管理员
	 * @return
	 */
	public String toAddAdmin(){
		if(Struts2Utils.getSession().getAttribute("admin")!=null){
			
			return "toAddAdmin";
		}
		return "index_index";
		
	}
	
	public String doAddAdmin(){
		if(Struts2Utils.getSession().getAttribute("admin")!=null){
			if(((User)Struts2Utils.getSession().getAttribute("admin")).getUserName().equals("admin")){
				User u = new User();
				u.setUserName(user.getUserName());
				u.setPhone(user.getUserName());
				u.setMail(user.getUserName());
				u.setUserPwd(user.getUserPwd());
				u.setUserType(user.getUserType());
				userService.add(u);
				return "addAdminSuccess";
			}else{
				Struts2Utils.render("text/html", "<script>alert(\"测试用户无权修改！，请联系管理员！\");history.go(-1);</script>","encoding:UTF-8");
				return null;
			}
			
		}
		return "index_index";
		
	}
	
	/**
	 * 管理员列表
	 * @return
	 */
	public String toAdminList(){
		if(Struts2Utils.getSession().getAttribute("admin")!=null){
			userList = userService.query("from User u where 1=1 and u.userType = 1");
			return "toAdminList";
		}
		return "index_index";
		
	}
	
	/**
	 * 密码管理
	 * @return
	 */
	public String updateAdminPwd(){
		if(Struts2Utils.getSession().getAttribute("admin")!=null){
			userName = id;
			return "updateAdminPwd";
		}
		return "index_index";
	}
	
	public void doUpdateAdminPwd(){
		if(Struts2Utils.getSession().getAttribute("admin")!=null){
			if(((User)Struts2Utils.getSession().getAttribute("admin")).getUserName().equals("admin")){
				try {
					user = (User) Struts2Utils.getSession().getAttribute("admin");
					user.setUserPwd(pwd);
					userService.add(user);
					Struts2Utils.renderText("success");
				} catch (Exception e) {
					Struts2Utils.renderText("error");
					e.printStackTrace();
				}
			}else{
				Struts2Utils.renderText("test");
			}
		}
		
	}
	
	public String doSeoSet(){
		if(Struts2Utils.getSession().getAttribute("admin")!=null){
			if(((User)Struts2Utils.getSession().getAttribute("admin")).getUserName().equals("admin")){
				try {
					String productImgPath = "/Images";
					if(myFile!=null){
						myFileFileName = myFileFileName.substring(myFileFileName.lastIndexOf("."), myFileFileName.length());
						imageFileName = "new-logo"+ myFileFileName;
					       File imageFile = new File(UploadImagesUtil.getFolder(ServletActionContext.getServletContext().getRealPath(productImgPath)+"/", "")+"/" + imageFileName);
					       copy(myFile, imageFile);
					       imageFileName = imageFileName.toLowerCase();
					       sysConfigure.setSaitLogo(productImgPath+"/" + imageFileName);
				       }
					sysConfigure.setAuthorization(Struts2Utils.getLocalIP());
					sysConfigureService.add(sysConfigure);
					ApplicationListenerImpl.sysConfigureJson = sysConfigureService.findById("1");
					Struts2Utils.render("text/html", "<script>alert(\"操作成功！\");history.go(-1);</script>","encoding:UTF-8");
				} catch (Exception e) {
					Struts2Utils.render("text/html", "<script>alert(\"操作失败，请联系管理员！\");history.go(-1);</script>","encoding:UTF-8");
					e.printStackTrace();
				}
			}else{
				Struts2Utils.render("text/html", "<script>alert(\"测试用户无权配置系统！，请联系管理员！\");history.go(-1);</script>","encoding:UTF-8");
			}
		}
		return null;
		
	}
	
	/**
	 * 充值记录
	 * @return
	 */
	public String rechargeAllList(){
		if(Struts2Utils.getSession().getAttribute("admin")!=null){
			startDate = DateUtil.DateToStr(DateUtil.addMonth(new Date(), -1));
			endDate = DateUtil.DateToStr(new Date());
			startDate +=" 00:00:00";
			endDate +=" 23:59:59";
			resultCount = consumetableService.userByConsumetableByDeltaByCount(userId,startDate,endDate);
			return "rechargeAllList";
		}
		return "index_index";
	}
	
	/**
	 * 消费记录
	 * @return
	 */
	public String consumeAllList(){
		if(Struts2Utils.getSession().getAttribute("admin")!=null){
			startDate = DateUtil.DateToStr(DateUtil.addMonth(new Date(), -1));
			endDate = DateUtil.DateToStr(new Date());
			startDate +=" 00:00:00";
			endDate +=" 23:59:59";
			resultCount = consumetableService.userByConsumetableByCount(userId,startDate,endDate);
			return "consumeAllList";
		}
		
		return "index_index";
	}
	
	/**
	 * 网站地图
	 * @return
	 */
	public String toSaitMap(){
		if(Struts2Utils.getSession().getAttribute("admin")!=null){
			return "toSaitMap";
		}
		return "index_index";
	}
	
	/**
	 * 站点提交 
	 * @return
	 */
	public String toSaitPost(){
		if(Struts2Utils.getSession().getAttribute("admin")!=null){
			return "toSaitPost";
			
		}
		return "index_index";
	}
	
	/**
	 * 网站统计
	 * @return
	 */
	public String toSaitCount(){
		if(Struts2Utils.getSession().getAttribute("admin")!=null){
			return "toSaitCount";
		}
		return "index_index";
	}
	
	public String timeOut(){
		
		return "timeout";
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
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

	public Productimage getProductimage() {
		return productimage;
	}

	public void setProductimage(Productimage productimage) {
		this.productimage = productimage;
	}

	public Producttype getProducttype() {
		return producttype;
	}

	public void setProducttype(Producttype producttype) {
		this.producttype = producttype;
	}

	public List<Producttype> getProductTypeList() {
		return productTypeList;
	}

	public void setProductTypeList(List<Producttype> productTypeList) {
		this.productTypeList = productTypeList;
	}

	public File getMyFile() {
		return myFile;
	}

	public void setMyFile(File myFile) {
		this.myFile = myFile;
	}

	public String getMyFileFileName() {
		return myFileFileName;
	}

	public void setMyFileFileName(String myFileFileName) {
		this.myFileFileName = myFileFileName;
	}

	public String getMyFileContentType() {
		return myFileContentType;
	}

	public void setMyFileContentType(String myFileContentType) {
		this.myFileContentType = myFileContentType;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
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

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}


	public String getPageString() {
		return pageString;
	}


	public void setPageString(String pageString) {
		this.pageString = pageString;
	}


	public String getPages() {
		return pages;
	}


	public void setPages(String pages) {
		this.pages = pages;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public List<File> getFiledata() {
		return Filedata;
	}


	public void setFiledata(List<File> filedata) {
		Filedata = filedata;
	}


	public List<String> getFiledataFileName() {
		return FiledataFileName;
	}


	public void setFiledataFileName(List<String> filedataFileName) {
		FiledataFileName = filedataFileName;
	}


	public List<String> getImageContentType() {
		return imageContentType;
	}


	public void setImageContentType(List<String> imageContentType) {
		this.imageContentType = imageContentType;
	}


	public List<Productimage> getProductimageList() {
		return productimageList;
	}


	public void setProductimageList(List<Productimage> productimageList) {
		this.productimageList = productimageList;
	}


	public String getTypeId() {
		return typeId;
	}


	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}


	public String getTypeName() {
		return typeName;
	}


	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}


	public List<ProductJSON> getProductJSONList() {
		return productJSONList;
	}


	public void setProductJSONList(List<ProductJSON> productJSONList) {
		this.productJSONList = productJSONList;
	}


	public ProductJSON getProductJSON() {
		return productJSON;
	}


	public void setProductJSON(ProductJSON productJSON) {
		this.productJSON = productJSON;
	}

	public String getChannelUrl() {
		return channelUrl;
	}

	public void setChannelUrl(String channelUrl) {
		this.channelUrl = channelUrl;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getBackUrl() {
		return backUrl;
	}

	public void setBackUrl(String backUrl) {
		this.backUrl = backUrl;
	}

	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}

	public List<News> getNewsList() {
		return newsList;
	}

	public void setNewsList(List<News> newsList) {
		this.newsList = newsList;
	}

	public Shareinfo getShareinfo() {
		return shareinfo;
	}

	public void setShareinfo(Shareinfo shareinfo) {
		this.shareinfo = shareinfo;
	}

	public Shareimage getShareimage() {
		return shareimage;
	}

	public void setShareimage(Shareimage shareimage) {
		this.shareimage = shareimage;
	}

	public List<ShareJSON> getShareJSONList() {
		return ShareJSONList;
	}

	public void setShareJSONList(List<ShareJSON> shareJSONList) {
		ShareJSONList = shareJSONList;
	}

	public ShareJSON getShareJSON() {
		return shareJSON;
	}

	public void setShareJSON(ShareJSON shareJSON) {
		this.shareJSON = shareJSON;
	}

	public Latestlottery getLatestlottery() {
		return latestlottery;
	}

	public void setLatestlottery(Latestlottery latestlottery) {
		this.latestlottery = latestlottery;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
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

	public List<Latestlottery> getLatestlotteryList() {
		return latestlotteryList;
	}

	public void setLatestlotteryList(List<Latestlottery> latestlotteryList) {
		this.latestlotteryList = latestlotteryList;
	}

	public List<Shareimage> getShareimageList() {
		return shareimageList;
	}

	public void setShareimageList(List<Shareimage> shareimageList) {
		this.shareimageList = shareimageList;
	}

	public String getAnnouncedTime() {
		return announcedTime;
	}

	public void setAnnouncedTime(String announcedTime) {
		this.announcedTime = announcedTime;
	}

	public SysInfoBean getSysInfoBean() {
		return sysInfoBean;
	}

	public void setSysInfoBean(SysInfoBean sysInfoBean) {
		this.sysInfoBean = sysInfoBean;
	}

	public List<OrderBean> getOrderBeanList() {
		return orderBeanList;
	}

	public void setOrderBeanList(List<OrderBean> orderBeanList) {
		this.orderBeanList = orderBeanList;
	}

	public List<Cardpassword> getCardpasswordList() {
		return cardpasswordList;
	}

	public void setCardpasswordList(List<Cardpassword> cardpasswordList) {
		this.cardpasswordList = cardpasswordList;
	}

	public List<Applymention> getApplymentionList() {
		return applymentionList;
	}

	public void setApplymentionList(List<Applymention> applymentionList) {
		this.applymentionList = applymentionList;
	}

	public List<Orderdetailaddress> getOrderdetailaddressList() {
		return orderdetailaddressList;
	}

	public void setOrderdetailaddressList(
			List<Orderdetailaddress> orderdetailaddressList) {
		this.orderdetailaddressList = orderdetailaddressList;
	}

	public SysConfigure getSysConfigure() {
		return sysConfigure;
	}

	public void setSysConfigure(SysConfigure sysConfigure) {
		this.sysConfigure = sysConfigure;
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

	public List<IndexImg> getIndexImgList() {
		return indexImgList;
	}

	public void setIndexImgList(List<IndexImg> indexImgList) {
		this.indexImgList = indexImgList;
	}

	public IndexImg getIndexImg() {
		return indexImg;
	}

	public void setIndexImg(IndexImg indexImg) {
		this.indexImg = indexImg;
	}

	public List<Producttype> getProductBrandList() {
		return productBrandList;
	}

	public void setProductBrandList(List<Producttype> productBrandList) {
		this.productBrandList = productBrandList;
	}

	public String getTId() {
		return tId;
	}

	public void setTId(String id) {
		this.tId = id;
	}

	public List<Suggestion> getSuggestionList() {
		return suggestionList;
	}

	public void setSuggestionList(List<Suggestion> suggestionList) {
		this.suggestionList = suggestionList;
	}

	public List<Orderdetail> getOrderdetailList() {
		return orderdetailList;
	}

	public void setOrderdetailList(List<Orderdetail> orderdetailList) {
		this.orderdetailList = orderdetailList;
	}

	public Orderdetailaddress getOrderdetailaddress() {
		return orderdetailaddress;
	}

	public void setOrderdetailaddress(Orderdetailaddress orderdetailaddress) {
		this.orderdetailaddress = orderdetailaddress;
	}
	

}
