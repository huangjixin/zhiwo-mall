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
<link rel="stylesheet" href="${ctx}/resources/libs/ztree/zTreeStyle.css" type="text/css">
		<div class="form-detail offline_form"> 
		  <form id="inputForm"  method="post" class="form-horizontal" >
		   <input type="hidden" name="id" id="cId" value="${nvo.activity.id}"/>
		   <input type="hidden" name="specifyType" id="specifyType" value=""/>
			 <div class="title diff_btn" id="info">
				<strong>新建活动</strong>
			</div>
			<ul class="clearfix form_learn">
				<li class="col-md-12"><strong><ins>*</ins>标题：</strong><input type="text" name="title" class="ipt-text" value="${nvo.activity.title }"></li>
				
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
				    <button onclick="changeC('projectImg1','1','3','1','3')" type="button" class="btn btn-sm btn-submit ipt-btnfile">请选择</button>
				</li>
				
				<li class="col-md-12"><strong><ins>*</ins>正文：</strong><input type="text" name="content" class="ipt-text" value="${nvo.activity.content }"></li>
				
				<li class="col-md-12">
					<strong><ins>*</ins>附件：</strong>
					<button type="button" class="btn btn-sm btn-submit ipt-btnfile" onclick ="changeC('EL_LESSON_COURSEWARE','2','70','1','1')">+ 上传附件</button><br>
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
				<li class="col-md-3">
					 <input id="attendanceObjectTypeCheck" type="checkbox"  onchange="selectAllDepartment(1)"><label for="attendanceObjectTypeCheck">所有人</label>
				</li>
				<li class="col-md-3"><button type="button" class="btn btn-submit btn-radius" onclick="getDepartmentTree(2)">指定部门</button></li>
				<li class="col-md-3"><button type="button" class="btn btn-submit btn-radius" onclick="uploadPerson(3)">特定人员</button></li>
				
			</ul> 
			<ul id="treeDemo" class="ztree" style="margin-left: 350px;"></ul><!-- 弹出ztree窗口 -->
			<div class="ui-button">
			    <button type="button" class="btn btn-submit"  onclick="saveccvo(3)">保存草稿</button>
				<button type="button" class="btn btn-submit"  onclick="saveccvo(1)">发布</button>
			    <button type="button" class="btn btn-default" onclick="javascript:history.go(-1);">取消</button>
			</div>
			<div id="ActivityMemberMapping">
			</div>
			
		</form>
		</div>
		      <form id="bannerFile" style="display: none;" method="post" enctype="multipart/form-data">
			      <input type="file" name="fileName" id="fileName" onchange="upload()" >
			      <input name="category" type="hidden" id="category">
			       <input name="url" id ="url"  type="hidden" value="">
		      </form>
		       <input id="fileType" type="hidden"> <!-- 附件类型：1 --图； 2--附件  （ 图片要回显 ，文件提供下载 ）-->
               <input id="urlLocation" type="hidden"><!-- 附件回显位置  标签Id -->
               <input id="formLocation" type="hidden">
               <input id="formType" type="hidden">
