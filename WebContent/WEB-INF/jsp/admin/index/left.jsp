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
<script language="JavaScript" src="js/jquery.js"></script>

<script type="text/javascript">
$(function(){	
	//导航切换
	$(".menuson li").click(function(){
		$(".menuson li.active").removeClass("active")
		$(this).addClass("active");
	});
	
	$('.title').click(function(){
		var $ul = $(this).next('ul');
		$('dd').find('ul').slideUp();
		if($ul.is(':visible')){
			$(this).next('ul').slideUp();
		}else{
			$(this).next('ul').slideDown();
		}
	});
})	
</script>


</head>

<body style="background:#f0f9fd;">
	<div class="lefttop"><span></span>工具栏</div>
    
    <dl class="leftmenu">
        
    <dd>
    <div class="title">
    <span><img src="images/leftico01.png" /></span>信息管理
    </div>
    	<ul class="menuson">
        <li><cite></cite><a href="admin/index" target="rightFrame">个人信息</a><i></i></li>
        <c:if test="${loginStuff.role.level>6 }">
	        <li><cite></cite><a href="admin/deptment/list" target="rightFrame">部门信息</a><i></i></li>
	      	<li><cite></cite><a href="admin/stuff/list" target="rightFrame">员工信息</a><i></i></li>
	        <li><cite></cite><a href="admin/system/role/list" target="rightFrame">权限管理</a><i></i></li>
	        <li><cite></cite><a href="admin/system/loginfo/list" target="rightFrame">操作日志</a><i></i></li>
        </c:if>
       </ul>    
    </dd>
        
    
    <dd>
    <div class="title">
    <span><img src="images/leftico02.png" /></span>出车管理
    </div>
    <ul class="menuson">
        <li><cite></cite><a href="admin/vehicle/list" target="rightFrame">进入车库</a><i></i></li>
        <li><cite></cite><a href="admin/vehicle/apply/list" target="rightFrame">申请列表</a><i></i></li>
        <li><cite></cite><a href="admin/vehicle/apply/add" target="rightFrame">填写申请</a><i></i></li>
        </ul>     
    </dd> 
    
    </dl>
    
</body>
</html>
