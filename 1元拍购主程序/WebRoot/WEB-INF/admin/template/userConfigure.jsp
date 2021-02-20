<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.eypg.util.ApplicationListenerImpl"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
	<title>1元拍购_管理中心</title>
	<link href="/admin_css/global.css"  rel="stylesheet" type="text/css"/>
	<link href="/admin_css/admin_style.css"  rel="stylesheet" type="text/css"/>
	<script language="javascript" type="text/javascript" src="/js/jquery-1.4.4-min.js"></script>
  </head>
  
  <body>
<div class="header lr10"><a href="/admin_back/userListAll.action">会员列表</a><span class="span_fenge lr5">|</span><a href="/admin_back/toAddUser.action">添加会员</a><span class="span_fenge lr5">|</span><a href="/admin_back/toUserConfigure.action">会员福利配置</a><span class="span_fenge lr5">|</span></div>
  <div class="bk10"></div>
<div class="table_form lr10">
<form id="myform" method="post" action="/admin_back/doSeoSet.action">
<input type="hidden" name="sysConfigure.id" value="${sysConfigure.id }"/>
<input type="hidden" name="sysConfigure.imgUrl" value="${sysConfigure.imgUrl }"/>
<input type="hidden" name="sysConfigure.skinUrl" value="${sysConfigure.skinUrl }"/>
<input type="hidden" name="sysConfigure.wwwUrl" value="${sysConfigure.wwwUrl }"/>
<input type="hidden" name="sysConfigure.domain" value="${sysConfigure.domain }"/>
<input type="hidden" name="sysConfigure.mailName" value="${sysConfigure.mailName }"/>
<input type="hidden" name="sysConfigure.mailPwd" value="${sysConfigure.mailPwd }"/>
<input type="hidden" name="sysConfigure.saitName" value="${sysConfigure.saitName }"/>
<input type="hidden" name="sysConfigure.shortName" value="${sysConfigure.shortName }"/>
<input type="hidden" name="sysConfigure.saitTitle" value="${sysConfigure.saitTitle }"/>
<input type="hidden" name="sysConfigure.saitLogo" value="${sysConfigure.saitLogo }"/>
<input type="hidden" name="sysConfigure.keyword" value="${sysConfigure.keyword }"/>
<input type="hidden" name="sysConfigure.description" value="${sysConfigure.description }"/>
<input type="hidden" name="sysConfigure.tenpayPartner" value="${sysConfigure.tenpayPartner }"/>
<input type="hidden" name="sysConfigure.tenpayKey" value="${sysConfigure.tenpayKey }"/>
<input type="hidden" name="sysConfigure.tenpayStatus" value="${sysConfigure.tenpayStatus }"/>
<input type="hidden" name="sysConfigure.alipayPartner" value="${sysConfigure.alipayPartner }"/>
<input type="hidden" name="sysConfigure.alipayKey" value="${sysConfigure.alipayKey }"/>
<input type="hidden" name="sysConfigure.alipayMail" value="${sysConfigure.alipayMail }"/>
<input type="hidden" name="sysConfigure.alipayStatus" value="${sysConfigure.alipayStatus }"/>
<input type="hidden" name="sysConfigure.yeepayKey" value="${sysConfigure.yeepayKey }"/>
<input type="hidden" name="sysConfigure.yeepayPartner" value="${sysConfigure.yeepayPartner }"/>
<input type="hidden" name="sysConfigure.yeepayStatus" value="${sysConfigure.yeepayStatus }"/>
<input type="hidden" name="sysConfigure.icp" value="${sysConfigure.icp }"/>
<input type="hidden" name="sysConfigure.serviceQq" value="${sysConfigure.serviceQq }"/>
<input type="hidden" name="sysConfigure.serviceTel" value="${sysConfigure.serviceTel }"/>
<input type="hidden" name="sysConfigure.qqAppId" value="${sysConfigure.qqAppId }"/>
<input type="hidden" name="sysConfigure.qqAppKey" value="${sysConfigure.qqAppKey }"/>
<input type="hidden" name="sysConfigure.qqAppStatus" value="${sysConfigure.qqAppStatus }"/>
<input type="hidden" name="sysConfigure.messagePartner" value="${sysConfigure.messagePartner }"/>
<input type="hidden" name="sysConfigure.messageKey" value="${sysConfigure.messageKey }"/>
<input type="hidden" name="sysConfigure.messageChannel" value="${sysConfigure.messageChannel }"/>
<input type="hidden" name="sysConfigure.messageSign" value="${sysConfigure.messageSign }"/>
<table width="100%" class="lr10">
  <tbody>
  <tr>
    <td width="150px;">注册送余额：</td>
    <td><input type="text" value="${sysConfigure.regBalance }" size="10" name="sysConfigure.regBalance" class="input-text" /> 会员注册后送多少余额！(默认请填写0.0)</td> 
  </tr> 
   <tr>
	 <td width="150px;">充值满：</td>
	 <td><input type="text" value="${sysConfigure.recMoney }" size="10" name="sysConfigure.recMoney" class="input-text" /> 
	 送：<input type="text" value="${sysConfigure.recBalance }" size="10" name="sysConfigure.recBalance" class="input-text" /> 会员充值满多少后送多少金额! (默认请填写0.0)</td>
  </tr>
  <tr>
    <td width="150px;">佣金提成：</td>
    <td><input type="text" value="${sysConfigure.commission }" size="10" name="sysConfigure.commission" class="input-text" /> 邀请会员消费后，佣金提成百分比，如果8%请填写0.08 (默认请填写0.08)</td>
  </tr> 
  <tr>
    <td width="150px;">邀请奖励福分：</td>
    <td><input type="text" value="${sysConfigure.invite }" size="10" name="sysConfigure.invite" class="input-text" /> 邀请会员直接奖励邀请者多少福分。(默认请填写50)</td>
  </tr> 
  <tr>
    <td width="150px;">资料完善奖励福分：</td>
    <td><input type="text" value="${sysConfigure.userData }" size="10" name="sysConfigure.userData" class="input-text" /> 会员完善资料后奖励多少福分。(默认请填写80)</td>
  </tr> 
  <tr>
    <td width="150px;">购买商品奖励福分：</td>
    <td><input type="text" value="${sysConfigure.buyProduct }" size="10" name="sysConfigure.buyProduct" class="input-text" /> 用户每次消费1元获得多少福分。默认为1：1(默认请填写1)</td>
  </tr>
	<tr>
    	<td width="100"></td> 
   		<td><input type="submit" value=" 提交 " class="button" />
            <input type="button" value="返回" class="button"  onclick="history.go(-1)" /></td>
    </tr>
</tbody></table>
<p style="color:red;">以上信息必须填写，不能为空，否则影响系统正常运行。</p>
</form>
</div>
<script type="text/javascript">
	var b = function (w) {
		var x = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
		return x.test(w);
	};
	$("#sendTestMail").click(function(){
		var G = $("#testMailName").val();
		if (!(b(G))){
			alert("请输入正常的邮箱地址！");
			return false;
		}
		$.ajax({
			url:"/admin_back/doTestMail.action",
			type:"POST",
			data:"userName="+G,
			success:function(data){
				if(data=="mailError"){
					alert("请先配置系统邮箱及系统邮箱密码！");
				}else if(data=="success"){
					alert("发送成功！请进入邮箱查收！");
				}else{
					alert("发送失败，请核对邮箱！");
				}
			},
			error:function(){
				alert("请求异常，请联系管理员！");
			}
		});
	});
	
</script>
 </body>
</html>
