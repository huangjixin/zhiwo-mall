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
				<li class="col-md-4"><strong>请求IP&nbsp;<big>:</big></strong><input id="requestIp" name="requestIp" value="${requestIp}" class="ipt-text" type="text" placeholder=""></li>
				<li class="col-md-4"><strong>请求地址&nbsp;<big>:</big></strong><input id="requestUrl" name="requestUrl" value="${requestUrl}" class="ipt-text" type="text" placeholder=""></li>
				<li class="col-md-12" style="text-align: right">
					<button type="button" class="btn btn-submit btn-radius btn-search" onclick="ftr()"><i class="icon-search"></i> 查询</button>
				</li>
			</ul>
		</div>
		<!-- E Filter Box -->
		
		<table class="table table-agents">
			<thead>
				<tr>
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
	                            <td>${operateLog.method }</td>
	                            <td>${operateLog.requestIp}</td>
	                            <td>${operateLog.requestUrl}</td>
	                            <td><fmt:formatDate value="${operateLog.createDate}" pattern="YYYY-MM-dd"/></td>
	                        </tr>
	                        </c:forEach>
	                </c:if>
			</tbody>
		</table>
		
		<div class="table-paging clearfix" id="pageDiv">
		</div>
		<script type="text/javascript" src="${ctx}/resources/js/common/page.js"></script>
		<script>
		
		$(function(){
			var requestIp = '${requestIp}';
			var requestUrl ='${requestUrl}';
			pageAjax("${ctx}/listByPageAjax?requestIp="+requestIp+"&requestUrl="+requestUrl,'${page.pageSize}','${page.pageNo}','${page.pageTotal}','pageDiv');
		})
		
		
		//ajax 分页 拼接数据
       function pageNext(url){
	    // 查询字段取页面加载时 model传入的值  防止分页执行查询
	    var requestIp = '${requestIp}';
	    var requestUrl ='${requestUrl}';
	    $.get(url,function(returnData){
		    if(returnData.code == '1'){
			   var html = "";
			   $.each(returnData.data.records,function(i,row){
				
			/* row.code = row.code==null?'':row.code; //判断null字段
			row.name = row.name==null?'':row.name; */
			html+='<tr>'+
				  '<td>'+row.method+'</td>'+
				  '<td>'+row.requestIp+'</td>'+
				  '<td>'+row.requestUrl+'</td>'+
				  '<td>'+new Date(row.createDate).Format("yyyy-MM-dd")+'</td></tr>';
			})
			$(".table tbody").html(html);
			pageAjax("${ctx}/listByPageAjax?requestIp="+requestIp+"&requestUrl="+requestUrl,returnData.data.pageSize,returnData.data.pageNo,returnData.data.pageTotal,'pageDiv');
		}
	})
}
		
		function ftr(){
			var requestIp  = $("#requestIp").val();
			var requestUrl = $("#requestUrl").val();
			location.href="${ctx}/operateLogList?requestIp="+requestIp+"&requestUrl="+requestUrl;
		}
		</script>
</body>
</html>