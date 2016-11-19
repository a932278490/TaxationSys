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
    <base href="<%=basePath%>">
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
        <li class="click" onclick="distribute(${vehicleId})"><span><img src="images/t01.png" /></span>分配该车</li>
        </ul>
        
        
        <ul class="toolbar1">
        <li><span><img src="images/t05.png" /></span>设置</li>
        </ul>
    
    </div>
    
    
    <table class="tablelist">
    	<thead>
    	<tr>
        <th><input name="" type="checkbox" value="" /></th>
        <th>编号<i class="sort"><img src="images/px.gif" /></i></th>
		<th>所属部门</th>
		<th>人数</th>
		<th>姓名</th>
		<th>出发时间</th>
		<th>还车时间</th>
		<th>目的地</th>
		<th>出车事由</th>
		<th>申请时间</th>
		<th>是否分配</th>
		<th>是否审批</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${vehicleApplyPager.dataList}" var="c">
        
	        <tr>
	        <td><input name="" type="checkbox" value="${c.id }" /></td>
	        <td>${c.id }</td>
			<td>${c.deptment.name }</td>
			<td>${c.pcount }</td>
			<td>${c.pname }</td>
			<td><f:formatDate value="${c.beginDate }" type="both"/></td>
			<td><f:formatDate value="${c.endDate }" type="both"/></td>
			<td>${c.destination }</td>
			<td>${c.reason }</td>
			<td><f:formatDate value="${c.orderDate }" type="both"/></td>
			<c:if test="${c.vehicleId!=0 }">
				<td>${c.vehicle.licence }</td>
			</c:if>
			<c:if test="${c.vehicleId==0 }">
				<td>待分配</td>
			</c:if>
			<c:if test="${c.status==0 }">
				<td class="td-status"><span class="label label-danger radius">未审批</span></td>
			</c:if>
			<c:if test="${c.status==1 }">
				<td class="td-status"><span class="label label-success radius">部门负责人审批通过</span></td>
			</c:if>
			<c:if test="${c.status==2 }">
				<td class="td-status"><span class="label label-success radius">局领导审批通过</span></td>
			</c:if>
			<c:if test="${c.status==3 }">
				<td class="td-status"><span class="label label-success radius">已完成</span></td>
			</c:if>
	        </tr> 
        </c:forEach>
        </tbody>
    </table>
    
   
   <%--  <div class="pagin">
    	<div class="message">共<i class="blue">${loginfoPager.totalCount }</i>条记录，当前显示第&nbsp;<i class="blue">${deptmentList.currentPage }&nbsp;</i>页</div>
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

	function distribute(vehicleId){
			var chks=$("input:checked");//获取所有选中的checkbox,chks是一个元素数组
			for(var i=0;i<chks.length;i++){
				$.ajax({
					url: "admin/vehicle/apply/dodistribute",
					type: 'POST',
					data: "id="+chks[i].value+"&vehicleId="+vehicleId,
					async : false,
					success:function(data){
						if (data.status) {
							alert('分配成功!');
						}else{
							alert('分配失败，人数过多!');
						};
					}
				});
			}
			location.reload(true);
	}

	</script>

</body>

</html>
