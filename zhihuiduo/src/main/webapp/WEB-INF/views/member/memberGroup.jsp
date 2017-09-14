<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>智惠多商品云购.会员团购</title>
<%@ include file="/WEB-INF/member-include/css.jsp"%>
<%@ include file="/WEB-INF/member-include/js.jsp"%>
<style>
body {
	font-size: 1.5rem;
	background-color: #F2F2F2;
}

.label label-info {
	padding-top: 5px;
}

.form-group {
	margin: 3px;
}
</style>

</head>
<body>
    <div class="page-header"
		style="text-align: center; font-size: 2rem; position:fixed; background-color:#fff; left:0;right:0; top:0; padding-top:10px; margin-top:0;">
		<b>我要拼团</b>&nbsp;&nbsp;<small  style="color:red;font-size: 1.3rem;" onClick="javascript:history.back();">返回</small>
	</div>
    <div style="height:45px;"></div>
    <div class="thumbnail">
		<div class="caption">
			<div class="media">
				<div class="media-left">
					<img class="media-object" src="${ctx}/images/busy.gif" data-original="${ctx}/${product.icon}"
						style="width: 120px; height: 120px; border-radius: 4px;">
				</div>
				<div class="media-body" style="padding-top:5px;">
					<h5 class="media-heading">${product.name}</h5>
                    <span style="color: gray; font-size: 1rem;">已拼${productsCount}件</span>
                    <p>
                    	<i class="fa fa-jpy fa-lg" aria-hidden="true" style="font-size: 2rem; padding-top: 10px; color:red;"></i>
                    </p>
                    
				</div>
			</div>
            
            <p
                style="text-align: left; font-size: 1.4rem; padding:4px; background-color:#FAFAFA">
               <small><i class="fa fa-check-square-o" aria-hidden="true" style="color:red;"></i>&nbsp;假一赔十</small>&nbsp;&nbsp;<small><i class="fa fa-check-square-o" aria-hidden="true" style="color:red;"></i>&nbsp;7天退换</small>&nbsp;&nbsp;<small><i class="fa fa-check-square-o" aria-hidden="true" style="color:red;"></i>&nbsp;48小时发货</small>
            </p>
            <div style="text-align:center; padding:10px;">
            	<span class="label label-danger" style="font-size: 2rem;text-align:center; width:100%; left:0;right:0;">参加拼单</span>
            </div>
            <hr class="hr1"/>
            <div style="font-size: 1.2rem;">
            <div class="pull-left">
                    	<span>拼单须知</span>
                    </div>
                    <div class="pull-right">
                    	<span>好友拼单</span>.<span>人满发货</span>.<span>人不满退款</span>
                    </div>
                    <div class="clearfix">
            </div>
		</div>
	</div>
 	<script>
		$(function() {
			$("img").lazyload({effect: "fadeIn"});
		});
	</script>
</body>
</html>
