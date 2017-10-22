<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="copyright"
	content="All Rights Reserved, Copyright (C) 2013, zhihuiduo, Ltd." />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>智惠多后台管理平台首页</title>
<link href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css"
	rel="stylesheet" type="text/css">
<link href="http://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet" type="text/css">
<link href="http://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	rel="stylesheet" type="text/css">
<link href="https://cdn.bootcss.com/bootstrap/4.0.0-alpha.6/css/bootstrap-reboot.min.css"
	rel="stylesheet" type="text/css">
<link href="https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link href="https://cdn.insdep.com/themes/1.0.0/easyui.css"
	rel="stylesheet" type="text/css">
<link href="https://cdn.insdep.com/themes/1.0.0/easyui_animation.css"
	rel="stylesheet" type="text/css">
<link href="https://cdn.insdep.com/themes/1.0.0/easyui_plus.css"
	rel="stylesheet" type="text/css">
<link href="https://cdn.insdep.com/themes/1.0.0/insdep_theme_default.css"
	rel="stylesheet" type="text/css">
<link href="https://cdn.insdep.com/themes/1.0.0/icon.css"
	rel="stylesheet" type="text/css">
<link href="https://cdn.bootcss.com/Swiper/3.4.2/css/swiper.min.css"
	rel="stylesheet" type="text/css">
<link href="https://cdn.bootcss.com/jquery.bootstrapvalidator/0.5.3/css/bootstrapValidator.min.css"
	rel="stylesheet" type="text/css">

