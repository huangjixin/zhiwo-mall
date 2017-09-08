<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>智惠多商品云购.趣味竞猜</title>
<%@ include file="/WEB-INF/member-include/css.jsp"%>
<%@ include file="/WEB-INF/member-include/js.jsp"%>
<style>
body {
	font-size: 1.5rem;
	background-color: #F2F2F2;
}

.thumbnail {
	padding: 1px;
	margin-bottom: 5px;
	line-height: 1.42857143;
	border: 1px solid #ddd;
	border-radius: 1px;
}

.label label-info {
	padding-top: 5px;
}

.form-group {
	margin: 3px;
}

.activeProperyValue {
	padding-left: 12px;
	padding-right: 12px;
	padding-top: 6px;
	padding-bottom: 6px;
	background-color: red;
	font-size: 1.4rem;
	color: white;
	border-radius: 4px;
	font-weight: normal;
}

.ProperyValue {
	padding-left: 12px;
	padding-right: 12px;
	padding-top: 6px;
	padding-bottom: 6px;
	background-color: #F5F5F5;
	font-size: 1.4rem;
	color: #878787;
	border-radius: 4px;
	font-weight: normal;
}

.label-danger{
     padding-top:5px;

}
</style>

</head>
<body>
	<div class="page-header"
		style="text-align: center; font-size: 2rem; margin-top: 20px; margin-bottom: 10px;">
		<b>趣味竞猜</b><small onclick="javascript:history.back()">&nbsp;&nbsp;返回</small>
	</div>
	<jsp:useBean id="now" class="java.util.Date" />
	<fmt:formatDate value="${now}" type="both" dateStyle="long" pattern="yyyy-MM-dd HH:mm:ss" var="today"/>
	
    <c:forEach var="guessQuestionOption" items="${list}">
    	<fmt:formatDate value="${guessQuestionOption.guessQuestion.questionEndTime}" type="both" dateStyle="long" pattern="yyyy-MM-dd HH:mm:ss" var="date"/>
    	<c:if test="${ date > today }">
    	<div class="thumbnail">
            <div class="caption">
                <input id="${guessQuestionOption.guessQuestion.id}" type="hidden"
                    value="${guessQuestionOption.guessQuestion.id}" />
                <p class="lead">
                    <span id="${guessQuestionOption.guessQuestion.id}_name">${guessQuestionOption.guessQuestion.name}</span>
                </p>
                <c:forEach var="options" items="${guessQuestionOption.guessQuestionOptions}">
                	 <p onClick="bet('${options.id}')">
                        <span id="${options.id}_name">${options.name}</span>
                        <span class="pull-right">赔率&nbsp;<span id="${options.id}_betRate">${options.betRate}</span>&nbsp;<i class="fa fa-angle-right fa-lg"></i></span>
                    </p>
                </c:forEach>
               
                <hr class="hr1" />
                <div class="pull-left">
                    <span class="label label-danger">投注截止时间：<fmt:formatDate value="${guessQuestionOption.guessQuestion.questionEndTime}" type="both" pattern="yyyy年MM月dd日  HH:mm:ss"/></span>
                </div>
                <div class="clearfix" style="height:20px;"></div>
            </div>
        </div>
        </c:if>
    </c:forEach>

	<!-- 模态框（Modal） -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true"  style="width:100%; margin:0; position:fixed; bottom:0; left:0; right:0;">
		<div class="modal-dialog" style="width:100%; margin:0; position:fixed; bottom:0; left:0; right:0; border-radius:0;">
			<div class="modal-content" style="border-radius:0;">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel" style="text-align:center;">广州恒大赢赔率1.26</h4>
				</div>
				<div class="modal-body" style="text-align: center;">
                	<div style="text-align:center">
                    	<label id="betRateBtn1"  onClick="setBetRateStyle('betRateBtn', 'betRateBtn1')" class="ProperyValue" name="betRateBtn" style="width:22%">100</label> <label  id="betRateBtn2"  onClick="setBetRateStyle('betRateBtn', 'betRateBtn2')" class="ProperyValue" name="betRateBtn" style="width:22%">200</label> <label id="betRateBtn3"  onClick="setBetRateStyle('betRateBtn', 'betRateBtn3')"  class="ProperyValue" name="betRateBtn" style="width:22%">500</label> <label id="betRateBtn4"   onClick="setBetRateStyle('betRateBtn', 'betRateBtn4')" class="ProperyValue" name="betRateBtn" style="width:22%">1000</label>
                    </div>
                    <div style="text-align:center">
                    	<label id="betRateBtn5"  onClick="setBetRateStyle('betRateBtn', 'betRateBtn5')"  class="ProperyValue" name="betRateBtn" style="width:22%">2000</label> <label id="betRateBtn6"  onClick="setBetRateStyle('betRateBtn', 'betRateBtn6')"  class="ProperyValue" name="betRateBtn" style="width:22%">5000</label> <label id="betRateBtn7"  onClick="setBetRateStyle('betRateBtn', 'betRateBtn7')"  class="ProperyValue" name="betRateBtn" style="width:22%">1万</label> <label id="betRateBtn8"  onClick="setBetRateStyle('betRateBtn', 'betRateBtn8')"  class="ProperyValue" name="betRateBtn" style="width:22%">5万</label>
                    </div>
                    <br>
                	<div style="text-align:center">
                    	<span>猜中赢<label style="color:red;">110</label>智惠豆</span>
                    </div>
                    <br>
                	<div style="text-align:center">
                    	<button type="submit" class="btn btn-danger"
							style="width: 80%; color: #ffffff;">立即投注</button>
                    </div>
					
					</div>
					<div class="modal-footer" style="text-align: center;">
						
					</div>
				
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
	<script type="text/javascript">
	function setDefaultMemberAddress(addressId){
		var url = "${ctx}/memberInfo/setDefaultMemberAddress";
		var data = {};
		data.id = addressId;
		$.ajax({
			 type: 'POST',
			 url: url ,
			data: data ,
		    success: function (data){
				if(data == '1'){
					alert('设置成功');
					location.reload();
				}
				
			},
		 	dataType: 'json'
		});
	}

	function create(){
		document.form.action="${ctx}/memberInfo/createMemberAddress";
		$("#name").val('');
		$("#mobilPhone").val('');
		$("#street").val('');
	}
	
	function bet(questionId){
		$('#myModal').modal('show');
		
	}
	
	function setBetRateStyle(proName, proValueId){
		$("[name="+proName+"]").removeClass("activeProperyValue");
		$("[name="+proName+"]").addClass("ProperyValue");
		$("#"+proValueId).removeClass("ProperyValue");
		$("#"+proValueId).addClass("activeProperyValue");
	}
	
</script>

</body>
</html>
