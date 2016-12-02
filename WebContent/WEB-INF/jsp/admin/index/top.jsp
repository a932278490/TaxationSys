<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<script language="JavaScript" src="js/jquery.js"></script>
<script src="lib/jplayer/jquery.jplayer.min.js"></script>
<script src="lib/jplayer/jquery.jplayer.js"></script>
<script type="text/javascript">
$(function(){	
	//顶部导航切换
	$(".nav li a").click(function(){
		$(".nav li a.selected").removeClass("selected")
		$(this).addClass("selected");
	})	
})	
/*
 * the first time to call
 */
setTimeout(function(){
    Push();
//  alert("setTimeout called");
},200);

setInterval(function(){
    Push();
    //alert("setInterval called");
},15000);

function Push(){
	$.ajax({
		url: "admin/message/messageInfo",
		type: 'POST',
		dataType: 'json',
		data: 'stuffId='+${loginStuff.id},
		success:function(data){
			if (data.status) {
				$('#messageCount').html(data.content);
				if(data.content>0){
						$("#jplayer").jPlayer('play');
						alert("有新的消息");
						$("#messageClick").click();
				}
			}else{
			};
		}
	});
}

$(function() { 
    $("#jplayer").jPlayer({ 
      swfPath: "<%=basePath%>lib/jplayer/jquery.jplayer.swf", 
      ready: function () { 
        $(this).jPlayer("setMedia", { 
          mp3: "<%=basePath%>lib/jplayer/2487.mp3" 
        }); 
      }, 
      supplied: "mp3" 
    }); 
});

function messageList(){
	$.ajax({
		url: "admin/message/list",
		type: 'GET'
	});
}


</script>


</head>

<body style="background:url(images/topbg.gif) repeat-x;">
<div id="jplayer"></div>
    <div class="topleft">
    <img src="images/logo.png" title="系统首页" />
    </div>
    
        
    <ul class="nav">
    <li><a href="admin/index" target="rightFrame" class="selected"><img src="images/icon01.png" title="工作台" /><h2>使用指南</h2></a></li>
    <li><a href="admin/vehicle/list" target="rightFrame"><img src="images/icon02.png" title="模型管理" /><h2>用车情况</h2></a></li>
    <li><a href="admin/vehicle/apply/add"  target="rightFrame"><img src="images/icon03.png" title="模块设计" /><h2>发布申请</h2></a></li>
    <li><a href="admin/vehicle/apply/list"  target="rightFrame"><img src="images/icon04.png" title="常用工具" /><h2>申请状态</h2></a></li>
   <!--  <li><a href="admin/system/loginfo/list" target="rightFrame"><img src="images/icon05.png" title="文件管理" /><h2>文件管理</h2></a></li>
    <li><a href="admin/vehicle/apply/list"  target="rightFrame"><img src="images/icon06.png" title="系统设置" /><h2>系统设置</h2></a></li>
     --></ul>
            
    <div class="topright">    
    <ul>
    <!-- <li><span><img src="images/help.png" title="帮助"  class="helpimg"/></span><a href="#">帮助</a></li>
    <li><a href="#">关于</a></li> -->
    <li><a href="admin/logout" target="_parent">退出</a></li>
    </ul>
     
    <div class="user">
    <span>${loginStuff.name }</span>
    <i>消息</i>
    <span><a onclick="parent.frames['rightFrame'].location='<%=basePath%>admin/message/list'" id="messageClick"  target="rightFrame"><b id="messageCount" >0</b></a></span>
    </div>    
    
    </div>

</body>
</html>
