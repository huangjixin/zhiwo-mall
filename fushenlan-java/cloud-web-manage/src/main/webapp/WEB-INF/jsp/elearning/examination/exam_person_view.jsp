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
			<div class="tab-pane active yhsj_ques">
				<div class="header-bars">
					<%-- <h4>${elPaperVo.name}</h4> --%>
					<ul class="clearfix">
					    <li>试卷名称：${eavo.name }</li>
						<li>答题人：${eavo.userName }</li>
						<li>满分：${eavo.fullMark }分</li>
						<li>通过分数：${eavo.passMark }分</li>
						<li>答题时间：${eavo.examTime }分钟</li>
					</ul>
				</div>
				
				
				<c:forEach items="${eList}" var="examAccountVo" >
				<c:if test="${examAccountVo.questionType==0 }" >
					<!-- 单选题 -->
				<div class="ggsi_single">
					<div class="title"><strong>单选题</strong></div>
					<ul class="clearfix ggsi_view">
						<li class="col-md-12">
							<div class="search_result">
								<div class="single_choice">
									<div class="subject clearfix">
										<h5>${examAccountVo.content }</h5>
										<i>本题${examAccountVo.questionScore }分</i>
									</div>
									<ol>
									<%-- <c:forEach items="${questionVo.questionAnswer}" var="questionAnswer" >
									
										<li>A.${questionAnswer.answer }</li>
										
										</c:forEach>
									</ol> --%>
								</div>
								
								考生答案：<input type="text" readonly="readonly" class="ipt-text" value="${examAccountVo.answer }" onkeyup="value=value.replace(/[^\d.]/g,'')">
								考生得分：<input type="text" readonly="readonly" class="ipt-text" value="${examAccountVo.score }" onkeyup="value=value.replace(/[^\d.]/g,'')">
								
								<%-- <button class="open_single" onclick="openSin()">展开解析</button>
								<div class="analysis_sin" >
								  <input  name="analysis" id="analysis" readonly="readonly"   style="border:none;" value="${examAccountVo.analysis}" >
							    </div> --%>
						
							</div>
						</li>
					</ul>
				</div>
				</c:if>
				
				
	       <c:if test="${examAccountVo.questionType==1 }" >
				<!-- 多选题 -->
				<div class="ggsi_single">
					<div class="title"><strong>多选题</strong></div>
					<ul class="clearfix ggsi_view">
						<li class="col-md-12">
							<div class="search_result">
								<div class="single_choice">
									<div class="subject clearfix">
										<h5>${examAccountVo.content }</h5>
										<i>本题${examAccountVo.questionScore }分</i>
									</div>
									<%-- <ol>
										<c:forEach items="${questionVo.questionAnswer}" var="questionAnswer" >
									
										<li>A.${questionAnswer.answer }</li>
										
										</c:forEach>
									</ol> --%>
								</div>
								考生答案：<input type="text" readonly="readonly" class="ipt-text" value="${examAccountVo.answer }" onkeyup="value=value.replace(/[^\d.]/g,'')">
								考生得分：<input type="text" readonly="readonly" class="ipt-text" value="${examAccountVo.score }" onkeyup="value=value.replace(/[^\d.]/g,'')">
								<%-- <button class="open_single" onclick="MultiSelect()">展开解析</button>
								<div class="MultiSelect" >
								  <input  name="analysis"  readonly="readonly"   style="border:none;" value="${questionVo.analysis}" >
							    </div> --%>
							</div>
						</li>
					</ul>
				</div>
					
					</c:if>
					<c:if test="${examAccountVo.questionType==2 }" >
					
			<!-- 判断题 -->
				<div class="ggsi_single">
					<div class="title"><strong>判断题</strong></div>
					<ul class="clearfix ggsi_view">
						<li class="col-md-12">
							<div class="search_result">
								<div class="single_choice">
									<div class="subject clearfix">
										<h5>${examAccountVo.content }</h5>
										<i>本题${examAccountVo.questionScore}分</i>
									</div>
								</div>
								考生答案：<input type="text" readonly="readonly" class="ipt-text" value="${examAccountVo.answer }" onkeyup="value=value.replace(/[^\d.]/g,'')">
								考生得分：<input type="text" readonly="readonly" class="ipt-text" value="${examAccountVo.score }" onkeyup="value=value.replace(/[^\d.]/g,'')">
								<%-- <button class="open_single" onclick="judge()">展开解析</button>
								<div class="judge" >
								  <input  name="analysis"  readonly="readonly"   style="border:none;" value="${questionVo.analysis}" >
							    </div> --%>
							</div>
						</li>
					</ul>
				</div>
					
					</c:if>
					
			<c:if test="${examAccountVo.questionType==3 }" >
				<!-- 问答题 -->
				<div class="ggsi_single">
					<div class="title"><strong>问答题</strong></div>
					<%-- <input type="hidden" name="questionsAndAnswers" value="${examAccountVo.id }"> --%>
					<%-- <input type="hidden" name="eueId" id="eueId" value="${questionVo.eueId }">  --%>
					<ul class="clearfix ggsi_view">
						<li class="col-md-12">
							<div class="search_result">
								<div class="single_choice">
									<div class="subject clearfix">
										<h5>${examAccountVo.content }</h5>
										<i>本题${examAccountVo.questionScore}分</i>
									</div>
								</div>
								考生答案：<input type="text" readonly="readonly" class="ipt-text" value="${examAccountVo.answer }" onkeyup="value=value.replace(/[^\d.]/g,'')">
							          考生得分：<input type="text" readonly="readonly" class="ipt-text" value="${examAccountVo.score }" onkeyup="value=value.replace(/[^\d.]/g,'')">
							    <%-- <button class="open_single" onclick="answers()">展开解析</button>
								<div class="answers" >
								  <input  name="analysis"  readonly="readonly"   style="border:none;" value="${questionVo.analysis}" >
							    </div>
							    <c:if test="${paperState=='1' }" >
							    <p style="background: #eee;padding-left: 20px;">此题得分：<input class="ipt-text" name="questionsAndAnswersAndScore" type="text" placeholder="" disabled="disabled" value="${questionVo.answerQuestionScore}" style="width: 10%;" onkeyup="value=value.replace(/[^\d.]/g,'')">分</p>
							    </c:if> 
							    <c:if test="${paperState=='2' }" >
							    <p style="background: #eee;padding-left: 20px;">给出分数：<input class="ipt-text" name="questionsAndAnswersAndScore" type="text" placeholder="" style="width: 10%;" onkeyup="value=value.replace(/[^\d.]/g,'')">分</p>
							    </c:if> --%>
							</div>
						</li>
					</ul>
				</div>
					
					</c:if>
			    </c:forEach>
			<div class="ui-button">
				<button type="button" class="btn btn-default" onclick="javascript:history.go(-1);">返回</button>
			</div>
		
<script type="text/javascript" src="${ctx}/resources/libs/layer/layer.js"></script>


  <script type="text/javascript">
			
</script>
</body>
</html>
