<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>

</head>
<body>


<style>
.handleTd{
text-align: center;
}

</style>


	<form id="smsNews" action="${ctx}/manage/sms/getSmsNews" method="get">

		<!-- S Filter Box -->
		<div class="filter-box">
			<ul class="ui-form grid-row">
				<li class="col-md-4"><strong>发送内容</strong><input  id="content" name="content" value="${content}" type="text" placeholder=""></li>
				<li class="col-md-4" style="margin-left: -91px;"><strong>接收号码</strong><input  id="phone" name="phone" value="${phone}" type="text" placeholder=""></li>
				<li class="col-md-4">
					<strong>发送状态</strong>
					
						<select id="status" name="status" value="${status }">
						    <option <c:if test="${status==''}">selected</c:if> value="">全部</option>
							<option value="1" <c:if test="${status=='1'}">selected</c:if>>发送成功</option>
							<option value="2" <c:if test="${status=='2'}">selected</c:if>>发送失败</option>
							<option value="3" <c:if test="${status=='3'}">selected</c:if>>未知</option>
						</select>
				</li>
				<li class="col-md-4">
					<strong>系统来源：</strong>
					
						<select name="systemCode" id="systemCode" >
						<option value="">全部</option>
							<c:forEach items="${factors}" var="factors" varStatus="ext">
						  <option value="${factors.code }" <c:if test="${factors.code==systemCode}">selected</c:if>   >${factors.name }</option>
						</c:forEach>
						</select>
				</li>
				<li class="col-md-4" style="margin-left: -92px;">
					<strong>通道来源：</strong>
					
						<select name="channelCode" id="channelCode" >
						<option value="">全部</option>
							<c:forEach items="${channels}" var="channels" varStatus="ext">
						  <option value="${channels.code }" <c:if test="${channels.code==channelCode}">selected</c:if>>${channels.name }</option>
						</c:forEach>
						</select>
				</li>
				
				<li class="col-md-8" style="text-align: right"><button type="button" class="btn btn-submit btn-radius btn-search" onclick="selectNews()"><i class="icon-search"></i> 查询</button></li>
			</ul>
		</div>
		<!-- E Filter Box -->
		
		<table class="table table-agents table_public" style="table-layout:fixed; ">
			<thead>
				<tr>
					<th style="width: 6%;">序号</th>
					<th style="width:19%;">发送内容</th>
					<th>接收号码</th>
					<th>发送状态</th>
					<th>返回结果</th>
					<th>发送时间</th>
					<th>系统来源</th>
					<th>通道</th>
					<th>送达数|目标数</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				
			<c:forEach items="${data.records}" var="smsNews" varStatus="ext" >
                  <tr>
                  	<td style="width: 6%;">${ext.index+1}</td>
					<td style="white-space:nowrap;overflow:hidden;text-overflow: ellipsis;"><div class="autocut" title="${smsNews.content}">${smsNews.content}</td>
					<td>${smsNews.phone}</td>
					<c:if test="${smsNews.status==0 }">
					<td>全部</td>
					</c:if>
					<c:if test="${smsNews.status==1 }">
					<td>发送成功</td>
					</c:if>
					<c:if test="${smsNews.status==2 }">
					<td>发送失败</td>
					</c:if>
					<c:if test="${smsNews.status==3 }">
					<td>未知</td>
					</c:if>
					<c:if test="${smsNews.status=='' || null==smsNews.status}">
					<td></td>
					</c:if>
					<td>${smsNews.result}</td>
					<td><fmt:formatDate value="${smsNews.sendTime}" pattern="yyyy-MM-dd"/></td>
					<td>${smsNews.systemName}</td>
					<td>${smsNews.channelName}</td>
					<td>${smsNews.finishCount}|${smsNews.targetCount}</td>
					<td class="handleTd">
						<a class="color-detail" href="#" onclick="selectPaper('${smsNews.id}')"  title="查看">查看</a>
						<%-- <c:if test="${smsNews.status==2}" >
						<a class="color-detail" href="#" onclick="selectPaper('${paper.id}')"  title="重发">重发</a>
						</c:if> --%>
						
					</td>
				</tr>
                    </c:forEach>
			</tbody>
		</table>
		<div class="table-paging clearfix" id="pageDiv">
		</div>
		</form>
		
		<script type="text/javascript" src="${ctx}/resources/js/common/page.js"></script>
		<script type="text/javascript">
		$(function(){
			var content= $("#content").val();
			var phone = $("#phone").val();
			var status = $("#status").val();
			var systemCode = $("#systemCode").val();
			var channelCode = $("#channelCode").val();
			pageAjax("${ctx}/manage/sms/getSmsNewsAjsx?content="+content+"&phone="+phone+"&status="+status+"&systemCode="+systemCode+"&channelCode="+channelCode,'${data.pageSize}','${data.pageNo}','${data.pageTotal}','pageDiv');
		})
		 var codeArray ={};
    codeArray['0'] = '全部';
    codeArray['1'] = '发送成功';
    codeArray['2'] = '发送失败';
    codeArray['3'] = '未知';
		
		 function pageNext(url){
    	// 查询字段取页面加载时 model传入的值  防止分页执行查询
   			var content= $("#content").val();
			var phone = $("#phone").val();
			var status = $("#status").val();
			var systemCode = $("#systemCode").val();
			var channelCode = $("#channelCode").val();
    	$.get(url,function(returnData){
    		if(returnData.code == '1'){
    			var html = "";
    			$.each(returnData.data.records,function(i,row){
    				var tex=row.sendTime;
		   			 var time=crtTimeFtt(tex);
        			html+='<tr>'+
        				  '<td>'+(i+1)+'</td>'+
        				  '<td>'+row.content+'</td>'+
        				  '<td>'+row.phone+'</td>'+
        				  '<td>'+codeArray[row.status]+'</td>'+
        				  '<td>'+row.result+'</td>'+
        				  '<td>'+time+'</td>'+
        				  '<td>'+row.systemName+'</td>'+
        				  '<td>'+row.channelName+'</td>'+
        				  '<td>'+row.finishCount+'|'+row.targetCount+'</td>'+
        				  '<td class="handleTd"><a class="color-detail" href="#" onclick="selectPaper('+'\''+row.id+'\''+')"  title="查看">查看</a></td>';
        			})
    			$(".table tbody").html(html);
    			pageAjax("${ctx}/manage/sms/getSmsNewsAjsx?content="+content+"&phone="+phone+"&status="+status+"&systemCode="+systemCode+"&channelCode="+channelCode,returnData.data.pageSize,returnData.data.pageNo,returnData.data.pageTotal,'pageDiv');
    		}
    	})
    }
		
		function selectNews(){
			var content= $("#content").val();
			var phone = $("#phone").val();
			var status = $("#status").val();
			var systemCode = $("#systemCode").val();
			var channelCode = $("#channelCode").val();
			$.ajax({
				  async : false,  
			      cache : false,  
			      type: 'POST',
			      data:{"content":content,"phone":phone,"status":status,"systemCode":systemCode,"channelCode":channelCode},
				      url: "${ctx }/manage/sms/getSmsNewsAjsx",
				      dataType: "json",
				  success: function(returnData){
					 if(returnData.code==1){
						var html = "";
						$.each(returnData.data.records,function(i,row){
		    				var tex=row.sendTime;
				   			 var time=crtTimeFtt(tex);
		        			html+='<tr>'+
		        				  '<td>'+(i+1)+'</td>'+
		        				  '<td>'+row.content+'</td>'+
		        				  '<td>'+row.phone+'</td>'+
		        				  '<td>'+codeArray[row.status]+'</td>'+
		        				  '<td>'+row.result+'</td>'+
		        				  '<td>'+time+'</td>'+
		        				  '<td>'+row.systemName+'</td>'+
		        				  '<td>'+row.channelName+'</td>'+
		        				  '<td>'+row.finishCount+'|'+row.targetCount+'</td>'+
		        				  '<td class="handleTd"><a class="color-detail" href="#" onclick="selectPaper('+'\''+row.id+'\''+')"  title="查看">查看</a></td>';
		        			})
						$(".table tbody").html(html);
						pageAjax("${ctx}/manage/sms/getSmsNewsAjsx?content="+content+"&phone="+phone+"&status="+status+"&systemCode="+systemCode+"&channelCode="+channelCode,returnData.data.pageSize,returnData.data.pageNo,returnData.data.pageTotal,'pageDiv');
					 }
				  },
				  
				});
		}
		
		
		function selectPaper(id){
			location.href="${ctx}/manage/sms/selectSmsNewsById?idl="+id+"&type=BUSNIESS";
			
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
