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
<title>出车审批列表</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 出车管理 <span class="c-gray en">&gt;</span> 出车申请管理 <a class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="pd-20">
	
	<div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l">
	<c:if test="${loginStuff.role.level>=3 }">
	<a href="javascript:;" onclick="datadel()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a>
	</c:if>
	</span> <span class="r">共有数据：<strong>${vehicleApplyPager.totalCount } </strong> 条</span> </div>
	<div class="mt-20">
	<table class="table table-border table-bordered table-hover table-bg table-sort">
		<thead>
			<tr class="text-c">
				<th width="25"><input type="checkbox" name="" value=""></th>
				<th width="50">ID</th>
				<th width="50">所属部门</th>
				<th width="50">人数</th>
				<th width="150">姓名</th>
				<th width="100">出发时间</th>
				<th width="100">还车时间</th>
				<th width="100">目的地</th>
				<th width="100">出车事由</th>
				<th width="100">申请时间</th>
				<th width="70">是否分配</th>
				<th width="70">状态</th>
				<c:if test="${loginStuff.role.level>=2 }">
				<th width="100">操作</th>
				</c:if>
			</tr>
		</thead>
		<tbody>
		    <c:forEach items="${vehicleApplyPager.dataList}" var="c">
				<tr class="text-c">
					<td><input type="checkbox" value="${c.id }" name=""></td>
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
					<td class="td-status"><span class="label label-danger radius">待分配</span></td>
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
					<c:if test="${loginStuff.role.level>=2 }">
					<td class="td-manage">
					<c:if test="${c.status==2 }">
					<a style="text-decoration:none" onClick="member_stop(this,'${c.id }')" href="javascript:;" title="取消审批"><span class="label label-danger radius">审批不通过</span></a>
					</c:if>
					<c:if test="${loginStuff.role.level==2 }">
						<c:if test="${c.status==1 }">
						<a style="text-decoration:none" onClick="member_stop(this,'${c.id }')" href="javascript:;" title="取消审批"><span class="label label-danger radius">审批不通过</span></a>
						</c:if>
					</c:if>
					<c:if test="${loginStuff.role.level==3 }">
						<c:if test="${c.status==1 }">
						<a style="text-decoration:none" onClick="member_startDistrubute(this,'${c.id }')" href="javascript:;" title="审批通过"><span class="label label-success radius">审批通过</span></a>
						</c:if>
						<c:if test="${c.status==0 }">
						<a style="text-decoration:none" onClick="member_startDistrubute(this,'${c.id }')" href="javascript:;" title="审批通过"><span class="label label-success radius">审批通过</span></a>
						</c:if>
					</c:if>
					<c:if test="${c.status==0 && loginStuff.role.level!=3 }">
					<a style="text-decoration:none" onClick="member_start(this,'${c.id }')" href="javascript:;" title="审批通过"><span class="label label-success radius">审批通过</span></a>
					</c:if>
					<!--  <a style="text-decoration:none" class="ml-5" onClick="change_password('修改密码','change-password.html','10001','600','270')" href="javascript:;" title="修改密码"><i class="Hui-iconfont">&#xe63f;</i></a>-->
					<a title="删除" href="javascript:;" onclick="member_del(this,'${c.id }')" class="ml-5" style="text-decoration:none"><span class="btn btn-link">删除</span></a>
					<c:if test="${c.status==3 }">
					<a title="导出" href="admin/vehicle/apply/print?id=${c.id }" class="ml-5" style="text-decoration:none"><span class="btn btn-link">导出</span></a>
					<a title="打印预览" href="admin/vehicle/apply/word?id=${c.id }" class="ml-5" style="text-decoration:none"><span class="btn btn-link">打印</span></a>
					</c:if>
					</td>
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
<script type="text/javascript" src="/TaxationSys/WebContent/lib/My97DatePicker/WdatePicker.js"></script> 
<script type="text/javascript" src="lib/datatables/1.10.0/jquery.dataTables.min.js"></script> 
<script type="text/javascript" src="js/H-ui.js"></script> 
<script type="text/javascript" src="js/H-ui.admin.js"></script> 
<script type="text/javascript">
$(function(){
	$('.table-sort').dataTable({
		"aaSorting": [[ 9, "dese" ]],//默认第几个排序
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
/*批量删除*/
/*批量删除*/
function datadel(){
	layer.confirm('确认要删除吗？',function(index){
		var chks=$("input:checked");//获取所有选中的checkbox,chks是一个元素数组
		for(var i=0;i<chks.length;i++){
			$.ajax({
				url: "admin/vehicle/apply/del",
				type: 'POST',
				data: "id="+chks[i].value,
				});
		}
		layer.msg('已删除!',{icon:1,time:1000});
		location.reload(true);
		
	});
	
}
/*用户-添加*/
function member_add(title,url,w,h){
	layer_show(title,url,w,h);
}
/*用户-查看*/
function member_show(title,url,id,w,h){
	layer_show(title,url,w,h);
}
/*用户-停用*/
function member_stop(obj,id){
	layer.confirm('确认要更改状态为审批未通过吗？',function(index){
		$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="member_start(this,id)" href="javascript:;" title="启用"><i class="Hui-iconfont">&#xe6e1;</i></a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-defaunt radius">已停用</span>');
		$(obj).remove();
		$.ajax({
			url: "admin/vehicle/apply/stop",
			type: 'get',
			async : false,
			data: "id="+id
			});
		
		layer.msg('已停用!',{icon: 5,time:1000});
		location.reload(true);    
	});
}

/*用户-启用*/
function member_start(obj,id){
	layer_show("通知领导","admin/vehicle/apply/adviceManger?id="+id,'','510');
}

function member_startDistrubute(obj,id){
	layer.confirm('确认审批通过',function(index){
		$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="member_stop(this,id)" href="javascript:;" title="停用"><i class="Hui-iconfont">&#xe631;</i></a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已启用</span>');
		$(obj).remove();
		$.ajax({
			url: "admin/vehicle/apply/start",
			type: 'post',
			async : false,
			data: "applyId="+id
			});
		layer.msg('已完成!',{icon: 6,time:1000});
		location.reload(true);  
	});
}
/*用户-编辑*/
function member_edit(title,url,id,w,h){
	url=url+"?id="+id;
	layer_show(title,url,w,h);
}
/*密码-修改*/
function change_password(title,url,id,w,h){
	layer_show(title,url,w,h);	
}
/*用户-删除*/
function member_del(obj,id){
	layer.confirm('确认要删除吗？',function(index){
		$.ajax({
			url: "admin/vehicle/apply/del",
			type: 'POST',
			async : false,
			data: "id="+id
			});
		
		$(obj).parents("tr").remove();
		layer.msg('已删除!',{icon:1,time:1000});
		location.reload(true);
	});
}

/*用户-打印*/
function member_print(obj,id){
	layer.confirm('确认要打印吗？',function(index){
		$.ajax({
			url: "admin/vehicle/apply/print",
			type: 'POST',
			async : false,
			data: "id="+id
			});
		layer.msg('已成功打印!请到D盘查看word文档！',{icon:1,time:1000}); 
		location.reload(true);
	});
}
</script> 
</body>
</html>