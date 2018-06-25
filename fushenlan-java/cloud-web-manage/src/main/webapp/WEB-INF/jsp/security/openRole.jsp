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
	<link rel="stylesheet" href="${ctx}/resources/libs/ztree/zTreeStyle.css" type="text/css">
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/style.css">
	<script type="text/javascript" src="${ctx}/resources/js/common/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="${ctx}/resources/libs/ztree/jquery.ztree.core.js"></script>
	<script type="text/javascript" src="${ctx}/resources/libs/ztree/jquery.ztree.excheck.js"></script>
	<script type="text/javascript" src="${ctx}/resources/libs/ztree/jquery.ztree.exedit.js"></script>
	<script type="text/javascript" src="${ctx}/resources/libs/layer/layer.js"></script>
	<form action="" id="resouceForm" name="resouceForm" method="post">
		<div style="float: left; width: 35%;height: 100%; margin-top: 20px;">
			<div style="float: left; margin-left: 30px;">
				<ul id="treeDemo" class="ztree"></ul>
			</div>
			<div style="margin-top: 80%; margin-left: 30px;">
				<button type="button" class="btn btn-submit btn-radius btn-search" onclick="saveAccountRole('${accountId}')">赋权</button><br /><br />
				<div style="font-size: 12px;">
					<p><span style="color: red; font-size: 20px;">*</span>请注意：</p>
					<p style="margin-left: 30px;">1.二级菜单为赋权的角色,一级菜单无法赋权；</p>
					<p style="margin-left: 30px;">2.只选择一级菜单时，默认选择该菜单下的所有角色。</p>
				</div>
			</div>
		</div>
		<div style="width: 65%; float: left; height:200px; overflow-y:auto; margin-top: 20px;"" id="resouceDiv">
			
		</div><br><br>
		
	</form>
	<script type="text/javascript">
		//调用layer弹窗
		function dylayer2() {
			layer.open({
				type : 2,
				title : 'layer mobile页',
				shadeClose : true,
				shade : 0.8,
				area : [ '380px', '500px' ],
				content : '${ctx}/layerdemo?flag=2' //iframe的url
			});
		}

		//加载使用ztree
		var setting = {
			view : {
				selectedMulti : false
			},
			edit : {
				enable : true,
				editNameSelectAll : true,
				showRemoveBtn : showRemoveBtn,
				showRenameBtn : showRenameBtn
			},
			data : {
				simpleData : {
					enable : true
				}
			},
			callback : {
				beforeDrag : beforeDrag,
				onClick : zTreeOnClick,
				onRename : onRename
			},
			check : {
				enable : true,
				autoCheckTrigger : true,
				chkStyle : "checkbox",
				chkboxType : {
					"Y" : "ps",
					"N" : "s"
				},
				nocheckInherit : true,
				chkDisabledInherit : true
			}

		};
		//不显示删除按钮
		function showRemoveBtn(treeId, treeNode) {
			return false;
		}
		//不显示编辑按钮
		function showRenameBtn(treeId, treeNode) {
			return false;
		}
		var zNodes = [
		
		];
		var log, className = "dark";
		function beforeDrag(treeId, treeNodes) {
			return false;
		}
		
		function zTreeOnClick(event, treeId, treeNode) {
			//alert(treeNode.id); 
			var html = "<table style='border-collapse:collapse; border-spacing:0;'>"+
				"<tr style='height:10px;' ><td align='center' colspan='2'>该角色的资源列表</td></tr>"+
				"<tr style='height:10px;'>"+
					"<th align='center' style='width: 200px; border: 1px solid #e3e3e3;'>资源名称</th>"+
					"<th align='center' style='width: 400px;border: 1px solid #e3e3e3;'>资源Url</th>"+
				"</tr>";
			 $.ajax({
		    	  type: 'post',
		    	  url: "${ctx }/manageAccounts/findResourceByRoleId",
		    	  dataType: "json",
		    	  data: {"roleId":treeNode.id},
		    	  success: function (result) {//请求失败处理函数  
		    		  $.each(result,function(n,value) {   
		    	           //alert(n+' '+value.resourceName); 
		    	           html += "<tr style='height:20px;'>"+
			    	           		"<td align='center'  style='width: 200px;border: 1px solid #e3e3e3; '>" + value.resourceName + "</td>"+
			    	           		"<td align='center'  style='width: 400px;border: 1px solid #e3e3e3; '>"+ value.url + "</td>"+
		    	           		"</tr>";       
		    	           });
		    		  	html +="</table>"
		    			$("#resouceDiv").html(html); 
		    	       
			        },
			      error:function (data) {//请求失败处理函数  
			        	alert("失败");
			        },
		    	});
			
		
		}
		
		function onRename(e, treeId, treeNode, isCancel) {
			showLog((isCancel ? "<span style='color:red'>" : "") + "[ "
					+ getTime() + " onRename ]&nbsp;&nbsp;&nbsp;&nbsp; "
					+ treeNode.name + (isCancel ? "</span>" : ""));
		}
		function showLog(str) {
			if (!log)
				log = $("#log");
			log.append("<li class='"+className+"'>" + str + "</li>");
			if (log.children("li").length > 8) {
				log.get(0).removeChild(log.children("li")[0]);
			}
		}
		function getTime() {
			var now = new Date(), h = now.getHours(), m = now.getMinutes(), s = now
					.getSeconds(), ms = now.getMilliseconds();
			return (h + ":" + m + ":" + s + " " + ms);
		}

		var newCount = 1;
		function addHoverDom(treeId, treeNode) {
			var sObj = $("#" + treeNode.tId + "_span");
			if (treeNode.editNameFlag
					|| $("#addBtn_" + treeNode.tId).length > 0)
				return;
			/* var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
					+ "' title='add node' onfocus='this.blur();'></span>";
			sObj.after(addStr); */
			var btn = $("#addBtn_" + treeNode.tId);
			if (btn)
				btn.bind("click", function() {
					var zTree = $.fn.zTree.getZTreeObj("treeDemo");
					zTree.addNodes(treeNode, {
						id : (100 + newCount),
						pId : treeNode.id,
						name : "new node" + (newCount++)
					});
					return false;
				});
		};  
		function selectAll() {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			zTree.setting.edit.editNameSelectAll = $("#selectAll").attr(
					"checked");
		}
		$(document).ready(function() {
				var array = {};
				<c:forEach items="${roleByAccountId}" var="role">
					array["${role.roleId}"] = "";
				</c:forEach>
				//alert(array);
					$.post("${ctx}/manageAccounts/findRoleAndResource",
							function(result) {
								//console.log(result);
								var resultJson = eval(result);
								$.each(resultJson, function(index, resource) {
									var zNode ={};
									if(array[resource.id] == ""){
										zNode= {id:resource.id,pId:resource.parentId,name:resource.roleName, open:false, checked : true};
									}else{
										zNode= {id:resource.id,pId:resource.parentId,name:resource.roleName, open:false};
									}
									zNodes.push(zNode);
								});
								$.fn.zTree.init($("#treeDemo"), setting, zNodes);
								$("#selectAll").bind("click", selectAll);
							});

				});
	</script>
	<script type="text/javascript">
		function saveAccountRole(accountId) {
			var check_val = [];
			var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
			var nodes = treeObj.getCheckedNodes(true);
			v="";
            for(var i=0;i<nodes.length;i++){
	            v+=nodes[i].name + ",";
	            if(nodes[i].level!=0){
	            	check_val.push(nodes[i].id);//获取选中节点的值
	            }
	          
            }
            var jsonStr = {};
			for (var i = 0; i < check_val.length; i++) {
				jsonStr[i] = check_val[i];
			}
            var jsonstr = JSON.stringify(jsonStr);
            if (jsonstr == "{}" || jsonstr == {}) {
				layer.msg('角色权限不能为空！');
				return;
			}
            //调用父类里的方法
            parent.saveRole(accountId,jsonstr); 
		}
	</script>
</body>
</html>