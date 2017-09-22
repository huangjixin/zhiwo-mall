<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>智惠多商品云购.个人中心</title>
<%@ include file="/WEB-INF/member-include/css.jsp"%>
<%@ include file="/WEB-INF/member-include/js.jsp"%>
<link href="${ctx}/css/zhihuiduo.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div
		style="height: 110px; background-color: #E02E24; color: #ffffff; padding-left: 10px;">
		<div style="height: 1px;"></div>
		<div class="media">
        	<shiro:user>
        	<div class="pull-right">
            	<span class="label label-warning" style="padding-top:5px;"  onClick="logout()">退出</span>
            </div>
            </shiro:user>
			<div class="media-left" href="#">
				<img id="wechatIcon" class="media-object img-circle" <c:if test="${member!=null}"><c:if test="${member.icon!=null}">src="${ctx}/${member.icon}"</c:if></c:if> 
					style="width: 80px;">
			</div>
			<div class="media-body">
				<div style="height: 25px;"></div>
				<h4 class="media-heading" id="wechatName"><shiro:principal/></h4>
                <c:if test="${!empty member.openId}">
                已绑定微信
                </c:if>
			</div>
		</div>
	</div>
	<ul class="list-group">
    	<c:if test="${member!=null}">
		<li class="list-group-item">我的足迹<span class="badge"><i
				class="fa fa-angle-right" aria-hidden="true"></i></i></span><i
			class="fa fa-arrow-circle-right" aria-hidden="true"></i></li>
		<li class="list-group-item">我的抽奖<span class="badge"><i
				class="fa fa-angle-right" aria-hidden="true"></i></i></span><i
			class="fa fa-arrow-circle-right" aria-hidden="true"></i></li>
		<li class="list-group-item">我的优惠券<span class="badge"><i
				class="fa fa-angle-right" aria-hidden="true"></i></i></span><i
			class="fa fa-arrow-circle-right" aria-hidden="true"></i></li>
		<li class="list-group-item">我的拼团<span class="badge"><i
				class="fa fa-angle-right" aria-hidden="true"></i></i></span></li>
		<li class="list-group-item">我的竞猜<span class="badge"><i
				class="fa fa-angle-right" aria-hidden="true"></i></i></span></li>
		<a href="${ctx}/memberInfo/memberPlayAccount" class="list-group-item">我的竞猜豆账户<span
			class="badge"><i class="fa fa-angle-right" aria-hidden="true"></i></i></span></a>
		<a href="${ctx}/memberInfo/memberAccount" class="list-group-item">我的账户<span
			class="badge"><i class="fa fa-angle-right" aria-hidden="true"></i></i></span></a>
		<a href="${ctx}/memberInfo/memberAddress" class="list-group-item">收货地址<span
			class="badge"><i class="fa fa-angle-right" aria-hidden="true"></i></i></span></a>
		</c:if>
	</ul>
	<div style="height: 1px;">&nbsp;</div>

	<div style="width: 100%; text-align: center"
		onClick="javascript:history.back();">
		<label class="activeProperyValue">返回</label>
	</div>
	<%--<%@ include file="/WEB-INF/member-include/bottomIndex.jsp"%>--%>
     <script>
	 	function logout(){
			window.loacation.href='${ctx}/memberLogin/logout';
		}
		
		$(function() {
			$("img").lazyload({effect: "fadeIn"});
		});
	</script>
</body>
</html>