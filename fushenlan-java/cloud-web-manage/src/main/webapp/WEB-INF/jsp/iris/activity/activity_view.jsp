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
<script src="${ctx}/resources/js/common/jquery-form.js" type="text/javascript"></script>
<script type="text/javascript"	src="${ctx}/resources/libs/layer/layer.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/datepicker/WdatePicker.js"></script>
<style>

.remind{width:800px; overflow:hidden;}
.remind div:nth-of-type(5n){margin-bottom:10px;}
.remind div:nth-of-type(5n+1){margin-left:0;}
</style>	
		<div class="form-detail offline_form"> 
		  <form id="inputForm"  method="post" class="form-horizontal" >
		   <input type="hidden" name="id" id="cId" value="${nvo.activity.id}"/>
		    <input type="hidden" name="signUpSet" id="signUpSet" value="${nvo.activity.signUpSet}"/>
			 <div class="title diff_btn" id="info">
				<strong>活动详情</strong>
			</div>
			<ul class="clearfix form_learn">
				<li class="col-md-12"><strong><ins>*</ins>活动名称：</strong><input type="text" name="title" class="ipt-text" value="${nvo.activity.title }"></li>
				
				<li class="col-md-4" style="margin-left: 30px;"><strong><ins>*</ins>类型：</strong>
				        <select name="type" style="width:120px;border:1px solid #333" >
							<option value="">全部</option>
							<option value="0" <c:if test="${nvo.activity.type eq '0'}">selected</c:if>>公司动态</option>
							<option value="1" <c:if test="${nvo.activity.type eq '1'}">selected</c:if>>热门活动</option>
							<option value="2" <c:if test="${nvo.activity.type eq '2'}">selected</c:if>>政策公告</option>
							<option value="3" <c:if test="${nvo.activity.type eq '3'}">selected</c:if>>产品上线</option>
						</select>
				</li>
				
				<li class="col-md-4" style="margin-left: 30px;"><strong><ins>*</ins>格式：</strong>
				        <select name="contentType" id="contentType" style="width:120px;border:1px solid #333" onchange="address()">
							<option value="1" <c:if test="${nvo.activity.contentType eq '1'}">selected</c:if>>图文</option>
							<option value="2" <c:if test="${nvo.activity.contentType eq '2'}">selected</c:if>>第三方链接地址</option>
						</select>
				</li>
				
				
				<li class="col-md-12" id="san"><strong><ins>*</ins>请输入第三方链接地址：</strong><input type="text" name="externalLinkUrl" class="ipt-text" value="${nvo.activity.externalLinkUrl }"></li>
				
				<li class="col-md-12 zlgl_share">
					<strong>保密信息：</strong>
					<label for="allow" style="padding-left: 120px;"><input type="radio" id="on" value="1" name="isSecret" <c:if test="${nvo.activity.isSecret==1 }">checked="checked"</c:if>>打开</label>
					<label for="notAllow"><input type="radio" id="off" value="0" name="isSecret" <c:if test="${nvo.activity.isSecret==0 }">checked="checked"</c:if>>关闭</label>
				</li>
				
				<li class="col-md-12 zlgl_share" id="wShare">
					<strong>开启微信分享：</strong>
					<label for="allow" style="padding-left: 120px;"><input type="radio" value="1"  name="openShare" <c:if test="${nvo.activity.openShare==1 }">checked="checked"</c:if>>允许</label>
					<label for="notAllow"><input type="radio" value="0"  name="openShare" <c:if test="${nvo.activity.openShare==0 }">checked="checked"</c:if>>不允许</label>
				</li>
				
				<li class="col-md-10 auto"><strong><ins>*</ins>封面：</strong>
				    <input name="fileId1" id="fileId1" value="" type="hidden" ><!--图  -->
				    <input name="path" id="path" value="" type="hidden" >
				    
	                <input name="fileId2" id="fileId2" value="" type="hidden" ><!--附件  -->
	                <input name="attachmentPath" id="attachmentPath" value="" type="hidden" >
	                <input name="bak1" id="bak1" value="" type="hidden" >
	                
	                
	                <input name="fileId3" id="fileId3" value="" type="hidden" ><!--课程资料附件  -->
	                
	                
	                
					<img id ="projectImg1" src="${nvo.activity.path}" width="80" height="80"><input type="hidden" id="attachmentBannerOne">
				</li>
				
				<li class="col-md-12"><strong><ins>*</ins>正文：</strong><input type="text" name="content" class="ipt-text" value="${nvo.activity.content }"></li>
				
				<li class="col-md-12">
					<strong><ins>*</ins>附件：</strong>
					<button type="button" class="btn btn-sm btn-submit ipt-btnfile" onclick ="changeC('EL_LESSON_COURSEWARE','2','70','1')">+ 上传附件</button><br>
					<input type="hidden" id="attachmentCoursewareOne">
					<a id="A_EL_LESSON_COURSEWARE" href ="${nvo.activity.attachmentPath }" target="_blank"><span id="EL_LESSON_COURSEWARE">${nvo.activity.bak1 }</span></a>
				</li>
				
				<li class="col-md-12">
				    <strong><ins>*</ins>活动地址：</strong><input type="text" name="activityAddress" class="ipt-text" value="${nvo.activity.activityAddress }">
				    <strong><ins>*</ins>活动时间：</strong> 
				     <input type="text" id="actStartDate" placeholder="开始时间" class="Wdate" name="actStartDate" value="<fmt:formatDate value="${nvo.activity.actStartDate}" pattern="YYYY-MM-dd"/>" style="width:120px;border:1px solid #333" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'actEndDate\')}'})" >
						&nbsp;—&nbsp;
				      <input type="text" id="actEndDate" placeholder="结束时间" class="Wdate" name="actEndDate"   value="<fmt:formatDate value="${nvo.activity.actEndDate}" pattern="YYYY-MM-dd"/>" style="width:120px;border:1px solid #333" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'actStartDate\')}'})">
				    <strong><ins>*</ins>报名时间：</strong> 
				     <input type="text" id="signUpStartDate" placeholder="开始时间" class="Wdate" name="signUpStartDate" value="<fmt:formatDate value="${nvo.activity.signUpStartDate}" pattern="YYYY-MM-dd"/>" style="width:120px;border:1px solid #333" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'signUpEndDate\')}'})" >
						&nbsp;—&nbsp;
				      <input type="text" id="signUpEndDate" placeholder="结束时间" class="Wdate" name="signUpEndDate"   value="<fmt:formatDate value="${nvo.activity.signUpEndDate}" pattern="YYYY-MM-dd"/>" style="width:120px;border:1px solid #333" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'signUpEndDate\')}'})">
				</li>
				
				<li class="col-md-12">
					<strong>报名人数：</strong>
					<label for="allow" style="padding-left: 120px;"><input type="radio" id="signUpOn" value="1" name="signUpSet" <c:if test="${nvo.activity.signUpSet==1 }">checked="checked"</c:if>>不限</label>
					<label for="notAllow"><input type="radio" id="signUpOff" value="2" name="signUpSet" <c:if test="${nvo.activity.signUpSet==2 }">checked="checked"</c:if>>设置人数</label>
				    
				    <input id="limitNumber" type="text" name="limitNumber" class="ipt-text" value="${nvo.activity.limitNumber }">
				</li>
				
			</ul> 
			
		</form>
		<div class="title diff_btn" id="info">
				<strong>报名情况</strong>
			</div>
		<!-- S Filter Box -->
		<div class="filter-box">
			<ul class="ui-form grid-row">
				<li class="col-md-5"><strong>关键字搜索</strong><input class="ipt-text" id="acounyName" name="acounyName"  style="margin-left: 0px;"  type="text" placeholder=""></li>
				<li class="col-md-8" >
				<button type="button" class="btn btn-submit btn-radius  btn-search" onclick="activityApplyListAjax()">查询</button>
				<button class="btn btn-sm btn-submit ipt-btnfile"onclick="exportExcel('${nvo.activity.id}','${nvo.activity.signUpSet}')"  title="导出">导出</button></li>
				
			</ul>
		</div>
		<!-- E Filter Box -->
		<table class="table table-agents table_public">
			<thead>
				<tr>
					<th>用户名</th>
					<th>工号</th>
					<th>职级</th>
					<th>所属总公司</th>
					<th>所属分公司</th>
					<th>所属部门</th>
					<th>报名状态</th>
					<th>报名时间</th>
				</tr>
			</thead>
			<tbody>
				
			<c:forEach items="${activityApplyList.records}" var="ApplyDetail" varStatus="ext" >
                  <tr>
                  	<td>${ApplyDetail.acountName}</td>
					<td>${ApplyDetail.agentCode}</td>
					<td>${ApplyDetail.levelName}</td>
					<td>${ApplyDetail.companyName}</td>
					<td>${ApplyDetail.branchCompanyName}</td>
					<td>${ApplyDetail.orgName}</td>
					<td>
					<c:if test="${ApplyDetail.status=='0' }">未报名</c:if>
					<c:if test="${ApplyDetail.status=='1' }">已报名</c:if>
					</td>
					<td><fmt:formatDate value="${ApplyDetail.applyDate}" pattern="yyyy-MM-dd"/>  </td> 
				</tr>
                    </c:forEach>
			</tbody>
		</table>
		<div class="table-paging clearfix" id="pageDiv">
		</div>
		</div>
		      <form id="bannerFile" style="display: none;" method="post" enctype="multipart/form-data">
			      <input type="file" name="fileName" id="fileName" onchange="upload()" >
			      <input name="category" type="hidden" id="category">
		      </form>
		       <input id="fileType" type="hidden"> <!-- 附件类型：1 --图； 2--附件  （ 图片要回显 ，文件提供下载 ）-->
               <input id="urlLocation" type="hidden"><!-- 附件回显位置  标签Id -->
               <input id="formLocation" type="hidden">
        <script type="text/javascript" src="${ctx}/resources/js/common/page.js"></script>
