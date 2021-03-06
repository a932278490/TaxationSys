<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html>
<head>
    <base href="<%=basePath%>">
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,member-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<script type="text/javascript" src="lib/PIE_IE678.js"></script>
<![endif]-->
<link href="css/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="css/H-ui.admin.css" rel="stylesheet" type="text/css" />
<link href="lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet" type="text/css" />
<!--[if IE 6]>
<script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>出车申请列表</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 人事管理 <span class="c-gray en">&gt;</span> 员工信息管理 <a class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="pd-20">

	<div class="cl pd-5 bg-1 bk-gray mt-20"> 
	<c:if test="${loginStuff.role.level>=3 }">
	<span class="l"><a href="javascript:;" onclick="distribute(${vehicleId})" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i>分配该车</a></span> 
	</c:if>
	<span class="r">共有数据：<strong>${vehicleApplyPager.totalCount } </strong> 条</span> </div>
	<div class="mt-20">
	<table class="table table-border table-bordered table-hover table-bg table-sort">
		<thead>
			<tr class="text-c">
				<th width="25"><input type="checkbox" name="" value=""></th>
				<th width="80">ID</th>
				<th width="100">所属部门</th>
				<th width="90">人数</th>
				<th width="100">姓名</th>
				<th width="100">出发时间</th>
				<th width="100">还车时间</th>
				<th width="100">目的地</th>
				<th width="100">出车事由</th>
				<th width="100">申请时间</th>
				<th width="70">是否分配</th>
				<th width="70">状态</th>
			</tr>
		</thead>
		<tbody>
		    <c:forEach items="${vehicleApplyPager.dataList}" var="c">
				<tr class="text-c">
					<td><input type="checkbox" value="${c.id }" name=""></td>
					<td>${c.id }</td>
					<td>${c.deptId }</td>
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
					<td class="td-status"><span class="label label-danger radius">未分配</span></td>
					</c:if>
					<c:if test="${c.status==0 }">
					<td class="td-status"><span class="label label-danger radius">未审批</span></td>
					</c:if>
					<c:if test="${c.status==1 }">
					<td class="td-status"><span class="label label-success radius">科长已审批</span></td>
					</c:if>
					<c:if test="${c.status==2 }">
					<td class="td-status"><span class="label label-success radius">局领导已审批</span></td>
					</c:if>
					
				</tr>
			</c:forEach>
		</tbody>
	</table>

	</div>
</div>
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="lib/layer/1.9.3/layer.js"></script>
<script type="text/javascript" src="lib/laypage/1.2/laypage.js"></script> 
<script type="text/javascript" src="lib/My97DatePicker/WdatePicker.js"></script> 
<script type="text/javascript" src="lib/datatables/1.10.0/jquery.dataTables.min.js"></script> 
<script type="text/javascript" src="js/H-ui.js"></script> 
<script type="text/javascript" src="js/H-ui.admin.js"></script> 
<script type="text/javascript">
$(function(){
	$('.table-sort').dataTable({
		"aaSorting": [[ 1, "ase" ]],//默认第几个排序
		"bStateSave": true,//状态保存
		"aoColumnDefs": [
		  //{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
		  //{"orderable":false,"aTargets":[0,8,9]}// 制定列不参与排序
		]
	});
	$('.table-sort tbody').on( 'click', 'tr', function () {
		if ( $(this).hasClass('selected') ) {
			$(this).removeClass('selected');
		}
		else {
			table.$('tr.selected').removeClass('selected');
			$(this).addClass('selected');
		}
	});
});

function distribute(vehicleId){
	layer.confirm('确认要将选中的申请分配到该车吗？',function(index){
		var chks=$("input:checked");//获取所有选中的checkbox,chks是一个元素数组
		for(var i=0;i<chks.length;i++){
			$.ajax({
				url: "admin/vehicle/apply/dodistribute",
				type: 'POST',
				data: "id="+chks[i].value+"&vehicleId="+vehicleId,
				async : false,
				success:function(data){
					if (data.status) {
						layer.msg('分配成功!',{icon:1,time:2000});
					}else{
						layer.msg('分配失败，人数过多!',{icon:1,time:2000});
					};
				}
			});
		}
		location.reload(true);
	});
}

function fatherReload(){
	parent.location.reload(true); 
}

$(window).unload(function (evt) {  
    if (typeof evt == 'undefined') {  
        evt = window.event;  
    }  
    if (evt) {  
        var n = window.event.screenX - window.screenLeft;   
        var b = n > document.documentElement.scrollWidth-20;  
          
        if(b && window.event.clientY < 0 || window.event.altKey){  
            // 这个可以排除刷新 关闭的时候触发  
        }   
    }  
});  
</script> 
</body>
</html>