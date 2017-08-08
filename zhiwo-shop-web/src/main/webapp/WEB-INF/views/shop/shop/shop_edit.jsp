<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>店铺编辑</title>
<%@ include file="/WEB-INF/include/easyui-css.jsp"%>
<%@ include file="/WEB-INF/include/easyui-js.jsp"%>
<script type="text/javascript" src="${ctx}/js/jquery-easyui/ajaxfileupload.js"></script>
</head>
<body>
<form class="form-horizontal" role="form" action="${ctx}/shop/create" method="post">
	<div class="form-group">
		<label for="name" class="col-sm-2 control-label">店铺名称</label>
		<div class="col-sm-4">
			<input type="text" class="form-control" id="name" 
				   placeholder="请输入店铺名称">
		</div>
	</div>
	<div class="form-group">
		<label for="code" class="col-sm-2 control-label">店铺代码</label>
		<div class="col-sm-4">
			<input type="text" class="form-control" id="code" 
				   placeholder="请输入店铺代码">
		</div>
	</div>
    <div class="form-group">
		<label for="file" class="col-sm-2 control-label">头像上传</label>
		<div class="col-sm-4">
			
            <input type="file" id="file" name="file" style="display: none;" onchange="$('#path').val($('#file').val());"
			 multiple accept="image/*"/>
             <button type="button" class="btn btn-primary" onclick="$('#file').click();" >选择文件</button>
             <button type="button" class="btn btn-primary" onclick="fileUpload();" >上传</button>
		</div>
	</div>
    
	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-10">
			<button type="submit" class="btn btn-default">提交</button>
		</div>
	</div>
</form>
<%@ include file="/WEB-INF/include/easyui-footerjs.jsp"%>
<script type="javascript">
	function fileUpload() {
		var fileValue = $('#file').val();
	 	if(fileValue==''){
	 		return;
	 	}
	 	var url = '${ctx}/fileupload/userAssets';
			$.ajaxFileUpload({
				url : url, //用于文件上传的服务器端请求地址
				secureuri : false, //是否需要安全协议，一般设置为false
				fileElementId : 'file', //文件上传域的ID
				dataType : 'json', //返回值类型 一般设置为json
				success : function(data, status) //服务器成功响应处理函数
				{
					alert("上传成功");
				},
				error : function(data, status, e)//服务器响应失败处理函数
				{
					alert("上传失败");
				}
			})
			return false;
		}
</script>
</body>
</html>