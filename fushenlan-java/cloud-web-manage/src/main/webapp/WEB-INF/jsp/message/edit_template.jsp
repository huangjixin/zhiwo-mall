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
		<form id="smsTemplate" action="${ctx}/manage/sms/saveSmsTemplate" method="post">
		<input type="hidden" name="id" value="${smsTemplate.id}">
			<div class="title"><strong style="font-size: 17px;">创建模板</strong></div>
			<ul class="clearfix form_learn">
				<li class="col-md-12" style="margin-bottom: 12;"><strong style="font-size: 17px;"> 模板名称</strong><input type="text" id="title" name="title" class="ipt-text" style="width: 31%;" value="${smsTemplate.title} "></li>
				<li class="col-md-12"><strong style="font-size: 17px;">模板编码</strong><input type="text" id="code" name="code" class="ipt-text" value="${smsTemplate.code}" style="width: 31%;"></li>
					<li class="col-md-12" style="margin-top: 12;margin-bottom: 12;"><strong style="font-size: 17px;">模板类型：</strong>
					
					<label for="random"><input type="radio" onclick="seleState('true')" value="BUSNIESS" <c:if test="${smsTemplate.type=='BUSNIESS'}">checked</c:if>  id="type1" name="type"><strong  style="font-size: 15px;">业务类</strong></label>
					<label for="fixed"><input type="radio" onclick="seleState('false')" value="VALIDATE" <c:if test="${smsTemplate.type=='VALIDATE'}">checked</c:if> id="type2" name="type"><strong  style="font-size: 15px;">验证类</strong></label>
				</li>
				<li class="col-md-4" style="margin-bottom: 12;">
					
					<div class="ipt-select" style="left: 83px;">
						<select name="paper.tagId" id="smsTemplateType" style="width: 22%;">
						</select>
						<button type="button" class="btn btn-submit" onclick="addParam()">添加参数</button>
						
					</div>
					
				</li>
				
				<li class="col-md-12" ><strong style="font-size: 17px;">模板内容：<textarea id="content" style="width:450px; height:200px; overflow:hidden; border:solid 1px #a49c9c; border-radius:7px; resize:none;" name="" rows="10" cols="20">${smsTemplate.content }</textarea></li>
			<li class="col-md-12" style="margin-top: 12;"><strong style="font-size: 17px;">通道设置：</strong>
			<dl style="margin-left: 91px;">
						<dt style="font-size: 17px;">主通道：</dt>
						<dd>
						<c:forEach items="${channels}" var="SmsChannel" varStatus="ext">
							<label for="random"><input type="radio" value="${SmsChannel.code }"  <c:if test="${smsTemplate.masterChannelCode==SmsChannel.code}">checked</c:if> id="random" name="masterChannelCode">${SmsChannel.name} </label>
						</c:forEach>
						</dd><br>
						<dt style="font-size: 17px;">备用通道：</dt>
						<dd>
							<c:forEach items="${channels}" var="SmsChannel" varStatus="ext">
							<label for="random"><input type="radio" value="${SmsChannel.code }" <c:if test="${smsTemplate.slaveChannelCode==SmsChannel.code}">checked</c:if> id="random" name="slaveChannelCode">${SmsChannel.name} </label>
						</c:forEach>
						</dd>
			</dl>
			</li>
			<li class="col-md-12"><strong style="font-size: 17px;">备注：</strong><input type="text" id="remark" name="remark" class="ipt-text" value="${smsTemplate.remark }" style="width: 34%;"></li>
			</ul>
			</form>
			<div class="ui-button">
				<button type="button" class="btn btn-submit" onclick="saveTemplate()">保存</button>
				<button type="button" class="btn btn-default" onclick="cancel()">取消</button>
			</div>
			
		</div>
	

<script type="text/javascript" src="${ctx}/resources/libs/layer/layer.js"></script>




<script type="text/javascript">


