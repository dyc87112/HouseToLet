<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.js"></script>
<style type="text/css">
body {
	font-family: Microsoft YaHei, Droid Sans Mono;
	padding-top: 60px;
	padding-bottom: 40px;
}

.sidebar-nav {
	padding: 9px 0;
}
</style>
<title>House To Let</title>
</head>
<body>
	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container-fluid">
				<a class="btn btn-navbar" data-toggle="collapse"
					data-target=".nav-collapse"> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span>
				</a> <a class="brand" href="<%=request.getContextPath() %>/index">House To Let</a>
				<div class="nav-collapse collapse">
					<p class="navbar-text pull-right">
						欢迎${sessionScope.user.username}登录系统，<a href="<%=request.getContextPath() %>/logout" class="navbar-link">退出</a>
					</p>
					<ul class="nav">
						<li class="active"><a href="<%=request.getContextPath() %>/index">Home</a></li>
						<li><a href="#about">About</a></li>
						<li><a href="#contact">Contact</a></li>
					</ul>
				</div>
				<!--/.nav-collapse -->
			</div>
		</div>
	</div>

	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span3">
				<div class="well sidebar-nav">
					<ul class="nav nav-list">
						<li class="nav-header">常用功能</li>
						<li id="newRental"><a href="<%=request.getContextPath()%>/rental/create">新建租赁</a></li>
						<li id="newPayment"><a href="<%=request.getContextPath()%>/payment/validRentalList">账单结算</a></li>
						<li id="netList"><a href="<%=request.getContextPath()%>/rental/listRentalNet">网络设置</a></li>
						<li class="nav-header">信息查询</li>
						<li id="emptyRoom"><a href="<%=request.getContextPath()%>/room/emptyList">空房查询</a></li>
						<li id="historyRental"><a href="#">历史租赁查询</a></li>
						<li id="historyPayment"><a href="#">历史账单查询</a></li>
						<li class="nav-header">综合管理</li>
						<li id="roomManage"><a href="<%=request.getContextPath()%>/room/list">房屋管理</a></li>
						<li id="rentalManage"><a href="<%=request.getContextPath()%>/rental/list">租赁管理</a></li>
						<li id="paymentManage"><a href="<%=request.getContextPath()%>/payment/list">账单管理</a></li>
						<li class="nav-header">统计分析</li>
						<li><a href="#">统计一</a></li>
						<li><a href="#">统计二</a></li>
						<li><a href="#">统计三</a></li>
						<li class="nav-header">系统设置</li>
						<li id="config"><a href="<%=request.getContextPath()%>/config">参数设置</a></li>
						<li><a href="<%=request.getContextPath()%>/modify/password">修改密码</a></li>
						<li><a href="<%=request.getContextPath()%>/modify/email">邮箱修改</a></li>
					</ul>
				</div>
				<!--/.well -->
			</div>
			<!--/span-->
			<div class="span9">
				<jsp:include page="../${pagePath}.jsp"></jsp:include>
			</div>
			<!--/span-->
		</div>
		<input id="pageContent" type="hidden" value="${pageContent }">
		<!--/row-->
		<hr>
		<footer>
			<p>&copy; Design By DIDI 2013</p>
		</footer>
	</div>
</body>
<script type="text/javascript">
	var pageContent = document.getElementById("pageContent").value;
	document.getElementById(pageContent).setAttribute("class", "active");
</script>
</html>