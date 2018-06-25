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
 	<link rel="stylesheet" href="${ctx}/resources/css/reset.css">
 	
 	
    
    
</head>

<body>


<style>
.table {
    background-color: #fff;
    border-radius: 5px;
    float: left;
    min-height: 60%;
    background: #FFFFFF;
    border: 1px solid #bfbfbf;
    border-radius: 8px;
    width: 100%;
} 
        .demo-wrapper {
            position: relative;
            width: 100%;
            overflow: hidden;
            background: #FFFFFF;
        }
        .left-zTree {
            float: left;
            width: 30%;
            height: 30%;
            overflow: auto;
            background: #FFFFFF;
            border: 1px solid #bfbfbf;
    	    border-radius: 8px;
    	   	margin-bottom: 20px;
        }
        .right-table {
            float: left;
            width: 68%;
            height: 30%;
            overflow: auto;
            background: #FFFFFF;
            border: 1px solid #bfbfbf;
    	    border-radius: 8px;
        }
        .tool-box {
            width: 100%;
            height: 36px;
            text-align: right;
        }
        .tool-box button {
            width: 80px;
            height: 100%;
            line-height: 36px;
            border-radius: 4px;
            color: #FFFFFF;
            background-color: #FCCD1E;
            cursor: pointer;
            outline: none;
            margin:8px 10px;
        }
        /* ui-tabrow button {
            width: 80px;
            height: 100%;
            line-height: 36px;
            border-radius: 10px;
            color: #FFFFFF;
            background-color: #FCCD1E;
            cursor: pointer;
            outline: none;
        } */
        .mask {
            position: absolute;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            display: none;
            background-color: rgba(0, 0, 0, .6);
        }
        .mask #Dictionary{
        	position: absolute;
    		width: 60%;
    		top: 50%;
    		left: 50%;
    		transform: translate(-50%,-50%);
        }
        .mask #Dictionary .table tr td{
        	height:60px;
        }
        .mask #Dictionary .table tr td input{
        	border:1px solid #999;
        }
        .mask #Dictionary .table tr td .sure,.mask #Dictionary .table tr td .cancel{
        	width: 80px;
		    height: 100%;
		    line-height: 36px;
		    border-radius: 10px;
		    color: #FFFFFF;
		    background-color: #FCCD1E;
		    cursor: pointer;
        }
        .mask #Dictionary .table tr td .cancel{
        	background-color: #999;
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
        input.ipt-text {
		    width: 100%;
		    font-size: 14px;
		    line-height: 36px;
		    padding: 0 10px;
		    border: 1px solid #dadada;
		    background-color: #fff;
		    border-radius: 3px;
		    box-sizing: border-box;
		}

        .ui-table{display:table;width:96%;box-sizing:border-box;border-top:1px solid #ededed}
        .ui-table .ui-tabrow{display:table-row;height: 52px}
        .ui-table .ui-tabcell{display:table-cell;padding:10px 5px;text-align: center;line-height:20px;color:#575757;font-size:16px;border-bottom:1px solid #ededed;vertical-align:middle;box-sizing:border-box}
        .ui-table .ui-table-head{background-image: linear-gradient(0deg, #F5F5F5 0%, #F7F7F7 98%);height: 44px}
        .ui-table .ui-table-head .ui-tabcell{height:36px;padding:0 5px;color:#8a8a8a;font-size:15px;text-align: center}
        .ui-table .ui-tabrow:not(.ui-table-head):nth-child(odd){background-color:#fafafa}
    </style>






 	<link rel="stylesheet" href="${ctx}/resources/libs/ztree/zTreeStyle.css" type="text/css">
 	
	<script type="text/javascript" src="${ctx}/resources/libs/ztree/jquery.ztree.core.js"></script>
	<script type="text/javascript" src="${ctx}/resources/libs/ztree/jquery.ztree.excheck.js"></script>
	<script type="text/javascript" src="${ctx}/resources/libs/ztree/jquery.ztree.exedit.js"></script>
	<script type="text/javascript" src="${ctx}/resources/libs/layer/layer.js"></script>
	
	<%-- <shiro:hasPermission name="contract:view">  
		<a onclick="getCheckbox()" >权限控制按钮</a>
	</shiro:hasPermission>
	 --%>
	
	
	 <div class="demo-wrapper">
        <div class="left-zTree"><ul id="treeDemo" class="ztree"></ul>
        </div>
        <div class="right-table" id="dictionaryDiv">
        <div class="mask1">
        <form id="DictionarySelf"  method="post">
        <input type="hidden" name="id" id="dictionaryId" value="">
         <table class="tableSelf" style="border-collapse:separate; border-spacing:10px;">
		    	<tr class="notContent">
		    		<td><strong>字典code</strong></td>
		    		<td><input name="code" class="ipt-text" value=""id="codeSelf"/></td>
		    	
		    		<td><strong>中文名称</strong></td>
		    		<td>
		    			<input name="cnName" class="ipt-text" id="cnNameSelf" value="">				    			
		    		</td>
		    	</tr>
		    	<tr>
		    		<td><strong>英文名称</strong></td>
		    		<td>
		    			<input name="enName" class="ipt-text"  id ="enNameSelf" value="">					    			
		    		</td>
		    	
		    		<td><strong>字典取值</strong></td>
		    		<td>
		    			<input name="value" class="ipt-text" id="valueSelf" value="">					    			
		    		</td>
		    	</tr>
				<tr>
		    		<td><strong>同级排序</strong></td>
		    		<td>
		    			<input name="sort" class="ipt-text"  id="sortSelf" value="">					    			
		    		</td>
		    	
		    		<td><strong>字典描述</strong></td>
		    		<td>
		    			<input name="description" class="ipt-text"  id="description" value="">					    			
		    		</td>
		    </table> 
		    
		    </form>
        </div>
         
            <div class="tool-box">
            <button type="button" onclick= "saveDicyionarySelf()">保存</button>
		    <button type="button" onclick= "addDictionary()">增加</button>
            </div>
            
        </div>
	<div class="table" >
                <ul class="ui-table" id="ul1">
                    <li class="ui-tabrow ui-table-head">
                        <p class="ui-tabcell">字典code</p>
                        <p class="ui-tabcell">中文名称</p>
                        <p class="ui-tabcell">英文名称</p>
                        <p class="ui-tabcell">字典取值</p>
                        <p class="ui-tabcell">同级排序</p>
                        <p class="ui-tabcell">操作</p>
                        
                    </li>
                    
                    <c:forEach items="${data}" var="dictionary" >
                    <li class="ui-tabrow">
                    	<input type="hidden" name="field＿name" value="${dictionary.id}">
                        <p class="ui-tabcell" id="code">${dictionary.code}</p>
                        <p class="ui-tabcell">${dictionary.cnName }</p>
                        <p class="ui-tabcell">${dictionary.enName }</p>
                        <p class="ui-tabcell">${dictionary.value }</p>
                        <p class="ui-tabcell">${dictionary.sort }</p>
                        <p class="tool-box" style="text-align:center">
                        <button type="button" onclick="deledDictionary('${dictionary.id }','${dictionary.code }')">删除</button>
                		<button type="button"  onclick="selectDictionary('${dictionary.id }')">修改</button>
                        </p>
                        
                    </li>
                    </c:forEach>
                </ul>
             </div>
            </div>
    <div class="mask">
        <form id="Dictionary" <%-- action="${ctx}/updateOrganization" --%> method="post">
		<table class="table">
		   <input type="hidden" name="id" id="dictionaryOtherId" value="">
		     <input name="parentId" type="hidden" value=""id="parentId"/>
		    	<tr class="notContent">
		    		<td>字典code</td>
		    		<td><input name="code" value=""id="code1"/></td>
		    	
		    		<td>中文名称</td>
		    		<td>
		    			<input name="cnName" id="cnName1" value="">				    			
		    		</td>
		    	</tr>
		    	<tr>
		    		<td>英文名称</td>
		    		<td>
		    			<input name="enName"  id ="enName1" value="">					    			
		    		</td>
		    	
		    		<td>字典取值</td>
		    		<td>
		    			<input name="value" id="value1" value="">					    			
		    		</td>
		    	</tr>
				<tr>
		    		<td>同级排序</td>
		    		<td>
		    			<input name="sort"  id="sort1" value="">					    			
		    		</td>
		    	
		    		<td>字典描述</td>
		    		<td>
		    			<input name="description1"  id="description" value="">					    			
		    		</td>
		    	
		    	
		    	
		    	<tr align="center">
		    		<td colspan="2">
		    		<button type="button" onclick="saveDictionzry()" class="sure">确定</button>
		    		</td>
		    		<td colspan="2">
		    		<button type="button" onclick="closeWindos()" class="cancel">取消</button>
		    		</td>
		    		
		    	</tr>
		    </table>
		</form>
        
        
        
        <div class="table-list"></div>
    </div>
    
   
	
	
	
	
	
	
	
	
	
<script type="text/javascript">

/* $('.tool-box button').on('click', function () {
    $('.mask').show();
}); */

$('.submit-confirm').on('click', function () {
    $('.mask').hide();
});

$('.submit-cancel').on('click', function () {
    $('.mask').hide();
});
	var code = "";
	var cnName = "";
	var enName = "";
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
	//加载使用ztree
	var setting = {
		view: {
			//addHoverDom: addHoverDom,
			removeHoverDom: removeHoverDom,
			selectedMulti: false
		},
		edit: {
			//enable: true,
			//editNameSelectAll: true,
			//showRemoveBtn: showRemoveBtn,
			//showRenameBtn: showRenameBtn
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		callback: {
			beforeDrag: beforeDrag,
			//beforeEditName: beforeEditName,
			//beforeRemove: beforeRemove,
			//beforeRename: beforeRename,
			beforeClick: beforeClick,
			//onRemove: onRemove,
			onClick: zTreeOnClick,
			//onRename: onRename
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
	
	//查询单个字典信
	var zNodes =[];
	function beforeClick(treeId, treeNode){
		/* var parentId = treeNode.id;
		var zTree = $.fn.zTree.getZTreeObj(treeId);
		var nodes = zTree.getSelectedNodes();
		if(nodes.length==0){
			$.ajax({  
		        async : false,  
		        cache : false,  
		        type: 'GET',  
		        dataType : "json",  
		        url: "${ctx}/dictionary/selectOneById?id="+parentId,//请求的action路径  
		        error: function () {//请求失败处理函数  
		           layer.msg("查询失败") 
		        },  
		        success:function(data){ //请求成功后处理函数。
		        	if(data.length>0){
			            $.each(data,function(index,resource){
			            	var zNode = {id:resource.id,pId:resource.parentId,name:resource.cnName, open:false};
							zNodes.push(zNode);
			            });
		        		$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		        	}
		        }  
		    });  
		} */
		$("#parentId").val(treeNode.id);
		var id = treeNode.id
			$.ajax({  
		        async : false,  
		        cache : false,  
		        type: 'Get',  
		        dataType : "json",
		        url: "${ctx}/manage/dictionary/findChilds?id="+id,
		        error: function () {//请求失败处理函数  
		           layer.msg("查询失败") 
		        },  
		        success:function(data){ //请求成功后处理函数。
		        	 $(".ui-table li:not(:first)").html("").remove();
		    		  $.each(data.dictionarys,function(i,item){
		    			 
		    			  /* $("#ul1").append("<li class='ui-tabrow'><p class='ui-tabcell'>"+item.code+"</p><p class='ui-tabcell'>"+item.cnName+"</p><p class='ui-tabcell'>"+item.enName+"</p><p class='ui-tabcell'>"+item.value+"</p><p class='ui-tabcell go-to-next'>"+item.sort+"</p><p class='tool-box' style='text-align:center'><button type='button' onclick='deledDictionary(\""+item.id+"\"",\""+item.code+"\")'>删除</button>&nbsp&nbsp&nbsp<button type='button'  onclick='selectDictionary(\""+item.id+"\")'>修改</button></p></li>");
		    			   */
		    			   var enName=item.enName;
		    			   var cnName=item.cnName;
		    			   var value=item.value;
		    			   var sort=item.sort;
		    			   if(null==item.enName){
		    				   enName="";
		    			   }
		    			   if(null==item.cnName){
		    				   cnName="";
		    			   }
		    			   if(null==item.value){
		    				   value="";
		    			   }
		    			   if(null==item.sort){
		    				   sort="";
		    			   }
		    			  $("#ul1").append("<li class='ui-tabrow'><p class='ui-tabcell'>"+item.code+"</p><p class='ui-tabcell'>"+cnName+"</p><p class='ui-tabcell'>"+enName+"</p><p class='ui-tabcell'>"+value+"</p><p class='ui-tabcell'>"+sort+"</p><p class='tool-box' style='text-align:center'><button type='button' onclick='deledDictionary(\""+item.id+"\",\""+item.code+"\")'>删除</button><button type='button' onclick='selectDictionary(\""+item.id+"\")'>修改</button></p></li>");
		    		  })
		    		  $("#dictionaryId").val(data.dictionary.id);
		    		  $("#codeSelf").val(data.dictionary.code);
		    		  $("#cnNameSelf").val(data.dictionary.cnName);
		    		  $("#enNameSelf").val(data.dictionary.enName);
		    		  $("#valueSelf").val(data.dictionary.value);
		    		  $("#sortSelf").val(data.dictionary.sort); 
		    		  $("#description").val(data.dictionary.description); 
		    		  code = data.dictionary.code;
		    		  cnName = data.dictionary.cnName;
		    		  enName = data.dictionary.enName;
		        }  
		    }); 
	}
	var log, className = "dark";
	function beforeDrag(treeId, treeNodes) {
		return false;
	}
	
	//编辑节点
	function beforeEditName(treeId, treeNode) {
		className = (className === "dark" ? "":"dark");
		showLog("[ "+getTime()+" beforeEditName ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name);
		var zTree = $.fn.zTree.getZTreeObj(treeId);
		zTree.selectNode(treeNode);
		setTimeout(function() {
			if (confirm("进入节点 -- " + treeNode.name + " 的编辑状态吗？")) {
				setTimeout(function() {
					zTree.editName(treeNode);
				}, 0);
			}
		}, 0);
		return false;
	} 
	//删除节点
	 function beforeRemove(treeId, treeNode) {
		var id = treeNode.id;
		layer.confirm("确认删除 节点 -- " + treeNode.name + " 吗？", {
			  btn: ['确定','取消'] //按钮
			}, function(){
				$.post("${ctx}/dictionary/delete",{id:id},function(data){
					if(data.code=="1"){
						layer.confirm("删除成功",{btn: ['确定']},function(){
		    	    		window.location.href="${ctx}/dictionary/getDictary";
		    	    	})
		    	    }else{
		    	    	layer.confirm("删除失败",{btn: ['确定']},function(){
		    	    		window.location.href="${ctx}/dictionary/getDictary";
		    	    	})
		    	    }
					
				})
			}, function(){
		});
		return false;
	}
	 
	function onRemove(e, treeId, treeNode) {
		showLog("[ "+getTime()+" onRemove ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name);
	}
	
	//查询当前节点下的子节点信息
	function zTreeOnClick(event, treeId, treeNode){
		/* var html = "";
		html+="<ul class='ui-form grid-row'><li class='col-md-3'>";
		if(treeNode.id!=null){
			//html+="<input id='id' name='id' type='hidden' value='"+treeNode.id+"' />";
		}
		html+="<input id='id' name='parentId' type='hidden' value='"+treeNode.pId+"' />"+
		"<input type='text' class='ipt-text' style='width:150px;' name='cnName' value='"+treeNode.name+"'/></li>"+
		   "<li class='col-md-3 col-btn'><button type='button' class='btn btn-submit btn-radius btn-search' onclick='submitDic()'>确定</button></li></ul>";
		$("#dictionaryDiv").html(html); */
		
		/* $.ajax({  
	        async : false,  
	        cache : false,  
	        type: 'POST',  
	        dataType : "json",  
	        data:{"id":treeId},
	        url: "${ctx}/manage/dictionary/selectAllChild
	        error: function () {//请求失败处理函数  
	           layer.msg("查询失败") 
	        },  
	        success:function(data){ //请求成功后处理函数。
	        	
	        }  
	    });   */
	    /* var id =treeId;
	    
		$.ajax({  
	        async : false,  
	        cache : false,  
	        type: 'GET',   
	        url: "${ctx}/manage/dictionary/findChilds?id="+treeId,
	        error: function () {//请求失败处理函数  
	           layer.msg("查询失败aaa") 
	        },  
	        success:function(data){ //请求成功后处理函数。
	        	layer.msg("查询成功") 
	        }  
	    }); 
	     */
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
		return true;
	}
	function showRenameBtn(treeId, treeNode) {
		return !treeNode.isLastNode;
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
		var sObj = $("#" + treeNode.tId + "_span");
		if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
		var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
			+ "' title='add node' onfocus='this.blur();'></span>";
		sObj.after(addStr);
		var btn = $("#addBtn_"+treeNode.tId);
		if (btn) btn.bind("click", function(){
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			zTree.addNodes(treeNode, {id:null, pId:treeNode.id, name:"新增节点"});
			return false;
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
		$.get("${ctx}/manage/dictionary/ajaxSelectDictary",function(result){
			$.each(result,function(index,dictionary){
				var zNode = {id:dictionary.id,pId:dictionary.parentId,name:dictionary.cnName, open:false};
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
	function submitDic(){
		$.ajax({  
		        async : false,  
		        cache : false,  
		        type: 'POST',  
		        dataType : "json",  
		        data : $("#dictionaryForm").serialize(),
		        url: "${ctx}/dictionary/updateOne",//请求的action路径  
		        error: function (data) {//请求失败处理函数  
		            alert(data.msg);  
		        },  
		        success:function(data){ //请求成功后处理函数。
		        	alert(data.msg);
		        	
		        }  
		    }); 
		};
			/*
		$.ajax({
			  type: "GET",
			  url: "${ctx}/manage/dictionary/updateOne2",
			  success: function(data){
                alert(data.data.cnName);      
	             },
			  dataType: "json"
			});
		 */
	
	
	//修改或者新增Dictionary字典
	function saveDictionzry(){
		var code=$("#code1").val();
		if(code=="" || code==null){
			alert("字典不能为空");
			return;
		}
		var cnName=$("#cnName1").val();
		if(cnName=="" || cnName==null){
			alert("中文名称不能为空");
			return;
		}
	    var enName=$("#enName1").val();
	    var value =$("#value1").val();
	    if(value=="" || value==null){
			alert("字典值不能为空");
			return;
		}
	    var sort=$("#sort1").val();
	    var description=$("#description1").val();
	    var ids = $("#dictionaryOtherId").val();
	    $.ajax({  
	        async : false,  
	        cache : false,  
	        type: 'Post',  
	        dataType : "json",
	        data:$("#Dictionary").serialize(),
	        url: "${ctx}/manage/dictionary/updateOrInsert?ids="+ids,
	        error: function (data) {//请求失败处理函数  
	        	layer.msg(data.msg);
	        },  
	        success:function(data){ //请求成功后处理函数。
	        	if(data.code=='0'){
	        		layer.msg(data.msg);
	        	}else{
	        		layer.confirm(data.msg,{btn: ['确定']},function(){
	    	    		window.location.href="${ctx}/manage/dictionary/getDictary";
	    	    	})
	        	}
	        }  
	    }); 
		 }
		
	//取消修改或增加Dictionary
	function closeWindos(){
			 $('.mask').hide();
		 }
	//查询当前节点信息
	function selectDictionary(id){
		$.ajax({
			  async : false,  
		      cache : false,  
		      type: 'GET',
	    	  url: "${ctx }/manage/dictionary/selectOneById?id="+id,
	    	  dataType: "json",
	    	  success: function(result){
	    		  
	    		  $("#dictionaryOtherId").val(result.data.id);
	    		  $("#code1").val(result.data.code);
	    		  $("#cnName1").val(result.data.cnName);
	    		  $("#enName1").val(result.data.enName);
	    		  $("#value1").val(result.data.value);
	    		  $("#sort1").val(result.data.sort);
	    		  $("#description1").val(result.data.description);
	    	  }
	    	})
		$('.mask').show();
	}
	//删除Dictionary
	function deledDictionary(id,code){
		layer.confirm("确认删除 节点 -- " + code + " 吗？", {
			  btn: ['确定','取消'] //按钮
			}, function(){
				$.post("${ctx}/manage/dictionary/delete",{id:id},function(data){
					if(data.code=="1"){
						layer.confirm("删除成功",{btn: ['确定']},function(){
		    	    		window.location.href="${ctx}/manage/dictionary/getDictary";
		    	    	})
		    	    }else{
		    	    	layer.confirm(data.data,{btn: ['确定']},function(){
		    	    		window.location.href="${ctx}/manage/dictionary/getDictary";
		    	    	})
		    	    }
					
				})
			}, function(){
		});
	}
	function addDictionary(){
		$('.mask').show();
		
	}
	function saveDicyionarySelf(){
		var codeEq = $("#codeSelf").val();
		var cnNameEq = $("#cnNameSelf").val();
		var enNameEq = $("#enNameSelf").val();
		if(codeEq==code){
			$("#codeSelf").attr("disabled","disabled");
		}
		if(cnNameEq == cnName){
			$("#cnNameSelf").attr("disabled","disabled");
		}
		if(enNameEq == enName){
			$("#enNameSelf").attr("disabled","disabled");
		}
		  $.ajax({  
		        async : true,  
		        cache : false,  
		        type: 'Post',  
		        dataType : "json",
		        data:$("#DictionarySelf").serialize(),
		        url: "${ctx}/manage/dictionary/updateOrInsert",
		        error: function (data) {//请求失败处理函数  
		        	layer.confirm(data.msg,{btn: ['确定']},function(){
	    	    		window.location.href="${ctx}/manage/dictionary/getDictary";
	    	    	})
		        },  
		        success:function(data){ //请求成功后处理函数。
		        	if(data.code == '0'){
		        		layer.msg(data.msg);
		        	}else{
		        		layer.confirm(data.msg,{btn: ['确定']},function(){
		    	    		window.location.href="${ctx}/manage/dictionary/getDictary";
		    	    	})
		        	}
		        }  
		    }); 
	}
	
</script>
</body>
</html>