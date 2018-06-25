<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<title>富卫运维大平台</title>
<meta name="description" content="">
<link rel="stylesheet" type="text/css"
	href="${ctx }/resources/css/style.css">
<link rel="stylesheet" type="text/css"
	href="${ctx }/resources/css/font/iconfont.css">
<link rel="stylesheet" type="text/css"
	href="${ctx }/resources/css/page.css">
<link href="${ctx }/resources/libs/datepicker/skin/WdatePicker.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div class="form-detail">
	<input type="hidden" name="studyPlan" id="studyPlan" value="${studyPlan}">
		<div class="title">
			<strong>添加课程</strong>
		</div>
		<div class="nav-tabs">
			<strong class="active">添加已有课程</strong>
		</div>
		<!-- 添加已有课程 -->
		<div class="tab-pane active">
			<form id="f2">
				<ul class="edit clearfix online_form">
					<li class="col-md-3"><strong>课程关键字：</strong><input type="text"
						class="ipt-text" name="keyWord" placeholder="课程关键字"></li>
					<li class="col-md-3"><strong>课程分类：</strong>
						<div class="ipt-select">
							<i class="icon-arrow-down"></i><select name="type">
							 <option value="">请选择</option>
								<option value="1">视频</option>
								<option value="2">ppt</option>
								<option value="3" >scorm</option>
								<option value="4">word</option>
							</select>
						</div></li>
					<li class="col-md-3" ><strong>课程提交者：</strong><input type="text" name="submitter" class="ipt-text" placeholder="课程提交者"></li>
					<li class="col-md-6 sameWadte" style="width:600px;"><strong style="left:-19px">课程上传日期:</strong> 
					<input type="text" placeholder="开始时间" id="uploadTimeBegin" class="Wdate" name="uploadTimeBegin" style="max-width: 180px;" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'uploadTimeEnd\')}'})">&nbsp;—&nbsp;
					<input type="text" placeholder="结束时间" id="uploadTimeEnd" class="Wdate" name="uploadTimeEnd" style="max-width: 180px;" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'uploadTimeBegin\')}'})">
					</li>

					<li class="col-md-1 col-btn">
						<button type="button" class="btn btn-submit btn-radius btn-search"
							onclick="search()">
							<i class="icon-search"></i>查询
						</button>
					</li>
				</ul>
			</form>
			<div class="title">
				<strong>查询结果</strong>
			</div>
			<div id="result" style="height: 500px" class="search_result">
				<div class="list_div">
					<table class="table table-agents" id="tab">

					</table>
				</div>
			</div>
			<div class="ui-button">
				<button type="button" class="btn btn-submit" onclick="save('1')">保存</button>
				<button type="button" class="btn btn-default" onclick="backOne()">取消</button>
			</div>
		</div>
	</div>
   <script src="${ctx}/resources/js/common/jquery-3.2.1.min.js"></script> 
  <%--   <script src="${ctx}/resources/js/elerning/common.js"></script>  --%>
    <script type="text/javascript" src="${ctx}/resources/libs/layer/layer.js" ></script>
	<script type="text/javascript" src="${ctx}/resources/libs/datepicker/WdatePicker.js"></script>
	<script type="text/javascript">
	   var isOnline=${online};
	   var tagId='${stageId}';
	   function cancle() {
		location.href = "${ctx}/manage/studyPlanManage/learnPLan_addEdit"
		}
		$(function (){
			search();
		})
		//查询
		function search() {
			debugger;
			var html = '';
			var para = $('#f2').serialize();	
			para=para+'&isOnline='+isOnline;
			$.ajax({
			  type : 'POST',
			  dataType : "json",
			  data : para,
			  url : "${ctx}/manage/studyPlanManage/findCourses",//请求的action路径  
			  error : function(data) {//请求失败处理函数  
			    alert("系统繁忙~~~");
				},
			  success : function(data) { //请求成功后处理函数。
			  var idList="${idList}";
			  var data2 =data;
			  html += "<thead><tr><th><label class='pos-rel'><input   type='hidden' class='ace'></label></th><th>课程名称</th><th>课程分类</th>";
			  html += "<th>课程上传者</th><th>课程日期</th></tr></thread>";
			  html += '<tbody>';	
			  var type=new Array('','视频','ppt','scorm','word','ddd');//实现分类的回显
			  $.each(data2,function(i, item) {
			  if(idList.indexOf(item.id)>-1){//实现那边带过来数据
				  if(item.type!=null){
				  html += "<tr><td><label class='pos-rel'>";
				  html += "<input type='radio' checked id='"+item.id+"' type='"+item.isOnline+"'  name='checkbox' class='ace'  valu='"+item.name+"'></label></td>";
				  html += "<td>"+ item.name+ "</td><td>" + type[item.type]+ "</td><td>"+ item.createUser+ "</td><td>"+ item.gmtCreate+ "</td></tr>";
				  }else{
					  html += "<tr><td><label class='pos-rel'>";
					  html += "<input type='radio' id='"+item.id+"' type='"+item.isOnline+"'  name='checkbox' class='ace'  valu='"+item.name+"'></label></td>";
					  html += "<td>"+ item.name+ "</td><td>"+"</td><td>"+ item.createUser+ "</td><td>"+ item.gmtCreate+ "</td></tr>";
				  }
				  }else{
					  if(item.type!=null){
					  html += "<tr><td><label class='pos-rel'>";
					  html += "<input type='radio' id='"+item.id+"' type='"+item.isOnline+"'  name='checkbox' class='ace'  valu='"+item.name+"'></label></td>";
					  html += "<td>"+ item.name+ "</td><td>" + type[item.type]+ "</td><td>"+ item.createUser+ "</td><td>"+ item.gmtCreate+ "</td></tr>";
				  
					  }else{
						  html += "<tr><td><label class='pos-rel'>";
						  html += "<input type='radio' id='"+item.id+"' type='"+item.isOnline+"'  name='checkbox' class='ace'  valu='"+item.name+"'></label></td>";
						  html += "<td>"+ item.name+ "</td><td>"+"</td><td>"+ item.createUser+ "</td><td>"+ item.gmtCreate+ "</td></tr>";
					  }
					  }
			  });
			  html += "</tbody>";
			  $("#tab").html(html);
			}										
			});	
		  }
		//保存
		function save(ty){
		 var array=new Array();	
		 if(ty==1){
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
				parent.closeLayerdown(tagId,array); 
				backOne();
			}
		}else{
			type=2;
			var id=ty;
			var name=$("#cname").val();
			array[0]={name:name,id:id,type:type};
			parent.closeLayerd(tagId,array); 
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
		 
		 function updateAccount(){
				$.ajax({  
					cache:false,
					async:false, //不是异步处理  
			        type: "POST",  
			        dataType : "json",  
			        data :$('#f1').serialize(),  
			        url: "${ctx}/manage/studyPlanManage/editCourse",//请求的action路径  
			        success:function (data) {//请求成功处理函数 
			    	 	save(data.data);
			    	 	backOne();
			        },  
			        error: function (data) {//请求失败处理函数 
			        	 alert(data.msg);
			        }   
			    }); 
			}
	</script>
</body>
</html>