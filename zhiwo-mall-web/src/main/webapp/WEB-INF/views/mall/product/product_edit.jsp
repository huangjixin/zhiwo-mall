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
<script type="text/javascript"
	src="https://cdn.bootcss.com/jquery-jcrop/2.0.4/js/Jcrop.min.js"></script>
<style>
.swiper-container {
	width: 400px;
	height: auto;
	margin: 20px auto;
}

.swiper-slide {
	text-align: center;
	font-size: 18px;
	background: #ccc;
	/* Center slide text vertically */
	display: -webkit-box;
	display: -ms-flexbox;
	display: -webkit-flex;
	display: flex;
	-webkit-box-pack: center;
	-ms-flex-pack: center;
	-webkit-justify-content: center;
	justify-content: center;
	-webkit-box-align: center;
	-ms-flex-align: center;
	-webkit-align-items: center;
	align-items: center;
}
</style>
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

		</c:if>
		<input id="id" name="id" value="${product.id}" type="hidden" /> <input
			id="propertyValues" name="propertyValues" value="${propertyValues}"
			type="hidden" /> <input id="propertyPrices" name="propertyPrices"
			value="${propertyPrices}" type="hidden" /> <input id="icon"
			name="icon" value="${product.icon}" type="hidden" /> <input
			id="categoryId" name="categoryId" value="${product.categoryId}"
			type="hidden" />
		<div class="form-group">
			<label for="type" class="col-sm-2 control-label">商品分类</label>
			<button type="button" class="btn btn-danger btn-sm"
				onClick="$('#treegrid').treegrid('unselectAll');$('#categoryId').val('');">
				清除</button>
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
					placeholder="请输入商品名称,开头关键字请用【】括号，否则审核不通过" value="${product.name}">
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
			<label for="description" class="col-sm-2 control-label">描述</label>
			<div class="col-sm-4">
				<textarea name="description" class="form-control" rows="8">${product.description}</textarea>
			</div>
		</div>
		<div class="form-group">
			<label for="file" class="col-sm-2 control-label">商品缩略图</label>
			<div class="col-sm-4">
				<input type="file" id="file" name="file" style="display: none;"
					accept="image/*"
					onChange="$('#message').html($('#file').val());preImg(this.id,'iconImg');" />
				<button type="button" class="btn btn-success fileinput-button"
					onclick="$('#file').click();">
					<i class="fa fa-plus"></i>&nbsp;&nbsp;选择文件
				</button>
				<button type="button" class="btn btn-primary start"
					onclick="fileUploadToServer();">
					<i class="fa fa-upload"></i> <span>&nbsp;&nbsp;开始上传</span>
				</button>
				<button type="button" class="btn btn-success fileinput-button"
					onClick="window.open('${ctx}/js/webpzhaunhuan.zip');">下载图片压缩工具</button>
				<label id="message">${message}</label>
			</div>
		</div>
		<div class="form-group">
			<label for="file" class="col-sm-2 control-label"></label>
			<div class="col-sm-4">
				<label style="color: red;">缩略图要求图片长宽比例为2:1，用工具将图片压缩成webp格式。(预览)</label>
				<img id="iconImg"
					<c:if test="${!empty product.icon}">src="${ctx}/${product.icon}"</c:if>
					class=".img-responsive" width="200px;" height="100px;">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">设置商品轮播图</label>

			<div class="col-sm-4">
				<input type="file" id="swiperFile" name="file"
					style="display: none;" accept="image/*"
					onChange="$('#swiperMessage').html($('#swiperFile').val());preImg(this.id,'swiperImg');" />
				<button type="button" class="btn btn-success fileinput-button"
					onclick="$('#swiperFile').click();">
					<i class="fa fa-plus"></i>&nbsp;&nbsp;选择文件
				</button>
				<button type="button" class="btn btn-primary start"
					onclick="uploadSwiperImageTOServer();">
					<i class="fa fa-upload"></i> <span>&nbsp;&nbsp;开始上传</span>
				</button>
				<button type="button" class="btn btn-primary start"
					onclick="uploadSwiperImageTOServer();">从已上传的图片当中选择</button>
				<br> <label id="swiperMessage"></label> <label
					style="color: red;">轮播图要求图片长宽比例为2:1，用工具将图片压缩成webp格式。(预览)</label> <img
					id="swiperImg" class=".img-responsive" width="200px;"
					height="100px;">
				<!-- Swiper -->
				<div class="swiper-container">
					<div class="swiper-wrapper" id="proImagesWrapper">
                    	<c:forEach var="swiperImage" items="${swiperImages}">
                        	<div class="swiper-slide" id="${swiperImage.id}swiper-slide">
                            	<div>
                                	<img id="${swiperImage.id}" class=".img-responsive" src="${ctx}/${swiperImage.url}" width="400px;">
                               		<button type="button" class="btn btn-danger btn-sm" onClick="deleteSwiperImage('${swiperImage.id}')">删除</button>
                                </div>
							</div>
                        </c:forEach>
					</div>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label for="allowDistribution" class="col-sm-2 control-label">是否允许分销</label>
			<div class="col-sm-4">
				<select id="allowDistribution" class="easyui-combobox"
					name="allowDistribution" style="width: 200px;">
					<option value="false"
						<c:if test="${!product.allowDistribution}">selected=true</c:if>>否</option>
					<option value="true"
						<c:if test="${product.allowDistribution}">selected=true</c:if>>是</option>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label for="distributionValue" class="col-sm-2 control-label">商品分销让利值(<i
				class="fa fa-jpy"></i>)
			</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" id="distributionValue"
					name="distributionValue" placeholder="请输入商品分销让利值(元)"
					value="${product.distributionValue}"> <label>如果允许会员分销，销售出去的商品将扣除让利值给会员</label>
			</div>
		</div>
		<div class="form-group">
			<label for="purchasingCost" class="col-sm-2 control-label">进货价(<i
				class="fa fa-jpy"></i>)
			</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" id="purchasingCost"
					name="purchasingCost" placeholder="请输入商品进货价(元)"
					value="${product.purchasingCost}"> <label>输入商品进货价有利于您统计商品的盈利情况</label>
			</div>
		</div>
        <div class="form-group">
			<label for="numberCount" class="col-sm-2 control-label">开团人数(<i
				class="fa fa-jpy"></i>)
			</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" id="numberCount"
					name="numberCount" placeholder="请输入开团人数"
					value="${product.numberCount}">
			</div>
		</div>
		<div class="form-group">
			<label for="gourpSalePrice" class="col-sm-2 control-label">团购价(<i
				class="fa fa-jpy"></i>)
			</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" id="gourpSalePrice"
					name="gourpSalePrice" placeholder="请输入商品团购价(元)"
					value="${product.gourpSalePrice}">
			</div>
		</div>
		<div class="form-group">
			<label for="independentPrice" class="col-sm-2 control-label">单独购买价(<i
				class="fa fa-jpy"></i>)
			</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" id="independentPrice"
					name="independentPrice" placeholder="单独购买价(元)"
					value="${product.independentPrice}">
			</div>
		</div>
		<div class="form-group">
			<label for="propertySetting" class="col-sm-2 control-label">属性设置</label>
			<div class="col-sm-4">
				<input id="propertySetting" class="easyui-combobox"
					name="propertySetting" style="width: 200px;"
					data-options="valueField:'id',textField:'name',url:'${ctx}/productProperty/listAll',onLoadSuccess: function () { 
 var data = $('#propertySetting').combobox('getData');
             if (data.length > 0) {
                 $('#propertySetting').combobox('select', data[0].id);
             } 
}"
					editable="false" />
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
				<button id="addPropertyBtn" onClick="setBtnStatus(false,true);"
					type="button" class="btn btn-success fileinput-button"
					data-toggle="modal" data-target="#propertyModal">
					<i class="fa fa-plus"></i>&nbsp;&nbsp;新增
				</button>
			</div>
		</div>
		<div class="form-group">
			<label for="independentPrice" class="col-sm-2 control-label">属性组合</label>
			<div class="col-sm-4" id="proValueDiv">

				<c:forEach var="property" items="${properties}" varStatus="status">
					<div class="col-sm-12" id="${property.id}">
						<label class="checkbox-inline" id="${property.id}">${property.name}:</label>
						<c:forEach var="pValue" items="${propertyValues}"
							varStatus="pValueStatus">
							<c:if test="${property.id==pValue.propertyId}">

								<label class="checkbox-inline" id="${pValue.id}">${pValue.name}<label
									style="color: red;"
									onClick="removePropertyValue('${pValue.id}')">(删除)</label><label
									style="color: red;"
									onClick="modifyPropertyValue('${pValue.id}')">(修改)</label></label>


							</c:if>
						</c:forEach>
					</div>
				</c:forEach>
			</div>
		</div>
		<div class="form-group">
			<label for="independentPrice" class="col-sm-2 control-label">属性组合价格设定(元)</label>
			<div class="col-sm-4" id="proValuePriceDiv">
				<c:forEach var="packagePrice" items="${packagePrices}"
					varStatus="packagePriceStatus">
					<div class="col-sm-12" id="${packagePrice.propertyValueId}">

						<c:set value="${fn:split(packagePrice.propertyValueId, '_') }"
							var="proValueIds" />
						<c:forEach var="proValueId" items="${proValueIds}">
							<c:forEach var="propertyValue" items="${propertyValues}">
								<c:if test="${proValueId==propertyValue.id}">
									<c:forEach var="property" items="${properties}">
										<c:if test="${propertyValue.propertyId==property.id}">
											<label class="checkbox-inline">${property.name}：${propertyValue.name}</label>
										</c:if>
									</c:forEach>

								</c:if>
							</c:forEach>
						</c:forEach>
						<input type="text" id="${packagePrice.propertyValueId}_GroupInput"
							value="${packagePrice.gourpPrice}" class="form-control"
							placeholder="组合拼团价(元)" onBlur="priceCallback()"> <input
							type="text" id="${packagePrice.propertyValueId}_IndependInput"
							value="${packagePrice.independentPrice}" class="form-control"
							placeholder="单独购买价(元)" onBlur="priceCallback()">

					</div>
				</c:forEach>
				<!--<div class="col-sm-12" id="specificationProValueDiv">
                	<label  class="checkbox-inline">款式：108黑底【男款】</label>
					<label  class="checkbox-inline">尺寸：39</label>
                    <input type="text" class="form-control" placeholder="108黑底【男款】+39组合拼团价(元)">
                    <input type="text" class="form-control" placeholder="108黑底【男款】+39组合单独购买价(元)">
				</div>-->
			</div>

		</div>
		<div class="form-group">
			<label for="independentPrice" class="col-sm-2 control-label"></label>
			<div class="col-sm-4">
				<%@ include file="/WEB-INF/include/easyui-buttonForm.jsp"%>

			</div>
		</div>
		<div class="form-group">
			<label for="file" class="col-sm-2 control-label">商品内容</label>
			<div class="col-sm-9">
				<script id="container" name="content" type="text/plain">${product.content}</script>
			</div>
		</div>
		<!-- 选择属性模态框（Modal） -->
		<div class="modal fade" id="propertyModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">属性设置</h4>
					</div>
					<div class="modal-body">
						<input type="hidden" id="pValueId" />
						<div class="form-group">
							<label for="propertyValue" class="col-sm-2 control-label">属性值</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" id="propertyValue"
									name="propertyValue" placeholder="属性值" value="">

							</div>
						</div>
						<div class="form-group">
							<label for="propertyValue" class="col-sm-2 control-label">是否禁用</label>
							<div class="col-sm-9">
								<select id="propertyValueDisabled" class="easyui-combobox"
									style="width: 200px;">
									<option value="false">否</option>
									<option value="true">是</option>
								</select>
							</div>
						</div>

						<div class="form-group">
							<label for="propertyValueImg" class="col-sm-2 control-label">属性值图片</label>
							<div class="col-sm-9">
								<img id="propertyValueImg" class=".img-responsive"
									style="width: 100px;"> <input type="hidden"
									id="propertyValueImg" name="propertyValueImg" value="">
								<input type="hidden" id="selectedPropertyValueImg"
									name="propertyValueImg" value="">
								<div class="row" id="propertyValueImgRow">
									<c:forEach var="prImage" items="${prImages}">
										<div class="col-sm-3 col-md-3" id="${prImage.id}div">
											<a href="#" class="thumbnail"> <img id="${prImage.id}"
												src="${ctx}/${prImage.url}" class="img-responsive"
												width="100px" height="100px"
												onClick="$('#propertyValueImg').val('${prImage.url}');$('#selectedPropertyValueImg').val('${prImage.id}')"><label
												style="color: red;"
												onClick="deleteProvalueImage('${prImage.id}')">删除</label>
											</a>
										</div>
									</c:forEach>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label"></label>
							<div class="col-sm-9">
								<label id="messageLabel"></label>
							</div>
						</div>


						<div class="form-group">
							<label class="col-sm-2 control-label"></label>
							<div class="col-sm-9">
								<input type="file" id="prImageFile" name="file"
									style="display: none;" multiple="multiple" accept="image/*"
									onChange="$('#proImageMessage').html($('#prImageFile').val());preImg(this.id,'preProvalueImg');" />

								<button type="button" class="btn btn-success btn-sm"
									onclick="$('#prImageFile').click();">选择本地文件</button>
								<button type="button" class="btn btn-primary btn-sm"
									onClick="uploadProImageTOServer();">
									<i class="fa fa-upload"></i> <span>&nbsp;&nbsp;开始上传</span>
								</button>
								<button type="button" class="btn btn-danger btn-sm">
									<i class="fa fa-trash fa-lg"></i>&nbsp;&nbsp;删除
								</button>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label"></label>
							<div class="col-sm-9">
								<label id="proImageMessage">${proImageMessage}</label>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label"></label>
							<div class="col-sm-9">
								<img id="preProvalueImg" class="img-responsive" width="100px"
									height="100px">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label"></label>
							<div class="col-sm-9">
								<button id="addAppendPropertyBtn" type="button"
									class="btn btn-success" onClick="appendProperty();">新增
								</button>
								&nbsp;&nbsp;
								<button id="modifyAppendPropertyBtn" type="button"
									class="btn btn-success" onClick="updateData();">修改</button>
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
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal -->

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

		var propertiesString = '${propertiesString}';

		var propertyValuesString = '${propertyValuesString}';

		var packagePricesString = '${packagePricesString}';

		//保存属性数组
		var propertyArray = [];

		//保存属性值数组
		var proArray = [];
		//保存属性值数组
		var propertyValueArray = [];
		//价格数组
		var pricesArray = [];

		// 初始化按钮等工作。
		$().ready(function() {
			//返回列表。
			$("#returnBtn").bind("click", function() {
				backToList('product');
			});

			if (propertiesString != '' && propertiesString != '[]') {
				proArray = JSON.parse('${propertiesString}');
			}

			if (packagePricesString != '' && packagePricesString != '[]') {
				pricesArray = JSON.parse('${packagePricesString}');
			}

			if (propertyValuesString != '' && propertyValuesString != '[]') {
				propertyValueArray = JSON.parse('${propertyValuesString}');
			}

			for (var i = 0; i < propertyValueArray.length; i++) {
				var proValue = propertyValueArray[i].propertyId;
				var proValueIndex = $.inArray(proValue, propertyArray);
				if (proValueIndex == -1) {
					propertyArray.push(proValue);
				}
			}

		});

		//回填价格函数
		function priceCallback() {
			var maxGroupPrice = 0;
			var minGroupPrice = 0;
			var maxIndepentPrice = 0;
			var minIndepentPrice = 0;
			if (pricesArray.length > 0) {
				var object = pricesArray[0];
				minGroupPrice = $('#' + object.propertyValueId + "_GroupInput")
						.val() == '' ? 0 : $(
						'#' + object.propertyValueId + "_GroupInput").val();
				minIndepentPrice = $(
						'#' + object.propertyValueId + "_IndependInput").val() == '' ? 0
						: $('#' + object.propertyValueId + "_IndependInput")
								.val();

			}
			for (var j = 1; j < pricesArray.length; j++) {
				var object = pricesArray[j];
				var groupPrice1 = $(
						'#' + object.propertyValueId + "_GroupInput").val();
				var indepentPrice1 = $(
						'#' + object.propertyValueId + "_IndependInput").val();
				if (groupPrice1 != '') {
					minGroupPrice = groupPrice1 < minGroupPrice ? groupPrice1
							: minGroupPrice;
				}
				if (indepentPrice1 != '') {
					minIndepentPrice = indepentPrice1 < minIndepentPrice ? indepentPrice1
							: minIndepentPrice;
				}

			}

			$('#gourpSalePrice').val(minGroupPrice);
			$('#independentPrice').val(minIndepentPrice);
		}

		// 移除属性值。
		function removePropertyValue(pValueId) {
			$('#' + pValueId).remove();
			var flag = -1;
			for (var i = 0; i < propertyValueArray.length; i++) {
				if (pValueId == propertyValueArray[i].id) {
					flag = i;
					break;
				}
			}
			if (flag != -1) {
				var num = 0;
				for (i = 0; i < propertyValueArray.length; i++) {
					if (propertyValueArray[i].id == pValueId) {
						num += 1;
					}
				}
				if (num == 1) {
					var pId = propertyValueArray[flag].propertyId;
					var posi = propertyArray.indexOf(pId);
					if (posi != -1) {
						propertyArray.splice(posi, 1);
					}
				}

				if (propertyValueArray.length == 1) {
					propertyValueArray = [];
				} else
					propertyValueArray.splice(flag, 1);
			}

			var tempArray = [];
			for (i = 0; i < pricesArray.length; i++) {
				var price = pricesArray[i];
				var propertyValueId = price.propertyValueId;
				var index = propertyValueId.indexOf(pValueId);
				if (index != -1) {
					tempArray.push(pricesArray[i]);
				}
			}

			for (i = 0; i < tempArray.length; i++) {
				var price = tempArray[i];
				var index = $.inArray(price, pricesArray);
				if (index != -1) {
					if (pricesArray.length == 1) {
						var price = pricesArray[i];
						var propertyValueId = price.propertyValueId;
						index = propertyValueId.indexOf(pValueId);
						if (index != -1) {
							if (pValueId.length == propertyValueId.length) {
								pricesArray = [];
							} else {
								propertyValueId = propertyValueId.replace(
										pValueId, '');
								index = propertyValueId.indexOf('__');
								if (index != -1) {
									propertyValueId = propertyValueId.replace(
											'__', '_');
								}

								if (propertyValueId.indexOf('_') == 0) {
									propertyValueId = propertyValueId
											.substring(1);
								}
								if (propertyValueId.lastIndexOf('_') == propertyValueId.length - 1) {
									propertyValueId = propertyValueId.substr(0,
											propertyValueId.length - 1);
								}
							}
						}

					} else {
						pricesArray.splice(index, 1);
					}
				}
			}

			//刷新界面。
			//refreshComponent();
			refreshComponent1();
		}

		function setBtnStatus(addAppendPropertyBtnStatus,
				modifyAppendPropertyBtnStatus) {
			$('#addAppendPropertyBtn').attr("disabled",
					addAppendPropertyBtnStatus);
			$('#modifyAppendPropertyBtn').attr("disabled",
					modifyAppendPropertyBtnStatus);
		}

		//修改属性值。
		function modifyPropertyValue(pValueId) {
			$('#addPropertyBtn').click();
			setBtnStatus(true, false);

			var flag = -1;
			var properValue;
			for (var i = 0; i < propertyValueArray.length; i++) {
				if (pValueId == propertyValueArray[i].id) {
					flag = i;
					break;
				}
			}
			if (flag != -1) {
				properValue = propertyValueArray[flag];
				$('#pValueId').val(properValue.id);
				$('#propertyValue').val(properValue.name);
			}
		}

		function updateData() {
			//找寻数组值元素。
			var flag = -1;
			var properValue;
			var pValueId = $('#pValueId').val();
			for (var i = 0; i < propertyValueArray.length; i++) {
				if (pValueId == propertyValueArray[i].id) {
					flag = i;
					break;
				}
			}
			if (flag != -1) {
				properValue = propertyValueArray[flag];

				if (properValue) {
					properValue.disable = $('#propertyValueDisabled').combobox(
							'getValue');
					properValue.name = $('#propertyValue').val();
					properValue.imageId = $('#propertyValueImg').val();

					//propertyValueArray[flag] = properValue;

					for (var j = 0; j < pricesArray.length; j++) {
						var object = pricesArray[j];

						object.groupPrice = $(
								'#' + object.propertyValueId + "_GroupInput")
								.val();
						object.indepentPrice = $(
								'#' + object.propertyValueId + "_IndependInput")
								.val();
						object.propertyValueId = object.propertyValueId;
						object.productId = $('#id').val();
					}

					refreshComponent();
					refreshComponent1();
					for (j = 0; j < pricesArray.length; j++) {
						var object = pricesArray[j];
						$('#' + object.propertyValueId + "_GroupInput").val(
								object.groupPrice);
						$('#' + object.propertyValueId + "_IndependInput").val(
								object.indepentPrice);
					}

					$('#prImagemessage').html('修改属性成功');
				}
			}

		}

		// 插入属性值。
		function appendProperty() {

			//判断属性个数
			if (propertyArray.length > 3) {
				$('#messageLabel').html('属性个数不得超过3个');
				return;
			} else {
				$('#messageLabel').html('');
			}

			var protext = $('#propertySetting').combobox('getText');
			if ('' == protext) {
				$('#messageLabel').html('请在下拉框选择属性');
				return;
			} else {
				$('#messageLabel').html('');
			}
			var proValue = $('#propertySetting').combobox('getValue');
			var proValueIndex = $.inArray(proValue, propertyArray);
			if (proValueIndex == -1) {
				propertyArray.push(proValue);
			}

			var propertyValueJson = {};

			propertyValueJson.disable = $('#propertyValueDisabled').combobox(
					'getValue');
			propertyValueJson.propertyId = proValue;
			propertyValueJson.name = $('#propertyValue').val();
			propertyValueJson.id = new Date().getTime() + ""
					+ Math.round(Math.random() * 100);
			propertyValueJson.imageId = $('#propertyValueImg').val();
			propertyValueJson.productId = $('#id').val();

			propertyValueArray.push(propertyValueJson);

			pricesArray = [];
			for (var i = 0; i < propertyValueArray.length; i++) {
				//只有一种属性
				if (propertyArray.length == 1) {
					var price1 = {};
					price1.gourpPrice = "";
					price1.independentPrice = "";
					price1.id = new Date().getTime() + ""
							+ Math.round(Math.random() * 100);
					price1.productId = $('#id').val();
					price1.propertyValueId = propertyValueArray[i].id;

					pricesArray.push(price1);

				}
				for (var j = i + 1; j < propertyValueArray.length; j++) {
					//两种属性
					if (propertyArray.length == 2) {
						if (propertyValueArray[i].propertyId != propertyValueArray[j].propertyId) {
							var price2 = {};
							price2.gourpPrice = "";
							price2.independentPrice = "";
							price2.id = new Date().getTime() + ""
									+ Math.round(Math.random() * 100);
							price2.productId = $('#id').val();
							price2.propertyValueId = propertyValueArray[i].id
									+ "_" + propertyValueArray[j].id;

							pricesArray.push(price2);
						}
					}
					for (var p = j + 1; p < propertyValueArray.length; p++) {
						//三种属性
						if (propertyArray.length == 3) {
							if (propertyValueArray[i].propertyId != propertyValueArray[j].propertyId
									&& propertyValueArray[i].propertyId != propertyValueArray[p].propertyId
									&& propertyValueArray[j].propertyId != propertyValueArray[p].propertyId) {
								var price3 = {};
								price3.gourpPrice = "";
								price3.independentPrice = "";
								price3.id = (new Date().getTime() + "" + Math
										.round(Math.random() * 100));
								price3.productId = $('#id').val();
								price3.propertyValueId = propertyValueArray[i].id
										+ "_"
										+ propertyValueArray[j].id
										+ "_"
										+ propertyValueArray[p].id;

								pricesArray.push(price3);
							}
						}
					}
				}

			}

			refreshComponent();
			refreshComponent1();
			//清空属性值文本框。
			$('#propertyValue').val('');
			$('#prImagemessage').html('新增属性成功');
		}

		//刷新界面控件。
		function refreshComponent() {
			for (var i = 0; i < proArray.length; i++) {
				var pId = proArray[i].id;
				//先清空。
				$('#' + pId).empty();
				var parm = '';
				var label = '<label class="checkbox-inline" id="'+proArray[i].id+'">'
						+ proArray[i].name + ':</label>';
				parm += label;
				for (var j = 0; j < propertyValueArray.length; j++) {
					var propertyValue = propertyValueArray[j];
					var proId = propertyValue.propertyId;
					if (pId == proId) {
						var labelCheckBox = '<label class="checkbox-inline" id="'+propertyValueArray[j].id+'">'
								+ propertyValueArray[j].name
								+ '<label  style="color:red;" onClick="removePropertyValue(\''
								+ propertyValue.id
								+ '\')">(删除)</label><label  style="color:red;" onClick="modifyPropertyValue(\''
								+ propertyValue.id
								+ '\')">(修改)</label></label>';
						parm += labelCheckBox;
					}
				}
				parm += '';
				$('#' + pId).append(parm);
			}
		}

		function refreshComponent1() {
			$('#proValuePriceDiv').empty();
			for (var i = 0; i < pricesArray.length; i++) {
				var para = '';
				var pName = '';
				var price = pricesArray[i];
				var properValueIds = price.propertyValueId.split('_');

				for (var j = 0; j < properValueIds.length; j++) {
					var pValue = properValueIds[j];
					for (var p = 0; p < propertyValueArray.length; p++) {
						var pvalueId = propertyValueArray[p].id;
						if (pValue == pvalueId) {
							for (var k = 0; k < proArray.length; k++) {
								if (propertyValueArray[p].propertyId == proArray[k].id) {
									para += '<label  class="checkbox-inline">'
											+ proArray[k].name + '：'
											+ propertyValueArray[p].name
											+ '</label>';
									pName += proArray[k].name + ":"
											+ propertyValueArray[p].name + " ";
								}
							}
							//var para=' <input id="'+price.propertyValueId+'_GroupInput" type="text" class="form-control" placeholder="合拼团价(元)"><input  id="'+price.propertyValueId+'_IndependInput" type="text" class="form-control" placeholder="合单独购买价(元)">';

						}
					}
				}

				if (propertyValueArray.length > 0) {
					para += '<input id="'
							+ price.propertyValueId
							+ '_GroupInput" type="text" class="form-control" placeholder="'
							+ pName
							+ '拼团价(元)"  onBlur="priceCallback()"><input  id="'
							+ price.propertyValueId
							+ '_IndependInput" type="text" class="form-control" placeholder="'
							+ pName + '单独购买价(元)"  onBlur="priceCallback()">';
					$('#proValuePriceDiv').append(para);
				}

			}
		}

		function onSubmitHandler() {
			var s = JSON.stringify(propertyValueArray);
			$('#propertyValues').val(s);

			for (var j = 0; j < pricesArray.length; j++) {
				var object = pricesArray[j];

				object.groupPrice = $(
						'#' + object.propertyValueId + "_GroupInput").val();
				object.indepentPrice = $(
						'#' + object.propertyValueId + "_IndependInput").val();
				object.propertyValueId = object.propertyValueId;
				object.productId = $('#id').val();
			}

			var priceString = JSON.stringify(pricesArray);
			$('#propertyPrices').val(priceString);
		}

		//上传到服务器。
		function fileUploadToServer() {
			var fileValue = $('#file').val();
			if (fileValue == '') {
				$('#message').html('请选择一个文件')
				return;
			}
			$('#message').html('正在上传……');
			var url = '${ctx}/fileupload/prAssets?productId=' + $('#id').val();
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

		//上传产品属性值图片
		function uploadProImageTOServer() {
			var fileValue = $('#prImageFile').val();
			if (fileValue == '') {
				$('#prImagemessage').html('请选择一个文件')
				return;
			}
			$('#prImagemessage').html('正在上传……');
			var url = '${ctx}/fileupload/prAssets?productId=' + $('#id').val();
			$
					.ajaxFileUpload({
						url : url, //用于文件上传的服务器端请求地址
						secureuri : false, //是否需要安全协议，一般设置为false
						fileElementId : 'prImageFile', //文件上传域的ID
						dataType : 'json', //返回值类型 一般设置为json
						success : function(data, status) //服务器成功响应处理函数
						{
							if (data.assets.length > 0) {
								var para = '<div class="col-sm-3 col-md-3"><a href="#" class="thumbnail"><img id="${prImage.id}" src="${ctx}/${prImage.url}"  class="img-responsive" width="100"></a></div>';
								for (var i = 0; i < data.assets.length; i++) {
									var assets = data.assets[i];
									para = '<div class="col-sm-3 col-md-3"><a href="#" class="thumbnail"><img id="'
											+ assets.id
											+ '" src="${ctx}/'
											+ assets.url
											+ '"  class="img-responsive" width="100"  onClick="$(\'#propertyValueImg\').val(\''
											+ assets.url
											+ '\');$(\'#selectedPropertyValueImg\').val(\''
											+ assets.id
											+ '\')"><label style="color:red;" onClick="deleteProvalueImage(\''
											+ assets.id
											+ '\')">删除</label></a></div>';
									$('#propertyValueImgRow').append(para);
								}

							}
							$('#prImagemessage').html('');
						},
						error : function(data, status, e)//服务器响应失败处理函数
						{
							$('#prImagemessage').html('上传失败');
							//alert("上传失败");
						}
					})
		}

		//上传产轮播图片
		function uploadSwiperImageTOServer() {
			var fileValue = $('#swiperFile').val();
			if (fileValue == '') {
				$('#swiperMessage').html('请选择一个文件')
				return;
			}
			$('#swiperMessage').html('正在上传……');
			var url = '${ctx}/fileupload/prSwiperAssets?productId=' + $('#id').val();
			$
					.ajaxFileUpload({
						url : url, //用于文件上传的服务器端请求地址
						secureuri : false, //是否需要安全协议，一般设置为false
						fileElementId : 'swiperFile', //文件上传域的ID
						dataType : 'json', //返回值类型 一般设置为json
						success : function(data, status) //服务器成功响应处理函数
						{
							if (data.assets.length > 0) {
								var para = '';
								for (var i = 0; i < data.assets.length; i++) {
									var assets = data.assets[i];
									para = '<div><div class="swiper-slide" id="'+assets.id+'swiper-slide"><img class=".img-responsive" src="${ctx}/'+assets.url+'" width="400px;"><button type="button" class="btn btn-danger btn-sm"  onClick="deleteSwiperImage(\''+assets.id+'\')">删除</button></div></div>';
									$('#proImagesWrapper').append(para);
								}

							}
							$('#swiperMessage').html('');
						},
						error : function(data, status, e)//服务器响应失败处理函数
						{
							$('#swiperMessage').html('上传失败');
							//alert("上传失败");
						}
					})
		}

		//删除图片。
		function deleteProvalueImage(imageId) {
			var url = '${ctx}/prImage/delete?id=' + imageId;
			$.ajax({
				url : url,
				dataType : 'json', //返回值类型 一般设置为json
				success : function(data) //服务器成功响应处理函数
				{
					$('#' + imageId + 'div').remove();
				}
			})
		}
		
		
		//删除轮播图片。
		function deleteSwiperImage(imageId) {
			var url = '${ctx}/prImage/delete?id=' + imageId;
			$.ajax({
				url : url,
				dataType : 'json', //返回值类型 一般设置为json
				success : function(data) //服务器成功响应处理函数
				{
					$('#' + imageId + 'swiper-slide').remove();
				}
			})
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
	</script>
	<%@ include file="/WEB-INF/include/easyui-footerjs.jsp"%>
	<!-- Initialize Swiper -->
	<script>
		var swiper = new Swiper('.swiper-container');
	</script>
</body>
</html>