<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<br>
<div class="container" style="width: 100%">
	<form:form commandName="payment" class="form-horizontal"
		action="./create?rentalId=${rental.id }&isFirst=true&isClose=false"
		method="post">
		<fieldset>
			<legend>
				账单结算（开房）--<a href="#" style="color: red;">${rental.room.name }房间</a>
			</legend>
			<div class="control-group">
				<label class="control-label">租客基本信息：</label>
				<div class="controls">
					客户姓名：${rental.customerName }<br> 客户电话：${rental.customerTel }<br>
					客户证件号码：${rental.customerId }<br>
					<br>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">结算日期：</label>
				<div class="controls">
					<form:input path="startDate" type="text" readonly="true" />
					至
					<form:input path="endDate" type="text" readonly="true" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">基础租金（元）：</label>
				<div class="controls">
					<form:input path="basePayment" type="text" readonly="true" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">押金（元）：</label>
				<div class="controls">
					<form:input path="deposit" type="text" readonly="true" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">起租电表读数：</label>
				<div class="controls">
					<form:input path="startElect" type="text" placeholder="请输入电表读数"
						class="{validate:{ required:true,number:true }}" />
					（单位：千瓦时）
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">起租水表读数：</label>
				<div class="controls">
					<form:input path="startWater" type="text" placeholder="请输入水表读数"
						class="{validate:{ required:true,number:true }}" />
					（单位：吨）
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">结转零头：</label>
				<div class="controls">
					<form:input path="adjustPrice" type="text" placeholder="请输入结转零头" readonly="true"
						class="{validate:{ required:true,number:true }}" />
					（单位：元）
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">其他费用：</label>
				<div class="controls">
					<form:input path="adjustSum" type="text" placeholder="请输入调整金额"
						class="{validate:{ required:true,number:true }}" />
					（单位：元）
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">其他费用说明：</label>
				<div class="controls">
					<form:input path="adjustInfo" type="text" placeholder="请输入调整金额说明" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">是否使用网络</label>
				<div class="controls">
					<form:select path="hasNet" placeholder="是否使用网络" readonly="true">
						<c:if test="${payment.hasNet == false }">
							<form:option value="false">否</form:option>
						</c:if>
						<c:if test="${payment.hasNet == true }">
							<form:option value="true">是</form:option>
						</c:if>
					</form:select>
					（网费：${payment.netPrice }元/月）
				</div>
			</div>
			<div class="control-group">
				<label class="control-label"></label>
				<div class="controls">
					<button class="btn btn-normal btn-primary" type="submit">生成账单</button>
				</div>
			</div>
			<form:input path="waterPrice" type="hidden" />
			<form:input path="netPrice" type="hidden" />
			<form:input path="electPrice" type="hidden" />
		</fieldset>
	</form:form>
</div>
<script type="text/javascript">
	$(document).ready(
		function() {
			$("#startDate").datepicker();
			$("#endDate").datepicker();
			$("#payment").validate({});
		});
</script>
