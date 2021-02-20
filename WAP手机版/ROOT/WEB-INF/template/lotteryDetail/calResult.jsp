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
    <link href="/css/lottery.css" rel="stylesheet" type="text/css" />
    <script src="/js/calresult.js" language="javascript" type="text/javascript"></script>
  </head>
<body>
<div class="h5-1yyg-v1">
    
<!-- 栏目页面顶部 -->


<!-- 内页顶部 -->

    <header class="g-header">
        <div class="head-l">
	        <a href="javascript:;" onclick="history.go(-1)" class="z-HReturn"><s></s><b>返回</b></a>
        </div>
        <h2>计算结果</h2>
        <div class="head-r">
	        <a id="btnCalMethod" href="javascript:;" class="z-RCornerBtn"><i></i>计算方法</a>
        </div>
    </header>

    <input name="hidCodeID" type="hidden" id="hidCodeID" value="${id }" />
    <input name="hidRaffleTime" type="hidden" id="hidRaffleTime" value="${latestlottery.announcedTime }" />
    <input name="hidBaseNum" type="hidden" id="hidBaseNum" value="${latestlottery.dateSum }" />
    <input name="hidCodeQuantity" type="hidden" id="hidCodeQuantity" value="${latestlottery.productPrice }" />
    <input name="hidYuShu" type="hidden" id="hidYuShu" value="${latestlottery.randomNumber-10000001}" />
    <input name="hidCodeRno" type="hidden" id="hidCodeRno" value="${latestlottery.randomNumber}" />
    <input name="sscNumber" type="hidden" id="sscNumber" value="${latestlottery.sscNumber }" />
    <input name="sscPeriod" type="hidden" id="sscPeriod" value="${latestlottery.sscPeriod }" />

    <section id="calResult" class="z-minheight">
        <div id="divGoodsLoading" class="loading loading2"><b></b>正在加载</div>
    </section>
    
</div>
</body>
</html>