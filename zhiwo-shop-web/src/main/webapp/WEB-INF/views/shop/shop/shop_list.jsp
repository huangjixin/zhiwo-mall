<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>店铺列表</title>
<%@ include file="/WEB-INF/include/easyui-css.jsp"%>
<%@ include file="/WEB-INF/include/easyui-js.jsp"%>

</head>
<body>
	<%@ include file="/WEB-INF/include/easyui-toolbar.jsp"%>
	<div id="toolbar">
		<nav class="navbar navbar-default" role="navigation">
            <div class="container-fluid"> 
           
            <form class="navbar-form navbar-left" role="search">
                <div class="form-group">
                    <%@ include file="/WEB-INF/include/easyui-buttonGroup.jsp"%>
                &nbsp;&nbsp;&nbsp;&nbsp;
               <input id="nameInput"  class="form-control" placeholder="名称">
                </div>
                <button type="submit" class="btn btn-default">查询</button>
            </form>
            </div>
        </nav>
		
		<%-- <%@ include file="/WEB-INF/include/easyui-queryButton.jsp"%> --%>
		<!-- <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true"
			onclick="create('shop')">新增</a> <a href="#" class="easyui-linkbutton"
			iconCls="icon-edit" plain="true" onclick="editCategory()">编辑</a> <a href="#"
			class="easyui-linkbutton" iconCls="icon-remove" plain="true"
			onclick="destroy()">删除</a> -->
	</div>
	<table id="tgrid" 
		title="店铺列表" 
		class="easyui-datagrid"
		url="${ctx}/shop/select" 
		toolbar="#toolbar" 
		rownumbers="true"
		fitColumns="true" 
		fit="true" 
		singleSelect="true">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'id',align:'center',hidden:true">id</th>
				<th data-options="field:'name',align:'center',width:100">店铺名称</th>
                <th data-options="field:'code',align:'center',width:100">代码</th>
				<th data-options="field:'createDate',align:'center',width:100,formatter:formatTime">创建日期</th>
				<th data-options="field:'updateDate',align:'center',width:100,formatter:formatTime">更新日期</th>
				<!-- <th data-options="field:'By',align:'center',width:100">创建人</th>
				<th data-options="field:'updateBy',align:'center',width:100">更新人</th> -->
				<th data-options="field:'opt',align:'center',width:100,formatter:formatOpt">操作</th>
			</tr>
		</thead>
	</table>
	<script type="text/javascript">
		// 初始化按钮等工作。
		$().ready(function() {
			init("shop","tgrid");
			
			$('#nameInput').bind('keypress',function(event){
			  if(event.keyCode == "13")    
			  {
				    doResearch();
			  }
			});
			
			$("#queryBtn").bind("click", function() {
				doResearch();
			});
	
			$("#removeBatchBtn").bind("click", function() {
				deleteRows('tgrid','user');
			});
		})
		
		
		//查询
		function doResearch(){
			var parameters = {};
			//var usergroupId = $('#shop').combobox('getValue');
//			if(usergroupId!=''){
//				parameters.usergroupId = usergroupId;
//			}
			parameters.username = $('#nameInput').val();
			query('tgrid',parameters);
		}
		
		//格式化操作，添加删除和编辑按钮。
		function formatOpt(value, rec) {
			var btn = '<div style="padding: 5px;">';
//			<%
//				if(SecurityUtils.getSubject()!=null&&SecurityUtils.getSubject().isPermitted("system:shop:delete")){
//				%>
				btn += '<a href="#" class="easyui-linkbutton button-red"  onclick="deleteById(\'tgrid\',\''
					+ rec.id + '\',\'shop\')"><i class="icon-trash"></i>&nbsp;&nbsp;删除 </a>';
					btn += "&nbsp;&nbsp;";
					btn += ''
//				<%
//				}
//			%>
//			<%
//				if(SecurityUtils.getSubject()!=null&&SecurityUtils.getSubject().isPermitted("system:shop:edit")){
//				%> 
				btn += '<a href="#" class="easyui-linkbutton button-green"  onclick="update(\''
					+ rec.id + '\',\'shop\')"><i class="icon-edit"></i>&nbsp;&nbsp;编辑 </a>';
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
						$.post('${ctx}/shop/delete', {
							id : row.id
						}, function(result) {
							if (result > 0) {
								$('#tgrid').datagrid('reload'); // reload the shop data
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