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
			<legend>网络使用情况</legend>
			<table class="table table-striped table-hover">
				<thead>
					<tr>
						<th>房间名称</th>
						<th>客户姓名</th>
						<th>起租日期</th>
						<th>基本房租</th>
						<th>押金</th>
						<th>网络使用状态</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${rentalList}" var="rental" varStatus="status">
						<tr>
							<td>${rental.room.name}</td>
							<td>${rental.customerName }</td>
							<td><fmt:formatDate value="${rental.startDate }" pattern="yyyy年MM月dd日" /></td>
							<td>${rental.basePayment } 元</td>
							<td>${rental.deposit } 元</td>
							<c:if test="${rental.hasNet == true }"><td>使用中</td></c:if>
							<c:if test="${rental.hasNet == false }"><td>未使用</td></c:if>
							<td>
								<c:if test="${rental.hasNet == true}">
									<a href="./setNet/${rental.id }?hasNet=false" class="btn btn-warning">冻结</a>
								</c:if>
								<c:if test="${rental.hasNet == false}">
									<a href="./setNet/${rental.id }?hasNet=true" class="btn btn-warning">启用</a>
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