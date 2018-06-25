<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 	<title>富卫运维大平台</title>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/style.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/font/iconfont.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/page.css">
</head>
<body>
<!-- S Wrapper -->
<div class="wrapper">
	
	<!-- S Homepage Banner -->
	<div class="hp-banner"><!--<img src="../imgs/banner-1366x260.jpg" alt="">--></div>
	<!-- E Homepage Banner -->
	
	<!-- S Homepage User -->
	<div class="hp-user">
		<strong><i class="icon-user color-active"></i></strong>
		<p>你好！&nbsp;<span><shiro:principal property="accountName" /></span></p>
	</div>
	<!-- E Homepage User -->
	
	<!-- S Homepage Import -->
	<div class="hp-import">
		<p><span>请选择你要进入的系统</span></p>
		<!-- <p><button onclick="tuichu()">退出</button></p> -->
		<ul class="clearfix">
			<li class="hp-import-1"><a href="${ctx}/main/system?name=productCenter" title="产品中心"><i class="icon-product"></i><strong>产品中心</strong></a></li>
			<li class="hp-import-2"><a href="${ctx}/main/system?name=elearning" title="学习平台"><i class="icon-book"></i><strong>学习平台</strong></a></li>
			<li class="hp-import-3"><a href="${ctx}/main/system?name=erecruitment" title="招募系统"><i class="icon-vip"></i><strong>招募系统</strong></a></li>
		</ul>
	</div>
	<!-- E Homepage Import -->

</div>
<!-- E Wrapper -->

<script src="${ctx}/resources/js/common/jquery-3.2.1.min.js"></script>
<script src="${ctx}/resources/js/common/lj-alert.js"></script>
<script src="${ctx}/resources/js/homepage.js"></script>
<script>
  function tuichu(){
	  location.href="${ctx}/tuichu";
  }
</script>
</body>

</html>