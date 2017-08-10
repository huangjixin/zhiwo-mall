<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>店铺编辑</title>
<%@ include file="/WEB-INF/include/easyui-css.jsp"%>
<%@ include file="/WEB-INF/include/easyui-js.jsp"%>
<script type="text/javascript" src="${ctx}/js/jquery-easyui/ajaxfileupload.js"></script>
<%--<script type="text/javascript" src="http://cdn.bootcss.com/blueimp-file-upload/9.17.0/js/vendor/jquery.ui.widget.min.js"></script>
<script type="text/javascript" src="http://cdn.bootcss.com/blueimp-file-upload/9.17.0/js/jquery.fileupload.js"></script>
<script type="text/javascript" src="http://cdn.bootcss.com/blueimp-file-upload/9.17.0/js/jquery.iframe-transport.min.js"></script>
<script type="text/javascript" src="http://cdn.bootcss.com/blueimp-file-upload/9.17.0/js/jquery.fileupload-process.min.js"></script>
<script type="text/javascript" src="http://cdn.bootcss.com/blueimp-file-upload/9.17.0/js/jquery.fileupload-image.min.js"></script>
<script type="text/javascript" src="http://cdn.bootcss.com/blueimp-file-upload/9.17.0/js/jquery.fileupload-audio.js"></script>
<script type="text/javascript" src="http://cdn.bootcss.com/blueimp-file-upload/9.17.0/js/jquery.fileupload-video.js"></script>
<script type="text/javascript" src="http://cdn.bootcss.com/blueimp-file-upload/9.17.0/js/jquery.fileupload-validate.js"></script>
<script type="text/javascript" src="http://cdn.bootcss.com/blueimp-file-upload/9.17.0/js/jquery.fileupload-ui.js"></script>--%>
</head>
<body>
<form class="form-horizontal" role="form" action="${ctx}/shop/create" method="post">
	<div class="form-group">
		<label for="name" class="col-sm-1 control-label">店铺名称</label>
		<div class="col-sm-4">
			<input type="text" class="form-control" id="name" name="name"
				   placeholder="请输入店铺名称" value="${shop.name}">
		</div>
	</div>
	<div class="form-group">
		<label for="code" class="col-sm-1 control-label">店铺代码</label>
		<div class="col-sm-4">
			<input type="text" class="form-control" id="code" name="code"
				   placeholder="请输入店铺代码(店铺拼音)" value="${shop.code}">
		</div>
	</div>
    <div class="form-group">
		<label for="file" class="col-sm-1 control-label">头像上传</label>
		<div class="col-sm-4">
			<input type="file" id="file" name="file" style="display: none;" accept="image/*" onChange="$('#message').html($('#file').val())"/>
             <button type="button" class="btn btn-success fileinput-button" onclick="$('#file').click();" >
             	<i class="fa fa-plus"></i>&nbsp;&nbsp;选择文件</button>
             <button type="button" class="btn btn-primary start"  onclick="fileUploadToServer();">
                    <i class="fa fa-upload"></i>
                    <span>&nbsp;&nbsp;开始上传</span>
              </button>
              <%@ include file="/WEB-INF/include/easyui-buttonForm.jsp"%>
              <label id="message">${message}</label>
		</div>
	</div>
    <div class="form-group">
		<label for="file" class="col-sm-1 control-label"></label>
		<div class="col-sm-4">
			<img id="icon" src="${shop.icon}" class=".img-responsive" style="width:100px;">
		</div>
	</div>
    <div class="form-group">
		<label for="file" class="col-sm-1 control-label">店铺内容</label>
		<div class="col-sm-10">
		<script id="container" name="content" type="text/plain">${shop.content}</script>
		</div>
	</div>
    
</form>
<!-- 配置文件 -->
    <script type="text/javascript" src="${ctx}/js/ueditor/ueditor.config.js"></script>
    <!-- 编辑器源码文件 -->
    <script type="text/javascript" src="${ctx}/js/ueditor/ueditor.all.js"></script>
    <!-- 实例化编辑器 -->

	<script type="text/javascript">
	var ue = UE.getEditor('container', {
        autoHeightEnabled: true,
        autoFloatEnabled: true,
        initialFrameHeight:483
    });
	// 初始化按钮等工作。
	$().ready(function() {
		//返回列表。
		$("#returnBtn").bind("click", function() {
			backToList('shop');
		});	
	});
		
	function fileUploadToServer() {
		var fileValue = $('#file').val();
	 	if(fileValue==''){
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
					if(data.assets.length>0){
						var assets = data.assets[0];
						$("#icon").attr('src','${ctx}/'+assets.url); 
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