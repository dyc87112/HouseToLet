<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script>
$.validator.setDefaults({
	submitHandler : function() {
		form.submit();
	},
	showErrors : function(map, list) {
		var focussed = document.activeElement;
		if (focussed && $(focussed).is("input, textarea")) {
			$(this.currentForm).tooltip("close", {
				currentTarget : focussed
			}, true);
		}
		this.currentElements.removeAttr("title").removeClass(
				"ui-state-highlight");
		$.each(list, function(index, error) {
			$(error.element).attr("title", error.message).addClass(
					"ui-state-highlight");
		});
		if (focussed && $(focussed).is("input, textarea")) {
			$(this.currentForm).tooltip("open", {
				target : focussed
			});
		}
	}
});
</script>
<script>
	$(document).ready(function() {
		$("#startDate").datepicker();
		
		$("#rental").tooltip({
			show: { effect: "blind", duration: 300 },
			hide: { effect: "explode", duration: 800 },
			position: { 
					my: "left+15 center", 
					at: "right center" }
		});

		$("#rental").validate({
			rules : {
				customerName : "required",
				customerId : "required",
				customerTel : "required",
				startDate : {
					required : true,
					dateISO : true
				},
				basePayment : {
					required : true,
					number : true
				},
				deposit : {
					required : true,
					number : true
				}
			},
			messages: {
				customerName : {
					required : "客户名称必须输入"
				},
				customerId : {
					required : "客户证件必须输入"
				},
				customerTel : {
					required : "客户电话必须输入"
				},
				startDate : {
					required : "起租日期必须选择",
					dateISO : "日期格式不正确"
				},
				basePayment : {
					required : "基础租金必须输入",
					number : "租金必须为数字格式"
				},
				deposit : {
					required : "押金必须输入",
					number : "押金必须为数字格式"
				}
			}
			/*
			,
			errorPlacement: function(error, element) {    
				   error.appendTo( element.parent() );    
			},
			focusInvalid: false,    
			onkeyup: false  
			*/
			
		});
		
	});
</script>
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
					<form:input path="customerName" type="text" placeholder="请输入客户姓名" />
					<form:errors path="customerName" cssStyle="color:red" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="customerId">客户证件号码：</label>
				<div class="controls">
					<form:input path="customerId" type="text" placeholder="请输入客户证件号码" />
					<form:errors path="customerId" cssStyle="color:red" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="customerTel">客户电话：</label>
				<div class="controls">
					<form:input path="customerTel" type="text" placeholder="请输入客户电话" />
					<form:errors path="customerTel" cssStyle="color:red" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="startDate">起租日期：</label>
				<div class="controls">
					<form:input path="startDate" type="text" placeholder="请输入起租日期" />
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
					<form:input path="basePayment" type="text" placeholder="请输入基础房租" />
					<form:errors path="basePayment" cssStyle="color:red" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="deposit">押金（单位：元）：</label>
				<div class="controls">
					<form:input path="deposit" type="text" placeholder="请输入押金" />
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
