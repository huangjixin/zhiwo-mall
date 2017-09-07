<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>竞猜问题编辑</title>
<%@ include file="/WEB-INF/include/easyui-css.jsp"%>
<%@ include file="/WEB-INF/include/easyui-js.jsp"%>
<script type="text/javascript"
	src="${ctx}/js/jquery-easyui/ajaxfileupload.js"></script>
<script type="text/javascript"  
    src="http://tarruda.github.com/bootstrap-datetimepicker/assets/js/bootstrap-datetimepicker.min.js">  
</script> 
</head>
<body>
	
    
	
	<form  class="form-horizontal" role="form"
		<c:if test="${operation=='edit'}">
 action="${ctx}/guessQuestion/update"
		</c:if>
		<c:if test="${operation==null}">
 action="${ctx}/guessQuestion/create"
		</c:if>
		method="post">
         <input id="id" name="id" value="${guessQuestion.id}" type="hidden"/>
		 <input id="icon" name="icon" value="${guessQuestion.icon}" type="hidden"/>
         
		<div class="form-group">
			<label for="name" class="col-sm-1 control-label">竞猜问题名称</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" id="name" name="name"
					placeholder="请输入竞猜问题名称" value="${guessQuestion.name}">
			</div>
		</div>
		<div class="form-group">
			<label for="code" class="col-sm-1 control-label">代码</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" id="code" name="code"
					placeholder="请输入代码(拼音)" value="${guessQuestion.code}">
			</div>
		</div>
        <div class="form-group">
			<label for="questionEndTime" class="col-sm-1 control-label">截止日期</label>
            
			<div class="col-sm-4">
				<input type="text" id="questionEndTime"  name="questionEndTime" class="easyui-datetimebox" value="<fmt:formatDate value="${guessQuestion.questionEndTime}" pattern="yyyy-MM-dd HH:mm:ss"/>">
			</div>
		</div>
        
		<div class="form-group">
			<label for="file" class="col-sm-1 control-label">问题图片</label>
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
				<label id="message">${message}</label>
			</div>
		</div>
		<div class="form-group">
			<label for="file" class="col-sm-1 control-label"></label>
			<div class="col-sm-4">
				<img id="iconImg" <c:if test="${!empty guessQuestion.icon}">src="${ctx}/${guessQuestion.icon}"</c:if> 	class=".img-responsive"
					style="width: 100px;">
			</div>
		</div>
		<div class="form-group">
			<label for="description" class="col-sm-1 control-label">问题描述</label>
			<div class="col-sm-4">
				<textarea name="description" class="form-control" rows="4" >${guessQuestion.description}</textarea>
			</div>
		</div>
        <div class="form-group">
			<label for="description" class="col-sm-1 control-label">问题选项</label>
			<div class="col-sm-8">
            	<table id="datagrid" 
                    title="" 
                    class="easyui-datagrid"
                    url="${ctx}/guessQuestionOptions/selectByQuestionId?guessQuestionId=${guessQuestion.id}" 
                    rownumbers="true"
                    fitColumns="true" 
                    fit="false" 
                    pagination="false"
                    singleSelect="true">
                    <thead>
                        <tr>
                            <th data-options="field:'id',align:'center',hidden:true">id</th>
                            <th data-options="field:'name',align:'center',width:50">名称</th>
                            <th data-options="field:'betRate',align:'center',width:50">赔率</th>
                            <th data-options="field:'opt',align:'center',width:50,formatter:formatOpt">操作</th>
                        </tr>
                    </thead>
                </table>
				<!--<input type="text" class="form-control" name="code"
					placeholder="请输入代码(拼音)" value=""><button type="button" class="btn btn-danger btn-sm" onclick=""><i class="fa fa-trash fa-lg"></i>&nbsp;&nbsp;删除 </button>
                    <button type="button" class="btn btn-danger btn-sm" onclick=""><i class="fa fa-trash fa-lg"></i>&nbsp;&nbsp;修改</button>-->
			</div>
		</div>
        
        <div class="form-group">
			<label  class="col-sm-1 control-label"></label>
			<div class="col-sm-4">
				<%@ include file="/WEB-INF/include/easyui-buttonForm.jsp"%>
                
			</div>
		</div>
        <!-- 模态框（Modal） -->
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                    <h4 class="modal-title" id="myModalLabel">
                        游戏竞猜问题选项新增与编辑
                    </h4>
                </div>
                <div class="modal-body">
                    <div class="form-group">
							<label for="questionName" class="col-sm-2 control-label">名称</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" id="optionName"
									placeholder="名称" value="">

							</div>
						</div>
                     <div class="form-group">
							<label for="betRate" class="col-sm-2 control-label">赔率</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" id="betRate"
									name="betRate" placeholder="赔率" value="">

							</div>
						</div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                    </button>
                    <button type="button" class="btn btn-primary" onClick="saveOptions();">
                        保存
                    </button>
                    <label id="message"></label>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>
	</form>
    <div class="form-group">
			<label  class="col-sm-1 control-label"></label>
			<div class="col-sm-4">
				 <button id="editOptionsBtn" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#myModal">新增竞猜问题选项</button> 
			</div>
		</div>
   
	<script type="text/javascript">
		var editOptions=false;
		// 初始化按钮等工作。
		$().ready(function() {
			//返回列表。
			$("#returnBtn").bind("click", function() {
				backToList('guessQuestion');
			});
			//var unixTimestamp = new Date('${guessQuestion.questionEndTime}');  
			//$("#questionEndTime").datetimebox('setValue', unixTimestamp);
		});

		
		function saveOptions(){
			var optionName = $('#optionName').val();
			var betRate    = $('#betRate').val();	
			var guessQuestionId    = $('#id').val();
			var data = {};
			data.name=optionName;
			data.betRate = betRate;
			data.guessQuestionId = guessQuestionId;
			var url = '${ctx}/guessQuestionOptions/create';
			/*if(editOptions){
				data.id = $('#datagrid').datagrid('getSelected').id;
				url = '${ctx}/guessQuestionOptions/update';
			}*/
			
			$.ajax({url:url,method:"post",data:data,success:function(result){
				if(result=='1'){
					$('#message').html("保存成功");
					$("#datagrid").datagrid('reload');
				}
			}});
			
			editOptions = false;
		}
		
		function fileUploadToServer() {
			var fileValue = $('#file').val();
			if (fileValue == '') {
				$('#message').html('请选择一个文件')
				return;
			}
			$('#message').html('正在上传……');
			var url = '${ctx}/fileupload/userAssets?orgId='+$('#id').val;
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
		
		function updateSetting(name,betRate){
			editOptions = true;
			$('#editOptionsBtn').click();
			
			$('#optionName').val(name);
			$('#betRate').val(betRate);
		}
		
		function setRightAnswer(rowId){
			var questionId    = $('#id').val();
			var questionOptionsId = rowId;
			var data = {};
			data.questionId=questionId;
			data.questionOptionsId = questionOptionsId;
			var url = '${ctx}/guessQuestion/releaseAnswer';
			$.ajax({url:url,method:"post",data:data,success:function(result){
				$("#datagrid").datagrid('reload');
			}});
			
		}
		
		//格式化操作，添加删除和编辑按钮。
		function formatOpt(value, rec) {
			var qId = '${guessQuestionAnswer.questionOptionsId}';
			var btn = '<div style="padding: 5px;">';
				
				btn += '<button type="button" class="btn btn-danger btn-sm" onclick="deleteById(\'datagrid\',\''
					+ rec.id + '\',\'guessQuestionOptions\')"><i class="fa fa-trash fa-lg"></i>&nbsp;&nbsp;删除 </button>';
					btn += "&nbsp;&nbsp;";
				btn += '<button type="button" class="btn btn-danger btn-sm" onclick="setRightAnswer(\''
					+ rec.id + '\')"><i class="fa fa-edit fa-lg"></i>&nbsp;&nbsp;设置为正确答案 </button>';
					btn += "&nbsp;&nbsp;";
				//btn += '<button type="button" class="btn btn-info btn-sm" onclick="updateSetting(\''+rec.name+'\',\''+rec.betRate+'\');"><i class="fa fa-edit fa-lg"></i>&nbsp;&nbsp;编辑</button>';
			btn += '</div>';
			if(qId==rec.id){
					btn += '<label style="color:red;">该项已发布为答案</label>&nbsp;&nbsp;';
			}
			return btn;
		}
	</script>
	<%@ include file="/WEB-INF/include/easyui-footerjs.jsp"%>
</body>
</html>