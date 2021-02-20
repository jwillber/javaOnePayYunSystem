<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.eypg.util.ApplicationListenerImpl"%>
<%@ taglib prefix="d"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="false"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title><d:title default="1ypg.com" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link rel="shortcut icon" href="/favicon.ico" />
		<meta name="description" content="<%=ApplicationListenerImpl.sysConfigureJson.getDescription()%>" />
		<meta name="keywords" content="<%=ApplicationListenerImpl.sysConfigureJson.getKeyword()%>" />
		<link rel="stylesheet" type="text/css" href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/header.css" />
		<script language="javascript" type="text/javascript" src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/jquery123.js"></script>
		<d:head />
	</head>

	<body <d:getProperty property="body.id" writeEntireProperty="true" />
		<d:getProperty property="body.class" writeEntireProperty="true" />
		<d:getProperty property="body.rf" writeEntireProperty="true" />>
		<div class="g-frame-top">
			<div class="g-width head-nav-bd">
				<ul class="head-nav-bd-l">
					<li class="M-favorite menu">
						<div class="menu-hd">
							<a href="javascript:;" id="addSiteFavorite">收藏</a>
						</div>
					</li>
					<li class="M-mobile menu" name="liHover">
						<div class="menu-hd">
							<a href="javascript:;"><b><span
									class="Hicon"></span>手机<%=ApplicationListenerImpl.sysConfigureJson.getShortName()%></b> </a>
							<i class=""></i>
						</div>
						<div class="menu-bd" style="display: none;">
							<div class="top-nav-bor J-mobile-size">
								<div class="M-mobile-content">
									<img src="/Images/mobile-wx.png" alt="" />
								</div>
							</div>
						</div>
					</li>
					<li style="display: block;" class="M-attention menu" name="liHover">
						<div class="menu-hd">
							<b>关注</b>
							<i></i>
						</div>
						<div class="menu-bd">
							<div class="top-nav-bor M-focus-size">
								<ul>
									<li class="U_sina">
										<a target="_blank" href="http://weibo.com/1ypg"><s></s><em>新浪微博</em>
										</a>
									</li>
									<li class="U_qq">
										<a target="_blank" href="http://t.qq.com/admin-1ypg"><s></s><em>腾讯微博</em>
										</a>
									</li>
									<li class="U_wx">
										<a 
											href="javascript:;"><s></s><em>微信关注</em>
										</a>
									</li>
									<li class="U_wximg">
										<img src="/Images/1yyg-wx.png" alt="" />
									</li>
								</ul>
							</div>
						</div>
					</li>
<!--					<li class="M-attention menu">-->
<!--						<div class="menu-hd">-->
<!--							<a target="_blank"-->
<!--								href="/help/qqgroup.html">官方QQ群</a>-->
<!--						</div>-->
<!--					</li>-->
				</ul>
				<ul class="head-nav-bd-r">
					<!--登录-->
					<li class="M-login menu" id="liLoginBox">
					</li>
					<li class="M-login menu" id="liRegisterBox">
					</li>
					<li class="M-my-1yyg menu" name="liHover">
						<div class="menu-hd">
							<a href="/user/index.html?forward=myUser">我的<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%></a>
							<i></i>
						</div>
						<div class="menu-bd">
							<div class="top-nav-bor M-my-size">
								<ul class="U-txt">
									<li>
										<a href="/user/UserBuyList.html"><%=ApplicationListenerImpl.sysConfigureJson.getShortName()%>记录</a>
									</li>
									<li>
										<a href="/user/OrderList.html">获得的商品</a>
									</li>
									<li>
										<a href="/user/MemberModify.html">个人设置</a>
									</li>
								</ul>
							</div>
						</div>
					</li>
					<li class="mod_sitemap_gap"></li>
					<div style="display: none;" class="menu-bd-msg"></div>
