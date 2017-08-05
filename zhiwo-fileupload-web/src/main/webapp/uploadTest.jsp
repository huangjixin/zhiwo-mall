<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-easyui/jquery.min.js">
	
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-easyui/ajaxfileupload.js">
	
</script>
<title>上传测试</title>
</head>
<body>
	<form enctype="multipart/form-data"  method="post"  action="${pageContext.request.contextPath}/fileupload/userAssets">
		<label>选择文件 </label> 
		<input type="file" id="file" name="file" 
			style="display: none;" onchange="$('#path').val($('#file').val());"
			 multiple/>
		<input type="button" id="selectFile" value="选择文件"
			onclick="$('#file').click();" /> 
		<input type="button" id="upload"
			name="upload" value="上传" onclick="fileUpload();" />
		<input id="path" name="path" class="easyui-validatebox" />
		
	</form>
	<form id="form1"  action="${pageContext.request.contextPath}/fileupload/userAssets">
		<div class="row">
		      <label for="fileToUpload">Select a File to Upload</label>
		<input type="file" name="fileToUpload" id="fileToUpload" onchange="fileSelected();"/>
		    </div>
		<div id="fileName"></div>
		<div id="fileSize"></div>
		<div id="fileType"></div>
		<div class="row">
		<input type="button" onclick="uploadFile()" value="Upload" />
		    </div>
		<div id="progressNumber"></div>
	</form>
	<script type="text/javascript">
      function fileSelected() {
        var file = document.getElementById('fileToUpload').files[0];
        if (file) {
          var fileSize = 0;
          if (file.size > 1024 * 1024)
            fileSize = (Math.round(file.size * 100 / (1024 * 1024)) / 100).toString() + 'MB';
          else
            fileSize = (Math.round(file.size * 100 / 1024) / 100).toString() + 'KB';

          document.getElementById('fileName').innerHTML = 'Name: ' + file.name;
          document.getElementById('fileSize').innerHTML = 'Size: ' + fileSize;
          document.getElementById('fileType').innerHTML = 'Type: ' + file.type;
        }
      }

      function uploadFile() {
        var fd = new FormData();
        fd.append("fileToUpload", document.getElementById('fileToUpload').files[0]);
        var xhr = new XMLHttpRequest();
        xhr.upload.addEventListener("progress", uploadProgress, false);
        xhr.addEventListener("load", uploadComplete, false);
        xhr.addEventListener("error", uploadFailed, false);
        xhr.addEventListener("abort", uploadCanceled, false);
        xhr.open("POST", "${pageContext.request.contextPath}/fileupload/userAssets");
        xhr.send(fd);
      }

      function uploadProgress(evt) {
        if (evt.lengthComputable) {
          var percentComplete = Math.round(evt.loaded * 100 / evt.total);
          document.getElementById('progressNumber').innerHTML = percentComplete.toString() + '%';
        }
        else {
          document.getElementById('progressNumber').innerHTML = 'unable to compute';
        }
      }

      function uploadComplete(evt) {
        /* This event is raised when the server send back a response */
        alert(evt.target.responseText);
      }

      function uploadFailed(evt) {
        alert("There was an error attempting to upload the file.");
      }

      function uploadCanceled(evt) {
        alert("The upload has been canceled by the user or the browser dropped the connection.");
      }
    </script>
	<script type="text/javascript">
	function fileUpload() {
		var fileValue = $('#file').val();
	 	if(fileValue==''){
	 		return;
	 	}
	 	var url = '${pageContext.request.contextPath}/fileupload/userAssets';
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