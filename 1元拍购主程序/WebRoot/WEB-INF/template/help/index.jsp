<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.eypg.util.ApplicationListenerImpl"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <meta name="decorator" content="index_template" />
    <title><%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>_帮助中心 一元<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %> <%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>网</title>
	<link rel="stylesheet" type="text/css" href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/newbie.css" />
  </head>
  
  <body class="helper">
 <div class="GuideContent clearfix">
	<div class="GuideW">
		<div class="Guidehead">
			<p><img border="0" alt="" src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/Images/Guide_1.jpg" /></p>
			<p><img border="0" alt="" src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/Images/Guide_2.jpg" /></p>
		</div>
		<div class="Guidebor clearfix">
			<ul class="Guide-Explain clearfix">
				<li class="Explain-l ExplainA"><p>选择一款商品，点击“立即<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>”</p><img border="0" alt="" src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/Images/Guide_3.jpg" /></li>
				<li class="Explain-r Explainimg"><img border="0" alt="" src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/Images/Guide_4.jpg" /></li>
			</ul>
			<div class="Guide-Separate">
				<img border="0" alt="" src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/Images/Guide_5.jpg" />
			</div>
			<ul class="Guide-Explain clearfix">
				<li class="Explain-l Explainimg"><img border="0" alt="" src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/Images/Guide_6.jpg" /></li>
				<li class="Explain-r ExplainB"><p>支付1元，购买1人次，获得1个“<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>码”</p><img border="0" alt="" src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/Images/Guide_7.jpg" /></li>
			</ul>
			<div class="Guide-Separate">
				<img border="0" alt="" src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/Images/Guide_5.jpg" />
			</div>
			<ul class="Guide-Explain clearfix">
				<li class="Explain-l ExplainC"><p>当一件商品达到总参与人次，抽出1名商品获得者；</p><img border="0" alt="" src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/Images/Guide_8.jpg" /></li>
				<li class="Explain-r Explainimg"><img border="0" alt="" src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/Images/Guide_9.jpg" /></li>
			</ul>
			<div class="Guide-Separate">
				<img border="0" alt="" src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/Images/Guide_5.jpg" />
			</div> 
			<div class="Rule clearfix">
				<h2><img border="0" alt="" src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/Images/Guide_10.jpg" /></h2>
				<ul class="Rule-Exp">
					<li>每件商品参考市场价平分成相应“等份”，每份1元，1份对应1个<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>码。</li>
					<li>同一件商品可以购买多次或一次购买多份。</li>
					<li>当一件商品所有“等份”全部售出后计算出“幸运<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>码”，拥有“幸运<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>码”者即可获得此商品。</li>
				</ul>
			</div>
			<div class="Rule clearfix Rulemat">
				<h2><img border="0" alt="" src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/Images/Guide_11.jpg" /></h2>
				<ul class="Rule-Exp">
					<li>取该商品最后购买时间前网站所有商品50条购买时间记录。</li>
					<li>时间按时、分、秒、毫秒依次排列组成一组数值。如20:15:25.362则为201525362）将这50个时间的数值进行求和（得出数值A）</li>
					<li>为保证公平公正公开，系统还会等待一小段时间，取最近下一期中国福利彩票“时时彩”的开奖结果（一个五位数值B）</li>
					<li>（数值A+数值B）除以该商品总参与人次后取余数 + 原始数 10000001，得到最终幸运号码，拥有该幸运号码者，直接获得该商品。</li>
					<li>注：最后一个号码分配时间距离“时时彩”最近下一期开奖大于24小时，默认“时时彩”开奖结果为最后一期开奖号码。 </li>
				</ul>
			</div>
			<div class="Rule-line"></div>
			<div class="Rule-button">
				<a target="_blank" href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>"><img border="0" alt="" src="<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>/Images/Guide_14.jpg" /></a>
			</div>
		</div>
		<div class="Rule-Anglebg">
		</div>
		<div class="clear"></div>
	</div>
</div>
  </body>
</html>
