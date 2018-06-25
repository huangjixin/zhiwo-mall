<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

</head>
<body>

	
	

		
		<div class="form-detail">
		<form id="updataPrivateQuestion"  method="post">
		
		<input type="hidden" name="question_type"  id="question_type" value="${questionVo.type}">
			<c:if test="${questionVo.type==0}">
			
				<div class="title"><strong>编辑单选题</strong></div>
			<!-- 单选题 -->
			<ul class="clearfix">
				<li class="col-md-12"><strong>题目内容：</strong><input class="ipt-text" type="text"  maxlength="50" placeholder="" id="content" name="content" value="${questionVo.content}"></li>
				<li class="col-md-4">
					<strong>题目类型：</strong>
					<div class="ipt-select">
						<i class="icon-arrow-down"></i>
						   <select name="tagId" id="tagId"  value="${questionVo.tagId}">
							<option value="0"  <c:if test="${questionVo.tagId==0}">selected</c:if>>视频</option>
							<option value="1" <c:if test="${questionVo.tagId==1}">selected</c:if>>PPT</option>
							<option value="3" <c:if test="${questionVo.tagId==2}">selected</c:if>>SCORM</option>
							<option value="2"  <c:if test="${questionVo.tagId==3}">selected</c:if>>Word</option>
							<option value="4" <c:if test="${questionVo.tagId==4}">selected</c:if>>EXCEL</option>
							
						</select>
					</div>
				</li>
				<li class="col-md-12"><strong>题目解析：</strong><input type="text" name="analysis" id="analysis" class="ipt-text" value="${questionVo.analysis}"></li>
				<li class="col-md-12">
						<strong>题目选项（正确选项请勾选表示）：</strong>
						<table class="col-md-12 form_table">
							<thead>
								<tr>
									<th>选项</th>
									<th>选项内容</th>
									<th></th>
								</tr>
							</thead>
							<tbody id="formtable">
							<c:forEach items="${questionVo.questionAnswer}" var="questionAnswer"  varStatus="ext">
                    			<tr id="answerTd${arr[ext.index] }">
									<td>
										<label for="yes"><input type="radio" value="0"  <c:if test="${questionAnswer.isRight==1}">checked</c:if> id="yes" name="share"><span name="selectName">${questionAnswer.option }</span></label>
										<input type="hidden" value="${questionAnswer.id }"  id="answerId" name="answerId">
									</td>
									<td>
										<input type="text" class="ipt-text" name="answer"  value="${questionAnswer.answer}">
									</td>
									<td >
										<button class="remove" type="button" onclick="sinDeleSelect(this)" title="删除考题"><i class="icon-remove"></i></button>
										<button type="button" title="升序" onclick="sinAscending(this,'answerTd${arr[ext.index] }')" class="form_sort"><span>↑</span></button>
										<button type="button" title="降序" onclick="sinDescending(this,'answerTd${arr[ext.index] }')" class="form_sort"><span>↓</span></button>
									</td>
								</tr>
                    		</c:forEach>
								
								
							</tbody>
						</table>
					<button type="button" onclick="sinAdd(this)" class="btn btn-sm btn-submit question_add" id="question_add">+ 添加</button> 
					</li>
			</ul>
			<div class="ui-button">
				<button type="button" class="btn btn-submit" onclick="saveSinQuestion('${questionVo.id}')">保存</button>
				<button type="button" class="btn btn-default" onclick="cancleUpdate()">取消</button>
			</div>
			
			
			</c:if>
			
			<c:if test="${questionVo.type==1}">
				
				
				<!-- 多选题-->
			<div class="title"><strong>编辑多选题</strong></div>
			<ul class="clearfix form_learn">
				<li class="col-md-12"><strong><ins>*</ins>题目内容：</strong><input type="text" maxlength="50"  id="content" name="content" class="ipt-text" value="${questionVo.content}"></li>
				<li class="col-md-4">
					<strong><ins>*</ins>题目类型：</strong>
					<div class="ipt-select">
						<i class="icon-arrow-down"></i>
						<select name="tagId" id="tagId"  value="${questionVo.tagId}">
							<option value="0"  <c:if test="${questionVo.tagId==0}">selected</c:if>>视频</option>
							<option value="1" <c:if test="${questionVo.tagId==1}">selected</c:if>>PPT</option>
							<option value="3" <c:if test="${questionVo.tagId==2}">selected</c:if>>SCORM</option>
							<option value="2"  <c:if test="${questionVo.tagId==3}">selected</c:if>>Word</option>
							<option value="4" <c:if test="${questionVo.tagId==4}">selected</c:if>>EXCEL</option>
							
						</select>
					</div>
				</li>
				<li class="col-md-12"><strong>题目解析：</strong><input type="text"  name="analysis" id="analysis" class="ipt-text" value="${questionVo.analysis}"></li>
				<li class="col-md-12">
					<strong>题目选项（正确选项请勾选表示）：</strong>
					<table class="col-md-12 form_table">
						<thead>
							<tr>
								<th>选项</th>
								<th>选项内容</th>
								<th></th>
							</tr>
						</thead>
						<tbody id="formtable2">
							<c:forEach items="${questionVo.questionAnswer}" var="questionAnswer" varStatus="ext" >
                    			<tr id="multiAnswerTd${arr[ext.index] }">
									<td>
										<label for="yes"><input type="checkbox" value="0"  <c:if test="${questionAnswer.isRight==1}">checked</c:if> id="yes" name="share"><span name="selectName">${questionAnswer.option }</span></label>
										<input type="hidden" value="${questionAnswer.id }"  id="answerId" name="answerId">
									</td>
									<td>
										<input type="text" class="ipt-text" name="answer"  value="${questionAnswer.answer}">
									</td>
									<td >
										<button class="remove" type="button" onclick="deleSelect(this)" title="删除考题"><i class="icon-remove"></i></button>
										<button type="button" title="升序" onclick="ascending(this,'multiAnswerTd${arr[ext.index] }')" class="form_sort"><span>↑</span></button>
										<button type="button" title="降序" onclick="descending(this,'multiAnswerTd${arr[ext.index] }')" class="form_sort"><span>↓</span></button>
									</td>
								</tr>
                    		</c:forEach>
						</tbody>
					</table>
					<button type="button" id="question_add" onclick="addSelect(this)" class="btn btn-sm btn-submit question_add">+ 添加</button>
				</li>
			</ul>
			<div class="ui-button">
				<button type="button" class="btn btn-submit" onclick="saveSinQuestion('${questionVo.id }')">保存</button>
				<button type="button" class="btn btn-default" onclick="cancleUpdate()">取消</button>
			</div>
			
			
			</c:if>
			
			<c:if test="${questionVo.type==2}">
				
				<!-- 判断题 -->
			<div class="title"><strong>编辑判断题</div>
				<ul class="clearfix form_learn">
					<li class="col-md-12"><strong><ins>*</ins>题目内容：</strong><input type="text" maxlength="50" id="content" class="ipt-text" value="${questionVo.content}"></li>
					<li class="col-md-4">
						<strong><ins>*</ins>题目类型：</strong>
						<div class="ipt-select">
							<i class="icon-arrow-down"></i>
							<select name="tagId" id="tagId"  value="${questionVo.tagId}">
							<option value="0"  <c:if test="${questionVo.tagId==0}">selected</c:if>>视频</option>
							<option value="1" <c:if test="${questionVo.tagId==1}">selected</c:if>>PPT</option>
							<option value="3" <c:if test="${questionVo.tagId==2}">selected</c:if>>SCORM</option>
							<option value="2"  <c:if test="${questionVo.tagId==3}">selected</c:if>>Word</option>
							<option value="4" <c:if test="${questionVo.tagId==4}">selected</c:if>>EXCEL</option>
							
						</select>
						</div>
					</li>
					<li class="col-md-12"><strong>题目解析：</strong><input type="text" id="analysis"  name="analysis" class="ipt-text" value="${questionVo.analysis} "></li>
					<li class="col-md-6 zlgl_share">
						<strong>是否正确：</strong>
						<label for="yes"><input type="radio" value="0" <c:if test="${questionVo.questionAnswer[0].isRight==1}">checked</c:if> id="yes" name="share">是
						</label>
						<label for="no"><input type="radio" value="0" <c:if test="${questionVo.questionAnswer[0].isRight==0}">checked</c:if> id="no" name="share">否
						</label>
					</li>
				</ul>
				<div class="ui-button">
				<button type="button" class="btn btn-submit" onclick="saveSinaQuestion('${questionVo.id }','${questionVo.questionAnswer[0].id}')">保存</button>
				<button type="button" class="btn btn-default" onclick="cancleUpdate()">取消</button>
			</div>
			
			
			
			</c:if>
			
			
			<c:if test="${questionVo.type==3}">
				
					<!-- 问答题 -->
			<div class="title"><strong>编辑问答题</strong></div>
			<ul class="clearfix form_learn">
				<li class="col-md-12"><strong><ins>*</ins>题目内容：</strong><input type="text"  maxlength="50" id="content" name="content" class="ipt-text" value="${questionVo.content}"></li>
				<li class="col-md-4">
					<strong><ins>*</ins>题目类型：</strong>
					<div class="ipt-select">
						<i class="icon-arrow-down"></i>
						<select name="tagId" id="tagId"  value="${questionVo.tagId}">
							<option value="0"  <c:if test="${questionVo.tagId==0}">selected</c:if>>视频</option>
							<option value="1" <c:if test="${questionVo.tagId==1}">selected</c:if>>PPT</option>
							<option value="3" <c:if test="${questionVo.tagId==2}">selected</c:if>>SCORM</option>
							<option value="2"  <c:if test="${questionVo.tagId==3}">selected</c:if>>Word</option>
							<option value="4" <c:if test="${questionVo.tagId==4}">selected</c:if>>EXCEL</option>
							
						</select>
					</div>
				</li>
				<li class="col-md-12"><strong><ins>*</ins>题目解析：</strong><input type="text" id="analysis" name="analysis" class="ipt-text" value="${questionVo.analysis}"></li>
				<li class="col-md-12"><strong>参考答案：</strong><input type="text" class="ipt-text"  id="answerSin" value="${questionVo.questionAnswer[0].answer}"></li>
			</ul>
			<div class="ui-button">
				<button type="button" class="btn btn-submit" onclick="saveAnswersQuestion('${questionVo.id }','${questionVo.questionAnswer[0].id}')">保存</button>
				<button type="button" class="btn btn-default" onclick="cancleUpdate()">取消</button>
			</div>
			
			
			
			</c:if>
			
		
			
			
			
			</form>
			
		</div>

