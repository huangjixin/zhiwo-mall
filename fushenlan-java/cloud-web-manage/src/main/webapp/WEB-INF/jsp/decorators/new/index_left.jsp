<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 	<title>富卫运维大平台</title> 
</head>
<body>
	<div class="wrap-menu">
		<dl>
			<c:forEach var="resourceVo" items="${sessionScope.resourceList}" varStatus="idx">
				<dt><strong><i class="icon-renyuanliebiao"></i>${resourceVo.resourceName}</strong><i class="icon-arrow-down"></i></dt>
				<dd>
					<ul>
					<c:forEach var="resource" items="${resourceVo.list}">
					<%-- ${ctx}${resource.url} --%>
						<li class="menu" data="${resource.url}" onclick="session(this);"><a href="javascript:void(0);">${resource.resourceName }</a></li>
					</c:forEach>
					</ul>
				</dd>
			</c:forEach>
			
			<%-- <dt><strong><i class="icon-user-staff"></i>运营人员管理</strong><i class="icon-arrow-down"></i></dt>
			<dd>
				<!-- <ul>
					<li><a href="#">运营人员管理</a></li>
					<li><a href="#">角色管理</a></li>
				</ul> -->
			</dd>
			<dt><strong><i class="icon-business"></i>增员业务管理</strong><i class="icon-arrow-down"></i></dt>
			<dd>
				<ul id="menuUl">
					<li><a href="#">增员活动管理</a></li>
					<li><a href="${ctx}/manage/personnelList">人才库管理</a></li>
					<li><a href="./zysjgl.html">增员试卷管理</a></li>
					<li><a href="#">资料核查管理</a></li>
					<li><a href="#">培训核查管理</a></li>
					<li><a href="${ctx}/manage/flow/list">流程基础管理</a></li>
					<li><a href="${ctx}/manage/flow/flowList">增员流程管理</a></li>
					<li><a href="#">分公司授权管理</a></li>
				</ul>
			</dd> --%>
		</dl>
	</div>
</body>
<script type="text/javascript" src="${ctx}/resources/js/JquerySession.js"></script>
<script type="text/javascript">
    $(function(){
    	var url = $.session.get("dd-dt-li");
    	$(".menu").each(function(){
    		if(url == $(this).attr("data")){
    			$(this).parents().prev().addClass("active");
    			$(this).parents().stop().slideDown(300,function(){
    				$(this).css("display","block");
    			});
    			$(this).find("a").css('color', '#ff6e02');
    		}
    	})
    });
	function session(obj){
		var url = $(obj).attr("data");
		$.session.set("dd-dt-li",url);
		window.location.href="${ctx}"+url;
	}
</script>
</html>