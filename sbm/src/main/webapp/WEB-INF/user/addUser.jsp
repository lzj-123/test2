<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>添加用户</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script language="javascript" type="text/javascript" src="<%=basePath%>My97DatePicker/WdatePicker.js"></script>
	<style type="text/css">
		.input
		{
			color:#594b47;
			padding:8px 0px 5px 10px;
			width:150px;
			height:30px;
			outline: none;
			border:1px solid #848586;
		}
		form>:last-child{
			margin-left:15px;
			padding:0;
			padding-left:25px;
			color:#fff;
			letter-spacing:2em;
			text-align:center;
			width:150px;
			height:30px;
			outline: none;
			background:#f40;
		}
		.input:focus{
			border:1px solid #3c3c3c;
		}
		form>:last-child:hover{
			background:#f54;
		}
		tr{
			height:50px;
		}
	</style>
	<script type="text/javascript">
		if("${param.islogin}" == "no"){
			alert("请登录 ");		
		}
	</script>
  </head>
  <body>
 		<center>
    	<h1>添&nbsp;加&nbsp;用&nbsp;户</h1>
        <h6 style="padding-left:25%;"><a href="index.jsp">返回主页</a></h6>
    	<form action="userctlr/addUser.do" method="post" enctype="multipart/form-data">
    	<table style="padding:0 0 20px 90px;" >
    	<tr>
    		<td style="text-align:right">用户名:</td>
    		<td><input type="text" name="uname" class="input"></td>
    	</tr>
    	<tr>
	    	<td style="text-align:right">密&nbsp;码:</td>
	    	<td><input type="password" name="upass" class="input"/></td>
    	</tr>
    	<tr>
	    	<td style="text-align:right">性&nbsp;别:</td>
	    	<td><input type="radio" name="sex" value="男" checked/>男<input type="radio" name="sex" value="女"/>女 </td>
    	</tr>
    	<tr>
	    	<td style="text-align:right">年&nbsp;龄:</td>
	    	<td><input type="text" name="age" class="input"/></td>
    	</tr>
    	<tr>
	    	<td style="text-align:right">生&nbsp;日:</td>
	    	<td><input name="birthday" class="input" type="text" onclick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})">
	    	</td>
    	</tr>
    	<tr>
	    	<td style="text-align:right">状&nbsp;态:</td>
	    	<td>
	    	<select name="active" class="input">
    		<option value="0" selected>启用</option>
			<option value="1">禁用</option>
			</td>
    	</tr>
    	<tr>
	    	<td style="text-align:right">头&nbsp;像:</td>
	    	<td><input type="file" name="files"/></td>
    	</tr>
    	</table>
    	<input type="submit" value="保存"/>
    	</form>
    	</center>
  </body>
</html>
