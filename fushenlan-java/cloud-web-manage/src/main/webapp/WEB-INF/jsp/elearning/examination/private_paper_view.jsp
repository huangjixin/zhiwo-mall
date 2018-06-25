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
<script type="text/javascript" src="${ctx}/resources/js/common/jquery-3.2.1.min.js" ></script>
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
			<div class="tab-pane active yhsj_ques">
				<div class="header-bars">
					<h4>${paper.name}</h4>
					<ul class="clearfix">
						<li>满分：${paper.fullMark }分</li>
						<li>通过分数：${paper.passMark }分</li>
						<li>答题时间：${paper.examTime }分钟</li>
					</ul>
				</div>
				
				<c:forEach items="${questionVo}" var="questionVo"  varStatus="inx">
				<c:if test="${questionVoMap[questionVo.questionId].type==0 }" >
					<!-- 单选题 -->
				<div class="ggsi_single">
					<div class="title"><strong>单选题</strong></div>
					<ul class="clearfix ggsi_view">
						<li class="col-md-12">
							<div class="search_result">
								<div class="single_choice">
									<div class="subject clearfix">
										<h5><span>${inx.index+1 }.</span>${questionVoMap[questionVo.questionId].content }</h5>
										<i>本题${questionVo.questionScore }分</i>
									</div>
									<ol id="radioAnswer">
									<c:forEach items="${questionVoMap[questionVo.questionId].questionAnswer}" var="questionAnswer" varStatus="idx">
										<li>${questionAnswer.option }.${questionAnswer.answer }</li>
									</c:forEach>
									</ol>
								</div>
								
								<button class="open_single" onclick="openSin()">展开解析</button>
								
								<div class="analysis_sin" >
								  <input  name="analysis" id="analysis" readonly="readonly"   style="border:none;" value="${questionVoMap[questionVo.questionId].analysis}" >
							    </div>
						
							</div>
						</li>
					</ul>
				</div>
				</c:if>
				<c:if test="${questionVoMap[questionVo.questionId].type==1 }" >
				<!-- 多选题 -->
				<div class="ggsi_single">
					<div class="title"><strong>多选题</strong></div>
					<ul class="clearfix ggsi_view">
						<li class="col-md-12">
							<div class="search_result">
								<div class="single_choice">
									<div class="subject clearfix">
										<h5><span>${inx.index+1 }.</span>${questionVoMap[questionVo.questionId].content }</h5>
										<i>本题${questionVo.questionScore }分</i>
									</div>
									<ol id="multiselectAnswer">
										<c:forEach items="${questionVoMap[questionVo.questionId].questionAnswer}" var="questionAnswer">
									
										<li>${questionAnswer.option }.${questionAnswer.answer }</li>
										
										</c:forEach>
									</ol>
								</div>
								<button class="open_single" onclick="MultiSelect()">展开解析</button>
								<div class="MultiSelect" >
								  <input  name="analysis"  readonly="readonly"   style="border:none;" value="${questionVoMap[questionVo.questionId].analysis}" >
							    </div>
							</div>
						</li>
					</ul>
				</div>
			</c:if>
			<c:if test="${questionVoMap[questionVo.questionId].type==2 }" >
			<!-- 判断题 -->
				<div class="ggsi_single">
					<div class="title"><strong>判断题</strong></div>
					<ul class="clearfix ggsi_view">
						<li class="col-md-12">
							<div class="search_result">
								<div class="single_choice">
									<div class="subject clearfix">
										<h5><span>${inx.index+1 }.</span>${questionVoMap[questionVo.questionId].content }</h5>
										<i>本题${questionVo.questionScore}分</i>
									</div>
								</div>
								<button class="open_single" onclick="judge()">展开解析</button>
								<div class="judge">
								  <input  name="analysis"  readonly="readonly"   style="border:none;" value="${questionVoMap[questionVo.questionId].analysis}" >
							    </div>
							</div>
						</li>
					</ul>
				</div>
			</c:if>
			<c:if test="${questionVoMap[questionVo.questionId].type==3 }" >
				<!-- 问答题 -->
				<div class="ggsi_single">
					<div class="title"><strong>问答题</strong></div>
					<input type="hidden" name="questionsAndAnswers" value="${questionVo.id }">
					<ul class="clearfix ggsi_view">
						<li class="col-md-12">
							<div class="search_result">
								<div class="single_choice">
									<div class="subject clearfix">
										<h5><span>${inx.index+1 }.</span>${questionVoMap[questionVo.questionId].content }</h5>
										<i>本题${questionVo.questionScore}分</i>
									</div>
								</div>
								<button class="open_single" onclick="answers()">展开解析</button>
								<div class="answers" >
								  <input  name="analysis"  readonly="readonly"   style="border:none;" value="${questionVoMap[questionVo.questionId].analysis}" >
							    </div>
							</div>
						</li>
					</ul>
				</div>
			</c:if>
			</c:forEach>
			<div class="ui-button">
			
				<button type="button" class="btn btn-submit" onclick="cancle()">返回</button>
			</div>
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
    		var index=parent.layer.getFrameIndex(window.name);
    		parent.layer.close(index);
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
							layer.confirm(data.msg, {
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
