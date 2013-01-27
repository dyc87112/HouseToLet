<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<br>
<div class="container">
	<form:form commandName="room" class="form-horizontal" action="${postPath}" method="post">
		<fieldset>
			<legend>${title }</legend>
			<div class="control-group">
				<label class="control-label" for="name">房间名称：</label>
				<div class="controls">
					<form:input path="name" type="text" placeholder="请输入房间名称" />
					<form:errors path="name" cssStyle="color:red"/> 
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="info">备注：</label>
				<div class="controls">
					<form:textarea path="info" type="text" placeholder="请输入备注信息" />
					<form:errors path="info" cssStyle="color:red"/> 
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
