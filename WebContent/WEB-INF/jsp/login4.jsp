<%@ page language="java" pageEncoding="UTF-8"%>  
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>  
<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<script type="text/javascript" src="lib/PIE_IE678.js"></script>
<![endif]-->
<!--[if IE 6]>
<script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>兰州经济技术开发区地方税务局信息管理系统后台登录界面</title>
	<script>
		function login(){
			$.ajax({
				url: "<%=basePath %>login/login.do",
				type: 'POST',
				dataType: 'json',
				data: $('#loginform').serialize(),
				success:function(data){
					if (data.status) {
						window.location.href="<%=basePath %>admin/main"; 
					}else{
						alert(data.info);
					};
				}
			});
			return false;
		};
	</script>
	<style>
	body {
	
 margin-top:0px;
 margin-right:0px;
 margin-bottom:0px;
 margin-left:0px;
 background-color:#CCDAF7;
 background-image:url(img/login_background.jpg);
 background-repeat:no-repeat;
 background-position:center;
 background-attachment:fixed;
}
#login_Button{
position: fixed;
top: 0px;
left: 0px;
width: 100px;
height: 220px;
}
#login_table{
	width:300px;
	margin:auto; /*外边距自动居中*/
/* 	margin-left:600px; */
	margin-top:450px 
}
	</style>
</head>
<body>
    <form id="loginform">
    <table id="login_table">
    	<tr>
    	<td><label>用户名</label>
    	</td>
    		<td >
    		<input name="account" style="width:150px;" type="text" placeholder="账户">
    		</td>
    		<td rowspan="2">
    		 <input id="login_button" type="button" onclick="return login()" style=" width:50px; height:56px; background:url(img/login_button.jpg) no-repeat bottom left;">
    		</td>
        </tr>
        <tr>
        <td><label>密&nbsp;  码</label>
    	</td>
        	<td>
        		<input name="password"  style="width:150px;" type="password" placeholder="密码" />
        	</td>
    	<tr>
    	
    	</tr>
    </table>
    
	<!-- 	<div>
          <input name="account" type="text" style="margin-left:600px; margin-top:450px"  placeholder="账户">
        </div>
        <div>
          <input name="password" type="password" style="margin-left:600px; margin-top:10px"  placeholder="密码">
        </div>  
        <div> 
        <input id="login_button" name="" type="button" onclick="return login();" style=" width:50px; height:56px; background:url(img/login_button.jpg) no-repeat bottom left;">
        </div> -->
    </form>
	<script type="text/javascript" src="js/jquery.js"></script> 

</body>
</html>