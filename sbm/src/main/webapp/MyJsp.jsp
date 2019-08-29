<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'MyJsp.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="<%=basePath%>js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript">
		$(function(){
			$("#name").blur(function(){
				var valu = $(this).val();
				if(valu == ""){
					alert("空的");
					return;
				}
				var url = "<%=basePath%>loginctlr/checkUname.do";
				var param = "uname=" + valu;
				
				$.ajax({
					type:"post",
					url:url,
					data:param,
					dataType:"json",
					success:function(m){
						console.log(m);
						$("#sp").html(m.content);
					},
					error:function(e){
						console.log(e);
						alert("请求失败，请联系管理员！");
					}				
				});
			});
		});
	</script>
  </head>
  
  <body>
    <center>
    	<form action=" ">
    		用户名：<input type="text" name="uname" id="name">
    		<span id="sp"></span><br>
    		密码：<input type="password" name="upass">
    	</form>
    
    </center>
  </body>
</html>
