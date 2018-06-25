<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<title>富卫运维大平台</title>
<meta name="description" content="">
<link rel="stylesheet" type="text/css" href="../../css/style.css">
<link rel="stylesheet" type="text/css" href="../../css/font/iconfont.css">
<link rel="stylesheet" type="text/css" href="../../css/page.css">
</head>
<body>
	<style>
	textarea{
		width: 100%;
	    font-size: 16px;
	    height: 76px;
	    line-height: 36px;
	    padding: 0 10px;
	    border: 1px solid #dadada;
	    background-color: #fff;
	    border-radius: 3px;
	    box-sizing: border-box;
	    max-width:360px !important;
	}
	.ui-form li{
		    height: auto;
	}
	.btn-search{
		position:inherit;
	}
   
	</style>
		<div class="filter-box">
		 <div class="<c:if test='${operation=="check"}'>mask-layer</c:if>"></div>
		<form id="flowItemForm"  method="post">
			<input name="id" hidden id="${flowItem.data.id}" value="${flowItem.data.id}">
			<input id="operation" hidden  value="${operation}">
			<ul class="ui-form grid-row">
				<li class="col-md-5 auto"><strong class="long">系统名称：</strong><input class="ipt-text" type="text" name="systemName" id ="systemName" value="${flowItem.data.systemName}" placeholder=""></li>
				<li class="col-md-5 auto">
					<strong class="long">模块名称：</strong><input class="ipt-text" type="text" name="moudleName" id ="moudleName" value="${flowItem.data.moudleName }" placeholder="">
				</li>
				<li class="col-md-5 auto"><strong class="long">业务名称：</strong><input class="ipt-text" type="text" name="businessName" id ="businessName" value="${flowItem.data.businessName }" placeholder=""></li>
				<li class="col-md-5 auto">
					<strong class="long">功能名称：</strong><input class="ipt-text" type="text" name="flowItemName" id ="flowItemName" value="${flowItem.data.flowItemName }">
				</li>
				<li class="col-md-5 auto" style="width:100% !important;">
					<strong class="long">功能说明：</strong><textarea class="ipt-text" type="text" name="flowItemNameDesc" id ="flowItemNameDesc" >${flowItem.data.flowItemNameDesc }</textarea>
				</li>
				<li class="col-md-5" style="width:100% !important;text-align:center">
					<c:if test="${operation=='modify'}">
						<button  type="button" onclick="save()" class="btn btn-submit btn-radius btn-search"> 确定</button>	
						<button  type="button" onclick="cancal()" class="btn btn-submit btn-radius btn-search"> 取消</button>
					 </c:if>
					 <c:if test="${operation=='check'}">
					 	<button type="button" class="btn btn-submit" onclick="javascript:history.back(-1)">返回</button>
					 </c:if>
				</li>
			</ul>
		  </form>
		</div>
		
		
<script type="text/javascript" src="${ctx}/resources/js/common/page.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/layer/layer.js"></script>
<script>
	//查看时不可编辑
	$(function(){
		if($("#operation").val()=="check"){
			$("input,textarea").attr("readonly","readonly");
		}
	})
	//返回
	function cancal(){
		window.location.href = "${ctx}/manage/flow/list";
	}
	//更新或插入
	function save(){
		$.ajax({
		  	  url: "${ctx}/manage/flow/saveOrUpdateFlowItem",
		  	  dataType: "json",
		  	  type: 'get',
		  	  data :$('#flowItemForm').serialize(),
		  	  success: function(result) {
				if (result.code=="1") {
					layer.alert("保存成功", {icon: 1},function(){
						window.location.href="${ctx}/manage/flow/list";
					});
				}else{
					layer.alert("保存失败!", {icon: 1});
				}
			  }
		  })
	}
</script>
</body>
</html>
