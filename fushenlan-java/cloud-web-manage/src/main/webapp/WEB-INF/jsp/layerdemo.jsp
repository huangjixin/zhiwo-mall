<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 	<title>富卫运维大平台</title>
</head>
<body>
	<script src="${ctx}/resources/js/common/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="${ctx}/resources/libs/layer/layer.js"></script>
	<script src="${ctx}/resources/js/common.js"></script>
	<center>${tips}</center>
	<a onclick="aa()">dddd</a>
		<script type="text/javascript">
	function aa(){
		parent.dylayer2();
		
	}
	</script>
</body>
</html>