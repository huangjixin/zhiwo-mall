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
<%-- <script src="${ctx}/resources/js/common/jquery-3.2.1.min.js"></script> --%>
<link rel="stylesheet" href="${ctx}/resources/libs/ztree/zTreeStyle.css" type="text/css">
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/style.css">
<script type="text/javascript" src="${ctx}/resources/js/common/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/datepicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common/page.js"></script>
	<div class="bg hide"></div>
	<div class="popup hide">
		<div class="tb_hd">
			<!-- <span class="table_header">选择讲师</span> -->
			<div class="form-search">
				<input type="text" placeholder="" class="form-control">
				<i><img src="../../imgs/search.png" alt=""></i>
			</div>
			<button class="cancel_tb"><img src="../../imgs/cancel.png" alt=""></button>
		</div>
		<div class="filter-box">
		      <ul class="ui-form grid-row">
		         <li class="col-md-3"><strong>讲师姓名</strong><input id="accountName" value ="${accountName }"class="ipt-text" type="text" placeholder="讲师姓名"></li>
		         <li class="col-md-3"><strong>手机号</strong><input id="pho" value ="${phone }"class="ipt-text" type="text" placeholder="手机号"></li>
		         <li class="col-md-12" style="text-align: right">
		            <button type="button" class="btn btn-submit btn-radius  btn-search" onclick="findtr()">查询</button>
		         </li>
		      </ul>
		</div>
		<table class="table table-agents">
			<thead>
				<tr>
					<th>
						<label class="pos-rel">
							<input type="checkbox" class="ace"  id="checkbox" >
						</label>
					</th>
					<th>编号</th>
					<th>讲师姓名</th>
					<th>手机号</th>
					<th>邮箱</th>
					<th>职级</th>
				</tr>
			</thead>
			<tbody id="ter">
				<c:if test="${not empty accountList}">
					<c:forEach  varStatus="idx" var="account" items="${accountList}">
						<tr>
							<td>
								<input id="accountId" name="checkbox" type="checkbox" class="ace" value="${account.id }"
								<c:forEach var="lecturer" items="${courseLecturerList}">
								<c:if test="${account.id==lecturer.lecturerId }">checked</c:if>
								</c:forEach>
								>
							</td>
							<td>${account.id }</td>
							<td><a  href="#" class="clickable">${account.accountName }</a></td>
							<td>${account.mobile }</td>
							<td>${account.email }</td>
							<td>
								<c:if test="${account.postType==1 }">总监</c:if>
								<c:if test="${account.postType==2 }">主管</c:if>
								<c:if test="${account.postType==3 }">代理人</c:if>
								<c:if test="${account.postType==4 }">内勤</c:if>
							</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
		<div class="btn_list" align="center" style="margin-top: 15px;">
			<button class="btn btn-submit" onclick="addLecturers()">确定</button>
			<button class="btn btn-default" onclick="backOne();">取消</button>
		</div>
		<input type="hidden" class="ace"  id="courseId" value="${course.id }" >
	</div>
	<script type="text/javascript"	src="${ctx}/resources/libs/layer/layer.js"></script>
	<script type="text/javascript">
	
	 function backOne(){
			var index=parent.layer.getFrameIndex(window.name);
			parent.layer.close(index);
	}
	function addLecturers() {
		var str ="";
		$("input[name='checkbox']").each(function(){
			if(this.checked==true){
				var vs  =this.value;
				str = str+vs+",";
			}
		})
		if(str.length==0){
			layer.alert('你还没有选择需要的记录', {icon: 5});
		}else{
			var id= $("#courseId").val();
			//alert(id);
			var jsonStr =[];
			 var accountIds = new Array();
			 var accountNames = new Array();
			 var groupCheckbox=$("input[name='checkbox']:checked");
			     for(i=0;i<groupCheckbox.length;i++){
			        if(groupCheckbox.eq(i).is(":checked")){//
			        	var name=groupCheckbox.eq(i).parent().siblings().find(".clickable").text();
			        	accountIds[i] =groupCheckbox[i].value;
			        	accountNames[i] =name;
			        }
			    }
		         parent.accountList(accountIds,accountNames); 
		}
		
	}
	</script>
	<script type="text/javascript">
	$("#checkbox").click(function () {
	    if($(this).is(':checked')){
	    	$("input[name='checkbox']").prop("checked",true);
	    }else{
	    	$("input[name='checkbox']").prop("checked",false);
	    }
	});
	
	function findtr(){
		var accountName = $("#accountName").val();
		var mobile = $("#pho").val();
		 $.ajax({
			  async : false,  
		      cache : false,  
		      type: 'POST',
	    	  url: "${ctx }/manage/findByOtherRoleId",
	    	  data: {"accountName" : accountName,"mobile":mobile},
	    	  dataType: "json",
	    	  success: function(data){
	    		  $("#ter").children().remove();
	    		  if(data.data.length==0){
	    			  layer.msg("没有数据");
	    		  }else{
	    			  $.each(data.data,function(i,item){
	    				 var accountTypeName = "";
	    				 if (item.accountType == null || item.accountType == 'null' || item.accountType == '') {
	    					 
	    				 } else {
	    					 if (item.accountType == '1') {
	    						 accountTypeName = "总监";
	    					 } else if (item.accountType == '2') {
	    						 accountTypeName = "主管";
	    					 } else if (item.accountType == '3') {
	    						 accountTypeName = "代理人";
	    					 } else if (item.accountType == '4') {
	    						 accountTypeName = "内勤";
	    					 } else {
	    						 
	    					 }
	    				 }
	    			  $("#ter").append('<tr><td><input id="accountId" type="checkbox" name="checkbox" value='+item.id+' class="ace"></td><td>'+item.id+'</td><td><a  href="#" class="clickable">'+item.accountName+'</a></td><td>'+item.mobile+'</td><td>'+item.email+'</td><td>'+accountTypeName+'</td></tr>');
	    		    })
	    		  }
	    	   }
	    	}) 
	}

	</script>
</body>
</html>