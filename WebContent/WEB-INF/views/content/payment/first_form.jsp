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
				<label class="control-label">结算日期：</label>
				<div class="controls">
					<form:input path="startDate" type="text" readonly="true" />
					至
					<form:input path="endDate" type="text" readonly="true" />
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
			<div class="control-group" style="display: none;">
				<label class="control-label">电费单价：</label>
				<div class="controls">
					<form:input path="electPrice" type="text" readonly="true" />
					（单位：元/千瓦时）
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
					<input id="netPrice" type="hidden" value="${rental.user.netPrice }" />
					（网费：${rental.user.netPrice }元/月）
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">合计：</label>
				<div class="controls">
					<input id="sumPay" type="text" readonly="readonly" placeholder="点击合计按钮计算" />元
				</div>
			</div>
			<div class="control-group">
				<label class="control-label"></label>
				<div class="controls">
					<button id="getSum" class="btn btn-normal btn-warning" type="button">合计</button>
					<button class="btn btn-normal btn-primary" type="submit">生成账单</button>
				</div>
			</div>
		</fieldset>
	</form:form>
</div>
<script type="text/javascript">
	$(document).ready(
		function() {
			$("#getSum").click(function(){
				// TODO AJAX请求提交表单，计算合计值	
				var sumPay = 0;
				$("#sumPay").attr("value", sumPay);
			});
			
			$("#startDate").datepicker({
					onSelect : function(dateText, inst) {
						var date = new Date(dateText);
						date.setDate(date.getDate() + 30);
						// FIXME 目前以30天为一个结账周期，需要改为一个月，要研究一下日期的计算方法
						/*
						if(date.getDate() == '31') {
							date.setMonth(date.getMonth() + 1);
							if(date.getDate() == '01') {
								date.setDate(date.getDate() - 1);
							}													
						} else {
							date.setMonth(date.getMonth() + 1);
						}
						*/
						$("#endDate").attr("value", date.Format("yyyy-MM-dd"));
					}
			});
			$("#payment").validate({});
		});
</script>
<script>
	Date.prototype.Format = function(fmt) {
		var o = {
			"M+" : this.getMonth() + 1, //月份 
			"d+" : this.getDate(), //日 
			"h+" : this.getHours(), //小时 
			"m+" : this.getMinutes(), //分 
			"s+" : this.getSeconds(), //秒 
			"q+" : Math.floor((this.getMonth() + 3) / 3), //季度 
			"S" : this.getMilliseconds()
		//毫秒 
		};
		if (/(y+)/.test(fmt))
			fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
					.substr(4 - RegExp.$1.length));
		for ( var k in o)
			if (new RegExp("(" + k + ")").test(fmt))
				fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
						: (("00" + o[k]).substr(("" + o[k]).length)));
		return fmt;
	}
</script>