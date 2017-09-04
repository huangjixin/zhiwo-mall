<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">  
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=no">
<meta name="renderer" content="webkit">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">

<link href="http://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="http://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css">
<link href="https://cdn.bootcss.com/bootstrap/4.0.0-alpha.6/css/bootstrap-reboot.min.css" rel="stylesheet" type="text/css">
<link href="https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">

<link href="http://cdn.bootcss.com/Swiper/3.4.2/css/swiper.min.css" rel="stylesheet" type="text/css">
<style>

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
	font-weight:normal;
	height: 35px;
	line-height: 35px;
	background: #ffffff;
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
	height: 40px;
	line-height: 40px;
	text-align: left;
	background: #fff;
	color: #333;
	border: none;
	text-align: center;
	font-size: 1.5rem;
}

.bt-name {
	font-size: 1.5rem;
	font-weight:normal;
	color: #A0A0A0;
}

.bt-name a {
	font-weight:normal;
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
	