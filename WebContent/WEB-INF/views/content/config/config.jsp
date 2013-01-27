<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<br>
<div class="container">
	<form:form commandName="userConfig" class="form-horizontal" action="./config" method="post">
		<fieldset>
			<legend>参数设置</legend>
			<div class="control-group">
				<label class="control-label" for="electPrice">电费单价（元/千瓦时）：</label>
				<div class="controls">
					<form:input  path="electPrice" type="text" placeholder="请输入电费" />
					<form:errors path="electPrice" cssStyle="color:red"/> 
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="waterPrice">水费单价（元/吨）：</label>
				<div class="controls">
					<form:input path="waterPrice" type="text" placeholder="请输入水费" /> 
					<form:errors path="waterPrice" cssStyle="color:red"/> 
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="netPrice">网费单价（元/月）：</label>
				<div class="controls">
					<form:input path="netPrice" type="text" placeholder="请输入网费" /> 
					<form:errors path="netPrice" cssStyle="color:red"/> 
				</div>
			</div>
			<div class="control-group">
				<label class="control-label"></label>
				<div class="controls">
					<button class="btn btn-normal btn-primary" type="submit">保存</button>
				</div>
			</div>
		</fieldset>
	</form:form>
</div>