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
</style>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/style.css">
<script type="text/javascript" src="${ctx}/resources/js/common/jquery-3.2.1.min.js" ></script>
<script type="text/javascript"	src="${ctx}/resources/libs/layer/layer.js"></script>
<script type="text/javascript"	src="${ctx}/resources/libs/input/input-validate.js"></script>
		<div class="form-detail offline_form">
			<div class="title diff_btn">
				<strong>班级计划预览</strong>
				<input type="hidden" id="classPlanFwVo" name="classPlanFwVo" value="${classPlanFwVo }">
				<!-- <button type="button" class="btn btn-sm btn-submit ipt-btnfile">选择课程</button> -->
			</div>
			<ul class="clearfix form_learn">
				<li class="col-md-12"><strong>班级计划名称：</strong>${classPlan.name }</li>
				<li class="col-md-12"><strong>班级计划说明：</strong>${classPlan.description }</li>
				<li class="col-md-3">
					<strong>班级计划分类：</strong>
					<c:if test="${classPlan.tagId eq 1 }">初审面试</c:if>
					<c:if test="${classPlan.tagId eq 2 }">甄选面试</c:if>
					<c:if test="${classPlan.tagId eq 3 }">决定面试</c:if>
				</li>
				<li class="col-md-12 zlgl_share" id="train"><strong>培训对象：</strong>
					<label for="ksh"><c:if test="${classPlan.isTrainingNew == 1 }">新人</c:if></label>
					<label for="bkck"><c:if test="${classPlan.isTrainingAgent == 1 }">内勤</c:if></label>
					<label for="jtgk"><c:if test="${classPlan.isTrainingStaff == 1 }">外勤</c:if></label>
				</li>
				<c:if test="${classPlan.isTrainingNew == 1 }">
					<li class="col-md-12" id="isNotSign"><strong>完成课程后是否签约:</strong>
					  <label class="isNot">
					  <c:if test="${classPlan.isSign == 1 }">是</c:if>
					  <c:if test="${classPlan.isSign == 2 }">否</c:if>
					  </label>
			 		</li>
		 		</c:if> 
				<li class="col-md-4"><strong>班级负责人：</strong>
					${managerName }
				</li>
				<li class="col-md-4" style="margin-left: 30px;"><strong>班级限额人数：</strong>${classPlan.userLimit }</li>
			</ul> 
			<div class="title"><strong>班级计划详情</strong></div>
			<div class="cycle_hd sameWadte date">
				<b>班级周期：</b>
				 <fmt:formatDate value="${classPlan.startDate }" pattern="yyyy-MM-dd"/>
				 &nbsp;—&nbsp;
				 <fmt:formatDate value="${classPlan.endDate }" pattern="yyyy-MM-dd"/>
			</div>
			<div class="nav-tabs">
			    <c:if test="${markType eq 'stage'}"><strong>阶段型计划</strong></c:if>
			    <c:if test="${markType eq 'ordinary'}"><strong>普通型计划</strong></c:if>
			</div>
			<div class="tab-pane active" id="stage">
				<div class="title"><strong>课程</strong><button type="button" title="删除考题"><!-- <i class="icon-delete"></i> --></button></div>
				<ul id="stageUl" class="edit clearfix xxjh_add">
						<li name="stageLi" id="stageLi1" class="col-md-12 remove-middle-border">
							<div class="col-md-3 border-stage date" style="height: 180px;" name="stageNum">
								<span name="spanStageNum" style="display:block; padding-top:40px;">第一阶段</span>
								<div class="sameWadte" style="overflow-y:auto;margin-top:20px"">
								&nbsp;—&nbsp;
								</div>
							</div>
							<div class="col-md-6 border-stage" name="classDiv" style="overflow-y:auto;height: 180px;">	
							
							</div>
						</li>
				</ul>
			</div>
			<div class="tab-pane" id="ordinary">
				<ul class="edit clearfix">
					<li class="col-md-12">
						<strong>课程：</strong>
						<ol id="ordinaryOnLine">
							
						</ol>
					</li>
				</ul>
			</div>
           <div class="title" id="xianzhi"><strong>计划限制</strong>
           </div>
			<ul class="clearfix">
				<li class="col-md-3"><strong>人员限制：</strong><span id="authorityName"></span></li>
				<li class="col-md-12 zlgl_share"><strong>计划售价：</strong>
					<c:if test="${classPlan.isFree == 1 }">
						免费
					</c:if>
					<c:if test="${classPlan.isFree == 0 }">
						${classPlan.exchangePoint }积分兑换
					</c:if>
				</li>
				<li class="col-md-12 zlgl_share">	
					<c:if test="${classPlan.isRewardPoint == 1 }">
						<strong>计划奖励：</strong>
						<label for="integra">
							可获得${classPlan.rewardPoint }积分
						</label>
					</c:if>
				</li>
				<li class="col-md-12 zlgl_share">	
					<c:if test="${classPlan.isRewardCertification == 1 }">
						<strong>证书奖励：</strong>
					</c:if>
				</li>
				<li class="col-md-12 zlgl_share">	
						<c:if test="${classPlan.isRewardQualification == 1 }">
						<strong>DMS资格：&nbsp;</strong>
						<label for="qualify" >
						<c:if test="${classPlan.qualificationId eq 0}">保险</c:if>
						<c:if test="${classPlan.qualificationId eq 1}">技巧</c:if>
						<c:if test="${classPlan.qualificationId eq 2}">产品</c:if>
						<c:if test="${classPlan.qualificationId eq 3}">沟通</c:if>
						</label>
						</c:if>
				</li>
				<li class="col-md-12 zlgl_share">
					<strong>是否允许计划过期后继续任务：</strong>
					<label for="allow" style="margin-left: 120px;">
						<c:if test="${classPlan.isAllowExpired eq 1 }">
						允许
						</c:if>
						<c:if test="${classPlan.isAllowExpired eq 0 }">
						不允许
						</c:if>
					</label>
				</li>
					<li class="col-md-12 zlgl_share" >
						<strong>过期提醒：</strong>
						<c:if test="${classPlan.isExpiredAlarm eq 1 }">
						开启
						<div class="remind" style="padding: 0" name="tixing" id="olgAxpired">
							<%-- <c:forEach items="${expiredAlarm}" var="expiredAlarm" varStatus="ext">
							    <div name="olg" style="margin-left:10px ;margin-top:5px;">
								<span>第${ext.index+1}次提醒：</span><br>
								<span>过期前&nbsp;<input type="text" class="ipt-text" value="${expiredAlarm.beforeDay1 }" style="max-width: 50px">&nbsp;天</span>
								</div>
							</c:forEach> --%>
						</div>
						</c:if>
						<c:if test="${classPlan.isExpiredAlarm eq 0 }">
						关闭
						</c:if>
					</li>
			</ul>
			<div class="ui-button">
				<button type="button" class="btn btn-submit" onclick="save()">保存</button>
				<!-- <button type="button" class="btn btn-submit" onclick="preview()">保存</button> -->
				<button type="button" class="btn btn-default" onclick="backOne()">取消</button>
			</div>
	</form>	
		</div>
