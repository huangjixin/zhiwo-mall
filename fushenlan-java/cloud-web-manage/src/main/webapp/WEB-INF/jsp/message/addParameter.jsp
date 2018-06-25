<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<title>富卫运维大平台</title>
<meta name="description" content="">
<meta name="description" content="">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/elerning/style.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/elerning/font/iconfont.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/elerning/page.css">
</head>
<body>

	
		
		<div class="form-detail">
		<form id="addParameter" action="${ctx}/manage/sms/SaveSmsParameter" method="post">
		<input type="hidden" name="id" value="${smsParameterFactor.id}">
			<div class="title"><strong style="font-size: 17px;">新增参数</strong></div>
			<ul class="clearfix form_learn">
				<li class="col-md-12" style="margin-bottom: 12;"><strong style="font-size: 17px;"> 参数因子</strong><input type="text" id="factor" name="factor" class="ipt-text" style="width: 31%;" value="${smsParameterFactor.factor} "></li>
				<li class="col-md-12"><strong style="font-size: 17px;">参数名称</strong><input type="text" id="factorName" name="factorName" class="ipt-text" value="${smsParameterFactor.factorName}" style="width: 31%;"></li>
					<li class="col-md-12" style="margin-top: 12;margin-bottom: 12;"><strong style="font-size: 17px;">参数类型：</strong>
					
					<label for="random"><input type="radio"  value="BUSNIESS" <c:if test="${smsParameterFactor.factorType=='BUSNIESS'}">checked</c:if>  id="type1" name="factorType"><strong  style="font-size: 15px;">业务类</strong></label>
					<label for="fixed"><input type="radio"  value="VALIDATE" <c:if test="${smsParameterFactor.factorType=='VALIDATE'}">checked</c:if> id="type2" name="factorType"><strong  style="font-size: 15px;">验证类</strong></label>
				</li>
				
			<li class="col-md-12"><strong style="font-size: 17px;">备注：</strong><input type="text" id="remark" name="remark" class="ipt-text" value="${smsParameterFactor.remark }" style="width: 34%;"></li>
			</ul>
			</form>
			<div class="ui-button">
				<button type="button" class="btn btn-submit" onclick="saveTemplate()">保存</button>
				<button type="button" class="btn btn-default" onclick="cancel()">取消</button>
			</div>
			
		</div>
	

<script type="text/javascript" src="${ctx}/resources/libs/layer/layer.js"></script>




<script type="text/javascript">





function cancel(){
	location.href="${ctx}/manage/sms/getParameter";
}
		function saveTemplate(){
			var factor=$("#factor").val();
			var factorName=$("#factorName").val();
			if(null==factor || factor==""){
				layer.alert("请输入参数因子");
				return;
			}
			if(null==factorName || factorName==""){
				layer.alert("请输入参数名称");
				return;
			}
			
			var x='1';
			 var groupCheckbox1=$("input[name='factorType']");
			
			 for(i=0;i<groupCheckbox1.length;i++){
				 if(groupCheckbox1.eq(i).is(":checked")){
					 x='0';
				 }	
		    }
			
			 if(x=="1"){
					layer.msg("参数类型不能为空"); 
					return;
				 }
			 $.ajax({
				  async : false,  
			      cache : false, 
			      type: 'POST',
		   	      url: "${ctx }/manage/sms/SaveSmsParameter",
			      data:$("#addParameter").serialize(), 
		   	    //  dataType: "json",
		   	  success: function(data){
		   		 if(data.code==1){
		   			 layer.confirm(data.msg, {
		   				icon: 6,
		   			  	btn: ['确定'] //按钮
		   			}, function(){
		   				location.href="${ctx}/manage/sms/getParameter";
		   			}); 
		   			
		   		 }else{
		   			layer.confirm(data.msg, {
		   				icon: 3,
		   			  	btn: ['确定'] //按钮
		   			}, function(){
		   				location.href="${ctx}/manage/sms/getParameter";
		   			}); 
		   		 }
		   	  },
		   	  error: function (data) {//请求失败处理函数  
		   		
	   		 }
		   	  
		   	});
			
		}
	



</script>
</body>
</html>
