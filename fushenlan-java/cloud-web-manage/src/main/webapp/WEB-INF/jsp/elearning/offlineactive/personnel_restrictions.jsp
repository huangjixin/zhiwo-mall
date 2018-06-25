<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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

</head>
<body>
<script src="${ctx}/resources/js/common/jquery-3.2.1.min.js"></script> 
<link rel="stylesheet" href="${ctx}/resources/css/reset.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/style.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/font/iconfont.css">
<link rel="stylesheet" href="${ctx}/resources/libs/ztree/zTreeStyle.css" type="text/css">

<script type="text/javascript" src="${ctx}/resources/libs/ztree/jquery.ztree.core.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/ztree/jquery.ztree.excheck.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/ztree/jquery.ztree.exedit.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/layer/layer.js"></script>	

<style>

.table1{
   width: 100%;
   background-color: #fff;
   border-radius: 5px;
}
</style>
		
		<div class="form-detail">
		
		<div class="title"><strong>人员限制</strong></div>
			<div class="nav-tabs">
				<strong onclick="cOrg()" class="active" >组织架构</strong>
				<strong onclick="cPerson()" >指定人群</strong>
				<!-- <strong >上传名单</strong> -->
			</div>
			
			<!-- 组织架构 -->
			<div class="tab-pane active clearfix"  name="organize">
			    <div style="display:none" id="huancun">
			         <table class="table1">
				     <thead>
				       <tr border="0";>
				         <th></th> 
				         <th><h5>名称</h5></th>
				         <th><h5>职位</h5></th>
				         <th><h5>手机号</h5></th>
				         <th><h5>邮箱</h5></th>
					     <th><h5>账户类型</h5></th>
				       </tr>
			         </thead>
			         <tbody class="tdyOther" id="otherTd">
			         </tbody> 
				</table>
				</div>
				
				<div class="organize_left col-md-6 clearfix">
					<ul id="treeDemo" class="ztree"></ul>
				</div>
				<div class="organize_right col-md-6 clearfix">
				
				<table class="table1">
				     <thead>
				       <tr border="0";>
				         <th></th> 
				         <th><h5>名称</h5></th>
				         <th><h5>职位</h5></th>
				         <th><h5>手机号</h5></th>
				         <th><h5>邮箱</h5></th>
					     <th><h5>账户类型</h5></th>
				       </tr>
			         </thead>
			         <tbody class="tdy" id="tb">
			         </tbody> 
				</table>
				</div> 
			</div>
			<!-- 指定人群 -->
			<div class="tab-pane clearfix"  name="person">
				<div class="title"><strong>查询条件</strong></div>	
				<ul class="edit clearfix online_form">
					<li class="col-md-3"><strong>人员姓名：</strong>
						<input class="ipt-text proTime" id="personName" type="text" placeholder="人员姓名">
					</li>
					<li class="col-md-3"><strong>手机号码：</strong>
						<input class="ipt-text proTime" id="phone" type="text" placeholder="手机号码">
					</li>
					
					<li class="col-md-4" style="text-align: right">
						<button type="button" onclick="findTr()" class="btn btn-submit btn-radius btn-search"><i class="icon-search"></i> 查询</button>
					</li>
				</ul>
				  <div class="organize_left col-md-6 clearfix">
				   <table class="table1">
				    <thead>
				       <tr border="0";> 
				         <th></th>
				         <th><h5>名称</h5></th>
				         <th><h5>职位</h5></th>
				         <th><h5>手机号</h5></th>
				         <th><h5>邮箱</h5></th>
					     <th><h5>账户类型</h5></th>
					     
				       </tr>
			         </thead>
			         <tbody class="tdyOther" id="ter">
			        <c:forEach  varStatus="idx" var="account" items="${accountList}">
				         <tr>
				            <td>
							   <input <c:if test="${fn:contains(personIds,account.id)}">checked="checked"</c:if> type="checkbox" class="ace" value="${account.id}">
					        </td>
					        <td>${account.accountName }</td>
					        <td>${account.postType }</td>
					        <td>${account.mobile }</td>
					        <td>${account.email }</td>
					        <td>${account.accountType }</td>
					        <input type="hidden" value="2"/>
				         </tr>
				     </c:forEach> 
			         </tbody>
				</table>
				  </div>
			</div>
			<!-- 上传名单 -->
			<div class="tab-pane clearfix">
			
			</div>
			<div class="ui-button">
				<button type="button" onclick="sure()" class="btn btn-submit">确定</button>
				<button type="button" onclick="cancel()" class="btn btn-default">取消</button>
			</div>
			
		</div>


