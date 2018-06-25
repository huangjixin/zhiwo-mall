<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 	<title>富卫运维大平台</title>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/style.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/font/iconfont.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/login.css">
</head>
<body>

<div class="wrapper wrap-login">
	<div class="login-box">
	  <form action="${ctx}/loginAccount" method="post">
		<h2><img src="${ctx}/resources/imgs/logo-white.png" alt="FWD">富卫渠道运维大平台</h2>
		<ul>
			<li class="ipt-icon">
				<i class="icon-user"></i>
				<input class="ipt-text" type="text" id="userName" name="accountName" placeholder="请输入工号">
			</li>
			<li class="ipt-icon">
				<i class="icon-lock"></i>
				<input class="ipt-text" type="password" id="password" name="password" placeholder="请输入密码">
			</li>
			<li class="login-btn">
				<button type="submit" id="dl" class="btn btn-submit">登 录</button>
			</li>
		</ul>
		</form>
	</div>
</div>

<script src="${ctx}/resources/js/common/jquery-3.2.1.min.js"></script>
<script src="${ctx}/resources/js/common/lj-alert.js"></script>
<script src="${ctx}/resources/js/homepage.js"></script>
<script src="${ctx}/resources/js/login.js"></script>
<script type="text/javascript"	src="${ctx}/resources/libs/layer/layer.js"></script>
<script>
  $(function(){
	var aa = '${name}';
	if(aa == "wrong"){
		layer.msg("用户名或密码错误");
	}
  })
</script>

</body>

</html>