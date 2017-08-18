<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>商品属性编辑</title>
<%@ include file="/WEB-INF/include/easyui-css.jsp"%>
<%@ include file="/WEB-INF/include/easyui-js.jsp"%>
</head>
<body>
	<form class="form-horizontal" role="form"
		<c:if test="${operation=='edit'}">
 action="${ctx}/productProperty/update"
		</c:if>
		<c:if test="${operation==null}">
 action="${ctx}/productProperty/create"
		</c:if>
		method="post">
        <c:if test="${operation=='edit'}">
        <input id="id" name="id" value="${productProperty.id}" type="hidden"/>
		</c:if>        
		<div class="form-group">
			<label for="name" class="col-sm-1 control-label">商品属性名称</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" id="name" name="name"
					placeholder="请输入商品属性名称" value="${productProperty.name}">
			</div>
		</div>
		<div class="form-group">
			<label for="code" class="col-sm-1 control-label">商品属性代码</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" id="code" name="code"
					placeholder="请输入商品属性代码(商品属性拼音)" value="${productProperty.code}">
			</div>
		</div>
		<div class="form-group">
			<label for="description" class="col-sm-1 control-label">属性描述</label>
			<div class="col-sm-4">
				<textarea name="description" class="form-control" rows="4" >${productProperty.description}</textarea>
			</div>
		</div>
        <div class="form-group">
			<label class="col-sm-1 control-label"></label>
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
				backToList('productProperty');
			});
		});
	</script>
	<%@ include file="/WEB-INF/include/easyui-footerjs.jsp"%>
</body>
</html>