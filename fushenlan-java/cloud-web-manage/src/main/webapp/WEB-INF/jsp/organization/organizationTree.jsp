<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 	<title>富卫运维大平台</title>
 	
</head>
<body>
	<link rel="stylesheet" href="${ctx}/resources/css/reset.css">
	<link rel="stylesheet" href="${ctx}/resources/libs/ztree/zTreeStyle.css" type="text/css">
 	<script type="text/javascript" src="${ctx}/resources/libs/ztree/jquery.ztree.core.js"></script>
	<script type="text/javascript" src="${ctx}/resources/libs/ztree/jquery.ztree.excheck.js"></script>
	<script type="text/javascript" src="${ctx}/resources/libs/ztree/jquery.ztree.exedit.js"></script>
	<script type="text/javascript" src="${ctx}/resources/libs/layer/layer.js"></script>
	
	<%-- <shiro:hasPermission name="contract:view">  
		<a onclick="getCheckbox()" >权限控制按钮</a>
	</shiro:hasPermission> --%>
<style>
        .demo-wrapper {
            position: relative;
            width: 100%;
            height: auto;
            overflow: hidden;
            border: 1px solid #ccc;
        }
        .left-zTree {
            float: left;
            width: 30%;
            height: 500px;
            
        }
        .right-table {
            float: left;
            width: 70%;
            height: 500px;
        }
        .tool-box {
            width: 100%;
            height: 36px;
            margin: 25px 0 25px;
            text-align: right;
        }
        .tool-box button {
            width: 60px;
            height: 30px;
            line-height: 30px;
            border-radius: 10px;
            color: #FFFFFF;
            background-color: #74c1ca;
            cursor: pointer;
            outline: none;
        }
        .ui-tabrow button {
            width: 60px;
            height: 100%;
            line-height: 30px;
            border-radius: 10px;
            color: #FFFFFF;
            background-color: #74c1ca;
            cursor: pointer;
            outline: none;
        }
        .mask {
            position: absolute;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            display: none;
            background-color: rgba(0, 0, 0, .6);
        }
        .mask #option-form {
            width: 70%;
            margin: 200px auto;
            background-color: #FFFFFF;
            padding: 44px 30px;
            border-radius: 20px;
        }
        #option-form ul {
            width: 100%;
            overflow: hidden;
        }
        #option-form ul li {
            float: left;
            width: 33.33%;
            height: 38px;
            line-height: 38px;
            display: flex;
            margin-bottom: 20px;
        }
        #option-form li strong {
            width: 40%;
            text-align: right;
        }
        #option-form li button {
            margin: 0;
            width: 80px;
            height: 38px;
            text-align: center;
            line-height: 38px;
            color: #FFFFFF;
            background-color: #FCCD1E;
            border-radius: 10px;
        }
        #option-form li button:first-child {
            margin-right: 30px;
        }
        #option-form li>p {
            width: 55%;
        }
        #option-form li>p input{
            width: 100%;
            border: 1px solid #ccc;
        }
        #option-form .radio {
            width: 20px;
        }

        .ui-table{display:table;width:100%;box-sizing:border-box;border-top:1px solid #ededed}
        .ui-table .ui-tabrow{display:table-row;height: 52px}
        .ui-table .ui-tabcell{display:table-cell;padding:10px 5px;text-align: center;line-height:20px;color:#575757;font-size:16px;border-bottom:1px solid #ededed;vertical-align:middle;box-sizing:border-box}
        .ui-table .ui-table-head{background-image: linear-gradient(0deg, #F5F5F5 0%, #F7F7F7 98%);height: 44px}
        .ui-table .ui-table-head .ui-tabcell{height:36px;padding:0 5px;color:#8a8a8a;font-size:15px;text-align: center}
        .ui-table .ui-tabrow:not(.ui-table-head):nth-child(odd){background-color:#fafafa}
    </style>
    
    
    
    <div class="demo-wrapper">
        <div class="left-zTree"><ul id="treeDemo" class="ztree"></ul>
        </div>
        <div class="right-table" id="resouceDiv">
        	<div>
        		 <form id="option-form-show" <%-- action="${ctx}/updateOrganization" --%> method="post">
		<table class="table">
		    	<tr>
		    		<td>组织编号</td>
		    		<td>
		    		<div>
							保存后系统自动生成<input type="hidden" name="id" value="${organization.id }">
							<input type="hidden" id="parentIdShow" name="parentId" value="${parentId }">
							<input type="hidden" id="layerShow" name="layer" value="${layer }">
							<input type="hidden" id="rootIdShow" name="rootId" value="${rootId }">
					</div>
		    		</td>
		    	<!-- </tr>
		    	<tr class="notContent"> -->
		    		<td>机构代码</td>
		    		<td><input name="code" value="${organization.code }"id="parentCodeShow"/></td>
		    	</tr>
		    	<tr>
		    		<td>机构名称</td>
		    		<td>
		    			<input name="cnName" value="${organization.cnName}" id="parentCnNameShow">				    			
		    		</td>
		    	<!-- </tr>
		    	<tr> -->
		    		<td>外文名称</td>
		    		<td>
		    			<input name="enName" value="${organization.enName }" id="parentEnNameShow">					    			
		    		</td>
		    	</tr>
		    </table>
		</form>
        	</div>
            <div class="tool-box">
                <button type="button" id="insertOrganization" value="" onclick="insertOrganization();">增加</button>
                <!-- <button type="button">查询</button> -->
            </div>
            <div class="table">
                <ul class="ui-table">
                    <li class="ui-tabrow ui-table-head">
                        <p class="ui-tabcell">机构代码</p>
                        <p class="ui-tabcell">机构名称</p>
                        <p class="ui-tabcell">外文名称</p>
                        <p class="ui-tabcell">机构地址</p>
                        <p class="ui-tabcell">邮编</p>
                        <p class="ui-tabcell">电话号码</p>
                        <p class="ui-tabcell">机构描述</p>
                        <p class="ui-tabcell">操作</p>
                    </li>
                    <c:forEach items="${organizations}" var="organization" varStatus="status">
                     <li class="ui-tabrow">
                        <input type="hidden" name="id" value="${organization.id }">
                        <p class="ui-tabcell">${organization.code }</p>
                        <p class="ui-tabcell">${organization.cnName }</p>
                        <p class="ui-tabcell">${organization.enName }</p>
                        <p class="ui-tabcell">${organization.address }</p>
                        <p class="ui-tabcell">${organization.postCode }</p>
                        <p class="ui-tabcell">${organization.phoneNumber }</p>
                        <p class="ui-tabcell go-to-next">${organization.description }</p>
                        <p class="ui-tabcell go-to-next">
                        <button type="button" onclick="deleteOranization('${organization.id }','${organization.code }')">删除</button>
                		<button type="button" onclick="updateOranization('${organization.id }')">修改</button>
                		</p>
                    </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>


    <div class="mask">
        <!-- <form id="option-form"> -->
        <form id="option-form" <%-- action="${ctx}/updateOrganization" --%> method="post">
		<table class="table">
		    	<tr>
		    		<td>组织编号</td>
		    		<td>
		    		<div>
							保存后系统自动生成<input type="hidden" name="id" id="organizationId" value="${organization.id }">
							<input type="hidden" id="parentId" name="parentId" value="${parentId }">
							<input type="hidden" id="layer" name="layer" value="${layer }">
							<input type="hidden" id="rootId" name="rootId" value="${rootId }">
					</div>
		    		</td>
		    	<!-- </tr>
		    	<tr class="notContent"> -->
		    		<td>机构代码</td>
		    		<td><input name="code" id="code" value="${organization.code }"id="organization_title"/></td>
		    	</tr>
		    	<tr>
		    		<td>机构名称</td>
		    		<td>
		    			<input name="cnName" id="cnName" value="${organization.cnName}">				    			
		    		</td>
		    	<!-- </tr>
		    	<tr> -->
		    		<td>外文名称</td>
		    		<td>
		    			<input name="enName" id="enName" value="${organization.enName }">					    			
		    		</td>
		    	</tr>
				<tr>
		    		<td>企业id</td>
		    		<td>
		    			<input name="companyId" id="companyId" value="${organization.companyId }">					    			
		    		</td>
		    	<!-- </tr>
				<tr> -->
		    		<td>机构地址</td>
		    		<td>
		    			<input name="address" id="address" value="${organization.address }">					    			
		    		</td>
		    	</tr>
				<tr>
		    		<td>机构类型</td>
		    		<td>
		    			<input name="type" id="type" value="${organization.type }">					    			
		    		</td>
		    	<!-- </tr>
		    	<tr> -->
		    		<td>是否可用</td>
		    		<td>
		    			<%-- <input name="enabled" id="enabled" value="${organization.enabled }"> --%>
		    			<label for="enabledTrue">是：<input type="radio" name="enabled" id="enabledTrue" value="1"></label>
		    			<label for="enabledFalse">否：<input type="radio" name="enabled" id="enabledFalse" value="0"></label>					    			
		    		</td>
		    	</tr>
				<tr>
		    		<td>邮编</td>
		    		<td>
		    			<input name="postCode" id="postCode" value="${organization.postCode }">					    			
		    		</td>
		    	<!-- </tr>
				<tr> -->
		    		<td>电话号码</td>
		    		<td>
		    			<input name="phoneNumber" id="phoneNumber" value="${organization.phoneNumber }">					    			
		    		</td>
		    	</tr>
				<tr>
		    		<td>传真</td>
		    		<td>
		    			<input name="faxNumber" id="faxNumber" value="${organization.faxNumber }">					    			
		    		</td>
		    	<!-- </tr>
				<tr> -->
		    		<td>机构描述</td>
		    		<td>
		    			<input name="description" id="description" value="${organization.description }">					    			
		    		</td>
		    	</tr>
		    	<tr align="center">
		    		<td colspan="4">
		    		<button type="button" class="btn btn-submit" onclick="saveOrganization()">保存</button>
		    		<button type="button" class="btn btn-default" onclick="cancelOrganization()">取消</button>
		    		</td>
		    	</tr>
		    </table>
		</form>
        <div class="table-list"></div>
    </div>

    <!-- <script src="./jquery-3.2.1.min.js"></script> -->
    <script type="text/javascript">
	//调用layer弹窗
	function dylayer(){
		layer.open({
			  type: 2,
			  title: 'layer mobile页',
			  shadeClose: true,
			  shade: 0.8,
			  area: ['380px', '500px'],
			  content: '${ctx}/layerdemo' //iframe的url
		}); 
	}
	var parentCode;
	
	//加载使用ztree
	var setting = {
		view: {
			addHoverDom: addHoverDom,
			removeHoverDom: removeHoverDom,
			selectedMulti: false
		},
		edit: {
			enable: true,
			editNameSelectAll: true,
			showRemoveBtn: showRemoveBtn,
			showRenameBtn: showRenameBtn
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		callback: {
			beforeDrag: beforeDrag,
			beforeEditName: beforeEditName,
			beforeRemove: beforeRemove,
			beforeRename: beforeRename,
			onRemove: onRemove,
			onClick: zTreeOnClick,
			onRename: onRename
		},
		check: {
			enable: true,
			autoCheckTrigger: true,
			chkStyle: "checkbox",
			chkboxType: { "Y": "ps", "N": "s" },
			nocheckInherit: true,
			chkDisabledInherit: true
		}
		
	};
	
	var zNodes =[
	];
	var log, className = "dark";
	function beforeDrag(treeId, treeNodes) {
		return false;
	}
	function beforeEditName(treeId, treeNode) {
		//编辑界面
		var organizationId=treeNode.id;
		window.location.href="${ctx}/manage/getOrganizationById?organizationId="+organizationId;
	}
	function beforeRemove(treeId, treeNode) {
		var result=confirm("确认删除 节点 -- " + treeNode.name + " 吗？");
		if(result){
			$.ajax({
				  async : false,  
			      cache : false,  
			      type: 'POST',  
		    	  url: "${ctx }/manage/deleteOrganization",
		    	  data: {"id" : treeNode.id},
		    	  dataType: "json",
		    	  success: function(result){
		    		  layer.alert(date.msg);
		    		  if(result.data=="1"){
		    			  window.location.href="${ctx}/manage/organizationJsp"; 
		    		  }
		    	  },
		    	})
		    	return false;
		}
		else{
			return false;
		}
		//return result;
		
	}
	function onRemove(e, treeId, treeNode) {
		showLog("[ "+getTime()+" onRemove ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name);
	}
	function zTreeOnClick(event, treeId, treeNode){
		var organizationId=treeNode.id;
		parentCode=treeNode;
		 $.ajax({
			  async : false,  
		      cache : false,  
		      type: 'POST',
	    	  url: "${ctx }/manage/getOrganizationListByParentId",
	    	  data: {"id" : organizationId},
	    	  dataType: "json",
	    	  success: function(data){
	    		  $(".ui-table li:not(:first)").html("").remove();
	    		  if(data.data.length==0){
	    			  $(".ui-table").append("<li class='ui-tabrow'><p class='ui-tabcell'>当前无子节点</p></li>");
	    		  }else{
	    		  $.each(data.data,function(i,item){
	    			  $(".ui-table").append("<li class='ui-tabrow'><input type='hidden' name='id' value="+item.id+"><p class='ui-tabcell'>"+item.code+"</p><p class='ui-tabcell'>"+item.cnName+"</p><p class='ui-tabcell'>"+item.enName+"</p><p class='ui-tabcell'>"+item.address+"</p><p class='ui-tabcell'>"+item.postCode+"</p><p class='ui-tabcell'>"+item.phoneNumber+"</p><p class='ui-tabcell go-to-next'>"+item.description+"</p><p class='ui-tabcell go-to-next'><button type='button' onclick='deleteOranization(\""+item.id+"\",\""+item.code+"\")'>删除</button><button type='button' onclick='updateOranization(\""+item.id+"\")'>修改</button></p></li>");
	    		  })
	    		  }
	    		  
	    		  showOrganization(organizationId);
	    		  
	    	  }
	    	}) 
	}
	
	function showOrganization(organizationId){
		 $.ajax({
			  async : false,  
		      cache : false,  
		      type: 'POST',
	    	  url: "${ctx }/manage/getOrganizationById",
	    	  data: {"organizationId" : organizationId},
	    	  dataType: "json",
	    	  success: function(result){
	    		  $("#parentCodeShow").val(result.data.code);
	    		  $("#parentCnNameShow").val(result.data.cnName);
	    		  $("#parentEnNameShow").val(result.data.enName);
	    	  }
	    	}) 
	}
	
	
	
	function beforeRename(treeId, treeNode, newName, isCancel) {
		className = (className === "dark" ? "":"dark");
		showLog((isCancel ? "<span style='color:red'>":"") + "[ "+getTime()+" beforeRename ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name + (isCancel ? "</span>":""));
		if (newName.length == 0) {
			setTimeout(function() {
				var zTree = $.fn.zTree.getZTreeObj("treeDemo");
				zTree.cancelEditName();
				alert("节点名称不能为空.");
			}, 0);
			return false;
		}
		return true;
	}
	function onRename(e, treeId, treeNode, isCancel) {
		showLog((isCancel ? "<span style='color:red'>":"") + "[ "+getTime()+" onRename ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name + (isCancel ? "</span>":""));
	}
	function showRemoveBtn(treeId, treeNode) {
		return false;
	}
	function showRenameBtn(treeId, treeNode) {
		return false;
	}
	function showLog(str) {
		if (!log) log = $("#log");
		log.append("<li class='"+className+"'>"+str+"</li>");
		if(log.children("li").length > 8) {
			log.get(0).removeChild(log.children("li")[0]);
		}
	}
	function getTime() {
		var now= new Date(),
		h=now.getHours(),
		m=now.getMinutes(),
		s=now.getSeconds(),
		ms=now.getMilliseconds();
		return (h+":"+m+":"+s+ " " +ms);
	}
	
	var newCount = 1;
	function addHoverDom(treeId, treeNode) {
		//新增界面
		var sObj = $("#" + treeNode.tId + "_span");
		if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
		var btn = $("#addBtn_"+treeNode.tId);
		if (btn) btn.bind("click", function(){
			var organizationId = treeNode.layer;
			var layer = treeNode.id;
			var rootId = treeNode.rootId;
			window.location.href="${ctx}/manage/getOrganizationParent?id="+organizationId+"&layer="+layer+"&rootId="+rootId;
		}); 
	};
	function removeHoverDom(treeId, treeNode) {
		$("#addBtn_"+treeNode.tId).unbind().remove();
	};
	function selectAll() {
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		zTree.setting.edit.editNameSelectAll =  $("#selectAll").attr("checked");
	}
	
	$(document).ready(function(){
		$.post("${ctx}/manage/getAllOrganizationList",function(result){
			$.each(result,function(index,organization){
				var zNode = {id:organization.id,pId:organization.parentId,name:organization.code,layer:organization.layer,rootId:organization.rootId, open:false};
				zNodes.push(zNode);
			});
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			$("#selectAll").bind("click", selectAll);
		}); 
	});
	//获取树checkbox节点
	function getCheckbox(){
		var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
		var nodes = treeObj.getCheckedNodes(true);
		$.each(nodes,function(i,treeNode){
			alert(treeNode.name);
		})
	}
	
	
	function saveOrganization(){
		var code=$("#code").val();
		if(code=="" || code==null){
			layer.msg('机构代码不能为空', function(){
				return;
				});
			return;
		}
		var cnName=$("#cnName").val();
		if(cnName=="" || cnName==null){
			layer.msg('机构名称不能为空', function(){
				return;
				});
			return;
		}
	    var enName=$("#enName").val();
	    if(enName=="" || enName==null){
			layer.msg('外文名称不能为空', function(){
				return;
				});
			return;
		}
		
		$.ajax({
	  	  url: "${ctx }/manage/insertAndUpdateOrganization",
	  	  dataType: "json",
	  	  data :$('#option-form').serialize(),
	  	  success: function(result) {
			if (result.code=="true") {
					$('.mask').hide();
					var parentId=$("#parentId").val();
					if(null!=parentId||""!=parentId){
						historyClick(parentId);
					}
			}else{
				layer.alert("保存失败!"+result.msg, {icon: 1});
				canSave=0;
			}
		  }
	  	})
	}
	
	function historyClick(parentId){
		var organizationId=parentId;
		 $.ajax({
			  async : false,  
		      cache : false,  
		      type: 'POST',
	    	  url: "${ctx }/manage/getOrganizationListByParentId",
	    	  data: {"id" : organizationId},
	    	  dataType: "json",
	    	  success: function(data){
	    		  $(".ui-table li:not(:first)").html("").remove();
	    		  if(data.data.length==0){
	    			  $(".ui-table").append("<li class='ui-tabrow'><p class='ui-tabcell'>当前无子节点</p></li>");
	    		  }else{
	    		  $.each(data.data,function(i,item){
	    			  $(".ui-table").append("<li class='ui-tabrow'><input type='hidden' name='id' value="+item.id+"><p class='ui-tabcell'>"+item.code+"</p><p class='ui-tabcell'>"+item.cnName+"</p><p class='ui-tabcell'>"+item.enName+"</p><p class='ui-tabcell'>"+item.address+"</p><p class='ui-tabcell'>"+item.postCode+"</p><p class='ui-tabcell'>"+item.phoneNumber+"</p><p class='ui-tabcell go-to-next'>"+item.description+"</p><p class='ui-tabcell go-to-next'><button type='button' onclick='deleteOranization(\""+item.id+"\",\""+item.code+"\")'>删除</button><button type='button' onclick='updateOranization(\""+item.id+"\")'>修改</button></p></li>");
	    		  })
	    		  }
	    		  
	    		  showOrganization(organizationId);
	    		  
	    	  }
	    	}) 
	}
	
	
	
	function deleteOranization(id,code){
		var result=layer.msg('确认删除 节点 -- ' + code + ' 吗？', {
			  time: 0 //不自动关闭
			  ,btn: ['确定', '取消']
			  ,yes: function(index){
				  if(result){
						$.ajax({
							  async : false,  
						      cache : false,  
						      type: 'POST',  
					    	  url: "${ctx }/manage/deleteOrganization",
					    	  data: {"id" :id},
					    	  dataType: "json",
					    	  success: function(result){
					    		  layer.alert(result.msg);
					    		  if(result.data=="1"){
					    			  //parent.location.href="${ctx}/manage/organizationJsp";
					    			  var parentId=parentCode.id;
										if(null!=parentId||""!=parentId){
											historyClick(parentId);
										}
					    			  
					    			  
					    			  
					    		  }
					    	  },
					    	  
					    	})
					}			    
			  }
			});
	}
	
	
	function updateOranization(id){
		$.ajax({
			  async : false,  
		      cache : false,  
		      type: 'POST',
	    	  url: "${ctx }/manage/getOrganizationById",
	    	  data: {"organizationId" : id},
	    	  dataType: "json",
	    	  success: function(result){
	    		  $("#organizationId").val(result.data.id);
	    		  $("#code").val(result.data.code);
	    		  $("#parentId").val(result.data.parentId);
	    		  $("#cnName").val(result.data.cnName);
	    		  $("#enName").val(result.data.enName);
	    		  $("#companyId").val(result.data.companyId);
	    		  $("#address").val(result.data.address);
	    		  $("#type").val(result.data.type);
	    		  if(result.data.enabled){
	    			  $("#enabledTrue").prop("checked",true);
	    		  }else{
	    			  $("#enabledFalse").prop("checked",true);
	    		  }
	    		  $("#postCode").val(result.data.postCode);
	    		  $("#phoneNumber").val(result.data.phoneNumber);
	    		  $("#faxNumber").val(result.data.faxNumber);
	    		  $("#description").val(result.data.description);
	    	  }
	    	})
		$('.mask').show();
	}
	
	function insertOrganization(){
		if(null!=parentCode){
			$("#parentId").val(parentCode.id);
			$("#rootId").val(parentCode.rootId);
			$("#layer").val(parentCode.layer);
		}
		 $('.mask').show();
	}
	
	function cancelOrganization(){
		$('.mask').hide();
	}
	

    </script>
</body>
</html>