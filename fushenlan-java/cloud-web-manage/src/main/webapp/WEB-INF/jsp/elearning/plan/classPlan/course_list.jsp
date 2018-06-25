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
<link rel="stylesheet" href="${ctx}/resources/libs/ztree/zTreeStyle.css" type="text/css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/style.css">
<script type="text/javascript" src="${ctx}/resources/js/common/jquery-3.2.1.min.js" ></script>
<script type="text/javascript" src="${ctx}/resources/libs/datepicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common/page.js"></script>
	<div class="bg hide"></div>
	<div class="popup hide">
		<div class="tb_hd">
			<div class="form-search">
				<input type="text" class="form-control">
				<i><img src="${ctx}/resources/imgs/search.png" alt=""></i>
			</div>
			<button class="cancel_tb"><img src="${ctx}/resources/imgs/cancel.png" alt=""></button>
		</div>
		<table class="table table-agents">
			<thead>
				<tr>
					<th>
						<label class="pos-rel">
							<input type="checkbox" class="ace"  id="checkbox" >
						</label>
					</th>
					<th>课程名称</th>
					<th>课程说明</th>
				</tr>
			</thead>
			<tbody id="tbody">
				<%-- <c:if test="${not empty courseList}">
					<c:forEach  varStatus="idx" var="course" items="${courseList}">
						<tr>
							<td>
								<input id="${course.id}" name="checkbox" type="checkbox" class="ace" value="${course.name }">
							</td>
							<td>${course.name }</td>
							<td>${course.description }</td>
						</tr>
					</c:forEach>
				</c:if> --%>
			</tbody>
		</table>
		<div class="btn_list" align="center" style="margin-top: 15px;">
			<button class="btn btn-submit" onclick="addCourse()">确定</button>
			<button class="btn btn-default" onclick ="back()">返回</button>
		</div>
	</div>
	<script type="text/javascript">
	var isOnline = "${isOnline}";
	$(function(){
		var divId = parent.stageLiMark;
		var associateIds = parent.$("#"+divId).find("input[name$='.associateId']");
		var ids = [];
		$.each(associateIds,function(){
			ids[$(this).val()] = "";
		})
		var html = "";
		<c:forEach  varStatus="idx" var="course" items="${courseList}">
		if(ids['${course.id}']!=""){
			html+='<tr>'+
				'<td>'+
				'<input id="${course.id}" name="checkbox" type="checkbox" class="ace" value="${course.name }">'+
				'</td>'+
				'<td>${course.name }</td>'+
				'<td>${course.description }</td>'+
				'</tr>';
		}
		</c:forEach>
		$("#tbody").html(html);
		$("input[name='checkbox']").each(function(){
			if(ids[$(this).attr("id")] == ""){
				$(this).prop("checked",true);
			}
		})
		var len = $("input[name='checkbox']").length;
		var count = 0;
		$("input[name='checkbox']").each(function (){
			if($(this).is(':checked')){
				count++;
			}
		});
		if(len == count){
			$("#checkbox").prop("checked",true);
		}else{
			$("#checkbox").prop("checked",false);
		}
		
	});
	function addCourse() {
		var id= $("#courseId").val();
		var courseIds = new Array();
		var courseNames = [];
		var type = 2;
	    var groupCheckbox=$("input[name='checkbox']:checked");
		for(i=0;i<groupCheckbox.length;i++){
		    if(groupCheckbox.eq(i).is(":checked")){//
		     	var name=$(groupCheckbox.eq(i)).val();
		     	courseIds[i] =$(groupCheckbox[i]).attr("id");
		     	courseNames[courseIds[i]] = name;
		    }
		}
		if(isOnline == '0'){
			type = 3;
		}
	    parent.courseList(courseIds,courseNames,type);
	    var index=parent.layer.getFrameIndex(window.name);
	    parent.layer.close(index);
	}
	$("#checkbox").click(function () {
	    if($(this).is(':checked')){
	    	$("input[name='checkbox']").prop("checked",true);
	    }else{
	    	$("input[name='checkbox']").prop("checked",false);
	    }
	});
	$("input[name='checkbox']").click(function () {
		var len = $("input[name='checkbox']").length;
		var count = 0;
		$("input[name='checkbox']").each(function (){
			if($(this).is(':checked')){
				count++;
			}
		});
		if(len == count){
			$("#checkbox").prop("checked",true);
		}else{
			$("#checkbox").prop("checked",false);
		}
	});
	
	function back(){
		 var index=parent.layer.getFrameIndex(window.name);
	     parent.layer.close(index);
	}
	</script>
</body>
</html>