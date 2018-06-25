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
<meta name="description" content="">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/elerning/style.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/elerning/font/iconfont.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/elerning/page.css">
</head>
<body>
		<div class="form-detail">
		<form id="paperQuestion" action="${ctx}/manage/paper/addPaperQuestion" method="post">
		   <input type="hidden" name="paper.id" value="${elPaperVo.id }">
			<div class="title"><strong>试卷基本信息</strong></div>
			<ul class="clearfix form_learn">
				<li class="col-md-12"><strong><ins>*</ins>试卷名称：</strong><input type="text" id="paperName"  name="paper.name" class="ipt-text" value="${elPaperVo.name }"></li>
				<li class="col-md-12"><strong><ins>*</ins>试卷说明：</strong><input type="text" id="discription" name="paper.discription" class="ipt-text" value="${elPaperVo.discription }"></li>
				<li class="col-md-4">
					<strong><ins>*</ins>试卷分类：</strong>
					<div class="ipt-select">
						<i class="icon-arrow-down"></i>
						<select name="paper.tagId" value="${elPaperVo.tagId }"  id="paperTagId">
							<option  value="0" <c:if test="${tagId=='0'}">selected</c:if> >视频</option>
							<option value="1" <c:if test="${tagId=='1'}">selected</c:if> >PPT</option>
							<option value="2" <c:if test="${tagId=='2'}">selected</c:if> >SCORM</option>
							<option value="3" <c:if test="${tagId=='3'}">selected</c:if> >WORD</option>
							<option value="4" <c:if test="${tagId=='4'}">selected</c:if> >EXCEL</option>
						</select>
					</div>
				</li>
				<li class="col-md-4 srsj_ipt"><strong><ins>*</ins>答题时间：</strong><input type="text" name="paper.examTime" class="ipt-text" value="${elPaperVo.examTime }" id="examTime" onkeyup="value=value.replace(/[^\d.]/g,'')"><b>分钟</b></li>
				<li class="col-md-4 srsj_ipt"><strong><ins>*</ins> 考试满分：</strong><input type="text" name="paper.fullMark" class="ipt-text" value="${elPaperVo.fullMark }" id="fullMark" onkeyup="value=value.replace(/[^\d.]/g,'')"><b>分</b></li>
				<li class="col-md-4 srsj_ipt"><strong><ins>*</ins>通过分数：</strong><input type="text" name="paper.passMark"  class="ipt-text" value="${elPaperVo.passMark }" id="passMark" onkeyup="value=value.replace(/[^\d.]/g,'')"><b>分</b></li>
				<li class="col-md-12"><strong><ins>*</ins>其他设置：</strong>
					<dl>
						<dt>阅卷方式：</dt>
						<dd>
							<label for="artificial"><input type="radio" value="1" <c:if test="${elPaperVo.isArtificialMark==1}">checked</c:if> id="artificial" name="paper.isArtificialMark">人工阅卷（可选择主观题）</label>
							<label for="auto"><input type="radio" value="0" <c:if test="${elPaperVo.isArtificialMark==0}">checked</c:if> id="auto" name="paper.isArtificialMark">自动阅卷（不可选择主观题）</label>
						</dd><br>
						<dt>可测试次数：</dt>
						<dd class="srsj_ipt">
							<input type="text" class="ipt-text"  name="paper.testNum" value="${elPaperVo.testNum }" style="width:25%" onkeyup="value=value.replace(/[^\d.]/g,'')"><b>次</b>
						</dd><br>
						<dt>是否可查看答案：</dt>
						<dd>
							<label for="random"><input type="radio" value="1" id="random" name="paper.isSeeAnswer">考试后可查看</label>
							<label for="fixed"><input type="radio" value="2" id="fixed" name="paper.isSeeAnswer">不可查看</label>
							<label for="fixed"><input type="radio" value="3" id="fixed" name="paper.isSeeAnswer">考试通过后可查看</label>
						</dd><br>
						<dt>考题顺序：</dt>
						<dd>
							<label for="random"><input type="radio" value="1" <c:if test="${elPaperVo.isQuestionShuffle==1}">checked</c:if> id="random" name="paper.isQuestionShuffle">随机排序</label>
							<label for="fixed"><input type="radio" value="0" <c:if test="${elPaperVo.isQuestionShuffle==0}">checked</c:if> id="fixed" name="paper.isQuestionShuffle">固定排序</label>
						</dd><br>
						<dt>考题选线顺序：</dt>
						<dd>
							<label for="stoch"><input type="radio" value="1" <c:if test="${elPaperVo.isQuestionItemShuffle==1}">checked</c:if> id="stoch" name="paper.isQuestionItemShuffle">随机排序</label>
							<label for="fasten"><input type="radio" value="0" <c:if test="${elPaperVo.isQuestionItemShuffle==0}">checked</c:if> id="fasten" name="paper.isQuestionItemShuffle">固定排序</label>
						</dd>
					</dl>
				</li>
			</ul>
			
			<ul class="clearfix form_learn">
				<a class="btn btn-submit" title="添加已有考题" onclick="chooseLineOne()">+添加考题</a>
				<a class="btn btn-submit" onclick="score()">+平均分配分数</a>
				<a class="btn btn-submit" title="" onclick="addQuestions()">+新增考题</a>
			</ul>
			
			<div class="paper_question">
				<table class="table table-agents" id="tableQuestion">
				<thead>
				  <tr>
					<th>考题id</th>
					<th>考题内容</th>
					<th>考题类型</th>
					<th>分数</th>
					<th>操作</th>
				  </tr>
				 <thead>
					<tbody class="tr_question" id="tRquestion">
					<c:forEach items="${elPaperVo.questionVo}" var="questionVo" varStatus="ext">
	                 <tr>
						 <td><input style="border:0px" name="paperQuestionList[${ext.index}].questionId" readonly="readonly" type="text" class="ipt-text" value="${questionVo.id }"></td>
						 <td ><input style="border:0px"  readonly="readonly" type="text" class="ipt-text" value="${questionVo.content }"></td>
						 <td>
						   <c:if test="${questionVo.type ==0}">单选题</c:if>
						    <c:if test="${questionVo.type ==1}">多选题</c:if>
						    <c:if test="${questionVo.type ==2}">判断题</c:if>
						    <c:if test="${questionVo.type ==3}">问答题</c:if>
						 
						    <%-- <c:if test="${questionVo.type ==0}"><input style="border:0px"  readonly="readonly" type="text" class="ipt-text" value="单选题"></c:if>
						    <c:if test="${questionVo.type ==1}"><input style="border:0px"  readonly="readonly" type="text" class="ipt-text" value="多选题"></c:if>
						    <c:if test="${questionVo.type ==2}"><input style="border:0px"  readonly="readonly" type="text" class="ipt-text" value="判断题"></c:if>
						    <c:if test="${questionVo.type ==3}"><input style="border:0px"  readonly="readonly" type="text" class="ipt-text" value="问答题"></c:if> --%>
						 </td>
						 <td width="10%"><input type="text" name="paperQuestionList[${ext.index}].questionScore"  class="ipt-text" value="${questionVo.questionScore }"></td>
						 <td><button type="button" onclick="remove(this)" title="移除"><i class="icon-remove" style="height: 38px;font-size: 24px;color: #f60"></i></button></td>
					 </tr>
                    </c:forEach>
				
				
					</tbody>
				</table>
				
			</div>
			</form>
			<div class="ui-button">
				<button type="button" class="btn btn-submit" onclick="paperPreview()">预览</button>
				<button type="button" class="btn btn-submit" onclick="savePaper()">保存</button>
				<button type="button" class="btn btn-default" onclick="cancel()">取消</button>
			</div>
			
		</div>
		
	

