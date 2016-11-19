<%@ page language="java" pageEncoding="UTF-8"%>  
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>欢迎登录后台管理系统</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="js/jquery.js"></script>
<script src="js/cloud.js" type="text/javascript"></script>

<script language="javascript">
	$(function(){
    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
	$(window).resize(function(){  
    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
    })  
});  
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
    document.onkeydown=function(event){
        var e = event || window.event || arguments.callee.caller.arguments[0];
        if(e && e.keyCode==27){ // 按 Esc 
            //要做的事情
          }
        if(e && e.keyCode==113){ // 按 F2 
             //要做的事情
           }            
         if(e && e.keyCode==13){ // enter 键
             $('#loginButton').click();
        }
    }; 
</script> 

</head>

<body style="background-color:#1c77ac; background-image:url(images/light.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">



    <div id="mainBody">
      <div id="cloud1" class="cloud"></div>
      <div id="cloud2" class="cloud"></div>
    </div>  
    
    <div class="loginbody">
    
    <span class="systemlogo"></span> 
       
    <div class="loginbox">
    
    <form id="loginform">
    <ul>
    <li><input name="account" type="text" class="loginuser" value="账号" onclick="JavaScript:this.value=''"/></li>
    <li><input name="password" type="password" class="loginpwd" value="密码" onclick="JavaScript:this.value=''"/></li>
    <li><input id="loginButton" type="button" onclick="return login();" class="loginbtn" value="登录"  />
    </li>
    </ul>
    </form>
    </div>
    </div>
</body>

</html>
