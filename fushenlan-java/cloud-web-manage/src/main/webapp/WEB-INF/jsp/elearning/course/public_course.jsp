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
<%-- <script type="text/javascript" src="${ctx}/resources/js/common/jquery-3.2.1.min.js"></script> --%>
<script type="text/javascript" src="${ctx}/resources/libs/datepicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common/page.js"></script>

<div class="wrapper">
		<c:if test="${search_pubType== 2}">
	    <div class="filter-box">
			<form id="course" action="${ctx}/manage/publicCourseListByPageByGroupId" method="get">
				<ul class="ui-form grid-row">
					<li class="col-md-6"><strong>课程关键字</strong><input id="search_keyWord" name="keyWord" class="ipt-text" type="text" placeholder="" value="${search_keyWord }"></li>
					<%-- <li class="col-md-3">
						<strong>二级分类</strong>
							<select id="search_tagId" name="tagId">
								<option value="">请选择</option>
                                <c:forEach var="tag" items="${tagList}">
                                    <option value="${tag.id}"
                                        <c:if test="${search_tagId == tag.id}">
                                            selected
                                        </c:if>
                                    >${tag.tagName}</option>
                                </c:forEach>
							</select>
					</li>	 --%>	
					<li class="col-md-6 sameWadte">
						<strong>课程上传日期</strong>
							<input id="search_uploadTimeBegin" name="uploadTimeBegin" value="${search_uploadTimeBegin }" type="text" placeholder="开始时间" class="Wdate" style="max-width: 180px;" onfocus="WdatePicker({onpicked: function(){jQuery(this).trigger('change');},oncleared: function(){jQuery(this).trigger('change');}})">&nbsp;—&nbsp;
							<input id="search_uploadTimeEnd" name="uploadTimeEnd" value="${search_uploadTimeEnd }" type="text" placeholder="结束时间" class="Wdate" style="max-width: 180px;" onfocus="WdatePicker({onpicked: function(){jQuery(this).trigger('change');},oncleared: function(){jQuery(this).trigger('change');}})">
					</li>
					<li class="col-md-3">
						<strong>课程性质</strong>
						<div class="course_status" style="margin-left: 20px;">
								<label for="upper"><input <c:if test="${search_isOnline==1}">checked="checked"</c:if>  type="radio" value="1" id="upper" name="isOnline">线上
								</label>
								<label for="off"><input <c:if test="${search_isOnline==0 }">checked="checked"</c:if> type="radio" value="0" id="off" name="isOnline">线下
								</label>
						</div>
					</li>
					<li class="col-md-3" style="text-align: right">
						<button type="submit" class="btn btn-submit btn-radius btn-search" ><i class="icon-search"></i> 查询</button>
					</li>
				</ul>
			</form>
		</div>
	</c:if>	
		
	<c:if test="${search_pubType== 1}">	
		<div class="filter-box">
			<form id="course" action="${ctx}/manage/publicCourseListByPage" method="get">
				<ul class="ui-form grid-row">
					<li class="col-md-6"><strong>课程关键字</strong><input id="search_keyWord" name="keyWord" class="ipt-text" type="text" placeholder="" value="${search_keyWord }"></li>
					<%-- <li class="col-md-3">
						<strong>二级分类</strong>
							<select id="search_tagId" name="tagId">
								<option value="" >请选择</option>
								<c:forEach var="tag" items="${tagList}">
                                    <option value="${tag.id}"
                                        <c:if test="${search_tagId == tag.id}">
                                            selected
                                        </c:if>
                                    >${tag.tagName}</option>
                                </c:forEach>
							</select>
				    </li> --%>			
					<%-- <li class="col-md-3"><strong>课程提交者</strong><input id="submitter" name="submitter" class="ipt-text" type="text" value="${search_submitter }" placeholder=""></li> --%>
					<li class="col-md-6 sameWadte">
						<strong>课程上传日期</strong>
							<input id="search_uploadTimeBegin" name="uploadTimeBegin" value="${search_uploadTimeBegin }" type="text" placeholder="开始时间" class="Wdate" style="max-width: 180px;" onfocus="WdatePicker({onpicked: function(){jQuery(this).trigger('change');},oncleared: function(){jQuery(this).trigger('change');}})">&nbsp;—&nbsp;
							<input id="search_uploadTimeEnd" name="uploadTimeEnd" value="${search_uploadTimeEnd }" type="text" placeholder="结束时间" class="Wdate" style="max-width: 180px;" onfocus="WdatePicker({onpicked: function(){jQuery(this).trigger('change');},oncleared: function(){jQuery(this).trigger('change');}})">
					</li>
					<li class="col-md-3">
						<strong>课程性质</strong>
						<div class="course_status" style="margin-left: 20px;">
								<label for="upper"><input <c:if test="${search_isOnline==1}">checked="checked"</c:if>  type="radio" value="1" id="upper" name="isOnline">线上
								</label>
								<label for="off"><input <c:if test="${search_isOnline==0 }">checked="checked"</c:if> type="radio" value="0" id="off" name="isOnline">线下
								</label>
						</div>
					</li>
					<li class="col-md-3" style="text-align: right">
						<button type="submit" class="btn btn-submit btn-radius btn-search" ><i class="icon-search"></i> 查询</button>
					</li>
				</ul>
			</form>
		</div>
	</c:if>		
		
		<table class="table table-agents">
			<thead>
				<tr>
					<th style="width: 200px;">课程名称</th>
					<th style="width: 130px;">课程性质</th>
					<!-- <th style="width: 180px;">课程上传者</th> -->
					<th style="width: 180px;">上传日期</th>
					<!-- <th style="width: 180px;">一级分类</th>
					<th style="width: 180px;">二级分类</th> -->
					<th style="width: 180px;">是否分享</th>
					<th style="width: 180px;">是否关联计划</th>
					<th style="width: 140px;">操作</th>
				</tr>
			</thead>
			<tbody>
			 	<c:if test="${not empty page}">
			 		<c:forEach  varStatus="idx" var="course" items="${page.records}">
						<tr>
							<td><a  href="${ctx }/manage/selectCourseById?id=${course.id }" class="clickable">${course.name }</a></td>
							<td>
								<c:if test="${course.isOnline==0 }">线下</c:if>
								<c:if test="${course.isOnline==1 }">线上</c:if>
							</td>
							<%-- <td>${course.createUser }</td> --%>
							<td><fmt:formatDate value="${course.gmtCreate }" pattern="yyyy-MM-dd"/></td>
							<%-- <td>${course.groupName}</td>
							<td>${course.tagName}</td> --%>
							<td><c:if test="${course.isShare==0 }">否</c:if>
								<c:if test="${course.isShare==1 }">是</c:if>
							</td>
							<td><c:if test="${course.isRelatePlan==0 }">否</c:if>
								<c:if test="${course.isRelatePlan==1 }">是</c:if>
							</td>
							<td>
								<a class="color-detail" href="${ctx }/manage/selectCourseById?id=${course.id }" title="查看">查看</a>
							</td>
						</tr>
			 		</c:forEach>
			 	</c:if>
			</tbody>
		</table>
		<div class="table-paging clearfix" id="pageDiv">
		</div>
