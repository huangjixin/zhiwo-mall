<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
			<div class="title diff_btn"></div>
			<ul class="clearfix form_learn">
				<li class="col-md-12"><strong><span style="color:red">*</span>活动名称：</strong><label>${offlineActivity.name }</label></li>
				<li class="col-md-12"><strong><span style="color:red">*</span>活动说明：</strong><label>${offlineActivity.description }</label></li>
				<li style="margin-top:10px;" class="col-md-10"><strong><span style="color:red">*</span>活动缩略图：</strong><img src="${offlineActivity.bannerPath }" width="80" height="80"></li>
				<li style="margin-top:30px;" class="col-md-12"><strong>报名时间：</strong><label style="margin-right:30px">${offlineActivity.enterStartDate }
				--&nbsp;${offlineActivity.enterEndDate }</label> 
				</li>
				<li class="col-md-12"><strong>课程状态:</strong><label style="margin-right:30px">
				<c:choose>
				<c:when test="${offlineActivity.state==0 }">下架</c:when>
				<c:when test="${offlineActivity.state==1 }">上架</c:when>
				<c:otherwise>未上架</c:otherwise>
				</c:choose>
				</label>
				</li>
				<li class="col-md-12"><strong>上课时间：</strong><label style="margin-right:30px">${offlineActivity.startDate }--${offlineActivity.endDate }</label>
				</li>
				<li class="col-md-6"><strong>上课地点：</strong><label>${offlineActivity.address }</label></li>
				<li class="col-md-12 zlgl_share"><strong>签到设置：</strong>
					<label>
					<c:if test="${offlineActivity.isNeedEnter==1 }">需要报名</c:if>
			        <c:if test="${offlineActivity.isNeedEnter==0 }">不需要报名</c:if>
					</label>
					<label for="bkck">
					<c:if test="${offlineActivity.isNeedSign==1 }">需要签到</c:if>
			        <c:if test="${offlineActivity.isNeedSign==0 }">不需要签到</c:if>
					</label>
					<label>
                    <c:if test="${offlineActivity.isNeedSign==1 }">不报名也能签到</c:if>
			        <c:if test="${offlineActivity.isNeedSign==0 }">不报名不能签到</c:if>
			        </label>
				</li>
				<li class="col-md-12"><strong>签到时间：</strong>
				<label style="margin-right:30px">
				<c:if test="${offlineActivity.signTimeType==1 }">
				课程开始前<span style="color:red">  ${offlineActivity.beforeBegin }</span> 分钟(截止到上课前)
				</c:if>
				<c:if test="${offlineActivity.signTimeType==2 }">
				课程开始前<span style="color:red"> ${offlineActivity.beforeBegin }</span> 分钟到课程开始后<span style="color:red"> 
				  ${offlineActivity.afterBegin }</span>分钟内
				</c:if>
				<c:if test="${offlineActivity.signTimeType==3 }">
				课程开始后 <span style="color:red">  ${offlineActivity.afterBegin }</span>
				</c:if>
				</label>
				</li>
				<li class="col-md-12"><strong>权限设置：</strong><label style="margin-right:30px"></label>
				<c:forEach items="${planAuthorityList}" var="planAuthority" varStatus="extx">
    	         ${planAuthority.authorityType}:${planAuthority.associateId};
			    </c:forEach>
				</li>
				
				<li class="col-md-12 zlgl_share"><strong>计划奖励：</strong>
					<label for="integra">
					<c:if test="${offlineActivity.isRewardPoint==1 }">积分奖励:${offlineActivity.rewardPoint }</c:if>
					</label>
				</li>
			</ul>
		<div class="title">
			用户报名列表
			</div>
			<div>
		    <form id="f1" class="f3"  method="get">
				<ul class="clearfix form_learn">
					<li class="col-md-12">
					<input type="hidden" name="id" id="acid" >
					<input type="hidden" id="check" name="type" value="check">
					用户姓名:<input type="text" id="accountName" name="accountName" class="ipt-text" value=""  style="max-width: 130px;">
					<input type="radio"   name="isSign" value="1"/><label style="margin-right:10px">已签到</label>
					<input type="radio" name="isSign" value="0"/><label style="margin-right:10px">未签到</label>
					<button type="button" onclick="search()" style="top:392px;" class="btn btn-submit btn-radius btn-search"><i class="icon-search"></i> 查询</button>
				</ul>
			</form>
			</div>
			<hr />
			<table class="table table-agents">
			<tbody id="tbd">
			</tbody>
		      </table>

		<div id="div1"></div>

	   <%--  <script src="${ctx}/resources/js/common/jquery-3.2.1.min.js"></script> --%>
       <%-- <script src="${ctx}/resources/js/elerning/common.js"></script>  --%>
       <script type="text/javascript" src="${ctx}/resources/libs/layer/layer.js" ></script>
	   <script type="text/javascript" src="${ctx}/resources/libs/datepicker/WdatePicker.js"></script>
	   <script type="text/javascript" src="${ctx}/resources/js/common/page.js"></script>
	   <script type="text/javascript">
	   var id="${id}";
	   $("#acid").val(id);
	   function search(){
		   var type=$("#check").val();
		   var accountName=$("#accountName").val();
		   var isSign=$("#f1 input[name=isSign]:checked").val();
		    var html = '';
			var para = $('#f1').serialize();
			$.ajax({
			  type : 'GET',
			  dataType : "json",
			  data : para,
			  url : "${ctx}/manage/offlineManage/findByAccountName",//请求的action路径  
			  error : function(data) {//请求失败处理函数  
			    layer.msg("系统繁忙~~~");
				},
				
			  success : function(data){ //请求成功后处理函数。
			  if(null != data["page"]){
			  var data2 =data["page"].records;
			  var id=data["offlineActivity"].id;
			  var pageSize=data["page"].pageSize;
			  var pageTotal=data["page"].pageTotal;
			  var pageNo=data["page"].pageNo;
			  var mapIsign = data["mapIsign"];
			  var mapIsign_arr = {};
			  $.each(mapIsign,function(i, item){
				  mapIsign_arr[i]=item;
			   });
			  html += "<thead><tr><th><label class='pos-rel'>用户名</label></th><th>机构</th><th>城市</th>";
			  html += "<th>职级</th><th>报名日期</th><th>签到状态</th><th>签到时间</th></tr></thread>";
			  html += '<tbody>';	
			  $.each(data2,function(i, item){
					  html += "<tr><td>";
					  html += "<label class='pos-rel'>"+item.accountName+"</label></td>";
					  html += "<td>"+item.branchName+"</td><td>" + item.branchProvince+ "</td><td>"+ item.postType+ "</td><td>"+ item.enterTime+
					  	"</td><td>"+mapIsign_arr[item.id].isSign+"</td><td>"+mapIsign_arr[item.id].gmtCreate+"</td></tr>";
			   });
			  html += "</tbody>";
			  $("#tbd").html(html); 
			  var dd="<div class='table-paging clearfix' id='pageDiv'></div>";
			  $("#div1").html(dd);
			  page("${ctx}/manage/offlineManage/findByid?id="+id+"&accountName="+accountName+"&isSign="+isSign+"&type="+type,2,pageNo,pageTotal,'pageDiv');
			  }else{
				  $("#tbd").html('');   
			  }
			  }
	      });
	   };
	     $(function(){
		   search();
	    });
	  
	   </script>
</body>
</html>