<script type="text/javascript" src="${ctx}/resources/libs/ztree/jquery.ztree.core.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/ztree/jquery.ztree.excheck.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/ztree/jquery.ztree.exedit.js"></script>
<script>
function selectAllDepartment(type){
	$("#specifyType").val(type);
	 //清空隐藏域
	   $("#ActivityMemberMapping").empty();
	   var parentId = '${companyId}';
	 var html="";
	 html+='<input type="hidden" name="activityMemberMapping[0].companyId" id="" value="'+parentId+'"/>';
	   $("#ActivityMemberMapping").append(html);
}
function getDepartmentTree(type){
	$("#specifyType").val(type);
	//加载树
	$.fn.zTree.init($("#treeDemo"), setting, zNodes);
}
	 /*-----------------------------------------------树--------------------------------------------------------  */
    //设置ztree点击函数
    function zTreeOnClick(event, treeId, treeNode){
	   //获取组织id
	   var orgId = treeNode.id;
	   var orgName = treeNode.name;
	   //清空隐藏域
	   $("#ActivityMemberMapping").empty();
	   //赋值
	   var html="";
   		html+='<input type="hidden" name="activityMemberMapping[0].orgName" id="" value="'+orgName+'"/>';
   		html+='<input type="hidden" name="activityMemberMapping[0].orgId" id="" value="'+orgId+'"/>';
		$("#ActivityMemberMapping").append(html);
			//关闭树
		$("#treeDemo").empty();
		$("#treeDemo").append("已选中:"+treeNode.name);
   }
  	//设置ztree参数
	var setting = {
		view: {
			dblClickExpand: false,
			showLine: true,
			selectedMulti: false
			/* addHoverDom: addHoverDom,
			removeHoverDom: removeHoverDom,
			selectedMulti: false */
		},
		/* edit: {
			enable: true,
			editNameSelectAll: true,
			showRemoveBtn: showRemoveBtn,
			showRenameBtn: showRenameBtn
		}, */
		data: {
			simpleData: {
				enable: true 
			}
		},
		 callback: {
			onClick: zTreeOnClick,
		} 
	}; 
     var zNodes =[];
    //加载ztree数据
    $(document).ready(function(){
    		//获取代理人company_id作为parent_id
    		var parentId = '${companyId}';
			$.ajax({
		  	      type: 'post',
		  	      data :{"parentId":parentId},
		  	  	  url: "${ctx}/manage/listChildOrganization",
		  	  	  dataType:"json",
		  	  	  success: function(result) {
		  	  		$.each(result,function(index,organization){
    					var zNode = {id:organization.id,pId:organization.parentId,name:organization.cnName,layer:organization.layer,rootId:organization.rootId, open:false};
    					zNodes.push(zNode);
    				});
		  		  }
		  	  	})
	});

/*-----------------------------------------------附件上传--------------------------------------------------------  */
	//打开上传人员输入框
