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
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/style.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/font/iconfont.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/page.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/libs/datepicker/skin/default/datepicker.css">
	<link rel="stylesheet" href="${ctx}/resources/libs/ztree/zTreeStyle.css" type="text/css">
	<style  type="text/css">
      	* {margin: 0 ;padding: 0}
        #calendar {width: 100%;margin: 100px auto;overflow: hidden;border: 1px solid #000;padding: 20px;position: relative;margin-top: 20px;}
         #calendar h4 {text-align: center;margin-bottom: 10px;font-size:25px;}
        #calendar .a1 {position: absolute;top: 20px;left: 20px;}
        #calendar .a2 {position: absolute;top: 20px;right: 20px;}
        #calendar .week {height: 70px;line-height: 20px;border-bottom: 1px solid #000;margin-bottom: 10px}
        #calendar .week li {float: left;width: 14%;height: 70px;text-align: center;list-style: none;}
        #calendar .dateList {clear: both;margin-left: 10;}
        #calendar .dateList li {float: left;width: 14%;height: 70px;text-align: center;line-height: 30px;list-style: none; font-size:25px;border:0.2px solid gray;margin:0 0 -2 -2;}
        #calendar .dateList .ccc {background-color:#F0F0F0}
        #calendar .dateList .red {background: #F90;color: #fff}
        #calendar .dateList .sun {color: #f00;}
        #calendar .dateList .sunccc {color: #f00;background-color:#F0F0F0}
        #yearMonthSelect{ position:relative;height:30px;background:#F0F0F0;text-decoration: none;}
        #yearMonthSelect select option[selected]{font-weight:bold}
        #yearMonthSelect select option{color:#EA5400;}
        /*添加hover效果*/
        #yearMonthSelect:hover{ background:#FF8000}
	</style>
