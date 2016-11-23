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

/*批量删除*/
function datadel(){
		var chks=$("input:checked");//获取所有选中的checkbox,chks是一个元素数组
		for(var i=0;i<chks.length;i++){
			$.ajax({
				url: "admin/message/del",
				type: 'POST',
				data: "id="+chks[i].value,
				async : false
				});
		}
		parent.frames['rightFrame'].location='<%=basePath%>admin/vehicle/apply/list1';
}
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
        <li class="click" onclick="datadel()" ><span><img src="images/t04.png" /></span>详情</li>
       <!--  <li class="click"><span><img src="images/t02.png" /></span>修改</li>
        <li><span><img src="images/t03.png" /></span>删除</li>
        <li><span><img src="images/t04.png" /></span>统计</li> -->
        </ul>
        
        
       <!--  <ul class="toolbar1">
        <li><span><img src="images/t05.png" /></span>设置</li>
        </ul> -->
    
    </div> 
    
    
    <table class="tablelist">
    	<thead>
    	<tr>
        <th><input name="" type="checkbox" value="" /></th>
        <th>编号<i class="sort"><img src="images/px.gif" /></i></th>
        <th>消息内容</th>
        </tr>
        </thead>
        <tbody>
       		<c:forEach items="${messageList}" var="c">
				<tr class="text-c">
					<td><input type="checkbox" value="${c.id } " checked="checked" name="" /></td>
					<td>${c.id }</td>
					<td>${c.content }</td>
				</tr>
			</c:forEach>
        </tbody>
    </table>
    
   
   <%--  <div class="pagin">
    	<div class="message">共<i class="blue">${messagePager.totalCount }</i>条记录，当前显示第&nbsp;<i class="blue">${deptmentList.currentPage }&nbsp;</i>页</div>
        <ul class="paginList">
        <li class="paginItem"><a href="javascript:;"><span class="pagepre"></span></a></li>
        <li class="paginItem"><a href="javascript:;">1</a></li>
        <li class="paginItem current"><a href="javascript:;">2</a></li>
        <li class="paginItem"><a href="javascript:;">3</a></li>
        <li class="paginItem"><a href="javascript:;">4</a></li>
        <li class="paginItem"><a href="javascript:;">5</a></li>
        <li class="paginItem more"><a href="javascript:;">...</a></li>
        <li class="paginItem"><a href="javascript:;">10</a></li>
        <li class="paginItem"><a href="javascript:;"><span class="pagenxt"></span></a></li>
        </ul>
    </div> --%>
    
    
    </div>
    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	</script>

</body>

</html>
