<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>智惠多商品云购</title>
<%@ include file="/WEB-INF/member-include/css.jsp"%>
<%@ include file="/WEB-INF/member-include/js.jsp"%>
<link href="/css/zhihuiduo.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/js/zhihuiduo/index.js"></script>
<script type="text/javascript" src="/js/navbarscroll.js"></script>
</head>
<body>
	<div id="p" class="fadeDiv" style="display:none;">
    	 <img id="memberIcon" src="/uassets/2017/8/27/1503807062182.jpg" class="fadeImg">&nbsp;&nbsp;<span>黄记新</span>拼单了&nbsp;&nbsp;<i class="fa fa-chevron-right" aria-hidden="true"></i>
    </div>
	<div class="wrapper wrapper02" id="wrapper02" name="wrapper">
		<div class="scroller">
			<ul class="clearfix">
				<li id="indexLi0" ><a href="javascript:void(0)">首页</a></li>
				<li id="indexLi1" ><a href="javascript:void(0)">服饰</a></li>
				<li id="indexLi2"><a href="javascript:void(0)">男装</a></li>
				<li id="indexLi3"><a href="javascript:void(0)">母婴</a></li>
				<li id="indexLi4"><a href="javascript:void(0)">家居</a></li>
				<li id="indexLi5"><a href="javascript:void(0)">美食</a></li>
				<li id="indexLi6"><a href="javascript:void(0)">电器</a></li>
				<li id="indexLi7"><a href="javascript:void(0)">美妆</a></li>
				<li id="indexLi8"><a href="javascript:void(0)">家纺</a></li>
				<li id="indexLi9"><a href="javascript:void(0)">手机</a></li>
				<li id="indexLi10"><a href="javascript:void(0)">运动</a></li>
				<li id="indexLi11"><a href="javascript:void(0)">水果</a></li>
			</ul>
		</div>
	</div>
	<div style="height: 40px; text-aling: center; font-size: 1.4rem;"></div>
	<div class="swiper-container">
		<div class="swiper-wrapper">
			<div class="swiper-slide">
				<div class="row" id="indexProductsRow">
                	<div class="swiper-container">
		<div class="swiper-wrapper">
			<div class="swiper-slide">
				<div class="row" id="indexProductsRow">
                	
				</div>
			</div>
				</div>
			</div>
			<div class="swiper-slide">Slide 2</div>
			<div class="swiper-slide">Slide 3</div>
			<div class="swiper-slide">Slide 4</div>
			<div class="swiper-slide">Slide 5</div>
			<div class="swiper-slide">Slide 6</div>
			<div class="swiper-slide">Slide 7</div>
			<div class="swiper-slide">Slide 8</div>
			<div class="swiper-slide">Slide 9</div>
			<div class="swiper-slide">Slide 10</div>
            <div class="swiper-slide">Slide 11</div>
		</div>
	</div>
	<div style="height: 60px; text-aling: center; font-size: 1.4rem;"></div>
    
	<!--↓3列菜单开始↓-->
	<%@ include file="/WEB-INF/member-include/bottomIndex.jsp"%>
    <script>
    	window.rawData= ${rawData};
		
		var ctx = "";
		var fadeCount = 0;
		var groupPurcses;
		var products;
		var indexPageInfo;
		var swiper;
		var navbarIndex;
		var currentScrollTop;
		var currentPageNum=0;
		
		$(function() {
			ctx = window.location.protocol+"//"+window.location.host;
			var obj = window.rawData;
			
			if(obj){
				indexPageInfo = obj.productPage;
				products = obj.products;
				groupPurcses = obj.groupPurcses;
				addIndexListProduct(ctx,"indexProductsRow",indexPageInfo.rows);
			}
			
			//图片懒加载。
			$("img").lazyload({effect: "fadeIn"});
			$("#p").hide(100,function(){
				self.setInterval("fadeInOut('p')",5000); 
			});
			$("#p").hide();
			swiper = new Swiper('.swiper-container',{
				onSlideChangeEnd: function(swiper){
					var swiperActiveIndex = swiper.activeIndex;
					if(navbarIndex != swiperActiveIndex){
						$("#indexLi"+swiperActiveIndex).click();
						navbarIndex = swiperActiveIndex;
					}
					  //alert(swiper.activeIndex);
				}
			});
			//通过lass调取，一句可以搞定，用于页面中可能有多个导航的情况
			$('.wrapper').navbarscroll({ 
            	endClickScroll:function(obj){ 
                	console.log();
					var liId =  $(obj).attr("id");
					var dot = liId.lastIndexOf("indexLi");
					var index = liId.substring(7,liId.length);
					var swiperActiveIndex = swiper.activeIndex;
					if(swiperActiveIndex!=index){
						navbarIndex = index;
						swiper.slideTo(index, 500);
					}
				}
            });
			
			$(document).scroll(function(){  
				var scrollTop = $(document).scrollTop();
				var docHeight = $(document).height();
				var winHeight = $(window).height();
				if(scrollTop>=docHeight-winHeight){
					getRemoteProduct();
				}
			 });
		});
		
		
		
		function getRemoteProduct(){
			var swiperActiveIndex = swiper.activeIndex;
			//首页去取数据。
			if(swiperActiveIndex ==0){
				 var data = {};
				 data.pageNum = currentPageNum+1;
   				 data.pageSize = 10;
				 $.ajax({  
					url:ctx+"/indexGoods",  
					data:data,   
					type:"get",  
					success:function(data){  
						var list = data.rows;
						addIndexListProduct(ctx,"indexProductsRow",list);
					}  
				});  
			}
			console.log('远程加载数据'); 
		}
		
		function fadeInOut(detination){
			if(groupPurcses){
				if(groupPurcses.length>0){
					if(fadeCount>=groupPurcses.length){
						fadeCount=0;
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
			
			$("#"+detination).fadeOut(500).fadeIn(500);
		}
		
		//首页批量添加商品
		function addIndexListProduct(urlHead,target,products){
			if(!products){
				return;
			}
			var length = products.length;
			for(var i=0;i<length;i++){
				var pro = products[i];
				addProduct(urlHead,target,pro);
				$('#'+pro.id+'Div').fadeIn('slow');
			}
			currentPageNum+=1;
			$("img").lazyload();
		}
		
		//添加商品
		function addProduct(urlHead,target,product){
			<!--<img src="${ctx}/${product.icon}" data-original="${ctx}/${product.icon}"><a href="'+urlHead+'/goodsDetail?goodsId='+product.id+'>-->
			var redirectUrl = urlHead+"/goodsDetail/"+product.id+'.htm?timestamp='+new Date().getTime();
			var parameter = '<div id="'+product.id+'Div" style="display:none;" class="col-sm-12 col-md-12" onClick="showProduct(\''+redirectUrl+'\');"><div class="thumbnail" style="padding-top:0px;"><img class="lazy" src="'+urlHead+'/images/busy.gif"  data-original="'+urlHead+'/'+product.icon+'" style="max-height:200px;" ><div class="caption" style="text-align: left;"><p>'+product.name+'</p><div class="pull-left"><label style="color: red; font-size: 2rem;"><i class="fa fa-jpy"></i>'+product.gourpSalePrice+'</label> <label style="color: gray; font-size: 1.4rem;" class="checkbox-inline">已团14万件</label></div><div class="pull-right"><img src="'+urlHead+'/images/1671169078.jpg" class="img-circle" width="30px" /> <img src="'+urlHead+'/images/1671169078.jpg" class="img-circle" width="30px" /><button type="button" class="btn btn-danger">去开团 ></button></div><div class="clearfix"></div></div></div></div>';
			console.log(parameter);
			$("#"+target).append(parameter);
		}
		
		//跳转到
		function showProduct(url){
			window.location.href=url;
		}
		
		
		//顶层页面切换至
		function navScrollTo(index){
			mySwiper.slideTo(index);
		}
		
		//SWIPER种类页面切换
		function swiperTo(index){
			swiper.slideTo(index, 300);
			navScrollTo(index)
		}
		
	</script>
</body>
</html>
