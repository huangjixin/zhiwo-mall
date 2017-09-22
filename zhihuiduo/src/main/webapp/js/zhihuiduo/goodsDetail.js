		var ctx = "";
		$(function() {
			//通过lass调取，一句可以搞定，用于页面中可能有多个导航的情况
			$('.wrapper').navbarscroll();
			
			var swiper = new Swiper('.swiper-container');
			ctx = window.location.protocol+"//"+window.location.host;
			var obj = JSON.parse(window.rawData);
			
			var products;
			if(obj){
				products = obj.products;
				addListProduct(ctx,"productRow",products)
			}
			
			$("img").lazyload({effect: "fadeIn"});
		});
		
		//批量添加商品
		function addListProduct(urlHead,target,products){
			var length = products.length;
			for(var i=0;i<length;i++){
				var pro = products[i];
				addProduct(urlHead,target,pro);
				$('#'+pro.id+'Div').fadeIn('slow');
			}
		}
		
		//添加商品描述
		function addProduct(urlHead,target,product){
			
			var redirectUrl = urlHead+"/goodsDetail?goodsId="+product.id;
			var parameter = '';
			console.log(parameter);
			$("#"+target).append(parameter);
			
		}
		
		//跳转到
		function showProduct(url){
			window.location.href=url;
		}