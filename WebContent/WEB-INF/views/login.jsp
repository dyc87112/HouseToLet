<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.js"></script>
<style type="text/css">
body {
	padding-top: 40px;
	padding-bottom: 40px;
	background-color: #f5f5f5;
}

.form-signin {
	max-width: 300px;
	padding: 19px 29px 29px;
	margin: 0 auto 20px;
	background-color: #fff;
	border: 1px solid #e5e5e5;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	border-radius: 5px;
	-webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
	-moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
	box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
}

.form-signin .form-signin-heading,.form-signin .checkbox {
	margin-bottom: 10px;
}

.form-signin input[type="text"],.form-signin input[type="password"] {
	font-size: 16px;
	height: auto;
	margin-bottom: 15px;
	padding: 7px 9px;
}
</style>

<title>House To Let</title>
</head>
<body>
	<div class="container">
		<form:form commandName="user" class="form-signin" action="./login" method="post">
			<h3 class="form-signin-heading">登录</h3>
			<form:errors path="username" cssStyle="color:red"/> 
			<form:input path="username" type="text" class="input-block-level" placeholder="请输入用户名" />
			<form:errors path="password" cssStyle="color:red"/> 
			<form:input path="password" type="password" class="input-block-level" placeholder="请输入密码" /> 
			<label class="checkbox">
				<input type="checkbox" value="remember-me">记住我
			</label>
			<button class="btn btn-normal btn-primary" type="submit">登录</button>
			<a class="btn btn-normal" href="<%=request.getContextPath()%>/register">注册</a>
		</form:form>
	</div>
</body>
</html>