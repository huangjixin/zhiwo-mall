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
						style="width: 60px; height: 60px; border-radius: 4px; border:1 solid gray">
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
		<div class="col-xs-6" onClick="gotoGoodsDetail();"><div class="thumbnail"><img class="img-responsive" src="${ctx}/images/busy.webp" data-original="${ctx}/${prod.icon}"><div class="caption" style="font-size: 1.3rem;"><h6>${prod.name}</h6><div class="pull-left"><label style="color: red;"><i class="fa fa-jpy"></i>${prod.gourpSalePrice}</label> <span>已拼1222件</span></div><div class="clearfix"></div></div></div></div></a>
	</c:forEach>
	
    <%@ include file="/WEB-INF/member-include/fade-ui.jsp"%>
	<script>
		var obj = ${rawData};
		
				var ctx = "";
		var fadeCount = 0;
		var groupPurcses;
		var products;
		var shop;
		
		$(function() {
			ctx = window.location.protocol+"//"+window.location.host;
			
			//通过lass调取，一句可以搞定，用于页面中可能有多个导航的情况
			//$('.wrapper').navbarscroll();
			
			if(obj){
				products = obj.goodsList;
				shop =  obj.shop;
				groupPurcses = obj.groupPurcses;

				addShop();
			}
			
			//图片懒加载。
			$("img").lazyload({effect: "fadeIn"});
			$("#p").hide(100,function(){
				self.setInterval("fadeInOut('p')",5000); 
			});
			$("#p").hide();
			
		});
		
		function fadeInOut(detination){
			if(groupPurcses){
				if(groupPurcses.length>0){
					if(fadeCount>groupPurcses.length){
						fadeCount+=0;
					}
					var group = groupPurcses[fadeCount];
					$("#p").empty();
					var mIcon = group.memberIcon;
					var name =  group.memberName;
					var url = ctx+'/memberGroup?goodsId='+group.productId+"&groupPurcseId="+group.id;
					var param = '<img id="memberIcon" src="'+mIcon+'" class="fadeImg">&nbsp;&nbsp;<span>'+name+'</span>拼单了&nbsp;&nbsp;<i class="fa fa-chevron-right" aria-hidden="true"></i>';
					$("#p").bind("click",function(){
						showProduct(url);
					});
					$("#p").append(param);
					fadeCount+=1;
				}
				
				$("#"+detination).fadeOut(2000).fadeIn(1000);
			}
			
			
		}
		
		//添加店铺信息
		function addShop(){
			if(obj){
				if(shop){
					$("#shopTitle").html(shop.name);
					if(shop && shop.icon!=''){
						$("#shopIcon").attr("src",ctx+"/images/busy.webp");
						$("#shopIcon").attr("data-original",ctx+"/"+shop.icon);
					}
					
					$("#shopName").html(shop.name);
					$("#shopDescription").html(shop.description);
				}
				
				if(products){
					var length = products.length;
					for(var i=0;i<length;i++){
						var prod = products[i];
						var url = ctx+"/goodsDetail/"+product.id+'.htm?timestamp='+new Date().getTime();
						var prodIcon = '<img class="img-responsive" src="'+ctx+'/images/busy.webp" data-original="'+ctx+'/'+prod.icon+'">';
						var prodName = '<h5>'+prod.name+'</h5>';
						var parameter = '<div id="'+prod.id+'Div" style="display:none;" class="col-xs-6" onClick="turnTo(\''+url+'\');"><div class="thumbnail">'+prodIcon+'<div class="caption">'+prodName+'<div style="height:2px;"></div><div class="pull-left"><label style="color: red;"><i class="fa fa-jpy"></i>'+prod.gourpSalePrice+'</label> <span style="font-size:1.3rem;">已拼'+prod.numberCount+'件</span></div><div class="clearfix"></div></div></div></div>';
						$("#shopGoods").after(parameter);
						$('#'+prod.id+'Div').fadeIn('slow');
					}
				}
			}
		}
		
		//跳转到
		function turnTo(url){
			window.location.href=url;
		}
	</script>

</body>
</html>