</div>
<script type="text/javascript">
$(function(){
	var keyWord = '${search_keyWord}';
	var type = '${search_type}';
	var submitter = '${search_submitter}';
	var isOnline = '${search_isOnline}';
	var pubType = '${search_pubType}';
	//var id = '${search_id}';
	//var groupId = '${search_groupId}';
	var tagId = '${search_tagId}';
	var uploadTimeBegin = '${search_uploadTimeBegin}';
	var uploadTimeEnd = '${search_uploadTimeEnd}';
	
	pageAjax("${ctx}/manage/elearning/listByPageAjaxOther?keyWord="+keyWord+"&type="+type+"&submitter="+submitter+"&isOnline="+isOnline+"&pubType="+pubType+"&id="+id+"&uploadTimeBegin="+uploadTimeBegin+"&uploadTimeEnd="+uploadTimeEnd,'${page.pageSize}','${page.pageNo}','${page.pageTotal}','pageDiv');	
		
})

   var codeArray ={};
	codeArray['0'] = '全部';
	codeArray['1'] = '初审面试';
	codeArray['2'] = '甄选面试';
	codeArray['3'] = '决定面试';
	codeArray['isOnline0'] = '线下';
	codeArray['isOnline1'] = '线上';
	codeArray['isShare0'] = '否';
	codeArray['isShare1'] = '是';
	codeArray['isRelatePlan0'] = '否';
	codeArray['isRelatePlan01'] = '是';

	//ajax 分页 拼接数据
	function pageNext(url){
		// 查询字段取页面加载时 model传入的值  防止分页执行查询
    var keyWord = '${search_keyWord}';
	var type = '${search_type}';
	var submitter = '${search_submitter}';
	var isOnline = '${search_isOnline}';
	var pubType = '${search_pubType}';
	var id = '${search_id}';
	//var groupId = '${search_groupId}';
	//var tagId = '${search_tagId}';
	var uploadTimeBegin = '${search_uploadTimeBegin}';
	var uploadTimeEnd = '${search_uploadTimeEnd}';
		$.get(url,function(returnData){
			if(returnData.code == '1'){
				var html = "";
				$.each(returnData.data.records,function(i,row){
					/*row.state = row.state==null?'':row.state; //判断null字段
				 row.name = row.name==null?'':row.name; */
				html+='<tr>'+
					  '<td>'+row.name+'</td>'+
					  '<td>'+codeArray['isOnline'+row.isOnline]+'</td>'+
					  '<td>'+new Date(row.gmtCreate).Format("yyyy-MM-dd")+'</td>'+
					  '<td>'+codeArray['isShare'+row.isShare]+'</td>'+
					  '<td>'+codeArray['isRelatePlan'+row.isRelatePlan]+'</td>'+
					  '<td><a class="color-detail" href="${ctx }/manage/selectCourseById?id='+row.id+'" title="查看">查看</a>'+
					  '</tr>';
				
				})
				$(".table tbody").html(html);
				pageAjax("${ctx}/manage/elearning/listByPageAjaxOther?keyWord="+keyWord+"&type="+type+"&submitter="+submitter+"&isOnline="+isOnline+"&pubType="+pubType+"&id="+id+"&uploadTimeBegin="+uploadTimeBegin+"&uploadTimeEnd="+uploadTimeEnd,returnData.data.pageSize,returnData.data.pageNo,returnData.data.pageTotal,'pageDiv');
			}
		})
	}

	document.body.onmousedown = function (event) {
	    event = event || window.event;
	    var target = event.target || event.srcElement;
	    if (target.type === 'radio') {
	        target.previousValue = target.checked;
	    }
	}
	document.body.onclick = function (event) {
	    event = event || window.event;
	    var target = event.target || event.srcElement;
	    if (target.type === 'radio') {
	        if (target.previousValue) {
	            target.checked = false;
	        }
	    }
	}
/* $(document).ready(function(){
	var radioclick=1;
	$("#upper").click(function(){
	    if(radioclick%2==0){
	        $(this).prop("checked",false);
	    }
	    radioclick++;
	});
	$("#off").click(function(){
	    if(radioclick%2==0){
	        $(this).prop("checked",false);
	    }
	    radioclick++;
	});
	
}) */
</script>
</body>
</html>