<form id="attendanceAddOrUpdateForm"  method="post">
	<div class="title"><strong>新增考勤规则</strong></div>
		<div class="filter-box" style='height:300px'>
			<ul>
				<li><input name="attendanceRules.id" value="${ attendanceRulesVo.attendanceRules.id}" type="hidden"></li>
				<li><input name="attendanceRules.calendarId" value="${ attendanceRulesVo.attendanceRules.calendarId}" type="hidden"></li>
			</ul>
			<ul class="ui-form grid-row">
				<li class="col-md-3" style="width: 1000px"><strong style="width:100px"><a style="color:red">*</a>考勤规则名称</strong><input size="570px" id="attendanceRulesName" name="attendanceRules.ruleName" type="text" placeholder="请输入规则名称" value="${ attendanceRulesVo.attendanceRules.ruleName}"></li>
			</ul>
			<ul class="ui-form grid-row">
				<li class="col-md-3" style="width: 1000px"><strong style="width:100px"><a style="color:red">*</a>考勤规则说明</strong>
				<textarea id="attendanceRulesDescription" style="width:570px; height:200px; overflow:hidden; border:solid 1px #a49c9c; border-radius:7px; resize:none;padding-left: 8" name="attendanceRules.description" rows="4" cols="50" placeholder="请输入规则说明" value="">${ attendanceRulesVo.attendanceRules.description}</textarea>
				</li>
			</ul>
		</div>
		<div class="title"><strong>日历设置</strong></div>
		<!-- 可选择年月 -->
		<div class="filter-box">
			 <div id="calendar">
	            <h4>
	            	<a id="yearMonthSelect">
		            	<select id="yearSelect">
			            </select>
	            	</a>年
	            	<a id="yearMonthSelect">
		            	<select id="monthSelect">
			            </select>
	            	</a>月
	            </h4>
	             <a href="##" class="a1">上月</a>
	             <a href="##" class="a2">下月</a>
	             <ul class="calendarBookDetail">
	             	
	             </ul>
	             <ul class="week">
	                 <li>一</li>
	                 <li>二</li>
	                 <li>三</li>
	                 <li>四</li>
	                 <li>五</li>
	                 <li>六</li>
	                 <li>日</li>
	             </ul>
	             <ul class="dateList"></ul>
	         </div>
	         <a>TIPS：请在日历中，点击选择出勤日期</a>
         </div>
		<div class="title"><strong>考勤规则设置</strong></div>
		<div class="filter-box">
			<ul class="ui-form grid-row">
			 	<li class="col-md-3"><input  id="attendanceTimeCheck" type="checkbox" onchange="unlockTimeInput()">
			 		<c:if test="${rulesType !=1&&rulesType !=2}">
			 			<input type="hidden" name="attendanceRules.rulesType" value="${attendanceRulesVo.attendanceRules.rulesType }">
			 		</c:if>
			 		<c:if test="${attendanceRulesVo.attendanceRules.rulesType !=1&&attendanceRulesVo.attendanceRules.rulesType !=2}">
			 			<input type="hidden" name="attendanceRules.rulesType" value="${rulesType }">
			 		</c:if>
			 		
			 		<c:if test="${rulesType =='2' || attendanceRulesVo.attendanceRules.rulesType==2}">
			 			<label for="attendanceTimeCheck">活动签到时间</label>
			 		</c:if>
			 		<c:if test="${rulesType =='1' ||attendanceRulesVo.attendanceRules.rulesType==1}">
			 			<label for="attendanceTimeCheck">考勤签到时间</label>
			 		</c:if>
			 	<input id="validSignInTime" type="text" name="attendanceRules.validSignInTime" value="<fmt:formatDate value='${ attendanceRulesVo.attendanceRules.validSignInTime}' type='date' pattern="hh:mm"/>" placeholder="输入考勤时间 如09:00"></li>
			</ul>
		</div>
		<div class="title"><strong>考勤对象设置</strong></div>
		<div class="filter-box">
			<ul class="ui-form grid-row">
				<!-- 生成考勤对象及适用类型隐藏域 -->
				<li class="col-md-3" id="attendanceObject">
					<c:if test="${attendanceRulesVo!=null}">
						<!-- 编辑时回显考勤对象内容 -->
						<input type="hidden" name="attendanceRules.attendanceObjectType" value="${attendanceRulesVo.attendanceRules.attendanceObjectType}">
	    				<input type="hidden" name="calendarBook.orgId" value="${attendanceRulesVo.calendarBook.orgId}">
	    				<input type="hidden" name="calendarBook.isGlobalScope" value="${attendanceRulesVo.calendarBook.isGlobalScope}">
	    				<c:forEach items="${attendanceRulesVo.attendanceObjectsList}" var="attendanceObject" varStatus="status">
	    					<input type="hidden" name="attendanceObject[${status.index }].orgId" value="${attendanceObject.orgId}">
							<input type="hidden" name="attendanceObject[${status.index }].attendanceObjectType" value="${attendanceObject.attendanceObjectType}">
							<input type="hidden" name="attendanceObject[${status.index }].agentCode" value="${attendanceObject.agentCode}">
	    				</c:forEach>
					</c:if>
				</li>
				<li class="col-md-3">
					 <input id="attendanceObjectTypeCheck" type="checkbox"  onchange="selectAllDepartment()"><label for="attendanceObjectTypeCheck">所有人</label>
				</li>
				<li class="col-md-3"><button id="departmentButton" type="button" class="btn btn-submit btn-radius" onclick="getDepartmentTree()">指定部门</button></li>
				<li class="col-md-3"><button id="personButton" type="button" class="btn btn-submit btn-radius" onclick="uploadPerson()">特定人员</button></li>
			</ul>
			
			<ul id="treeDemo" class="ztree" style="margin-left: 350px;"></ul><!-- 弹出ztree窗口 -->
			<ul class="ui-form grid-row">
				<li class="col-md-5">
					<c:if test="${operatorType!=0 }">
						<button type="button" class="btn btn-submit btn-radius" onclick="saveAttendance()">保存</button>
					</c:if>
					<button type="button" class="btn btn-submit btn-radius" onclick="location='${ctx}/manage/attendance/selectAttendanceRulesByParams'">取消</button>
					<c:if test="${operatorType==1 }">
						<button type="button" class="btn btn-submit btn-radius" onclick="deleteAttendance()">删除</button>
					</c:if>
				</li>
			</ul>
		</div>
</form>
	<form id="personFile" style="display: none;" method="post" enctype="multipart/form-data">
       <input type="file" name="fileName" id="fileName" onchange="upload()" >
       <input name="category" type="hidden" id ="category">
       <input name="fileId" id ="fileId"  type="hidden" value="">
       <input name="url" id ="url"  type="hidden" value="">
    </form>
