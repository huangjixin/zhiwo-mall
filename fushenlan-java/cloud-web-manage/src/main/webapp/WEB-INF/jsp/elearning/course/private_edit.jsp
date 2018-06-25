<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 	<title>富卫运维大平台</title>
</head>
<body>
<script src="${ctx}/resources/js/common/jquery-form.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/resources/libs/layer/layer.js" ></script>
	<form id="form1" action="" >
	   <input name="fileId1"  value="" type="hidden" ><!--课程缩略图  -->
       <input name="fileId2"  value="" type="hidden" ><!--课程课件  -->
       <input name="fileId3"  value="" type="hidden" ><!--课程资料附件  -->
		<div class="wrapper">
			<div class="form-detail">
			<div class="title">
					<strong>修改课程</strong>
			</div>
			<div class="nav-tabs">
				<c:if test="${course.isOnline==0 }">
					<strong class="active">线下课程</strong>
				</c:if>
				<c:if test="${course.isOnline==1 }">
					<strong class="active">线上课程</strong>
				</c:if>
			</div>
			<!-- 线上课程-->
			<c:if test="${course.isOnline==1 }">
				<div class="tab-pane active clearfix">
					<div class="ggkc_left clearfix col-md-6">
						<ul class="clearfix form_learn">
							<li class="col-md-10"><strong><ins>*</ins> 课程名称：</strong>
								<input name="id" type="hidden" class="ipt-text" value="${course.id }">
								<input name="name" type="text" class="ipt-text" value="${course.name }">
							</li>
							<li class="col-md-10"><strong><ins>*</ins> 课程说明：</strong>
								<input name="description" type="text" class="ipt-text" value="${course.description }">
							</li>
							<li class="col-md-10 auto"><strong><ins>*</ins>课程缩略图：</strong>
								<img src="${courseImg.path}" width="80" height="80" id="projectImg1">
								<button type="button" class="btn btn-sm btn-submit ipt-btnfile" onclick="changeC('projectImg1','1','24')">请选择</button>
							</li>
							<%-- <li class="col-md-9">
								<strong><ins>*</ins>课程分类：</strong>
								<div class="ipt-select">
									<i class="icon-arrow-down"></i>
									<select id="tagId" name="tagId">
							             <c:forEach items="${tagList}" var="tag" >
		                                    <option value="${tag.id}"
		                                        <c:if test="${course.tagId == tag.id}">
		                                            selected
		                                        </c:if>
		                                    >${tag.tagName}</option>
		                                </c:forEach>
									</select>
								</div>
							</li> --%>
							<li class="col-md-9 srsj_ipt"><strong><ins>*</ins>至少学习：</strong>
								<input name="learningTime" type="text" class="ipt-text" value="${course.learningTime }" onkeyup="value=value.replace(/[^\d.]/g,'')"><b>分</b>
							</li>
							<li class="col-md-9 ques_classifiy">
								<strong>课程讲师</strong>
								<div class="ipt-select">
									<input id="lecturerId" type="hidden" name="lecturerId" value="${accountId }">
									<input id="lecturerName"  type="text" class="ipt-text" value="${accountName }" readonly>
									<button type="button" onclick="queryLecturer('${course.id }')" class="btn btn-submit" style="right: -90px;">选择</button>
									<button type="button" onclick="clearLecturer()" class="btn btn-submit" style="right: -150px;">清除</button>
								</div>
							</li>
						</ul>
					</div>
					<div class="ggkc_right clearfix col-md-6">
						<ul class="clearfix form_learn">
							<li class="col-md-12">
								<strong><ins>*</ins>课程课件：</strong>
								<button type="button" class="btn btn-sm btn-submit ipt-btnfile" onclick ="changeC('EL_LESSON_COURSEWARE','2','30')">+ 上传课件</button><br>
								<a id="A_EL_LESSON_COURSEWARE" href ="${courseWare.path }" target="_blank"><span id="EL_LESSON_COURSEWARE">${courseWare.originalName }</span></a>
							</li>
							<li class="col-md-4">
                                <strong><ins>*</ins>课件类型：</strong>
                                <div class="ipt-select">
                                    <select name="type">
                                        <option <c:if test="${course.type==1 }">selected</c:if> value="1">视频</option>
                                        <option <c:if test="${course.type==2 }">selected</c:if>  value="2">SCORM</option>
                                        <option <c:if test="${course.type==3 }">selected</c:if> value="3">PPT</option>
                                        <option <c:if test="${course.type==4 }">selected</c:if> value="4">WORD</option>
                                        <option <c:if test="${course.type==5 }">selected</c:if> value="5">EXCEL</option>
                                    </select>
                                </div>
                            </li>
							<li class="col-md-12" id="materialLi1">
								<strong><ins>*</ins>课程资料：</strong>
								<button type="button"  onclick="queryMaterial()"  class="btn btn-submit">选择附件</button>
								<input type="hidden" name="materialId" id="materialId" value="${materialIds}"><input type="text"  class="ipt-text" id="materialName" name="materialName" value="${materialNames}"><br>
								<!-- <button type="button" class="btn btn-sm btn-submit ipt-btnfile" onclick="changeC('materialLi1','3','31')">+ 上传附件</button><br> -->
								<button type="button"  onclick="createMaterial(1)"  class="btn btn-submit">新建资料</button>
								<c:forEach items="${courseAttachement}" var="attachment">
                                    &nbsp;&nbsp;<a href="${attachment.path }" target="_blank"><span>${attachment.originalName}</span></a>
                                </c:forEach>
							</li>
						</ul>
					</div>
				</div>
			</c:if>
			<!-- 线下课程-->
			<c:if test="${course.isOnline==0 }">
				<div class="tab-pane active clearfix">
					<div class="ggkc_left clearfix col-md-6">
						<ul class="clearfix form_learn">
							<li class="col-md-10"><strong><ins>*</ins> 课程名称：</strong>
								<input name="id" type="hidden" class="ipt-text" value="${course.id }">
								<input id="name" name="name" type="text" class="ipt-text" value="${course.name }">
							</li>
							<li class="col-md-10"><strong><ins>*</ins> 课程说明：</strong>
								<input id="description" name="description" type="text" class="ipt-text" value="${course.description }">
							</li>
							<li class="col-md-10 auto"><strong><ins>*</ins>课程缩略图：</strong>
								<img src="${courseImg.path}" width="80" height="80" id="projectImg0"><button type="button" class="btn btn-sm btn-submit ipt-btnfile" onclick="changeC('projectImg0','1','24')">请选择</button>
							</li>
							<%-- <li class="col-md-9">
								<strong><ins>*</ins>课程分类：</strong>
								<div class="ipt-select">
									<i class="icon-arrow-down"></i>
									<select id="tagId" name="tagId">
                                         <c:forEach items="${tagList}" var="tag" >
                                            <option value="${tag.id}"
                                                <c:if test="${course.tagId == tag.id}">
                                                    selected
                                                </c:if>
                                            >${tag.tagName}</option>
                                        </c:forEach>
                                    </select>
								</div>
							</li> --%>
							<li class="col-md-9 ques_classifiy">
								<strong>课程讲师：</strong>
								<div class="ipt-select">
									<input id="lecturerId" type="hidden" name="lecturerId" value="${accountId }">
									<input type="text" class="ipt-text" id ="lecturerName" value="${accountName }" readonly>
									<button type="button" onclick="queryLecturer('${course.id }')" class="btn btn-submit" style="right: -90px;">选择</button>
								    <button type="button" onclick="clearLecturer()" class="btn btn-submit" style="right: -150px;">清除</button>
							</li>
						</ul>
					</div>
					<div class="ggkc_right clearfix col-md-6">
						<ul class="clearfix form_learn">
							<li class="col-md-12" id="materialLi2">
								<strong><ins>*</ins>课程资料：</strong>
								<button type="button" onclick="queryMaterial()"  class="btn btn-submit">选择附件</button>
								<input type="hidden" name="materialId" id="materialId" value="${materialIds}"><input type="text" id="materialName" class="ipt-text"  name="materialName" value="${materialNames}" readonly><br>
								<!-- <button type="button" class="btn btn-sm btn-submit ipt-btnfile" onclick="changeC('materialLi2','3','31')">+ 上传附件</button> -->
								<button type="button"  onclick="createMaterial(2)"  class="btn btn-submit">新建资料</button>
								<%-- <c:forEach items="${courseAttachement}" var="attachment">
								    &nbsp;&nbsp;<a href="${attachment.path }" target="_blank"><span>${attachment.originalName}</span></a>
								</c:forEach> --%>
								
							</li>
						</ul>
					</div>
				</div>
			</c:if>
			<div class="ui-button">
				<button type="button" class="btn btn-submit" onclick="updateAccount()">保存</button>
				<button type="button" class="btn btn-default"  onclick="history.back(-1)">取消</button>
			</div>
			</div>
		</div>
	</form>
	
	<!-- 附件表单 -->
	<form id="bannerFile" style="display: none;" method="post" enctype="multipart/form-data">
        <input type="file" name="fileName" id="fileName" onchange="upload()" >
        <input name="category" type="hidden" id="category">
    </form>
    <input id="fileType" type="hidden"> <!-- 附件类型：1 --表示课程缩略图； 2--课程课件； 3--课程附件  （ 图片要回显 ，文件提供下载 ）-->
    <input id="urlLocation" type="hidden"><!-- 附件回显位置  标签Id -->
	<script type="text/javascript">
	
        var isOnline = '${course.isOnline}';    // 1--线上 0--线下 
        
        var switchButton = '1';
		function updateAccount(){
			if (switchButton == '1') {
				$.ajax({  
					cache:false,
					async:false, //不是异步处理  
			        type: "POST",  
			        dataType : "json",  
			        data :$('#form1').serialize(),  
			        url: "${ctx}/manage/editCourse",//请求的action路径  
			        success:function (data) {//请求失败处理函数 
			    	   layer.msg(data.msg);
			    	   location.href="${ctx}/manage/privateCourseListByPage";
			        },  
			        error: function (data) {//请求失败处理函数 
			        	layer.msg(data.msg);
			        }   
			    }); 
				switchButton = '0';
			}
		}
		
		function accountList(accountIds,accountNames) {
            $("#lecturerId").val(accountIds+",");
            $("#lecturerName").val(accountNames+",");
			layer.closeAll();
			
		}
		
		function accountListToAddMaterial(materialId,materialName){
            $("#materialId").val(materialId);
            $("#materialName").val(materialName);
			layer.closeAll();
		}
		
		//上传 
		function upload(){
			var btn_index = layer.load(2);
	        var fileType =  $("input#fileType").val();  //类型：图片要回显 
	        var urlLocation = $("input#urlLocation").val();  //图片回显位置  Id 
	         $("#bannerFile").ajaxSubmit({
	            url : '${ctx}/manage/commonUploadFile',
	            type :"post",
	            success : function(data){
	                    console.log(data);
	                    if (data != null) {  //图片要回显
	                        if (fileType == '1') {  //课程缩略图 (单个)
	                            $("#form1 input[name='fileId1']").val(data.id);
	                            $("#"+urlLocation).attr("src",data.path); 
	                        } else if (fileType == '2'){ //课程课件 (单个)
	                            $("#form1 input[name=fileId2]").val(data.id);
	                            $("#"+urlLocation).html(data.originalName);
	                            $("#A_"+urlLocation).attr("href",data.path);
	                        } else if (fileType == '3') { //课程附件 （可以多个）
	                            var fileIds = $("#form1 input[name=fileId3]").val();
	                            if (fileIds != '') {
	                                fileIds =fileIds+','+data.id;
	                                $("#form1 input[name=fileId3]").val(fileIds);
	                            } else {
	                                $("#form1 input[name=fileId3]").val(data.id);
	                            }
	                            var str = "&nbsp;&nbsp;";
	                            str = str + '<a href="'+data.path+'" target="_blank">'
	                                +"<span>"+data.originalName+"</span></a>";
	                            $("#form1 li#"+urlLocation).append(str);   
	                        }
	                    }
	                    layer.close(btn_index);
	                },error:function() {
	                    alert("上传错误");
	                    layer.close(btn_index);
	                } 
	            });  
	        }
		
		//点击上传触发事件
		function changeC(urlLocation,fileType,category){  
	        $("input#category").val(category);  //附件表中定义的附件类型 
	        $("input#fileType").val(fileType);  //类型： 附件类型：1 --表示课程缩略图； 2--课程课件； 3--课程附件 
	        $("input#urlLocation").val(urlLocation);  //回显位置  标签Id 
	        $("input#fileName").click();
	    }
		
		//清除讲师关联 
	    function clearLecturer () {
	        $("#lecturerId").val("");
	        $("#lecturerName").val("");
	        $(".lecturerId").val("");
	        $(".lecturerName").val("");
	    }
		
	    function queryLecturer(obj) {
            layer.open({
                  type: 2,
                  title: "讲师列表",
                  closeBtn: 1, //
                  shade: [0],
                  area: ['80%', '80%'],
                  content: "${ctx}/manage/findByRoleId?courseId="+obj
                });
        }
    
        function queryMaterial() {
            layer.open({
                  type: 2,
                  title: "资料列表",
                  closeBtn: 1, //
                  shade: [0],
                  area: ['80%', '80%'],
                  content: "${ctx}/manage/publicMaterialListByCourse"
                });
        }
		
	  //新建资料 
	    function createMaterial(form) {
	        layer.open({
	            type: 2,
	            title: "资料列表",
	            closeBtn: 1, //
	            shade: [0],
	            area: ['80%', '80%'],
	            content: "${ctx}/manage/ceateMaterialForCourse?form="+form
	          });
	    }
	    
	    //新建资料回调函数 
	    function returnMaterialList(materialId,materialName,formId) {
            var materialIds = $("#materialId").val();
            if (materialIds != '') {
                $("#materialId").val(materialIds+','+materialId);
            } else {
                $("#materialId").val(materialId);
            }
            var materialNames = $("#materialName").val();
            if (materialNames != '') {
                $("#materialName").val(materialNames+','+materialName);
            } else {
                $("#materialName").val(materialName);
            }
	        layer.closeAll();
	    }
	</script>
	<style type="text/css">
		.bg{
			width: 100%;
			height: 100%;
			position: absolute;
			top:0;
			left:0;
			background: rgba(0,0,0,0.7);
			z-index: 1;
		}
		.popup{
			position: absolute;
			top:50%;
			left: 50%;
			z-index: 2;
			transform: translate(-50%,-50%);
			background: #fff;
			width: 750px;
			border-radius: 2px;
		}
		.form-search{
			text-align: right;
			height: 45px;
			line-height: 45px;
			position: relative;
		    padding-right: 50px;
	        display: inline-block;
	   		float: right;
		}
		.form-search .form-control{
			border:1px solid #999;
			height: 30px;
		}
		.form-search i{
			position: absolute;
			top:50%;
			right: 60px;
			margin-top: -10px;
		}
		.popup .table{
			border-radius: 0;
			border-top: 1px dashed #dfdfdf;
		}
		.table_header{
		    line-height: 45px;
	    	padding-left: 20px;
	    	font-weight: 700;
		}
		.cancel_tb{
			position: absolute;
			top:5px;
			right: 5px;
		}
		.btn_list{
			height: 60px;
	    	text-align: center;
	    	line-height: 60px;
		}
		.btn_list button{
			border-radius: 25px;
			padding:0 25px;
		}
		.btn_list button:first-child{
			margin-right: 50px;
		}
		.hide{
			display: none;
		}
		#materialName{
			width:60%;
		}
		
	</style>
</body>
</html>