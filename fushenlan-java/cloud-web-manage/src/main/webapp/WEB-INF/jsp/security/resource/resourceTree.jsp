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
.radioDiv{
	line-height: 38px;	
}

</style>
 	<link rel="stylesheet" href="${ctx}/resources/libs/ztree/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="${ctx}/resources/libs/ztree/jquery.ztree.core.js"></script>
	<script type="text/javascript" src="${ctx}/resources/libs/ztree/jquery.ztree.excheck.js"></script>
	<script type="text/javascript" src="${ctx}/resources/libs/ztree/jquery.ztree.exedit.js"></script>
	<script type="text/javascript" src="${ctx}/resources/libs/layer/layer.js"></script>
	<div style="width: 20%;float: left;" class="filter-box">
		<ul id="treeDemo" class="ztree"></ul>
		<ul class="ztree">
		   <li><button onclick="addParent('${sessionScope.resourcePID}')">添加父节点</button></li>
		</ul>
	</div>
	<div style="width: 80%;float: left;" id="resouceDiv">
	<div class="filter-box">
	<form action="${ctx}/resource/update" id="resourceForm" name="resourceForm" method="post">
		<input id="id" name="id" value="" type="hidden" />
		<input id="parentId" name="parentId" value="" type="hidden" />
		<input id="layer" name="layer" value="" type="hidden" />
		<ul class="ui-form grid-row">
				<li class="col-md-4" style="margin-left:100px;"><strong>资源路径:</strong><input class="ipt-text" id="resourceName" name="resourceName" value="" style="width:600px;" type="text"></li>
				<li class="col-md-4" style="margin-left:150px;">
					<strong>资源类型:</strong>
					<div class="radioDiv" style="margin-left:10px;width:100px;display:inline-block;">
					<input type="radio" name="resourceType" id="resourceTypeM" checked="checked" value="M">菜单
					<input type="radio" name="resourceType" id="resourceTypeB" value="B">按钮</div>
				</li>
			</ul>
			<ul class="ui-form grid-row">
				<li class="col-md-4" style="margin-left:100px;"><strong>菜单路径:</strong><input class="ipt-text" id="url" name="url" value="" style="width:600px;" type="text"></li>
				<li class="col-md-4" style="margin-left:150px;">
					<strong>是否启用:</strong>
					<div class="radioDiv" style="margin-left:10px;width:100px;display:inline-block"><input type="radio" name="enabled" id="enabledQ" checked="checked" value="1">是
					<input type="radio" name="enabled" id="enabledF" value="0">否</div>
				</li>
			</ul>
				<ul class="ui-form grid-row">
					<li class="col-md-4" style="margin-left:100px;"><strong>顺序:</strong><input class="ipt-text" id="seq" name="seq" value="" style="width:600px;" type="text"></li>
					<li class="col-md-4 col-btn" style="margin-left:150px;">
						<button type="button" class="btn btn-success btn-radius" onclick="save('1')">确定</button>
				    </li>
			    </ul>
			</form>
			
		  </div>  
	<table class="table table-agents">
			<thead>
				<tr>
					<th>资源路径</th>
					<th>资源类型</th>
					<th>菜单路径</th>
					<th>是否启用</th>
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
	/* function beforeClick(treeId, treeNode){
		var zNodess = zNodess;
		var parentId = treeNode.id;
			$.ajax({  
		        async : false,  
		        cache : false,  
		        type: 'POST',  
		        dataType : "json",  
		        data : {parentId:parentId},
		        url: "${ctx}/resource/find/listByParentId",//请求的action路径  
		        error: function () {//请求失败处理函数  
		            alert('删除成功');  
		        },  
		        success:function(data){ //请求成功后处理函数。
		        	if(data.length>0){
			            $.each(data,function(index,resource){
			            	var zNode = {id:resource.id,pId:resource.parentId,name:resource.resourceName, open:false};
			            	zNodess.push(zNode);
			            });
			            zNodes = unique(zNodess);
		        		$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		        	}
		        }  
		    });  
	} */
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
		/* className = (className === "dark" ? "":"dark");
		showLog("[ "+getTime()+" beforeRemove ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name);
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		zTree.selectNode(treeNode); */
		var id = treeNode.id;
		var bool = true;
		if(confirm("确认删除 节点 -- " + treeNode.name + " 吗？")){
			if(id != null){
				$.ajax({  
			        cache : false,  
			        type: 'POST',  
			        dataType : "json",  
			        data : {id:id},
			        url: "${ctx}/manage/resource/dele",//请求的action路径  
			        beforeSend: function () {  //加载层loading
						layer.load(1, {
							  shade: [0.5,'#fff'] //0.1透明度的白色背景
						});
		            },
					error:function(){
						var index = layer.load();
						layer.close(index);
						layer.msg("删除失败");
	        	    	bool = false;
			    	},
			        success:function(data){ //请求成功后处理函数。
			        	    if(data.code=="1"){
			        	    	var index = layer.load();
								layer.close(index);
			        	    	layer.msg("删除成功");
			        	    	bool = true;
			        	    }else{
			        	    	var index = layer.load();
								layer.close(index);
			        	    	layer.msg("删除失败");
			        	    	bool = false;
			        	    }
			        	}
			    	}); 
			}else{
				layer.msg("删除成功");
				bool = true;
			}
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
	var name;
	function zTreeOnClick(event, treeId, treeNode){
		
		treeNodeEdit = treeNode;
		treeIdEdit = treeId;
		var html = "";
		var htmlForm = "";
		if(treeNode.id!=null){
			$.ajax({
				type : "post",
				data : {id:treeNode.id},
				url : "${ctx}/manage/resource/find/listByPidOrId",
				success : function(data) {
					if (data.code=='1') {
						$.each(data.data,function(index,resource){
							if(treeNode.id != resource.id){
								html+='<tr><td>'+resource.resourceName+'</td>';
								if(resource.resourceType=='M'){
									html+='<td>菜单</td>';
								}else{
									html+='<td>按钮</td>';
								}
								if( null == resource.url){
									html+='<td></td>';
								}else{
									html+='<td>'+resource.url+'</td>';
								}
								if(resource.enabled=='0'){
									html+='<td>未启用</td>';
								}else{
									html+='<td>已启用</td>';
								}
								html +='</tr>';
							}else{
								$("#id").val(resource.id);
								$("#parentId").val(resource.parentId);
								$("#layer").val(treeNode.level);
								$("#resourceName").val(resource.resourceName);
							
								if(resource.resourceType=="M"){
									$("resourceTypeM").prop('checked',true);
								}else{
									$("resourceTypeB").prop('checked',true);
								}
								if( null == resource.url){
									$("#url").val("");
								}
								else{
									$("#url").val(resource.url);
								}
								if(resource.enabled=='0'){
									$("enabledF").prop('checked',true);
								}else{
									$("enabledQ").prop('checked',true);
								}
								if(null == resource.seq){
									$("#seq").val("0");
								}else{
									$("#seq").val(resource.seq);
								}
							}
						});
						$("#resourceTbody").html(html);
					}
				}
			});
		}else{
			$('#id').val(treeNode.id)
			if(parentType == 1 && null == treeNode.pId){
				$('#parentId').val('${sessionScope.resourcePID}');
			}else{
				$('#parentId').val(treeNode.pId);
			}
			$('#layer').val(treeNode.level);
			$('#resourceName').val(treeNode.name);
			$('#url').val("");
			$("#enabledQ").prop('checked',true);
			$("#resourceTypeM").prop('checked',true);
			$("#seq").val("0");
			/* htmlForm+='<input id="id" name="id" value="" type="hidden" />'+
			'<input id="parentId" name="parentId" value="'+treeNode.pId+'" type="hidden" />'+
			'<input id="layer" name="layer" value="'+treeNode.level+'" type="hidden" />'+
			'<ul class="ui-form grid-row">'+
			'<li class="col-md-3" style="margin-left:100px;"><strong>资源路径:</strong><input class="ipt-text" id="resourceName" name="resourceName" value="'+treeNode.name+'" style="width:600px;" type="text"></li>'+
			'<li class="col-md-3" style="margin-left:150px;">'+
			'<strong>资源类型:</strong>'+
			'<div class="radioDiv" style="margin-left:10px;width:150;">'+
			'<input type="radio" name="resourceType" id="resourceTypeM" checked="checked" value="M">菜单'+
			'<input type="radio" name="resourceType" id="resourceTypeB" value="B">按钮'+
			'</div></li></ul>'+
			'<ul class="ui-form grid-row">'+
			'<li class="col-md-3" style="margin-left:100px;"><strong>菜单路径:</strong><input class="ipt-text" id="url" name="url" value="" style="width:600px;" type="text"></li>'+
			'<li class="col-md-3" style="margin-left:150px;">'+
			'<strong>是否启用:</strong>'+
			'<div class="radioDiv" style="margin-left:10px;width:150;">'+
			'<input type="radio" name="enabled" id="enabledQ" checked="checked" value="1">是'+
			'<input type="radio" name="enabled" id="enabledF" value="0">否'+
			'</div></li></ul>'+
			'<ul class="ui-form grid-row" style="display:inline-block; ">'+
			'<li class="col-md-3" style="margin-left:100px;"><strong>顺序:</strong><input class="ipt-text" id="seq" name="seq" value="0" style="width:600px;" type="text"></li>'+
			'<li class="col-md-3 col-btn" style="margin-left:150px;">'+
			'<button type="button" class="btn btn-success btn-radius" onclick="save(\'1\')">确定</button>'+
			'</li></ul>'; */
			$("#resourceTbody").html("");
			/* $("#resourceForm").html(htmlForm); */
		}
		name=treeNode.name;
		parentType = 0;
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
		$.post("${ctx}/manage/resource/find/list",function(result){
			$.each(result,function(index,resource){
				var zNode = {id:resource.id,pId:resource.parentId,name:resource.resourceName,seq:resource.seq, open:false};
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
	var parentType = 0;
	function save(){
		var resourceName = $("#resourceName").val();
		var type = "1";
		if(name == resourceName){
			type = "0";
		}
		$.ajax({
			type : "post",
			data : $("#resourceForm").serialize(),
			url : "${ctx}/manage/resource/update?type="+type,
			beforeSend: function () {  //加载层loading
				layer.load(1, {
					  shade: [0.5,'#fff'] //0.1透明度的白色背景
				});
            },
			success : function(data) {
				if (data.code=='1') {
					layer.alert("保存成功", {icon: 1}, function(){
						treeNodeEdit.id= data.data;
						treeNodeEdit.name= $("#resourceName").val();
						treeNodeEdit.seq = $("#seq").val();
						var treeObj = $.fn.zTree.getZTreeObj(treeIdEdit); 
						treeObj.updateNode(treeNodeEdit);
						$("#id").val(data.data);
						var index = layer.load();
						layer.close(index);
						index = layer.alert();
						layer.close(index);
					});
				}else {
					layer.alert(data.msg, {icon: 5},function(){
						 var index = layer.load();
						 layer.close(index);
						 index = layer.alert();
						 layer.close(index);
					});
				}
			}
		});
	}
	function addParent(parentId){
		parentType = 1;
		var zNode = {name:"新增节点",seq:'0', open:false};
		var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
		zNode = treeObj.addNodes(null, zNode);
		$("#resourceTbody").html("");
	}
</script>
</body>
</html>