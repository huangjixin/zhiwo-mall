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
<style  type="text/css">
.ui-form li .course_status label {
    padding: 0 0 0 15px;
}
</style>
<form id="tagSearchForm" action="${ctx}/manage/listByTagName" method="post">
		<div class="filter-box">
			<ul class="ui-form grid-row">
				<li class="col-md-3"><strong>一级分类</strong><input id="parentTagName" name=parentTagName type="text" placeholder="请输入一级分类" value="${ parentTagName}"></li>
				<li class="col-md-3"><strong>二级分类</strong><input id="childTagName" name=childTagName type="text" placeholder="请输入二级分类" value="${ childTagName}"></li>
			</ul>
			<button type="submit_btn" class="btn btn-submit btn-radius btn-search"><i class="icon-search"></i> 查询</button>
		</div>
	</form>
		<div class="tab-btn">
		<a class="btn btn-success btn-radius" href="${ctx }/manage/toAddTagJsp" title="新增标签">新增标签</a>
		</div>
		<table class="table table-agents table_public">
			<thead>
				<tr>
					<th width="30%"  align="center">一级分类</th>
					<th>二级分类</th>
					<th width="30%" align="center">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${childPageList!=null}">
					<c:forEach items="${childPageList}" var="tag" varStatus="status">
						<tr>
							<td>${tag.parentTagName }</td>
							<td>${tag.tagName }</td>
							<td>
								<a class="color-detail" href="${ctx }/manage/toAddTagJsp?parentTagName=${tag.parentTagName }&&catagory=${tag.catagory }">编辑</a>
								<a class="color-detail" href="#" onclick="deleteTag('${tag.id}')">删除</a>
							</td>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${parentPageList!=null}">
					<c:forEach items="${parentPageList}" var="tagVo" varStatus="status">
						<tr>
							<td>${tagVo.tagName }</td>
							<td>
								<c:forEach items="${tagVo.tagTwoList}" var="childTag" varStatus="status">
									${childTag.tagName }&nbsp;  
								</c:forEach> 
							</td>
							<td>
								<a class="color-detail" href="${ctx }/manage/toAddTagJsp?parentTagName=${tagVo.tagName }&&catagory=${tagVo.catagory }">编辑</a>
							</td>
						</tr>
					</c:forEach>
				</c:if>
				
			</tbody>
		</table>
		<div class="table-paging clearfix" id="pageDiv">
		</div>
	
<%-- <script src="${ctx}/resources/js/common/jquery-3.2.1.min.js"></script> --%>
<%-- <script src="${ctx}/resources/js/common.js"></script> --%>
<script type="text/javascript" src="${ctx}/resources/libs/datepicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common/page.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/layer/layer.js"></script>
<script type="text/javascript">
$(function(){
	var parentTagName = $("#parentTagName").val();
	var childTagName = $("#childTagName").val();
	if('${childPageList}' == ''){
		var pageNo = '${parentPageInfo.pageNo}';
		var pageSize = '${parentPageInfo.pageSize}';
		var pageTotal = '${parentPageInfo.pageTotal}'; 
	}else{
		var pageNo = '${childPageInfo.pageNo}';
		var pageSize = '${childPageInfo.pageSize}';
		var pageTotal = '${childPageInfo.pageTotal}'; 
	}
	
	pageAjax("${ctx}/manage/listByTagNameAjax?parentTagName="+parentTagName+"&childTagName="+childTagName,pageSize,pageNo,pageTotal,'pageDiv');
})
 function pageNext(url){
		    	// 跳转页面ajax
		    	var parentTagName = '${parentTagName}';
				var childTagName = '${childTagName}';
		    	$.get(url,function(returnData){
		    		if(returnData.data!=null){
		    			var html = "";
		    			$.each(returnData.records,function(i,elment){
		    				if('${parentPageList}' == ''){
		    					//二级标签list
		    					html+='<tr><td>'+elment.parentTagName+'</td><td>'+elment.tagName+'</td><td>'+
		    					'<td><a class="color-detail" href="${ctx }/manage/toAddTagJsp?parentTagName='+elment.parentTagName+'catagory='+elment.catagory
		    					+'>编辑</a><a class="color-detail" href="#" onclick="deleteTag("'+elment.id+'">删除</a></td></tr>';
		    				}else{
		    					//一级标签list
		    					//拼二级标签字符串
		    					var tagNames = null;
		    					var tagTwoList = elment.tagTwoList;
		    					for(var i = 0;i < tagTwoList.size();i++){
		    						tagNames += (tagTwoList.get(i).getTagName()+" ");
		    					}
		    					html+='<tr><td>'+elment.tagName+'</td><td>'+tagNames+'</td><td>'+
		    					'<td><a class="color-detail" href="${ctx }/manage/toAddTagJsp?parentTagName='+elment.tagName+'catagory='+elment.catagory
		    					+'>编辑</a></td></tr>';
		    				}
		    			})
		    			$(".table tbody").html(html);
		    			pageAjax("${ctx}/manage/classPlan/listByTagNameAjax?parentTagName="+parentTagName+"&childTagName="+childTagName,returnData.pageSize,returnData.pageNo,returnData.pageTotal,'pageDiv');
		    		}
		    	})
		    }

function deleteTag(tagId){
	var parentTagName = $('input[name="parentTagName"]').val();
	var childTagName = $('input[name="childTagName"]').val();
		$.ajax({
	      type: 'POST',
	  	  url: "${ctx}/manage/deleteTag",
	  	  dataType:"text",
	  	  data :{"tagId":tagId},
	  	  traditional:true,
	  	  success: function(result) {
	  		  if(result=="标签删除成功"){
	  			location.href="${ctx}/manage/listByTagName?parentTagName="+parentTagName+"&&childTagName="+childTagName;
	  			layer.message("标签删除成功");
	  		  }else{
	  			layer.alert(result); 
	  		  }
	  			
		  },
		  error: function(){
			  alert(2);
		  }
	  	})
}
</script>
</body>
</html>