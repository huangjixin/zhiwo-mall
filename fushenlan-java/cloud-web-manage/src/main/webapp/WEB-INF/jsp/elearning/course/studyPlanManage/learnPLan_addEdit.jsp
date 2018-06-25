<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<title>富卫运维大平台</title>
<meta name="description" content="">
<link rel="stylesheet" type="text/css" href="../../css/style.css">
<link rel="stylesheet" type="text/css" href="../../css/font/iconfont.css">
<link rel="stylesheet" type="text/css" href="../../css/page.css">
</head>
<body>
<script type="text/javascript"	src="${ctx}/resources/libs/layer/layer.js"></script>
<style type="text/css">
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
	.paper-stage {
		overflow: hidden;
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
	
	.commonPlan{
    border:solid,1px,red;
	border-radius:8px;
	height: 150px;
	}
	
</style>
<script type="text/javascript">
var names=['一','二','三','四','五','六','七','八','九','十','十一','十二','十三','十四','十五'];//阶段名
</script>
<div class="form-detail">
			<div class="title" ><strong>学习计划基本信息</strong></div>
			<form action="${ctx}/manage/studyPlanManage/testPaper_preview" id="inputForm" name="inputForm" method="post">
			 <ul class="clearfix form_learn">
			 	<input type="hidden" id="plan_id" name="studyPlan.id" value="${id }" />
			 	<input type="hidden" id="planType" name="studyPlan.planType" value="${studyPlanCopy.planType}" />
				<li class="col-md-12"><strong><ins>*</ins>计划名称：</strong><input type="text" id="plan_name" name="studyPlan.name"  <c:if test="${name!=null }">value="${name }"</c:if>  class="ipt-text" placeholder="个人背景调查"></li>
				<li class="col-md-12"><strong><ins>*</ins>计划说明：</strong><input type="text" id="plan_description" name="studyPlan.description" <c:if test="${description!=null }">value="${description }"</c:if>   class="ipt-text" placeholder="个人背景调查"></li>
				<li class="col-md-4">
					<strong><ins>*</ins>计划分类：</strong>
					<div class="ipt-select">
						<i class="icon-arrow-down"></i>
						<c:if test="${tages!=null }">
						<select id="tagId" name="studyPlan.tagId">
						  <option  value="">--请选择--</option>
						  <c:forEach items="${tags }" var="item">
						  <option <c:if test="${tagId==tags.id }">selected</c:if> value="tags.id">${tags.tagName }</option>
						  </c:forEach>
						</select>
						</c:if>
						<c:if test="${tages==null }">
						<select id="tagId" name="studyPlan.tagId">
						  <option  value="">--请选择--</option>
							<option <c:if test="${tagId==1 }">selected</c:if> value="1">保险</option>
							<option <c:if test="${tagId==2 }">selected</c:if> value="2">技巧</option>
							<option <c:if test="${tagId==3 }">selected</c:if> value="3" >产品</option>
							<option <c:if test="${tagId==4 }">selected</c:if> value="4">沟通</option>
						</select>
						</c:if>
					</div>
				</li>
			</ul> 
			<div class="title"><strong>学习计划详情</strong></div>
			<div class="nav-tabs">
				<strong class="active"  onclick="asd('1')" id="stage1">阶段型计划</strong>
				<strong id="stage2" onclick="asd('2')">普通型计划</strong>
			</div>
	     <div class="nav-content">
		    <div class="tab-pane active"  id="stagec1">
				    <div class="title"><strong>线上课程</strong></div>
					<ul id="ul1" class="edit clearfix xxjh_add">
						<li id="li1" class="col-md-12 remove-middle-border">
							<div class="col-md-3 border-stage mm" style="height: 150px;line-height: 150px;">
								第一阶段
							</div>
							<div class="col-md-6 border-stage course" style="height:150px;overflow-y:auto;">	
								<c:forEach items="${tarPlanCourse }" var="item" varStatus="idx">
									<c:if test="${maxStage>1 && item.stage==1  }"><!-- 最大阶段大于一 -->
										<div class="paper-stage">
											<div class="col-md-8" >
												<input name="planCourse[${idx.index}].seq" type="hidden" id="seq" value="${item.seq }"/>
												<input name="planCourse[${idx.index}].isStage" type="hidden" id="isStage" value="1"/>
											    <input name="planCourse[${idx.index}].stage" type="hidden" value="${item.stage }"/>
												<input tar="k" name="planCourse[${idx.index}].associateId" type="hidden" value="${item.associateId }"/>
												<input name="planCourse[${idx.index}].associateType" type="hidden" value="${item.associateType }"/>
												<input name="planCourse[${idx.index}].desName" type="hidden" value="${item.name }" />
												<span>${item.name }</span> 
												<label><input type="radio"  name="planCourse[${idx.index}].isCompulsory" value="1" <c:if test="${item.isCompulsory==1 }">checked</c:if> />必修</label>
												<label><input name="planCourse[${idx.index}].isCompulsory" value="0" type="radio"  <c:if test="${item.isCompulsory==0 }">checked</c:if>/>选修</label>
											</div>
											<div class="col-md-4">					
												<button type="button" vale="'+array[i].id+'" stag="1" content="rev" title="移除考题内容" onclick="deleteIn(this)"  class="learn_remove sc remove-paper"><i class="icon-remove i" ></i></button>
												<button type="button" content="up" title="升序" class="learn_upper up paper-stage-up" ><img src="${ctx }/resources/imgs/u11115.png"></button>
												<button type="button" content="down" title="降序" class="down paper-stage-down" ><img src="${ctx }/resources/imgs/u11119.png"></button>
											</div>
										</div>
									</c:if>
								</c:forEach>
								<!-- 只有一个阶段 -->
								<c:forEach items="${tarPlanCourse }" var="item" varStatus="idx">
									<c:if test="${isStage==1 && item.stage==1 && maxStage==1}">
										<div class="paper-stage">
											<div class="col-md-8" >
												<input name="planCourse[${idx.index}].seq" type="hidden" id="seq" value="${item.seq }"/>
												<input name="planCourse[${idx.index}].isStage" type="hidden" id="isStage" value="1"/>
											    <input name="planCourse[${idx.index}].stage" type="hidden" value="${item.stage }"/>
												<input tar="k" name="planCourse[${idx.index}].associateId" type="hidden" value="${item.associateId }"/>
												<input name="planCourse[${idx.index}].associateType" type="hidden" value="${item.associateType }"/>
												<input name="planCourse[${idx.index}].desName" type="hidden" value="${item.name }" />
												<span>${item.name }</span> 
												<label><input type="radio"  name="planCourse[${idx.index}].isCompulsory" value="1" <c:if test="${item.isCompulsory==1 }">checked</c:if> />必修</label>
												<label><input name="planCourse[${idx.index}].isCompulsory" value="0" type="radio"  <c:if test="${item.isCompulsory==0 }">checked</c:if>/>选修</label>
											</div>
											<div class="col-md-4">					
												<button type="button" vale="'+array[i].id+'" stag="1" content="rev" title="移除考题内容" onclick="deleteIn(this)"  class="learn_remove sc remove-paper"><i class="icon-remove i" ></i></button>
												<button type="button" content="up" title="升序" class="learn_upper up paper-stage-up" ><img src="${ctx }/resources/imgs/u11115.png"></button>
												<button type="button" content="down" title="降序" class="down paper-stage-down" ><img src="${ctx }/resources/imgs/u11119.png"></button>
											</div>
										</div>
									</c:if>
								</c:forEach>
								</div>
						<!-- ------------------------------------------------------ -->
							
							<div class="col-md-3 border-stage" style="height: 150px;">
								<a class="btn-submit btn-sm btn " href="javascript:addcourse('li1')"  title="添加线上课程">+添加线上课程</a>
							    <a class="btn-submit btn-sm btn " href="javascript:addtest('li1')"  title="添加试卷">+添加试卷</a>
							    <a class="btn-submit btn-sm btn" href="javascript:adddowncourse('li1')"  title="添加线下课程">+添加线下课程</a>
								<div>	
								    <button stag="1" type="button" name='orev' title="移除考题内容" class="learn_remove remove-link" style="vertical-align: middle;"><i class="icon-remove" style="color: red;font-size: 20px;margin-right: 9px"></i></button>
								    <button type="button" name='oup' title="升序" class="learn_upper stage-go-up"><img src="${ctx }/resources/imgs/u11115.png"></button>
								    <button type="button" name='odown' title="降序" class="stage-go-down"><img src="${ctx }/resources/imgs/u11119.png"></button>
								</div>
							</div>
						</li>
	     <c:if test="${maxStage>1 }" >
		<c:forEach var="index" begin="2" end="${maxStage }" step="1" varStatus="stus"><!-- 初始化阶段 -->
		<li id="li${stus.index}" class="col-md-12 remove-middle-border"><div class="col-md-3 border-stage mm" style="height: 150px;line-height: 150px;">			
	            第${stus.index}阶段</div><div class="col-md-6 border-stage course" style="height:150px">			
		        <c:forEach items="${tarPlanCourse }" var="item" varStatus="idx">
					<c:if test="${item.stage==stus.index }">
					<div class="paper-stage"><div class="col-md-8">
					<input name="planCourse[${idx.index}].seq" id="seq" type="hidden" value="${item.seq }"/>
				    <input name="planCourse[${idx.index}].stage" type="hidden" value="${item.stage }"/>
					<input tar="k" name="planCourse[${idx.index}].associateId" type="hidden" value="${item.associateId }"/>
					<input name="planCourse[${idx.index}].associateType" type="hidden" value="${item.associateType }"/>
					<input name="planCourse[${idx.index}].desName" type="hidden" value="${item.name }" />
					<span>${item.name }</span> 
					<label><input type="radio"  name="planCourse[${idx.index}].isCompulsory" <c:if test="${item.isCompulsory==1 }">checked</c:if> value="1" />必修</label>
					<label><input name="planCourse[${idx.index}].isCompulsory" value="0" type="radio"  <c:if test="${item.isCompulsory==0 }">checked</c:if>/>选修</label>
					</div>
					<div class="col-md-4">	
					<button type="button" vale="'+array[i].id+'" stag="${stus.index }" content="rev" title="移除考题内容" onclick="deleteIn(this)"  class="learn_remove sc remove-paper"><i class="icon-remove i" ></i></button>
					<button type="button" content="up" title="升序" class="learn_upper up paper-stage-up" ><img src="${ctx }/resources/imgs/u11115.png"></button>
					<button type="button" content="down" title="降序" class="down paper-stage-down" ><img src="${ctx }/resources/imgs/u11119.png"></button>
					</div></div>
					</c:if>
					 </c:forEach>
		 </div>
	     <div class="col-md-3 border-stage" style="height: 150px;">
		 <a class="btn-submit btn-sm btn" style="margin-top:10px" href="javascript:addcourse('li${stus.index}')"  title="添加线上课程">+添加线上课程</a>
	     <a class="btn-submit btn-sm btn " href="javascript:addtest('li${stus.index}')"  title="添加试卷">+添加试卷</a>
		 <div>	
		 <button type="button" name="orev" stag="${stus.index}" title="移除考题内容" class="learn_remove remove-link" style="vertical-align: middle;">
		 <i class="icon-remove" style="color: red;font-size: 20px;margin-right: 9px"></i></button>
		 <button type="button" title="升序" name="oup" class="learn_upper stage-go-up"><img src="${ctx }/resources/imgs/u11115.png"></button>
		 <button type="button" name="odown" title="降序" class="stage-go-down"><img src="${ctx }/resources/imgs/u11119.png"></button>
		 </div>
			</div></li>			
			</c:forEach>
		   </c:if>
				</ul>
				<button type="button" class="btn btn-submit" onclick="addStage()">+ 添加阶段</button>
				</form>
					<div class="ui-button">
						<a class="btn-submit btn " href="javascript:preview('1');"  title="预览">预览</a>
						<button type="button" class="btn btn-default" onclick="cancle()">取消</button>
					</div>
			 </div>
			
				 <div class="tab-pane" id="stagec2"><!-- 普通计划 -->
				 <form  id="ordinaryForm" action="${ctx}/manage/studyPlanManage/testPaper_preview"  method="post">
				 <input type="text" id="plan_id2" name="studyPlan.id" type="hidden" />
				 <input type="text" id="plan_name2"  name="studyPlan.name" type="hidden" />
				 <input type="text" id="plan_description2" name="studyPlan.description" type="hidden" />
				 <input type="text" id="plan_tagId2" name="studyPlan.tagId" type="hidden" />
				 <input type="hidden" id="plan_planType2" name="studyPlan.planType" value="${studyPlanCopy.planType}" />
					<div class="tab-pane" style="display: block;">
						<ul class="edit clearfix">
							<li class="col-md-12">
								<strong>线上课程：</strong>
								<a class="btn-submit btn-sm btn"  href="javascript:addcourse('dow2')"  title="添加线上课程" >+添加线上课程</a>
								<a class="btn-submit btn-sm btn"  href="javascript:addtest('dow2')"  title="添加试卷" >+添加试卷</a>
								<a class="btn-submit btn-sm btn"  href="javascript:adddowncourse('dow2')" title="添加线下课程">+添加线下课程</a>
						<div class="btn-text" class="commonPlan" id="dow2">
						    <c:forEach items="${tarPlanCourse }" var="item" varStatus="idx">
						    <c:if test="${maxStage==1 && isStage!=1 }" >
					           <div class="paper-stage"> <div class="col-md-8">
					           <input name="planCourse[${idx.index}].seq" id="seq" type="hidden" value="${item.seq }"/>
				               <input name="planCourse[${idx.index}].stage" type="hidden" value="${item.stage }"/>
					           <input tar="k" name="planCourse[${idx.index}].associateId" type="hidden" value="${item.associateId }"/>
					           <input name="planCourse[${idx.index}].associateType" type="hidden" value="${item.associateType }"/>
					           <input name="planCourse[${idx.index}].desName" type="hidden" value="${item.name }" />
					           <span>${item.name }</span> 
					           <label><input type="radio"  name="planCourse[${idx.index}].isCompulsory" <c:if test="${item.isCompulsory==1 }">checked</c:if> value="1" />必修</label>
					           <label><input name="planCourse[${idx.index}].isCompulsory" value="0" type="radio"  <c:if test="${item.isCompulsory==0 }">checked</c:if>/>选修</label>
					           </div> 
					           <div class="col-md-4">					
					           <button type="button" vale="'+array[i].id+'" signp="dowhtml2" stag="'+index+'" content="rev" title="移除考题内容" onclick="deleteIno(this)"  class="learn_remove sc remove-paper"><i class="icon-remove i" ></i></button>
					           <button type="button" onclick="upIn(this)" content="up" title="升序" class="learn_upper up paper-stage-up" ><img src="${ctx }/resources/imgs/u11115.png"></button>
					           <button type="button" onclick="downIn(this)" content="down" title="降序" class="down paper-stage-down" ><img src="${ctx }/resources/imgs/u11119.png"></button>
					         </div>
					   </div>
					        </c:if>
					         </c:forEach>
						</div>	
					</li>
				</ul>
			</div>
					</form>
					<div class="ui-button">
						<a class="btn-submit btn " href="javascript:preview('2');"  title="预览2">预览</a>
						<button type="button" class="btn btn-default" onclick="cancle()">取消</button>
					</div>
			</div>
          </div>
		<%-- <script src="${ctx}/resources/js/common/jquery-3.2.1.min.js"></script> --%>
		<%-- <script src="${ctx}/resources/js/elerning/common.js"></script> --%>
     <script>
 	 var dd=$("input[name$='.stage']").length;
     var htmls=new Array();//阶段线上课程
     var stacounum=dd;//阶段型下标
     var coursenum=dd;//普通型的下标
     var dowhtml1='';//阶段型计划的线下课程html
     var dowhtml2='';//普通型计划的线上课程html
     var dowhtml3='';//普通型计划的线下课程html
     htmls[1]='';
     var html='';
     var issta="${isStage}";
  	 $(function(){
  		var stageNum="${maxStage}";
  		if(stageNum!=null && stageNum!=''){
  		 if(stageNum==1 && issta!=1) {//普通型
  		 $("#stage1").removeClass("active");
  	     $("#stage2").addClass("active");
  	  	 $("#stagec1").removeClass("active");
  	  	 $("#stagec2").addClass("active");
  	     dowhtml2=$("#dow2").html();
  	     dowhtml3=$("#dow3").html();
  		 }else{
  		 for(var i=0;i<=stageNum;i++){//阶段型
  		 if(i==0){
  		  dowhtml1=$("#dow1").html();
  		  }else{
  		   htmls[i]=$("#li"+i).children(".course").html();
  			  }
  			}
  		    }
  			}
  		 $(".mm").each(function(index,ele){
			 $(ele).html('第'+names[index]+'阶段');
		 })
  		
  		});
		var ids=new Array();//每个元素代表每个阶段的值(id)
		
		/* $(function(){
			var keyword  = $("#kd").val();
			page("${ctx}/manage/erecruitment/personnelList?keyWord="+keyword,'${page.pageSize}','${page.pageNo}','${page.pageTotal}','pageDiv')
		}) */
	
		
		function ftr(){
			var keyword  = $("#kd").val();
			location.href="${ctx}/manage/erecruitment/personnelList?keyWord="+keyword;
		}
		
		function check(id){
			location.href="${ctx}/manage/erecruitment/findId?id="+id;
		}
		
		function cancle(){
			location.href="${ctx}/manage/studyPlanManage"
		}
		
	      /* 删除阶段 */
		$('#ul1').on('click', 'button.remove-link', function() {
			var brothers=$(this).parents('li').nextAll();
			var current=$(this).parents('li').attr("id");
			var stageNum=current.substring(2);
			var sta=parseInt(stageNum);
			$(this).parents('li').remove();
			brothers.each(function(index,ele){
				htmls[sta+index]=htmls[sta+index+1];
				var stagId=sta+index;
				var id=stagId;
				stagId='li'+stagId;
				var hh=$(ele).find("[href^='javascript']");
				console.log(hh);
				var stagNum=sta+index-1;
				ele.firstChild.innerHTML='第'+names[stagNum]+'阶段';
				$(ele).attr("id",stagId);
				var dd=$(ele).children('.course').children('.paper-stage');
				dd.each(function(){
				$(this).find("[name$='.stage']").val(id);
				var pa=$(this).parent().next();
				pa.find("[name='orev']").attr("stag",id);
				});
				
				var courseHref="javascript:addcourse(\'"+stagId+"\')";
			    var testHref="javascript:addtest(\'"+stagId+"\')";
			    var downcouHref="javascript:adddowncourse(\'"+stagId+"\')"
				hh.each(function(index,innerele){
			    	if(index==0){
			    		$(innerele).attr("href",courseHref);
			    	}
			    	if(index==1)
                    {
			    		$(innerele).attr("href",testHref);	
			    	}
			    	if(index==2){
			    		$(innerele).attr("href",downcouHref);	
			    	}
				}); 
 			});	
		});
		
		$('#ul1').on('click', 'button.paper-stage-up', function() {
			$(this).parents('.paper-stage').prev().before($(this).parents('.paper-stage')[0]);
		});
		
		$('#ul1').on('click', 'button.paper-stage-down', function() {
			$(this).parents('.paper-stage').next().after($(this).parents('.paper-stage')[0]);
		});
		
		/* 阶段的升序 */
		$('#ul1').on('click', 'button.stage-go-up', function() {
			var prev=$(this).parents('li').prev();
			var current=$(this).parents('li');
			var currentId=current.attr("id");
			var prevId=prev.attr("id");
			var curNum=currentId.substring(2);
			var prevNum=prevId.substring(2);
			if(curNum>1){
			var courseHref="javascript:addcourse(\'li"+prevNum+"\')";
		    var testHref="javascript:addtest(\'li"+prevNum+"\')";
		    var downcouHref="javascript:adddowncourse(\'li"+prevNum+"\')"
			var curh=current.find("[href^='javascript']");
			curh.each(function(inde,els){
				if(inde==0){
					$(els).attr("href",courseHref);	
				}
				if(inde==1){
					$(els).attr("href",testHref);
				}
				if(inde==2){
					$(els).attr("href",downcouHref);
				}
			})
			courseHref="javascript:addcourse(\'li"+curNum+"\')";
		    testHref="javascript:addtest(\'li"+curNum+"\')";
		    downcouHref="javascript:adddowncourse(\'li"+curNum+"\')"
		    var prech=prev.find("[href^='javascript:addcourse']");
		    var preth=prev.find("[href^='javascript:addtest']");
		    var predh=prev.find("[href^='javascript:adddowncourse']");
		    prech.attr("href",courseHref);
		    preth.attr("href",testHref);
		    predh.attr("href",downcouHref);
		    prech.attr("href",courseHref);
		    preth.attr("href",testHref);
		    predh.attr("href",downcouHref);
			var cur=prevNum-1;
			var pre=curNum-1;
			var target;
			target=htmls[curNum];
			htmls[curNum]=htmls[prevNum];
			htmls[prevNum]=target;
			var dd='';
			dd=prev.attr("id");
			prev.attr("id",current.attr('id'));
			current.attr("id",dd);
			var premiddle=prev.children('.course').children('.paper-stage');//得到中间的部分
			var curmiddle=current.children('.course').children('.paper-stage');
			premiddle.each(function(){
				$(this).find("[name$='.stage']").val(curNum);
				var pa=$(this).parent().next();
				pa.find("[name='orev']").attr("stag",curNum);
				});
			curmiddle.each(function(){
				$(this).find("[name$='.stage']").val(prevNum);
				var pa=$(this).parent().next();
				pa.find("[name='orev']").attr("stag",prevNum);
				});
			prev.before(current[0]);
			 $(".mm").each(function(index,ele){
				 $(ele).html('第'+names[index]+'阶段');
			 })
		}
		});
		
		/* 阶段的降序 */
		$('#ul1').on('click', 'button.stage-go-down', function(){
			var prev=$(this).parents('li').next();//下一个
			var current=$(this).parents('li');
			var currentId=current.attr("id");
			var prevId=prev.attr("id");
			var curNum=currentId.substring(2);
			var prevNum=prevId.substring(2);
			var cur=prevNum-1;
			var pre=curNum-1;
			var courseHref="javascript:addcourse(\'li"+curNum+"\')";
		    var testHref="javascript:addtest(\'li"+curNum+"\')";
		    var downcouHref="javascript:adddowncourse(\'li"+curNum+"\')"
		    var next=prev.find("[href^='javascript']");
		    next.each(function(inde,els){
				if(inde==0){
					$(els).attr("href",courseHref);	
				}
				if(inde==1){
					$(els).attr("href",testHref);
				}
				if(inde==2){
					$(els).attr("href",downcouHref);
				}
			})
			courseHref="javascript:addcourse(\'li"+prevNum+"\')";
		    testHref="javascript:addtest(\'li"+prevNum+"\')";
		    downcouHref="javascript:adddowncourse(\'li"+prevNum+"\')"
		    var prech=current.find("[href^='javascript:addcourse']");
		    var preth=current.find("[href^='javascript:addtest']");
		    var predh=current.find("[href^='javascript:adddowncourse']");
		    prech.attr("href",courseHref);
		    preth.attr("href",testHref);
		    predh.attr("href",downcouHref);
		    prech.attr("href",courseHref);
		    preth.attr("href",testHref);
		    predh.attr("href",downcouHref);
		    var target;
			target=htmls[curNum];
			htmls[curNum]=htmls[prevNum];
			htmls[prevNum]=target;
		    
			/* var target;
			target=htmls[curNum];
			htmls[curNum]=htmls[prevNum]; */
			var dd='';
			dd=prev.attr("id");
			prev.attr("id",current.attr('id'));
			current.attr("id",dd);
			var premiddle=prev.children('.course').children('.paper-stage');//得到中间的部分
			var curmiddle=current.children('.course').children('.paper-stage');
			premiddle.each(function(){
				$(this).find("[name$='.stage']").val(curNum);
				var pa=$(this).parent().next();
				pa.find("[name='orev']").attr("stag",curNum);
				});
			curmiddle.each(function(){
				$(this).find("[name$='.stage']").val(prevNum);
				var pa=$(this).parent().next();
				pa.find("[name='orev']").attr("stag",prevNum);
				});
			prev.after(current[0]);
			 $(".mm").each(function(index,ele){
				 $(ele).html('第'+names[index]+'阶段');
			 }) 
		});
		
		    //添加线上课程
		    function addcourse(stageId){
		    var online=1;//course表中1代表线上
			var idList='';//把已有id的给带过去
		    var index=$("input[name$='.stage']").length;
			//var index=stageId.substring(2);
			if(stageId.indexOf('l')>-1){
			  for(var i=1;i<=index;i++){
				  $('#li'+i).find("input[tar='k']").each(function(){
					var kk=$(this).attr("value");
					idList+=kk+',';
					});
				}
			}else{
				$('#'+stageId).find("input[tar='k']").each(function(){
				 var kk=$(this).attr("value");
				 idList+=kk+',';
					});
			    }
			  layer.open({
				  type: 2,
				  title: false,
				  closeBtn: 1, //不显示关闭按钮
				  shade: [0],
				  area: ['1200px', '600px'],
				  content: "${ctx}/manage/studyPlanManage/onlineCourse_add?stageId="+stageId+"&idList="+idList+"&online="+online,
				  end: function(){
				  }
			   });
		  }
		  
		//添加线下课程
		  function adddowncourse(stageId){
			var online=0;//course表中1代表线上
			var idList='';//把已有id的给带过去
			 var index=$("input[name$='.stage']").length;
				//var index=stageId.substring(2);
				if(stageId.indexOf('l')>-1){
				  for(var i=1;i<=index;i++){
					  $('#li'+i).find("input[tar='k']").each(function(){
						var kk=$(this).attr("value");
						idList+=kk+',';
						});
					}
				}else{
					$('#'+stageId).find("input[tar='k']").each(function(){
						 var kk=$(this).attr("value");
						 idList+=kk+',';
					});
				}
			  layer.open({
				  type: 2,
				  title: false,
				  closeBtn: 1, //不显示关闭按钮
				  shade: [0],
				  area: ['1200px', '600px'],
				  content: "${ctx}/manage/studyPlanManage/offlineCourse_add?stageId="+stageId+"&idList="+idList+"&online="+online,
				  end: function(){
				  }
			   });
		  }
		
		 //添加试卷
		  function addtest(stageId){
			 var stageId=stageId;
			 var idList='';//把已有id的给带过去
			 var index=$("input[name$='.stage']").length;
			 //var index=stageId.substring(2);
				if(stageId.indexOf('l')>-1){//阶段型的添加试卷
					for(var i=1;i<=index;i++){
					$('#li'+i).find("input[tar='k']").each(function(){
						var kk=$(this).attr("value");
						idList+=kk+',';
						});
						}	
				}else{//普通型的试卷
					$('#'+stageId).find("input[tar='k']").each(function(){
					 var kk=$(this).attr("value");
						idList+=kk+',';
						});
				}
				  layer.open({
					  type: 2,
					  title: false,
					  closeBtn: 1, //不显示关闭按钮
					  shade: [0],
					  area: ['1200px', '600px'],
					  content: "${ctx}/manage/studyPlanManage/testPaper_add?stageId="+stageId+"&idList="+idList,
					  end: function(){
					  }
				   });
			  } 
		
		 
		 //添加阶段
		function addStage(){
		var index = $("#ul1").find(".course").length;
		if(index>9){
			layer.msg('最多只能添加10个阶段');
		}else{
		var cur_id='li'+index;
		index=index+1;
		var tar_id='li'+index;
		htmls[index]='';
		var newul="<li id='li"+index+"' class='col-md-12 remove-middle-border'><div class='col-md-3 mm border-stage' style='height: 150px;line-height: 150px;'>"+
        "第"+names[index-1]+"阶段"+
	     "</div>"+
	     "<div class='col-md-6 border-stage course' style='height:150px;overflow-y:auto;'>"+	
	     "</div>"+
	     "<div class='col-md-3 border-stage' style='height: 150px;'>"+
		 "<a class='btn-submit btn-sm btn' style='margin-top:4px;margin-right:4px;margin-bottom:4px' href='javascript:addcourse(\"li"+index+"\")'  title='添加线上课程'>+添加线上课程</a>"+
	     "<a class='btn-submit btn-sm btn' href='javascript:addtest(\"li"+index+"\")'  title='添加试卷'>+添加试卷</a>"+
	     "<a class='btn-submit btn-sm btn' style='margin-bottom:5px;' href='javascript:adddowncourse(\"li"+index+"\")' title='添加线下课程'>+添加线下课程</a>"+
		 "<div>"+
		 "<button type='button' name='orev' stag='\""+index+"\"' title='移除考题内容' class='learn_remove remove-link' style='vertical-align: middle;'><i class='icon-remove' style='color: red;"+
         "font-size: 20px;margin-right: 9px'></i></button>"+
		 "<button type='button' title='升序' name='oup' class='learn_upper stage-go-up'><img src='${ctx }/resources/imgs/u11115.png'></button>"+
		 "<button type='button' name='odown' title='降序' class='stage-go-down'><img src='${ctx }/resources/imgs/u11119.png'></button>"+
		 "</div>"+
	     "</div>"+
         "</li>";
		  $("#ul1").append(newul); 
		}
		}

		//生成课程(线上)普通型是0
		function closeLayerup(tagId,array){
			if(tagId.indexOf('l')>-1){//若是带li的则是阶段型的
			  var index=tagId.substring(2);//定义第几阶段的变量
		     var dd=$("input[name$='.stage']").length;
		     //stacounum
			  for(var i=0;i<array.length;i++){
					var seq=i+1;
					htmls[index]+='<div class="paper-stage"><div class="col-md-8">'+
					'<input name="planCourse['+stacounum+'].seq" id="seq" type="hidden" value="'+seq+'"/>'+
					'<input name="planCourse['+stacounum+'].stage" type="hidden" value="'+index+'"/>'+
					'<input name="planCourse['+stacounum+'].isStage" type="hidden" value="1"/>'+
					'<input tar="k" name="planCourse['+stacounum+'].associateId" type="hidden" value="'+array[i].id+'"/>'+
					'<input name="planCourse['+stacounum+'].associateType" type="hidden" value="'+array[i].type+'"/>'+
					'<input name="planCourse['+stacounum+'].desName" type="hidden" value="'+array[i].name+'" />'+
					'<span>'+array[i].name+'</span>'+
					'<label><input type="radio"  name="planCourse['+stacounum+'].isCompulsory" value="1" checked />必修</label>'+
					'<label><input name="planCourse['+stacounum+'].isCompulsory" value="0" type="radio" />选修</label>'+
					'</div>'+
					'<div class="col-md-4">'+					
					'<button type="button" vale="'+array[i].id+'" stag="'+index+'" content="rev" title="移除考题内容" onclick="deleteIn(this)"  class="learn_remove sc remove-paper"><i class="icon-remove i" ></i></button>'+
					'<button type="button" content="up" title="升序" class="learn_upper up paper-stage-up" ><img src="${ctx }/resources/imgs/u11115.png"></button>'+
					'<button type="button" content="down" title="降序" class="down paper-stage-down" ><img src="${ctx }/resources/imgs/u11119.png"></button>'+
					'</div>'+
					'</div>'
					stacounum++;
				}
			    var parent=$('#'+tagId);
				var target=parent.children(".course");
				target.html(htmls[index]);
			 }else{
			  index=1;//普通型阶段是0
			  var str='';
			 var dd=$("input[name$='.stage']").length;
			  for(var i=0;i<array.length;i++){
				var seq=i+1;
				dowhtml2+='<div class="paper-stage"><div class="col-md-8">'+
				'<input name="planCourse['+coursenum+'].seq" id="seq" type="hidden" value="'+seq+'"/>'+
				'<input name="planCourse['+coursenum+'].stage" type="hidden" value="'+index+'"/>'+
				'<input tar="k" name="planCourse['+coursenum+'].associateId" type="hidden" value="'+array[i].id+'"/>'+
				'<input name="planCourse['+coursenum+'].associateType" type="hidden" value="'+array[i].type+'"/>'+
				'<input name="planCourse['+coursenum+'].desName" type="hidden" value="'+array[i].name+'" />'+
				'<span>'+array[i].name+'</span>'+
				'<label><input type="radio"  name="planCourse['+coursenum+'].isCompulsory" value="1" checked />必修</label>'+
				'<label><input name="planCourse['+coursenum+'].isCompulsory" value="0" type="radio" />选修</label>'+
				'</div>'+
				'<div class="col-md-4">'+					
				'<button type="button" vale="'+array[i].id+'" stag="'+index+'" content="rev" title="移除考题内容" signp="dowhtml2" onclick="deleteIno(this)"  class="learn_remove sc remove-paper"><i class="icon-remove i" ></i></button>'+
				'<button type="button" content="up" onclick="upIn(this)" title="升序" class="learn_upper up paper-stage-up" ><img src="${ctx }/resources/imgs/u11115.png"></button>'+
				'<button type="button" content="down" onclick="downIn(this)" title="降序" class="down paper-stage-down" ><img src="${ctx }/resources/imgs/u11119.png"></button>'+
				'</div>'+
				'</div>'
				coursenum++;
				}
			   var target=$('#'+tagId);
				target.html(dowhtml2);
			}
		}
		
		//试卷
		function closeLayerd(tagId,array){
			if(tagId.indexOf('l')>-1){//若是带li的则是阶段型的
			 var index=tagId.substring(2);//定义第几阶段的变量
			 for(var i=0;i<array.length;i++){
					var seq=i+1;
					htmls[index]+='<div class="paper-stage"><div class="col-md-8" >'+
					'<input name="planCourse['+stacounum+'].seq" id="seq" type="hidden" value="'+seq+'"/>'+
					'<input name="planCourse['+stacounum+'].isStage" id="isStage" type="hidden" value="1"/>'+
					'<input name="planCourse['+stacounum+'].stage" type="hidden" value="'+index+'"/>'+
					'<input tar="k" name="planCourse['+stacounum+'].associateId" type="hidden" value="'+array[i].id+'"/>'+
					'<input name="planCourse['+stacounum+'].associateType" type="hidden" value="'+array[i].type+'"/>'+
					'<input name="planCourse['+stacounum+'].desName" type="hidden" value="'+array[i].name+'" />'+
					'<span>'+array[i].name+'</span>'+
					'<label><input type="radio"  name="planCourse['+stacounum+'].isCompulsory" value="1" checked />必修</label>'+
					'<label><input name="planCourse['+stacounum+'].isCompulsory" value="0" type="radio" />选修</label>'+
					'</div>'+
					'<div class="col-md-4">'+					
					'<button type="button" vale="'+array[i].id+'" stag="'+index+'" content="rev" title="移除考题内容" onclick="deleteIn(this)"  class="learn_remove sc remove-paper"><i class="icon-remove i" ></i></button>'+
					'<button type="button" content="up" title="升序" class="learn_upper up paper-stage-up" ><img src="${ctx }/resources/imgs/u11115.png"></button>'+
					'<button type="button" content="down" title="降序" class="down paper-stage-down" ><img src="${ctx }/resources/imgs/u11119.png"></button>'+
					'</div>'+
					'</div>'
					stacounum++;
				}
			    var parent=$('#'+tagId);
				var target=parent.children(".course");
				target.html(htmls[index]);
			}else{
				 index=1;//普通型阶段是0
				  var str='';
				 var dd=$("input[name$='.stage']").length;
				  for(var i=0;i<array.length;i++){
					var seq=i+1;
					dowhtml2+='<div class="paper-stage"><div class="col-md-8">'+
					'<input name="planCourse['+coursenum+'].seq" id="seq" type="hidden" value="'+seq+'"/>'+
					'<input name="planCourse['+coursenum+'].stage" type="hidden" value="'+index+'"/>'+
					'<input tar="k" name="planCourse['+coursenum+'].associateId" type="hidden" value="'+array[i].id+'"/>'+
					'<input name="planCourse['+coursenum+'].associateType" type="hidden" value="'+array[i].type+'"/>'+
					'<input name="planCourse['+coursenum+'].desName" type="hidden" value="'+array[i].name+'" />'+
					'<span>'+array[i].name+'</span>'+
					'<label><input type="radio"  name="planCourse['+coursenum+'].isCompulsory" value="1" checked />必修</label>'+
					'<label><input name="planCourse['+coursenum+'].isCompulsory" value="0" type="radio" />选修</label>'+
					'</div>'+
					'<div class="col-md-4">'+					
					'<button type="button" vale="'+array[i].id+'" stag="'+index+'" content="rev" title="移除考题内容" signp="dowhtml2" onclick="deleteIno(this)"  class="learn_remove sc remove-paper"><i class="icon-remove i" ></i></button>'+
					'<button type="button" content="up" onclick="upIn(this)" title="升序" class="learn_upper up paper-stage-up" ><img src="${ctx }/resources/imgs/u11115.png"></button>'+
					'<button type="button" content="down" onclick="downIn(this)" title="降序" class="down paper-stage-down" ><img src="${ctx }/resources/imgs/u11119.png"></button>'+
					'</div>'+
					'</div>'
					coursenum++;
					}
				   var target=$('#'+tagId);
					target.html(dowhtml2);
				}
			}
			
		
		//生成课程(线下)不分阶段，stage为0（阶段) 普通为0
		function closeLayerdown(tagId,array){
			 var str='';
			 var index=tagId.substring(3);
			 var dd=$("input[name$='.stage']").length;
			 if(index==1){//阶段型计划的线下课程
				index=1;
				for(var i=0;i<array.length;i++){
					var seq=i+1;
					dowhtml1+='<div class="paper-stage"><div class="col-md-8">'+
					'<input name="planCourse['+stacounum+'].seq" type="hidden" id="seq"  value="'+seq+'"/>'+
					'<input name="planCourse['+stacounum+'].stage" type="hidden" value="'+index+'"/>'+
					'<input tar="k" name="planCourse['+stacounum+'].associateId" type="hidden" value="'+array[i].id+'"/>'+
					'<input name="planCourse['+stacounum+'].associateType" type="hidden" value="'+array[i].type+'"/>'+
					'<input name="planCourse['+stacounum+'].desName" type="hidden" value="'+array[i].name+'" />'+
					'<span>'+array[i].name+'</span>'+
					'<label><input type="radio"  name="planCourse['+stacounum+'].isCompulsory" value="1" checked />必修</label>'+
					'<label><input name="planCourse['+stacounum+'].isCompulsory" value="0" type="radio" />选修</label>'+
					'</div>'+
					'<div class="col-md-4">'+					
					'<button type="button"  onclick="deleteIno(this)" signp="dowhtml1" vale="'+array[i].id+'" stag="'+index+'" content="rev" title="移除考题内容" onclick="deleteIn(this)"  class="learn_remove sc remove-paper"><i class="icon-remove i" ></i></button>'+
					'<button type="button"  onclick="upIn(this)" content="up" title="升序" class="learn_upper up paper-stage-up" ><img src="${ctx }/resources/imgs/u11115.png"></button>'+
					'<button type="button"  onclick="downIn(this)" content="down" title="降序" class="down paper-stage-down" ><img src="${ctx }/resources/imgs/u11119.png"></button>'+
					'</div>'+
					'</div>'
					stacounum++;
					}
				   var target=$('#'+tagId);
				   target.html(dowhtml1);
			 }else{
				 index=1;
		     for(var i=0;i<array.length;i++){
			   var seq=i+1;
				dowhtml3+='<div class="paper-stage"><div class="col-md-8" >'+
				'<input name="planCourse['+coursenum+'].seq" type="hidden" value="'+seq+'" id="seq"/>'+
				'<input name="planCourse['+coursenum+'].stage" type="hidden" value="'+index+'"/>'+
				'<input tar="k" name="planCourse['+coursenum+'].associateId" type="hidden" value="'+array[i].id+'"/>'+
				'<input name="planCourse['+coursenum+'].associateType" type="hidden" value="'+array[i].type+'"/>'+
				'<input name="planCourse['+coursenum+'].desName" type="hidden" value="'+array[i].name+'" />'+
				'<span>'+array[i].name+'</span>'+
				'<label><input type="radio"  name="planCourse['+coursenum+'].isCompulsory" value="1" checked />必修</label>'+
				'<label><input name="planCourse['+coursenum+'].isCompulsory" value="0" type="radio" />选修</label>'+
				'</div>'+
				'<div class="col-md-4">'+					
				'<button type="button" signp="dowhtml3" onclick="deleteIno(this)" vale="'+array[i].id+'" stag="'+index+'" content="rev" title="移除考题内容" onclick="deleteIn(this)"  class="learn_remove sc remove-paper"><i class="icon-remove i" ></i></button>'+
				'<button type="button" onclick="upIn(this)" content="up" title="升序" class="learn_upper up paper-stage-up" ><img src="${ctx }/resources/imgs/u11115.png"></button>'+
				'<button type="button"  onclick="downIn(this)" content="down" title="降序" class="down paper-stage-down" ><img src="${ctx }/resources/imgs/u11119.png"></button>'+
				'</div>'+
				'</div>'
				coursenum++;
					}
					var target=$('#'+tagId);
					target.html(dowhtml3); 
			  }
		  }
		
		function closed(tagId,array){
			for(var i=0;i<array.length;i++){
				alert(array[i].name);
				alert(array[i].id);
				alert(array[i].type);
			}
		}
		
		 function asd(type){
	    	 if(type == '1'){
	    		 $("#planType").val("1");  //阶段型计划
	    		 $("#stagec1").removeAttr("style");
	    		 $("#stagec1").attr("class","active");
	    		 $("#stagec2").attr("style","display: none;");
	    		 $("#stagec2").removeAttr("class");
	    	 }else{
	    		 $("#plan_planType2").val("0");  //普通型计划
	    	    $("#stagec2").removeAttr("style");
	    		$("#stagec2").attr("class","active");
	    		$("#stagec1").attr("style","display: none;");
	    		$("#stagec1").removeAttr("class"); 
	    		
	    	 }
	     }
		
	   function preview(num){
		 var planName=$("#plan_name").val().trim();
		 var planDescription=$("#plan_description").val().trim(); 
		 var tagId=$("#tagId").val().trim();
		 $("#planType").val("1"); //阶段型计划 
		 if(planName && planDescription && tagId ){
		   var exit=$("input[name$='.stage']").length;
		   if(exit>0){
		   if(num==1){/* 在预览之前对每个阶段进行排序 */
			   $(".course").each(function(){
				   $(this).children().each(function(index,element){
					   var tar= $(this).children()[0]; 
					   var tar2=$(tar);
					 var fintar=$(tar2.children()[0]);
					 fintar.attr("value",index+1);
				   })
			   });
		      /*  $("#dow1").children().each(function(index,element){
			   var tar= $(this).children()[0]; 
			   var tar2=$(tar);
			   var fintar=$(tar2.children()[0]);
			   fintar.attr("value",index+1);
		   });  */
			  $('#inputForm').submit(); 
		   }else{
			   var id=$("#plan_id").val();
			   var name= $("#plan_name").val();
			   var descript=$("#plan_description").val();
			   var tagid=$("#tagId").val();
			   var planType=$("#planType").val();
			   $("#plan_id2").val(id);
			   $("#plan_id2").attr("type","hidden");
			   $("#plan_name2").val(name);
			   $("#plan_description2").val(descript);
			   $("#plan_description2").attr("type","hidden");
			   $("#plan_tagId2").val(tagid);
			   $("#plan_planType2").val("0");  //普通计划 
			   $("#plan_name2").attr("type","hidden");
			   $("#plan_tagId2").attr("type","hidden");
			   $("#dow2").children().each(function(index,element){
				   var tar= $(this).children()[0]; 
				   var tar2=$(tar);
				   var fintar=$(tar2.children()[0]);
				   fintar.attr("value",index+1);
			   });
			 /*   $("#dow3").children().each(function(index,element){
				   var tar= $(this).children()[0]; 
				   var tar2=$(tar);
				   var fintar=$(tar2.children()[0]);
				   fintar.attr("value",index+1);
			   }); */
			   $('#ordinaryForm').submit(); 
		   }}else{
			   layer.msg('未添加学习计划内容');
		   }
	   }else{
		  layer.msg("计划名称或计划分类或计划说明不能为空~~");
	   }
	   }
	   
	 //对课程或者是试卷的删除
	   function deleteIn(obj){//阶段型的删除
		 var grandpa= $(obj).parent().parent();
	     var parent=$(obj).parent();
	     var grandpas= grandpa.nextAll();
		 grandpa.remove();
		 var stag= $(obj).attr('stag');
		 var target=$('#li'+stag).children(".course");
	     htmls[stag]=target.html();//把htmls[stag]的值设为当前的
	   } 
	 
	 //普通类型的排序
	 function upIn(obj){
		var current= $(obj).parents(".paper-stage");
		var preNode=current.prev();
		preNode.before(current);
	 }
	 
	 function downIn(obj){
		 var current= $(obj).parents(".paper-stage");
		 var nextNode=current.next();
		 nextNode.after(current);
	 }
	 
	 function deleteIno(obj){//非阶段型的删除
		var sign= $(obj).attr("signp");
		var current= $(obj).parents(".paper-stage");
		current.remove();
		var index=sign.substring(7);
		if(index==1){
		  dowhtml1= $(dow1).html();
		}
		if(index==2){
		   dowhtml2= $(dow2).html();
		}
		if(index==3){
		  dowhtml3= $(dow3).html();
		}
	 }
	   
</script>
</body>
</html>