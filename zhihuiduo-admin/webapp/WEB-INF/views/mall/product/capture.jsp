<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>商品抓取</title>
<%@ include file="/WEB-INF/include/easyui-css.jsp"%>
<%@ include file="/WEB-INF/include/easyui-js.jsp"%>
<script type="text/javascript"
	src="${ctx}/js/jquery-easyui/ajaxfileupload.js"></script>
<style>
.swiper-container {
	width: 400px;
	height: auto;
	margin: 20px auto;
}

.swiper-slide {
	text-align: center;
	font-size: 18px;
	background: #ffffff;
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

thumbnail.active {
	border-color: #428bca;
}
</style>
</head>
<body>
	<form class="form-horizontal" method="post" enctype="multipart/form-data" action="${ctx}/product/createFromAlibabaByCont">
		
		<div id="legend" class="">
				<legend class="">选择阿里巴巴商品源文件</legend>
			</div>
			<button type="submit">提交</button>
			<div class="control-group">
				<input type="file" name="file" id="file">
			</div>
		
	</form>
</body>
</html>