$(function(){
	var html="";
	var smsTemplate="${smsTemplate}";
	  var  factor=null;
	  if(null == smsTemplate || "" == smsTemplate){
		  $("#type1").prop('checked', true);
		 <c:forEach items="${factors}" var="SmsParameter" varStatus="ext">
			<c:if test="${SmsParameter.factorType=='BUSNIESS' }">
			 html+="<option value='${SmsParameter.factor }'>${SmsParameter.factorName }</option>";
			</c:if>
			
		</c:forEach>
		$("#smsTemplateType").append(html);
	  
	  }
	  
	  
   		 	
	if('${smsTemplate.type}'=='BUSNIESS'){
		<c:forEach items="${factors}" var="SmsParameter" varStatus="ext">
			<c:if test="${SmsParameter.factorType=='BUSNIESS' }">
			 html+="<option value='${SmsParameter.factor }'>${SmsParameter.factorName }</option>";
			</c:if>
			
		</c:forEach>
		console.log(html)
		$("#smsTemplateType").append(html);
   	  
	}else if('${smsTemplate.type}'=='VALIDATE'){
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
var BUSNIESS="";
var VALIDATE=""
function addParam(){
	var groupCheckbox=$("input[name='type']");
	if(groupCheckbox.eq(0).is(":checked")){
		var s=$("#smsTemplateType").val();
		BUSNIESS=BUSNIESS+s+",";
	}
	if(groupCheckbox.eq(1).is(":checked")){
		var x=$("#smsTemplateType").val();
		VALIDATE=VALIDATE+x+",";
	}
	var es=$("#smsTemplateType").val();
			$("#content").val($("#content").val()+'\${'+es+'\}');
}		

		function saveTemplate(){
			var title=$("#title").val();
			var code=$("#code").val();
			var content=$("#content").val();
			if(null==title || title==""){
				layer.alert("请输入模板名称");
				return;
			}
			if(null==code || code==""){
				layer.alert("请输入模板编码");
				return;
			}
			if(null==content || content==""){
				layer.alert("请输入短信内容");
				return;
			}
			/* var data = $.param({"content":content}) + "&" + $("#smsTemplate").serialize();  */
			var groupCheckboxaa=$("input[name='type']");
			if(groupCheckboxaa.eq(0).is(":checked")){
				if(BUSNIESS.substr(BUSNIESS.length-1,BUSNIESS.length)==","){
					BUSNIESS=BUSNIESS.substr(0,BUSNIESS.length-1);
				}
				data=$.param({"parameterType":BUSNIESS}) + "&" +$.param({"content":content})+"&"+ $("#smsTemplate").serialize();
			}
			if(groupCheckboxaa.eq(1).is(":checked")){
				if(VALIDATE.substr(VALIDATE.length-1,VALIDATE.length)==","){
					VALIDATE=VALIDATE.substr(0,VALIDATE.length-1);
				}
				data=$.param({"parameterType":VALIDATE}) + "&"+$.param({"content":content})+"&" + $("#smsTemplate").serialize();
			}
			
			var bul='1';
			var x='1';
			 var groupCheckbox=$("input[name='masterChannelCode']");
			 var groupCheckbox1=$("input[name='slaveChannelCode']");
			 for(i=0;i<groupCheckbox.length;i++){
				 if(groupCheckbox.eq(i).is(":checked")){
					 bul='0';
				 }	
		    }
			 for(i=0;i<groupCheckbox1.length;i++){
				 if(groupCheckbox1.eq(i).is(":checked")){
					 x='0';
				 }	
		    }
				
			 if(bul=="1"){
				layer.msg("主通道不能为空"); 
				return;
			 }
			 if(x=="1"){
					layer.msg("备用通道不能为空"); 
					return;
				 }
			 $.ajax({
				  async : false,  
			      cache : false, 
			      type: 'POST',
		   	      url: "${ctx }/manage/sms/saveSmsTemplate?",
			      data:data,
		   	    //  dataType: "json",
		   	  success: function(data){
		   		 if(data.code==1){
		   			 layer.confirm(data.msg, {
		   				icon: 6,
		   			  	btn: ['确定'] //按钮
		   			}, function(){
		   				location.href="${ctx}/manage/sms/getTemplate";
		   			}); 
		   			
		   		 }else{
		   			layer.confirm(data.msg, {
		   				icon: 3,
		   			  	btn: ['确定'] //按钮
		   			}, function(){
		   				location.href="${ctx}/manage/sms/getTemplate";
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
