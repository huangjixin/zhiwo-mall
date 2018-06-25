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

<script type="text/javascript"	src="${ctx}/resources/libs/layer/layer.js"></script>
<style>
.remind{width:800px; overflow:hidden;}
.remind div:nth-of-type(5n){margin-bottom:10px;}
.remind div:nth-of-type(5n+1){margin-left:0;}

	width: 180px;
	font-size: 14px;
	line-height: 36px;
	padding: 0 10px;
	border: 1px solid #dadada;
	border-radius: 3px;
}
.fl{float:left;}
.pad-left-20{padding-left:20px;}
.pad-right-20{padding-right:20px;}
.mar-bottom-20{margin-bottom:20px;}
.isNot{
    margin-left:50px;
}
.border-stage {
		border: 1px solid #000!important;
		text-align: center;
	}
	.remove-middle-border {
		padding-left: 0!important;
	}
	.border-stage {
		border: 1px solid #000!important;
		text-align: center;
	}
	.remove-middle-border .border-stage:nth-child(2) {
		border-left: none!important;
		border-right: none!important;
	}
	p{
	text-align: left;
	text-indent: 2em;
	}
	[type="radio"]{
	margin-left:3px;
	}
	[class="col-md-6 border-stage"]{
	height: 150px;
	
	}
	div span{
	weight:160px;
	}
	
	.sc{
	vertical-align: middle;
	position: relative;
	right: 0px !important;
	}
	.up{
	position: relative;
	right:0px;
	}
	.i{
	color: red;
    font-size: 20px;margin-right: 9px;
	}
	.down{
	position: relative;
	}
	.aButton{
	margin-top:15px;
	}
	.marL{
		margin-left:10%;
	}
	.btn-submit:hover{
		color:#ffffff;
	}