<script type="text/javascript" src="https://cdn.insdep.com/jquery/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="https://cdn.insdep.com/easyui/jquery.easyui-1.5.1.min.js"></script>
<script type="text/javascript" src="https://cdn.insdep.com/themes/1.0.0/jquery.insdep-extend.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery-easyui/ajaxfileupload.js"></script>
<script type="text/javascript" src="https://cdn.bootcss.com/jquery.bootstrapvalidator/0.5.3/js/bootstrapValidator.min.js"></script>
<!--<script type="text/javascript" src="easyui/1.3.4/jquery.easyui.min.js"></script>
<script type="text/javascript" src="easyui/1.3.4/locale/easyui-lang-zh_CN.js"></script>-->
</head>
<body>
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'center',border:false" style="padding: 2px;">
			<!-- Begin of toolbar -->
			<div id="toolbar">
				<div style="padding: 5px 5px;">
					<button type="button" onclick="openAdd()"
						class="btn btn-primary btn-xs">
						<i class="fa fa-plus" aria-hidden="true"></i>&nbsp;添加
					</button>
					<button type="button" onclick="openEdit()"
						class="btn btn-success btn-xs">
						<i class="fa fa-edit" aria-hidden="true"></i>&nbsp;修改
					</button>
					<button type="button" onClick="remove();"
						class="btn btn-danger  btn-xs">
						<i class="fa fa-trash" aria-hidden="true"></i>&nbsp;批量删除
					</button>
					<button type="button"
						onClick="$('#datagrid').datagrid('unselectAll');"
						class="btn btn-primary  btn-xs">
						<i class="fa fa-train" aria-hidden="true"></i>&nbsp;取消
					</button>
					<button onClick="$('#datagrid').datagrid('reload');" type="button"
						class="btn btn-info  btn-xs">
						<i class="fa fa-refresh" aria-hidden="true"></i>&nbsp;刷新
					</button>
					<button type="button" class="btn btn-warning  btn-xs">
						<i class="fa fa-arrow-left" aria-hidden="true"></i>&nbsp;返回
					</button>

				</div>
				<div style="padding: 2px 5px;">
					<!--<span>起始时间：</span><input class="easyui-datebox" style="width:100px;height:24px;">
                <span>结束时间：</span><input class="easyui-datebox" style="width:100px;height:24px;">
                &nbsp;-->
					<span>用户组：</span>  <input class="easyui-combobox" id="queryUsergroupId" name="usergroupId"
                            data-options="valueField:'id',textField:'text',url:'${ctx}/userGroup/listAll',value:''">
                    <span>是否启用：</span> <select class="easyui-combobox" id="queryDisable"
						panelHeight="auto" style="width: 80px; height: 24px;">
                        <option value="">全部</option>
						<option value="0">启用</option>
						<option value="1">禁用</option>
					</select> &nbsp; <span>关键词(请输入用户名)：</span><input
						class="easyui-textbox" id="searchInput"
						style="width: 200px; height: 24px;">
						<span>电话：</span><input
						class="easyui-textbox" id="mInput"
						style="width: 200px; height: 24px;">
					<button type="button" class="btn btn-default btn-xs" onClick="doResearch();">
						<i class="fa fa-search" aria-hidden="true"></i>&nbsp;搜索
					</button>
				</div>
			</div>

			<table id="datagrid" class="easyui-datagrid"
				data-options="url:'${ctx}/user/select',rownumbers:true,fitColumns:true,pageSize:10,pagination:true,singleSelect:false,toolbar:'#toolbar'">
				<thead>
					<tr>
						<th data-options="field:'id',align:'center',width:100,hidden:true">id</th>
						<th data-options="field:'username',align:'center',width:100">用户名称</th>
                        <th data-options="field:'mobilPhone',align:'center',width:100">手机</th>
                        <th data-options="field:'qq',align:'center',width:100">QQ</th>
						<th
							data-options="field:'icon',align:'center',width:100,formatter:formatIcon">头像</th>
						<th data-options="field:'email',align:'center',width:100">邮件</th>
						<th
							data-options="field:'opt',align:'center',width:100,formatter:formatOpt">操作</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
	<!-- Begin of easyui-dialog -->
	<div id="dialog" class="easyui-dialog"
		data-options="closed:true,iconCls:'icon-save'"
		style="width: 700px; padding: 10px; overflow: hidden;">
		<div style="max-height: 400px;">

			<form id="form" method="post" class="form-horizontal" role="form">
				<input id="id" type="hidden" name="id"> <input id="icon"
					type="hidden" name="icon">
                <div class="form-group">
                    <label for="usergroupId" class="col-sm-2 control-label">用户组</label>
                    <div class="col-sm-4">
                        <input class="easyui-combobox" id="usergroupId" name="usergroupId"
                            data-options="valueField:'id',textField:'text',url:'${ctx}/userGroup/listAll',value:''">
                    </div>
                </div>
				<div class="form-group">
					<label for="username" class="col-sm-2 control-label">姓 名:</label>
					<div class="col-sm-4">
						<input type="text" name="username" class="form-control"
							placeholder="请输入用户名">
					</div>
                    <label for="username" class="col-sm-2 control-label">邮 箱:</label>
					<div class="col-sm-4">
						<input type="text" name="email" class="form-control"
							placeholder="请输邮箱">
					</div>
					
				</div>
				<div class="form-group" id="passwordGroup">
					<label class="col-sm-2 control-label" for="password">密码：</label>
					<div class="col-sm-4">
						<input class="form-control" id="password" name="password"
							type="password" />
					</div>
					<label class="col-sm-2 control-label" for="repassword">确认密码：</label>
					<div class="col-sm-4">
						<input class="form-control" id="repassword" name="repassword"
							type="password" />
					</div>
				</div>

				<div class="form-group">
					<label for="disable" class="col-sm-2 control-label">是否启用:</label>
					<div class="col-sm-4">
						<select class="easyui-combobox" name="disable" panelHeight="auto"
							style="width: 100px; height: 24px;">
							<option value="0" selected="selected">启用</option>
							<option value="1">禁用</option>
						</select>
					</div>
					<label for="mobilPhone" class="col-sm-2 control-label">手 机:</label>
					<div class="col-sm-4">
						<input type="text" name="mobilPhone" class="form-control"
							placeholder="请输手机">
					</div>
				</div>

				<div class="form-group">
					<label for="qq" class="col-sm-2 control-label">Q Q:</label>
					<div class="col-sm-4">
						<input type="text" name="qq" class="form-control" placeholder="QQ">
					</div>
					<label for="icon" class="col-sm-2 control-label">头 像:</label>
					<div class="col-sm-4">
						<input type="hidden" name="icon"> <input type="file"
							id="file" name="file" style="display: none;" accept="image/*"
							onChange="preImg(this.id,'iconImg');" />
						<button type="button"
							class="btn btn-success btn-sm fileinput-button"
							onclick="$('#file').click();">
							<i class="fa fa-plus"></i>&nbsp;&nbsp;选择文件
						</button>
						<button type="button" class="btn btn-primary btn-sm start"
							onclick="fileUploadToServer();">
							<i class="fa fa-upload"></i> <span>&nbsp;&nbsp;开始上传</span>
						</button>
						<span id="message"></span>
					</div>
				</div>
				<div class="form-group">
					<label for="description" class="col-sm-2 control-label">描述</label>
					<div class="col-sm-4">
						<textarea name="description" class="form-control" rows="3"></textarea>
					</div>
					<label for="iconImg" class="col-sm-2 control-label">头像预览:</label>
					<div class="col-sm-4">
						<img id="iconImg" name="iconImg" class=".img-responsive"
							style="width: 100px;">
					</div>
				</div>
			</form>
		</div>
	</div>
	<!-- End of easyui-dialog -->
	<script type="text/javascript">
		$(function(){
			//createDatagrid();	
			formValidator();
		})
		
	function formatOpt(value, rec) {
			var btn = '<div style="padding: 5px;">';
			if(rec.disable){
				btn += '<button type="button" class="btn btn-primary btn-sm" onclick="disableSetting(\''+rec.id+'\','+ rec.disable + ')">设置启用</button>';
				btn += "&nbsp;&nbsp;";
			}else{
				btn += '<button type="button" class="btn btn-danger btn-sm" onclick="disableSetting(\''+rec.id+'\','+ rec.disable + ')">设置禁用</button>';
				btn += "&nbsp;&nbsp;";
			}
			
				/*btn += '<button type="button" class="btn btn-danger btn-sm" onclick="deleteById(\'tgrid\',\''
					+ rec.id + '\',\'role\')"><i class="fa fa-trash fa-lg"></i>&nbsp;&nbsp;删除 </button>';
					btn += "&nbsp;&nbsp;";
					btn += ''*/
				btn += '<button type="button" class="btn btn-info btn-sm" onclick="openEdit(\''
					+ rec.id + '\')"><i class="fa fa-edit fa-lg"></i>&nbsp;&nbsp;编辑</button>';
				btn += '<button type="button" class="btn btn-info btn-sm" onclick="passwordReset(\''
					+ rec.id + '\')"><i class="fa fa-edit fa-lg"></i>&nbsp;&nbsp;重置密码</button>';
			btn += '</div>';
			return btn;
		}

		function passwordReset(id){
			
		}
		
		function formatIcon(value, rec) {
			if(rec.icon==''){
				return '';
			}
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
					$('#datagrid').datagrid('reload');		
				 }
			 });
		}
		
		//查询
		function doResearch(){
			
			var parameters = {};
			parameters.searchPara = $('#searchInput').val();
			var disable  = $('#queryDisable').combobox('getValue');
			if(disable  != ''){
				parameters.disable = disable;
			}
			var usergroupId  = $('#queryUsergroupId').combobox('getValue');
			if(usergroupId  != ''){
				parameters.usergroupId = usergroupId;
			}
			
			var mobilPhone = $('#mInput').val();
			if(mobilPhone  != ''){
				parameters.mobilPhone = mobilPhone;
			}
			$('#datagrid').datagrid('reload', parameters);
		}			
	/**
	* Name 添加记录
	*/
	function add(){
		var bootstrapValidator = $("#form").data('bootstrapValidator');
		bootstrapValidator.validate();
		var isValid = bootstrapValidator.isValid();
		if(!isValid){
			return;
		}
		
		$('#form').form('submit', {
			url:'${ctx}/user/create',
			success:function(data){
				if(data){
					$.messager.alert('信息提示','提交成功！','info');
					$('#datagrid').datagrid("reload");
					$('#dialog').dialog('close');
				}
				else
				{
					$.messager.alert('信息提示','提交失败！','info');
				}
			}
		});
	}
	
	/**
	* Name 修改记录
	*/
	function edit(){
		var bootstrapValidator = $("#form").data('bootstrapValidator');
		bootstrapValidator.validate();
		var isValid = bootstrapValidator.isValid();
		if(!isValid){
			return;
		}
		
		$('#form').form('submit', {
			url:'${ctx}/user/update',
			success:function(data){
				if(data){
					$.messager.alert('信息提示','提交成功！','info');
					$('#dialog').dialog('close');
					$('#datagrid').datagrid("reload");
				}
				else
				{
					$.messager.alert('信息提示','提交失败！','info');
				}
			}
		});
	}
	
	/**
	* Name 删除记录
	*/
	function remove(){
		var item = $('#datagrid').datagrid('getSelected');
		if(!item){
			return;
		}
		
		$.messager.confirm('信息提示','确定要删除记录？', function(result){
			if(result){
				var items = $('#datagrid').datagrid('getSelections');
				var ids = [];
				var idStr = "";
				$(items).each(function(){
					ids.push(this.id);	
					idStr += this.id+",";
				});
				idStr = idStr.substring(0,idStr.length-1);
				var data={};
				data.idstring= idStr;
				//alert(ids);return;
				var url = "${ctx}/user/deleteById"
				$.ajax({
					url:url,
					data:data,
					success:function(data){
						$('#datagrid').datagrid('reload');
					}	
				});
			}	
		});
	}
	
	/**
	* Name 打开添加窗口
	*/
	function openAdd(){
		//清空表单校验
		$("#form").data('bootstrapValidator').destroy();
		$('#form').data('bootstrapValidator', null);
		formValidator();
		
		$('#passwordGroup').css('display','inline');
		$('#message').html('');
		$('#form').form('clear');
		var id = new Date().getTime();
		$('#id').val(id);
		
		$('#dialog').dialog({
			closed: false,
			modal:true,
            title: "添加信息",
            buttons: [{
                text: '确定',
                iconCls: 'icon-ok',
                handler: add
            }, {
                text: '取消',
                iconCls: 'icon-cancel',
                handler: function () {
                    $('#dialog').dialog('close');                    
                }
            }]
        });
	}
	
	/**
	* Name 打开修改窗口
	*/
	function openEdit(recId){
		var itemId;
		var item;
		if(recId){
			itemId = recId;
		}else{
			item = $('#datagrid').datagrid('getSelected');
		}
		
		if(!item){
			return;
		}
		itemId = item.id;
		$('#passwordGroup').css('display','none');
		//清空表单校验
		$("#form").data('bootstrapValidator').destroy();
		$('#form').data('bootstrapValidator', null);
		formValidator();
				
		$('#message').html('');
		$('#form').form('clear');
		var url = '${ctx}/user/show/'+itemId;
		//alert(item.productid);return;
		$.ajax({
			url:url,
			success:function(data){
				if(data){
					$('#form').form('load', data);//绑定值
					$('#usergroupId').combobox('setValue',data.usergroupId);	
					
					$('#iconImg').attr('src','${ctx}/'+data.icon);
				}
				else{
					$('#dialog').dialog('close');
				}
			}	
		});
		$('#dialog').dialog({
			closed: false,
			modal:true,
            title: "修改信息",
            buttons: [{
                text: '确定',
                iconCls: 'icon-ok',
                handler: edit
            }, {
                text: '取消',
                iconCls: 'icon-cancel',
                handler: function () {
                    $('#dialog').dialog('close');                    
                }
            }]
        });
	}	
	
	
	
		/** 
		 * 从 file 域获取 本地图片 url 
		 */
		function getFileUrl(sourceId) {
			var url;
			if (navigator.userAgent.indexOf("MSIE") >= 1) { // IE 
				url = document.getElementById(sourceId).value;
			} else if (navigator.userAgent.indexOf("Firefox") > 0) { // Firefox 
				url = window.URL.createObjectURL(document
						.getElementById(sourceId).files.item(0));
			} else if (navigator.userAgent.indexOf("Chrome") > 0) { // Chrome 
				url = window.URL.createObjectURL(document
						.getElementById(sourceId).files.item(0));
			}
			return url;
		}

		/** 
		 * 将本地图片 显示到浏览器上 
		 */
		function preImg(sourceId, targetId) {
			var url = getFileUrl(sourceId);
			var imgPre = document.getElementById(targetId);
			imgPre.src = url;
		}
		
		function fileUploadToServer() {
			var fileValue = $('#file').val();
			if (fileValue == '') {
				$('#message').html('请选择一个文件')
				return;
			}
			$('#message').html('正在上传……');
			var id = $("#id").val();
			var url = '${ctx}/fileupload/userAssets?orgId='+id;
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
					$('#message').html('上传成功');
				},
				error : function(data, status, e)//服务器响应失败处理函数
				{
					alert("上传失败");
				}
			})
		}
		
		
		//form验证规则
	function formValidator(){
		$('#form').bootstrapValidator({
　　　　　　　　message: 'This value is not valid',
            　feedbackIcons: {
                　　　　　　　　valid: 'glyphicon glyphicon-ok',
                　　　　　　　　invalid: 'glyphicon glyphicon-remove',
                　　　　　　　　validating: 'glyphicon glyphicon-refresh'
            　　　　　　　　   },
            fields: {
                username: {
                    message: '用户帐号验证失败',
                    validators: {
                        notEmpty: {
                            message: '用户帐号不能为空'
                        },stringLength: {
                            min: 1,
                            max: 18,
                            message: '长度必须在1到18位之间'
                        },regexp: {
							 regexp: /^[a-zA-Z0-9_\.]+$/,
							 message: '用户名由数字字母下划线和.组成'
						}
                    }
                },
             password: {
                 message:'密码无效',
                 validators: {
                     notEmpty: {
                         message: '密码不能为空'
                     },
                     stringLength: {
                         min: 6,
                         max: 30,
                         message: '密码长度必须在6到30之间'
                     },
                     identical: {//相同
                         field: 'password', //需要进行比较的input name值
                         message: '两次密码不一致'
                     },
                     different: {//不能和用户名相同
                         field: 'username',//需要进行比较的input name值
                         message: '不能和用户名相同'
                     },
                     regexp: {
                         regexp: /^[a-zA-Z0-9_\.]+$/,
                         message: '用户名只能够输入数字字母'
                     }
                 }
             },repassword: {
                 message: '密码无效',
                 validators: {
                     notEmpty: {
                         message: '密码不能为空'
                     },
                     stringLength: {
                         min: 6,
                         max: 32,
                         message: '用户名长度必须在6到30之间'
                     },
                     identical: {//相同
                         field: 'password',
                         message: '两次密码不一致'
                     },
                     different: {//不能和用户名相同
                         field: 'username',
                         message: '不能和用户名相同'
                     },
                     regexp: {//匹配规则
                         regexp: /^[a-zA-Z0-9_\.]+$/,
                         message: 'The username can only consist of alphabetical, number, dot and underscore'
                     }
                 }
             },
             email: {
                 validators: {
                     notEmpty: {
                         message: '邮件不能为空'
                     },
                     emailAddress: {
                         message: '请输入正确的邮件地址如：123@qq.com'
                     }
                 }
             },
                mobilPhone: {
                    validators: {
                        notEmpty: {
                            message: '电话不能为空'
                        },stringLength: {
                         min: 11,
                         max: 11,
                         message: '请输入11位手机号码'
                     },
                     regexp: {
                         regexp: /^1[3|5|8]{1}[0-9]{9}$/,
                         message: '请输入正确的手机号码'
                     }
                    }
                },qq: {/*键名和input name值对应*/
                    message: 'QQ验证不通过',
                    validators: {
                        notEmpty: {/*非空提示*/
                            message: 'QQ不能为空'
                        },
                        regexp: {/* 只需加此键值对，包含正则表达式，和提示 */
                            regexp: /^[0-9_\.]+$/,
                            message: '只能是数字'
                        },
                        stringLength: {/*长度提示*/
                            min: 6,
                            max: 12,
                            message: '用户名长度必须在6到12之间'
                        }/*最后一个没有逗号*/
                    }
                },描述: {
                 message:'描述无效',
                 validators: {
                     stringLength: {
                         min: 0,
                         max: 120,
                         message: '描述长度必须在0到120之间'
                     }
                 	}
             	}
            }
        });
	}
</script>
</body>