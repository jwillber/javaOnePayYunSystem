<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.eypg.util.ApplicationListenerImpl"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <meta name="decorator" content="index_template" />
    <title>编辑个人资料_<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%></title>
    <link rel="stylesheet" type="text/css" href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/layoutframe.css?data=20131121" />
    <link rel="stylesheet" type="text/css" href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/layoutsetup.css?data=20131121" />
  </head>
  
<body>
<div class="main-content clearfix">
        
<div class="left">
	<div class="head">
	    <a target="_blank" href="<%=ApplicationListenerImpl.sysConfigureJson.getWwwUrl()%>/u/${user.userId }.html">
	    <img width="160" height="160" border="0" runat="server" alt="" src="${user.faceImg }" id="imgUserPhoto" /></a>
	</div>
	<div class="head-but">
		<a class="blue" href="/user/UserPhoto.html">修改头像</a>
		<a class="blue fr" href="/user/MemberModify.html">编辑资料</a>
	</div>
	<div class="sidebar-nav">
<p class="sid-line"></p>
<h2 class=""><a href="index.html"><b></b>我的<%=ApplicationListenerImpl.sysConfigureJson.getSaitName()%></a></h2><p class="sid-line"></p>
<h3 url="/user/UserBuyList.html" haschild="1" class="sid-icon02 "><a href="javascript:;"><b></b>我的<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %> <s title="收起"></s></a></h3>
<ul><li class=""><a href="/user/UserBuyList.html"><%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>记录</a></li>
<li class=""><a href="/user/OrderList.html">获得的商品</a></li>
<li class=""><a href="/user/PostSingleList.html">晒单</a></li></ul>
<!--<p class="sid-line"></p><h3 url="/user/JoinGroup.html" haschild="1" class="sid-icon03 "><a href="javascript:;"><b></b>圈子管理 <s title="收起"></s></a></h3>-->
<!--<ul><li class=""><a href="/user/JoinGroup.html">加入的圈子</a></li>-->
<!--<li class=""><a href="/user/Topic.html">圈子话题</a></li></ul>-->
<p class="sid-line"></p><h3 url="/user/InvitedList.html" haschild="1" class="sid-icon04 "><a href="javascript:;"><b></b>邀请管理 <s title="收起"></s></a></h3>
<ul><li class=""><a href="/user/InvitedList.html">邀请好友</a></li>
<li class=""><a href="/user/CommissionQuery.html">佣金明细</a></li>
<li class=""><a href="/user/ApplyMention.html">提现申请</a></li>
<li class=""><a href="/user/MentionList.html">提现记录</a></li></ul>
<p class="sid-line"></p><h3 url="/user/UserBalance.html" haschild="1" class="sid-icon05 "><a href="javascript:;"><b></b>账户管理 <s title="收起"></s></a></h3>
<ul><li class=""><a href="/user/UserBalance.html">账户明细</a></li>
<li class=""><a href="/user/UserRecharge.html">账户充值</a></li>
<li class=""><a href="/user/userCardRecharge.html">实卡充值</a></li>
</ul>
<!--<p class="sid-line"></p><h3 url="/user/MyFriends.html" haschild="1" class="sid-icon06 "><a href="javascript:;"><b></b>我的好友 <s title="收起"></s></a></h3>-->
<!--<ul><li class=""><a href="/user/MyFriends.html">我的好友</a></li>-->
<!--<li class=""><a href="/user/SearchFriends.html">查找好友</a></li>-->
<!--<li class=""><a href="/user/FriendsApply.html">好友请求</a></li></ul>-->
<p class="sid-line"></p><h3 url="" haschild="0" class="sid-icon07 "><a href="/user/MemberPoints.html"><b></b>我的福分</a></h3>

<!--<p class="sid-line"></p><h3 url="" haschild="0" class="sid-icon08 "><a href="/user/UserMessage.html"><b></b>消息管理</a></h3>-->

