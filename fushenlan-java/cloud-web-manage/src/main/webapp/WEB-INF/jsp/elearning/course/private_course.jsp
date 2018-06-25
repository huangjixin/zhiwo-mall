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
<script type="text/javascript"	src="${ctx}/resources/libs/layer/layer.js"></script>
<!-- S Wrapper -->
<div class="wrapper">
		<!-- S Filter Box -->
		<div class="nav-tabs">
            <strong id="up" <c:if test ='${search_pubType eq 1}'>class="active"</c:if>  onclick="selectCourse(1)">私人课程库</strong>
            <strong id="down" <c:if test ='${search_pubType eq 2}'>class="active"</c:if> onclick="selectCourse(2)">公共课程库</strong>
        </div>
		<form action="${ctx}/manage/privateCourseListByPage" method="get" >
		<input type="hidden" style="margin-left: 0px;" name="pubType" id="search_pubType" value="${search_pubType }">
			<div class="filter-box">
				<ul class="ui-form grid-row">
					<li class="col-md-4"><strong>课程关键字</strong><input id="search_keyWord" name="keyWord" class="ipt-text" type="text" placeholder="" value="${search_keyWord }"></li>
					<li class="col-md-6 sameWadte">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong>课程上传时间</strong>&nbsp;&nbsp;
						<input id="search_uploadTimeBegin" name="uploadTimeBegin" value="${search_uploadTimeBegin }" type="text" placeholder="开始时间" class="Wdate" style="max-width: 180px;" onfocus="WdatePicker({onpicked: function(){jQuery(this).trigger('change');},oncleared: function(){jQuery(this).trigger('change');}})">&nbsp;—&nbsp;
						<input id="search_uploadTimeEnd" name="uploadTimeEnd" value="${search_uploadTimeEnd }" type="text" placeholder="结束时间" class="Wdate" style="max-width: 180px;" onfocus="WdatePicker({onpicked: function(){jQuery(this).trigger('change');},oncleared: function(){jQuery(this).trigger('change');}})">
					</li>
					<li class="col-md-3">
						<strong>课程性质</strong>
						<div class="course_status" style="margin-left: 20px;">
								<label for="upper"><input <c:if test="${search_isOnline==1}">checked="checked"</c:if> type="radio" value="1" id="upper" name="isOnline">线上
								</label>
								<label for="off"><input <c:if test="${search_isOnline==0}">checked="checked"</c:if> type="radio" value="0" id="off" name="isOnline">线下
								</label>
						</div>
						
					</li>
					<li class="col-md-12" style="text-align: right">
						<button type="submit" class="btn btn-submit btn-radius btn-search"><i class="icon-search"></i> 查询</button>
						<c:if test ='${search_pubType eq 1}'>
							<a class="btn btn-success btn-radius" href="${ctx}/manage/addCourse"  title="新增"><i class="icon-jia"></i>新增</a>
							<button type="button" class="btn btn-success btn-radius" onclick="shareCourses()">批量分享</button>
							<button type="button" class="btn btn-success btn-radius" onclick="deleteBatchCourse()">批量删除</button>
						</c:if>
					</li>
				</ul>
			</div>
		</form>
		<!-- E Filter Box -->
		
		<table class="table table-agents table_public">
			<thead>
				<tr>
					<th>
						<label class="pos-rel">
							<input type="checkbox" class="ace" id="checkbox">
						</label>
					</th>
					<th>课程名称</th>
					<th>课程性质</th>
					<th>上传日期</th>
					<th>是否分享</th>
					<th>是否关联计划</th>
					<th style="width: 120px;">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${not empty page}">
			 		<c:forEach  varStatus="idx" var="course" items="${page.records}">
						<tr>
							<td>
								<input id="courseId" name="checkbox" type="checkbox" class="ace" value="${course.id }">
							</td>
							<td><a  href="${ctx }/manage/selectCourseById?id=${course.id }" class="clickable">${course.name }</a></td>
							<td>
								<c:if test="${course.isOnline==0 }">线下</c:if>
								<c:if test="${course.isOnline==1 }">线上</c:if>
							</td>
							<td><fmt:formatDate value="${course.gmtCreate }" pattern="yyyy-MM-dd"/></td>
							<td><c:if test="${course.isShare==0 }">否</c:if>
								<c:if test="${course.isShare==1 }">是</c:if>
							</td>
							<td><c:if test="${course.isRelatePlan==0 }">否</c:if>
								<c:if test="${course.isRelatePlan==1 }">是</c:if>
							</td>
							<td>
							    <c:if test ='${search_pubType eq 1}'>
									<a class="color-detail" href="${ctx}/manage/updateCourse?id=${course.id }"  title="修改">修改</a>
									<a class="color-detail" href="#"  onclick="shareCourse('${course.name }','${course.id }')" title="分享">分享</a>
								</c:if>
								<c:if test ='${search_pubType eq 2}'>
								    <a class="color-detail" href="${ctx }/manage/selectCourseById?id=${course.id }" title="查看">查看</a>
								</c:if>
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

