<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<title>富卫运维大平台</title>
<meta name="description" content="">
</head>
<body>
        <link rel="stylesheet" href="${ctx}/resources/libs/ztree/zTreeStyle.css" type="text/css">
        <link rel="stylesheet" type="text/css" href="${ctx}/resources/css/style.css">
        <script type="text/javascript" src="${ctx}/resources/js/common/jquery-3.2.1.min.js"></script>
        <script type="text/javascript" src="${ctx}/resources/js/common/page.js"></script>
        <script type="text/javascript" src="${ctx}/resources/libs/layer/layer.js" ></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery-validation/1.11.1/jquery.validate.min.js"></script> 
<script type="text/javascript" src="${ctx}/resources/js/common/jquery-form.js"></script>
<style type="text/css">
	input.ipt-text{
		width:60%
	}
</style>
	<div class="wrapper">
		<form id="MaterialForm" method="post">
		    <input name="fileId" id ="fileId"  type="hidden" value="">	<!-- 附件Id -->	
			<input type="hidden" class="ipt-text" name="id" value="${material.id }" id="materialId">
			<input name="createType" id ="createType"  type="hidden" value="1"> <!--区分从课程中新建资料  -->
			<input name="form" id="form" type="hidden" value="${form}"/>
			<div class="form-detail">
				<ul class="clearfix form_learn">
					<li class="col-md-12"><strong><ins>*</ins>资料名称：</strong>
					   <input type="text" class="ipt-text required" id="name" name="name" value="${material.name }"> &nbsp;&nbsp;
					</li>
					<li class="col-md-12"><strong><ins>*</ins>资料说明：</strong>
					   <input type="text" name="description" id="description" class="ipt-text required" value="${material.description }">&nbsp;&nbsp;
				    </li>
					<li class="col-md-4">
						<strong><ins>*</ins>资料分类：</strong>
						<div class="ipt-select">
							<select name="type">
								<option <c:if test="${material.type==1 }">selected</c:if> value="1">视频</option>
								<option <c:if test="${material.type==2 }">selected</c:if>  value="2">SCORM</option>
								<option <c:if test="${material.type==3 }">selected</c:if> value="3">PPT</option>
								<option <c:if test="${material.type==4 }">selected</c:if> value="4">WORD</option>
								<option <c:if test="${material.type==5 }">selected</c:if> value="5">EXCEL</option>
							</select>
						</div>
					</li>
					<%-- <li class="col-md-4">
                        <strong><ins>*</ins>标签：</strong>
                        <div class="ipt-select">
                            <select name="tagId">
                                <c:forEach items="${tagList}" var="tag" >
                                    <option value="${tag.id}"
                                        <c:if test="${material.tagId == tag.id}">
                                            selected
                                        </c:if>
                                    >${tag.tagName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </li> --%>
					<li class="col-md-12">
						<strong><ins>*</ins>资料附件：</strong>
						<button type="button" class="btn btn-sm btn-submit" onclick ="changeC(32)">+ 上传附件</button><br>
						<a id="uploadUrl" href ="${attachment.path}" target ="_blank"><span id="uploadLocation">${attachment.originalName}</span></a>
					</li>
				</ul>
				<div class="ui-button">
					<button type="button" class="btn btn-submit" onclick="saveMaterial()">保存</button>
					<button type="button" class="btn btn-default" onclick="closeLayer()">取消</button>
				</div>
		    </div>
		</form>
	</div>
        <form id="bannerFile" style="display: none;" method="post" enctype="multipart/form-data">
            <input type="file" name="fileName" id="fileName" onchange="upload()" >
            <input name="category" type="hidden" id ="category">
        </form>
	<script type="text/javascript">
        $(document).ready(function() {
        	 $("#MaterialForm").validate({
                 rules : {
                	 name : {
                         required : true,
                         maxlength : [20]
                     },
                     description : {
                         required : true,
                         maxlength : [100]
                     }
                 },
                 messages: {
                     name: {
                         required: '此项必填',
                         maxlength: '资料名最长为20'
                     },
                     description: {
                         required: '此项必填',
                         maxlength: '资料说明最长为100'
                     }
                 }
             });
        });
        
        //防重复提交标志位 
		var switchButton =1;
        //保存方法 
		function saveMaterial(){
			var validator = $("#MaterialForm").validate();
			var fileId = $("#fileId").val();
			var materialId = $("input#materialId").val();
			if (fileId == '' && materialId == '') {  //当资料Id为空即新增资料时,才判断附件不为空
                layer.alert("请先添加资料附件");
            } else {
            	if (validator.form()) {
                    if(switchButton){
                        $.ajax({
                          type: 'POST',
                          url: "${ctx }/manage/insertAndUpdateMaterial2",
                          dataType: "json",
                          data :$('#MaterialForm').serialize(),
                          success: function(result) {
                              if (result == null || result == 'null') {
                                  layer.alert("提交失败");                                
                              } else {
                            	  console.log(result);
                            	  var id = result.id;
                            	  parent.returnMaterialList(id,result.name,$("#form").val()); 
                              }
                          }
                        });
                    }
                    switchButton =0;
                } else {
                    $("label.error").attr("style","color:red");
                }
            }
		}
	
		function changeC(category){  
		    $("input#category").val(category);  //附件表中定义的附件类型 
		    fileName.click();
		}
		
		function upload(){
			var btn_index = layer.load(2);
	         $("#bannerFile").ajaxSubmit({
	            url : '${ctx}/manage/commonUploadFile',
	            type :"post",
	            success : function(data){
	                    console.log(data);
	                    if (data != null) {  //附件要回显
	                        $("input#fileId").val(data.id);
	                        $("span#uploadLocation").html(data.originalName);
	                        $("#uploadUrl").attr("href",data.path);
	                    }
	                    layer.close(btn_index);
	                },error:function() {
	                    alert("附件上传错误");
	                    layer.close(btn_index);
	                } 
	            });  
	        }
		
		//关闭弹框 
        function closeLayer() {
            var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
            parent.layer.close(index);
        }
	</script>
</body>
</html>