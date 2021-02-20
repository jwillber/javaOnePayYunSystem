<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.eypg.util.ApplicationListenerImpl"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <meta name="decorator" content="index_template" />
    <title>购物车_<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%> 一元<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %> <%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>网</title>
     <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <meta content="app-id=518966501" name="apple-itunes-app" />
    <meta content="yes" name="apple-mobile-web-app-capable" />
    <meta content="black" name="apple-mobile-web-app-status-bar-style" />
    <meta content="telephone=no" name="format-detection" />
    <link href="/css/cartlist.css" rel="stylesheet" type="text/css" />
    <script src="/js/payment.js" language="javascript" type="text/javascript"></script>
  </head>
  
  <body>
<div class="h5-1yyg-v1">
    
<!-- 栏目页面顶部 -->


<!-- 内页顶部 -->

    <header class="g-header">
        <div class="head-l">
	        <a href="javascript:;" onclick="history.go(-1)" class="z-HReturn"><s></s><b>返回</b></a>
        </div>
        <h2>结算支付</h2>
        <div class="head-r">
	        
        </div>
    </header>

    <section class="clearfix g-pay-lst">
		<ul>
		<s:iterator var="productCarts" value="productCartList">
			<li>
			    <a href="/products/${productCarts.productId }.html" class="gray6">(第${productCarts.productPeriod}期)${productCarts.productName }</a>
			    <span>
			        <em class="orange arial">${productCarts.count }</em>人次
			    </span>
			</li>
		</s:iterator>
		</ul>
		<p class="g-pay-Total gray9">合计：<span class="orange arial Fb F16">${productCarts.moneyCount }.00</span> 元</p>
		<p class="g-pay-bline"></p>
    </section>
    <input name="hidShopMoney" type="hidden" id="hidShopMoney" value="${productCarts.moneyCount }" />
    <input name="hidBalance" type="hidden" id="hidBalance" value="${user.balance }" />
    <input name="hidPoints" type="hidden" id="hidPoints" value="${user.commissionPoints }" />
    <section class="clearfix g-Cart">
	    <article class="clearfix m-round g-pay-ment">
		    <ul id="ulPayway">
		    	<c:choose>
		    		<c:when test="${(user.commissionPoints/100)>=1}">
		    			<li class="gray6 z-pay-ff"><i id="spPoints" class="z-pay-mentsel" sel="1"></i><span>福分支付<em class="orange"><fmt:formatNumber value="${(user.commissionPoints/100)}" type="number" maxFractionDigits="0" pattern="0" />.00</em>元（您的福分：${user.commissionPoints }）</span></li>
		    		</c:when>
		    		<c:otherwise>
		    			<li class="gray6 z-pay-ff z-pay-grayC"><i id="spPoints" class="z-pay-mentDis" sel="0"></i><span>您的福分不足（您的福分：${user.commissionPoints }）</span></li>
		    		</c:otherwise>
		    	</c:choose>
			    <c:choose>
			    	<c:when test="${user.balance>=productCarts.moneyCount}">
			    		<li class="gray6 z-pay-ye"><i id="spBalance" class="z-pay-mentsel" sel="1"></i><span>余额支付<em class="orange"><fmt:formatNumber value="${(productCarts.moneyCount-(user.commissionPoints/100))}" type="number" maxFractionDigits="0" pattern="0" />.00</em>元（账户余额：${user.balance } 元）</span></li>
			    	</c:when>
			    	<c:otherwise>
			    		<li class="gray6 z-pay-ye z-pay-grayC"><a href="/member/recharge.do" class="z-pay-Recharge">去充值</a><span>您的余额不足（账户余额：${user.balance } 元）</span></li>
			    	</c:otherwise>
			    </c:choose>
			    
		    </ul>
	    </article>
	    <article id="bankList" class="clearfix mt10 m-round g-pay-ment g-bank-ct">
		    <ul>
			    <li class="gray6 z-pay-grayC"><s class="z-arrow"></s>选择网银支付</li>
                <li class="gray9 hidden"><i class="z-bank-Roundsel"><s></s></i>网银+</li>
                <li class="gray9 hidden"><i class="z-bank-Round"><s></s></i>微信支付</li>
                <li class="gray9 hidden"><i class="z-bank-Round"><s></s></i>银联在线</li>
            </ul>
	    </article>
	    <div class="g-Total-bt">
		    <a id="btnPay" href="javascript:;" class="orgBtn">确认支付</a>
	    </div>
    </section>
</div>
</body>
</html>
