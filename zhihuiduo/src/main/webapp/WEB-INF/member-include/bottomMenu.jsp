<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="menu">
	<a href="${ctx}/mindex"><div class="menuItemActive" id="indexBtn"
			style="width: 13%;">
			<i class="fa fa-home fa-lg" aria-hidden="true"
				style="font-size: 2rem; padding-top: 10px;"></i>
			<h6 class="menuTitle">首页</h6>
		</div> </a> <a href="${ctx}/memberGuess/guess"><div class="menuItem"
			id="guessBtn" style="width: 13%;">
			<i class="fa fa-money fa-lg" aria-hidden="true"
				style="font-size: 2rem; padding-top: 10px;"></i>
			<h6 class="menuTitle">趣猜</h6>
		</div> </a>
	<div class="menuItem" id="personalInfoBtn" style="width: 13%;">
		<i class="fa fa-wechat fa-lg" aria-hidden="true"
			style="font-size: 2rem; padding-top: 10px;"></i>
		<h6 class="menuTitle">客服</h6>
	</div>
	<div class="menuItem"
		style="width: 20%; background-color: #F3ABA7;" id="independBuyBtn">
		<h4 class="menuTitle" style="color: #ffffff;">
			<i class="fa fa-jpy"></i>87
		</h4>
		<h5 style="color: #ffffff; font-weight: normal;">单独购买</h5>

	</div>
	<div class="menuItem"
		style="width: 20%; background-color: #E02E24;" id="groupBuyBtn">
		<h4 class="menuTitle" style="color: #ffffff;">
			<i class="fa fa-jpy"></i>77
		</h4>
		<h5 style="color: #ffffff; font-weight: normal;">一键开团</h5>

	</div>
	<div class="menuItem" 
		style="width: 21%; background-color: #E02E24;" id="shareBuyBtn">
		<h4 class="menuTitle" style="color: #ffffff;">
			<i class="fa fa-jpy"></i>1
		</h4>
		<h5 style="color: #ffffff; font-weight: normal;">一键分销</h5>
	</div>
</div>

