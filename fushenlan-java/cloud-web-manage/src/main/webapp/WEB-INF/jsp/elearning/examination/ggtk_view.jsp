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
</head>
<body>
		
			<div class="form-detail">
			<div class="title"><strong>查看考题</strong></div>
			<ul class="clearfix">
				<li class="col-md-12 zlgl_share">
				
				<c:if test="${questionVo.type==0}">
						<strong>单选题</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:
					</c:if>
					<c:if test="${questionVo.type==1}">
						<strong>多选题:</strong>
					</c:if>
					<c:if test="${questionVo.type==2}">
						<strong>判断题:</strong>
					</c:if>
					<c:if test="${questionVo.type==3}">
						<strong>问答题:</strong>
					</c:if>
					${questionVo.content}
				
				<%-- <c:if test="${questionVo.type==2}">
						<div class="form_answer">
						<span>是否正确：</span>
						<label for="yes"><input type="radio" <c:if test="${questionVo.isRight==1}">checked</c:if>  value="" id="yes" name="share">是
						</label>
						<label for="no"><input type="radio" <c:if test="${questionVo.isRight==0}">checked</c:if>  value="" id="no" name="share">否
						</label>
					</div>
					</c:if> --%>
					
					<div class="answer_key">
						答案解析：${questionVo.analysis}</br>
						<c:forEach items="${questionVo.questionAnswer}" var="questionAnswer" varStatus="ext">
						<c:if test="${questionAnswer.isRight eq '1'}" >
							正确答案：${questionAnswer.answer} 
						</c:if>
						</c:forEach>
					</div>
				</li>
				<li class="col-md-4"><strong>试题提交者：</strong>${questionVo.createUser}</li><br/>
			
				<li class="col-md-4"><strong>是否分享：</strong>
				<c:if test="${questionVo.listShareMap ==null}">
				<li>否</li>
				</c:if>
				<c:if test="${questionVo.listShareMap !=null}">
					已分享的用户组:
					<c:forEach items="${questionVo.listShareMap}" var="shareMap" >
						${shareMap.group_id},
                    </c:forEach>
				</c:if>
				</li>	<br/>
				<li class="col-md-4"><strong>是否关联试卷：</strong>&nbsp&nbsp&nbsp&nbsp
				<c:if test="${questionVo.listRelationMap ==null}">
				<li>否</li>
				</c:if>
				<c:if test="${questionVo.listRelationMap !=null}">
					已关联的试卷:
					<c:forEach items="${questionVo.listRelationMap}" var="relationMap" >
						${relationMap.name},
                    </c:forEach>
				</c:if>
				</li>
			</ul>
			 <p class="tool-box" style="text-align:center">
			<button type="button" class="btn btn-submit" onclick="cancle()">返回</button>
			</p>
		</div>
		
	

<script type="text/javascript">


function cancle(){
	location.href="${ctx}/manage/question/GetPulicQuestion"
}
</script>
</body>
</html>
