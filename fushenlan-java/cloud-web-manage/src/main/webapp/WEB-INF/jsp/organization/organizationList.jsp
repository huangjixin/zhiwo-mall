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
 	<link rel="stylesheet" href="${ctx}/resources/libs/ztree/zTreeStyle.css" type="text/css">
 	<script type="text/javascript" src="${ctx}/resources/libs/ztree/jquery.ztree.core.js"></script>
	<script type="text/javascript" src="${ctx}/resources/libs/ztree/jquery.ztree.excheck.js"></script>
	<script type="text/javascript" src="${ctx}/resources/libs/ztree/jquery.ztree.exedit.js"></script>
	<script type="text/javascript" src="${ctx}/resources/libs/layer/layer.js"></script>
	
	<shiro:hasPermission name="contract:view">  
		<a onclick="getCheckbox()" >权限控制按钮</a>
	</shiro:hasPermission>
	<div style="width: 30%;float: left;"><ul id="treeDemo" class="ztree"></ul></div>
	<div style="width: 70%;float: left;" id="resouceDiv">
	</div>
	<!-- <a onclick="dylayer()">调用layer</a> -->
<script type="text/javascript">
	//调用layer弹窗
	function dylayer(){
		layer.open({
			  type: 2,
			  title: 'layer mobile页',
			  shadeClose: true,
			  shade: 0.8,
			  area: ['380px', '500px'],
			  content: '${ctx}/layerdemo' //iframe的url
		}); 
	}
	
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
		},
		check: {
			enable: true,
			autoCheckTrigger: true,
			chkStyle: "checkbox",
			chkboxType: { "Y": "ps", "N": "s" },
			nocheckInherit: true,
			chkDisabledInherit: true
		}
		
	};
	
	var zNodes =[
		/* { id:1, pId:0, name:"父节点 1", open:true},
		{ id:11, pId:1, name:"叶子节点 1-1", open:true},
		{ id:1111, pId:11, name:"叶子节点 1-1"},
		{ id:12, pId:1, name:"叶子节点 1-2"},
		{ id:13, pId:1, name:"叶子节点 1-3"},
		{ id:2, pId:0, name:"父节点 2", open:true},
		{ id:21, pId:2, name:"叶子节点 2-1"},
		{ id:22, pId:2, name:"叶子节点 2-2"},
		{ id:23, pId:2, name:"叶子节点 2-3"},
		{ id:3, pId:0, name:"父节点 3", open:true},
		{ id:31, pId:3, name:"叶子节点 3-1"},
		{ id:32, pId:3, name:"叶子节点 3-2"},
		{ id:33, pId:3, name:"叶子节点 3-3"} */
	];
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
		    		  alert(result.msg);
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
		var organizationId=treeNode.id;
		/* $.ajax({
	    	  url: "${ctx }/getOrganizationById",
	    	  data: {"organizationId" : organizationId},
	    	  dataType: "json",
	    	  success: function(date){
	    		  alert(date.id);
	    	  }
	    	}) */
	    	//window.location.href="${ctx}/getOrganizationById?id="+organizationId;
		/* html+="<table><tr><td>"+treeNode.tId+"</td><td>"+treeNode.name+"</td></tr></table>";
		$("#resouceDiv").html(html); */
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
		return true;
	}
	function showRenameBtn(treeId, treeNode) {
		return treeNode;
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
		var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
			+ "' title='add node' onfocus='this.blur();'></span>";
		sObj.after(addStr);
		var btn = $("#addBtn_"+treeNode.tId);
		if (btn) btn.bind("click", function(){
			var organizationId = treeNode.layer;
			var layer = treeNode.id;
			var rootId = treeNode.rootId;
			window.location.href="${ctx}/manage/getOrganizationParent?id="+organizationId+"&layer="+layer+"&rootId="+rootId;
			/* var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			zTree.addNodes(treeNode, {id:(100 + newCount), pId:treeNode.id, name:"new node" + (newCount++)});
			return false; */
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
		$.post("${ctx}/manage/getOrganizationListByParentId",function(result){
			$.each(result,function(index,organization){
				var zNode = {id:organization.id,pId:organization.parentId,name:organization.code,layer:organization.layer,rootId:organization.rootId, open:false};
				zNodes.push(zNode);
			});
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
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
	
</script>
</body>
</html>