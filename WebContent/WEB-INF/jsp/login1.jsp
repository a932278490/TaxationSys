<%@ page language="java" pageEncoding="UTF-8"%>  
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>  
<!DOCTYPE html>
<html lang="zh-CN" class="bg">
<head>
	<base href="<%=basePath %>" />
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
	<!-- Bootstrap -->
	<link href="css/bootstrap.min.css" rel="stylesheet">

	<!-- HTML5 Shim and Respond.js IE8 flowers of HTML5 elements and media queries -->
	<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	<!--[if lt IE 9]>
	<script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	<script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
	<![endif]-->

	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<link rel="stylesheet" type="text/css" href="css/admin.css">
	<script>
		function login(){
			$.ajax({
				url: "<%=basePath %>login/login.do",
				type: 'POST',
				dataType: 'json',
				data: $('#loginform').serialize(),
				success:function(data){
					if (data.status) {
						window.location.href=data.url; 
					}else{
						alert(data.info);
					};
				}
			});
			return false;
		};
	</script>
</head>
<body style="margin:0;padding:0;height:100%;background-size:100% 100%;" background="img/dsjbgimg.jpg" >
<div style="width:400px;position:absolute;top:50%;left:50%;margin-left:-200px;margin-top:-250px;overflow:auto;">
	<div class="well well-lg" style="width:100%;margin-bottom:0px;background:rgba(255,255,255, 0.7);">
		<div class="text-center">
		<h4>经济技术开发区地税局</h4>
		<h1>行政管理信息系统</h1></div>
		<br>
		<form id="loginform">
			<div class="form-group form-group-lg">
				<div class="input-group">
					<div class="input-group-addon">账 <span style="color:#eee;">一</span> 号:</div>
					<input type="text" name="account" class="form-control" id="account" placeholder="">
				</div>
			</div>
			<div class="form-group form-group-lg">
			
				<div class="input-group">
					<div class="input-group-addon">密 <span style="color:#eee;">一</span> 码:</div>
					<input type="password" name="password" class="form-control" id="password" placeholder="">
				</div>
			</div>
	
			<hr>
			<button type="submit" class="btn btn-danger btn-lg btn-block" onclick="return login();">登陆</button>
		</form>
		<br>
		<p class="text-muted" style="border: 1px #D0D0D0 dotted;padding: 10px;background: #FFF;">信息科技术支持</p>
		
		
	</div>
</div>
</body>
</html>