<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="menu">
	<a href="${ctx}/mindex"><div class="menuItemActive" id="indexBtn"
			style="width: 25%;">
			<i class="fa fa-home" aria-hidden="true" style="font-size: 2.5rem;"></i>
			<h6 class="menuTitle">首页</h6>
		</div> </a>
	<div class="menuItem" id="newGoodsBtn" style="width: 25%;">
		<i class="fa fa-diamond" aria-hidden="true" style="font-size: 2.5rem;"></i>
		<h6 class="menuTitle">新品</h6>

	</div>
	<a href="${ctx}/memberGuess/guess"><div class="menuItem"
			id="guessBtn" style="width: 25%;">
			<i class="fa fa-money" aria-hidden="true" style="font-size: 2.5rem;"></i>
			<h6 class="menuTitle">趣猜</h6>
		</div> </a>
	<!--<div
		style="width: 25%; float: left; background-color: #FFFFFF; position: relative; height: 52px; padding-top:5px; box-sizing: border-box; -webkit-box-sizing: border-box;">
        	<i class="fa fa-search" aria-hidden="true"  style="font-size:2.5rem;"></i>
			<h6 style="padding-top:1px;">搜索</h6>
			
	</div>-->
	<a href="${ctx}/memberInfo/info"><div class="menuItem"
			id="personalInfoBtn" style="width: 25%;">
			<i class="fa fa-smile-o" aria-hidden="true"
				style="font-size: 2.5rem;"></i>
			<h6 class="menuTitle">个人中心</h6>

		</div></a>
	<div style="clear: both;"></div>
</div>

