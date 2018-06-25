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
<script type="text/javascript"	src="${ctx}/resources/libs/layer/layer.js"></script>
<style type="text/css">
	.col{
	    background-color:red!important;
	}
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
    .mask-layer{
		width:100%;
		height:100%;
		position: absolute;
  		z-index: 10;
	}
</style>
		<div class="form-detail">
		   <form id="inputForm" action="${ctx}/manage/erecruitment/savePaper" method="post" class="form-horizontal" enctype="multipart/form-data">
		    <input type="hidden" name="paper.id" value="${pvo.paper.id}"/>
			<ul class="clearfix ul-top">
				<li class="col-md-12"><strong>菜单名称：</strong>个人背景调查</li>
				<li class="col-md-12 auto"><strong>菜单Icon：</strong><img src="" width="80" height="80"><!-- <button type="button" class="btn btn-sm btn-submit ipt-btnfile">请选择<input type="file"></button> --></li>
				<li class="col-md-4">
					<strong>试卷类型：</strong>
						<input type="text" class="ipt-text"  value="${paperType=='1'?'初审面试':paperType=='2'?'甄选面试':paperType=='3'?'决定面试':'其他'}"  readonly="readonly">
						<input type="text" class="ipt-text" name="paper.paperType" value="${paperType}" hidden  readonly="readonly">
						<%-- <select id="pt" name="paper.paperType" readonly>
						    <option value="" disabled>全部</option>
							<option value="1" <c:if test="${paperType eq '1'}">selected</c:if>>初审面试</option>
							<option value="2" <c:if test="${paperType eq '2'}">selected</c:if>>甄选面试</option>
							<option value="3" <c:if test="${paperType eq '3'}">selected</c:if>>决定面试</option>
						</select> --%>
				</li>
				<li class="col-md-4">
					<strong>分公司${userInfo.companyId}名称：</strong>
						 <c:if test="${userInfo.companyId == 'B0311'}">
					        <select id="ptorgId" name="paper.orgId" >
							    <option value="" disabled>全部</option>
							    <c:forEach  varStatus="idx" var="orgList" items="${orgList}">
							    	<option value="${orgList.code}" <c:if test="${pvo.paper.orgId == orgList.code}">selected</c:if> <c:if test="${userInfo.companyId== orgList.code}">selected</c:if>>${orgList.cnName}</option>
							    </c:forEach>
							</select>
						</c:if>
						<c:if test="${userInfo.companyId != 'B0311'}">
							 <c:forEach  varStatus="idx" var="orgList" items="${orgList}">
							    	<c:if test="${userInfo.companyId == orgList.code}">
							    		<input type="text" class="ipt-text"  value="${orgList.cnName}"  readonly="readonly">
							  			<input type="text" class="ipt-text" name="paper.orgId" value="${orgList.code}" hidden  readonly="readonly">
							  		</c:if> 
							 </c:forEach>
						</c:if>
				</li>
				<li class="col-md-4"><strong>试卷名称：</strong><input type="text" class="ipt-text" name="paper.paperName" value="${pvo.paper.paperName }"></li>
				<li class="col-md-12"><strong>试卷说明：</strong><input type="text" class="ipt-text" name="paper.paperDesc" value="${pvo.paper.paperDesc }"></li>
			</ul>
			
			<div class="nav-tabs">
			    <!-- <strong class="active " onclick="chooseOne()">基础试题</strong>
				<strong onclick="chooseTwo()">附加试题</strong> -->
			    <button type="button" class="btn btn-default active col" id="jichust" onclick="chooseOne(this)">基础试题</button>
			    <button type="button" class="btn btn-default" id="fujiast" onclick="chooseTwo(this)">附加试题</button>
			</div>
			
			<div class="tab-pane active" name="jichu">
				 <div class="<c:if test='${userInfo.companyId != "B0311"}'>mask-layer</c:if>"></div>
			    <c:forEach items="${paperItemVoJcList}" var="paperItemVo" varStatus="ext">
			   <div name="jzDiv">
			       <div class="title" name="sq${ext.index}"><strong>试卷考题${ext.index+1}</strong><button type="button" onclick="removeQuestions(this)" title="删除考题"><i class="icon-delete"></i></button></div>
				<ul class="edit clearfix" name="edit${ext.index}">
				    <input type="hidden" name="paperItemVo[${ext.index}].paperItem.paperType" value="1">
					<li class="col-md-4"><strong>考题名称：</strong><input type="text" name="paperItemVo[${ext.index}].paperItem.paperItemName" class="ipt-text" value="${paperItemVo.paperItem.paperItemName }"></li>
					<li class="col-md-4"><strong>总分：</strong><input type="text" name="paperItemVo[${ext.index}].paperItem.totalScore" class="ipt-text" value="${paperItemVo.paperItem.totalScore }"></li>
					<li class="col-md-4"><strong>预警分：</strong><input type="text" name="paperItemVo[${ext.index}].paperItem.warningScore" class="ipt-text" value="${paperItemVo.paperItem.warningScore }"></li>
					<li class="col-md-12"><strong>考题说明：</strong><input type="text" name="paperItemVo[${ext.index}].paperItem.paperItemDesc" class="ipt-text" value="${paperItemVo.paperItem.paperItemDesc }"></li>
					<li class="col-md-12">
						<strong>考题内容：</strong>
						 <c:forEach items="${paperItemVo.paperItemInfo}" var="paperItemInfo" varStatus="ex">
						     <p name="jchu${ex.index}"><input type="text" id="jzContent${ex.index}" class="ipt-text" name="paperItemVo[${ext.index}].paperItemInfo[${ex.index}].paperContent" value="${paperItemInfo.paperContent }" style="margin-bottom:10px;"><button type="button" onclick="removeZcContent(this)" title="移除考题内容"><i class="icon-remove"></i></button></p>
						 </c:forEach>
					</li>
					<li class="col-md-12">
						    <button type="button" onclick="addP(this)" class="btn btn-sm btn-submit">+ 添加</button>
					</li>  
				   </ul>
				 </div> 
			    </c:forEach>
			    
			    <div id="addzejia">
			       <button type="button" class="btn btn-submit" onclick="addtr('addzejia',this);">+ 添加考题</button>
			    </div>
			 </div>   
			<div class="tab-pane" name="fujia">
			    <c:forEach items="${paperItemVoFjList}" var="paperItemVo" varStatus="ext">
			      <div name="fjDiv">
			          <div class="title" name="sqFz${ext.index+pLt}"><strong>附加题${ext.index+1}</strong><button type="button" onclick="removeFzQuestions(this)" title="删除附加题"><i class="icon-delete"></i></button></div>
				   <ul class=" edit clearfix" name="fuji${ext.index+pLt}">
				    <input type="hidden" name="paperItemVo[${ext.index+pLt}].paperItem.paperType" value="2">
					<li class="col-md-4"><strong>考题名称：</strong><input type="text" name="paperItemVo[${ext.index+pLt}].paperItem.paperItemName" class="ipt-text" value="${paperItemVo.paperItem.paperItemName }"></li>
					<li class="col-md-4"><strong>考题说明：</strong><input type="text" name="paperItemVo[${ext.index+pLt}].paperItem.paperItemDesc" class="ipt-text" value="${paperItemVo.paperItem.paperItemDesc }"></li>
					<li class="col-md-12">
						<strong>考题内容：</strong>
						<c:forEach items="${paperItemVo.paperItemInfo}" var="paperItemInfo" varStatus="ex">
						     <p name="jchu${ex.index}"><input id="fjContent${ex.index}" type="text" class="ipt-text" name="paperItemVo[${ext.index+pLt}].paperItemInfo[${ex.index}].paperContent" value="${paperItemInfo.paperContent }" ><button type="button" onclick="removeFjContent(this)" title="移除考题内容"><i class="icon-remove"></i></button></p>
						</c:forEach>
					</li>
					<li class="col-md-12">
						    <button type="button" onclick="addFjP(this)" class="btn btn-sm btn-submit">+ 添加</button>
					</li>
				  </ul>
				  </div>
			    </c:forEach>
			          <div id="addfujia">
			              <button type="button" class="btn btn-submit" onclick="addFujia('addfujia',this);">+ 添加附加题</button>
			          </div>    
			</div>
			</form> 	
			<div class="ui-button" style="text-align:center">
			    <button type="button" class="btn btn-submit" onclick="savePa()">保存</button>
				<button type="button" class="btn btn-default" onclick="javascript:history.go(-1);">返回</button>
			</div> 
		</div>

