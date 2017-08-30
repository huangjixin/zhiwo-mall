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
	font-size: 1.6rem;
	background: #ccc;
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

.clearfix:after {
	content: ".";
	display: block;
	height: 0;
	clear: both;
	visibility: hidden;
}

.clearfix {
	list-style: none;
}

.wrapper02 {
	position: fixed;
	z-index: 999;
	height: 3rem;
	width: 100%;
	overflow: hidden;
	margin: 0 auto;
	background: #ffffff;
	border-bottom: 1px solid #E5E5E5
}

.wrapper02 .scroller {
	position: absolute
}

.wrapper02 .scroller li {
	height: 3rem;
	color: #333;
	float: left;
	line-height: 3rem;
	font-size: 1.5rem;
	text-align: center
}

.wrapper02 .scroller li a {
	color: #333;
	display: block;
	margin: 0 1rem;
	text-decoration: none;
}

.wrapper02 .scroller li a:hover {
	text-decoration: none
}

.wrapper02 .scroller li.cur {
	background: red
}

.wrapper02 .scroller li.cur a {
	color: #fff;
}

.btn3 {
	position: fixed;
	z-index: 3;
	bottom: 0px;
	border-top: 1px solid #F0F0F0;
	background: #e6e6e6;
	width: 100%;
	text-align: center;
	box-sizing: border-box;
	-webkit-box-sizing: border-box;
}

.menu {
	position: relative;
	float: left;
	width: 33.33%;
	height: 40px;
	line-height: 40px;
	background: #fff;
	border-right: 0px solid #ebebeb;
	box-sizing: border-box;
	-webkit-box-sizing: border-box;
}

.menu:last-child {
	border-right: none;
}

.new-sub {
	position: absolute;
	bottom: 60px;
	z-index: 10;
	width: 100%;
	padding: 0px 10px;
	background: #fff;
	box-sizing: border-box;
	-webkit-box-sizing: border-box;
	border: 1px solid #EEEEEE;
	border-radius: 5px;
	display: none;
}

.new-sub li {
	width: 100%;
	background: #fff;
	float: none;
	box-sizing: border-box;
	-webkit-box-sizing: border-box;
	border-top: 1px solid #f2f2f2;
}

.new-sub li a {
	display: block;
	height: 50px;
	line-height: 50px;
	text-align: left;
	background: #fff;
	color: #333;
	border: none;
	text-align: center;
	font-size: 16px;
}

.bt-name {
	font-size: 16px;
	color: #A0A0A0;
}

.bt-name a {
	display: block;
	font-size: 16px;
	color: #A0A0A0;
	text-decoration: none;
}

.bt-name .active {
	display: block;
	font-size: 16px;
	color: red;
	text-decoration: none;
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
	<div style="height: 10px; text-aling: center; font-size: 1.4rem;"></div>
	<div class="swiper-container">
		<div class="swiper-wrapper">
			<div class="swiper-slide">
				<div class="row">
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
					<div style="height: 60px; text-aling: center; font-size: 1.4rem;">没有数据了</div>
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
	<div class="btn3 clearfix">

		<div class="menu">
			<div class="bt-name" style="color: red;">
				<i class="fa fa-home" aria-hidden="true"></i>首页
			</div>
		</div>
		<!--menu-->


		<div class="menu">
			<div class="bt-name">
				<i class="fa fa-star" aria-hidden="true"></i>新品
			</div>
		</div>
		<!--menu-->


		<div class="menu">
			<div class="bt-name">
				<i class="fa fa-user-circle-o" aria-hidden="true"></i>个人中心
			</div>
		</div>
		<!--menu-->

	</div>
	<!--btn3-->
	<script>
		$(function() {
			//demo示例一到四 通过lass调取，一句可以搞定，用于页面中可能有多个导航的情况
			$('.wrapper').navbarscroll();
		});

		var swiper = new Swiper('.swiper-container');
	</script>
</body>
</html>
