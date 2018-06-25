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
				<li class="col-md-4"><strong>任务名称</strong><input name="name" id="name" value ="${name }"class="ipt-text" type="text" style="margin-left: 0px; placeholder=""></li>
				<li class="col-md-3">
				    <strong>任务分类</strong>
					<select name="tagId" id="tagId">
					    <option value="">全部</option>
						<option value="1" <c:if test="${tagId eq '1'}">selected</c:if>>初审面试</option>
						<option value="2" <c:if test="${tagId eq '2'}">selected</c:if>>甄选面试</option>
						<option value="3" <c:if test="${tagId eq '3'}">selected</c:if>>决定面试</option>
					</select>
				</li>
				<li class="col-md-3">
					<strong>任务状态</strong>
					<select name="state" id="state">
					    <option value="">全部</option>
						<option value="1" <c:if test="${state eq '0'}">selected</c:if>>已停用</option>
						<option value="2" <c:if test="${state eq '1'}">selected</c:if>>已启动</option>
					</select>
				</li>
				<li class="col-md-12" style="text-align: right">
					<button type="button" class="btn btn-submit btn-radius  btn-search" onclick="findtr()">查询</button>
				</li>
			</ul>
		</div>
		<div class="tab-btn">
		<a class="btn btn-success btn-radius" href="javascript:;" onclick="addCCo()" title="新增"><i class="icon-jia"></i>新增</a>
		<button type="button" onclick="batchenable()" class="btn btn-success btn-radius btn-list-stop"><i class="icon-tingzhishangbao ong"></i>批量停用</button>
		<button type="button" onclick="batchDisable()" class="btn btn-success btn-radius btn-list-start"><i class="icon-qiyong green"></i>批量启用</button>
		<button type="button" onclick="batchDelete()" class="btn btn-success btn-radius btn-list-delete"><i class="icon-close red"></i>批量删除</button>
		</div>
		<table class="table table-agents">
			<thead>
				<tr>
					<th>
						<label class="pos-rel">
							<input type="checkbox" class="ace" id="selectAll">
						</label>
					</th>
					<th>任务代码</th>
					<th>任务名称</th>
					<th>任务分类</th>
					<th>任务状态</th>
					<th>参与人数</th>
					<th>开始日期</th>
					<th>结束日期</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
			<c:if test="${not empty page}">
	          <c:forEach  varStatus="idx" var="com" items="${page.records}">
				<tr>
					<td>
						<label class="pos-rel">
							<input type="checkbox" class="ace" value="${com.id}">
						</label>
					</td>
					<td>${com.code }</td>
					<td>${com.name }</td>
					<td>
					   <c:if test="${com.tagId==1}">初审面试</c:if>
	                   <c:if test="${com.tagId==2}">甄选面试</c:if>
	                   <c:if test="${com.tagId==3}">决定面试</c:if>
					</td>
					<td>
	                   <c:if test="${com.state==0}">已停用</c:if>
	                   <c:if test="${com.state==1}">已启动</c:if>
					</td>
					<td>${com.associateId }</td>
					<td><fmt:formatDate value="${com.startDate}" pattern="YYYY-MM-dd"/></td>
					<td><fmt:formatDate value="${com.endDate}" pattern="YYYY-MM-dd"/></td>
					<td>
					    <c:if test="${com.state==0}"><button class="color-detail"onclick="edit('${com.id}')" title="修改">修改</button></c:if>
					</td>
				</tr>
				</c:forEach>
	          </c:if>
			</tbody>
		</table>
		<div class="table-paging clearfix" id="pageDiv">
		</div>

<script type="text/javascript" src="${ctx}/resources/js/common/page.js"></script> 
<script>

$(function(){
	var name = '${name}';
	var tagId ='${tagId}';
	var state = '${state}';
	pageAjax("${ctx}/manage/elearning/listByPageAjax?name="+name+"&tagId="+tagId+"&state="+state,'${page.pageSize}','${page.pageNo}','${page.pageTotal}','pageDiv');
});

var codeArray ={};
codeArray['1'] = '初审面试';
codeArray['2'] = '甄选面试';
codeArray['3'] = '决定面试';
codeArray['state1'] = '未开始';
codeArray['state2'] = '进行中';
codeArray['state3'] = '已结束';
codeArray['state4'] = '已停用';