<script>
    function chooseTwo(){
	   $("div[name='fujia']").addClass("active");
	   $("div[name='jichu']").removeClass("active");
	   $("#fujiast").addClass("col");
	   $("#jichust").removeClass("col");
	   
    }
    function chooseOne(){
	   $("div[name='fujia']").removeClass("active");
	   $("div[name='jichu']").addClass("active");
	   $("#fujiast").removeClass("col");
	   $("#jichust").addClass("col");
    }
    
    var countTwo=0;
    function addtr(id,obj){
    	var sss = 1 ;
    	var count=$("div[name='jichu']").children().length-1;
    	var countFj=$("div[name='fujia']").children().length-1;
    	if(count==0){
    		sss=1;
    	}else{
    		sss = $("div[name='jzDiv']").last().children().first().children().first().html().slice(4);
    		sss++;
    	}
    	if(count==0){
    		
    		count=0;
    	}else if(count>0 && countFj==0){   //说名附加题没有，基础题不是第一个
    		count = $("div[name='jzDiv']").last().children().first().attr("name").slice(2);
    	    count++;
    	}else if(countFj>0 && count>1 ){
    		count = $("div[name='jzDiv']").last().children().first().attr("name").slice(2);
			countFj = $("div[name='fjDiv']").last().children().first().attr("name").slice(4);
			if(count>countFj){
				count++
			}else{
				count=countFj*1+1;
			}
    	} 
    	countTwo =$(obj).parent().prev().children("p").length;
    	var aHtml ='<div name="jzDiv">'+
    		'<div class="title" name="sq'+count+'"><strong>试卷考题'+sss+'</strong><button type="button" onclick="removeQuestions(this)" title="删除考题"><i class="icon-delete"></i></button></div>'+
		    '<ul class="edit clearfix" name="edit'+count+'">'+
		    '<input type="hidden" name="paperItemVo['+count+'].paperItem.paperType" value="1">'+
			'<li class="col-md-4"><strong>考题名称：</strong><input type="text" class="ipt-text" name="paperItemVo['+count+'].paperItem.paperItemName" value=""></li>'+
			'<li class="col-md-4"><strong>总分：</strong><input type="text" class="ipt-text" name="paperItemVo['+count+'].paperItem.totalScore" value=""></li>'+
			'<li class="col-md-4"><strong>预警分：</strong><input type="text" class="ipt-text" name="paperItemVo['+count+'].paperItem.warningScore" value=""></li>'+
			'<li class="col-md-12"><strong>考题说明：</strong><input type="text" class="ipt-text" name="paperItemVo['+count+'].paperItem.paperItemDesc" value=""></li>'+
			'<li class="col-md-12">'+
				'<strong>考题内容：</strong>'+
				     '<p name="jchu'+countTwo+'"><input type="text" class="ipt-text" name="paperItemVo['+count+'].paperItemInfo['+countTwo+'].paperContent" value="" style="margin-bottom:10px;"><button type="button" onclick="removeZcContent(this)" title="移除考题内容"><i class="icon-remove"></i></button></p>'+
			'</li>'+
			'<li class="col-md-12">'+
				   '<button type="button" class="btn btn-sm btn-submit" onclick="addP(this)">+ 添加</button>'+
			'</li>'+ 
		   '</ul>'+
		   '</div>';
    	$("#"+id).before(aHtml);       
    }
    
    function addP(obj){
    	var ss = $(obj).parent().prev().children("p").last().attr("name").slice(4);
    	ss++;
    	var dd  = $(obj).parent().parent().attr("name").slice(4);
    	var bil = $(obj).parent().prev().last();
    	var ap = '<p name="zchu'+ss+'"><input type="text" class="ipt-text" name="paperItemVo['+dd+'].paperItemInfo['+ss+'].paperContent" value="" style="margin-bottom:10px;"><button type="button" onclick="removeZcContent(this)" title="移除考题内容"><i class="icon-remove"></i></button></p>';
    	 bil.append(ap);
    }
    
    function removeZcContent(obj){
    	var tlen  = $(obj).parent().parent().children("p").length;
    	if(tlen == 1){
    		$("#jzContent0").val("");
    		layer.msg("最后一条了");
    	}else{
    		$(obj).parent().remove();
    	}
    }
    
    function removeQuestions(obj){
    	$(obj).parent().parent().remove();
    	$("div[name='jzDiv']").each(function(index){
    		var bg =index*1+1
    		nr ='<strong>试卷考题'+bg+'</strong><button type="button" onclick="removeQuestions(this)" title="删除考题"><i class="icon-delete"></i></button>';
    		$(this).children().first().children().remove();
    		$(this).children().first().append(nr);
    	})
    }
    
    
    
    var countTwoFj=0;
    function addFujia(id,obj){
    	var hh=1;
    	var countFj=$("div[name='fujia']").children().length-1;
    	var count=$("div[name='jichu']").children().length-1;
    	if(countFj == 0){
    		hh=1;
    	}else{
    		 hh  = $("div[name='fjDiv']").last().children().first().children().first().html().slice(3);
    		 hh++;
    	} 
    	if(countFj==0){  //说名附加题是第一个
    		countFj=0;
    	    if(count==0){
    	    	countFj++;
    	    }else{
    	    	count = $("div[name='jzDiv']").last().children().first().attr("name").slice(2); //得先田填基础题
        		countFj=count*1;
        		countFj++;
    	    }
    	}else if(countFj*1>0 && count==1 ){   //说名附加题不是第一个，基础题是第一个
    		countFj = $("div[name='fjDiv']").last().children().first().attr("name").slice(4);  //1
    		countFj++;
    	}else if(countFj>0 && count>1){       //说名附加题不是第一个，基础题不是第一个
    		count = $("div[name='jzDiv']").last().children().first().attr("name").slice(2);
    		countFj = $("div[name='fjDiv']").last().children().first().attr("name").slice(4);
    		if(countFj > count){
    			countFj++;
    		}else{
    			countFj=count*1+1;
    		}
    	}
    	countTwoFj  = $(obj).parent().prev().children("p").length;
    	var aHtml ='<div name="fjDiv">'+
    	'<div class="title" name="sqFz'+countFj+'"><strong>附加题'+hh+'</strong><button type="button" onclick="removeFzQuestions(this)" title="删除考题"><i class="icon-delete"></i></button></div>'+
    	'<ul class="edit clearfix" name="fuji'+countFj+'">'+
    	'<input type="hidden" name="paperItemVo['+countFj+'].paperItem.paperType" value="2">'+
    	'<li class="col-md-4"><strong>考题名称：</strong><input type="text" class="ipt-text" name="paperItemVo['+countFj+'].paperItem.paperItemName" value=""></li>'+
    	'<li class="col-md-12"><strong>考题说明：</strong><input type="text" class="ipt-text" name="paperItemVo['+countFj+'].paperItem.paperItemDesc" value=""></li>'+
    	'<li class="col-md-12">'+
    	'<strong>考题内容：</strong>'+
    	'<p name="fjia'+countTwoFj+'"><input type="text" class="ipt-text" name="paperItemVo['+countFj+'].paperItemInfo['+countTwoFj+'].paperContent" value="" style="margin-bottom:10px;"><button type="button" onclick="removeFjContent(this)" title="移除考题内容"><i class="icon-remove"></i></button></p>'+
        '</li>'+
    	'<li class="col-md-12">'+
    	'<button type="button" class="btn btn-sm btn-submit" onclick="addFjP(this)">+ 添加</button>'+
    	'</li>'+
    	'</ul>'+
    	'</div>';
    	$("#"+id).before(aHtml);
    }
    function addFjP(obj){
    	var lg = $(obj).parent().prev().children("p").last().attr("name").slice(4);
    	lg++;
    	var jq  = $(obj).parent().parent().attr("name").slice(4);
    	var bFzil = $(obj).parent().prev().last();
    	var aFzp = '<p name="fjia'+lg+'"><input type="text" class="ipt-text" name="paperItemVo['+jq+'].paperItemInfo['+lg+'].paperContent" value="" style="margin-bottom:10px;"><button type="button" onclick="removeFjContent(this)" title="移除考题内容"><i class="icon-remove"></i></button></p>';
    	bFzil.append(aFzp);
    }
    
    function removeFjContent(obj){
    	var tFlen  = $(obj).parent().parent().children("p").length;
    	if(tFlen == 1){
    		$("#fjContent0").val("");
    		layer.msg("最后一条了");
    	}else{
    		$(obj).parent().remove();
    	}
    }
    function removeFzQuestions(obj){
    	$(obj).parent().parent().remove();
    	$("div[name='fjDiv']").each(function(index){
    		var fg =index*1+1
    		nr ='<strong>附加题'+fg+'</strong><button type="button" onclick="removeFzQuestions(this)" title="删除考题"><i class="icon-delete"></i></button>';
    		$(this).children().first().children().remove();
    		$(this).children().first().append(nr);
    	})
    	
    }
    
    function savePa(){
    	 	$.ajax({
				type : "post",
				data:$("#inputForm").serialize(),
				url : "${ctx}/manage/erecruitment/savePaper",
				dateType: "json",
				success : function(msg) {
					if (null != msg) {
						layer.alert("保存成功", {icon: 1}, function(){
							parent.location.href="${ctx}/manage/erecruitment/paperList";
						});
					}else {
						layer.alert("保存失败", {icon: 5});
					}
				}
			});
        }
    
</script>
</body>
</html>
