<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>智惠多商品云购.会员账户明细</title>
<%@ include file="/WEB-INF/member-include/css.jsp"%>
<%@ include file="/WEB-INF/member-include/js.jsp"%>
</head>
<body>
	<div
		style="height: 110px; background-color: #E02E24; color: #ffffff; padding-left: 10px;">
		<div style="height: 1px;"></div>
		<div class="media">
        	<shiro:user>
			<div class="media-left" href="#">
				<img id="wechatIcon" class="media-object img-circle" <c:if test="${member!=null}"><c:if test="${member.icon!=null}">src="${ctx}/${member.icon}"</c:if></c:if> 
					style="width: 80px;">
			</div>
			<div class="media-body">
				<div style="height: 25px;"></div>
				<h4 class="media-heading" id="wechatName"><shiro:principal/></h4>
                	余额: ${memberPlayAccount.zhihuidouCount} 智惠豆
			</div>
            </shiro:user>
		</div>
	</div>
</body>
</html>
