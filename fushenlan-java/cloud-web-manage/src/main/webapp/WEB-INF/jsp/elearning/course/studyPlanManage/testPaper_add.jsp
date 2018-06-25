<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  <%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> --%>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<meta name="description" content="">
<%-- <link rel="stylesheet" type="text/css" href="${ctx}/resources/css/style.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/font/iconfont.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/page.css"> --%>
<link rel="stylesheet" type="text/css" href="${ctx }/resources/css/style.css">
<link rel="stylesheet" type="text/css" href="${ctx }/resources/css/font/iconfont.css">
<link rel="stylesheet" type="text/css" href="${ctx }/resources/css/page.css">
<title>Insert title here</title>
</head>
<body>
		<div class="form-detail">
			<div class="title"><strong>添加试卷</strong></div>
			<div class="tab-pane active" style="border: none">
				<form>
				<ul class=" clearfix online_form">
					<li class="col-md-3"><strong>试卷关键字：</strong><input name="name" type="text" class="ipt-text" placeholder="试卷关键字"></li>
					<li class="col-md-3">
						<strong>试卷分类：</strong>
						<div class="ipt-select">
							<i class="icon-arrow-down"></i>
							<select name="type">
							    <option value="" >请选择</option>
								<option value="1">职业规范</option>
								<option value="2">演讲口才</option>
								<option value="3">销售技巧</option>
								<option value="4">保险产品</option>
							</select>
						</div>
					</li>
					<!-- <li class="col-md-3">
						<strong>答题时间：</strong>
						<input class="ipt-text" name="examTime"  type="text" placeholder="答题时间">
					</li> -->
					<li class="col-md-1 col-btn">
					<button type="button"  class="btn btn-submit btn-radius btn-search" style="top:114px;right:50px;"  onclick="search()" ><i class="icon-search"></i> 查询</button>
				</li>
				</ul>
			</form>
				
			<div class="title"><strong>查询结果</strong></div>
			<div id="result" style="height:500px;overflow-y:auto;" class="search_result" >
				<div class="list_div">
					<table class="table table-agents" id="tab">
					</table>		
				</div> 
			</div>
			</div>
		</div>
			<div class="ui-button">
				<button type="button" class="btn btn-submit" onclick="save()">保存</button>
				<button type="button" class="btn btn-default" onclick="cancle()">取消</button>
			</div>
		<%-- <script type="${ctx}/resources/js/common/page.js"></script> --%>
       <script src="${ctx}/resources/js/common/jquery-3.2.1.min.js"></script> 
		<%-- <script src="${ctx}/resources/js/elerning/common.js"></script> --%>
		<script type="text/javascript"	src="${ctx}/resources/libs/layer/layer.js"></script>
		<script type="text/javascript">
		$(function(){
			search();
		});
		
		 var tagId='${stageId}';
		 $.fn.serializeObject = function()    
	     {    
	   		var o = {};    
	  		 var a = this.serializeArray();    
	   		$.each(a, function() {    
	       	if (o[this.name]) {    
	           if (!o[this.name].push) {    
	               o[this.name] = [o[this.name]];    
	           }    
	           o[this.name].push(this.value || '');    
	      	 } else {    
	           o[this.name] = this.value || '';    
	      	 }    
	  			 });    
	   			return o;    
		};
		function search(){
		    var  html='';
	        var submitter=1;
	        var para = $('form').serialize();
	        para='submitter='+submitter+'&'+para;
			$.ajax({  
		        type: 'GET', 
		        contentType:'application/json', 
		        dataType :"json",  
		        data :para,
		        url: "${ctx}/manage/studyPlanManage/selectPapers",//请求的action路径  
		        error: function (data) {//请求失败处理函数  
		          layer.msg("系统繁忙~~~"); 
		        },  
		        success:function(data){ //请求成功后处理函数。
		        var idList="${idList}";
		        if(data.code==1){
		           var data2= data.data;
		           html+="<thead><tr><th><label class='pos-rel'><input  id='checkbox' type='checkbox' name='checkallbox' class='ace'></label></th>";
		           html+="<th>试卷名称</th><th>通过分数/总分</th><th>试卷上传者</th><th>上传日期</th><th>试卷分类</th> ";
		           html+='</tr></thread><tbody>';
		           $.each(data2,function(i,item){  
		            	if(idList.indexOf(item.id)>-1){
		            	 }else{
		            		 var type=new Array('','职业规范','演讲口才','销售技巧','保险产品');
		            		 if(item.type!=null){
		            		html+="<tr><td><label class='pos-rel'>";
			            	html+="<input type='checkbox' id='"+item.id+"'  name='checkbox' class='ace' valu='"+item.name+"'></label></td>"; 
	                        html+="<td>"+item.name+"</td><td>"+item.passMark+"</td><td>"+item.createUser+"</td><td>"+item.gmtCreate+"</td><td>"+type[item.type]+"</td></tr>"; 
		            		 }else{
		            			 html+="<tr><td><label class='pos-rel'>";
					            	html+="<input type='checkbox' id='"+item.id+"'  name='checkbox' class='ace' valu='"+item.name+"'></label></td>"; 
			                        html+="<td>"+item.name+"</td><td>"+item.passMark+"</td><td>"+item.createUser+"</td><td>"+item.gmtCreate+"</td><td></td></tr>"; 
		            		 }
		            		 
		            	 }
		           });
		           html+="</tbody>";
		        		$("#tab").html(html);
		        		//实现全选与全不选
		        		$("#checkbox").click(function () {
		        		     if($(this).is(':checked')){
		        		    	$("input[name='checkbox']").prop("checked",true);
		        		    }else{
		        		    	$("input[name='checkbox']").prop("checked",false);
		        		    } 
		        		});	
		        	}
		        }
		        });  
		   
	     };
	     
	     //取消
	     function cancle(){
				location.href="${ctx}/manage/studyPlanManage/learnPLan_addEdit";
			}
	     
		function save(){
		 var array=new Array();		
		 $("#tab").find("input[name='checkbox']:checked").each(function(i){
			if($(this).is(":checked")){
				var name=$(this).attr("valu");//名
				var id=$(this).attr("id");//试卷id
				var type=1;//关联类别
				array[i]={name:name,id:id,type:type};
				}
			})
		    parent.closeLayerd(tagId,array); 
			backOne();
		}
		
		 function backOne(){
				var index=parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
			}
	    
	    
		</script>
</body>	
</html>