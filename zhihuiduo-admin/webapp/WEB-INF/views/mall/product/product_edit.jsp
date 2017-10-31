<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>商品编辑</title>
<%@ include file="/WEB-INF/include/easyui-css.jsp"%>
<%@ include file="/WEB-INF/include/easyui-js.jsp"%>
<script type="text/javascript" src="${ctx}/js/jquery-easyui/ajaxfileupload.js"></script>
<!--<script type="text/javascript" src="https://cdn.bootcss.com/jquery-jcrop/2.0.4/js/Jcrop.min.js"></script>-->
<style>
.swiper-container {
	width: 400px;
	height: auto;
	margin: 20px auto;
}

.swiper-slide {
	text-align: center;
	font-size: 18px;
	background: #ffffff;
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

thumbnail.active {
  border-color: #428bca;
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
			id="propertyValues" name="propertyValues" value="" type="hidden" />
		<input id="propertyPrices" name="propertyPrices"
			value="${propertyPrices}" type="hidden" /> <input id="icon"
			name="icon" value="${product.icon}" type="hidden" /> <input
			id="categoryId" name="categoryId" value="${product.categoryId}"
			type="hidden" />
		<div class="form-group">
			<label for="type" class="col-sm-2 control-label">商品分类</label>
			<button type="button" class="btn btn-danger btn-sm"
				onClick="$('#treegrid').treegrid('unselectAll');$('#categoryId').val('');">
				清除</button>
			<div class="col-sm-6">
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
			<div class="col-sm-6">
				<input type="text" class="form-control" id="name" name="name"
					placeholder="请输入商品名称,开头关键字请用【】括号，否则审核不通过" value="${product.name}">
			</div>
		</div>
		<div class="form-group">
			<label for="code" class="col-sm-2 control-label">商品代码</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" id="code" name="code"
					placeholder="请输入商品代码(商品拼音)" value="${product.code}">
			</div>
		</div>
		<div class="form-group">
			<label for="storage" class="col-sm-2 control-label">商品库存</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" id="storage" name="storage"
					placeholder="请输入库存" value="${product.storage}">
			</div>
		</div>
		<div class="form-group">
			<label for="description" class="col-sm-2 control-label">描述</label>
			<div class="col-sm-6">
				<textarea name="description" class="form-control" rows="8">${product.description}</textarea>
			</div>
		</div>
		<div class="form-group">
			<label for="file" class="col-sm-2 control-label">商品缩略图</label>
			<div class="col-sm-6">
				<input type="file" id="file" name="file" style="display: none;"
					accept="image/*"
					onChange="$('#message').html($('#file').val());preImg(this.id,'iconImg');" />
				<button type="button" class="btn btn-success fileinput-button"
					onclick="$('#file').click();">
					<i class="fa fa-plus"></i>&nbsp;&nbsp;选择文件
				</button>
				<button type="button" class="btn btn-primary start"
					onclick="uploadProImages('file','message','thumbnail',function uploadPackagePriceImageHandler(data, status){
            $('#message').html('上传成功');
		});">
					<i class="fa fa-upload"></i> <span>&nbsp;&nbsp;开始上传</span>
				</button>
				<button type="button" class="btn btn-success fileinput-button"
					onClick="window.open('${ctx}/js/webpzhaunhuan.zip');">下载图片压缩工具</button>
                <br>
				<label id="message">${message}</label>
			</div>
		</div>
		<div class="form-group">
			<label for="file" class="col-sm-2 control-label"></label>
			<div class="col-sm-6">
				<label style="color: red;">缩略图要求白底长宽比例2:1且压缩为webp格式。(预览)</label>
				<br> <img id="iconImg"
					<c:if test="${!empty product.icon}">src="${ctx}/${product.icon}"</c:if>
					class=".img-responsive" width="100px;">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">设置商品轮播图</label>

			<div class="col-sm-6">
				<button id="addPropertyBtn" onClick="setBtnStatus(false,true);"
					type="button" class="btn btn-success fileinput-button"
					data-toggle="modal" data-target="#swiperImageModal">
					<i class="fa fa-plus"></i>&nbsp;&nbsp;新增轮播图
				</button>
			</div>
		</div>
		
		<div class="form-group">
			<label for="isSentUnpay" class="col-sm-2 control-label">是否支持货到付款</label>
			<div class="col-sm-6">
				<select id="isSentUnpay" class="form-control"
					name="isSentUnpay" style="width: 200px;">
					<option value="0"
						<c:if test="${product.isSentUnpay=='0'}">selected=true</c:if>>否</option>
					<option value="1"
						<c:if test="${product.isSentUnpay=='1'}">selected=true</c:if>>是</option>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label for="allowDistribution" class="col-sm-2 control-label">是否允许分销</label>
			<div class="col-sm-6">
				<select id="allowDistribution" class="form-control"
					name="allowDistribution" style="width: 200px;">
					<option value="false"
						<c:if test="${!product.allowDistribution}">selected=true</c:if>>否</option>
					<option value="true"
						<c:if test="${product.allowDistribution}">selected=true</c:if>>是</option>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label for="status" class="col-sm-2 control-label">上架/下架</label>
			<div class="col-sm-6">
				<select id="status" class="form-control" name="status"
					style="width: 200px;">
					<option value="online"
						<c:if test="${product.status=='online'}">selected=true</c:if>>上架</option>
					<option value="offline"
						<c:if test="${product.status=='offline'}">selected=true</c:if>>下架</option>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label for="distributionValue" class="col-sm-2 control-label">商品分销让利值(<i
				class="fa fa-jpy"></i>)
			</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" id="distributionValue"
					name="distributionValue" placeholder="请输入商品分销让利值(元)"
					value="${product.distributionValue}"> <label>如果允许会员分销，销售出去的商品将扣除让利值给会员</label>
			</div>
		</div>
		<div class="form-group">
			<label for="purchasingCost" class="col-sm-2 control-label">进货价(<i
				class="fa fa-jpy"></i>)
			</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" id="purchasingCost"
					name="purchasingCost" placeholder="请输入商品进货价(元)"
					value="${product.purchasingCost}"> <label>输入商品进货价有利于您统计商品的盈利情况</label>
			</div>
		</div>
		<div class="form-group">
			<label for="numberCount" class="col-sm-2 control-label">开团人数(<i
				class="fa fa-jpy"></i>)
			</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" id="numberCount"
					name="numberCount" placeholder="请输入开团人数"
					value="${product.numberCount}">
			</div>
		</div>
        <div class="form-group">
			<label for="marketPrice" class="col-sm-2 control-label">市价(<i
				class="fa fa-jpy"></i>)
			</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" id="marketPrice"
					name="marketPrice" placeholder="请输入商品市价(市价为划线价一般比真实售价高)"
					value="${product.marketPrice}">
			</div>
		</div>
        <div class="form-group">
			<label for="transportFee" class="col-sm-2 control-label">运费(<i
				class="fa fa-jpy"></i>)
			</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" id="transportFee"
					name="transportFee" placeholder="请输入运费(0或者不填为包邮)"
					value="${product.transportFee}">
			</div>
		</div>
		<div class="form-group">
			<label for="gourpSalePrice" class="col-sm-2 control-label">团购价(<i
				class="fa fa-jpy"></i>)
			</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" id="gourpSalePrice"
					name="gourpSalePrice" placeholder="请输入商品团购价(元)"
					value="${product.gourpSalePrice}">
			</div>
		</div>
		<div class="form-group">
			<label for="independentPrice" class="col-sm-2 control-label">单独购买价(<i
				class="fa fa-jpy"></i>)
			</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" id="independentPrice"
					name="independentPrice" placeholder="单独购买价(元)"
					value="${product.independentPrice}">
			</div>
		</div>
		<div class="form-group">
			<label for="propertySetting" class="col-sm-2 control-label">属性设置</label>
			<div class="col-sm-6">
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
			<div class="col-sm-6" id="proValueDiv">

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
			<div class="col-sm-6" id="proValuePriceDiv">
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
                       &nbsp;&nbsp;<button id="${packagePrice.propertyValueId}_editBtn"
                       onClick="settingPackagePrice('${packagePrice.propertyValueId}')" type="button" class="btn btn-primary btn-xs">编辑该属性图片</button>
                       &nbsp;&nbsp;<select id="${packagePrice.propertyValueId}_disable" class="easyui-combobox" style="width:80px; display:none;">
                            <option value="0" <c:if test="${packagePrice.disable=='0'}">selected="selected"</c:if>>启用</option>
                            <option value="1" <c:if test="${packagePrice.disable=='1'}">selected="selected"</c:if>>禁用</option>
                        </select>
						<input type="text" id="${packagePrice.propertyValueId}_GroupInput"
							value="${packagePrice.gourpPrice}" class="form-control"
							placeholder="组合拼团价(元)" onBlur="priceCallback()">
                       &nbsp;&nbsp; <input
							type="text" id="${packagePrice.propertyValueId}_IndependInput"
							value="${packagePrice.independentPrice}" class="form-control"
							placeholder="单独购买价(元)" onBlur="priceCallback()">
						<input id="${packagePrice.propertyValueId}_icon" value="${packagePrice.icon}" class="form-control" type="hidden">
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
			<div class="col-sm-6">
				<%@ include file="/WEB-INF/include/easyui-buttonForm.jsp"%>
                <c:if test="${operation=='edit'}">
                	<a href="${ctx}/goodsDetail?goodsId=${product.id}" target="_blank">
                    	<button type="button" id="previewBtn" class="btn btn-default">
                   		 <i class="fa fa-floppy-o" aria-hidden="true"></i>&nbsp;&nbsp;预览
                	</button> 
                    </a>
				</c:if>
				
			</div>
		</div>
        <div class="form-group">
			<label  class="col-sm-2 control-label">商品详情图</label>
			<div class="col-sm-6">
				<input type="file" id="detailFile" name="file" style="display: none;"
					accept="image/*"
					onChange="$('#detailMessage').html($('#detailFile').val());preImg(this.id,'detailImg');" />
				<button type="button" class="btn btn-success fileinput-button"
					onclick="$('#detailFile').click();">
					<i class="fa fa-plus"></i>&nbsp;&nbsp;选择文件
				</button>
				<button type="button" class="btn btn-primary start"
					onclick="uploadProImages('detailFile','detailMessage','detail',function uploadPackagePriceImageHandler(data, status){
            	$('#detailImg').attr('src','');
            	$('#detailMessage').html('上传成功');
                if (data.assets.length > 0) {
                     var para = '';
                     for (var i = 0; i < data.assets.length; i++) {
                         var assets = data.assets[i];
                         var para = '<img id=&quot;'+assets.id+'&quot; class=&quot;img-responsive&quot; src=&quot;${ctx}/'+assets.url+'&quot;/>';
						 ue.execCommand('insertHtml', para);
                     }
                }
		});">
					<i class="fa fa-upload"></i> <span>&nbsp;&nbsp;开始上传</span>
				</button>
				<br><label id="detailMessage"></label>
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

		<!-- 轮播图设置模态框（Modal） -->
		<div class="modal fade" id="swiperImageModal" tabindex="-2"
			role="dialog" aria-labelledby="swiperImageModalLabel"
			aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="swiperImageModalLabel">轮播设置</h4>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<label for="propertyValue" class="col-sm-2 control-label"></label>
							<div class="col-sm-9">
								<input type="file" id="swiperFile" name="file"
									style="display: none;" accept="image/*"
									onChange="$('#swiperMessage').html($('#swiperFile').val());preImg(this.id,'swiperImg');" />
								<button type="button" class="btn btn-success fileinput-button"
									onclick="$('#swiperFile').click();">
									<i class="fa fa-plus"></i>&nbsp;&nbsp;选择文件
								</button>
								<button type="button" class="btn btn-primary start"
									onclick="uploadProImages('swiperFile','swiperMessage','swiper',function uploadPackagePriceImageHandler(data, status){
            $('#swiperImg').attr('src','');
            $('#swiperMessage').html('');
            getSwiperImgs();
		});">
									<i class="fa fa-upload"></i> <span>&nbsp;&nbsp;开始上传</span>
								</button>
								<br> <label id="swiperMessage"></label>
								<br><img id="swiperImg" class=".img-responsive" width="80px;">
                               	<br>
                                <div class="row" id="swiperImageRow">
                                   
                                </div>
							</div>
						</div>

					</div>
					<div class="modal-footer">
                    	<input id="swiperImage" type="hidden" value=""/>
                        <button type="button" class="btn btn-danger" id="swiperDeleteBtn" onClick="deleteImage($('#swiperImage').val(),'swiperMessage',function(data)
				{
                	$('#swiperMessage').val('');
					getSwiperImgs();
				});" disabled="disabled">删除</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭
						</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal -->

		</div>
        
        
		<!-- 价格属性组合图设置模态框（Modal） -->
		<div class="modal fade" id="packagePriceImageModal" tabindex="-2"
			role="dialog" aria-labelledby="packagePriceImageModalLabel"
			aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="packagePriceImageModalLabel">属性组合图设置</h4>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<label for="propertyValue" class="col-sm-2 control-label"></label>
							<div class="col-sm-9">
								<input type="file" id="packagePriceFile" name="file"
									style="display: none;" accept="image/*"
									onChange="$('#packagePriceMessage').html($('#packagePriceFile').val());preImg(this.id,'packagePriceImg');" />
								<button type="button" class="btn btn-success fileinput-button"
									onclick="$('#packagePriceFile').click();">
									<i class="fa fa-plus"></i>&nbsp;&nbsp;选择文件
								</button>
								<button type="button" class="btn btn-primary start"
									onclick="uploadProImages('packagePriceFile','packagePriceMessage','prop',function uploadPackagePriceImageHandler(data, status){
            $('#packagePriceImage').val(data.assets[0].url);
            $('#packagePriceImg').attr('src','');
            $('#packagePriceMessage').html('');
			getPackagePriceImgs();
		});">
									<i class="fa fa-upload"></i> <span>&nbsp;&nbsp;开始上传</span>
								</button>
								<br> <label id="packagePriceMessage"></label>
								<br><img id="packagePriceImg" class=".img-responsive" width="80px;">
                               	<br><hr class="hr1"/>
                                <div class="row" id="packagePriceImageRow">
                                   
                                </div>
                                
							</div>
						</div>

					</div>
					<div class="modal-footer">
                    	<input id="packagePriceImage" type="hidden" value=""/>
                        <input id="tempPackagePriceImage" type="hidden" value=""/>
                    	<button type="button" class="btn btn-primary" id="packagePriceSaveBtn" onClick="savePackagePriceImage();">保存
						</button>
                        <button type="button" class="btn btn-danger" id="packagePriceDeleteBtn" onClick="deleteImage($('#tempPackagePriceImage').val(),'packagePriceMessage',function(data)
				{
                	$('#packagePriceMessage').val('');
                    $('#tempPackagePriceMessage').val('');
					getPackagePriceImgs();
				});" disabled="disabled">删除</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭
						</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal -->

		</div>
	</form>
	<!-- 配置文件 -->
	<script type="text/javascript" src="${ctx}/ueditor/ueditor.config.js"></script>
	<!-- 编辑器源码文件 -->
	<script type="text/javascript" src="${ctx}/ueditor/ueditor.all.js"></script>
	<!-- 实例化编辑器 -->

	<script type="text/javascript">
		UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;  
		UE.Editor.prototype.getActionUrl = function(action) {  
			  if (action == 'uploadimage' || action == 'uploadscrawl' || action == 'uploadvideo') {  
				  return '/fileupload/config/prDetailAssets';  
			  } else {  
				  return this._bkGetActionUrl.call(this, action);  
			  }  
		}  
	
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
		
		var settingPackagePriceImgId='';

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

			$('#collapsePrImages').collapse('hide');
			
			//validateForm();
			
			//$("img").load({effect : "fadeIn"});
			//获得属性组合图。
			getPackagePriceImgs();
			//获得轮播图。
			getSwiperImgs();
		});
		
		
		//设置价格属性组合图。
		function settingPackagePrice(packagePriceId){
			$("#packagePriceImageModal").modal("show");
			settingPackagePriceImgId=packagePriceId;
			var source = $("#"+packagePriceId+"_icon").val();
			$("#packagePriceImg").attr("src","${ctx}/"+source);
		}
		
		//ajax获得属性组合属性图
		function getPackagePriceImgs(){		
			$.ajax({
				url: "${ctx}/product/selectByProductId?productId=${product.id}",
				datatype: 'json',
				type: "post",
				success: function (data) {
					$("#tempPackagePriceImage").val("");
					$("#packagePriceImage").val("");
					$("#packagePriceImageRow").empty();
					$("#packagePriceDeleteBtn").attr("disabled","disabled");
					$("#packagePriceSaveBtn").attr("disabled","disabled");
					
					for(var i=0;i<data.length;i++){
						var icon =data[i].url;
						var param = '<div class="col-sm-3 col-md-3"><a href="#" class="thumbnail" onClick="settingPackagePriceImage(\''+data[i].id+'\',\''+data[i].url+'\');return false;"><img id="'+data[i].id+'" src="${ctx}/'+icon+'"/></a></div>';
						
                        $("#packagePriceImageRow").append(param);  
					}
				}
			});	
		}
		
		//ajax获得轮播图
		function getSwiperImgs(){		
			$.ajax({
				url: "${ctx}/product/selectSwiperImages?productId=${product.id}",
				datatype: 'json',
				type: "post",
				success: function (data) {
					$("#swiperImage").val("");
					$("#swiperImageRow").empty();
					$("#swiperDeleteBtn").attr("disabled","disabled");
					
					for(var i=0;i<data.length;i++){
						var icon =data[i].url;
						var param = '<div class="col-sm-3 col-md-3"><a href="#" class="thumbnail" onClick="settingSwiperImage(\''+data[i].id+'\');return false;"><img id="'+data[i].id+'" src="${ctx}/'+icon+'"/></a></div>';
						
                        $("#swiperImageRow").append(param);  
					}
				}
			});	
		}
		
		//删除图片
		function deleteImage(imgId,labelMessage,callback){
			if(imgId==''){
				return;
			}
			$('#'+labelMessage).val('正在删除……');
			
			var url = '${ctx}/prImage/delete?id=' + imgId;
			$.ajax({
				url : url,
				dataType : 'json', //返回值类型 一般设置为json
				success : callback
			})
		}
		
		//设置好轮播弹窗的按钮状态。
		function settingSwiperImage(id){
			$("#swiperImage").val(id);
			$("#swiperDeleteBtn").attr("disabled",false);
		}
		
		//设置好价格属性弹窗的按钮状态。
		function settingPackagePriceImage(id,url){
			$("#tempPackagePriceImage").val(id);
			$("#packagePriceImage").val(url);
			$("#packagePriceDeleteBtn").attr("disabled",false);
			$("#packagePriceSaveBtn").attr("disabled",false);
		}
		
		//保存属性
		function savePackagePriceImage(){
			var icon =$("#packagePriceImage").val();
			if(icon==''){
				return;
			}
			if(settingPackagePriceImgId==''){
				return;
			}
			$('#'+settingPackagePriceImgId+'_icon').val(icon);
			$("#packagePriceImageModal").modal("hide");
			
		}

		//上传产品属性值图片
		function uploadProImages(fileElement,messageLabel,type,callbackFunc) {
			var fileValue = $('#'+fileElement).val();
			if (fileValue == '') {
				$('#'+messageLabel).html('请选择一个文件')
				return;
			}
			$('#'+messageLabel).html('正在上传……');
			
			var url = '${ctx}/fileupload/prAssets?productId=' + $('#id').val()+"&type="+type;
			$.ajaxFileUpload({
						url : url, //用于文件上传的服务器端请求地址
						secureuri : false, //是否需要安全协议，一般设置为false
						fileElementId : fileElement, //文件上传域的ID
						dataType : 'json', //返回值类型 一般设置为json
						success : callbackFunc,
						error : function(data, status, e)//服务器响应失败处理函数
						{
							$('#'+messageLabel).html('上传失败');
						}
					})
		}
		
		//上传属性组合图片回调函数。
		function uploadPackagePriceImageHandler(data, status){
			getPackagePriceImgs();
		}
		
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
						var pri = pricesArray[i];
						var propertyValueId = pri.propertyValueId;
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
			//$('#addPropertyBtn').click();
			$('#propertyModal').modal('show')
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

						object.groupPrice = $('#' + object.propertyValueId + "_GroupInput").val();
						object.indepentPrice = $('#' + object.propertyValueId + "_IndependInput").val();
						object.propertyValueId = object.propertyValueId;
						object.productId = $('#id').val();
						object.icon = $('#' + object.propertyValueId + "_icon").val();
						object.disable = '0';
					}

					refreshComponent();
					refreshComponent1();
					for (j = 0; j < pricesArray.length; j++) {
						var object = pricesArray[j];
						$('#' + object.propertyValueId + "_GroupInput").val(object.groupPrice);
						$('#' + object.propertyValueId + "_IndependInput").val(object.indepentPrice);
						$('#' + object.propertyValueId + "_icon").val(object.icon);
						//$('#' + object.propertyValueId + "_disable").val(object.disable);
					}

					//$('#prImagemessage').html('修改属性成功');
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
					price1.disable = "0";
					price1.icon = "";
					pricesArray.push(price1);

				}
				for (var j = i + 1; j < propertyValueArray.length; j++) {
					//两种属性
					if (propertyArray.length == 2) {
						if (propertyValueArray[i].propertyId != propertyValueArray[j].propertyId) {
							var price2 = {};
							price2.gourpPrice = "";
							price2.independentPrice = "";
							price2.id = new Date().getTime() + ""+ Math.round(Math.random() * 100);
							price2.productId = $('#id').val();
							price2.propertyValueId = propertyValueArray[i].id+ "_" + propertyValueArray[j].id;
							price2.disable = "0";
							price2.icon = "";
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
								price3.propertyValueId = propertyValueArray[i].id+ "_"+ propertyValueArray[j].id+ "_"+ propertyValueArray[p].id;
								price3.disable = "0";
								price3.icon = "";
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
					para += '&nbsp;&nbsp;<button id="'+price.propertyValueId+'_editBtn" onClick="settingPackagePrice(\''+price.propertyValueId+'\')" type="button" class="btn btn-primary btn-xs">编辑该属性图片</button>&nbsp;&nbsp;<select id="\''+price.propertyValueId+'\'_disable" class="easyui-combobox" style="width:80px;display:none;"><option value="1" >启用</option><option value="0">禁用</option></select>';
						
					para += '<input id="'
							+ price.propertyValueId
							+ '_GroupInput" type="text" class="form-control" placeholder="'
							+ pName
							+ '拼团价(元)"  onBlur="priceCallback()"><input  id="'
							+ price.propertyValueId
							+ '_IndependInput" type="text" class="form-control" placeholder="'
							+ pName + '单独购买价(元)"  onBlur="priceCallback()">'+'<input id="'+price.propertyValueId+'_icon" value="" type="hidden">';
					$('#proValuePriceDiv').append(para);
				}

			}
		}
		
		function onSubmitHandler() {
			var s = "";
			if(propertyValueArray.length!=0){
				s = JSON.stringify(propertyValueArray);
			}
			$('#propertyValues').val(s);

			for (var j = 0; j < pricesArray.length; j++) {
				var object = pricesArray[j];
				//object.disable = $('#' + object.propertyValueId + "_disable").combobox('getValue');
				object.disable = '0';
				object.icon = $('#' + object.propertyValueId + "_icon").val();
				object.groupPrice = $('#' + object.propertyValueId + "_GroupInput").val();
				object.indepentPrice = $('#' + object.propertyValueId + "_IndependInput").val();
				object.propertyValueId = object.propertyValueId;
				object.productId = $('#id').val();
				pricesArray[j]=object;
			}

			var priceString = "";
			if(pricesArray.length!=0){
				priceString = JSON.stringify(pricesArray);
			}
			$('#propertyPrices').val(priceString);
		}

		//上传商品详情图片
		function uploadDetailImageTOServer() {
			var fileValue = $('#detailFile').val();
			if (fileValue == '') {
				$('#detailMessage').html('请选择一个文件')
				return;
			}
			
			$('#detailMessage').html('正在上传……');
			var url = '${ctx}/fileupload/prDetailAssets?productId='+ $('#id').val()+"&type=detail";
			$
					.ajaxFileUpload({
						url : url, //用于文件上传的服务器端请求地址
						secureuri : false, //是否需要安全协议，一般设置为false
						fileElementId : 'detailFile', //文件上传域的ID
						dataType : 'json', //返回值类型 一般设置为json
						success : function(data, status) //服务器成功响应处理函数
						{
							if (data.assets.length > 0) {
								var para = '';
								for (var i = 0; i < data.assets.length; i++) {
									var assets = data.assets[i];
									para = '<div><img id="'+assets.id+'" src="${ctx}/'+assets.url+'" class=".img-responsive" width="50px;"/>&nbsp;&nbsp;<button type="button" class="btn btn-info" onClick="insertIntoUeditor(\''+assets.id+'\')">插入</button></div>';
									$('#detailImgsDiv').append(para);
								}
								//$('#datagrid').datagrid("reload");
								
								$('#detailImg').attr("src","");
							}
							$('#detailMessage').html('');
						},
						error : function(data, status, e)//服务器响应失败处理函数
						{
							$('#detailMessage').html('上传失败');
							//alert("上传失败");
						}
					})
		}
		
		function insertIntoUeditor(imgId){
			var source = $('#'+imgId).attr('src');
			var para = '<img id="'+imgId+'" class="img-responsive" src="'+source+'"/>';
			ue.execCommand('insertHtml', para);
			/*var para = '<img id="'+imgId+'" class="img-responsive" src="/images/busy.gif" data-original="'+source+'"/>';*/
			/*ue.setContent(para,true);*/
			
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
		
		var operationMode = "${operation}";
		
		function createMode(){
			document.form.action="${ctx}/product/create";
			document.form.submit();
		}	
	
		function createAndToEditMode(){
			document.form.action="${ctx}/product/update";
			document.form.submit();
		}
		
		
		///验证函数
		function validateForm(){
			$('form').bootstrapValidator({
	　　　　　　　　message: 'This value is not valid',
				　feedbackIcons: {
					　　　　　　　　valid: 'glyphicon glyphicon-ok',
					　　　　　　　　invalid: 'glyphicon glyphicon-remove',
					　　　　　　　　validating: 'glyphicon glyphicon-refresh'
				　　　　　　　　   },
				fields: {
					name: {
						message: '标题验证失败',
						validators: {
							notEmpty: {
								message: '标题不能为空'
							},stringLength: {
								min: 1,
								max: 120,
								message: '长度必须在1到120之间'
							}
						}
					},
					description: {
						message: '标题验证失败',
						validators: {
							notEmpty: {
								message: '标题不能为空'
							},stringLength: {
								min: 1,
								max: 1200,
								message: '长度必须在1到1200之间'
							}
						}
					},
					purchasingCost: {
						message: '进货价验证失败',
						validators: {
							notEmpty: {
								message: '进货价不可以为空'
							}
						}
					},
					gourpSalePrice: {
						message: '团购价验证失败',
						validators: {
							notEmpty: {
								message: '团购价不可以为空'
							}
						}
					},
					independentPrice: {
						message: '单独购买价验证失败',
						validators: {
							notEmpty: {
								message: '单独购买不可以为空'
							}
						}
					},
					storage: {
						message: '库存验证失败',
						validators: {
							notEmpty: {
								message: '库存不可以为空'
							}
						}
					},
					marketPrice: {
						message: '市价验证失败',
						validators: {
							notEmpty: {
								message: '市价不可以为空'
							}
						}
					}
				}
			});	
		}
	</script>
	<%@ include file="/WEB-INF/include/easyui-footerjs.jsp"%>
	<!-- Initialize Swiper -->
	<script>
		var swiper = new Swiper('.swiper-container');
	</script>
</body>
</html>