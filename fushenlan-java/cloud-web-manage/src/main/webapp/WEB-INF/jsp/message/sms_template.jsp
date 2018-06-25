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
<style type="text/css">
.form-detail {
    min-height: 51%;
    padding: 47px;
    background-color: #fff;
    border: 1px solid #dfdfdf;
    border-radius: 5px;
    width: 72%;
}

</style>


   <form id="PaperForm" action="${ctx}/manage/sms/getTemplate" method="post">

		<!-- S Filter Box -->
		<div class="filter-box">
			<ul class="ui-form grid-row">
				<li class="col-md-3"><strong>模板名称</strong><input  value="${title}" id="title" name="title" type="text" placeholder=""></li>
				<li class="col-md-3"><strong>模板编码</strong><input  value="${code}" id="code" name="code" type="text" placeholder=""></li>		
						<li class="col-md-4">
					<strong>黑名单类型：</strong>
					
						<select id="type" name="type" value="${type }">
						    <option <c:if test="${type==''}">selected</c:if> value="">请选择</option>
							<option value="BUSNIESS" <c:if test="${type=='BUSNIESS'}">selected</c:if>>业务类</option>
							<option value="VALIDATE" <c:if test="${type=='VALIDATE'}">selected</c:if>>验证类</option>
							<option value="ALL" <c:if test="${type=='ALL'}">selected</c:if>>全部</option>
						</select>
					
				</li>
				<li class="col-md-3"><strong>主通道</strong><input value="${masterCode}" id="masterCode" name="masterCode" type="text" placeholder=""></li>
				<li class="col-md-12" style="text-align: right">
					<button type="button" class="btn btn-submit btn-radius btn-search" onclick="selectTemplate()"><i class="icon-search"></i> 查询</button>
					<button type="button" class="btn btn-success btn-radius" onclick="addBacth()"><i class="icon-jia"></i>新增模板</button>
				</li>
			</ul>
		</div>
		<!-- E Filter Box -->
		
		<table class="table table-agents " style="table-layout:fixed; ">
			<thead>
				<tr>
					<th style="width:8%;">模板名称</th>
					<th style="width:8%;">模板编码</th>
					<th>模板类型</th>
					<th style="width:19%;">模板内容</th>
					<th>主通道</th>
					<th>备用通道</th>
					<th>创建时间</th>
					<th>主模板状态</th>
					<th>备用模板状态</th>
					<th style="width: 11%;">操作</th>
				</tr>
			</thead>
			<tbody>
				
				<c:forEach items="${data.records}" var="SmsTemplate" varStatus="ext"  >
                 <tr>
					<td style="white-space:nowrap;overflow:hidden;text-overflow: ellipsis;"><div class="autocut" title="${SmsTemplate.title}">${SmsTemplate.title}</td>
					<td style="white-space:nowrap;overflow:hidden;text-overflow: ellipsis;"><div class="autocut" title="${SmsTemplate.code}">${SmsTemplate.code}</td>
					<c:if test="${SmsTemplate.type=='BUSNIESS'}">
					<td>业务类</td>
					</c:if>
					<c:if test="${SmsTemplate.type=='VALIDATE'}">
					<td>验证类</td>
					</c:if>
					<c:if test="${SmsTemplate.type=='ALL'}">
					<td>全部</td>
					</c:if>
					<c:if test="${SmsTemplate.type=='' || null==SmsTemplate.type}">
					<td></td>
					</c:if>
					<td style="white-space:nowrap;overflow:hidden;text-overflow: ellipsis;"><div class="autocut" title="${SmsTemplate.content}">${SmsTemplate.content}</td>
					
					<td>${SmsTemplate.masterChannelName}</td>
					
					<td>${SmsTemplate.slaveChannelName}</td>
					<td><fmt:formatDate value="${SmsTemplate.createTime}" pattern="yyyy-MM-dd"/></td>
					<td>
					<c:if test="${SmsTemplate.masterStatus=='0'}">审核中</c:if>
					<c:if test="${SmsTemplate.masterStatus=='1'}">审核通过</c:if>
					<c:if test="${SmsTemplate.masterStatus=='2'}">审核未通过</c:if>
					</td>
					<td>
					<c:if test="${SmsTemplate.slaveStatus=='0'}">审核中</c:if>
					<c:if test="${SmsTemplate.slaveStatus=='1'}">审核通过</c:if>
					<c:if test="${SmsTemplate.slaveStatus=='2'}">审核未通过</c:if>
					</td>
					<td>
						<a class="color-detail"  onclick="edit('${SmsTemplate.id}')" title="修改">编辑</a>
						<a class="color-detail"  onclick="dele('${SmsTemplate.id}')" title="修改">删除</a>
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
	var title= $("#title").val();
	var masterCode= $("#masterCode").val();
	var code= $("#code").val();
	var type= $("#type").val();
	pageAjax("${ctx}/manage/sms/getTemplateAjsx?title="+title+"&masterCode="+masterCode+"&code="+code+"&type="+type,'${data.pageSize}','${data.pageNo}','${data.pageTotal}','pageDiv');
})

	var codeArray ={};
    codeArray['BUSNIESS'] = '业务类';
    codeArray['VALIDATE'] = '验证类';
    codeArray['masterStatus0'] = '审核中';
    codeArray['masterStatus1'] = '审核通过';
    codeArray['masterStatus2'] = '审核未通过';
    codeArray['slaveStatus0'] = '审核中';
    codeArray['slaveStatus1'] = '审核通过';
    codeArray['slaveStatus2'] = '审核未通过';
		
		 function pageNext(url){
    	// 查询字段取页面加载时 model传入的值  防止分页执行查询
   			var title= $("#title").val();
			var masterCode= $("#masterCode").val();
			var code= $("#code").val();
			var type= $("#type").val();
    	$.get(url,function(returnData){
    		if(returnData.code == '1'){
    			var html = "";
    			$.each(returnData.data.records,function(i,row){
    				row.content = row.content==null?'':row.content; //判断null字段
    				var tex=row.createTime;
		   			 var time=crtTimeFtt(tex);
        			html+='<tr>'+
        				  '<td style="white-space:nowrap;overflow:hidden;text-overflow: ellipsis;"><div class="autocut" title="'+row.title+'">'+row.title+'</td>'+
        				  '<td style="white-space:nowrap;overflow:hidden;text-overflow: ellipsis;"><div class="autocut" title="'+row.code+'">'+row.code+'</td>'+
        				  '<td>'+codeArray[row.type]+'</td>'+
        				  '<td style="white-space:nowrap;overflow:hidden;text-overflow: ellipsis;"><div class="autocut" title="'+row.content+'">'+row.content+'</td>'+
        				  '<td>'+row.masterChannelName+'</td>'+
        				  '<td>'+row.slaveChannelName+'</td>'+
        				  '<td>'+time+'</td>'+
        				  '<td>'+codeArray['masterStatus'+row.masterStatus]+'</td>'+
        				  '<td>'+codeArray['slaveStatus'+row.slaveStatus]+'</td>'+
        				  '<td class="handleTd"><a class="color-detail"  onclick="edit('+'\''+row.id+'\''+')" title="修改">编辑</a><a class="color-detail"  onclick="dele('+'\''+row.id+'\''+')" title="删除">删除</a></td>';
        			})
    			$(".table tbody").html(html);
    			pageAjax("${ctx}/manage/sms/getTemplateAjsx?title="+title+"&masterCode="+masterCode+"&code="+code+"&type="+type,returnData.data.pageSize,returnData.data.pageNo,returnData.data.pageTotal,'pageDiv');
    		}
    	})
    }

