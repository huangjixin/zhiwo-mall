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

 <form id="PaperForm" action="${ctx}/manage/paper/GetExamPaper" method="get">

		<!-- S Filter Box -->
		<div class="filter-box">
			<ul class="ui-form grid-row">
				<li class="col-md-4"><strong>试卷名称</strong><input class="ipt-text" id="paperName" name="name" value="${name}" style="margin-left: 0px; type="text" placeholder="试卷名称"></li>
				<li class="col-md-3"><strong>答题人</strong><input class="ipt-text" id="userName" name="userName" value="${userName }" style="margin-left: 0px; type="text" placeholder="答题人姓名"></li>
				<li class="col-md-3">
					<strong>试卷状态</strong>
						<select id="paperState" name="paperState" value="${paperState }">
							 <option <c:if test="${paperState==''}">selected</c:if> value="">全部</option>
							<option value="1" <c:if test="${paperState=='1'}">selected</c:if>>已阅卷</option>
							<option value="2" <c:if test="${paperState=='2'}">selected</c:if>>待阅卷</option>
						</select>
				</li>
				<li class="col-md-4" style="text-align: right"><button type="submit" class="btn btn-submit btn-radius btn-search" onclick=""><i class="icon-search"></i> 查询</button></li>
			</ul>
		</div>
		<!-- E Filter Box -->
		
		<table class="table table-agents table_public">
			<thead>
				<tr>
					<th>试卷号</th>
					<th>试卷名称</th>
					<th>题量</th>
					<th>通过分数/总分</th>
					<th>答题人</th>
					<th>试卷状态</th>
					<th>试卷得分</th>
					<th>答题时间</th>
					<th>考试次数</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				
				<c:forEach items="${data.records}" var="elPaperVo"  varStatus="ext">
                   <tr>
                   <td>${ext.index+1 }</td>
					<td>${elPaperVo.name }</td>
					<td>${elPaperVo.classNum }</td>
					<td>${elPaperVo.passMark }/${elPaperVo.fullMark }</td>
					<td>${elPaperVo.userName }</td>
					<td>
					<c:if test="${elPaperVo.paperState =='1' }">已阅卷</c:if>
					<c:if test="${elPaperVo.paperState =='2' }">待阅卷</c:if>
					</td>
					<td>${elPaperVo.score }</td>
					<td>${elPaperVo.examTime }</td>
					<td>${elPaperVo.examNum }</td>
					<td>
						<a class="color-detail" href="${ctx}/manage/paper/selectExamPaperById?id=${elPaperVo.id }&paperState=${elPaperVo.paperState }&userId=${elPaperVo.userId}&examNum=${elPaperVo.examNum}&planCourseId=${elPaperVo.planCourseId}"  title="查看">查看</a>
					  
					</td>
				</tr>
                    </c:forEach>
			</tbody>
		</table>
		<div class="table-paging clearfix" id="pageDiv">
		</div>
	</form>

<script type="text/javascript" src="${ctx}/resources/js/common/page.js"></script>

