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
<!--     
    	<ul class="toolbar">
        <li class="click"><span><img src="images/t01.png" /></span>添加</li>
        <li class="click"><span><img src="images/t02.png" /></span>修改</li>
        <li><span><img src="images/t03.png" /></span>删除</li>
        <li><span><img src="images/t04.png" /></span>统计</li>
        </ul> -->
        
        
        <ul class="toolbar1">
        <li onclick="window.location.href='<%=basePath%>admin/vehicle/apply/list1'"><span><img src="images/t04.png" /></span>查看未完成申请</li>
        </ul>
    
    </div> 
    
    
    <table class="tablelist">
    	<thead>
    	<tr>
        <th>编号<i class="sort"><img src="images/px.gif" /></i></th>
		<th>部门</th>
		<th>人数</th>
		<th>姓名</th>
		<th>出发时间</th>
		<th>还车时间</th>
		<th>目的地</th>
		<th>事由</th>
		<th>申请时间</th>
		<th>是否分配</th>
		<th>是否审批</th>
        <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${vehicleApplyPager.dataList}" var="c">
        
	        <tr>
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
				<td><font color="red"><strong>待分配</strong></font></td>
			</c:if>
			<c:if test="${c.status==0 }">
				<td class="td-status"><span><font color="red"><strong>未审批</strong></font></span></td>
			</c:if>
			<c:if test="${c.status==1 }">
				<td class="td-status"><span><font color="green"><strong>部门负责人通过</strong></font></span></td>
			</c:if>
			<c:if test="${c.status==2 }">
				<td class="td-status"><span><font color="green"><strong>局领导通过</strong></font></span></td>
			</c:if>
			<c:if test="${c.status==3 }">
				<td class="td-status"><span><font color="green"><strong>已完成</strong></font></span></td>
			</c:if>
			<c:if test="${c.status==4 }">
				<td class="td-status"><span><font color="red"><strong>已退回</strong></font></span></td>
			</c:if>
			<!--操作，复杂权限  -->
	        <td>
	        	<c:if test="${loginStuff.role.level==2 }">
					<c:if test="${c.status==1 }" >
						<a onclick="member_stop(this,'${c.id }')" href="javascript:;" title="取消审批"><span><font color="blue"><strong>不通过</strong></font></span></a>
					</c:if>
					<c:if test="${c.status==0 }" >
						<a href="admin/vehicle/apply/adviceManger?id=${c.id }" title="审批通过"><span><font color="blue"><strong>通过</strong></font></span></a>
						<a href="admin/vehicle/apply/refuse?id=${c.id }" title="退回"><span><font color="blue"><strong>退回</strong></font></span></a>
					</c:if>
				</c:if>
				
				<c:if test="${loginStuff.role.level==3 }">
					<c:if test="${c.status==0 }" >
						<a onclick="member_start(this,'${c.id }')" href="javascript:;" title="审批通过"><span><font color="blue"><strong>通过</strong></font></span></a>
						<a href="admin/vehicle/apply/refuse?id=${c.id }" title="退回"><span><font color="blue"><strong>退回</strong></font></span></a>
					
					</c:if>
					<c:if test="${c.status==1 }" >
						<a onclick="member_start(this,'${c.id }')" href="javascript:;" title="审批通过"><span><font color="blue"><strong>通过</strong></font></span></a>
						<a href="admin/vehicle/apply/refuse?id=${c.id }" title="退回"><span><font color="blue"><strong>退回</strong></font></span></a>
					
					</c:if>
					<c:if test="${c.status==2 }" >
						<a onclick="member_stop(this,'${c.id }')" href="javascript:;" title="取消审批"><span><font color="blue"><strong>不通过</strong></font></span></a>
					</c:if>
				</c:if>
	        	<c:if test="${c.status==3 }">
					<a title="导出" href="admin/vehicle/apply/export?id=${c.id }"><span><font color="blue"><strong>导出</strong></font></span></a>
					<a title="打印预览" href="admin/vehicle/apply/word?id=${c.id }" ><span><font color="blue"><strong>打印</strong></font></span></a>
				</c:if>
				<c:if test="${loginStuff.role.level==7 }">
					<a title="删除申请" href="javascript:;" onclick="member_del(${c.id});"><span><font color="red"><strong>删除</strong></font></span></a>
				</c:if>
	        </td>
	        <!--操作结束  -->
	        </tr> 
        </c:forEach>
        </tbody>
    </table>
    
   
        <div class="pagin">
    	<div class="message">共<i class="blue">${vehicleApplyPager.totalCount }</i>条记录，当前显示第&nbsp;<i class="blue">${vehicleApplyPager.currentPage }&nbsp;</i>页</div>
        <ul class="paginList">
        <li class="paginItem"><a href="admin/vehicle/apply/list?currentPage=${vehicleApplyPager.currentPage-1 }"><span class="pagepre"></span></a></li>
        <li class="paginItem"><a href="admin/vehicle/apply/list?currentPage=${vehicleApplyPager.currentPage+1 }"><span class="pagenxt"></span></a></li>
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
	function member_del(id){
			$.ajax({
				url: "admin/vehicle/apply/del",
				type: 'POST',
				async : false,
				data: "id="+id
				});
			location.reload(true);
	}

	/*用户-停用*/
	function member_stop(obj,id){
			$.ajax({
				url: "admin/vehicle/apply/stop",
				type: 'get',
				async : false,
				data: "id="+id
				});
			location.reload(true);    
	}
	
	/*用户-启用*/
	function member_start(obj,id){
			$.ajax({
				url: "admin/vehicle/apply/start",
				type: 'post',
				async : false,
				data: "applyId="+id
				});
			location.reload(true);  
	}
	</script>

</body>

</html>
