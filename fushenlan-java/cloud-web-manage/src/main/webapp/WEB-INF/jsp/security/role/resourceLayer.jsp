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
<style>
.col-md-4 strong{
	width:200px;
}

</style>
<script type="text/javascript" src="${ctx}/resources/js/common/jquery-3.2.1.min.js" ></script>
 	<link rel="stylesheet" href="${ctx}/resources/libs/ztree/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="${ctx}/resources/libs/ztree/jquery.ztree.core.js"></script>
	<script type="text/javascript" src="${ctx}/resources/libs/ztree/jquery.ztree.excheck.js"></script>
	<script type="text/javascript" src="${ctx}/resources/libs/ztree/jquery.ztree.exedit.js"></script>
	<script type="text/javascript" src="${ctx}/resources/libs/layer/layer.js"></script>
	<div><ul id="treeDemo" class="ztree">
	</ul></div>
	<div>
		<ul class="ui-form grid-row">
			<li class="col-md-3 col-btn">
				<button type="button" class="btn btn-submit btn-radius btn-search" onclick="back()">返回</button>
				<button type="button" class="btn btn-submit btn-radius btn-search" onclick="saveRR()">确定</button>
			</li>
		</ul>
	</div>
<script type="text/javascript">
	
	//加载使用ztree
	var setting = {
		view: {
			addHoverDom: false,
			removeHoverDom: removeHoverDom,
			selectedMulti: false
		},
		edit: {
			enable: true,
			editNameSelectAll: true,
			showRemoveBtn: false,
			showRenameBtn: false
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
			/* beforeClick: beforeClick, */
			onRemove: onRemove,
			/* onClick: zTreeOnClick, */
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
	
	var zNodes =[];
	var log, className = "dark";
	function beforeDrag(treeId, treeNodes) {
		return false;
	}
	function beforeEditName(treeId, treeNode) {
		className = (className === "dark" ? "":"dark");
		showLog("[ "+getTime()+" beforeEditName ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name);
		var zTree = $.fn.zTree.getZTreeObj(treeId);
		zTree.selectNode(treeNode);
		setTimeout(function() {
			if (confirm("进入节点 -- " + treeNode.name + " 的编辑状态吗？")) {
				setTimeout(function() {
					zTree.editName(treeNode);
				}, 0);
			}
		}, 0);
		return false;
	}
	function beforeRemove(treeId, treeNode) {
		className = (className === "dark" ? "":"dark");
		showLog("[ "+getTime()+" beforeRemove ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name);
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		zTree.selectNode(treeNode);
		var id = treeNode.id;
		var bool = true;
		if(confirm("确认删除 节点 -- " + treeNode.name + " 吗？")){
		$.ajax({  
	        async : false,  
	        cache : false,  
	        type: 'POST',  
	        dataType : "json",  
	        data : {id:id},
	        url: "${ctx}/resource/dele",//请求的action路径  
	        error: function () {//请求失败处理函数  
	        	bool = true;
	        },  
	        success:function(data){ //请求成功后处理函数。
	        	    if(data.code=="success"){
	        	    	bool = true;
	        	    }
	        	}
	    	}); 
		}else{
			bool = false;
		}
		return bool;
		
	}
	function onRemove(e, treeId, treeNode) {
		showLog("[ "+getTime()+" onRemove ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name);
	}
	var treeNodeEdit ;
	var treeIdEdit;
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
		return !treeNode.isLastNode;
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
		var sObj = $("#" + treeNode.tId + "_span");
		if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
		var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
			+ "' title='add node' onfocus='this.blur();'></span>";
		sObj.after(addStr);
		var btn = $("#addBtn_"+treeNode.tId);
		if (btn) btn.bind("click", function(){
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			zTree.addNodes(treeNode, {id:null, pId:treeNode.id, name:"新增节点"});
			return false;
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
		var array = {};
		<c:forEach items="${list}" var="rr">
			array["${rr.resourceId}"] = "";
		</c:forEach>
		var parentId = "${parentId}";
		$.post("${ctx}/manage/resource/find/list?roleParentId="+parentId,function(result){
			$.each(result,function(index,resource){
				var zNode ={};
				if(array[resource.id] == ""){
					zNode= {id:resource.id,pId:resource.parentId,name:resource.resourceName,seq:resource.seq, open:false, checked : true};
				}else{
					zNode= {id:resource.id,pId:resource.parentId,name:resource.resourceName,seq:resource.seq, open:false};
				}
				zNodes.push(zNode);
			});
			var zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, zNodes);
			/* var nodes = zTreeObj.getCheckedNodes();
			console.log(nodes);
			for (var i = 0, l = nodes.length; i < l; i++) {
				zTreeObj.checkNode(nodes[i], true, true);
			}  */
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
	function unique(arr) {
		var result = [], isRepeated;
		result = zNodes;
		for (var i = 0, len = arr.length; i < len; i++) {
			isRepeated = false;
			for (var j = 0, len = result.length; j < len; j++) {
				if (arr[i] == result[j]) {   
					isRepeated = true;
					break;
				}
			}
			if (!isRepeated) {
				result.push(arr[i]);
			}
		}
		return result;
	}
	var nodess;
	function saveRR(){
		var roleId = "${roleId}";
		var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
		var nodes = treeObj.getCheckedNodes(true);
		nodess = nodes;
		var resourceIds ="";
		$(nodes).each(function(index,treeNode){
				resourceIds+=treeNode.id+",";
		});
		resourceIds = resourceIds.substring(0, resourceIds.length-1);
		$.ajax({
			type : "post",
			data : {roleId:roleId,resourceIds:resourceIds},
			url : "${ctx}/manage/roleResource/save",
			beforeSend: function () {  //加载层loading
				layer.load(1, {
					  shade: [0.5,'#fff'] //0.1透明度的白色背景
					});
            },  
			success : function(data) {
				if (data.code=='1') {
					var event, treeId, treeNode;
					event = parent.eventEdit;
					treeId = parent.treeIdEdit;
					treeNode = parent.treeNodeEdit;
					parent.zTreeOnClick(event,treeId,treeNode);
					var index=parent.layer.getFrameIndex(window.name);
					parent.layer.close(index);
				}else {
					var index=layer.load();
					layer.close(index);
					layer.msg("保存失败")
				}
			}
		});
	}
	function back(){
		var index=parent.layer.getFrameIndex(window.name);
		parent.layer.close(index);
	}
</script>
</body>
</html>