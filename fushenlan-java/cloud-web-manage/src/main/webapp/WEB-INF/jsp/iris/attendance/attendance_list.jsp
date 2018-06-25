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
<script type="text/javascript" src="${ctx}/resources/js/common/page.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/style.css">
<style  type="text/css">
.addCalder{
    color:yellow;
}
.calMonth tr td{
width:320px;
cursor:pointer;
}
.calMonth tr td.addCalder button{
	width: 55px;
	height: 55px;
	border-radius: 100%;
	background-color: red;
	color: #fff;
}
.filter-box .btn .btn-search{
    min-width: 100px;
    margin-right: 70px;
}
.btn-search {
    position: absolute;
    right: 800px;
    top: 28px;
}
.filter-box {
padding: 10px 5px 0px;
}
</style>
  <table class="calMonth">
  <tr>
  <td id="1"><button>1</button></td>
  <td id="2"><button>2</button></td>
  <td id="3"><button>3</button></td>
  <td id="4"><button>4</button></td>
  <td id="5"><button>5</button></td>
  <td id="6"><button>6</button></td>
  <td id="7"><button>7</button></td>
  <td id="8"><button>8</button></td>
  <td id="9"><button>9</button></td>
  <td id="10"><button>10</button></td>
  <td id="11"><button>11</button></td>
  <td id="12"><button>12</button></td>
  </tr>
  </table>
  <h1>基础考勤规则</h1>
  <div class="filter-box">
      <ul class="ui-form grid-row">
         <li class="col-md-3"><strong>关键字搜索:</strong><input id="keyWord1" value ="${keyWord1 }"class="ipt-text" type="text" placeholder="规则名称"></li>
         <li class="col-md-3" style="text-align: right">
            <button type="button" class="btn btn-submit btn-radius  btn-add" onclick="findBase()">查询</button>
            <button type="button" class="btn btn-submit btn-radius  btn-add" onclick="addBase()">新增</button>
         </li>
      </ul>
  </div>
  <table class="table1 table table-agents">
			<thead>
				<tr>
					<th style="width: 200px;">规则名称</th>
					<th style="width: 130px;">规则说明</th>
					<th style="width: 180px;">操作人</th>
					<th style="width: 180px;">操作时间</th>
					<th style="width: 140px;">操作</th>
				</tr>
			</thead>
			<tbody>
			 	<c:if test="${not empty resuleMap}">
			 		<c:forEach  varStatus="idx" var="base" items="${resuleMap.baseAttendanceRules.records}">
						<tr>
							<td>${base.ruleName}</td>
							<td>${base.description}</td>
							<td>${base.modifyUserName}</td>
							<td><fmt:formatDate value="${base.gmtModified }" pattern="yyyy-MM-dd"/></td>
							<td>
								<a class="color-detail" href="${ctx }/manage/attendance/toAddAttendanceRoles?id=${base.id }&operatorType=0" title="查看">查看</a>
								<a class="color-detail" href="${ctx }/manage/attendance/toAddAttendanceRoles?id=${base.id }&operatorType=1" title="修改">修改</a>
							</td>
						</tr>
			 		</c:forEach>
			 	</c:if>
			</tbody>
		</table>
		<div class="table-paging clearfix" id="pageDiv">
		</div>
 <h1>活动考勤规则</h1>
  <div class="filter-box">
      <ul class="ui-form grid-row">
         <li class="col-md-3"><strong>关键字搜索:</strong><input id="keyWord2" value ="${keyWord2 }"class="ipt-text" type="text" placeholder="规则名称"></li>
         <li class="col-md-3" style="text-align: right">
            <button type="button" class="btn btn-submit btn-radius  btn-add" onclick='findActivity();'>查询</button>
            <button type="button" class="btn btn-submit btn-radius  btn-add" onclick="addActivity()">新增</button>
         </li>
      </ul>
  </div>
  <table class="table2 table table-agents">
			<thead>
				<tr>
					<th style="width: 200px;">规则名称</th>
					<th style="width: 130px;">规则说明</th>
					<th style="width: 180px;">操作人</th>
					<th style="width: 180px;">操作时间</th>
					<th style="width: 140px;">操作</th>
				</tr>
			</thead>
			<tbody>
			 	<c:if test="${not empty resuleMap}">
			 		<c:forEach  varStatus="idx" var="activity" items="${resuleMap.activityAttendanceRules.records}">
						<tr>
							<td>${activity.ruleName}</td>
							<td>${activity.description}</td>
							<td>${activity.modifyUserName}</td>
							<td><fmt:formatDate value="${activity.gmtModified }" pattern="yyyy-MM-dd"/></td>
							<td>
								<a class="color-detail" href="${ctx }/manage/attendance/toAddAttendanceRoles?id=${activity.id }&operatorType=0" title="查看">查看</a>
								<a class="color-detail" href="${ctx }/manage/attendance/toAddAttendanceRoles?id=${activity.id }&operatorType=1" title="修改">修改</a>
							</td>
						</tr>
			 		</c:forEach>
			 	</c:if>
			</tbody>
		</table>
		<div class="table-paging clearfix" id="pageDiv2">
		</div>
