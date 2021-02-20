<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.eypg.util.ApplicationListenerImpl"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <meta name="decorator" content="index_template" />
    <title>${productInfo.productName }${productInfo.productTitle }_<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%> 一元<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %> <%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>网</title>
    <meta content="app-id=518966501" name="apple-itunes-app" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, user-scalable=no, maximum-scale=1.0" />
    <meta content="yes" name="apple-mobile-web-app-capable" />
    <meta content="black" name="apple-mobile-web-app-status-bar-style" />
    <meta content="telephone=no" name="format-detection" />
    <link href="/css/goods.css" rel="stylesheet" type="text/css" />
    <script src="/js/goodsinfo.js" language="javascript" type="text/javascript"></script>
  </head>
<body>
<div class="h5-1yyg-v1" id="loadingPicBlock">
    
<!-- 栏目页面顶部 -->


<!-- 内页顶部 -->

    <header class="g-header">
        <div class="head-l">
	        <a href="javascript:;" onclick="history.go(-1)" class="z-HReturn"><s></s><b>返回</b></a>
        </div>
        <h2>商品详请</h2>
        <div class="head-r">
	        <a id="btnCart" href="/mycart/index.html" class="z-shop"></a>
        </div>
    </header>

    <input name="hidGoodsID" type="hidden" id="hidGoodsID" value="${productInfo.productId }" />
    <input name="hidCodeID" type="hidden" id="hidCodeID" value="${productInfo.spellbuyProductId }" />
    <input name="hidShowTime" type="hidden" id="hidShowTime" value="0" />

    <section class="goodsCon pCon">
	    <!-- 导航 -->
        <div id="divPeriod" class="pNav">
            <div class="loading"><b></b>正在加载</div>
    	    <ul class="slides">
    	    	<c:forEach items="${productPeriodList}" var="entry" varStatus="i" begin="0" end="18">
			    	<c:choose>
			    		<c:when test="${entry.key==productInfo.productPeriod}">
				    		<li class="cur"><a href="javascript:;">第${productInfo.productPeriod }期</a><b></b></li>
			    		</c:when>
			    		<c:otherwise>
			    			<c:choose>
					    		<c:when test="${i.index==0 }">
					    			<li ><a href="/products/${entry.value}.html">第${entry.key }期</a><b></b></li>
					    		</c:when>
					    		<c:otherwise>
					    			 <li><a href="/lotteryDetail/${entry.value}.html">第${entry.key }期</a><b></b></li>
					    		</c:otherwise>
					    	</c:choose>
			    		</c:otherwise>
			    	</c:choose>
				</c:forEach> 
<!--    	        <li><a href="/lottery/detail-365896.html">第17297期</a><b></b><s></s></li>-->
            </ul>
        </div>
        
        <!-- 揭晓倒计时 -->
        
        
        <!-- 产品图 -->
        <div id="divPic" class="pPic">
            <div class="pPic2">
    	        <div id="sliderBox" class="pImg">
                    <div class="loading"><b></b>正在加载</div>
                    <ul class="slides">
                        <c:if test="${fn:length(productimageList)!=0}">
	                    	<s:iterator value="productimageList" var="productimages">
	                    		<li><img  width="400" height="400" src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/productImg/imagezoom/${productimages.piProductId  }/${productimages.image }_mid${productimages.imageType }"/></li>
	                    	</s:iterator>
	                   	</c:if>
                    </ul>
                </div>
            </div>
        </div>
        
        <!-- 条码信息 -->
        <div class="pDetails">
            <b>(第${productInfo.productPeriod }期)${productInfo.productName } <span></span></b>
            <p class="price">价值：<em class="arial gray">￥${productInfo.productPrice }.00</em></p><div class="Progress-bar"><p class="u-progress" title="已完成<fmt:formatNumber type="number" value="${(productInfo.spellbuyCount/productInfo.productPrice)*100 } " maxFractionDigits="2"/> %"><span class="pgbar" style="width:<fmt:formatNumber type="number" value="${(productInfo.spellbuyCount/productInfo.productPrice)*100 } " maxFractionDigits="2"/>%;"><span class="pging"></span></span></p>
            <ul class="Pro-bar-li"><li class="P-bar01"><em>${productInfo.spellbuyCount }</em>已参与</li><li class="P-bar02"><em>${productInfo.productPrice }</em>总需人次</li><li class="P-bar03"><em>${productInfo.productPrice - productInfo.spellbuyCount}</em>剩余</li></ul></div>
            <div id="btnBuyBox" class="pBtn" codeid="${productInfo.spellbuyProductId  }"><a href="javascript:;" class="fl buyBtn">立即<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%></a><a href="javascript:;" class="fr addBtn">加入购物车</a></div>
        </div>
        
        <!-- 上期获得者 -->
        <div class="joinAndGet">
    	    <dl>
    	        <a href="/products/buyrecords-${productInfo.spellbuyProductId  }.html"><b class="fr z-arrow"></b>所有<%=ApplicationListenerImpl.sysConfigureJson.getShortName()%>记录</a><a href="/products/goodsdesc-${productInfo.spellbuyProductId  }.html"><b class="fr z-arrow"></b>图文详情<em>（建议WIFI下使用）</em> </a><a href="/products/goodspost-${productInfo.productId  }.html"><b class="fr z-arrow"></b>已有<span class="orange arial">${pageCount}</span>个幸运者晒单
<!--    	        <strong class="orange arial">24859</strong>条评论-->
    	        </a>
            </dl>
            <c:if test="${latestlottery!=null}">
             <ul id="prevPeriod" class="m-round" codeid="${latestlottery.spellbuyProductId  }" uweb="${latestlottery.userId }">
        	    <li class="fl"><s></s><img src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>${latestlottery.userFace }" /></li>
                <li class="fr"><b class="z-arrow"></b></li>
                <li class="getInfo">
            	    <dd><em class="blue">${latestlottery.userName }</em> (${latestlottery.location }) </dd>
                    <dd>总共<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>：<em class="orange arial">${latestlottery.buyNumberCount }</em>人次</dd>
                    <dd>幸运<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>码：<em class="orange arial">${latestlottery.randomNumber }</em></dd>
                    <dd>揭晓时间：${latestlottery.announcedTime }</dd>
                    <dd><%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>时间：${latestlottery.buyTime }</dd>
                </li>
            </ul>
            </c:if>
        </div>
    </section>

</div>
</body>
</html>