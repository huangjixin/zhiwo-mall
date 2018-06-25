<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/style.css">
     <script src="${ctx}/resources/js/common/jquery-3.2.1.min.js"></script>
     <script type="text/javascript"	src="${ctx}/resources/libs/layer/layer.js"></script>
     <ul class="clearfix form_learn">
        <input type="hidden" id="id"  value="${id}"/>
        <input type="hidden" id="astk"  value="${isSticky}"/>
		<strong>置顶排序：</strong><input type="text" id="sseq" class="ipt-text" value="${stickySeq }">
	 </ul> 	
	<div class="panel-footer">	
		<center>
			<input class="btn btn-default search-btn prese-btn" type="button" value="确定" onclick="chooseStudy();"/> 
			<input type="button" class="btn btn-default search-btn prese-btn" value="返回" id="closeBtn" onclick="backOne();"/> 
		</center>
	</div>	
	
	
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
    	var id = $("#id").val();
    	var isSticky = $("#astk").val();
    	var stickySeq = $("#sseq").val().trim();
    	if(stickySeq==""){
    		layer.msg("请填写置顶顺序");
    		return false;
    	}
    	if(isNaN(stickySeq)){
    		layer.msg("请填写数字");
    		return false;
    	}
    	if(stickySeq*1>=101 || stickySeq*1<=0){
    		layer.msg("请填写1-100之间的数字");
    		return false;
    	}
    	if(!(Math.floor(stickySeq) == stickySeq)){
    		layer.msg("请填写1-100之间的整数");
    		return false;
    	}
    	parent.backSticky(id,isSticky,stickySeq);
		var index=parent.layer.getFrameIndex(window.name);
		parent.layer.close(index);
		
    }
    
    
    function backOne(){
		var index=parent.layer.getFrameIndex(window.name);
		parent.layer.close(index);
	}
    </script>
</body>
</html>