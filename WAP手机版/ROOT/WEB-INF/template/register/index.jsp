<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.eypg.util.ApplicationListenerImpl"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <meta name="decorator" content="index_template" />
    <title>会员注册_<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%> 一元<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %> <%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>网</title>
	<meta content="app-id=518966501" name="apple-itunes-app" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, user-scalable=no, maximum-scale=1.0" />
    <meta content="yes" name="apple-mobile-web-app-capable" />
    <meta content="black" name="apple-mobile-web-app-status-bar-style" />
    <meta content="telephone=no" name="format-detection" />
    <link href="/css/login.css" rel="stylesheet" type="text/css" />
    <script src="/js/register.js" language="javascript" type="text/javascript"></script>
  </head>
  
 <body>
    <div class="h5-1yyg-v1" id="content">
        
<!-- 栏目页面顶部 -->


<!-- 内页顶部 -->

    <header class="g-header">
        <div class="head-l">
	        <a href="javascript:;" onclick="history.go(-1)" class="z-HReturn"><s></s><b>返回</b></a>
        </div>
        <h2>注册</h2>
        <div class="head-r">
	        <a href="/" class="z-Home"></a>
        </div>
    </header>

        <section>
        <div class="registerCon">
	        <ul>
    	        <li><input id="userMobile" type="tel" placeholder="请输入您的手机号码" class="rText"><s class="rs1"></s></li>
                <li><a id="btnNext" class="nextBtn  orgBtn">下一步</a></li>
                <li><span id="isCheck"><em></em>我已阅读并同意</span><a href="/help/agreement.html"><%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>用户服务协议</a></li>
            </ul>
        </div>
        </section>
        
   </div>
 </body>
</html>
