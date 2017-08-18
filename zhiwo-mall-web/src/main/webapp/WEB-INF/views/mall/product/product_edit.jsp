<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>商品编辑</title>
<%@ include file="/WEB-INF/include/easyui-css.jsp"%>
<%@ include file="/WEB-INF/include/easyui-js.jsp"%>
<script type="text/javascript"
	src="${ctx}/js/jquery-easyui/ajaxfileupload.js"></script>
</head>
<body>
	<form class="form-horizontal" role="form" onSubmit="onSubmitHandler();"
		<c:if test="${operation=='edit'}">
 action="${ctx}/product/update"
		</c:if>
		<c:if test="${operation==null}">
 action="${ctx}/product/create"
		</c:if>
		method="post">
        <c:if test="${operation=='edit'}">
        <input id="id" name="id" value="${product.id}" type="hidden"/>
		</c:if>
        <input id="icon" name="icon" value="${product.icon}" type="hidden"/>
        <input id="categoryId" name="categoryId" value="${product.categoryId}" type="hidden"/>
        <div class="form-group">
			<label for="type" class="col-sm-2 control-label">商品分类</label>
            <button type="button" class="btn btn-danger btn-sm"  onClick="$('#treegrid').treegrid('unselectAll');$('#categoryId').val('');">
                清除
            </button>
			<div class="col-sm-4">
				<table id="treegrid" title="商品分类" class="easyui-treegrid"
                    data-options="
                                    url: '${ctx}/prCategory/getPrCategoryTree',
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
                                    	$('#categoryId').val(item.id);
                                    },
                                    onLoadSuccess:function(row,data){
                                    	var ope = '${operation}';
                                        var pid = '${product.categoryId}';
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
			<label for="name" class="col-sm-2 control-label">商品名称</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" id="name" name="name"
					placeholder="请输入商品名称" value="${product.name}">
			</div>
		</div>
		<div class="form-group">
			<label for="code" class="col-sm-2 control-label">商品代码</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" id="code" name="code"
					placeholder="请输入商品代码(商品拼音)" value="${product.code}">
			</div>
		</div>
		<div class="form-group">
			<label for="file" class="col-sm-2 control-label">商品缩略图</label>
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
			<label for="file" class="col-sm-2 control-label"></label>
			<div class="col-sm-4">
				<img id="iconImg" <c:if test="${!empty product.icon}">src="${ctx}/${product.icon}"</c:if>  	class=".img-responsive"
					style="width: 100px;">
			</div>
		</div>
        <div class="form-group">
			<label for="allowDistribution" class="col-sm-2 control-label">是否允许分销</label>
			<div class="col-sm-4">
				<select id="cc" class="easyui-combobox" name="allowDistribution" style="width:200px;">   
                    <option value="true" <c:if test="${allowDistribution}">selected=true</c:if>>是</option> 
                    <option value="false" <c:if test="${!allowDistribution}">selected=true</c:if>>否</option>
                </select> 
			</div>
		</div>
        <div class="form-group">
			<label for="distributionValue" class="col-sm-2 control-label">商品分销让利值(<i class="fa fa-jpy"></i>)</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" id="distributionValue" name="distributionValue"
					placeholder="请输入商品分销让利值" value="${product.distributionValue}">
                <label>如果允许会员分销，销售出去的商品将扣除让利值给会员</label>
			</div>
		</div>
        <div class="form-group">
			<label for="purchasingCost" class="col-sm-2 control-label">进货价(<i class="fa fa-jpy"></i>)</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" id="purchasingCost" name="purchasingCost"
					placeholder="请输入商品进货价" value="${product.purchasingCost}">
                <label>输入商品进货价有利于您统计商品的盈利情况</label>
			</div>
		</div>
         <div class="form-group">
			<label for="gourpSalePrice" class="col-sm-2 control-label">团购价(<i class="fa fa-jpy"></i>)</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" id="gourpSalePrice" name="gourpSalePrice"
					placeholder="请输入商品团购价" value="${product.gourpSalePrice}">
			</div>
		</div>
        <div class="form-group">
			<label for="independentPrice" class="col-sm-2 control-label">单独购买价(<i class="fa fa-jpy"></i>)</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" id="independentPrice" name="independentPrice"
					placeholder="单独购买价" value="${product.independentPrice}">
			</div>
		</div>
        <div class="form-group">
               <label for="propertySetting" class="col-sm-2 control-label">属性设置</label>
               <div class="col-sm-4">
               		<input id="propertySetting" class="easyui-combobox" name="propertySetting"  style="width:200px;"  data-options="valueField:'id',textField:'name',url:'${ctx}/productProperty/listAll'" editable="false" /> 
                    <!--<select id="propertySetting" class="easyui-combobox" name="propertySetting" style="width:200px;">   
                         				<option value="style">款式</option> 
                                        <option value="specification">规格</option>
                                        <option value="size">尺寸</option>
                                        <option value="color">颜色</option>
                                        <option value="capacity">容量</option>
                                        <option value="combo">套餐</option>
                                        <option value="other">其它</option>
                     </select>-->
                     &nbsp;&nbsp;
                     <button type="button" class="btn btn-success fileinput-button" data-toggle="modal" data-target="#propertyModal">
                        <i class="fa fa-plus"></i>&nbsp;&nbsp;新增
                    </button>
                    &nbsp;&nbsp;
                     <button type="button" class="btn btn-success fileinput-button" data-toggle="modal" data-target="#propertyModal">
                        <i class="fa fa-plus"></i>&nbsp;&nbsp;管理
                    </button>
                </div>
        </div>
        <div class="form-group">
			<label for="independentPrice" class="col-sm-2 control-label">属性组合</label>
			<div class="col-sm-4" id="proValueDiv">
			</div>
		</div>
        <div class="form-group">
			<label for="independentPrice" class="col-sm-2 control-label">属性组合价格设定</label>
			<div class="col-sm-4" id="proValuePriceDiv">
				<!--<div class="col-sm-12" id="specificationProValueDiv">
                	<label  class="checkbox-inline">款式：108黑底【男款】</label>
					<label  class="checkbox-inline">尺寸：39</label>
                    <input type="text" class="form-control" placeholder="108黑底【男款】+39组合拼团价(元)">
                    <input type="text" class="form-control" placeholder="108黑底【男款】+39组合单独购买价(元)">
				</div>-->
			</div>
		</div>
		<div class="form-group">
			<label for="file" class="col-sm-2 control-label">商品内容</label>
			<div class="col-sm-9">
				<script id="container" name="content" type="text/plain">${product.content}</script>
			</div>
		</div>
        <!-- 选择属性模态框（Modal） -->
        <div class="modal fade" id="propertyModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                            &times;
                        </button>
                        <h4 class="modal-title" id="myModalLabel">
                            属性设置
                        </h4>
                    </div>
                    <div class="modal-body">
                    	<div class="form-group">
                            <label for="propertyValue" class="col-sm-2 control-label">属性值</label>
                            <div class="col-sm-9">
                               	<input type="text" class="form-control" id="propertyValue" name="propertyValue"
									placeholder="属性值" value="">
                                
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="propertyValue" class="col-sm-2 control-label">是否禁用</label>
                            <div class="col-sm-9">
                               	<select id="propertyValueDisabled" class="easyui-combobox" style="width:200px;">
                                    <option>否</option>
                                    <option>是</option> 
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="propertyValueImg" class="col-sm-2 control-label">属性值图片</label>
                            <div class="col-sm-9">
                            	<img id="propertyValueImg" class=".img-responsive" style="width: 100px;">
                               	<input type="hidden" class="form-control" id="propertyValueImg" name="propertyValueImg"
									placeholder="属性值图片" value=""、>
                                    
                            </div>
                        </div>
                        <div class="form-group">
                            <label  class="col-sm-2 control-label"></label>
                            <div class="col-sm-9">
                            	<label id="messageLabel"></label>                                   
                            </div>
                        </div>
                        <div class="form-group">
                            <label  class="col-sm-2 control-label"></label>
                           	<div class="col-sm-9">
                               	<button type="button" class="btn btn-success" onClick="appendProperty();">新增
                        		</button>
                                &nbsp;&nbsp;
                                    <button type="button" class="btn btn-success fileinput-button">从已上传的图片选择
                                    </button>&nbsp;&nbsp;
                                    <button type="button" class="btn btn-success fileinput-button">选择本地文件
                                    </button>
                            </div>
                        </div>
                    	
                        
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                        </button>
                        <!--<button type="button" class="btn btn-primary">
                            保存
                        </button>-->
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal -->
        </div>
	</form>
	<!-- 配置文件 -->
	<script type="text/javascript"
		src="${ctx}/js/ueditor/ueditor.config.js"></script>
	<!-- 编辑器源码文件 -->
	<script type="text/javascript" src="${ctx}/js/ueditor/ueditor.all.js"></script>
	<!-- 实例化编辑器 -->

	<script type="text/javascript">
		var ue = UE.getEditor('container', {
			autoHeightEnabled : true,
			autoFloatEnabled : true,
			initialFrameHeight : 483
		});
		// 初始化按钮等工作。
		$().ready(function() {
			//返回列表。
			$("#returnBtn").bind("click", function() {
				backToList('product');
			});
		});
		
		//保存属性数组
		var propertyArray = [];
		//保存属性值数组
		//var propertyValueArray = [];
		var pVauleJsonArray = [];
		var priceVauleJsonArray = [];
		//保存属性值组合价数组
		var propertyValuePackPriceArray = [];
		
		// 插入属性值。
		function appendProperty(){
			onSubmitHandler();
			
			//判断属性个数
			if(pVauleJsonArray.length>3){
				$('#messageLabel').html('属性个数不得超过3个');
				return;
			}else{
				$('#messageLabel').html('');
			}
			
			var protext = $('#propertySetting').combobox('getText');
			if(''==protext){
				$('#messageLabel').html('请在下拉框选择属性');
				return;
			}else{
				$('#messageLabel').html('');
			}
			var proValue = $('#propertySetting').combobox('getValue');

			var proValueIndex = $.inArray(proValue,propertyArray);
			if(proValueIndex==-1){
				propertyArray.push(proValue);
			}
			
			propertyValuePackPriceArray = [];
			
			var propertyValueJson = {};
			var date = new Date(); 
			propertyValueJson.propertyId = proValue;
			propertyValueJson.name = $('#propertyValue').val();
			propertyValueJson.id   = date.getTime()+""+Math.round(Math.random()*1000);
							
			//propertyValueArray.push(propertyValueJson);
			var object = {};
			object.id = proValue;
			object.name = protext;
			//
			var index = searchElement(pVauleJsonArray,proValue);
			if(index==-1){
				var propertyValueArray = [];
				propertyValueArray.push(propertyValueJson);
				object.propertyValueArray = propertyValueArray;
				pVauleJsonArray.push(object);
			}else{
				var obj = pVauleJsonArray[index];
				var propertyValueArray = obj.propertyValueArray;
				propertyValueArray.push(propertyValueJson);
				pVauleJsonArray.splice(index,1,obj);
			}
			
			//界面添加控件
			var pro = $('#propertySetting').combobox('getValue');
			pro+='ProValueDiv';
			var proDivs = $('#proValueDiv').find($('#'+pro));
			var proDiv;
			var propertyValueGroup = "";
			var pValue =  $('#propertyValue').val();
			var labelCheckBox = '<label class="checkbox-inline">'+pValue+'</label>';
			if(proDivs.length == 0){
				var label = '<label class="checkbox-inline">'+protext+':</label>';
				propertyValueGroup = '<div class="col-sm-12" id="'+pro+'">'+label+labelCheckBox+'</div>';
				$('#proValueDiv').append(propertyValueGroup);
			}else{
				proDiv = proDivs[0];
				$(proDiv).append(labelCheckBox);
			}
						
			$('#proValuePriceDiv').empty();
			
			
			for(var i=0;i<pVauleJsonArray.length;i++){
				var o1 = pVauleJsonArray[i];
				var array1 = o1.propertyValueArray;
				var para = '';
				for(var k=0;k<array1.length;k++){
					var pVJson1 = array1[k];
					para = '<div class="col-sm-12" ><label  class="checkbox-inline">'+o1.name+'：'+pVJson1.name+'</label><input  id="'+pVJson1.id+'_GroupInput" type="text" class="form-control" placeholder="'+pVJson1.name+'拼团价(元)"><input id="'+pVJson1.id+'_IndependInput" type="text" class="form-control" placeholder="'+pVJson1.name+'单独购买价(元)"></div>';
					
					
					
					if(pVauleJsonArray.length==1){
						$('#proValuePriceDiv').append(para);
						//设置属性
						var propertyValuePriceJson1 = {};
						propertyValuePriceJson1.propertyValueId = pVJson1.id;
						propertyValuePackPriceArray.push(propertyValuePriceJson1);
					}
					
					for(var j=1;j<pVauleJsonArray.length;j++){
						var o2 = pVauleJsonArray[j];
						if(o1.id==o2.id){
							continue;
						}
						var array2 = o2.propertyValueArray;
						for(var p=0;p<array2.length;p++){
							var pVJson2 = array2[p];
							
							para = '<div class="col-sm-12" ><label  class="checkbox-inline">'+o1.name+'：'+pVJson1.name+'</label><label class="checkbox-inline">'+o2.name+'：'+pVJson2.name+'</label><input id="'+pVJson1.id+'_'+pVJson2.id+'_GroupInput" type="text" class="form-control" placeholder="'+pVJson1.name+'和'+pVJson2.name+'组合拼团价(元)"><input id="'+pVJson1.id+'_'+pVJson2.id+'_IndependInput"  type="text" class="form-control" placeholder="'+pVJson1.name+'和'+pVJson2.name+'组合单独购买价(元)"></div>';
							console.log(para);
							if(pVauleJsonArray.length==2){
								$('#proValuePriceDiv').append(para);
								
								//设置属性
								var propertyValuePriceJson2 = {};
								propertyValuePriceJson2.propertyValueId = pVJson1.id+"_"+pVJson2.id;
								propertyValuePackPriceArray.push(propertyValuePriceJson2);
							}
							
							
							for(j=1;j<pVauleJsonArray.length;j++){
								var o3 = pVauleJsonArray[j];
								if(o1.id==o3.id || o2.id==o3.id ){
									continue;
								}
								
								var array3 = o3.propertyValueArray;
								for(p=0;p<array3.length;p++){
									var pVJson3 = array3[p];
									para = '<div class="col-sm-12" ><label  class="checkbox-inline">'+o1.name+'：'+pVJson1.name+'</label><label class="checkbox-inline">'+o2.name+'：'+pVJson2.name+'</label><label class="checkbox-inline">'+o3.name+'：'+pVJson3.name+'</label><input id="'+pVJson1.id+'_'+pVJson2.id+'_'+pVJson3.id+'_GroupInput" type="text" class="form-control" placeholder="'+pVJson1.name+'和'+pVJson2.name+'和'+pVJson3.name+'组合拼团价(元)"><input id="'+pVJson1.id+'_'+pVJson2.id+'_'+pVJson3.id+'_IndependInput"  type="text" class="form-control" placeholder="'+pVJson1.name+'和'+pVJson2.name+'和'+pVJson3.name+'组合单独购买价(元)"></div>';
									console.log(para);
									if(pVauleJsonArray.length==3){
										$('#proValuePriceDiv').append(para);
										
										var propertyValuePriceJson3 = {};
										propertyValuePriceJson3.propertyValueId = pVJson1.id+"_"+pVJson2.id+"_"+pVJson3.id;
										propertyValuePackPriceArray.push(propertyValuePriceJson3);
									}
								}
							}
						}
					}
				}
				
				
			}
			
			//propertyValueGroup = '<div class="col-sm-9" id="specificationProValueDiv"><label class="checkbox-inline">规格</label><label class="checkbox-inline"><input type="checkbox" id="inlineCheckbox1" value="option1"> 选项1</label><label class="checkbox-inline"><input type="checkbox" id="inlineCheckbox2" value="option1"> 选项2</label><label class="checkbox-inline"><input type="checkbox" id="inlineCheckbox3" value="option1"> 选项3</label></div>';
			//$('#proValueDiv').append(propertyValueGroup);
		}
		
		//查找属性值对象。
		function searchElement(array,value){
			var flag = -1;
			for(var i=0;i<array.length;i++){
				var obj = array[i];
				if(obj.id == value){
					flag=i;
					break;
				}
			}
			
			return flag;
		}
		
		function onSubmitHandler(){
			var s = JSON.stringify(pVauleJsonArray);
			//alert(s);
			var length = pVauleJsonArray.length;
			var inputs = $('#proValuePriceDiv').find('input');
			for(var i=0;i<inputs.length;i++){
				var obj = inputs[i];
				
				//alert($(obj).attr('id')+"------"+$(obj).val());
			}
			
			priceVauleJsonArray = [];
			for(var j=0;j<propertyValuePackPriceArray.length;j++){
				var object = propertyValuePackPriceArray[j];
				
				var priceJson1 = {};
				priceJson1.groupPrice = $('#'+object.propertyValueId+"_GroupInput").val();
				priceJson1.indepentPrice = $('#'+object.propertyValueId+"_IndependInput").val();
				priceJson1.propertyValueId = object.propertyValueId;
				priceVauleJsonArray.push(priceJson1);
				console.log($('#'+object.propertyValueId+"_IndependInput").val()+''+$('#'+object.propertyValueId+"_GroupInput").val());
			}
		}
		
		//上传到服务器。
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