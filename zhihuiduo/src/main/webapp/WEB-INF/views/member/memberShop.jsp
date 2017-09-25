<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>智惠多商品云购.店铺.</title>
<%@ include file="/WEB-INF/member-include/css.jsp"%>
<%@ include file="/WEB-INF/member-include/js.jsp"%>
<link href="${ctx}/css/zhihuiduo.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/zhihuiduo/memberShop.js"></script>
<style>
body {
	background-color: #ffffff;
}

.thumbnail {
	padding: 0px;
	margin-bottom: 5px;
	line-height: 1.42857143;
	border: 0px solid #ddd;
	border-radius: 0px;
}

.label label-info {
	padding-top: 5px;
}

.form-group {
	margin: 3px;
}

.col-xs-6 {
	padding-left: 2px;
	padding-right: 2px;
}
</style>

</head>
<body>
	<div class="page-header"
		style="text-align: center; font-size: 2rem; position:fixed; background-color:#fff; left:0;right:0; top:0; padding-top:10px; margin-top:0;">
		<b id="shopTitle"></b>&nbsp;&nbsp;<small  style="color:red;" onClick="javascript:history.back();">返回</small>
	</div>
    <div style="height:45px;"></div>
	<div class="thumbnail">
		<div class="caption">
			<div class="media" style="padding-top: 4px; padding-bottom: 8px;">
				<div class="media-left">
					<img id="shopIcon" class="media-object"
						style="width: 60px; height: 60px; border-radius: 4px;">
				</div>
				<div class="media-body">
					<h4 class="media-heading" style="padding-top: 6px;" id="shopName">${shop.name}</h4>
					<span style="color: gray; font-size: 1.4rem;">商品数量${productsCount}</span>
				</div>
				<div class="media-right" style="padding-top: 10px;">
					<i class="fa fa-wechat fa-lg" aria-hidden="true"
						style="font-size: 3rem; padding-top: 10px; color:green;"></i>
				</div>

			</div>
			<div>
				<p id="shopDescription">${shop.description}</p>
			</div>
		</div>
	</div>


	<div class="thumbnail" id="shopGoods">
		<div class="caption">
			<h4>全部商品</h4>
		</div>
	</div>

	<c:forEach var="prod" items="${products}">
    <a href="${ctx}/goodsDetail?goodsId=${prod.id}">
		<div class="col-xs-6" onClick="gotoGoodsDetail();"><div class="thumbnail"><img class="img-responsive" src="${ctx}/images/busy.gif" data-original="${ctx}/${prod.icon}"><div class="caption" style="font-size: 1.3rem;"><h6>${prod.name}</h6><div class="pull-left"><label style="color: red;"><i class="fa fa-jpy"></i>${prod.gourpSalePrice}</label> <span>已拼1222件</span></div><div class="clearfix"></div></div></div></div></a>
	</c:forEach>
	
    <%@ include file="/WEB-INF/member-include/fade-ui.jsp"%>
	<script>
		var obj = ${rawData};
	</script>

</body>
</html>
