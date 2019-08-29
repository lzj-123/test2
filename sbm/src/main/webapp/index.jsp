<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>主页</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
       <center>
       <h1>这&nbsp;是&nbsp;主&nbsp;页</h1>
       <h6 style="padding-left:25%;">当前用户:&nbsp;${empty nowuser ? "游客" : nowuser.username}<a href="<%=basePath%>logout.do" onclick="return comfirm('确定退出？')">退出登录</a></h6>
       <a href="userctlr/showUsers.do">查看用户</a><br><br><br>
       <a href="userctlr/goadd.do">添加用户</a><br><br><br>
       <a href="userctlr/showUsersPage.do">分页查看用户</a><br><br><br>
       <a href="table.jsp">查看用户json-easyui</a><br><br><br>
       
       </center>
  </body>
</html>
