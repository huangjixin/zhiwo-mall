<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%@ taglib uri="/WEB-INF/tld/Data" prefix="data" %>
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
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/elerning/style.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/elerning/font/iconfont.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/elerning/page.css">
</head>
<body>
<style type="text/css">

.analysis_sin{
 display: none;
 
} 
.MultiSelect{
 display: none;
 
} 
.judge{
 display: none;
 
} 
.answers{
 display: none;
 
} 


</style>

		
		<div class="title"><strong>查看试卷</strong></div>
		<input type="hidden" name="paperId" id="paperId" value="${id }"> 
			<div class="tab-pane active yhsj_ques">
				<div class="header-bars">
					<h4>${elPaperVo.name}</h4>
					<Strong>答题人：${elPaperVo.userName }</Strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<Strong>满分：${elPaperVo.fullMark }分</Strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<Strong>通过分数：${elPaperVo.passMark }分</Strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<Strong>答题时间：${elPaperVo.examTime }</Strong>
					<%-- <ul class="clearfix">
						<li>答题人：${elPaperVo.userName }</li>&nbsp;&nbsp;
						<li>满分：${elPaperVo.fullMark }分</li>&nbsp;&nbsp;
						<li>通过分数：${elPaperVo.passMark }分</li>&nbsp;&nbsp;
						<li>答题时间：${elPaperVo.examTime }分钟</li>
					</ul> --%>
				</div>
				
				
				<c:forEach items="${elPaperVo.questionVo}" var="questionVo" varStatus="inx">
				<c:if test="${questionVo.type==0 }" >
					<!-- 单选题 -->
				<div class="ggsi_single">
					<div class="title"><strong>单选题</strong></div>
					<ul class="clearfix ggsi_view">
						<li class="col-md-12">
							<div class="search_result">
								<div class="single_choice">
									<div class="subject clearfix">
										<h5><span>${inx.index+1 }.</span>${questionVo.content }</h5>
										<i>本题${questionVo.questionScore }分</i>
									</div>
									<ol>
									<c:forEach items="${questionVo.questionAnswer}" var="questionAnswer" varStatus="idx" >
									
										<li><data:DataTag type="option" option="${idx.index+1}"/>.${questionAnswer.answer }</li>
										
										</c:forEach>
									</ol>
								</div>
								
								<%-- <li class="col-md-4 srsj_ipt"><strong><ins>*</ins>考生答案：</strong><input type="text" readonly="readonly" class="ipt-text" value="${questionVo.answer }" onkeyup="value=value.replace(/[^\d.]/g,'')"></li> --%>
								考生该题答案：<input type="text" readonly="readonly" class="ipt-text" value="${questionVo.answer }" onkeyup="value=value.replace(/[^\d.]/g,'')">
								<button class="open_single" onclick="openSin()">展开解析</button>
								
								<div class="analysis_sin" >
								  <input  name="analysis" id="analysis" readonly="readonly"   style="border:none;" value="${questionVo.analysis}" >
							    </div>
						
							</div>
						</li>
					</ul>
				</div>
				</c:if>
					<c:if test="${questionVo.type==1 }" >
					
			
				<!-- 多选题 -->
				<div class="ggsi_single">
					<div class="title"><strong>多选题</strong></div>
					<ul class="clearfix ggsi_view">
						<li class="col-md-12">
							<div class="search_result">
								<div class="single_choice">
									<div class="subject clearfix">
										<h5><span>${inx.index+1 }.</span>${questionVo.content }</h5>
										<i>本题${questionVo.questionScore }分</i>
									</div>
									<ol>
										<c:forEach items="${questionVo.questionAnswer}" var="questionAnswer" varStatus="idx" >
									
										<li><data:DataTag type="option" option="${idx.index+1}"/>.${questionAnswer.answer }</li>
										
										</c:forEach>
									</ol>
								</div>
								考生该题答案：<input type="text" readonly="readonly" class="ipt-text" value="${questionVo.answer }" onkeyup="value=value.replace(/[^\d.]/g,'')">
								<button class="open_single" onclick="MultiSelect()">展开解析</button>
								<div class="MultiSelect" >
								  <input  name="analysis"  readonly="readonly"   style="border:none;" value="${questionVo.analysis}" >
							    </div>
							</div>
						</li>
					</ul>
				</div>
					
					</c:if>
					<c:if test="${questionVo.type==2 }" >
					
			<!-- 判断题 -->
				<div class="ggsi_single">
					<div class="title"><strong>判断题</strong></div>
					<ul class="clearfix ggsi_view">
						<li class="col-md-12">
							<div class="search_result">
								<div class="single_choice">
									<div class="subject clearfix">
										<h5><span>${inx.index+1 }.</span>${questionVo.content }</h5>
										<i>本题${questionVo.questionScore}分</i>
									</div>
								</div>
								考生该题答案：<input type="text" readonly="readonly" class="ipt-text" value="${questionVo.answer }" onkeyup="value=value.replace(/[^\d.]/g,'')">
								<button class="open_single" onclick="judge()">展开解析</button>
								<div class="judge" >
								  <input  name="analysis"  readonly="readonly"   style="border:none;" value="${questionVo.analysis}" >
							    </div>
							</div>
						</li>
					</ul>
				</div>
				</c:if>
				<c:if test="${questionVo.type==3 }" >
				<!-- 问答题 -->
				<div class="ggsi_single">
					<div class="title"><strong>问答题</strong></div>
					<input type="hidden" name="questionsAndAnswers" value="${questionVo.id }">
					<input type="hidden" name="eueId" id="eueId" value="${questionVo.eueId }"> 
					<ul class="clearfix ggsi_view">
						<li class="col-md-12">
							<div class="search_result">
								<div class="single_choice">
									<div class="subject clearfix">
										<h5><span>${inx.index+1 }.</span>${questionVo.content }</h5>
										<i>本题${questionVo.questionScore}分</i>
									</div>
								</div>
								考生该题答案：<input type="text" readonly="readonly" class="ipt-text" value="${questionVo.answer }" onkeyup="value=value.replace(/[^\d.]/g,'')">
								<button class="open_single" onclick="answers()">展开解析</button>
								<div class="answers" >
								  <input  name="analysis"  readonly="readonly"   style="border:none;" value="${questionVo.analysis}" >
							    </div>
							    <c:if test="${paperState=='1' }" >
							    <p style="background: #eee;padding-left: 20px;">此题得分：<input class="ipt-text" name="questionsAndAnswersAndScore" type="text" placeholder="" disabled="disabled" value="${questionVo.answerQuestionScore}" style="width: 10%;" onkeyup="value=value.replace(/[^\d.]/g,'')">分</p>
							    </c:if> 
							    <c:if test="${paperState=='2' }" >
							    <p style="background: #eee;padding-left: 20px;">给出分数：<input class="ipt-text" name="questionsAndAnswersAndScore" type="text" placeholder="" style="width: 10%;" onkeyup="value=value.replace(/[^\d.]/g,'')">分</p>
							    </c:if>
							</div>
						</li>
					</ul>
				</div>
					
					</c:if>
			    </c:forEach>
			<div class="ui-button">
			
			<c:if test="${paperState=='1' }" >
				<button type="button" class="btn btn-submit" onclick="cancle()">返回</button>
			</c:if>	
			<c:if test="${paperState=='2' }" >
				<button type="button" class="btn btn-submit" onclick="submitExam()">提交</button>
				<button type="button" class="btn btn-default" onclick="cancle()">返回</button>
			</c:if>	
			</div>
		

