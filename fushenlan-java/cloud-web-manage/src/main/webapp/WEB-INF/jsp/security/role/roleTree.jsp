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
 	<link rel="stylesheet" href="${ctx}/resources/libs/ztree/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="${ctx}/resources/libs/ztree/jquery.ztree.core.js"></script>
	<script type="text/javascript" src="${ctx}/resources/libs/ztree/jquery.ztree.excheck.js"></script>
	<script type="text/javascript" src="${ctx}/resources/libs/ztree/jquery.ztree.exedit.js"></script>
	<script type="text/javascript" src="${ctx}/resources/libs/layer/layer.js"></script>
	<form action="${ctx}/manage/role/saveOrUpdate" id="roleForm" name="roleForm" method="post">
	<%-- <shiro:hasPermission name="contract:view"> 
		<a onclick="getCheckbox()" >权限控制按钮</a>
	</shiro:hasPermission> --%>
	<div style="width: 20%;float: left;" class="filter-box"><ul id="treeDemo" class="ztree"></ul></div>
	<div style="width: 80%;float: left;" id="roleDiv">
	<div class="filter-box">
			<input id="id" name="id" value="" type="hidden">
			<input id="parentId" name="parentId" value="" type="hidden">
			<ul class="ui-form grid-row">
				<li class="col-md-4" style="margin-left:10%;"><strong>角色名称:</strong><input class="ipt-text" id="roleName" name="roleName" value="" style="width:600px;" type="text" placeholder=""></li>
				<li class="col-md-4" style="margin-left:10%;"><strong>上级:</strong><input class="ipt-text" id="parentIdName" name="parentIdName" value="" style="width:600px;" type="text" placeholder="" readonly="readonly"></li>
			</ul>
			<!-- <ul class="ui-form grid-row">
				<li class="col-md-3" style="margin-left:150px;"><strong>创建者名称:</strong><input class="ipt-text" style="width:600px;" type="text" placeholder=""></li>
				<li class="col-md-3" style="margin-left:150px;"><strong>修改者名称:</strong><input class="ipt-text" style="width:600px;" type="text" placeholder=""></li>
			</ul> -->
			<div style="width:100%;text-align:center;">
			<ul class="ui-form grid-row" style="display:inline-block; ">
				<li class="col-md-3 col-btn" style="margin-right:75px;">
				<button type="button" class="btn btn-submit btn-radius" onclick='dylayer()'>添加资源</button>
				</li> 
				<li class="col-md-3 col-btn" style="margin-left:75px;">
				<button type="button" class="btn btn-success btn-radius" onclick="save('1')">确定</button>
			    </li>
		    </ul>
		    </div>
		</div>
	<table class="table table-agents">
			<thead>
				<tr>
					<th>资源路径</th>
					<th>菜单路径</th>
					<th>资源类型</th>
					<!-- <th>上级</th> -->
				</tr>
			</thead>
			<tbody id="resourceTbody">
				<!-- <tr>
					<td>上海分公司</td>
					<td>甄选面试</td>
					<td>个人背景调查</td>
					<td>评估个人、家庭条件</td>
				</tr> -->
			</tbody>
		</table>
		
	</div>
	<!-- <a onclick="dylayer()">调用layer</a> -->
	</form>
