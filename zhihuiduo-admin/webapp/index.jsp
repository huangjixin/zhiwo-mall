<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>智惠多后台首页</title>
<link href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css" rel="stylesheet" type="text/css">
<link href="http://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="http://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css">
<link href="https://cdn.bootcss.com/bootstrap/4.0.0-alpha.6/css/bootstrap-reboot.min.css" rel="stylesheet" type="text/css">
<link href="https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link href="${ctx}/js/jquery-easyui/themes/insdep/easyui.css" rel="stylesheet" type="text/css">
<link href="${ctx}/js/jquery-easyui/themes/insdep/easyui_animation.css" rel="stylesheet" type="text/css">
<link href="${ctx}/js/jquery-easyui/themes/insdep/easyui_plus.css" rel="stylesheet" type="text/css">
<link href="${ctx}/js/jquery-easyui/themes/insdep/insdep_theme_default.css" rel="stylesheet" type="text/css">
<link href="${ctx}/js/jquery-easyui/themes/insdep/icon.css" rel="stylesheet" type="text/css">
<link href="https://cdn.bootcss.com/Swiper/3.4.2/css/swiper.min.css" rel="stylesheet" type="text/css">
<link href="https://cdn.bootcss.com/jquery.bootstrapvalidator/0.5.3/css/bootstrapValidator.min.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="https://cdn.bootcss.com/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery-easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery-easyui/themes/insdep/jquery.insdep-extend.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery-easyui/ajaxfileupload.js"></script>
<script type="text/javascript" src="https://cdn.bootcss.com/jquery.bootstrapvalidator/0.5.3/js/bootstrapValidator.min.js"></script>
<!--<link rel="stylesheet" type="text/css" href="easyui/1.3.4/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="css/wu.css" />
<link rel="stylesheet" type="text/css" href="css/icon.css" />
<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="easyui/1.3.4/jquery.easyui.min.js"></script>
<script type="text/javascript" src="easyui/1.3.4/locale/easyui-lang-zh_CN.js"></script>-->
<style>
	.header{
		height:55px; 
		position:relative; 
		z-index:0; 
		overflow:hidden; 
		border-bottom:1px #95b8e7 solid;
		background:url(../images/bg_header.jpg) bottom repeat-x;
	}
	.header-left { position:absolute; z-index:1; left:15px; bottom:20px;}
	.header-left h1 { font:20px/20px Verdana; color:#fff;}
	.header-right { position:absolute; z-index:1; right:5px; bottom:0; color:#fff; text-align:right;}
	.header-right p { line-height:0.7em;}
	.header-right a { color:#fff; margin:0 5px;}
</style>
</head>
<body class="easyui-layout">   
    <div data-options="region:'north',title:'',split:false" class="header">
    	<div class="header-left">
        	<h1>智惠多后台管理</h1>
        </div>
        <div class="header-right">
        	<div><strong class="easyui-tooltip" title="2条未读消息"><shiro:principal/></strong>，欢迎您！</div>
            <div><a href="#">网站首页</a>|<a href="#">支持论坛</a>|<a href="#">帮助中心</a>|<a href="${ctx}/logout">安全退出</a></div>
        </div>
    </div>
    <div data-options="region:'west',title:'',split:true" style="width:200px;">
    	<table id="menuTreegrid" title="" class="easyui-treegrid" 
        	data-options="
								url: '${ctx}/resources/getMenu',
								fit:true,
								method: 'get',
								rownumbers: false,
								idField: 'id',
								collapsible:true,
								treeField: 'name',
								showHeader: true,
								lines: true,
								singleSelect : true,
								fitColumns:true,
                                onLoadSuccess:function(row,data){
                                 	$('#menuTreegrid').treegrid('collapseAll');
                                },onClickRow:function(row){
                                	if(row.path && ''!=row.path){
                                    	var url = '${ctx}/'+row.path;
                                    	$('#mainIframe').attr('src',url);
                                        
                                        
                                    }
                                }
							">
        <thead>
            <tr>
                <th field="name" width="160">菜单名称</th>
            </tr>
        </thead>
      </table>
    </div>   
    <div data-options="region:'center',title:''" style="padding:5px;background:#eee;">
    	<iframe id="mainIframe" scrolling="auto" frameborder="0" style="width:100%;height:100%;"></iframe>
    </div>   
<!--</body> 
<body class="easyui-layout">
	<div class="header" data-options="region:'north',border:false,split:true">
    	<div class="header-left">
        	<h1>智惠多后台管理</h1>
        </div>
        <div class="header-right">
        	<p><strong class="easyui-tooltip" title="2条未读消息"><shiro:principal/></strong>，欢迎您！</p>
            <p><a href="#">网站首页</a>|<a href="#">支持论坛</a>|<a href="#">帮助中心</a>|<a href="${ctx}/logout">安全退出</a></p>
        </div>
    </div>
	<div class="sidebar" data-options="region:'west',split:true,border:true,title:'导航菜单'"> 
    	
    </div>	
   
    <div class="main" data-options="region:'center'">
        <div id="tabs" class="easyui-tabs" data-options="border:false,fit:true">  
            <div title="首页" data-options="href:'temp/layout-1.html',closable:false,iconCls:'icon-tip',cls:'pd3'"></div>
        </div>
    </div>
    
	<div class="footer" data-options="region:'south',border:true,split:true">
    	
    </div>-->
    <!-- end of footer -->  
    <script type="text/javascript">
		$(function(){			
			$('.side-tree a').bind("click",function(){
				var title = $(this).text();
				var url = $(this).attr('data-link');
				var iconCls = $(this).attr('data-icon');
				var iframe = $(this).attr('iframe')==1?true:false;
				addTab(title,url,iconCls,iframe);
			});	
		})
		
		/**
		* Name 载入树形菜单 
		*/
		$('#side-tree').tree({
			url:'temp/menu.php',
			cache:false,
			onClick:function(node){
				var url = node.attributes['url'];
				if(url==null || url == ""){
					return false;
				}
				else{
					addTab(node.text, url, '', node.attributes['iframe']);
				}
			}
		});
		
		/**
		* Name 选项卡初始化
		*/
		$('#tabs').tabs({
			tools:[{
				iconCls:'icon-reload',
				border:false,
				handler:function(){
					$('#datagrid').datagrid('reload');
				}
			}]
		});
			
		/**
		* Name 添加菜单选项
		* Param title 名称
		* Param href 链接
		* Param iconCls 图标样式
		* Param iframe 链接跳转方式（true为iframe，false为href）
		*/	
		function addTab(title, href, iconCls, iframe){
			var tabPanel = $('#tabs');
			if(!tabPanel.tabs('exists',title)){
				var content = '<iframe scrolling="auto" frameborder="0"  src="'+ href +'" style="width:100%;height:100%;"></iframe>';
				if(iframe){
					tabPanel.tabs('add',{
						title:title,
						content:content,
						iconCls:iconCls,
						fit:true,
						cls:'pd3',
						closable:true
					});
				}
				else{
					tabPanel.tabs('add',{
						title:title,
						href:href,
						iconCls:iconCls,
						fit:true,
						cls:'pd3',
						closable:true
					});
				}
			}
			else
			{
				tabPanel.tabs('select',title);
			}
		}
		/**
		* Name 移除菜单选项
		*/
		function removeTab(){
			var tabPanel = $('#tabs');
			var tab = tabPanel.tabs('getSelected');
			if (tab){
				var index = tabPanel.tabs('getTabIndex', tab);
				tabPanel.tabs('close', index);
			}
		}
	</script>
</body>
</html>