</style>	
		<div class="form-detail offline_form"> 
		  <form id="inputForm"  method="post" class="form-horizontal" >
		   <input type="hidden" name="compulsoryCplan.id" id="cId" value="${ccvo.compulsoryCplan.id}"/>
			 <div class="title diff_btn" id="info">
				<strong>任务基本信息</strong>
			</div>
			<ul class="clearfix form_learn">
				<li class="col-md-12"><strong><ins>*</ins>任务名称：</strong><input type="text" name="compulsoryCplan.name" class="ipt-text" value="${ccvo.compulsoryCplan.name }"></li>
				<li class="col-md-12"><strong><ins>*</ins>任务说明：</strong><input type="text" name="compulsoryCplan.description" class="ipt-text" value="${ccvo.compulsoryCplan.description }"></li>
				<li style="margin-left: -134px;width: 111%;margin-top: 5px;" class="col-md-12"><strong><ins>*</ins>活动缩略图：</strong><img name="compulsoryCplan.bannerPath" id="bannerPath"  src="${at.path }" width="80" height="80">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button type="button" class="btn btn-sm btn-submit ipt-btnfile" onclick="changeC(24)">请选择</button>
			    <input type="hidden" name="offlineActivity.bannerPath" id="bannerPath2"  value="${offlineActivity.bannerPath }">
			    <input type="hidden" name="fId" id="fId"  value="${at.id }">
			    </li>
				
				
				<li class="col-md-6"><strong><ins>*</ins> 任务日期：</strong>
				      <input type="text" id="startDate" placeholder="开始时间" class="Wdate" name="compulsoryCplan.startDate" value="<fmt:formatDate value="${ccvo.compulsoryCplan.startDate}" pattern="YYYY-MM-dd"/>" style="width:120px;border:1px solid #333" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDate\')}'})" >
						&nbsp;—&nbsp;
				      <input type="text" id="endDate" placeholder="结束时间" class="Wdate" name="compulsoryCplan.endDate"   value="<fmt:formatDate value="${ccvo.compulsoryCplan.endDate}" pattern="YYYY-MM-dd"/>" style="width:120px;border:1px solid #333" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startDate\')}'})">
				</li> 
				<li class="col-md-4" style="margin-left: 30px;"><strong><ins>*</ins>任务类型：</strong>
				        <select name="compulsoryCplan.tagId" style="width:120px;border:1px solid #333" >
							<option value="">全部</option>
							<option value="1" <c:if test="${ccvo.compulsoryCplan.tagId eq '1'}">selected</c:if>>初审面试</option>
							<option value="2" <c:if test="${ccvo.compulsoryCplan.tagId eq '2'}">selected</c:if>>甄选面试</option>
							<option value="3" <c:if test="${ccvo.compulsoryCplan.tagId eq '3'}">selected</c:if>>决定面试</option>
						</select>
				</li>
			</ul> 
		
			
			<div class="title" id="xianzhi"><strong>任务参与限制</strong>
			   <c:forEach items="${ccvo.planAuthorityList}" var="planAuthority" varStatus="extx">
			     <div style="display:none";>
    	         <input type="hidden" name="planAuthorityList[${extx.index}].authorityType" value="${planAuthority.authorityType}">
    	         <input type="hidden" name="planAuthorityList[${extx.index}].associateId" value="${planAuthority.associateId}">
			     </div>
			   </c:forEach>
			</div>
			
			<ul class="clearfix">
				<!-- <li class="col-md-3"><strong>人员限制：</strong><button onclick="choosePerson()" type="button" class="btn btn-submit overt_button">选择</button></li> -->
				
				<li class="col-md-12 zlgl_share"><strong>发布对象：</strong>
				    <label ><input type="radio" value="0" id="allow" name="compulsoryCplan.openRange" <c:if test="${ccvo.compulsoryCplan.openRange==0 }">checked="checked"</c:if>>所有人
					</label>
					<label ><input type="radio" value="1" id="notAllow" onclick="choosePerson()" name="compulsoryCplan.openRange" <c:if test="${ccvo.compulsoryCplan.openRange==1 }">checked="checked"</c:if>>特定对象
					</label>
					<label ><input type="radio" value="2" id="notTwoAllow" name="compulsoryCplan.openRange" <c:if test="${ccvo.compulsoryCplan.openRange==2 }">checked="checked"</c:if>>游客及登录用户
					</label>
				</li>
				
				
				
				<li class="col-md-12 zlgl_share"><strong>任务奖励：</strong>
					<label for="integra">
						<input type="checkbox" value="1" id="integra" <c:if test="${ccvo.compulsoryCplan.isRewardPoint==1 }">checked="checked"</c:if> name="compulsoryCplan.isRewardPoint">可获积分&nbsp;
						<input type="text" name="compulsoryCplan.rewardPoint" onclick="rewardPoint()" id="ral" class="ipt-text" value="${ccvo.compulsoryCplan.rewardPoint }"  style="max-width: 130px;">&nbsp;积分
					</label>
					<label for="award">
						<input type="checkbox" id="isRewardCertification" value="1" <c:if test="${ccvo.compulsoryCplan.isRewardCertification==1 }">checked="checked"</c:if> id="award" name="compulsoryCplan.isRewardCertification">证书奖励&nbsp;
						<input type="text"  id="certificationId" name="compulsoryCplan.certificationId"  class="ipt-text" value="${ccvo.compulsoryCplan.certificationId }"  style="max-width: 130px;">
					</label>
					<label for="qualify" >
						<input type="checkbox" value="1" id="qualify" <c:if test="${ccvo.compulsoryCplan.isRewardQualification==1 }">checked="checked"</c:if> name="compulsoryCplan.isRewardQualification">DMS资格&nbsp;
						<select style="width:120px;border:1px solid #333" id="zige" value="${ccvo.compulsoryCplan.qualificationId }" name="compulsoryCplan.qualificationId">
						    <option value="">请选择</option>
							<option value="0" <c:if test="${ccvo.compulsoryCplan.qualificationId eq '0'}">selected</c:if>>保险</option>
							<option value="1" <c:if test="${ccvo.compulsoryCplan.qualificationId eq '1'}">selected</c:if>>技巧</option>
							<option value="2" <c:if test="${ccvo.compulsoryCplan.qualificationId eq '2'}">selected</c:if>>产品</option>
							<option value="3" <c:if test="${ccvo.compulsoryCplan.qualificationId eq '3'}">selected</c:if>>沟通</option>
						</select>
					</label>
				</li>
				<li class="col-md-12 zlgl_share">
					<strong>是否允许计划过期后继续任务：</strong>
					<label for="allow" style="padding-left: 120px;"><input type="radio" value="1" id="allow" name="compulsoryCplan.isAllowExpired" <c:if test="${ccvo.compulsoryCplan.isAllowExpired==1 }">checked="checked"</c:if>>允许
					</label>
					<label for="notAllow"><input type="radio" value="0" id="notAllow" name="compulsoryCplan.isAllowExpired" <c:if test="${ccvo.compulsoryCplan.isAllowExpired==0 }">checked="checked"</c:if>>不允许
					</label>
				</li>
				<li class="col-md-12 zlgl_share" >
					<strong>过期提醒：</strong>
					<label for="on"><input type="radio" value="0" id="on" name="compulsoryCplan.isExpiredAlarm" <c:if test="${ccvo.compulsoryCplan.isExpiredAlarm==0 }">checked="checked"</c:if>>关闭
					</label>
					<label for="off"><input type="radio" value="1" id="off" name="compulsoryCplan.isExpiredAlarm" <c:if test="${ccvo.compulsoryCplan.isExpiredAlarm==1 }">checked="checked"</c:if>>开启
					</label>
					<c:if test="${ccvo.compulsoryCplan.id == null}">
					   <div class="remind" style="padding: 0" name="tixing">
					  <!--  <div name="olg" style="margin-left:10px;margin-top:5px;"> -->
					    <div name="olg">
						<span>第1次提醒：</span><br>
						<span>过期前&nbsp;<input type="text" class="ipt-text"  name="expiredAlarmList[0].beforeDay1" value=""  style="max-width: 50px">&nbsp;天</span>
						 </div>                                                                          
						<button type="button" class="addStation" id ="addlg" onclick="addInput(this)">+</button>
					  </div>
					</c:if>
					<c:if test="${ccvo.compulsoryCplan.id != null && ccvo.compulsoryCplan.id!=''}">
					   <div class="remind" style="padding: 0" name="tixing">
					    <c:forEach items="${ccvo.expiredAlarmList}" var="expiredAlarm" varStatus="ext">
					    <div name="olg" style="margin-left:10px;margin-top:5px;" >
						<span>第${ext.index+1}次提醒：</span><br>
						<span>过期前&nbsp;<input type="text" class="ipt-text" name="expiredAlarmList[${ext.index}].beforeDay1" value="${expiredAlarm.beforeDay1 }" style="max-width: 50px">&nbsp;天</span>
						</div>
						</c:forEach>
						<button type="button"  id ="addlg" class="addStation" onclick="addInput(this)">+</button>
					   </div>
					</c:if>
				</li>
				
				<li class="col-md-12 zlgl_share">
					<strong>所属分类：</strong>
					<select style="width:120px;border:1px solid #333" id="zige" value="${ccvo.compulsoryCplan.dictCode }" name="compulsoryCplan.dictCode">
						    <option value="">请选择</option>
						    <c:forEach var="dictionary" items="${dList }">
									<option value="${dictionary.code}" <c:if test='${ccvo.compulsoryCplan.dictCode eq dictionary.code}'>selected="selected"</c:if> >${dictionary.cnName}</option>
							</c:forEach>
					</select>
				</li> 
				
				<li class="col-md-12 zlgl_share">
					<strong>顺序学习：</strong>
					<label for="allow" style="padding-left: 120px;"><input type="radio" value="0" id="allow" name="compulsoryCplan.isSeq" <c:if test="${ccvo.compulsoryCplan.isSeq==0 }">checked="checked"</c:if>>不需要</label>
					<label for="notAllow"><input type="radio" value="1" id="notAllow" name="compulsoryCplan.isSeq" <c:if test="${ccvo.compulsoryCplan.isSeq==1}">checked="checked"</c:if>>需要</label></br>
					注：阶段型计划顺序学习设置为否时仅指阶段内可以分顺序学习，阶段仍需要按照顺序完成
				</li>
				
			  </ul>
			 
			<div class="title"><strong>必修任务详情</strong></div>
			<div class="nav-tabs" name="planTypeOther">
			<c:if test="${ccvo.compulsoryCplan.id == null}">
			     <input type="hidden" name="compulsoryCplan.planType" id="planType" value="1"/>
			</c:if>
			<c:if test="${ccvo.compulsoryCplan.id != null && ccvo.compulsoryCplan.id!=''}">
			     <input type="hidden" name="compulsoryCplan.planType" id="planType" value="${ccvo.compulsoryCplan.planType}"/>
			</c:if>
				<strong id="stageLabel" class="labelMark active" onclick="labelHtml('1',this)">阶段型计划</strong>
				<strong id="ordinaryLabel" onclick="labelHtml('2',this)">普通型计划</strong>
			</div>
			<div class="tab-pane active" id="stage">
				<div class="title"><strong>课程</strong><button type="button"></button></div>
				   <ul id="stageUl" class="edit clearfix xxjh_add">
				      <c:if test="${ccvo.compulsoryCplan.id == null}">
						<li name="stageLi" id="stageLi1" class="col-md-12 remove-middle-border">
							<div class="col-md-3 border-stage date" style="height: 180px;" name="stageNum">
								<span name="spanStageNum" style="display:block; padding-top:40px;">第一阶段</span>
							</div>
							<div class="col-md-6 border-stage" name="classDiv" style="overflow-y:auto;height: 180px;">	
							        
							</div>
							<div class="col-md-3 border-stage" style="height: 180px;">
								<div class="aButton"><a class="btn-submit btn-sm btn " href="javascript:courseManager('1','stageLi1')"  title="添加线上课程">+添加线上课程</a></div>
								<div class="aButton"><a class="btn-submit btn-sm btn " href="javascript:courseManager('0','stageLi1')"  title="添加线下课程">+添加线下课程</a></div>
							    <div class="aButton"><a class="btn-submit btn-sm btn " href="javascript:addPaper('stageLi1')"  title="添加试卷">+添加试卷</a></div>
								<div>	
									 <button type="button" name="orev" title="删除阶段" class="learn_remove remove-link" style="vertical-align: middle;" onclick="removeStage('stageLi1')"><i class="icon-remove" style="color: red;font-size: 20px;margin-right: 9px"></i></button>
									 <button type="button" title="升序" class="learn_upper" onclick="moveUp('stageLi',1)"><img src="${ctx }/resources/imgs/u11115.png"></button>
									 <button type="button" title="降序" onclick="moveDown('stageLi',1)"><img src="${ctx }/resources/imgs/u11119.png"></button>
								</div>
							</div>
						</li>
					</c:if>
				</ul>
			    
				<button type="button" class="btn btn-submit" onclick="addStage();">+ 添加阶段</button>
			</div>
			
			
			<div class="tab-pane" id="ordinary">
				<ul class="edit clearfix">
					<li class="col-md-12">
						<strong>课程：</strong>
						<a class="btn-submit btn-sm btn " href="javascript:courseManager('1','ordinaryOnLine')"  title="添加线上课程">+添加线上课程</a>
						<a class="btn-submit btn-sm btn " href="javascript:courseManager('0','ordinaryOnLine')"  title="添加线下课程">+添加线下课程</a>
						<a class="btn-submit btn-sm btn " href="javascript:addPaper('ordinaryOnLine')"  title="添加试卷">+添加试卷</a>
						<ol id="ordinaryOnLine">
						    
						</ol>
					</li>
				</ul>
			</div>
			</form>
			<div class="ui-button">
				<button type="button" class="btn btn-submit"  onclick="saveccvo()">保存</button>
			    <button type="button" class="btn btn-default" onclick="javascript:history.go(-1);">取消</button>
			</div>
		</div>
		
		<form id="bannerFile" style="display: none;" method="post" enctype="multipart/form-data">
            <input type="file" name="fileName" id="fileName" onchange="upload()" >  <!-- name值要填fileName -->
            <input name="category" type="hidden" id="category">
        </form>
		
		
