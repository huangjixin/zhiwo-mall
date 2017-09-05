<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>智惠多商品云购.个人中心</title>
<%@ include file="/WEB-INF/member-include/css.jsp"%>
<%@ include file="/WEB-INF/member-include/js.jsp"%>
<style>
	body{
		font-size:1.5rem;
	}
</style>
</head>
<body>
	<div style="height:140px; background-color:#E02E24;color:#ffffff; padding-left:25px;">
    	<div style="height:16px;"></div>
    	<div class="media">
            <a class="media-left" href="#">
                <img id="wechatIcon" class="media-object img-circle" src="${ctx}/images/1671169078.jpg" style="width:80px;">
            </a>
            <div class="media-body">
            	<div style="height:25px;"></div>
                <h4 class="media-heading" id="wechatName">黄记新</h4>
                已绑定微信
            </div>
        </div>
    </div>
	<ul class="list-group">
		<li class="list-group-item">我的足迹<span class="badge"><i class="fa fa-angle-right" aria-hidden="true"></i></i></span><i class="fa fa-arrow-circle-right" aria-hidden="true"></i></li>
		<li class="list-group-item">我的抽奖<span class="badge"><i class="fa fa-angle-right" aria-hidden="true"></i></i></span><i class="fa fa-arrow-circle-right" aria-hidden="true"></i></li>
		<li class="list-group-item">我的优惠券<span class="badge"><i class="fa fa-angle-right" aria-hidden="true"></i></i></span><i class="fa fa-arrow-circle-right" aria-hidden="true"></i></li>
		<li class="list-group-item">我的拼团<span class="badge"><i class="fa fa-angle-right" aria-hidden="true"></i></i></span></li>
		<li class="list-group-item">我的竞猜<span class="badge"><i class="fa fa-angle-right" aria-hidden="true"></i></i></span></li>
		<a href="${ctx}/memberInfo/memberPlayAccount" class="list-group-item">我的竞猜豆账户<span class="badge"><i class="fa fa-angle-right" aria-hidden="true"></i></i></span></a>
		<a href="${ctx}/memberInfo/memberAccount" class="list-group-item">我的账户<span class="badge"><i class="fa fa-angle-right" aria-hidden="true"></i></i></span></a>
		<a href="${ctx}/memberInfo/memberAddress" class="list-group-item">收货地址<span class="badge"><i class="fa fa-angle-right" aria-hidden="true"></i></i></span></a>
		
	</ul>
	<%@ include file="/WEB-INF/member-include/bottomIndex.jsp"%>
</body>
</html>