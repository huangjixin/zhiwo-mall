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
</head>
<body>
	<input id="checkStatus" type="hidden" value="${product.checkStatus}"> 
	<%@ include file="/WEB-INF/include/easyui-toolbar.jsp"%>
	<div id="toolbar">
		<nav class="navbar navbar-default" role="navigation">
            <div class="container-fluid"> 
           
            <div class="navbar-form navbar-left" role="search">
                <div class="form-group">
                    <%@ include file="/WEB-INF/include/easyui-buttonGroup.jsp"%>
                	&nbsp;&nbsp;&nbsp;&nbsp;
               		<input id="nameInput"  class="form-control" placeholder="名称">
                    <span>上架/下架</span>
                    <select id="queryStatus" class="easyui-combobox"
					 style="width: 100px;">
                     <option value="" >全部</option>
					 <option value="online" >上架</option>
					 <option value="offline" >下架</option>
					</select>
                    <span>审核状态</span>
                    <select id="queryDisable" class="easyui-combobox"
					 style="width: 100px;">
                     <option value="" >全部</option>
					 <option value="true" >启用</option>
					 <option value="false" >禁用</option>
					</select>
                </div>
                <button id="queryBtn" class="btn btn-default">查询</button>
            </div>
            </div>
        </nav>
	</div>
	<table id="tgrid" 
		title="商品列表" 
		class="easyui-datagrid"
		url="${ctx}/product/select?checkStatus=${product.checkStatus}" 
		toolbar="#toolbar" 
		rownumbers="true"
		fitColumns="true" 
		fit="true" 
		singleSelect="false"
        pagination="true">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'id',align:'center',hidden:true">id</th>
				<th data-options="field:'name',align:'center',width:100">商品名称</th>
                <th data-options="field:'code',align:'center',width:100">代码</th>
                <th data-options="field:'disable',align:'center',width:100,formatter:formatDisable">审核状态</th>
                <th data-options="field:'status',align:'center',width:100,formatter:formatStatus">上架/下架状态</th>
				<th data-options="field:'createDate',align:'center',width:100,formatter:formatTime">创建日期</th>
				<th data-options="field:'updateDate',align:'center',width:100,formatter:formatTime">更新日期</th>
				<!-- <th data-options="field:'By',align:'center',width:100">创建人</th>
				<th data-options="field:'updateBy',align:'center',width:100">更新人</th> -->
				<th data-options="field:'opt',align:'center',width:140,formatter:formatOpt">操作</th>
			</tr>
		</thead>
	</table>
	<script type="text/javascript">
		// 初始化按钮等工作。
		$().ready(function() {
			init("product","tgrid");
			
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
				deleteRows('tgrid','product');
			});
		})
		
		
		//查询
		function doResearch(){
			var parameters = {};
			parameters.name = $('#nameInput').val();
			var status = $("queryStatus").combobox("getValue");
			if(""!=status){
				parameters.status=status;
			}
			
			var disable = $("queryDisable").combobox("getValue");
			if(""!=disable){
				parameters.disable=disable;
			}
			
			query('tgrid',parameters);
		}
		
		//格式化状态。
		function formatStatus(value, rec) {
			var btn = '<div style="padding: 5px;">';
			if(value=='online'){
				btn += '<button type="button" class="btn btn-primary btn-sm">上架 </button>';
					btn += "&nbsp;&nbsp;";
			}
			
			if(value=='offline'){
				btn += '<button type="button" class="btn btn-danger btn-sm">下架 </button>';
					btn += "&nbsp;&nbsp;";
			}
			
					
			btn += '</div>';
			return btn;
		}
		
		//格式化操作，添加删除和编辑按钮。
		function formatOpt(value, rec) {
			var btn = '<div style="padding: 5px;">';
				btn += '<a href="${ctx}/goodsDetail/'+rec.id+'" target="_blank"><button type="button" class="btn btn-primary btn-sm">预览 </button></a>';
						btn += "&nbsp;&nbsp;";
						btn += ''
//			<%
//				if(SecurityUtils.getSubject()!=null&&SecurityUtils.getSubject().isPermitted("system:product:delete")){
//				%>

				btn += '<button type="button" class="btn btn-danger btn-sm" onclick="deleteById(\'tgrid\',\''
					+ rec.id + '\',\'product\')"><i class="fa fa-trash fa-lg"></i>&nbsp;&nbsp;删除 </button>';
					btn += "&nbsp;&nbsp;";
					btn += ''
//				<%
//				}
//			%>
//			<%
//				if(SecurityUtils.getSubject()!=null&&SecurityUtils.getSubject().isPermitted("system:product:edit")){
//				%> 
				btn += '<button type="button" class="btn btn-info btn-sm" onclick="update(\''
					+ rec.id + '\',\'product\')"><i class="fa fa-edit fa-lg"></i>&nbsp;&nbsp;编辑</button>';
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
						$.post('${ctx}/product/delete', {
							id : row.id
						}, function(result) {
							if (result > 0) {
								$('#tgrid').datagrid('reload'); // reload the product data
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