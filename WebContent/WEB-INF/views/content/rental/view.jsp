<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<br>
<div class="container" style="width: 100%">
	<form:form commandName="rental" class="form-horizontal" action="./" method="post">
		<fieldset>
			<legend>${rental.room.name }房间租赁关系详细</legend>
			<div>
				租赁房间号：${rental.room.name } <br><br>
				客户姓名：${rental.customerName } <br>
				客户证件号码：${rental.customerId } <br>
				客户电话：${rental.customerTel } <br><br>
				起租日期：<fmt:formatDate value="${rental.startDate }" pattern="yyyy年MM月dd日" /><br>
				是否使用网络：<c:if test="${rental.hasNet == true }">是</c:if>
						<c:if test="${rental.hasNet == false }">否</c:if><br>
				基础房租：${rental.basePayment } 元<br>
				押金：${rental.deposit } 元<br>
				备注信息：${rental.otherInfo } <br><br>
				租赁创建日期：：<fmt:formatDate value="${rental.createDate }" pattern="yyyy年MM月dd日 HH时mm分ss秒" />
			</div>
			<br>
			<div>
				<a href="../update/${rental.id }" class="btn btn-info">编辑</a>
				<a href="../del/${rental.id }" class="btn btn-danger">删除</a>
			</div>
			<br>
			</fieldset>
	</form:form>
</div>