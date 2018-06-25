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
.viewList{
    	font-size: 14px;
    	color: #F18800;
    	text-decoration: none;
    	margin-left: 80px;
    	}
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
	.button-font {
		font-size: 15px!important;
	}
	
	.remove-middle-border {
		padding-left: 0!important;
	}
	/* .border-stage {
		border: 1px solid #000!important;
		text-align: center;
	} */
	.remove-middle-border .border-stage:nth-child(2) {
		border-left: none!important;
		/* border-right: none!important; */
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
</style>
<script type="text/javascript"	src="${ctx}/resources/libs/layer/layer.js"></script>
<script type="text/javascript"	src="${ctx}/resources/libs/input/input-validate.js"></script>
		<div class="form-detail offline_form">
			<div class="title">
				<strong>编辑岗位路线</strong>
				<button  type="button" class="viewList button-font" onclick="edit(${id},'${levelName }')">编辑</button>
			</div>
	<form action="${ctx}/manage/development/save" id="stageForm" method="post">
			<input type="hidden" id="id" name="postDevelopment.id" value="${postDevelopment.id }"> 
			<ul class="clearfix form_learn">
				<li class="col-md-12"><strong>LEVEL说明：</strong><input type="text"  class="ipt-text"  readonly="readonly"  name="postDevelopment.description" value="${postDevelopment.description}"></li>
			</ul> 
			<div class="title"><strong>岗位计划详情</strong></div>
			<div class="cycle_hd sameWadte date">
				
				<ul class="clearfix form_learn">
				<li class="col-md-12 zlgl_share"><strong>计划限制：</strong>
					
					<c:choose>
					<c:when test="${postDevelopment.limitTypes==1}">
					<label for="integra">
						<input type="radio" value="1" id="limitTypes1"  readonly="readonly"   <c:if test="${postDevelopment.limitTypes==1}">checked</c:if> name="postDevelopment.limitTypes">升级后的&nbsp;
						<input type="text" class="ipt-text sameTime"  readonly="readonly"  name="postDevelopment.endDays" value="${postDevelopment.endDays}" onkeyup="value=value.replace(/[^\d.]/g,'')" >&nbsp;天内完成
					</label>
					</c:when>
					<c:otherwise>
					<label for="integra">
						<input type="radio" value="1" id="limitTypes1"  readonly="readonly"   <c:if test="${postDevelopment.limitTypes==1}">checked</c:if> name="postDevelopment.limitTypes">升级后的&nbsp;
						<input type="text" class="ipt-text sameTime"  readonly="readonly"  name="postDevelopment.endDays" value="" onkeyup="value=value.replace(/[^\d.]/g,'')" >&nbsp;天内完成
					</label>
					</c:otherwise>
					</c:choose>
					
					
					
					<c:choose>
					<c:when test="${postDevelopment.limitTypes==2}">
					<label for="award">
						<input type="radio" value="2" id="limitTypes2"  readonly="readonly"  <c:if test="${postDevelopment.limitTypes==2}">checked</c:if> name="postDevelopment.limitTypes">升级后的第&nbsp;
						<input type="text" class="ipt-text sameTime"  readonly="readonly"  name="postDevelopment.startDays" value="${postDevelopment.startDays}" onkeyup="value=value.replace(/[^\d.]/g,'')" >&nbsp;天 -&nbsp;<input type="text"   readonly="readonly"  class="ipt-text sameTime" name="postDevelopment.endDays" value="${postDevelopment.endDays}" onkeyup="value=value.replace(/[^\d.]/g,'')" >&nbsp;天完成
					</label>
					</c:when>
					<c:otherwise>
					<label for="award">
						<input type="radio" value="2" id="limitTypes2"  readonly="readonly"  <c:if test="${postDevelopment.limitTypes==2}">checked</c:if> name="postDevelopment.limitTypes">升级后的第&nbsp;
						<input type="text" class="ipt-text sameTime"  readonly="readonly"  name="postDevelopment.startDays" value="" onkeyup="value=value.replace(/[^\d.]/g,'')" >&nbsp;天 -&nbsp;<input type="text" class="ipt-text sameTime"  readonly="readonly"  name="postDevelopment.endDays" value="" onkeyup="value=value.replace(/[^\d.]/g,'')" >&nbsp;天完成
					</label>
					</c:otherwise>
					</c:choose>
					
					
					<c:choose>
					<c:when test="${postDevelopment.limitTypes==3}">
					<label for="qualify">
						<input type="radio" value="3" id="limitTypes3"  readonly="readonly"  <c:if test="${postDevelopment.limitTypes==3}">checked</c:if> name="postDevelopment.limitTypes">升级后的&nbsp;
						<input type="text" class="ipt-text sameTime"  readonly="readonly"  name="postDevelopment.startDays" value="${postDevelopment.startDays}" onkeyup="value=value.replace(/[^\d.]/g,'')" >&nbsp;天后可开始学习
					</label>
					</c:when>
					<c:otherwise>
					<label for="qualify">
						<input type="radio" value="3" id="limitTypes3"  readonly="readonly"  <c:if test="${postDevelopment.limitTypes==3}">checked</c:if> name="postDevelopment.limitTypes">升级后的&nbsp;
						<input type="text" class="ipt-text sameTime"  readonly="readonly"  name="postDevelopment.startDays" value="" onkeyup="value=value.replace(/[^\d.]/g,'')" >&nbsp;天后可开始学习
					</label>
					</c:otherwise>
					</c:choose>
					
					
					
				</li>
			</ul> 
			</div>
			<div class="nav-tabs">
				<strong id="stageLabel" class="active" onclick="labelHtml(1)">阶段型计划</strong>
				<strong id="ordinaryLabel" onclick="labelHtml(2)">普通型计划</strong>
			</div>
			<div class="tab-pane active" id="stage">
				<div class="title"><strong>线上课程</strong><button type="button" title="删除考题"><!-- <i class="icon-delete"></i> --></button></div>
				<ul id="stageUl" class="edit clearfix xxjh_add">
						<li name="stageLi" id="stageLi1" class="col-md-12 remove-middle-border">
						<%-- 	<div class="col-md-3 border-stage date" style="height: 150px;" name="stageNum">
								<span style="display:block; padding-top:30px;">第一阶段</span>
								<div class="sameWadte" style="overflow-y:auto;">
								<input type="text" placeholder="开始时间" value="${postDevelopment.startDate }" name="postDevelopment.startDate" class="Wdate startDate" style="max-width: 100px;" onfocus="WdatePicker({onpicked: function(){jQuery(this).trigger('change');},oncleared: function(){jQuery(this).trigger('change');}})">
								&nbsp;—&nbsp;
								<input type="text" placeholder="结束时间" value="${postDevelopment.endDate }" name="postDevelopment.endDate" class="Wdate endDate" style="max-width: 100px;" onfocus="WdatePicker({onpicked: function(){jQuery(this).trigger('change');},oncleared: function(){jQuery(this).trigger('change');}})">
								</div>
							</div>
							<div class="col-md-6 border-stage" name="classDiv" style="overflow-y:auto;">	
							
							</div>
							<div class="col-md-3 border-stage" style="height: 150px;">
								<div class="aButton"><a class="btn-submit btn-sm btn " href="javascript:courseManager('1','stageLi1')"  title="添加线上课程/课程组">添加线上课程/课程组</a></div>
							    <div class="aButton"><a class="btn-submit btn-sm btn " href="javascript:addPaper('stageLi1')"  title="添加试卷">+添加试卷</a></div>
							</div> --%>
						</li>
				</ul>
				
				<!-- <div class="title"><strong>线下课程</strong></div>
				<button type="button" class="btn-submit btn-sm btn" onclick="courseManager('0','isOnLineCourse')">+ 添加线下课程</button> -->
				<ol id="isOnLineCourse">
					<!-- <li style="padding-left: 0" class="sameWadte"> 
						<span>任务日期：</span>
						<input type="text" placeholder="开始时间" class="Wdate" style="max-width: 180px;" onfocus="WdatePicker({onpicked: function(){jQuery(this).trigger('change');},oncleared: function(){jQuery(this).trigger('change');}})">&nbsp;—&nbsp;
						<input type="text" placeholder="结束时间" class="Wdate" style="max-width: 180px;" onfocus="WdatePicker({onpicked: function(){jQuery(this).trigger('change');},oncleared: function(){jQuery(this).trigger('change');}})">&nbsp;&nbsp;
						<span>签到地点：</span>
						<input class="ipt-text proTime" type="text" placeholder="">
						<button class="addStation">+</button>
					</li> -->
				</ol>
			</div>
			<div class="tab-pane" id="ordinary">
				<ul class="edit clearfix">
					<li class="col-md-12">
						<ol id="ordinaryOnLine" style="display:table;width:100%;">
							
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
           <div class="title"><strong>其他设置</strong></div>
			<ul class="clearfix">
				
				<li class="col-md-12 zlgl_share">
					<strong>是否允许计划过期后继续任务：</strong>
					<label for="allow" style="padding-left: 120px;">
						<input type="radio" value="1" id="allow" name="postDevelopment.isAllowExpired" <c:if test="${postDevelopment.isAllowExpired == 1 }">checked</c:if>>允许
					</label>
					<label for="notAllow">
						<input type="radio" value="0" id="notAllow" name="postDevelopment.isAllowExpired" <c:if test="${postDevelopment.isAllowExpired == 0 }">checked</c:if>>不允许
					</label>
				</li>
				<li class="col-md-12 zlgl_share" >
					<strong>过期提醒：</strong>
					<label for="on"><input type="radio" value="1" id="on" name="postDevelopment.isExpiredAlarm" <c:if test="${postDevelopment.isExpiredAlarm == 1 }">checked</c:if>>开启
					</label>
					<label for="off"><input type="radio" value="0" id="off" name="postDevelopment.isExpiredAlarm" <c:if test="${postDevelopment.isExpiredAlarm == 0 }">checked</c:if>>关闭
					</label>
					<c:choose>
						<c:when test="${not empty expiredAlarmList}">
							<div class="remind" style="padding: 0" name="tixing">
							    <c:forEach items="${expiredAlarmList}" var="expiredAlarm" varStatus="ext">
							    <div name="olg" style="margin-left:10px ;margin-top:5px;">
								<span ty="remind">第${ext.index+1}次提醒：</span><br>
								<span>过期前&nbsp;<input type="text" class="ipt-text" readonly="readonly" name="expiredAlarmList[${ext.index}].beforeDay1" value="${expiredAlarm.beforeDay1 }" style="max-width: 50px">&nbsp;天</span>
								</div>
								</c:forEach>
						   </div>
						</c:when>
						<c:otherwise>
							<!-- <div class="remind" style="padding: 0" name="tixing">
							    <div name="olg" style="margin-left:10px ;margin-top:5px;">
								<span>第一次提醒：</span><br>
								<span>过期前&nbsp;<input type="text" class="ipt-text" readonly="readonly"  name="expiredAlarmList[0].beforeDay1" value=""  style="max-width: 50px">&nbsp;天</span>
								 </div>
							</div> -->
						</c:otherwise>
					</c:choose>
					
				</li>
			</ul>
			<div class="ui-button">
				<button type="button" class="btn btn-submit" onclick="cancel()">返回</button>
			</div>
	</form>	
		</div>
<script type="text/javascript" src="${ctx}/resources/libs/datepicker/WdatePicker.js"></script>
<script>
var reminds=new Array('一','二','三','四','五','六','七','八','九','十','十一','十二');
$(function(){
$("[ty=remind]").each(function(index,ele){
	$(ele).html('第'+reminds[index]+'次提醒:')
})	
});
function edit(ids,name){
	location.href="${ctx}/manage/development/saveDeveById?id="+ids+"&levelName="+name;
	
	
	}




  var courseDivNumS = 0;
  $(function(){
	  var html = "";
	  var count = 0;
	  if("${not empty classDevelopmentList}" == "true"){
		  if("${markType}" == "stage"){
			  $("#stage").attr("class","tab-pane active");
			  $("#stageLabel").attr("class","active");
			  <c:forEach var="classPlanVo" items="${classDevelopmentList}">
			  if("${classPlanVo.stage}" != ""){
			  var mark = "${classPlanVo.stage}";
			  html +='<li name="stageLi" id="stageLi${classPlanVo.stage}" class="col-md-12 remove-middle-border">'+
				'<div class="col-md-3 border-stage" style="height: 150px;" name="stageNum">'+
				'<span name="spanStageNum" style="display:block; padding-top:30px;">';
				if(mark>10){
					mark = mark.toString();
					html +='第'+N[10]+N[mark[1]]+'阶段';
				}else{
					html +='第'+N[mark]+'阶段';
				}
				html +='</span>'+
				'<div class="sameWadte date" style="overflow-y:auto;">'+
				'<input type="text" placeholder="开始时间" value="${classPlanVo.planCourseDtoFMVo[0].startTime}" name="startTime" class="Wdate startDate" style="max-width: 100px;" onfocus="WdatePicker({onpicked: function(){jQuery(this).trigger(\'change\');},oncleared: function(){jQuery(this).trigger(\'change\');}})">'+
				'&nbsp;—&nbsp;'+
				'<input type="text" placeholder="结束时间" value="${classPlanVo.planCourseDtoFMVo[0].endTime}" name="endTime" class="Wdate endDate" style="max-width: 100px;" onfocus="WdatePicker({onpicked: function(){jQuery(this).trigger(\'change\');},oncleared: function(){jQuery(this).trigger(\'change\');}})">'+
				'</div>'+
				'</div>'+
				'<div class="col-md-6 border-stage" name="classDiv" style="overflow-y:auto;">';
					<c:forEach var="pc" items="${classPlanVo.planCourseDtoFMVo}" varStatus="idx">
					html +='<div name="courseDiv" class="col-md-8" id="courseDiv'+(count+1)+'" style="margin-left:10%;width:80%;">'+
				    '<input type="hidden" id="${pc.associateId}" name="planCourseDto['+count+'].associateId" value="${pc.associateId}"/>'+
				    '<input type="hidden" name="planCourseDto['+count+'].associateType" value="${pc.associateType}"/>'+
					'<input type="hidden" name="planCourseDto['+count+'].seq" value="${pc.seq}"/>'+
					'<input type="hidden" name="planCourseDto['+count+'].stage" value="${pc.stage}"/>'+
					'<input type="hidden" name="planCourseDto['+count+'].startTime" value="${pc.startTime}"/>'+
					'<input type="hidden" name="planCourseDto['+count+'].endTime" value="${pc.startTime}"/>'+
					'<span style="margin-right:20px;">${pc.name}</span>';
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
					html +=	'<button type="button" onclick="view(\'${pc.associateId}\',${pc.associateType})" class="viewList">查看</button>'+
					'</div>';
					count++;
					</c:forEach>
					html+='</div>';
			  	}
				</c:forEach>
				$("#stageUl").html(html);
		  }else{
			  $("#ordinary").attr("class","tab-pane active");
			  $("#stageLabel").removeAttr("class","active");
			  $("#ordinaryLabel").attr("class","active");
			  $("#stage").removeAttr("class");
			  $("#stage").attr("class","tab-pane");
			  <c:forEach var="pc" items="${OrdinaryLineList.planCourseDtoFMVo}" varStatus="idx">
			  var sel="选修";
			  if("${pc.isCompulsory}"=="1"){
				  sel="必修";
			  
			  }
			  
			  html+= '<li style="padding-left: 0;display:table-row" name="courseDiv" id="isOnLine'+(count+1)+'" >'+
				'<tbody>'+
				'<tr>'+
				'<td>'+
				'<div style="display:table-cell">${pc.name}</div><div class="table-cell">'+sel+'</div><div style="display:table-cell"><button type="button" onclick="view(\'${pc.associateId}\',${pc.associateType})" class="viewList" style="float:none">查看</button></div>'+
				'</td>'+
				'</tr>'+
				'</tbody>';
	    		count++;
	    		</c:forEach>
	    		$("#ordinaryOnLine").html(html);
		  }
	  }
	  if("${not empty lineList}" == "true"){
	  	var htmlLine = "";
		  <c:forEach var="lineCourse" items="${lineList}">
		  htmlLine+= '<li style="padding-left: 0" name="courseDiv" id="isOnLine'+(count+1)+'" >'+
			'<input type="hidden" id="${lineCourse.associateId}" name="planCourseDto['+count+'].associateId" value="${lineCourse.associateId}"/>'+
		    '<input type="hidden" name="planCourseDto['+count+'].associateType" value="${lineCourse.associateType}"/>'+
			'<input type="hidden" name="planCourseDto['+count+'].seq" value="${lineCourse.seq}"/>'+
			'<input type="hidden" name="planCourseDto['+count+'].stage" value="${lineCourse.stage}"/>'+
			'<div style="width:20%;float: left;margin-left:10%;"><span style="float: left">${lineCourse.name}</span></div>'+
			'<div>';
			if("${lineCourse.isCompulsory}"=="1"){
				htmlLine+='<label><input type="radio" name="planCourseDto['+count+'].isCompulsory" value="0" checked/><span style="float:left; margin-right:20px;">选修</span></label>'+
			              '<label><input name="planCourseDto['+count+'].isCompulsory" type="radio" value="1" /><span style="float:left; margin-right:100px;">必修</span></label>';
			}
			else if("${lineCourse.isCompulsory}"=="0"){
				htmlLine+='<label><input type="radio" name="planCourseDto['+count+'].isCompulsory" value="0" /><span style="float:left; margin-right:20px;">选修</span></label>'+
			              '<label><input name="planCourseDto['+count+'].isCompulsory" type="radio" value="1" checked/><span style="float:left; margin-right:100px;">必修</span></label>';
			}
			else{
				htmlLine+='<label><input type="radio" name="planCourseDto['+count+'].isCompulsory" value="0" /><span style="float:left; margin-right:20px;">选修</span></label>'+
			              '<label><input name="planCourseDto['+count+'].isCompulsory" type="radio" value="1" /><span style="float:left; margin-right:100px;">必修</span></label>';
			}
			htmlLine+='<div class="col-md-4"><button type="button" title="移除" class="learn_removed" onclick="detr(\'isOnLine'+(count+1)+'\')"><i class="icon-remove"></i></button>'+
			' <button type="button" title="升序" class="learn_upper" onclick="ascending(\'isOnLine'+(count+1)+'\')"><img src="${ctx}/resources/imgs/u11115.png"></button>'+
			' <button type="button" title="降序" onclick="descending(\'isOnLine'+(count+1)+'\')"><img src="${ctx}/resources/imgs/u11119.png"></button></div>'+	
			'</div>';
		  	
			if("${markType}" == "stage"){
				htmlLine+= '<div class="col-md-12 sameWadte marL"><span class="fl">任务日期：</span>'+
    		'<input type="text" placeholder="开始时间" name="planCourseDto['+count+'].startTime" class="Wdate" style="max-width: 180px;" value="${lineCourse.startTime}" onfocus="WdatePicker({onpicked: function(){jQuery(this).trigger(\'change\');},oncleared: function(){jQuery(this).trigger(\'change\');}})"><span class="fl">&nbsp;—&nbsp;</span>'+
    		'<input type="text" placeholder="结束时间" name="planCourseDto['+count+'].endTime" class="Wdate" style="max-width: 180px;" value="${lineCourse.endTime}" onfocus="WdatePicker({onpicked: function(){jQuery(this).trigger(\'change\');},oncleared: function(){jQuery(this).trigger(\'change\');}})">&nbsp;&nbsp;'+
    		'<span class="fl pad-left-20">签到地点：</span>'+
    		'<input class="ipt-text proTime" name="planCourseDto['+count+'].signAddress" type="text" placeholder="" value="${lineCourse.signAddress}">';
			}
			htmlLine+= '</li>';
			count++;
		  </c:forEach>
		 
		  if(htmlLine !=""){
			  
		  }
		  if("${markType}" == "stage"){
			  $("#isOnLineCourse").html(htmlLine);
		  }else{
			  $("#ordinaryLine").html(htmlLine);
		  } 
	  }
	  courseDivNumS = count;
	  if('${postDevelopment.isFree}' == '0' ){
	  	 $("#integraText").addClass("notNull");
	  }
	  if("${postDevelopment.isRewardPoint}" == '1'){
	 	 $("#chargeText").addClass("notNull");
	  }
	  $("#"+"${classPlan.qualificationId}").attr("selected","selected");
	  $("#qualifySele").find("[id='${classPlan.qualificationId}']").attr("selected","selected");
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
	  if($(this).is(':checked')){
		  $(this).attr("value",1);
		  html+='<li class="col-md-12" id="isNotSign"><strong><ins>*</ins>完成课程后是否签约:</strong>'+
		  '<label class="isNot"><input type="radio" name="classPlan.isSign" id="isSign" value="1" />是</label>'+
		  '<label class="isNot"><input type="radio" name="classPlan.isSign" id="notSign" value="2" />否</label></li>';
		  $("#train").after(html);
	  }else{
		  $(this).removeAttr("value");
		  $("#isNotSign").remove();
	  }
  });
  $("#bkck").click(function () {
	  if($(this).is(':checked')){
		  $(this).attr("value",1);
	  }else{
		  $(this).removeAttr("value");
	  }
	  
  });
  $("#jtgk").click(function () {
	  if($(this).is(':checked')){
		  $(this).attr("value",1);
	  }else{
		  $(this).removeAttr("value");
	  }
  });
  $("#award").click(function () {
	  if($(this).is(':checked')){
		  $(this).attr("value",1);
	  }else{
		  $(this).removeAttr("value");
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
		  $(this).attr("value",1);
		  $("#integraText").addClass("notNull");
	  }else{
		  $(this).removeAttr("value");
		  $("#integraText").removeClass("notNull");
	  }
  });
  $("#qualify").click(function () {
	  if($(this).is(':checked')){
		  $(this).attr("value",1);
	  }else{
		  $(this).removeAttr("value");
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
			  html +='<div name="courseDiv" class="col-md-8" id="courseDiv'+(i+1)+'">'+
			    '<input type="hidden" id="'+ids[count]+'" name="planCourseDto['+i+'].associateId" value="'+ids[count]+'"/>'+
				'<input type="hidden" name="planCourseDto['+i+'].seq" value="'+(seq+1)+'"/>'+
				'<input type="hidden" name="planCourseDto['+i+'].stage" value="'+stage+'"/>'+
				'<input type="hidden" name="planCourseDto['+i+'].associateType" value="'+type+'"/>'+
				'<input type="hidden" name="planCourseDto['+i+'].startTime" value=""/>'+
				'<input type="hidden" name="planCourseDto['+i+'].endTime" value=""/>'+
				'<span>'+names[ids[count]]+'</span><label>'+
				'<input type="radio" name="planCourseDto['+i+'].isCompulsory" value="0" />选修</label><label><input name="planCourseDto['+i+'].isCompulsory" type="radio" value="1"/>必修</label>'+
				'</div>'+
				'<div class="col-md-4">'+					
				'<button type="button" title="移除课程" class="learn_remove sc" onclick="removeCourse(\'courseDiv'+(i+1)+'\',\''+stageLiMark+'\')"><i class="icon-remove i" ></i></button>'+
				'<button type="button" title="升序" class="learn_upper up" onclick="moveUp(\'courseDiv\','+(i+1)+',\''+stageLiMark+'\')"><img src="${ctx }/resources/imgs/u11115.png"></button>'+
				'<button type="button" title="降序" class="down" onclick="moveDown(\'courseDiv\','+(i+1)+',\''+stageLiMark+'\')"><img src="${ctx }/resources/imgs/u11119.png"></button>'+
				'</div>';
				count++;
				seq++;
				/* var j = i+1;
				courseDivNum = j; */
		 }
		 var courseObj = $("#"+stageLiMark).find("div[name='classDiv']");
		 courseObj.append(html);
		 courseDivNumS = $("[name='courseDiv']").length;
      }else{
    	  var countIOL = 0;
    	  for(var i = courseDivNum ; i < ids.length+courseDivNum ; i++){
    		  type = 3;
    		  if(stageLiMark == "ordinaryOnLine"){
    			  type = 2;
    		  }
	    	  html+= '<li class="sameWadte" style="padding-left: 0" name="courseDiv" id="isOnLine'+(i+1)+'" >'+
	    	    '<input type="hidden" id="'+ids[count]+'" name="planCourseDto['+i+'].associateId" value="'+ids[count]+'"/>'+
				'<input type="hidden" name="planCourseDto['+i+'].seq" value="'+(seq+1)+'"/>'+
				'<input type="hidden" name="planCourseDto['+i+'].associateType" value="'+type+'"/>'+
				'<input type="hidden" name="planCourseDto['+i+'].stage" value="1"/>'+
				'<div style="width:20%;float: left;margin-left:10%;"><span style="float: left">'+names[ids[count]]+'</span></div>'+
	    	  	'<label><input type="radio" name="planCourseDto['+i+'].isCompulsory" value="0" /><span style="float:left; margin-right:20px;">选修</span></label>'+
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
	    $("#"+stageLiMark).append(html);
	    courseDivNumS = $("[name='courseDiv']").length;
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
	  layer.open({
		  type: 2,
		  closeBtn: 0,
		  title: '班级负责人',
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
		  title: false,
		  closeBtn: 1, //不显示关闭按钮
		  shade: [0],
		  area: ['800px', '450px'],
		  content: "${ctx}/manage/classPlan/paperList", 
		  end: function(){
			  
		  }
	   });
  }
  var N = [
           "零", "一", "二", "三", "四", "五", "六", "七", "八", "九","十"
       ];
  function addStage(){
	  var mark = $("li[name='stageLi']").length+1;
	  divMark = mark;
	  var html = "";
	  html +='<li name="stageLi" id="stageLi'+mark+'" class="col-md-12 remove-middle-border">'+
		'<div class="col-md-3 border-stage" style="height: 150px;" name="stageNum">'+
		'<span name="spanStageNum" style="display:block; padding-top:30px;">';
		if(mark>10){
			mark = mark.toString();
			html +='第'+N[10]+N[mark[1]]+'阶段';
		}else{
			html +='第'+N[mark]+'阶段';
		}
		html +='</span>'+
		'<div class="sameWadte date" style="overflow-y:auto;">'+
		'<input type="text" placeholder="开始时间" value="" name="startTime" class="Wdate startDate" style="max-width: 100px;" onfocus="WdatePicker({onpicked: function(){jQuery(this).trigger(\'change\');},oncleared: function(){jQuery(this).trigger(\'change\');}})">'+
		'&nbsp;—&nbsp;'+
		'<input type="text" placeholder="结束时间" value="" name="endTime" class="Wdate endDate" style="max-width: 100px;" onfocus="WdatePicker({onpicked: function(){jQuery(this).trigger(\'change\');},oncleared: function(){jQuery(this).trigger(\'change\');}})">'+
		'</div>'+
		'</div>'+
		'<div class="col-md-6 border-stage" name="classDiv" style="overflow-y:auto;">'+
		'</div>'+
		'<div class="col-md-3 border-stage" style="height: 150px;">'+
		'<div class="aButton"><a class="btn-submit btn-sm btn " href="javascript:courseManager(\'1\',\'stageLi'+mark+'\')"  title="添加线上课程/课程组">添加线上课程/课程组</a></div>'+
	    '<div class="aButton"><a class="btn-submit btn-sm btn " href="javascript:addPaper(\'stageLi'+mark+'\')"  title="添加试卷">+添加试卷</a></div>'+
		'<div class="aButton">'+
		'<button type="button" name="orev" title="删除阶段" class="learn_remove remove-link" onclick="removeStage(\'stageLi'+mark+'\')" style="vertical-align: middle;"><i class="icon-remove" style="color: red;font-size: 20px;margin-right: 9px"></i></button>'+
		'<button type="button" title="升序" class="learn_upper" onclick="moveUp(\'stageLi\','+mark+')"><img src="${ctx }/resources/imgs/u11115.png"></button>'+
		'<button type="button" title="降序" onclick="moveDown(\'stageLi\','+mark+',\''+stageLiMark+'\')"><img src="${ctx }/resources/imgs/u11119.png"></button>'+
		'</div>'+
		'</div>'+
		'</li>';
	  $("#stageUl").append(html);
  }
  function moveUp(id,mark,liId){
		  var prevMark;
		  /* var mark = $("#"+id).find("input[name$='.stage']").val(); */
		  var prevDIV = '';
		  var thisDIV = '';
		  if(id == "stageLi"){
			  if($("#"+id+mark).prev().length==0){
				  layer.msg("已是第一个");
			  }else{
				  prevDIV = $("#"+id+mark).prev().find("div[name='classDiv']");
				  thisDIV = $("#"+id+mark).find("div[name='classDiv']");
				  prevDIV.find("input[name$='.stage']").each(function(){
					  prevMark = $(this).val();
					  $(this).val(mark);
				  });
				  thisDIV.find("input[name$='.stage']").each(function(){
					  $(this).val(prevMark);
				  });
				  $("#"+id+mark).prev().before($("#"+id+mark));
				  $("span[name='spanStageNum']").each(function(i){
					  $(this).text("第"+N[i+1]+"阶段");
				  })
			  }
		  }else if(id == "courseDiv" || id == "isOnLine"){
			  if($("#"+id+mark).prev().length == 0){
				  layer.msg("已是第一个");
			  }
			  prevMark =  $("#"+id+mark).prevAll("[name = 'courseDiv']").eq(0).find("input[name$='.seq']").val();
			  $("#"+id+mark).prevAll("[name = 'courseDiv']").eq(0).find("input[name$='.seq']").val(mark);
			  $("#"+id+mark).find("input[name$='.seq']").val(prevMark);
			  prevDIV = $("#"+id+mark).prevAll("[name='courseDiv']").eq(0);
			  thisDIV = $("#"+id+mark);
			  var thisHtml = thisDIV.html();
			  var prevHtml = prevDIV.html();
			  prevDIV.html(thisHtml);
			  thisDIV.html(prevHtml);
		  }
  }
  function moveDown(id,mark,liDiv){
		  var nextMark = mark+1;
		  var nextDIV;
		  var thisDIV;
		  if(id == "stageLi"){
			  if($("#"+id+mark).next().length==0){
				  layer.msg("已是最后一个");
			  }else{
				  nextDIV = $("#"+id+mark).next().find("div[name='classDiv']");
				  thisDIV = $("#"+id+mark).find("div[name='classDiv']");
				  nextDIV.find("input[name$='.stage']").each(function(){
					  nextMark = $(this).val();
					  $(this).val(mark);
				  });
				  thisDIV.find("input[name$='.stage']").each(function(){
					  $(this).val(nextMark);
				  });
				  $("#"+id+mark).next().after($("#"+id+mark));
				  $("span[name='spanStageNum']").each(function(i){
					  $(this).text("第"+N[i+1]+"阶段");
				  })
			  }
		  }else if($("#"+id+mark).nextAll("[name = 'courseDiv']").length==0){
				  layer.msg("已是最后一个");
		  }else{
			  nextDIV = $("#"+id+mark).nextAll("[name = 'courseDiv']").eq(0);
			  thisDIV = $("#"+id+mark);
			  nextDIV.find("input[name$='.seq']").val(mark);
			  thisDIV.val(nextMark);
		  }
		  var thisHtml = thisDIV.html();
		  var nextHtml = nextDIV.html();
		  nextDIV.html(thisHtml);
		  thisDIV.html(nextHtml);
  }
  function removeCourse(divId,liId){
	  $("#"+divId).next().remove();
	  $("#"+divId).remove();
	  $("#"+liId).find("[name='courseDiv']").each(function(i){
		  $(this).find("input[name$='.seq']").val(i+1);
	  });
  }
  function save(){
	 
		  var classPlanName = '${classPlan.name}' ;
		 $("li[name='stageLi']").each(function(){
			 var startTime = $(this).find("input[name='startTime']").eq(0).val();
			 var endTime = $(this).find("input[name='endTime']").eq(0).val();
			 $(this).find("input[name$='.startTime']").each(function(){
				 $(this).val(startTime);
			 })
			 $(this).find("input[name$='.endTime']").each(function(){
				 $(this).val(endTime);
			 })
		 });
		 
		 $.ajax({
				type : "post",
				data : $('#stageForm').serialize(),
				url : "${ctx}/manage/development/save",
				success : function(data) {
					if(data.code==1){
						layer.msg("保存成功");
						location.href="${ctx}/manage/classPlan/index";
					}else{
						layer.msg("保存失败")
					}
				}
			});
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
  function labelHtml(type){
	  if(type==1){
		  /* $("#stage").attr("class","active"); */
		  $("#stage").find("input").each(function(){
			  $(this).removeAttr("disabled");
		  });
		  $("#ordinary").find("input").each(function(){
			  $(this).attr("disabled","disabled");
		  });
	  }else{
		  /* $("#stage").removeAttr("class","active"); */
		/*   $("#ordinary").attr("class","active"); */
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
	   window.location.href = "${ctx}/manage/development/getDevelopment";
   }
   
   function view(id,type){
	   if(type==1){
		   layer.open({
				  type: 2,
				  title: false,
				  closeBtn: 1, //不显示关闭按钮
				  shade: [0],
				  area: ['1200px', '700px'],
				  content: "${ctx}/manage/development/selectPaper?id="+id, 
				  end: function(){
					  
				  }
			   });
		   
	   }else{
		   
		   layer.open({
				  type: 2,
				  title: false,
				  closeBtn: 1, //不显示关闭按钮
				  shade: [0],
				  area: ['1200px', '700px'],
				  content: "${ctx}/manage/development/selectCourse?id="+id, 
				  end: function(){
					  
				  }
			   });
		   
	   }
	   
	   
	   
	   
	   
   }
</script>
</body>
</html>