<!--					<li style="display: none;" class="M-mark menu" id="liTopUMsg">-->
<!--						<div class="menu-hd">-->
<!--							<a href="/user/userMessage.html">消息</a>-->
<!--							<i></i>-->
<!--						</div>-->
<!--						<div style="display: none;" class="menu-bd"></div>-->
<!--					</li>-->
					<li class="M-shop menu" id="liTopCart">
						<div class="menu-hd">
							<a href="/mycart/index.html" target="_blank" class="u-shop-tit"><span class="Hicon"></span>购物车(<strong>0</strong>)</a>
							<em></em>
						</div>
						<div class="menu-bd">
							<div class="top-nav-bor M-shop-size"><div class="M-shop-content"></div></div>
						</div>
					</li>
					<li class="mod_sitemap_gap"></li>
					<li class="F-money menu">
						<div class="menu-hd">
							<a href="/user/UserRecharge.html">快速充值</a>
						</div>
					</li>
					<li class="mod_sitemap_gap"></li>
					<li class="F-invitation menu">
						<div class="menu-hd">
							<a href="/referAuth.html">邀请</a>
						</div>
					</li>
					<li class="mod_sitemap_gap"></li>
					<li class="F-help menu">
						<div class="menu-hd">
							<a href="/help/whatPaigou.html">帮助</a>
						</div>
					</li>
					<li class="mod_sitemap_gap"></li>
					<li class="F-service menu">
						<div class="menu-hd">
							<a class="F-icon-guest" target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=<%=ApplicationListenerImpl.sysConfigureJson.getServiceQq() %>&site=qq&menu=yes" ><s></s>在线客服</a>
						</div>
					</li>
				</ul>
			</div>
		</div>

		<div class="g-frame-head">
			<div class="head_mid">
				<div class="g-width head_mid_bg">
					<div style="height: 80px; background: url(<%=ApplicationListenerImpl.sysConfigureJson.getSaitLogo() %>) 2px 0 no-repeat;" class="logo_1yyg" id="topLogoAd">
						<a title="<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>" href="/" class="logo_1yyg_img"><%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%></a>
