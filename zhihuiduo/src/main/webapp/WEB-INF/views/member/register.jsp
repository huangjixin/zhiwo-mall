<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>智惠多商品云购.会员登录</title>
<%@ include file="/WEB-INF/member-include/css.jsp"%>
<%@ include file="/WEB-INF/member-include/js.jsp"%>
<link href="${ctx}/css/signin.css" rel="stylesheet">
<%@ include file="/WEB-INF/include/easyui-js.jsp"%>
</head>
<body>
<div class="signin">
	<div class="signin-head"><img src="${ctx}/images/test/head_120.png" alt="" class="img-circle"></div>
	<form class="form-signin" role="form" action="${ctx}/memberLogin/login?fromURL=${fromURL}" method="post">
		<input id="username" name="username" type="text" class="form-control" placeholder="用户名" required autofocus />
		<input id="password" name="password" type="password" class="form-control" placeholder="密码" required />
		<button class="btn btn-default btn-warning " type="submit">登录</button>
		<a href="${ctx}/memberLogin/register"><button class="btn btn-default btn-warning " type="button">注册</button></a>
<!-- 		<label class="checkbox"> -->
<!-- 			<input type="checkbox" value="rememberMe"> 记住我 -->
<!-- 		</label> -->
		<label>${message}</label>
	</form>
</div>
</body>
</html>