<input id="saveAttendanceInput" type="hidden" >
<script type="text/javascript" src="${ctx}/resources/js/common/page.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/layer/layer.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/ztree/jquery.ztree.core.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/ztree/jquery.ztree.excheck.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/ztree/jquery.ztree.exedit.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common/jquery-form.js"></script>
<script type="text/javascript">
/*-----------------------------------------------绘制日历--------------------------------------------------------  */
$(function() {
	var iNow=0;
	//定义一个baselist 每次翻页都更新该list
	var baseDailyTranctionList='${dailyTransactionList}';
	if('${operatorType}'==''){
		//新增则绘制当前日期
		run(0,'${dailyTransactionList}');
		iNow=0;
	}else{
		//修改操作则获取dailytransaction中日期值 并计算相对于当前月的偏移值
		var day = '${attendanceRulesVo.dailyTransactionList[10].day}';
		if(day==""){
			//未获取到则绘制当前日历
			run(0,'${dailyTransactionList}');
			iNow=0;
		}else{
			var n = getIntervalMonth(new Date(day),new Date());
			run(n,'${dailyTransactionList}');
			iNow = n;
		}
	} 
    
    //上个月
    $(".a1").click(function(){
    	//新建考勤时dailyTransactionList为前页暂存每日详情list
		//修改考勤时dailyTransactionList为本考勤对应每日详情list
    	//暂存本页数据
   		$.ajax({
  	      type: 'post',
  	      data :$("#attendanceAddOrUpdateForm").serialize()+"&lastPageDailyTransactionsList="+baseDailyTranctionList,
  	  	  url: "${ctx}/manage/attendance/saveCurrentPageDailyTransaction",
  	  	  dataType:"json",
  	  	success:function (data) {
  	  		//非空白数据更新baselist
  	  		if(data!=''){
  	  			baseDailyTranctionList = JSON.stringify(data);
  	  		}
  	  	 iNow--;
         run(iNow,baseDailyTranctionList);
        },
  	  	error: function (data) {
  	  	layer.msg("暂存本页数据出错");
        }
  	  	})
    });
    //下个月
    $(".a2").click(function(){
    	//新建考勤时dailyTransactionList为前页暂存每日详情list
		//修改考勤时dailyTransactionList为本考勤对应每日详情list
    	//暂存本页数据
   		$.ajax({
  	      type: 'post',
  	   	  async:false,
  	      data :$("#attendanceAddOrUpdateForm").serialize()+"&lastPageDailyTransactionsList="+baseDailyTranctionList,
  	  	  url: "${ctx}/manage/attendance/saveCurrentPageDailyTransaction",
  	  	  dataType:"json",
  	  	success:function (data) {
  	  		//非空白数据更新baselist
  	  		if(data!=''){
  	  			baseDailyTranctionList = JSON.stringify(data);
  	  		}
  	  		iNow++;
        	run(iNow,baseDailyTranctionList);
        },
  	  	error: function (data) {
  	  	layer.msg("暂存本页数据出错");
        }
  	  	})
    })
    //选择年份点击事件
    $("#yearSelect").change(function(){
   		$.ajax({
  	      type: 'post',
  	   	  async:false,
  	      data :$("#attendanceAddOrUpdateForm").serialize()+"&lastPageDailyTransactionsList="+baseDailyTranctionList,
  	  	  url: "${ctx}/manage/attendance/saveCurrentPageDailyTransaction",
  	  	  dataType:"json",
  	  	success:function (data) {
  	  		//非空白数据更新baselist
  	  		if(data!=''){
  	  			baseDailyTranctionList = JSON.stringify(data);
  	  		}
  	  		//获得选择年份
  	    	var jumpYear = $("#yearSelect").val();
  	  		//判断需要跳转月数
  	  		var jumpDate = new Date();
  	  		jumpDate.setYear(jumpYear);
  	  		var n = getIntervalMonth(jumpDate,new Date());
			run(n,baseDailyTranctionList);
			iNow = n;
        },
  	  	error: function (data) {
  	  	layer.msg("暂存本页数据出错");
        }
  	  	})
    })
    //选择月份点击事件
    $("#monthSelect").change(function(){
    	//暂存本页数据
   		$.ajax({
  	      type: 'post',
  	   	  async:false,
  	      data :$("#attendanceAddOrUpdateForm").serialize()+"&lastPageDailyTransactionsList="+baseDailyTranctionList,
  	  	  url: "${ctx}/manage/attendance/saveCurrentPageDailyTransaction",
  	  	  dataType:"json",
  	  	success:function (data) {
  	  		//非空白数据更新baselist
  	  		if(data!=''){
  	  			baseDailyTranctionList = JSON.stringify(data);
  	  		}
  	  		//获得选择月份(显示月份多1)
  	    	var jumpMonth = $("#monthSelect").val()-1;
  	  		//判断需要跳转月数
  	  		var jumpDate = new Date();
  	  		jumpDate.setMonth(jumpMonth);
  	  		var n = getIntervalMonth(jumpDate,new Date());
			run(n,baseDailyTranctionList);
			iNow = n;
        },
  	  	error: function (data) {
  	  	layer.msg("暂存本页数据出错");
        }
  	  	})
    })
});
	