<!--						<a class="logo_1yyg_img" style="background: url('/Images/logo-jd.png') no-repeat scroll 2px 0 rgba(0, 0, 0, 0);display: inline;float: left;margin-left: -180px;margin-top: 23px;"></a>-->
					</div>
					<div class="head_number">
						<ul>
							<li class="F-number-l"></li>
							<li class="F-number-bg">
								<span class="gray9">已有</span>
								<span id="spHeadTotalNum" class="f-fn-size"></span>
								<span class="gray9">人次参与</span>
								<a target="_blank" href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl() %>/getNewRecord.html" class="gray9"><span>全站记录</span><i class="mod_dropmenu_arrow"></i> </a>
							</li>
							<li class="F-number-r"></li>
						</ul>
					</div>
					<div class="head_search">
						<div class="top_search">
							<input type="text" value="输入“汽车” 试试" class="init" id="txtHSearch" placeholder="" style="color: rgb(187, 187, 187);">
							<input type="button" onmouseover="this.className='search_submit_hover'" onmouseout="this.className='search_submit'" value="搜索" class="search_submit" id="btnHSearch">
						</div>
						<div class="keySearch">
							<a target="_blank"
								href="/search/hot20/%E6%99%BA%E8%83%BD%E6%89%8B%E6%9C%BA.html">智能手机</a>
							<a target="_blank"
								href="/search/hot20/3G%E6%89%8B%E6%9C%BA.html">3G手机</a>
							<a target="_blank"
								href="/search/hot20/%E5%AE%9D%E9%A9%AC.html">宝马</a>
							<a target="_blank"
								href="/search/hot20/%E5%8D%95%E5%8F%8D.html">单反</a>
						</div>
					</div>
				</div>
			</div>

			<!-- 头部导航栏 -->
			<div class="nav">
				<div class="g-width nav-bd">
					<div class="M-All-goods" id="divGoodsSortList">
						<div class="U-All-T">
							<a class="white" href="/list/hot20.html"><b
								class=""></b>所有商品</a>
						</div>
						<div class="U-goods-class" style="display: none;">
							<div class="goods-class-list">
								<dl class="">
									<dt class="U-goods-1">
										<a href="/list/hot20/1001.html"> <i class="F-goods-img"></i>手机数码<i class="F-goods-arrow"></i> </a>
									</dt>
									<dd>
										<a href="/list/hot20/1020.html">手机</a>
									</dd>
									<dd>
										<a href="/list/hot20/1023.html">数码相框</a>
									</dd>
									<dd>
										<a href="/list/hot20/1022.html">单反相机</a>
									</dd>
								</dl>
								<dl>
									<dt class="U-goods-2">
										<a href="/list/hot20/1002.html"> <i class="F-goods-img"></i>电脑办公<i class="F-goods-arrow"></i> </a>
									</dt>
									<dd>
										<a href="/list/hot20/1008.html">上网本</a>
									</dd>
									<dd>
										<a href="/list/hot20/1009.html">平板电脑</a>
									</dd>
									<dd>
										<a href="/list/hot20/1026.html">笔记本</a>
									</dd>
								</dl>
								<dl>
									<dt class="U-goods-3">
										<a href="/list/hot20/1003.html"> <i class="F-goods-img"></i>家用电器<i class="F-goods-arrow"></i> </a>
									</dt>
									<dd>
										<a href="/list/hot20/1003.html">大家电</a>
									</dd>
									<dd>
										<a href="/list/hot20/1003.html">生活电器</a>
									</dd>
									<dd>
										<a href="/list/hot20/1003.html">个人护理</a>
									</dd>
								</dl>
								<dl>
									<dt class="U-goods-4">
										<a href="/list/hot20/1004.html"> <i class="F-goods-img"></i>化妆个护<i class="F-goods-arrow"></i> </a>
									</dt>
									<dd>
										<a href="/list/hot20/1010.html">眼霜</a>
									</dd>
									<dd>
										<a href="/list/hot20/1011.html">洁面</a>
									</dd>
									<dd>
										<a href="/list/hot20/1013.html">防晒隔离霜</a>
									</dd>
									<dd>
										<a href="/list/hot20/1016.html">BB霜</a>
									</dd>
									<dd>
										<a href="/list/hot20/1019.html">女士香水</a>
									</dd>
								</dl>
								<dl>
									<dt class="U-goods-5">
										<a href="/list/hot20/1005.html"> <i class="F-goods-img"></i>钟表首饰<i class="F-goods-arrow"></i> </a>
									</dt>
									<dd>
										<a href="/list/hot20/1005.html">珠宝首饰</a>
									</dd>
								</dl>
								<dl>
									<dt class="U-goods-6">
										<a href="/list/hot20/1007.html"> <i class="F-goods-img"></i>其它商品<i class="F-goods-arrow"></i> </a>
									</dt>
									<dd>
										<a href="/list/hot20/1007.html">汽车</a>
									</dd>
									<dd>
										<a href="/list/hot20/1007.html">旅游</a>
									</dd>
								</dl>
							</div>
						</div>
					</div>
					<div class="M-nav-column">
						<ul>
							<li class="F-nav-back">
								<a class="home" href="/">首页</a>
							</li>
							<li class="F-nav-lottery">
								<a href="/lottery/index.html">最新揭晓</a>
							</li>
							<li class="F-nav-share">
								<a href="/share/new20.html">晒单分享</a>
							</li>