<script type="text/javascript" src="${ctx}/resources/libs/datepicker/WdatePicker.js"></script>
<%-- <script type="text/javascript" src="${ctx}/resources/js/elerning/common.js"></script> --%>
<script>
  var courseDivNumS = 0;
  $(function(){
	  var html = "";
	  var classPlanVo = parent.htmlCont;
	  var markType = classPlanVo.markType;
	  var count = 0;
	  var planCourseDto = classPlanVo.classPlanFwVo.planCourseDto;
	  var expiredAlarm = classPlanVo.expiredAlarm;
	 
	  if(markType == "stage"){
		  var stageNum = classPlanVo.stageNum;;
		  for(var mark = 1; mark <= stageNum; mark++){
		  html +='<li name="stageLi" class="col-md-12 remove-middle-border">'+
			'<div class="col-md-3 border-stage" style="height: 180px;width:30%;" name="stageNum">'+
			'<span name="spanStageNum" style="display:block; padding-top:40px;">';
			if(mark>10){
				mark = mark.toString();
				html +='第'+N[10]+N[mark[1]]+'阶段';
			}else{
				html +='第'+N[mark]+'阶段';
			}
			html +='</span>'+
			'<div class="sameWadte date" style="overflow-y:auto;margin-top:20px">'+
			parent.$("#stageStartTime"+mark).val()+
			'&nbsp;—&nbsp;'+
			parent.$("#stageEndTime"+mark).val()+
			''+
			'</div>'+
			'</div>'+
			'<div class="col-md-6 border-stage" name="classDiv" style="overflow-y:auto;height: 180px;width:70%;">';
			$.each(planCourseDto,function(indexPct,pct){
				$.each(planCourseDto,function(indexPc,pc){
					if(pc.seq== indexPct+1 && pc.stage==mark){
					    html +='<div name="courseDiv" class="col-md-8" style="margin-left:10%;width:80%;">'+
						'<span style="margin-right:20px;">'+parent.courseName[pc.associateId]+'</span>';
						if(pc.isCompulsory=="0"){
							html +='选修';
						}else if(pc.isCompulsory=="1"){
							html +='必修';
						}
						html +='</div>';
					}
				});
			});
			html+='</div>'+
			'</li>';
		 	}
			$("#stageUl").html(html);
		 }else{
			 $.each(planCourseDto,function(index,pc){
				 if(pc.associateId != null){
					  html+= '<li style="padding-left: 0" name="courseDiv" >'+
						'<div style="width:20%;float: left;margin-left:10%;"><span style="float: left">'+parent.courseName[pc.associateId]+'</span></div>'+
						'<div>';
						if(pc.isCompulsory==0){
							html+='<label><span style="float:left; margin-right:20px;">选修</span></label>';
						}
						else if(pc.isCompulsory==1){
							html+='<label><span style="float:left; margin-right:20px;">必修</span></label>';
						}
						html+='</li>';
					  }
			 });
	    	$("#stageUl").html(html);
	  }
	  $("#authorityName").text(parent.$("#authorityName").val());
	  var olgHtml = "";
	  if("${classPlan.isExpiredAlarm}" == '1'){
		  $.each(expiredAlarm,function(index,expiredAlarm){
			  olgHtml +='<div name="olg" style="margin-left:10px ;margin-top:5px;">'+
				'<span name="olgAxpired">';
			    if(index>10){
			    	index = index.toString();
			    	olgHtml +='第'+N[10]+N[mark[1]]+'次提醒：';
				}else{
					olgHtml +='第'+N[index+1]+'次提醒：';
				}
			    olgHtml +='</span><br>'+
				'<span>过期前&nbsp;<input type="text" class="ipt-text"  value="'+expiredAlarm.beforeDay1 +'" style="max-width: 50px" onkeyup="ea_digit_verify(this)">&nbsp;天</span>'+
				'</div>';
		  });
	  }
	  $("#olgAxpired").html(olgHtml);
  });
  var N = [
           "零", "一", "二", "三", "四", "五", "六", "七", "八", "九","十"
       ];
  function save(){
	  layer.load(1, {
			shade: [0.1,'#f8f8f8'] //0.1透明度的白色背景
		});
	  var name = parent.$("input[name='classPlan.name']").val();
	  var isNot = 1;
	  if(name != parent.classPlanName){
		  isNot = 0;
	  }
	  $.ajax({
		    async : false ,
			type : "post",
			data : parent.$('#stageForm').serialize(),
			url : "${ctx}/manage/classPlan/save?isNot="+isNot,
			success : function(data) {
				if(data.code==1){
					layer.confirm('保存成功', {
						icon: 3,
					  	btn: ['确定', '取消'] //按钮
					},  function(){
						parent.saveSuccess();
						var index=parent.layer.getFrameIndex(window.name);
						parent.layer.close(index);
					},function(){
						parent.saveSuccess();
						var index=parent.layer.getFrameIndex(window.name);
						parent.layer.close(index);
					}
					);
				}else{
					var index = layer.load();
					layer.close(index);
					layer.msg("保存失败")
				}
			},error:function(){
				var index = layer.load();
				layer.close(index);
				layer.msg("保存失败")
			}
		});
  }
  function backOne(){
		var index=parent.layer.getFrameIndex(window.name);
		parent.layer.close(index);
	}
</script>
</body>
</html>
