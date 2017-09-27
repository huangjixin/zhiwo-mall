<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="Head1">
<title>智惠多平台管理</title>
<link href="Css/default.css" rel="stylesheet" type="text/css" />
<!--<link rel="stylesheet" type="text/css" href="js/jquery-easyui/themes/default/easyui.css" />-->
    <link rel="stylesheet" type="text/css" href="js/jquery-easyui/themes/icon.css" />
<link href="https://cdn.insdep.com/themes/1.0.0/easyui.css"
	rel="stylesheet" type="text/css">
<link href="https://cdn.insdep.com/themes/1.0.0/easyui_animation.css"
	rel="stylesheet" type="text/css">
<link href="https://cdn.insdep.com/themes/1.0.0/easyui_plus.css"
	rel="stylesheet" type="text/css">
<link href="https://cdn.insdep.com/themes/1.0.0/insdep_theme_default.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="https://cdn.insdep.com/jquery/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="https://cdn.insdep.com/easyui/jquery.easyui-1.5.1.min.js"></script>
<!--<script type="text/javascript" src="js/jquery-easyui/jquery.min.js"></script>-->
<!--<script type="text/javascript" src="js/jquery-easyui/jquery.easyui.min.js"></script>-->
<style>
	*{font-size:12px; font-family:Tahoma,Verdana,微软雅黑,新宋体}
