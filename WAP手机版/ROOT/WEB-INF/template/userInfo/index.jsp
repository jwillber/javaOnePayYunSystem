<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.eypg.util.ApplicationListenerImpl"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <meta name="decorator" content="index_template" />
    <title>${userName }的个人主页_<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%> <%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>网</title>
	<meta content="app-id=518966501" name="apple-itunes-app" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, user-scalable=no, maximum-scale=1.0" />
    <meta content="yes" name="apple-mobile-web-app-capable" />
    <meta content="black" name="apple-mobile-web-app-status-bar-style" />
    <meta content="telephone=no" name="format-detection" />
    <link href="/css/star.css" rel="stylesheet" type="text/css" />
    <script src="/js/userindex.js" language="javascript" type="text/javascript"></script>
  </head>
  
  <body>
    <div class="h5-1yyg-v11">
        
<!-- 栏目页面顶部 -->


<!-- 内页顶部 -->

    <header class="g-header">
        <div class="head-l">
	        <a href="javascript:;" onclick="history.go(-1)" class="z-HReturn"><s></s><b>返回</b></a>
        </div>
        <h2>个人主页</h2>
        <div class="head-r">
	        
        </div>
    </header>

   <section>
	<div class="mainCon">
    	<div class="mBanner">
        	<ul>
            	<li class="mUserHead"><img src="${user.faceImg }"></li><li class="mUserInfo"><p>${user.userName }</p><b></b><br/>经验值：${user.experience }
            	<s:if test="user.experience<10000">
		            <span class="z-class-icon01"><s></s><%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>小将</span>
	            </s:if>
	            <s:elseif test="user.experience<50000">
	            	<span class="z-class-icon02"><s></s><%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>少将</span>
	            </s:elseif>
	            <s:elseif test="user.experience<100000">
	            	<span class="z-class-icon03"><s></s><%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>中将</span>
	            </s:elseif>
	            <s:elseif test="user.experience<500000">
	            	<span class="z-class-icon04"><s></s><%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>上将</span>
	            </s:elseif>
	            <s:elseif test="user.experience<1000000">
	            	<span class="z-class-icon05"><s></s><%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>少校</span>
	            </s:elseif>
	            <s:elseif test="user.experience<2000000">
	            	<span class="z-class-icon06"><s></s><%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>中校</span>
	            </s:elseif>
	            <s:elseif test="user.experience<5000000">
	            	<span class="z-class-icon07"><s></s><%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>上校</span>
	            </s:elseif>
            	</li>
            </ul>
        </div>
        <div class="g-snav" id="divMidNav">
        	<span class="g-snav-lst mCurr"><a href="javascript:;"><%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>记录</a></span>
            <span class="g-snav-lst"><a href="javascript:;">获得的商品</a></span>
            <span class="g-snav-lst"><a href="javascript:;">晒单</a></span>
        </div>
        <input name="hdUserID" type="hidden" id="hdUserID" value="${user.userId }" />
        <!--<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>记录-->
        <div id="divBuyRecord" class="mBuyRecord">
        
        </div>
        <!--获得商品-->
        <div id="divGetGoods" class="mBuyRecord" style="display:none">
   
        </div>
        <!--晒单-->
        <div id="divSingle" class="mSingle" style="display:none">
        	<ul>
             
            </ul>
        </div>
        <a id="btnLoadMore" class="loading" href="javascript:;" style="display:none;">点击加载更多</a>
        <div id="divLoading" class="loading"><b></b>正在加载</div>
    </div>
</section>
  </div>
</body>
</html>
