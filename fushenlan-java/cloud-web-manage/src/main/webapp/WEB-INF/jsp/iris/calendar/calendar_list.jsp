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
	<form id="PaperForm" action="${ctx}/manage/calendarTransaction/getTransactionByPage" method="get">

		<!-- S Filter Box -->
		<div class="filter-box">
			 <ul class="ui-form grid-row">
			<li class="col-md-4"><strong>消息标题</strong><input name="theme" id="theme" value ="${theme }"class="ipt-text" type="text" style="margin-left: 0px; placeholder=""></li>
				<li class="col-md-6"><strong style="margin-left: -114px;font-size: 16;">活动日期：</strong>
				     <input id="startTime" name="startTime" value="${startTime }" type="text" placeholder="开始时间" class="Wdate" style="max-width: 26%;" onfocus="WdatePicker({onpicked: function(){jQuery(this).trigger('change');},oncleared: function(){jQuery(this).trigger('change');}})">&nbsp;—&nbsp;
					 <input id="endTime" name="endTime" value="${endTime }" type="text" placeholder="结束时间" class="Wdate" style="max-width: 26%;" onfocus="WdatePicker({onpicked: function(){jQuery(this).trigger('change');},oncleared: function(){jQuery(this).trigger('change');}})">
				</li>
				<li class="col-md-3">
				    <strong>活动状态</strong>
					<select name="state" id="state">
					    <option value="">全部</option>
						<option value="0" <c:if test="${state eq '0'}">selected</c:if>>未发布</option>
						<option value="2" <c:if test="${state eq '2'}">selected</c:if>>已撤回</option>
						<option value="1" <c:if test="${state eq '1'}">selected</c:if>>已发布</option>
					</select>
				</li>
				
				<li class="col-md-8" style="text-align: right"><button type="submit" class="btn btn-submit btn-radius btn-search" onclick=""><i class="icon-search"></i> 查询</button></li>
			</ul>
		</div>
		<!-- E Filter Box -->
				<div class="tab-btn">
		<a class="btn btn-success btn-radius" href="javascript:;" onclick="addCalendar()" title="新增"><i class="icon-jia"></i>新增</a>
		</div>
		<table class="table table-agents table_public">
			<thead>
				<tr>
					<th>消息标题</th>
					<th>消息状态</th>
					<th>推送时间</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				
			<c:forEach items="${data.records}" var="calendar" varStatus="ext" >
                  <tr>
					<td>${calendar.theme}</td>
					<td>
						<c:if test="${calendar.state == 0  }">未发布 </c:if>
						<c:if test="${calendar.state == 1  }">已发布 </c:if>
						<c:if test="${calendar.state == 2  }">已撤回 </c:if>
					</td>
					<td>
					<fmt:formatDate value="${calendar.date }" pattern="yyyy-MM-dd"/>
					
					</td>
					<c:if test="${calendar.state != 1  }">
					<td>
						<a class="color-detail" href="#" onclick="editCalendar('${calendar.id}')"  title="修改">修改</a>
						<a class="color-detail" href="#" onclick="putCalendar('${calendar.id}')"  title="发布">发布</a>
						<a class="color-detail" href="#" onclick="delCalendar('${calendar.id}','${calendar.state }')"  title="删除">删除</a>
					</td>
					 </c:if>
					 <c:if test="${calendar.state == 1  }">
					<td>
						<a class="color-detail" href="#" onclick="backCalendar('${calendar.id}')"  title="撤回">撤回</a>
						<a class="color-detail" href="#" onclick="checkCalendar('${calendar.id}')"  title="查看">查看</a>
						<a class="color-detail" href="#" onclick="delCalendar('${calendar.id}','${calendar.state }')"  title="删除">删除</a>
						
					</td>
					 </c:if>
					 
				</tr>
                    </c:forEach>
               </tbody>
              </table>
         <div class="table-paging clearfix" id="pageDiv">
	</div>
