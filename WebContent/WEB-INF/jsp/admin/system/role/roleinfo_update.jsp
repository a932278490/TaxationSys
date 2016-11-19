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
<link href="css/select.css" rel="stylesheet" type="text/css" />
<link href="lib/My97DatePicker/skin/WdatePicker.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery.idTabs.min.js"></script>
<script type="text/javascript" src="js/select-ui.min.js"></script>
<script type="text/javascript" src="editor/kindeditor.js"></script>
<script type="text/javascript" src="lib/My97DatePicker/WdatePicker.js"></script>

<script type="text/javascript">
    KE.show({
        id : 'content7',
        cssPath : './index.css'
    });
  </script>
  
<script type="text/javascript">
$(document).ready(function(e) {
    $(".select1").uedSelect({
		width : 345			  
	});
	$(".select2").uedSelect({
		width : 167  
	});
	$(".select3").uedSelect({
		width : 100
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
    
    <div class="formbody">
    
    
    <div id="usual1" class="usual"> 
    
    <div class="itab">
  	<ul> 
    <li><a href="#tab1" class="selected">填写权限信息</a></li> 
  	</ul>
    </div> 
    
  	<div id="tab1" class="tabson">
    
    <form action="admin/system/role/saveUpdate" method="post" id="form-update">
    <input type="hidden" value="${role.id}" name="id" />	
    <ul class="forminfo">
    <li><label>权限名称<b>*</b></label><input name="name" value="${role.name }" type="text" class="dfinput" style="width:518px;"/></li>
   
    <li><label>权限级别<b>*</b></label>  
	    <div class="vocation">
		    <select class="select1" name="level">
					<option value="1">1级</option>
					<option value="2">2级</option>
					<option value="3">3级</option>
					<option value="4">4级</option>
					<option value="5">5级</option>
					<option value="6">6级</option>
					<option value="7">7级</option>
		    </select>
	    </div>
    </li>
   <!--  <li><label>通知内容<b>*</b></label>
    <textarea id="content7" name="content" style="width:700px;height:250px;visibility:hidden;"></textarea>
    </li> -->
    <li><label>&nbsp;</label><input type="submit" class="btn" value="确认更改"/></li>
    </ul>
    </form>
  	</div>
       
	</div> 
 
	<script type="text/javascript"> 
      $("#usual1 ul").idTabs(); 
    </script>
    
    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	</script>
    
    
    
    
    
    </div>


</body>

</html>
