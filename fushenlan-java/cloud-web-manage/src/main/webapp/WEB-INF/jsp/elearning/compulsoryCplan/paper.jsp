<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<!--  <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"> -->
<!--  <meta name="apple-mobile-web-app-capable" content="yes"> -->
<!-- <meta name="apple-mobile-web-app-status-bar-style" content="black"> -->
<!-- <meta name="format-detection" content="telephone=no"> -->
<!-- <meta name="description" content="nodecorate"> -->
<title>Insert title here</title>
</head>
<body>
<script src="${ctx}/resources/js/common/jquery-3.2.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/style.css">
     <script type="text/javascript"	src="${ctx}/resources/libs/layer/layer.js"></script>
		<table class="table table-agents">
			<thead>
				<tr>
					<th>
					</th>
					<th>添加试卷</th>
				</tr>
			</thead>
			<tbody class="tdy" name="ty">
				<c:forEach items="${paperList}" var="paper">
					<tr>
					  <td><input type="radio" name="selectAll" id="selectById" value="${paper.id }"/></td>
					  <td>${paper.name}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	<div class="panel-footer">	
		<center>
			<input class="btn btn-default search-btn prese-btn" type="button" value="确定" onclick="chooseStudy();"/> 
			<input type="button" class="btn btn-default search-btn prese-btn" value="返回" id="closeBtn" onclick="backOne();"/> 
		</center>
	</div>	
	
	
   <%--  <script src="${ctx}/resources/js/common.js"></script> --%>
    
    <script>
    
    $(function(){
    	var str = "${scId}";
    	$("tbody[name='ty']").children().each(function(i){
    		if(str.match($(this).children().first().children().val())){
    			$(this).remove();
    		}
    	}) 
    })
    function chooseStudy(){
	var str ="";
	var sId;
	var sName;
	$(".tdy :input[type='radio']").each(function(i){
		if(this.checked==true){
			sId = $(this).val();
			sName = $(this).parent().parent().children('td').eq(1).text();
			var rs = this.value;
			str=str+rs+",";
		}
	});
	if(str.length==0){
		layer.alert('你还没有选择要的记录', {icon: 5});
	}else{
		layer.confirm('你确定要选中的记录吗？', {
			icon: 3,
		  	btn: ['确定', '取消'] //按钮
		}, function(){
		  	parent.addPaperOne(sName,sId);
			var index=parent.layer.getFrameIndex(window.name);
			parent.layer.close(index);
		});
	  
     }
    }
    
    
    function backOne(){
		var index=parent.layer.getFrameIndex(window.name);
		parent.layer.close(index);
	}
    </script>
</body>
</html>