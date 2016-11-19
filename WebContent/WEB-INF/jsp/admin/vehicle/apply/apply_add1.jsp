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
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<script type="text/javascript" src="lib/PIE_IE678.js"></script>
<![endif]-->
<link href="css/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="css/H-ui.admin.css" rel="stylesheet" type="text/css" />
<link href="lib/icheck/icheck.css" rel="stylesheet" type="text/css" />
<link href="lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet" type="text/css" />
<!--[if IE 6]>
<script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="lib/My97DatePicker/WdatePicker.js"></script>
<title>申请出车</title>
<script type="text/javascript">

function addOption(obj){
	var str="";
	for(j=0;j<obj.length;j++){
		str+="<option value=\""+obj[j].name+"\">"+obj[j].name+"</option>";
	}
	return str;
}

</script>
</head>
<body>
<div class="pd-20">
	<form action="admin/vehicle/apply/save" method="post" class="form form-horizontal" id="form-admin-add">
		
		<input type="hidden" name="stuffId" value="${loginStuff.id}"/>
		<input type="hidden" name="deptId" value="${loginStuff.deptId }" />
		<%-- <div class="row cl">
			<label class="form-label col-3">申请部门：</label>
			<div class="formControls col-5"> <span class="select-box" style="width:150px;">
				<select class="select" name="deptId" size="1">
				 <c:forEach items="${deptmentList}" var="c">
					<option value="${c.id }">${c.name }</option>
				 </c:forEach>
				</select>
				</span> </div>
		</div> --%>
		
		<div class="row cl" id="pcount">
			<label class="form-label col-3">人数：</label>
			<div class="formControls col-5"> 
				<span class="select-box" style="width:150px;">
					<select class="select" name="pcount" size="1" onchange="addPeople(this.value)">
						<option value="0">0人</option>
						<option value="1">1人</option>
						<option value="2">2人</option>
						<option value="3">3人</option>
						<option value="4">4人</option>
						<option value="5">5人</option>
						<option value="6">6人</option>
						<option value="7">7人</option>
						<option value="8">8人</option>
						<option value="9">9人</option>
						<option value="10">10人</option>
					</select>
				</span>
			</div>
		</div>
		
		<div class="row cl">
			<div id="pname" class="formControls col-9"> 
			
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-3"><span class="c-red">*</span>出发时间：</label>
			<div class="formControls col-5">
				<input type="text" class="input-text" name="beginDate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" style="width:190px;" datatype="*" nullmsg="出车时间不能为空"" >
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-3"><span class="c-red">*</span>还车时间：</label>
			<div class="formControls col-2">
				<input type="text" class="input-text" name="endDate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})"  style="width:190px; datatype="*" nullmsg="还车时间不能为空"">
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-3"><span class="c-red">*</span>出车目的地：</label>
			<div class="formControls col-5">
				<input type="text" class="input-text" value="" placeholder="" id="user-name" name="destination" datatype="*2-16" nullmsg="目的地不能为空" style="width:150px;">
			</div>
			<div class="col-4"> </div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-3"><span class="c-red">*</span>出车事由：</label>
			<div class="formControls col-5">
				<input type="text" class="input-text" value="" placeholder="" id="user-name" name="reason" datatype="*2-16" nullmsg="出车事由不能为空" >
			</div>
			<div class="col-4"> </div>
		</div>

		<div class="row cl">
			<div class="col-9 col-offset-3">
				<input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
			</div>
		</div>
	</form>
</div>
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="lib/icheck/jquery.icheck.min.js"></script> 
<script type="text/javascript" src="lib/Validform/5.3.2/Validform.min.js"></script> 
<script type="text/javascript" src="lib/layer/1.9.3/layer.js"></script> 
<script type="text/javascript" src="js/H-ui.js"></script> 
<script type="text/javascript" src="js/H-ui.admin.js"></script> 
<script type="text/javascript">
function selectval(){
	str="";
	ocount = $("#pname  option:selected").length;
	for(i=0;i<ocount;i++){
		str+=$("#pname  option:selected")[i].innerText+",";
	}
	return str;
}

$(function(){
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	
	$("#form-admin-add").Validform({
		tiptype:2,
		callback:function(){
			layer.msg('添加成功',{icon:1,time:2000});
		}
	});
	
	
}); 

function addPeople(count){
	str="";
	$("#pname").empty();
	$.ajax({
		url: "admin/stuff/stuffinfo",
		type: 'POST',
		dataType: 'json',
		async : false,
		success:function(data){
			obj = data.content;
			str=addOption(obj);
		}
	});
	for(i=0;i<count;i++){
		$("#pname").append("<span class=\"select-box\" style=\"width:100px;\"><select id=\"pname\" class=\"select\" name=\"pname\" size=\"1\">"+str+"</select></span>");
	}
	
}

</script>
</body>
</html>