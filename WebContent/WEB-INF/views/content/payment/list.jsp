<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<br>
<div class="container" style="width: 100%">
	<form:form commandName="payment" class="form-horizontal" action="" method="post">
		<fieldset>
			<legend>账单管理</legend>
		</fieldset>
		<table class="table table-striped table-hover">
				<thead>
					<tr>
						<th>房间名称</th>
						<th>客户姓名</th>
						<th>结算时间</th>
						<th>账单类型</th>
						<th>基本房租</th>
						<th>押金</th>
						<th>水费</th>
						<th>电费</th>
						<th>网费</th>
						<th>调整费</th>
						<th>合计</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${paymentList}" var="payment" varStatus="status">
						<tr>
							<td>${payment.room.name}</td>
							<td>${payment.rental.customerName }</td>
							<td><fmt:formatDate value="${payment.startDate }" pattern="yyyy年MM月dd日" />-<br>
								<fmt:formatDate value="${payment.endDate }" pattern="yyyy年MM月dd日" /></td>
							<td>${payment.type } </td>
							<td>${payment.basePayment } 元</td>
							<td>${payment.deposit } 元</td>
							<td>${payment.waterPay } 元</td>
							<td>${payment.electPay } 元</td>
							<td>${payment.netPay } 元</td>
							<td>${payment.adjustSum } 元</td>
							<td>${payment.sumPay } 元</td>
							<td>
								<a href="./view/${payment.id }" class="btn btn-info">详细</a>
								<a href="./del/${payment.id }" class="btn btn-danger">删除</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
	</form:form>
</div>