function selectTemplate(){
	var title= $("#title").val();
	var masterCode= $("#masterCode").val();
	var code= $("#code").val();
	var type= $("#type").val();
	$.ajax({
		  async : false,  
	      cache : false,  
	      type: 'POST',
	      data:{"title":title,"masterCode":masterCode,"code":code,"type":type},
		      url: "${ctx }/manage/sms/getTemplateAjsx",
		      dataType: "json",
		  success: function(returnData){
			 if(returnData.code==1){
				var html = "";
				$.each(returnData.data.records,function(i,row){
					row.content = row.content==null?'':row.content; //判断null字段
    				var tex=row.createTime;
		   			 var time=crtTimeFtt(tex);
        			html+='<tr>'+
        				  '<td>'+row.title+'</td>'+
        				  '<td>'+row.code+'</td>'+
        				  '<td>'+codeArray[row.type]+'</td>'+
        				  '<td>'+row.content+'</td>'+
        				  '<td>'+row.masterChannelName+'</td>'+
        				  '<td>'+row.slaveChannelName+'</td>'+
        				  '<td>'+time+'</td>'+
        				  '<td class="handleTd"><a class="color-detail"  onclick="edit('+row.id+')" title="修改">编辑</a><a class="color-detail"  onclick="dele('+row.id+')" title="修改">删除</a></td>';
        			})
    			$(".table tbody").html(html);
				pageAjax("${ctx}/manage/sms/getTemplateAjsx?title="+title+"&masterCode="+masterCode+"&code="+code+"&type="+type,returnData.data.pageSize,returnData.data.pageNo,returnData.data.pageTotal,'pageDiv');
    		}
		  },
		  
		});
}



	function edit(id){
	location.href="${ctx}/manage/sms/editTemplateById?idl="+id;
	}
	 function addBacth(){
		 location.href="${ctx}/manage/sms/editTemplateById";
	 }   
	   function dele(id){
		
		
		  $.ajax({
			  async : false,  
		      cache : false,  
		      type: 'GET',
	    	  url: "${ctx }/manage/sms/deleTemplateById?idl="+id,
	    	  success: function(data){
	    		  if(data.code=='1'){
	    			  layer.confirm("删除成功", {
			   				icon: 6,
			   			  	btn: ['确定'] //按钮
			   			}, function(){
			   			  location.href="${ctx}/manage/sms/getTemplate";
			   			}); 
	    		  }else{
	    			  layer.confirm(data.msg, {
			   				icon: 3,
			   			  	btn: ['确定'] //按钮
			   			}, function(){
			   			  location.href="${ctx}/manage/sms/getTemplate";
			   			}); 
	    		  }
	    	  },
	    	  error: function (data) {//请求失败处理函数  
	    		  layer.confirm(data.msg, {
		   				icon: 3,
		   			  	btn: ['确定'] //按钮
		   			}, function(){
		   			  location.href="${ctx}/manage/sms/getTemplate";
		   			}); 
		        },
	    	  
	    	});
	}
	   function crtTimeFtt(val) {
		    if (val != null) {
		            var date = new Date(val);
		            return date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate();
		        }
		}
</script>
</body>
</html>
