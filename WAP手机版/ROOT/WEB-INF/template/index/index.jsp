<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.eypg.util.ApplicationListenerImpl"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<meta name="decorator" content="index_template" />
	    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, user-scalable=no, maximum-scale=1.0"/>
	    <meta content="yes" name="apple-mobile-web-app-capable" />
	    <meta content="black" name="apple-mobile-web-app-status-bar-style" />
	    <meta content="telephone=no" name="format-detection" />
	    <link href="/Images/touch-icon.png" rel="apple-touch-icon-precomposed" />
	    <link href="favicon.ico" rel="shortcut icon" />
	    <link href="/css/index.css" rel="stylesheet" type="text/css" />
    	<script language="javascript" type="text/javascript" src="/js/index.js"></script>
		<title><%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%> <%=ApplicationListenerImpl.sysConfigureJson.getSaitTitle() %></title>
	</head>

	<body>
		<div id="loadingPicBlock" class="h5-1yyg-v1">
<!-- 栏目页面顶部 -->
    <header class="header">
        <h1 class="fl"><span><%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%></span><a href="/"></a></h1>
        <div class="fl u-slogan"></div>
        <div class="fr head-r">
            <a class="z-Member" href="/user/index.html"></a>
            <a class="z-shop" href="/mycart/index.html" id="btnCart"></a>
        </div>
    </header>
    <!-- 栏目导航 -->
    <nav class="g-snav u-nav">
	    <div class="g-snav-lst"><a class="nav-crt" href="/">首页</a> <s class="z-arrowh"></s></div>
	    <div class="g-snav-lst"><a href="/list/hot20.html">所有商品</a></div>
	    <div class="g-snav-lst"><a href="/lottery/index.html">最新揭晓</a></div>
	    <div class="g-snav-lst"><a href="/share/new20.html">晒单</a></div>
    </nav>


<!-- 内页顶部 -->
    <input type="hidden" value="0" id="hidStartAt" name="hidStartAt" />
    <!-- 焦点图 -->
    <section id="sliderBox" class="hotimg"></section>
    <!-- 最新揭晓 -->
    <section class="g-main">
	    <div class="m-tt1">
		    <h2 class="fl"><a href="/lottery/index.html">最新揭晓</a></h2>
		    <div class="fr u-more">
			    <a class="u-rs-m1" href="/lottery/index.html"><b class="z-arrow"></b></a>
		    </div>
	    </div>
	    <article class="h5-1yyg-w310 m-round m-lott-li" id="divLottery">
<!--	    <div id="154145" class="m-lott-conduct"><p class="z-lott-tt">(第2980期)小米（MIUI） 红米1S 3G手机 移动版 <b class="z-arrow"></b><span class="z-lott-time">揭晓倒计时<span class="minute">00</span>:<span class="second">17</span>:<span class="millisecond">7</span><span class="last">6</span></span></p></div>-->
<!--	    <div id="154415" class="m-lott-conduct"><p class="z-lott-tt">(第9855期)小米（MIUI）10400mAh移动电源<b class="z-arrow"></b><span class="z-lott-time">正在计算,请稍后...</span></p></div>-->
<!--	    <div id="130949" class="m-lott-conduct"><p onclick="location.href='/lottery/detail-130949.html'">恭喜<span class="z-user blue">不会中奖的命</span>获得 (第596期)闪迪（SanDisk）酷捷 (CZ51)16GB U盘 <b class="z-arrow"></b></p></div>-->
		    <ul class="clearfix">
		        <s:iterator var="latestlotterys" value="latestlotteryList">
			        <li>
		                <a class="u-lott-pic" href="/lotteryDetail/${latestlotterys.spellbuyProductId }.html">
		                	<img border="0" class="scrollLoading" data-url="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>${latestlotterys.productImg }" src="/Images/pixel.gif" />
		                </a>
		                <span>恭喜<a class="blue z-user" href="/u/${latestlotterys.userId }.html">${latestlotterys.buyUser}</a>获得</span>
			        </li>
		        </s:iterator>
		    </ul>
	    </article>
    </section>
    <!-- 热门推荐 -->
    <section class="g-main">
	    <div class="m-tt1">
		    <h2 class="fl"><a href="/list/m1.html">热门推荐</a></h2>
		    <div class="fr u-more">
			    <a class="u-rs-m1" href="/list/m1.html"><b class="z-arrow"></b></a>
		    </div>
	    </div>
	    <article class="clearfix h5-1yyg-w310 m-round m-tj-li">
		    <ul id="ulRecommend">
		        <s:iterator var="hotProduct" value="hotProductList" status="index">
			        <li id="${hotProduct.productId }">
			            <div class="f_bor_tr">
			                <div class="m-tj-pic">
			                    <a class="u-lott-pic" href="/products/${hotProduct.productId }.html">
			                        <img border="0" alt="" class="scrollLoading" data-url="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>${hotProduct.headImage }" src="/Images/pixel.gif" />
			                    </a>
			                    <ins class="u-promo">价值:￥${hotProduct.productPrice}.00</ins>
			                </div>
			                <div class="Progress-bar"><p title="已完成<fmt:formatNumber type="number" value="${(hotProduct.currentBuyCount/hotProduct.productPrice)*100 } " maxFractionDigits="2"/>%" class="u-progress">
			                <span style="width:<fmt:formatNumber type="number" value="${(hotProduct.currentBuyCount/hotProduct.productPrice)*100 } " maxFractionDigits="2"/>%;" class="pgbar">
			                <span class="pging"></span></span></p><ul class="Pro-bar-li"><li class="P-bar01"><em>${hotProduct.currentBuyCount}</em>已参与</li>
			                <li class="P-bar02"><em>${hotProduct.productPrice}</em>总需人次</li>
			                <li class="P-bar03"><em>${hotProduct.productPrice - hotProduct.currentBuyCount}</em>剩余</li></ul></div>
			            </div>
                    </li>
                </s:iterator>
		    </ul>
	    </article>
    </section>
    

</div>
<script language="javascript" type="text/javascript">
var $indexImg = ${indexImgList };
</script>
    <script language="javascript" type="text/javascript" src="/js/jquery.scrollloading-min.js"></script>
	</body>
</html>
