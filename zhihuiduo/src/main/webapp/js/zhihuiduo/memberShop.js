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
					$("#shopIcon").attr("data-original",shop.icon);
					$("#shopName").html(shop.name);
					$("#shopDescription").html(shop.description);
				}
				
				if(products){
					var length = products.length;
					for(var i=0;i<length;i++){
						var prod = products[i];
						var url = ctx+'/goodsDetail?goodsId='+prod.id;
						var prodIcon = '<img class="img-responsive" src="'+ctx+'/images/busy.gif" data-original="'+ctx+'/'+prod.icon+'">';
						var prodName = '<h5>'+prod.name+'</h5>';
						var parameter = '<div id="'+prod.id+'Div" style="display:none;" class="col-xs-6" onClick="turnTo('+url+');"><div class="thumbnail">'+prodIcon+'<div class="caption">'+prodName+'<div style="height:2px;"></div><div class="pull-left"><label style="color: red;"><i class="fa fa-jpy"></i>'+prod.gourpSalePrice+'</label> <span style="font-size:1.3rem;">已拼1222件</span></div><div class="clearfix"></div></div></div></div>';
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