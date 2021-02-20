<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.eypg.util.ApplicationListenerImpl"%>
<%@ taglib prefix="s" uri="/struts-tags"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		    <meta name="decorator" content="index_template" />
		<title>支付结果_<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%></title>
		<meta content="app-id=518966501" name="apple-itunes-app" />
	    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, user-scalable=no, maximum-scale=1.0" />
	    <meta content="yes" name="apple-mobile-web-app-capable" />
	    <meta content="black" name="apple-mobile-web-app-status-bar-style" />
	    <meta content="telephone=no" name="format-detection" />
		<link href="/css/cartlist.css" rel="stylesheet" type="text/css" />
		<script src="/js/cartlist.js" language="javascript" type="text/javascript"></script>
	</head>

<body>
    <div class="h5-1yyg-v1">
        
<!-- 栏目页面顶部 -->


<!-- 内页顶部 -->

    <header class="g-header">
        <div class="head-l">
	        <a href="/"  class="z-HReturn"><s></s><b>首页</b></a>
        </div>
        <h2><%=ApplicationListenerImpl.sysConfigureJson.getShortName()%>结果</h2>
        <div class="head-r">
	        
        </div>
    </header>
    	<c:choose>
   			<c:when test="${fn:length(successCartList)>0}">
   				<section class="clearfix g-pay-success" id="shopResultBox">
				<div class="g-pay-auto">
				<div class="z-pay-tips"><s></s><b><em class="gray6">支付成功，请等待揭晓！</em>成功支付${fn:length(successCartList)}个商品！</b></div></div>
				<div class="u-Btn"><div class="u-Btn-li"><a class="z-CloseBtn" href="/user/UserBuyList.html">查看<%=ApplicationListenerImpl.sysConfigureJson.getShortName()%>记录</a></div>
				<div class="u-Btn-li"><a class="z-DefineBtn" href="/">继续购物</a></div></div>
				<article class="clearfix mt10 m-round g-pay-successC">
				<ul class="">
				<s:iterator value="successCartList" var="successCart">
					<li onclick="location.href='/user/UserBuyDetail-${successCart.productId }.html'" class="gray6"><p class="gray9">${successCart.buyDate}</p>${successCart.productName }<span class="fr"><b class="z-arrow"></b><em class="orange">${successCart.buyCount}</em>人次</span></li>
				</s:iterator>
				</ul>
				</article>
				</section>
   			</c:when>
   			<c:otherwise>
	        <section id="shopResultBox" class="clearfix g-pay-success">
	            <div class="Submitting">
	    	        <h4>哎呀！<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>失败了。已经被人抢拍了吧。</h4>
	    	        <div class="loading-progress">
	    	            <span class="loading-pgbar">
	    	                <span class="loading-pging"></span>
	    	            </span>
	                </div>
	            </div>
	        </section>
   			</c:otherwise>
   		</c:choose>
<!--        <input name="hidShopID" type="hidden" id="hidShopID" value="123" />-->
<!--        <section id="shopResultBox" class="clearfix g-pay-success">-->
<!--            <div class="Submitting">-->
<!--    	        <h4>正在提交数据……</h4>-->
<!--    	        <div class="loading-progress">-->
<!--    	            <span class="loading-pgbar">-->
<!--    	                <span class="loading-pging"></span>-->
<!--    	            </span>-->
<!--                </div>-->
<!--            </div>-->
<!--        </section>-->
   </div>

</body>
</html>