<script type="text/javascript">
//定义全局变量月份
var month;
$(function(){
	//初始化页面
	month = '${month}';
	$("#"+month).addClass("addCalder");
	pageAjax("${ctx}/manage/attendance/selectBaseAttendanceRulesByParams?month="+month,'${resuleMap.baseAttendanceRules.pageSize}','${resuleMap.baseAttendanceRules.pageNo}','${resuleMap.baseAttendanceRules.pageTotal}','pageDiv');
	pageAjax("${ctx}/manage/attendance/selectActivityAttendanceRulesByParams?month="+month,'${resuleMap.activityAttendanceRules.pageSize}','${resuleMap.activityAttendanceRules.pageNo}','${resuleMap.activityAttendanceRules.pageTotal}','pageDiv2');
		
	})
	//点击月份
	$('.calMonth tr td').click(function(){
		month=$(this).find("button")[0].textContent;
		console.log(month);
		window.location.href="${ctx}/manage/attendance/selectAttendanceRulesByParams?month="+month;
		})
	//检索基础考勤
	function findBase(){
		var rulesType=1;
		var keyWord=$("#keyWord1").val();
	 	$.ajax({
		  type: 'get',
		  url: "${ctx }/manage/attendance/selectBaseAttendanceRulesByParams",
		  dataType: "json",
		  data: {"month" : month,"rulesType":rulesType,"keyWord":keyWord},
		  success: function (data) {
			  if(data.records!=null){
					var html = "";
					$.each(data.records,function(i,row){
					html+='<tr>'+
					  '<td>'+row.ruleName+'</td>'+
					  '<td>'+row.description+'</td>'
					  if(row.modifyUserName==null){
					   html+= '<td></td>' 
					  }else{
					   html+= '<td>'+row.modifyUserName+'</td>'  
					  }
					  html+='<td>'+new Date(row.gmtModified).Format("yyyy-MM-dd")+'</td>'+
					  '<td><a class="color-detail" href="${ctx }/manage/attendance/toAddAttendanceRoles?id='+row.id+'&operatorType=0" title="查看">查看</a> <a class="color-detail" href="${ctx }/manage/attendance/toAddAttendanceRoles?id='+row.id+'&operatorType=1" title="修改">修改</a>'+
					  '</tr>';
					
					})
					$(".table1 tbody").html(html);
					pageAjax("${ctx}/manage/attendance/selectBaseAttendanceRulesByParams?keyWord="+keyWord+"&month="+month,data.pageSize,data.pageNo,data.pageTotal,'pageDiv');
				}
	      },
	    error:function (data) {//请求失败处理函数  
	  	  layer.msg("服务器错误");
	      },
		});
	}
    //检索活动考勤
	function findActivity(){
		var rulesType=2;
		var keyWord=$("#keyWord2").val();
	 	$.ajax({
		  type: 'get',
		  url: "${ctx }/manage/attendance/selectActivityAttendanceRulesByParams",
		  dataType: "json",
		  data: {"month":month,"rulesType":rulesType,"keyWord":keyWord},
		  success: function (data) {
			  if(data.records!=null){
					var html = "";
					$.each(data.records,function(i,row){
					html+='<tr>'+
						  '<td>'+row.ruleName+'</td>'+
						  '<td>'+row.description+'</td>'
						  if(row.modifyUserName==null){
						   html+= '<td></td>' 
						  }else{
						   html+= '<td>'+row.modifyUserName+'</td>'  
						  }
						  html+='<td>'+new Date(row.gmtModified).Format("yyyy-MM-dd")+'</td>'+
						  '<td><a class="color-detail" href="${ctx }/manage/attendance/toAddAttendanceRoles?id='+row.id+'&operatorType=0" title="查看">查看</a> <a class="color-detail" href="${ctx }/manage/attendance/toAddAttendanceRoles?id='+row.id+'&operatorType=0" title="修改">修改</a>'+
						  '</tr>';
					
					})
					$(".table2 tbody").html(html);
					pageAjax("${ctx}/manage/attendance/selectActivityAttendanceRulesByParams?keyWord="+keyWord+"&month="+month,data.pageSize,data.pageNo,data.pageTotal,'pageDiv2');
				}
	      },
	    error:function (data) {//请求失败处理函数  
	  	  layer.msg("服务器错误");
	      },
		});
	}
	function addBase(){
		window.location.href="${ctx}/manage/attendance/toAddAttendanceRoles?rulesType="+1;
	}
	function addActivity(){
		window.location.href="${ctx}/manage/attendance/toAddAttendanceRoles?rulesType="+2;
	}
	
	//ajax 分页 拼接数据
	function pageNext(url){
		// 查询字段取页面加载时 model传入的值  防止分页执行查询
		$.get(url,function(returnData){
			if(returnData.records !=null){
				var html = "";
				var rulesType;
				$.each(returnData.records,function(i,row){
					rulesType=row.rulesType;
					html+='<tr>'+
					  '<td>'+row.ruleName+'</td>'+
					  '<td>'+row.description+'</td>'
					  if(row.modifyUserName==null){
					   html+= '<td></td>' 
					  }else{
					   html+= '<td>'+row.modifyUserName+'</td>'  
					  }
					  html+='<td>'+new Date(row.gmtModified).Format("yyyy-MM-dd")+'</td>'+
					  '<td><a class="color-detail" href="${ctx }/manage/attendance/toAddAttendanceRoles?id='+row.id+'&operatorType=0" title="查看">查看</a> <a class="color-detail" href="${ctx }/manage/attendance/toAddAttendanceRoles?id='+row.id+'&operatorType=1" title="修改">修改</a>'+
					  '</tr>';
				})
				//var month=$("#monthDis").val();
				var keyWord1 =$("#keyWord1").val();
				var keyWord2 = $("#keyWord2").val();
				if(rulesType=="1"){
					$(".table1 tbody").html(html);
					pageAjax("${ctx}/manage/attendance/selectBaseAttendanceRulesByParams?keyWord="+keyWord1+"&month="+month,returnData.pageSize,returnData.pageNo,returnData.pageTotal,'pageDiv');
				}else{
					$(".table2 tbody").html(html);
					pageAjax("${ctx}/manage/attendance/selectActivityAttendanceRulesByParams?keyWord="+keyWord2+"&month="+month,returnData.pageSize,returnData.pageNo,returnData.pageTotal,'pageDiv2');
				}
			}
		})
	}
	
</script>
</body>
</html>