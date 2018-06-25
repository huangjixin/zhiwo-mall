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
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/page.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/libs/datepicker/skin/default/datepicker.css">
	    <div class="title"><strong>新增公开课程</strong></div>
		<div class="form-detail">
			<div class="nav-tabs">
				<strong class="active">选择课程</strong>
				<strong>选择计划</strong>
			</div>
			<form action="${ctx }/manage/toAddJsp" class="form" method="post">
			<div class="tab-pane active">
				<ul class="edit clearfix online_form ui-form">
					<li class="col-md-3"><strong>课程关键字</strong><input type="text"  name="courseKeyWord" placeholder="" value="${map.courseKeyWord }"></li>
					<li class="col-md-3">
						<strong>课程类型</strong><select name="courseType">
							<option value="" >请选择</option>
							<option <c:if test="${map.courseType==1 }">selected</c:if> value="1" >视频</option>
							<option <c:if test="${map.courseType==2 }">selected</c:if>  value="2" >PPT</option>
							<option <c:if test="${map.courseType==3 }">selected</c:if>  value="3" >SCORM</option>
							<option <c:if test="${map.courseType==4 }">selected</c:if>  value="4" >WORD</option>
							<option <c:if test="${map.courseType==5 }">selected</c:if>  value="5" >EXCEL</option>
						</select>
						
					</li>
					<li class="col-md-3"><strong>课程提交者</strong><input type="text"  name="courseCreateUser" value="${map.courseCreateUser }"></li>
					<li class="col-md-3">
						<strong>一级分类</strong><select name="courseGroupId">
							<option value="" >请选择</option>
							<option <c:if test="${map.courseGroupId==1 }">selected</c:if> value="1" >视频</option>
							<option <c:if test="${map.courseGroupId==2 }">selected</c:if>  value="2" >PPT</option>
							<option <c:if test="${map.courseGroupId==3 }">selected</c:if>  value="3" >SCORM</option>
							<option <c:if test="${map.courseGroupId==4 }">selected</c:if>  value="4" >WORD</option>
							<option <c:if test="${map.courseGroupId==5 }">selected</c:if>  value="5" >EXCEL</option>
						</select>
						
					</li>
					<li class="col-md-3">
						<strong>二级分类</strong><select name="courseTagId">
							<option value="" >请选择</option>
							<option <c:if test="${map.courseTagId==1 }">selected</c:if> value="1" >视频</option>
							<option <c:if test="${map.courseTagId==2 }">selected</c:if>  value="2" >PPT</option>
							<option <c:if test="${map.courseTagId==3 }">selected</c:if>  value="3" >SCORM</option>
							<option <c:if test="${map.courseTagId==4 }">selected</c:if>  value="4" >WORD</option>
							<option <c:if test="${map.courseTagId==5 }">selected</c:if>  value="5" >EXCEL</option>
							</select>
						
					</li>
					<li class="col-md-6 sameWadte">
						<strong>资料上传时间</strong>
						<input type="text" name="courseUploadTimeBegin" value="${map.courseUploadTimeBegin }" placeholder="开始时间" class="Wdate" style="max-width: 180px;" onfocus="WdatePicker({onpicked: function(){jQuery(this).trigger('change');},oncleared: function(){jQuery(this).trigger('change');}})">&nbsp;—&nbsp;
						<input type="text" name="courseUploadTimeEnd" value="${map.courseUploadTimeEnd }" placeholder="结束时间" class="Wdate" style="max-width: 180px;" onfocus="WdatePicker({onpicked: function(){jQuery(this).trigger('change');},oncleared: function(){jQuery(this).trigger('change');}})">
					</li>
					
				</ul>
				<button type="submit_btn" class="btn btn-submit btn-radius btn-search"><i class="icon-search"></i> 查询</button>
				<div class="title"><strong>查询结果</strong></div>
				<div class="find_result">
					<table class="table table-agents">
						<thead>
							<tr>
								<th><input type="checkbox" id="checkbox" class="aceCourse" value="${course.id }"></th>
								<th>课程名称</th>
								<th>课程类型</th>
								<th>课程大小</th>
								<th>课程提交者</th>
								<th>上传日期</th>
								<th>课程分类</th>
							</tr>
						</thead>
						<tbody>
						<c:forEach items="${courseList }" var="course">
							<tr>
								<td>
									<label class="pos-rel">
										<input type="checkbox" name="checkbox" class="aceCourse" value="${course.id }">
									</label>
								</td>
								<td>${course.name }</td>
								<td>${course.type }</td>
								<td>${course.learningTime }</td>
								<td>${course.createUser }</td>
								<td><fmt:formatDate value="${course.gmtCreate }" type="both"/></td>
								<td>${course.tagId }</td>
						</c:forEach>		
							</tr>
						</tbody>
					</table>
				</div>
				<div class="ui-button">
				<a class="btn btn-submit" href="#" onclick="toAddJspSecond('2')" title="下一步">下一步</a>
				<button type="button" class="btn btn-default"  onclick="history.go(-1)">取消</button>
				</div>
			</div>
		</form>
		<form action="${ctx }/manage/toAddJsp" class="form" method="post">	
			<div class="tab-pane">
				<ul class="edit clearfix online_form">
					<li class="col-md-3"><strong>计划关键字：</strong><input type="text" class="ipt-text" placeholder="" name="planKeyWord" value="${map.planKeyWord }"></li>
					<li class="col-md-3"><strong>计划代码：</strong><input type="text" class="ipt-text" placeholder="" name="planCode" value="${map.planCode }"></li>
					<li class="col-md-3">
						<strong>二级分类：</strong>
						<div class="ipt-select">
							<i class="icon-arrow-down"></i>
							<select name="planTagId">
							<option value="" >请选择</option>
							<option <c:if test="${map.planTagId==1 }">selected</c:if> value="1" >视频</option>
							<option <c:if test="${map.planTagId==2 }">selected</c:if>  value="2" >PPT</option>
							<option <c:if test="${map.planTagId==3 }">selected</c:if>  value="3" >SCORM</option>
							<option <c:if test="${map.planTagId==4 }">selected</c:if>  value="4" >WORD</option>
							<option <c:if test="${map.planTagId==5 }">selected</c:if>  value="5s" >EXCEL</option>
							</select>
						</div>
					</li>
					<li class="col-md-6 sameWadte">
						<strong>计划上传时间：</strong>
						<input type="text" name="planUploadTimeBegin" value="${map.planUploadTimeBegin }" placeholder="开始时间" class="Wdate" style="max-width: 180px;" onfocus="WdatePicker({onpicked: function(){jQuery(this).trigger('change');},oncleared: function(){jQuery(this).trigger('change');}})">&nbsp;—&nbsp;
						<input type="text" name="planUploadTimeEnd" value="${map.planUploadTimeEnd }" placeholder="结束时间" class="Wdate" style="max-width: 180px;" onfocus="WdatePicker({onpicked: function(){jQuery(this).trigger('change');},oncleared: function(){jQuery(this).trigger('change');}})">
					</li>
					<li class="col-md-4 col-btn" style="text-align: right;">
						<button type="submit_btn" class="btn btn-submit btn-radius btn-search"><i class="icon-search"></i> 查询</button>
					</li>
				</ul>
				<div class="title"><strong>查询结果</strong></div>
				<div class="find_result">
					<table class="table table-agents">
						<thead>
							<tr>
								<th></th>
								<th>计划名称</th>
								<th>计划代码</th>
								<th>计划阶段数</th>
								<th>计划提交者</th>
								<th>上传日期</th>
								<th>计划分类</th>
							</tr>
						</thead>
						<tbody>
						<c:forEach items="${planList }" var="studyPlan" >
							<tr>
								<td>
									<label class="pos-rel">
										<input type="radio" name="radio" class="acePlan" value="${studyPlan.id }">
									</label>
								</td>
								<td>${studyPlan.name }</td>
								<td>${studyPlan.code }</td>
								<td>${studyPlan.stageNum }</td>
								<td>${studyPlan.createUser }</td>
								<td><fmt:formatDate value="${studyPlan.gmtCreate }" type="both"/></td>
								<td>${studyPlan.tagId }</td>
								
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="ui-button">
				<a class="btn btn-submit" href="#" onclick="toAddJspSecond('4')" title="下一步">下一步</a>
				<button type="button" class="btn btn-default" onclick="history.go(-1)">取消</button>
				</div>
			</div>
		</form>
			
		</div>


