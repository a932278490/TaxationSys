<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

<script type="text/javascript">
$(document).ready(function(){
  $(".click").click(function(){
  $(".tip").fadeIn(200);
  });
  
  $(".tiptop a").click(function(){
  $(".tip").fadeOut(200);
});

  $(".sure").click(function(){
  $(".tip").fadeOut(100);
});

  $(".cancel").click(function(){
  $(".tip").fadeOut(100);
});

});
</script>


</head>


<body>

<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="admin/index">首页</a></li>
		</ul>
	</div>
    
    <div class="rightinfo">
    
     <div class="tools">
    
    	<ul class="toolbar">
        <!-- <li class="click"><span><img src="images/t01.png" /></span>添加</li>
        <li class="click"><span><img src="images/t02.png" /></span>修改</li>
        <li><span><img src="images/t03.png" /></span>删除</li>
        <li><span><img src="images/t04.png" /></span>统计</li>
 -->    
 
        <li onclick="window.location.href='<%=basePath%>admin/system/loginfo/delAll'"><span><img src="images/t03.png" /></span>清空日志</li>
 		</ul>
        
        
        <!-- <ul class="toolbar1">
        <li><span><img src="images/t05.png" /></span>设置</li>
        </ul> -->
    
    </div> 
    
    
    <table class="tablelist">
    	<thead>
    	<tr>
        <th>编号<i class="sort"><img src="images/px.gif" /></i></th>
        <th>操作内容</th>
		<th>操作用户</th>
		<th>操作时间</th>
       <!--  <th>操作</th> -->
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${loginfoPager.dataList}" var="c">
        
	        <tr>
	        <td>${c.id }</td>
			<td>${c.content }</td>
			<td>${c.username }</td>
			<td><f:formatDate value="${c.date}" type="both"/></td>
	     <%--    <td>
	        	<a href="admin/system/loginfo/update?id=${c.id }" class="tablelink">编辑</a>   
	        	<a href="javascript:;" onclick="member_del(this,'${c.id }')" class="tablelink"> 删除</a>
	        </td> --%>
	        </tr> 
        </c:forEach>
        </tbody>
    </table>
    
    <div class="pagin">
    	<div class="message">共<i class="blue">${loginfoPager.totalCount }</i>条记录，当前显示第&nbsp;<i class="blue">${loginfoPager.currentPage }&nbsp;</i>页</div>
        <ul class="paginList">
        <li class="paginItem"><a href="admin/system/loginfo/list?currentPage=${loginfoPager.currentPage-1 }"><span class="pagepre"></span></a></li>
        <li class="paginItem"><a href="admin/system/loginfo/list?currentPage=${loginfoPager.currentPage+1 }"><span class="pagenxt"></span></a></li>
        </ul>
    </div> 
    
    
    <div class="tip">
    	<div class="tiptop"><span>提示信息</span><a></a></div>
        
      <div class="tipinfo">
        <span><img src="images/ticon.png" /></span>
        <div class="tipright">
        <p>是否确认对信息的修改 ？</p>
        <cite>如果是请点击确定按钮 ，否则请点取消。</cite>
        </div>
        </div>
        
        <div class="tipbtn">
        <input name="" type="button"  class="sure" value="确定" />&nbsp;
        <input name="" type="button"  class="cancel" value="取消" />
        </div>
    
    </div>
    
    
    
    
    </div>
    
    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	
	/*用户-删除*/
	function member_del(obj,id){
			$.ajax({
				url: "admin/system/loginfo/del",
				type: 'POST',
				async : false,
				data: "id="+id
				});
			location.reload(true);
	}
	
	</script>

</body>

</html>
