<%@ page import="indi.dpl.domain.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>welcome</title>
</head>

<%
	User user=(User)session.getAttribute("session_user");
	String name="";
	if(user==null){
		request.setAttribute("msg", "请登录");
		request.getRequestDispatcher("/user/login.jsp").forward(request, response);
		return;
	}
	else{
	    name=user.getUsername();
	}
%>
<body>
	<h1>欢迎<%=name%></h1>
</body>
</html>