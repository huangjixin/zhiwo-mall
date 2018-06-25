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
</head>
<body>
<script type="text/javascript"	src="${ctx}/resources/libs/layer/layer.js"></script>
	<div class="filter-box">
      <ul class="ui-form grid-row">
         <li class="col-md-3"><strong>关键字搜索:</strong><input id="keyWord" value ="${keyWord }"class="ipt-text" type="text" placeholder="工号/姓名"></li>
         <li class="col-md-6"><strong style="margin-left: -114px;font-size: 16;">活动日期：</strong>
		     <input id="startDate" name="startDate" value="${startDate }" type="text" placeholder="开始时间" class="Wdate" style="max-width: 26%;" onfocus="WdatePicker({onpicked: function(){jQuery(this).trigger('change');},oncleared: function(){jQuery(this).trigger('change');}})">&nbsp;—&nbsp;
			 <input id="endDate" name="endDate" value="${endDate }" type="text" placeholder="结束时间" class="Wdate" style="max-width: 26%;" onfocus="WdatePicker({onpicked: function(){jQuery(this).trigger('change');},oncleared: function(){jQuery(this).trigger('change');}})">
		 </li> 
         <li class="col-md-1" style="text-align: right">
            <button type="button" class="btn btn-submit btn-radius  btn-search" onclick="find()">查询</button>
         </li>
      </ul>
    </div>
		<table class="table table-agents">
			<thead>
				<tr>
					<th>考勤人</th>
					<th>工号</th>
					<th>分公司</th>
					<th>职务</th>
					<th>考勤日期</th>
					<th>考勤时间</th>
				</tr>
			</thead>
			<tbody>
			<c:if test="${not empty page}">
	          <c:forEach  varStatus="idx" var="result" items="${page.records}">
				<tr>
					<td>${result.name }</td>
					<td>${result.agentCode }</td>
					<td>${result.companyName }</td>
					<td>${result.levelName }</td>
					<td><fmt:formatDate value="${result.checkDate }" type="date"/></td>
					<td><fmt:formatDate value="${result.checkTime }" type="time" /></td>
				</tr>
				</c:forEach>
	          </c:if>
			</tbody>
		</table>
		<div class="table-paging clearfix" id="pageDiv">
		</div>

<script type="text/javascript" src="${ctx}/resources/js/common/page.js"></script> 
<script type="text/javascript" src="${ctx}/resources/libs/datepicker/WdatePicker.js"></script>
<script>

$(function(){
	var keyWord ='${keyWord}';
	var startDate ='${startDate}';
	var endDate = '${endDate}';
	pageAjax("${ctx}/manage/attendance/selectAttendanceResultByPageAjax?keyWord="+keyWord+"&startDate="+startDate+"&endDate="+endDate,'${page.pageSize}','${page.pageNo}','${page.pageTotal}','pageDiv');
});

//ajax 分页 拼接数据
function pageNext(url){
	// 查询字段取页面加载时 model传入的值  防止分页执行查询
	var keyWord = $("#keyWord").val();
	var startDate= $("#startDate").val();
	var endDate= $("#endDate").val();
	$.get(url,function(returnData){
		if(returnData.code == '1'){
			var html = "";
			$.each(returnData.data.records,function(i,row){
				html+= '<tr>'
					if(row.name==null){
					   html+= '<td></td>' 
					  }else{
					   html+= '<td>'+row.name+'</td>'  
					  } 
				   html+='<td>'+row.agentCode+'</td>'
				   if(row.companyName==null){
					   html+= '<td></td>' 
					  }else{
					   html+= '<td>'+row.companyName+'</td>'  
					  }
				   if(row.levelName==null){
					   html+= '<td></td>' 
					  }else{
					   html+= '<td>'+row.levelName+'</td>'  
					  }
			       html+='<td>'+new Date(row.checkDate).Format("yyyy-MM-dd")+'</td>'
				   if(row.checkTime==null){
					   html+= '<td></td>' 
					  }else{
					   html+= '<td>'+new Date(row.checkTime).Format("hh:mm:ss")+'</td>' 
					  }
					html+='</tr>'
			})
			$(".table tbody").html(html);
			pageAjax("${ctx}/manage/attendance/selectAttendanceResultByPageAjax?keyWord="+keyWord+"&startDate="+startDate+"&endDate="+endDate,returnData.data.pageSize,returnData.data.pageNo,returnData.data.pageTotal,'pageDiv');
		}
	})
}

 //检索
function find(){
	 var keyWord = $("#keyWord").val();
	   var startDate= $("#startDate").val();
	   var endDate= $("#endDate").val();
 	$.ajax({
	  type: 'get',
	  url: "${ctx }/manage/attendance/selectAttendanceResultByPageAjax",
	  dataType: "json",
	  data: {"keyWord":keyWord,"startDate":startDate,"endDate":endDate},
	  success: function (data) {
		  if(data.code == '1'){
				var html = "";
				$.each(data.data.records,function(i,row){
				html+= '<tr>'
				if(row.name==null){
				   html+= '<td></td>' 
				  }else{
				   html+= '<td>'+row.name+'</td>'  
				  } 
			   html+='<td>'+row.agentCode+'</td>'
			   if(row.companyName==null){
				   html+= '<td></td>' 
				  }else{
				   html+= '<td>'+row.companyName+'</td>'  
				  }
			   if(row.levelName==null){
				   html+= '<td></td>' 
				  }else{
				   html+= '<td>'+row.levelName+'</td>'  
				  }
		       html+='<td>'+new Date(row.checkDate).Format("yyyy-MM-dd")+'</td>'
			   if(row.checkTime==null){
				   html+= '<td></td>' 
				  }else{
				   html+= '<td>'+new Date(row.checkTime).Format("hh:mm:ss")+'</td>'  
				  }
				html+='</tr>'
				})
				$(".table tbody").html(html);
				pageAjax("${ctx}/manage/attendance/selectAttendanceResultByPageAjax?keyWord="+keyWord+"&startDate="+startDate+"&endDate="+endDate,data.data.pageSize,data.data.pageNo,data.data.pageTotal,'pageDiv');
			}
		  },
    error:function (data) {//请求失败处理函数  
  	  layer.msg("服务器错误");
      },
	});
}
</script>
</body>
</html>
