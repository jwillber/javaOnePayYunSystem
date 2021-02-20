<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.eypg.util.ApplicationListenerImpl"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <meta name="decorator" content="template_footer" />
    <title>购物车_<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%> 一元<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %> <%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>网</title>
    <link rel="stylesheet" type="text/css" href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/weixinpay.css" />
    <script language="javascript" type="text/javascript">
        $(function () { var e = $("#qr_box"); var c = $("#guide"); c.css({ left: "50%", opacity: 0 }); e.hover(function () { c.css("display", "block").stop().animate({ marginLeft: "+156px", opacity: 1 }, 900, "swing", function () { c.animate({ marginLeft: "+143px" }, 300) }) }, function () { c.stop().animate({ marginLeft: "-101px", opacity: 0 }, "400", "swing", function () { c.hide() }) }); 
        var d = $("#hidShopID").val(); var b = 0; 
        //var a = function () { $.getJSON("http://account.1yyg.com/JPData/API.ashx?action=getwxpayresult&id=" + d + "&fun=?", function (f) { if (f.state == 0) { if ($("#hidIsBuyPay").val() == "0") { location.replace("http://member.1yyg.com/userbalance.do") } else { location.replace("http://cart.1yyg.com/shopok.do?id=" + d) } } else { b++; setTimeout(a, 3000 + parseInt(b / 20) * 1000) } }) }; 
        //setTimeout(a, 10000) 
        });
    </script>
  </head>
  
  <body>
 	<div class="wx_header">
        <div class="wx_logo">
            <img src="/Images/wxlogo_pay.png" alt="微信支付标志" title="微信支付"></div>
    </div>
    <div class="weixin">
        <div class="weixin2">
            <b class="wx_box_corner left pngFix"></b><b class="wx_box_corner right pngFix"></b>
            <div class="wx_box pngFix">
                <div class="wx_box_area">
                    <div class="pay_box qr_default">
                        <div class="area_bd"><span id="qr_box" class="wx_img_wrapper">
                            <img style="border-width:0px;" alt="二维码" src="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/uploadImages/${out_trade_no }.png" class="qrcode" id="payQRImg" />
                            <img id="guide" alt="" src="/Images/wxwebpay_guide.png" class="guide pngFix" style="left: 50%; opacity: 0; display: none; margin-left: -101px;" />
                        </span>
                            <div class="msg_default_box"><i class="icon_wx pngFix"></i>
                                <p>
                                    请使用微信扫描<br />
                                    二维码以完成支付
                                </p>
                            </div>
                            <div class="msg_box"><i class="icon_wx pngFix"></i>
                                <p><strong>扫描成功</strong>请在手机确认支付</p>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="wx_hd">
                    <div class="wx_hd_img icon_wx"></div>
                </div>
                <div class="wx_money"><span>￥</span>${moneyCount }.00</div>
                <!--支付订单号-->
                <div class="wx_pay">
                    <p><span class="wx_left">支付订单号</span><span class="wx_right">${out_trade_no}</span></p>
                </div>
                <div class="wx_kf">
                    <div class="wx_kf_img icon_wx"></div>
                    <div class="wx_kf_wz">
                        <p>客服</p>
                        <p><%=ApplicationListenerImpl.sysConfigureJson.getServiceTel()%></p>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <link type="text/css" rel="stylesheet" href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/pagedialog.css" />
    <script language="javascript" type="text/javascript" src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/cartlist.js"></script>
    <script language="javascript" type="text/javascript" src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/pagedialog.js"></script>
    <div class="pageDialogBG" id="pageDialogBG"></div>
	<div class="pageDialogBorder" id="pageDialogBorder"></div>
	<div class="pageDialog" id="pageDialog">
		<div class="pageDialogClose" id="pageDialogClose" title="关闭"></div>
		<div class="pageDialogMain" id="pageDialogMain">&nbsp;</div>
	</div>
</body>
</html>
