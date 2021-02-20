<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	ResourceBundle url = ResourceBundle.getBundle("config");
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>1元拍购_管理中心</title>
		<link href="/admin_css/global.css"  rel="stylesheet" type="text/css"/>
		<link href="/admin_css/admin_style.css"  rel="stylesheet" type="text/css"/>
		<script type="text/javascript" src="/ueditor/editor_config.js"></script>
		<script type="text/javascript" src="/ueditor/editor_all.js"></script>
		<script language="javascript" type="text/javascript"
			src="/js/jquery-1.4.4-min.js"></script>
	</head>

	<body>
	<div class="header lr10"><a href="/admin_back/userListAll.action">会员列表</a><span class="span_fenge lr5">|</span><a href="/admin_back/toAddUser.action">添加会员</a><span class="span_fenge lr5">|</span><a href="/admin_back/toUserConfigure.action">会员福利配置</a><span class="span_fenge lr5">|</span></div>
	<div class="bk10"></div>

<div class="header-data lr10">
 查询类型：
<select id="typeId" class="form-control" style="width:180px;">
<option value="">请选择</option>
<option value="userId">用户ID</option>
<option value="userName">用户昵称</option>
<option value="mail">邮箱</option>
<option value="phone">手机</option>
</select>
<input type="text" value="" name="keyword" id="keyword" class="input-text wid200"/>
<input type="submit" value="搜索" id="search"  class="button" />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;排序：
<select id="orderName" class="form-control" style="width:180px;">
<option value="">请选择</option>
<option value="oldDate">注册时间</option>
<option value="balance">用户余额</option>
<option value="commissionPoints">福分</option>
<option value="experience">经验值</option>
</select>
</div>
<div class="bk10"></div>

<div class="table-list lr10">        
  <!--start-->
  <table width="100%" cellspacing="0">
    <thead>
		<tr>
            <th align="center">UID</th>
            <th align="center">用户名</th>
            <th align="center">邮箱</th>
            <th align="center">手机</th>
            <th align="center">余额</th>
			<th align="center">福分</th> 
			<th align="center">经验值</th>			
			<th align="center">登陆地址、IP</th>
			<th align="center">注册时间</th>		
			<th align="center">最新登录时间</th>
            <th align="center">管理</th>
		</tr>
    </thead>
    <tbody>
    
    <s:iterator value="userList" var="users" status="st">
		<tr class="gradeA even">
			<td align="center">${users.userId }</td>
			<td align="center">${users.userName }</td>
			<td align="center">${users.mail }</td>
			<td align="center">${users.phone }</td>
			<td align="center">${users.balance }</td>
			<td align="center">${users.commissionPoints }</td>
			<td align="center">${users.experience }</td>
			<td align="center">${users.ipLocation }(${users.ipAddress })</td>
			<td align="center">${users.oldDate }</td>
			<td align="center">${users.newDate }</td>
			<td align="center">
			<a name="toUpdate" ids="${users.userId }" href="javascript:;"><input type="submit" value="修改" class="button" /></a>
			<a href="javascript:alert('无权删除');"><input type="submit" value="删除" class="button" /></a>
			</td>
		</tr>
	</s:iterator>
  	</tbody>
</table>
</div>
		${pageString }
		 	<script language="javascript" type="text/javascript">
		 	$("[name='toUpdate']").click(function(){
	 			var id = $(this).attr("ids");
	 			var burl = document.URL;
	 			window.location.href = "/admin_back/toUpdateUser.action?id="+id+"&backUrl="+burl;  
	 		});
	 		$("#search").click(function(){
	 			var typeId = $("#typeId").val();
	 			var keyword = $("#keyword").val();
	 			window.location.href = "/admin_back/userListAll.action?typeId="+typeId+"&keyword="+keyword;
	 		});
	 		$("#orderName").change(function(){
	 			var typeName = $("#orderName").val();
	 			window.location.href = "/admin_back/userListAll.action?typeName="+typeName;
	 		});
		 	</script>
	</body>
</html>