function uploadPerson(type){
	$("#specifyType").val(type);
	
	//判断是否勾选所有人
		if($("#attendanceObjectTypeCheck").prop("checked")){
			layer.msg("请先去除勾选所有人");
			return;
		}
	//清除ul
	$("#treeDemo").empty();
	//添加上传和解析按钮
	$("#treeDemo").append('<button type="button" class="btn btn-sm btn-submit" onclick ="changeC(\'EL_LESSON_COURSEWARE\',\'2\',\'70\',\'1\',\'2\')">上传excel</button>');
	$("#treeDemo").append('<a id="uploadUrl" href ="${attachment.path}" target ="_blank"><span id="uploadLocation">${attachment.originalName}</span></a>');
	$("#treeDemo").append('<button type="button" class="btn btn-sm btn-submit" onclick="uploadOther()" >解析excel</button>');
}

	//将已上传附件内容解析进页面方法
	function uploadOther(){
	var url = $("#url").val();
	$("#bannerFile").ajaxSubmit({
        url : '${ctx}/manage/activity/resolveActivity',
        type :"post",
        success : function(data){
        	layer.msg("解析成功");
        	   //清空隐藏域
     	   $("#ActivityMemberMapping").empty();
        	var html="";
        	$.each(data.data,function(i,row){
        		html+='<input type="hidden" name="activityMemberMapping['+i+'].acountName" id="" value="'+row.acountName+'"/>'
        		html+='<input type="hidden" name="activityMemberMapping['+i+'].agentCode" id="" value="'+row.agentCode+'"/>'
        		html+='<input type="hidden" name="activityMemberMapping['+i+'].companyName" id="" value="'+row.companyName+'"/>'
        		html+='<input type="hidden" name="activityMemberMapping['+i+'].branchCompanyName" id="" value="'+row.branchCompanyName+'"/>'
        		html+='<input type="hidden" name="activityMemberMapping['+i+'].levelName" id="" value="'+row.levelName+'"/>'
        		html+='<input type="hidden" name="activityMemberMapping['+i+'].orgName" id="" value="'+row.orgName+'"/>'
        	})
        	$("#ActivityMemberMapping").append(html);
            },
            error:function(data) {
            	layer.msg(data.msg);
            } 
      });  
}  


        
     function saveccvo(sendType){
    	    var pathId = $("#fileId1").val();
    	    var FilePathId = $("#fileId2").val();
	        $.ajax({
			type : "post",
			data:$("#inputForm").serialize(),
			url : "${ctx}/manage/activity/saveActivity?pathId="+pathId+"&FilePathId="+FilePathId+"&sendType="+sendType,
			dateType: "json",
			success : function(data) {
				if (data.code ==1) {
					layer.alert("保存成功", {icon: 1}, function(){
						parent.location.href="${ctx}/manage/activity/activityList";
					});
				}else {
					layer.alert("保存失败", {icon: 5});
				}
			}
		  });
     }
       
   $(function(){
	   var contentType = $("#contentType").val();
   	   if(contentType ==2){
   		  document.getElementById("san").style.display='block';
        }else{
   		  document.getElementById("san").style.display='none';
   	   }
   	   
   	  var groupCheckbox=$("input[name='isSecret']");
      if(groupCheckbox.eq(1).is(":checked")){
   	  document.getElementById("wShare").style.display='none';
   	  }
      
      var groupCheckboxOther=$("input[name='signUpSet']");
      if(groupCheckboxOther.eq(0).is(":checked")){
   	  document.getElementById("limitNumber").style.display='none';
   	  }
      
   })  
       
     function changeC(urlLocation,fileType,category,formLocation,type){  
		$("input#category").val(category);  //附件表中定义的附件类型 
		$("input#fileType").val(fileType);  //类型： 附件类型：1 --表示课程缩略图； 2--课程课件； 3--课程附件 
		$("input#urlLocation").val(urlLocation);  //回显位置  标签Id 
		$("input#formLocation").val(formLocation); //表单位置 
		$("input#formType").val(type); 
	  	$("input#fileName").click();
	  }

  	function upload(){
 		var btn_index = layer.load(2);
        var fileType =  $("input#fileType").val();  //类型：图片要回显 
        var urlLocation = $("input#urlLocation").val();  //图片回显位置  Id 
        var formLocation = $("input#formLocation").val(); //表单位置 
        var formType = $("input#formType").val(); 
 		 $("#bannerFile").ajaxSubmit({
            url : '${ctx}/manage/commonUploadFile',
            type :"post",
            success : function(data){
	            	if (data != null) {  //图片要回显
	            		layer.msg("上传成功");
            			if (fileType == '1') {  //图 (单个)
            				$("#fileId1").val(data.id);
            				$("#path").val(data.path);
                            $("#"+urlLocation).attr("src",data.path); 
                            
                        } else if (fileType == '2'){ //附件 (单个)
                        	$("#fileId2").val(data.id);
                        	$("#attachmentPath").val(data.path);
                        	$("#bak1").val(data.originalName);
                        	$("#url").val(data.url);
                        	if(formType=='1'){
                       		 $("#"+urlLocation).html(data.originalName);
                       	}
                            $("#A_"+urlLocation).attr("href",data.path);
                        } else if (fileType == '3') { //课程附件 （可以多个）
                        	var fileIds = $("#form"+formLocation+" input[name=fileId3]").val();
                        	if (fileIds != '') {
                                fileIds =fileIds+','+data.id;
                                $("#form"+formLocation+" input[name=fileId3]").val(fileIds);
                            } else {
                            	$("#form"+formLocation+" input[name=fileId3]").val(data.id);
                            }
                        	var str = "&nbsp;&nbsp;";
                        	str = str + '<a href="'+data.path+'" target="_blank">'
                        		+"<span>"+data.originalName+"</span></a>";
                        	$("#form"+formLocation+" li#materialLi").append(str);	
                        }
	            	}
	            	layer.close(btn_index);
	            },error:function() {
	            	alert("上传错误");
	            	layer.close(btn_index);
	            } 
            });  
		}
    
  	$("#signUpOn").click(function(){
   	 document.getElementById("limitNumber").style.display='none';
    });
  	$("#signUpOff").click(function(){
   	 document.getElementById("limitNumber").style.display='block';
    });
  	
     $("#on").click(function(){
    	 document.getElementById("wShare").style.display='none';
     });
     
     $("#off").click(function(){
    	 document.getElementById("wShare").style.display='block';
     });
    
    function address(){
    	var contentType = $("#contentType").val();
    	if(contentType ==2){
    		document.getElementById("san").style.display='block';
    	}
    	else if(contentType ==1){
    		document.getElementById("san").style.display='none';
    	}
    }
    
</script>
</body>
</html>