body{background:#D2E0F2; }
a{ color:Black; text-decoration:none;}
a:hover{ color:Red; text-decoration:underline;}
.textbox03 {border: #878787 1px solid;padding: 4px 3px;font:Verdana, Geneva, sans-serif,宋体;line-height: 14px; background-color: #fff;  height: auto; font-size: 14px;  font-weight: bold; width: 190px; }

.txt01{font:Verdana, Geneva, sans-serif,宋体;padding:3px 2px 2px 2px; border-width:1px; border-color:#ddd;  color:#000;}
.txt {border: #878787 1px solid;padding: 4px 3px;font:Verdana, Geneva, sans-serif,宋体;line-height: 14px; background-color: #fff;  height: auto; font-size: 14px;}
.footer{text-align:center;color:#15428B; margin:0px; padding:0px;line-height:23px; font-weight:bold;}

.head a{color:White;text-decoration:underline;}

.easyui-accordion ul{list-style-type:none;margin:0px; padding:10px;}
.easyui-accordion ul li{ padding:0px;}
.easyui-accordion ul li a{line-height:24px;}
.easyui-accordion ul li div{margin:2px 0px;padding-left:10px;padding-top:2px;}
.easyui-accordion ul li div.hover{border:1px dashed #99BBE8; background:#E0ECFF;cursor:pointer;}
.easyui-accordion ul li div.hover a{color:#416AA3;}
.easyui-accordion ul li div.selected{border:1px solid #99BBE8; background:#E0ECFF;cursor:default;}
.easyui-accordion ul li div.selected a{color:#416AA3; font-weight:bold;}

.icon{padding:2px 9px; background:url(../images/tabicons.png) no-repeat; }
.icon-sys{ background:url(../images/directory.png) no-repeat;}
.icon-add{background-position: -19px 0px;}
.icon-nav{background-position: -100px -18px;}
.icon-users{background-position: -100px -480px;}
.icon-role{background-position: -360px -200px;}
.icon-set{background-position: -380px -200px;}
.icon-log{background-position: -380px -80px;}
</style>
<script type="text/javascript">
	var _menus = {
		"menus" : [ {
			"menuid" : "1",
			"icon" : "icon-sys",
			"menuname" : "系统管理",
			"menus" : [ {
				"menuname" : "菜单管理",
				"icon" : "icon-nav",
				"url" : "http://www.16sucai.com"
			}, {
				"menuname" : "添加用户",
				"icon" : "icon-add",
				"url" : "user"
			}, {
				"menuname" : "用户管理",
				"icon" : "icon-users",
				"url" : "demo2.html"
			}, {
				"menuname" : "角色管理",
				"icon" : "icon-role",
				"url" : "demo2.html"
			}, {
				"menuname" : "权限设置",
				"icon" : "icon-set",
				"url" : "demo.html"
			}, {
				"menuname" : "系统日志",
				"icon" : "icon-log",
				"url" : "demo.html"
			} ]
		}, {
			"menuid" : "8",
			"icon" : "icon-sys",
			"menuname" : "员工管理",
			"menus" : [ {
				"menuname" : "员工列表",
				"icon" : "icon-nav",
				"url" : "demo.html"
			}, {
				"menuname" : "视频监控",
				"icon" : "icon-nav",
				"url" : "demo1.html"
			} ]
		}, {
			"menuid" : "56",
			"icon" : "icon-sys",
			"menuname" : "部门管理",
			"menus" : [ {
				"menuname" : "添加部门",
				"icon" : "icon-nav",
				"url" : "demo1.html"
			}, {
				"menuname" : "部门列表",
				"icon" : "icon-nav",
				"url" : "demo2.html"
			} ]
		}, {
			"menuid" : "28",
			"icon" : "icon-sys",
			"menuname" : "财务管理",
			"menus" : [ {
				"menuname" : "收支分类",
				"icon" : "icon-nav",
				"url" : "demo.html"
			}, {
				"menuname" : "报表统计",
				"icon" : "icon-nav",
				"url" : "demo1.html"
			}, {
				"menuname" : "添加支出",
				"icon" : "icon-nav",
				"url" : "demo.html"
			} ]
		}, {
			"menuid" : "39",
			"icon" : "icon-sys",
			"menuname" : "商城管理",
			"menus" : [ {
				"menuname" : "商品分",
				"icon" : "icon-nav",
				"url" : "/shop/productcatagory.aspx"
			}, {
				"menuname" : "商品列表",
				"icon" : "icon-nav",
				"url" : "/shop/product.aspx"
			}, {
				"menuname" : "商品订单",
				"icon" : "icon-nav",
				"url" : "/shop/orders.aspx"
			} ]
		} ]
	};
	
	//设置登录窗口
	function openPwd() {
		$('#w').window({
			title : '修改密码',
			width : 300,
			modal : true,
			shadow : true,
			closed : true,
			height : 160,
			resizable : false
		});
	}
	
	//关闭登录窗口
	function close() {
		$('#w').window('close');
	}

	//修改密码
	function serverLogin() {
		var $newpass = $('#txtNewPass');
		var $rePass = $('#txtRePass');

		if ($newpass.val() == '') {
			msgShow('系统提示', '请输入密码！', 'warning');
			return false;
		}
		if ($rePass.val() == '') {
			msgShow('系统提示', '请在一次输入密码！', 'warning');
			return false;
		}

		if ($newpass.val() != $rePass.val()) {
			msgShow('系统提示', '两次密码不一至！请重新输入', 'warning');
			return false;
		}

		$.post('/ajax/editpassword.ashx?newpass=' + $newpass.val(), function(
				msg) {
			msgShow('系统提示', '恭喜，密码修改成功！<br>您的新密码为：' + msg, 'info');
			$newpass.val('');
			$rePass.val('');
			close();
		})

	}

	$(function() {

		openPwd();
		//
		$('#editpass').click(function() {
			$('#w').window('open');
		});

		$('#btnEp').click(function() {
			serverLogin();
		})

		$('#loginOut').click(function() {
			$.messager.confirm('系统提示', '您确定要退出本次登录吗?', function(r) {

				if (r) {
					location.href = '/ajax/loginout.ashx';
				}
			});

		})

	});

	$(function() {
		//InitLeftMenu();
		tabClose();
		tabCloseEven();
	})

	//初始化左侧
	function InitLeftMenu() {

		// $(".easyui-accordion").empty();
		var menulist = "";

		$.each(_menus.menus,
						function(i, n) {
							menulist += '<div title="'+n.menuname+'"  icon="'+n.icon+'" style="overflow:auto;">';
							menulist += '<ul>';
							$.each(
											n.menus,
											function(j, o) {
												menulist += '<li><div><a target="mainFrame" href="' + o.url + '" ><span class="icon '+o.icon+'" ></span>'
														+ o.menuname
														+ '</a></div></li> ';
											})
							menulist += '</ul></div>';
						})

		$(".easyui-accordion").append(menulist);

		$('.easyui-accordion li a').click(function() {
			var tabTitle = $(this).text();
			var url = $(this).attr("href");
			addTab(tabTitle, url);
			$('.easyui-accordion li div').removeClass("selected");
			$(this).parent().addClass("selected");
		}).hover(function() {
			$(this).parent().addClass("hover");
		}, function() {
			$(this).parent().removeClass("hover");
		});

		$(".easyui-accordion").accordion();
	}

	function addTab(subtitle, url) {
		if (!$('#tabs').tabs('exists', subtitle)) {
			$('#tabs').tabs('add', {
				title : subtitle,
				content : createFrame(url),
				closable : true,
				width : $('#mainPanle').width() - 10,
				height : $('#mainPanle').height() - 26
			});
		} else {
			$('#tabs').tabs('select', subtitle);
		}
		tabClose();
	}

	function createFrame(url) {
		var s = '<iframe name="mainFrame" scrolling="auto" frameborder="0"  src="'
				+ url + '" style="width:100%;height:100%;"></iframe>';
		return s;
	}

	function tabClose() {
		/*双击关闭TAB选项卡*/
		$(".tabs-inner").dblclick(function() {
			var subtitle = $(this).children("span").text();
			$('#tabs').tabs('close', subtitle);
		})

		$(".tabs-inner").bind('contextmenu', function(e) {
			$('#mm').menu('show', {
				left : e.pageX,
				top : e.pageY,
			});

			var subtitle = $(this).children("span").text();
			$('#mm').data("currtab", subtitle);

			return false;
		});
	}
	//绑定右键菜单事件
	function tabCloseEven() {
		//关闭当前
		$('#mm-tabclose').click(function() {
			var currtab_title = $('#mm').data("currtab");
			$('#tabs').tabs('close', currtab_title);
		})
		//全部关闭
		$('#mm-tabcloseall').click(function() {
			$('.tabs-inner span').each(function(i, n) {
				var t = $(n).text();
				$('#tabs').tabs('close', t);
			});
		});
		//关闭除当前之外的TAB
		$('#mm-tabcloseother').click(function() {
			var currtab_title = $('#mm').data("currtab");
			$('.tabs-inner span').each(function(i, n) {
				var t = $(n).text();
				if (t != currtab_title)
					$('#tabs').tabs('close', t);
			});
		});
		//关闭当前右侧的TAB
		$('#mm-tabcloseright').click(function() {
			var nextall = $('.tabs-selected').nextAll();
			if (nextall.length == 0) {
				//msgShow('系统提示','后边没有啦~~','error');
				alert('后边没有啦~~');
				return false;
			}
			nextall.each(function(i, n) {
				var t = $('a:eq(0) span', $(n)).text();
				$('#tabs').tabs('close', t);
			});
			return false;
		});
		//关闭当前左侧的TAB
		$('#mm-tabcloseleft').click(function() {
			var prevall = $('.tabs-selected').prevAll();
			if (prevall.length == 0) {
				alert('到头了，前边没有啦~~');
				return false;
			}
			prevall.each(function(i, n) {
				var t = $('a:eq(0) span', $(n)).text();
				$('#tabs').tabs('close', t);
			});
			return false;
		});

		//退出
		$("#mm-exit").click(function() {
			$('#mm').menu('hide');
		})
	}

	//弹出信息窗口 title:标题 msgString:提示信息 msgType:信息类型 [error,info,question,warning]
	function msgShow(title, msgString, msgType) {
		$.messager.alert(title, msgString, msgType);
	}

	function clockon() {
		var now = new Date();
		var year = now.getFullYear(); //getFullYear getYear
		var month = now.getMonth();
		var date = now.getDate();
		var day = now.getDay();
		var hour = now.getHours();
		var minu = now.getMinutes();
		var sec = now.getSeconds();
		var week;
		month = month + 1;
		if (month < 10)
			month = "0" + month;
		if (date < 10)
			date = "0" + date;
		if (hour < 10)
			hour = "0" + hour;
		if (minu < 10)
			minu = "0" + minu;
		if (sec < 10)
			sec = "0" + sec;
		var arr_week = new Array("星期日", "星期一", "星期二", "星期三", "星期四", "星期五",
				"星期六");
		week = arr_week[day];
		var time = "";
		time = year + "年" + month + "月" + date + "日" + " " + hour + ":" + minu
				+ ":" + sec + " " + week;

		$("#bgclock").html(time);

		var timer = setTimeout("clockon()", 200);
	}
</script>

</head>
<body class="easyui-layout" style="overflow-y: hidden" scroll="no">
	<noscript>
		<div
			style="position: absolute; z-index: 100000; height: 2046px; top: 0px; left: 0px; width: 100%; background: white; text-align: center;">
			<img src="images/noscript.gif" alt='抱歉，请开启脚本支持！' />
		</div>
	</noscript>
	<div region="north" split="true" border="false"
		style="overflow: hidden; height: 30px; background: url(images/layout-browser-hd-bg.gif) #7f99be repeat-x center 50%; line-height: 20px; color: #fff; font-family: Verdana, 微软雅黑, 黑体">
		<span style="float: right; padding-right: 20px;" class="head">欢迎
			<a href="#" id="editpass">修改密码</a> <a href="#" id="loginOut">安全退出</a>
		</span> <span style="padding-left: 10px; font-size: 16px;"><img
			src="images/blocks.gif" width="20" height="20" align="absmiddle" />
			m</span>
	</div>
	<div region="south" split="true"
		style="height: 30px; background: #D2E0F2;">
		<div class="footer"></div>
	</div>
	<div region="west" split="true" title="导航菜单" style="width: 180px;"
		id="west">
        <div class="easyui-accordion" style="width:500px;height:300px;">
            <div title="系统设置" data-options="iconCls:'icon-ok'" style="overflow:auto;padding:10px;">
                <ul class="easyui-tree wu-side-tree">
				<li iconCls="icon-chart-organisation"><a
					href="javascript:void(0)" data-icon="icon-chart-organisation"
					data-link="temp/layout-2.html" iframe="0">菜单导航</a></li>
				<li iconCls="icon-users"><a href="javascript:void(0)"
					data-icon="icon-users" data-link="/user" iframe="0">用户管理</a></li>
				<li iconCls="icon-user-group"><a href="javascript:void(0)"
					data-icon="icon-user-group" data-link="temp/layout-3.html"
					iframe="0">角色管理</a></li>
				<li iconCls="icon-book"><a href="javascript:void(0)"
					data-icon="icon-book" data-link="temp/layout-3.html" iframe="0">数据字典</a></li>
				<li iconCls="icon-cog"><a href="javascript:void(0)"
					data-icon="icon-cog" data-link="temp/layout-3.html" iframe="0">系统参数</a></li>
				<li iconCls="icon-application-osx-error"><a
					href="javascript:void(0)" data-icon="icon-application-osx-error"
					data-link="temp/layout-3.html" iframe="0">操作日志</a></li>
			</ul>
            </div>
             <div title="系统设置" data-options="iconCls:'icon-ok'" style="overflow:auto;padding:10px;">
                <ul class="easyui-tree wu-side-tree">
				<li iconCls="icon-chart-organisation"><a
					href="javascript:void(0)" data-icon="icon-chart-organisation"
					data-link="temp/layout-2.html" iframe="0">菜单导航</a></li>
				<li iconCls="icon-users"><a href="javascript:void(0)"
					data-icon="icon-users" data-link="/user" iframe="0">用户管理</a></li>
				<li iconCls="icon-user-group"><a href="javascript:void(0)"
					data-icon="icon-user-group" data-link="temp/layout-3.html"
					iframe="0">角色管理</a></li>
				<li iconCls="icon-book"><a href="javascript:void(0)"
					data-icon="icon-book" data-link="temp/layout-3.html" iframe="0">数据字典</a></li>
				<li iconCls="icon-cog"><a href="javascript:void(0)"
					data-icon="icon-cog" data-link="temp/layout-3.html" iframe="0">系统参数</a></li>
				<li iconCls="icon-application-osx-error"><a
					href="javascript:void(0)" data-icon="icon-application-osx-error"
					data-link="temp/layout-3.html" iframe="0">操作日志</a></li>
			</ul>
            </div>
             <div title="系统设置" data-options="iconCls:'icon-ok'" style="overflow:auto;padding:10px;">
                <ul class="easyui-tree wu-side-tree">
				<li iconCls="icon-chart-organisation"><a
					href="javascript:void(0)" data-icon="icon-chart-organisation"
					data-link="temp/layout-2.html" iframe="0">菜单导航</a></li>
				<li iconCls="icon-users"><a href="javascript:void(0)"
					data-icon="icon-users" data-link="/user" iframe="0">用户管理</a></li>
				<li iconCls="icon-user-group"><a href="javascript:void(0)"
					data-icon="icon-user-group" data-link="temp/layout-3.html"
					iframe="0">角色管理</a></li>
				<li iconCls="icon-book"><a href="javascript:void(0)"
					data-icon="icon-book" data-link="temp/layout-3.html" iframe="0">数据字典</a></li>
				<li iconCls="icon-cog"><a href="javascript:void(0)"
					data-icon="icon-cog" data-link="temp/layout-3.html" iframe="0">系统参数</a></li>
				<li iconCls="icon-application-osx-error"><a
					href="javascript:void(0)" data-icon="icon-application-osx-error"
					data-link="temp/layout-3.html" iframe="0">操作日志</a></li>
			</ul>
            </div>
           </div>
	</div>
	<div id="mainPanle" region="center"
		style="background: #eee; overflow-y: hidden">
		<div id="tabs" class="easyui-tabs" fit="true" border="false">
			<div title="欢迎使用" style="padding: 20px; overflow: hidden;" id="home">

				<h1>Welcome to jQuery UI!</h1>

			</div>
		</div>
	</div>


	<!--修改密码窗口-->
	<div id="w" class="easyui-window" title="修改密码" collapsible="false"
		minimizable="false" maximizable="false" icon="icon-save"
		style="width: 300px; height: 150px; padding: 5px; background: #fafafa;">
		<div class="easyui-layout" fit="true">
			<div region="center" border="false"
				style="padding: 10px; background: #fff; border: 1px solid #ccc;">
				<table cellpadding=3>
					<tr>
						<td>新密码：</td>
						<td><input id="txtNewPass" type="Password" class="txt01" /></td>
					</tr>
					<tr>
						<td>确认密码：</td>
						<td><input id="txtRePass" type="Password" class="txt01" /></td>
					</tr>
				</table>
			</div>
			<div region="south" border="false"
				style="text-align: right; height: 30px; line-height: 30px;">
				<a id="btnEp" class="easyui-linkbutton" icon="icon-ok"
					href="javascript:void(0)"> 确定</a> <a class="easyui-linkbutton"
					icon="icon-cancel" href="javascript:void(0)" onclick="closeLogin()">取消</a>
			</div>
		</div>
	</div>

	<div id="mm" class="easyui-menu" style="width: 150px;">
		<div id="mm-tabclose">关闭</div>
		<div id="mm-tabcloseall">全部关闭</div>
		<div id="mm-tabcloseother">除此之外全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-tabcloseright">当前页右侧全部关闭</div>
		<div id="mm-tabcloseleft">当前页左侧全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-exit">退出</div>
	</div>


</body>
</html>