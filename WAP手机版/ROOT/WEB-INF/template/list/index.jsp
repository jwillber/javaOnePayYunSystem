<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.eypg.util.ApplicationListenerImpl"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<meta name="decorator" content="index_template" />
		<title><%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>_商品列表页 <%=ApplicationListenerImpl.sysConfigureJson.getShortName() %> <%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>网,1元购物,1元购,一元购物</title>
		<meta content="app-id=518966501" name="apple-itunes-app" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, user-scalable=no, maximum-scale=1.0" />
		<meta content="yes" name="apple-mobile-web-app-capable" />
		<meta content="black" name="apple-mobile-web-app-status-bar-style" />
		<meta content="telephone=no" name="format-detection" />
		<link href="/css/goods.css" rel="stylesheet" type="text/css" />
		<script src="/js/goodslist.js" language="javascript" type="text/javascript"></script>
	</head>

	<body>
		<div class="h5-1yyg-v1" id="loadingPicBlock">
    
<!-- 栏目页面顶部 -->

    <header class="header">
        <h1 class="fl"><span>一元云购</span><a href="/"></a></h1>
        <div class="fl u-slogan"></div>
        <div class="fr head-r">
            <a href="/user/index.html" class="z-Member"></a>
            <a id="btnCart" href="/mycart/index.html" class="z-shop"></a>
        </div>
    </header>
    <!-- 栏目导航 -->
    <nav class="g-snav u-nav">
	    <div class="g-snav-lst"><a href="/">首页</a> </div>
	    <div class="g-snav-lst"><a class="nav-crt" href="/list/hot20.html">所有商品</a> <s class="z-arrowh"></s></div>
	    <div class="g-snav-lst"><a href="/lottery/index.html">最新揭晓</a></div>
	    <div class="g-snav-lst"><a href="/share/new20.html">晒单</a></div>
    </nav>
<!-- 内页顶部 -->
    <input name="hidOrderFlag" type="hidden" id="hidOrderFlag" value="about20" />
    <section class="goodsCon">
	    <!-- 导航 -->
	    <div class="goodsNav">
            <ul id="divGoodsNav">
       	 	    <li class="current" order="about20"><a href="javascript:;">即将揭晓<b></b></a></li>
                <li order="hot20"><a href="javascript:;">人气<b></b></a></li>
                <li order="date20"><a href="javascript:;">最新<b></b></a></li>
                <li type="price" order="priceAsc20"><a href="javascript:;">价值<em></em><s></s><b></b></a></li>
                <li order="" class="">
                    <a href="javascript:;">全部<s class="arrowUp"></s></a>
                    <dl style="display: none;">
                        <dd class="sOrange" type=""><a href="javascript:;"><b class="g1"></b>全部</a></dd>
                        <dd type="1001"><a href="javascript:;"><b class="g2"></b>手机数码</a></dd>
                        <dd type="1002"><a href="javascript:;"><b class="g3"></b>电脑办公</a></dd>
                        <dd type="1003"><a href="javascript:;"><b class="g4"></b>家用电器</a></dd>
                        <dd type="1006"><a href="javascript:;"><b class="g5"></b>礼品箱包</a></dd>
                        <dd type="1005"><a href="javascript:;"><b class="g6"></b>钟表首饰</a></dd>
                        <dd type="1007"><a href="javascript:;"><b class="g7"></b>其他商品</a></dd>
                    </dl>
                </li>
           </ul>
        </div>
        <!-- 列表 -->
        <div class="goodsList">
            <div style="display: none;" class="loading" id="divGoodsLoading"><b></b>正在加载</div>
            <a style="display:none;" href="javascript:;" class="loading" id="btnLoadMore">点击加载更多</a>
        </div>
    </section>
 
 </div>
	</body>
</html>