<script type="text/javascript">
    var roleNameEQ = "";
    var parentsId;
	//调用layer弹窗
	function dylayer(){
		var roleId = $("#id").val();
		var parentId = $("#parentId").val();
		var pId;
		if(null != parentsId){
			pId = parentsId;
		}else{
			pId = parentId;
		}
		var roleName = $("#roleName").val();
		roleNameEQ = roleName;
		if(parentId.length == 0){
			layer.msg("请选择子节点");
		}else if(roleId.length == 0){
			save('2');
			roleId = $("#id").val();
			
			layer.open({
				  type: 2,
				  title: '资源',
				  shadeClose: true,
				  shade: 0.8,
				  area: ['380px', '500px'],
				  content: '${ctx}/manage/role/resourceLayer?roleId='+roleId+"&parentId="+pId //iframe的url
			}); 
		}else{
			layer.open({
				  type: 2,
				  title: '资源',
				  shadeClose: true,
				  shade: 0.8,
				  area: ['380px', '500px'],
				  content: '${ctx}/manage/role/resourceLayer?roleId='+roleId+"&parentId="+pId //iframe的url
			}); 
		}
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
			showRenameBtn: false,
			showAddBtn: showAddBtn
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
			onClick: zTreeOnClick,
			onRename: onRename
		},
		check: {
			enable: false,
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
		var bool = false;
		var bool = false;
		if(confirm("确认删除 节点 -- " + treeNode.name + " 吗？")){
			$.ajax({  
				async: false,
		        type: 'POST',  
		        dataType : "json",  
		        data : {id:id,dele:"0"},
		        url: "${ctx}/manage/role/saveOrUpdate?type=1",//请求的action路径  
		        beforeSend: function () {  //加载层loading
					layer.load(1, {
						  shade: [0.5,'#fff'] //0.1透明度的白色背景
						});
	            },
		        success:function(data){ //请求成功后处理函数。
		        	    if(data.code=="1"){
		        	    	bool = true;
		        			layer.msg("删除成功");
		        			$('#id').val("");
		        			$('#parentId').val("");
		        			$('#roleName').val("");
		        			$('#parentIdName').val("");
		        			$("#resourceTbody").html("");
		        	    }else{
		        	    	bool = false;
		        	    	layer.msg("删除失败");
		        	    }
		        	    var index =  layer.load();
						layer.close(index);
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
	var eventEdit;
	function zTreeOnClick(event, treeId, treeNode){
		roleNameEQ = treeNode.name;
		eventEdit = event;
		treeIdEdit = treeId;
		treeNodeEdit = treeNode;
		var parentNode = treeNode.getParentNode();
		var html = "";
		$('#id').val(treeNode.id);
		if(null != parentNode){
			$('#parentIdName').val(parentNode.name);
			parentsId = parentNode.cId;
		}
		if(treeNode.id !=null ){
			$.ajax({
				async: false,
				type : "post",
				data : {id:treeNode.id},
				url : "${ctx}/manage/role/ajaxSele",
				success : function(data) {
					if (data.length!=0) {
						$('#id').val(data[0].id);
						$('#parentId').val(data[0].parentId);
						$('#roleName').val(data[0].roleName);
						if(null != parentNode){
							$('#parentIdName').val(parentNode.name);
						}
						$.each(data[0].resource,function(index,resource){
							html+='<tr><td>'+resource.resourceName+'</td>';
							if(null == resource.url){
								html+='<td></td>';
							}else{
								html+='<td>'+resource.url+'</td>';
							}
							if(resource.resourceType=='M'){
								html+='<td>菜单</td>';
							}else{
								html+='<td>按钮</td>';
							}
							html +='</tr>';
							/* html+='<td>'+parentNode.name+'</td>'; */
						});
						$("#resourceTbody").html(html);
					}
				} 
			});
		}else{
			$('#id').val(treeNode.id)
			$('#parentId').val(treeNode.pId);
			$('#roleName').val(treeNode.name);
		}
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
		return !treeNode.isLastNode;
	}
	function showAddBtn(treeId, treeNode) {
		if(treeNode.level == 1){
			return false;
		}
		return true;
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
		if(treeNode.level != 1){
			var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
				+ "' title='add node' onfocus='this.blur();'></span>";
			sObj.after(addStr);
		}
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
		$.post("${ctx}/manage/role/find/list",function(result){
			$.each(result,function(index,role){
				var zNode = {id:role.id,pId:role.parentId,name:role.roleName,cId:role.parentId, open:false};
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
	function save(mark){
		var type = 0;
		var roleName = $("#roleName").val();
		if(roleName == roleNameEQ){
			type = 1;
		}
		$.ajax({
			async: false,
			type : "post",
			data : $("#roleForm").serialize(),
			url : "${ctx}/manage/role/saveOrUpdate?type="+type,
			beforeSend: function () {  //加载层loading
				layer.load(1, {
					shade: [0.5,'#fff'] //0.1透明度的白色背景
				});
	        },
			success : function(data) {
				if (data.code=='1') {
					if(mark == '2'){
						 treeNodeEdit.id = data.data;
						 $("#id").val(data.data);
						 treeNodeEdit.name= $("#roleName").val();
						 var treeObj = $.fn.zTree.getZTreeObj(treeIdEdit); 
						 treeObj.updateNode(treeNodeEdit);
						 var index =  layer.load();
						 layer.close(index);
					}else{
						layer.alert("保存成功", {icon: 1}, function(){
							 treeNodeEdit.id = data.data;
							 if(null == $("#id").val() || '' == $("#id").val()){
							 	$("#id").val(data.data);
							 }
							 treeNodeEdit.name= $("#roleName").val();
							 var treeObj = $.fn.zTree.getZTreeObj(treeIdEdit); 
							 treeObj.updateNode(treeNodeEdit);
							 var index =  layer.load();
							 layer.close(index);
							 index = layer.alert();
							 layer.close(index);
						});
					}
				}else {
					layer.msg(data.msg);
					var index =  layer.load();
					layer.close(index);
				}
			}
		});
	}
</script>
</body>
</html>