<script type="text/javascript">
$(function(){
	var name='${name}';
	var userName = '${userName}';
	var paperState ='${paperState}';
	var groupId ='${groupId}';
	var tagId = '${tagId}';
	page("${ctx}/manage/paper/GetExamPaper?name="+name+"&userName="+userName+"&paperState="+paperState+"&groupId="+groupId+"&tagId="+tagId,'${data.pageSize}','${data.pageNo}','${data.pageTotal}','pageDiv');
	//pageAjax("${ctx}/manage/paper/ajaxExamPaper?name="+name+"&userName="+userName+"&paperState="+paperState+"&groupId="+groupId+"&tagId="+tagId,'${data.pageSize}','${data.pageNo}','${data.pageTotal}','pageDiv');
})
//用于页面回显问题  视实际情况而定 
	 var codeArray ={};
	 codeArray['0'] = '视频';
	 codeArray['1'] = 'ppt';
	 codeArray['2'] = 'SCORM';
	 codeArray['3'] = 'WORD';
	 codeArray['4'] = 'EXCEL';
	 codeArray['type0'] = '单选';
	 codeArray['type1'] = '多选';
	 codeArray['type2'] = '判断';
	 codeArray['type3'] = '问答';
	 codeArray['paperState1'] = '已阅卷';
	 codeArray['paperState2'] = '待阅卷';
	 
	 
	 
	 function pageNext(url){
	    	// 查询字段取页面加载时 model传入的值  防止分页执行查询
			var name='${name}';
			var userName = '${userName}';
			var paperState ='${paperState}';
			var groupId ='${groupId}';
			var tagId = '${tagId}';
	    	$.get(url,function(returnData){
	    		if(returnData.code == '1'){
	    			var html = "";
	    			$.each(returnData.data.records,function(i,row){
	    	 			row.groupId = row.groupId==null?'':row.groupId; //判断null字段
	    	 			row.createUser = row.createUser==null?'':row.createUser; //判断null字段
	    	 			codeArray[row.tagId] =codeArray[row.tagId] == undefined ?'':codeArray[row.tagId]; //判断undefined字段
	    	 			var tex=row.gmtModified;
	    	 			html+='<tr>'+
	    	 				 '<td>'+(i+1)+'</td>'+
	    	 				 '<td>'+row.name+'</td>'+
	    	 				 '<td>'+row.classNum+'</td>'+
	    	 				 '<td>'+row.passMark+'/'+row.fullMark+'</td>'+
	    	 				 '<td>'+row.userExam[0].userName+'</td>'+
	    	 				'<td>'+codeArray['paperState'+row.paperState]+'</td>'+
	    	 				 '<td>'+row.userExam[0].score+'</td>'+
	    	 				'<td>'+row.examTime+'</td>'+
	    	 				 '<td><a class="color-detail" href="${ctx}/manage/paper/selectExamPaperById?id='+row.id+'&paperState='+row.paperState+'  title="查看">查看</a></td>';
	    	 			})
	    			$(".table tbody").html(html);
	    			pageAjax("${ctx}/manage/paper/ajaxExamPaper?name="+name+"&userName="+userName+"&paperState="+paperState+"&groupId="+groupId+"&tagId="+tagId,returnData.data.pageSize,returnData.data.pageNo,returnData.data.pageTotal,'pageDiv');
	    		}
	    	})
	    }
	     
	 
	 
	 
	 
/* function selectExamPaperAjax(){
	$.ajax({
		  async : false,  
	      cache : false,  
	      type: 'POST',
	      data:$("#PaperForm").serialize(),
	      url: "${ctx }/manage/paper/ajaxExamPaper",
	      dataType: "json",
	  success: function(returnData){
		 if(returnData.code==1){
			var html = "";
			$.each(returnData.data.records,function(i,row){
 			row.groupId = row.groupId==null?'':row.groupId; //判断null字段
 			row.createUser = row.createUser==null?'':row.createUser; //判断null字段
 			codeArray[row.tagId] =codeArray[row.tagId] == undefined ?'':codeArray[row.tagId]; //判断undefined字段
 			var tex=row.gmtModified;
 			html+='<tr>'+
 				 '<td>'+(i+1)+'</td>'+
 				 '<td>'+row.name+'</td>'+
 				 '<td>'+row.classNum+'</td>'+
 				 '<td>'+row.passMark+'/'+row.fullMark+'</td>'+
 				 '<td>'+codeArray[row.groupId]+'</td>'+
 				  '<td>'+codeArray[row.tagId]+'</td>'+
 				 '<td>'+row.userExam[0].userName+'</td>'+
 				'<td>'+codeArray['paperState'+row.paperState]+'</td>'+
 				 '<td>'+row.userExam[0].score+'</td>'+
 				'<td>'+row.examTime+'</td>'+
 				 '<td><a class="color-detail" href="${ctx}/manage/paper/selectExamPaperById?id='+row.id+'&paperState='+row.paperState+'  title="查看">查看</a></td>';
 			})
 			$(".table tbody").html(html);
			}
	  },
	  
	  
	});
} */
</script>
</body>
</html>
