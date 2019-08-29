<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <head>
    <base href="<%=basePath%>">
    <title>登录</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style type="text/css">
		input
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
		input:focus{
			border:1px solid #3c3c3c;
		}
		form>:last-child:hover{
			background:#f54;
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
    	<h1>欢迎登录</h1>
    	<h3 style="color:red;">
    		${param.relogin eq "nouser" ? "用户名错误" : ""}
    		${param.relogin eq "pass" ? "密码错误" : ""}
    		${param.relogin eq "codeerror" ? "验证码错误" : ""}
    		${param.relogin eq "unknown" ? "未知错误" : ""}
    	</h3>
    	<form action="loginctlr/loginFail.do" method="post">
    	<span>用户名:</span><input type="text" name="uname"/><br><br><br>
    	<span>密&nbsp;码:</span><input type="password" name="upass"/><br><br><br>
    	<span>验证码:</span><input type="text" name="randomcode"/><br>
    	<img alt="" src="validatecode.jsp" align="absMiddle" id="valimg">
    	<a href="javascript:srcRefresh()">刷新</a>
    	<script type="text/javascript">
			function srcRefresh(){
				document.getElementById("valimg").src = "<%=basePath %>validatecode.jsp"+"?" + Math.random();
			}    	
    	</script>
    	<br><br><br>
    	<input type="submit" value="登录"/>
    	</form>
    	</center>
  </body>
</html>
