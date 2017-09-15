<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>智惠多商品云购.商品详情</title>
<link href="${ctx}/css/bootstrap-spinner.css" rel="stylesheet"
	type="text/css">
<%@ include file="/WEB-INF/member-include/css.jsp"%>
<%@ include file="/WEB-INF/member-include/js.jsp"%>
<script type="text/javascript" src="${ctx}/js/jquery.spinner.min.js"></script>

<style>
body {
	background-color: #F2F2F2;
	font-size: 1.5rem;
}

.container-fluid {
	font-size: 1.5rem;
	/* 	background: #ccc; */
}

.row {
	/*padding-left: 8px;
	padding-right: 8px;*/
	
}

.thumbnail {
	padding: 0px;
	margin-bottom: 5px;
	line-height: 1.42857143;
	border: 0px solid #ddd;
	border-radius: 0px;
}

.swiper-container {
	text-align: center;
	width: 100%;
	margin: 0px auto;
}

.swiper-slide {
	text-align: center;
	/*font-size: 18px;*/
	font-size: 1.6rem;
	/* Center slide text vertically */
	display: -webkit-box;
	display: -ms-flexbox;
	display: -webkit-flex;
	display: flex;
	-webkit-box-pack: center;
	-ms-flex-pack: center;
	-webkit-justify-content: center;
	justify-content: center;
	-webkit-box-align: center;
	-ms-flex-align: center;
	-webkit-align-items: center;
	align-items: center;
}

.hr1 {
	height: 1px;
	border: none;
	border-top: 1px solid #F0F0F0;
}

.btn-primary:active {
	background-color: red !important;
}

.activeProperyValue {
	padding-left: 12px;
	padding-right: 12px;
	padding-top: 6px;
	padding-bottom: 6px;
	background-color: red;
	font-size: 1.2rem;
	color: white;
	border-radius: 4px;
	font-weight: normal;
}

.ProperyValue {
	padding-left: 12px;
	padding-right: 12px;
	padding-top: 6px;
	padding-bottom: 6px;
	background-color: #F5F5F5;
	font-size: 1.2rem;
	color: #878787;
	border-radius: 4px;
	font-weight: normal;
}

.modal {
	position: fixed;
	right: 0;
	bottom: 0;
	left: 0;
}