<script src="${ctx}/resources/js/common.js"></script> 
<script type="text/javascript"	src="${ctx}/resources/libs/layer/layer.js"></script>
<script type="text/javascript">
	
	
	
    function cancel(){
    	var index=parent.layer.getFrameIndex(window.name);
		parent.layer.close(index);
    }
	
	
	function sure(){
		var dh= 0;
		var tt ="";
		$("#tb :input[type='checkbox']").each(function(i){
			if(this.checked==true){
				tt +=$(this).val()+",";
			}
		});
		if(tt!=""){
			var ss = $(".tdy").children().first().attr("name");
			$("#otherTd").children('tr[name='+ss+']').remove();
		}
		parent.$("#xianzhi").children('div').remove();
		
		
		
		var mIds ="";
		$("#tb :input[type='checkbox']").each(function(i){
			if(this.checked==true){
			var authorityType = $(this).parent().parent().children().last().val();
			var associateId = this.value;
			mIds+=this.value+",";
			parent.addPerson(authorityType,associateId,dh);
			dh++;
			var index=parent.layer.getFrameIndex(window.name);
			parent.layer.close(index);
			}
		});
		
		$("#otherTd :input[type='checkbox']").each(function(i){
			var authorityType = $(this).parent().parent().children().last().val();
			var associateId = this.value;
			mIds+=this.value+",";
			parent.addPerson(authorityType,associateId,dh);
			dh++;
			var index=parent.layer.getFrameIndex(window.name);
			parent.layer.close(index);
		});
		$("#ter :input[type='checkbox']").each(function(i){
			if(this.checked==true){
				var authorityType = $(this).parent().parent().children().last().val();
				var associateId = this.value;
				if(!mIds.includes(associateId)){
					parent.addPerson(authorityType,associateId,dh);
					dh++;
					var index=parent.layer.getFrameIndex(window.name);
					parent.layer.close(index);	
				}
			}
		});
	}
	
    function addPersonOther(){
    	$("#xianzhi").children('div').remove();
    }
	
	
	 function findTr(){
		 var accountName = $("#personName").val();
		 var mobile = $("#phone").val();
		 $.ajax({
			  async : false,  
		      cache : false,  
		      type: 'POST',
	    	  url: "${ctx }/manage/elearning/choosePersonOther",
	    	  data: {"accountName" : accountName,"mobile":mobile},
	    	  dataType: "json",
	    	  success: function(data){
	    		  $("#ter").children().remove();
	    		  if(data.data.length==0){
	    			  layer.msg("没有数据");
	    		  }else{
	    			  $.each(data.data,function(i,item){
	    			  $("#ter").append('<tr><td><input type="checkbox" name="id" value='+item.id+'></td><td>'+item.accountName+'</td><td>'+item.postType+'</td><td>'+item.mobile+'</td><td>'+item.email+'</td><td>'+item.accountType+'</td><input type="hidden" value="2"/></tr>');
	    		    })
	    		  }
	    	   }
	    	}) 
	 }
	
     function cPerson(){
    	 $("div[name='organize']").removeClass("active");
    	 $("div[name='person']").addClass("active");
     }
     
     function cOrg(){
    	 $("div[name='organize']").addClass("active");
    	 $("div[name='person']").removeClass("active")
     }
    var parentCode;
	
	//加载使用ztree
	var setting = {
		view: {
			addHoverDom: addHoverDom,
			removeHoverDom: removeHoverDom,
			selectedMulti: false
		},
		edit: {
			enable: true,
			editNameSelectAll: true,
			showRemoveBtn: showRemoveBtn,
			showRenameBtn: showRenameBtn
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		callback: {
			beforeDrag: beforeDrag,
			beforeEditName: beforeEditName,
			beforeRemove: beforeRemove,
			beforeRename: beforeRename,
			onRemove: onRemove,
			onClick: zTreeOnClick,
			onRename: onRename
		}/* ,
		check: {
			enable: true,
			autoCheckTrigger: true,
			chkStyle: "checkbox",
			chkboxType: { "Y": "ps", "N": "s" },
			nocheckInherit: true,
			chkDisabledInherit: true
		} */
		
	};
	
	var zNodes =[];
	var log, className = "dark";
	function beforeDrag(treeId, treeNodes) {
		return false;
	}
	function beforeEditName(treeId, treeNode) {
		//编辑界面
		/* className = (className === "dark" ? "":"dark");
		showLog("[ "+getTime()+" beforeEditName ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name);
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		zTree.selectNode(treeNode);
		setTimeout(function() {
			if (confirm("进入节点 -- " + treeNode.name + " 的编辑状态吗？")) {
				setTimeout(function() {
					zTree.editName(treeNode);
				}, 0);
			}
		}, 0);
		return false; */
		var organizationId=treeNode.id;
		window.location.href="${ctx}/manage/getOrganizationById?organizationId="+organizationId;
	}
	function beforeRemove(treeId, treeNode) {
		//调用删除********************
		/* className = (className === "dark" ? "":"dark");
		showLog("[ "+getTime()+" beforeRemove ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name);
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		zTree.selectNode(treeNode); */
		var result=confirm("确认删除 节点 -- " + treeNode.name + " 吗？");
		if(result){
			$.ajax({
				  async : false,  
			      cache : false,  
			      type: 'POST',  
		    	  url: "${ctx }/manage/deleteOrganization",
		    	  data: {"id" : treeNode.id},
		    	  dataType: "json",
		    	  success: function(result){
		    		  //layer.alert(date.msg, {icon: 1});
		    		  if(result.data=="1"){
		    			  window.location.href="${ctx}/manage/organizationJsp"; 
		    		  }
		    	  },
		    	  
		    	})
		    	return false;
		}
		else{
			return false;
		}
		//return result;
		
	}
	function onRemove(e, treeId, treeNode) {
		showLog("[ "+getTime()+" onRemove ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name);
	}
	function zTreeOnClick(event, treeId, treeNode){
		var sh = $(".tdy").children().first().attr("name");
		var organizationId=treeNode.id;
		var pIds = '${personIds}';
		var ids ="";
		$("#otherTd :input[type='checkbox']").each(function(i){
			 ids+=$(this).val()+",";
		});
		ids+=pIds;
		var tt ="";
		$("#tb :input[type='checkbox']").each(function(i){
			if(this.checked==true){
				tt +=$(this).val()+",";
			}
		});
	 	 var ss = $(".tdy").children().first().attr("name");
		 $("#otherTd").children('tr[name='+ss+']').remove();
		
		$(".tdy :input[type='checkbox']").each(function(i){      //数据加到隐藏的table里
			if(this.checked==true){
				var html = $(this).parent().parent().html();
				var chtml='<tr name='+sh+'>'+html+'</tr>';
				$("#otherTd").append(chtml);
			}
		});
		parentCode=treeNode;
		 $.ajax({
			  async : false,  
		      cache : false,  
		      type: 'POST',
	    	  url: "${ctx }/manage/elearning/getOrganizationListByParentId",
	    	  data: {"id" : organizationId},
	    	  dataType: "json",
	    	  success: function(data){
	    		  $("#tb").children().remove();
	    		  if(data.data.length==0){
	    			  layer.msg("当前无子节");
	    		  }else{
	    		  $.each(data.data,function(i,item){
	    			  if(ids.includes(item.id)){
	    				  $("#tb").append('<tr name='+item.companyId+'><td><input type="checkbox" name="id" checked="checked" value='+item.id+'></td><td>'+item.accountName+'</td><td>'+item.postType+'</td><td>'+item.mobile+'</td><td>'+item.email+'</td><td>'+item.accountType+'</td><input type="hidden" value="1"/></tr>');
	    			  }else{
	    				  $("#tb").append('<tr name='+item.companyId+'><td><input type="checkbox" name="id" value='+item.id+'></td><td>'+item.accountName+'</td><td>'+item.postType+'</td><td>'+item.mobile+'</td><td>'+item.email+'</td><td>'+item.accountType+'</td><input type="hidden" value="1"/></tr>');
	    			  }
	    			  
	    		  })
	    		  }
	    	  }
	    	}) 
	}
	function beforeRename(treeId, treeNode, newName, isCancel) {
		className = (className === "dark" ? "":"dark");
		showLog((isCancel ? "<span style='color:red'>":"") + "[ "+getTime()+" beforeRename ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name + (isCancel ? "</span>":""));
		if (newName.length == 0) {
			setTimeout(function() {
				var zTree = $.fn.zTree.getZTreeObj("treeDemo");
				zTree.cancelEditName();
				alert("节点名称不能为空.");
			}, 0);
			return false;
		}
		return true;
	}
	function onRename(e, treeId, treeNode, isCancel) {
		showLog((isCancel ? "<span style='color:red'>":"") + "[ "+getTime()+" onRename ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name + (isCancel ? "</span>":""));
	}
	function showRemoveBtn(treeId, treeNode) {
		return false;
	}
	function showRenameBtn(treeId, treeNode) {
		return false;
	}
	function showLog(str) {
		if (!log) log = $("#log");
		log.append("<li class='"+className+"'>"+str+"</li>");
		if(log.children("li").length > 8) {
			log.get(0).removeChild(log.children("li")[0]);
		}
	}
	function getTime() {
		var now= new Date(),
		h=now.getHours(),
		m=now.getMinutes(),
		s=now.getSeconds(),
		ms=now.getMilliseconds();
		return (h+":"+m+":"+s+ " " +ms);
	}
	
	var newCount = 1;
	function addHoverDom(treeId, treeNode) {
		//新增界面
		var sObj = $("#" + treeNode.tId + "_span");
		if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
		var btn = $("#addBtn_"+treeNode.tId);
		if (btn) btn.bind("click", function(){
			var organizationId = treeNode.layer;
			var layer = treeNode.id;
			var rootId = treeNode.rootId;
			window.location.href="${ctx}/manage/getOrganizationParent?id="+organizationId+"&layer="+layer+"&rootId="+rootId;
		}); 
	};
	function removeHoverDom(treeId, treeNode) {
		$("#addBtn_"+treeNode.tId).unbind().remove();
	};
	function selectAll() {
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		zTree.setting.edit.editNameSelectAll =  $("#selectAll").attr("checked");
	}
	
	$(document).ready(function(){
		$.post("${ctx}/manage/elearning/getAllOrganizationList",function(result){
			$.each(result,function(index,organization){
				var zNode = {id:organization.id,pId:organization.parentId,name:organization.code,layer:organization.layer,rootId:organization.rootId, open:false};
				zNodes.push(zNode);
			});
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			parentCode = zNodes[0];
			$("#selectAll").bind("click", selectAll);
		}); 
	});
	//获取树checkbox节点
	function getCheckbox(){
		var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
		var nodes = treeObj.getCheckedNodes(true);
		$.each(nodes,function(i,treeNode){
			alert(treeNode.name);
		})
	}
	
	
	
	
	function deleteOranization(id,code){
		var result=confirm("确认删除 节点 -- " + code + " 吗？");
		if(result){
			$.ajax({
				  async : false,  
			      cache : false,  
			      type: 'POST',  
		    	  url: "${ctx }/manage/deleteOrganization",
		    	  data: {"id" :id},
		    	  dataType: "json",
		    	  success: function(result){
		    		  alert(result.msg);
		    		  //layer.alert(date.msg, {icon: 1});
		    		  if(result.data=="1"){
		    			  parent.location.href="${ctx}/manage/organizationJsp"; 
		    		  }
		    	  },
		    	  
		    	})
		}
	}
	
	
	function updateOranization(id){
		$.ajax({
			  async : false,  
		      cache : false,  
		      type: 'POST',
	    	  url: "${ctx }/manage/getOrganizationById",
	    	  data: {"organizationId" : id},
	    	  dataType: "json",
	    	  success: function(result){
	    		  $("#organizationId").val(result.data.id);
	    		  $("#code").val(result.data.code);
	    		  $("#cnName").val(result.data.cnName);
	    		  $("#enName").val(result.data.enName);
	    		  $("#companyId").val(result.data.companyId);
	    		  $("#address").val(result.data.address);
	    		  $("#type").val(result.data.type);
	    		  $("#enabled").val(result.data.enabled);
	    		  $("#postCode").val(result.data.postCode);
	    		  $("#phoneNumber").val(result.data.phoneNumber);
	    		  $("#faxNumber").val(result.data.faxNumber);
	    		  $("#description").val(result.data.description);
	    	  }
	    	})
		$('.mask').show();
	}
	
	
	
  </script>
</body>
</html>
