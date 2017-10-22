<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>密码修改</title>
<%@ include file="/WEB-INF/include/easyui-css.jsp"%>
<%@ include file="/WEB-INF/include/easyui-js.jsp"%>
</head>
<body>
	<form class="form-horizontal" role="form" action="${ctx}/user/changePass"
		method="post">
        <input id="id" name="id" value="${user.id}" type="hidden"/>
        <div class="form-group">
			<label for="password" class="col-sm-1 control-label">用户旧密码</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" id="password" name="password"
					placeholder="请输入旧密码" value="${user.password}">
			</div>
		</div>
		<div class="form-group">
			<label for="newPassword" class="col-sm-1 control-label">用户密码确认</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" id="newPassword" name="newPassword"
					placeholder="请输入新密码" value="${newPassword}">
			</div>
		</div>
        <div class="form-group">
			<label for="newPasswordConfirm" class="col-sm-1 control-label">用户新密码</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" id="newPasswordConfirm" name="newPasswordConfirm"
					placeholder="请输入密码确认" value="">
			</div>
		</div>
        <div class="form-group">
			<label  class="col-sm-1 control-label"></label>
			<div class="col-sm-4">
				<button type="submit" id="submitBtn" class="btn btn-default">
                    <i class="fa fa-floppy-o" aria-hidden="true"></i>&nbsp;&nbsp;保存
                </button>
                <label id="message">${message}</label>
			</div>
		</div>
	</form>
	<script type="text/javascript">
		
		// 初始化按钮等工作。
		$().ready(function() {

		});
	</script>
	<%@ include file="/WEB-INF/include/easyui-footerjs.jsp"%>
</body>
</html>