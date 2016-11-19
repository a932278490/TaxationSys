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
<title>添加车辆信息</title>
</head>
<body>
<div class="pd-20">
	<form action="admin/vehicle/saveUpdate" method="post" class="form form-horizontal" id="form-admin-update">
		<div class="row cl">
			<label class="form-label col-3"><span class="c-red">*</span>车牌号：</label>
			<div class="formControls col-5">
				<input type="text" class="input-text"  placeholder="" id="user-name" name="licence" datatype="*2-16" nullmsg="车牌号不能为空">
			</div>
			<div class="col-4"> </div>
		</div>
		
		
		<div class="row cl">
			<label class="form-label col-3">司机：</label>
			<div class="formControls col-5">
				<input type="text" class="input-text"  placeholder="" id="user-tel" name="driver"  datatype="*2-16" >
			</div>
			<div class="col-4"> </div>
		</div>
		
		
		<div class="row cl">
			<label class="form-label col-3"><span class="c-red">*</span>车载量：</label>
			<div class="formControls col-5">
				<input type="" placeholder="" autocomplete="off"  class="input-text" datatype="n" nullmsg="请输入数字" name="capacity">
			</div>
			<div class="col-4"> </div>
		</div>
		<div class="row cl">
			<div class="col-9 col-offset-3">
				<input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;确认更改&nbsp;&nbsp;">
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
$(function(){
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	
	$("#form-admin-update").Validform({
		tiptype:2,
		callback:function(){
			var index = parent.layer.getFrameIndex(window.name);
			parent.location.reload(true);   
			$.ajax({
				url: "admin/vehicle/save",
				type: 'POST',
				data: $('#form-admin-update').serialize()
				});
			layer.msg('更改成功',{icon:1,time:1000});
			parent.layer.close(index);  
		}
	});
}); 
</script>
</body>
</html>