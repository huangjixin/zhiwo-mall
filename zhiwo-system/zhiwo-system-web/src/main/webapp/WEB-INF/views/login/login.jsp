<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>
<html>
<head>
<title>登录</title>
<%@ include file="/WEB-INF/include/easyui-css.jsp"%>
<link href="${ctx}/css/signin.css" rel="stylesheet">
<%@ include file="/WEB-INF/include/easyui-js.jsp"%>
</head>
<body>
<div class="signin">
	<div class="signin-head"><img src="images/test/head_120.png" alt="" class="img-circle"></div>
	<form class="form-signin" role="form" action="${ctx}/login" method="post">
		<input id="username" type="text" class="form-control" placeholder="用户名" required autofocus />
		<input id="password" type="password" class="form-control" placeholder="密码" required />
		<button class="btn btn-lg btn-warning btn-block" type="submit">登录</button>
		<label class="checkbox">
			<input type="checkbox" value="rememberMe"> 记住我
		</label>
	</form>
</div>
<!--username: <input type="text" id="username"><br><br>  
password: <input type="password" id="password"><br><br>
<button id="loginbtn">登录</button>-->
</body>
<script type="text/javascript">
$('#loginbtn').click(function() {
    var param = {
        username : $("#username").val(),
        password : $("#password").val()
    };
    $.ajax({ 
        type: "post", 
        url: "<%=request.getContextPath()%>" + "/login", 
        data: param, 
        dataType: "json", 
        success: function(data) { 
            if(data.success == false){
                alert(data.errorMsg);
            }else{
            	alert("登录成功");
                //登录成功
                //window.location.href = "<%=request.getContextPath()%>" +  "/loginsuccess.jhtml";
            }
        },
        error: function(data) { 
            alert("调用失败...."); 
        }
    });
});
</script>
</html>