/* $(function(){
	var keyWord=$("#search_keyWord").val();
	var tagId=$("#search_tagId").val();
	var uploadTimeBegin=$("#search_uploadTimeBegin").val();
	var uploadTimeEnd=$("#search_uploadTimeEnd").val();
	 page("${ctx}/manage/privateCourseListByPage?keyWord="+keyWord+"&tagId="+tagId+"&uploadTimeBegin="+uploadTimeBegin+"&uploadTimeEnd="+uploadTimeEnd,'${page.pageSize}','${page.pageNo}','${page.pageTotal}','pageDiv');	
	}); */

	$("#checkbox").click(function () {
        if($(this).is(':checked')){
            $("input[name='checkbox']").prop("checked",true);
        }else{
            $("input[name='checkbox']").prop("checked",false);
        }
    });

	$(function(){
		var keyWord = '${search_keyWord}';
		//var tagId ='${search_tagId}';
		var uploadTimeBegin = '${search_uploadTimeBegin}';
		var uploadTimeEnd = '${search_uploadTimeEnd}';
		var isOnline = '${search_isOnline}';
		pageAjax("${ctx}/manage/privateCourse/listByPageAjax?keyWord="+keyWord+"&uploadTimeBegin="+uploadTimeBegin+"&uploadTimeEnd="+uploadTimeEnd+"&isOnline="+isOnline,'${page.pageSize}','${page.pageNo}','${page.pageTotal}','pageDiv');
	});
	
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
	    //var tagId ='${search_tagId}';
	    var uploadTimeBegin = '${search_uploadTimeBegin}';
	    var uploadTimeEnd = '${search_uploadTimeEnd}';
	    var isOnline = '${search_isOnline}';
		$.get(url,function(returnData){
			if(returnData.code == '1'){
				var html = "";
				$.each(returnData.data.records,function(i,row){
				html+='<tr><td>'+
					  '<input type="checkbox" class="ace" value="'+row.id+'"></td>'+
					  '<td>'+row.name+'</td>'+
					  '<td>'+codeArray['isOnline'+row.isOnline]+'</td>'+
					  '<td>'+new Date(row.gmtCreate).Format("yyyy-MM-dd")+'</td>'+
					  '<td>'+codeArray['isShare'+row.isShare]+'</td>'+
					  '<td>'+codeArray['isRelatePlan'+row.isRelatePlan]+'</td>'+
					  '<td><a class="color-detail" href="${ctx}/manage/updateCourse?id='+row.id+'"  title="修改">修改</a>'+
					  '<a class="color-detail" href="#"  onclick="shareCourse(\''+row.name+'\',\''+row.id+'\')" title="分享">分享</a></tr>';
				})
				$(".table tbody").html(html);
				pageAjax("${ctx}/manage/privateCourse/listByPageAjax?keyWord="+keyWord+"&uploadTimeBegin="+uploadTimeBegin+"&uploadTimeEnd="+uploadTimeEnd+"&isOnline="+isOnline,returnData.data.pageSize,returnData.data.pageNo,returnData.data.pageTotal,'pageDiv');
			}
		})
	}
	
    function deleteBatchCourse() {
	    var obj = document.getElementsByName("checkbox");
	    var check_val = [];
	    for(k in obj){
	        if(obj[k].checked)
	            check_val.push(obj[k].value);
	    }
	    var jsonStr = {};
	    for(var i=0;i<check_val.length;i++){
	    	jsonStr[i]=check_val[i];
	    }
	    var jsonstr=JSON.stringify(jsonStr);
		/* alert(jsonstr); */
	    if (jsonstr == "{}" || jsonstr == {}) {
			layer.msg('请先选择要删除的课程');
			return;
		}
	    layer.confirm('确定删除?', {
	        icon: 3,
	        btn: ['确定', '取消'] //按钮
	      },function () {
			    $.ajax({
			    	  type: 'post',
			    	  url: "${ctx }/manage/deleteBatchCourse",
			    	  dataType: "json",
			    	  data: {"jsonstr" : jsonstr},
			    	  success: function (data) {//请求失败处理函数  
			    	  		if(data.data==null || data.data==""){
			    	  			layer.alert("删除成功", {icon: 1}, function(){
			    	  				window.location.href="${ctx}/manage/privateCourseListByPage";
								});
			    	  		}else{
			    	  			layer.alert("该课程已加入计划，不能删除！课程编号为："+data.data, {icon: 1}, function(){
			    	  				window.location.href="${ctx}/manage/privateCourseListByPage";
								});
			    	  		}
				        },
				      error:function (data) {//请求失败处理函数  
				    	  layer.msg("删除失败");
				        },
			    	});
			  });
	}
	
	function shareCourse(name,id) {
		 var courseIds = new Array();
		 var courseNames = new Array(); 
		 courseIds[0] =id;
     	courseNames[0] =name;
     	location.href="${ctx}/manage/privateShareCourses?courseIds="+courseIds+"&courseNames="+courseNames;
	}
	function shareCourses() {
		var jsonStr =[];
		 var courseIds = new Array();
		 var courseNames = new Array();
		 var groupCheckbox=$("input[name='checkbox']:checked");
	     for(i=0;i<groupCheckbox.length;i++){
	        if(groupCheckbox.eq(i).is(":checked")){
	        	var name=groupCheckbox.eq(i).parent().siblings().find(".clickable").text();
	        	//alert(name);
        	courseIds[i] =groupCheckbox[i].value;
        	courseNames[i] =name;
	        }
	    }
		if (courseIds.length <= 0) {
			 layer.alert("请先选择要分享的资料");
		} else {
			 location.href="${ctx}/manage/privateShareCourses?courseIds="+courseIds+"&courseNames="+courseNames;
		}
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
	
	function selectCourse(type) {
        location.href = "${ctx}/manage/privateCourseListByPage?pubType="+type;
	}
</script>
</body>
</html>