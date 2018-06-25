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
<style>

#u1314 {
    border-width: 0px;
    position: absolute;
    left: 144px;
    top: 467px;
    width: 391px;
    height: 92px;
}
</style>
	
		
		<div class="form-detail">
		<form id="smsTemplate" action="${ctx}/manage/sms/saveSmsTemplate" method="post">
		<input type="hidden" name="id" value="${smsTemplate.id}">
			<div class="title"><strong style="font-size: 17px;">消息详情</strong></div>
			<ul class="clearfix form_learn">
				<li class="col-md-12"><strong style="font-size: 17px;">短信内容：</strong><textarea style="width:450px; height:200px; overflow:hidden; border:solid 1px #a49c9c; border-radius:7px; resize:none;" name="" rows="10" cols="20">${smsNewsResultVO.content }</textarea></li>
			<%-- 	<li class="col-md-4">
					<strong style="font-size: 17px;">系统来源：</strong>
					
						<select name="systemCode" id="systemCode"  >
						<option value="">全部</option>
							<c:forEach items="${SmsSystems}" var="SmsSystems" varStatus="ext">
						  <option value="${SmsSystems.code }" <c:if test="${smsNewsResultVO.systemCode==SmsSystems.code}">selected</c:if>  >${SmsSystems.name }</option>
						</c:forEach>
						</select><br>
				</li>
				<li class="col-md-4">
					<strong style="font-size: 17px;">通道来源：</strong>
					
						<select name="channelCode" id="channelCode" style="border-radius:3px;" >
						<option value="">全部</option>
							<c:forEach items="${channels}" var="channels" varStatus="ext">
						  <option value="${channels.code }" <c:if test="${smsNewsResultVO.channelCode==channels.code}">selected</c:if>   >${channels.name }</option>
						</c:forEach>
						</select>
				</li> --%>
				
				
			<li class="col-md-4">
					<strong style="font-size: 17px;">通道来源：</strong>
						<select name="paper.tagId" id="paperTagId"  style="border: 1px solid #cfcfcf;width: 18%;height: 29px;margin-bottom: 12;margin-top: 12;">
							<c:forEach items="${channels}" var="channels" varStatus="ext">
						  <option value="${channels.code }" <c:if test="${smsNewsResultVO.channelCode==channels.code}">selected</c:if>   >${channels.name }</option>
						</c:forEach>
						</select>
				</li>
				<li class="col-md-12">
					<strong style="font-size: 17px;">系统来源：</strong>
						<select name="paper.tagId" id="paperTagId" style="border: 1px solid #cfcfcf;width: 6%;height: 29px;" >
							<c:forEach items="${SmsSystems}" var="SmsSystems" varStatus="ext">
						  <option value="${SmsSystems.code }" <c:if test="${smsNewsResultVO.systemCode==SmsSystems.code}">selected</c:if>  >${SmsSystems.name }</option>
						</c:forEach>
						</select>
				</li> 
				
				
				<li class="col-md-12" style="margin-top: 12;margin-bottom: 12;"><strong style="font-size: 17px;">发送时间：</strong><span style="font-size: 17px;"><fmt:formatDate value="${smsNewsResultVO.sendTime}" pattern="yyyy-MM-dd"/></span></li>
			<li class="col-md-12"><strong style="font-size: 17px;">状态：</strong>
			<c:if test="${smsNewsResultVO.result==1 }">
			<span style="color: #00CC33;font-size: 25px;">
			发送成功
			</span></c:if>
			<c:if test="${smsNewsResultVO.result==2 }">
			<span style="color: #f82105;font-size: 25px;">
			发送失败
			</span></c:if>
			</li>
			<li class="col-md-12" style="margin-top: 12;margin-bottom: 12;"><strong style="font-size: 17px;">发送对象：</strong>
			<div style="border:3px solid #000;height: 200;width: 473;border-radius: 5px;border: solid 1px #a49c9c;margin-left: 76px;margin-top: -19px;">
			<c:forEach items="${smsNewsResultVO.smsNewsInfos}" var="smsNewsInfos" varStatus="ext">
${smsNewsInfos.phone }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<c:if test="${smsNewsInfos.status==1 }"><span style="color: #00CC33;">发送成功</span></c:if><c:if test="${smsNewsInfos.status==2 }"><span style="color: #f82105;">发送失败</span></c:if><br>
			</c:forEach> 
			</div>
			<span style="font-size: 17px;margin-left: 72px;"><buttton type="button" onclick="selcetUser()" style="color: red;">查看发送失败用户</buttton></span>
			</li>
			</ul>
			</form>
			<div class="ui-button">
				<button type="button" class="btn btn-submit" onclick="saveTemplate()">确定</button>
			</div>
		
		</div>