<script>
$(function(){
	var acounyName ='${acounyName}';
	var id ='${id}';
	var signUpSet ='${signUpSet}';
	pageAjax("${ctx}/manage/activity/activityApplyListAjax?acounyName="+acounyName+"&id="+id+"&signUpSet="+signUpSet,'${activityApplyList.pageSize}','${activityApplyList.pageNo}','${activityApplyList.pageTotal}','pageDiv');
})
        
		//用于页面回显问题  视实际情况而定 
		 var codeArray ={};
		 codeArray['0'] = '未报名';
		 codeArray['1'] = '已报名';

		 
      function pageNext(url){
		    	// 查询字段取页面加载时 model传入的值  防止分页执行查询
		    	var id=$("#cId").val();
		  var acounyName='${acounyName}';
		  var signUpSet=$("#signUpSet").val();
		    	$.get(url,function(returnData){
		    		if(returnData.code == '1'){
		    			var html = "";
		    			$.each(returnData.data.records,function(i,row){
		    				row.acountName = row.acountName==null?'':row.acountName; 
		    				row.agentCode = row.agentCode==null?'':row.agentCode; 
		    				row.levelName = row.levelName==null?'':row.levelName; 
		    				row.companyName = row.companyName==null?'':row.companyName; 
		    				row.branchCompanyName = row.branchCompanyName==null?'':row.branchCompanyName; 
		    				row.orgName = row.orgName==null?'':row.orgName; 
		    				row.applyDate = row.applyDate==null?'':row.applyDate; 
		    				var tex="";
		    				var time="";
		    				if(null !=row.applyDate && row.applyDate !=""){
		    					tex=row.applyDate;
		    					time=crtTimeFtt(tex);
		    				}
				   			html+='<tr>'+
				   				 '<td>'+row.acountName+'</td>'+
				   				 '<td>'+row.agentCode+'</td>'+
				   				 '<td>'+row.levelName+'</td>'+
				   				'<td>'+row.companyName+'</td>'+
				   				'<td>'+row.branchCompanyName+'</td>'+
				   				'<td>'+row.orgName+'</td>'+
				   				 '<td>'+codeArray[row.status]+'</td>'+
				   				'<td>'+time+'</td>';
				   			})
		    			$(".table tbody").html(html);
		    			pageAjax("${ctx}/manage/activity/activityApplyListAjax?acounyName="+acounyName+"&id="+id+"&signUpSet="+signUpSet,returnData.data.pageSize,returnData.data.pageNo,returnData.data.pageTotal,'pageDiv');
		    		}
		    	})
		    }
      
  	 function activityApplyListAjax(){
  		var signUpSet=$("#signUpSet").val();
  		var acounyName=$("#acounyName").val();
  		var id=$("#cId").val()
		$.ajax({
			  async : false,  
		      cache : false,  
		      type: 'GET',
		      url: "${ctx }/manage/activity/activityApplyListAjax?acounyName="+acounyName+"&id="+id+"&signUpSet="+signUpSet,
		      dataType: "json",
		  success: function(returnData){
			 if(returnData.code==1){
				var html = "";
				$.each(returnData.data.records,function(i,row){
					row.acountName = row.acountName==null?'':row.acountName; 
    				row.agentCode = row.agentCode==null?'':row.agentCode; 
    				row.levelName = row.levelName==null?'':row.levelName; 
    				row.companyName = row.companyName==null?'':row.companyName; 
    				row.branchCompanyName = row.branchCompanyName==null?'':row.branchCompanyName; 
    				row.orgName = row.orgName==null?'':row.orgName; 
    				var tex="";
    				var time="";
    				if(null !=row.applyDate && row.applyDate !=""){
    					tex=row.applyDate;
    					time=crtTimeFtt(tex);
    				}
		   			html+='<tr>'+
		   				 '<td>'+row.acountName+'</td>'+
		   				 '<td>'+row.agentCode+'</td>'+
		   				 '<td>'+row.levelName+'</td>'+
		   				'<td>'+row.companyName+'</td>'+
		   				'<td>'+row.branchCompanyName+'</td>'+
		   				'<td>'+row.orgName+'</td>'+
		   				 '<td>'+codeArray[row.status]+'</td>'+
		   				'<td>'+time+'</td>';
		   			})
    			$(".table tbody").html(html);
				pageAjax("${ctx}/manage/activity/activityApplyListAjax?acounyName="+acounyName+"&id="+id+"&signUpSet="+signUpSet,returnData.data.pageSize,returnData.data.pageNo,returnData.data.pageTotal,'pageDiv');
				}
		  },
		  
		  
		});
		 
		
	} 
		     
      function crtTimeFtt(val) {
		    if (val != null) {
		            var date = new Date(val);
		            return date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate();
		        }
		}
      
      
      function exportExcel(id,signUpSet){
    		/* $.ajax({
    			type : "GET",
    			url : "${ctx}/manage/activity/exportExcel?id="+id+"&signUpSet="+signUpSet,
    			dateType: "json",
    			success : function(data) {
    				if (data.code == '1') {
    					layer.msg(data.msg);
    				}else {
    					layer.alert(data.msg, {icon: 5});
    				}
    			}
    		}); */
    	  location.href="${ctx}/manage/activity/exportExcel?id="+id+"&signUpSet="+signUpSet;
       }
       
</script>
</body>
</html>
