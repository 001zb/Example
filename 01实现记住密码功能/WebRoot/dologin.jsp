<%@ page language="java" import="java.util.*" pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'dologin.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta charset="utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <%
    	//设置post访问格式
    	request.setCharacterEncoding("utf-8");
    	//通过name获得userName的val值
    	String userName=request.getParameter("userName");
    	//通过name获得password的val值
    	String password=request.getParameter("pwd");
    	//记住用户名密码功能(注意：cookie存放密码会存在安全隐患)
    	String remFlag=request.getParameter("remFlag");
    	if("1".equals(remFlag)){//1.表示用户勾选记住密码
			String loginInfo=userName+","+password;
			Cookie userCookie=new Cookie("loginInfo",loginInfo);
			userCookie.setMaxAge(60*60*24*30);//设置cookie存活期为一个月
			userCookie.setPath("/");
			response.addCookie(userCookie);
    	}
     %>
    
  </body>
</html>
