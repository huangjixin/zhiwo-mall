<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>资源编辑</title>
<%@ include file="/WEB-INF/include/easyui-css.jsp"%>
<%@ include file="/WEB-INF/include/easyui-js.jsp"%>
</head>
<body>
	<form class="form-horizontal" role="form"
		<c:if test="${operation=='edit'}">
 action="${ctx}/resources/update"
		</c:if>
		<c:if test="${operation==null}">
 action="${ctx}/resources/create"
		</c:if>
		method="post">
        <c:if test="${operation=='edit'}">
        <input id="id" name="id" value="${resources.id}" type="hidden"/>
		</c:if>
        <input id="parentId" name="parentId" value="${resources.parentId}" type="hidden"/>
        <div class="form-group">
			<label for="type" class="col-sm-1 control-label">资源父类</label>
            <button type="button" class="btn btn-danger btn-sm"  onClick="$('#treegrid').treegrid('unselectAll');$('#parentId').val('');">
                清除
            </button>
			<div class="col-sm-4">
				<table id="treegrid" title="资源树" class="easyui-treegrid"
                    data-options="
                                    url: '${ctx}/resources/getResourcesCheckboxTree',
                                    collapsed:true,
                                    fit:false,
                                    method: 'get',
                                    rownumbers: false,
                                    idField: 'id',
                                    collapsible:true,
                                    treeField: 'name',
                                    showHeader: true,
                                    lines: true,
                                    singleSelect : true,
                                    fitColumns:true,
                                    onSelect: function (item) {
                                    	$('#parentId').val(item.id);
                                    },
                                    onLoadSuccess:function(row,data){
                                    	var ope = '${operation}';
                                        var pid = '${resources.parentId}';
                                        if(pid!=''){
                                        	$('#treegrid').treegrid('select',pid);
                                        }
                                    }
                                ">
                    <thead>
                        <tr>
                            <th data-options="field:'ck',checkbox:true"></th>
                            <th data-options="field:'id',align:'center',hidden:true">id</th>
                            <th data-options="field:'name',align:'left',width:100">名称</th>
                            <th data-options="field:'authName',align:'center',width:100">权限名称</th>
                            <!--<th data-options="field:'path',align:'center',width:100">链接</th>-->
                            <th data-options="field:'type',align:'center',width:100,formatter:formatType">类型</th>
                        </tr>
                    </thead>
                </table>
			</div>
		</div>
		<div class="form-group">
			<label for="name" class="col-sm-1 control-label">资源名称</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" id="name" name="name"
					placeholder="请输入资源名称" value="${resources.name}">
			</div>
		</div>
		<div class="form-group">
			<label for="authName" class="col-sm-1 control-label">权限名称</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" id="authName" name="authName"
					placeholder="请输入授权名称比如system:user:view" value="${resources.authName}">
			</div>
		</div>
		<div class="form-group">
			<label for="code" class="col-sm-1 control-label">代码</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" id="code" name="code"
					placeholder="请输入资源代码(资源拼音)" value="${resources.code}">
			</div>
		</div>
        <div class="form-group">
			<label for="type" class="col-sm-1 control-label">类型</label>
			<div class="col-sm-4">
				<select id="type" class="easyui-combobox" name="type" style="width:200px;">   
                    <option value="menu" <c:if test="${resources.type=='menu'}">selected=true</c:if>>菜单</option>   
                    <option value="button" <c:if test="${resources.type=='button'}">selected=true</c:if>>按钮</option> 
                </select> 
                <label id="message">${message}</label>
			</div>
		</div>
		<div class="form-group">
			<label for="path" class="col-sm-1 control-label">链接</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" id="path" name="path"
					placeholder="请输入链接" value="${resources.path}">
			</div>
		</div>
		<div class="form-group">
			<label for="description" class="col-sm-1 control-label">描述</label>
			<div class="col-sm-4">
				<textarea name="description" class="form-control" rows="4" >${resources.description}</textarea>
			</div>
		</div>
        <div class="form-group">
			<label  class="col-sm-1 control-label"></label>
			<div class="col-sm-4">
				<%@ include file="/WEB-INF/include/easyui-buttonForm.jsp"%>
			</div>
		</div>
        
	</form>

	<script type="text/javascript">
		
		// 初始化按钮等工作。
		$().ready(function() {

			//返回列表。
			$("#returnBtn").bind("click", function() {
				backToList('resources');
			});
		});
		
		function beforeSubmitHandler()  
		{  
			return false;  
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
	</script>
	<%@ include file="/WEB-INF/include/easyui-footerjs.jsp"%>
</body>
</html>