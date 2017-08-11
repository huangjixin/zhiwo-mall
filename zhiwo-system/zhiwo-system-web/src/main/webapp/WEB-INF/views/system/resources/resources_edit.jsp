<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>资源编辑</title>
<%@ include file="/WEB-INF/include/easyui-css.jsp"%>
<%@ include file="/WEB-INF/include/easyui-js.jsp"%>
<script type="text/javascript"
	src="${ctx}/js/jquery-easyui/ajaxfileupload.js"></script>
</head>
<body>
	<form class="form-horizontal" role="form"
		<c:if test="${operation=='edit'}">
 action="${ctx}/resources/update"
		</c:if>
		<c:if test="${operation==null}">
 action="${ctx}/resources/create"
		</c:if>
		method="post">
        <c:if test="${operation=='edit'}">
        <input id="id" name="id" value="${resources.id}" type="hidden"/>
		</c:if>
		<div class="form-group">
			<label for="name" class="col-sm-1 control-label">资源名称</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" id="name" name="name"
					placeholder="请输入资源名称" value="${resources.name}">
			</div>
		</div>
		<div class="form-group">
			<label for="authName" class="col-sm-1 control-label">权限名称</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" id="authName" name="authName"
					placeholder="请输入授权名称比如system:user:view" value="${resources.authName}">
			</div>
		</div>
		<div class="form-group">
			<label for="code" class="col-sm-1 control-label">代码</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" id="code" name="code"
					placeholder="请输入资源代码(资源拼音)" value="${resources.code}">
			</div>
		</div>
		
		<div class="form-group">
			<label for="description" class="col-sm-1 control-label">描述</label>
			<div class="col-sm-4">
				<textarea name="description" class="form-control" rows="4" >${resources.description}</textarea>
			</div>
		</div>
        <div class="form-group">
			<label  class="col-sm-1 control-label"></label>
			<div class="col-sm-4">
				<%@ include file="/WEB-INF/include/easyui-buttonForm.jsp"%>
				<label id="message">${message}</label>
			</div>
		</div>
	</form>

	<script type="text/javascript">
		
		// 初始化按钮等工作。
		$().ready(function() {

			//返回列表。
			$("#returnBtn").bind("click", function() {
				backToList('resources');
			});
		});

	</script>
	<%@ include file="/WEB-INF/include/easyui-footerjs.jsp"%>
</body>
</html>