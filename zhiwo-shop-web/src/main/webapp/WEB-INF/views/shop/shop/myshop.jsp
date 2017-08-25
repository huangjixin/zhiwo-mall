<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的店铺</title>
<%@ include file="/WEB-INF/include/easyui-css.jsp"%>
<%@ include file="/WEB-INF/include/easyui-js.jsp"%>
<script type="text/javascript" src="${ctx}/js/jquery-easyui/ajaxfileupload.js"></script>
<script type="text/javascript" src="https://cdn.bootcss.com/jquery-jcrop/2.0.4/js/Jcrop.min.js"></script>
</head>
<body>
	<form class="form-horizontal" role="form" onSubmit="onSubmitHandler();" method="post">
		<input id="id" name="id" value="${shop.id}" type="hidden" /> 
		<input id="icon" name="icon" value="${shop.icon}" type="hidden" />
		<div class="form-group">
			<label for="name" class="col-sm-2 control-label">商铺名称</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" id="name" name="name"
					placeholder="请输入商品名称,开头关键字请用【】括号，否则审核不通过" value="${product.name}">
			</div>
		</div>
		<div class="form-group">
			<label for="code" class="col-sm-2 control-label">商品代码</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" id="code" name="code"
					placeholder="请输入商品代码(商品拼音)" value="${product.code}">
			</div>
		</div>
		<div class="form-group">
			<label for="description" class="col-sm-2 control-label">描述</label>
			<div class="col-sm-4">
				<textarea name="description" class="form-control" rows="8">${product.description}</textarea>
			</div>
		</div>
		<div class="form-group">
			<label for="file" class="col-sm-2 control-label">商铺头像</label>
			<div class="col-sm-4">
				<input type="file" id="file" name="file" style="display: none;"
					accept="image/*"
					onChange="$('#message').html($('#file').val());preImg(this.id,'iconImg');" />
				<button type="button" class="btn btn-success fileinput-button"
					onclick="$('#file').click();">
					<i class="fa fa-plus"></i>&nbsp;&nbsp;选择文件
				</button>
				<button type="button" class="btn btn-primary start"
					onclick="fileUploadToServer();">
					<i class="fa fa-upload"></i> <span>&nbsp;&nbsp;开始上传</span>
				</button>
				<button type="button" class="btn btn-success fileinput-button"
					onClick="window.open('${ctx}/js/webpzhaunhuan.zip');">下载图片压缩工具</button>
				<label id="message">${message}</label>
			</div>
		</div>
		<div class="form-group">
			<label for="file" class="col-sm-2 control-label"></label>
			<div class="col-sm-4">
				<label style="color: red;">缩略图要求图片长宽比例为2:1，用工具将图片压缩成webp格式。(预览)</label>
				<img id="iconImg"
					<c:if test="${!empty product.icon}">src="${ctx}/${product.icon}"</c:if>
					class=".img-responsive" width="200px;" height="100px;">
			</div>
		</div>
	</form>
	
<script type="text/javascript">
	//上传到服务器。
	function fileUploadToServer() {
		var fileValue = $('#file').val();
		if (fileValue == '') {
			$('#message').html('请选择一个文件')
			return;
		}
		$('#message').html('正在上传……');
		var url = '${ctx}/fileupload/userAssets?productId=' + $('#id').val();
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
	
	/** 
	 * 从 file 域获取 本地图片 url 
	 */
	function getFileUrl(sourceId) {
		var url;
		if (navigator.userAgent.indexOf("MSIE") >= 1) { // IE 
			url = document.getElementById(sourceId).value;
		} else if (navigator.userAgent.indexOf("Firefox") > 0) { // Firefox 
			url = window.URL.createObjectURL(document
					.getElementById(sourceId).files.item(0));
		} else if (navigator.userAgent.indexOf("Chrome") > 0) { // Chrome 
			url = window.URL.createObjectURL(document
					.getElementById(sourceId).files.item(0));
		}
		return url;
	}

	/** 
	 * 将本地图片 显示到浏览器上 
	 */
	function preImg(sourceId, targetId) {
		var url = getFileUrl(sourceId);
		var imgPre = document.getElementById(targetId);
		imgPre.src = url;
	}
</script>
<%@ include file="/WEB-INF/include/easyui-footerjs.jsp"%>
</body>
</html>