<script type="text/javascript" src="${ctx}/resources/libs/layer/layer.js"></script>
<script type="text/javascript">


var sinMark = "${questionVo.questionAnswer}";
sinMark = sinMark.substr(0,sinMark.length - 1)
sinMark =sinMark.substr(1);
sinMark= sinMark.split("),");
sinMark=sinMark.length+1;
var mark = sinMark;

 $('#formtable .icon-remove').each(function (i,ele) {
$(ele).click(function () {
   $(this).parents("tr").remove();
         })
     })
 




	//取消修改
	function cancleUpdate(){
		location.href="${ctx}/manage/question/GetPulicQuestion?otherType="+1;
	 }
	 
	 
	 //修改单选，多选
	function saveSinQuestion(id ){
		var content=$("#content").val();
		var tagId=$("#tagId").val();
		var analysis=$("#analysis").val();
		var questionId=id;
		var question_type=$("#question_type").val();
		if(null==content || content==""){
			layer.msg("试题内容不能为空");
			return;
		}
		if(null==tagId || tagId==""){
			layer.msg("试题类型不能为空");
			return;
		}
		 var selects = new Array();
		 var answers = new Array();
		 var answerIds = new Array();
		
		 var groupCheckboxId=$("input[name='answerId']");
	     for(i=0;i<groupCheckboxId.length;i++){
	    	 answerIds.push(groupCheckboxId[i].value);
	    }
		 
		 
	var  verify=0;	 
		 
		 var groupCheckbox=$("input[name='share']");
	     for(i=0;i<groupCheckbox.length;i++){
	        if(groupCheckbox.eq(i).is(":checked")){
	        	//var name=groupCheckbox.eq(i).parent().parent().siblings().find(".clickable").text();
	        	var x='1';
	        	verify=1;
	    	   selects.push(x);
	    	//contents[i] =name;
	        }else{
	        	selects.push(groupCheckbox[i].value);
	        }
	    }
	     if(verify==0){
	    	 layer.msg("请选择正确的选项");
	    	 return;
	     }
	     var groupCheckbox1=$("input[name='answer']");
	     for(i=0;i<groupCheckbox1.length;i++){
	    	 answers.push(groupCheckbox1[i].value);
	    }
	     
	     for(i=0;i<answers.length;i++){
	    	 if(null==answers[i] || answers[i]==""){
					layer.msg("选项内容不能为空");
					return;
				}
	     }
		
		$.ajax({  
	        async : false,  
	        cache : false,  
	        type: 'POST',  
	        dataType : "json",  
	        data : {"content":content,"tagId":tagId,"analysis":analysis,"questionId":questionId,selects:selects,answers:answers,"question_type":question_type,"answerIds":answerIds},
	        url: "${ctx}/manage/question/updateOrSavePrivateQuestion",//请求的action路径  
	        error: function (data) {//请求失败处理函数  
	        	
	        	 layer.confirm(data.msg, {
	    				icon: 3,
	    			  	btn: ['确定'] //按钮
	    			}, function(){
	    				location.href="${ctx}/manage/question/GetPulicQuestion?otherType="+1;
	    			}); 
	        },  
	        success:function(data){ //请求成功后处理函数。
	        	
	        	layer.confirm(data.msg, {
    				icon: 6,
    			  	btn: ['确定'] //按钮
    			}, function(){
    				location.href="${ctx}/manage/question/GetPulicQuestion?otherType="+1;
    			}); 
	        	
	        	
	        }  
	    }); 
	 }
	 
	 //修改判断题
	 function saveSinaQuestion(id,answerId){
		 
		 var content=$("#content").val();
			var tagId=$("#tagId").val();
			var analysis=$("#analysis").val();
			var questionId=id;
			var question_type=$("#question_type").val();
			
			if(null==content || content==""){
				layer.msg("试题内容不能为空");
				return;
			}
			if(null==tagId || tagId==""){
				layer.msg("试题类型不能为空");
				return;
			}
			
			var isRead="0";
			var groupCheckbox=$("#input[name='share']");
		 	if(groupCheckbox.eq(0).is(":checked")){
		 		isRead=1
		 	}
		 	$.ajax({  
		        async : false,  
		        cache : false,  
		        type: 'POST',  
		        dataType : "json",  
		        data : {"content":content,"tagId":tagId,"analysis":analysis,"questionId":questionId,"question_type":question_type,"answerId":answerId,"isRead":isRead},
		        url: "${ctx}/manage/question/updateOrSavePrivateQuestion",//请求的action路径  
		        error: function (data) {//请求失败处理函数  
		            alert(data.msg);  
		        },  
		        success:function(data){ //请求成功后处理函数。
		        	
		        	alert(data.msg);
		        	location.href="${ctx}/manage/question/GetPulicQuestion?otherType="+1;
		        	
		        }  
		    }); 
		 
	 }
	 
	 //修改问答题
	 function saveAnswersQuestion(id,answerId){
		 
		 var content=$("#content").val();
			var tagId=$("#tagId").val();
			var analysis=$("#analysis").val();
			var questionId=id;
			var question_type=$("#question_type").val();
			var answerSin=$("#answerSin").val();
			
			
			if(null==content || content==""){
				layer.msg("试题内容不能为空");
				return;
			}
			if(null==tagId || tagId==""){
				layer.msg("试题类型不能为空");
				return;
			}
			if(null==analysis || analysis==""){
				layer.msg("试题解析不能为空");
				return;
			}
			$.ajax({  
		        async : false,  
		        cache : false,  
		        type: 'POST',  
		        dataType : "json",  
		        data : {"content":content,"tagId":tagId,"analysis":analysis,"questionId":questionId,"answerSin":answerSin,"question_type":question_type,"answerId":answerId},
		        url: "${ctx}/manage/question/updateOrSavePrivateQuestion",//请求的action路径  
		        error: function (data) {//请求失败处理函数  
		            alert(data.msg);  
		        },  
		        success:function(data){ //请求成功后处理函数。
		        	
		        	location.href="${ctx}/manage/question/GetPulicQuestion?otherType="+1;
		        	
		        }  
		    }); 
		 
	 }
		 
	//单选升序
		function sinAscending(obj,trId){
			if($("#"+trId).prev().html()){
				$("#"+trId).prev().before($("#"+trId));
				 var groupCheckbox=$("#formtable").find("span[name='selectName']");
				
				     $.each(groupCheckbox,function(i){
				    	 $(this).text(String.fromCharCode(64 + parseInt(i+1)));
				     });
			  } else{
				  layer.msg("已经是第一条了");
				  return false;
			  }
			
		}

		//单选降序
		 function sinDescending(obj,trId){
			 if($("#"+trId).next().html()){
					$("#"+trId).next().after($("#"+trId));
					 var groupCheckbox=$("#formtable").find("span[name='selectName']");
					
					     $.each(groupCheckbox,function(i){
					    	 $(this).text(String.fromCharCode(64 + parseInt(i+1)));
					     });
				  } else{
					  layer.msg("已经是最后一条了");
					  return false;
				  }
		  }
		
		 function sinDeleSelect(obj){
			 
			 if($("#formtable tr").length==2){
					layer.msg("试题选项不能少于两项");
					return;
				}
			 $(obj).parent().parent().remove();
			 var groupCheckbox=$("#formtable").find("span[name='selectName']");
			 $.each(groupCheckbox,function(i){
				   $(this).text(String.fromCharCode(64 + parseInt(i+1)));
			});
			 sinMark;
		}
		 
		//多选降序
		 function descending(obj,trId){
			 if($("#"+trId).next().html()){
					$("#"+trId).next().after($("#"+trId));
					 var groupCheckbox=$("#formtable2").find("span[name='selectName']");
					
					     $.each(groupCheckbox,function(i){
					    	 $(this).text(String.fromCharCode(64 + parseInt(i+1)));
					     });
				  } else{
					  layer.msg("已经是最后一条了");
					  return false;
				  }
		  }
		

			//多选升序
			function ascending(obj,trId){
				if($("#"+trId).prev().html()){
					$("#"+trId).prev().before($("#"+trId));
					 var groupCheckbox=$("#formtable2").find("span[name='selectName']");
					
					     $.each(groupCheckbox,function(i){
					    	 $(this).text(String.fromCharCode(64 + parseInt(i+1)));
					     });
				  } else{
					  layer.msg("已经是第一条了");
					  return false;
				  }
				
			}
			

			//增加多选
			function addSelect(obj){
				 var  str = ' <tr id="multiAnswerTd'+String.fromCharCode(64 + parseInt(mark))+'">\n' +
			        '        <td>\n' +
			        '        <label for="yes"><input type="checkbox" value="0" id="yes" name="share"><span name="selectName">'+String.fromCharCode(64 + parseInt(mark))+'</span></label>\n' +
			        '            </td>\n' +
			        '            <td>\n' +
			        '            <input type="text" name="answer" class="ipt-text" value="">\n' +
			        '            </td>\n' +
			        '            <td>\n' +
			        '        <button class="remove" type="button" onclick="deleSelect(this)" title="删除考题"><i class="icon-remove"></i></button>\n' +
			        '        <button type="button" title="升序" onclick="ascending(this,\'multiAnswerTd'+String.fromCharCode(64 + parseInt(mark))+'\')" class="form_sort"><span>↑</span></button>\n' +
			        '        <button type="button" title="降序" onclick="descending(this,\'multiAnswerTd'+String.fromCharCode(64 + parseInt(mark))+'\')" class="form_sort"><span>↓</span></button>\n' +
			        '        </td>\n' +
			        '        </tr>';  
			    $('#formtable2').append(str);
			    var groupCheckbox=$("#formtable2").find("span[name='selectName']");
				 $.each(groupCheckbox,function(i){
					   $(this).text(String.fromCharCode(64 + parseInt(i+1)));
				});
			    mark++;
			}
		
			//增加单选
			function sinAdd(){
				var  str = ' <tr id="answerTd'+String.fromCharCode(64 + parseInt(sinMark))+'">\n' +
		        '        <td>\n' +
		        '        <label for="yes"><input type="radio" value="0" id="yes" name="share"><span name="selectName">'+String.fromCharCode(64 + parseInt(sinMark))+'</span></label>\n' +
		        '            </td>\n' +
		        '            <td>\n' +
		        '            <input type="text" name="answer" class="ipt-text" value="">\n' +
		        '            </td>\n' +
		        '            <td>\n' +
		        '        <button class="remove" type="button" onclick="sinDeleSelect(this)" title="删除考题"><i class="icon-remove"></i></button>\n' +
		        '        <button type="button" title="升序" onclick="sinAscending(this,\'answerTd'+String.fromCharCode(64 + parseInt(sinMark))+'\')" class="form_sort"><span>↑</span></button>\n' +
		        '        <button type="button" title="降序" onclick="sinDescending(this,\'answerTd'+String.fromCharCode(64 + parseInt(sinMark))+'\')" class="form_sort"><span>↓</span></button>\n' +
		        '        </td>\n' +
		        '        </tr>';  
		    $('#formtable').append(str);
		    var groupCheckbox=$("#formtable").find("span[name='selectName']");
			 $.each(groupCheckbox,function(i){
				   $(this).text(String.fromCharCode(64 + parseInt(i+1)));
			});
			 sinMark++;
			}
			
			function deleSelect(obj){
				if($("#formtable2 tr").length==3){
					layer.msg("试题选项不能少于三项");
					return;
				}
				
				 $(obj).parent().parent().remove();
				 var groupCheckbox=$("#formtable2").find("span[name='selectName']");
				 $.each(groupCheckbox,function(i){
					   $(this).text(String.fromCharCode(64 + parseInt(i+1)));
				});
				mark;
			}
			

</script>
</body>
</html>
