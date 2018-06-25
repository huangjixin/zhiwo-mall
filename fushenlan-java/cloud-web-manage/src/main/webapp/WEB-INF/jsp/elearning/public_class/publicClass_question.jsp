<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 	<title>富卫运维大平台</title>
</head>
<body>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/style.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/font/iconfont.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/page.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/libs/datepicker/skin/default/datepicker.css">

		<div class="form-detail">
			<div class="title"><strong>课程评论管理</strong></div>
			<form action="${ctx }/manage/questionListByPage"  >
			<ul class="edit clearfix">
				<li class="col-md-4"><strong>课程名称：</strong>${publicClassVo.studyPlan.name }</li>
				<li class="col-md-4"><strong>课程编号：</strong>${publicClassVo.id }</li>
				<li class="col-md-4"><strong>课程讲师：</strong>${lecturerName }</li>
				
				
				<li class="col-md-4"><strong>用户姓名：</strong>
					<input name="courseId" id=courseId type="hidden" value="${map.courseId }">
					<input class="ipt-text proTime" id="userName" name="userName" value="${map.userName }" type="text" placeholder="用户姓名">
				</li>
				<li class="col-md-6"><strong>问题内容：</strong>
					<input class="ipt-text " name="comment" id="comment" value="${map.comment }" type="text" placeholder="">
				</li>
				<li class="col-md-6 sameWadte">
					<strong>提问时间：</strong>
					<input type="text" name="beginTime" id="beginTime" value="${map.beginTime }" placeholder="开始时间" class="Wdate" style="max-width: 180px;" onfocus="WdatePicker({onpicked: function(){jQuery(this).trigger('change');},oncleared: function(){jQuery(this).trigger('change');}})">&nbsp;—&nbsp;
					<input type="text" name="endTime" id="endTime" value="${map.endTime }" placeholder="结束时间" class="Wdate" style="max-width: 180px;" onfocus="WdatePicker({onpicked: function(){jQuery(this).trigger('change');},oncleared: function(){jQuery(this).trigger('change');}})">
				</li>
				<li class="col-md-8 zlgl_share"><strong>问题状态：</strong>
					<c:choose>  
		        <c:when test="${'0' eq map.isOpen}">  
		        	<label for="yes"><input name="isOpen" value="0" type="radio" id="yes" checked="checked">隐藏
					</label>
					<label for="no"><input name="isOpen" value="1"  type="radio" id="no">开启
					</label>
		        </c:when>  
		        <c:when test="${'1' eq map.isOpen}">  
		        	<label for="yes"><input name="isOpen" value="0" type="radio" id="yes">隐藏
					</label>
					<label for="no"><input name="isOpen" value="1"  type="radio" id="no" checked="checked">开启
					</label> 
		        </c:when>  
		        <c:otherwise>  
		        	<label for="yes"><input name="isOpen" value="0" type="radio" id="yes">隐藏
					</label>
					<label for="no"><input name="isOpen" value="1"  type="radio" id="no">开启
					</label>
		        </c:otherwise>  
		        </c:choose> 
				</li>
				<li class="col-md-4" style="text-align: right">
					<button type="submit_btn" class="btn btn-submit btn-radius btn-search"><i class="icon-search"></i> 查询</button>
				</li>
			</ul>
			</form>
			<div class="nav-tabs">
				<strong class="active">未回复</strong>
				<strong >已回复</strong>
			</div>
			<!-- 未回复 -->
			<div class="tab-pane active">
				<table class="table table-agents" style="margin-top: 20px">
					<thead>
						<tr>
						<th>
							<label class="pos-rel">
								<input type="checkbox" class="ace"  id="checkbox">
							</label>
						</th>
							<th>问题描述</th>
							<th>提交人</th>
							<th>提交时间</th>
							<th>问题状态</th>
							<th>点赞数</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${page.records }" var="planQuestion">
						<c:if test="${planQuestion.quesAnswer==null||planQuestion.quesAnswer=='' }">
						<tr>
							<td>
								<label class="pos-rel">
									<input type="checkbox" class="ace"  name="checkbox"  value="${planQuestion.id }">
								</label>
							</td>
							<td>${planQuestion.quesDesc }</td>
							<td>${planQuestion.userName }</td>
							<td>${planQuestion.gmtCreate }</td>
							<td>
							<c:if test="${planQuestion.isOpen==1 }">开启	</c:if>
							<c:if test="${planQuestion.isOpen==0 }">隐藏	</c:if>
							</td>
							<td>${planQuestion.likeNum }</td>
							<td>
								<a class="color-detail" href="#" onclick="updateQuestion('${planQuestion.id }')" title="">回复</a>
								<c:if test="${planQuestion.isOpen==0 }">
								<a class="color-detail" href="#" onclick="theBatchShelve(1,'${planQuestion.id }')" title="">开启</a>	
								</c:if>
								<c:if test="${planQuestion.isOpen==1 }">
								<a class="color-detail" href="#" onclick="theBatchShelve(0,'${planQuestion.id }')" title="">隐藏</a>
								</c:if>
							</td>
						</tr>
						</c:if>
					</c:forEach>
					</tbody>
					<c:if test="${not empty page.records }">
					<tfoot>
					<tr>
					<td>
					<button class="btn btn-submit" style="background: #f00" onclick="theBatchShelves(0)">批量隐藏</button>
					<button class="btn btn-submit"  onclick="theBatchShelves(1)">批量开启</button>
					</td>
					</tr>
					</tfoot>
					</c:if>
				</table>
				<div class="table-paging clearfix" id="pageDiv">
				</div>
			</div>
			<!-- 已回复 -->
			<div class="tab-pane">
				<table class="table table-agents" style="margin-top: 20px">
					<thead>
						<tr>
						<th>
							<label class="pos-rel">
								<input type="checkbox" class="ace"  id="checkboxs">
							</label>
						</th>
							<th>问题描述</th>
							<th>提交人</th>
							<th>提交时间</th>
							<th>问题状态</th>
							<th>点赞数</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${page.records }" var="planQuestion">	
						<c:if test="${planQuestion.quesAnswer!=null&&planQuestion.quesAnswer!='' }">
						<tr>
							<td>
								<label class="pos-rel">
									<input type="checkbox" class="ace" name="checkboxs" value="${planQuestion.id }">
								</label>
							</td>
							<td>${planQuestion.quesDesc }</td>
							<td>${planQuestion.userName }</td>
							<td>${planQuestion.gmtCreate }</td>
							<td>
							<c:if test="${planQuestion.isOpen==1 }">开启	</c:if>
							<c:if test="${planQuestion.isOpen==0 }">隐藏	</c:if>
							</td>
							<td>${planQuestion.likeNum }</td>
							<td>
								<a class="color-detail" href="#" onclick="updateQuestion('${planQuestion.id }')" title="">回复</a>
								<c:if test="${planQuestion.isOpen==0 }">
								<a class="color-detail" href="#" onclick="theBatchShelve(1,'${planQuestion.id }')" title="">开启</a>	
								</c:if>
								<c:if test="${planQuestion.isOpen==1 }">
								<a class="color-detail" href="#" onclick="theBatchShelve(0,'${planQuestion.id }')" title="">隐藏</a>
								</c:if>
								
							</td>
						</tr>
						</c:if>
					</c:forEach>	
					</tbody>
					<c:if test="${not empty page.records }">
					<tfoot>
					<tr>
					<td>
					<button class="btn btn-submit" style="background: #f00" onclick="theBatchShelves(0)">批量隐藏</button>
					<button class="btn btn-submit"  onclick="theBatchShelves(1)">批量开启</button>
					</td>
					</tr>
					</tfoot>
					</c:if>
				</table>
				<div class="table-paging clearfix" id="pageDivs">
				</div>
			</div>
			<div class="ui-button">
				<!-- <button type="button" class="btn btn-submit">保存</button> -->
				<button type="button" class="btn btn-default" onclick="cancle()">取消</button>
			</div>
			
		</div>

