<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品列表</title>
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
			onclick="create('product')">新增</a> <a href="#" class="easyui-linkbutton"
			iconCls="icon-edit" plain="true" onclick="editCategory()">编辑</a> <a href="#"
			class="easyui-linkbutton" iconCls="icon-remove" plain="true"
			onclick="destroy()">删除</a>
	</div>
	<table id="dg" title="商品列表" class="easyui-datagrid"
		url="${ctx}/product/select" toolbar="#toolbar" rownumbers="true"
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
	<script>
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
		
	</script>
	
	<%@ include file="/WEB-INF/include/easyui-footerjs.jsp"%>
</body>
</html>