<!--							<li class="F-nav-circle">-->
<!--								<a href="/group/index.html">拍购圈</a>-->
<!--							</li>-->
						</ul>
					</div>
					<div class="M-nav-help">
						<a target="_blank" title="新手指南"
							href="/help/index.html"><s></s> </a>
					</div>
				</div>
			</div>
		</div>

		<d:body />

		<div class="g-frame-footer">
			<div class="g-width footer">
				<div class="M-guide">
					<dl>
						<dt>
							新手指南
						</dt>
						<dd>
							<a target="_blank" href="/help/whatPaigou.html">了解<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%></a>
						</dd>
						<dd>
							<a target="_blank"
								href="/help/questionDetail.html">常见问题</a>
						</dd>
						<dd>
							<a target="_blank" href="/help/agreement.html">服务协议</a>
						</dd>
						<dd>
							<a target="_blank"
								href="/help/userExperience.html">会员福分经验值</a>
						</dd>
					</dl>
					<dl>
						<dt>
							<%=ApplicationListenerImpl.sysConfigureJson.getShortName()%>保障
						</dt>
						<dd>
							<a target="_blank"
								href="/help/genuinetwo.html"><%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>保障体系</a>
						</dd>
						<dd>
							<a target="_blank" href="/help/genuine.html">正品保障</a>
						</dd>
						<dd>
							<a target="_blank"
								href="/help/securepayment.html">安全支付</a>
						</dd>
						<dd>
							<a target="_blank"
								href="/help/suggestion.html">投诉建议</a>
						</dd>
					</dl>
					<dl>
						<dt>
							商品配送
						</dt>
						<dd>
							<a target="_blank" href="/help/ship.html">商品配送</a>
						</dd>
						<dd>
							<a target="_blank"
								href="/help/deliveryFees.html">配送费用</a>
						</dd>
						<dd>
							<a target="_blank" href="/help/prodCheck.html">商品验货与签收</a>
						</dd>
						<dd>
							<a target="_blank" href="/help/shiptwo.html">长时间未收到商品</a>
						</dd>
					</dl>
					<dl>
						<dt>
							关于<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>
						</dt>
						<dd>
							<a target="_blank" href="/about/index.html"><%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>介绍</a>
						</dd>
						<dd>
							<a target="_blank" href="http://weibo.com/1ypg"><%=ApplicationListenerImpl.sysConfigureJson.getShortName()%>微博</a>
						</dd>
					</dl>
					<dl>
						<dt>
							携手<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>
						</dt>
						<dd>
							<a target="_blank" href="/about/jobs.html"><%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>招聘</a>
						</dd>
						<dd>
							<a target="_blank" href="/about/Links.html">友情链接</a>
						</dd>
						<dd>
							<a target="_blank"
								href="/help/qqgroup.html">官方QQ群交流</a>
						</dd>
					</dl>
					<dl class="dl_last">
						<dt>
							应用更新
						</dt>