<%-- <script src="${ctx}/resources/js/common/jquery-3.2.1.min.js"></script> --%>
<%-- <script src="${ctx}/resources/js/common.js"></script> --%>
<script type="text/javascript" src="${ctx}/resources/libs/datepicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common/page.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/layer/layer.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	var courseId=$("#courseId").val();
	var userName=$("#userName").val();
	var beginTime=$("#beginTime").val();
	var endTime=$("#endTime").val();
	var comment=$("#comment").val();
	var isOpen=$("input[name='isOpen']:checked").val();
	if(isOpen==null){
		isOpen="";
    }
	page("${ctx}/manage/questionListByPage?courseId="+courseId+"&userName="+userName+"&beginTime="+beginTime+"&endTime="+endTime+"&comment="+comment+"&isOpen="+isOpen,'${page.pageSize}','${page.pageNo}','${page.pageTotal}','pageDiv');	
	page("${ctx}/manage/questionListByPage?courseId="+courseId+"&userName="+userName+"&beginTime="+beginTime+"&endTime="+endTime+"&comment="+comment+"&isOpen="+isOpen,'${page.pageSize}','${page.pageNo}','${page.pageTotal}','pageDivs');	
}); 




$("#checkbox").click(function () {
    if($(this).is(':checked')){
    	$("input[name='checkbox']").prop("checked",true);
    }else{
    	$("input[name='checkbox']").prop("checked",false);
    }
});
$("#checkboxs").click(function () {
    if($(this).is(':checked')){
    	$("input[name='checkboxs']").prop("checked",true);
    }else{
    	$("input[name='checkboxs']").prop("checked",false);
    }
});

