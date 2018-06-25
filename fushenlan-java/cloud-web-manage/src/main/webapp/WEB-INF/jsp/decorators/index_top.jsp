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
	<div class="wrap-header">
		<div class="logo"><a href="${ctx}/main"><img src="${ctx}/resources/imgs/logo-white.png" alt="FWd">大后台管理系统</a></div>
		<!-- / Logo -->
		<div class="user">
			<strong><i class="icon-yonghu"></i>你好！&nbsp;<span><shiro:principal property="accountName" /></span></strong>
			<button  onclick="logout()" class="btn-signout" type="button" title="退出"><i class="icon-fanhuiyou"></i>退出</button>
		</div>
		<!-- / User -->
	</div>
</body>
<script>
   function logout(){
	   $.session.remove("dd-dt-li");
	   location.href="${ctx}/logout";
   }
</script>
</html>