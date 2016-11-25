<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>

</head>


<body>

	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="admin/index">首页</a></li>
		</ul>
	</div>

	<div class="mainindex">


		<div class="welinfo">
			<span><img src="images/sun.png" alt="天气" /></span> <b>${loginStuff.name },欢迎使用信息管理系统</b><font color="red"><strong>(${loginStuff.role.name })</strong></font>
			<a href="admin/system/loginStuff/stuffInfo">修改密码</a>
		</div>

		<div class="xline"></div>

	

		<div class="xline"></div>
		<div class="box"></div>

		<div class="welinfo">
			<span><img src="images/dp.png" alt="提醒" /></span> <b>兰州经开区出车信息管理系统使用指南</b>
		</div>

		<ul class="infolist">
			<li><span>您可以快速进行出车申请</span><a class="ibtn" href="admin/vehicle/apply/add">发布申请</a></li>
			<li><span>您可以快速查看申请通过情况</span><a class="ibtn" href="admin/vehicle/apply/list">查看申请列表</a></li>
			<li><span>您可以进行密码修改、账户设置等操作</span><a class="ibtn" href="admin/system/loginStuff/stuffInfo">账户管理</a></li>
		</ul>

		<div class="xline"></div>

		<div class="uimakerinfo">
			<b>查看本网站使用指南，您可以了解更多信息信息</b>
		</div>

		<ul class="umlist">
			<li><a href="admin/vehicle/apply/add">如何发布出车申请</a></li>
		</ul>


	</div>



</body>

</html>
