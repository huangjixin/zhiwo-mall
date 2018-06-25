<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 	<title>富卫运维大平台</title>
</head>
<body>
<script type="text/javascript" src="${ctx}/resources/libs/layer/layer.js" ></script>

<!-- S Wrapper -->
<div class="wrapper">
	<!-- S Wrapper Content -->
		<div class="form-detail">
			<div class="title"><strong>分享课程</strong></div>
			<ul class="clearfix">
				<!-- <li class="col-md-12"><strong>课程名称：</strong>【判断题】低于18周岁均为非民事行为责任人，不可单独办理银行业务。</li> -->
				<c:forEach items="${courses }" var="course"  varStatus="status">
					<li class="col-md-6"><strong>课程名称：</strong>${course.name }</li>
					<input type="hidden" name="courseId" class="courseId" value="${course.id }">
				</c:forEach>
				<li class="col-md-6 zlgl_share">
						<strong>是否分享：</strong>
						<label for="yes"><input type="radio" value="" id="yes" name="share" <c:if test="${not empty groupIds}">checked</c:if>>是
						</label>
						<label for="no"><input type="radio" value="" id="no" name="share" <c:if test="${empty groupIds}">checked</c:if>>否
						</label>
				</li>
				<li class="col-md-12 zlgl_share">
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
				<button type="button" class="btn btn-submit" onclick="share()">保存</button>
				<button type="button" class="btn btn-default" onclick="history.go(-1)">取消</button>
			</div>
			<div class="ui-button—no" align="center">
                <button type="button" class="btn btn-default" onclick="history.go(-1)">取消</button>
            </div>
		</div>
		<!-- / E Form Detail -->
</div>
<!-- E Wrapper -->
<script type="text/javascript">
    
	$(document).ready(function (){
	    if($("#no").is(":checked")){
	        $(".col-md-12").hide();
	        $(".ui-button").hide();
	        
	   }else{
	       $(".col-md-12").show();
	       $(".ui-button").show();
	       $(".ui-button—no").hide();
	   }
	})
	$("input[name='share']").change(function(){
	    if($("#no").is(":checked")){
	         $(".col-md-12").hide();
	         $(".ui-button").hide();
	         $(".ui-button—no").show();
	    }else{
	        $(".col-md-12").show();
	        $(".ui-button").show();
	        $(".ui-button—no").hide();
	    }
	});
    
	function share(){
		//获取所有的资料Id
		var courses = new Array();
		var groupCheckbox=$("input[name='courseId']");
		    for(i=0;i<groupCheckbox.length;i++){
		      courses[i] =groupCheckbox[i].value;
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
			  	  url: "${ctx}/manage/doShareCourses",
			  	  data :{"courseIds" : courses,"groupIds":groups},
			  	  success: function(result) {
			  		if(result){
			  			layer.alert("分享成功");
			  			location.href="${ctx}/manage/privateCourseListByPage";
			  		}
				  }
			  });  
	     } else {
	    	 layer.alert("请选择要分享的用户组");
	     }
		     
	}
	
</script>
</body>
</html>