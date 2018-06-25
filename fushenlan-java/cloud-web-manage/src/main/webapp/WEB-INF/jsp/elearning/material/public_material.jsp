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
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/font/iconfont.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/libs/datepicker/skin/default/datepicker.css">
<style type="text/css">
	.col-md-4{
		width:300px;
	}
	li.sameWadte{
		    width: 600px;
    padding-right: 0px;
    padding-left: 128px;
	}
	.materialUploadTime{
	}
	li.selecButton{
		width:95%;
	}
</style>
<form id="interfaceSearchForm" action="${ctx}/manage/publicMaterialListByPage" method="post">
		<div class="filter-box">
			<ul class="ui-form grid-row">
				<li class="col-md-4"><strong>资料名称</strong>
				<input type="hidden" name="pubType" value="${search_pubType }">
				<input class="ipt-text" type="text" placeholder="请输入资料名称" style="margin-left: 0px; name="keyWord" id="search_keyWord" value="${search_keyWord }"></li>
				<li class="col-md-4"><strong>资料提交者</strong><input class="ipt-text" style="margin-left: 0px; type="text" id="search_submitter" placeholder="资料提交者编号" name="submitter" value="${search_submitter }"></li>
				<!-- <li class="col-md-4"><strong>资料编号</strong><input class="ipt-text" type="text" id="search_id" placeholder="请输入资料编号" name="id" value="${search_id }"></li> -->
				<li class="col-md-4">
					<strong>资料类型</strong>
						<%-- <input type="text" class="ipt-text" value="${search_type }" placeholder="请选择" readonly  name="type"> --%>
						<select name="type" id="search_type">
							<option value="" >请选择</option>
							<option <c:if test="${search_type==1 }">selected</c:if> value="1" >视频</option>
							<option <c:if test="${search_type==2 }">selected</c:if>  value="2" >SCORM</option>
							<option <c:if test="${search_type==3 }">selected</c:if>  value="3" >PPT</option>
							<option <c:if test="${search_type==4 }">selected</c:if>  value="4" >WORD</option>
							<option <c:if test="${search_type==5 }">selected</c:if>  value="5" >EXCEL</option>
						</select>
				</li>
				<li class="col-md-4 sameWadte" style="padding-left:0;">
					<strong class="materialUploadTime">资料上传时间</strong>
					<input type="text" name="uploadTimeBegin" id="search_uploadTimeBegin" value="${search_uploadTimeBegin }" placeholder="开始时间" class="Wdate" style="max-width: 180px;" onfocus="WdatePicker({onpicked: function(){jQuery(this).trigger('change');},oncleared: function(){jQuery(this).trigger('change');}})">
					&nbsp;—&nbsp;
					<input type="text" name="uploadTimeEnd" id="search_uploadTimeEnd" value="${search_uploadTimeEnd }" placeholder="结束时间" class="Wdate" style="max-width: 180px;" onfocus="WdatePicker({onpicked: function(){jQuery(this).trigger('change');},oncleared: function(){jQuery(this).trigger('change');}})">
				</li>
				<li class="col-md-6 selecButton" style="text-align: right"><button type="submit_btn" class="btn btn-submit btn-radius btn-search"><i class="icon-search"></i> 查询</button></li>
			</ul>
		</div>
		</form>
		
		<table class="table table-agents" style="text-align: center;">
			<thead>
				<tr>
					<!-- <th>资料编号</th> -->
					<th>资料名称</th>
					<th>资料类型</th>
					<th>提交者</th>
					<th>上传日期</th>
					<th>是否共享</th>
					<th>是否关联计划</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${page.records}" var="material" varStatus="status">
				<tr>
					<!--  <td>${material.id }</td>-->
					<td><a href="#"  onclick="updateMaterial('${material.id }')" class="clickable">${material.name }</a></td>
					<td>
						<c:if test="${material.type==1 }">视频</c:if>
						<c:if test="${material.type==2 }">SCORM</c:if>
						<c:if test="${material.type==3 }">PPT</c:if>
						<c:if test="${material.type==4 }">WORD</c:if>
						<c:if test="${material.type==5 }">EXCEL</c:if>
					</td>
					<td>${material.createUser }</td>
					<td><fmt:formatDate value="${material.gmtCreate }" type="date" pattern="yyyy-MM-dd"/></td>
					<%-- <td>${material.groupName }</td>
					<td>${material.tagName }</td> --%>
					<td>
						<c:if test="${material.isShare=='0'}">
							否
						</c:if>
						<c:if test="${material.isShare=='1'}">
							是
						</c:if>
					</td>
					<td>
						<c:if test="${material.isRelatePlan=='0'}">
							否
						</c:if>
						<c:if test="${material.isRelatePlan=='1'}">
							是
						</c:if>
					</td>
					<td>
						<a class="color-detail" href="#"  onclick="updateMaterial('${material.id }')" title="查看">查看</a>
					</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="table-paging clearfix" id="pageDiv">
		</div>
		
<%-- <script src="${ctx}/resources/js/common/jquery-3.2.1.min.js"></script> --%>
<%-- <script src="${ctx}/resources/js/common.js"></script> --%>
<script type="text/javascript" src="${ctx}/resources/libs/datepicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common/page.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	   $(".menu").each(function(i){
		   if($(this).attr("data") == '/manage/publicMaterialListByPage'){
			   $("dt").removeClass("active");
			   $("dd").hide();
			   $(this).parent().parent().prev().addClass("active");
			   $(this).parent().parent().show();
		   }
	   });
    	var keyWord=$("#search_keyWord").val();
    	var type=$("#search_type").val();
    	var submitter=$("#search_submitter").val();
    	var pubType=$("#search_pubType").val();
    	var id=$("#search_id").val();
    	//var groupId=$("#search_groupId").val();
    	//var tagId=$("#search_tagId").val();
    	var uploadTimeBegin=$("#search_uploadTimeBegin").val();
    	var uploadTimeEnd=$("#search_uploadTimeEnd").val();
    
        //page("${ctx}/manage/publicMaterialListByPage?keyWord="+keyWord+"&type="+type+"&submitter="+submitter+"&pubType="+pubType+"&id="+id+"&groupId="+groupId+"&tagId="+tagId+"&uploadTimeBegin="+uploadTimeBegin+"&uploadTimeEnd="+uploadTimeEnd,'${page.pageSize}','${page.pageNo}','${page.pageTotal}','pageDiv');	
        page("${ctx}/manage/publicMaterialListByPage?keyWord="+keyWord+"&type="+type+"&submitter="+submitter+"&pubType="+pubType+"&id="+id+"&uploadTimeBegin="+uploadTimeBegin+"&uploadTimeEnd="+uploadTimeEnd,'${page.pageSize}','${page.pageNo}','${page.pageTotal}','pageDiv');
	});

	function updateMaterial(id){
		location.href="${ctx}/manage/viewMaterial?id="+id;
	}

</script>
</body>
</html>