/*-----------------------------------------------考勤方法--------------------------------------------------------  */
	//删除考勤规则
	function deleteAttendance(){
		layer.confirm('确定要删除该考勤规则吗？', {
			icon: 3,
		  	btn: ['确定', '取消'] //按钮
		}, function(){
			var id = '${attendanceRulesVo.attendanceRules.id}';
			$.ajax({
		  	      type: 'post',
		  	      data :{"id":id},
		  	  	  url: "${ctx}/manage/attendance/deleteAttendanceRules",
		  	  	  dataType:"json",
		  	  	error: function (data) {
		  	  	layer.msg("删除出错");
		        },  
		  	  	  success: function(result) {
		  	  		window.location.href = '${ctx}/manage/attendance/selectAttendanceRulesByParams';
		  		  }
		  	  	})
		});
	}
	
	//保存考勤规则
	function saveAttendance(){
	//读取其他页详情list
	var baseDailyTranctionList = $("#saveAttendanceInput").val();
	//添加校验
	if($('#attendanceRulesName').val()==''){
		layer.msg("名称不能为空");
		return;
	}
	if($('#attendanceRulesDescription').val()==''){
		layer.msg("说明不能为空");
		return;
	}
	if($('#attendanceObject').children().length==0){
		layer.msg("考勤对象不能为空");
		return;
	}
	if($("#validSignInTime").attr("disabled")!="disabled"&&$("#validSignInTime").val()==''){
		layer.msg("考勤时间不能为空");
		return;
	}
	
	console.log($("#attendanceAddOrUpdateForm").serialize()+"&lastPageDailyTransactionsList="+baseDailyTranctionList);
		$.ajax({
			  async:true,
	  	      type: 'post',
	  	      timeout : 0,
	  	      data :$("#attendanceAddOrUpdateForm").serialize()+"&lastPageDailyTransactionsList="+baseDailyTranctionList,
	  	  	  url: "${ctx}/manage/attendance/addOrUpdateAttendanceRules",
	  	  	  dataType:"json",
	  	  	 
	  	  	error: function (data) {
	  	  	layer.msg("新增或修改出错");
	        },  
	  	  	  success: function(result) {
	  	  		layer.confirm('新增或修改成功,是否跳转至列表页？', {
				icon: 3,
			  	btn: ['确定', '取消'] //按钮
				}, function(){
					window.location.href = '${ctx}/manage/attendance/selectAttendanceRulesByParams';
				});
	  		  }
	  	  	})
	}
	
	//选中所有人
    function selectAllDepartment(){
    	//清除ul
    	$("#treeDemo").empty();
    	if($("#attendanceObjectTypeCheck").prop("checked")){
    		var companyId = '${companyId}';
    		//设置考勤规则和考勤对象都为1 并设置考勤对象companyId
    		var htmla = '<input type="hidden" name="attendanceRules.attendanceObjectType" value="1">';
    		var htmlb = '<input type="hidden" name="calendarBook.isGlobalScope" value="1">';
    		var htmlc = '<input type="hidden" name="attendanceObjectsList[0].attendanceObjectType" value="1">';
    		var htmld = '<input type="hidden" name="attendanceObjectsList[0].companyId" value="'+companyId+'">';
    		var html = htmla + htmlb + htmlc + htmld;
    		$("#attendanceObject").empty();
    		$("#attendanceObject").append(html);
    		$("#treeDemo").append("已选中:本公司所有人");
    	}
    }
  	//打开选择部门树
    function getDepartmentTree(){
  		//判断是否勾选所有人
  		if($("#attendanceObjectTypeCheck").prop("checked")){
  			layer.msg("请先去除勾选所有人");
  			return;
  		}
  		//清除ul
    	$("#treeDemo").empty();
    	//加载树
    	$.fn.zTree.init($("#treeDemo"), setting, zNodes);
    }
    /*-----------------------------------------------附件上传--------------------------------------------------------  */
  	//打开上传人员输入框
    function uploadPerson(){
    	//判断是否勾选所有人
  		if($("#attendanceObjectTypeCheck").prop("checked")){
  			layer.msg("请先去除勾选所有人");
  			return;
  		}
    	//清除ul
    	$("#treeDemo").empty();
    	//添加上传和解析按钮
    	$("#treeDemo").append('<button type="button" class="btn btn-sm btn-submit" onclick ="changeC(\'EL_LESSON_COURSEWARE\',\'2\',\'70\',\'1\')">上传excel</button>');
    	$("#treeDemo").append('<a id="uploadUrl" href ="${attachment.path}" target ="_blank"><span id="uploadLocation">${attachment.originalName}</span></a>');
    	$("#treeDemo").append('<button type="button" class="btn btn-sm btn-submit" onclick="uploadOther()" >解析excel</button>');
    	$("#treeDemo").append("TIPS:代理人信息格式为(agentCode companyId orgId)");
    }
  	//点击上传按钮事件
    function changeC(urlLocation,fileType,category,formLocation){  
    	$("input#category").val(category);  //附件表中定义的附件类型 
    	$("input#fileType").val(fileType);  //类型： 附件类型：1 --表示课程缩略图； 2--课程课件； 3--课程附件 
    	$("input#urlLocation").val(urlLocation);  //回显位置  标签Id 
    	$("input#formLocation").val(formLocation); //表单位置 
      	$("input#fileName").click();
   }
  	//上传附件方法
  	function upload(){
  		var btn_index = layer.load(2);
        $("#personFile").ajaxSubmit({
        url : '${ctx}/manage/commonUploadFile',
        type :"post",
        success : function(data){
                if (data != null) {  //附件要回显
                    $("input#fileId").val(data.id);
                    $("span#uploadLocation").html(data.originalName);
                    $("#uploadUrl").attr("href",data.path);
                    $("#url").val(data.url);
                }
                layer.close(btn_index);
            },error:function() {
                alert("附件上传错误");
                layer.close(btn_index);
            } 
        });  
  	}
  	//将已上传附件内容解析进页面方法
  	function uploadOther(){
    	var url = $("#url").val();
		$("#personFile").ajaxSubmit({
	        url : '${ctx}/manage/attendance/resolveAttanchment',
	        type :"post",
	        success : function(data){
	        		//清空考勤隐藏域
        			$("#attendanceObject").empty();
        			$("#treeDemo").empty();
        			
	        		if(data.code==0){
	        			//文件格式错误
	        			layer.msg(data.msg);
	        			return;
	        		}
	        		
	                if(data.data.length==0){
	                	layer.msg("没有数据");
	                }else{
	                	$("#attendanceObject").append('<input type="hidden" name="attendanceRules.attendanceObjectType" value="3">');
	                	$("#attendanceObject").append('<input type="hidden" name="calendarBook.isGlobalScope" value="3">');
	                	$("#treeDemo").append("已选中:");
	                	//将选中代理人的agentid添加到隐藏域
	                	$.each(data.data,function(i,item){
	                		//往隐藏域添加考勤对象
		               		$("#attendanceObject").append('<input type="hidden" name="attendanceObjectsList['+i+'].agentCode" value="'+item.agentCode+'">');
		               		$("#attendanceObject").append('<input type="hidden" name="attendanceObjectsList['+i+'].orgId" value="'+item.orgId+'">');
		               		$("#attendanceObject").append('<input type="hidden" name="attendanceObjectsList['+i+'].companyId" value="'+item.companyId+'">');
		               		$("#attendanceObject").append('<input type="hidden" name="attendanceObjectsList['+i+'].attendanceObjectType" value="3">');
		           			$("#treeDemo").append(" "+item.agentCode);
	                	}) 
	                }
	            },
	            error:function(data) {
	            	layer.msg(data.msg);
	            } 
	      });  
	}  
  	
  	//选中考勤时间设置
	function unlockTimeInput(){
		if($("#validSignInTime").attr("disabled")=="disabled"){
			$("#validSignInTime").removeAttr("disabled");
		}else{
			$("#validSignInTime").attr("disabled","disabled");
		}
	}
  	
  	/*-----------------------------------------------日历--------------------------------------------------------  */
	//获取月份差
  	function getIntervalMonth(startDate,endDate){
        var startMonth = startDate.getMonth();
        var endMonth = endDate.getMonth();
        var intervalMonth = (startDate.getFullYear()*12+startMonth) - (endDate.getFullYear()*12+endMonth);
        return intervalMonth;
	}
  	
  	//获取当前yyyy-MM-dd格式日期方法
	function getDateByIndex(i,datePrefix){
		if(i<10){
			return datePrefix+"-0"+i;
		}else{
			return datePrefix+"-"+i;
		}
	}
	//获取当前周几方法
	function getWeekByIndex(i,datePrefix){
		var date = new Date(getDateByIndex(i,datePrefix));
		return date.getDay();
	}
	//获取每日日期前缀方法
	function getDatePrefix(month,year){
		var datePrefix;
		if(month<9){
        	datePrefix = year+"-0"+(month+1);
        }else{
        	datePrefix = year+"-"+(month+1);
        }
		return datePrefix;
	}
	//获取月份日期前缀方法
	function getMonthDatePrefix(month,year){
		var monthDatePrefix;
		if(month<9){
        	 monthDatePrefix = year+"0"+(month+1);
        }else{
        	monthDatePrefix = year+""+(month+1);
        }
		return monthDatePrefix;
	}
	
		//日历绘制方法
		  function run(n,data) {
			//将baselist带给保存方法
			$('#saveAttendanceInput').val(data);
            var oDate = new Date(); //定义时间
            oDate.setMonth(oDate.getMonth()+n);//设置月份
          	//本月上月下月年月信息
            var year = oDate.getFullYear(); //年
            var month = oDate.getMonth(); //月(月份从0开始)
            var today = oDate.getDate(); //日
            oDate.setMonth(oDate.getMonth()-1);
            var lastMonthYear = oDate.getFullYear(); //上个月年
            var lastMonth = oDate.getMonth(); //上个月
            oDate.setMonth(oDate.getMonth()+2);
            var nextMonthYear = oDate.getFullYear(); //下个月年
            var nextMonth = oDate.getMonth(); //下个月
            //本月上月下月日期年月前缀
            var datePrefix = getDatePrefix(month,year);
            var monthDatePrefix = getMonthDatePrefix(month,year);
            var lastDatePrefix = getDatePrefix(lastMonth,lastMonthYear);
            var lastMonthDatePrefix = getMonthDatePrefix(lastMonth,lastMonthYear);
            var nextDatePrefix= getDatePrefix(nextMonth,nextMonthYear);
            var nextMonthDatePrefix = getMonthDatePrefix(nextMonth,nextMonthYear);
            
            //计算本月有多少天
          var allDay = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31][month];
        	//计算上月有多少天
        	var lastMonthAllDay = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31][lastMonth];
        	//计算下月有多少天
        	var nextMonthAllDay = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31][lastMonth];
            //判断闰年
            if(month == 1) {
                if(year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
                    allDay = 29;
              }
            }
			
            //判断本月第一天是星期几
            oDate.setMonth(oDate.getMonth()-1);
            oDate.setDate(1); //时间调整到本月第一天
            var week = oDate.getDay(); //读取本月第一天是星期几 返回值是 0（周日） 到 6（周六） 之间的一个整数
            //默认周日在最左侧，为显示方便，将周六周日移动到最右侧
            if(week==0){
            	week=6;
            }else{
            	week=week-1;
            }

            //console.log(week);
          $(".dateList").empty();//每次清空
          //插入日历详情
          $(".calendarBookDetail").append("<input type='hidden' name='calendarBook.calendarDate' value='"+monthDatePrefix+"'>");
            //插入上个月日期
            for(var i = 0; i < week; i++) {
            	if('${rulesType}' !="1"&&'${rulesType}' !="2"){
            		 $(".dateList").append("<li><input type='hidden' name='dailyTransactionList["+i+"].day' value='"+getDateByIndex(lastMonthAllDay-week+i+1,lastDatePrefix)+"'><input type='hidden' name='dailyTransactionList["+i+"].dayOfWeek' value='"+getWeekByIndex((lastMonthAllDay-week+1+i),lastDatePrefix)+"'><input type='hidden' name='dailyTransactionList["+i+"].transationType' value='"+1+"'><input type='hidden' name='dailyTransactionList["+i+"].attendanceType' value='${attendanceRulesVo.attendanceRules.rulesType}'>"  + (lastMonthAllDay-week+1+i) + "</li>");
            	}else{
            		 $(".dateList").append("<li><input type='hidden' name='dailyTransactionList["+i+"].day' value='"+getDateByIndex(lastMonthAllDay-week+i+1,lastDatePrefix)+"'><input type='hidden' name='dailyTransactionList["+i+"].dayOfWeek' value='"+getWeekByIndex((lastMonthAllDay-week+1+i),lastDatePrefix)+"'><input type='hidden' name='dailyTransactionList["+i+"].transationType' value='"+1+"'><input type='hidden' name='dailyTransactionList["+i+"].attendanceType' value='${rulesType}'>" + (lastMonthAllDay-week+1+i) + "</li>");
            	}
            }
            //插入本月日期
            for(var i = 1; i <= allDay; i++) {
            	//添加当月每天的li
		 		if('${rulesType}' !="1"&&'${rulesType}' !="2"){
		 			$(".dateList").append("<li><input type='hidden' name='dailyTransactionList["+(week+i-1)+"].day' value='"+getDateByIndex(i,datePrefix)+"'><input type='hidden' name='dailyTransactionList["+(week+i-1)+"].dayOfWeek' value='"+getWeekByIndex(i,datePrefix)+"'><input type='hidden' name='dailyTransactionList["+(week+i-1)+"].transationType' value='"+1+"'><input type='hidden' name='dailyTransactionList["+(week+i-1)+"].attendanceType' value='${attendanceRulesVo.attendanceRules.rulesType}'>" + i + "</li>");
		 		}else{
		 			$(".dateList").append("<li><input type='hidden' name='dailyTransactionList["+(week+i-1)+"].day' value='"+getDateByIndex(i,datePrefix)+"'><input type='hidden' name='dailyTransactionList["+(week+i-1)+"].dayOfWeek' value='"+getWeekByIndex(i,datePrefix)+"'><input type='hidden' name='dailyTransactionList["+(week+i-1)+"].transationType' value='"+1+"'><input type='hidden' name='dailyTransactionList["+(week+i-1)+"].attendanceType' value='${rulesType}'>" + i + "</li>");
		 		}
            }
          	//插入下个月日期
          	 var left = (allDay+week)%7;
          	if(left!=0){
          		left = 7-left;
          	}
            for(var i = 1; i <= left; i++) {
            	if('${rulesType}' !="1"&&'${rulesType}' !="2"){
            		$(".dateList").append("<li><input type='hidden' name='dailyTransactionList["+(allDay+week+i-1)+"].day' value='"+getDateByIndex(i,nextDatePrefix)+"'><input type='hidden' name='dailyTransactionList["+(allDay+week+i-1)+"].dayOfWeek' value='"+getWeekByIndex(i,nextDatePrefix)+"'><input type='hidden' name='dailyTransactionList["+(allDay+week+i-1)+"].transationType' value='"+1+"'><input type='hidden' name='dailyTransactionList["+(allDay+week+i-1)+"].attendanceType' value='${attendanceRulesVo.attendanceRules.rulesType}'>" + i + "</li>");
            	}else {
            		$(".dateList").append("<li><input type='hidden' name='dailyTransactionList["+(allDay+week+i-1)+"].day' value='"+getDateByIndex(i,nextDatePrefix)+"'><input type='hidden' name='dailyTransactionList["+(allDay+week+i-1)+"].dayOfWeek' value='"+getWeekByIndex(i,nextDatePrefix)+"'><input type='hidden' name='dailyTransactionList["+(allDay+week+i-1)+"].transationType' value='"+1+"'><input type='hidden' name='dailyTransactionList["+(allDay+week+i-1)+"].attendanceType' value='${rulesType}'>" + i + "</li>");
            	}
                
            } 
            
            //标记颜色=====================
            $(".dateList li").each(function(i, elm){
                //console.log(index,elm);
                var curentDate = $(this).children().eq(0).val();
                var monthCount = curentDate.split("-")[1];
               /*  if (n==0) {
                	//非本月且非节假日
                    if((monthCount-month-1)!=0&&i%7!=5&&i%7!=6){
                    $(this).addClass('ccc')
                }else if(i%7==5  ||  i%7==6   ){
                    $(this).addClass('sun')
                }
                }else if(n<0){
                    $(this).addClass('ccc')
                }else if(i%7==5  ||  i%7==6   ){
                    $(this).addClass('sun')
                } */
                 
            	//非本月背景色灰色
                if((monthCount-month-1)!=0&&i%7!=5&&i%7!=6){
                $(this).addClass('ccc');
                //节假日背景色灰色字体红色
            	}else if((monthCount-month-1)!=0&&i%7==5){
            		$(this).addClass('sunccc');
            	}else if((monthCount-month-1)!=0&&i%7==6){
            		$(this).addClass('sunccc');
            	}else if(i%7==5  ||  i%7==6   ){
                $(this).addClass('sun')
            	}
            });
            
            //定义标题日期并设置为下拉框
            var displayDate = new Date();
            displayDate.setMonth(month-2);
            var lastLastMonth= displayDate.getMonth();
            
            displayDate = new Date();
            displayDate.setMonth(month+2);
            var nextNextMonth= displayDate.getMonth();
            
            nextMonth++;
            nextNextMonth++;
            month++;
            lastMonth++;
            lastLastMonth++;
            
            var htmla ='<option>'+(year-2)+'</option><option>'+(year-1)+'</option><option selected="selected">'; 
			var htmlb = year+'</option><option>'+(year+1)+'</option><option>'+(year+2)+'</option>';
			var htmlc ='<option>'+lastLastMonth+'</option><option>'+lastMonth+'</option><option selected="selected">'; 
			var htmld = month  +'</option><option>'+nextMonth+'</option><option>'+nextNextMonth+'</option>';
			$("h4 #yearSelect").html(htmla+htmlb);
			$("h4 #monthSelect").html(htmlc+htmld);
            
            var dayList=$(".dateList li");
            
            //若非查看操作则为每天添加点击事件
            if('${operatorType==0 }'=='false'){
                 for(i=0;i<dayList.length;i++) {
                 	dayList[i].onclick = function () {
                 		if($(this).children().eq(2).val()==1){
                 			//点击变色
                             this.style="background-color:#FF8000";
                           	//设置类型为工作日
                             $(this).children().eq(2).val(2);
                 		}else{
                 			//变色
                             $(this).removeAttr("style");
                           	//设置类型为工作日
                             $(this).children().eq(2).val(1);
                 		}
                     }
                 } 
            }
            //将传入的每日详情赋值
            for(i=0;i<dayList.length;i++) {
            	var currentDate = dayList[i].children[0].value;
            	if(data!=''){
            		//将data字符串转为数组data2
            		var data2 = eval(data);
                    for(var j in data2){
                    	if(new Date(data2[j].day).toLocaleDateString()==new Date(currentDate).toLocaleDateString()){
                			dayList[i].children[2].value=data2[j].transationType;
                			if(data2[j].transationType==2){
                				dayList[i].setAttribute("style","background-color:#FF8000");
                			}
                		}
                    }
            	} 
            }
        };
        /*-----------------------------------------------编辑时考勤对象回显--------------------------------------------------------  */        
        //查看时将所有输入框置为不可点击
        if('${operatorType==0 }'=='true'){
        	$('#attendanceRulesName').attr("disabled","disabled");
        	$('#attendanceRulesDescription').attr("disabled","disabled");
        	$('#attendanceRulesDescription').attr("disabled","disabled");
        	$('#attendanceTimeCheck').attr("disabled","disabled");
        	$('#validSignInTime').attr("disabled","disabled");
        	$('#attendanceObjectTypeCheck').attr("disabled","disabled");
        	$('#departmentButton').attr("disabled","disabled");
        	$('#personButton').attr("disabled","disabled");
        }
        //若考勤规则为所有人则勾选所有人
       if('${attendanceRulesVo.attendanceRules.attendanceObjectType}'==1){
    	   $("#attendanceObjectTypeCheck").attr("checked","checked");
    	   $("#treeDemo").append("已选中:所有人");
       }else if('${attendanceRulesVo.attendanceRules.attendanceObjectType}'==2){
    	 //若考勤规则为部门则显示部门
    	   var orgId = '${attendanceRulesVo.attendanceObjectsList[0].orgId}';
    	   $("#treeDemo").append("已选中:");
    	   $.ajax({
    		   	type:"POST",
    			url:"${ctx}/manage/getOrganizationById",
    			data:{"organizationId":orgId},
    			success:function(data){
    				$("#treeDemo").append(data.data.cnName);
    			}
    	   })
       }else if('${attendanceRulesVo.attendanceRules.attendanceObjectType}'==3){
    	 //若考勤规则为人则显示人
    	   var agentCode = '';
    	   <c:forEach items='${attendanceRulesVo.attendanceObjectsList}' var='agent' >
    	   	agentCode += ('${agent.agentCode}'+' ');
    	   </c:forEach>
    	   $("#treeDemo").append("已选中:"+agentCode);
       }
     //判断选中考勤时间
       if('${attendanceRulesVo.attendanceRules.validSignInTime}'==''){
    	   $("#validSignInTime").attr("disabled","disabled");
       }else{
    	   $("#attendanceTimeCheck").attr("checked","checked");
       }
       /*-----------------------------------------------树--------------------------------------------------------  */
        //设置ztree点击函数
        function zTreeOnClick(event, treeId, treeNode){
    	   //获取组织id
    	   var orgId = treeNode.id;
    	   //清空隐藏域
    	   $("#attendanceObject").empty();
    	   //赋值
       		var htmla = '<input type="hidden" name="attendanceRules.attendanceObjectType" value="2">';
       		var htmlb = '<input type="hidden" name="calendarBook.orgId" value="'+orgId+'">';
       		var htmlc = '<input type="hidden" name="calendarBook.isGlobalScope" value="2">';
       		var htmld = '<input type="hidden" name="attendanceObjectsList[0].orgId" value="'+orgId+'">';
   			var htmle = '<input type="hidden" name="attendanceObjectsList[0].attendanceObjectType" value="2">';
   			var html = htmla + htmlb+ htmlc+ htmld+ htmle;
   			$("#attendanceObject").append(html);
   			//关闭树
			$("#treeDemo").empty();
			$("#treeDemo").append("已选中:"+treeNode.name);
       }
      	//设置ztree参数
    	var setting = {
    		view: {
    			dblClickExpand: false,
    			showLine: true,
    			selectedMulti: false
    			/* addHoverDom: addHoverDom,
    			removeHoverDom: removeHoverDom,
    			selectedMulti: false */
    		},
    		/* edit: {
    			enable: true,
    			editNameSelectAll: true,
    			showRemoveBtn: showRemoveBtn,
    			showRenameBtn: showRenameBtn
    		}, */
    		data: {
    			simpleData: {
    				enable: true 
    			}
    		},
    		 callback: {
    			onClick: zTreeOnClick,
    		} 
    	}; 
         var zNodes =[];
        //加载ztree数据
        $(document).ready(function(){
        		//获取代理人company_id作为parent_id
        		var parentId = '${companyId}';
        		if(parentId!="B0311"){
        			parentId="B0311";
        		}
    			$.ajax({
			  	      type: 'post',
			  	      data :{"parentId":parentId},
			  	  	  url: "${ctx}/manage/listChildOrganization",
			  	  	  dataType:"json",
			  	  	  success: function(result) {
			  	  		$.each(result,function(index,organization){
	    					var zNode = {id:organization.id,pId:organization.parentId,name:organization.cnName,layer:organization.layer,rootId:organization.rootId, open:false};
	    					zNodes.push(zNode);
	    				});
			  		  }
			  	  	})
    	});
     
		</script>
	</body>
</html>