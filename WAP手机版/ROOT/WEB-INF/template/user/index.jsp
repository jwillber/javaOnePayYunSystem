<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.eypg.util.ApplicationListenerImpl"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <meta name="decorator" content="index_template" />
    <title>我的<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>中心_<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%> 一元<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %> <%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>网</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, user-scalable=no, maximum-scale=1.0"/>
    <meta content="yes" name="apple-mobile-web-app-capable" />
    <meta content="black" name="apple-mobile-web-app-status-bar-style" />
    <meta content="telephone=no" name="format-detection" />
    <link href="/css/member.css" rel="stylesheet" type="text/css" />
  </head>
  
<body>
 <div class="h5-1yyg-v11">
    
<!-- 栏目页面顶部 -->


<!-- 内页顶部 -->

    <header class="g-header">
        <div class="head-l">
	        <a href="javascript:;" onclick="history.go(-1)" class="z-HReturn"><s></s><b>返回</b></a>
        </div>
        <h2>我的<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %></h2>
        <div class="head-r">
	        <a href="/" class="z-Home"></a>
        </div>
    </header>

    <section class="clearfix g-member">
	    <div class="clearfix m-round m-name"><div class="fl f-Himg"><a href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/u/${user.userId }.html" class="z-Himg">
	    <img src="${user.faceImg }" border=0 /></a>
	    <s:if test="user.experience<10000">
            <span class="z-class-icon01 gray02"><s></s><%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>小将</span>
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
	    </div>
	    <div class="m-name-info"><p class="u-name"><b class="z-name gray01">${user.userName }</b><em>(
	    <s:if test="user.phone!=null">
	        ${user.phone }
        </s:if>
        <s:else>
        	 ${user.mail }
        </s:else>)</em></p><ul class="clearfix u-mbr-info">
	    <li>可用福分 <span class="orange">${user.commissionPoints }</span></li><li>经验值 <span class="orange">${user.experience }</span></li>
	    <li>余额 <span class="orange">￥${user.balance}</span><a href="/user/UserRecharge.html" class="fr z-Recharge-btn">去充值</a></li></ul></div></div>
	    <div class="m-round m-member-nav">
		    <ul id="ulFun">
			    <li><a href="/user/UserBuyList.html"><b class="z-arrow"></b>我的<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>记录</a></li>
			    <li><a href="/user/OrderList.html"><b class="z-arrow"></b>获得的商品</a></li>
                <li><a href="/user/MemberPoints.html"><b class="z-arrow"></b>我的福分</a></li>
                <li><a href="/user/CommissionQuery.html"><b class="z-arrow"></b>佣金明细</a></li>
			    <li><a href="/user/PostSingleList.html"><b class="z-arrow"></b>我的晒单</a></li>
			    <li><a href="/user/UserBalance.html"><b class="z-arrow"></b>帐户明细</a></li>
		    </ul>
	    </div>
    </section>
</div>
</body>
</html>
