<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>智惠多商品云购.会员登录</title>
<%@ include file="/WEB-INF/member-include/css.jsp"%>
<%@ include file="/WEB-INF/member-include/js.jsp"%>
</head>
<body>
	<form class="form-horizontal" role="form" action="${ctx}/memberLogin/login" method="post">
  <div class="form-group">
    <label for="username" class="col-sm-2 control-label">账号</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="username" name="username" placeholder="请输入用户名/手机号">
    </div>
  </div>
  <div class="form-group">
    <label for="password" class="col-sm-2 control-label">密码</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="password" name="password" placeholder="请输入密码">
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <div class="checkbox">
        <label>
          <input type="checkbox">请记住我
        </label>
      </div>
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <button type="submit" class="btn btn-default">登录</button>
    </div>
  </div>
</form>
</body>
</html>
