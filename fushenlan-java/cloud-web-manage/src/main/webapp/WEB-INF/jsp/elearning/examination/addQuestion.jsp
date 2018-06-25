<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/elerning/style.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/elerning/font/iconfont.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/elerning/page.css">
</head>
<body>
<script type="text/javascript"	src="${ctx}/resources/libs/layer/layer.js"></script>
		<div class="form-detail">
		<div class="title"><strong>新增考题</strong></div>
			<div class="nav-tabs">
				<strong class="active">新增选择题</strong>
				<strong>新增多选题</strong>
				<strong >新增判断题</strong>
				<strong>新增问答题</strong>
			</div>
			<!-- 单选题 -->
			
			<div class="tab-pane active" id="uniterming">
		 
			<input type="hidden" name="question_type"  id="unitermingQuestion_type" value="0">
				<ul class="clearfix form_learn">
					<li class="col-md-12"><strong><ins>*</ins>题目内容：</strong><input type="text" id="unitermingContent"  class="ipt-text" value=""></li>
					<li class="col-md-4">
						<strong><ins>*</ins>题目类型：</strong>
						<div class="ipt-select">
							<i class="icon-arrow-down"></i>
							<select id="unitermingTagId" >
								<option value="0">视频</option>
								<option value="1">PPT</option>
								<option value="2" >SCORM</option>
								<option value="3">WOED</option>
								<option value="4">EXCEL</option>
							</select>
						</div>
					</li>
					<li class="col-md-12"><strong>题目解析：</strong><input type="text" id="unitermingAnalysis" class="ipt-text" value=""></li>
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
							<tbody id ="formtable">
								<tr id="answerTdA">
									<td>
										<label for="yes"><input type="radio" value="A" id="yes" name="share"><span name="selectName">A</span></label>
									</td>
									<td name="answerTd">
										<input type="text" name="answer" class="ipt-text" value="">
									</td>
									<td>
										<button class="remove" type="button" onclick="sinDeleSelect(this)" title="删除考题"><i class="icon-remove"></i></button>
										<button type="button" title="升序" onclick="sinAscending(this,'answerTdA')" class="form_sort"><span>↑</span></button>
										<button type="button" title="降序" onclick="sinDescending(this,'answerTdA')" class="form_sort"><span>↓</span></button>
									</td>
								</tr>
								<tr id="answerTdB">
									<td>
										<label for="yes"><input type="radio" value="B" id="yes" name="share"><span name="selectName">B</span></label>
									</td>
									<td  name="answerTd">
										<input type="text" class="ipt-text" name="answer" value="">
									</td>
									<td>
									<button class="remove" type="button" onclick="sinDeleSelect(this)" title="删除考题"><i class="icon-remove"></i></button>
										<button type="button" title="升序" onclick="sinAscending(this,'answerTdB')" class="form_sort"><span>↑</span></button>
										<button type="button" title="降序" onclick="sinDescending(this,'answerTdB')" class="form_sort"><span>↓</span></button>
									</td>
								</tr>
								<tr id="answerTdC">
									<td>
										<label for="yes"><input type="radio" value="C" id="yes" name="share"><span name="selectName">C</span></label>
									</td>
									<td  name="answerTd">
										<input type="text" class="ipt-text" name="answer" value="">
									</td>
									<td>
										<button class="remove" type="button" onclick="sinDeleSelect(this)" title="删除考题"><i class="icon-remove"></i></button>
										<button type="button" title="升序" onclick="sinAscending(this,'answerTdC')" class="form_sort"><span>↑</span></button>
										<button type="button" title="降序" onclick="sinDescending(this,'answerTdC')" class="form_sort"><span>↓</span></button>
									</td>
								</tr>
								<tr id="answerTdD">
									<td>
										<label for="yes"><input type="radio" value="D" id="yes" name="share"><span name="selectName">D</span></label>
									</td>
									<td  name="answerTd">
										<input type="text" class="ipt-text"  name="answer" value="">
									</td>
									<td>
										<button class="remove" type="button" onclick="sinDeleSelect(this)" title="删除考题"><i class="icon-remove"></i></button>
										<button type="button" title="升序" onclick="sinAscending(this,'answerTdD')" class="form_sort"><span>↑</span></button>
										<button type="button" title="降序" onclick="sinDescending(this,'answerTdD')" class="form_sort"><span>↓</span></button>
									</td>
								</tr>
							</tbody>
						</table>
						<button type="button" onclick="sinAdd(this)" class="btn btn-sm btn-submit question_add" id="question_add">+ 添加</button> 
					</li>
				</ul>
				<div class="ui-button">
				<button type="button" class="btn btn-submit" onclick="saveQuestion('uniterming')">保存</button>
				<button type="button" class="btn btn-default" onclick="cancleUpdate()">取消</button>
			  </div>
			  
			</div>
			
			<!-- 多选题 -->
			<div class="tab-pane" id="MultiSelect">
			<input type="hidden" name="question_type"  id="MultiSelectQuestion_type" value="1">
				<ul class="clearfix form_learn">
					<li class="col-md-12"><strong><ins>*</ins>题目内容：</strong><input type="text"  id="MultiSelectContent" class="ipt-text" value=""></li>
					<li class="col-md-4">
						<strong><ins>*</ins>题目类型：</strong>
						<div class="ipt-select">
							<i class="icon-arrow-down"></i>
							<select id="MultiSelectTagId">
								<option value="0">视频</option>
								<option value="1">PPT</option>
								<option value="2" >SCORM</option>
								<option value="3">WOED</option>
								<option value="4">EXCEL</option>
							</select>
						</div>
					</li>
					<li class="col-md-12"><strong>题目解析：</strong><input type="text" id="MultiSelectAnalysis" class="ipt-text" value=""></li>
					<li class="col-md-12">
						<strong>题目选项（正确选项请勾选表示）：</strong>
						<table class="col-md-12 form_table" >
							<thead>
								<tr>
									<th>选项</th>
									<th>选项内容</th>
									<th></th>
								</tr>
							</thead>
							<tbody id="formtable2">
								<tr id="multiAnswerTdA">
									<td>
										<label for="yes"><input type="checkbox" value="A" id="yes" name="share"><span name="selectName">A</span></label>
									</td>
									<td>
										<input type="text" class="ipt-text" name="answer" value="">
									</td>
									<td>
										<button class="remove" type="button" onclick="deleSelect(this)" title="删除考题"><i class="icon-remove"></i></button>
										<button type="button" title="升序" onclick="ascending(this,'multiAnswerTdA')" class="form_sort"><span>↑</span></button>
										<button type="button" title="降序" onclick="descending(this,'multiAnswerTdA')" class="form_sort"><span>↓</span></button>
									</td>
								</tr>
								<tr id="multiAnswerTdB">
									<td>
										<label for="yes"><input type="checkbox" value="B" id="yes" name="share"><span name="selectName">B</span></label>
									</td>
									<td>
										<input type="text" class="ipt-text" name="answer" value="">
									</td>
									<td>
										<button class="remove" type="button" onclick="deleSelect(this)" title="删除考题"><i class="icon-remove"></i></button>
										<button type="button" title="升序" onclick="ascending(this,'multiAnswerTdB')" class="form_sort"><span>↑</span></button>
										<button type="button" title="降序" onclick="descending(this,'multiAnswerTdB')" class="form_sort"><span>↓</span></button>
									</td>
								</tr>
								<tr id="multiAnswerTdC">
									<td>
										<label for="yes"><input type="checkbox" value="0" id="yes" name="share"><span name="selectName">C</span></label>
									</td>
									<td>
										<input type="text" class="ipt-text" name="answer" value="">
									</td>
									<td>
										<button class="remove" type="button" onclick="deleSelect(this)" title="删除考题"><i class="icon-remove"></i></button>
										<button type="button" title="升序" onclick="ascending(this,'multiAnswerTdC')" class="form_sort"><span>↑</span></button>
										<button type="button" title="降序" onclick="descending(this,'multiAnswerTdC')" class="form_sort"><span>↓</span></button>
									</td>
								</tr>
								<tr id="multiAnswerTdD">
									<td>
										<label for="yes"><input type="checkbox" value="0" id="yes" name="share"><span name="selectName">D</span></label>
									</td>
									<td>
										<input type="text" class="ipt-text" name="answer" value="">
									</td>
									<td>
										<button class="remove" type="button" onclick="deleSelect(this)" title="删除考题"><i class="icon-remove"></i></button>
										<button type="button" title="升序" onclick="ascending(this,'multiAnswerTdD')" class="form_sort"><span>↑</span></button>
										<button type="button" title="降序" onclick="descending(this,'multiAnswerTdD')" class="form_sort"><span>↓</span></button>
									</td>
								</tr>
							</tbody>
						</table>
						<button type="button" id="question_add" onclick="addSelect(this)" class="btn btn-sm btn-submit question_add">+ 添加</button>
					</li>
				</ul>
				
				<div class="ui-button">
				<button type="button" class="btn btn-submit" onclick="saveQuestion('MultiSelect')">保存</button>
				<button type="button" class="btn btn-default" onclick="cancleUpdate()">取消</button>
			  </div>
			</div>
			<!-- 判断题 -->
			<div class="tab-pane" id="judge">
			<input type="hidden" name="question_type"  id="judgeQuestion_type" value="2">
				<ul class="clearfix form_learn">
					<li class="col-md-12"><strong><ins>*</ins>题目内容：</strong><input type="text"  id="judgeContent" class="ipt-text" value=""></li>
					<li class="col-md-4">
						<strong><ins>*</ins>题目类型：</strong>
						<div class="ipt-select">
							<i class="icon-arrow-down"></i>
							<select id="judgeTagId">
								<option value="0">视频</option>
								<option value="1">PPT</option>
								<option value="2" >SCORM</option>
								<option value="3">WOED</option>
								<option value="4">EXCEL</option>
							</select>
						</div>
					</li>
					<li class="col-md-12"><strong>题目解析：</strong><input type="text"  id="judgeAnalysis" class="ipt-text" value=""></li>
					<li class="col-md-6 zlgl_share">
						<strong>是否正确：</strong>
						<label for="yes"><input type="radio" value="0" checked="checked" id="yes" name="share">是
						</label>
						<label for="no"><input type="radio" value="0" id="no" name="share">否
						</label>
					</li>
				</ul>
				 <div class="ui-button">
				<button type="button" class="btn btn-submit" onclick="judgeQuestion('judge')">保存</button>
				<button type="button" class="btn btn-default" onclick="cancleUpdate()">取消</button>
			</div>
			</div>
			<!-- 问答题 -->
			<div class="tab-pane" id="Ask" >
			<input type="hidden" name="question_type"  id="AskQuestion_type" value="3">
				<ul class="clearfix form_learn">
					<li class="col-md-12"><strong><ins>*</ins>题目内容：</strong><input type="text" id="AskContent" class="ipt-text" value=""></li>
					<li class="col-md-4">
						<strong><ins>*</ins>题目类型：</strong>
						<div class="ipt-select">
							<i class="icon-arrow-down"></i>
							<select id="AskTagId">
								<option value="0">视频</option>
								<option value="1">PPT</option>
								<option value="2" >SCORM</option>
								<option value="3">WOED</option>
								<option value="4">EXCEL</option>
							</select>
						</div>
					</li>
					<li class="col-md-12"><strong><ins>*</ins>题目解析：</strong><input type="text" id="AskAnalysis" class="ipt-text" value=""></li>
					<li class="col-md-12"><strong>参考答案：</strong><input type="text" class="ipt-text" id="AskAnswerSin" value=""></li>
				</ul>
				<div class="ui-button">
				<button type="button" class="btn btn-submit" onclick="saveAnswerQuestion('Ask')">保存</button>
				<button type="button" class="btn btn-default" onclick="cancleUpdate()">取消</button>
			</div>
			</div>
			
			
			
		</div>
		<!-- / E Table Paging -->
		<script type="text/javascript" src="${ctx}/resources/libs/layer/layer.js"></script>
		<script type="text/javascript">
		var mark = 5;
		var marks = 5;
		var sinMark = 5;
		
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
		
		$('.nav-tabs').each(function(index, element) {
			var _obj = $(this);
			$(this).on('click', 'strong:not(.active)', function(){
				$(this).addClass('active').siblings('strong').removeClass('active');
				_obj.nextAll('.tab-pane').removeClass('active').eq($(this).index()).addClass('active')
			})
		});
		
		
		function cancleUpdate(){
			location.href="${ctx}/manage/question/GetPrivateQuestion";
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
		}
		

		//新增单选，多选
		   function saveQuestion(id){
			
			var content=$("#"+id+"Content").val();
			var tagId=$("#"+id+"TagId").val();
			var analysis=$("#"+id+"Analysis").val();
			var question_type=$("#"+id+"Question_type").val();
			
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
			
			 var  verify=0;	
			 
			 var groupCheckbox=$("#"+id+" input[name='share']");
		     for(i=0;i<groupCheckbox.length;i++){
		        if(groupCheckbox.eq(i).is(":checked")){
		        	//var name=groupCheckbox.eq(i).parent().parent().siblings().find(".clickable").text();
		        	var x='1';
		        	verify=1;
		    	   selects.push(x);
		    	//contents[i] =name;
		        }else{
		        	selects.push('0');
		        }
		    }
		     if(verify==0){
		    	 layer.msg("请选择正确的选项");
		    	 return;
		     }
		     
		     var groupCheckbox1=$("#"+id+" input[name='answer']");
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
		        data : {"content":content,"tagId":tagId,"analysis":analysis,selects:selects,answers:answers,"question_type":question_type},
		        url: "${ctx}/manage/question/updateOrSavePrivateQuestion",//请求的action路径  
		        error: function (data) {//请求失败处理函数  
		            layer.confirm("添加失败", {
		   				icon: 3,
		   			  	btn: ['确定'] //按钮
		   			}, function(){
		   				location.href="${ctx}/manage/question/GetPulicQuestion?otherType="+1;
		   			}); 
		        },  
		        success:function(data){ //请求成功后处理函数。
		        	layer.confirm("添加成功", {
		   				icon: 6,
		   			  	btn: ['确定'] //按钮
		   			}, function(){
		   				location.href="${ctx}/manage/question/GetPulicQuestion?otherType="+1;
		   			}); 
		        	
		        	
		        }  
		    }); 
		 }
		
		
		
		
		//新增判断题
		function judgeQuestion(id){
			var content=$("#"+id+"Content").val();
			var tagId=$("#"+id+"TagId").val();
			var analysis=$("#"+id+"Analysis").val();
			var question_type=$("#"+id+"Question_type").val();
			if(null==content || content==""){
				layer.msg("试题内容不能为空");
				return;
			}
			if(null==tagId || tagId==""){
				layer.msg("试题类型不能为空");
				return;
			}
		 	var isRead="0";
		 	var groupCheckbox=$("#"+id+" input[name='share']");
		 	if(groupCheckbox.eq(0).is(":checked")){
		 		isRead=1
		 	}
		 	
		 	$.ajax({  
		        async : false,  
		        cache : false,  
		        type: 'POST',  
		        dataType : "json",  
		        data : {"content":content,"tagId":tagId,"analysis":analysis,"question_type":question_type,"isRead":isRead},
		        url: "${ctx}/manage/question/updateOrSavePrivateQuestion",//请求的action路径  
		        error: function (data) {//请求失败处理函数  
		            layer.confirm("添加失败", {
		   				icon: 3,
		   			  	btn: ['确定'] //按钮
		   			}, function(){
		   				location.href="${ctx}/manage/question/GetPulicQuestion?otherType="+1;
		   			}); 
		        },  
		        success:function(data){ //请求成功后处理函数。
		        	layer.confirm("添加成功", {
		   				icon: 6,
		   			  	btn: ['确定'] //按钮
		   			}, function(){
		   				location.href="${ctx}/manage/question/GetPulicQuestion?otherType="+1;
		   			}); 
		        	
		        	
		        }   
		    }); 
		 	
		 	
		}
		//新增问答     
	 function saveAnswerQuestion(id){
		 var content=$("#"+id+"Content").val();
			var tagId=$("#"+id+"TagId").val();
			var analysis=$("#"+id+"Analysis").val();
			var question_type=$("#"+id+"Question_type").val();
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
			
		 var answerSin=$("#"+id+"AnswerSin").val();
		 
		 $.ajax({  
		        async : false,  
		        cache : false,  
		        type: 'POST',  
		        dataType : "json",  
		        data : {"content":content,"tagId":tagId,"analysis":analysis,"question_type":question_type,"answerSin":answerSin},
		        url: "${ctx}/manage/question/updateOrSavePrivateQuestion",//请求的action路径  
		        error: function (data) {//请求失败处理函数  
		            layer.confirm("添加失败", {
		   				icon: 3,
		   			  	btn: ['确定'] //按钮
		   			}, function(){
		   				location.href="${ctx}/manage/question/GetPulicQuestion?otherType="+1;
		   			}); 
		        },  
		        success:function(data){ //请求成功后处理函数。
		        	layer.confirm("添加成功", {
		   				icon: 6,
		   			  	btn: ['确定'] //按钮
		   			}, function(){
		   				location.href="${ctx}/manage/question/GetPulicQuestion?otherType="+1;
		   			}); 
		        	
		        	
		        }   
		    }); 
			
		 
	 }
	 
	 

</script>
</body>
</html>
