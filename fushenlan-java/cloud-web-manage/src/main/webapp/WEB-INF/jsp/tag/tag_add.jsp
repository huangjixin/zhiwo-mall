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
						<thead>
							<tr>
								<th>标签名称</th>
								<th>标签ID</th>
								<th>创建者</th>
								<th>创建时间</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
						<c:forEach items="${childTagList }" var="childTag">
							<tr>
								<td>${childTag.tagName }</td>
								<td>${childTag.id }</td>
								<td>${childTag.createUser }</td>
								<td><fmt:formatDate value="${childTag.createTime }" pattern="yyyy-MM-dd" type="date"></fmt:formatDate></td>
								<td><a class="color-detail" href="#" onclick="deleteTag('${childTag.id}')">删除</a></td>
							</tr>
						</c:forEach>		
						</tbody>
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
	var parentTagName = '${parentTagName}';
		var pageNo = '${childPageInfo.pageNo}';
		var pageSize = '${childPageInfo.pageSize}';
		var pageTotal = '${childPageInfo.pageTotal}'; 
	pageAjax("${ctx}/manage/toAddTagJspAjax?parentTagName="+parentTagName,pageSize,pageNo,pageTotal,'pageDiv');
})
 function pageNext(url){
		    	// 跳转页面ajax
		    	var parentTagName = '${parentTagName}';
		    	$.get(url,function(returnData){
		    		if(returnData!=null){
		    			var html = "";
		    			$.each(returnData.records,function(i,elment){
		    					var tex=elment.createTime;
				   			 	var time=crtTimeFtt(tex);
		    					html+="<tr><td>"+elment.tagName+"</td><td>"+elment.id+"</td><td>"+elment.createUser+"</td><td>";
		    					html+=time;
		    					html+="</td><td><a class='color-detail' href='#' onclick='deleteTag('+elment.id+')'>删除</a></td></tr>";
		    			})
		    			$(".table tbody").html(html);
		    			pageAjax("${ctx}/manage/classPlan/listByTagNameAjax?parentTagName="+parentTagName+returnData.pageSize,returnData.pageNo,returnData.pageTotal,'pageDiv');
		    		}
		    		
		    	})
		    }
$(function(){
	$('.nav-tabs').each(function(index, element) {
		var _obj = $(this);
		$(this).on('click', 'strong:not(.active)', function(){
			$(this).addClass('active').siblings('strong').removeClass('active');
			_obj.nextAll('.form').children('.tab-pane').removeClass('active').eq($(this).index()).addClass('active')
		})
	});
	
})	
function addTag(){
	var parentTagName = $('input[name="parentTagName"]').val();
	var childTagName = $('input[name="childTagName"]').val();
	var catagory = $('#catagory').val();
	if(parentTagName==""||childTagName==""||catagory==""){
		layer.alert("请输入完整信息"); 
		return;
	}
		$.ajax({
	      type: 'POST',
	  	  url: "${ctx}/manage/addTag",
	  	  dataType:"text",
	  	  data :{"parentTagName":parentTagName,"childTagName":childTagName,"catagory":catagory},
	  	  traditional:true,
	  	  success: function(result) {
	  		  if(result=="新增标签成功"){
	  			location.href="${ctx}/manage/toAddTagJsp?parentTagName="+parentTagName;
	  			layer.message("新增标签成功");
	  		  }else{
	  			layer.alert(result); 
	  		  }
		  }
	  	})
}
function deleteTag(tagId){
	var parentTagId = $('#parentTagId option:selected').val();
		$.ajax({
	      type: 'POST',
	  	  url: "${ctx}/manage/deleteTag",
	  	  dataType:"text",
	  	  data :{"tagId":tagId},
	  	  traditional:true,
	  	  success: function(result) {
	  		  if(result=="标签删除成功"){
	  			location.href="${ctx}/manage/toAddTagJsp?parentTagId="+parentTagId;
	  		  }else{
	  			layer.alert(result); 
	  		  }
	  			
		  }
	  	})
}
 
/*可输入下拉框  */
function selectParentTagNames(){
	var selected = $('#parentTagNames').val();
	//将下拉框选择值赋给输入框
	$('input[name="parentTagName"]').val(selected);
} 

/*输入后模糊检索一级标签  */
/*  function searchParentTags(){
	 var parentTagName = $('input[name="parentTagName"]').val();
	 $.ajax({
	      type: 'POST',
	  	  url: "${ctx}/manage/findParentTags",
	  	  data :{"parentTagName":parentTagName},
	  	  traditional:true,
	  	  success: function(data) {
	  		  if (data != null) {
	  				$("#parentTagName").empty();
	  			  for(var i = 0;i < data.length;i++){
	  				$("#parentTagName").append("<option value='" + data[i].tagName + "'>" + data[i].tagName + "</option>");
	  			  }
	  		  }
		  }
	  	}) 
}  */
 
</script>
</body>
</html>