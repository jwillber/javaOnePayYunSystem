<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.eypg.util.ApplicationListenerImpl"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <meta name="decorator" content="index_template" />
    <title>最新揭晓_<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%></title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, user-scalable=no, maximum-scale=1.0" />
	<meta content="app-id=518966501" name="apple-itunes-app" />
	<meta content="yes" name="apple-mobile-web-app-capable" />
	<meta content="black" name="apple-mobile-web-app-status-bar-style" />
	<meta content="telephone=no" name="format-detection" />
	<link href="/css/lottery.css" rel="stylesheet" type="text/css" />
  </head>
  
  <body  class="lottery"  id="loadingPicBlock">
	<div class="h5-1yyg-v1" id="loadingPicBlock">
    
<!-- 栏目页面顶部 -->

    <header class="header">
        <h1 class="fl"><span><%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%></span><a href="/"></a></h1>
        <div class="fl u-slogan"></div>
        <div class="fr head-r">
            <a href="/user/index.html" class="z-Member"></a>
            <a id="btnCart" href="/mycart/index.html" class="z-shop"></a>
        </div>
    </header>
    <!-- 栏目导航 -->
    <nav class="g-snav u-nav">
	    <div class="g-snav-lst"><a href="/" >首页</a></div>
	    <div class="g-snav-lst"><a href="/list/hot20.html" >所有商品</a></div>
	    <div class="g-snav-lst"><a href="/lottery/index.html" class="nav-crt">最新揭晓</a> <s class="z-arrowh"></s></div>
	    <div class="g-snav-lst"><a href="/share/new20.html" >晒单</a></div>
    </nav>


<!-- 内页顶部 -->

    <section class="revealed">
	    <div id="divLottery" class="revCon">
            <div id="divLotteryLoading" class="loading"><b></b>正在加载</div>
            <a id="btnLoadMore" class="loading" href="javascript:;" style="display:none;">点击加载更多</a>
        </div>
    </section>
    
</div>
 	<script src="/js/lotteryfun.js" language="javascript" type="text/javascript"></script>
<!--	<script type="text/javascript" src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/lotterytime.js"></script>-->
  </body>
</html>
