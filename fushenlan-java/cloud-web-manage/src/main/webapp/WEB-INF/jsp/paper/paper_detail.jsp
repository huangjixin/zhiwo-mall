<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<title>富卫运维大平台</title>
<meta name="description" content="">

</head>
<body>
<style>
	img{
	  vertical-align: baseline;
	}
    select,option{
      border:solid 1px #c7c7c7;
      width:100%;
      height:37.56px;
      border-radius: 3px;
      padding-left:20px;
    }
    .ul-top li{
    	margin-left:10px;
    }
    .col-md-4{
    	width:25%;
    	margin-left:10px;
    	
    }

</style>
		<div class="form-detail">
		
			<ul class="clearfix">
				<li class="col-md-12"><strong>菜单名称：</strong>个人背景调查</li>
				<li class="col-md-12 auto"><strong>菜单Icon：</strong><img src="" width="80" height="80"></li>
				<li class="col-md-4"><strong>试卷类型：</strong>
				                     <c:if test="${pvo.paper.paperType==1}">初审</c:if>
	                                 <c:if test="${pvo.paper.paperType==2}">甄选</c:if>
	                                 <c:if test="${pvo.paper.paperType==3}">决定</c:if>
				</li>
				<li class="col-md-4">
					<strong>分公司名称：</strong>
				    <c:forEach  varStatus="idx" var="orgList" items="${orgList}">
				    	 <c:if test="${pvo.paper.orgId == orgList.code}">${orgList.cnName}</c:if>
				    </c:forEach>
				</li>
				<li class="col-md-4"><strong>试卷名称：</strong>${pvo.paper.paperName }</li>
				<li class="col-md-12"><strong>试卷说明：</strong>${pvo.paper.paperDesc }</li>
			</ul>
			
			<div class="nav-tabs">
			    <button type="button" class="btn btn-default active" onclick="chooseOne()">基础试题</button>
			    <button type="button" class="btn btn-default" onclick="chooseTwo()">附加试题</button>
				<!-- <strong class="active">基础试题</strong>
				<strong>附加试题</strong> -->
			</div>
			
			<div class="tab-pane active" name="jichu">
			    <c:forEach items="${paperItemVoJcList}" var="paperItemVo" varStatus="ext">
			       <div class="title"><strong>试卷考题${ext.index+1}</strong></div>
				<ul class="clearfix">
					<li class="col-md-4"><strong>考题名称：</strong>${paperItemVo.paperItem.paperItemName }</li>
					<li class="col-md-4"><strong>总分：</strong>${paperItemVo.paperItem.totalScore }</li>
					<li class="col-md-4"><strong>预警分：</strong>${paperItemVo.paperItem.warningScore }</li>
					<li class="col-md-12"><strong>考题说明：</strong>${paperItemVo.paperItem.paperItemDesc }</li>
					<li class="col-md-12">
						<strong>考题内容：</strong>
						 <c:forEach items="${paperItemVo.paperItemInfo}" var="paperItemInfo" varStatus="ext">
						     <p>${paperItemInfo.paperContent }</p>
						 </c:forEach>
					</li> 
				   </ul>
			    </c:forEach>
			</div>
			<div class="tab-pane" name="fujia">
			    <c:forEach items="${paperItemVoFjList}" var="paperItemVo" varStatus="ext">
			          <div class="title" name="fujiaDiv"><strong>附加题${ext.index+1}</strong></div>
				  <ul class="clearfix" name="fujiaUl">
					<li class="col-md-4"><strong>考题名称：</strong>${paperItemVo.paperItem.paperItemName }</li>
					<li class="col-md-4"><strong>考题说明：</strong>${paperItemVo.paperItem.paperItemDesc }</li>
					<li class="col-md-12">
						<strong>考题内容：</strong>
						<c:forEach items="${paperItemVo.paperItemInfo}" var="paperItemInfo" varStatus="ext">
						     <p>${paperItemInfo.paperContent }</p>
						</c:forEach>
					</li>
				  </ul>
			    </c:forEach>
			</div> 
			
			<div class="ui-button" style="text-align:center">
				<button type="button" class="btn btn-default" onclick="javascript:history.go(-1);">返回</button>
			</div> 
			
		</div>
<script>
   $(function(){ 
	  var sss =  $("ul[name='fujiaUl']").length;
	  for(var i=0;i<sss;i++ ) {
		  var aaa =  $("div[name='fujiaDiv']").html();
	  }
   }); 
   function chooseTwo(){
	   $("div[name='fujia']").addClass("active");
	   $("div[name='jichu']").removeClass("active");
   }
   function chooseOne(){
	   $("div[name='fujia']").removeClass("active");
	   $("div[name='jichu']").addClass("active");
   }
</script>
</body>
</html>
