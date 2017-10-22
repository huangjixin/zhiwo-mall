<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>店铺分类编辑</title>
<%@ include file="/WEB-INF/include/easyui-css.jsp"%>
<%@ include file="/WEB-INF/include/easyui-js.jsp"%>
<script type="text/javascript" src="${ctx}/js/jquery-easyui/ajaxfileupload.js"></script>

</head>
<body>
	<form class="form-horizontal" role="form"
		<c:if test="${operation=='edit'}">
 action="${ctx}/shopCategory/update"
		</c:if>
		<c:if test="${operation==null}">
 action="${ctx}/shopCategory/create"
		</c:if>
		method="post">
        <c:if test="${operation=='edit'}">
        <input id="id" name="id" value="${shopCategory.id}" type="hidden"/>
		</c:if>
        <input id="parentId" name="parentId" value="${resources.parentId}" type="hidden"/>
        <input id="icon" name="icon" value="${shopCategory.icon}" type="hidden"/>
        <div class="form-group">
			<label for="type" class="col-sm-1 control-label">资源父类</label>
            <button type="button" class="btn btn-danger btn-sm"  onClick="$('#treegrid').treegrid('unselectAll');$('#parentId').val('');">
                清除
            </button>
			<div class="col-sm-4">
				<table id="treegrid" title="店铺分类树形展示" class="easyui-treegrid"
                    data-options="
                                    url: '${ctx}/shopCategory/getShopCategoryTree',
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
                                        var pid = '${shopCategory.parentId}';
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
                            <th data-options="field:'code',align:'center',width:100">代码</th>
                        </tr>
                    </thead>
                </table>
			</div>
		</div>
		<div class="form-group">
			<label for="name" class="col-sm-1 control-label">店铺分类名称</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" id="name" name="name"
					placeholder="请输入店铺分类名称" value="${shopCategory.name}">
			</div>
		</div>
		<div class="form-group">
			<label for="code" class="col-sm-1 control-label">店铺分类代码</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" id="code" name="code"
					placeholder="请输入店铺分类代码(店铺分类拼音)" value="${shopCategory.code}">
			</div>
		</div>
		<div class="form-group">
			<label for="file" class="col-sm-1 control-label">头像上传</label>
			<div class="col-sm-4">
				<input type="file" id="file" name="file" style="display: none;"
					accept="image/*" onChange="$('#message').html($('#file').val())" />
				<button type="button" class="btn btn-success fileinput-button"
					onclick="$('#file').click();">
					<i class="fa fa-plus"></i>&nbsp;&nbsp;选择文件
				</button>
				<button type="button" class="btn btn-primary start"
					onclick="fileUploadToServer();">
					<i class="fa fa-upload"></i> <span>&nbsp;&nbsp;开始上传</span>
				</button>
				<%@ include file="/WEB-INF/include/easyui-buttonForm.jsp"%>
				<label id="message">${message}</label>
			</div>
		</div>
		<div class="form-group">
			<label for="iconImg" class="col-sm-1 control-label"></label>
			<div class="col-sm-4">
				<img id="iconImg" name="icon" src="${ctx}/${shopCategory.icon}" class=".img-responsive"
					style="width: 100px;">
			</div>
		</div>
		<div class="form-group">
			<label for="description" class="col-sm-1 control-label">店铺分类描述</label>
			<div class="col-sm-10">
				<textarea name="description" class="form-control" rows="4" >${shopCategory.description}</textarea>
			</div>
		</div>
	</form>

	<script type="text/javascript">
		// 初始化按钮等工作。
		$().ready(function() {
			//返回列表。
			$("#returnBtn").bind("click", function() {
				backToList('shopCategory');
			});
		});

		function fileUploadToServer() {
			var fileValue = $('#file').val();
			if (fileValue == '') {
				$('#message').html('请选择一个文件')
				return;
			}
			$('#message').html('正在上传……');
			var url = '${ctx}/fileupload/userAssets';
			$.ajaxFileUpload({
				url : url, //用于文件上传的服务器端请求地址
				secureuri : false, //是否需要安全协议，一般设置为false
				fileElementId : 'file', //文件上传域的ID
				dataType : 'json', //返回值类型 一般设置为json
				success : function(data, status) //服务器成功响应处理函数
				{
					if (data.assets.length > 0) {
						var assets = data.assets[0];
						$("#iconImg").attr('src', '${ctx}/' + assets.url);
						$("#icon").val(assets.url);
					}
					$('#message').html('');
				},
				error : function(data, status, e)//服务器响应失败处理函数
				{
					alert("上传失败");
				}
			})
		}
	</script>
	<%@ include file="/WEB-INF/include/easyui-footerjs.jsp"%>
</body>
</html>