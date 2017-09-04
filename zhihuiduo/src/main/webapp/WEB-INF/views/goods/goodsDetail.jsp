<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>智惠多商品云购</title>
<link href="${ctx}/css/bootstrap-spinner.css" rel="stylesheet" type="text/css">
<%@ include file="/WEB-INF/member-include/css.jsp"%>
<%@ include file="/WEB-INF/member-include/js.jsp"%>
<script type="text/javascript" src="${ctx}/js/jquery.spinner.min.js"></script>

<style>

.container-fluid {
	font-size: 1.5rem;
	/* 	background: #ccc; */
}

.row {
	padding-left: 8px;
	padding-right: 8px;
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
	font-size: 1.4rem;
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
	font-size: 1.4rem;
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
	 <c:forEach var="packagePrice" items="${packagePrices}"
					varStatus="packagePriceStatus">
						<input id="${packagePrice.propertyValueId}_GroupInput" value="${packagePrice.gourpPrice}" type="hidden"> 
                        <input id="${packagePrice.propertyValueId}_IndependInput" value="${packagePrice.independentPrice}"  type="hidden">
				</c:forEach>
	<div class="container-fluid">
		<div class="row">
			<div class="swiper-container">
				<div class="swiper-wrapper">
					<!-- 轮播图设置 -->
					<c:forEach var="prImage" items="${swiperImages}">
						<div class="swiper-slide">
							<img src="${ctx}/${prImage.url}" alt="智惠多月饼"
								class="img-responsive">
						</div>
					</c:forEach>
				</div>
			</div>
			<br>
			<div class="pull-left">
				<label style="color: red; font-size: 1.8rem;"><i
					class="fa fa-jpy"></i>88</label> <label
					style="color: gray; font-size: 1.3rem;" class="checkbox-inline">已团14万件</label>
			</div>
			<div class="pull-right">
				<label style="color: gray; font-size: 1.5rem;">已拼72734件·2人拼单</label>
			</div>

			<div class="clearfix"></div>
			<div style="text-align: left; font-size: 1.5rem; font-weight: bold;">${product.name}</div>
			<div>&nbsp;</div>
			<div style="text-align: left; font-size: 1.4rem; color: #666;">${product.description}</div>

			<hr class="hr1" />
		</div>
		<div class="row">
			<div class="thumbnail">
				<div class="media">
					<div class="media-body">
						<label>&nbsp;</label>
						<h4 class="media-heading">商品评价</h4>
						<hr class="hr1" />

					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="thumbnail">
				<div class="pull-left">
					<label style="color: gray; font-size: 1.3rem;"
						class="checkbox-inline">商品数量12.已团14万件</label>
				</div>

				<div class="pull-right">
					<label style="color: gray; font-size: 1.3rem;"
						class="checkbox-inline">进店逛逛</label>
				</div>
				<div class="clearfix"></div>
				<button id="addPropertyBtn"
					type="button" class="btn btn-success fileinput-button"
					data-toggle="modal" data-target="#swiperImageModal">
					<i class="fa fa-plus"></i>购买
				</button>
			</div>
		</div>
		<div class="row">
			<div class="thumbnail">
				<div class="media">
					<div class="media-body">
						<label>&nbsp;</label>
						<h4 class="media-heading">商品详情</h4>
						<hr class="hr1" />
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
				<div style="text-align: center">
					<a data-toggle="collapse" style="text-decoration: none;"
						href="#collapseExample" aria-expanded="false"
						aria-controls="collapseExample"> 点击查看价格说明 </a>
				</div>
				<br>
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
	</div>
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
							<i class="fa fa-jpy"></i><label id="priceLabel">88</label>
						</h4>
						<label style="font-size: 1.2rem;" id="propertyValueLabel">请选择属性</label>
					</div>
				</div>
				<div style="height: 80px;"></div>
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
											style="font-size: 1.2rem;">${property.name}</label>
								
								<c:forEach var="pValue" items="${propertyValues}"  varStatus="status">
                                	<c:if test="${property.id==pValue.propertyId}">
										<label id="${pValue.id}" name="${property.code}" class="ProperyValue" onClick="settingProperyValueCla('${property.code}', '${pValue.id}')">${pValue.name}</label>
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
                                        <span class="input-group-btn" style="color:white;" >
                                            <a class="btn btn-danger" data-dir="dwn"><span class="glyphicon glyphicon-minus"></span></a>
                                        </span>
                                        <input type="text" disabled name="numberCount" id="numberCount" style="height:28px;" class="form-control text-center" value="1" max=9999 min=1>
                                        <span class="input-group-btn" style="color:white;" >
                                            <a class="btn btn-info" data-dir="up"><span class="glyphicon glyphicon-plus"></span></a>
                                        </span>
                           </div>       
                	 </div>
				</div>
                

              	<div class="modal-body">

				</div>
				<div class="modal-footer">
                	
                </div>
				
				<button type="button" class="btn btn-danger"
					style="width: 100%; margin: 0; position: fixed; bottom: 0; left: 0; right: 0; border-radius: 0px;">
					确定</button>
			</div>
		</div>
	</div>
    <%@ include file="/WEB-INF/member-include/bottomMenu.jsp"%>
	<script>
		var propertiesString = '${propertiesString}';
		var propertyValuesString = '${propertyValuesString}';

		//保存属性数组
		var propertyValueArray = [];
		var selectedPValue=[];
		//保存属性值数组
		var proArray = [];
		
		$().ready(function() {
			
			if (propertiesString != '' || propertiesString != '[]') {
				proArray = JSON.parse('${propertiesString}');
			}
			
			if (propertyValuesString != '' || propertyValuesString != '[]') {
				propertyValueArray = JSON.parse('${propertyValuesString}');
			}
			
			// spinner(+-btn to change value) & total to parent input 
				$(document).on('click', '.number-spinner a', function () {
					var btn = $(this),
					input = btn.closest('.number-spinner').find('input'),
					total = $('#passengers').val(),
					oldValue = input.val().trim();
			
				if (btn.attr('data-dir') == 'up') {
				  if(oldValue < input.attr('max')){
					oldValue++;
					total++;
				  }
				} else {
				  if (oldValue > input.attr('min')) {
					oldValue--;
					total--;
				  }
				}
				input.val(oldValue);
			  });
			  
			  //单独开团
			  $("#independBuyBtn").bind("click",function(){
				   $("#swiperImageModal").modal('show');
			  });
			  //拼团开团
			  $("#groupBuyBtn").bind("click",function(){
				  $("#swiperImageModal").modal('show');
			  });
		});
					
	
		function settingProperyValueCla(proName, proValueId) {
			var obj;
			for (var i = 0; i < propertyValueArray.length; i++) {
				if(proValueId == propertyValueArray[i].id){
					obj = propertyValueArray[i];
					break;
				}
			}
			
			//找到属性
			if(obj){
				var flag = -1;
				for (var i = 0; i < selectedPValue.length; i++) {
					var object = selectedPValue[i];
					if(object.propertyId == obj.propertyId){
						flag = i;
						break;
					}
				}
				if(flag != -1){
					selectedPValue.splice(flag,1);
				}
			}
			
			$("[name="+proName+"]").removeClass("activeProperyValue");
			$("[name="+proName+"]").addClass("ProperyValue");
			$("#"+proValueId).removeClass("ProperyValue");
			$("#"+proValueId).addClass("activeProperyValue");
			
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
			
			if(selectedPValue.length==1){
				var object = selectedPValue[0];
				groupId+=object.id+"_GroupInput";
				if($('#'+groupId).length>0){
					groupIdExist = true;
				}
			}
			
			//if(selectedPValue.length==2){
				for (var i = 0; i < selectedPValue.length; i++) {
					groupId = "";
					var object = selectedPValue[i];
					groupId+=object.id+"_";
					var temp = groupId;
					for (var j = 0; j < selectedPValue.length; j++) {
						temp = groupId;
						if(object==selectedPValue[j]){
							continue;
						}
						
						var obj= selectedPValue[j];
						if(selectedPValue.length==2){
							temp+=obj.id+"_GroupInput";
						}else{
							temp+=obj.id;
						}
						
						if($('#'+temp).length>0){
							groupIdExist = true;
							break;
						}
						
						if(selectedPValue.length==3){
							var temp1 = temp;
							for (var k = 0; k < selectedPValue.length; k++) {
								var obj1= selectedPValue[k];
								if(obj1==object || obj1==obj){
									continue;
								}
								
								temp1+="_"+obj1.id+"_GroupInput";
								
								if($('#'+temp1).length>0){
									temp=temp1;
									groupIdExist = true;
									break;
								}
							}
						}
						
						if(groupIdExist == true){
							groupId = temp;
							break;
						}
					}
				
					if(groupIdExist == true){
						groupId = temp;
						break;
					}
				}
			//}
			
			if(groupIdExist == true){
				var groupvalue = $('#'+groupId).val();
				$('#priceLabel').html(groupvalue);
				console.log("团购价是："+groupvalue);
			}
			var proValues = "已选 ";
			for (var i = 0; i < selectedPValue.length; i++) {
				proValues += selectedPValue[i].name+"  ";
			}
			$('#propertyValueLabel').html(proValues);
		}
	
		var swiper = new Swiper('.swiper-container');
	</script>
</body>
</html>
