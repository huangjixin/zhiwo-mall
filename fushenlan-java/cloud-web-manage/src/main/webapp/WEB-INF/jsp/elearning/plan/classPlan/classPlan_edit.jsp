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
<style>
.overt_selectOne{
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
<script type="text/javascript"	src="${ctx}/resources/libs/layer/layer.js"></script>
<script type="text/javascript"	src="${ctx}/resources/libs/input/input-validate.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common/jquery-form.js"></script>
		<div class="form-detail offline_form">
			<div class="title diff_btn">
				<c:if test="${classPlan.id eq null}">
				    <strong>新建班级计划</strong>
				</c:if>
				<c:if test="${classPlan.id ne null}">
				    <strong>修改班级计划</strong>
				</c:if>
				
				<!-- <button type="button" class="btn btn-sm btn-submit ipt-btnfile">选择课程</button> -->
			</div>
	<form action="${ctx}/manage/classPlan/save" id="stageForm" method="post">
			<input type="hidden" id="id" name="classPlan.id" value="${classPlan.id }"> 
			<input type="hidden" id="fileId" name="fileId" value="">
			<ul class="clearfix form_learn">
				<li class="col-md-12"><strong><ins>*</ins>班级计划名称：</strong><input type="text" name="classPlan.name" class="ipt-text notNull" value="${classPlan.name }"></li>
				<li class="col-md-12"><strong><ins>*</ins>班级计划说明：</strong><input type="text" name="classPlan.description" class="ipt-text notNull" value="${classPlan.description }"></li>
				<li class="col-md-3">
					<strong>班级计划分类：</strong>
					<div class="ipt-select">
						<i class="icon-arrow-down"></i>
						<select name="classPlan.tagId">
							<option id="0" value="0" selected>--全部--</option>
							<option id="1" value="1">初审面试</option>
							<option id="2" value="2">甄选面试</option>
							<option id="3" value="3">决定面试</option>
						</select>
					</div>
				</li>
				<li class="col-md-12 zlgl_share" id="train"><strong>培训对象：</strong>
					<label for="ksh"><input type="radio" name="classPlan.isTrainingNew" <c:if test="${classPlan.isTrainingNew ne 1 }">value="0"</c:if> <c:if test="${classPlan.isTrainingNew == 1 }">checked value="1"</c:if> id="ksh">新人</label>
					<%-- <label for="bkck"><input type="checkbox" name="classPlan.isTrainingAgent" value="1" <c:if test="${classPlan.isTrainingAgent == 1 }">checked </c:if> id="bkck">内勤</label> --%>
					<label for="jtgk"><input type="radio" name="classPlan.isTrainingStaff" <c:if test="${classPlan.isTrainingStaff ne 1 }">value="0"</c:if> <c:if test="${classPlan.isTrainingStaff == 1 }">checked value="1"</c:if> id="jtgk">外勤</label>
				</li>
				<c:if test="${classPlan.isTrainingNew == 1 }">
					<li class="col-md-12" id="isNotSign"><strong><ins>*</ins>完成课程后是否签约:</strong>
					  <label class="isNot"><input type="radio" name="classPlan.isSign" id="isSign" value="1" <c:if test="${classPlan.isSign == 1 }">checked </c:if>/>是</label>
					  <label class="isNot"><input type="radio" name="classPlan.isSign" id="notSign" value="2" <c:if test="${classPlan.isSign == 2 }">checked </c:if> />否</label>
			 		</li>
		 		</c:if> 
				<li class="col-md-4"><strong><ins>*</ins> 班级负责人：</strong>
					<!-- <input type="hidden" name="managerIds" class="ipt-text" value=""> -->
					 <c:forEach var="id" items="${accountId}">
						<input type="hidden" name="managerIds" class="ipt-text" value="${id }">
					</c:forEach>
					<input type="text" id="managerName" class="ipt-text notNull" value="${accountName }" onclick="classManager();"  readonly="readonly">
				</li>
				<li class="col-md-4" style="margin-left: 30px;"><strong><ins>*</ins>班级限额人数：</strong><input type="text" class="ipt-text notNull" name="classPlan.userLimit" value="${classPlan.userLimit }" onkeyup="ea_digit_verify(this)"></li>
				<li class="col-md-10 auto"><strong><ins>*</ins>班级计划缩略图：</strong>
	               <img id ="smallImage" src="${smallImage.path}" width="80" height="80"><input type="hidden" id="attachmentBannerOne">
	               <button onclick="changeC(24)" type="button" class="btn btn-sm btn-submit ipt-btnfile">请选择</button>
	            </li>
			</ul> 
			<div class="title"><strong>班级计划详情</strong></div>
			<div class="cycle_hd sameWadte date">
				<b>班级周期：</b>
				<input type="text" placeholder="开始时间" value="${classPlan.startDate }" name="classPlan.startDate" id="cycleStartDate" class="Wdate startDate" style="max-width: 180px;" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'cycleEndDate\')}'})">
						&nbsp;—&nbsp;
				<input type="text" placeholder="结束时间" value="${classPlan.endDate }" name="classPlan.endDate" class="Wdate endDate" style="max-width: 180px;" id="cycleEndDate" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'cycleStartDate\')}'})">
			</div>
			<div class="nav-tabs">
				<strong id="stageLabel" class="labelMark active" onclick="labelHtml('1',this)">阶段型计划</strong>
				<strong id="ordinaryLabel" onclick="labelHtml('2',this)">普通型计划</strong>
				<input type="hidden" id="planType" name="classPlan.planType" value="${classPlan.planType}" />
			</div>
			<div class="tab-pane active" id="stage">
				<div class="title"><strong>课程</strong><button type="button" title="删除考题"><!-- <i class="icon-delete"></i> --></button></div>
				<ul id="stageUl" class="edit clearfix xxjh_add">
						<li name="stageLi" id="stageLi1" class="col-md-12 remove-middle-border">
							<div class="col-md-3 border-stage date" style="height: 180px;" name="stageNum">
								<span name="spanStageNum" style="display:block; padding-top:40px;">第一阶段</span>
								<div class="sameWadte" style="overflow-y:auto;margin-top:20px"">
								<input type="text" placeholder="开始时间" value="" name="startTime" id="stageStartTime1" class="Wdate startDate" style="max-width: 100px;" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'stageEndTime1\')}'})">
								&nbsp;—&nbsp;
								<input type="text" placeholder="结束时间" value="" name="endTime" id="stageEndTime1" class="Wdate endDate" style="max-width: 100px;" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'stageStartTime1\')}'})">
								</div>
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
				</ul>
				<button type="button" class="btn btn-submit" onclick="addStage();">+ 添加阶段</button>
				
				<!-- <div class="title"><strong>线下课程</strong></div>
				<button type="button" class="btn-submit btn-sm btn" onclick="courseManager('0','isOnLineCourse')">+ 添加线下课程</button>
				<ol id="isOnLineCourse">
					<li style="padding-left: 0" class="sameWadte"> 
						<span>任务日期：</span>
						<input type="text" placeholder="开始时间" class="Wdate" style="max-width: 180px;" onfocus="WdatePicker({onpicked: function(){jQuery(this).trigger('change');},oncleared: function(){jQuery(this).trigger('change');}})">&nbsp;—&nbsp;
						<input type="text" placeholder="结束时间" class="Wdate" style="max-width: 180px;" onfocus="WdatePicker({onpicked: function(){jQuery(this).trigger('change');},oncleared: function(){jQuery(this).trigger('change');}})">&nbsp;&nbsp;
						<span>签到地点：</span>
						<input class="ipt-text proTime" type="text" placeholder="">
						<button class="addStation">+</button>
					</li>
				</ol> -->
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
					<!-- <li class="col-md-12">
						<strong>线下课程：</strong>
						<button type="button" class="btn-submit btn-sm btn" onclick="courseManager('0','ordinaryLine')">+ 添加线下课程</button>
						<ol id="ordinaryLine">
					
						</ol>
					</li> -->
				</ul>
			</div>
           <div class="title" id="xianzhi"><strong>计划限制</strong>
              <c:forEach items="${planAuthorityList}" var="planAuthority" varStatus="extx">
			     <div style="display:none">
    	         <input type="hidden" name="" value="${planAuthority.authorityType}">
    	         <input type="hidden" name="planAuthorityList[${extx.index}].authorityType" value="${planAuthority.authorityType}">
    	         <input type="hidden" name="planAuthorityList[${extx.index}].associateId" value="${planAuthority.associateId}">
			     </div>
			   </c:forEach>
           </div>
			<ul class="clearfix">
				<li class="col-md-3"><strong>开放对象：</strong>
					<input type="hidden" id="authorityName" class="ipt-text" readonly="readonly" value="${authorityName }">
					<!-- <button onclick="choosePerson()" type="button" class="btn btn-submit overt_button">选择</button> -->
					<input type="radio" id="openRange" name="classPlan.openRange" class="" value="0" checked/> 全部公开
					<input type="radio" id="openRange" name="classPlan.openRange" class="" value="1"  onclick ="choosePerson()" <c:if test="${classPlan.openRange == 1 }">checked</c:if>/> 指定用户
					<input type="radio" id="openRange" name="classPlan.openRange" class="" value="2" <c:if test="${classPlan.openRange == 2 }">checked</c:if>/> 游客及登录用户
				</li>
				<li class="col-md-12 zlgl_share"><strong>计划售价：</strong>
					<label for="free"><input type="radio" value="1" id="free" name="classPlan.isFree" <c:if test="${classPlan.isFree == 1 }">checked </c:if>>免费</label>
					<label for="charge"><input type="radio" value="0" id="charge" name="classPlan.isFree" <c:if test="${classPlan.isFree == 0 }">checked </c:if>>积分兑换&nbsp;
					<!-- onkeyup="value=value.replace(/[^\d.]/g,'')" 
					onkeyup="value=value.replace(/[^\d.]/g,'')"
					-->
					<input name="classPlan.exchangePoint" id="chargeText" type="text" class="ipt-text" value="${classPlan.exchangePoint }" style="max-width: 130px;" notNull="计划售价" maxlength="5" onkeyup="digit_verify(this)">&nbsp;积分</label>
				<li class="col-md-12 zlgl_share"><strong>计划奖励：</strong>
					<label for="integra">
						<input type="checkbox" id="integra" name="classPlan.isRewardPoint"  value="1" <c:if test="${classPlan.isRewardPoint == 1 }">checked</c:if>>可获积分&nbsp;
						<input type="text" id="integraText" class="ipt-text" value="${classPlan.rewardPoint }" name="classPlan.rewardPoint" style="max-width: 130px;" notNull="可获积分" maxlength="5" onkeyup="digit_verify(this)">&nbsp;积分
					</label>
					<label for="award">
						<input type="checkbox" id="award" name="classPlan.isRewardCertification" value="1" <c:if test="${classPlan.isRewardCertification == 1 }">checked</c:if>>证书奖励&nbsp;
						<button type="button" class="btn btn-submit overt_button" >选择
						</button>
					</label>
					<label for="qualify" >
						<input type="checkbox" id="qualify" name="classPlan.isRewardQualification" value="1" <c:if test="${classPlan.isRewardQualification == 1 }">checked </c:if>>DMS资格&nbsp;
						<select class="overt_selectOne" id="qualifySele" name="classPlan.qualificationId">
							<option id="0" value="0">保险</option>
							<option id="1" value="1">技巧</option>
							<option id="2" value="2" selected>产品</option>
							<option id="3" value="3">沟通</option>
						</select>
					</label>
				</li>
				<li class="col-md-12 zlgl_share">
					<strong>是否允许计划过期后继续任务：</strong>
					<label for="allow" style="padding-left: 120px;">
						<input type="radio" value="1" id="allow" name="classPlan.isAllowExpired" <c:if test="${classPlan.isAllowExpired == 1 }">checked</c:if>>允许
					</label>
					<label for="notAllow">
						<input type="radio" value="0" id="notAllow" name="classPlan.isAllowExpired" <c:if test="${classPlan.isAllowExpired == 0 }">checked</c:if>>不允许
					</label>
				</li>
				<li class="col-md-12 zlgl_share" id="">
					<strong>过期提醒：</strong>
					<label for="on"><input type="radio" value="1" id="on" name="classPlan.isExpiredAlarm" <c:if test="${classPlan.isExpiredAlarm == 1 }">checked</c:if>>开启
					</label>
					<label for="off"><input type="radio" value="0" id="off" name="classPlan.isExpiredAlarm" <c:if test="${classPlan.isExpiredAlarm == 0 }">checked</c:if>>关闭
					</label>
					<div class="remind" style="padding: 0" name="tixing" id="olgAxpired">
					    <div name="olg" style="margin-left:10px ;margin-top:5px;">
						<span>第一次提醒：</span><br>
						<span>过期前&nbsp;<input type="text" class="ipt-text"  name="expiredAlarmList[0].beforeDay1" value=""  style="max-width: 50px" onkeyup="ea_digit_verify(this)">&nbsp;天</span>
						 </div>
						<button type="button" class="addStation" id ="addlg" onclick="addInput(this)">+</button>
					</div>
				</li>
				<li class="col-md-3"><strong>顺序学习：</strong>
                    <input type="radio" id="isSeq" name="classPlan.isSeq" class="" value="1" checked/> 是
                    <input type="radio" id="isSeq" name="classPlan.isSeq" class="" value="0" <c:if test="${classPlan.isSeq == 0 }">checked</c:if>/> 否
                </li>
			</ul>
			<div class="ui-button">
				<!-- <button type="button" class="btn btn-submit" onclick="save()">保存</button> -->
				<button type="button" class="btn btn-submit" onclick="preview()">预览</button>
				<button type="button" class="btn btn-default" onclick="cancel()">取消</button>
			</div>
	</form>	
		</div>
		
	<form id="bannerFile" style="display: none;" method="post" enctype="multipart/form-data">
        <input type="file" name="fileName" id="fileName" onchange="upload()" >
        <input name="category" type="hidden" id ="category">
    </form>
