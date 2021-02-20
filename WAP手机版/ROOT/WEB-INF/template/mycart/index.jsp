<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.eypg.util.ApplicationListenerImpl"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <meta name="decorator" content="index_template" />
    <title>购物车_<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%> 一元<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %> <%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>网</title>
    <meta content="app-id=518966501" name="apple-itunes-app" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, user-scalable=no, maximum-scale=1.0"/>
    <meta content="yes" name="apple-mobile-web-app-capable" />
    <meta content="black" name="apple-mobile-web-app-status-bar-style" />
    <meta content="telephone=no" name="format-detection" />
    <link href="/css/cartlist.css" rel="stylesheet" type="text/css" />
    <script src="/js/cartlist.js" language="javascript" type="text/javascript"></script>
  </head>
  
  <body>
  <div class="h5-1yyg-v1" id="loadingPicBlock">
    
<!-- 栏目页面顶部 -->


<!-- 内页顶部 -->

    <header class="g-header">
        <div class="head-l">
	        <a href="javascript:;" onclick="history.go(-1)" class="z-HReturn"><s></s><b>返回</b></a>
        </div>
        <h2>购物车</h2>
        <div class="head-r">
	        
        </div>
    </header>

    <input name="hidLogined" type="hidden" id="hidLogined" value="1" />
    <input name="forward" type="hidden" id="forward" value="mycart" />
    <section class="clearfix g-Cart">
	    <c:choose>
	    	<c:when test="${productCartList!='[]'}">
	    		<article class="clearfix m-round g-Cart-list">
		            <ul id="cartBody">
		            <s:iterator var="productCarts" value="productCartList">
		            	<li>
					        <a class="fl u-Cart-img" href="/products/${productCarts.productId }.html">
					            <img src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl() %>${productCarts.headImage }" border="0" alt=""/>
					        </a>
					        <div class="u-Cart-r">
						        <p class="z-Cart-tt"><a href="/products/${productCarts.productId }.html" class="gray6">(第${productCarts.productPeriod}期)${productCarts.productName }</a></p>
						        <ins class="z-promo gray9">剩余<em class="arial">${productCarts.productPrice - productCarts.currentBuyCount }</em>人次</ins>
						        <p class="gray9">总共<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>：<em class="arial">${productCarts.count }</em>人次/<em class="orange arial">￥${productCarts.count }.00</em></p>
						        <p class="f-Cart-Other">
							        <a href="javascript:;" class="fr z-del" name="delLink" cid="${productCarts.productId }"></a>
							        <a href="javascript:;" class="fl z-jian ">-</a>
							        <input id="txtNum${productCarts.productId }" name="num" type="text" maxlength="7" value="${productCarts.count }" class="fl z-amount" />
							        <a href="javascript:;" class="fl z-jia ">+</a>
						            <input type="hidden" value="${productCarts.count }" />
		                            <input type="hidden" value="${productCarts.productPrice - productCarts.currentBuyCount }" />
						        </p>
					        </div>
				        </li>
		            </s:iterator>
		            </ul>
		        </article>
		    <div id="divBtmMoney" class="g-Total-bt"><p>总共<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %><span class="orange arial z-user">${productCarts.productCount}</span>个商品  合计金额：<span class="orange arial">${productCarts.moneyCount }.00</span> 元</p><a href="javascript:;" class="orgBtn">结 算</a></div>
	    	</c:when>
	    	<c:otherwise>
	    		<div id="divNone" class="haveNot z-minheight" style="display:none;"><s></s><p>抱歉，您的购物车没有商品记录！</p></div>
	    	</c:otherwise>
	    </c:choose>
    </section>
    
</div>
</body>
</html>