<p class="sid-line"></p><h3 url="" haschild="0" class="sid-icon09 sid-hcur "><a href="/user/MemberModify.html"><b></b>个人设置</a></h3>


		<p class="sid-line"></p>
	</div>
	<div class="sid-service">
		<p><a class="service-btn" target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=<%=ApplicationListenerImpl.sysConfigureJson.getServiceQq() %>&site=qq&menu=yes" id="btnLeftQQ"><s></s>在线客服</a></p>
		<span>客服热线(免长途费)</span>
		<b class="tel"><%=ApplicationListenerImpl.sysConfigureJson.getServiceTel() %></b>
	</div>
</div>
<script type="text/javascript">

    var _NavState = [true, true, true, true, true];
    
    $("div.sidebar-nav").find("h3").each(function(i,v){
        var _This = $(this);
        var _HasClild = _This.attr("hasChild")=="1";
        
        var _SObj = _This.find("s");
        _This.click(function(e){
            if(_HasClild){
                var _State = _NavState[i];
                
                /* 一级栏目更改样式 */
                if(_State){
                    _This.addClass("sid-iconcur");
                    _SObj.attr("title","展开");
                }
                else {
                    _This.removeClass("sid-iconcur");
                    _SObj.attr("title","收起");
                }
                
                /* 二级栏目显示或隐藏 */
                _This.next("ul").children().each(function(){
                    if(_State){
                        $(this).hide(50);
                    }
                    else {
                        $(this).show(50);
                    }
                });
                _NavState[i] = !_State;
            }
        });
    });
    
</script>
	<div class="R-content">
		<input name="hidAuth" id="hidAuth" type="hidden" value="${userId }" />
		<div class="member-t"><h2>新手机号验证</h2><a class="blue" href="javascript:history.go(-1);">&lt;&lt; 返回</a></div>
		<div class="tel_verification">
                <div class="prompt orange">请完成手机验证，验证手机不仅能加强账户安全，快速找回密码，还会在您成功<%=ApplicationListenerImpl.sysConfigureJson.getShortName() %>到商品后及时通知您！<em></em></div>
                <div id="divSend" class="text_tel">
                    <span>第一步输入手机号码：</span><input type="text" maxlength="20" value="${key }" class="tel" id="txtMobile"><input type="button" value="确认手机号码" class="orangebut" id="butSubmit" />
                    <div id="showErr"></div>
                </div>
                <div style="display:none;" class="verification_code" id="divChecking">
                    <h4 id="sendok"></h4>
                    <div class="text_verification_code">
                        <span class="text">输入验证码：</span><input type="text" maxlength="12" class="tel" id="txtMobileSN"><div id="snErr"></div>
                        <div class="back">
                            <p>
                                2分钟后未收到手机验证码，请点击重新发送</p>
                            <input type="button" value="重新发送" id="btnSendSN"></div>
                        <div class="value">
                            <input type="button" class="bluebut" value="提交" id="butSaveSubmit"><div style="display:none;" class="ing" id="saveSubmitStatus">
                                <p>正在验证</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
     </div>

</div>
    <link type="text/css" rel="stylesheet" href="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/css/pagedialog.css?data=20131121" />
    <script language="javascript" type="text/javascript" src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/mobilechecking.js?data=20131121"></script>
    <script language="javascript" type="text/javascript" src="<%=ApplicationListenerImpl.sysConfigureJson.getSkinUrl()%>/js/pagedialog.js?data=20131121"></script>
    <div class="pageDialogBG" id="pageDialogBG"></div>
	<div class="pageDialogBorder" id="pageDialogBorder"></div>
	<div class="pageDialog" id="pageDialog">
		<div class="pageDialogClose" id="pageDialogClose" title="关闭"></div>
		<div class="pageDialogMain" id="pageDialogMain">&nbsp;</div>
	</div>
</body>
</html>
