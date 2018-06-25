<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 	<title>富卫运维大平台</title>
 	<script type="text/javascript" src="http://remoteserver.com/remote.js"></script>
</head>
<body>
    
  <form id="form2" action="http://192.168.5.25:30002/attachment/insert" method="post">
 <br/>
 <br/>
 <br/>
        文件path1：<input type="text" name="path"><br/>
        文件url：  <input type="text" name="url"><br/>
       <input type="submit" value="提交2">
    </form>
    
    <form id="form3" action="http://192.168.5.25:30002/attachment/testUploadFiles" method="post" enctype="multipart/form-data">
 <br/>
 <br/>
 <br/>
        上传文件3：<input type="file" name="file1"><br/>
        上传文件3：<input type="file" name="file2"><br/>
       <input type="submit" value="提交3">
    </form>
    
<script src="${ctx}/resources/js/common/jquery-3.2.1.min.js"></script>
<script src="${ctx}/resources/js/common/ajaxfileupload.js" type="text/javascript"></script>
<script type="text/javascript">
function form1() {
	//var formData = new FormData($("#form1"));
	var formData =$("#form1").serialize();
	alert("formData==="+formData);
	$.ajax({
		   url: "http://192.168.5.25:30002/attachment/testUploadFiles",
		   type: "POST",
		   data: formData,
		   success:function(data){
			   alert("success----------");
		   },
			error:function() {
				alert("error----------");
			}
		});
	alert("end1-----------");  
}


</script>
</body>
</html>