<script type="text/javascript" src="${ctx}/resources/libs/datepicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common/jquery-form.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/libs/datepicker/skin/wdatepicker.css">
<script>



function upload(){
    $("#bannerFile").ajaxSubmit({
       url : '${ctx}/manage/commonUploadFile',
       type :"post",
       success : function(data){
               if (data != null) {  //图片要回显
                   $("#fId").val(data.id);
                   $("img[name='compulsoryCplan.bannerPath']").attr("src",data.path);
                   $("input#bannerPath2").val(data.path);
               }
           },error:function() {
              layer.msg("上传错误");
           } 
       });  
   }

function changeC(category){  
   $("input#category").val(category);  //附件表中定义的附件类型 
   $("input#fileName").click();
}

        $(function(){
    	      var html = "";
    		  var count = 0;
    		  if("${not empty cpList}" == "true"){
    			  if("${ccvo.compulsoryCplan.planType}"==1){
    				  $("#stage").addClass("tab-pane active");
    				  $("#stageLabel").addClass("labelMark active");
    				  for(var ext=0;ext<"${str}"; ext++){
    		        html+='<li name="stageLi" id="stageLi'+(ext*1+1)+'" class="col-md-12 remove-middle-border">'+
						'<div class="col-md-3 border-stage date" style="height: 180px;" name="stageNum">';
						'<span name="spanStageNum" style="display:block; padding-top:40px;">';
						var mark =(ext*1+1);
						if(mark>10){
							mark = mark.toString();
							html +='第'+N[10]+N[mark[1]]+'阶段';
						}else{
							html +='第'+N[mark]+'阶段';
						}
						html+='</span>';
						html+='</div>';
						html+='<div class="col-md-6 border-stage" name="classDiv" style="overflow-y:auto;height: 180px;">';
						<c:forEach var="pc" items="${cpList}" varStatus="idx">
						    if(mark=="${pc.stage}"){
						      html +='<div name="courseDiv" class="col-md-8" id="courseDiv'+(count+1)+'" style="margin-left:10%;width:80%;">'+
						        '<input type="hidden" id="${pc.associateId}" name="planCourseList['+count+'].associateId" value="${pc.associateId}"/>'+
							    '<input type="hidden" name="planCourseList['+count+'].associateType" value="${pc.associateType}"/>'+
								'<input type="hidden" name="planCourseList['+count+'].seq" value="${pc.seq}"/>'+
								'<input type="hidden" name="planCourseList['+count+'].stage" value="${pc.stage}"/>'+
								'<span style="margin-right:20px;" name="courseName">${pc.name}</span>';
						        if("${pc.isCompulsory}"=="0"){
									html +='<label style="margin-right:20px;"><input type="radio" name="planCourseList['+count+'].isCompulsory" value="0" checked />选修</label>'+
									'<label style="margin-right:20px;"><input name="planCourseList['+count+'].isCompulsory" type="radio" value="1"/>必修</label>';
								}else if("${pc.isCompulsory}"=="1"){
									html +='<label style="margin-right:20px;"><input type="radio" name="planCourseList['+count+'].isCompulsory" value="0" />选修</label>'+
									'<label style="margin-right:20px;"><input name="planCourseList['+count+'].isCompulsory" type="radio" value="1" checked />必修</label>';
								}else{
									html +='<label style="margin-right:20px;"><input type="radio" name="planCourseList['+count+'].isCompulsory" value="0" />选修</label>'+
									'<label style="margin-right:20px;"><input name="planCourseList['+count+'].isCompulsory" type="radio" value="1" />必修</label>';
								}
						        html +=	'<button type="button" title="移除课程" class="learn_remove sc" onclick="removeCourse(\'courseDiv'+(count+1)+'\',\'stageLi${classPlanVo.stage}\')" style="margin-right:20px;"><i class="icon-remove i" ></i></button>'+
								'<button type="button" title="升序" class="learn_upper up" onclick="moveUp(\'courseDiv\',\'courseDiv'+(count+1)+'\')"><img src="${ctx }/resources/imgs/u11115.png"></button>'+
								'<button type="button" title="降序" class="down" onclick="moveDown(\'courseDiv\',\'courseDiv'+(count+1)+'\',\'stageLiMark\')"><img src="${ctx }/resources/imgs/u11119.png"></button>'+
								'</div>';
								count++;
						    }
						</c:forEach>
						html+='</div>'+
						'<div class="col-md-3 border-stage" style="height: 180px;">'+
						'<div class="aButton"><a class="btn-submit btn-sm btn " href="javascript:courseManager(\'1\',\'stageLi'+mark+'\')"  title="添加线上课程">+添加线上课程</a></div>'+
						'<div class="aButton"><a class="btn-submit btn-sm btn " href="javascript:courseManager(\'0\',\'stageLi'+mark+'\')"  title="添加线下课程">+添加线下课程</a></div>'+
					    '<div class="aButton"><a class="btn-submit btn-sm btn " href="javascript:addPaper(\'stageLi'+mark+'\')"  title="添加试卷">+添加试卷</a></div>'+
						'<div>'+
						'<button type="button" name="orev" title="删除阶段" class="learn_remove remove-link" onclick="removeStage(\'stageLi'+mark+'\')" style="vertical-align: middle;"><i class="icon-remove" style="color: red;font-size: 20px;margin-right: 9px"></i></button>'+
						'<button type="button" title="升序" class="learn_upper" onclick="moveUp(\'stageLi\',\'stageLi'+mark+'\')"><img src="${ctx }/resources/imgs/u11115.png"></button>'+
						'<button type="button" title="降序" onclick="moveDown(\'stageLi\',\'stageLi'+mark+'\')"><img src="${ctx }/resources/imgs/u11119.png"></button>'+
						'</div>'+
						'</div>'+
						'</li>';
    				  }
    				  $("#stageUl").append(html);
    			  }else if("${ccvo.compulsoryCplan.planType}"==0){
    				  $("#ordinary").addClass("tab-pane active");
    				  $("#stageLabel").removeClass("active labelMark");
    				  $("#ordinaryLabel").addClass("active");
    				  $("#stage").removeAttr("class");
    				  $("#stage").attr("class","tab-pane");
    				  
						<c:forEach var="pc" items="${cpList}" varStatus="idx">
						html+= '<li style="padding-left: 0" name="courseDiv" id="isOnLine'+(count+1)+'" >'+
						'<input type="hidden" id="${pc.associateId}" name="planCourseList['+count+'].associateId" value="${pc.associateId}"/>'+
					    '<input type="hidden" name="planCourseList['+count+'].associateType" value="${pc.associateType}"/>'+
						'<input type="hidden" name="planCourseList['+count+'].seq" value="${pc.seq}"/>'+
						'<input type="hidden" name="planCourseList['+count+'].stage" value="${pc.stage}"/>'+
						'<div style="width:20%;float: left;margin-left:10%;"><span style="float: left" name="courseName">${pc.name}</span></div>'+
						'<div>';
						if("${pc.isCompulsory}"=="0"){
							html+='<label><input type="radio" name="planCourseList['+count+'].isCompulsory" value="0" checked/><span style="float:left; margin-right:20px;">选修</span></label>'+
						              '<label><input name="planCourseList['+count+'].isCompulsory" type="radio" value="1" /><span style="float:left; margin-right:100px;">必修</span></label>';
						}
						else if("${pc.isCompulsory}"=="1"){
							html+='<label><input type="radio" name="planCourseList['+count+'].isCompulsory" value="0" /><span style="float:left; margin-right:20px;">选修</span></label>'+
						              '<label><input name="planCourseList['+count+'].isCompulsory" type="radio" value="1" checked/><span style="float:left; margin-right:100px;">必修</span></label>';
						}
						else{
							html+='<label><input type="radio" name="planCourseList['+count+'].isCompulsory" value="0" /><span style="float:left; margin-right:20px;">选修</span></label>'+
						              '<label><input name="planCourseList['+count+'].isCompulsory" type="radio" value="1" /><span style="float:left; margin-right:100px;">必修</span></label>';
						}
						html+='<div class="col-md-4"><button type="button" title="移除" class="learn_removed" onclick="detr(\'isOnLine'+(count+1)+'\')"><i class="icon-remove"></i></button>'+
			    		' <button type="button" title="升序" class="learn_upper" onclick="ascending(\'isOnLine'+(count+1)+'\')"><img src="${ctx}/resources/imgs/u11115.png"></button>'+
			    		' <button type="button" title="降序" onclick="descending(\'isOnLine'+(count+1)+'\')"><img src="${ctx}/resources/imgs/u11119.png"></button></div>'+
			    		'</li>';
			    		count++;
						</c:forEach>
						$("#ordinaryOnLine").append(html);
    			  }
    		  }
    		  
       });
       
       
       function labelHtml(type,obj){     //普通还是阶段
    		  if(type=='1'){
    			  $("#stage").addClass("active");
    			  $("#ordinary").removeClass("active");
    			  $("#planType").val(1);
    			  $("#stage").find("input").each(function(){
    				  $(this).removeAttr("disabled");
    			  });
    			  $("#ordinary").find("input").each(function(){
    				  $(this).attr("disabled","disabled");
    			  });
    		  }else{
    			  $("#ordinary").addClass("active");
    			  $("#stage").removeClass("active");
    			  $("#planType").val(0);
    			  $("#stage").find("input").each(function(){
    				  $(this).attr("disabled","disabled");
    			  });
    			  $("#ordinary").find("input").each(function(){
    				  $(this).removeAttr("disabled","disabled");
    			  });
    		  }
    	 }

