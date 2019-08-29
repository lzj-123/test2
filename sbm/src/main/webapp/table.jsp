<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="<%=basePath%>jquery-easyui-1.5.5/jquery.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>jquery-easyui-1.5.5/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>jquery-easyui-1.5.5/locale/easyui-lang-zh_CN.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>jquery-easyui-1.5.5/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>jquery-easyui-1.5.5/themes/icon.css">
  </head>
  
  <body>
   	<table id="t1"></table>
   	
   	<script type="text/javascript">
   		$("#t1").datagrid({
   			url:"<%=basePath%>userctlr/showUsersJson.do",
   			dataType:"json",
   			width:"725px",
   			columns: [
   				[
   					{field:"uid",title:"编号",width:"100px"},
   					{field:"uname",title:"名称",width:"100px"},
   					{field:"upass",title:"密码",width:"100px"},
   					{field:"sex",title:"性别",width:"100px"},
   					{field:"age",title:"年龄",width:"100px"},
   					{field:"active",title:"状态",width:"100px"},
   					{field:"pic",title:"图片路径",width:"100px"}
   				]
   			]
   		})
   	</script>
  </body>
</html>