<script type="text/javascript" src="${ctx}/resources/libs/layer/layer.js"></script>




<script type="text/javascript">
function chooseLineOne(){
	  layer.open({
		  type: 2,
		  title: false,
		  closeBtn: 1, //不显示关闭按钮
		  shade: [0],
		  area: ['800px', '450px'],
		  content: "${ctx}/manage/paper/selectQuestion", 
		  end: function(){
			  
		  }
	   });
}
function addQuestions(){
	layer.open({
		  type: 2,
		  title: false,
		  closeBtn: 1, //不显示关闭按钮
		  shade: [0],
		  area: ['1000px', '600px'],
		  content: "${ctx}/manage/question/addQuestionOther", 
		  end: function(){
			  
		  }
	   });
}

function addStudy(id,content,type){
	var hh  = $("#tRquestion tr").length;
	
  /* var str='<table class="table table-agents">'+
		 '<thead>'+
		 '<tr>'+
		 '<th>'+
		 '<label class="pos-rel">'+
		 '<input type="checkbox" class="ace" id="selectAll">'+
		 '</label>'+
		 '</th>'+
		 '<th>题号</th>'+
		 '<th>考题内容</th>'+
		 '<th>考题类型</th>'+
		 '</tr>'+
		 '</thead>'+
		 '<tbody>'; */
		 var str="";
			   str=str+'<tr>'+
				 '<td><input style="border:0px;" name="paperQuestionList['+hh+'].questionId" readonly="readonly" type="text" class="ipt-text" value='+id+'></td>'+
				 '<td ><input style="border:0px;"  readonly="readonly" type="text" class="ipt-text" value='+content+'></td>'+
				 '<td><input style="border:0px;"  readonly="readonly" type="text" class="ipt-text" value='+type+'></td>'+
				 '<td width="10%"><input type="text" name="paperQuestionList['+hh+'].questionScore"  class="ipt-text" value="0"></td>'+
				 '<td><button type="button" onclick="remove(this)" title="移除"><i class="icon-remove" style="height: 38px;font-size: 24px;color: #f60;"></i></button></td>'+
				 '</tr>';
	 $(".tr_question").append(str);  
	 
}