var isTrainingNew = 0;
var courseDivNumS = 0;
var N = [
         "零", "一", "二", "三", "四", "五", "六", "七", "八", "九","十"
     ];
     
     
function removeStage(stageId){
	   $("#"+stageId).remove();
	   $("li[name='stageLi']").each(function(i){
		   $(this).find("span[name='spanStageNum']").html("第"+N[i+1]+"阶段");
		   $(this).find("input[name$='.seq']").each(function(j){
				$(this).val(j+1);
		   });
		   $(this).find("input[name$='.stage']").each(function(j){
				  $(this).val(i+1);
		   });
		   
	   })
	   var liObj = $("#stage").find("[name='courseDiv']");
	   if(liObj.length == 0){
	 	   courseDivNumS = 0;
	   }
}  
     
function removeCourse(divId,liId){
	  $("#"+divId).remove();
	  var liObj = $("#stage").find("[name='courseDiv']");
	  if(liObj.length == 0){
		  courseDivNumS = 0;
	  }else{
		  $("#"+liId).find("[name='courseDiv']").each(function(i){
			  $(this).find("input[name$='.seq']").val(i+1);
		  });  
	  }
}
function moveUp(idType,id){
	  var prevDIV = '';
	  var thisDIV = '';
	  if(idType == "stageLi"){
		  var prevMark;
		  var mark;
		  if($("#"+id).prev().length==0){
			  layer.msg("已是第一个");
			  return;
		  }else{
			  prevDIV = $("#"+id).prev();
			  thisDIV = $("#"+id);
			  mark = thisDIV.attr("data-stage");
			  prevMark = prevDIV.attr("data-stage");
			  prevDIV.find("input[name$='.stage']").each(function(){
				  $(this).val(mark);
			  });
			  thisDIV.find("input[name$='.stage']").each(function(){
				  $(this).val(prevMark);
			  });
			  prevDIV.before($("#"+id));
			  $("span[name='spanStageNum']").each(function(i){
				  $(this).text("第"+N[i+1]+"阶段");
			  })
		  }
	  }else if(idType == "courseDiv" || idType == "isOnLine"){
		  if($("#"+id).prev().length == 0){
			  layer.msg("已是第一个");
			  return;
		  }
		  prevDIV = $("#"+id).prev();
		  thisDIV = $("#"+id);
		  mark = thisDIV.find("input[name$='.seq']").val();
		  prevMark =  prevDIV.find("input[name$='.seq']").val();
		  prevDIV.find("input[name$='.seq']").val(mark);
		  thisDIV.find("input[name$='.seq']").val(prevMark);
		  prevDIV.before(thisDIV);
	  }
}
function moveDown(idType ,id){
    var markThis;
    var markNext;
	  var nextDIV;
	  var thisDIV;
	  if(idType == "stageLi"){
		  if($("#"+id).next().length==0){
			  layer.msg("已是最后一个");
			  return;
		  }
		  nextDIV = $("#"+id).next();
		  thisDIV = $("#"+id);
		  markThis = thisDIV.attr("data-stage");
		  markNext = nextDIV.attr("data-stage");
		  nextDIV.find("input[name$='.stage']").each(function(){
			  $(this).val(markThis);
		  });
		  thisDIV.find("input[name$='.stage']").each(function(){
			  $(this).val(markNext);
		  });
		  nextDIV.after($("#"+id));
		  $("span[name='spanStageNum']").each(function(i){
			  $(this).text("第"+N[i+1]+"阶段");
		  })
	  }else{
		  if($("#"+id).next().length==0){
			  layer.msg("已是最后一个");
			  return;
	  	  }
		  nextDIV = $("#"+id).next();
		  thisDIV = $("#"+id);
		  markThis = thisDIV.find("input[name$='.seq']").val();
		  markNext = nextDIV.find("input[name$='.seq']").val();
		  nextDIV.find("input[name$='.seq']").val(markThis);
		  nextDIV.after($("#"+id));
	  }
}
     
