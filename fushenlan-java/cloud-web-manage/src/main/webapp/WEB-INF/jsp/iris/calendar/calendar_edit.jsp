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
	    <div class="form-detail offline_form"> 
		  <form id="inputForm"  method="post" class="form-horizontal" >
		   <input type="hidden" name="id" id="id" value="${calendarTransaction.id}"/>
			 <div class="title diff_btn" id="info">
				<strong>新建推送消息</strong>
			</div>
			<ul class="clearfix form_learn">
				<li class="col-md-12"><strong><ins>*</ins>消息标题：</strong><input type="text"  name="theme" id = "theme" class="ipt-text" value="${calendarTransaction.theme }"  ></li>
				<li class="col-md-12"><strong><ins>*</ins>消息内容：</strong><input type="text"  name="transactionDesc" id = "transactionDesc" class="ipt-text" value="${calendarTransaction.transactionDesc }" ></li>
			<%-- 	<li class="col-md-12" ><strong><ins>*</ins>类型：</strong>
			        <select name="transactionType" style="width:120px;border:1px solid #333" >
						<option value="">全部</option>
						<option value="0" <c:if test="${transactionType eq '0'}">selected</c:if>>会议邀请</option>
						<option value="1" <c:if test="${transactionType eq '1'}">selected</c:if>>客户拜访</option>
						<option value="2" <c:if test="${transactionType eq '2'}">selected</c:if>>日常备忘</option>
						<option value="3" <c:if test="${transactionType eq '3'}">selected</c:if>>消息列表</option>
						<option value="3" <c:if test="${transactionType eq '3'}">selected</c:if>>其他</option>
					</select>
				</li> 
				<li class="col-md-12" id="san"><strong><ins>*</ins>作者：</strong><input type="text" name="createUser" class="ipt-text" value="${nvo.activity.externalLinkUrl }"></li>
				<li class="col-md-12"><ins>*</ins><strong> 详细来源：</strong><input type="text" name="content" class="ipt-text" value="${nvo.activity.content }"></li>
				<li class="col-md-12"><ins>*</ins><strong>发布时间：</strong>
				     <input id="startTime" name="startTime" value="${startTime }" type="text" placeholder="开始时间" class="Wdate" style="max-width: 26%;" onfocus="WdatePicker({onpicked: function(){jQuery(this).trigger('change');},oncleared: function(){jQuery(this).trigger('change');}})">&nbsp;—&nbsp;
					 <input id="endTime" name="endTime" value="${endTime }" type="text" placeholder="结束时间" class="Wdate" style="max-width: 26%;" onfocus="WdatePicker({onpicked: function(){jQuery(this).trigger('change');},oncleared: function(){jQuery(this).trigger('change');}})">
				</li> --%>
				<li class="col-md-12">
					<strong>推送时间：</strong>
					<label for="allow" style="padding-left: 120px;"><input type="radio" id="date" value="1" name="date" <c:if test="${calendarTransaction.sendType==1 }">checked="checked"</c:if>>立即推送</label>
					<label for="notAllow"><input type="radio" id="date" value="2" name="date" <c:if test="${calendarTransaction.sendType==2 }">checked="checked"</c:if>>预定时间</label>
				   <input id="startTime" name="startTime"  value= "<fmt:formatDate value="${calendarTransaction.date }" pattern = "yyyy-MM-dd"/>" type="text" placeholder="yyyy-MM-dd" class="Wdate" style="max-width: 26%;" onfocus="WdatePicker({onpicked: function(){jQuery(this).trigger('change');},oncleared: function(){jQuery(this).trigger('change');}})">
				</li>
				
				<li class="col-md-12">
					<strong>推送设置：</strong>
					<label for="allow" style="padding-left: 120px;"><input type="radio" id="specifyType" value="1" name="send" <c:if test="${calendarTransaction.specifyType==1 }">checked="checked"</c:if>>不限定人数</label>
					<label for="notAllow"><input type="radio" id="specifyType" value="2" name="send" <c:if test="${calendarTransaction.specifyType==2 }">checked="checked"</c:if>>指定人数</label>
				   	<button type="button" class="btn btn-submit"  onclick="choose()">选择</button>
				</li>
				
			</ul>
			<c:if test="${state != 1 }">
			<div class="ui-button">
			    <button type="button" class="btn btn-default"  onclick="javascript:history.go(-1);">取消</button>
				<button type="button" class="btn btn-submit"  onclick="save()">保存</button>
			    <button type="button" class="btn btn-submit"  onclick="sendMsg()">发布</button>
			</div>
			</c:if>
		</form>
		</div>
		<script type="text/javascript" src="${ctx}/resources/libs/datepicker/WdatePicker.js"></script>
		<script type="text/javascript">
		function sendMsg(){
			var id = $("#id").val();
			
			if(id == ""){
				 var specifyType = "";
		    	 var specifyTypes = document.getElementsByName("send");
		    	 var state = 1;
			   	 for(var i=0;i<specifyTypes.length;i++){
			    	 if(specifyTypes[i].checked){
			    		 specifyType = specifyTypes[i].value
		    		 }
			   	 }
		    	 var date = "";
		    	 var dates = document.getElementsByName("date");
		    	 for(var i=0;i<dates.length;i++){
			    	 if(dates[i].checked){
			    		 date = dates[i].value
		    			 }
			     }
		    	 
		    	 var theme = $("#theme").val();
		    	 var transactionDesc = $("#transactionDesc").val();
		    	 var sendType =  2;
		    	 if(date == 1){
		    		 date = new Date().Format("yyyy-MM-dd");
		    		 sendType = 1;
		    	 }else{
		    		 date = $("#startTime").val();
		    	 }
		    	 
		    	 
			        $.ajax({
						type : "post",
					    async: false,
						data:{
							"specifyType":specifyType,
							"date":date,
							"theme":theme,
							"transactionDesc":transactionDesc,
							"sendType":sendType,
							"state":state
						},
						url : "${ctx}/manage/calendarTransaction/addTransaction",
						success : function(data) {
							if (data.code ==1) {
								alert("保存成功");
								location.href= "${ctx}/manage/calendarTransaction/getTransactionByPage";
							}else {
								layer.alert("保存失败", {icon: 5});
							}
						}
					  });
			}else{
				var specifyType = "";
		    	 var specifyTypes = document.getElementsByName("send");
		    	 var state = 1;
			   	 for(var i=0;i<specifyTypes.length;i++){
			    	 if(specifyTypes[i].checked){
			    		 specifyType = specifyTypes[i].value
		    		 }
			   	 }
		    	 var date = "";
		    	 var dates = document.getElementsByName("date");
		    	 for(var i=0;i<dates.length;i++){
			    	 if(dates[i].checked){
			    		 date = dates[i].value
		    			 }
			     }
		    	 
		    	 var theme = $("#theme").val();
		    	 var transactionDesc = $("#transactionDesc").val();
		    	 var sendType =  2;
		    	 if(date == 1){
		    		 date = new Date().Format("yyyy-MM-dd");
		    		 sendType = 1;
		    	 }else{
		    		 date = $("#startTime").val();
		    	 }
		    	 
		    	 
			        $.ajax({
						type : "post",
					    async: false,
						data:{
							"specifyType":specifyType,
							"date":date,
							"theme":theme,
							"transactionDesc":transactionDesc,
							"sendType":sendType,
							"state":state,
							"id":id
							
						},
						url : "${ctx}/manage/calendarTransaction/editTransaction",
						success : function(data) {
							if (data.code ==1) {
								alert("修改成功");
								location.href= "${ctx}/manage/calendarTransaction/getTransactionByPage";
							}else {
								layer.alert("保存失败", {icon: 5});
							}
						}
					  });
				
				
				
			}
			
			
	    	
	     }		
		
		
		
		
		
	     function save(){
	    	 
	    		var id = $("#id").val();
				
				if(id == ""){
					 var specifyType = "";
			    	 var specifyTypes = document.getElementsByName("send");
			    	 var state = 0;
				   	 for(var i=0;i<specifyTypes.length;i++){
				    	 if(specifyTypes[i].checked){
				    		 specifyType = specifyTypes[i].value
			    		 }
				   	 }
			    	 var date = "";
			    	 var dates = document.getElementsByName("date");
			    	 for(var i=0;i<dates.length;i++){
				    	 if(dates[i].checked){
				    		 date = dates[i].value
			    			 }
				     }
			    	 
			    	 var theme = $("#theme").val();
			    	 var transactionDesc = $("#transactionDesc").val();
			    	 var sendType =  2;
			    	 if(date == 1){
			    		 date = new Date().Format("yyyy-MM-dd");	
			    		 sendType = 1;
			    	 }else{
			    		 date = $("#startTime").val();
			    	 }
			    	 
			    	 
				        $.ajax({
							type : "post",
						    async: false,
							data:{
								"specifyType":specifyType,
								"date":date,
								"theme":theme,
								"transactionDesc":transactionDesc,
								"sendType":sendType,
								"state":state
							},
							url : "${ctx}/manage/calendarTransaction/addTransaction",
							success : function(data) {
								if (data.code ==1) {
									alert("保存成功");
									location.href= "${ctx}/manage/calendarTransaction/getTransactionByPage";
								}else {
									layer.alert("保存失败", {icon: 5});
								}
							}
						  });
				}else{
					 var specifyType = "";
			    	 var specifyTypes = document.getElementsByName("send");
			    	 var state = 0;
				   	 for(var i=0;i<specifyTypes.length;i++){
				    	 if(specifyTypes[i].checked){
				    		 specifyType = specifyTypes[i].value
			    		 }
				   	 }
			    	 var date = "";
			    	 var dates = document.getElementsByName("date");
			    	 for(var i=0;i<dates.length;i++){
				    	 if(dates[i].checked){
				    		 date = dates[i].value
			    			 }
				     }
			    	 
			    	 var theme = $("#theme").val();
			    	 var transactionDesc = $("#transactionDesc").val();
			    	 var sendType =  2;
			    	 if(date == 1){
			    		 date = new Date().Format("yyyy-MM-dd");	
			    		 sendType = 1;
			    	 }else{
			    		 date = $("#startTime").val();
			    	 }
			    	 
			    	 
				        $.ajax({
							type : "post",
						    async: false,
							data:{
								"specifyType":specifyType,
								"date":date,
								"theme":theme,
								"transactionDesc":transactionDesc,
								"sendType":sendType,
								"state":state,
								"id":id
							},
							url : "${ctx}/manage/calendarTransaction/editTransaction",
							success : function(data) {
								if (data.code ==1) {
									alert("修改成功");
									location.href= "${ctx}/manage/calendarTransaction/getTransactionByPage";
								}else {
									layer.alert("保存失败", {icon: 5});
								}
							}
						  });
				}
	    	
	     }	
	     
	     
	     Date.prototype.Format = function (fmt) { //author: meizz 
	    	    var o = {
	    	        "M+": this.getMonth() + 1, //月份 
	    	        "d+": this.getDate(), //日 
	    	        "h+": this.getHours(), //小时 
	    	        "m+": this.getMinutes(), //分 
	    	        "s+": this.getSeconds(), //秒 
	    	        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
	    	        "S": this.getMilliseconds() //毫秒 
	    	    };
	    	    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	    	    for (var k in o)
	    	    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	    	    return fmt;
	    	}
		
		</script>
		
</body>
</html>