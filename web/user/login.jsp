<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>login</title>
</head>
<%-- 在客户端进行表单校验 --%>

<script type="text/javascript">
 	function clk(){
 		window.location.href="${pageContext.request.contextPath}/user/regist.jsp";
 	}
</script>

<body>
<h1>登陆</h1>
<p style="color: red; font-weight: 900">${msg }</p>
<form action="${pageContext.request.contextPath}/LoginServlet " method="post">
	用户名：<input type="text" name="username" value="${user.username}"/><br/>
	密  码：<input type="password" name="password" value="${user.password}"/><br/>
	<input type="submit" value="登陆"/> 
	<input type="button" value="注册" onclick="clk()"> 
</form>
</body>
</html>