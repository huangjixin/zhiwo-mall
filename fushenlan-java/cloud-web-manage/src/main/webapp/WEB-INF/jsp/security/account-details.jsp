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
		<div class="filter-box">
		<strong>用户详情界面</strong>
		</div>
		<div class="filter-box">
			<ul style="margin-left: 100px; margin-right: 100px;">
				<li style="float: left;">
					<strong style="display:inline-block;width:120px;text-align:right">编号<big>:</big></strong>&nbsp;&nbsp;&nbsp;${acc.id }<br/><br/>
					<strong style="display:inline-block;width:120px;text-align:right">账户名<big>:</big></strong>&nbsp;&nbsp;&nbsp;${acc.accountName }<br/><br/>
					<strong style="display:inline-block;width:120px;text-align:right">密码<big>:</big></strong>&nbsp;&nbsp;&nbsp;${acc.password }<br/><br/>
					<strong style="display:inline-block;width:120px;text-align:right">是否开通<big>:</big></strong>&nbsp;&nbsp;&nbsp;
							<c:if test="${acc.enabled==true }">
								<td>是</td>
							</c:if>
							<c:if test="${acc.enabled==false }">
								<td>否</td>
							</c:if><br/><br/>
					<strong style="display:inline-block;width:120px;text-align:right">开通时间<big>:</big></strong>&nbsp;&nbsp;&nbsp;<fmt:formatDate value="${acc.enabledTime }" pattern="yyyy-MM-dd"/><br/><br/>
					<strong style="display:inline-block;width:120px;text-align:right">是否锁定<big>:</big></strong>&nbsp;&nbsp;&nbsp;
							<c:if test="${acc.locked==true }">
								<td>是</td>
							</c:if>
							<c:if test="${acc.locked==false }">
								<td>否</td>
							</c:if><br/><br/>
					<strong style="display:inline-block;width:120px;text-align:right">锁定时间<big>:</big></strong>&nbsp;&nbsp;&nbsp;<fmt:formatDate value="${acc.lockedTime }" pattern="yyyy-MM-dd"/><br/><br/>
					<strong style="display:inline-block;width:120px;text-align:right">公司id<big>:</big></strong>&nbsp;&nbsp;&nbsp;${acc.companyId }<br/><br/>
					<strong style="display:inline-block;width:120px;text-align:right">手机号<big>:</big></strong>&nbsp;&nbsp;&nbsp;${acc.mobile }<br/><br/>
				</li>
				<li>
					<strong style="display:inline-block;width:120px;text-align:right">座机号<big>:</big></strong>&nbsp;&nbsp;&nbsp;${acc.telephone }<br/><br/>
					<strong style="display:inline-block;width:120px;text-align:right">邮箱<big>:</big></strong>&nbsp;&nbsp;&nbsp;${acc.email }<br/><br/>
					<strong style="display:inline-block;width:120px;text-align:right">账号类型<big>:</big></strong>&nbsp;&nbsp;&nbsp;
							<c:if test="${acc.accountType==1 }">
								<td>后台管理</td>
							</c:if>
							<c:if test="${acc.accountType==3 }">
								<td>代理人</td>
							</c:if>
							<c:if test="${acc.accountType==4 }">
								<td>内勤</td>
							</c:if><br/><br/>
							
					
					
					
					
						
					<strong style="display:inline-block;width:120px;text-align:right">是否首次登录<big>:</big></strong>&nbsp;&nbsp;&nbsp;
							<c:if test="${acc.firstLogin==true }">
								<td>是</td>
							</c:if>
							<c:if test="${acc.firstLogin==false }">
								<td>否</td>
							</c:if><br/><br/>
					<strong style="display:inline-block;width:120px;text-align:right">第一次登录时间<big>:</big></strong>&nbsp;&nbsp;&nbsp;<fmt:formatDate value="${acc.firstLoginTime }" pattern="yyyy-MM-dd"/><br/><br/>
					<strong style="display:inline-block;width:120px;text-align:right">上次登录时间<big>:</big></strong>&nbsp;&nbsp;&nbsp;<fmt:formatDate value="${acc.lastLoginTime }" pattern="yyyy-MM-dd"/><br/><br/>
					<strong style="display:inline-block;width:120px;text-align:right">过期时间<big>:</big></strong>&nbsp;&nbsp;&nbsp;<fmt:formatDate value="${acc.expiredTime }" pattern="yyyy-MM-dd"/><br/><br/>
					 <strong style="display:inline-block;width:120px;text-align:right">用户职级<big>:</big></strong>&nbsp;&nbsp;&nbsp;	
					       <c:forEach var="level" items="${lList }">
					            <c:if test="${level.levelCode== acc.postType}">
					              <td>${level.levelName}</td><br/><br/>
					            </c:if>
						   </c:forEach> 
					<strong style="display:inline-block;width:120px;text-align:right"><big></big></strong>&nbsp;&nbsp;&nbsp;<br/><br/>
				</li>
			</ul>
		</div>
		<div style="text-align:center">
			<button type="button" class="btn btn-submit btn-radius btn-search" style="position: initial;margin:0 auto;" onclick="javascript:history.back(-1)">返回</button>
		</div>
</body>
</html>