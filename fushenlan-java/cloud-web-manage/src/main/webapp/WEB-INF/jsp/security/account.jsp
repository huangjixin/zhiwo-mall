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
		
		<script type=”text/javascript” src="${ctx}/resources/js/common/jquery-3.2.1.min.js" ></script>
		<script type="text/javascript" src="${ctx}/resources/libs/layer/layer.js" ></script>
		<div class="filter-box">
			<ul class="ui-form grid-row">
				<li class="col-md-4"><strong>姓名</strong><input id="name" name="id" value="${accountName}" class="ipt-text" type="text" placeholder=""></li>
				<li class="col-md-4"><strong>手机号码</strong><input id="phone" name="id" value="${telephone}" class="ipt-text" type="text" placeholder=""></li>
				<li class="col-md-4" style="text-align: right">
					<button type="button" class="btn btn-submit btn-radius" onclick="ftr()" style="position:relative;top:0;"> 查询</button>
					
				</li>
			</ul>
		</div>
		<div class="tab-btn">
		<button type="button" class="btn btn-success btn-radius" onclick="insert()"><i class="icon-jia"></i>添加</button>	
		</div>
		
		<table class="table table-agents">
			<thead>
				<tr>
					<th>用户姓名</th>
					<th>用户职级</th>
					<th>总公司名字</th>
					<th>角色</th>
					<th>手机号</th>
					<th>邮箱</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
	            <c:if test="${not empty page}">
	             	<c:forEach  varStatus="idx" var="account" items="${page.records}">
						<tr>
							<td>${account.accountName }</td>
							<td>${account.postName }</td>
							<td>${account.companyName }</td>
							<td>${account.roles }</td>
							<td>${account.mobile }</td>
							<td>${account.email }</td>
							<td style="width: 150px;">
								<a class="color-detail" href="${ctx }/manageAccounts/accountFindById?id=${account.id }" title="查看详情">查看</a>
								<a class="color-edit" href="${ctx }/manageAccounts/update?id=${account.id }" title="修改">修改</a>
								<a class="color-edit" href="#" title="删除" onclick="deleteAccount('${account.id }')">删除</a>
								<a class="color-edit" href="#" onclick="fuquan('${account.id }')" title="用户赋权">赋权</a>
								<a class="color-edit" href="#" onclick="resert('${account.id }')" title="重置密码">重置</a>
							</td>
						</tr>
							<%-- </c:if> --%>
					</c:forEach>
			    </c:if>
			</tbody>
		</table>
		<div class="table-paging clearfix" id="pageDiv">
			
		</div>
		<script src="${ctx }/resources/js/common/page.js" type="text/javascript"></script>
		<script>
		$(function(){
			var accountName = '${accountName}';
			var telephone ='${telephone}';
			pageAjax("${ctx}/manageAccounts/listByPageAjax?accountName="+accountName+"&telephone="+telephone,'${page.pageSize}','${page.pageNo}','${page.pageTotal}','pageDiv');
		});
		
		
		//ajax 分页 拼接数据
		function pageNext(url){
			// 查询字段取页面加载时 model传入的值  防止分页执行查询
			var accountName = '${accountName}';
			var telephone ='${telephone}';
			$.get(url,function(returnData){
				if(returnData.code == '1'){
					var html = "";
					$.each(returnData.data.records,function(i,row){
						row.companyName = row.companyName==null?'':row.companyName; //判断null字段
						row.postName = row.postName==null?'':row.postName;
						row.roles = row.roles==null?'':row.roles;
				    html+='<tr>'+
				    	  '<td>'+row.accountName+'</td>'+
						  '<td>'+row.postName+'</td>'+
						  '<td>'+row.companyName+'</td>'+
						  '<td>'+row.roles+'</td>'+
						  '<td>'+row.mobile+'</td>'+
						  '<td>'+row.email+'</td>'+
						  '<td style="width: 150px;">'+
						  '<a class="color-detail" href="${ctx }/manageAccounts/accountFindById?id='+row.id+'" title="查看详情">查看</a>'+
						  '<a class="color-edit" href="${ctx }/manageAccounts/update?id='+row.id+'" title="修改">修改</a>'+
						  '<a class="color-edit" href="#" title="删除" onclick="deleteAccount(\''+row.id+'\')">删除</a>'+
						  '<a class="color-edit" href="#" onclick="fuquan(\''+row.id+'\')" title="用户赋权">赋权</a>'+
						  '<a class="color-edit" href="#" onclick="resert(\''+row.id+'\')" title="重置密码">重置</a>'+
						  '</td>'+
						  '</tr>';
					})
					$(".table tbody").html(html);
					pageAjax("${ctx}/manageAccounts/listByPageAjax?accountName="+accountName+"&telephone="+telephone,returnData.data.pageSize,returnData.data.pageNo,returnData.data.pageTotal,'pageDiv');
				}
			})
		}
		
		
		function ftr(){
			var accountName =$("#name").val();
			var telephone =$("#phone").val();
			location.href="${ctx}/manageAccounts/listByPages?accountName="+accountName+"&telephone="+telephone;
		}
		</script>
		<script type="text/javascript">
		function deleteAccount(obj) {
			if(confirm("确认要删除？")){
				$.ajax({  
			        async : false,  
			        cache : false,  
			        type: 'post',  
			        dataType : "json",  
			        data : {id : obj},
			        url: "${ctx}/manageAccounts/daleteAccounts",//请求的action路径  
			        success:function (data) {//请求成功处理函数  
			        		alert('删除成功！');
			        		window.location.href="${ctx}/manageAccounts/listByPages"
			        },  
			        error: function (data) {//请求失败处理函数  
			            alert('删除失败！');  
			        },  
			    }); 
			}
		}
		
		function resert(obj) {
			if(confirm("确认要重置密码为admin123吗？")){
				$.ajax({  
			        async : false,  
			        cache : false,  
			        type: 'post',  
			        dataType : "json",  
			        data : {id : obj},
			        url: "${ctx}/manageAccount/resetPassword",//请求的action路径  
			        success:function (data) {//请求成功处理函数  
			        		alert('重置密码成功！新密码为admin123');
			        		window.location.href="${ctx}/manageAccounts/listByPages"
			        },  
			        error: function (data) {//请求失败处理函数  
			            alert('重置密码失败！');  
			        },  
			    }); 
			}
		}
		function fuquan(id){
			layer.open({
				  type: 2,
				  title: "角色授权",
				  closeBtn: 1, //
				  shade: [0],
				  area: ['70%', '70%'],
				  content: "${ctx}/manageAccounts/openRoles?accountId="+id
				});
		}
		function insert() {
			window.location.href="${ctx }/manageAccounts/update"
		}
		function saveRole(accountId,jsonstr) {
			 $.ajax({
		    	  type: 'post',
		    	  url: "${ctx }/manageAccounts/insertAccountRole",
		    	  dataType: "json",
		    	  data: {"accountId":accountId,"jsonstr" : jsonstr},
		    	  success: function (data) {//请求失败处理函数  
			        	alert("成功");
		    		  	layer.closeAll();
			        },
			      error:function (data) {//请求失败处理函数  
			        	alert("失败");
			        },
		    	});
		}
		</script>
</body>
</html>