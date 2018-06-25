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
	.col-md-5{
		width:100% !important;
	}
	input.ipt-text{
		width:220px;
	}
	.btn-search{
		position:inherit;
	}
</style>
	<div class="filter-box">
		<div>总公司增员流程</div>
	</div>
	<c:if test="${not empty headFlow}">
		<table class="table table-agents" >
			<thead>
				<tr>
					<th>总公司</th>
					<th>流程名称</th>
					<th>流程说明</th>
					<th>操作人</th>
					<th>操作时间</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>
						<c:forEach items="${orgList}" var="org">
							<c:if test="${org.id == headFlow.orgId}">
								${org.cnName}
							</c:if>
						</c:forEach>
					</td>
					<td>${headFlow.flowName}</td>
					<td>${headFlow.flowDesc}</td>
					<td>${headFlow.updateUserName}</td>
					<td><fmt:formatDate value="${headFlow.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td><a href="${ctx}/manage/flow/addFlow?flowId=${headFlow.id}&operation=check">查看</a><a href="${ctx}/manage/flow/addFlow?flowId=${headFlow.id}&operation=modify">修改</a></td>
				</tr>
			</tbody>
		</table>
		</c:if>
		
	<div class="filter-box" style="margin-top:60px;">
		<div>分公司增员流程</div>
	</div>
		<ul class="ui-form grid-row">
			<li class="col-md-5 auto">
				<strong class="long">流程名称：</strong><input class="ipt-text" type="text" name="keyWord" id ="flowName" value="${flow.flowName }" placeholder="">
				<button  style="margin-left:60px;" type="button" onclick="ftr()" value="" class="btn btn-submit btn-search"> 查询</button>	
				<button style="margin-left:60px;" type="button" onclick="addFlow()" class="btn btn-submit btn-search"> 新增</button>		
			</li>
		</ul>
	<table class="table table-agents">
			
			<thead>
				<tr>
					<th>分公司</th>
					<th>流程名称</th>
					<th>流程说明</th>
					<th>操作人</th>
					<th>操作时间</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				 <c:if test="${not empty flowList}">
		                   <c:forEach  varStatus="idx" var="flows" items="${flowList.data.records}">
		                        <tr>
		                            <td>
		                              <c:forEach items="${orgList}" var="org">
										<c:if test="${org.id == flows.orgId}">
											${org.cnName}
										</c:if>
									  </c:forEach>
		                            </td>
		                            <td>${flows.flowName}</td>
		                            <td>${flows.flowDesc}</td>
		                            <td>${flows.updateUser}</td>
		                            <td><fmt:formatDate value="${flows.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		                            <td><a href="${ctx}/manage/flow/addFlow?flowId=${flows.id}&operation=check">查看</a><a href="${ctx}/manage/flow/addFlow?flowId=${flows.id}&operation=modify">修改</a><a onclick="del('${flows.id}')">删除</a></td>
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
		page("${ctx}/manage/flow/flowList?flowName="+$("#flowName").val(),'${flowList.data.pageSize}','${flowList.data.pageNo}','${flowList.data.pageTotal}','pageDiv')
	})
	//新增
	function addFlow(){
		location.href="${ctx}/manage/flow/addFlow?operation=modify";
	}
	//查询
	function ftr(){
		location.href="${ctx}/manage/flow/flowList?flowName="+$("#flowName").val();
	}
	//删除
	function del(flowId){
		layer.confirm('<center>确定删除此流程？</center></br><center>（流程删除后可能会对于已进行流程造成影响）</center>', {
			  btn: ['删除','取消'] //按钮
			}, function(){
				$.ajax({
				  	  url: "${ctx}/manage/flow/deleteFlow",
				  	  dataType: "json",
				  	  type: 'get',
				  	  data :{"flowId":flowId},
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
