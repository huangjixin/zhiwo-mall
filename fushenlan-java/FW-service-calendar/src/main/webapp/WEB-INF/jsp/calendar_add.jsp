<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 	<title>富卫运维大平台</title>
 	<!-- 日历样式 -->
 	<style type="text/css">
	.calendarContent .calendarTitle{ 
        width: 850px;  
    }
	.calendarContent li{ 
        list-style: none;  
		width:120px;
		height:60px;
		border:0.2px solid gray;
		float:left;
		margin:0 0 -2px -2px;
		background-color:#F8F8F8;
    }
	.calendarTitle li{ 
		list-style: none;  
		width:120px;
		height:60px;
		border:0.2px solid gray;
		float:left;
		margin:0 0 -2px -2px;
        background-color:#E8E8E8;  
    }
    .otherMonth { 
        background-color:#C0C0C0;  
    }
</style>
</head>
<body>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/style.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/font/iconfont.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/page.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/libs/datepicker/skin/default/datepicker.css">
<div class="form-detail">
	<div class="title"><strong>新增标签</strong></div>
		<div class="tab-pane active">
			<form action="${ctx }/manage/toAddTagJsp" class="form" method="post">
				<div class="title"><strong>选择分类</strong></div>
					<ul class="edit clearfix online_form ui-form">
						<li class="col-md-3">
							<strong>分类</strong>
							<select name="catagory" id="catagory" style="width:150px;">
								<option value="">请选择</option>
								<c:forEach items="${catagorys }" var="selectedCatagory" varStatus="status">
									 <option <c:if test="${catagoryId==selectedCatagory.id }">selected</c:if> value="${selectedCatagory.id }" >${selectedCatagory.name }</option> 
								</c:forEach>	
							</select>
						</li>
					</ul>
				<div class="title"><strong>选择一级标签</strong></div>
					<ul class="edit clearfix online_form ui-form">
					<li class="col-md-3">
						<strong>一级标签</strong>
						<select name="parentTagNames" id="parentTagNames" style="width:150px;" onchange="selectParentTagNames()">
							<c:forEach items="${allParentTags }" var="tag" varStatus="status">
								 <option <c:if test="${parentTagName==tag.tagName  }">selected</c:if> value="${tag.tagName }" >${tag.tagName }</option> 
							</c:forEach>	
						</select>
						<input type="text"  name="parentTagName" placeholder="输入一级标签" value="${parentTagName }" style="width:130px;margin-left:-157px">
					</li>
					<li class="col-md-3">
						<button type="submit_btn" class="btn btn-submit btn-radius "><i class="icon-search"></i> 查询</button>
					</li>
				
				</ul>
			</form>
			<div class="title"><strong>创建二级标签</strong></div>
				<ul class="edit clearfix online_form ui-form">
					<li class="col-md-3"><strong>二级标签</strong><input type="text"  name="childTagName" placeholder="输入二级标签" value="${childTagName }"></li>
					<li class="col-md-3">
						<button type="button" class="btn btn-submit btn-radius " onclick="addTag()"> 创建</button>
					</li>
				</ul>
			<div class="title"><strong>查询结果</strong></div>
				<div class="find_result">
					<table class="table table-agents">
						<ul class="calendarTitle">
							<li>星期一</li>
							<li>星期二</li>
							<li>星期三</li>
							<li>星期四</li>
							<li>星期五</li>
							<li>星期六</li>
							<li>星期日</li>
						</ul>
						<ul class="calendarContent">
							<c:forEach items="${calendarBookDetailVo.calendarDailyTransactionList }" var="calendarDailyTransaction" varStatus="status">
								<!-- 判断是否第一个元素 -->
								<c:if test="${status.count==1}">
									<!-- 第一个元素前出现上个月日期li的个数 -->
									<c:forEach var="i" begin="1" end="${calendarDailyTransaction.dayOfWeek}" step="1">
										<li class="otherMonth"></li>	
									</c:forEach>								
								</c:if>
								<li><fmt:formatDate value="${calendarDailyTransaction.day }"  pattern="dd"/></li>
								<!-- 判断是否最后一个元素 -->
								<c:if test="${fn:length(calendarBookDetailVo.calendarDailyTransactionList)==status.count}">
									<!-- 最后一个元素后出现下个月日期li的个数 -->
									<c:forEach var="i" begin="${calendarDailyTransaction.dayOfWeek}" end="7" step="1">
										<li class="otherMonth"></li>	
									</c:forEach>								
								</c:if>
							</c:forEach>
						</ul>
					</table>
				</div>
		</div>
		<!-- <div class="table-paging clearfix" id="pageDiv">
		</div> -->
</div>
			
		
<%-- <script src="${ctx}/resources/js/common/jquery-3.2.1.min.js"></script> --%>
<%-- <script src="${ctx}/resources/js/common.js"></script> --%>
<script type="text/javascript" src="${ctx}/resources/libs/datepicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common/page.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/layer/layer.js"></script>
<script type="text/javascript">
$(function(){
	$("li").css("color","red");	
	$("li").css("width","98px");	
	$("li").css("height","98px");
})

function getMonthCalendar(){
	
}
</script>
</body>
</html>