<script type="text/javascript" src="${ctx}/resources/libs/layer/layer.js"></script>




<script type="text/javascript">


function selcetUser(){
	var id="${idl}";
	layer.open({
		type: 2,
		  title: false,
		  closeBtn: 1, //不显示关闭按钮
		  shade: [0],
		  area: ['800px', '700px'],
		  content: "${ctx}/manage/sms/selectFailUserById?idl="+id, 
		});
}



$(function(){
	var html="";
	var smsTemplate="${smsTemplate}";
	  var  factor=null;
   		 	
	if('${smsTemplate.type}'=='BUSNIESS'){
		<c:forEach items="${factors}" var="SmsParameter" varStatus="ext">
			<c:if test="${SmsParameter.factorType=='BUSNIESS' }">
			 html+="<option value='${SmsParameter.factor }'>${SmsParameter.factorName }</option>";
			</c:if>
			
		</c:forEach>
		console.log(html)
		$("#smsTemplateType").append(html);
   	  
	}else{
		<c:forEach items="${factors}" var="SmsParameter" varStatus="ext">
		<c:if test="${SmsParameter.factorType=='VALIDATE' }">
		 html+="<option value='${SmsParameter.factor }'>${SmsParameter.factorName }</option>";
		</c:if>
		
	</c:forEach>
	console.log(html)
	$("#smsTemplateType").append(html);
		
	}
	
	
	
})

function chooseLineOne(){
	  layer.open({
		  type: 2,
		  title: false,
		  closeBtn: 1, //不显示关闭按钮
		  shade: [0],
		  area: ['800px', '450px'],
		  content: "${ctx}/manage/paper/selectQuestion", 
		  end: function(){
			  
		  }
	   });
}


function addStudy(id,content,type){
	
	

	
	
	
	var hh  = $("#tableQuestion tr").length;
  /* var str='<table class="table table-agents">'+
		 '<thead>'+
		 '<tr>'+
		 '<th>'+
		 '<label class="pos-rel">'+
		 '<input type="checkbox" class="ace" id="selectAll">'+
		 '</label>'+
		 '</th>'+
		 '<th>题号</th>'+
		 '<th>考题内容</th>'+
		 '<th>考题类型</th>'+
		 '</tr>'+
		 '</thead>'+
		 '<tbody>'; */
		
		 
		 
		 var str="";
		
			   str=str+'<tr>'+
				 '<td><input style="border:0px;" name="paperQuestionList['+hh+'].questionId" readonly="readonly" type="text" class="ipt-text" value='+id+'></td>'+
				 '<td ><input style="border:0px;"  readonly="readonly" type="text" class="ipt-text" value='+content+'></td>'+
				 '<td><input style="border:0px;"  readonly="readonly" type="text" class="ipt-text" value='+type+'></td>'+
				 '<td width="10%"><input type="text" name="paperQuestionList['+hh+'].questionScore"  class="ipt-text" value="0"></td>'+
				 '<td><button type="button" onclick="remove(this)" title="移除"><i class="icon-remove" style="height: 38px;font-size: 24px;color: #f60;"></i></button></td>'+
				 '</tr>';
	 $(".tr_question").append(str);  
	 
}

function remove(obj){
	$(obj).parents("tr").remove();
}




function cancel(){
	location.href="${ctx}/manage/sms/getTemplate";
}



function seleState(bul){
	var html="";
	$("#smsTemplateType").html("");
        if(bul=='true'){
        	<c:forEach items="${factors}" var="SmsParameter" varStatus="ext">
			<c:if test="${SmsParameter.factorType=='BUSNIESS' }">
			 html+="<option value='${SmsParameter.factor }'>${SmsParameter.factorName }</option>";
			</c:if>
			
		</c:forEach>
		$("#smsTemplateType").append(html);
        }else{
        	<c:forEach items="${factors}" var="SmsParameter" varStatus="ext">
    		<c:if test="${SmsParameter.factorType=='VALIDATE' }">
    		 html+="<option value='${SmsParameter.factor }'>${SmsParameter.factorName }</option>";
    		</c:if>
    		
    	</c:forEach>
    	$("#smsTemplateType").append(html);
    		
        }
}
function addParam(){
	var es=$("#smsTemplateType").val();
	
	
			$("#content").val($("#content").val()+'['+es+']');
	
}		
		function saveTemplate(){
			var type="${type}";
			if(type=='VALIDATE'){
			location.href="${ctx}/manage/sms/getSmsVerify";
			}
		   location.href="${ctx}/manage/sms/getSmsNews";
		   			
		}
	



</script>
</body>
</html>
