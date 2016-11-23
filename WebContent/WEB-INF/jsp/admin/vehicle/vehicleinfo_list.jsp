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
    	<c:if test="${loginStuff.role.name != '司机'}">
        <li onclick="window.location.href='<%=basePath%>admin/vehicle/add'"><span><img src="images/t01.png" /></span>添加</li>
        </c:if>
        <!-- <li class="click"><span><img src="images/t02.png" /></span>修改</li>
        <li><span><img src="images/t03.png" /></span>删除</li>
        <li><span><img src="images/t04.png" /></span>统计</li> -->
        </ul>
        
        
      <!--   <ul class="toolbar1">
        <li><span><img src="images/t05.png" /></span>设置</li>
        </ul> -->
    
    </div> 
    
    
    <table class="tablelist">
    	<thead>
    	<tr>
        <th>分配</th>
		<th>车牌号</th>
		<th>司机</th>
		<th>最大车载数</th>
		<th>剩余座位</th>
		<th>是否派出</th>
        <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${vehiclePager.dataList}" var="c">
        
	        <tr>
	        <td>
	        <c:if test="${loginStuff.role.level>3 }">
	        
	        <c:if test="${c.status==1 }">
	        <a href="admin/vehicle/apply/distributePage?vehicleId=${c.id }"><font color="green"><strong>分配员工到该车</strong></font></a>
	        </c:if>
	        </c:if>
	        </td>
			<td>${c.licence }</td>
			<td>${c.driver }</td>
			<td>${c.capacity }</td>
			<td><a href="admin/vehicle/apply/distributeInfo?vehicleId=${c.id }" >${c.remain }个,<font color="red"><strong>查看乘车信息</strong></font></a></td>
					
			<c:if test="${c.status==1 }">
			<td>未派出</td>
			</c:if>
			<c:if test="${c.status==0 }">
			<td>已派出</td>
			</c:if>
	        <td>
	        <c:if test="${loginStuff.role.level>3 }">
	        <c:if test="${c.status==1 }">
	        <c:if test="${loginStuff.role.name != '司机'}">
			<a onclick="member_stop(this,'${c.id }')" href="javascript:;" title="派出"><font color="blue"><strong>派出</strong></font></a>
			</c:if>		
			</c:if>
				<c:if test="${c.status==0 }">
				<c:if test="${c.driver == loginStuff.name }">
				<a onclick="member_start(this,'${c.id }')" href="javascript:;" title="车辆回库"><font color="blue"><strong>归还</strong></font></a>
				</c:if>
				</c:if>
				<c:if test="${loginStuff.role.name != '司机'}">
	        	<a href="admin/vehicle/update?id=${c.id }"><font color="blue"><strong>编辑</strong></font></a> 
	        	<a href="admin/vehicle/del?id=${c.id }"><font color="blue"><strong>删除</strong></font></a>  
	        	</c:if>
	        </c:if>
	        </td>
	        </tr> 
        </c:forEach>
        </tbody>
    </table>
    
   
      <div class="pagin">
    	<div class="message">共<i class="blue">${vehiclePager.totalCount }</i>条记录，当前显示第&nbsp;<i class="blue">${vehiclePager.currentPage }&nbsp;</i>页</div>
        <ul class="paginList">
        <li class="paginItem"><a href="admin/vehicle/list?currentPage=${vehiclePager.currentPage-1 }"><span class="pagepre"></span></a></li>
        <li class="paginItem"><a href="admin/vehicle/list?currentPage=${vehiclePager.currentPage+1 }"><span class="pagenxt"></span></a></li>
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

	/*用户-停用*/
	function member_stop(obj,id){
			$.ajax({
				url: "admin/vehicle/stop",
				type: 'get',
				async : false,
				data: "id="+id
				});
			location.reload(true);    
	}
	
	/*用户-启用*/
	function member_start(obj,id){
			$.ajax({
				url: "admin/vehicle/start",
				type: 'get',
				async : false,
				data: "id="+id
				});
			location.reload(true);
	}
	</script>
</body>

</html>
