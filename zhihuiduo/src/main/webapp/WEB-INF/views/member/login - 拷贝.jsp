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
<link href="${ctx}/css/zhihuiduo.css" rel="stylesheet" type="text/css" />
<%@ include file="/WEB-INF/include/easyui-js.jsp"%>
</head>
<body>
<div class="col-xs-3 col-sm-3">
         
</div>
<div class="col-xs-6 col-sm-6">

</div>
<div class="col-xs-3 col-sm-3">
           
</div>
<div class="signin">
       <div class="signin-head"><img src="${ctx}/images/test/head_120.png" alt="" class="img-circle"></div>
	<form class="form-signin" role="form" action="${ctx}/memberLogin/loginByMobilPhone?fromURL=${fromURL}" method="post">
		<input id="mobilPhone" name="mobilPhone" type="text" class="form-control" placeholder="电话" required autofocus autocomplete= "off"/>
		<input id="validateCode" name="validateCode" type="text" class="form-control" placeholder="验证码" required autocomplete= "off"/>
		<button class="btn btn-lg btn-warning " type="submit">登录</button>
<!-- 		<label class="checkbox"> -->
<!-- 			<input type="checkbox" value="rememberMe"> 记住我 -->
<!-- 		</label> -->
		<label>${message}</label>
        
        <input id="subscribe" name="age" value="${user.subscribe}" type="hidden"/>
        <input id="openid" name="openId" value="${user.openid}" type="hidden"/>
        <input id="nickname" name="nickname" value="${user.nickname}" type="hidden"/>
        <input id="nickname_emoji" name="nickname_emoji" value="${user.nickname_emoji}" type="hidden"/>
        <input id="sex" name="sex" value="${user.sex}" type="hidden"/>
        <input id="language" name="language" value="${user.language}" type="hidden"/>
        <input id="headimgurl" name="icon" value="${user.headimgurl}" type="hidden"/>
        <input id="unionid" name="orgId" value="${user.unionid}" type="hidden"/>
        <input id="remark" name="description" value="${user.remark}" type="hidden"/>
        <input id="groupid" name="memberGroupId" value="${user.groupid}" type="hidden"/>
	</form>
    </div>
	

</body>
</html>
