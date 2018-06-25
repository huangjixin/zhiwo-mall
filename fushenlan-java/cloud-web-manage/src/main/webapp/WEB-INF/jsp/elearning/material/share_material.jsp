<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<title>富卫运维大平台</title>
<meta name="description" content="">
</head>
<body>
		<script type="text/javascript" src="${ctx}/resources/libs/layer/layer.js" ></script>
		<div class="form-detail">
			<div class="title"><strong>分享资料</strong></div>
			<ul class="clearfix">
				<c:forEach items="${materials }" var="material"  varStatus="status">
					<li class="col-md-6"><strong>资料名称：</strong>${material.name }</li>
					<input type="hidden" name="materialId" class="materialId" value="${material.id }">
				</c:forEach>
				<li class="col-md-6 zlgl_share">
						<strong>是否分享：</strong>
						<label for="yes"><input type="radio" value="1" id="yes" name="share" <c:if test="${not empty groupIds}">checked</c:if>>是
						</label>
						<label for="no"><input type="radio" value="0" id="no" name="share" <c:if test="${empty groupIds}">checked</c:if>>否
						</label>
				</li>
				
				<li class="col-md-12 zlgl_share">
					<strong>分享范围：</strong>
					<c:if test="${not empty groupList}">
					    <c:forEach items="${groupList}" var="group">
							<label><input name="groupId" type="checkbox" class="groupId" value="${group.id}" 
							        <c:if test="${fn:contains(groupIds,group.id)}">
							             checked
							        </c:if> 
							         />${group.groupName}
							</label> 
					    </c:forEach>
					 </c:if>
				</li>
			</ul>
			<div class="ui-button">
				<button type="button" class="btn btn-submit" onclick="share()">保存并关闭</button>
				<button type="button" class="btn btn-default" onclick="history.go(-1)">取消</button>
			</div>
			<!-- <div class="ui-button—no" align="center">
				<button type="button" class="btn btn-default" onclick="history.go(-1)">取消</button>
			</div> -->
		</div>
<script type="text/javascript" src="${ctx}/resources/libs/layer/layer.js"></script>
<script type="text/javascript">
	$(document).ready(function (){
		if($("#no").is(":checked")){
			 $(".col-md-12").hide();
			 //$(".ui-button").hide();
			// $(".ui-button—no").show();
		}else{
			$(".col-md-12").show();
			//$(".ui-button").show();
			//$(".ui-button—no").hide();
		}
	})

	function share(){
		//获取所有的资料Id
		var materials = new Array();
		 var groupCheckbox=$("input[name='materialId']");
		     for(i=0;i<groupCheckbox.length;i++){
		    	 materials[i] =groupCheckbox[i].value;
		    	 //materials.push(groupCheckbox[i].value);
		    }
		
		//获取所有的用户组Id
		var groups = new Array();
		 var groupCheckbox=$("input[name='groupId']:checked");
		     for(i=0;i<groupCheckbox.length;i++){
		        if(groupCheckbox.eq(i).is(":checked")){
		        	groups[i] =groupCheckbox[i].value;
		        	//groups.push(groupCheckbox[i].value);
		        }
		    }
		 if($("#no").is(":checked")){ 
			 $.ajax({
                 type: 'POST',
                 url: "${ctx}/manage/doShareMaterials",
                 data :{"materialIds" : materials,"groupIds":null,"noShare":"flag"},
                 success: function(result) {
                   if(result){
                       layer.alert("取消分享成功");
                       location.href="${ctx}/manage/privateMaterialListByPage";
                   }
                 }
              });
		 } else {
			 if (groups.length > 0) {
	             $.ajax({
	                 type: 'POST',
	                 url: "${ctx}/manage/doShareMaterials",
	                 data :{"materialIds" : materials,"groupIds":groups},
	                 //traditional:true,
	                 success: function(result) {
	                   if(result){
	                       layer.alert("分享成功");
	                       location.href="${ctx}/manage/privateMaterialListByPage";
	                   }
	                 }
	              });
	         } else {
	             layer.alert("请选择要分享的用户组");
	         }
		 }   
	}
	
	$("input[name='share']").change(function(){
		if($("#no").is(":checked")){
			 $(".col-md-12").hide();
			// $(".ui-button").hide();
			// $(".ui-button—no").show();
		}else{
			$(".col-md-12").show();
			//$(".ui-button").show();
			//$(".ui-button—no").hide();
		}
	});
	
</script>
</body>
</html>