<!--						<dd>-->
<!--							<a target="_blank" href="/app/mobile.html">手机版</a>-->
<!--						</dd>-->
<!--						<dd>-->
<!--							<a target="_blank" href="/app/desktop.html">桌面版</a>-->
<!--						</dd>-->
					</dl>
				</div>

				<div class="service-promise">
					<ul>
						<li class="M-android ">
							<s class="F-bg"></s>
							<p class="F-txt">
								<b class="gray9">下载安卓客户端</b>
								<s class="F-android-img"></s>
								<a target="_blank" href="/app/mobile.html"
									class="orange_btn">立即下载</a>
							</p>
						</li>
						<li class="M-wx">
							<a target="_blank"
								href="/app/microchannel.html"> <s
								class="F-wxm"> <img width="75" height="75" border="0" alt="" src="/Images/1yyg-wx.png" />
							</s> </a>
							<p class="F-txt">
								<a target="_blank"
									href="/app/microchannel.html"> </a><a
									target="_blank"
									href="/app/microchannel.html"> <b
									class="gray9"><i></i>关注官方微信</b> <s class="F-wx-img"></s> </a>
							</p>

						</li>
						<li class="M-time">
							<s class="F-bg"></s>
							<p id="pServerTime" class="F-txt">
								<b class="gray9">服务器时间</b>
								<span class="F-txt-dig">00</span>
								<span class="F-txt-point">:</span>
								<span class="F-txt-dig">00</span>
								<span class="F-txt-point">:</span>
								<span class="F-txt-dig">00</span>
							</p>
						</li>
						<li class="M-fund">
							<s class="F-bg"></s>
							<p class="F-txt">
								<b class="gray9">拍购公益基金</b>
								<a target="_blank" href="/"><span id="spFundTotal" class="F-fund-buy fam-y"></span> </a>
							</p>
						</li>
						<li class="M-tel">
							<s class="F-bg"></s>
							<p class="F-txt">
								<b class="gray9">服务热线</b>
								<i class="F-tel-img"><%=ApplicationListenerImpl.sysConfigureJson.getServiceTel() %></i>
								<a class="F-icon-guest" target="_blank"  href="http://wpa.qq.com/msgrd?v=3&uin=<%=ApplicationListenerImpl.sysConfigureJson.getServiceQq() %>&site=qq&menu=yes"><s></s>在线客服</a>
							</p>
						</li>
					</ul>
				</div>
				<div class="M-security">
					<a target="_blank" class="U-fair"
						href="/help/genuinetwo.html"> <s
						class="F-security-img"></s>
						<p class="F-security-T">
							100%公平公正
						</p>
						<p class="F-security-C">
							参与过程公开透明，用户可随时查看
						</p> </a>
					<a target="_blank" class="U-security"
						href="/help/genuine.html"> <s
						class="F-security-img"></s>
						<p class="F-security-T">
							100%正品保证
						</p>
						<p class="F-security-C">
							精心挑选优质商家，100%品牌正品
						</p> </a>
					<a target="_blank" class="U-free"
						href="/help/deliveryFees.html"> <s
						class="F-security-img"></s>
						<p class="F-security-T">
							全国免运费
						</p>
						<p class="F-security-C">
							全场商品全国包邮（港澳台地区除外）
						</p> </a>
				</div>
			</div>
		</div>

		<div class="g-frame copyright">
			<div class="footer_links">
				<a href="/" target="_blank">首页</a>
				<span></span>
				<a href="/about/index.html" target="_blank">关于<%=ApplicationListenerImpl.sysConfigureJson.getShortName()%></a>
				<span></span>
				<a href="/help/privacy.html" target="_blank">隐私声明</a>
				<span></span>
				<a href="/about/jobs.html" target="_blank">加入<%=ApplicationListenerImpl.sysConfigureJson.getShortName()%></a>
				<span></span>
				<a href="/about/Links.html" target="_blank">友情链接</a>
			</div>
			<div class="copyright">
				<%=ApplicationListenerImpl.sysConfigureJson.getIcp()%>
			</div>
			<div class="footer_icon">
				<a class="fi_ectrustchina" 
					href="javascript:;"></a>
				<a class="fi_315online" 
					href="javascript:;"></a>
				<a class="fi_qh" 
					href="javascript:;"></a>
				<a class="fi_cnnic" 
					href="javascript:;"></a>
				<a class="fi_anxibao" 
					href="javascript:;"></a>
				<a class="fi_pingan" 
					href="javascript:;"></a>
			</div>
		</div>

		<div style="display: block;" class="quickBack" id="divRighTool">
			<dl class="quick_But">
				<dd style="" class="quick_cart" id="divRigCart">
					<a class="quick_cartA" target="_blank"
						href="/mycart/index.html" id="btnMyCart"><b>购物车</b><s></s><em
						style="display: none;">0</em> </a>
					<div class="Roll_mycart" id="rCartlist" style="display: none;">
						<i class="quick_triangle"></i>
						<ul style="display: none;"></ul>
						<div class="quick_goods_loding">
							<img border="0"
								src="/Images/goods_loading.gif" alt="" />
							正在加载......
						</div>
						<p style="display: none;" id="p1"></p>
						<h3 style="display: none;">
							<a class="orange_btn"
								href="/mycart/index.html" target="_blank">去购物车结算</a>
						</h3>
					</div>
				</dd>
				<dd class="quick_service">
					<a class="quick_serviceA " target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=<%=ApplicationListenerImpl.sysConfigureJson.getServiceQq() %>&site=qq&menu=yes" ><b>在线客服</b><s></s>
					</a>
				</dd>
				<dd class="quick_Collection">
					<a class="quick_CollectionA" href="javascript:void();"
						id="btnFavorite"><b>收藏本站</b><s></s> </a>
				</dd>
				<dd class="quick_Return">
					<a class="quick_ReturnA" href="javascript:void()" id="btnGotoTop"><b>返回顶部</b><s></s>
					</a>
				</dd>
			</dl>
		</div>

		<div style="display: block;" class="quick_publish_bottom"
			id="divRighRaff">
			
		</div>
		<script type="text/javascript">
var $img = "<%=ApplicationListenerImpl.sysConfigureJson.getImgUrl()%>";
var $skin = "<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>";
var $www = "<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>";
var $domain = "<%=ApplicationListenerImpl.sysConfigureJson.getDomain()%>";
var $saitName = "<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%>";
var $shortName = "<%=ApplicationListenerImpl.sysConfigureJson.getShortName()%>";
</script>
		<script language="javascript" type="text/javascript" src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/bottom2.js?data=20131121"></script>
	</body>
</html>
