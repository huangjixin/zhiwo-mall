<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<title>富卫运维大平台</title>
<meta name="description" content="">
<link rel="stylesheet" type="text/css" href="../../css/style.css">
<link rel="stylesheet" type="text/css" href="../../css/font/iconfont.css">
<link rel="stylesheet" type="text/css" href="../../css/page.css">
</head>
<body>
	<style>
		.zw_fi{
			margin-top:20px;
			height:60px;
		}
		.ax_default{
		    font-family: 'Arial Normal', 'Arial';
		    font-weight: 400;
		    font-style: normal;
		    font-size: 13px;
		    color: #333333;
		    line-height: normal;
		    float:left;
		    margin-left:30px;
		}
		.ax_default div{
			padding: 10px;
		    border: 1px solid #ed9720;
		    background-color: #ed9720;
		    max-width: 100px;
		    color: #fff;
		    text-align: center;
			
		}
		.ty_div{
			width:112px;
			text-align:center;
		}
		.ty_div p{
	   		position: relative;
		    top: -76px;
		    width: 86px;
		    word-wrap: break-word;
		    font-size: 45px;
		    color: #FFFFFF;
		    font-family: 'Arial Negreta', 'Arial Normal', 'Arial';
		}
		.form-detail {
	    min-height: 100%;
	    padding: 20px;
	    background-color: #fff;
	    border-radius: 5px;
	    position: relative;
	}
	.title {
	    position: relative;
	    line-height: 36px;
	    border-bottom: 1px solid #dfdfdf;
	    margin-bottom: 10px;
	}
	.form-detail ul {
	    margin-bottom: 15px;
	}
	.form-detail li {
	    position: relative;
	    line-height: 38px;
	    min-height: 38px;
	    font-size: 14px;
	    margin: 5px 0;
	}
	.process_list>li span{
		font-family: 'Arial Negreta', 'Arial Normal', 'Arial';
    	font-weight: 700;
    	font-style: normal;
    	font-size: 45px;
    	color: #FFFFFF;
	    background-color: rgba(255, 153, 0, 1);
	    width: 90px;
	    height: 90px;
	    line-height: 90px;
	    text-align: center;
	    display: inline-block;
	    border-radius: 50%;
	}
	.process_list>li{
		padding: 20px 0;
		border-bottom: 1px solid #dfdfdf;
	}
	.tache_hd{
		width: 205px;
		height: 110px;
		background: rgba(255, 153, 0, 1);
		border-radius: 5px;
	    padding: 0 10px;
	    float: left;
	    margin-left: 30px;
	}
	.tache_hd h5{
		font-size: 14px;
		color: #fff;
		font-weight: 700;
		display: inline-block;
		float: left;
		line-height: 35px;
	}
	.tache_hd .btn_lab{
		float: right;
	}
	.tache_hd .btn_lab button{
		width: 25px;
	}
	.tache_content{
		float: left;
		width:75%;
	}
	.tache_content i{
		color: #fff;
	}
	.tache1{
	    display: inline-block;
    	vertical-align: middle;
	}
	.tache_check button{
		width: 100%;
		height: 100%;
		color: #fff;
		font-size: 14px;
		font-weight: 700;
	}
	.ui-button{
		text-align: center;
	}
	.ui-button .btn{
		width: 90px;
		height: 30px;
		line-height: 30px;
		margin: 0 10px;
	}
	.ty{
		font-family: 'Arial Negreta', 'Arial Normal', 'Arial';
	    font-weight: 700;
	    font-style: normal;
	    font-size: 45px;
	    color: #FFFFFF;
	    background-color: rgba(255, 153, 0, 1);
	    width: 90px;
	    height: 90px;
	    line-height: 90px;
	    text-align: center;
	    display: inline-block;
	    border-radius: 50%;
	}
	.layer_col{
		width:100%;
	}
	select{
		width: 100%;
	    font-size: 14px;
	    line-height: 36px;
	    padding: 0 10px;
	    border: 1px solid #dadada;
	    background-color: #fff;
	    border-radius: 3px;
	    box-sizing: border-box;
	}
	
	.ui-form li{
		margin-left:0px;
	}
	.btn-search{
		    position: inherit;
	}
	.mask-layer{
		width:100%;
		height:100%;
		position: absolute;
  		z-index: 10;
	}
	</style>
	
	
	<div class="filter-box">
     <div class="<c:if test='${operation=="check"}'>mask-layer</c:if>"></div>
	   <div>增员业务功能</div>
	   <!-- BTN1 (矩形) -->
   		<input name="id" hidden id="flowId" value="${ flow.id}">
   		
		<ul class="ui-form grid-row" style="margin-top:30px;">
			<li class="col-md-5 auto"><strong class="long">流程名称：</strong><input class="ipt-text" type="text" name="flow_name" id ="flowName"  value="${flow.flowName }" placeholder=""></li>
			<li class="col-md-5 auto">
				<strong class="long">流程说明：</strong><input class="ipt-text" type="text" name="flowDesc" id ="flowDesc" value="${flow.flowDesc }" placeholder="">
			</li>
			<li class="col-md-5 auto">
				<strong class="long">所属分公司：</strong>
				<select class="ipt-text"  name="orgId" id ="orgId" <c:if test="${flow.headFlag=='Y'}">disabled</c:if> <c:if test="${userInfo.companyId != 'B0311'}">disabled</c:if> f_id="${flow.orgId}" value="" placeholder="">
				 	<option  value="">请选择分公司</option>
					<c:forEach  varStatus="idx" var="org" items="${orgList}">
						  <option <c:if test="${userInfo.companyId != 'B0311' and userInfo.companyId == org.code}">selected</c:if> <c:if test="${org.code == flow.orgId}">selected</c:if> value="${org.code}">${org.cnName}</option>
					</c:forEach>
				</select>
				<%-- 是否总公司:<input id="isHeadFlag" type="checkbox" <c:if test="${flow.headFlag=='Y'}">checked</c:if> value=""> --%>
			</li>
			<li class="col-md-5 auto" hidden>
					<select id="selectCsList">
						<c:forEach  varStatus="idx" var="plist" items="${pList}">
						  <option value="${plist.code}">${plist.value}</option>
						</c:forEach>
					</select>
					
					<select id="selectYwList">
						<c:forEach  varStatus="idx" var="flowItemList" items="${flowItemList}">
						  <option value="${flowItemList.id}">${flowItemList.flowItemName}</option>
						</c:forEach>
					</select>
			</li>
		</ul>
	</div>

	
		<div class="form-detail offline_form">
		 <div class="<c:if test='${operation=="check"}'>mask-layer</c:if>"></div>
			<div class="title diff_btn">
				<strong>流程配置</strong>
			</div>
			<ul class="clearfix process_list xinzeng">
				<li>
					<span>1</span>
					<div class="tache1">
						<div class="tache_hd clearfix" >
							<h5>流程环节1</h5>
							<div class="btn_lab">
								<button class="bianji">
									<img src="${ctx}/resources/imgs/erecruitment/u5393.png" alt="">
								</button>
								<button class="del">
									<img src="${ctx}/resources/imgs/erecruitment/u5395.png" alt="">
								</button>
							</div>
							<div class="tache_content yw">
								<h5>业务模块：</h5>
								<i f_id="1">在线增员</i>
							</div>
							<div class="tache_content cs">
								<h5>从属组织：</h5>
								<i f_id="${plist[0].code}">${plist[0].value}</i>
							</div>
						</div>
						<div class="tache_hd tache_check ty">
							<button>+添加流程环节</button>
						</div>
					</div>
				</li>
				<li>
					<span>2</span>
					<div class="tache1">
						<div class="tache_hd clearfix" >
							<h5>流程环节2</h5>
							<div class="btn_lab">
								<button class="bianji">
									<img src="${ctx}/resources/imgs/erecruitment/u5393.png" alt="">
								</button>
								<button class="del">
									<img src="${ctx}/resources/imgs/erecruitment/u5395.png" alt="">
								</button>
							</div>
							<div class="tache_content yw">
								<h5>业务模块：</h5>
								<i f_id="1">在线增员</i>
							</div>
							<div class="tache_content cs">
								<h5>从属组织：</h5>
								<i f_id="${plist[0].code}">${plist[0].value}</i>
							</div>
							<div class="btn_lab ">
								<button>
									<input type="checkbox" class="isCheck" checked value="1">
								</button>
							</div>
						</div>
						<div class="tache_hd tache_check ty">
							<button>+添加流程环节</button>
						</div>
					</div>
				</li>
				<li>
					<span>3</span>
					<div class="tache1">
						<div class="tache_hd clearfix" >
							<h5>流程环节3</h5>
							<div class="btn_lab">
								<button class="bianji">
									<img src="${ctx}/resources/imgs/erecruitment/u5393.png" alt="">
								</button>
								<button class="del">
									<img src="${ctx}/resources/imgs/erecruitment/u5395.png" alt="">
								</button>
							</div>
							<div class="tache_content yw">
								<h5>业务模块：</h5>
								<i f_id="1">在线增员</i>
							</div>
							<div class="tache_content cs">
								<h5>从属组织：</h5>
								<i f_id="${plist[0].code}">${plist[0].value}</i>
							</div>
							<div class="btn_lab ">
										<button>
											<input type="checkbox" class="isCheck" checked value="1">
										</button>
							</div>
						</div>
						<div class="tache_hd tache_check ty">
							<button>+添加流程环节</button>
						</div>
					</div>
				</li>
				<li>
					<span>4</span>
					<div class="tache1">
						<div class="tache_hd clearfix" >
							<h5>流程环节4</h5>
							<div class="btn_lab">
								<button class="bianji">
									<img src="${ctx}/resources/imgs/erecruitment/u5393.png" alt="">
								</button>
								<button class="del">
									<img src="${ctx}/resources/imgs/erecruitment/u5395.png" alt="">
								</button>
							</div>
							<div class="tache_content yw">
								<h5>业务模块：</h5>
								<i f_id="1">在线增员</i>
							</div>
							<div class="tache_content cs">
								<h5>从属组织：</h5>
								<i f_id="${plist[0].code}">${plist[0].value}</i>
							</div>
							<div class="btn_lab ">
										<button>
											<input type="checkbox" class="isCheck" checked value="1">
										</button>
							</div>
						</div>
						<div class="tache_hd tache_check ty">
							<button>+添加流程环节</button>
						</div>
					</div>
				</li>
				
				<li>
					<span>5</span>
					<div class="tache1">
						<div class="tache_hd clearfix">
							<h5>流程环节5</h5>
							<div class="btn_lab">
								<button class="bianji">
									<img src="${ctx}/resources/imgs/erecruitment/u5393.png" alt="">
								</button>
								<button class="del">
									<img src="${ctx}/resources/imgs/erecruitment/u5395.png" alt="">
								</button>
							</div>
							<div class="tache_content yw">
								<h5>业务模块：</h5>
								<i f_id="5">培训报名</i>
							</div>
							<div class="tache_content cs">
								<h5>从属组织：</h5>
								<i f_id="${plist[1].code}">${plist[1].value}</i>
							</div>
						</div>
					</div>
				</li>
				<li>
					<span>6</span>
					<div class="tache1">
						<div class="tache_hd clearfix">
							<h5>流程环节6</h5>
							<div class="btn_lab">
								<button class="bianji">
									<img src="${ctx}/resources/imgs/erecruitment/u5393.png" alt="">
								</button>
								<button class="del">
									<img src="${ctx}/resources/imgs/erecruitment/u5395.png" alt="">
								</button>
							</div>
							<div class="tache_content yw">
								<h5>业务模块：</h5>
								<i f_id="6">培训考试</i>
							</div>
							<div class="tache_content cs">
								<h5>从属组织：</h5>
								<i f_id="${plist[2].code}">${plist[2].value}</i>
							</div>
						</div>
					</div>
				</li>
				<li>
					<span>7</span>
					<div class="tache1">
						<div class="tache_hd clearfix">
							<h5>流程环节7</h5>
							<div class="btn_lab">
								<button class="bianji">
									<img src="${ctx}/resources/imgs/erecruitment/u5393.png" alt="">
								</button>
								<button class="del">
									<img src="${ctx}/resources/imgs/erecruitment/u5395.png" alt="">
								</button>
							</div>
							<div class="tache_content yw">
								<h5>业务模块：</h5>
								<i f_id="7">签约</i>
							</div>
							<div class="tache_content cs">
								<h5>从属组织：</h5>
								<i f_id="${plist[3].code}">${plist[3].value}</i>
							</div>
						</div>
					</div>
				</li>
				<!-- <li>
					<span>5</span>
					<div class="tache1">
					
						<div class="tache_hd tache_check bianji">
							<button>+添加流程环节</button>
						</div>
					</div>
				</li> -->
			</ul>
			<ul class="clearfix process_list update">
				 <c:if test="${not empty aFlowList}">
				 	  <c:forEach  varStatus="varStatus" var="aflow" items="${aFlowList}">
				 	  	<li>
							<span>1 </span>
							<div class="tache1">
								<div class="tache_hd clearfix" >
									<h5>流程环节${varStatus.index+1}</h5>
									<div class="btn_lab">
										<button class="bianji">
											<img src="${ctx}/resources/imgs/erecruitment/u5393.png" alt="">
										</button>
										<button class="del">
											<img src="${ctx}/resources/imgs/erecruitment/u5395.png" alt="">
										</button>
									</div>
									<div class="tache_content yw">
										<h5>业务模块：</h5>
										<i f_id="${aflow.flowItemId}">${aflow.flowItemName }</i>
									</div>
									<div class="tache_content cs">
										<h5>从属组织：</h5>
										<i f_id="${aflow.orgId}">
											<c:forEach  varStatus="idx" var="plist" items="${pList}">
												<c:if test="${aflow.orgId==plist.code}"> ${plist.value}</c:if>
											</c:forEach>
										</i>
									</div>
									
									<c:if test="${not empty aflow.isCheck}">
									<div class="btn_lab ">
										<button>
											<input type="checkbox" class="isCheck" checked value="1">
										</button>
									</div>
									</c:if>
									
								</div>
								<c:if test="${not empty aflow.isCheck or varStatus.index eq '0'}">
									<div class="tache_hd tache_check ty">
										<button>+添加流程环节</button>
									</div>
								</c:if>
							</div>
						</li>
				 	  </c:forEach>
				 </c:if>
			</ul>
		</div>
		<div class="ui-button form-detail" style="min-height:60px;">
				<c:if test="${operation=='modify'}">
					
						<button type="button" class="btn btn-submit" onclick="save()">保存</button>
						<button type="button" class="btn btn-default" onclick="cancal()">取消</button>
					
				</c:if>
				<c:if test="${operation=='check'}">
						<button type="button" class="btn btn-submit" onclick="javascript:history.back(-1)">返回</button>
				</c:if>
		 </div>