function addStage(){
	  var mark = $("li[name='stageLi']").length+1;
	  divMark = mark;
	  var html = "";
	  html +='<li name="stageLi" id="stageLi'+mark+'" data-stage="'+mark+'" class="col-md-12 remove-middle-border">'+
		'<div class="col-md-3 border-stage" style="height: 180px;" name="stageNum">'+
		'<span name="spanStageNum" style="display:block; padding-top:40px;">';
		if(mark>10){
			mark = mark.toString();
			html +='第'+N[10]+N[mark[1]]+'阶段';
		}else{
			html +='第'+N[mark]+'阶段';
		}
		html +='</span>'+
		'</div>'+
		'<div class="col-md-6 border-stage" name="classDiv" style="overflow-y:auto;height: 180px;">'+
		'</div>'+
		'<div class="col-md-3 border-stage" style="height: 180px;">'+
		'<div class="aButton"><a class="btn-submit btn-sm btn " href="javascript:courseManager(\'1\',\'stageLi'+mark+'\')"  title="添加线上课程">添加线上课程</a></div>'+
		'<div class="aButton"><a class="btn-submit btn-sm btn " href="javascript:courseManager(\'0\',\'stageLi'+mark+'\')"  title="添加线下课程">添加线下课程</a></div>'+
	    '<div class="aButton"><a class="btn-submit btn-sm btn " href="javascript:addPaper(\'stageLi'+mark+'\')"  title="添加试卷">+添加试卷</a></div>'+
		'<div>'+
		'<button type="button" name="orev" title="删除阶段" class="learn_remove remove-link" onclick="removeStage(\'stageLi'+mark+'\')" style="vertical-align: middle;"><i class="icon-remove" style="color: red;font-size: 20px;margin-right: 9px"></i></button>'+
		'<button type="button" title="升序" class="learn_upper" onclick="moveUp(\'stageLi\',\'stageLi'+mark+'\')"><img src="${ctx }/resources/imgs/u11115.png"></button>'+
		'<button type="button" title="降序" onclick="moveDown(\'stageLi\',\'stageLi'+mark+'\')"><img src="${ctx }/resources/imgs/u11119.png"></button>'+
		'</div>'+
		'</div>'+
		'</li>';
	  $("#stageUl").append(html);
}
     
var stageLiMark ;
function courseList(ids,names,type){
	 var lg = $("#ordinaryOnLine").children().length;
	  var html="";
	  var courseDivNum = 0;
	  if(courseDivNumS == 0){
		  courseDivNum = $("[name='courseDiv']").length;
	  }else{
		  courseDivNum = courseDivNumS+lg;
	  }
    var count = 0;
    var seq = $("#"+stageLiMark).find("[name='courseDiv']").length;
    if(stageLiMark != "isOnLineCourse" && stageLiMark != "ordinaryLine" && stageLiMark != "ordinaryOnLine") {
	      var stage = stageLiMark.substring(stageLiMark.length-1,stageLiMark.length);
		  for(var i = courseDivNum ; i < ids.length+courseDivNum ; i++){
			  html +='<div name="courseDiv" class="col-md-8" id="courseDiv'+(i+1)+'" style="margin-left:10%;width:80%;">'+
			    '<input type="hidden" id="'+ids[count]+'" name="planCourseList['+i+'].associateId" value="'+ids[count]+'"/>'+
				'<input type="hidden" name="planCourseList['+i+'].seq" value="'+(seq+1)+'"/>'+
				'<input type="hidden" name="planCourseList['+i+'].stage" value="'+stage+'"/>'+
				'<input type="hidden" name="planCourseList['+i+'].associateType" value="'+type+'"/>'+
				'<span style="margin-right:20px;" name="courseName">'+names[ids[count]]+'</span><label style="margin-right:20px;">'+
				'<input type="radio" name="planCourseList['+i+'].isCompulsory" value="0" checked/>选修</label><label style="margin-right:20px;"><input name="planCourseList['+i+'].isCompulsory" type="radio" value="1"/>必修</label>'+
				'<button type="button" title="移除课程" class="learn_remove sc" onclick="removeCourse(\'courseDiv'+(i+1)+'\',\''+stageLiMark+'\')" style="margin-right:20px;"><i class="icon-remove i" ></i></button>'+
				'<button type="button" title="升序" class="learn_upper up" onclick="moveUp(\'courseDiv\',\'courseDiv'+(i+1)+'\')"><img src="${ctx }/resources/imgs/u11115.png"></button>'+
				'<button type="button" title="降序" class="down" onclick="moveDown(\'courseDiv\',\'courseDiv'+(i+1)+'\')"><img src="${ctx }/resources/imgs/u11119.png"></button>'+
				'</div>';
				count++;
				seq++;
		 }
		 var courseObj = $("#"+stageLiMark).find("div[name='classDiv']");
		 courseDivNumS += count;
		 courseObj.append(html);
    }else{
  	  var countIOL = 0;
  	  for(var i = courseDivNum ; i < ids.length+courseDivNum ; i++){
  		  if(stageLiMark != "ordinaryOnLine"){
  			  type = 3;
  		  }
	    	  html+= '<li class="sameWadte" style="padding-left: 0" name="courseDiv" id="isOnLine'+(i+1)+'" >'+
	    	    '<input type="hidden" id="'+ids[count]+'" name="planCourseList['+i+'].associateId" value="'+ids[count]+'"/>'+
				'<input type="hidden" name="planCourseList['+i+'].seq" value="'+(seq+1)+'"/>'+
				'<input type="hidden" name="planCourseList['+i+'].associateType" value="'+type+'"/>'+
				'<input type="hidden" name="planCourseList['+i+'].stage" value="1"/>'+
				'<div style="width:20%;float: left;margin-left:10%;"><span style="float: left" name="courseName">'+names[ids[count]]+'</span></div>'+
	    	  	'<label><input type="radio" name="planCourseList['+i+'].isCompulsory" value="0" checked /><span style="float:left; margin-right:20px;">选修</span></label>'+
				'<label><input name="planCourseList['+i+'].isCompulsory" type="radio" value="1" /><span style="float:left; margin-right:100px;">必修</span></label>'+
	    	  	'<div><button type="button" title="移除" class="learn_removed" onclick="detr(\'isOnLine'+(i+1)+'\')"><i class="icon-remove"></i></button>'+
	    	  	' <button type="button" title="升序" class="learn_upper" onclick="ascending(\'isOnLine'+(i+1)+'\',\''+stageLiMark+'\')"><img src="${ctx}/resources/imgs/u11115.png"></button>'+
	    		' <button type="button" title="降序" onclick="descending(\'isOnLine'+(i+1)+'\',\''+stageLiMark+' \')"><img src="${ctx}/resources/imgs/u11119.png"></button></div>';
	    		if(stageLiMark == "isOnLineCourse"){
	    			html+= '<div class="col-md-12 marL"><span class="fl">任务日期：</span>'+
		    		'<input type="text" placeholder="开始时间" name="planCourseList['+i+'].startTime" class="Wdate" style="max-width: 180px;" onfocus="WdatePicker({onpicked: function(){jQuery(this).trigger(\'change\');},oncleared: function(){jQuery(this).trigger(\'change\');}})"><span class="fl">&nbsp;—&nbsp;</span>'+
		    		'<input type="text" placeholder="结束时间" name="planCourseList['+i+'].endTime" class="Wdate" style="max-width: 180px;" onfocus="WdatePicker({onpicked: function(){jQuery(this).trigger(\'change\');},oncleared: function(){jQuery(this).trigger(\'change\');}})">&nbsp;&nbsp;'+
		    		'<span class="fl pad-left-20">签到地点：</span>'+
		    		'<input class="ipt-text proTime" name="planCourseList['+i+'].signAddress" teype="text" placeholder="">';
	    		}
	    		html+= '</li>';
	    		count++;
	    		seq++;
	    		countIOL++;
  	  }
  	  courseDivNumS += countIOL;
	    $("#"+stageLiMark).append(html);
    } 
}









