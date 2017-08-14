<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>会员账户</title>
<%@ include file="/WEB-INF/include/easyui-css.jsp"%>
<%@ include file="/WEB-INF/include/easyui-js.jsp"%>
</head>
<body>
	<form class="form-horizontal" role="form"
		<c:if test="${operation=='edit'}">
 action="${ctx}/memberAccount/update"
		</c:if>
		method="post">
        <c:if test="${operation=='edit'}">
        <input id="id" name="id" value="${memberAccount.id}" type="hidden"/>
		</c:if>
		<input id="memberId" name="memberId" value="${memberAccount.memberId}" type="hidden"/>
		<div class="form-group">
			<label for="realName" class="col-sm-1 control-label">会员实名</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" id="realName" name="realName"
					placeholder="请输入用户实名" value="${memberAccount.realName}">
			</div>
		</div>
        <div class="form-group">
			<label for="balance" class="col-sm-1 control-label">账号余额</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" id="balance" name="balance"
					placeholder="请输入账号余额" value="${memberAccount.balance}">
			</div>
		</div>
        
		<div class="form-group">
			<label for="locked" class="col-sm-1 control-label">是否锁定</label>
			<div class="col-sm-4">
				<select id="cc" class="easyui-combobox" name="locked" id="locked" style="width:200px;">   
				    <option value="0" <c:if test="${locked==false}">selected=true</c:if>>否</option>   
				    <option value="1" <c:if test="${locked==true}">selected=true</c:if>>是</option> 
				</select> 
			</div>
		</div>
		<div class="form-group">
			<label for="description" class="col-sm-1 control-label">描述</label>
			<div class="col-sm-4">
				<textarea name="description" class="form-control" rows="4" >${memberAccount.description}</textarea>
			</div>
		</div>
        <div class="form-group">
			<label  class="col-sm-1 control-label"></label>
			<div class="col-sm-4">
				<%@ include file="/WEB-INF/include/easyui-buttonForm.jsp"%>
			</div>
		</div>
	</form>

	<script type="text/javascript">
		
		// 初始化按钮等工作。
		$().ready(function() {

			//返回列表。
			$("#returnBtn").bind("click", function() {
				backToList('member');
			});
		});
	</script>
	<%@ include file="/WEB-INF/include/easyui-footerjs.jsp"%>
</body>
</html>