<script type="text/javascript" src="${ctx}/resources/js/common/page.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/layer/layer.js"></script>
<script>
	
	$("#flowDesc").click(function(){
		
	})

	//初始化加载
	$(document).ready(function(){
		if("${aFlowList}" != ""){
			$(".xinzeng").remove();
		}
		sort();
	})
	//排序
	function sort(){
		$(".process_list li").each(function(idx){
			$(this).find("span").text(idx+1);
			$(this).find(".tache_hd").children("h5").text("流程环节"+(idx+1));
		})
	}
	//添加节点
	$(".process_list").on("click",".ty",function(){
		if($(".process_list li").length<7){
			var item='<li><span></span><div class="tache1"><div class="tache_hd tache_check bianji"><button>+添加环节</button>'
				    + '	<input type="checkbox" checked hidden  class="isCheck" value="1"/>'
				    +'</div><div class="tache_hd tache_check  ty"><button>+添加流程环节</button></div></div></li>';
			$(this).parents("li").after(item);
			sort();
		}else{
			layer.msg("无法添加节点");
		}
	})
	
	//点击删除事件
	$(".process_list").on("click",".del",function(){
		var _this = $(this);
		if(_this.parent().parent().find(".isCheck").hasClass("isCheck")){
			layer.confirm('确认删除？', {
				  btn: ['删除','取消'] //按钮
				}, function(){
				  _this.parents("li").remove();
				  //_this.parents("li").html("<span></span><div class='tache1'><div class='tache_hd tache_check  ty'><button>+添加流程环节</button></div></div>");
				  layer.msg('节点已移除', {icon: 1});
				  sort();
				});
		}else{
			 layer.msg('节点不可移除', {icon: 1});
		}
	})
	//编辑
	$(".process_list").on("click",".bianji",function(){
		var currentyw = $(this).parents(".tache_hd").find(".yw i").attr("f_id");
		var currentcs = $(this).parents(".tache_hd").find(".cs i").attr("f_id");
		$("#selectYwList option").each(function(){
			$(this).removeAttr("selected");
			if($(this).val()== currentyw){
				$(this).attr("selected","selected");
			}
		})
		$("#selectCsList option").each(function(){
			$(this).removeAttr("selected");
			if($(this).val()== currentcs){
				$(this).attr("selected","selected");
			}
		})
		
		var csit = "<select id='selectCs'>"+$("#selectCsList").html()+"</select>";
		var ywit = "<select id='selectYw'>"+$("#selectYwList").html()+"</select>";
		
		var index = $(this).parents("li").find("span").text()-1;
		var item ='<ul class="ui-form grid-row" style="margin-top:30px;">'
			+'<li class="col-md-5 auto layer_col"><strong class="long">流程名称：</strong>'+ywit+'</li>'//<input class="ipt-text" type="text" name="flow_name" id ="lyflowName" value="" placeholder="">
			+'<li class="col-md-5 auto layer_col"><strong class="long">从属组织：</strong>'+csit+'</li>'//<input class="ipt-text" type="text" name="orgId" id ="lyorgId" value="" placeholder="">
			+'<li class="col-md-5" style="width:90% !important;text-align:center">'
			+'<button  type="button" onclick="addThis('+index+')" class="btn btn-submit btn-radius btn-search"> 确定</button>	'
			+'<button  type="button" onclick="layer.closeAll();" class="btn btn-submit btn-radius btn-search"> 取消</button>'
			+'</li>'
			+'</ul>	'
		layer.open({
			  type: 1,
			  skin: 'layui-layer-rim', //加上边框
			  area: ['500px', '300px'], //宽高
			  content: item
		});
			
	})
	
	//编辑确认
	function addThis(idx){
		var _this = $(".process_list").find("li").eq(idx)
		var ly_id = $("#selectCs option:selected").val();
		var ly_text = $("#selectCs option:selected").text();
		
		var Yw_id = $("#selectYw option:selected").val();
		var Yw_text = $("#selectYw option:selected").text();
		
		var item = '<div class="tache_hd clearfix" >'
				  +'<h5>流程环节1</h5>'
				  +'<div class="btn_lab">'
				  +'	<button class="bianji">'
				  +'		<img src="${ctx}/resources/imgs/erecruitment/u5393.png" alt="">'
				  +'	</button>'
				  +'	<button class="del">'
				  +'		<img src="${ctx}/resources/imgs/erecruitment/u5395.png" alt="">'
				  +'	</button>'
				  +'</div>'
				  +'<div class="tache_content yw">'
				  +'	<h5>业务模块：</h5>'
				  +'	<i f_id="'+Yw_id+'">'+Yw_text+'</i>'
				  +'</div>'
				  +'<div class="tache_content cs">'
				  +'	<h5>从属组织：</h5>'
				  +'	<i f_id="'+ly_id+'">'+ly_text+'</i>'
				  +'</div>'
				  +'<div class="btn_lab ">';
				  
			if(_this.find(".isCheck").val()=="1"){
				item = item + '<button class="">'
						    + '	<input type="checkbox" checked  class="isCheck" value="1"/>'
						    + '</button>';
			}			
			
			item = item +'</div></div>';
			if(_this.find(".isCheck").val()=="1"||idx=='0' ){	  
			item = item +'<div class="tache_hd tache_check ty">'
				  +'<button>+添加流程环节</button>'
				  +'</div>';
			}	
		 $(".process_list").find("li").eq(idx).find(".tache1").html(item);
		 sort()
		 layer.closeAll();
		
	}
	//返回
	function cancal(){
		window.location.href = "${ctx}/manage/flow/flowList";
	}
	
	
	function save(){
		layer.confirm('<center>确定修改此流程？</center></br><center>（流程修改后可能会对于已进行流程造成影响）</center>', {
			  btn: ['确定','取消' ] //可以无限个按钮
			}, function(index, layero){
			  saveOrUpdate();
			}, function(index){
			  layer.close();
			});
	}
	
	
	//更新或插入
	function saveOrUpdate(){
		var obj = {};
		var flow = {};
		var  flowName  = $("#flowName").val();
		var  flowDesc  = $("#flowDesc").val();
		var  orgId  = $("#orgId").val();
		var  flowId = $("#flowId").val();
		flow.flowName = flowName;
		flow.flowDesc = flowDesc;
		flow.orgId = orgId;
		flow.id = flowId;
		if($("#isHeadFlag").is(":checked")){
			flow.headFlag = "Y";
		}else{
			flow.headFlag = "N"
		}
		obj.flow = flow;
		var flowActionList = []
		var flag = true;
		$(".process_list").find("li").each(function(idx){
			if($(this).find(".yw i").attr("f_id")==''|| $(this).find(".yw i").attr("f_id") ==undefined){
				layer.msg("节点信息不能为空!");
				flag = false;
			}
			var faction = {};
			faction.flowItemId = $(this).find(".yw i").attr("f_id");
			faction.orgId = $(this).find(".cs i").attr("f_id");
			faction.processingRole = $(this).find(".cs i").attr("f_id");
			faction.isCheck = $(this).find(".isCheck").val();
			faction.step = idx+1;
			flowActionList.push(faction);
		})	
		
		obj.flowActionList = flowActionList;
		if(flag){
		 	  $.ajax({
			  	  url: "${ctx}/manage/flow/saveOrUpdateFlow",
			  	  type: 'POST',
			  	  data :JSON.stringify(obj),
			  	  contentType:"application/json",
			  	  success: function(result) {
					if (result.code=="1") {
						layer.alert("保存成功", {icon: 1},function(){
							window.location.href="${ctx}/manage/flow/flowList";
						});
					}else{
						layer.alert(result.data, {icon: 1});
					}
				  }
			  })   
		}
	}
	
	//是否总公司按钮
	$("#isHeadFlag").click(function(){
		if($(this).is(':checked')){
			$("#orgId").attr("disabled","disabled");
		}else{
			$("#orgId").removeAttr("disabled");
		}
	})
</script>
</body>
</html>