</form>
<script type="text/javascript" src="${ctx}/resources/js/common/page.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/datepicker/WdatePicker.js"></script>
<script>    
$(function(){
	var theme='${theme}';
	var state ='${state}';
	var startTime ='${startTime}';
	var endTime ='${endTime}';
	pageAjax("${ctx}/manage/calendarTransaction/getTransactionByPage/listByPageAjax?theme="+theme+"&state="+state+"&startTime="+startTime+"&endTime="+endTime,'${data.pageSize}','${data.pageNo}','${data.pageTotal}','pageDiv');
})
	
	
   function pageNext(url){
		    	// 查询字段取页面加载时 model传入的值  防止分页执行查询
			var theme='${theme}';
			var state ='${state}';
			var startTime ='${startTime}';
			var endTime ='${endTime}';
		    	$.get(url,function(returnData){
		    		if(returnData.code == '1'){
		    			var html = "";
		    			$.each(returnData.data.records,function(i,row){
				   			row.groupId = row.groupId==null?'':row.groupId; //判断null字段
				   			row.createUser = row.createUser==null?'':row.createUser; //判断null字段
				   			codeArray[row.tagId] =codeArray[row.tagId] == undefined ?'':codeArray[row.tagId]; //判断undefined字段
				   			var tex=row.gmtModified;
				   			 var time=crtTimeFtt(tex);
				   			html+='<tr>'+
				   				 '<td>'+row.theme+'</td>';
				   				 if(row.state == 0){
				   					html+= '<td>未发布</td>';
				   				 }else if(row.state == 1){
				   					html+= '<td>已发布</td>';
				   				 }else if(row.state == 2){
				   					html+= '<td>已撤销</td>';
				   				 }else{
				   					html+= '<td></td>';
				   				 }
				   				 html +='<td>'+row.date+'</td>';
				   				 if(row.state == 1){
				   					html += '<td>'+
										'<a class="color-detail" href="#" onclick="backCalendar('+row.id+')"  title="撤回">撤回</a>'+
										'<a class="color-detail" href="#" onclick="checkCalendar('+row.id+')"  title="查看">查看</a>'+
										'<a class="color-detail" href="#" onclick="delCalendar('+row.id+'+'+row.state+')"  title="删除">删除</a>'+
									'</td>' 
				   				 }else{
				   					html += '<td>'+
									'<a class="color-detail" href="#" onclick="editCalendar('+row.id+')"  title="修改">修改</a>'+
									'<a class="color-detail" href="#" onclick="putCalendar('+row.id+')"  title="发布">发布</a>'+
									'<a class="color-detail" href="#" onclick="delCalendar('+row.id+'+'+row.state+')"  title="删除">删除</a>'+
								'</td>'
				   				 }
				   				 
				   			})
		    			$(".table tbody").html(html);
		    			pageAjax("${ctx}/manage/calendarTransaction/getTransactionByPage/listByPageAjax?theme="+theme+"&state="+state+"&startTime="+startTime+"&endTime="+endTime,'${data.pageSize}','${data.pageNo}','${data.pageTotal}','pageDiv');
		    		}
		    	})
}




function delCalendar(id ,state){
	if(state ==1){
		alert("发布状态无法删除");
		return 0;
	}else{
		 $.ajax({
			  async : false,  
		      cache : false,  
		      type: 'POST',
		   	  url: "${ctx}/manage/calendarTransaction/removeTransactionById",
		   	  data: {"id":id},
		   	  dataType: "json",
		   	  success: function(data){
		   		  alert("删除成功");
		   		window.location.reload();
		   	  }
		   });
	}
	
	
	
}
function addCalendar(){
	location.href="${ctx}/manage/calendarTransaction/toCalendarTransactionEditPage";
}


function editCalendar(id){
	location.href="${ctx}/manage/calendarTransaction/toCalendarTransactionEditPage?id="+id+"&state=0";
}

function putCalendar(id){
	var state = 1;
	 $.ajax({
		  async : false,  
	      cache : false,  
	      type: 'POST',
	   	  url: "${ctx}/manage/calendarTransaction/editTransaction",
	   	  data: {"id":id ,
	   		     "state":state},
	   	  dataType: "json",
	   	  success: function(data){
	   		  alert("发布成功");
	   		window.location.reload();
	   	  }
	   });
}

function backCalendar(id){
	var state = 2;
	 $.ajax({
		  async : false,  
	      cache : false,  
	      type: 'POST',
	   	  url: "${ctx}/manage/calendarTransaction/editTransaction",
	   	  data: {"id":id ,
	   		     "state":state},
	   	  dataType: "json",
	   	  success: function(data){
	   		  alert("撤回成功");
	   		window.location.reload();
	   	  }
	   });
}

function checkCalendar(id){
	location.href="${ctx}/manage/calendarTransaction/toCalendarTransactionEditPage?id="+id+"&state=1";
}


</script>
</body>
</html>