$(function(){
	$('.nav-tabs').each(function(index, element) {
		var _obj = $(this);
		$(this).on('click', 'strong:not(.active)', function(){
			$(this).addClass('active').siblings('strong').removeClass('active');
			_obj.nextAll('.tab-pane').removeClass('active').eq($(this).index()).addClass('active')
		})
	});
})

function theBatchShelves(state,isReply){
	 var type="Question";
	 var questionIds = new Array();
	 var groupCheckbox=$("input[name='checkbox']:checked");
	 var courseId=$("#courseId").val();
	 if(isReply==1){
		 groupCheckbox=$("input[name='checkboxs']:checked");
	 }
	     for(i=0;i<groupCheckbox.length;i++){
	        if(groupCheckbox.eq(i).is(":checked")){
	        	questionIds.push(groupCheckbox[i].value);
	        }
	    }
	   $.ajax({
	      type: 'POST',
	  	  url: "${ctx}/manage/theBatchQuestionOrComment",
	  	  data :{"id" : questionIds,"state":state,"type":type},
	  	  traditional:true,
	  	  success: function(result) {
	  		if(result){
	  			/* layer.alert("修改成功"); */
	  			location.href="${ctx}/manage/questionListByPage?courseId="+courseId;
	  		}else{
	  			layer.alert("回复失败");
	  		}
		  }
	  	})
}

function theBatchShelve(state,id){
	 var type="Question";
	 var questionIds = new Array();
	     questionIds.push(id);
	var courseId=$("#courseId").val();
	   $.ajax({
	      type: 'POST',
	  	  url: "${ctx}/manage/theBatchQuestionOrComment",
	  	  data :{"id" : questionIds,"state":state,"type":type},
	  	  traditional:true,
	  	  success: function(result) {
	  		if(result){
	  			/* layer.alert("修改成功"); */
	  			location.href="${ctx}/manage/questionListByPage?courseId="+courseId;
	  		}else{
	  			layer.alert("修改失败");
	  		}
		  }
	  	})
}

function updateQuestion(questionId){
	var publicClassId=$("#courseId").val();
	location.href="${ctx}/manage/toUpdateQuestionJsp?publicClassId="+publicClassId+"&questionId="+questionId;
}

 function cancle(){
	   window.history.go(-1);
 }

</script>
</body>
</html>