.modal-dialog {
	position: fixed;
	width: auto;
	margin: 0px;
	bottom: 0;
}
</style>
</head>
<body>
	<input id="gourpSalePrice" name="gourpSalePrice" type="hidden"
		value="${product.gourpSalePrice }">
	<input id="independentPrice" name="independentPrice" type="hidden"
		value="${product.independentPrice }">
	<c:forEach var="packagePrice" items="${packagePrices}"
		varStatus="packagePriceStatus">
		<input id="${packagePrice.propertyValueId}_GroupInput"
			value="${packagePrice.gourpPrice}" type="hidden">
		<input id="${packagePrice.propertyValueId}_IndependInput"
			value="${packagePrice.independentPrice}" type="hidden">
	</c:forEach>
	<div class="thumbnail">
		<div class="swiper-container">
			<div class="swiper-wrapper">
				<!-- 轮播图设置 -->
				<c:forEach var="prImage" items="${swiperImages}">
					<div class="swiper-slide">
						<img class="img-responsive" src="${ctx}/images/busy.gif" data-original="${ctx}/${prImage.url}">
					</div>
				</c:forEach>
			</div>
		</div>
		<div class="caption">
			<br>

			<div class="pull-left">
				<label style="color: red; font-size: 2rem;"><i
					class="fa fa-jpy"></i>${product.gourpSalePrice}</label>&nbsp;&nbsp;<label
					style="color: gray; font-size: 1.5rem;"><i
					class="fa fa-jpy"></i><span style="text-decoration: line-through">${product.marketPrice}</span></label>
			</div>
			<div class="pull-right">
				<label style="color: gray; font-size: 1.5rem;">已拼72734件·2人拼单</label>
			</div>

			<div class="clearfix"></div>
			<div style="text-align: left; font-size: 1.5rem; font-weight: bold;">${product.name}</div>
			<div>&nbsp;</div>
			<div style="text-align: left; font-size: 1.4rem; color: #666;">${product.description}</div>
			<br>
			<p style="text-align: left; font-size: 1.4rem; padding:4px; background-color:#FAFAFA">
		        <small><i class="fa fa-check-square-o" aria-hidden="true" style="color:red;"></i>&nbsp;假一赔十</small>&nbsp;&nbsp;<small><i class="fa fa-check-square-o" aria-hidden="true" style="color:red;"></i>&nbsp;7天退换</small>&nbsp;&nbsp;<small><i class="fa fa-check-square-o" aria-hidden="true" style="color:red;"></i>&nbsp;48小时发货</small>
		    </p>
			<hr class="hr1" />
		</div>
	</div>
    
	<div class="thumbnail">
		<div class="caption">
        	<div class="page-header" style="padding-top:0px; margin-top:5px;">
                <h4>看看谁在开团</h4>
            </div>
            <div class="media">
            <div class="pull-right" style="padding-top:7px;">
            	<button type="button" class="btn btn-danger">去参团</button>
			</div>
            <div class="media-left">
            		<img class="img-circle" src="${ctx}/images/busy.gif"  data-original="${ctx}/${shop.icon}"  style="width:50px;">
                    <!--<img class="img-circle" src="${ctx}/${shop.icon}" style="width:50px;">-->
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
			</div>	
			<c:forEach var="groupPurcse" items="${groupPurcses}">
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
			</c:forEach>
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
						<h5 class="media-heading" style="padding-top: 6px;">${shop.name}</h5>
						<span style="color: gray; font-size: 1.4rem;">商品数量${fn:length(goodsList)}.已团${shopProductsCount}件</span>
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
                    <div class="media-body">
                    	${product.content} 
                        <!--<img
						src="${ctx}/images/goods/user_13926205227/product_12365/02f8bc94495c5f6dde5e20f6e3e206c4.jpeg@750w_1l_50Q"
						class="img-responsive"> <img
						src="${ctx}/images/goods/user_13926205227/product_12365/0c4d438262012c7099a25f05958272ef.jpeg@750w_1l_50Q"
						class="img-responsive"> <img
						src="${ctx}/images/goods/user_13926205227/product_12365/583052abadbe0b8daf936fd447ebe31c.jpeg@750w_1l_50Q"
						class="img-responsive"> <img
						src="${ctx}/images/goods/user_13926205227/product_12365/b8863f414edab95ca9f41dc3a51d6176.jpeg@750w_1l_50Q"
						class="img-responsive">-->
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


	<!-- 轮播图设置模态框（Modal） -->
	<div class="modal fade" id="swiperImageModal" tabindex="-2"
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
				<div class="media" style="position: absolute; top: -50px;">
					<a class="media-left" href="#"> <img id="propertyValueImg"
						class="media-object img-rounded"
						src="${ctx}/images/goods/user_13926205227/product_12365/02f8bc94495c5f6dde5e20f6e3e206c4.jpeg@750w_1l_50Q"
						width="100px;" height="100px;">
					</a>
					<div class="media-body">
						<div style="height: 55px;"></div>
						<h4 class="media-heading" style="color: red; font-size: 1.8rem;">
							<i class="fa fa-jpy"></i><label id="priceLabel">${product.gourpSalePrice}</label>
						</h4>
						<label style="font-size: 1.2rem;" id="propertyValueLabel">请选择属性</label>
					</div>
				</div>
                <div style="height: 100px;"></div>
				<div style="min-height: 200px; max-height:300px; overflow:scroll;">
					
					<c:forEach var="property" items="${properties}">
						<c:set value="false" var="flag" />
						<c:forEach var="pValue" items="${propertyValues}">
							<c:if test="${property.id==pValue.propertyId}">
								<c:set value="true" var="flag" />
							</c:if>
						</c:forEach>
						<c:if test="${flag==true}">
							<div class="form-group">
								<div class="col-sm-9">
									<label for="propertyValue" class="col-sm-1 control-label"
										style="font-size: 1rem;">${property.name}</label>

									<c:forEach var="pValue" items="${propertyValues}"
										varStatus="status">
										<c:if test="${property.id==pValue.propertyId}">
											<label id="${pValue.id}" name="${property.code}"
												class="ProperyValue"
												onClick="settingProperyValueCla('${property.code}', '${pValue.id}')">${pValue.name}</label>
										</c:if>
									</c:forEach>
								</div>
							</div>
						</c:if>
					</c:forEach>
					<div class="form-group">
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
					<button type="submit" class="btn btn-danger"
						style="width: 100%; margin: 0; position: fixed; bottom: 0; left: 0; right: 0; border-radius: 0px;">
						确定</button>
				</form>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/member-include/bottomMenu.jsp"%>
	<script>
		var propertiesString = '${propertiesString}';
		var propertyValuesString = '${propertyValuesString}';

		//保存属性数组
		var propertyValueArray = [];
		var selectedPValue = [];
		//保存属性值数组
		var proArray = [];
		var mode = 'group';
		$()
				.ready(
						function() {
							//图片懒加载。
							$("img").lazyload({
								threshold : 200,
								placeholder : "${ctx}/images/heart.png",
								event : "click",
								effect : "fadeIn",	
							});
							
							
							if (propertiesString != '') {
								proArray = JSON.parse('${propertiesString}');
							}

							if (propertyValuesString != '') {
								propertyValueArray = JSON
										.parse('${propertyValuesString}');
							}

							// spinner(+-btn to change value) & total to parent input 
							$(document)
									.on(
											'click',
											'.number-spinner a',
											function() {
												var btn = $(this), input = btn
														.closest(
																'.number-spinner')
														.find('input'), total = $(
														'#passengers').val(), oldValue = input
														.val().trim();

												if (btn.attr('data-dir') == 'up') {
													if (oldValue < input
															.attr('max')) {
														oldValue++;
														total++;
													}
												} else {
													if (oldValue > input
															.attr('min')) {
														oldValue--;
														total--;
													}
												}
												input.val(oldValue);
											});
							$("#independentPriceLabel").html("${product.independentPrice}");
							$("#gourpSalePriceLabel").html("${product.gourpSalePrice}");
							$("#distributionValueLabel").html("${product.distributionValue}");
							
							$("#indexBtn").removeClass("menuItemActive");
							$("#indexBtn").addClass("menuItem");
							//跳转到首页
							$("#indexBtn").bind("click", function() {
								window.location = "${ctx}/mindex";
							});
							//单独开团
							$("#independBuyBtn")
									.bind(
											"click",
											function() {
												mode = 'independ';
												$("#swiperImageModal").modal(
														'show');
												$('#priceLabel').html("");
												for (var i = 0; i < proArray.length; i++) {
													$(
															"[name="
																	+ proArray[i].code
																	+ "]")
															.removeClass(
																	"activeProperyValue");
													$(
															"[name="
																	+ proArray[i].code
																	+ "]")
															.addClass(
																	"ProperyValue");
												}
												if (propertyValueArray.length == 0) {
													$('#priceLabel')
															.html(
																	$(
																			"#independentPrice")
																			.val());
												}

											});
							//拼团开团
							$("#groupBuyBtn")
									.bind(
											"click",
											function() {
												mode = 'group'
												$("#swiperImageModal").modal(
														'show');
												for (var i = 0; i < proArray.length; i++) {
													$(
															"[name="
																	+ proArray[i].code
																	+ "]")
															.removeClass(
																	"activeProperyValue");
													$(
															"[name="
																	+ proArray[i].code
																	+ "]")
															.addClass(
																	"ProperyValue");
												}
												$('#priceLabel').html("");

												if (propertyValueArray.length == 0) {
													$('#priceLabel')
															.html(
																	$(
																			"#gourpSalePrice")
																			.val());
												}
											});
											
							 $("img").lazyload({effect: "fadeIn"});
						});

		var selectedProperyPackagePrice = "";

		function settingProperyValueCla(proName, proValueId) {
			var obj;
			for (var i = 0; i < propertyValueArray.length; i++) {
				if (proValueId == propertyValueArray[i].id) {
					obj = propertyValueArray[i];
					break;
				}
			}

			//找到属性
			if (obj) {
				var flag = -1;
				for (var i = 0; i < selectedPValue.length; i++) {
					var object = selectedPValue[i];
					if (object.propertyId == obj.propertyId) {
						flag = i;
						break;
					}
				}
				if (flag != -1) {
					selectedPValue.splice(flag, 1);
				}
			}

			$("[name=" + proName + "]").removeClass("activeProperyValue");
			$("[name=" + proName + "]").addClass("ProperyValue");
			$("#" + proValueId).removeClass("ProperyValue");
			$("#" + proValueId).addClass("activeProperyValue");

			for (var j = 0; j < propertyValueArray.length; j++) {
				var object = propertyValueArray[j];
				var id = object.id;
				if (id == proValueId) {
					var proValueIndex = $.inArray(object, selectedPValue);
					if (proValueIndex == -1) {
						selectedPValue.push(object);
					}
				}
			}

			//打印调试
			var groupId = "";
			var groupInputId = "";
			var groupIdExist = false;

			if (selectedPValue.length == 1) {
				var object = selectedPValue[0];
				if (mode == 'group') {
					groupId += object.id + "_GroupInput";
				} else {
					groupId += object.id + "_IndependInput";
				}

				if ($('#' + groupId).length > 0) {
					groupIdExist = true;
				}
			}else{
				for (var i = 0; i < selectedPValue.length; i++) {
					groupId = "";
					var object = selectedPValue[i];
					groupId += object.id + "_";
					var temp = groupId;
					for (var j = 0; j < selectedPValue.length; j++) {
						temp = groupId;
						if (object == selectedPValue[j]) {
							continue;
						}

						var obj = selectedPValue[j];
						if (selectedPValue.length == 2) {
							if (mode == 'group') {
								temp += obj.id + "_GroupInput";
							} else {
								temp += obj.id + "_IndependInput";
							}

						} else {
							temp += obj.id;
						}

						if ($('#' + temp).length > 0) {
							groupIdExist = true;
							break;
						}

						if (selectedPValue.length == 3) {
							var temp1 = temp;
							for (var k = 0; k < selectedPValue.length; k++) {
								var obj1 = selectedPValue[k];
								if (obj1 == object || obj1 == obj) {
									continue;
								}
								if (mode == 'group') {
									temp1 += "_" + obj1.id + "_GroupInput";
								} else {
									temp1 += "_" + obj1.id + "_IndependInput";
								}

								if ($('#' + temp1).length > 0) {
									temp = temp1;
									groupIdExist = true;
									break;
								}
							}
						}

						if (groupIdExist == true) {
							groupId = temp;
							break;
						}
					}

					if (groupIdExist == true) {
						groupId = temp;
						break;

					}
				}
			}

			if (groupIdExist == true) {
				var groupvalue = $('#' + groupId).val();
				$('#priceLabel').html(groupvalue);
				console.log("团购价是：" + groupvalue);
				selectedProperyPackagePrice = groupId;
			}
			var proValues = "已选 ";
			for (var i = 0; i < selectedPValue.length; i++) {
				proValues += selectedPValue[i].name + "  ";
			}
			$('#propertyValueLabel').html(proValues);
		}

		//去结算。
		function checkOut() {
			if (selectedProperyPackagePrice != "") {
				var index = selectedProperyPackagePrice.lastIndexOf("_");
				if (index != -1) {
					selectedProperyPackagePrice = selectedProperyPackagePrice
							.substring(0, index);
				}
			}
			var packagePriceId = selectedProperyPackagePrice;
			$("#packagePriceId").val(packagePriceId);
			var dealPrice = $('#priceLabel').html();
			$("#dealPrice").val(dealPrice);
			var buyNum = $("#numberCount").val();
			$("#buyNum").val(buyNum);
			var proValues = JSON.stringify(selectedPValue);
			$("#proValues").val(proValues);
			$("#mode").val(mode);
			if (dealPrice == "") {
				return false;
			} else {
				return true;
			}
		}

		var swiper = new Swiper('.swiper-container');
	</script>
</body>
</html>
