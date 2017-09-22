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
						style="width: 120px; height: 120px; border-radius: 4px;">
				</div>
				<div class="media-body" style="padding-top:5px;">
					<h5 class="media-heading" id="nameHeading">${product.name}</h5>
                    <span style="color: gray; font-size: 1rem;">已拼${productsCount}件</span>
                    <p>
                    	<i  id="priceP" class="fa fa-jpy fa-lg" aria-hidden="true" style="font-size: 2rem; padding-top: 10px; color:red;">${product.gourpSalePrice}</i>
                    </p>
                   
				</div>
			</div>
            
            <p
                style="text-align: left; font-size: 1.4rem; padding:4px; background-color:#FAFAFA">
               <small><i class="fa fa-check-square-o" aria-hidden="true" style="color:red;"></i>&nbsp;假一赔十</small>&nbsp;&nbsp;<small><i class="fa fa-check-square-o" aria-hidden="true" style="color:red;"></i>&nbsp;7天退换</small>&nbsp;&nbsp;<small><i class="fa fa-check-square-o" aria-hidden="true" style="color:red;"></i>&nbsp;48小时发货</small>
            </p>
            <div style="text-align:center; padding:10px;" id="groupPurcseMemberIcon">
                <c:if test="${groupPurcse.disable==true}">
                	<c:forEach var="groupPurcseMember" items="${groupPurcseMembers}">
                		<img src="${groupPurcseMember.memberIcon}" class="img-circle" width="60px" height="60px;" />
                	</c:forEach>
                </c:if>
                <c:if test="${groupPurcse.disable==false}">
                	<img src="${groupPurcse.memberIcon}" class="img-circle" width="60px" height="60px;" />
                	<img src="" class="img-circle" width="50px" height="50px;" />
                </c:if>
            	
            </div>
            <div style="text-align:center; padding:10px;">
            	<c:if test="${groupPurcse.disable==true}">
                	<a href="${ctx}/goodsDetail?goodsId=${product.id}"><span class="label label-danger" style="font-size: 2rem;text-align:center; width:100%; left:0;right:0;">去开团</span></a>
                </c:if>
            	<c:if test="${groupPurcse.disable==false}">
                	<span class="label label-danger" style="font-size: 2rem;text-align:center; width:100%; left:0;right:0;">参加拼单</span>
                </c:if>
            </div>
            <br>
            <hr class="hr1"/>
            <br>
            <div style="font-size: 1.2rem;">
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
 	<script>
	 	window.rawData= '${rawData}';
		var properties;
		var productPropertyValues;
		var packagePrices;
		var groupPurcse;
	    var groupPurcseMembers;
		
		var ctx = "";
		var fadeCount = 0;
		
		$(function() {
			ctx = window.location.protocol+"//"+window.location.host;
			
			var obj = JSON.parse(window.rawData);
			if(obj){
				 properties= obj.properties;
				 productPropertyValues= obj.productPropertyValues;
				 packagePrices= obj.packagePrices;
				 groupPurcse= obj.groupPurcse;
	    		 groupPurcseMembers= obj.groupPurcseMembers;
				 
				 appendProductPro(ctx,obj);
			}
			
			$("img").lazyload({effect: "fadeIn"});
		});
		
		
		//添加商品属性。
		function appendProductPro(urlHead,product){
			$("#mediaIcon").attr("src",urlHead+'/images/busy.gif');
			$("#mediaIcon").attr("data-original",urlHead+'/'+product.icon);
			$("#nameHeading").html(product.name);
			$("#nameHeading").bind("click",function(){
				var url = urlHead+"/goodsDetail?goodsId="+product.id;
				showProduct(url);
			});
			$("#priceP").html(product.gourpSalePrice);
			
			var disable = groupPurcse.disable;
			var para = "";
			var length = 0;
			if(disable ==true){
				length = groupPurcseMembers.length;
				for(var i=0;i<length;i++){
					var gpm = groupPurcseMembers[i];
					para+='<img src="'+gpm.memberIcon+'" class="img-circle" width="60px" height="60px;" />';
				}
				$("#groupPurcseMemberIcon").append(para);
			}else{
				para+='<img src="'+groupPurcse.memberIcon+'" class="img-circle" width="60px" height="60px;" />';
                para+='<img src="" class="img-circle" width="50px" height="50px;" />';
				$("#groupPurcseMemberIcon").append(para);
			}
			
			
		}
		
		//跳转到
		function showProduct(url){
			window.location.href=url;
		}
	</script>
</body>
</html>
