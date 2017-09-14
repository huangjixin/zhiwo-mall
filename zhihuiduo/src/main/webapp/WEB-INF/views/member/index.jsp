<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>智惠多商品云购</title>
<%@ include file="/WEB-INF/member-include/css.jsp"%>
<%@ include file="/WEB-INF/member-include/js.jsp"%>

<!--<script type="text/javascript" src="${ctx}/js/flexible.js"></script>-->
<script type="text/javascript" src="${ctx}/js/iscroll.js"></script>
<script type="text/javascript" src="${ctx}/js/navbarscroll.js"></script>
<style>
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
	background: #F2F2F2;
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
</style>
</head>
<body>
	<div class="wrapper wrapper02" id="wrapper02" name="wrapper">
		<div class="scroller">
			<ul class="clearfix">
				<li><a href="javascript:void(0)">首页</a></li>
				<li><a href="javascript:void(0)">服饰</a></li>
				<li><a href="javascript:void(0)">男装</a></li>
				<li><a href="javascript:void(0)">母婴</a></li>
				<li><a href="javascript:void(0)">家居</a></li>
				<li><a href="javascript:void(0)">美食</a></li>
				<li><a href="javascript:void(0)">电器</a></li>
				<li><a href="javascript:void(0)">美妆</a></li>
				<li><a href="javascript:void(0)">家纺</a></li>
				<li><a href="javascript:void(0)">手机</a></li>
				<li><a href="javascript:void(0)">运动</a></li>
				<li><a href="javascript:void(0)">水果</a></li>
			</ul>
		</div>
	</div>
	<div style="height: 30px; text-aling: center; font-size: 1.4rem;"></div>
	<div class="swiper-container">
		<div class="swiper-wrapper">
			<div class="swiper-slide">
				<div class="row">
                	<c:forEach var="product" items="${list}">
                    	<a href="${ctx}/goodsDetail?goodsId=${product.id}"><div class="col-sm-12 col-md-12">
                            <div class="thumbnail">
                            	
                                <img class="lazy" src="${ctx}/images/busy.gif"  data-original="${ctx}/${product.icon}">
                               <!--<img src="${ctx}/${product.icon}" data-original="${ctx}/${product.icon}">-->
                                <div class="caption" style="text-align: left;">
                                    <p>${product.name}</p>
                                    <div class="pull-right">
                                        <img src="${ctx}/images/1671169078.jpg" class="img-circle"
                                            width="30px" /> <img src="${ctx}/images/1671169078.jpg"
                                            class="img-circle" width="30px" />
                                        <button type="button" class="btn btn-danger">去开团 ></button>
                                    </div>
                                    <div class="pull-left">
                                        <label style="color: red; font-size: 2rem;"><i
                                            class="fa fa-jpy"></i>${product.gourpSalePrice}</label> <label
                                            style="color: gray; font-size: 1.4rem;"
                                            class="checkbox-inline">已团14万件</label>
                                    </div>
    
                                    <div class="clearfix"></div>
                                </div>
                            </div>
                        </div>
                        </a>
                    </c:forEach>
					
					<div class="col-sm-12 col-md-12">
						<div class="thumbnail">
							<img src="${ctx}/images/yuebing.png" alt="智惠多月饼">
							<div class="caption" style="text-align: left;">
								<p>【呆呆兔】 1000g500g 可选中秋月饼广式多口味水果味小月饼 独立包装散装33个左右传统糕点</p>
								<div class="pull-right">
									<img src="${ctx}/images/1671169078.jpg" class="img-circle"
										width="30px" /> <img src="${ctx}/images/1671169078.jpg"
										class="img-circle" width="30px" />
									<button type="button" class="btn btn-danger">去开团 ></button>
								</div>
								<div class="pull-left">
									<label style="color: red; font-size: 2rem;"><i
										class="fa fa-jpy"></i>88</label> <label
										style="color: gray; font-size: 1.4rem;"
										class="checkbox-inline">已团14万件</label>
								</div>

								<div class="clearfix"></div>
							</div>
						</div>
					</div>

					<div class="col-sm-12 col-md-12">
						<div class="thumbnail">
							<div style="height: 70px; text-aling: center; font-size: 1.4rem;">没有数据了</div>
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
		</div>
	</div>

	<!--↓3列菜单开始↓-->
	<%@ include file="/WEB-INF/member-include/bottomIndex.jsp"%>
	<script>
		$(function() {
			//通过lass调取，一句可以搞定，用于页面中可能有多个导航的情况
			$('.wrapper').navbarscroll();
			
			 $("img").lazyload({effect: "fadeIn"});
		});

		var swiper = new Swiper('.swiper-container');
	</script>
</body>
</html>
