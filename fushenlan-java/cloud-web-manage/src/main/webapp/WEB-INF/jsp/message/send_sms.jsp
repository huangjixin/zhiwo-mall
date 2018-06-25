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
#u1385 {
    border-width: 0px;
    position: absolute;
    left: 82px;
    top: 0px;
    width: 65px;
    white-space: nowrap;
    font-size: 25px;
	color: #00CC33;
	text-align: center;
},
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
		<input type="hidden" name="id" value="${smsTemplate.id}">
			<div class="title"><strong style="font-size: 17px;">消息详情</strong></div>
			<ul class="clearfix form_learn">
				<li class="col-md-12" style="margin-bottom: 12;"><strong style="font-size: 17px;">短信内容：</strong><textarea  style="width:450px; height:200px; overflow:hidden; border:solid 1px #a49c9c; border-radius:7px; resize:none;" name="" id="centent" rows="10" cols="20"></textarea></li>
				<%-- <li class="col-md-12">
					<strong><ins>*</ins>通道来源：</strong>
					<div class="ipt-select">
						<i class="icon-arrow-down" style="left: 253px;"></i>
						<select name="channels" id="channels"  style="width: 20%;">
							<c:forEach items="${channels}" var="channels" varStatus="ext">
						  <option value="${channels.code }" <c:if test="${smsNewsResultVO.channelCode==channels.code}">selected</c:if>   >${channels.name }</option>
						</c:forEach>
						</select>
					</div> 
				</li>--%>
				
				
			<li class="col-md-12" ><strong style="font-size: 17px;">发送时间：</strong>
				<button type="button" class="btn btn-default" id="ts" onclick="timingSend()">定时</button>
				<button type="button" class="btn btn-submit" id="ns" onclick="nowSend()">立即</button>
				<input type="hidden" name="state" id="state" value="0">
			<%-- <input type="text" style="width:65%;" id="sendTime" name="sendTime" class="ipt-text" readonly="readonly" value="<fmt:formatDate value="${smsNewsResultVO.sendTime}" pattern="yyyy-MM-dd"/>"> --%>
			</li>
			<li class="col-md-12"  style="margin-bottom: 10;">
			<div class="analysis_sin" style="display: none;margin-left: 86px;margin-top: 12;" >
			<strong style="font-size: 18;">发送时间:</strong> <input type="text" id="startDate" placeholder="发送时间" class="Wdate" name="compulsoryCplan.startDate" value="<fmt:formatDate value="${ccvo.compulsoryCplan.startDate}" pattern="YYYY-MM-dd"/>" style="width:120px;border:1px solid #333;height: 25;" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'startDate\')}'})" >
			<select id="hh" style="width: 45px;height: 25px;border: 1px solid #333;">
			</select>时
			<select id="min" style="width: 45px;height: 25px;border: 1px solid #333;">
			</select>分
			</div>
			</li>
			<li class="col-md-4" ><strong style="font-size: 17px;">发送对象：</strong>
			 
						<button type="button" class="btn btn-sm btn-submit ipt-btnfile" onclick ="changeC('MESSAGE_FUJIAN','2','70','1')">选择文件</button>
						<a id="uploadUrl" href ="${attachment.path}" target ="_blank"><span id="uploadLocation">${attachment.originalName}</span></a>
						<button type="button" class="btn btn-submit" onclick="uploadOther()" style="height: 32px;width: 60px;background-color: #0d6de4;margin-left: 19%;">导入</button>
						
		         
			
			      <form id="personFile" style="display: none;" method="post" enctype="multipart/form-data">
                     <input type="file" name="fileName" id="fileName" onchange="upload()" >
                     <input name="category" type="hidden" id ="category">
                     <input name="fileId" id ="fileId"  type="hidden" value="">
                     <input name="url" id ="url"  type="hidden" value="">
                  </form>
			
			
			
			<%--  <form name="form1" id="bannerFile" METHOD="POST" ACTION="${ctx}/manage/sms/upLode" ENCTYPE="multipart/form-data" style="margin-left: 21%;margin-bottom: 2%;margin-top: -6%;">    
    		<input name="fileName" type="FILE" id="attach" size="50" ><button type="button" class="btn btn-submit" onclick="upload()" style="height: 27px;width: 60px;background-color: #0d6de4;margin-left: -31%;">导入</button>
  			</form> --%>
  			<strong style="margin-left: 20%;">导入文件为XLS或XLSX格式：</strong> 
			<textarea  style="width:450px; height:200px; overflow:hidden; border:solid 1px #a49c9c; border-radius:7px; resize:none;margin-top: 1%;margin-left: 19%;" id="phone" placeholder="不同号码之间用英文或中文逗号分隔"></textarea>
			</li>
			</ul>
			
			<div class="ui-button">
				<button type="button" class="btn btn-submit" onclick="sendSms()">发送</button>
			</div>
		
		</div>
<script type="text/javascript" src="${ctx}/resources/libs/layer/layer.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/datepicker/WdatePicker.js"></script>
<script src="${ctx}/resources/js/common/jquery-form.js" type="text/javascript"></script>



