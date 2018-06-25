<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 	<title>富卫运维大平台</title>
</head>
<body>
		<!-- S Filter Box -->
		<div class="filter-box">
			<ul class="ui-form grid-row">
				<li class="col-md-3"><strong>编号&nbsp;<big>:</big></strong><input id="id" name="id" value="${id}" class="ipt-text" type="text" placeholder=""></li>
				<li class="col-md-3"><strong>请求IP&nbsp;<big>:</big></strong><input id="requestIp" name="requestIp" value="${requestIp}" class="ipt-text" type="text" placeholder=""></li>
				<li class="col-md-3"><strong>请求地址&nbsp;<big>:</big></strong><input id="requestUrl" name="requestUrl" value="${requestUrl}" class="ipt-text" type="text" placeholder=""></li>
				<li class="col-md-3 col-btn">
					<button type="button" class="btn btn-submit btn-radius btn-search" onclick="ftr()"><i class="icon-search"></i> 查询</button>
					<!-- <button type="button" class="btn btn-success btn-radius">新增</button> -->
				</li>
			</ul>
		</div>
		<!-- E Filter Box -->
		
		<table class="table table-agents">
			<thead>
				<tr>
	                <th>编号</th>
	                <th>调用方法</th>
	                <th>请求IP</th>
	                <th>请求地址</th>
	                <th>创建时间</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${not empty page}">
	                   <c:forEach  varStatus="idx" var="operateLog" items="${page.records}">
	                        <tr>
	                            
	                            <td>${operateLog.id }</td>
	                            <td>${operateLog.method }</td>
	                            <td>${operateLog.requestIp}</td>
	                            <td>${operateLog.requestUrl}</td>
	                            <td><fmt:formatDate value="${operateLog.createDate}" pattern="YYYY-MM-dd"/></td>
	                        </tr>
	                        </c:forEach>
	                </c:if>
			</tbody>
		</table>
		<!-- / E Table -->
		
		<div class="table-paging clearfix" id="pageDiv">
		</div>
		<!-- / E Table Paging -->
		<script>
		$(function(){
			page("${ctx}/operateLogList?requestIp=",'${page.pageSize}','${page.pageNo}','${page.pageTotal}','pageDiv')
		})
		function ftr(){
			var id  = $("#id").val();
			var requestIp  = $("#requestIp").val();
			var requestUrl = $("#requestUrl").val();
			location.href="${ctx}/operateLogList?id="+id+"&requestIp="+requestIp+"&requestUrl="+requestUrl;
		}
		</script>
</body>
</html>