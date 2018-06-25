<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/elerning/style.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/elerning/font/iconfont.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/elerning/page.css">
</head>
<body>
		<div class="form-detail">
			<div class="title"><strong>资料信息</strong></div>
			<ul class="clearfix">
				<li class="col-md-4"><strong>资料名称：</strong>${material.name }</li>
				<li class="col-md-4"><strong>资料说明：</strong>${material.description }</li>
				<li class="col-md-4"><strong>资料分类：</strong>${material.type }</li>
				<li class="col-md-4"><strong>资料附件：</strong>${material.name }</li>
				<li class="col-md-4"><strong>资料更新日期：</strong>
				<fmt:formatDate value="${material.gmtModified }" type="both"/>
				</li>
				<li class="col-md-4"><strong>资料提交者：</strong>${material.createUser }</li>
			</ul>
			<div class="title"><strong>关联信息</strong></div>
			<ul class="clearfix">
				<li class="col-md-6"><strong>关联计划：</strong>
				<c:forEach items="${material.courses}" var="course" varStatus="status">
					<c:if test="${course!=null&&course!=''}">
						<span>${course }课程计划</span>&nbsp;&nbsp;
					</c:if>
				</c:forEach>
				<c:forEach items="${material.classPlan}" var="classPlan" varStatus="status">
					<c:if test="${classPlan!=null&&classPlan!=''}">
						<span>${classPlan }班级计划</span>&nbsp;&nbsp;
					</c:if>
				</c:forEach>
				<c:forEach items="${material.postDevelopment}" var="postDevelopment" varStatus="status">
					<c:if test="${postDevelopment!=null&&postDevelopment!=''}">
						<span>${postDevelopment }岗位发展计划</span>&nbsp;&nbsp;
					</c:if>
				</c:forEach>
				<c:forEach items="${material.compulsoryCplan}" var="compulsoryCplan" varStatus="status">
					<c:if test="${compulsoryCplan!=null&&compulsoryCplan!=''}">
						<span>${compulsoryCplan }必修任务</span>&nbsp;&nbsp;
					</c:if>
				</c:forEach>
				<c:forEach items="${material.studyPlan}" var="studyPlan" varStatus="status">
					<c:if test="${studyPlan!=null&&studyPlan!=''}">
						<span>${studyPlan }学习计划</span>&nbsp;&nbsp;
					</c:if>
				</c:forEach>
				</li>
				<li class="col-md-4"><strong>是否分享：</strong>
					<c:if test="${material.isShare=='0'}">
						否
					</c:if>
					<c:if test="${material.isShare=='1'}">
						是
					</c:if>
				</li>
			</ul>
			
			<div class="ui-button">
				<button type="button" class="btn btn-submit" onclick="history.back(-1)">返回</button>
			</div>
		</div>

<%-- <script src="${ctx}/resources/js/common/jquery-3.2.1.min.js"></script> --%>
<%-- <script src="${ctx}/resources/js/elerning/common.js"></script> --%>
</body>
</html>
