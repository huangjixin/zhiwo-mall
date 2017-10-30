<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>资源列表</title>
<%@ include file="/WEB-INF/include/easyui-css.jsp"%>
<%@ include file="/WEB-INF/include/easyui-js.jsp"%>

</head>
<body>
	<%@ include file="/WEB-INF/include/easyui-toolbar.jsp"%>
	<div id="toolbar">
		<nav class="navbar navbar-default" role="navigation">
            <div class="container-fluid"> 
           
            <div class="navbar-form navbar-left" role="search">
                <div class="form-group">
                    <button type="button"   class="btn btn-success btn-sm" id="addBtn"><i class="fa fa-plus fa-lg"></i>&nbsp;&nbsp;添加</button>
					<button type="button"  class="btn btn-info btn-sm" id="refreshBtn"><i class="fa fa-refresh fa-lg"></i>&nbsp;&nbsp;刷新</button>
					<button type="button" class="btn btn-primary btn-sm" id="unselectBtn"><i class="fa fa-remove fa-lg"></i>&nbsp;&nbsp;取消选中</button>
                </div>
            </div>
            </div>
        </nav>
	</div>
	
	<table id="treegrid" title="资源树" class="easyui-treegrid" toolbar="#toolbar" 
				data-options="
								url: '${ctx}/resources/getResourcesCheckboxTree',
								fit:true,
								method: 'get',
								rownumbers: false,
								idField: 'id',
								collapsible:true,
								treeField: 'name',
								showHeader: true,
								lines: true,
								singleSelect : false,
								fitColumns:true,
                                onLoadSuccess:function(row,data){
                                 	$('#treegrid').treegrid('collapseAll');
                                 }
							">
				<thead>
					<tr>
						<th data-options="field:'ck',checkbox:true"></th>
						<th data-options="field:'id',align:'center',hidden:true">id</th>
						<th data-options="field:'name',align:'left',width:100">名称</th>
						<th data-options="field:'authName',align:'center',width:100">权限名称</th>
						<th data-options="field:'path',align:'center',width:100">链接</th>
						<th data-options="field:'type',align:'center',width:100,formatter:formatType">类型</th>
                        <th data-options="field:'opt',align:'center',formatter:formatOpt">操作</th>
					</tr>
				</thead>
			</table>
	<script type="text/javascript">
		// 初始化按钮等工作。
		$().ready(function() {
			$("#addBtn").click(function(){
				create("resources");
			});
		
			$("#refreshBtn").click(function(){
				$("#treegrid").treegrid('reload'); // 重新加载;
			});
			$("#unselectBtn").click(function(){
				$("#treegrid").treegrid('unselectAll'); // 取消选中;
			});
		})
		
		
	function deleteTreeById(grid,id, module) {
		var url = '${ctx}/' + module + '/deleteById';
		var pamameter = {};
		pamameter.idstring = id;
		$.ajax({
			type : "POST",
			url : url,
			data : pamameter,
			error : function(request) {
				alert("连接失败");
			},
			success : function(data) {
				$('#'+grid).treegrid('reload'); // 重新加载;
			}
		});
	}
	
		//格式化类型
		function formatType(value, rec) {
			var result = "菜单";
			if(rec.type=='button'){
				result = "按钮";
			}else if(rec.type=='menu'){
				result = "菜单";
			}
			return result;
		}

		//格式化操作，添加删除和编辑按钮。
		function formatOpt(value, rec) {
			var btn = '<div style="padding: 5px;">';
//			<%
//				if(SecurityUtils.getSubject()!=null&&SecurityUtils.getSubject().isPermitted("system:product:delete")){
//				%>
				btn += '<button type="button" class="btn btn-danger btn-sm" onclick="deleteTreeById(\'treegrid\',\''
					+ rec.id + '\',\'resources\')"><i class="fa fa-trash fa-lg"></i>&nbsp;&nbsp;删除 </button>';
					btn += "&nbsp;&nbsp;";
					btn += ''
//				<%
//				}
//			%>
//			<%
//				if(SecurityUtils.getSubject()!=null&&SecurityUtils.getSubject().isPermitted("system:product:edit")){
//				%> 
				btn += '<button type="button" class="btn btn-info btn-sm" onclick="update(\''
					+ rec.id + '\',\'resources\')"><i class="fa fa-edit fa-lg"></i>&nbsp;&nbsp;编辑</button>';
//				 <%
//				}
//							%> 
			
			btn += '</div>';
			return btn;
		}

		// 删除
		function destroy() {
			var row = $('#tgrid').datagrid('getSelected');
			if (row) {
				$.messager.confirm('确定', '确定删除？', function(r) {
					if (r) {
						$.post('${ctx}/resources/delete', {
							id : row.id
						}, function(result) {
							if (result > 0) {
								$('#dg').datagrid('reload'); // reload the resources data
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