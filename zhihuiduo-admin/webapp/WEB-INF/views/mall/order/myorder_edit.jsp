<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>订单编辑</title>
<%@ include file="/WEB-INF/include/easyui-css.jsp"%>
<%@ include file="/WEB-INF/include/easyui-js.jsp"%>
<script type="text/javascript"
	src="${ctx}/js/jquery-easyui/ajaxfileupload.js"></script>
</head>
<body>
	<form class="form-horizontal" role="form"
		<c:if test="${operation=='edit'}">
 action="${ctx}/order/update"
		</c:if>
		method="post">
		<div class="form-group">
			<label for="name" class="col-sm-1 control-label">订单编号</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" id="id" name="id" editable="false"
					placeholder="请输入订单名称" value="${order.id}">
			</div>
		</div>
		<div class="form-group">
			<label for="code" class="col-sm-1 control-label">订单代码</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" id="code" name="code"
					placeholder="请输入订单代码(订单拼音)" value="${order.code}">
			</div>
		</div>
		<div class="form-group">
			<label for="description" class="col-sm-1 control-label">订单描述</label>
			<div class="col-sm-4">
				<textarea name="description" class="form-control" rows="4" >${order.description}</textarea>
			</div>
		</div>
		<div class="form-group">
			<label for="file" class="col-sm-1 control-label"></label>
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
				backToList('order/myOrderList');
			});
		});
	</script>
	<%@ include file="/WEB-INF/include/easyui-footerjs.jsp"%>
</body>
</html>