function courseManager(type,divId){
	  stageLiMark = divId;
	  var title = '线上课程'
	  if(type == '0'){
		  title = '线下课程'  
	  }
	  layer.open({
		  type: 2,
		  closeBtn: 0,
		  title: title,
		  shadeClose: true,
		  shade: 0.8,
		  area: ['900px', '500px'],
		  content: '${ctx}/manage/classPlan/courseList?isOnline='+type//iframe的url
	}); 
}
function addPaper(divId){
	  stageLiMark = divId;
	  layer.open({
		  type: 2,
		  title: '添加试卷',
		  closeBtn: 1, //不显示关闭按钮
		  shade: [0],
		  area: ['800px', '450px'],
		  content: "${ctx}/manage/classPlan/paperList", 
		  end: function(){
			  
		  }
	   });
}


    
    
    function addPersonOther(){
    	$("#xianzhi").children('div').remove();
    }
    
    function addFileId(fileId){
    	var html='<div style="display:none";>'+ 
        '<input type="hidden" id="fileId" value="'+fileId+'">'+
        '</div>';
      $("#info").append(html);
    }
    
    function addPerson(authorityType,associateId,dh){
    	var lh = $("#xianzhi").children('div').length;
    	var html='<div style="display:none";>'+ 
    	         '<input type="hidden" name="planAuthorityList['+lh+'].authorityType" value="'+authorityType+'">'+
    	         '<input type="hidden" name="planAuthorityList['+lh+'].associateId" value="'+associateId+'">'+
    	         '</div>';
    	         
    	  $("#xianzhi").append(html);     
    }

    
    function choosePerson(){
    	var hostId = $("#cId").val();
    	var personIds = "";
    	$("#xianzhi").children('div').each(function(){
    		personIds += $(this).children().first().next().val()+",";
    	});
    	personIds=personIds.substring(0,personIds.length-1);
    	  layer.open({
    		  type: 2,
    		  title: false,
    		  closeBtn: 1, //不显示关闭按钮
    		  shade: [0],
    		  area: ['1200px', '550px'],
    		  content: "${ctx}/manage/elearning/choosePerson?personIds="+personIds+"&hostId="+hostId,
    		  end: function(){
    			  
    		  }
    	   });
    } 
    
    //循环出顺序最大的那个
    function compare(){
      var ss ="" ;
   	  var temp=0;
   	  $("#tianjia").children().each(function(i){
   		  var str =  $(this).children().first().val();
   		  ss = ss+str+",";
   	  })
   	  ss=ss.substring(0,ss.length-1);
   	  var atr =  ss.split(",");
   	  for(var j=0;j<atr.length;j++ ){
   		  if(atr[j]>temp){
   			  temp= atr[j];
   		  }else{
   			  continue;
   		  }
   	  }
   	  return temp;
    }
    
    function compareOther(){
        var ss ="" ;
     	  var temp=0;
     	  $("#tianjiaLine").children().each(function(i){
     		  var str =  $(this).children().first().val();
     		  ss = ss+str+",";
     	  })
     	  ss=ss.substring(0,ss.length-1);
     	  var atr =  ss.split(",");
     	  for(var j=0;j<atr.length;j++ ){
     		  if(atr[j]>temp){
     			  temp= atr[j];
     		  }else{
     			  continue;
     		  }
     	  }
     	  return temp;
      }
    
    function biggerTianjia(){
	  var ss ="" ;
	  var temp=0;
	  $("#tianjia").children().each(function(i){
		  var arr =  $(this).children().first().attr("name").split("]");
		  var str = arr[0].slice(15);
		  ss = ss+str+",";
	  })
	  ss=ss.substring(0,ss.length-1);
	  var atr =  ss.split(",");
	  for(var j=0;j<atr.length;j++ ){
		  if(atr[j]>temp){
			  temp= atr[j];
		  }else{
			  continue;
		  }
	  }
	  return temp;
  }
  
  
  function biggerTianjiaLine(){
	  var ss ="" ;
	  var temp1=0;
	  $("#tianjiaLine").children().each(function(i){
		  var arr =  $(this).children().first().attr("name").split("]");
		  var str = arr[0].slice(15);
		  ss = ss+str+",";
	  })
	  ss=ss.substring(0,ss.length-1);
	  var atr =  ss.split(",");
	  for(var j=0;j<atr.length;j++ ){
		  if(atr[j]>temp1){
			  temp1= atr[j];
		  }else{
			  continue;
		  }
	  }
	  return temp1;
  }
  
  
  $("#certificationId").click(function(){
	  if($("#isRewardCertification").is(':checked')==false){
		  $("#certificationId").attr("readOnly","true");
		  $("#certificationId").val("");
		  layer.msg("请选中证书奖励选项");
	  }
  })
  
  $("#zige").click(function(){
	  if($("#qualify").is(':checked')==false){
		  $("#zige").val("");
		  layer.msg("请选中DMS资格选项");
	  }
  });
  
  function rewardPoint(){
	  if($("#integra").is(':checked')==false){
		  $("#ral").attr("readOnly","true");
		  layer.msg("请选中可获积分选项");
	  }
  }
  
  $("#isRewardCertification").click(function(){
	  if($(this).is(':checked')==false){
		  $("#certificationId").val("");
	  }else{
		  $("#certificationId").attr("readOnly",false);
	  }
  });
  
  $("#integra").click(function(){
	  if($(this).is(':checked')==false){
		  $("#ral").val("");
	  }else if($(this).is(':checked')==true){
		  $("#ral").attr("readOnly",false);
	  }
  });
   
  
  $("#qualify").click(function(){
	  if($(this).is(':checked')==false){
		  $("#zige").val("");
	  }
  });
  
  $("#on").click(function(){
	  $("div[name='tixing']").children("div").remove();
	   var bHtml ='<div name="olg">'+
       '<span>第1次提醒：</span><br>'+
	   '<span>过期前&nbsp;<input type="text" class="ipt-text" readonly name="expiredAlarmList[0].beforeDay1" value=""  style="max-width: 50px">&nbsp;天</span>'+
	   '</div>';
	   $("#addlg").before(bHtml);
	   
  });
  
  $("#off").click(function(){
	  $("div[name='tixing']").children("div").remove();
	   var bHtml ='<div name="olg">'+
       '<span>第1次提醒：</span><br>'+
	   '<span>过期前&nbsp;<input type="text" class="ipt-text" name="expiredAlarmList[0].beforeDay1" value=""  style="max-width: 50px">&nbsp;天</span>'+
	   '</div>';
	   $("#addlg").before(bHtml);
	   
  });
  //降序
  /* function descending(obj){
	  if($(obj).parent().next().html()){
		  
		   var arr =  $(obj).parent().children().first().attr("name").split("]");
		   var str = arr[0].slice(15);
		   var px = $(obj).parent().children().first().val();
		   var npx = $(obj).parent().next().children().first().val();
		   
		   $(obj).parent().children().first().remove();
		   var html='<input type="hidden" name="planCourseList['+str+'].seq" value="'+npx+'">';
		   $(obj).parent().prepend(html);
		   
		   var nextArr =  $(obj).parent().next().children().first().attr("name").split("]");
		   var nextStr = nextArr[0].slice(15);
		   
		   $(obj).parent().next().children().first().remove();
		   var nextHtml='<input type="hidden" name="planCourseList['+nextStr+'].seq" value="'+px+'">';
		   $(obj).parent().next().prepend(nextHtml);
		  
		  $(obj).parent().next().after($(obj).parent());
	  }else{
		  layer.msg("已经是最后一条了");
		  return false;
	  }
  } */
  //升序
