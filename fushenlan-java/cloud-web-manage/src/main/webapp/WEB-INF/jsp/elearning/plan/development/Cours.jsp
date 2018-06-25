<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/style.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/font/iconfont.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/page.css"><head>


</head>
<body>
<script src="${ctx}/resources/js/common/jquery-3.2.1.min.js"></script>
<script src="${ctx}/resources/js/common.js"></script>
<!-- S Wrapper -->
<div class="wrapper">
	<!-- S Wrapper Content -->
		<div class="form-detail clearfix ggkc_form">
			<div class="title">
				<strong>课程信息</strong>
			</div>
			<div class="ggkc_left clearfix col-md-6">
				<ul class="clearfix">
					<li class="col-md-12"><strong>课程名称：</strong>
						${course.name }
					</li>
					<li class="col-md-12"><strong>课程说明：</strong>${course.description }</li>
					<li class="col-md-12 auto"><strong>课程缩略图：</strong><img src="" width="80" height="80"></li>
					<li class="col-md-12"><strong>一级分类：</strong>${course.groupId }</li>
					<li class="col-md-12"><strong>二级分类：</strong>${course.tagId }</li>
				</ul>
			</div>
			<div class="ggkc_right clearfix col-md-6">
				<ul class="clearfix ">
					<li class="col-md-12"><strong>课程课件：</strong>
						低于18周岁均为非民事行为责任人，不可单独办理银行业务
					</li>
					<li class="col-md-12"><strong>课程材料：</strong>啦啦啦啦啦啦啦啦啦资料附件</li>
				</ul>
			</div>
		</div>
		<div class="form-detail clearfix ggkc_form">
			<div class="title"><strong>课程信息</strong></div>
			<ul class="clearfix">
				<li class="col-md-6"><strong>关联计划：</strong>
					 <c:if test="${not empty studyPlanList1}">
					 	<span>公开课：
					        <c:forEach items="${studyPlanList1 }" var="publicClass" varStatus="index">
								${publicClass.name },
							</c:forEach>
						</span>&nbsp;&nbsp;<br>
				    </c:if>
				    <c:if test="${not empty studyPlanList2}">
				    	<span>学习计划：
					        <c:forEach items="${studyPlanList2 }" var="studyPlan" varStatus="index">
								${studyPlan.name },
							</c:forEach>
						</span>&nbsp;&nbsp;<br>
				    </c:if>
				    <%-- <c:if test="${not empty studyPlanList}">
				    	<span>岗位发展计划：
					        <c:forEach items="${studyPlanList }" var="studyPlan" varStatus="index">
								${studyPlan.name },
							</c:forEach>
						</span>&nbsp;&nbsp;<br>
				    </c:if> --%>
				    <c:if test="${not empty compulsoryCplanList}">
				    	<span>必修任务：
					        <c:forEach items="${compulsoryCplanList }" var="compulsoryCplan" varStatus="index">
								${compulsoryCplan.name },
							</c:forEach>
						</span>&nbsp;&nbsp;<br>
				    </c:if>
				    <c:if test="${not empty classPlanList}">
				    	<span>班级计划：
					        <c:forEach items="${classPlanList }" var="classPlan" varStatus="index">
								${classPlan.name },
							</c:forEach>
						</span>&nbsp;&nbsp;
				    </c:if>
				    <c:if test="${empty studyPlanList1}">
				    	<c:if test="${empty studyPlanList2}">
				    		<c:if test="${empty classPlanList}">
				    			<c:if test="${empty compulsoryCplanList}">
				    				<span>该课程没有关联任何计划</span>
				    			</c:if>
				    		</c:if>
				    	</c:if>
				    </c:if>
				</li>
				<li class="col-md-6">
						<strong>是否分享：</strong>
						<span><c:if test="${course.isShare==1 }">是</c:if><c:if test="${course.isShare==0 }">否</c:if></span>
				</li>
			</ul>
		</div>
		<!-- / E Form Detail -->
		
</div>
<!-- E Wrapper -->

<script src="../../js/common/jquery-3.2.1.min.js"></script>
</body>
</html>
