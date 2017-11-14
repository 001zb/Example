<%@ page language="java" import="java.util.*" pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>登录</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta charset="utf-8"/>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	
	-->
  </head>
  
  <body>
	<form action="dologin.jsp" method="post">
		<input type="text" id="userName" name="userName" value="${username }" placeholder="用户名">
		<br>
		<input type="password" id="pwd" name="pwd" maxLength="50" value="${password }" placeholder="密码">
		<br>
		<label for="remember-me">
  	 	 	<input id="remFlag" name="remFlag" type="checkbox" onclick="remember();"> 记住密码 
		</label>
		<div><input type="submit" value="登录"/></div>
	</form>
  </body>
</html>




