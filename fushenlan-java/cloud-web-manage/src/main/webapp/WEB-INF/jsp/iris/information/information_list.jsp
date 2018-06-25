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
				<li class="col-md-4"><strong>标题名称</strong><input name="title" id="title" value ="${title }"class="ipt-text" type="text" style="margin-left: 0px; placeholder=""></li>
				<li class="col-md-3">
				    <strong>信息状态</strong>
					<select name="status" id="status">
					    <option value="">全部</option>
						<option value="0" <c:if test="${status eq '0'}">selected</c:if>>未发布</option>
						<option value="1" <c:if test="${status eq '1'}">selected</c:if>>已撤回</option>
						<option value="2" <c:if test="${status eq '2'}">selected</c:if>>已发布</option>
					</select>
				</li>
				<li class="col-md-3">
					<strong>信息类型</strong>
					<select name="type" id="type">
					    <option value="">全部</option>
						<option value="0" <c:if test="${type eq '0'}">selected</c:if>>公司动态</option>
						<option value="1" <c:if test="${type eq '1'}">selected</c:if>>热门活动</option>
						<option value="2" <c:if test="${type eq '2'}">selected</c:if>>政策公告</option>
						<option value="3" <c:if test="${type eq '3'}">selected</c:if>>产品上线</option>
					</select>
				</li>
				<li class="col-md-12" style="text-align: right">
					<button type="button" class="btn btn-submit btn-radius  btn-search" onclick="findtr()">查询</button>
				</li>
				<li class="col-md-6"><strong style="margin-left: -114px;font-size: 16;">选择月份：</strong>
				     <input id="startDateMonth" name="startDateMonth" value="${satrtTime }" type="text" placeholder="开始时间" class="Wdate" style="max-width: 26%;" onfocus="WdatePicker({onpicked: function(){jQuery(this).trigger('change');},oncleared: function(){jQuery(this).trigger('change');}})">&nbsp;—&nbsp;
					 <input id="endDateMonth" name="uploadTimeEnd" value="${endTime }" type="text" placeholder="结束时间" class="Wdate" style="max-width: 26%;" onfocus="WdatePicker({onpicked: function(){jQuery(this).trigger('change');},oncleared: function(){jQuery(this).trigger('change');}})">
				</li> 
			</ul>
		</div>
		<div class="tab-btn">
		<a class="btn btn-success btn-radius" href="javascript:;" onclick="addCCo()" title="新增"><i class="icon-jia"></i>新增</a>
		</div>
		<table class="table table-agents">
			<thead>
				<tr>
					<th>信息标题</th>
					<th>信息类型</th>
					<th>信息状态</th>
					<th>发布日期</th>
					<th>阅读量</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
			<c:if test="${not empty page}">
	          <c:forEach  varStatus="idx" var="com" items="${page.records}">
				<tr>
					<td>${com.title }</td>
					<td>
					   <c:if test="${com.status==0}">未发布</c:if>
	                   <c:if test="${com.status==1}">已撤回</c:if>
	                   <c:if test="${com.status==2}">已发布</c:if>
					</td>
					<td>
	                   <c:if test="${com.type==0}">公司动态</c:if>
	                   <c:if test="${com.type==1}">热门活动</c:if>
	                   <c:if test="${com.type==2}">政策公告</c:if>
	                   <c:if test="${com.type==3}">产品上线</c:if>
					</td>
					<td><fmt:formatDate value="${com.scheduleTime}" pattern="YYYY-MM-dd"/></td>
					<td>${com.viewCount }</td>
					<td>
						<c:if test="${com.status ==2}"><button class="color-detail"onclick="edit('${com.id}')" title="查看">查看</button></c:if>
						<c:if test="${com.status !=2}"><button class="color-detail"onclick="edit('${com.id}')" title="编辑">编辑</button></c:if>
						<c:if test="${com.status ==0 || com.status==1}"><button class="color-detail"onclick="release('${com.id}','2')" title="发布">发布</button></c:if>
						<c:if test="${com.status ==2}"><button class="color-detail"onclick="release('${com.id}','1')" title="撤回">撤回</button></c:if>
						<button class="color-detail"onclick="deleteNew('${com.id}','${com.status}')" title="删除">删除</button>
					</td>
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
	var title ='${title}';
	var status ='${status}';
	var type = '${type}';
	var satrtTime = '${satrtTime}';
	var endTime = '${endTime}';
	
	pageAjax("${ctx}/manage/news/listByPageAjax?title="+title+"&status="+status+"&type="+type+"&satrtTime="+satrtTime+"&endTime="+endTime,'${page.pageSize}','${page.pageNo}','${page.pageTotal}','pageDiv');
});

