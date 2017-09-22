<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="menu">
	<a href="${ctx}/mindex" style="color:#58595B"><div class="menuItemActive" id="indexBtn"
			style="width: 13%;">
			<i class="fa fa-home fa-lg" aria-hidden="true"
				style="font-size: 2rem; padding-top: 10px;"></i>
			<h6 class="menuTitle">首页</h6>
		</div> </a> <a href="${ctx}/memberGuess/guess" style="color:#58595B"><div class="menuItem"
			id="guessBtn" style="width: 13%;">
			<i class="fa fa-money fa-lg" aria-hidden="true"
				style="font-size: 2rem; padding-top: 10px;"></i>
			<h6 class="menuTitle">趣猜</h6>
		</div> </a>
	<div class="menuItem" id="personalInfoBtn" style="width: 13%;color:#58595B">
		<i class="fa fa-wechat fa-lg" aria-hidden="true"
			style="font-size: 2rem; padding-top: 10px;"></i>
		<h6 class="menuTitle">客服</h6>
	</div>
	<div class="menuItem"
		style="width: 20%; background-color: #F3ABA7;" id="independBuyBtn">
		<h5 class="menuTitle" style="color: #ffffff;">
			<i class="fa fa-jpy"></i><span id="independentPriceLabel">87</span>
		</h5>
		<h5 style="color: #ffffff; font-weight: normal;">单独购买</h5>

	</div>
	<div class="menuItem"
		style="width: 20%; background-color: #E02E24;" id="groupBuyBtn">
		<h5 class="menuTitle" style="color: #ffffff;">
			<i class="fa fa-jpy"></i><span id="gourpSalePriceLabel">77</span>
		</h5>
		<h5 style="color: #ffffff; font-weight: normal;">一键开团</h5>

	</div>
	<div class="menuItem" 
		style="width: 21%; background-color: #E02E24;" id="shareBuyBtn">
		<h5 class="menuTitle" style="color: #ffffff;">
			<i class="fa fa-jpy"></i><span id="distributionValueLabel">1</span>
		</h5>
		<h5 style="color: #ffffff; font-weight: normal;">分销获利</h5>
	</div>
</div>

