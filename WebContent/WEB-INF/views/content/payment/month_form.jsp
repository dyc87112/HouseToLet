<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<br>
<div class="container" style="width: 100%">
	<form:form commandName="payment" class="form-horizontal"
		action="./create?rentalId=${rental.id }&isFirst=false&isClose=false"
		method="post">
		<fieldset>
			<legend>
				账单结算（月结）-- <a href="#" style="color: red;">${rental.room.name
					}房间</a>
			</legend>
			<div class="control-group">
				<label class="control-label">租客基本信息：</label>
				<div class="controls">
					客户姓名：${rental.customerName }<br> 客户电话：${rental.customerTel }<br>
					客户证件号码：${rental.customerId }<br> <br>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">基础租金：</label>
				<div class="controls">
					<form:input path="basePayment" type="text" readonly="true" />
					（单位：元）
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">结算日期：</label>
				<div class="controls">
					<form:input path="startDate" type="text" readonly="true" />至
					<form:input path="endDate" type="text" readonly="true" />
				</div>
			</div>
			<form:input path="deposit" type="hidden" />
			<div class="control-group">
				<label class="control-label">上月电表读数：</label>
				<div class="controls">
					<form:input path="startElect" type="text" placeholder="请输入电表读数"
						readonly="true" />
					（单位：千瓦时）
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">本月电表读数：</label>
				<div class="controls">
					<form:input path="endElect" type="text" placeholder="请输入电表读数"
						class="{validate:{ required:true,number:true,min:${payment.startElect } }}" />
					（单位：千瓦时）
				</div>
			</div>
			<div class="control-group" style="display: none;">
				<label class="control-label">电费单价：</label>
				<div class="controls">
					<form:input path="electPrice" type="text" readonly="true" />
					（单位：元/千瓦时）
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">上月水表读数：</label>
				<div class="controls">
					<form:input path="startWater" type="text" placeholder="请输入水表读数"
						readonly="true" />
					（单位：吨）
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">本月水表读数：</label>
				<div class="controls">
					<form:input path="endWater" type="text" placeholder="请输入水表读数"
						class="{validate:{ required:true,number:true,min:${payment.startWater } }}" />
					（单位：吨）
				</div>
			</div>
			<div class="control-group" style="display: none;">
				<label class="control-label">水费单价：</label>
				<div class="controls">
					<form:input path="waterPrice" type="text" readonly="true" />
					（单位：元/吨）
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">调整金额：</label>
				<div class="controls">
					<form:input path="adjustSum" type="text" placeholder="请输入调整金额"
						class="{validate:{ required:true,number:true }}" />
					（单位：元）
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">调整金额说明：</label>
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
					（网费：${rental.user.netPrice }元/月）
				</div>
			</div>
			<div class="control-group">
				<label class="control-label"></label>
				<div class="controls">
					<button class="btn btn-normal btn-primary" type="submit">提交</button>
				</div>
			</div>
		</fieldset>
	</form:form>
</div>
<script type="text/javascript">
	$(document).ready(
			function() {
				$("#payment").validate({});
			});
</script>