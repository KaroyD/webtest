<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册</title>

<script type="text/javascript">
	/*
	创建一个函数，用于改变验证码图片
	*/
	function _change(){
		var ele = document.getElementById("vCode");
		ele.src = "${pageContext.request.contextPath}/VerifyCodeServlet?xxx=" + new Date().getTime();
	}
</script>

</head>
<body>
<h1>注册</h1>
<p style="color: red; font-weight: 900">${msg }</p>
<form action="${pageContext.request.contextPath}/RegistServlet" method="post">
	用户名：<input type="text" name="username" value="${user.username}"/>${errors.username }<br/>
	密  码：<input type="password" name="password" value="${user.password}"/>${errors.password }<br/>
	验证码：<input type="text" name="verifyCode" value="${user.verifyCode}" size="3"/>
		  	<img id="vCode" src="${pageContext.request.contextPath}/VerifyCodeServlet" border="2"/>
		  	<a href="javascript:_change()">换一张</a>${errors.verifyCode }<br/>
		  	<input type="submit" value="注册"/>
</form>
</body>
</html>