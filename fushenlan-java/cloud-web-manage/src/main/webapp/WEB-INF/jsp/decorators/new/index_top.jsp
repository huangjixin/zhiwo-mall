<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
</head>
<body>
	<div class="layer-header">
        <ul class="layer-tab float-wrapper">
            <li class="layer-tab-item item-active"><a href="/main/system?name=elearning">学习平台</a></li>
            <li class="layer-tab-item">产品中心</li>
            <li class="layer-tab-item">在线投保</li>
            <li class="layer-tab-item">招募</li>
            <li class="layer-tab-item">增员</li>
            <li class="layer-tab-item">IRIS</li>
        </ul>
        <div class="all-service">
            <span class="iconfont icon-classify"></span>
            <span>所有服务</span>
        </div>
    </div>
</body>
<script>
   function logout(){
	   $.session.remove("dd-dt-li");
	   location.href="${ctx}/logout";
   }
</script>
</html>