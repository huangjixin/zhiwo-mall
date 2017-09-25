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
					
					var para = '<div class="swiper-slide" id="'+swiperImage.id+'SwiperImage" style="display:none;"><img class="img-responsive" src="'+ctx+'/'+swiperImage.url+'"></div>';
					
					$('#swiperWrapper').append(para);
					$('#'+swiperImage.id+'SwiperImage').fadeIn('slow');
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
						$("#propertyValueImg").attr("src",ctx+'/images/busy.gif');
						$("#propertyValueImg").attr("data-original",packagePrice.icon);
						$("#propertyValueImg").lazyload({effect: "fadeIn"});
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