<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>智惠多商品云购.商品详情</title>
<#include "css.ftl" />
<#include "js.ftl" />
<link href="../css/bootstrap-spinner.css" rel="stylesheet" type="text/css">
<link href="../css/zhihuiduo.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/jquery.spinner.min.js"></script>
<script type="text/javascript" src="../js/zhihuiduo/goodsDetail.js"></script>
</head>
<body>
	<div class="thumbnail">
		<div class="swiper-container">
			<div class="swiper-wrapper" id="swiperWrapper">
				
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
			<div style="text-align: left; font-size: 1.5rem; font-weight: bold;" id="productName"></div>
			<div>&nbsp;</div>
			<div style="text-align: left; font-size: 1.4rem; color: #666;" id="productDescription"></div>
			<br>
			<p style="text-align: left;padding:4px; background-color:#FAFAFA">
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

	<div class="thumbnail" onClick="gotoShop();">
		<div class="caption">
			<div class="pull-left">
				<div class="media" style="padding-top: 4px; padding-bottom: 8px;">
					<div class="media-left">
						<img class="media-object"
							style="width: 50px; height: 50px; border-radius: 4px;">
					</div>
					<div class="media-body">
						<h5 class="media-heading" style="padding-top: 6px;" id="shopHead"></h5>
						<span style="color: gray; font-size: 1.4rem;">商品数量<span id="goodsCount"></span>.已团 件</span>
					</div>
					<div class="media-right" style="padding-top: 10px;">
						<img class="media-object"
                        	 src="/images/busy.gif" data-original="/images/goodsDetail/gotoShop.png"
							 style="width: 75px; height: 25px;">
					</div>
				</div>
			</div>

			<div class="pull-right"></div>
			<div class="clearfix"></div>
		</div>
	</div>
	
	<div class="thumbnail">
		<div class="caption">
			<div class="media">
				<div class="media-body">
					<label>&nbsp;</label>
					<h4 class="media-heading">商品详情</h4>
					<hr class="hr1" />
                    <div class="media-body" id="proContent">
                    	
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
						src="/images/goods/user_13926205227/product_12365/02f8bc94495c5f6dde5e20f6e3e206c4.jpeg@750w_1l_50Q"
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
				<form action="/memberOrder/checkOut" method="post"
					onsubmit="return checkOut();">
					<input id="packagePriceId" name="packagePriceId" type="hidden">
					<input id="dealPrice" name="dealPrice" type="hidden"> <input
						id="buyNum" name="buyNum" type="hidden"> <input
						id="shopId" name="shopId" value="" type="hidden">
					<input id="goodsId" name="goodsId" value=""
						type="hidden"> <input id="proValues" name="proValues"
						type="hidden"><input id="mode" name="mode"
						type="hidden">
                     <shiro:authenticated>
							<button type="submit" class="btn btn-danger"
						style="width: 100%; margin: 0; position: fixed; bottom: 0; left: 0; right: 0; border-radius: 0px;">
						确定</button>					
				</form>
			</div>
		</div>
	</div>
    <#include "fade-ui.ftl" />
    <#include "bottomMenu.ftl" />
	<script>
		var obj = ${rawData};

		
		var goodsList ;
		var swiperImages ;
		var packagePrices ;
		var groupPurcses ;
		
		var properties;
		var propertyValues;
		var packagePrices;
		var groupPurcse;
	    var groupPurcseMembers;
		
		//保存属性数组
		var propertyValueArray = [];
		var selectedPValue = [];
		var selectedProperyPackagePrice = "";
		
		
		var ctx = "";
		var fadeCount = 0;
	
		$(function() {
			ctx = window.location.protocol+"//"+window.location.host;
			var data =  window.rawData;
			
			//var obj = JSON.parse(data);
			if(obj){
				 goodsList= obj.goodsList ;
		 		 swiperImages= obj.swpierImages ;
		         packagePrices= obj.packagePrices ;
		         groupPurcses= obj.groupPurcses ;
		
				 properties= obj.properties;
    			 propertyValueArray= propertyValues= obj.productPropertyValues;
				 packagePrices= obj.packagePrices;
				 groupPurcse= obj.groupPurcse;
	    		 groupPurcseMembers= obj.groupPurcseMembers;
				 
				 appendProductPro(ctx,obj);
			}
			
			
			$("img").lazyload({effect: "fadeIn"});
			
			
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
											
			$("#indexBtn").removeClass("menuItemActive");
							$("#indexBtn").addClass("menuItem");
							//跳转到首页
							$("#indexBtn").bind("click", function() {
								window.location = ctx+"/mindex";
							});
							//单独开团
							$("#independBuyBtn").bind("click",function() {
												mode = 'independ';
												$("#prriceModal").modal('show');
												$('#priceLabel').html("");
												for (var i = 0; i < properties.length; i++) {
													$("[name="+ properties[i].code+ "]").removeClass("activeProperyValue");
													$("[name="+ properties[i].code+ "]").addClass("ProperyValue");
												}
												if (propertyValueArray.length == 0) {
													$('#priceLabel').html(obj.independentPrice);
												}

											});
							//拼团开团
							$("#groupBuyBtn")
									.bind("click",function() {
												mode = 'group'
												$("#prriceModal").modal('show');
												for (var i = 0; i < properties.length; i++) {
													$("[name="+ properties[i].code+"]").removeClass("activeProperyValue");
													$("[name="+ properties[i].code+"]").addClass("ProperyValue");
												}
												$('#priceLabel').html("");

												if (propertyValueArray.length == 0) {
													$('#priceLabel').html(obj.gourpSalePrice);
												}
											});
											
					 $("#p").hide(100,function(){
								 self.setInterval("fadeInOut('p')",6000); 
					});
					
					var swiper = new Swiper('.swiper-container',{
						autoplay : 5000,
						loop : true
					});
		});
	
	
		//添加商品属性。
		function appendProductPro(urlHead,product){
//			$('#priceLabel').html(product.groupSale);
			//添加隐藏属性。
			if(packagePrices){
				var length = packagePrices.length;
				for(var i=0;i<length;i++){
					var packagePrice= packagePrices[i];
					var para = "";
					para+='<input id="'+packagePrice.propertyValueId+'_GroupInput" value="'+packagePrice.gourpPrice+'" type="hidden">';
					para+='<input id="'+packagePrice.propertyValueId+'_IndependInput" value="'+packagePrice.independentPrice+'" type="hidden">';
					$('body').append(para);
				}
			}
			
			if(swiperImages){
				for(var i=0;i<length;i++){
					var swiperImage= swiperImages[i];
					
					var para = '<div class="swiper-slide" id="'+swiperImage.id+'SwiperImage"><img class="img-responsive" src="'+ctx+'/'+swiperImage.url+'"></div>';
					
					$('#swiperWrapper').append(para);
					//$('#'+swiperImage.id+'SwiperImage').fadeIn('slow');
				}
			}
			
			$('#gSalePriceLabel').html(product.gourpSalePrice);
			$('#marketPriceLabel').html(product.marketPrice);
			$('#soldQuantitySpan').html(product.soldQuantity);
			$('#proContent').html(product.content);
			$('#productName').html(product.name);
			$('#productDescription').html(product.description);
			
			
			$('#shopHead').html(product.shopName);
			if(goodsList){
				$('#goodsCount').html(goodsList.length);
			}
			
			if(groupPurcses){
				var length = groupPurcses.length;
				for(var i=0;i<length;i++){
					var gPurcse= groupPurcses[i];
					var url = ctx+"/memberGroup?goodsId="+gPurcse.productId+"&groupPurcseId="+gPurcse.id;
					var para = '<div class="media" onClick="showProduct(\''+url+'\');"><div class="pull-right" style="padding-top:7px;"><button type="button" class="btn btn-danger">去参团</button></div><div class="media-left"><img class="img-circle" src="'+ctx+'/images/busy.gif" style="width:40px;" data-original="'+gPurcse.memberIcon+'"></div><div class="media-body"><h5 class="media-heading" style="padding-top: 6px;">'+gPurcse.memberName+'</h5><span style="color: gray; font-size: 1.4rem;">还差1人，剩余2小时</span></div></div>';
					
					$('#groupPurcseHeader').after(para);
				}
			}
			
			
			//属性弹窗设置。
			var propertesPara = "";
			
			if(properties){
				var length = properties.length;
				for(var i=0;i<length;i++){
					var flag = false;
					var property= properties[i];
					if(propertyValues){
						var len = propertyValues.length;
						for(var j=0;j<len;j++){
							var pValue = propertyValues[j];
							if(property.id==pValue.propertyId){
								flag = true;
								break;
							}
						}
						
						if(flag == true){
							var proPara = '<div class="form-group"><div class="col-sm-9"><label for="propertyValue" class="col-sm-1 control-label" style="font-size: 1rem;">'+property.name+'</label>';
							for(var k=0;k<propertyValues.length;k++){
								var proValue= propertyValues[k];
								if(property.id==proValue.propertyId){
									var onclickFun = 'onClick="settingProperyValueCla(\''+property.code+'\',\''+proValue.id+'\')"';
									proPara +='<label '+onclickFun+' id="'+proValue.id+'" name="'+property.code+'" class="ProperyValue" >'+proValue.name+'</label>';
								}
							}
							
							proPara +='</div></div>';
							propertesPara += proPara;
						}
					}
				}
			}
			
			$("#numCountFormGroup").before(propertesPara);
		}
		
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
			var packagePriceProValueId = "";
			
			if (selectedPValue.length == 1) {
				var object = selectedPValue[0];
				if (mode == 'group') {
					groupId += object.id + "_GroupInput";
				} else {
					groupId += object.id + "_IndependInput";
				}
				packagePriceProValueId = object.id;
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
							packagePriceProValueId = obj.id;
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
								packagePriceProValueId = obj1.id;
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
				
				for(var i=0;i<packagePrices.length;i++){
					var packagePrice = packagePrices[i];
					if(packagePriceProValueId == packagePrice.propertyValueId){
						if(packagePrice.icon && packagePrice.icon!=''){
							$("#propertyValueImg").attr("src",ctx+"/"+packagePrice.icon);
							//$("#propertyValueImg").attr("src",ctx+'/images/busy.gif');
							//$("#propertyValueImg").attr("data-original",ctx+"/"+packagePrice.icon);
							$("#propertyValueImg").lazyload({effect: "fadeIn"});
						}
						break;
					}
				}
			}
			var proValues = "已选 ";
			for (var i = 0; i < selectedPValue.length; i++) {
				proValues += selectedPValue[i].name + "  ";
			}
			$('#propertyValueLabel').html(proValues);
		}
		
		//跳转到
		function showProduct(url){
			window.location.href=url;
		}
		
		function showProductDialog(){
			$('#prriceModal').modal("show");
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
		
		function fadeInOut(detination){
			$("#"+detination).fadeOut(2000).fadeIn(1000);
		}
		
		function gotoShop(){
			if(obj){
			    var url = ctx+'/memberShop/'+obj.shopId+'.htm?timestamp='+new Date().getTime();
					showProduct(url);
			}	
		}
	</script>
</body>
</html>
