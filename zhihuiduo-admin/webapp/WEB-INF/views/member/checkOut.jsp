<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>智惠多商品云购.订单结算</title>

<%@ include file="/WEB-INF/member-include/css.jsp"%>
<%@ include file="/WEB-INF/member-include/js.jsp"%>
<link href="${ctx}/css/zhihuiduo.css" rel="stylesheet" type="text/css" />
<style>
body {
	font-size: 1.5rem;
	background-color: #F2F2F2;
}

.thumbnail {
	padding: 1px;
	margin-bottom: 5px;
	line-height: 1.42857143;
	border: 1px solid #ddd;
	border-radius: 1px;
}

.label label-info {
	padding-top: 5px;
}

.form-group {
	margin: 3px;
}
</style>
<script type="text/javascript" language="javascript"> 
//定义数组，存储省份信息 
var province = ["北京", "上海", "天津", "重庆", "浙江", "江苏", "广东", "福建", "湖南", "湖北", "辽宁", 
"吉林", "黑龙江", "河北", "河南", "山东", "陕西", "甘肃", "新疆", "青海", "山西", "四川", 
"贵州", "安徽", "江西", "云南", "内蒙古", "西藏", "广西", "宁夏", "海南", "香港", "澳门", "台湾"]; 

//定义数组,存储城市信息 
var beijing = ["东城区", "西城区", "海淀区", "朝阳区", "丰台区", "石景山区", "通州区", "顺义区", "房山区", "大兴区", "昌平区", "怀柔区", "平谷区", "门头沟区", "延庆县", "密云县"]; 
var shanghai = ["浦东新区", "徐汇区", "长宁区", "普陀区", "闸北区", "虹口区", "杨浦区", "黄浦区", "卢湾区", "静安区", "宝山区", "闵行区", "嘉定区", "金山区", "松江区", "青浦区", "南汇区", "奉贤区", "崇明县"]; 
var tianjing = ["河东", "南开", "河西", "河北", "和平", "红桥", "东丽", "津南", "西青", "北辰", "塘沽", "汉沽", "大港", "蓟县", "宝坻", "宁河", "静海", "武清"]; 
var chongqing = ["渝中区", "大渡口区", "江北区", "沙坪坝区", "九龙坡区", "南岸区", "北碚区", "万盛区", "双桥区", "渝北区", "巴南区", "万州区", "涪陵区", "黔江区", "长寿区", "江津区", "合川区", "永川区", "南川区"]; 
var jiangsu = ["南京", "无锡", "常州", "徐州", "苏州", "南通", "连云港", "淮安", "扬州", "盐城", "镇江", "泰州", "宿迁"]; 
var zhejiang = ["杭州", "宁波", "温州", "嘉兴", "湖州", "绍兴", "金华", "衢州", "舟山", "台州", "利水"]; 
var guangdong = ["广州", "韶关", "深圳", "珠海", "汕头", "佛山", "江门", "湛江", "茂名", "肇庆", "惠州", "梅州", "汕尾", "河源", "阳江", "清远", "东莞", "中山", "潮州", "揭阳"]; 
var fujiang = ["福州", "厦门", "莆田", "三明", "泉州", "漳州", "南平", "龙岩", "宁德"]; 
var hunan = ["长沙", "株洲", "湘潭", "衡阳", "邵阳", "岳阳", "常德", "张家界", "益阳", "郴州", "永州", "怀化", "娄底", "湘西土家苗族自治区"]; 
var hubei = ["武汉", "襄阳", "黄石", "十堰", "宜昌", "鄂州", "荆门", "孝感", "荆州", "黄冈", "咸宁", "随州", "恩施土家族苗族自治州"]; 
var liaoning = ["沈阳", "大连", "鞍山", "抚顺", "本溪", "丹东", "锦州", "营口", "阜新", "辽阳", "盘锦", "铁岭", "朝阳", "葫芦岛"]; 
var jilin = ["长春", "吉林", "四平", "辽源", "通化", "白山", "松原", "白城", "延边朝鲜族自治区"]; 
var heilongjiang = ["哈尔滨", "齐齐哈尔", "鸡西", "牡丹江", "佳木斯", "大庆", "伊春", "黑河", "大兴安岭"]; 
var hebei = ["石家庄", "保定", "唐山", "邯郸", "承德", "廊坊", "衡水", "秦皇岛", "张家口"]; 
var henan = ["郑州", "洛阳", "商丘", "安阳", "南阳", "开封", "平顶山", "焦作", "新乡", "鹤壁", "许昌", "漯河", "三门峡", "信阳", "周口", "驻马店", "济源"]; 
var shandong = ["济南", "青岛", "菏泽", "淄博", "枣庄", "东营", "烟台", "潍坊", "济宁", "泰安", "威海", "日照", "滨州", "德州", "聊城", "临沂"]; 
var shangxi = ["西安", "宝鸡", "咸阳", "渭南", "铜川", "延安", "榆林", "汉中", "安康", "商洛"]; 
var gansu = ["兰州", "嘉峪关", "金昌", "金川", "白银", "天水", "武威", "张掖", "酒泉", "平凉", "庆阳", "定西", "陇南", "临夏", "合作"]; 
var qinghai = ["西宁", "海东地区", "海北藏族自治州", "黄南藏族自治州", "海南藏族自治州", "果洛藏族自治州", "玉树藏族自治州", "海西蒙古族藏族自治州"]; 
var xinjiang = ["乌鲁木齐", "奎屯", "石河子", "昌吉", "吐鲁番", "库尔勒", "阿克苏", "喀什", "伊宁", "克拉玛依", "塔城", "哈密", "和田", "阿勒泰", "阿图什", "博乐"]; 
var shanxi = ["太原", "大同", "阳泉", "长治", "晋城", "朔州", "晋中", "运城", "忻州", "临汾", "吕梁"]; 
var sichuan = ["成都", "自贡", "攀枝花", "泸州", "德阳", "绵阳", "广元", "遂宁", "内江", "乐山", "南充", "眉山", "宜宾", "广安", "达州", "雅安", "巴中", "资阳", "阿坝藏族羌族自治州", "甘孜藏族自治州", "凉山彝族自治州"]; 
var guizhou = ["贵阳", "六盘水", "遵义", "安顺", "黔南布依族苗族自治州", "黔西南布依族苗族自治州", "黔东南苗族侗族自治州", "铜仁", "毕节"]; 
var anhui = ["合肥", "芜湖", "安庆", "马鞍山", "阜阳", "六安", "滁州", "宿州", "蚌埠", "巢湖", "淮南", "宣城", "亳州", "淮北", "铜陵", "黄山", "池州"]; 
var jiangxi = ["南昌", "九江", "景德镇", "萍乡", "新余", "鹰潭", "赣州", "宜春", "上饶", "吉安", "抚州"]; 
var yunnan = ["昆明", "曲靖", "玉溪", "保山", "昭通", "丽江", "普洱", "临沧", "楚雄彝族自治州", "大理白族自治州", "红河哈尼族彝族自治州", "文山壮族苗族自治州", "西双版纳傣族自治州", "德宏傣族景颇族自治州", "怒江傈僳族自治州", "迪庆藏族自治州"]; 
var neimenggu = ["呼和浩特", "包头", "乌海", "赤峰", "通辽", "鄂尔多斯", "呼伦贝尔", "巴彦淖尔", "乌兰察布"]; 
var guangxi = ["南宁", "柳州", "桂林", "梧州", "北海", "防城港", "钦州", "贵港", "玉林", "百色", "贺州", "河池", "崇左"]; 
var xizang = ["拉萨", "昌都地区", "林芝地区", "山南地区", "日喀则地区", "那曲地区", "阿里地区"]; 
var ningxia = ["银川", "石嘴山", "吴忠", "固原", "中卫"]; 
var hainan = ["海口", "三亚"]; 
var xianggang = ["中西区", "湾仔区", "东区", "南区", "九龙城区", "油尖旺区", "观塘区", "黄大仙区", "深水埗区", "北区", "大埔区", "沙田区", "西贡区", "元朗区", "屯门区", "荃湾区", "葵青区", "离岛区"]; 
var taiwan = ["台北", "高雄", "基隆", "台中", "台南", "新竹", "嘉义"]; 
var aomeng = ["澳门半岛", "氹仔岛", "路环岛"]; 

