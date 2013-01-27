<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<br>
<div class="container" style="width: 100%">
	<form:form commandName="" class="form-horizontal" action="">
		<fieldset>
			<div style="float: right;">
				<a href="./create" class="btn btn-normal btn-primary">新增</a>
			</div>
			<input id="rentalType" type="hidden" value="${rentalType }" />
			<legend>租赁管理</legend>
			<div>
				<ul class="nav nav-tabs">
					<li id="current"><a href="./listCurrent">当前租赁</a></li>
					<li id="history"><a href="./listHistory">历史租赁</a></li>
					<li id="all"><a href="./listAll">所有租赁</a></li>
				</ul>
			</div>
			<table class="table table-striped table-hover">
				<thead>
					<tr>
						<th>房间名称</th>
						<th>客户姓名</th>
						<th>起租日期</th>
						<c:if test="${rentalType != 'current'}">
							<th>结束日期</th>
						</c:if>
						<th>基本房租</th>
						<th>押金</th>
						<th>是否使用网络</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${rentalList}" var="rental" varStatus="status">
						<tr>
							<td>${rental.room.name}</td>
							<td>${rental.customerName }</td>
							<td><fmt:formatDate value="${rental.startDate }" pattern="yyyy年MM月dd日" /></td>
							<c:if test="${rentalType != 'current'}">
								<td><fmt:formatDate value="${rental.endDate }" pattern="yyyy年MM月dd日" /></td>
							</c:if>
							<td>${rental.basePayment } 元</td>
							<td>${rental.deposit } 元</td>
							<c:if test="${rental.hasNet == true }"><td>是</td></c:if>
							<c:if test="${rental.hasNet == false }"><td>否</td></c:if>
							<td>
								<a href="./view/${rental.id }" class="btn btn-info">详细</a>
								<c:if test="${rental.isValid == true}">
									<a href="../payment/create?rentalId=${rental.id }&isClose=false" class="btn btn-warning">月结</a>
									<a href="../payment/create?rentalId=${rental.id }&isClose=true" class="btn btn-warning">退房</a>
									<a href="./del/${rental.id }" class="btn btn-danger">删除</a>
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</fieldset>
	</form:form>
</div>
<script type="text/javascript">
	var rentalType = document.getElementById("rentalType").value;
	document.getElementById(rentalType).setAttribute("class", "active");
</script>