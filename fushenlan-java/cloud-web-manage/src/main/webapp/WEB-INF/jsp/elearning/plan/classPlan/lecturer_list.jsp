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
			<div class="form-search" style="background-color: #fff;">
			<ul class="ui-form grid-row">
				<li class="col-md-3" style="margin-left:12px;width:30%;margin-top:15px;"><strong style="">负责人名称</strong><input id="name" name="name" style="margin-left:50px;" class="ipt-text" type="text" value="${name }"></li>
				<li class="col-md-3" style="text-align: right;margin-top:15px;">
					<button type="button" class="btn btn-submit btn-radius btn-search" onclick="seleByName()"><i class="icon-search"></i>查询</button>
				</li>
			</ul>
			</div>
		</div>
		<table class="table table-agents">
			<thead>
				<tr>
					<th>
						<label class="pos-rel">
							<input type="checkbox" class="ace"  id="checkbox" >
						</label>
					</th>
					<th>讲师姓名</th>
					<th>手机号</th>
					<th>邮箱</th>
					<th>职级</th>
				</tr>
			</thead>
			<tbody id="tbody">
				
			</tbody>
		</table>
		<div class="btn_list" align="center" style="margin-top: 15px;">
			<button class="btn btn-submit" onclick="addLecturers()">确定</button>
			<button class="btn btn-default" onclick ="back()">返回</button>
		</div>
	</div>
	<script type="text/javascript">
	$(function(){
		var html = "";
		if("${not empty accountList}" == 'true'){
			<c:forEach  varStatus="idx" var="account" items="${accountList}">
			html+='<tr>'+
				'<td>'+
					'<input id="accountId" name="checkbox" type="checkbox" class="ace" value="${account.id }">'+
				'</td>'+
				'<td><a  href="#" class="clickable">${account.accountName }</a></td>'+
				'<td>${account.mobile }</td>'+
				'<td>${account.email }</td>'+
				'<td>';
					<c:if test="${account.postType==1 }">html+='总监';</c:if>
					<c:if test="${account.postType==2 }">html+='主管';</c:if>
					<c:if test="${account.postType==3 }">html+='代理人';</c:if>
					<c:if test="${account.postType==4 }">html+='内勤';</c:if>
				html+='</td>'+
			'</tr>';
		</c:forEach>
		}
		$("#tbody").append(html);
		var managerIds = parent.$("input[name^='managerIds']");
		var ids = [];
		$.each(managerIds,function(){
			ids[$(this).val()] = "";
		})
		$("input[name='checkbox']").each(function(){
			if(ids[$(this).val()] == ""){
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
	function addLecturers() {
		var id= $("#courseId").val();
		var accountIds = new Array();
		var accountNames = "" ;
	    var groupCheckbox=$("input[name='checkbox']:checked");
		for(i=0;i<groupCheckbox.length;i++){
		    if(groupCheckbox.eq(i).is(":checked")){//
		     	var name=groupCheckbox.eq(i).parent().siblings().find(".clickable").text();
		        	//alert(name);
	        	accountIds[i] =groupCheckbox[i].value;
	        	accountNames+=name+",";
		    }
		}
		accountNames = accountNames.substring(0,accountNames.length-1);
	    parent.accountList(accountIds,accountNames); 
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
	function seleByName(){
		var html ="";
		var name = $("#name").val();
		$.ajax({
			async : false ,
			type : "post",
			data : {name:name},
			url : "${ctx}/manage/classPlan/lecturerListAjax",
			success : function(data) {
				$.each(data,function(i,account){
					html+='<tr>'+
					'<td>'+
						'<input id="accountId" name="checkbox" type="checkbox" class="ace" value="'+account.id+'">'+
					'</td>'+
					'<td><a  href="#" class="clickable">'+account.accountName+'</a></td>'+
					'<td>'+account.mobile+'</td>'+
					'<td>'+account.email+'</td>'+
					'<td>';
						if(account.postType=='1')html+='总监';
						if(account.postType=='2')html+='主管';
						if(account.postType=='3')html+='代理人';
						if(account.postType=='4')html+='内勤';
					html+='</td>'+
				'</tr>';
				});
				$("#tbody").html(html);
			}
		});
	}
	function back(){
		 /* var index = parent.layer.open();
		 parent.layer.close(index); */
		 var index=parent.layer.getFrameIndex(window.name);
	     parent.layer.close(index);
	}
	</script>
</body>
</html>