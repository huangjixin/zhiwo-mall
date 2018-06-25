<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>

	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/style.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/font/iconfont.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/page.css">
</head>
<body>

	
		
	

<script type="text/javascript" src="${ctx}/resources/libs/layer/layer.js"></script>

	<table class="table table-agents">
			<thead>
				<tr>
					<th>序号</th>
					<th>手机号</th>
					
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${smsNewsResultVO.smsNewsInfos}" var="smsNewsInfos" varStatus="ext">
				<c:if test="${smsNewsInfos.status=='2'}">
				  <tr>
	                    <td>${ext.index+1 }</td>
						<td>${smsNewsInfos.phone }</td>
					</tr>
				</c:if>
	            </c:forEach>
	            <tr>
	            </tr>
	            
			</tbody>
		</table>
		<div class="ui-button">
				<button type="button" class="btn btn-submit" onclick="savePaper()">批量发送</button>
				<button type="button" class="btn btn-default" onclick="cancel()">关闭</button>
			</div>
		
	

<script type="text/javascript">
function cancel(){
	
	var index = parent.layer.getFrameIndex(window.name);  
	parent.layer.close(index);  
}
</script>
</body>
</html>
