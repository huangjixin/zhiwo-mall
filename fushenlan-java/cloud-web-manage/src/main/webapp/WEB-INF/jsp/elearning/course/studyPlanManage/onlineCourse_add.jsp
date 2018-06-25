<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
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
<link rel="stylesheet" type="text/css" href="${ctx }/resources/css/style.css">
<link rel="stylesheet" type="text/css" href="${ctx }/resources/css/font/iconfont.css">
<link rel="stylesheet" type="text/css" href="${ctx }/resources/css/page.css">
<link href="${ctx }/resources/libs/datepicker/skin/wdatepicker.css" rel="stylesheet" type="text/css">
<script type="text/javascript"	src="${ctx}/resources/libs/layer/layer.js"></script>
</head>
<body>
	<div class="form-detail">
	<input type="hidden" name="studyPlan" id="studyPlan" value="${studyPlan}">
		<div class="title">
			<strong>添加课程</strong>
		</div>
		<div class="nav-tabs">
			<strong class="active" >添加已有课程</strong> 
			<strong >本地上传课程</strong>
		</div>
		<!-- 添加已有课程 -->
		<div class="tab-pane active" id="stagec1">
			<form id="f2">
				<ul class="edit clearfix online_form">
					<li class="col-md-3"><strong>课程关键字：</strong><input type="text"
						class="ipt-text" name="keyWord" placeholder="课程关键字"></li>
					<li class="col-md-3"><strong >课程分类：</strong>
						<div class="ipt-select">
							<i class="icon-arrow-down"></i>
							<select id="ctagid" name="type">
							 <option value="">--请选择--</option>
								<option value="1">视频</option>
								<option value="2">ppt</option>
								<option value="3" >scorm</option>
								<option value="4">word</option>
								<option value="5">ddd</option>
							</select>
						</div></li>
					<li class="col-md-3"><strong>课程提交者：</strong><input type="text" name="submitter" class="ipt-text" placeholder="课程提交者"></li>
					<li class="col-md-6 sameWadte"><strong style="left:-22px">课程上传日期:</strong> 
					<input type="text" placeholder="开始时间" id="uploadTimeBegin" class="Wdate" name="uploadTimeBegin" style="max-width: 180px;" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'uploadTimeEnd\')}'})">&nbsp;—&nbsp;
					<input type="text" placeholder="结束时间" id="uploadTimeEnd" class="Wdate" name="uploadTimeEnd" style="max-width: 180px;" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'uploadTimeBegin\')}'})">
					</li>
					<li class="col-md-1 col-btn">
						<button type="button" class="btn btn-submit btn-radius btn-search" style="top:204px;right:220px;" onclick="search()">
							<i class="icon-search"></i>查询
						</button>
					</li>
				</ul>
			</form>
			<div class="title">
				<strong>查询结果</strong>
			</div>
			<div id="result" style="height:160px;overflow-y:auto;" class="search_result">
				<div class="list_div">
					<table class="table table-agents" id="tab">
					</table>
				</div>
			</div>
			
		<div class="ui-button">
				<button type="button" class="btn btn-submit"  onclick="save('1')">保存</button>
				<button type="button" class="btn btn-default" onclick="backOne()">取消</button>
		</div>
		</div>
		
		<!-- 本地上传课程 -->
		<div class="tab-pane clearfix" id="stagec2">
		 <form id="f1">
		  <div class="tab-pane active clearfix" id="courseFormDiv">
		      <input name="fileId1"  value="" type="hidden" ><!--课程缩略图  -->
	          <input name="fileId2"  value="" type="hidden" ><!--课程课件  -->
	          <input name="fileId3"  value="" type="hidden" ><!--课程资料附件  -->
			  <input id="isOnline" name="isOnline" type="hidden" value="1">
			<div class="ggkc_left clearfix col-md-6">
				<ul class="clearfix form_learn">
					<li class="col-md-10"><strong><ins>*</ins> 课程名称：</strong>
					 <input id="cname" name="name" type="text" class="ipt-text" />
					<li class="col-md-10"><strong><ins>*</ins> 课程说明：</strong>
					<input id="cdescription" name="description" type="text" class="ipt-text" ></li>
					
					<li class="col-md-10 auto"><strong><ins>*</ins>课程缩略图：</strong>
					<img id="projectImg1" src="" width="80" height="80" />
					<input type="hidden" id="attachmentBannerOne">
					<button onclick="changeC('projectImg1','1','3','1')" type="button" style="position: absolute;top:320px;left:269px;" class="btn btn-sm btn-submit ipt-btnfile">请选择</button>
					</li>
					<li class="col-md-9"><strong><ins>*</ins>课程分类：</strong>
						<div class="ipt-select">
							<i class="icon-arrow-down"></i> 
							<select id="ctagId2" name="tagId">
										<option value="0">全部</option>
										<option value="1">初审面试</option>
										<option value="2">甄选面试</option>
										<option value="3">决定面试</option>
							</select>
						</div></li>
					<li class="col-md-9 srsj_ipt"><strong><ins>*</ins>至少学习：</strong>
					<input id="learningTime1" name="learningTime" value="" type="text" class="ipt-text" style="width:220px" onkeyup="value=value.replace(/[^\d.]/g,'')"><b>分钟</b>
					</li>
					<li class="col-md-9 ques_classifiy"><strong>
					<input type="checkbox" name="lectuer">课程讲师：</strong>
						<div class="ipt-select">
							<input id="lecturerId" type="hidden" name="lecturerId" value="${accountId }">
							<input id="lecturerName"  type="text" class="ipt-text" value="${accountName }" readonly>
							<button type="button" onclick="queryLecturer()" class="btn btn-submit" style="right: -90px;">选择</button>
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
							<a id="A_EL_LESSON_COURSEWARE" href ="" target="_blank">
							<span id="EL_LESSON_COURSEWARE"></span></a>
						</li>
						<li class="col-md-12" id ="materialLi">
							<strong><ins>*</ins>课程资料：</strong>
								<button type="button" style="margin-top:5px;margin-bottom:5px;"  onclick="queryMaterial()"  class="btn btn-submit">选择资料</button>
								<input type="hidden" id="attachmentCoursewareOne">
								<input type="hidden" name="materialId" id="materialId">
								<input type="text" style="width:350px" class="ipt-text" id="materialName" name="materialName"><br>
								<button type="button" class="btn btn-sm btn-submit ipt-btnfile" style="margin-top:5px" onclick = "changeC('null','3','31','1')">+ 上传附件</button><br>
								<input type="hidden" id="attachmentCourseOne">
						</li>
					</ul>
				</div>
			     <div class="ui-button">
				  <button type="button" class="btn btn-submit" onclick="addAccount1()">新增</button>
				  <button type="button" class="btn btn-default"  onclick="backOne()">取消</button>
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
	</div>
	<script src="${ctx}/resources/js/common/jquery-3.2.1.min.js"></script> 
    <script src="${ctx}/resources/js/elerning/common.js"></script> 
    <script type="text/javascript" src="${ctx}/resources/libs/layer/layer.js" ></script>
	<script type="text/javascript" src="${ctx}/resources/libs/datepicker/WdatePicker.js"></script>
	<script src="${ctx}/resources/js/common/jquery-form.js" type="text/javascript"></script>
	<script type="text/javascript">
		$(function(){
			search();
		});
	   var isOnline=${online};
	   var tagId='${stageId}';
	   function cancle() {
		location.href = "${ctx}/manage/studyPlanManage/learnPLan_addEdit"
		}

		function search() {
			var html = '';
			var para = $('#f2').serialize();	
			para=para+'&isOnline='+isOnline;
			$.ajax({
			  type : 'POST',
			  dataType : "json",
			  data : para,
			  url : "${ctx}/manage/studyPlanManage/findCourses",//请求的action路径  
			  error : function(data) {//请求失败处理函数  
				layer.msg("系统繁忙~~~");
				},
			  success : function(data) { //请求成功后处理函数。
			  var idList="${idList}";
			  //idList = idList.substring(1,idList.length-1);			
			  var data2 =data;
			  html += "<thead><tr><th><label class='pos-rel'><input id='checkbox' name='checkall'  type='checkbox' class='ace'></label></th><th>课程名称</th><th>课程说明<th>课程分类</th>";
			  html += "<th>课程上传者</th></tr></thread>";
			  html += '<tbody>';	
			  $.each(data2,function(i, item) {
			  if(idList.indexOf(item.id)>-1){
				  }else{
					  var type=new Array('','视频','ppt','scorm','word','ddd');//实现分类的回显
					  if(item.type!=null){
					  html +="<tr><td><label class='pos-rel'>";
					  html +="<input type='checkbox' id='"+item.id+"' type='"+item.isOnline+"'  name='checkbox' class='ace'  valu='"+item.name+"'></label></td>";
					  html +="<td>"+ item.name+ "</td><td>" + item.description+ "</td><td>" + type[item.type]+ "</td><td>"+ item.createUser+ "</td></tr>";
					  }else{
					  html += "<tr><td><label class='pos-rel'>";
					  html += "<input type='checkbox' id='"+item.id+"' type='"+item.isOnline+"'  name='checkbox' class='ace'  valu='"+item.name+"'></label></td>";
					  html += "<td>"+ item.name+ "</td><td>" + item.description+ "</td><td></td><td>"+ item.createUser+ "</td></tr>";  
					  }
				  }
			  });
			  html += "</tbody>";
			  $("#tab").html(html);
			  //实现全选与全不选
			  $("#checkbox").click(function() {
			  if ($(this).is(':checked')) {
			  $("input[name='checkbox']").prop("checked",true);
			  } else {
			  $("input[name='checkbox']").prop("checked",false);
				}
			  }); 
			}										
			});	
		  }
		
		//保存
		function save(ty){
		 var array=new Array();	
		 if(ty==1){//添加已有课程
		 var type='';
		 if(isOnline==1){//表示线上
		  type=2;
		  $("#tab").find("input[name='checkbox']:checked").each(function(i){
			if($(this).is(":checked")){
				var name=$(this).attr("valu");//名字
				var id=$(this).attr("id");//线上课程id
				array[i]={name:name,id:id,type:type};
				}
		 })
		 parent.closeLayerup(tagId,array); 
		 backOne();
		 }else{
		 type=3;//线下
		 $("#tab").find("input[name='checkbox']:checked").each(function(i){
			if($(this).is(":checked")){
				var name=$(this).attr("valu");//名字
				var id=$(this).attr("id");//线上课程id
				array[i]={name:name,id:id,type:type};
				}
				});
				parent.closeLayerup(tagId,array); 
				backOne();
			}
		}else{
			type=2;
			var id=ty;
			var name=$("#cname").val();
			array[0]={name:name,id:id,type:type};
			parent.closeLayerup(tagId,array); 
			backOne();
		}
		}
		
		//查询讲师
		function queryLecturer(){
			layer.open({
				  type: 2,
				  title: "讲师列表",
				  closeBtn: 1, //
				  shade: [0],
				  area: ['80%', '80%'],
				  content:"${ctx}/manage/studyPlanManage/findByRoleId"
				});
		}
		
		 function backOne(){
				var index=parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
			}
		 
		 function accountList(accountIds,accountNames){
			 $("#lecturerId").val(accountIds+",");
			 $("#lecturerName").val(accountNames+",");
		 }; 

			function changeC(urlLocation,fileType,category,formLocation){ 
		 		$("input#category").val(category);  //附件表中定义的附件类型 
		 		$("input#fileType").val(fileType);  //类型： 附件类型：1 --表示课程缩略图； 2--课程课件； 3--课程附件 
		 		$("input#urlLocation").val(urlLocation);  //回显位置  标签Id 
		 		$("input#formLocation").val(formLocation); //表单位置 
		 		$("input#fileName").click();
		 	}
			
			//上传与下载
			function upload(){
		        var fileType =  $("input#fileType").val();  //类型：图片要回显 
		        var urlLocation = $("input#urlLocation").val();  //图片回显位置  Id 
		        var formLocation = $("input#formLocation").val(); //表单位置 
		 		 $("#bannerFile").ajaxSubmit({
		            url : '${ctx}/manage/commonUploadFile',
		            type :"post",
		            success : function(data){
		            //console.log(data);
		                 if (data != null) {  //图片要回显
	            			if (fileType == '1') {  //课程缩略图 (单个)
	            				$("#form"+formLocation+" input[name='fileId1']").val(data.id);
	                            $("#"+urlLocation).attr("src",data.path); 
	                        }else if (fileType == '2'){ //课程课件 (单个)
	                        	$("#form"+formLocation+" input[name=fileId2]").val(data.id);
	                            $("#"+urlLocation).html(data.originalName);
	                            $("#A_"+urlLocation).attr("href",data.path);
	                        }else if (fileType == '3') { //课程附件 （可以多个）
	                        	var fileIds = $("#form"+formLocation+" input[name=fileId3]").val();
	                        	if (fileIds != '') {
	                                fileIds =fileIds+','+data.id;
	                                $("#form"+formLocation+" input[name=fileId3]").val(fileIds);
	                            }else {
	                            	$("#form"+formLocation+" input[name=fileId3]").val(data.id);
	                            }
	                        	var str = "&nbsp;&nbsp;";
	                        	str = str + '<a href="'+data.path+'" target="_blank">'
	                        		+"<span>"+data.originalName+"</span></a>";
	                        	$("#form"+formLocation+" li#materialLi").append(str);	
	                        }
		            	}
			            },error:function() {
			            	layer.msg("上传错误");
			            } 
		            });  
				}

		 	function queryMaterial() {
		 		layer.open({
					  type: 2,
					  title: "资料列表",
					  closeBtn: 1, //
					  shade: [0],
					  area: ['90%', '80%'],
					  content: "${ctx}/manage/publicMaterialListByCourse"
					});
			}
		
		 	function accountListToAddMaterial(materialId,materialName){
		 		$("#materialId").val(materialId);
		 		$("#materialName").val(materialName);
		 		layer.closeAll();
		 	}
		 	
		 	//防重复提交 
			var switchButton = '1';
			//新增进入的方法
			function addAccount1() {
				var name = $("#cname").val();
				var description = $("#cdescription").val();
				var tagId = $("#ctagId2").val();
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
				if(tagId == null || tagId == ""){
					layer.msg("课程分类不能为空！");
					return false;
				} 
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
				        data :$('#f1').serialize(),  
				        url: "${ctx}/manage/editCourse",//请求的action路径  
				       success:function (data) {//请求失败处理函数 
				    	 	save(data.data);//把新的上传课程的id传过去
				    	 	backOne();
				        },  
				        error: function (data) {//请求失败处理函数 
				        	layer.msg(data.msg);
				        }   
				    });
					switchButton = '0';
				}
			}
		 
	</script>
</body>
</html>