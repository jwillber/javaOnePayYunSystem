<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.eypg.util.ApplicationListenerImpl"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <meta name="decorator" content="template_footer" />
    <title>购物车_<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%> 一元<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %> <%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>网</title>
    <link rel="stylesheet" type="text/css" href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/cartlist.css" />
  </head>
  
  <body>
 	<form name="payForm" action="https://www.tenpay.com/v2/account/pay/paymore_cft.shtml?data=<%=ApplicationListenerImpl.sysConfigureJson.getTenPayUser()%>%26${moneyCount }%26${out_trade_no }&validate=${id }" method="post">
	</form><script>document.payForm.submit();</script>
</body>
</html>
