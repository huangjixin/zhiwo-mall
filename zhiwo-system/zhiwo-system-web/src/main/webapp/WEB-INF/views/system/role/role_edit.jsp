<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>角色编辑</title>
<%@ include file="/WEB-INF/include/easyui-css.jsp"%>
<%@ include file="/WEB-INF/include/easyui-js.jsp"%>
<script type="text/javascript"
	src="${ctx}/js/jquery-easyui/ajaxfileupload.js"></script>
</head>
<body>
	<form class="form-horizontal" role="form"
		<c:if test="${operation=='edit'}">
 action="${ctx}/role/update"
		</c:if>
		<c:if test="${operation==null}">
 action="${ctx}/role/create"
		</c:if>
		method="post">
        <c:if test="${operation=='edit'}">
        <input id="id" name="id" value="${role.id}" type="hidden"/>
        </c:if>
         <input id="resourcesString" name="resources" value="${resources}" type="hidden"/>
        <div class="form-group">
			<label for="type" class="col-sm-1 control-label">资源授权</label>
            <button type="button" class="btn btn-danger btn-sm"  onClick="$('#treegrid').treegrid('unselectAll');$('#resourcesString').val('');">
                清除
            </button>
			<div class="col-sm-4">
				<table id="treegrid" title="资源树" class="easyui-treegrid"
                    data-options="
                                    url: '${ctx}/resources/getResourcesCheckboxTree',
                                    
                                    selectOnCheck:true,
                                    checkOnSelect:true,
                                    collapsed:true,
                                    fit:false,
                                    method: 'get',
                                    rownumbers: false,
                                    idField: 'id',
                                    collapsible:true,
                                    treeField: 'name',
                                    showHeader: true,
                                    lines: true,
                                    singleSelect : false,
                                    fitColumns:true,
                                    onClickRow: function (item) {
                                    	var resString = $('#resourcesString').val();
                                        var resArray = resString.split(',');
                                        var id = item.id;
                                        if (!Array.indexOf) {  
                                            Array.prototype.indexOf = function (obj) {  
                                                for (var i = 0; i < this.length; i++) {  
                                                    if (this[i] == obj) {  
                                                        return i;  
                                                    }  
                                                }  
                                                return -1;  
                                            }  
                                        }  
                                        var index = resArray.indexOf(id);
                                        if(index !=-1){
                                        	resArray.splice(index,1);
                                            resString  =  JSON.stringify(resArray); 
                                        	$('#resourcesString').val(resString);
                                        }
                                        alert(resString);
                                    },
                                    onUnselect: function (item) {
                                    	
                                    },
                                    onLoadSuccess:function(row,data){
                                    	$('#treegrid').treegrid('collapseAll');
                                    	var resString = ${resources}+'';
                                        if(resString == ''){
                                        	return;
                                        }
                                        var resArray = resString.split(',');
                                        var length = resArray.length;
                                        if(length>0){
                                        	for(var i = 0;i<length;i++){
                                            	$('#treegrid').treegrid('select',resArray[i]);
                                            }
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
			<label for="name" class="col-sm-1 control-label">角色名称</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" id="name" name="name"
					placeholder="请输入角色名称" value="${role.name}">
			</div>
		</div>
		<div class="form-group">
			<label for="code" class="col-sm-1 control-label">代码</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" id="code" name="code"
					placeholder="请输入代码(拼音)" value="${role.code}">
			</div>
		</div>
        <div class="form-group">
			<label  class="col-sm-1 control-label"></label>
			<div class="col-sm-4">
				<%@ include file="/WEB-INF/include/easyui-buttonForm.jsp"%>
				<label id="message">${message}</label>
			</div>
		</div>
	</form>

	<script type="text/javascript">
		
		// 初始化按钮等工作。
		$().ready(function() {

			//返回列表。
			$("#returnBtn").bind("click", function() {
				backToList('role');
			});
		});
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