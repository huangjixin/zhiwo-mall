<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>

	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/style.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/font/iconfont.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/page.css">
</head>
<body>

		<div class="form-detail" id="add">
		
			<div class="title"><strong>请批量导入加入黑名单的用户手机号，文件为XLS或XLSX格式：</strong></div>
			<ul class="clearfix form_learn">
			<li style="margin-left: 9%;margin-bottom: 1%;">
			<button type="button" class="btn btn-sm btn-submit ipt-btnfile" onclick ="changeC('EL_LESSON_COURSEWARE','2','70','1')">选择文件</button>
			    <a id="uploadUrl" href ="${attachment.path}" target ="_blank"><span id="uploadLocation">${attachment.originalName}</span></a>
				<button type="button" class="btn btn-submit" onclick="uploadOther()" style="height: 32px;width: 60px;background-color: #0d6de4;margin-left: 3%;">导入</button>
				 <form id="personFile" style="display: none;" method="post" enctype="multipart/form-data">
                     <input type="file" name="fileName" id="fileName" onchange="upload()" >
                     <input name="category" type="hidden" id ="category">
                     <input name="fileId" id ="fileId"  type="hidden" value="">
                     <input name="url" id ="url"  type="hidden" value="">
                  </form>		
			 </li> 
				<li class="col-md-12"><strong style="font-size: 17;"><ins>*</ins>黑名单号码：</strong><textarea  style="width:450px; height:200px; overflow:hidden; border:solid 1px #a49c9c; border-radius:7px; resize:none;" id="smsPhone" placeholder="不同号码之间用英文或中文逗号分隔"></textarea></li>
				<li class="col-md-4" style="margin-top: 12;">
					
					<div class="ipt-select">
					<strong style="font-size: 17;" ><ins>*</ins>黑名单类型：</strong>
						<select name="smsType" id="smsType" style="width: 21%;">
							<option value="">请选择</option>
							<option value="BUSNIESS">业务类</option>
							<option value="VALIDATE" >验证类</option>
						</select>
					</div>
				</li>
				<li class="col-md-12" style="margin-top: 12;"><strong style="font-size: 17px;">备注：</strong><input type="text" id="smsRemark" name="smsRemark" class="ipt-text" value="" style="width: 34%;margin-left: 59px;"></li>
				</ul>
		
			<div class="ui-button">
				<button type="button" class="btn btn-submit" onclick="saveBluk()">保存</button>
				<button type="button" class="btn btn-default" onclick="cancel()">取消</button>
			</div>
			
		</div>
		
	

<script type="text/javascript" src="${ctx}/resources/libs/layer/layer.js"></script>
<script src="${ctx}/resources/js/common/jquery-form.js" type="text/javascript"></script>
<script type="text/javascript">

function cancel(){
	
	location.href="${ctx}/manage/sms/getSmsList";
}
function saveBluk(){
	var phones =$("#smsPhone").val();
	var remak=$("#smsRemark").val();
	var type=$("#smsType").val();
	if(null==phones || phones==""){
		layer.alert("请输入黑名单号码");
		return;
	}
	if(null==type || type==""){
		layer.alert("请选择黑名单类型");
		return;
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
   	  	  url: "${ctx }/manage/sms/saveSmsBlackUser",
   	   	  data : {"phones":phones,"remak":remak,"type":type},
   	  //dataType: "json",
   	  success: function(data){
   		  if(data.code==1){
   			  layer.confirm(data.msg, {
		   				icon: 6,
		   			  	btn: ['确定'] //按钮
		   			}, function(){
		   				location.href="${ctx}/manage/sms/getSmsList";
		   			}); 
   		  }else{
   			  layer.confirm(data.msg, {
		   				icon: 3,
		   			  	btn: ['确定'] //按钮
		   			}, function(){
		   				location.href="${ctx}/manage/sms/getSmsList";
		   			}); 
   		  }
   	  },
   	  error: function (data) {//请求失败处理函数  
   		  layer.confirm(data.msg, {
	   				icon: 3,
	   			  	btn: ['确定'] //按钮
	   			}, function(){
	   				location.href="${ctx}/manage/sms/getSmsList";
	   			}); 
	        },
   	  
   	});
	
	
	
}


function getPath(obj) {
    if(obj)
{  
    if (window.navigator.userAgent.indexOf("MSIE")>=1)
   {
       obj.select();
       return document.selection.createRange().text;
   }   
    else if(window.navigator.userAgent.indexOf("Firefox")>=1)
    {
       if(obj.files)
        {
               return obj.files.item(0).getAsDataURL();
         }
         return obj.value;
    }
   return obj.value;
}
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
            	var ph=$("#smsPhone").val();
            	if(null !=ph && ph !=""){
            		ph=ph+",";
            	}
            	$("#smsPhone").val(ph+data.data);
            }else{
            	layer.msg(data.msg);
            }
            },error:function() {
            	layer.msg(data.msg);
            } 
      });  
} 
</script>
</body>
</html>