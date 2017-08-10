<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript">

	function createTree(tree,url){
		$('#'+tree).tree({
			url : url,
			method : 'get',
			animate : true,
			checkbox : true, //全部折叠
			onLoadSuccess : function(node, data) {
				$('#'+tree).tree("collapseAll");
			}
		})
	}
	
	function createDataGrid(gridId,url) {
		$('#'+gridId)
				.datagrid(
						{
							fitColumns:true,
							striped : true,
							singleSelect : false,
							url : url,
							queryParams:{},  
							loadMsg : '数据加载中请稍后……',
							pagination : true,
							rownumbers : true
						});
	}
	
	//创建栏目树。
	function createComboTree(id,url) {
		$('#' + id).combotree({
			url : url,
			valuefield : 'id',
			width : 220,
			textfield : 'name',
			required : false,
			editable : false,
			onClick : function(node) {
			}, //全部折叠
			onLoadSuccess : function(node, data) {
				$('#' + id).combotree('tree').tree("collapseAll");
			}
		});
	}
	
	//清除下拉树
	function clearComboTree(comboTreeId){
		$('#'+comboTreeId).combotree("clear")
	}
	
	//清除下拉树
	function clearComboTree(comboTreeId,id){
		$('#'+comboTreeId).combotree("clear");
		$('#' + id).val("");
	}

	function deleteById(grid,id, module) {
		var url = '${ctx}/' + module + '/deleteById';
		var pamameter = {};
		pamameter.idstring = id;
		$.ajax({
			type : "POST",
			url : url,
			data : pamameter,
			error : function(request) {
				alert("连接失败");
			},
			success : function(data) {
				$('#'+grid).datagrid('reload'); // 重新加载;
			}
		});
	}

	// 删除；
	function deleteRows(grid,module) {
		var url = '${ctx}/' + module + '/deleteById';
		var pamameter = null;
		//多行删除。
		var row = $('#'+grid).datagrid('getSelections');
		if (row == null || row.length == 0) {
			return;
		}
		var i = 0;
		var string = "";
		for (i; i < row.length; i++) {
			string += row[i].id;
			if (i < row.length - 1) {
				string += ',';
			} else {
				break;
			}
		}
		pamameter = {};
		pamameter.idstring = string;

		if (pamameter == null) {
			return;
		}
		$.ajax({
			type : "POST",
			url : url,
			data : pamameter,
			error : function(request) {
				alert("连接失败");
			},
			success : function(data) {
				$("#"+grid).datagrid('reload'); // 重新加载;
			}
		});
	}

	//取消选中；
	function unselect(grid) {
		$("#"+grid).datagrid('unselectAll');
	}
	
	//刷新；
	function refresh(grid) {
		$("#"+grid).datagrid('reload'); // 重新加载;
	}
	
	//添加；
	function create(module) {
		window.location.href = '${ctx}/' + module + '/create';
	}

	// 编辑；
	function update(id, module) {
		window.location.href = '${ctx}/' + module + '/edit/' + id;
	}
	
	//查询
	function query(grid,queryParams){
		//$('#'+grid).datagrid('options').pageNumber=1;//设置页码初始值为1
// 		$('#'+grid).datagrid('options').queryParams = queryParams;  
// 		$('#'+grid).datagrid('load'); // 重新加载;
		$('#'+grid).datagrid('reload', queryParams);
	}
	
	//格式化菜单
	function formatMenu(value, row, index) {
		if ("menu" == value) {
			return '<label style="color:blue;font-size:14px;">菜单</label>';
		}
		if ("button" == value) {
			return '<label style="color:green;font-size:14px;">按钮</label>';
		}

		return value;
	}
	
	//是否可用
	function formatDisable(value, row, index) {
		if (value) {
			return '<label style="color:red;font-size:14px;">禁用</label>';
		}
		if (!value) {
			return '<label style="color:green;font-size:14px;">可用</label>';
		}

		return value;
	}
	
	//排序
	function codeSorter(a,b){
		return (a>b?1:-1);
	}
	
	//返回到列表
	function backToList(module){
		window.location.href='${ctx}/'+module;
	}
	
	function init(module,grid){
		$("#addBtn").click(function(){
				create(module);
			});
		
		$("#refreshBtn").click(function(){
				refresh(grid);
			});
		$("#removeBtn").click(function(){
				deleteRows(grid,module);
			});
		$("#unselectBtn").click(function(){
			unselect(grid);
			});
	}
	
	//时间格式化函数
	function formatTime(value,row){  
        var unixTimestamp = new Date(value);  
        return unixTimestamp.toLocaleString();  
    }  
	
	//读取cookies
	function getCookie(name)
	{
		var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
		if(arr=document.cookie.match(reg))
		return unescape(arr[2]);
		else
		return null;
	}
	
	//删除cookies
	function delCookie(name)
	{
		var exp = new Date();
		exp.setTime(exp.getTime() - 1);
		var cval=getCookie(name);
		if(cval!=null)
		document.cookie= name + "="+cval+";expires="+exp.toGMTString();
	}
	
	//写cookies,时间默认一个月。
	function setCookie(name,value)
	{
		var Days = 30;
		var exp = new Date();
		exp.setTime(exp.getTime() + Days*24*60*60*1000);
		document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
	}
</script>