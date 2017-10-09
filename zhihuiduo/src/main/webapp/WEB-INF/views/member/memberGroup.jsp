<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>智惠多商品云购.会员团购</title>
<%@ include file="/WEB-INF/member-include/css.jsp"%>
<%@ include file="/WEB-INF/member-include/js.jsp"%>
<link href="${ctx}/css/zhihuiduo.css" rel="stylesheet" type="text/css" />
</head>
<body>
    <div class="page-header"
		style="text-align: center; font-size: 2rem; position:fixed; background-color:#fff; left:0;right:0; top:0; padding-top:10px; margin-top:0;">
		<b>我要拼团</b>&nbsp;&nbsp;<small  style="color:red;font-size: 1.3rem;" onClick="javascript:history.back();">返回</small>
	</div>
    <div style="height:45px;"></div>
    <div class="thumbnail">
		<div class="caption">
			<div class="media" id="media">
				<div class="media-left">
					<img id="mediaIcon" class="media-object"
						style="width: 120px; border-radius: 4px;">
				</div>
				<div class="media-body" style="padding-top:5px;">
					<h5 class="media-heading" id="nameHeading"></h5>
                    <span style="color: gray; font-size: 1rem;">已拼<b id="soldQuantityB"></b>件</span>
                    <p>
                    	<i  id="priceP" class="fa fa-jpy fa-lg" aria-hidden="true" style="font-size: 2rem; padding-top: 10px; color:red;">${product.gourpSalePrice}</i>
                    </p>
                   
				</div>
			</div>
            
            <p
                style="text-align: left; font-size: 1.4rem; padding:4px; background-color:#FAFAFA">
               <span><i class="fa fa-check-square-o" aria-hidden="true" style="color:red;"></i>&nbsp;假一赔十</span>&nbsp;&nbsp;<span><i class="fa fa-check-square-o" aria-hidden="true" style="color:red;"></i>&nbsp;7天退换</span>&nbsp;&nbsp;<span><i class="fa fa-check-square-o" aria-hidden="true" style="color:red;"></i>&nbsp;48小时发货</span>
            </p>
            <div style="text-align:center;">
	            <h3 id="offlineTitle" style="color:red;"></h3>
            <div style="text-align:center; padding:10px;" id="groupPurcseMemberIcon">
                
            </div>
            <br>
            <hr class="hr1"/>
            <br>
            <div style="font-size: 1.4rem;">
            <div class="pull-left">
                    	<span>拼单须知</span>
                    </div>
                    <div class="pull-right">
                    	<span>好友拼单</span>.<span>人满发货</span>.<span>人不满退款</span>
                    </div>
                    <div class="clearfix">
            </div>
		</div>
	</div>
    
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
				<form id="form"  method="post"
					onsubmit="return checkOut();">
					<input id="packagePriceId" name="packagePriceId" type="hidden">
					<input id="dealPrice" name="dealPrice" type="hidden"> <input
						id="buyNum" name="buyNum" type="hidden"> <input
						id="shopId" name="shopId" value="" type="hidden">
					<input id="goodsId" name="goodsId" value=""
						type="hidden"> <input id="proValues" name="proValues"
						type="hidden"><input id="mode" name="mode"
						type="hidden"><input id="groupPurcseId" name="groupPurcseId"
						type="hidden">
                        <button type="submit" id="submitBtn" class="btn btn-danger"
						style="width: 100%; margin: 0; position: fixed; bottom: 0; left: 0; right: 0; border-radius: 0px;">
						确定</button>
                     	<!--<shiro:authenticated>
							<button type="submit" class="btn btn-danger"
						style="width: 100%; margin: 0; position: fixed; bottom: 0; left: 0; right: 0; border-radius: 0px;">
						确定</button>
						</shiro:authenticated>
						<shiro:notAuthenticated>
							<a href="${ctx}/memberLogin"><button type="button" class="btn btn-danger"
						style="width: 100%; margin: 0; position: fixed; bottom: 0; left: 0; right: 0; border-radius: 0px;">
						去登录</button></a>
						</shiro:notAuthenticated>-->
					
				</form>
			</div>
		</div>
	</div>
    <%@ include file="/WEB-INF/member-include/bottomIndex.jsp"%>
 	<script>
	 	window.rawData= ${rawData};
		
		var interval = 1000; 
		
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
		var obj;
		
		//开团模式，是独立团还是拼团。
		var mode = "group";
		
		$(function() {
			ctx = window.location.protocol+"//"+window.location.host;
			
			obj = window.rawData;
			if(obj){
				 properties= obj.properties;
    			 propertyValueArray= propertyValues= obj.productPropertyValues;
				 packagePrices= obj.packagePrices;
				 groupPurcse= obj.groupPurcse;
	    		 groupPurcseMembers= obj.groupPurcseMembers;
				 
				 appendProductPro(ctx,obj);
			}
			
			$("img").lazyload({effect: "fadeIn"});
			// spinner(+-btn to change value) & total to parent input 
							$(document).on('click','.number-spinner a',function() {
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
		});
		
		
		//添加商品属性。
		function appendProductPro(urlHead,product){
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
			
			if(obj){
				if(obj.status=='offline'){
					$('#offlineTitle').html('该商品已下架');
					$('#submitBtn').html('该商品已下架');
					$('#submitBtn').attr('disabled',true);
				}else if(obj.status=='online'){
					$('#offlineTitle').html('');
					$('#submitBtn').attr('disabled',false);
					$('#submitBtn').html('确定');
				}
				
				if(obj.storage){
					if(obj.storage==0){
						$('#offlineTitle').html('该商品已售馨');
						$('#submitBtn').html('该商品已售馨');
						$('#submitBtn').attr('disabled',true);
					}
				}
			}
			
			$("#mediaIcon").attr("src",urlHead+'/'+product.icon);
			//$("#mediaIcon").attr("data-original",urlHead+'/'+product.icon);
			$("#nameHeading").html(product.name);
			$("#soldQuantityB").html(product.numberCount);
			$("#nameHeading").bind("click",function(){
				var url = urlHead+"/goodsDetail?goodsId="+product.id;
				showProduct(url);
			});
			$("#priceP").html(product.gourpSalePrice);
			
			var disable = groupPurcse.disable;
			var para = "";
			var length = 0;
			if(disable ==true){
				if(groupPurcseMembers){
					length = groupPurcseMembers.length;
					for(var i=0;i<length;i++){
						var gpm = groupPurcseMembers[i];
						var imgUrl = '';
						if(gpm.memberIcon){
							imgUrl = gpm.memberIcon;
						}
						imgUrl == ''?para+='<img class="img-circle" width="60px" height="60px;"  style="border:1px solid yellow;"/>':para+='<img src="'+gpm.memberIcon+'" class="img-circle" width="60px" height="60px;"  style="border:1px solid yellow;"/>';
						
						para+='&nbsp;';
					}
					
					para+="<br>";
					
					for(var i=0;i<length;i++){
						var gpm = groupPurcseMembers[i];
						if(gpm.memberName){
							para+='<span>'+gpm.memberName+'</span>';
							para+='&nbsp;&nbsp;&nbsp;&nbsp;';	
						}
					}
					
					para+="<br>";
					para+='<span style="font-size: 1.3rem;color:red;">该团已满</span><br>';
					para+="<br>";
					para+='<span onClick="showProductDialog()" class="label label-danger" style="font-size: 2rem;text-align:center; width:100%; left:0;right:0;">一键开团</span>';
					para+="<br>";
					$("#groupPurcseMemberIcon").append(para);
				}
			}else{
				if(groupPurcse.memberIcon){
				  para+='<img src="'+groupPurcse.memberIcon+'" class="img-circle" style="border:1px solid yellow;" width="60px" height="60px;" />';
				}else{
				  para+='<img class="img-circle" style="border:1px solid yellow;" width="60px" height="60px;" />';				
				}
				
				
				para+='&nbsp;';
                para+='<img  class="img-circle" width="60px" height="60px;" style="border:1px dashed gray;" /><br>';
				para+='<span style="width:50%;text-align:right;">'+groupPurcse.memberName+'</span>';
				para+='&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';	
				para+='<span style="width:50%;text-align:left;">?</span>';	
				para+='&nbsp;&nbsp;&nbsp;&nbsp;';	
				para+="<br>";
				para+='<span id="countimeSpan">仅剩1个名额，距离结束时间还有</span><b id="expiredTimeB" style="color:red;"></b><br>';
				para+="<br>";
				para+='<span onClick="showProductDialog()" class="label label-danger" style="font-size: 2rem;text-align:center; width:100%; left:0;right:0;">参团作战</span><br>';
				//$("#countimeSpan").before(para);
				$("#groupPurcseMemberIcon").append(para);
				
				window.setInterval(function(){showCountDown(groupPurcse.expiredTime,'expiredTimeB');}, interval); 
			}
			
			
			$("#propertyValueImg").attr("src",product.icon);
			$("#priceLabel").html(product.gourpSalePrice);
			
			
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
							//$("#propertyValueImg").attr("src",ctx+'/images/busy.webp');
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
			var disable = groupPurcse.disable;
			//如果为false那么开团人数未满，反则就表示已满，记得更新订单的isFormSuccess
			if(disable==false){
				$('#groupPurcseId').val(groupPurcse.id);
			}
			var packagePriceId = selectedProperyPackagePrice;
			$("#packagePriceId").val(packagePriceId);
			var dealPrice = $('#priceLabel').html();
			$("#dealPrice").val(dealPrice);
			var buyNum = $("#numberCount").val();
			$("#buyNum").val(buyNum);
			var proValues = JSON.stringify(selectedPValue);
			$("#proValues").val(proValues);
			$("#goodsId").val(obj.id);
			$("#mode").val(mode);
			var url = ctx+"/memberOrder/checkOut";
			$("#form").attr("action",url);
			if (dealPrice == "") {
				return false;
			} else {
				return true;
			}
		}
		
		function showCountDown(endDate,divname) 
		{ 
			var now = new Date(); 
			var leftTime=endDate-now.getTime(); 
			var leftsecond = parseInt(leftTime/1000); 
			//var day1=parseInt(leftsecond/(24*60*60*6)); 
			var day1=Math.floor(leftsecond/(60*60*24)); 
			var hour=Math.floor((leftsecond-day1*24*60*60)/3600); 
			var minute=Math.floor((leftsecond-day1*24*60*60-hour*3600)/60); 
			var second=Math.floor(leftsecond-day1*24*60*60-hour*3600-minute*60); 
			var timeStr = day1+"天"+hour+":"+minute+":"+second+"";
			$('#'+divname).html(timeStr);
		} 
		
	</script>
</body>
</html>
