<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.eypg.util.ApplicationListenerImpl"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <meta name="decorator" content="template_footer" />
    <title>���ﳵ_<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%> һԪ<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %> <%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>��</title>
    <link rel="stylesheet" type="text/css" href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/cartlist.css" />
  </head>
  
  <body>
 	<form name="payForm" action="https://shenghuo.alipay.com/send/payment/fill.htm" method="post">
	<input name="optEmail" id="optEmail"  value="<%=ApplicationListenerImpl.sysConfigureJson.getAliPayUser()%>"  type="hidden" />
	<input name="payAmount" id="payAmount" value="${moneyCount }" type="hidden" />
	<input name="title" id="title" value="<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>" type="hidden" />
	<input name="title" id="title" value="${out_trade_no }" type="hidden" />
	<input name="memo" id="memo" value="�벻Ҫ�޸ĸ���˵�����ݣ�����Ӱ��ϵͳ������" type="hidden" />
	</form><script>document.payForm.submit();</script>
</body>
</html>