//页面加载方法 
$(function () { 
//设置省份数据 
setProvince(); 

//设置背景颜色 
setBgColor(); 
}); 


//设置省份数据 
function setProvince() { 
//给省份下拉列表赋值 
var option, modelVal; 
var $sel = $("#selProvince"); 

//获取对应省份城市 
for (var i = 0, len = province.length; i < len; i++) { 
modelVal = province[i]; 
option = "<option value='" + modelVal + "'>" + modelVal + "</option>"; 

//添加到 select 元素中 
$sel.append(option); 
} 

//调用change事件，初始城市信息 
provinceChange(); 
} 


//根据选中的省份获取对应的城市 
function setCity(provinec) { 
var $city = $("#selCity"); 
var proCity, option, modelVal; 

//通过省份名称，获取省份对应城市的数组名 
switch (provinec) { 
case "北京": 
proCity = beijing; 
break; 
case "上海": 
proCity = shanghai; 
break; 
case "天津": 
proCity = tianjing; 
break; 
case "重庆": 
proCity = chongqing; 
break; 
case "浙江": 
proCity = zhejiang; 
break; 
case "江苏": 
proCity = jiangsu; 
break; 
case "广东": 
proCity = guangdong; 
break; 
case "福建": 
proCity = fujiang; 
break; 
case "湖南": 
proCity = hunan; 
break; 
case "湖北": 
proCity = hubei; 
break; 
case "辽宁": 
proCity = liaoning; 
break; 
case "吉林": 
proCity = jilin; 
break; 
case "黑龙江": 
proCity = heilongjiang; 
break; 
case "河北": 
proCity = hebei; 
break; 
case "河南": 
proCity = henan; 
break; 
case "山东": 
proCity = shandong; 
break; 
case "陕西": 
proCity = shangxi; 
break; 
case "甘肃": 
proCity = gansu; 
break; 
case "新疆": 
proCity = xinjiang; 
break; 
case "青海": 
proCity = qinghai; 
break; 
case "山西": 
proCity = shanxi; 
break; 
case "四川": 
proCity = sichuan; 
break; 
case "贵州": 
proCity = guizhou; 
break; 
case "安徽": 
proCity = anhui; 
break; 
case "江西": 
proCity = jiangxi; 
break; 
case "云南": 
proCity = yunnan; 
break; 
case "内蒙古": 
proCity = neimenggu; 
break; 
case "西藏": 
proCity = xizang; 
break; 
case "广西": 
proCity = guangxi; 
break; 
case "": 
proCity = xizang; 
break; 
case "宁夏": 
proCity = ningxia; 
break; 
case "海南": 
proCity = hainan; 
break; 
case "香港": 
proCity = xianggang; 
break; 
case "澳门": 
proCity = aomeng; 
break; 
case "台湾": 
proCity = taiwan; 
break; 
} 

//先清空之前绑定的值 
$city.empty(); 

//设置对应省份的城市 
for (var i = 0, len = proCity.length; i < len; i++) { 
modelVal = proCity[i]; 
option = "<option value='" + modelVal + "'>" + modelVal + "</option>"; 

//添加 
$city.append(option); 
} 

//设置背景 
setBgColor(); 
} 


