<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.eypg.util.ApplicationListenerImpl"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
  	<meta name="decorator" content="index_template" />
    <title>会员登录_<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%> 一元<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %> <%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>网</title>
    <meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
    <meta content="app-id=518966501" name="apple-itunes-app" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, user-scalable=no, maximum-scale=1.0" />
    <meta content="yes" name="apple-mobile-web-app-capable" />
    <meta content="black" name="apple-mobile-web-app-status-bar-style" />
    <meta content="telephone=no" name="format-detection" />
    <link href="/css/login.css" rel="stylesheet" type="text/css" />
    <script src="/js/login.js" language="javascript" type="text/javascript"></script>
  </head>
  
  <body>
   <div class="h5-1yyg-v1" id="content">
   <input type="hidden" id="forward" value="${forward }"/>
<!-- 栏目页面顶部 -->


<!-- 内页顶部 -->

    <header class="g-header">
        <div class="head-l">
	        <a href="javascript:;" onclick="history.go(-1)" class="z-HReturn"><s></s><b>返回</b></a>
        </div>
        <h2>登录</h2>
        <div class="head-r">
	        <a href="/" class="z-Home"></a>
        </div>
    </header>

        <section>
	        <div class="registerCon">
    	        <ul>
        	        <li class="accAndPwd">
            	        <dl><input id="txtAccount" type="text" placeholder="请输入您的手机号码/邮箱" class="lEmail" /><s class="rs4"></s></dl>
                        <dl>
                            <input type="password" id="txtPassword" class="lPwd" placeholder="密码" />
                            <s class="rs3"></s>
                        </dl>
                    </li>
                    <li><a href="javascript:;" id="btnLogin" class="nextBtn  orgBtn">登 录</a><input name="forward" type="hidden" id="forward" value="/user/index.html" /></li>
                    <li class="rSelect"><a href="/getbackpwd/index.html">忘记密码？</a><b></b><a href="/register/index.html">新用户注册</a></li>
                </ul>
	        </div>
        </section>
</div>
</body>
</html>