/*   function ascending(obj){
	   if($(obj).parent().prev().html()){
		   var arr =  $(obj).parent().children().first().attr("name").split("]");
		   var str = arr[0].slice(15);
		   
		   var nextArr =  $(obj).parent().prev().children().first().attr("name").split("]");
		   var nextStr = nextArr[0].slice(15);
		   var px = $(obj).parent().children().first().val();
		   var npx = $(obj).parent().prev().children().first().val();
		   
		   $(obj).parent().children().first().remove();
		   var html='<input type="hidden" name="planCourseList['+str+'].seq" value="'+npx+'">';
		   $(obj).parent().prepend(html);
		   
		   $(obj).parent().prev().children().first().remove();
		   var nextHtml='<input type="hidden" name="planCourseList['+nextStr+'].seq" value="'+px+'">';
		   $(obj).parent().prev().prepend(nextHtml);
		   $(obj).parent().prev().before($(obj).parent());
		   
	  } else{
		  layer.msg("已经是第一条了");
		  return false;
	  }
	   
  } */
  
  function detr(obj){
	  $("#"+obj).remove();
	  $("#isOnLineCourse").find("input[name$='.seq']").each(function(i){
		  $(this).val(i+1);
	  });
	  if($("#isOnLineCourse").children().length==0){
		  $("#iOLCButton").remove();
	  }
  }
  
/*   function addPaperOne(sName,sId){
	  
	  var slg =  $("#tianjia").children().length;
	  var tlg =  $("#tianjiaLine").children().length;
	  var gg;
	  var tt;
	  if(slg==0){
		  gg=0;
		  tt=1;
	  }else if(tlg==0){
		  var str4 = compare();
		  tt = str4*1+1;
		  var str3 = biggerTianjia();
		  gg= str3*1+1;
	  }
	  else{
		  var str4 = compare();
		  tt = str4*1+1;
	  var str1 = biggerTianjia();
	  var str2 = biggerTianjiaLine();
	  if(str1*1>str2*1){
		  gg= str1*1+1;
	  }else{
		  gg=str2*1+1
	    }
	  }
	  var bHtml ='<li style="padding-left: 0" name="onList">'+
	             '<input type="hidden" name="planCourseList['+gg+'].seq" value="'+tt+'">'+
	             '<input type="hidden" name="planCourseList['+gg+'].associateId" value="'+sId+'">'+
	             '<input type="hidden" name="planCourseList['+gg+'].associateType" value="1">'+
	             '<input type="text"  value="'+sName+'" border="0px">'+
	             '<label style="margin-right:20px; float:left; overflow:hidden;"><input type="radio" value="0" name="planCourseList['+gg+'].isCompulsory">选修</label>'+
			     '<label style="margin-right:20px; float:left; overflow:hidden;"><input type="radio" value="1" name="planCourseList['+gg+'].isCompulsory">必修</label>'+
	             '<button type="button" title="移除" class="learn_removed" onclick="detr(this)"><i class="icon-remove"></i></button>'+
				 '<button type="button" title="升序" class="learn_upper" onclick="ascending(this)" ><img src="${ctx}/resources/imgs/u11115.png"></button>'+
				 '<button type="button" title="降序" onclick="descending(this)"><img src="${ctx}/resources/imgs/u11119.png"></button>'+
	             '</li>';
	    $("#tianjia").append(bHtml);    
  } */
  
/*   function addPaper(){
	  var scId ="";
	  var  th =  $("li[name='onList']").length;
	  if(th!=0){
		  $("li[name='onList']").each(function(i){
			  scId=scId+$(this).children().first().next().val()+",";
		  })
		  scId=scId.substring(0,scId.length-1);
	  }
	  layer.open({
		  type: 2,
		  title: false,
		  closeBtn: 1, //不显示关闭按钮
		  shade: [0],
		  area: ['800px', '450px'],
		  content: "${ctx}/manage/elearning/selectPaper?scId="+scId, 
		  end: function(){
			  
		  }
	   });
  } */

