<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
</head>
<body>
		<div class="form-detail">
			<div class="title"><strong>分享试卷</strong></div>
			<ul class="clearfix">
			
				<c:forEach items="${data }" var="paper"  varStatus="status">
					<li class="col-md-6"><strong>试卷名称：</strong>${paper.name }</li>
					<input type="hidden" name="questionId" class="questionId" value="${paper.id }">
				</c:forEach>
				
				<li class="col-md-12 zlgl_share">
						<strong>是否分享：</strong>
						<label for="yes"><input type="radio" value="1" id="yes" name="isShare" <c:if test="${isShare=='1' }">checked</c:if>>是
						</label>
						<label for="no"><input type="radio" value="0" id="no" name="isShare" <c:if test="${isShare=='0' }">checked</c:if>>否
						</label>
				</li>
				<li class="col-md-12 zlgl_share" id="shareRange">
					<strong>分享范围：</strong>
					<label><input name="range" type="checkbox" value="1" />qq </label> 
					<label><input name="range" type="checkbox" value="2" />AAAAA </label> 
					<label><input name="range" type="checkbox" value="3" />qdasd </label> 
					<label><input name="range" type="checkbox" value="4" />市场复苏的方式 </label> 
				</li>
			</ul>
			<div class="ui-button">
				<button type="button" class="btn btn-submit" onclick="saveBatchPaper()">保存并关闭</button>
				<button type="button" class="btn btn-default" onclick="cancleUpdate()">取消</button>
			</div>
		</div>
		<!-- / E Form Detail -->
		
<script type="text/javascript" src="${ctx}/resources/libs/layer/layer.js"></script>

<script type="text/javascript">
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
	location.href="${ctx}/manage/paper/GetPublicPaper?otherType="+1;
 }
	function saveBatchPaper(){
		//获取所有的资料Id
		var ids = "";
		var isShare = $("input[name='isShare']:checked").val();
		var groupCheckbox=$("input[name='questionId']");
	    for(i=0;i<groupCheckbox.length;i++){
	        ids =ids+groupCheckbox[i].value+",";
	    	//materials.push(groupCheckbox[i].value);
	    }
		//获取所有的用户组Id
		var groupIds = "";
		 var groupCheckbox=$("input[name='range']:checked");
		     for(i=0;i<groupCheckbox.length;i++){
		        if(groupCheckbox.eq(i).is(":checked")){
		        	groupIds =groupIds+groupCheckbox[i].value+",";
		        	//groups.push(groupCheckbox[i].value);
		        }
		    }
		     if(groupCheckbox.length==0 && isShare == '1'){
				 layer.alert("请选择要分享的用户组的选项");
				 return;
			 }
		     /* var materialIds=JSON.stringify(materials); 
		     var groupIds=JSON.stringify(groups); */
		     ids = ids.substring(0, ids.length-1);
		     groupIds = groupIds.substring(0, ids.length-1);
		      $.ajax({
			      type: 'POST',
			  	  url: "${ctx}/manage/paper/saveSharePaper",
			  	  data :{"ids" : ids,"groupIds":groupIds,"isShare":isShare},
			  	  //traditional:true,
			  	  success: function(data) {
			  		 if(data.code==1){
			  			 layer.confirm("分享成功", {
				   				icon: 6,
				   			  	btn: ['确定'] //按钮
				   			}, function(){
				   				location.href="${ctx}/manage/paper/GetPublicPaper?otherType="+1;
				   			}); 
		    		  }else{
		    			  layer.confirm("分享失败", {
				   				icon: 3,
				   			  	btn: ['确定'] //按钮
				   			}, function(){
				   				location.href="${ctx}/manage/paper/GetPublicPaper?otherType="+1;
				   			});
		    		  }
				  },
				  error: function (data) {//请求失败处理函数  
					  layer.confirm("分享失败", {
			   				icon: 3,
			   			  	btn: ['确定'] //按钮
			   			}, function(){
			   				location.href="${ctx}/manage/paper/GetPublicPaper?otherType="+1;
			   			});
			        	
			        }
			  	})   
			  	
			  	
		
	}
 
 
</script>
</body>
</html>