<script type="text/javascript" src="${ctx}/resources/libs/datepicker/WdatePicker.js"></script>
<script>

function digit_verify(obj){
	obj.value=obj.value.replace(/[^\d.]/g,'');
	obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
}
function ea_digit_verify(obj){
	obj.value=obj.value.replace(/\D/g,'');
	/* obj.value=obj.value.replace(/[^\d.]/g,''); */
}

function addPersonOther(){
	$("#xianzhi").children('div').remove();
}
function addPerson(authorityType,associateId,dh){
	var lh = $("#xianzhi").children('div').length;
	var html='<div style="display:none";>'+ 
	         '<input type="hidden" name="planAuthorityList['+lh+'].authorityType" value="'+authorityType+'">'+
	         '<input type="hidden" name="planAuthorityList['+lh+'].associateId" value="'+associateId+'">'+
	         '</div>';
	  $("#xianzhi").append(html);     
}
var choosePersonType ;
function choosePerson(){
	choosePersonType = "classPlan"
	var personIds = "";
	$("#xianzhi").find("div input[name$='.associateId']").each(function(){
		personIds += $(this).val()+",";
	});
	personIds=personIds.substring(0,personIds.length-1);
	  layer.open({
		  type: 2,
		  title: false,
		  closeBtn: 1, //不显示关闭按钮
		  shade: [0],
		  area: ['1200px', '550px'],
		  content: "${ctx}/manage/classPlan/choosePerson?personIds="+personIds,
		  end: function(){
			  
		  }
	   });
} 
function addPersonName(associateName){
	associateName = associateName.substring(0,associateName.length-1);
	$("#authorityName").val(associateName);
}

  var isTrainingNew = 0;
  var courseDivNumS = 0;
  var N = [
           "零", "一", "二", "三", "四", "五", "六", "七", "八", "九","十"
       ];
  $(function(){
	  var html = "";
	  var count = 0;
	  if("${not empty classPlanList}" == "true"){
		  if("${markType}" == "stage"){
			  $("#stage").addClass("tab-pane active");
			  $("#stageLabel").addClass("labelMark active");
			  <c:forEach var="classPlanVo" items="${classPlanList}">
			  if("${classPlanVo.stage}" != ""){
			  var mark = "${classPlanVo.stage}";
			  html +='<li name="stageLi" id="stageLi${classPlanVo.stage}" data-stage="${classPlanVo.stage}" class="col-md-12 remove-middle-border">'+
				'<div class="col-md-3 border-stage" style="height: 180px;" name="stageNum">'+
				'<span name="spanStageNum" style="display:block; padding-top:40px;">';
				if(mark>10){
					mark = mark.toString();
					html +='第'+N[10]+N[mark[1]]+'阶段';
				}else{
					html +='第'+N[mark]+'阶段';
				}
				html +='</span>'+
				'<div class="sameWadte date" style="overflow-y:auto;margin-top:20px">'+
				'<input type="text" placeholder="开始时间" value="${classPlanVo.planCourseDtoFMVo[0].startTime}" name="startTime" class="Wdate startDate" style="max-width: 100px;" id="stageStartTime'+mark+'" onfocus="WdatePicker({maxDate:\'#F{$dp.$D('+'\\'+'\'stageEndTime'+mark+'\\'+'\')}\'})">'+
				'&nbsp;—&nbsp;'+
				'<input type="text" placeholder="结束时间" value="${classPlanVo.planCourseDtoFMVo[0].endTime}" name="endTime" class="Wdate endDate" style="max-width: 100px;" id="stageEndTime'+mark+'" onfocus="WdatePicker({minDate:\'#F{$dp.$D('+'\\'+'\'stageStartTime'+mark+'\\'+'\')}\'})">'+
				'</div>'+
				'</div>'+
				'<div class="col-md-6 border-stage" name="classDiv" style="overflow-y:auto;height: 180px;">';
					<c:forEach var="pc" items="${classPlanVo.planCourseDtoFMVo}" varStatus="idx">
						html +='<div name="courseDiv" class="col-md-8" id="courseDiv'+(count+1)+'" style="margin-left:10%;width:80%;">'+
					    '<input type="hidden" id="${pc.associateId}" name="planCourseDto['+count+'].associateId" value="${pc.associateId}"/>'+
					    '<input type="hidden" name="planCourseDto['+count+'].associateType" value="${pc.associateType}"/>'+
						'<input type="hidden" name="planCourseDto['+count+'].seq" value="${pc.seq}"/>'+
						'<input type="hidden" name="planCourseDto['+count+'].stage" value="${pc.stage}"/>'+
						'<input type="hidden" name="planCourseDto['+count+'].startTime" value="${pc.startTime}"/>'+
						'<input type="hidden" name="planCourseDto['+count+'].endTime" value="${pc.startTime}"/>'+
						'<span style="margin-right:20px;" name="courseName">${pc.name}</span>';
						if("${pc.isCompulsory}"=="0"){
							html +='<label style="margin-right:20px;"><input type="radio" name="planCourseDto['+count+'].isCompulsory" value="0" checked />选修</label>'+
							'<label style="margin-right:20px;"><input name="planCourseDto['+count+'].isCompulsory" type="radio" value="1"/>必修</label>';
						}else if("${pc.isCompulsory}"=="1"){
							html +='<label style="margin-right:20px;"><input type="radio" name="planCourseDto['+count+'].isCompulsory" value="0" />选修</label>'+
							'<label style="margin-right:20px;"><input name="planCourseDto['+count+'].isCompulsory" type="radio" value="1" checked />必修</label>';
						}else{
							html +='<label style="margin-right:20px;"><input type="radio" name="planCourseDto['+count+'].isCompulsory" value="0" />选修</label>'+
							'<label style="margin-right:20px;"><input name="planCourseDto['+count+'].isCompulsory" type="radio" value="1" />必修</label>';
						}
						html +=	'<button type="button" title="移除课程" class="learn_remove sc" onclick="removeCourse(\'courseDiv'+(count+1)+'\',\'stageLi${classPlanVo.stage}\')" style="margin-right:20px;"><i class="icon-remove i" ></i></button>'+
						'<button type="button" title="升序" class="learn_upper up" onclick="moveUp(\'courseDiv\',\'courseDiv'+(count+1)+'\')"><img src="${ctx }/resources/imgs/u11115.png"></button>'+
						'<button type="button" title="降序" class="down" onclick="moveDown(\'courseDiv\',\'courseDiv'+(count+1)+'\',\'stageLiMark\')"><img src="${ctx }/resources/imgs/u11119.png"></button>'+
						'</div>';
						count++;
					</c:forEach>
				html+='</div>'+
				'<div class="col-md-3 border-stage" style="height: 180px;">'+
				'<div class="aButton"><a class="btn-submit btn-sm btn " href="javascript:courseManager(\'1\',\'stageLi'+mark+'\')"  title="添加线上课程">+添加线上课程</a></div>'+
				'<div class="aButton"><a class="btn-submit btn-sm btn " href="javascript:courseManager(\'0\',\'stageLi'+mark+'\')"  title="添加线下课程">+添加线下课程</a></div>'+
			    '<div class="aButton"><a class="btn-submit btn-sm btn " href="javascript:addPaper(\'stageLi'+mark+'\')"  title="添加试卷">+添加试卷</a></div>'+
				'<div>'+
				'<button type="button" name="orev" title="删除阶段" class="learn_remove remove-link" onclick="removeStage(\'stageLi${classPlanVo.stage}\')" style="vertical-align: middle;"><i class="icon-remove" style="color: red;font-size: 20px;margin-right: 9px"></i></button>'+
				'<button type="button" title="升序" class="learn_upper" onclick="moveUp(\'stageLi\',\'stageLi${classPlanVo.stage}\')"><img src="${ctx }/resources/imgs/u11115.png"></button>'+
				'<button type="button" title="降序" onclick="moveDown(\'stageLi\',\'stageLi${classPlanVo.stage}\')"><img src="${ctx }/resources/imgs/u11119.png"></button>'+
				'</div>'+
				'</div>'+
				'</li>';
			  	}
				</c:forEach>
				$("#stageUl").html(html);
		  }else{
			  $("#ordinary").addClass("tab-pane active");
			  $("#stageLabel").removeClass("active labelMark");
			  $("#ordinaryLabel").addClass("active");
			  $("#stage").removeAttr("class");
			  $("#stage").attr("class","tab-pane");
			  <c:forEach var="pc" items="${OrdinaryLineList.planCourseDtoFMVo}" varStatus="idx">
			  html+= '<li style="padding-left: 0" name="courseDiv" id="isOnLine'+(count+1)+'" >'+
				'<input type="hidden" id="${pc.associateId}" name="planCourseDto['+count+'].associateId" value="${pc.associateId}"/>'+
			    '<input type="hidden" name="planCourseDto['+count+'].associateType" value="${pc.associateType}"/>'+
				'<input type="hidden" name="planCourseDto['+count+'].seq" value="${pc.seq}"/>'+
				'<input type="hidden" name="planCourseDto['+count+'].stage" value="${pc.stage}"/>'+
				'<div style="width:20%;float: left;margin-left:10%;"><span style="float: left" name="courseName">${pc.name}</span></div>'+
				'<div>';
				if("${pc.isCompulsory}"=="0"){
					html+='<label><input type="radio" name="planCourseDto['+count+'].isCompulsory" value="0" checked/><span style="float:left; margin-right:20px;">选修</span></label>'+
				              '<label><input name="planCourseDto['+count+'].isCompulsory" type="radio" value="1" /><span style="float:left; margin-right:100px;">必修</span></label>';
				}
				else if("${pc.isCompulsory}"=="1"){
					html+='<label><input type="radio" name="planCourseDto['+count+'].isCompulsory" value="0" /><span style="float:left; margin-right:20px;">选修</span></label>'+
				              '<label><input name="planCourseDto['+count+'].isCompulsory" type="radio" value="1" checked/><span style="float:left; margin-right:100px;">必修</span></label>';
				}
				else{
					html+='<label><input type="radio" name="planCourseDto['+count+'].isCompulsory" value="0" /><span style="float:left; margin-right:20px;">选修</span></label>'+
				              '<label><input name="planCourseDto['+count+'].isCompulsory" type="radio" value="1" /><span style="float:left; margin-right:100px;">必修</span></label>';
				}
				html+='<div class="col-md-4"><button type="button" title="移除" class="learn_removed" onclick="detr(\'isOnLine'+(count+1)+'\')"><i class="icon-remove"></i></button>'+
	    		' <button type="button" title="升序" class="learn_upper" onclick="ascending(\'isOnLine'+(count+1)+'\')"><img src="${ctx}/resources/imgs/u11115.png"></button>'+
	    		' <button type="button" title="降序" onclick="descending(\'isOnLine'+(count+1)+'\')"><img src="${ctx}/resources/imgs/u11119.png"></button></div>'+
	    		'</li>';
	    		count++;
	    		</c:forEach>
	    		$("#ordinaryOnLine").html(html);
		  }
	  }
	  courseDivNumS = count;
	  if("" != "${classPlan.id }"){
		  if('${classPlan.isFree}' == '0' ){
		  	 $("#chargeText").addClass("notNull");
		  }
		  if("${classPlan.isRewardPoint}" == '1'){
		 	 $("#integraText").addClass("notNull");
		  }
		  if("${classPlan.isTrainingNew}" == '1'){
			  isTrainingNew = 1;
		  }
		  $("#"+"${classPlan.qualificationId}").attr("selected","selected");
		  $("#qualifySele").find("[id='${classPlan.qualificationId}']").attr("selected","selected");
	  }
	  var olgHtml = ""
  	  var olgCount = 0;
  	  if("${not empty expiredAlarmList}" == "true"){
	    <c:forEach items="${expiredAlarmList}" var="expiredAlarm" varStatus="ext">
	    olgHtml +='<div name="olg" style="margin-left:10px ;margin-top:5px;">'+
		'<span name="olgAxpired">';
	    if(olgCount>10){
	    	olgCount = olgCount.toString();
	    	olgHtml +='第'+N[10]+N[mark[1]]+'次提醒：';
		}else{
			olgHtml +='第'+N[olgCount+1]+'次提醒：';
		}
	    olgHtml +='</span><br>'+
		'<span>过期前&nbsp;<input type="text" class="ipt-text" name="expiredAlarmList[${ext.index}].beforeDay1" value="${expiredAlarm.beforeDay1 }" style="max-width: 50px" onkeyup="ea_digit_verify(this)">&nbsp;天</span>'+
		'</div>';
		olgCount++;
		</c:forEach>
		olgHtml +='<button type="button"  id ="addlg" class="addStation" onclick="addInput(this)">+</button>';
  	  }else{
  		olgHtml +='<div name="olg" style="margin-left:10px ;margin-top:5px;">'+
		'<span>第一次提醒：</span><br>'+
		'<span>过期前&nbsp;<input type="text" class="ipt-text"  name="expiredAlarmList[0].beforeDay1" value=""  style="max-width: 50px" onkeyup="ea_digit_verify(this)">&nbsp;天</span>'+
		'</div>'+
		'<button type="button" class="addStation" id ="addlg" onclick="addInput(this)">+</button>';
  	  }
  	  $("#olgAxpired").html(olgHtml);
  });
  $('.nav-tabs').each(function(index, element) {
		var _obj = $(this);
		$(this).on('click', 'strong:not(.active)', function(){
			$(this).addClass('active').siblings('strong').removeClass('active');
			_obj.nextAll('.tab-pane').removeClass('active').eq($(this).index()).addClass('active')
		})
	});
  
  $("#ksh").click(function () {
	  var html = ""
	  if($(this).attr("value")=='1'){
		  isTrainingNew = 0
		  $(this).prop('checked', false);
		  $(this).attr("value",0);
		  $("#isNotSign").remove();
	  }else{
		  isTrainingNew = 1;
		  $(this).prop('checked', true); 
		  $(this).attr("value",1);
		  $("#jtgk").prop('checked', false);
		  $("#jtgk").attr("value",0);
		  html+='<li class="col-md-12" id="isNotSign"><strong><ins>*</ins>完成课程后是否签约:</strong>'+
		  '<label class="isNot"><input type="radio" name="classPlan.isSign" id="isSign" value="1" checked/>是</label>'+
		  '<label class="isNot"><input type="radio" name="classPlan.isSign" id="notSign" value="2" />否</label></li>';
		  $("#train").after(html);
	  }
  });
  $("#jtgk").click(function () {
	  if($(this).attr("value")=='1'){
		  $(this).prop('checked', false);
		  $(this).attr("value",0);
	  }else{
		  isTrainingNew = 0;
		  $(this).prop('checked', true); 
		  $(this).attr("value",1);
		  $("#ksh").prop('checked', false);
		  $("#ksh").attr('value', 0);
		  $("#isNotSign").remove();
	  }
  });
  
  $("#free").click(function () {
	  $("#chargeText").removeClass("notNull");
  });
  $("#charge").click(function () {
	  $("#chargeText").addClass("notNull");
  });
  $("#integra").click(function () {
	  if($(this).is(':checked')){
		  $("#integraText").addClass("notNull");
	  }else{
		  $("#integraText").removeClass("notNull");
	  }
  });
  $("#ksh").click(function () {
	  if($(this).is(':checked')){
	  }else{
	  }
  });
  var stageLiMark ;
  function accountList(accountIds,accountNames){
	  var html="";
	  $("input[name^='managerIds']").remove();
	  for(var i = 0 ; i < accountIds.length ; i++){
		  html +="<input type='hidden' name='managerIds' class='ipt-text' value='"+accountIds[i]+"'/>";
	  }
	  $("#managerName").before(html);
	  $("#managerName").val(accountNames);
	  
  }
  
  function courseList(ids,names,type){
	  var html="";
	  var courseDivNum = 0;
	  if(courseDivNumS == 0){
		  courseDivNum = $("[name='courseDiv']").length;
	  }else{
		  courseDivNum = courseDivNumS;
	  }
      var count = 0;
      var seq = $("#"+stageLiMark).find("[name='courseDiv']").length;
      if(stageLiMark != "isOnLineCourse" && stageLiMark != "ordinaryLine" && stageLiMark != "ordinaryOnLine") {
	      var stage = stageLiMark.substring(stageLiMark.length-1,stageLiMark.length);
		  for(var i = courseDivNum ; i < ids.length+courseDivNum ; i++){
			  html +='<div name="courseDiv" class="col-md-8" id="courseDiv'+(i+1)+'" style="margin-left:10%;width:80%;">'+
			    '<input type="hidden" id="'+ids[count]+'" name="planCourseDto['+i+'].associateId" value="'+ids[count]+'"/>'+
				'<input type="hidden" name="planCourseDto['+i+'].seq" value="'+(seq+1)+'"/>'+
				'<input type="hidden" name="planCourseDto['+i+'].stage" value="'+stage+'"/>'+
				'<input type="hidden" name="planCourseDto['+i+'].associateType" value="'+type+'"/>'+
				'<input type="hidden" name="planCourseDto['+i+'].startTime" value=""/>'+
				'<input type="hidden" name="planCourseDto['+i+'].endTime" value=""/>'+
				'<span style="margin-right:20px;" name="courseName">'+names[ids[count]]+'</span><label style="margin-right:20px;">'+
				'<input type="radio" name="planCourseDto['+i+'].isCompulsory" value="0" checked/>选修</label><label style="margin-right:20px;"><input name="planCourseDto['+i+'].isCompulsory" type="radio" value="1"/>必修</label>'+
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
	    	    '<input type="hidden" id="'+ids[count]+'" name="planCourseDto['+i+'].associateId" value="'+ids[count]+'"/>'+
				'<input type="hidden" name="planCourseDto['+i+'].seq" value="'+(seq+1)+'"/>'+
				'<input type="hidden" name="planCourseDto['+i+'].associateType" value="'+type+'"/>'+
				'<input type="hidden" name="planCourseDto['+i+'].stage" value="1"/>'+
				'<div style="width:20%;float: left;margin-left:10%;"><span style="float: left" name="courseName">'+names[ids[count]]+'</span></div>'+
	    	  	'<label><input type="radio" name="planCourseDto['+i+'].isCompulsory" value="0" checked /><span style="float:left; margin-right:20px;">选修</span></label>'+
				'<label><input name="planCourseDto['+i+'].isCompulsory" type="radio" value="1" /><span style="float:left; margin-right:100px;">必修</span></label>'+
	    	  	'<div><button type="button" title="移除" class="learn_removed" onclick="detr(\'isOnLine'+(i+1)+'\')"><i class="icon-remove"></i></button>'+
	    	  	' <button type="button" title="升序" class="learn_upper" onclick="ascending(\'isOnLine'+(i+1)+'\',\''+stageLiMark+'\')"><img src="${ctx}/resources/imgs/u11115.png"></button>'+
	    		' <button type="button" title="降序" onclick="descending(\'isOnLine'+(i+1)+'\',\''+stageLiMark+' \')"><img src="${ctx}/resources/imgs/u11119.png"></button></div>';
	    		if(stageLiMark == "isOnLineCourse"){
	    			html+= '<div class="col-md-12 marL"><span class="fl">任务日期：</span>'+
		    		'<input type="text" placeholder="开始时间" name="planCourseDto['+i+'].startTime" class="Wdate" style="max-width: 180px;" onfocus="WdatePicker({onpicked: function(){jQuery(this).trigger(\'change\');},oncleared: function(){jQuery(this).trigger(\'change\');}})"><span class="fl">&nbsp;—&nbsp;</span>'+
		    		'<input type="text" placeholder="结束时间" name="planCourseDto['+i+'].endTime" class="Wdate" style="max-width: 180px;" onfocus="WdatePicker({onpicked: function(){jQuery(this).trigger(\'change\');},oncleared: function(){jQuery(this).trigger(\'change\');}})">&nbsp;&nbsp;'+
		    		'<span class="fl pad-left-20">签到地点：</span>'+
		    		'<input class="ipt-text proTime" name="planCourseDto['+i+'].signAddress" teype="text" placeholder="">';
	    		}
	    		/* '<button style="margin-left:20px;" class="addStation">+</button></div>'+ */
	    		html+= '</li>';
	    		count++;
	    		seq++;
	    		countIOL++;
    	  }
    	  courseDivNumS += countIOL;
	    $("#"+stageLiMark).append(html);
      } 
  }
  
  function classManager(){
	  layer.open({
		  type: 2,
		  closeBtn: 0,
		  title: '班级负责人',
		  shadeClose: true,
		  shade: 0.8,
		  area: ['900px', '500px'],
		  content: '${ctx}/manage/classPlan/lecturerList' //iframe的url
	}); 
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
		'<div class="sameWadte date" style="overflow-y:auto;margin-top:20px">'+
		'<input type="text" placeholder="开始时间" value="" name="startTime" class="Wdate startDate" style="max-width: 100px;" id="stageStartTime'+mark+'" onfocus="WdatePicker({maxDate:\'#F{$dp.$D('+'\\'+'\'stageEndTime'+mark+'\\'+'\')}\'})">'+
		'&nbsp;—&nbsp;'+
		'<input type="text" placeholder="结束时间" value="" name="endTime" class="Wdate endDate" style="max-width: 100px;" id="stageEndTime'+mark+'" onfocus="WdatePicker({minDate:\'#F{$dp.$D('+'\\'+'\'stageStartTime'+mark+'\\'+'\')}\'})">'+
		'</div>'+
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
  function moveUp(idType,id){
		  /* var mark = $("#"+id).find("input[name$='.stage']").val(); */
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
  var classPlanName = '${classPlan.name}' ;
  function save(){
	  var bool = true;
	  var labelVal = $(".labelMark").attr("class");
	  if(valid()){
		 if(labelVal!="labelMark"){
			  if($("li[name='stageLi']").length == 0){
				  layer.msg("阶段不能为空");
				  return;
			  }
			 $("li[name='stageLi']").each(function(){
				 if($(this).find("div[name='courseDiv']").length==0 ){
					 layer.msg("阶段不能为空");
					 bool = false;
					 return false;
				 }
				 var startTime = $(this).find("input[name='startTime']").eq(0).val();
				 var endTime = $(this).find("input[name='endTime']").eq(0).val();
				 $(this).find("input[name$='.startTime']").each(function(){
					 $(this).val(startTime);
				 })
				 $(this).find("input[name$='.endTime']").each(function(){
					 $(this).val(endTime);
				 })
			 });
		 }
		 if(!bool){
			 return;
		 }
		 var name = $("input[name='classPlan.name']").val();
		 var isNot = 1;
		 if(name != classPlanName){
			 isNot = 0;
		 }
		 if($("li[name='stageLi']").length == 1 && labelVal!="labelMark"){
			 layer.confirm('一个阶段默认为普通型计划,你是否执行此操作?', {
					icon: 3,
				  	btn: ['确定', '取消'] //按钮
				}, function(){
					$.ajax({
						async : false ,
						type : "post",
						data : $('#stageForm').serialize(),
						url : "${ctx}/manage/classPlan/save?isNot="+isNot,
						success : function(data) {
							if(data.code==1){
								
								location.href="${ctx}/manage/classPlan/index";
							}else{
								layer.msg("保存失败")
							}
						}
					});
				})
		  }else{
			  $.ajax({
				    async : false ,
					type : "post",
					data : $('#stageForm').serialize(),
					url : "${ctx}/manage/classPlan/save?isNot="+isNot,
					success : function(data) {
						if(data.code==1){
							layer.confirm('保存成功', {
								icon: 3,
							  	btn: ['确定', '取消'] //按钮
							},  function(){
								location.href="${ctx}/manage/classPlan/index";
							});
						}else{
							layer.msg("保存失败")
						}
					}
				});
		  }
			 
	  }
  }
  function addInput(obj){
	  var ss =  $(obj).parent().children("div[name='olg']").length;
	  var dd =ss*1+1;
	       var aHtml ='<div name="olg" style = "margin-top:5px;">';
	       if(dd>10){
	    	    dd = dd.toString();
				aHtml +='<span>第'+N[10]+N[dd[1]]+'次提醒：</span><br>';
			}else{
				aHtml +='<span>第'+N[dd]+'次提醒：</span><br>'
			}
	       aHtml +='<span>过期前&nbsp;<input type="text" class="ipt-text"  name="expiredAlarmList['+ss+'].beforeDay1" value=""  style="max-width: 50px">&nbsp;天</span>'+
		   '</div>';
		   $(obj).before(aHtml);  
   }
  function descending(obj,id){
	  if($("#"+obj).next().html()){
		  $("#"+obj).next().after($("#"+obj));
		  $("#"+id).find("input[name$='.seq']").each(function(i){
			  $(this).val(i+1);
		  });
	  }else{
		  layer.msg("已经是最后一条了");
		  return false;
	  }
  }
  
  function ascending(obj,id){
	   if($("#"+obj).prev().html()){
		   $("#"+obj).prev().before($("#"+obj));
		   $("#"+id).find("input[name$='.seq']").each(function(i){
				  $(this).val(i+1);
			  });
	  } else{
		  layer.msg("已经是第一条了");
		  return false;
	  }
	   
  }
  
  function detr(obj){
	  $("#"+obj).remove();
	  $("#isOnLineCourse").find("input[name$='.seq']").each(function(i){
		  $(this).val(i+1);
	  });
	  if($("#isOnLineCourse").children().length==0){
		  $("#iOLCButton").remove();
	  }
  }
  function labelHtml(type,obj){
	  if(type=='1'){  
		 $("#planType").val("1");  //阶段型计划
		  $(obj).addClass("labelMark");
		  $("#stage").find("input").each(function(){
			  $(this).removeAttr("disabled");
		  });
		  $("#ordinary").find("input").each(function(){
			  $(this).attr("disabled","disabled");
		  });
	  }else{   
		  $("#planType").val("0"); //普通型计划 
		  $(obj).prev().removeClass("labelMark");
		  $("#stage").find("input").each(function(){
			  $(this).attr("disabled","disabled");
		  });
		  $("#ordinary").find("input").each(function(){
			  $(this).removeAttr("disabled","disabled");
		  });
	  }
   }
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
   function addStation(){
	   var htmlBu='<ol id="iOLCButton">'+
		'<li style="padding-left: 0" class="sameWadte"> '+
		'<span>任务日期：</span>'+
		'<input type="text" placeholder="开始时间" class="Wdate" style="max-width: 180px;" onfocus="WdatePicker({onpicked: function(){jQuery(this).trigger(\'change\');},oncleared: function(){jQuery(this).trigger(\'change\');}})">&nbsp;—&nbsp;'+
		'<input type="text" placeholder="结束时间" class="Wdate" style="max-width: 180px;" onfocus="WdatePicker({onpicked: function(){jQuery(this).trigger(\'change\');},oncleared: function(){jQuery(this).trigger(\'change\');}})">&nbsp;&nbsp;'+
		'<span>签到地点：</span>'+
		'<input class="ipt-text proTime" type="text" placeholder="">'+
		'<button class="addStation" onclick="addStation()">+</button>'+
		'</li>'+
		'</ol>';
	$("#isOnLineCourse").after(htmlBu);
   }
   function cancel(){
	   window.location.href = "${ctx}/manage/classPlan/index";
   }
   var courseName = [];
   var htmlCont;
   function preview(){
	  var trainVal = 0;
	  $("#train").find("input[type='radio']").each(function(){
		  trainVal += $(this).attr("value");
	  })
	  if(trainVal == 0){
		  layer.msg("培训对象不能为空");
		  return;
	  }
	  var bool = true;
	  var stageNum = 1 ;
	  var managerName = $("#managerName").val();
	  if($("#ordinaryLabel").attr("class") == "active"){
		  $("#ordinaryOnLine").find("li[name='courseDiv']").each(function(){
			  courseName[$(this).find("input[name$='.associateId']").val()] = $(this).find("span[name='courseName']").text();
		  })
	  }else{
		  stageNum = $("li[name='stageLi']").length;
		  $("#stageUl").find("div[name='courseDiv']").each(function(){
			  courseName[$(this).find("input[name$='.associateId']").val()] = $(this).find("span[name='courseName']").text();
		  })
	  }
	  var labelVal = $(".labelMark").attr("class");
	  if(valid()){
		 var classPlanName = '${classPlan.name}' ;
		 if(labelVal!=null){
			  if($("li[name='stageLi']").length == 0){
				  layer.msg("阶段不能为空");
				  return;
			  }
			 $("li[name='stageLi']").each(function(){
				 if($(this).find("div[name='courseDiv']").length==0 ){
					 layer.msg("阶段不能为空");
					 bool = false;
					 return false;
				 }
				 var startTime = $(this).find("input[name='startTime']").eq(0).val();
				 var endTime = $(this).find("input[name='endTime']").eq(0).val();
				 $(this).find("input[name$='.startTime']").each(function(){
					 $(this).val(startTime);
				 })
				 $(this).find("input[name$='.endTime']").each(function(){
					 $(this).val(endTime);
				 })
			 });
		 }
		 if(!bool){
			 return;
		 }
		 var name = $("input[name='classPlan.name']").val();
		 var isNot = 1;
		 if(name != classPlanName){
			 isNot = 0;
		 }
		 
		 if(stageNum == 1 && labelVal!=null){
			 layer.confirm('一个阶段默认为普通型计划,你是否执行此操作?', {
					icon: 3,
				  	btn: ['确定', '取消'] //按钮
				}, function(){
					$("#planType").val("0");  //普通型计划
					var index = layer.confirm();
					layer.close(index);
					$.ajax({  
		                  type: 'POST',  
		                  url: "${ctx}/manage/classPlan/previewAjax?managerName="+managerName+"&stageNum="+stageNum+"&"+$('#stageForm').serialize(),//发送请求  
		                  success: function(result) {  
		                	  htmlCont = result;//返回的结果页面 
		                      layer.open({
		        				  type: 2,
		        				  title: false,
		        				  closeBtn: 1, //不显示关闭按钮
		        				  shade: [0],
		        				  area: ['1000px', '550px'],
		        				  content: "${ctx}/manage/classPlan/preview"+getUrl(result.classPlan)+"&managerName="+result.managerName, 
		        				  end: function(){
		        				  }
		        			   }); 
		                  }  
		                }); 
				})
		  }else{
			  $("#planType").val("1");  //阶段型计划
			  $.ajax({  
                  type: 'POST',  
                  url: "${ctx}/manage/classPlan/previewAjax?managerName="+managerName+"&stageNum="+stageNum+"&"+$('#stageForm').serialize(),//发送请求  
                  success: function(result) {  
                	  htmlCont = result;//返回的结果页面 
                	  result.managerName
                      layer.open({
        				  type: 2,
        				  title: false,
        				  closeBtn: 1, //不显示关闭按钮
        				  shade: [0],
        				  area: ['1000px', '550px'],
        				  content: "${ctx}/manage/classPlan/preview"+getUrl(result.classPlan)+"&managerName="+result.managerName, 
        				  end: function(){
        				  }
        			   }); 
                  }  
                });  
			  
		  }
			 
		  }
   }
   function saveSuccess(){
	   location.href="${ctx}/manage/classPlan/index";
   }
   
   function getUrl (obj) {
	   var restUrl = '?';
	   for(var key in obj) {
		   if(obj[key] != null){
		   		restUrl = restUrl + key + '=' + obj[key] + '&';
		   }
	   }
	   restUrl = restUrl.substring(-1, restUrl.length -1);
	   return restUrl;
   }
   
   function changeC(category){  
       $("input#category").val(category);  //附件表中定义的附件类型 
       fileName.click();
   }
   
   function upload(){
       var btn_index = layer.load(2);
        $("#bannerFile").ajaxSubmit({
           url : '${ctx}/manage/commonUploadFile',
           type :"post",
           success : function(data){
                   console.log(data);
                   if (data != null) {  //附件要回显
                       $("input#fileId").val(data.id);
                       $("#smallImage").attr("src",data.path);
                   }
                   layer.close(btn_index);
               },error:function() {
                   alert("附件上传错误");
                   layer.close(btn_index);
               } 
           });  
       }
  
</script>
</body>
</html>