var codeArray ={};
codeArray['0'] = '未发布';
codeArray['1'] = '已撤回';
codeArray['2'] = '已发布';
codeArray['type0'] = '公司动态';
codeArray['type1'] = '热门活动';
codeArray['type2'] = '政策公告';
codeArray['type3'] = '产品上线';

//ajax 分页 拼接数据
function pageNext(url){
	// 查询字段取页面加载时 model传入的值  防止分页执行查询
	var title ='${title}';
	var status ='${status}';
	var type = '${type}';
	var satrtTime = '${satrtTime}';
	var endTime = '${endTime}';
	
	$.get(url,function(returnData){
		if(returnData.code == '1'){
			var html = "";
			$.each(returnData.data.records,function(i,row){
		    row.viewCount = row.viewCount==null?'':row.viewCount;
			html+='<tr>'+
				  '<td>'+row.title+'</td>'+
				  '<td>'+codeArray[row.status]+'</td>'+
				  '<td>'+codeArray['type'+row.type]+'</td>';
				  if(row.scheduleTime==null || row.scheduleTime==""){
					  html+='<td></td>';
				  }else{
					  html+='<td>'+new Date(row.scheduleTime).Format("yyyy-MM-dd")+'</td>';
				  }
				  html+='<td>'+row.viewCount+'</td>';
				  html+='<td>';
				  if(row.status==2){
					  html+='<button class="color-detail"onclick="edit(\''+row.id+'\')" title="查看">查看</button>&nbsp;&nbsp;';
				  }else if(row.status !=2){
					  html+='<button class="color-detail"onclick="edit(\''+row.id+'\')" title="编辑">编辑</button>&nbsp;&nbsp;';
				  }
				  if(row.status ==0 || row.status==1){
					  html+='<button class="color-detail"onclick="release(\''+row.id+'\',2)" title="发布">发布</button>&nbsp;&nbsp;';
				  }else if(row.status ==2){
					  html+='<button class="color-detail"onclick="release(\''+row.id+'\',1)" title="撤回">撤回</button>&nbsp;&nbsp;';
				  }
				  html+='<button class="color-detail"onclick="deleteNew(\''+row.id+'\',\''+row.status+'\')" title="删除">删除</button></td>&nbsp;&nbsp;';
			
			})
			$(".table tbody").html(html);
			pageAjax("${ctx}/manage/news/listByPageAjax?title="+title+"&status="+status+"&type="+type+"&satrtTime="+satrtTime+"&endTime="+endTime,returnData.data.pageSize,returnData.data.pageNo,returnData.data.pageTotal,'pageDiv');
		}
	})
}

   function findtr(){
	   var title = $("#title").val();
	   var status = $("#status").val();
	   var type = $("#type").val();
	   var satrtTime= $("#startDateMonth").val();
	   var endTime= $("#endDateMonth").val();
	   location.href="${ctx}/manage/news/newsList?title="+title+"&status="+status+"&type="+type+"&satrtTime="+satrtTime+"&endTime="+endTime;
   }

   function edit(id){
	   location.href="${ctx}/manage/news/edit?id="+id;
   }
   
   function release(id,status){
	   
	   layer.confirm('你确定要设置选中的记录吗？', {
			icon: 3,
		  	btn: ['确定', '取消'] //按钮
		}, function(){
			$.ajax({
				type : "post",
				data : {"id" : id, "status" : status},
				url : "${ctx}/manage/news/release",
				dateType: "json",
				success : function(data) {
					if (data.code == '1') {
						layer.alert("设置成功", {icon: 1}, function(){
							window.location="${ctx}/manage/news/newsList";
						});
					}else {
						layer.alert("设置失败", {icon: 5});
					}
				}
			});
		});
   }
   
   //刪除
   function deleteNew(id,status){
	   if(status=='2'){
		   layer.msg("已发布状态不能删除");
		   return;
	   };
	   layer.confirm('你确定要删除选中的记录吗？', {
			icon: 3,
		  	btn: ['确定', '取消'] //按钮
		}, function(){
			$.ajax({
				type : "post",
				data : {"id" : id},
				url : "${ctx}/manage/news/deleteNew",
				dateType: "json",
				success : function(data) {
					if (data.code == '1') {
						layer.alert("删除成功", {icon: 1}, function(){
							window.location="${ctx}/manage/news/newsList";
						});
					}else {
						layer.alert("删除失败", {icon: 5});
					}
				}
			});
		});
	   
   }
   
   
   function addCCo(){
	   location.href="${ctx}/manage/news/edit";
   }

</script>
</body>
</html>
