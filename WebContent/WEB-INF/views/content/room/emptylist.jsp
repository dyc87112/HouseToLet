<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<br>
<div class="container" style="width: 100%">
	<form:form commandName="" class="form-horizontal" action="">
		<fieldset>
			<legend>空房列表</legend>
			<table class="table table-striped table-hover">
				<thead>
					<tr>
						<th>#</th>
						<th>房间名称</th>
						<th>是否空房</th>
						<th>备注</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${roomList}" var="room" varStatus="status">
						<tr>
							<td>${status.index+1}</td>
							<td>${room.name }</td>
							<c:if test="${room.isEmpty== true }">
								<td>是</td>
							</c:if>
							<c:if test="${room.isEmpty == false }">
								<td>否</td>
							</c:if>
							<td>${room.info }</td>
							<td>
								<a href="../rental/create?roomId=${room.id }" class="btn btn-warning">新建租赁</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</fieldset>
	</form:form>
</div>