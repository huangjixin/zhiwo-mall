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
		<link rel="stylesheet" href="${ctx}/resources/libs/ztree/zTreeStyle.css" type="text/css">
        <link rel="stylesheet" type="text/css" href="${ctx}/resources/css/style.css">
		<script type="text/javascript" src="${ctx}/resources/js/common/jquery-3.2.1.min.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/common/page.js"></script>
		<script type="text/javascript" src="${ctx}/resources/libs/layer/layer.js" ></script>
		<div class="filter-box">
			<ul class="ui-form grid-row">
				<li class="col-md-3"><strong>姓名</strong><input id="name" name="id" value="${accountName}" class="ipt-text" type="text" placeholder=""></li>
				<li class="col-md-3"><strong>手机号码</strong><input id="phone" name="id" value="${telephone}" class="ipt-text" type="text" placeholder=""></li>
				<li class="col-md-6" style="text-align: right">
					<button type="button" class="btn btn-submit btn-radius btn-search" onclick="ftr()" style="position:relative;top:0;"> 查询</button>
					<button type="button" class="btn btn-submit btn-radius btn-search" onclick="selectUser()" style="position:relative;top:0;"><i class="icon-insert"></i>确认选择</button>
				</li>
				
			</ul>
		</div>
		
		<table class="table table-agents">
			<thead>
				<tr>
				    <label class="pos-rel">
                          <th><input type="checkbox" id="selectAll" class="ace"></th>
                    </label>
					<th>用户姓名</th>
					<th>用户职级</th>
					<th>角色</th>
					<th>手机号</th>
					<th>邮箱</th>
				</tr>
			</thead>
			<tbody class="td">
	            <c:if test="${not empty page}">
	             	<c:forEach  varStatus="idx" var="account" items="${page.records}">
						<tr>
                            <td><input id="childBox_${account.id }" name="checkbox" type="checkbox" class="ace" value="${account.id }" ></td>
							<td>${account.accountName }</td>
							<td>${account.postName }</td>
							<td>${account.roles }</td>
							<td>${account.mobile }</td>
							<td>${account.email }</td>
						</tr>
					</c:forEach>
			    </c:if>
			</tbody>
		</table>
		<!-- / E Table -->
		<div class="table-paging clearfix" id="pageDiv">
			
		</div>
		<!-- / E Table Paging -->
		<script src="${ctx }/resources/js/common/page.js" type="text/javascript"></script>
		<script>
			$(function(){
				
				$("#selectAll").click( function () { 
			        var obj = document.getElementById("selectAll"); 
			        var value = obj.checked;
			        if(value){
			            $(".td :input[type='checkbox']").each(function(i){
			                 this.checked=true;
			            });
			        }else{
			            $(".td :input[type='checkbox']").each(function(i){
			                 this.checked=false;
			            });
			        }   
			    });
				
				var accountName =$("#name").val();
				var telephone =$("#phone").val();
				page("${ctx}/manage/group/listUsers?accountName="+accountName+"&telephone="+telephone,'${page.pageSize}','${page.pageNo}','${page.pageTotal}','pageDiv')
			})
			
			//查询
			function ftr(){
				var accountName =$("#name").val();
				var telephone =$("#phone").val();
				location.href="${ctx}/manage/group/listUsers?accountName="+accountName+"&telephone="+telephone;
			}
			
			//确认选择 
			function selectUser () {
				var str="";
				var userName="";
		        $(".td :input[type='checkbox']").each(function(i){
		            if(this.checked==true){
		            	var rs=this.value;
		                str=str + rs + ",";
		                var name = $(this).parent().siblings(":first").text();
		                userName = userName + name+",";
		            }
		        });
		        if(str.length==0){
		        	layer.alert('先选择用户', {icon: 5});
		        	return;
		        }
		        str=str.substring(0,str.length-1);
		        userName = userName.substring(0,userName.length-1);
		        var oldUserStr = parent.document.getElementById("userStr").value;
		        if (oldUserStr != '') {
		        	parent.document.getElementById("userStr").value = oldUserStr+','+str;
		        } else {
		        	parent.document.getElementById("userStr").value = str;
		        }
		        var oldUserStrName = parent.document.getElementById("userStrName").value;
		        if (oldUserStrName != '') {
		        	parent.document.getElementById("userStrName").value = oldUserStrName+','+userName;
		        } else {
		        	parent.document.getElementById("userStrName").value = userName;
		        }
		        
		        closeLayer();
			}
		  //关闭弹框 
		  function closeLayer() {
                var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
                parent.layer.close(index);
            }
		</script>
    </body>
</html>