<%-- <script src="${ctx}/resources/js/common/jquery-3.2.1.min.js"></script> --%>
<%-- <script src="${ctx}/resources/js/common.js"></script> --%>
<script type="text/javascript" src="${ctx}/resources/libs/datepicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/layer/layer.js"></script>
<script type="text/javascript">
$(function(){
	$('.nav-tabs').each(function(index, element) {
		var _obj = $(this);
		$(this).on('click', 'strong:not(.active)', function(){
			$(this).addClass('active').siblings('strong').removeClass('active');
			_obj.nextAll('.form').children('.tab-pane').removeClass('active').eq($(this).index()).addClass('active')
		})
	});
})

	function toAddJspSecond(type){
		var id= new Array();
		//var courseId=$("input[class='aceCourse']:checked").val();
		var planId=$("input[class='acePlan']:checked").val();
		if(type=="2"){//线上课程
			//id=courseId;
			var groupCheckbox=$("input[name='checkbox']:checked");
		     for(i=0;i<groupCheckbox.length;i++){
		        if(groupCheckbox.eq(i).is(":checked")){
		        	id.push(groupCheckbox[i].value);
		        }
		    }
		}else{//学习计划
			id.push(planId);
		}
		if(""==id||null==id){
			layer.alert("请选择学习计划或者基础课程");
		}else{
			location.href="${ctx }/manage/toAddJspSecond?type="+type+"&planOrCourseId="+id;	
		}
		
}
function returnPublicClass(){
	location.href="${ctx}/manage/publicClassListByPage";
}

$("#checkbox").click(function () {
    if($(this).is(':checked')){
    	$("input[name='checkbox']").prop("checked",true);
    }else{
    	$("input[name='checkbox']").prop("checked",false);
    }
});



</script>
</body>
</html>