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
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/style.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/font/iconfont.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/libs/datepicker/skin/default/datepicker.css">
		
		<div class="form-detail">
			<div class="title"><strong>回复</strong></div>
			<form method="post" id="questionForm">
			<ul class="edit clearfix">
				<c:forEach items="${publicClassVo.course }" var="course">
				<li class="col-md-4"><strong>课程名称：</strong>${course.name }</li>
				</c:forEach>
				<li class="col-md-4"><strong>提问用户：</strong>${planQuestion.createUser }</li>
				<li class="col-md-4"><strong>提问时间：</strong>
				<fmt:formatDate value="${planQuestion.gmtCreate }"/>
				</li>
				<li class="col-md-4"><strong>问题状态：</strong>
				<c:if test="${planQuestion.quesAnswer==null||planQuestion.quesAnswer=='' }">未回复</c:if>
				<c:if test="${planQuestion.quesAnswer!=null&&planQuestion.quesAnswer!='' }">已回复</c:if>
				</li>
				<li class="col-md-4"><strong>问题描述：</strong>${planQuestion.quesDesc }</li>
				<li class="col-md-12"><strong>回复：</strong>
					<input class="ipt-text " type="text" placeholder="" name="quesAnswer" value="${planQuestion.quesAnswer }">
					<input type="hidden" name="id" value="${planQuestion.id }">
					<input type="hidden" id="courseId" value="${planQuestion.courseId }">
				</li>
			</ul>
			</form>
			<div class="ui-button">
				<button type="button" class="btn btn-submit" onclick="savequestion()">保存</button>
				<button type="button" class="btn btn-default" onclick="history.go(-1)">取消</button>
			</div>
			
		</div>


<%-- <script src="${ctx}/resources/js/common/jquery-3.2.1.min.js"></script> --%>
<%-- <script src="${ctx}/resources/js/common.js"></script> --%>
<script type="text/javascript">
	function savequestion(){
		var publicClassId=$("#courseId").val();
		$.ajax({
	      type: 'POST',
	  	  url: "${ctx }/manage/updateQuestion",
	  	  dataType: "json",
	  	  data :$('#questionForm').serialize(),
	  	  success: function(result) {
	  		/* alert(result.msg); */
			location.href="${ctx}/manage/questionListByPage?courseId="+publicClassId;
		  }
	  	})
	}
</script>
</body>
</html>
