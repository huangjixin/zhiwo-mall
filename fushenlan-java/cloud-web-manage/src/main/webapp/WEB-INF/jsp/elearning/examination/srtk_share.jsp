<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
</head>
<body>
	<div class="form-detail">
		<div class="title"><strong>分享考题</strong></div>
	    <input type="hidden" name="id" id="id" value="${id }">
		<ul class="clearfix">
			   <c:forEach items="${questions }" var="question">
					<li class="col-md-6"><strong>课程名称：</strong>${question.content }</li>
					<input type="hidden" name="questionId" class="questionId" value="${question.id }">
				</c:forEach>
			<li class="col-md-6 zlgl_share">
				 <strong>是否分享：</strong>
				   <label for="yes"><input type="radio" value="" id="yes" name="isShare" <c:if test="${not empty groupIds}">checked</c:if>>是
				   </label>
				   <label for="no"><input type="radio" value="" id="no" name="isShare" <c:if test="${empty groupIds}">checked</c:if>>否
				   </label>
			</li>
			<li class="col-md-12 zlgl_share" id="shareRange">
				<strong>分享范围：</strong>
				<c:if test="${not empty groupList}">
                        <c:forEach items="${groupList}" var="group">
                            <label><input name="groupId" type="checkbox" class="groupId" value="${group.id}" 
                                    <c:if test="${fn:contains(groupIds,group.id)}">
                                         checked
                                    </c:if> 
                                     />${group.groupName}
                            </label> 
                        </c:forEach>
                     </c:if>
			</li>
		</ul>
		<div class="ui-button">
			<button type="button" class="btn btn-submit" onclick="saveQuestion()">保存并关闭</button>
			<button type="button" class="btn btn-default"  onclick="cancleUpdate()">取消</button>
		</div>
	</div>
<script type="text/javascript" src="${ctx}/resources/libs/layer/layer.js"></script>

<script type="text/javascript">
      
      
      
      
       
       
function saveQuestion(){
	//获取所有的资料Id
	var questions = new Array();
	var groupCheckbox=$("input[name='questionId']");
	    for(i=0;i<groupCheckbox.length;i++){
	    	questions[i] =groupCheckbox[i].value;
	    }
	
	//获取所有的用户组Id
	var groups = new Array();
	 var groupCheckbox=$("input[name='groupId']:checked");
	     for(i=0;i<groupCheckbox.length;i++){
	        if(groupCheckbox.eq(i).is(":checked")){
	        	groups[i] =groupCheckbox[i].value;
	        }
	    }
     if (groups.length > 0) {
	      $.ajax({
		      type: 'POST',
		  	  url: "${ctx}/manage/question/saveShareQuestion",
		  	  data :{"questionIds" : questions,"groupIds":groups},
		  	  success: function(result) {
		  		if(result){
		  			layer.alert("保存成功", {icon: 1}, function(){
		  				location.href="${ctx}/manage/question/GetPulicQuestion?otherType="+1;
					});
		  		}
			  }
		  });  
     } else {
    	 layer.alert("请选择要分享的用户组");
     }
	     
}
$(function(){
	if('0'=='${isShare}'){
		$("#shareRange").hide();
	}
})

$("#no").click(function () {
	$("#shareRange").hide();
});

$("#yes").click(function () {
	$("#shareRange").show();
});
function cancleUpdate(){
	location.href="${ctx}/manage/question/GetPulicQuestion?otherType="+1;
 }
	$("#no").click(function () {
		$("#shareRange").hide();
	});
	$("#yes").click(function () {
		$("#shareRange").show();
	});
	
	/* function saveShare(){ 
		var isShare = $("input[name='isShare']:checked").val();
		var groupCheckbox;
		var groupIds="";
		if(isShare == '1'){
			groupCheckbox =$("input[name='range']:checked")
			if(groupCheckbox.length==0){
				layer.alert("请选择要分享的用户组的选项");
				return;
			}
		    for(i=0;i<groupCheckbox.length;i++){
		       if(groupCheckbox.eq(i).is(":checked")){
		        	groupIds=groupIds+groupCheckbox[i].value+",";
		       }
		    }
		}
		var ids=$("#id").val();
		$.ajax({
			  async : false,  
		      cache : false,  
		      type: 'GET',
	    	  url: "${ctx }/manage/question/saveShareQuestion?ids="+ids+"&groupIds="+groupIds+"&isShare="+isShare,
	    	  //dataType: "json",
	    	  success: function(data){
	    		  if(data.code==1){
	    			  layer.confirm(data.msg, {
			   				icon: 6,
			   			  	btn: ['确定'] //按钮
			   			}, function(){
			   			  location.href="${ctx}/manage/question/GetPulicQuestion?otherType="+1;
			   			}); 
	    		  }else{
	    			  layer.confirm(data.msg, {
			   				icon: 3,
			   			  	btn: ['确定'] //按钮
			   			}, function(){
			   			  location.href="${ctx}/manage/question/GetPulicQuestion?otherType="+1;
			   			});
	    		  }
	    	  },
	    	  error: function (data) {//请求失败处理函数  
	    		  layer.confirm(data.msg, {
		   				icon: 3,
		   			  	btn: ['确定'] //按钮
		   			}, function(){
		   			  location.href="${ctx}/manage/question/GetPulicQuestion?otherType="+1;
		   			});
		        }
	    	  
	    	});  
	} */
	

</script>


<script type="text/javascript">
$(function(){
	<c:forEach items="${list}" var="share" >
 	$("#"+"${share}").attr("checked","checked");
</c:forEach>
})



</script>

</body>
</html>
