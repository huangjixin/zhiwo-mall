<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/elerning/style.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/elerning/font/iconfont.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/elerning/page.css">
</head>
<body>
<script type="text/javascript"	src="${ctx}/resources/libs/layer/layer.js"></script>
		<div class="form-detail">
		<div class="title"><strong>用量统计分析</strong></div>
			<div class="nav-tabs">
				<strong class="active">按月统计</strong>
				<strong id="channel">按通道来源统计</strong>
				<strong id="system">按请求系统来源统计</strong>
			</div>
			<!-- 单选题 -->
			
		<div class="tab-pane active" id="uniterming">
		<ul class="ui-form grid-row">
				<li class="col-md-6"><strong style="margin-left: -114px;font-size: 16;">选择月份：</strong>
				     <input id="startDateMonth" name="startDateMonth" value="${satrtTime }" type="text" placeholder="开始时间" class="Wdate" style="max-width: 26%;" onfocus="WdatePicker({onpicked: function(){jQuery(this).trigger('change');},oncleared: function(){jQuery(this).trigger('change');}})">&nbsp;—&nbsp;
					 <input id="endDateMonth" name="uploadTimeEnd" value="${endTime }" type="text" placeholder="结束时间" class="Wdate" style="max-width: 26%;" onfocus="WdatePicker({onpicked: function(){jQuery(this).trigger('change');},oncleared: function(){jQuery(this).trigger('change');}})">
				</li> 
				<li class="col-md-4" style="text-align: right"><button type="button" class="btn btn-submit btn-radius btn-search"  onclick="selectMonth()"><i class="icon-search"></i> 查询</button></li>
			</ul>
		 		
		<table class="table table-agents">
			<thead>
				<tr>
					<th>月份</th>
					<th>请求总次数</th>
					<th>成功次数</th>
					<th>失败次数</th>
					<th>未知(无状态)</th>
				</tr>
			</thead>
			<tbody class="Month">
				<c:forEach items="${smsMonth.records}" var="smsMonth" varStatus="ext">
	                    <tr>
	                    <td>${smsMonth.name }</td>
						<td>${smsMonth.smsCount }</td>
						<td>${smsMonth.smsSuccessCount }</td>
						<td>${smsMonth.smsFailCount }</td>
						<td>${smsMonth.smsUnknownCount }</td>
					</tr>
	            </c:forEach>
	            <tr>
	            </tr>
			</tbody>
		</table>
		
			  
			</div>
			
			<!-- 按通道来源统计 -->
			<div class="tab-pane" id="MultiSelect">
			<ul class="ui-form grid-row">
			<li class="col-md-4">
					<strong style="font-size: 16;left: -45px;">通道来源</strong>
					
						<select id="channelCode" name="channelCode" value="${channelCode }">
						<option value="">全部</option>
						    <c:forEach items="${channels}" var="channels" varStatus="ext">
						  <option value="${channels.code }"<c:if test="${channels.code==channelCode }">selected</c:if>>${channels.name }</option>
						</c:forEach>
						</select>
				</li>
				
				<li class="col-md-6"><strong style="left: -37px;font-size: 16;">选择时间：</strong>
				     <input id="startDateChannel" name="startDateChannel" value="${satrtTime }" type="text" placeholder="开始时间" class="Wdate" style="max-width: 26%;" onfocus="WdatePicker({onpicked: function(){jQuery(this).trigger('change');},oncleared: function(){jQuery(this).trigger('change');}})">&nbsp;—&nbsp;
					 <input id="endDateChannel" name="endDateChannel" value="${endTime }" type="text" placeholder="结束时间" class="Wdate" style="max-width: 26%;" onfocus="WdatePicker({onpicked: function(){jQuery(this).trigger('change');},oncleared: function(){jQuery(this).trigger('change');}})">
				</li> 
				<li class="col-md-4" style="text-align: right"><button type="button" class="btn btn-submit btn-radius btn-search"  onclick="selectChannel()"><i class="icon-search"></i> 查询</button></li>
			</ul>
		 		
			<table class="table table-agents">
			<thead>
				<tr>
					<th>通道来源</th>
					<th>请求总次数</th>
					<th>成功次数</th>
					<th>失败次数</th>
					<th>未知(无状态)</th>
				</tr>
			</thead>
			<tbody class="channel"> 
				<c:forEach items="${smsChannelCode.records}" var="smsChannelCode" varStatus="ext">
	                    <tr>
	                    <td>${smsChannelCode.name }</td>
						<td>${smsChannelCode.smsCount }</td>
						<td>${smsChannelCode.smsSuccessCount }</td>
						<td>${smsChannelCode.smsFailCount }</td>
						<td>${smsChannelCode.smsUnknownCount }</td>
					</tr>
	            </c:forEach>
	            <tr>
	            </tr>
			</tbody>
		</table>
		
			</div>
			<div class="tab-pane" id="judge">
				<ul class="ui-form grid-row">
			<li class="col-md-4">
					<strong style="font-size: 16;left: -45px;">系统来源：</strong>
					
						<select id="systemCode" name="systemCode" value="${systemCode }">
							<option value="">全部</option>
						    <c:forEach items="${SmsSystems}" var="SmsSystems" varStatus="ext">
						  <option value="${SmsSystems.code }" <c:if test="${SmsSystems.code==systemCode }">selected</c:if> >${SmsSystems.name }</option>
						</c:forEach>
						</select>
				</li>
				<li class="col-md-6"><strong style="margin-left: -114px;font-size: 16;">选择时间：</strong>
				     <input id="startDateSystem" name="startDateSystem" value="${satrtTime }" type="text" placeholder="开始时间" class="Wdate" style="max-width: 26%;" onfocus="WdatePicker({onpicked: function(){jQuery(this).trigger('change');},oncleared: function(){jQuery(this).trigger('change');}})">&nbsp;—&nbsp;
					 <input id="endDateSystem" name="endDateSystem" value="${endTime }" type="text" placeholder="结束时间" class="Wdate" style="max-width: 26%;" onfocus="WdatePicker({onpicked: function(){jQuery(this).trigger('change');},oncleared: function(){jQuery(this).trigger('change');}})">
				</li> 
				<li class="col-md-4" style="text-align: right"><button type="button" class="btn btn-submit btn-radius btn-search"  onclick="selectSystem()"><i class="icon-search"></i> 查询</button></li>
			</ul>
			<table class="table table-agents">
			<thead>
				<tr>	
					<th>请求系统来源</th>
					<th>请求总次数</th>
					<th>成功次数</th>
					<th>失败次数</th>
					<th>未知(无状态)</th>
				</tr>
			</thead>
			<tbody class="system">
				<c:forEach items="${smsSystemCode.records}" var="smsSystemCode" varStatus="ext">
	                    <tr>
	                    <td>${smsSystemCode.name }</td>
						<td>${smsSystemCode.smsCount }</td>
						<td>${smsSystemCode.smsSuccessCount }</td>
						<td>${smsSystemCode.smsFailCount }</td>
						<td>${smsSystemCode.smsUnknownCount }</td>
					</tr>
	            </c:forEach>
	            <tr>
	            </tr>
			</tbody>
		</table>
			</div>
		</div>
		<!-- / E Table Paging -->
		<script type="text/javascript" src="${ctx}/resources/libs/layer/layer.js"></script>
		<script type="text/javascript" src="${ctx}/resources/libs/datepicker/WdatePicker.js"></script>
		<script type="text/javascript">
	
		
		$('.nav-tabs').each(function(index, element) {
			var _obj = $(this);
			$(this).on('click', 'strong:not(.active)', function(){
				$(this).addClass('active').siblings('strong').removeClass('active');
				_obj.nextAll('.tab-pane').removeClass('active').eq($(this).index()).addClass('active')
			})
		});
		
		
		
	 
	 function selectMonth(){
		 var startDateMonth=$("#startDateMonth").val();
		 var endDateMonth=$("#endDateMonth").val();
		 $.ajax({
			  async : false,  
		      cache : false,  
		      type: 'POST',
		      data:{"satrtTime":startDateMonth,"endTime":endDateMonth},
	 	      url: "${ctx }/manage/sms/getSmsNumMonthAjsx",
	 	      dataType: "json",
	 	  success: function(returnData){
	 		 if(returnData.code==1){
	 			var html = "";
	 			$.each(returnData.data.records,function(i,row){
	    			html+='<tr><td>'+row.name+'</td>'+
	    				  '<td>'+row.smsCount+'</td>'+
	    				  '<td>'+row.smsSuccessCount+'</td>'+
	    				  '<td>'+row.smsFailCount+'</td>'+
	    				  '<td>'+row.smsUnknownCount+'</td>';
	    			})
	    			$(".Month").html(html);
	 		 }
	 	  },
	 	  
	 	});

	 }
	 
	 function selectChannel(){
		 var startDateChannel=$("#startDateChannel").val();
		 var endDateChannel=$("#endDateChannel").val();
		 var channelCode=$("#channelCode").val();
		 $.ajax({
			  async : false,  
		      cache : false,  
		      type: 'POST',
		      data:{"satrtTime":startDateChannel,"endTime":endDateChannel,"channelCode":channelCode},
	 	      url: "${ctx }/manage/sms/getSmsNumChannelAjsx",
	 	      dataType: "json",
	 	  success: function(returnData){
	 		 if(returnData.code==1){
	 			var html = "";
	 			$.each(returnData.data.records,function(i,row){
	    			html+='<tr><td>'+row.name+'</td>'+
	    				  '<td>'+row.smsCount+'</td>'+
	    				  '<td>'+row.smsSuccessCount+'</td>'+
	    				  '<td>'+row.smsFailCount+'</td>'+
	    				  '<td>'+row.smsUnknownCount+'</td>';
	    			})
	    			$(".channel").html(html);
	 		 }
	 	  },
	 	  
	 	});
	 }

	 
	 function selectSystem(){
		 var startDateSystem=$("#startDateSystem").val();
		 var endDateSystem=$("#endDateSystem").val();
		 var systemCode=$("#systemCode").val();
		 $.ajax({
			  async : false,  
		      cache : false,  
		      type: 'POST',
		      data:{"satrtTime":startDateSystem,"endTime":endDateSystem,"systemCode":systemCode},
	 	      url: "${ctx }/manage/sms/getSmsNumSystemAjsx",
	 	      dataType: "json",
	 	  success: function(returnData){
	 		 if(returnData.code==1){
	 			var html = "";
	 			$.each(returnData.data.records,function(i,row){
	    			html+='<tr><td>'+row.name+'</td>'+
	    				  '<td>'+row.smsCount+'</td>'+
	    				  '<td>'+row.smsSuccessCount+'</td>'+
	    				  '<td>'+row.smsFailCount+'</td>'+
	    				  '<td>'+row.smsUnknownCount+'</td>';
	    			})
	    			$(".system").html(html);
	 		 }
	 	  },
	 	  
	 	});
	 }
	 
	 
	 
	 $(function (){
		 var active="${active}";
		 if(null==active|| active==""){
			 return;
		 }
		 if(active=='channel'){
			 $("strong").removeClass('active');
			 $('.tab-pane').removeClass('active');
			 $("#channel").addClass('active');
			 $("#MultiSelect").addClass('active');
		 }
		 
		 if(active=='system'){
			 $("strong").removeClass('active');
			 $('.tab-pane').removeClass('active');
			 $("#system").addClass('active');
			 $("#judge").addClass('active');
		 }
		 
		 
	 })
</script>
</body>
</html>
