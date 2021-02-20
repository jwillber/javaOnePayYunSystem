<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.eypg.util.ApplicationListenerImpl"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <meta name="decorator" content="index_template" />
    <title>${latestlottery.productName }${latestlottery.productTitle }_<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%> 一元<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %> <%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>网</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta content="app-id=518966501" name="apple-itunes-app" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, user-scalable=no, maximum-scale=1.0" />
    <meta content="yes" name="apple-mobile-web-app-capable" />
    <meta content="black" name="apple-mobile-web-app-status-bar-style" />
    <meta content="telephone=no" name="format-detection" />
    <link href="/css/goods.css" rel="stylesheet" type="text/css" />
    <script src="/js/lotterydetail.js" language="javascript" type="text/javascript"></script>
  </head>
<body >
<div class="h5-1yyg-v1" id="loadingPicBlock">
    
<!-- 栏目页面顶部 -->
<!-- 内页顶部 -->

    <header class="g-header">
        <div class="head-l">
	        <a href="javascript:;" onclick="history.go(-1)" class="z-HReturn"><s></s><b>返回</b></a>
        </div>
        <h2>揭晓结果</h2>
        <div class="head-r">
	        <a href="/" class="z-Home"></a>
        </div>
    </header>

    <input name="hidGoodsID" type="hidden" id="hidGoodsID" />
    <input name="hidCodeID" type="hidden" id="hidCodeID" />
    <section class="goodsCon pCon">
	    <!-- 导航 -->
        <div id="divPeriod" class="pNav">
            <div class="loading"><b></b>正在加载</div>
    	    <ul class="slides">
    	    	<c:forEach items="${productPeriodList}" var="entry" varStatus="i" begin="0" end="18">
			    	<c:choose>
			    		<c:when test="${entry.key==latestlottery.productPeriod}">
				    		<li class="cur"><a href="javascript:;">第${latestlottery.productPeriod }期</a><b></b></li>
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
        
        <!-- 揭晓信息 -->
        
        <div class="pProcess pProcess2">
    	    <div class="pResults">
        	    <div class="pResultsL" onclick="location.href='/u/${latestlottery.userId }.html'">
            	    <a>
            	        <img src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>${latestlottery.userFace }" />
            	        <span>${latestlottery.userName }</span>
            	    </a>
                    <s></s>
                </div>
        	    <div class="pResultsR">
                    <div class="g-snav">
                        <div class="g-snav-lst">总共<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %><br /><dd><b class="orange">${latestlottery.buyNumberCount }</b><br />人次</dd></div>
                        <div class="g-snav-lst">揭晓时间<br /><dd class="gray9"><span>${fn:substring(latestlottery.announcedTime,0,10) }<br />${fn:substring(latestlottery.announcedTime,11,23) }</span></dd></div>
                        <div class="g-snav-lst"><%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>时间<br /><dd class="gray9"><span>${fn:substring(latestlottery.buyTime,0,10) }<br />${fn:substring(latestlottery.buyTime,11,23)}</span></dd></div>
                    </div>
                </div>
        	    <p><a href="/lotteryDetail/calResult-${latestlottery.spellbuyProductId }.html" class="fr">查看计算结果</a>幸运<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>码：<b class="orange">${latestlottery.randomNumber}</b></p>
            </div>
        </div>
        

        <!-- 产品图 -->
        <div class="pPic pPicBor">
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
        <div class="pDetails pDetails-end">
                <b>(第${latestlottery.productPeriod }期)${latestlottery.productName } <span></span></b>
                <p class="price">价值：<em class="arial gray">￥${latestlottery.productPrice }.00</em></p>
                <div class="pClosed">本期已揭晓</div><div class="pOngoing" codeid="${productJSON.productId }">第<em class="arial">${productJSON.productPeriod }</em>期 正在进行中……<span class="fr">查看详情</span></div>
        </div>
        
        <!-- 参与记录，商品详细，晒单导航 -->
        <div class="joinAndGet">
    	    <dl>
    	        <a href="/products/buyrecords-${latestlottery.spellbuyProductId }.html"><b class="fr z-arrow"></b>所有<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>记录</a>
    	        <a href="/products/goodsdesc-${latestlottery.spellbuyProductId }.html"><b class="fr z-arrow"></b>图文详情<em>（建议WIFI下使用）</em> </a>
    	        <a href="/products/goodspost-${latestlottery.productId }.html"><b class="fr z-arrow"></b>已有<span class="orange arial">${pageCount}</span>个幸运者晒单
<!--    	        <strong class="orange arial">24839</strong>条评论-->
    	        </a>
            </dl>
            <!-- 上期获得者 -->
            
        </div>
    </section>
</div>
</body>
</html>