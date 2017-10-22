<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户列表</title>
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
                    <%@ include file="/WEB-INF/include/easyui-buttonGroup.jsp"%>
                	&nbsp;&nbsp;&nbsp;&nbsp;
               		<input id="nameInput"  class="form-control" placeholder="名称">
                 
                    <select id="disable" class="easyui-combobox"
                        name="disable" style="width: 200px;">
                        <option value="false">可用状态</option>
                        <option value="true">禁用状态</option>
                    </select>
                </div>
               
                <button id="queryBtn" class="btn btn-default">查询</button>
            </div>
            </div>
        </nav>
	</div>
	<table id="tgrid" 
		title="用户列表" 
		class="easyui-datagrid"
		url="${ctx}/user/select" 
		toolbar="#toolbar" 
		rownumbers="true"
		fitColumns="true" 
		fit="true" 
		pagination="true"
		singleSelect="false">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
                <th data-options="field:'id',align:'center',hidden:true">id</th>
				<th data-options="field:'username',align:'center'">账号名称</th>
                <th data-options="field:'mobilPhone',align:'center'">电话</th>
                <th data-options="field:'email',align:'center'">邮箱</th>
                <th data-options="field:'realName',align:'center'">实名</th>
                <th data-options="field:'icon',align:'center',formatter:formatIcon">头像</th>
                <th data-options="field:'loginCount',align:'center'">登录次数</th>
                <th data-options="field:'type',align:'center',width:40">商户类型</th>
				<!--<th data-options="field:'createDate',align:'center',width:100,formatter:formatTime">创建日期</th>
				<th data-options="field:'updateDate',align:'center',width:100,formatter:formatTime">更新日期</th>-->
				<!-- <th data-options="field:'By',align:'center',width:100">创建人</th>
				<th data-options="field:'updateBy',align:'center',width:100">更新人</th> -->
				<th data-options="field:'opt',align:'center',width:100,formatter:formatOpt">操作</th>
			</tr>
		</thead>
	</table>
	<script type="text/javascript">
		// 初始化按钮等工作。
		$().ready(function() {
			init("user","tgrid");
			
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
			parameters.username = $('#nameInput').val();
			parameters.disable  = $('#disable').combobox('getValue');
			query('tgrid',parameters);
		}
		
		//格式化操作，添加删除和编辑按钮。
		function formatOpt(value, rec) {
			var btn = '<div style="padding: 5px;">';
			if(rec.disable){
				btn += '<button type="button" class="btn btn-danger btn-sm" onclick="disableSetting(\''+rec.id+'\','+ rec.disable + ')">设置可用</button>';
				btn += "&nbsp;&nbsp;";
			}else{
				btn += '<button type="button" class="btn btn-danger btn-sm" onclick="disableSetting(\''+rec.id+'\','+ rec.disable + ')">设置禁用</button>';
				btn += "&nbsp;&nbsp;";
			}
//			<%
//				if(SecurityUtils.getSubject()!=null&&SecurityUtils.getSubject().isPermitted("system:user:delete")){
//				%>
				btn += '<button type="button" class="btn btn-danger btn-sm" onclick="deleteById(\'tgrid\',\''
					+ rec.id + '\',\'user\')"><i class="fa fa-trash fa-lg"></i>&nbsp;&nbsp;删除 </button>';
					btn += "&nbsp;&nbsp;";
					btn += ''
//				<%
//				}
//			%>
//			<%
//				if(SecurityUtils.getSubject()!=null&&SecurityUtils.getSubject().isPermitted("system:user:edit")){
//				%> 
				btn += '<button type="button" class="btn btn-info btn-sm" onclick="update(\''
					+ rec.id + '\',\'user\')"><i class="fa fa-edit fa-lg"></i>&nbsp;&nbsp;编辑</button>';
//				 <%
//				}
//							%> 
			
			btn += '</div>';
			return btn;
		}

		function formatIcon(value, rec) {
			var result = '<img id="iconImg" src="${ctx}/'+rec.icon+'" class=".img-responsive" style="width: 100px;">';
			return result;
		}
		
		//设置用户状态。
		function disableSetting(userId,value) {
			var url = "${ctx}/user/disableSetting?userId="+userId+"&disable="+value;
			$.ajax({
				 type: "POST",
				 url: url,
				 dataType: "json",
				 success: function(data){
					$('#tgrid').datagrid('reload');		
				 }
			 });
		}
		
		
		// 删除
		function destroy() {
			var row = $('#tgrid').datagrid('getSelected');
			if (row) {
				$.messager.confirm('确定', '确定删除？', function(r) {
					if (r) {
						$.post('${ctx}/user/delete', {
							id : row.id
						}, function(result) {
							if (result > 0) {
								$('#tgrid').datagrid('reload'); // reload the user data
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