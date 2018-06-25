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
<script src="${ctx}/resources/js/common/jquery-form.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/resources/libs/layer/layer.js" ></script>
	
<!-- S Wrapper -->
<div class="wrapper">
	<!-- S Wrapper Popup -->
	<div class="bg hide"></div>
	<div class="popup hide">
		<div class="tb_hd">
			<span class="table_header">选择讲师</span>
			<div class="form-search">
				<input type="text" placeholder="" class="form-control">
				<i><img src="" alt=""></i>
			</div>
			<button class="cancel_tb"><img src="" alt=""></button>
		</div>
		<table class="table table-agents">
			<thead>
				<tr>
					<th>
						<label class="pos-rel">
							<input type="checkbox" class="ace">
						</label>
					</th>
					<th>编号</th>
					<th>名称</th>
					<th>性质</th>
					<th>上传者</th>
					<th>日期</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>
						<label class="pos-rel">
							<input type="checkbox" class="ace">
						</label>
					</td>
					<td>1</td>
					<td>系统</td>
					<td>2017/12/2</td>
					<td>xxx部门</td>
					<td>职业规范</td>
					<td>xxx部门</td>
				</tr>
			</tbody>
		</table>
		<div class="btn_list">
			<button class="btn btn-submit">确定</button>
			<button class="btn btn-default"  onclick="history.back(-1)">取消</button>
		</div>
	</div>
	<!-- E Wrapper Popup -->
	<!-- <form id="formAdd" action="" > -->
		<div class="form-detail">
		<div class="title"><strong>新增课程</strong></div>
			<div class="nav-tabs">
				<strong id="up" class="active">线上课程</strong>
				<strong id="down" onclick="setIsOnline('0')">线下课程</strong>
			</div>
			<!-- 线上课程-->
		<form id="form1" class="form" action="" >
			<div class="tab-pane active clearfix" id="courseFormDiv">
	                <input name="fileId1"  value="" type="hidden" ><!--课程缩略图  -->
	                <input name="fileId2"  value="" type="hidden" ><!--课程课件  -->
	                <input name="fileId3"  value="" type="hidden" ><!--课程资料附件  -->
					<input id="isOnline" name="isOnline" type="hidden" value="1">
					<div class="ggkc_left clearfix col-md-6">
						<ul class="clearfix form_learn">
							<li class="col-md-10"><strong><ins>*</ins> 课程名称：</strong>
								<input id="name1" name="name" type="text" class="ipt-text" value="">
							</li>
							<li class="col-md-10"><strong><ins>*</ins> 课程说明：</strong>
								<input id="description1" name="description" type="text" class="ipt-text" value="">
							</li>
							<li class="col-md-10 auto"><strong><ins>*</ins>课程缩略图：</strong>
								<img id ="projectImg1" src="" width="80" height="80"><input type="hidden" id="attachmentBannerOne">
								<button onclick="changeC('projectImg1','1','24','1')" type="button" class="btn btn-sm btn-submit ipt-btnfile">请选择</button>
							</li>
							<%-- <li class="col-md-9">
								<strong><ins>*</ins>课程分类：</strong>
								<div class="ipt-select">
									<i class="icon-arrow-down"></i>
									<select id="tagId1" name="tagId">
										<c:forEach items="${tagList}" var="tag" >
                                            <option value="${tag.id}">${tag.tagName}</option>
                                        </c:forEach>
									</select>
								</div>
							</li> --%>
							<li class="col-md-9 srsj_ipt"><strong><ins>*</ins>至少学习：</strong>
								<input id="learningTime1" name="learningTime" type="text" class="ipt-text" value="" onkeyup="value=value.replace(/[^\d.]/g,'')"><b>分钟</b>
							</li>
							<li class="col-md-9 ques_classifiy">
								<strong><input type="checkbox" name="lectuer">课程讲师：</strong>
								<div class="ipt-select">
										<input id="lecturerId" type="hidden" name="lecturerId" value="${accountId }">
										<input id="lecturerName"  type="text" class="ipt-text" value="${accountName }" readonly>
										<button type="button" onclick="queryLecturer()" class="btn btn-submit" style="right: -90px;">选择</button>
										<button type="button" onclick="clearLecturer()" class="btn btn-submit" style="right: -150px;">清除</button>
								</div>
							</li>
						</ul>
					</div>
					<div class="ggkc_right clearfix col-md-6">
						<ul class="clearfix form_learn">
							<li class="col-md-12">
								<strong><ins>*</ins>课程课件：</strong>
								<button type="button" class="btn btn-sm btn-submit ipt-btnfile" onclick ="changeC('EL_LESSON_COURSEWARE','2','30','1')">+ 上传课件</button><br>
								<input type="hidden" id="attachmentCoursewareOne">
								<a id="A_EL_LESSON_COURSEWARE" href ="" target="_blank"><span id="EL_LESSON_COURSEWARE"></span></a>
							</li>
							<li class="col-md-4">
		                        <strong><ins>*</ins>课件类型：</strong>
		                        <div class="ipt-select">
		                            <select name="type">
		                                <option  value="1">视频</option>
		                                <option  value="2">SCORM</option>
		                                <option  value="3">PPT</option>
		                                <option  value="4">WORD</option>
		                                <option  value="5">EXCEL</option>
		                            </select>
		                        </div>
		                    </li>
							<li class="col-md-12" id ="materialLi">
								<strong><ins>*</ins>课程资料：</strong>
								<button type="button"  onclick="queryMaterial()"  class="btn btn-submit">选择资料</button>
								<input type="hidden" id="attachmentCoursewareOne">
								<input type="hidden" name="materialId" id="materialId"><input type="text" class="ipt-text" id="materialName" name="materialName"><br>
								<!-- <button type="button" class="btn btn-sm btn-submit ipt-btnfile" onclick = "changeC('null','3','31','1')">+ 上传附件</button><br> -->
								<button type="button"  onclick="createMaterial(1)"  class="btn btn-submit">新建资料</button>
								<input type="hidden" id="attachmentCourseOne">
							</li>
						</ul>
					</div>
					<div class="ui-button" style="margin-left: -100px;margin-top: 350px;">
						<button type="button" class="btn btn-submit" onclick="addAccount1()">新增</button>
						<button type="button" class="btn btn-default"  onclick="history.back(-1)">取消</button>
					</div>
			</div>
		</form>
			
			<!-- 线下课程-->
		<form id="form2" class="form" action="" >
			<div class="tab-pane  clearfix">
			    <input name="fileId1"  value="" type="hidden"><!--课程缩略图  -->
                <input name="fileId3"  value="" type="hidden"><!--课程资料附件  -->
				<input id="isOnline" name="isOnline" type="hidden" value="0">
				<div class="ggkc_left clearfix col-md-6">
					<ul class="clearfix form_learn">
						<li class="col-md-10"><strong><ins>*</ins> 课程名称：</strong>
							<input id="name2" name="name" type="text" class="ipt-text" value="">
						</li>
						<li class="col-md-10"><strong><ins>*</ins> 课程说明：</strong>
							<input id="description2" name="description" type="text" class="ipt-text" value="">
						</li>
						<li class="col-md-10 auto"><strong><ins>*</ins>课程缩略图：</strong>
							<input type="hidden" id="attachmentBannerTwo">
							<img id ="projectImg2" src="" width="80" height="80">
							<button type="button" class="btn btn-sm btn-submit ipt-btnfile" onclick = "changeC('projectImg2','1','24','2')">请选择</button>
						</li>
						<%-- <li class="col-md-9">
							<strong><ins>*</ins>课程分类：</strong>
							<div class="ipt-select">
								<i class="icon-arrow-down"></i>
								<select id="tagId2" name="tagId">
									<c:forEach items="${tagList}" var="tag" >
                                        <option value="${tag.id}">${tag.tagName}</option>
                                    </c:forEach>
								</select>
							</div>
						</li> --%>
						<li class="col-md-9 ques_classifiy">
							<strong><input type="checkbox" name="lector">课程讲师：</strong>
							<div class="ipt-select">
								<input id="lecturerId" class="lecturerId" type="hidden" name="lecturerId" value="${accountId }">
									<input id="lecturerName"  type="text" class="ipt-text lecturerName" value="${accountName }" readonly>
									<button type="button" onclick="queryLecturer() " class="btn btn-submit" style="right: -90px;">选择</button>
									<button type="button" onclick="clearLecturer()" class="btn btn-submit" style="right: -150px;">清除</button>
							</div>
						</li>
					</ul>
				</div>
				<div class="ggkc_right clearfix col-md-6">
					<ul class="clearfix form_learn">
						<li class="col-md-12" id="materialLi">
							<strong><ins>*</ins>课程资料：</strong>
							<button type="button"  onclick="queryMaterial()"  class="btn btn-submit">选择附件</button>
							<input type="hidden" name="materialId" id="materialId" class="materialId"><input type="text"  class="ipt-text materialName" id="materialName" name="materialName" ><br>
							<!-- <button type="button" class="btn btn-sm btn-submit ipt-btnfile" onclick = "changeC('null','3','31','2')">+ 上传附件</button> -->
							<button type="button"  onclick="createMaterial(2)"  class="btn btn-submit">新建资料</button>
							<input type="hidden" id="attachmentCourseTwo">
						</li>
					</ul>
				</div>
				<div class="ui-button" style="margin-left: -100px;margin-top: 300px;">
					<button type="button" class="btn btn-submit" onclick="addAccount2()">保存</button>
					<button type="button" class="btn btn-default" onclick="history.back(-1)">取消</button>
				</div>
			</div>
		</form>
		<form id="bannerFile" style="display: none;" method="post" enctype="multipart/form-data">
			<input type="file" name="fileName" id="fileName" onchange="upload()" >
			<input name="category" type="hidden" id="category">
		</form>
		<input id="fileType" type="hidden"> <!-- 附件类型：1 --表示课程缩略图； 2--课程课件； 3--课程附件  （ 图片要回显 ，文件提供下载 ）-->
        <input id="urlLocation" type="hidden"><!-- 附件回显位置  标签Id -->
        <input id="formLocation" type="hidden"><!-- 区分表单  2 --线下课程 1 --线上课程 -->
			
	</div>
	<!-- </form> -->