//省份选中事件 
function provinceChange() { 
var $pro = $("#selProvince"); 
setCity($pro.val()); 
} 


//设置下拉列表间隔背景样色 
function setBgColor() { 
var $option = $("select option:odd"); 
$option.css({ "background-color": "#DEDEDE" }); 
} 

</script>
</head>
<body>
	<div id="checkDiv">
		<div id="defaultAddress"  onClick="setCheckDivToNone(false);return false;">
			<!--<c:if test="${address==null}">
				<div
					style="height: 80px; line-height: 80px; background-color: #ffffff; padding-left: 10px; padding-right: 10px;">
					<div class="pull-left">
						<span><i class="fa fa-plus-square fa-lg" aria-hidden="true"
							style="color: red;"></i></span> <span>&nbsp;&nbsp;</span> <span>手动添加新地址</span>
					</div>
					<div class="pull-right">
						<span>&nbsp;&nbsp;</span> <span><i
							class="fa fa-arrow-circle-right lg" aria-hidden="true"></i></span>
					</div>
					<div class="clearfix"></div>
				</div>

			</c:if>
			<c:if test="${address!=null}">
				<div
					style="height: 80px; background-color: #ffffff; padding-left: 10px; padding-right: 10px; padding-top: 10px; border-bottom: 1px solid red;">
					<strong>${address.name}</strong>,<span>${address.mobilPhone}</span><br>
					<span>${address.province}&nbsp;&nbsp;${address.city}&nbsp;&nbsp;${address.street}</span>
				</div>
			</c:if>-->
		</div>

		<div
			style="background-color: #ffffff; padding-left: 10px; padding-right: 10px; padding-top: 3px; padding-bottom: 3px; margin-top: 4px;">
             <div class="pull-left"><span id="shopNameSpan"><!--<c:if test="${shop==null}">${product.enName}</c:if>--></span></div>
			
            <div class="pull-right" style="color:red; display:none;" id="tooltipDiv"><i class="fa fa-arrow-circle-o-up" aria-hidden="true"></i><span id="tooltip"></span></div>
            <div class="clearfix"></div>
		
            <div
                style="background-color: #ffffff; padding-left: 10px; padding-right: 10px; padding-top: 10px; padding-bottom: 10px; margin-top: 4px;">
                <div class="media">
                    <div class="media-left">
                        <img id="pIcon" class="media-object" src="${ctx}/images/busy.webp"
                            data-original="${ctx}/${product.icon}" width="120px" alt="媒体对象">
                    </div>
                    <div class="media-body">
                        <h5 class="media-heading" id="pName"></h5>
                        <h5 id="propertyValuesInfo"></h5>
                    </div>
                </div>
            </div>
        </div>
        <div id="paywayDiv" class="list-group">
        </div>
        <form id="payForm" role="form">
        	<input id="orderId" name="orderId" type="hidden"/>
            <input id="goodsId" name="goodsId" type="hidden"/>
            <input id="buyNum" name="buyNum" type="hidden"/>
            <input id="dealPrice" name="dealPrice" type="hidden"/>
            <input id="mode" name="mode" type="hidden"/>
            <input id="groupPurcseId" name="groupPurcseId" type="hidden"/>
            <input id="proValues" name="proValues" type="hidden"/>
            <input id="packagePriceId" name="packagePriceId" type="hidden"/>
            <input id="address" name="address" type="hidden"/>
            <input id="transportFee" name="transportFee" type="hidden"/>
            <input id="payway" name="payway" type="hidden"/>
        </form>
		<div
			style="height: 40px; background-color: #ffffff; border-top: 0px solid red; position: absolute; left: 0; right: 0; bottom: 0;">
			<div
				style="float: right; background-color: red; color: #ffffff; width: 25%; height: 40px; text-align: center; line-height: 40px;"
                 onClick="checkPay();">
				<span id="confirmBtn">确定</span>
			</div>
			<div
				style="float: right; background-color: #ffffff; color: #151516; width: 75%; height: 40px; text-align: left; line-height: 40px; padding-right: 30px; padding-left:2px;">
                运费:<span
					style="color: red;"><i class="fa fa-jpy"></i><label id="transportFeeLabel"></label></span>
                   &nbsp;
                货款:<span
					style="color: red;"><i class="fa fa-jpy"></i><label id="dealPriceLabel"></label></span>
                   &nbsp;
				实际付款:<span
					style="color: red;"><i class="fa fa-jpy"></i><label id="TotalPriceLabel"></label></span>
			</div>
		</div>
	</div>



	<!--地址弹窗-->
	<div id="addressSettingDiv" style="display: none;">
		<div class="page-header"
			style="text-align: center; font-size: 2rem; position: fixed; background-color: #fff; left: 0; right: 0; top: 0; padding-top: 10px; margin-top: 0;">
			<b>收货地址</b>&nbsp;&nbsp;<small style="color: red;"
				onClick="setCheckDivToNone(true);">返回</small>
		</div>
		<div style="height: 45px;"></div>

		<div id="addressDiv">
			<!--地址循环开始-->
			<!--<c:forEach var="addr" items="${addresses}">
				<div class="thumbnail" id="${addr.id}_thumbnail"
					onClick="searialForm('${addr.id}');return false;">
					<div class="caption">
						<input id="${addr.id}" type="hidden" value="${addr.id}" />
						<p>
							<span id="${addr.id}_name">${addr.name}</span>,<span
								id="${addr.id}_mobilPhone">${addr.mobilPhone}</span>
						</p>
						<p>
							<span id="${addr.id}_province">${addr.province}</span>&nbsp;&nbsp;<span
								id="${addr.id}_city">${addr.city}</span>&nbsp;&nbsp;<span
								id="${addr.id}_street">${addr.street}</span>
						</p>
						<hr class="hr1" />
						<div class="pull-left">
							<div class="checkbox">
								<c:if test="${addr.isDefault=='1'}">
									<span class="label label-danger" id="${addr.id}_defaultLabel">已为默认</span>
								</c:if>
							</div>
						</div>
						<div class="pull-right" style="font-weight: normal;">
							<span class="label label-info" style="padding-top: 5px;"
								onClick="useAddress('${addr.id}')"> <i
								class="fa fa-pencil-square-o" aria-hidden="true"></i>&nbsp;&nbsp;使用
							</span> &nbsp;&nbsp; <span class="label label-danger"
								style="padding-top: 5px;"
								onClick="deleteMemberAddress('${addr.id}')"> <i
								class="fa fa-trash-o" aria-hidden="true"></i>&nbsp;&nbsp;删除
							</span>
						</div>


						<div class="clearfix"></div>
					</div>
				</div>
			</c:forEach>-->

			<!--地址循环结束-->
		</div>

		<div
			style="text-align: center; margin-bottom: 20px; margin-top: 16px;">
			<button class="btn btn-danger" data-toggle="modal"
				data-target="#myModal"
				style="width: 80%; margin: 0; border-radius: 0px; color: #ffffff;"
				onClick="create();">新增地址</button>
		</div>


		<!-- 模态框（Modal） -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">添加/编辑收货地址</h4>
					</div>
					<form id="form" name="form" method="post"
						action="${ctx}/memberInfo/createMemberAddress" role="form">
						<div class="modal-body" style="padding-top: 2px;">
							<input id="id" name="id" type="hidden" />
							<div class="form-group">
								<input id="name" name="name" class="form-control"
									placeholder="姓名" />
							</div>
							<div class="form-group">
								<input id="mobilPhone" name="mobilPhone" class="form-control"
									placeholder="电话" />
							</div>

							<div class="form-group">
								<label>省 份：</label> <select id="selProvince" name="province"
									onchange="provinceChange();" class="form-control"></select> <label>市(区)：</label>
								<select id="selCity" name="city" class="form-control"></select>
							</div>
							<div class="form-group">
								<input id="street" name="street" class="form-control"
									placeholder="请填写详细的区和街道地址" />
							</div>

						</div>
						<div class="modal-footer" style="text-align: center;">
							<button type="button" class="btn btn-danger"
								style="width: 80%; color: #ffffff;" onClick="save();">保存</button>
						</div>
					</form>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal -->
		</div>
	</div>
   
	<script>
		window.rawData = ${rawData};
		
		var ctx = "";
		var obj;
		
		var payway = "wechat";
		var currentAddr;//当前地址。
		
		$(function() {
			ctx = window.location.protocol+"//"+window.location.host;
			
			if(window.rawData){
				obj = window.rawData;
				setDefaultAddr();
				setProduct();
				setAddresses();
				setPayway();
			}
			
			
			$("img").lazyload({effect: "fadeIn"});
		});

		function setDefaultAddr(){
			if(obj){
				var para = '';
				if(obj.defautAddress){
					var address = obj.defautAddress;
					currentAddr = address;
					para +='<div style="height: 80px; background-color: #ffffff; padding-left: 10px; padding-right: 10px; padding-top: 10px; border-bottom: 1px solid red;"><strong>'+address.name+'</strong>,<span>'+address.mobilPhone+'</span><br><span>'+address.province+'&nbsp;&nbsp;'+address.city+'&nbsp;&nbsp;'+address.street+'</span></div>';
				}else{
					para +='<div style="height: 80px; line-height: 80px; background-color: #ffffff; padding-left: 10px; padding-right: 10px;"><div class="pull-left"><span><i class="fa fa-plus-square fa-lg" aria-hidden="true" style="color: red;"></i></span> <span>&nbsp;&nbsp;</span> <span>手动添加新地址</span></div><div class="pull-right"><span>&nbsp;&nbsp;</span> <span><i class="fa fa-arrow-circle-right lg" aria-hidden="true"></i></span></div><div class="clearfix"></div></div>';
				}
				
				$('#defaultAddress').append(para);
			}
		}
					
		function setProduct(){
			if(obj){
				var para = '';
				if(obj.shop){
					$('#shopNameSpan').html(obj.shop.name);
				}
				if(obj.icon){
					$('#pIcon').attr("data-original",ctx+'/'+obj.icon);
				}
				if(obj.name){
					$('#pName').html(obj.name);
					$("#pName").bind("click",function(){
						self.location = ctx+"/goodsDetail/"+obj.id;
					});
				}
				
				if(obj.order){
					if(obj.order.description){
						$('#propertyValuesInfo').html(obj.order.description);
					}
				}
				
				
				var transportFee=0;
				if(obj.transportFee){
					transportFee=obj.transportFee;
				}
				$('#transportFeeLabel').html(transportFee);
				if(obj.gourpSalePrice){
					$('#dealPriceLabel').html(obj.gourpSalePrice);
					$('#TotalPriceLabel').html(transportFee + obj.gourpSalePrice);
				}
				
			}
		}
		
		//设置地址列表
		function setAddresses(){
			if(obj){
				if(obj.memberAddresses){
					var addres = obj.memberAddresses;
					var length = addres.length;
					for(var i=0;i<length;i++){
						var addr = addres[i];
						var isDefult = ''
						if(addr.isDefault=='1'){
							isDefult ='<span class="label label-danger" id="'+addr.id+'_defaultLabel">已为默认</span>'
						}
						var para = '<div class="thumbnail" id="'+addr.id+'_thumbnail" onClick="searialForm(\''+addr.id+'\');return false;"><div class="caption"><input id="'+addr.id+'" type="hidden" value="'+addr.id+'" /><p><span id="'+addr.id+'_name">'+addr.name+'</span>,<span id="'+addr.id+'_mobilPhone">'+addr.mobilPhone+'</span></p><p><span id="'+addr.id+'_province">'+addr.province+'</span>&nbsp;&nbsp;<span id="'+addr.id+'_city">'+addr.city+'</span>&nbsp;&nbsp;<span id="'+addr.id+'_street">'+addr.street+'</span></p><hr class="hr1" /><div class="pull-left"><div class="checkbox">'+isDefult+'</div></div><div class="pull-right" style="font-weight: normal; padding-top:10px;"><span class="label label-info" style="padding-top: 5px;" onClick="useAddress(\''+addr.id+'\')"> <i class="fa fa-pencil-square-o" aria-hidden="true"></i>&nbsp;&nbsp;使用</span> &nbsp;&nbsp; <span class="label label-danger" style="padding-top: 5px;" onClick="deleteMemberAddress(\''+addr.id+'\')"> <i class="fa fa-trash-o" aria-hidden="true"></i>&nbsp;&nbsp;删除</span></div><div class="clearfix"></div></div></div>';
						$('#addressDiv').append(para);
					}
				}
			}
		}
		
		//设置支付方式
		function setPayway(){
			if(obj){
				var para = '';
				
				para += '<a class="list-group-item focus" href="#" onClick="changePayway(\'wechat\');return false;"><i class="fa fa-weixin fa-fw" aria-hidden="true"></i>&nbsp; 微信支付(推荐)</a>';
				/*para +='<a class="list-group-item" href="#" onClick="changePayway(\'alipay\');return false;"><i class="fa fa-paypal fa-fw" aria-hidden="true"></i>&nbsp; 支付宝</a>';*/
				if(obj.isSentUnpay=='1'){
					 para +='<a class="list-group-item" href="#" onClick="changePayway(\'sendWithoutPay\');return false;"><i class="fa fa-truck fa-fw" aria-hidden="true"></i>&nbsp; 货到付款</a>';
				}
			   
				
				$('#paywayDiv').append(para);
			}
		}
			
		
		//点击修改支付方式。
		function changePayway(paywayPara){
			payway = paywayPara;
		}
		
		//确定支付。
		function checkPay(){
			if(currentAddr){
				$("#tooltipDiv").hide();
				$("#tooltip").html('');
			}else{
				$("#tooltipDiv").show();
				$("#tooltip").html('请设置地址');
				return;	
			}
			
			var url = ctx+'/memberOrder/getPayMchJs';
				 var data = {};
				 data.payway= payway;
				 //data.goodsId= obj.id;
				 data.orderId= obj.order.id;
				 //data.buyNum= obj.order.buyNum;
				 data.dealPrice= obj.order.dealPrice;
				 if(obj.transportFee){
					 data.transportFee= obj.transportFee;
				 }else{
					 data.transportFee= 0;	 
				 }
				
				 //data.groupPurcseId= obj.groupPurcseId;
				 //data.mode= obj.mode;
				 
				 $.ajax({
					 url:url,
					 type: "post",
					 data: data,
					 success: function (result) {
						 //跳转到开团成功或者拼团成功的页面。
						console.log(result);
						if(payway =='wechat'){
							//json 数据
							x_json = JSON.parse(result);
							pay();
						}else if(payway =='sendWithoutPay'){
							//self.location = ctx+"/memberGroup/"+obj.order.groupPurcseId+".htm?timestamp="+new Date().getTime();
						}else if(payway =='alipay'){
							
						}
				 	 },error: function(data) {
                       // alert("error:"+data.responseText);
             	 	 }
           		  });
				  
			
			
			console.log(payway);
		}
		
		var x_json;
		function pay(){
			WeixinJSBridge.invoke('getBrandWCPayRequest',x_json,function(res){
				if(res.err_msg == 'get_brand_wcpay_request:ok'){
					//支付成功，可以做跳转到支付成功的提示页面
				}else{
					//支付失败
					alert(res.err_msg);
				}
			});
		}
		
		function getQueryString(name){
			 var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
			 var r = window.location.search.substr(1).match(reg);
			 if(r!=null)return  unescape(r[2]); return null;
		}
		
		function setCheckDivToNone(toCheckDiv){
			$("#checkDiv").slideToggle("fast");
			$("#addressSettingDiv").slideToggle("fast");
		}
		
		function useAddress(addressId){
			var name = $("#"+addressId+"_name").html();
			var mobilPhone = $("#"+addressId+"_mobilPhone").html();
			var province = $("#"+addressId+"_province").html();
			var city = $("#"+addressId+"_city").html();
		 	var street = $("#"+addressId+"_street").html();
			
			var para = '<div style="height: 80px; background-color: #ffffff; padding-left: 10px; padding-right: 10px; padding-top: 10px; border-bottom: 1px solid red;"><strong>'+name+'</strong>,<span>'+mobilPhone+'</span><br><span>'+province+'&nbsp;&nbsp;'+city+' &nbsp;&nbsp;'+street+'</span></div>';

			$("#defaultAddress").empty();
			$("#defaultAddress").append(para);
			setCheckDivToNone(true);
			
			currentAddr = {};
			currentAddr.name = name;
			currentAddr.mobilPhone = mobilPhone;
			currentAddr.province = province;
			currentAddr.city = city;
		 	currentAddr.street = street;
			$("#tooltipDiv").hide();
			$("#tooltip").html('');
		}
		
		
		///////////////////////////////////////////////////////
	var mode = "create";
	var fromUrl = "${fromURL}";
	function backForward(){
		var data = $('#form').serialize();
		history.back();
	}
	
	function searialForm(addressId){
		$("#id").val(addressId);
		var name = $("#"+addressId+"_name").html();
		$("#name").val(name);
		var mobilPhone = $("#"+addressId+"_mobilPhone").html();
		$("#mobilPhone").val(mobilPhone);
		var province = $("#"+addressId+"_province").html();
		$("#selProvince").val(province);
		$('#selProvince').change();
		var city = $("#"+addressId+"_city").html();
		$("#selCity").val(city);
		var street = $("#"+addressId+"_street").html();
		$("#street").val(street);
	}
	
	
	function save(){
		var bootstrapValidator = $("#form").data('bootstrapValidator');
		bootstrapValidator.validate();
		var isValid = bootstrapValidator.isValid();
		if(!isValid){
			return;
		}
		
		var url = $("#form").attr("action");
		var data = $('#form').serialize();
		data = decodeURIComponent(data, true);
		 $.ajax({
			 url:url,
             type: "POST",
             data: data,
             success: function (result) {
				 var obj =  JSON.parse(result);
				 
				 if(mode == "create"){
					 var defaultUi = "";
					 if(obj.isDefault=='0'){
						defaultUi = '<span class="label label-info" style="padding-top:5px;"  id="'+obj.id+'_defaultSettingLabel"></span>';
					 }else if(obj.isDefault=='1'){
						defaultUi = '<span class="label label-danger" id="'+obj.id+'_defaultLabel">已为默认</span>';
					 }
						
					var parameter = '<div class="thumbnail" id="'+obj.id+'_thumbnail"><div class="caption"><input id="'+obj.id+'" type="hidden" value="'+obj.id+'" /><p><span id="'+obj.id+'_name">'+obj.name+'</span>,<span id="'+obj.id+'_mobilPhone">'+obj.mobilPhone+'</span></p><p><span id="'+obj.id+'_province">'+obj.province+'</span>&nbsp;&nbsp;<span id="'+obj.id+'_city">'+obj.city+'</span>&nbsp;&nbsp;<span id="'+obj.id+'_street">'+obj.street+'</span></p><hr class="hr1" /><div class="pull-left"><div class="checkbox">'+defaultUi+'</div></div><div class="pull-right" style="font-weight: normal; padding-top:10px;"><span class="label label-info" style="padding-top: 5px;" onClick="useAddress(\''+obj.id+'\')"> <i class="fa fa-pencil-square-o" aria-hidden="true"></i>&nbsp;&nbsp;使用</span> &nbsp;&nbsp; <span class="label label-danger" style="padding-top: 5px;" onClick="deleteMemberAddress(\''+obj.id+'\')"> <i class="fa fa-trash-o" aria-hidden="true"></i>&nbsp;&nbsp;删除</span></div><div class="clearfix"></div></div></div>';
					$("#addressDiv").append(parameter);
				 }else{
					
					$("#"+obj.id+"_name").html(obj.name);
					$("#"+obj.id+"_mobilPhone").html(obj.mobilPhone);
					$("#"+obj.id+"_province").html(obj.province);
					$("#"+obj.id+"_city").html(obj.city);
					$("#"+obj.id+"_street").html(obj.street);
				 }
				
				$('#myModal').modal('hide');
				//表单重置。
				$("#form").data('bootstrapValidator').destroy();
				$('#form').data('bootstrapValidator', null);
				formValidator();
             },
              error: function(data) {
                       // alert("error:"+data.responseText);
             }
           });
	}

	function create(){
		mode = "create";
		document.form.action="${ctx}/memberInfo/createMemberAddress";
		$("#name").val('');
		$("#mobilPhone").val('');
		$("#street").val('');
	}
	
	function deleteMemberAddress(addressId ){
		var url = "${ctx}/memberInfo/deleteMemberAddress?id="+addressId;
		$.ajax({
			 type: 'POST',
			 url: url ,
		    success: function (data){
				if(data == '1'){
					$("#"+addressId+"_thumbnail").remove();
				}
			},
		 	dataType: 'json'
		});
	}
	
	$(function () {
        formValidator();
    });
	
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
                name: {
                    message: '姓名验证失败',
                    validators: {
                        notEmpty: {
                            message: '姓名不能为空'
                        },stringLength: {
                            min: 1,
                            max: 18,
                            message: '长度必须在4到18位之间'
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
                },
                street: {
                    validators: {
                        notEmpty: {
                            message: '具体街道地址不能为空'
                        },stringLength: {
                            min: 0,
                            max: 60,
                            message: '字数最多为60个'
                        }
                    }
                }
            }
        });
	}
	</script>
</body>
</html>
