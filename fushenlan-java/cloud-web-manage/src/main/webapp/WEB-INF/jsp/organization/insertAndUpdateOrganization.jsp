<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>富卫运维大平台</title>
</head>
<body>
<div>

<form id="OrganizationForm" <%-- action="${ctx}/updateOrganization" --%> method="post">
		<table class="table">
		    	<tr>
		    		<td>组织编号</td>
		    		<td>
		    		<div>
							保存后系统自动生成<input type="hidden" name="id" value="${organization.id }">
							<input type="hidden" name="parentId" value="${parentId }">
							<input type="hidden" name="layer" value="${layer }">
							<input type="hidden" name="rootId" value="${rootId }">
					</div>
		    		</td>
		    	</tr>
		    	<tr class="notContent">
		    		<td>机构代码</td>
		    		<td><input name="code" value="${organization.code }"id="organization_title"/></td>
		    	</tr>
		    	<tr>
		    		<td>机构名称</td>
		    		<td>
		    			<input name="cnName" value="${organization.cnName}">				    			
		    		</td>
		    	</tr>
		    	<tr>
		    		<td>外文名称</td>
		    		<td>
		    			<input name="enName" value="${organization.enName }">					    			
		    		</td>
		    	</tr>
				<tr>
		    		<td>企业id</td>
		    		<td>
		    			<input name="companyId" value="${organization.companyId }">					    			
		    		</td>
		    	</tr>
				<tr>
		    		<td>机构地址</td>
		    		<td>
		    			<input name="address" value="${organization.address }">					    			
		    		</td>
		    	</tr>
				<tr>
		    		<td>机构类型</td>
		    		<td>
		    			<input name="type" value="${organization.type }">					    			
		    		</td>
		    	</tr><tr>
		    		<td>是否可用</td>
		    		<td>
		    			<input name="enabled" value="${organization.enabled }">					    			
		    		</td>
		    	</tr>
				<tr>
		    		<td>邮编</td>
		    		<td>
		    			<input name="postCode" value="${organization.postCode }">					    			
		    		</td>
		    	</tr>
				<tr>
		    		<td>电话号码</td>
		    		<td>
		    			<input name="phoneNumber" value="${organization.phoneNumber }">					    			
		    		</td>
		    	</tr>
				<tr>
		    		<td>传真</td>
		    		<td>
		    			<input name="faxNumber" value="${organization.faxNumber }">					    			
		    		</td>
		    	</tr>
				<tr>
		    		<td>机构描述</td>
		    		<td>
		    			<input name="remark" value="${organization.description }">					    			
		    		</td>
		    	</tr>
		    	<tr align="center">
		    		<td colspan="2">
		    		<button type="button" onclick="saveOrganization()">保存</button>
		    		</td>
		    	</tr>
		    </table>
		</form>
</div>
<script type="text/javascript" src="${ctx}/resources/libs/layer/layer.js"></script>
<script type="text/javascript">
function saveOrganization(){
	$.ajax({
  	  url: "${ctx }/manage/insertAndUpdateOrganization",
  	  dataType: "json",
  	  data :$('#OrganizationForm').serialize(),
  	  success: function(result) {
		if (result.code=="true") {
			layer.alert("保存成功", {icon: 1},function(){
				parent.location.href="${ctx}/manage/organizationJsp";
			});
		}else{
			layer.alert("保存失败!"+result.msg, {icon: 1});
			canSave=0;
		}
	  }
  	})
	/* $("#organizationForm").ajaxSubmit({	
		success: function(data) {
			if (data == 'false') {
				layer.alert("保存失败", {icon: 1});
				canSave=0;
			}else{
				layer.alert("保存成功", {icon: 1},function(){
					parent.location.href="${ctx}/organizationJsp";
				});
			}
		}
	}); */
}
</script>
</body>


</html>