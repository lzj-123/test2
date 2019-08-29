<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
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
       <h1>用&nbsp;户&nbsp;列&nbsp;表</h1>
       <h6 style="padding-left:25%;"><a href="userctlr/goadd.do">添加用户</a></h6>
       <table border="1" cellspacing="0" width="80%">
           <tr>
              <th>编号</th>
              <th>用户照片</th>
              <th>用户名称</th>
              <th>性别</th>
              <th>年龄</th>
              <th>生日</th>
              <th>状态</th>
              <th>操作</th>
           </tr>
           <c:forEach items="${pi.list }" var="users">
           <tr>
              <td>${users.uid }</td>
              <td><a href="userctlr/download.do?name=${users.pic }"><img src="upload/${users.pic }" width="50px" height="50px"></a></td>
              <td>${users.uname }</td>
              <td>${users.sex }</td>
              <td>${users.age }</td>
              <td><fmt:formatDate value="${users.birthday }" pattern="yyyy-MM-dd"/></td>
              <td>${users.active eq 1 ? "禁用" : "启用"}</td>
              <td>
              	<a href="userctlr/deleUser.do?uid=${users.uid }" onclick="return confirm('确定要删除掉该用户吗？')">删除</a>
              	<a href="userctlr/goupdate.do?uid=${users.uid }">编辑</a>
              </td>
           </tr>
           </c:forEach>
           <tr>
           		<td colspan="8" style="text-align: center">
           			当前${pi.pageNum}/${pi.pages}页,共${pi.total}条
           			<a href="userctlr/showUsersPage.do?pageNum=${pi.navigateFirstPage }">首页</a>
           			<a href="userctlr/showUsersPage.do?pageNum=${pi.prePage }">上一页</a>
           			<a href="userctlr/showUsersPage.do?pageNum=${pi.nextPage }">下一页</a>
           			<a href="userctlr/showUsersPage.do?pageNum=${pi.navigateLastPage }">尾页</a>
           		</td>
           </tr>
       </table>
       </center>
  </body>
</html>
