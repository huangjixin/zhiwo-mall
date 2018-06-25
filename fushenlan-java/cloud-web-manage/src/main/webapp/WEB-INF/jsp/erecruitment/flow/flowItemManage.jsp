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

.filter-box .ipt-text, .filter-box .ipt-select{
	max-width: 180px !important;	
}
.ui-form li{
	padding:0 20px 0 60px;

}
	.btn-search{
		position:inherit;
	}
	.col-md-5{
		    width: 36%;
	}
	.col-md-6{
		    width: 60%;
	}

</style>
		<div class="filter-box">
			<ul class="ui-form grid-row">
				<li class="col-md-5 auto"><strong class="long">系统名称：</strong><input class="ipt-text" type="text" name="keyWord" id ="systemName" value="${flowItem.systemName}" placeholder=""></li>
				<li class="col-md-6 auto">
					<strong class="long">模块名称：</strong><input class="ipt-text" type="text" name="keyWord" id ="moudleName" value="${flowItem.moudleName }" placeholder="">
					<button style="margin-left:60px;" type="button" onclick="addFlowItem()" class="btn btn-submit btn-radius btn-search"> 新增</button>	
				</li>
				<li class="col-md-5 auto"><strong class="long">业务名称：</strong><input class="ipt-text" type="text" name="keyWord" id ="businessName" value="${flowItem.businessName }" placeholder=""></li>
				<li class="col-md-6 auto">
					<strong class="long">功能名称：</strong><input class="ipt-text" type="text" name="keyWord" id ="flowItemName" value="${flowItem.flowItemName }" placeholder="功能名称">
					<button style="margin-left:60px;" type="button" onclick="ftr()" class="btn btn-submit btn-radius btn-search"><i class="icon-search"></i> 查询</button>
				</li>
			</ul>
		</div>
		
		<table class="table table-agents">
			<thead>
				<tr>
					<th>系统名称</th>
					<th>模块名称</th>
					<th>业务名称</th>
					<th>功能名称</th>
					<th>功能说明</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				 <c:if test="${not empty flowList}">
		                   <c:forEach  varStatus="idx" var="flows" items="${flowList.data.records}">
		                        <tr>
		                            <td>${flows.systemName}</td>
		                            <td>${flows.moudleName}</td>
		                            <td>${flows.businessName}</td>
		                            <td>${flows.flowItemName}</td>
		                            <td>${flows.flowItemNameDesc}</td>
		                            <td><a href="${ctx}/manage/flow/addFlowItem?flowItemId=${flows.id}&operation=check">查看</a><a href="${ctx}/manage/flow/addFlowItem?flowItemId=${flows.id}&operation=modify">修改</a><a style=" cursor: pointer;" onclick="del('${flows.id}')">删除</a></td>
		                       </tr>
		                  </c:forEach>
		            </c:if>
			</tbody>
		</table>
		
		<div class="table-paging clearfix" id="pageDiv">
		</div>
<script type="text/javascript" src="${ctx}/resources/js/common/page.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/layer/layer.js"></script>
<script>
	//初始化加载分页
	$(function(){
		page("${ctx}/manage/flow/list?systemName="+$("#systemName").val()+"&moudleName="+$("#moudleName").val()+"&businessName="+$("#businessName").val()+"&flowItemName="+$("#flowItemName").val(),'${flowList.data.pageSize}','${flowList.data.pageNo}','${flowList.data.pageTotal}','pageDiv')
	})
	//查询
	function ftr(){
		location.href="${ctx}/manage/flow/list?systemName="+$("#systemName").val()+"&moudleName="+$("#moudleName").val()+"&businessName="+$("#businessName").val()+"&flowItemName="+$("#flowItemName").val();
	}
	//新增
	function addFlowItem(){
		location.href="${ctx}/manage/flow/addFlowItem?operation=modify";
	}
	//删除
	function del(flowItemId){
		layer.confirm('<center>确定删除此模块？</center></br><center>（流程删除后可能会对于已创建流程造成影响）</center>', {
			  btn: ['删除','取消'] //按钮
			}, function(){
				$.ajax({
				  	  url: "${ctx}/manage/flow/deleteFlowItem",
				  	  dataType: "json",
				  	  type: 'get',
				  	  data :{"flowItemId":flowItemId},
				  	  success: function(result) {
						if (result.code=="1") {
							layer.msg("删除成功");
							 window.location.reload();
						}else{
							layer.msg("删除失败");
						}
					  },
					  error: function(){
						  layer.msg("删除失败");
					  }
				  })
			}, function(){
			 
			});
	}
</script>
</body>
</html>