<script type="text/javascript" src="${ctx}/resources/libs/layer/layer.js"></script>


  <script type="text/javascript">
			
			function openSin(){
				var display =$('.analysis_sin').css('display');
				if(display == 'none'){
					$(".analysis_sin").show();
				}else{
					$(".analysis_sin").hide();
				}
				
			}
			
			function MultiSelect(){
				var display =$('.MultiSelect').css('display');
				if(display == 'none'){
					$(".MultiSelect").show();
				}else{
					$(".MultiSelect").hide();
				}
				
			}
			
			function answers(){
				var display =$('.answers').css('display');
				if(display == 'none'){
					$(".answers").show();
				}else{
					$(".answers").hide();
				}
				
			}
			
			function judge(){
				var display =$('.judge').css('display');
				if(display == 'none'){
					$(".judge").show();
				}else{
					$(".judge").hide();
				}
				
			}
	
    	function cancle(){
    		  location.href="${ctx}/manage/paper/GetExamPaper"
    	}
    	
    	function submitExam(){
    		 var scores ="";
    		 var eueId ="";
    		 var questionId = "";
    		 var paperId=$("#paperId").val();
    		 var groupCheckbox=$("input[name='questionsAndAnswersAndScore']");
		     for(i=0;i<groupCheckbox.length;i++){
		    	 scores=scores+groupCheckbox[i].value+",";
		    } 
		     var questionsAndAnswers=$("input[name='questionsAndAnswers']");
		     for(i=0;i<questionsAndAnswers.length;i++){
		    	 questionId=questionId+questionsAndAnswers[i].value+",";
		    } 
		     
		     var eueIds=$("input[name='eueId']");
		     for(i=0;i<eueIds.length;i++){
		    	 eueId=eueId+eueIds[i].value+",";
		    } 
		     $.ajax({
					type : "post",
					data:{"scores":scores,"questionId":questionId,"paperId":paperId,"eueId":eueId},
					url : "${ctx}/manage/paper/checkExamination",
					dateType: "json",
					success : function(response) {
						if(response.code==1){
							layer.confirm(response.msg, {
				   				icon: 6,
				   			  	btn: ['确定'] //按钮
				   			}, function(){
				   				location.href="${ctx}/manage/paper/GetExamPaper";
				   			}); 
						}else{
							if(response.code==1){
								layer.confirm(data.msg, {
					   				icon: 3,
					   			  	btn: ['确定'] //按钮
					   			}, function(){
					   				location.href="${ctx}/manage/paper/GetExamPaper";
					   			}); 
						}
					}
					}
				});
		     
		     
    	}
    	

</script>
</body>
</html>
