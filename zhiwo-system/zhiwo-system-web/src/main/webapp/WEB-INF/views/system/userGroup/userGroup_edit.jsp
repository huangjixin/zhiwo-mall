<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>用户组编辑</title>
<%@ include file="/WEB-INF/include/easyui-css.jsp"%>
<%@ include file="/WEB-INF/include/easyui-js.jsp"%>
</head>
<body>
	<form class="form-horizontal" userGroup="form"
		<c:if test="${operation=='edit'}">
 action="${ctx}/userGroup/update"
		</c:if>
		<c:if test="${operation==null}">
 action="${ctx}/userGroup/create"
		</c:if>
		method="post">
        <c:if test="${operation=='edit'}">
        <input id="id" name="id" value="${userGroup.id}" type="hidden"/>
		</c:if>
		<div class="form-group">
			<label for="roles" class="col-sm-1 control-label">角色</label>
			<div class="col-sm-4">     	
		<select class="easyui-combobox" name="roles" data-options="multiple:true" style="width:250px;">
    	<c:forEach var="role" items="${roles}">
        	<option value="${role.id}" <c:if test="${role.selected==true}">selected=true</c:if> >${role.name}</option>
        </c:forEach>
        </select>
			</div>
		</div>
        <div class="form-group">
			<label for="name" class="col-sm-1 control-label">用户组名称</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" id="name" name="name"
					placeholder="请输入角色名称" value="${userGroup.name}">
			</div>
		</div>
		<div class="form-group">
			<label for="code" class="col-sm-1 control-label">代码</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" id="code" name="code"
					placeholder="请输入代码(拼音)" value="${userGroup.code}">
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
				backToList('userGroup');
			});
		});

		
	</script>
	<%@ include file="/WEB-INF/include/easyui-footerjs.jsp"%>
</body>
</html>