/*   function chooseLineOne(type){
	  var scId ="";
	  var  th =  $("li[name='offList']").length;
	  if(th!=0){
		  $("li[name='offList']").each(function(i){
			  scId=scId+$(this).children().first().next().val()+",";
		  })
		  scId=scId.substring(0,scId.length-1);
	  }
	  layer.open({
		  type: 2,
		  title: false,
		  closeBtn: 1, //不显示关闭按钮
		  shade: [0],
		  area: ['800px', '450px'],
		  content: "${ctx}/manage/elearning/selectLineOne?type="+type+"&scId="+scId,
		  end: function(){
			  
		  }
	   });
  } */

  /* function addStudy(sName,sId){
	  var slg =  $("#tianjia").children().length;
	  var tlg =  $("#tianjiaLine").children().length;
	  var gg;
	  var tt;
	  if(slg==0 && tlg==0){
		  gg=0;
		  tt=1;
	  }else if(tlg!=0 && slg==0){
		  var str3 =  biggerTianjiaLine();
		  gg= str3*1+1;
		  var str4 = compare();
		  tt = str4*1+1;
	  }else if(tlg==0 && slg!=0){
		  var str5 = compare();
		  tt = str5*1+1;
		  var str4 =biggerTianjia();
		  gg= str4*1+1;
	  }else{
	  var str4 = compare();
	  tt = str4*1+1;
	  var str1 = biggerTianjia();
	  var str2 = biggerTianjiaLine();
	  if(str1*1>str2*1){
		  gg= str1*1+1;
	  }else{
		  gg=str2*1+1
	    }
	  }
	  var bHtml ='<li style="padding-left: 0" name="onList">'+
	             '<input type="hidden" name="planCourseList['+gg+'].seq" value="'+tt+'">'+
	             '<input type="hidden" name="planCourseList['+gg+'].associateId" value="'+sId+'">'+
	             '<input type="hidden" name="planCourseList['+gg+'].associateType" value="2">'+
	             '<input type="text" value="'+sName+'" border="0px">'+
	             '<label style="margin-right:20px;float:left; overflow:hidden;"><input type="radio" value="0" name="planCourseList['+gg+'].isCompulsory">选修</label>'+
			     '<label style="margin-right:20px; float:left;  overflow:hidden;"><input type="radio" value="1" name="planCourseList['+gg+'].isCompulsory">必修</label>'+
	             '<button type="button" title="移除" class="learn_removed" onclick="detr(this)"><i class="icon-remove"></i></button>'+
				 '<button type="button" title="升序" class="learn_upper" onclick="ascending(this)"><img src="${ctx}/resources/imgs/u11115.png"></button>'+
				 '<button type="button" title="降序" onclick="descending(this)"><img src="${ctx}/resources/imgs/u11119.png"></button>'+
	             '</li>';
	    $("#tianjia").append(bHtml);    
  } */
  
  /* function addStudyLine(sName,sId){
	  
	  var slg =  $("#tianjia").children().length;
	  var tlg =  $("#tianjiaLine").children().length;
	  var gg ;
	  var tt ;
	  if(slg==0 && tlg==0){
		  gg=0;
		  tt=1;
	  }else if(tlg!=0 && slg==0){
		  var str3 = biggerTianjiaLine();
		  gg= str3*1+1;
		  var str4 = compareOther();
		  tt = str4*1+1;
	  }else if(slg!=0 && tlg==0){
		  var str4 = biggerTianjia();
		  gg= str4*1+1;
		  var str5 = compareOther();
		  tt = str5*1+1;
	  }else{
		  var str4 = compareOther();
		  tt = str4*1+1;
	  var str1 = biggerTianjia();
	  var str2 = biggerTianjiaLine();
	  if(str1*1>str2*1){
		  gg= str1*1+1;
	  }else{
		  gg=str2*1+1
	    }
	  }
 	  var lHtml ='<li style="padding-left: 0" name="offList">'+
 	  '<input type="hidden" name="planCourseList['+gg+'].seq" value="'+tt+'">'+
      '<input type="hidden" name="planCourseList['+gg+'].associateId" value="'+sId+'">'+
      '<input type="hidden" name="planCourseList['+gg+'].associateType" value="3">'+
      '<input type="text"  value="'+sName+'" border="0px">'+
      '<label style="margin-right:20px;float:left; overflow:hidden;"><input type="radio" value="0" name="planCourseList['+gg+'].isCompulsory">选修</label>'+
	  '<label style="margin-right:20px;float:left; overflow:hidden;"><input type="radio" value="1" name="planCourseList['+gg+'].isCompulsory">必修</label>'+
      '<button type="button" title="移除" class="learn_removed" onclick="detr(this)"><i class="icon-remove"></i></button>'+
	  '<button type="button" title="升序" class="learn_upper" onclick="ascending(this)"><img src="${ctx}/resources/imgs/u11115.png"></button>'+
	  '<button type="button" title="降序" onclick="descending(this)"><img src="${ctx}/resources/imgs/u11119.png"></button>'+
      '</li>';
	  $("#tianjiaLine").append(lHtml);
  } */

/*   function chooseOne(type){
	  var scId ="";
	  var  th =  $("li[name='onList']").length;
	  if(th!=0){
		  $("li[name='onList']").each(function(i){
			  scId=scId+$(this).children().first().next().val()+",";
		  })
		  scId=scId.substring(0,scId.length-1);
	  }
	    layer.open({
		  type: 2,
		  title: false,
		  closeBtn: 1, //不显示关闭按钮
		  shade: [0],
		  area: ['800px', '450px'],
		  content: "${ctx}/manage/elearning/selectOne?type="+type+"&scId="+scId,
		  end: function(){
			  
		  }
	   });
	 
   } */
   function addInput(obj){
	  var ss =  $(obj).parent().children("div[name='olg']").length;
	  var dd =ss*1+1;
	       var aHtml ='<div name="olg">'+
	       '<span>第'+dd+'次提醒：</span><br>'+
		   '<span>过期前&nbsp;<input type="text" class="ipt-text"  name="expiredAlarmList['+ss+'].beforeDay1" value=""  style="max-width: 50px">&nbsp;天</span>'+
		   '</div>';
		   $(obj).before(aHtml);  
   }
   function saveccvo(){
	   var fileId = $("#fileId").val();
	   var fOtherId = $("#fId").val();
	   $.ajax({
			type : "post",
			data:$("#inputForm").serialize(),
			url : "${ctx}/manage/elearning/saveCompulsoryCplan?fileId="+fileId+"&fOtherId="+fOtherId,
			dateType: "json",
			success : function(msg) {
				if (null != msg) {
					layer.alert("保存成功", {icon: 1}, function(){
						parent.location.href="${ctx}/manage/elearning/compulsoryCplanList";
					});
				}else {
					layer.alert("保存失败", {icon: 5});
				}
			}
		});
   }
</script>
</body>
</html>
