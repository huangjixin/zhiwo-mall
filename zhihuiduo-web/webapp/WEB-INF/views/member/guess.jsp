<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>智惠多商品云购.趣味竞猜</title>

<%@ include file="/WEB-INF/member-include/css.jsp"%>
<%@ include file="/WEB-INF/member-include/js.jsp"%>
<link href="${ctx}/css/zhihuiduo.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="page-header"
		style="text-align: center; font-size: 2rem; margin-top: 0px; margin-bottom: 10px;">
		<b>趣味竞猜</b><small onclick="javascript:history.back()">&nbsp;&nbsp;返回</small>
	</div>
	<jsp:useBean id="now" class="java.util.Date" />
	<fmt:formatDate value="${now}" type="both" dateStyle="long"
		pattern="yyyy-MM-dd HH:mm:ss" var="today" />

	<c:forEach var="guessQuestionOption" items="${list}">
		<fmt:formatDate
			value="${guessQuestionOption.guessQuestion.questionEndTime}"
			type="both" dateStyle="long" pattern="yyyy-MM-dd HH:mm:ss" var="date" />
		<c:if test="${ date > today }">
			<div class="thumbnail">
				<div class="caption">
					<input id="${guessQuestionOption.guessQuestion.id}" type="hidden"
						value="${guessQuestionOption.guessQuestion.id}" />
					<p class="lead">
						<span id="${guessQuestionOption.guessQuestion.id}_name">${guessQuestionOption.guessQuestion.name}</span>
					</p>
					<c:forEach var="options"
						items="${guessQuestionOption.guessQuestionOptions}">
						<p
							onClick="bet('${options.id}','${guessQuestionOption.guessQuestion.id}')">
							<span id="${options.id}_name">${options.name}</span> <span
								class="pull-right">猜中回报率&nbsp;<span
								id="${options.id}_betRate" style="color: red;">${options.betRate}</span>&nbsp;<i
								class="fa fa-angle-right fa-lg"></i></span>
						</p>
					</c:forEach>
					<!--<br>
					<hr class="hr1" />-->
                    <br>
					<div class="pull-left">
						<span class="label label-danger">投注截止时间：<fmt:formatDate
								value="${guessQuestionOption.guessQuestion.questionEndTime}"
								type="both" pattern="yyyy年MM月dd日  HH:mm:ss" /></span>
					</div>
					<div class="clearfix" style="height: 20px;"></div>
				</div>
			</div>
		</c:if>
	</c:forEach>

	<div style="width: 100%; text-align: center"
		onClick="javascript:history.back();">
		<label class="activeProperyValue">返回</label>
	</div>
	<!-- 模态框（Modal） -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true"
		style="width: 100%; margin: 0; position: fixed; bottom: 0; left: 0; right: 0;">
		<div class="modal-dialog"
			style="width: 100%; margin: 0; position: fixed; bottom: 0; left: 0; right: 0; border-radius: 0;">
			<div class="modal-content" style="border-radius: 0;">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel"
						style="text-align: center;">广州恒大赢赔率1.26</h4>
				</div>
				<div class="modal-body" style="text-align: center;">
					<div style="text-align: center">
						<label id="betRateBtn1"
							onClick="setBetRateStyle('betRateBtn', 'betRateBtn1')"
							class="ProperyValue" name="betRateBtn" style="width: 22%">100</label>
						<label id="betRateBtn2"
							onClick="setBetRateStyle('betRateBtn', 'betRateBtn2')"
							class="ProperyValue" name="betRateBtn" style="width: 22%">200</label>
						<label id="betRateBtn3"
							onClick="setBetRateStyle('betRateBtn', 'betRateBtn3')"
							class="ProperyValue" name="betRateBtn" style="width: 22%">500</label>
						<label id="betRateBtn4"
							onClick="setBetRateStyle('betRateBtn', 'betRateBtn4')"
							class="ProperyValue" name="betRateBtn" style="width: 22%">1000</label>
					</div>
					<div style="text-align: center">
						<label id="betRateBtn5"
							onClick="setBetRateStyle('betRateBtn', 'betRateBtn5')"
							class="ProperyValue" name="betRateBtn" style="width: 22%">2000</label>
						<label id="betRateBtn6"
							onClick="setBetRateStyle('betRateBtn', 'betRateBtn6')"
							class="ProperyValue" name="betRateBtn" style="width: 22%">5000</label>
						<label id="betRateBtn7"
							onClick="setBetRateStyle('betRateBtn', 'betRateBtn7')"
							class="ProperyValue" name="betRateBtn" style="width: 22%">10000</label>
						<label id="betRateBtn8"
							onClick="setBetRateStyle('betRateBtn', 'betRateBtn8')"
							class="ProperyValue" name="betRateBtn" style="width: 22%">50000</label>
					</div>
					<br>
					<div style="text-align: center">
						<span>猜中赢<label style="color: red;" id="winLabel">110</label>智惠豆
						</span>
					</div>
					<br>

					<form id="form" action="${ctx}/memberGuess/guessCheckOut"
						method="post">
						<shiro:authenticated>
							<input id="bet" value="" type="hidden" name="bet" />
							<input id="optionsId" name="optionId" value="" type="hidden" />
							<input id="questionId" name="questionId" value="" type="hidden" />
						</shiro:authenticated>

						<!--required:true,number:true}-->
						<div style="text-align: center">
							<shiro:authenticated>
								<button id="submitBtn" disabled="true" type="button"
									onClick="save();" class="btn btn-danger"
									style="width: 100%; margin: 0; position: fixed; bottom: 0; left: 0; right: 0; border-radius: 0px;">立即投注</button>
							</shiro:authenticated>
							<shiro:notAuthenticated>
								<a href="${ctx}/memberLogin"><button disabled="true"
										type="button" onClick="save();" class="btn btn-danger"
										style="width: 100%; margin: 0; position: fixed; bottom: 0; left: 0; right: 0; border-radius: 0px;">点击去登录</button></a>
							</shiro:notAuthenticated>
						</div>
					</form>
				</div>
				<div class="modal-footer" style="text-align: center;"></div>

			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>

	<%--<%@ include file="/WEB-INF/member-include/bottomIndex.jsp"%>--%>
	<!--$("#indexBtn").removeClass("menuItemActive");	
			$("#indexBtn").addClass("menuItem");
			$("#guessBtn").addClass("menuItemActive");-->
	<script type="text/javascript">
		
	<%@ include file="/WEB-INF/member-include/js-guess.jsp"%>
		
	</script>

</body>
</html>
