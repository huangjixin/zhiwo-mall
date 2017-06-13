<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>分类列表</title>
<%@ include file="/WEB-INF/include/easyui-css.jsp"%>
<%@ include file="/WEB-INF/include/easyui-js.jsp"%>
<style type="text/css">
#fm {
	margin: 0;
	padding: 10px 30px;
}

.ftitle {
	font-size: 14px;
	font-weight: bold;
	color: #666;
	padding: 5px 0;
	margin-bottom: 10px;
	border-bottom: 1px solid #ccc;
}

.fitem {
	margin-bottom: 5px;
}

.fitem label {
	display: inline-block;
	width: 80px;
}
</style>
</head>
<body>
	<div id="toolbar">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true"
			onclick="create()">新增</a> <a href="#" class="easyui-linkbutton"
			iconCls="icon-edit" plain="true" onclick="edit()">编辑</a> <a href="#"
			class="easyui-linkbutton" iconCls="icon-remove" plain="true"
			onclick="destroy()">删除</a>
	</div>
	<table id="dg" title="分类列表" class="easyui-datagrid"
		url="${ctx}/category/select" toolbar="#toolbar" rownumbers="true"
		fitColumns="true" fit="true" singleSelect="true">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'id',align:'center'" width="100%">id</th>
				<th data-options="field:'name',align:'center'" width="100%">商品名称</th>
				<th data-options="field:'createDate',align:'center',width:100">创建日期</th>
				<th data-options="field:'updateDate',align:'center',width:100">更新日期</th>
				<!-- <th data-options="field:'By',align:'center',width:100">创建人</th>
				<th data-options="field:'updateBy',align:'center',width:100">更新人</th> -->
				<th data-options="field:'opt',align:'center'">操作</th>
			</tr>
		</thead>
	</table>
	<div id="dlg" class="easyui-dialog"
		style="width: 600px; height: 400px; padding: 10px 20px" closed="true"
		buttons="#dlg-buttons">
		<div class="ftitle">商品分类信息</div>
		<form id="fm" method="post">
			<input id="parentids" name="parentids" type="hidden">
			<div class="fitem">
				<label>父类名称:</label> <input id="parentId" name="parentId"
					value="${org.parentId}" />
			</div>
			<div class="fitem">
				<label>类目名称:</label> <input name="name" class="easyui-validatebox"
					required="true">
			</div>
			<div class="fitem">
				<label>类目英文名称:</label> <input name="enName"
					class="easyui-validatebox" required="true">
			</div>
		</form>
	</div>
	<div id="dlg-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
			onclick="save()">保存</a> <a href="#" class="easyui-linkbutton"
			iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">取消</a>
	</div>
	<script>
		$().ready(function() {
			createCategoryTree('parentId','${ctx}/category/getTreeCategory');
		});	
		
		var url;
		function create() {
			$('#dlg').dialog('open').dialog('setTitle', 'New User');
			$('#fm').form('clear');
			url = '${ctx}/category/create';
		}

		function edit() {
			var row = $('#dg').datagrid('getSelected');
			if (row) {
				$('#dlg').dialog('open').dialog('setTitle', '商品信息编辑');
				$('#fm').form('load', row);
				url = '${ctx}/category/update';
			}
		}

		function save() {
			$('#fm').form('submit', {
				url : url,
				onSubmit : function() {
					return $(this).form('validate');
				},
				success : function(result) {
					if (result > 0) {
						$('#dlg').dialog('close'); // close the dialog
						$('#dg').datagrid('reload'); // reload the user data
					} else {
						$.messager.show({
							title : '保存错误',
							msg : result
						});
					}
				}
			});
		}

		function destroy() {
			var row = $('#dg').datagrid('getSelected');
			if (row) {
				$.messager.confirm('确定', '确定删除？', function(r) {
					if (r) {
						$.post('${ctx}/category/delete', {
							id : row.id
						}, function(result) {
							if (result > 0) {
								$('#dg').datagrid('reload'); // reload the user data
							} else {
								$.messager.show({ // show error message
									title : 'Error',
									msg : result
								});
							}
						}, 'json');
					}
				});
			}
		}
		
		
		//创建栏目树。
		function createCategoryTree(id,url) {
			$('#' + id).combotree({
				url : url,
				valuefield : 'id',
				width : 220,
				textfield : 'name',
				required : false,
				editable : false,
				onClick : function(node) {

				}, //全部折叠
				onLoadSuccess : function(node, data) {
					$('#' + id).combotree('tree').tree("collapseAll");
				},
				onSelect: function (item) { 
					var parent = item; 
					var tree = $('#' + id).combotree('tree'); 
					var path = new Array(); 
					do { 
					   path.unshift(parent.id); 
					   var parent = tree.tree('getParent', parent.target); 
					} while (parent); 
					var pathStr = ''; 
					for (var i = 0; i < path.length; i++) { 
					   pathStr += path[i]; 
					if (i < path.length - 1) { 
					   	pathStr += ','; 
					   } 
					} 
					$('#parentids').val(pathStr); 
				}
			});
		}
	</script>
	
	<%@ include file="/WEB-INF/include/easyui-footerjs.jsp"%>
</body>
</html>