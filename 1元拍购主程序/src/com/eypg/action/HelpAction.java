package com.eypg.action;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eypg.pojo.Suggestion;
import com.eypg.service.SysConfigureService;
import com.eypg.util.ApplicationListenerImpl;
import com.eypg.util.EmailUtil;
import com.eypg.util.MD5Util;
import com.eypg.util.MemCachedClientHelp;
import com.eypg.util.Struts2Utils;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("unused")
@Component("HelpAction")
public class HelpAction extends ActionSupport {

	private static final long serialVersionUID = -8756837342149838857L;
	
	private Suggestion suggestion;
	private String rnd;
	
	@Autowired
	private SysConfigureService sysConfigureService;
	
	HttpServletRequest request = null;
	HttpServletResponse response = null;
	
	public String index(){
		
		return "index";
	}
	
	public String openCookie(){
		
		return "openCookie";
	}
	
	public String whatPaigou(){
		
		return "whatPaigou";
	}
	
	public String paigouRule(){
		
		return "paigouRule";
	}
	
	public String paigouFlow(){
		
		return "paigouFlow";
	}
	
	public String questionDetail(){
		
		return "questionDetail";
	}
	
	public String agreement(){
		
		return "agreement";
	}
	
	public String genuinetwo(){
		
		return "genuinetwo";
	}
	
	public String genuine(){
		
		return "genuine";
	}
	
	public String securepayment(){
		
		return "securepayment";
	}
	
	public String ship(){
		
		return "ship";
	}
	
	public String suggestion(){
		
		return "suggestion";
	}
	
	public String doSuggestion(){
		request = Struts2Utils.getRequest();
		try {
			Cookie[] cookies = request.getCookies();
	    	if(request.isRequestedSessionIdFromCookie()){
	    		for (int i = 0; i < cookies.length; i++) {
	    			Cookie cookie = cookies[i];
	    			if(cookie.getName().equals("rndCode")){
	    				String rndCode = cookie.getValue();
	    				if(rnd.trim().equalsIgnoreCase(rndCode)){
	    					sysConfigureService.doSuggestion(suggestion);
	    					Struts2Utils.render("text/html", "<script>alert(\"提交成功！感谢您的投诉建议。\");window.location.href=\"/index.html\";</script>","encoding:UTF-8");
	    				}else{
	    					Struts2Utils.render("text/html", "<script>alert(\"验证码输入错误！\");history.go(-1);</script>","encoding:UTF-8");
	    				}
	    			}
	    		}
	    	}
		} catch (Exception e) {
			e.printStackTrace();
			Struts2Utils.render("text/html", "<script>alert(\"操作失败，请联系管理员！\");history.go(-1);</script>","encoding:UTF-8");
		}
		
		return null;
	}
	
	public String deliveryFees(){
		
		return "deliveryFees";
	}
	
	public String prodCheck(){
		
		return "prodCheck";
	}
	
	public String shiptwo(){
		
		return "shiptwo";
	}
	
	public String privacy(){
		
		return "privacy";
	}
	
	public String userExperience(){
		
		return "userExperience";
	}
	
	public String qqgroup(){
		
		return "qqgroup";
	}

	public Suggestion getSuggestion() {
		return suggestion;
	}

	public void setSuggestion(Suggestion suggestion) {
		this.suggestion = suggestion;
	}

	public String getRnd() {
		return rnd;
	}

	public void setRnd(String rnd) {
		this.rnd = rnd;
	}
	
}
