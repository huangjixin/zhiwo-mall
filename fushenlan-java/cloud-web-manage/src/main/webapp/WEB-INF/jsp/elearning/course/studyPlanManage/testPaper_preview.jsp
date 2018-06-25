<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
   <c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<title>富卫运维大平台</title>
<meta name="description" content="">
</head>
<body>
		<div class="form-detail">
			<div class="title"><strong>预览信息</strong></div>
			<form id="inputForm" action="${ctx }/manage/studyPlanManage/saveStudyPlan" method="post" >
			    <input name="studyPlan.name" value="${name }" type="hidden"/>
			    <input name="studyPlan.description" value="${description }" type="hidden"/>
			    <input name="studyPlan.tagId" value="${tagId }" type="hidden"/>
			    <input name="studyPlan.id" value="${id }" type="hidden"/>
			    <input name="isStage" value="${isStage }" type="hidden"/>
			    <input name="studyPlan.planType" value="${planType }" type="hidden"/>
			<ul class="clearfix">
				<li class="col-md-12"><strong>计划名称：</strong>${name }</li>
				<li class="col-md-12"><strong>计划说明：</strong>${description }</li>
				<li class="col-md-4"><strong>计划分类：</strong>${tagId }</li>
			</ul>
			<div class="title"><strong>学习计划详情</strong></div>
			<strong style="font-size: 14px">线上课程：</strong>
			<c:if test="${isStage==1 }"><!-- 阶段型计划 -->
			<table class="testPaper_table">
				<ul class="clearfix">
					<tbody id="tbdy">
						<c:forEach var="index" begin="1" end="${maxStage }" step="1">
						<tr>
							<td >
								<b name="tar">${index-1 }</b>
							</td>
							<td>
								<ol>
							<c:forEach items="${planCourseVoReal }" var="item" varStatus="idx" >
							 <c:if test="${item.stage==index}">
								<input name="planCourse[${idx.index}].stage" type="hidden" value="${item.stage }"/>
								<input name="planCourse[${idx.index}].desName" type="hidden" value="${item.desName }"/>
								<input name="planCourse[${idx.index}].associateId" type="hidden" value="${item.associateId }"/>
								<input name="planCourse[${idx.index}].associateType" type="hidden" value="${item.associateType }"/>
								<input name="planCourse[${idx.index}].isCompulsory" type="hidden" value="${item.isCompulsory }"/>
								<input name="planCourse[${idx.index}].seq" type="hidden" value="${item.seq }"/>
								<li><span>${item.desName }</span><i><c:if test="${item.isCompulsory==1 }" >必修</c:if><c:if test="${item.isCompulsory!=1 }" >选修</c:if></i></li>
							 </c:if>
							</c:forEach>
								</ol>
							</td>
						</tr>
						</c:forEach>
					</tbody>
					</ul>
			</table>
			</c:if>
			<c:if test="${isStage!=1 }"><!-- 普通类型计划 -->
			<div>
			<ul class="clearfix">
				<c:forEach items="${planCourseVoReal }" var="item" varStatus="idx" >
				<input name="planCourse[${idx.index}].stage" type="hidden" value="${item.stage }"/>
				<input name="planCourse[${idx.index}].desName" type="hidden" value="${item.desName }"/>
				<input name="planCourse[${idx.index}].associateId" type="hidden" value="${item.associateId }"/>
				<input name="planCourse[${idx.index}].associateType" type="hidden" value="${item.associateType }"/>
				<input name="planCourse[${idx.index}].isCompulsory" type="hidden" value="${item.isCompulsory }"/>
				<input name="planCourse[${idx.index}].seq" type="hidden" value="${item.seq }"/>
				<li><span style="margin-right:10px;">${item.desName }</span><i><c:if test="${item.isCompulsory==1 }" >必修</c:if><c:if test="${item.isCompulsory!=1 }" >选修</c:if></i></li>
				</c:forEach>
			</ul>
			</div>
		</c:if>
				<button type="button" class="btn btn-submit" onclick="cancle()">上一步</button>
				<button type="submit"  class="btn btn-submit">保存</button>
				<button type="button" class="btn btn-default" onclick="cancle2()">取消</button>
		</form>
	</div>
    <script type="text/javascript">
    var names=['一','二','三','四','五','六','七','八','九','十','十一','十二','十三','十四','十五'];//阶段名
    
   $(function(){
    	  $("b[name='tar']").each(function(){
    	    	var index=$(this).text();
    	    	$(this).text('第'+names[index]+'阶段');
    	    });	
    }); 
  
   function cancle(){
	   $("#inputForm").attr("action","${ctx}/manage/studyPlanManage/goback");
	   $("#inputForm").submit()
   }
   function cancle2(){
	   location.href="${ctx}/manage/studyPlanManage";
   }
 </script>
</body>
</html>