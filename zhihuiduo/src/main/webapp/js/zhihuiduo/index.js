		var ctx = "";
		var fadeCount = 0;
		var groupPurcses;
		$(function() {
			//通过lass调取，一句可以搞定，用于页面中可能有多个导航的情况
			$('.wrapper').navbarscroll();
			
			var swiper = new Swiper('.swiper-container');
			ctx = window.location.protocol+"//"+window.location.host;
			var obj = JSON.parse(window.rawData);
			
			var products;
			if(obj){
				products = obj.products;
				groupPurcses = obj.groupPurcses;
				addListProduct(ctx,"productRow",products)
			}
			
			//图片懒加载。
			$("img").lazyload({effect: "fadeIn"});
			$("#p").hide(100,function(){
				self.setInterval("fadeInOut('p')",5000); 
			});
			
			
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
			}
			
			$("#"+detination).fadeOut(2000).fadeIn(1000);
		}
		
		//批量添加商品
		function addListProduct(urlHead,target,products){
			var length = products.length;
			for(var i=0;i<length;i++){
				var pro = products[i];
				addProduct(urlHead,target,pro);
				$('#'+pro.id+'Div').fadeIn('slow');
			}
		}
		
		//添加商品
		function addProduct(urlHead,target,product){
			<!--<img src="${ctx}/${product.icon}" data-original="${ctx}/${product.icon}"><a href="'+urlHead+'/goodsDetail?goodsId='+product.id+'>-->
			var redirectUrl = urlHead+"/goodsDetail?goodsId="+product.id;
			var parameter = '<div id="'+product.id+'Div" style="display:none;" class="col-sm-12 col-md-12" onClick="showProduct(\''+redirectUrl+'\');"><div class="thumbnail" style="padding-top:0px;"><img class="lazy" src="'+urlHead+'/images/busy.gif"  data-original="'+urlHead+'/'+product.icon+'" style="max-height:200px;" ><div class="caption" style="text-align: left;"><p>'+product.name+'</p><div class="pull-left"><label style="color: red; font-size: 2rem;"><i class="fa fa-jpy"></i>'+product.gourpSalePrice+'</label> <label style="color: gray; font-size: 1.4rem;" class="checkbox-inline">已团14万件</label></div><div class="pull-right"><img src="'+urlHead+'/images/1671169078.jpg" class="img-circle" width="30px" /> <img src="'+urlHead+'/images/1671169078.jpg" class="img-circle" width="30px" /><button type="button" class="btn btn-danger">去开团 ></button></div><div class="clearfix"></div></div></div></div>';
			console.log(parameter);
			$("#"+target).append(parameter);
			
		}
		
		//跳转到
		function showProduct(url){
			window.location.href=url;
		}