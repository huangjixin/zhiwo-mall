<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>智惠多商品云购.会员登录</title>
<%@ include file="/WEB-INF/member-include/css.jsp"%>
<%@ include file="/WEB-INF/member-include/js.jsp"%>
<!--<link href="${ctx}/css/signin.css" rel="stylesheet">-->
<link href="${ctx}/css/zhihuiduo.css" rel="stylesheet" type="text/css" />
<%@ include file="/WEB-INF/include/easyui-js.jsp"%>
</head>
<body>
<div class="col-xs-12">
	<form id="form" class="form-horizontal" role="form" style="padding:5px;"  action="${ctx}/memberLogin/loginByMobilPhone?fromURL=${fromURL}" method="post">
 <div class="form-group" style="text-align:center; background-color:#89D5D5; padding-top:2px; padding-bottom:2px; background-image:url(../../../images/loginBackgroudImg.png);">
	<img src="${ctx}/images/test/head_120.png" alt="" class="img-circle">
</div>
 <div class="form-group input-group margin-bottom-sm">
  <span class="input-group-addon"><i class="fa fa-mobile fa-fw"></i></span>
  <input class="form-control" id="mobilPhone" name="mobilPhone"  style=";border-radius:0px;" type="text" placeholder="请输入电话">
</div>
<div class="form-group input-group">
  <span class="input-group-addon"><i class="fa fa-file-code-o fa-fw"></i></span>
  <input class="form-control" id="validateCode" name="validateCode" style=";border-radius:0px;" type="text" placeholder="请输入验证码">
</div>
<div class="form-group">
	<button class="btn btn-warning " type="button" style="border-radius: 0px;" onClick="getValidateCode();">获取验证码</button>
</div>
  <div class="form-group">
    <i class="fa fa-check" aria-hidden="true" style="color:red;"></i><span>我已经阅读并同意</span><a href="${ctx}/police.html"><span style="color:red;" onClick="redirectToPolice();">智惠多服务协议隐私政策</span></a>
  </div>
  <div class="form-group">
    <button type="submit" class="btn btn-danger" style="width:100%;border-radius: 0px; height:40px;">登录</button>
  </div>
</form>
</div>

<!--<div class="signin">
       <div class="signin-head"><img src="${ctx}/images/test/head_120.png" alt="" class="img-circle"></div>
	<form class="form-signin" role="form" action="${ctx}/memberLogin/loginByMobilPhone?fromURL=${fromURL}" method="post">
		<input id="mobilPhone" name="mobilPhone" type="text" class="form-control" placeholder="电话" required autofocus autocomplete= "off"/>
		<input id="validateCode" name="validateCode" type="text" class="form-control" placeholder="验证码" required autocomplete= "off"/>
		<button class="btn btn-lg btn-warning " type="submit">登录</button>
 		<label class="checkbox">
 			<input type="checkbox" value="rememberMe"> 记住我
		</label>
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
    </div>-->
	
<script>
	function getValidateCode(){
			var result = Validate.cellPhone();
			if(!result){
				return;
			}
	}
	
	$(function () {
        formValidator();
    });
	//form验证规则
	function formValidator(){
		$('#form').bootstrapValidator({
　　　　　　　　message: 'This value is not valid',
            　feedbackIcons: {
                　　　　　　　　valid: 'glyphicon glyphicon-ok',
                　　　　　　　　invalid: 'glyphicon glyphicon-remove',
                　　　　　　　　validating: 'glyphicon glyphicon-refresh'
            　　　　　　　　   },
            fields: {
            
                mobilPhone: {
                    validators: {
                        notEmpty: {
                            message: '电话不能为空'
                        },stringLength: {
                         min: 11,
                         max: 11,
                         message: '请输入11位手机号码'
                     },
                     regexp: {
                         regexp: /^1[3|5|8]{1}[0-9]{9}$/,
                         message: '请输入正确的手机号码'
                     }
                    }
                },
                validateCode: {
                    validators: {
                        notEmpty: {
                            message: '验证码不能为空'
                        },stringLength: {
                            min: 6,
                            max: 6,
                            message: '只能输入6位数验证码'
                        }
                    }
                }
            }
        });
	}
	
	var Validate = {
            isMobile: function (value) {
                var validateReg = /^((\+?86)|(\(\+86\)))?1\d{10}$/;
                return validateReg.test(value);
            },
            cellPhone: function () {
                var cellPhoneNumber = $("#mobilPhone").val();
                if (!Validate.isMobile(cellPhoneNumber)) {
                    alert("手机号码格式不正确");
                    return false;
                } else {
                    return true;
                }
            }
        }
</script>
</body>
</html>