//ajax 分页 拼接数据
function pageNext(url){
	// 查询字段取页面加载时 model传入的值  防止分页执行查询
	var name = '${name}';
	var tagId ='${tagId}';
	var state = '${state}';
	$.get(url,function(returnData){
		if(returnData.code == '1'){
			var html = "";
			$.each(returnData.data.records,function(i,row){
				
			row.code = row.code==null?'':row.code; //判断null字段
			row.name = row.name==null?'':row.name;
			html+='<tr><td><label class="pos-rel">'+
				  '<input type="checkbox" class="ace" value="'+row.id+'"></label></td>'+
				  '<td>'+row.code+'</td>'+
				  '<td>'+row.name+'</td>'+
				  '<td>'+codeArray[row.tagId]+'</td>'+
				  '<td>'+codeArray['state'+row.state]+'</td>'+
				  '<td>'+row.associateId+'</td>'+
				  '<td>'+new Date(row.startDate).Format("yyyy-MM-dd")+'</td>'+
				  '<td>'+new Date(row.endDate).Format("yyyy-MM-dd")+'</td>'+
				  '<td><button class="color-detail" onclick="edit(\''+row.id+'\')" title="修改">修改</button></td></tr>';
			})
			$(".table tbody").html(html);
			pageAjax("${ctx}/manage/elearning/listByPageAjax?name="+name+"&tagId="+tagId+"&state="+state,returnData.data.pageSize,returnData.data.pageNo,returnData.data.pageTotal,'pageDiv');
		}
	})
}

   
   $("#selectAll").click(function(){//给全选按钮加上点击事件
    var xz = $(this).prop("checked");//判断全选按钮的选中状态
    var ck = $(".ace").prop("checked",xz);  //让class名为qx的选项的选中状态和全选按钮的选中状态一致。  
    })
  
   function findtr(){
	   var state = $("#state").val();
	   var tagId = $("#tagId").val();
	   var name = $("#name").val();
	   location.href="${ctx}/manage/elearning/compulsoryCplanList?state="+state+"&tagId="+tagId+"&name="+name;
   }

   function edit(id){
	   location.href="${ctx}/manage/elearning/edit?id="+id;
   }

   function addCCo(){
	   location.href="${ctx}/manage/elearning/edit";
   }

   function batchenable(){
	   var str ="";
		$(".pos-rel :input[type='checkbox']").each(function(i){
			if(this.checked==true){
				var vs  = this.value;
				str = str+vs+",";
			}
		});
		if(str.length==0){
			layer.alert('你还没有选择需要的记录', {icon: 5});
		}else{
			layer.confirm('你确定要设置选中的记录吗？', {
				icon: 3,
			  	btn: ['确定', '取消'] //按钮
			}, function(){
			  	str=str.substring(0,str.length-1);
				$.ajax({
					type : "post",
					data : {"ids" : str},
					url : "${ctx}/manage/elearning/enable",
					dateType: "json",
					success : function(msg) {
						if (msg == 'success') {
							layer.alert("设置成功", {icon: 1}, function(){
								window.location="${ctx}/manage/elearning/compulsoryCplanList";
							});
						}else {
							layer.alert("设置失败", {icon: 5});
						}
					}
				});
			});
		}
   }

   function batchDisable(){
	   var str ="";
		$(".pos-rel :input[type='checkbox']").each(function(i){
			if(this.checked==true){
				var vs  = this.value;
				str = str+vs+",";
			}
		});
		if(str.length==0){
			layer.alert('你还没有选择需要的记录', {icon: 5});
		}else{
			layer.confirm('你确定要设置选中的记录吗？', {
				icon: 3,
			  	btn: ['确定', '取消'] //按钮
			}, function(){
			  	str=str.substring(0,str.length-1);
				$.ajax({
					type : "post",
					data : {"ids" : str},
					url : "${ctx}/manage/elearning/disable",
					dateType: "json",
					success : function(msg) {
						if (msg == 'success') {
							layer.alert("设置成功", {icon: 1}, function(){
								window.location="${ctx}/manage/elearning/compulsoryCplanList";
							});
						}else {
							layer.alert("设置失败", {icon: 5});
						}
					}
				});
			});
		}
   }
   function batchDelete(){
	   var str ="";
		$(".pos-rel :input[type='checkbox']").each(function(i){
			if(this.checked==true){
				var vs  = this.value;
				str = str+vs+",";
			}
		});
		if(str.length==0){
			layer.alert('你还没有选择要删除的记录', {icon: 5});
		}else{
			layer.confirm('你确定要删除选中的记录吗？', {
				icon: 3,
			  	btn: ['确定', '取消'] //按钮
			}, function(){
			  	str=str.substring(0,str.length-1);
				$.ajax({
					type : "post",
					data : {"ids" : str},
					url : "${ctx}/manage/elearning/deletetrs",
					dateType: "json",
					success : function(msg) {
						if (msg == 'success') {
							layer.alert("删除成功", {icon: 1}, function(){
								window.location="${ctx}/manage/elearning/compulsoryCplanList";
							});
						}else {
							layer.alert("删除失败", {icon: 5});
						}
					}
				});
			});
		}
   }
</script>
</body>
</html>
