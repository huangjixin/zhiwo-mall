<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>智惠多商品云购</title>
<%@ include file="/WEB-INF/member-include/css.jsp"%>
<%@ include file="/WEB-INF/member-include/js.jsp"%>
<style>
.container-fluid {
	
	/* 	background: #ccc; */
	
}
.row{
	padding-left:8px;
	padding-right:8px;
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
</style>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="swiper-container">
				<div class="swiper-wrapper">
					<div class="swiper-slide">
						<img src="${ctx}/images/yuebing.png" alt="智惠多月饼"
							class="img-responsive">
					</div>
					<div class="swiper-slide">
						<img src="${ctx}/images/yuebing.png" alt="智惠多月饼"
							class="img-responsive">
					</div>
					<div class="swiper-slide">
						<img src="${ctx}/images/yuebing.png" alt="智惠多月饼"
							class="img-responsive">
					</div>
				</div>
			</div>

			<div class="pull-left">
				<label style="color: red; font-size: 1.5rem;"><i
					class="fa fa-jpy"></i>88</label> <label
					style="color: gray; font-size: 1.3rem;" class="checkbox-inline">已团14万件</label>
			</div>
			<div class="pull-right">
				<label style="color: gray; font-size: 1.5rem;">已拼72734件·2人拼单</label>
			</div>

			<div class="clearfix"></div>
			<div style="text-align: left; font-size: 1.5rem; font-weight: bold;">【四色五码S-XXL】【V领棉衬衫】【透气清凉】春夏新款韩版宽松V领竖条纹衬衫女休闲百搭五分袖清新棉衬衣</div>
			<div>&nbsp;</div>
			<div style="text-align: left; font-size: 1.4rem; color: #666">优质高含棉料，透气不流汗，轻松透气无压力，清新百搭的四个颜色总有一款适合你，陪你度过整一个夏天，店家极力推荐！！！！S码（80斤-90斤）M码（90斤-100斤）L（101斤-110斤）XL（111斤-120斤），XXL(121-135斤）</div>

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
                        <label style="color: gray; font-size: 1.3rem;" class="checkbox-inline">商品数量12.已团14万件</label>
                    </div>
                    <div class="pull-right">
                        <label style="color: gray; font-size: 1.3rem;" class="checkbox-inline">进店逛逛</label>
                    </div>
                    <div class="clearfix"></div>
              </div>
         </div>
		<div class="row">
			<div class="thumbnail">
				<div class="media">
					<div class="media-body">
                    	<label>&nbsp;</label>
						<h4 class="media-heading">商品详情</h4>
						<hr class="hr1" />
						<img
							src="${ctx}/images/goods/user_13926205227/product_12365/02f8bc94495c5f6dde5e20f6e3e206c4.jpeg@750w_1l_50Q"
							class="img-responsive"> <img
							src="${ctx}/images/goods/user_13926205227/product_12365/0c4d438262012c7099a25f05958272ef.jpeg@750w_1l_50Q"
							class="img-responsive"> <img
							src="${ctx}/images/goods/user_13926205227/product_12365/583052abadbe0b8daf936fd447ebe31c.jpeg@750w_1l_50Q"
							class="img-responsive"> <img
							src="${ctx}/images/goods/user_13926205227/product_12365/b8863f414edab95ca9f41dc3a51d6176.jpeg@750w_1l_50Q"
							class="img-responsive">
					</div>
				</div>
			</div>
		</div>
	</div>

	<script>
		var swiper = new Swiper('.swiper-container');
	</script>
</body>
</html>
