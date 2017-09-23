<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>智惠多商品云购.商品详情</title>
<%@ include file="/WEB-INF/member-include/css.jsp"%>
<%@ include file="/WEB-INF/member-include/js.jsp"%>
<link href="${ctx}/css/bootstrap-spinner.css" rel="stylesheet" type="text/css">
<link href="${ctx}/css/zhihuiduo.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/jquery.spinner.min.js"></script>
<script type="text/javascript" src="${ctx}/js/zhihuiduo/goodsDetail.js"></script>
</head>
<body>
	<!--<input id="gourpSalePrice" name="gourpSalePrice" type="hidden"
		value="${product.gourpSalePrice }">
	<input id="independentPrice" name="independentPrice" type="hidden"
		value="${product.independentPrice }">
	<c:forEach var="packagePrice" items="${packagePrices}"
		varStatus="packagePriceStatus">
		<input id="${packagePrice.propertyValueId}_GroupInput"
			value="${packagePrice.gourpPrice}" type="hidden">
		<input id="${packagePrice.propertyValueId}_IndependInput"
			value="${packagePrice.independentPrice}" type="hidden">
	</c:forEach>-->
	<div class="thumbnail">
		<div class="swiper-container">
			<div class="swiper-wrapper" id="swiperWrapper">
				<!-- 轮播图设置
				<c:forEach var="prImage" items="${swiperImages}">
					<div class="swiper-slide">
						<img class="img-responsive" src="${ctx}/${prImage.url}" >
					</div>
				</c:forEach> -->
			</div>
		</div>
		<div class="caption">
			<br>

			<div class="pull-left">
				<label style="color: red; font-size: 2rem;"><i
					class="fa fa-jpy" id="gSalePriceLabel"></i></label>&nbsp;&nbsp;<label
					style="color: gray; font-size: 1.5rem;"><i id="marketPriceLabel"
					class="fa fa-jpy"></i><span style="text-decoration: line-through"></span></label>
			</div>
			<div class="pull-right">
				<label style="color: gray; font-size: 1.5rem;">已拼<span id="soldQuantitySpan"></span>件·2人拼单</label>
			</div>

			<div class="clearfix"></div>
			<div style="text-align: left; font-size: 1.5rem; font-weight: bold;" id="productName">${product.name}</div>
			<div>&nbsp;</div>
			<div style="text-align: left; font-size: 1.4rem; color: #666;" id="productDescription">${product.description}</div>
			<br>
			<p style="text-align: left; font-size: 1.4rem; padding:4px; background-color:#FAFAFA">
		        <span><i class="fa fa-check-square-o" aria-hidden="true" style="color:red;"></i>&nbsp;假一赔十</span>&nbsp;&nbsp;<span><i class="fa fa-check-square-o" aria-hidden="true" style="color:red;"></i>&nbsp;7天退换</span>&nbsp;&nbsp;<span><i class="fa fa-check-square-o" aria-hidden="true" style="color:red;"></i>&nbsp;48小时发货</span>
		    </p>
			<hr class="hr1" />
		</div>
	</div>
    
	<div class="thumbnail">
		<div class="caption">
        	<div class="page-header" style="padding-top:0px; margin-top:5px;" id="groupPurcseHeader">
                <h4>看看谁在开团</h4>
            </div>
           <!-- <div class="media">
            <div class="pull-right" style="padding-top:7px;">
            	<button type="button" class="btn btn-danger">去参团</button>
			</div>
            <div class="media-left">
            		<img class="img-circle" src="${ctx}/images/busy.gif"  data-original="${ctx}/${shop.icon}"  style="width:50px;">
                   <img class="img-circle" src="${ctx}/${shop.icon}" style="width:50px;">
                </div>
				<div class="media-body">
					<h5 class="media-heading" style="padding-top: 6px;">人生如戏，戏如人生</h5>
                    <span style="color: gray; font-size: 1.4rem;">还差一人，剩余2小时</span>
                   
				</div>
                
			</div>	
            <div class="media">
            <div class="pull-right" style="padding-top:7px;" >
            	<button type="button" class="btn btn-danger">去参团</button>
			</div>
            <div class="media-left">
                    <img class="img-circle" src="${ctx}/${shop.icon}" style="width:50px;">
                </div>
				<div class="media-body">
					<h5 class="media-heading" style="padding-top: 6px;">人生如戏，戏如人生</h5>
                    <span style="color: gray; font-size: 1.4rem;">还差一人，剩余2小时</span>
				</div>
			</div>	-->
			<!--<c:forEach var="groupPurcse" items="${groupPurcses}">groupPurcseHeader
            <a href="${ctx}/memberGroup?goodsId=${groupPurcse.productId}&groupPurcseId=${groupPurcse.id}">
			<div class="media">
             <div class="pull-right" style="padding-top:7px;">
            	<button type="button" class="btn btn-danger">去参团</button>
			</div>
            <div class="media-left">
                    <img class="img-circle" src="${ctx}/images/busy.gif" data-original="${ctx}/${groupPurcse.memberIcon}">
                </div>
				<div class="media-body">
                	<h5 class="media-heading" style="padding-top: 6px;">${groupPurcse.memberName}</h5>
                    <span style="color: gray; font-size: 1.4rem;">还差1人，剩余2小时</span>
				</div>
			</div>		
            </a>
			</c:forEach>-->
		</div>
	</div>
	
	<div class="thumbnail">
		<div class="caption">
			<div class="media">
				<div class="media-body">
					<label>&nbsp;</label>
					<h4 class="media-heading">商品评价</h4>
					<hr class="hr1" />

				</div>
			</div>
		</div>
	</div>

	<a href="${ctx}/memberShop/${product.shopId}">
	<div class="thumbnail">
		<div class="caption">
			<div class="pull-left">
				<div class="media" style="padding-top: 4px; padding-bottom: 8px;">
					<div class="media-left">
						<img class="media-object" src="${shop.icon}"
							style="width: 50px; height: 50px; border-radius: 4px;">
					</div>
					<div class="media-body">
						<h5 class="media-heading" style="padding-top: 6px;" id="shopHead"></h5>
						<span style="color: gray; font-size: 1.4rem;">商品数量<span id="goodsCount"></span>.已团${shopProductsCount}件</span>
					</div>
					<div class="media-right" style="padding-top: 10px;">
						<img class="media-object"
                        	 src="${ctx}/images/busy.gif" data-original="${ctx}/images/goodsDetail/gotoShop.png"
							 style="width: 75px; height: 25px;">
					</div>
				</div>
			</div>

			<div class="pull-right"></div>
			<div class="clearfix"></div>
		</div>
	</div>
	</a>
	<div class="thumbnail">
		<div class="caption">
			<div class="media">
				<div class="media-body">
					<label>&nbsp;</label>
					<h4 class="media-heading">商品详情</h4>
					<hr class="hr1" />
                    <div class="media-body" id="proContent">
                    	<!--${product.content}-->
                    </div>
					
				</div>
			</div>
		</div>
	</div>

	<div class="thumbnail">
		<div class="caption">
			<div style="text-align: center">
				<a data-toggle="collapse" style="text-decoration: none;"
					href="#collapseExample" aria-expanded="false"
					aria-controls="collapseExample"> 点击查看价格说明 </a>
			</div>
			<div class="collapse" id="collapseExample"
				style="margin-left: 10px; margin-right: 10px;">
				<p>
					<label style="color: #666;">单独购买价：</label>是您单独购买商品的价格
				</p>
				<p>
					<label style="color: #666;">一键拼单价：</label>是您拼单购买商品的价格
				</p>
				<p>
					<label style="color: #666;">划线价：</label>可能是商品的专柜价、吊牌价、零售价、指导价或该商品曾经展示过的销售价等，并非《价格法》、《禁止价格欺诈行为的规定》规定的"原价"，仅供您参考
				</p>
				<p>特别提示：实际的成交价格可能因您使用优惠券等发生变化，最终以订单结算页的价格为准。若商家单独对价格进行说明的，以商家的表述为准</p>

			</div>
		</div>
	</div>

	<div style="height: 75px; text-align: center">已经到底部了</div>


    <!--商品属性弹窗-->
    <div class="modal fade" id="prriceModal" tabindex="-2"
		role="dialog" aria-labelledby="swiperImageModalLabel"
		aria-hidden="true">
		<div class="modal-dialog"
			style="width: 100%; margin-left: 0; margin-right: 0; mim-height: 400px;">
			<!--width:100%; margin-left:0;margin-right:0;-->
			<div class="modal-content">
				<div class="pull-right">
					<button type="button" class="close" data-dismiss="swiperImageModal"
						aria-hidden="true">&times;&nbsp;</button>
				</div>
				<div class="media" style="position: absolute; top: -30px; left:10px;">
					<div class="media-left"> <img id="propertyValueImg"
						class="media-object img-rounded"
						src="${ctx}/images/goods/user_13926205227/product_12365/02f8bc94495c5f6dde5e20f6e3e206c4.jpeg@750w_1l_50Q"
						width="100px;" height="100px;" style="border:1px solid red">
					</div>
					<div class="media-body">
						<div style="height: 55px;"></div>
						<h4 class="media-heading" style="color: red; font-size: 1.8rem;">
							<i class="fa fa-jpy"></i><label id="priceLabel"></label>
						</h4>
						<label style="font-size: 1.2rem;" id="propertyValueLabel">请选择属性</label>
					</div>
				</div>
                <div style="height: 100px;"></div>
				<div id="packagePriceProDiv" style="min-height: 200px; max-height:300px; overflow:hidden;">
					
					<div class="form-group" id="numCountFormGroup">
						<div class="col-sm-9">
							<label class="col-sm-1 control-label" style="font-size: 1.2rem;">数量</label>
							<div class="input-group number-spinner col-md-4">
								<span class="input-group-btn" style="color: white;"> <a
									class="btn btn-danger" data-dir="dwn"><span
										class="glyphicon glyphicon-minus"></span></a>
								</span> <input type="text" disabled name="numberCount" id="numberCount"
									style="height: 28px;" class="form-control text-center"
									value="1" max=9999 min=1> <span class="input-group-btn"
									style="color: white;"> <a class="btn btn-info"
									data-dir="up"><span class="glyphicon glyphicon-plus"></span></a>
								</span>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer"></div>
				<form action="${ctx}/memberOrder/checkOut" method="post"
					onsubmit="return checkOut();">
					<input id="packagePriceId" name="packagePriceId" type="hidden">
					<input id="dealPrice" name="dealPrice" type="hidden"> <input
						id="buyNum" name="buyNum" type="hidden"> <input
						id="shopId" name="shopId" value="${product.shopId}" type="hidden">
					<input id="goodsId" name="goodsId" value="${product.id}"
						type="hidden"> <input id="proValues" name="proValues"
						type="hidden"><input id="mode" name="mode"
						type="hidden">
                     <shiro:authenticated>
							<button type="submit" class="btn btn-danger"
						style="width: 100%; margin: 0; position: fixed; bottom: 0; left: 0; right: 0; border-radius: 0px;">
						确定</button>
						</shiro:authenticated>
						<shiro:notAuthenticated>
							<a href="${ctx}/memberLogin"><button type="button" class="btn btn-danger"
						style="width: 100%; margin: 0; position: fixed; bottom: 0; left: 0; right: 0; border-radius: 0px;">
						去登录</button></a>
						</shiro:notAuthenticated>
					
				</form>
			</div>
		</div>
	</div>
    
    <%@ include file="/WEB-INF/member-include/fade-ui.jsp"%>
	<%@ include file="/WEB-INF/member-include/bottomMenu.jsp"%>
	<script>
		var obj = ${rawData};
		
		
		
		<%--<%@ include file="/WEB-INF/member-include/fade-js.jsp"%>--%>

		var swiper = new Swiper('.swiper-container');
	</script>
</body>
</html>