</div>

<!-- E Wrapper -->
<script type="text/javascript">
    
	$(document).ready(function() {
	});
	
	//选择讲师
	function queryLecturer() {
			layer.open({
			  type: 2,
			  title: "讲师列表",
			  closeBtn: 1, //
			  shade: [0],
			  area: ['80%', '80%'],
			  content: "${ctx}/manage/findByRoleId"
			});
	}
	//清除讲师关联 
    function clearLecturer () {
        $("#lecturerId").val("");
        $("#lecturerName").val("");
        $(".lecturerId").val("");
        $(".lecturerName").val("");
    }
	//回调函数
	function accountList(accountIds,accountNames) {
		var flag = $("#courseFormDiv").hasClass('active');
		if (flag) {
			$("#lecturerId").val(accountIds+",");
			$("#lecturerName").val(accountNames+",");
		} else {
			$(".lecturerId").val(accountIds+",");
	        $(".lecturerName").val(accountNames+",");
		}
		layer.closeAll();
		
	}
	function setIsOnline(obj) {
		$("#isOnline").val(obj);
	}
	
	//防重复提交 
	var switchButton = '1';
	
	function addAccount1() {
		var name = $("#name1").val();
		var description = $("#description1").val();
		//var tagId = $("#tagId1").val();
		var learningTime1 = $("#learningTime1").val()
		var lecturer = $("#lecturerId").val();
		if(name == null || name == ""){
			layer.msg("课程名称不能为空！");
			return false;
		}
		if(description == null || description == ""){
			layer.msg("课程说明不能为空！");
			return false;
		}
		/* if(tagId == null || tagId == ""){
			layer.msg("课程分类不能为空！");
			return false;
		}  */
		if(learningTime1 == null || learningTime1 == ""){
			layer.msg("学习时间不能为空！");
			return false;
		}
		if(lecturer == null || lecturer == ""){
			layer.msg("讲师不能为空！");
			return false;
		}
		if (switchButton == '1') {
			$.ajax({  
				cache:false,
				async:false, //不是异步处理  
		        type: "POST",  
		        dataType : "json",  
		        data :$('#form1').serialize(),  
		        url: "${ctx}/manage/editCourse",//请求的action路径  
		       success:function (data) {//请求失败处理函数 
		    	   location.href="${ctx}/manage/privateCourseListByPage";
		    	 	//window.open("${ctx}/manage/privateCourseListByPage", '_blank');
		        },  
		        error: function (data) {//请求失败处理函数 
		        	layer.msg(data.msg);
		        }   
		    });
			switchButton = '0';
		}
	}
	function addAccount2() {
		var name = $("#name2").val();
		var description = $("#description2").val();
		//var tagId = $("#tagId2").val();
		var lecturer = $(".lecturerId").val();
		if(name == null || name == ""){
			layer.msg("课程名称不能为空！");
			return false;
		}
		if(description == null || description == ""){
			layer.msg("课程说明不能为空！");
			return false;
		}
		/* if(tagId == null || tagId == ""){
			layer.msg("课程分类不能为空！");
			return false;
		}  */
		if(lecturer == null || lecturer == ""){
			layer.msg("讲师不能为空！");
			return false;
		}
		if (switchButton == '1') { 
			$.ajax({  
				cache:false,
				async:false, //不是异步处理  
		        type: "POST",  
		        dataType : "json",  
		        data :$('#form2').serialize(),  
		        url: "${ctx}/manage/editCourse",//请求的action路径  
		       success:function (data) {//请求失败处理函数
		    	   location.href="${ctx}/manage/privateCourseListByPage";
		        },  
		        error: function (data) {//请求失败处理函数 
		        	layer.msg(data.msg);
		        }   
		    }); 
			
			switchButton = '0';
		}
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

 	function accountListToAddMaterial(materialId,materialName){
 		var flag = $("#courseFormDiv").hasClass('active');
 		if (flag) {
	 		$("#materialId").val(materialId);
	 		$("#materialName").val(materialName);
 		} else {
	 		$("input.materialId").val(materialId);
	        $("input.materialName").val(materialName);
 		}
 		layer.closeAll();
 	}

 	function upload(){
 		var btn_index = layer.load(2);
        var fileType =  $("input#fileType").val();  //类型：图片要回显 
        var urlLocation = $("input#urlLocation").val();  //图片回显位置  Id 
        var formLocation = $("input#formLocation").val(); //表单位置 
 		 $("#bannerFile").ajaxSubmit({
            url : '${ctx}/manage/commonUploadFile',
            type :"post",
            success : function(data){
	            	console.log(data);
	            	if (data != null) {  //图片要回显
            			if (fileType == '1') {  //课程缩略图 (单个)
            				$("#form"+formLocation+" input[name='fileId1']").val(data.id);
                            $("#"+urlLocation).attr("src",data.path); 
                        } else if (fileType == '2'){ //课程课件 (单个)
                        	$("#form"+formLocation+" input[name=fileId2]").val(data.id);
                            $("#"+urlLocation).html(data.originalName);
                            $("#A_"+urlLocation).attr("href",data.path);
                        } else if (fileType == '3') { //课程附件 （可以多个）
                        	var fileIds = $("#form"+formLocation+" input[name=fileId3]").val();
                        	if (fileIds != '') {
                                fileIds =fileIds+','+data.id;
                                $("#form"+formLocation+" input[name=fileId3]").val(fileIds);
                            } else {
                            	$("#form"+formLocation+" input[name=fileId3]").val(data.id);
                            }
                        	var str = "&nbsp;&nbsp;";
                        	str = str + '<a href="'+data.path+'" target="_blank">'
                        		+"<span>"+data.originalName+"</span></a>";
                        	$("#form"+formLocation+" li#materialLi").append(str);	
                        }
	            	}
	            	layer.close(btn_index);
	            },error:function() {
	            	alert("上传错误");
	            	layer.close(btn_index);
	            } 
            });  
		}
 	
 	//
 	function changeC(urlLocation,fileType,category,formLocation){  
 		$("input#category").val(category);  //附件表中定义的附件类型 
 		$("input#fileType").val(fileType);  //类型： 附件类型：1 --表示课程缩略图； 2--课程课件； 3--课程附件 
 		$("input#urlLocation").val(urlLocation);  //回显位置  标签Id 
 		$("input#formLocation").val(formLocation); //表单位置 
 		$("input#fileName").click();
 	}
	function choose(){
		$('.popup').removeClass('hide');
		$('.bg').removeClass('hide');
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
		if (formId == '1') {      //表单一 form1
			var materialIds = $("#form1 #materialId").val();
			if (materialIds != '') {
				$("#form1 #materialId").val(materialIds+','+materialId);
			} else {
				$("#form1 #materialId").val(materialId);
			}
			var materialNames = $("#form1 #materialName").val();
		    if (materialNames != '') {
				$("#form1 #materialName").val(materialNames+','+materialName);
            } else {
            	$("#form1 #materialName").val(materialName);
            }
		} else {                  //表单二 form2
			var materialIds = $("#form2 .materialId").val();
            if (materialIds != '') {
                $("#form2 .materialId").val(materialIds+','+materialId);
            } else {
                $("#form2 .materialId").val(materialId);
            }
            var materialNames = $("#form2 .materialName").val();
            if (materialNames != '') {
                $("#form2 .materialName").val(materialNames+','+materialName);
            } else {
                $("#form2 .materialName").val(materialName);
            }
		}
		layer.closeAll();
	}
</script>
</body>
</html>