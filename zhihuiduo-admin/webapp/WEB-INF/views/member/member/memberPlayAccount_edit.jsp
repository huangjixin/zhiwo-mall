<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>会员智惠豆</title>
<%@ include file="/WEB-INF/include/easyui-css.jsp"%>
<%@ include file="/WEB-INF/include/easyui-js.jsp"%>
<script type="text/javascript" src="${ctx}/js/jquery-easyui/ajaxfileupload.js"></script>
</head>
<body>
	<form class="form-horizontal" role="form"
		<c:if test="${operation=='edit'}">
 action="${ctx}/member/update"
		</c:if>
		<c:if test="${operation==null}">
 action="${ctx}/member/create"
		</c:if>
		method="post">
        <c:if test="${operation=='edit'}">
        <input id="id" name="id" value="${member.id}" type="hidden"/>
		</c:if>
        <input id="icon" name="icon" value="${member.icon}" type="hidden"/>
		<div class="form-group">
			<label for="username" class="col-sm-1 control-label">会员名称</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" id="username" name="username"
					placeholder="请输入用户名称" value="${member.username}">
			</div>
		</div>
        <div class="form-group">
			<label for="password" class="col-sm-1 control-label">密码</label>
			<div class="col-sm-4">
				<input type="password" class="form-control" id="password" name="password"
					placeholder="请输入密码" value="${member.password}">
			</div>
		</div>
         <div class="form-group">
			<label for="passwordConfirm" class="col-sm-1 control-label">密码确认</label>
			<div class="col-sm-4">
				<input type="password" class="form-control" id="passwordConfirm"
					placeholder="请再次输入密码" value="">
			</div>
		</div>
		<div class="form-group">
			<label for="email" class="col-sm-1 control-label">邮箱</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" id="email" name="email"
					placeholder="请输入邮箱" value="${member.email}">
			</div>
		</div>
		<div class="form-group">
			<label for="mobilPhone" class="col-sm-1 control-label">手机</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" id="mobilPhone" name="mobilPhone"
					placeholder="" value="${member.mobilPhone}">
			</div>
		</div>
        <div class="form-group">
			<label for="weixin" class="col-sm-1 control-label">微信</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" id="weixin" name="weixin"
					placeholder="请输入微信号" value="${member.weixin}">
			</div>
		</div>
		<div class="form-group">
			<label for="file" class="col-sm-1 control-label">用户头像</label>
			<div class="col-sm-4">
				<input type="file" id="file" name="file" style="display: none;"
					accept="image/*" onChange="$('#message').html($('#file').val())" />
				<button type="button" class="btn btn-success fileinput-button"
					onclick="$('#file').click();">
					<i class="fa fa-plus"></i>&nbsp;&nbsp;选择文件
				</button>
				<button type="button" class="btn btn-primary start"
					onclick="fileUploadToServer();">
					<i class="fa fa-upload"></i> <span>&nbsp;&nbsp;开始上传</span>
				</button>
                <label id="message">${message}</label>
			</div>
		</div>
		<div class="form-group">
			<label for="file" class="col-sm-1 control-label"></label>
			<div class="col-sm-4">
				<img id="iconImg" <c:if test="${!empty member.icon}">src="${ctx}/${member.icon}" </c:if>class=".img-responsive"
					style="width: 100px;">
			</div>
		</div>
		<div class="form-group">
			<label for="description" class="col-sm-1 control-label">描述</label>
			<div class="col-sm-4">
				<textarea name="description" class="form-control" rows="4" >${member.description}</textarea>
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

		function fileUploadToServer() {
			var fileValue = $('#file').val();
			if (fileValue == '') {
				$('#message').html('请选择一个文件')
				return;
			}
			$('#message').html('正在上传……');
			var url = '${ctx}/fileupload/userAssets';
			$.ajaxFileUpload({
				url : url, //用于文件上传的服务器端请求地址
				secureuri : false, //是否需要安全协议，一般设置为false
				fileElementId : 'file', //文件上传域的ID
				dataType : 'json', //返回值类型 一般设置为json
				success : function(data, status) //服务器成功响应处理函数
				{
					if (data.assets.length > 0) {
						var assets = data.assets[0];
						$("#iconImg").attr('src', '${ctx}/' + assets.url);
						$("#icon").val(assets.url);
					}
					$('#message').html('');
				},
				error : function(data, status, e)//服务器响应失败处理函数
				{
					alert("上传失败");
				}
			})
		}
	</script>
	<%@ include file="/WEB-INF/include/easyui-footerjs.jsp"%>
</body>
</html>