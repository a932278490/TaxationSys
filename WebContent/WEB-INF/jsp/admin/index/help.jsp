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

		<div class="xline"></div>
普通用户（示例：13919769706  郑茂军）
功能：
1.修改个人信息。 
2.发布出车申请。
		<div class="xline"></div>
部门负责人（示例：13919383903  林彬）
功能：
1.修改个人信息。 
2.发布出车申请。
3.审批通过或退回申请单。 
		<div class="xline"></div>
局领导（示例：13609343289  鲁胜林）
功能：
1.修改个人信息。 
2.发布出车申请。
3.审批通过或退回申请单。 
<div class="xline"></div>
办公室主任（示例：13919808196  王志谦）
功能：
1.修改个人信息。 
2.发布出车申请。
3.分配申请到车辆。
4.取消分配申请。
5.派出。
6.车辆信息更改。例如修改司机，车牌号，车容量。
<div class="xline"></div>
机关车队（也就是司机）（示例：13919788300  马季   ）
功能：
1.修改个人信息。
2.发布出车申请。
3.归还车辆。
<div class="xline"></div>
系统管理员（示例：admin  admin）

功能：
1.修改个人信息。
2.修改，增加，删除，查看部门信息。
3.修改，增加，删除，查看员工信息。
4.修改，增加，删除，查看权限信息。
5.日志查看，清空。
<div class="xline"></div>
	</div>



</body>

</html>
