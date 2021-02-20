<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.eypg.util.ApplicationListenerImpl"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
  	<meta name="decorator" content="index_template" />
    <title><%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>_帮助中心 一元<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %> <%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>网</title>
    <meta content="app-id=518966501" name="apple-itunes-app" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, user-scalable=no, maximum-scale=1.0"/>
    <meta content="yes" name="apple-mobile-web-app-capable" />
    <meta content="black" name="apple-mobile-web-app-status-bar-style" />
    <meta content="telephone=no" name="format-detection" />
    <link href="/css/help.css" rel="stylesheet" type="text/css" />
    <script src="/js/help.js" language="javascript" type="text/javascript"></script>
  </head>
  
  <body class="helper">
 <div class="h5-1yyg-v11">
    
<!-- 栏目页面顶部 -->


<!-- 内页顶部 -->

    <header class="g-header">
        <div class="head-l">
	        <a href="javascript:;" onclick="history.go(-1)" class="z-HReturn"><s></s><b>返回</b></a>
        </div>
        <h2>新手指南</h2>
        <div class="head-r">
	        <a href="/" class="z-Home"></a>
        </div>
    </header>

    <section>
	    <div class="helpCon">
    	    <div class="helpMain m-round">
        	    <div class="helpInfo">
            	    <dt>
                	    <h3><i class="z-arrow fr iOpen"></i><em>01.</em>什么是<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%></h3>
                    </dt>
                    <dd id="dd1" class="helpBox" style="display:none;">
                        <%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>是一种新型的网购模式，只需1元就有可能买到一件商品。<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>网把一件商品平分成若干“等份”出售，每份1元，当一件商品所有“等份”售出后抽出一名幸运者，该幸运者即可获得此商品。
                    </dd>
                </div>
                <div class="helpInfo">
            	    <dt class="hShadow">
                	    <h3><i class="z-arrow fr iCollapse"></i><em>02.</em><%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>的规则是什么</h3>
                    </dt>
                    <ul class="helpBox stepTwo" style="display:none;">
                        <li><p>每件商品参考市场价平分成相应“等份”，每份1元，1份对应<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>码。</p></li>
                        <li><p>同一件商品可以购买多次或一次购买多份。</p></li>
                        <li><p>当一件商品所有“等份”全部售出后计算出“幸运<%=ApplicationListenerImpl.sysConfigureJson.getShortName()%>码”，拥有“幸运<%=ApplicationListenerImpl.sysConfigureJson.getShortName()%>码”者即可获得此商品。<p></li>
                        <li><p>幸运<%=ApplicationListenerImpl.sysConfigureJson.getShortName()%>码计算方法：<br/>
                            1）取该商品最后购买时间前网站所有商品50条购买时间记录（限时揭晓商品取截止时间前网站所有商品50条购买时间记录）。<br/>
                            2）时间按时、分、秒、毫秒依次排列组成一组数值。如20:15:25.362则为201525362）将这50个时间的数值进行求和（得出数值A）<br/>
                            3）为保证公平公正公开，系统还会等待一小段时间，取最近下一期中国福利彩票“时时彩”的开奖结果（一个五位数值B）<br/>
                            4）（数值A+数值B）除以该商品总参与人次后取余数 + 原始数 10000001，得到最终幸运号码，拥有该幸运号码者，直接获得该商品。</p></li>
                    </ul>
                </div>
                <div class="helpInfo">
            	    <dt class="hShadow">
                	    <h3><i class="z-arrow fr iCollapse"></i><em>03.</em><%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>的流程是怎样的</h3>
                    </dt>
                    <dd class="helpBox stepThree" style="display:none;">
                        <span class="orange">1、挑选商品</span>
                        <p>分类浏览或直接搜索商品，点击“立即<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>”。</p>
                        <span class="orange">2、支付1 元</span>
                        <p>通过在线支付平台，支付1元即购买1人次，获得1个“<%=ApplicationListenerImpl.sysConfigureJson.getShortName()%>码”。同一件商品可购买多次或一次购买多份，购买的“<%=ApplicationListenerImpl.sysConfigureJson.getShortName()%>码”越多，获得商品的几率越大。</p>
                        <span class="orange">3、揭晓获得者</span>
                        <p>当一件商品达到总参与人次，抽出1名商品获得者，<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>网会通过手机短信或邮件通知您领取商品。<br />
                        <b class="gray9">注：<br /> 1、商品揭晓后您可登录"我的<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>"查询详情，未获得商品的用户不会收到短信或邮件通知；
                            <br />2、商品揭晓后，请及时登录"我的<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>"完善个人资料，以便我们能够准确无误地为您配送商品。
                            <br />3、所有已揭晓商品均不给予退款
                        </b></p>
                        <span class="orange">4、晒单分享</span>
                        <p>晒出您收到的商品实物图片甚至您的靓照，说出您的<%=ApplicationListenerImpl.sysConfigureJson.getShortName()%>心得，让大家一起分享您的快乐。
                          <br />在您收到商品后，您只需登录网站完成晒单，并通过审核，即可获得1000福分奖励。在您成功晒单后，您的晒单会出现在网站"晒单分享"区，与大家分享喜悦。
                        </p>
                    </dd>
                </div>
                <div class="helpInfo">
            	    <dt class="hShadow" style="border-bottom:0 none;">
                	    <h3><i class="z-arrow fr iCollapse"></i><em>04.</em>常见问题</h3>
                    </dt>
                    <dd class="helpBox stepThree" style="display:none;">
                        <span class="gray9"><em class="orange">Q:</em>怎样查看我参与的商品有没有获得？</span>
                        <p><strong>A:</strong>每件商品揭晓结果公布之后，登录<%=ApplicationListenerImpl.sysConfigureJson.getShortName()%>网，进入"我的<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>"，在"<%=ApplicationListenerImpl.sysConfigureJson.getShortName()%>录"中即可查询揭晓情况。</p>
        
                        <span class="gray9"><em class="orange">Q:</em>我获得了商品，我还需要支付其他费用吗？</span>
                        <p><strong>A:</strong>不需要支付其他任何费用。</p>
                    
                        <span class="gray9"><em class="orange">Q:</em>当我获得商品以后我该做什么？</span>
                        <p><strong>A:</strong>在您获得商品后您会收到站内信、短信和电子邮件的通知。在这之后，您必须在“我的<%=ApplicationListenerImpl.sysConfigureJson.getShortName()%>中心”正确填写、真实的收货地址，完善或确认您的个人信息。我们会在您获得商品后3个工作日内通过电话方式与您取得联系。</p>
                    
                        <span class="gray9"><em class="orange">Q:</em><%=ApplicationListenerImpl.sysConfigureJson.getShortName()%>网的商品是正品吗？怎么保证？</span>
                        <p><strong>A:</strong><%=ApplicationListenerImpl.sysConfigureJson.getShortName()%>网承诺，所有商品100%正品，可享受厂家所提供的全国联保服务，享受商品的保修、换货和退货的义务（国家三包政策）。</p>
                    
                        <span class="gray9"><em class="orange">Q:</em>如何晒单分享？</span>
                        <p><strong>A:</strong>在您收到商品后，登录<%=ApplicationListenerImpl.sysConfigureJson.getShortName()%>网，进入"我的<%=ApplicationListenerImpl.sysConfigureJson.getShortName()%>中心"，在“晒单分享”区发布晒单信息，通过审核后，您还可获得400-1500福分奖励。在您成功晒单后，您的晒单会出现在网站“晒单分享”区，与大家分享喜悦。</p>
                    
                        <span class="gray9"><em class="orange">Q:</em>我收到的商品可以换货或者退货吗？</span>
                        <p><strong>A:</strong>非质量问题，不在三包范围内，不给予退换货。</p>
                    
                        <span class="gray9"><em class="orange">Q:</em>参与<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>需要注意什么？</span>
                        <p><strong>A:</strong>请务必正确填写真实有效的联系电话、收货地址以便在您获得商品时能及时与您取得联系。</p>
                    
                        <span class="gray9"><em class="orange">Q:</em>网上银行充值未及时到帐怎么办？</span>
                        <p><strong>A:</strong>网上支付未及时到帐可能有以下几个原因造成：<br />
                            第一，由于网速或者支付接口等问题，支付数据没有及时传送到支付系统造成的；<br />
                            第二，网速过慢，数据传输超时，使银行后台支付信息不能成功对接，导致银行交易成功而支付后台显示失败；<br />
                            第三，在网上支付如果使用某些防火墙软件，有时会屏蔽银行接口的弹出窗口，这时会造成在银行那边被扣费，但在我们网站上显示尚没支付。但请您放心，每天我们都会根据银行系统的帐务明细清单对前一天的订单进行逐笔核对，如遇问题订单，我们会做手工添加。
                        </p>
                    
                    </dd>
                </div>
    	    </div>
        </div>
    </section>

</div>
  </body>
</html>
