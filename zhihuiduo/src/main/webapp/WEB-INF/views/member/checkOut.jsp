<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>智惠多商品云购.订单结算</title>
<%@ include file="/WEB-INF/member-include/css.jsp"%>
<%@ include file="/WEB-INF/member-include/js.jsp"%>
<style>
	body{
		font-size:1.5rem;
		background-color:#F2F2F2;
	}
</style>
</head>
<body>
	
    	<c:if test="${address!=null}">
        <div style="height:80px;line-height:80px; background-color:#ffffff; padding-left:10px;padding-right:10px;">
        	<div class="pull-left" >
        	<span><i class="fa fa-plus-square fa-lg" aria-hidden="true" style="color:red;"></i></span>
          	<span>&nbsp;&nbsp;</span>
            <span>手动添加新地址</span>
        </div>
    	<div class="pull-right">
        	<span>&nbsp;&nbsp;</span>
        	<span><i class="fa fa-arrow-circle-right lg" aria-hidden="true"></i></span>
        </div>
        <div class="clearfix"></div>
        </div>
        </c:if>
         
        <c:if test="${address==null}">
        	<div style="height:80px; background-color:#ffffff; padding-left:10px;padding-right:10px;padding-top:10px; border-bottom:1px solid red;">
        	<strong>黄记新</strong>&nbsp;&nbsp;<span>13926205227</span><br>
                  <span>广东 广州 天河区 天河北路888号信源大厦2018</span>
         </div>
        </c:if>
    	
   
   
    <div style="background-color:#ffffff; padding-left:10px;padding-right:10px; padding-top:3px; padding-bottom:3px; margin-top:4px;">
    	<span>南极人+穆卡专卖店</span>
    </div>
    <div style="background-color:#ffffff; padding-left:10px;padding-right:10px; padding-top:10px; padding-bottom:10px;margin-top:4px;">
		<div class="media">
            <div class="media-left">
                <img class="media-object" src="${ctx}/${product.icon}" width="120px"
                     alt="媒体对象">
            </div>
            <div class="media-body">
                <h5 class="media-heading">${product.name}</h5>
                
            </div>
        </div>
   </div>
    
	<div style="height:40px; background-color:#ffffff; border-top:0px solid red; position:absolute; left:0;right:0; bottom:0;">
    	<div style="float:right;background-color:red; color:#ffffff; width:40%; height:40px; text-align:center;line-height:40px;">
        	<span>立即支付</span>
        </div>
        <div style="float:right;background-color:#ffffff; color:#151516; width:60%; height:40px; text-align:right;line-height:40px; padding-right:30px;">
        	<span>实际付款：</span>&nbsp;&nbsp;<span style="color:red;font-size:2rem;"><i class="fa fa-jpy"></i>&nbsp;<label style="">88</label></span>
        </div>
    </div>
</body>
</html>