function remove(obj){
	$(obj).parents("tr").remove();
}


function savePaper(){
	var fullMark=$("#fullMark").val();
	var passMark=$("#passMark").val();
	var paperName=$("#paperName").val();
	var discription=$("#discription").val();
	/* var paperTime=$("#paperTime").val(); */
	var paperTagId=$("#paperTagId").val();
	if(Number(passMark)>Number(fullMark)){
		layer.msg("通过分数不能大于满分");
		return;
	}
	if(null==paperName || paperName==""){
		layer.msg("试卷名称不能为空");
		return;
	}
	if(null==discription || discription==""){
		layer.msg("试卷说明不能为空");
		return;
	}
	/* if(null==paperTime || paperTime==""){
		layer.msg("考试时间不能为空");
		return;
	} */
	if(null==paperTagId || paperTagId==""){
		layer.msg("试卷分类不能为空");
		return;
	}
	if(null==fullMark || fullMark==""){
		layer.msg("试卷总分不能为空");
		return;
	}
	if(null==passMark || passMark==""){
		layer.msg("通过分数不能为空");
		return;
	}
	var sumNum = 0;
	$("input[name$='.questionScore']").each(function(){
		sumNum += Number($(this).val());
	})
	if(sumNum != Number(fullMark)){
		layer.msg("试题分数总和需等于试卷满分");
		return;
	}
	
	 $.ajax({
		  async : false,  
	      cache : false,  
	      type: 'POST',
	      data:$("#paperQuestion").serialize(),
   	      url: "${ctx }/manage/paper/savePaperQuestion",
   	      dataType: "json",
   	  success: function(data){
   		 if(data.code==1){
   			layer.confirm(data.msg, {
   				icon: 6,
   			  	btn: ['确定'] //按钮
   			}, function(){
   				location.href="${ctx}/manage/paper/GetPublicPaper?otherType="+1;
   			}); 
   			
   		 }else{
   			layer.confirm(data.msg, {
   				icon: 3,
   			  	btn: ['确定'] //按钮
   			}, function(){
   				location.href="${ctx}/manage/paper/GetPublicPaper?otherType="+1;
   			}); 
   		 }
   	  },
   	  error: function (data) {//请求失败处理函数  
   		layer.confirm(data.msg, {
				icon: 3,
			  	btn: ['确定'] //按钮
			}, function(){
				location.href="${ctx}/manage/paper/GetPublicPaper?otherType="+1;
			});  
   	  }
   	});
	
}

function score(){
	var fullMark=$("#fullMark").val();
	if(fullMark==""){
		layer.msg("请设置考试满分选项");
		return false;
	}
	var lg = $(".tr_question").children().length;
	if(lg==0){
		layer.msg("请添加考题选项");
		return false;
	}
	var score = (Number(fullMark)/Number(lg)).toFixed(0);
	$(".tr_question").children().each(function(){
		$(this).children().eq(3).children().val(score);
	});
}

function paperPreview(){
	var fullMark=$("#fullMark").val();
	var sumNum = 0;
	$("input[name$='.questionScore']").each(function(){
		sumNum += Number($(this).val());
	})
	if(sumNum != Number(fullMark)){
		layer.msg("试题分数总和需等于试卷满分");
		return;
	}
	layer.open({
		  type: 2,
		  title: false,
		  closeBtn: 1, //不显示关闭按钮
		  shade: [0],
		  area: ['1000px', '550px'],
		  content: "${ctx}/manage/paper/paperQuestionPreview?"+$("#paperQuestion").serialize(), 
		  end: function(){
			  
		  }
	   });
}

function cancel(){
	location.href="${ctx}/manage/paper/GetPublicPaper?otherType="+1;
}
</script>
</body>
</html>