<script type="text/javascript">
$(function(){
	var html="";
	var mins="";
	for(var i=0;i<23;i++){
		html+='<option value="'+(i+1)+'">'+(i+1)+'<option>';
	}
	$("#hh").append(html);
	
	for(var i=0;i<59;i++){
		html+='<option value="'+(i+1)+'">'+(i+1)+'<option>';
	}
	$("#min").append(html);
})

function sendSms(){
	/* //var channels=$("#channels").val(); */
	var taskStatus =$("#state").val();
	var centent =$("#centent").val();
	var startDate =$("#startDate").val();
	var sendTime=null;
	var hh =$("#hh").val();
	if(hh.length<2){
		hh=0+hh;
	}
	var min =$("#min").val();
	if(min.length<2){
		min=0+min;
	}
	var phones =$("#phone").val();
	if(null !=startDate && startDate !=""){
		sendTime	=startDate+"  "+hh+":"+min+":00";
	}
	
	if(null==centent || centent==""){
		layer.alert("请输入短信内容");
		return;
	}
	if(null==phones || phones==""){
		layer.alert("请输入接收号码");
		return;
	}
	if(taskStatus=="1"){
		if(null==startDate || startDate==""){
			layer.alert("请选择定时发送时间");
			return;
		}
	}
	if(phones.indexOf("，")!=-1){
		var phone=phones.split("，");
		for(var i=0;i<phone.length;i++ ){
			if(phone[i].length<11){
				layer.msg(phone[i]+"该号码不合法");
				return;
			}
		}
	}
	var phone=phones.split(",");
	for(var i=0;i<phone.length;i++ ){
		if(phone[i].length<11){
			layer.msg(phone[i]+"该号码不合法");
			return;
		}
	}
	
	$.ajax({  
        async : false,  
        cache : false,  
        type: 'POST',  
        dataType : "json",  
        data : {"taskStatus":taskStatus,"phones":phones,"sendTime":sendTime,"content":centent},
        url: "${ctx}/manage/sms/sendSms",//请求的action路径  
        error: function (data) {//请求失败处理函数  
            layer.confirm(data.msg, {
   				icon: 3,
   			  	btn: ['确定'] //按钮
   			}, function(){
   				location.href="${ctx}/manage/sms/sendSmsPage";
   			}); 
        },  
        success:function(data){ //请求成功后处理函数。
        	layer.confirm(data.msg, {
   				icon: 6,
   			  	btn: ['确定'] //按钮
   			}, function(){
   				location.href="${ctx}/manage/sms/sendSmsPage";
   			}); 
        	
        	
        }  
    }); 
	
	
	
	
	
}

function timingSend(){
	$(".analysis_sin").show();
	$("#state").val("1");
	$("#ts").removeClass('btn btn-default');
	$("#ts").addClass('btn btn-submit');
	$("#ns").removeClass('btn btn-submit');
	$("#ns").addClass('btn btn-default');
}

function nowSend(){
	$(".analysis_sin").hide();
	$("#state").val("0");
	$("#ns").removeClass('btn btn-default');
	$("#ns").addClass('btn btn-submit');
	$("#ts").removeClass('btn btn-submit');
	$("#ts").addClass('btn btn-default');
	
}


function changeC(urlLocation,fileType,category,formLocation){  
	$("input#category").val(category);  //附件表中定义的附件类型 
	$("input#fileType").val(fileType);  //类型： 附件类型：1 --表示课程缩略图； 2--课程课件； 3--课程附件 
	$("input#urlLocation").val(urlLocation);  //回显位置  标签Id 
	$("input#formLocation").val(formLocation); //表单位置 
  	$("input#fileName").click();
  }




function upload(){
    var btn_index = layer.load(2);
    $("#personFile").ajaxSubmit({
    url : '${ctx}/manage/commonUploadFile',
    type :"post",
    success : function(data){
            if (data != null) {  //附件要回显
                $("input#fileId").val(data.id);
                $("span#uploadLocation").html(data.originalName);
                $("#uploadUrl").attr("href",data.path);
                $("#url").val(data.url);
                
            }
            layer.close(btn_index);
        },error:function() {
            alert("附件上传错误");
            layer.close(btn_index);
        } 
    });  
}




function uploadOther(){
	var url = $("#url").val();
	$("#personFile").ajaxSubmit({
        url : '${ctx}/manage/sms/uploadOther?url='+url,
        type :"post",
        success : function(data){
        	if(data.code=='1'){
            	var ph=$("#phone").val();
            	if(null !=ph && ph !=""){
            		ph=ph+",";
            	}
            	$("#phone").val(ph+data.data);
            }else{
            	layer.msg(data.msg);
            }
            },error:function() {
            	layer.msg(data.msg);
            } 
      });  
} 

/* function upload(){
	$("#bannerFile").ajaxSubmit({
        url : '${ctx}/manage/sms/upLode',
        type :"post",
        success : function(data){
                if(data.code=='1'){
                	var ph=$("#phone").val();
                	if(null !=ph && ph !=""){
                		ph=ph+",";
                	}
                	$("#phone").val(ph+data.data);
                }else{
                	layer.msg(data.msg);
                }
            },error:function() {
            	layer.msg(data.msg);
            } 
        });  
} */
</script>
</body>
</html>
