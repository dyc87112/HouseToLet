<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<br>
<div class="container" style="width: 100%">
	<form:form commandName="rental" class="form-horizontal"
		action="./create" method="post">
		<fieldset>
			<legend>新建租赁关系</legend>
			<div class="control-group">
				<label class="control-label">请选择租赁的房间：</label>
				<div class="controls">
					<form:select path="room.id">
						<c:forEach items="${roomList }" var="room" varStatus="status">
							<form:option value="${room.id }">${room.name }</form:option>
						</c:forEach>
					</form:select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="customerName">客户姓名：</label>
				<div class="controls">
					<form:input path="customerName" type="text" placeholder="请输入客户姓名" 
								class="{validate:{ required:true}}"/>
					<form:errors path="customerName" cssStyle="color:red" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="customerId">客户证件号码：</label>
				<div class="controls">
					<form:input path="customerId" type="text" placeholder="请输入客户证件号码"
								class="{validate:{ required:true}}" />
					<form:errors path="customerId" cssStyle="color:red" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="customerTel">客户电话：</label>
				<div class="controls">
					<form:input path="customerTel" type="text" placeholder="请输入客户电话" 
								class="{validate:{ required:true}}" />
					<form:errors path="customerTel" cssStyle="color:red" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="startDate">起租日期：</label>
				<div class="controls">
					<form:input path="startDate" type="text" placeholder="请选择起租日期" 
								class="{validate:{ required:true,dateISO : true}}"/>
					<form:errors path="startDate" cssStyle="color:red" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="hasNet">是否使用网络：</label>
				<div class="controls">
					<form:select path="hasNet" placeholder="是否使用网络">
						<form:option value="false">否</form:option>
						<form:option value="true">是</form:option>
					</form:select>
					<form:errors path="hasNet" cssStyle="color:red" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="basePayment">基础房租（单位：元）：</label>
				<div class="controls">
					<form:input path="basePayment" type="text" placeholder="请输入基础房租"
								class="{validate:{ required:true,number:true }}" />
					<form:errors path="basePayment" cssStyle="color:red" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="deposit">押金（单位：元）：</label>
				<div class="controls">
					<form:input path="deposit" type="text" placeholder="请输入押金"
								class="{validate:{ required:true,number:true }}" />
					<form:errors path="deposit" cssStyle="color:red" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="otherInfo">备注信息：</label>
				<div class="controls">
					<form:textarea path="otherInfo" type="text" placeholder="请输入备注信息" />
					<form:errors path="otherInfo" cssStyle="color:red" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="otherInfo"></label>
				<div class="controls">
					<button class="btn btn-normal btn-primary" type="submit">提交</button>
				</div>
			</div>
		</fieldset>
	</form:form>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		$("#startDate").datepicker();
		
		$("#rental").validate({
		});
		
	});
</script>