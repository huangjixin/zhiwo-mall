checked<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<title>富卫运维大平台</title>
<meta name="description" content="">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/elerning/style.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/elerning/font/iconfont.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/elerning/page.css">
</head>
<body>
   <form id="PaperForm" action="${ctx}/manage/paper/GetPrivatePaper" method="post">

		<!-- S Filter Box -->
		<div class="filter-box">
			<ul class="ui-form grid-row">
				<li class="col-md-12" style="text-align: right">
					<a class="btn btn-success btn-radius" href="${ctx}/manage/sms/updateSmsParameter"  title="新增">新增</a>
				<!-- 	<button type="button" class="btn btn-success btn-radius" onclick="deleteBatchPaper()">批量删除</button> -->
				</li>
			</ul>
		</div>
		<!-- E Filter Box -->
		
		<table class="table table-agents ">
			<thead>
				<tr>
					<th>
						<label class="pos-rel">
							<input type="checkbox" class="ace" id="selectAll">
						</label>
					</th>
					<th>序号</th>
					<th>参数名称</th>
					<th>参数因子</th>
					<th>参数因子类型</th>
					<th>备注</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				
				<c:forEach items="${data.records}" var="factor" varStatus="ext" >
                 <tr>
					<td>
						<label class="pos-rel">
							<input type="hidden" name="isShare" id="isShare" value="${paper.isShare}">
							<input type="checkbox" class="ace"  name="checkbox" value="${paper.id }">
						</label>
					</td>
					<td>${ext.index+1}</td>
					<td ><a href="#" class="clickable" >${factor.factorName}</a></td>
					<td>${factor.factor}</td>
					<td>
					<c:if test="${factor.factorType=='VALIDATE' }">验证类</c:if>
					<c:if test="${factor.factorType=='BUSNIESS' }">业务类</c:if>
					</td>
					<td>${factor.remark}</td>
					<td>
						<a class="color-detail" href="${ctx}/manage/sms/updateSmsParameter?id=${factor.id}"  title="修改">修改</a>
						<a class="color-edit"  onclick="deleSmsParameter('${factor.id}')" title="删除"  >删除</a>
					</td>
				</tr>
                    </c:forEach>
			</tbody>
		</table>
		<div class="table-paging clearfix" id="pageDiv">
		</div>
		</form>
<script type="text/javascript" src="${ctx}/resources/js/common/page.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/layer/layer.js"></script>



<script type="text/javascript">

$(function(){
	pageAjax("${ctx}/manage/sms/getParameterAjax?test="+"",'${data.pageSize}','${data.pageNo}','${data.pageTotal}','pageDiv');
})




	$("#selectAll").click(function(){//给全选按钮加上点击事件
	    var xz = $(this).prop("checked");//判断全选按钮的选中状态
	    var ck = $(".ace").prop("checked",xz);  //让class名为ace的选项的选中状态和全选按钮的选中状态一致。  
	    })

	//用于页面回显问题  视实际情况而定 
	 var codeArray ={};
	 codeArray['VALIDATE'] = '验证类';
	 codeArray['BUSNIESS'] = '业务类';
	 function pageNext(url){
	    	// 查询字段取页面加载时 model传入的值  防止分页执行查询
	    	$.get(url,function(returnData){
	    		if(returnData.code == '1'){
	    			var html = "";
	    			$.each(returnData.data.records,function(i,row){
	    	   			html+='<tr><td><label class="pos-rel"><input type="checkbox" class="ace"  name="checkbox" value="'+row.id+'"></label></td>'+
	    	   				 '<td>'+(i+1)+'</td>'+
	    	   				 '<td>'+row.factorName+'</td>'+
	    	   				 '<td>'+row.factor+'</td>'+
	    	   				 '<td>'+codeArray[row.factorType]+'</td>'+
	    	   				'<td>'+row.remark+'</td>'+
	    	   				 '<td><a class="color-detail" onclick="update('+'\''+row.id+'\''+')"  title="修改">修改</a><a class="color-edit" onclick="deleSmsParameter('+'\''+row.id+'\''+')"  title="删除"  >删除</a></td>';
	    	   			})
	    			$(".table tbody").html(html);
	    			pageAjax("${ctx}/manage/sms/getParameterAjax?test="+"",returnData.data.pageSize,returnData.data.pageNo,returnData.data.pageTotal,'pageDiv');
	    		}
	    	})
	    }
	
	 function deleSmsParameter(id){
		  $.ajax({
   				  async : false,  
   			      cache : false,  
   			      type: 'GET',
   		    	  url: "${ctx }/manage/sms/deleSmsParameter?id="+id,
   		    	  //dataType: "json",
   		    	  success: function(data){
   		    		  if(data.code==1){
   		    			  
   		    			  layer.confirm(data.msg, {
   		    	   				icon: 6,
   		    	   			  	btn: ['确定'] //按钮
   		    	   			}, function(){
   		    	   				location.href="${ctx}/manage/sms/getParameter";
   		    	   			}); 
   		    		  }else{
   		    			  layer.confirm(data.msg, {
   		    	   				icon: 3,
   		    	   			  	btn: ['确定'] //按钮
   		    	   			}, function(){
   		    	   			location.href="${ctx}/manage/sms/getParameter";
   		    	   			}); 
   		    			  
   		    		  }
   		    	  },
   		    	  error: function (data) {//请求失败处理函数  
   		    		  layer.confirm(data.msg, {
   	    	   				icon: 3,
   	    	   			  	btn: ['确定'] //按钮
   	    	   			}, function(){
   	    	   			location.href="${ctx}/manage/sms/getParameter";
   	    	   			}); 
   			        },
   		    	  
   		    	});
	 }
	 function update(id){
		location.href="${ctx}/manage/sms/updateSmsParameter?id="+id;
	 }
</script>
</body>
</html>
