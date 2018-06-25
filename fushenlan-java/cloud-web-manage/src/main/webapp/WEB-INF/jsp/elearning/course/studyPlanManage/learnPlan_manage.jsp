<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<meta name="description" content="">
<title>Insert title here</title>
</head>
<body>
		<!-- S Filter Box --> 
		<div class="filter-box">
		<form action="${ctx }/manage/studyPlanManage/planListByPage" method="post">
			<ul class="ui-form grid-row">
				<li class="col-md-3"><strong>学习计划名称</strong><input id="sname" onclick="clean(this)" value="${name }" name="name" type="text" ></li>
				<li class="col-md-3"><strong>学习计划代码</strong><input  id="scode" onclick="clean(this)" name="code" value="${code }" type="text"></li>
				<li class="col-md-4" ><strong>二级分类</strong><!-- <input type="text" class="ipt-text" value="全部" placeholder="请选择" readonly> -->
						<select id="slv" name="tagId" style="border-radius:3px;">
							<option <c:if test="${tagId=='' }">selected</c:if> value="">--请选择--</option>
							<option <c:if test="${tagId==1 }">selected</c:if> value="1">视频</option>
							<option <c:if test="${tagId==2 }">selected</c:if> value="2">PPT</option>
							<option <c:if test="${tagId==3 }">selected</c:if> value="3">SCORM</option>
							<option <c:if test="${tagId==4 }">selected</c:if> value="4">WORD</option>
							<option <c:if test="${tagId==5 }">selected</c:if> value="5">EXCEL</option>
						</select>
				</li>
			</ul>
			<button type="submit" style="top:26px;" class="btn btn-submit btn-radius btn-search btn-list-search"><i class="icon-search"></i> 查询</button>
			</form>
		</div>
		<!-- E Filter Box -->
		<div class="tab-btn">
		<a class="btn btn-success btn-radius" href="${ctx }/manage/studyPlanManage/learnPLan_addEdit" title="新增"><i class="icon-jia"></i>新增</a>
		<button type="button" class="btn btn-success btn-radius btn-list-stop"><i class="icon-tingzhishangbao ong"></i>批量停用</button>
		<button type="button" class="btn btn-success btn-radius btn-list-start"><i class="icon-qiyong green"></i>批量启用</button>
		<button type="button" class="btn btn-success btn-radius btn-list-delete"><i class="icon-close red"></i>批量删除</button>
		</div>
		<table class="table ">
			<thead>
				<tr>
					<th>
						<label class="pos-rel">
							<input type="hidden" class="ace" >
							<input type="checkbox" name="checkall" class="ace" id="checkbox">
						</label>
					</th>
					<th>学习计划代码</th>
					<th>学习计划名称</th>
					<th>一级分类</th>
					<th>二级分类</th>
					<th>是否被关联</th>
					<th>课程数量</th>
					<th>计划阶段数</th>
					<th>计划状态</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody class="table-agents">
					<c:forEach items="${pageList.records}" var="row">
					<tr>
					   <td>
						<label class="pos-rel">
							<input type="checkbox" name="checkbox" state="${row.state }" dat="${row.isAssociate }" class="ace" value="${row.id}">
						</label>
					   </td>
						<td>${row.code}</td>
						<td>${row.name}</td>
					 	<td>${row.groupName}</td>
						<td>
						<c:choose>
							<c:when test="${row.tagId==1}">视频</c:when>
							<c:when test="${row.tagId==2 }">ppt</c:when>
							<c:when test="${row.tagId==3 }">SCORM</c:when>
							<c:when test="${row.tagId==4 }">WORD</c:when>
							<c:when test="${row.tagId==5 }">EXCEL</c:when>
							 <c:otherwise></c:otherwise> 
						</c:choose>
						</td>
						<td>
						<c:if test="${row.isAssociate==1 }">
						已被关联
						</c:if>
						 <c:if test="${row.isAssociate==0 }">
						未被关联
						</c:if></td>
						<td>${row.courseNum}</td>
						<td>${row.stageNum}</td>
						<td>
					    <c:if test="${row.state==1 }">
						启用
						</c:if>
						 <c:if test="${row.state==0 }">
						禁用
						</c:if>
						</td>
						<td>
						<a class="color-detail" href="javascript:;" onclick="edit('${row.id}','${row.state}')" title="修改">修改</a>
						</td>
						</tr>
						</c:forEach>
			</tbody>
		</table>
		<div class="table-paging clearfix" id="pageDiv">
		</div>
		<script type="text/javascript" src="${ctx}/resources/js/common/page.js"></script>
		<script type="text/javascript" src="${ctx}/resources/libs/layer/layer.js" ></script>
     
     <script>
    //实现全选与全不选
	  $("#checkbox").click(function() {
		  if ($(this).is(':checked')) {
		  $("input[name='checkbox']").prop("checked",true);
		  } else {
		  $("input[name='checkbox']").prop("checked",false);
			}
		  }); 
    
    function addPlan(){
    	location.href="${ctx }/manage/studyPlanManage/learnPLan_addEdit";
    	
    }
    
    function clean(obj){
    	$(obj).val("");
    }
    $(function(){
		var name = '${name}';
		var code = '${code}';
		var tagId ='${tagId}';
		pageAjax("${ctx}/manage/studyPlanManage/listByPageAjax?name="+name+"&code="+code+"&tagId="+tagId,'${pageList.pageSize}','${pageList.pageNo}','${pageList.pageTotal}','pageDiv');
		//page("${ctx}/manage/studyPlanManage/planListByPage?name="+name+"&code="+code+"&tagId="+tagId,'${pageList.pageSize}','${pageList.pageNo}','${pageList.pageTotal}','pageDiv');
	});
    //用于页面回显问题  视实际情况而定 
    var codeArray ={};
    codeArray['1'] = '视频';
    codeArray['2'] = 'ppt';
    codeArray['3'] = 'SCORM';
    codeArray['4'] = 'WORD';
    codeArray['5'] = 'EXCEL';
    codeArray['isAssociate0'] = '未被关联';
    codeArray['isAssociate1'] = '已被关联';
    codeArray['state1'] = '启用';
    codeArray['state0'] = '禁用';
    
    //ajax 分页 拼接数据
    function pageNext(url){
    	// 查询字段取页面加载时 model传入的值  防止分页执行查询
    	var name = '${name}';
		var code = '${code}';
		var tagId ='${tagId}';
    	$.get(url,function(returnData){
    		if(returnData.code == '1'){
    			var html = "";
    			console.log(returnData.data);
    			$.each(returnData.data.records,function(i,row){
    			row.groupId = row.groupId==null?'':row.groupId; //判断null字段
    			codeArray[row.tagId] =codeArray[row.tagId] == undefined ?'':codeArray[row.tagId]; //判断undefined字段
    			var id= ''+row.id;
    			html+='<tr><td><label class="pos-rel">'+
    				  '<input type="checkbox" name="checkbox" state="'+row.state+'" dat="'+row.isAssociate+'" class="ace" value="'+row.id+'"></label></td>'+
    				  '<td>'+row.code+'</td>'+
    				  '<td>'+row.name+'</td>'+
    				  '<td>'+row.groupId+'</td>'+
    				  '<td>'+codeArray[row.tagId]+'</td>'+
    				  '<td>'+codeArray['isAssociate'+row.isAssociate]+'</td>'+
    				  '<td>'+row.courseNum+'</td>'+
    				  '<td>'+row.stageNum+'</td>'+
    				  '<td>'+codeArray['state'+row.state]+'</td>'+
    				  '</td><td><a class="color-detail" href="javascript:;" onclick="edit(\''+row.id+'\',\''+row.state+'\')" title="修改">修改</a></td></tr>';
    			})
    			$(".table tbody").html(html);
    			pageAjax("${ctx}/manage/studyPlanManage/listByPageAjax?name="+name+"&code="+code+"&tagId="+tagId,returnData.data.pageSize,returnData.data.pageNo,returnData.data.pageTotal,'pageDiv');
    		}
    	})
    }
    
    function getTabList(){
    		var list = [];
    		$('.table-agents tbody tr').each(function() {
    			if($(this).find("input[type='checkbox']").prop('checked')) {
    				list.push($(this).data('id'));
    			}
    		});
    		return list;
    	}
    	
    	$('.btn-list-stop').on('click', function() {
    		var list=getTabList();
    		var data = new Array();
    		var str='';
    		var enabaled='0';
    		$(".table-agents").find("input[type='checkbox']").each(function(){
    			if($(this).is(":checked")){
    			var rs=	$(this).val();
    				var rs=this.value;
    				str=str+rs+",";
    			}
    		});
    		if (str == '') {
                layer.msg("请先选择记录");
                return;
            } else {
	    		str=str+enabaled;
	    		$.ajax({  
			        type: 'POST',  
			        dataType :"json",  
			        data :{"ids":str},
			        url: "${ctx}/manage/studyPlanManage/batchUpstates",//请求的action路径  
			        error: function (data) {//请求失败处理函数  
			            alert("失败"); 
			        },  
			        success:function(data){ //请求成功后处理函数。
			        location.href="${ctx}/manage/studyPlanManage/planListByPage";
		
			        }  
			    }); 
            }

    	});
    	
    	//批量启用
    	$('.btn-list-start').on('click', function() {
    		var list=getTabList();
    		var data = new Array();
    		var str='';
    		var enabaled='1';
    		$(".table-agents").find("input[type='checkbox']").each(function(){
    			if($(this).is(":checked")){
    			var rs=	$(this).val();
    				str=str+rs+",";
    			}
    		});
    		if (str == '') {
    			layer.msg("请先选择记录");
    			return;
    		} else {
	    		str=str+enabaled;
	    		var ids= JSON.stringify(data);
	    		$.ajax({  
			        type: 'POST',  
			        dataType :"json",  
			        data :{"ids":str},
			        url: "${ctx}/manage/studyPlanManage/batchUpstates",//请求的action路径  
			        error: function (data) {//请求失败处理函数  
			            layer.msg("失败"); 
			        },  
			        success:function(data){ //请求成功后处理函数。
			        location.href="${ctx}/manage/studyPlanManage/planListByPage";
			        }  
			    }); 
    		}
    	});
    	
    	$('.btn-list-delete').on('click', function() {
    		var list=getTabList();
    		var data = new Array();
    		var str='';
    		var isAssociate=0;
    		var state=0;
    		$(".table-agents").find("input[type='checkbox']").each(function(){
    			if($(this).is(":checked")){
    			var rs=	$(this).val();
    			var dat=$(this).attr("dat");//是否被关联
    			var stat=$(this).attr("state");//禁用或启用的状态
    			if(dat==1){//被关联的不能被删除
    				isAssociate=1;
    			}
    			if(stat==1){//启用状态的不能被删除
    				state=1;
    			}
    			if(dat==0 && stat==0){
    				str=str+rs+",";
    			}
    		 }
    		});
    		var len=str.length;
    		if(isAssociate==0 && state==0 && len>0){
    			str=str.substring(0,str.length-1);
    			layer.confirm('您确定要删除选中的记录吗？', {
    				  btn: ['确定','取消'] //按钮
    				}, function(){
    					 batchdeletes(str);
    				}, function(){
    				});
    			
    		}else if(isAssociate==0 && state==0){
    		  layer.msg("请先选择记录")
    		}else {
    		  layer.msg("选中中存在被关联或状态为启用的的记录");
    		}
    	});
    	
    	//批量删除学习计划
    	function batchdeletes(str){
    		var str=str;
    		$.ajax({  
		        type: 'POST',  
		        dataType :"json",  
		        data :{"ids":str},
		        url: "${ctx}/manage/studyPlanManage/betchdeletes",//请求的action路径  
		        error: function (data) {//请求失败处理函数  
		         layer.msg('系统繁忙~~')
		        },  
		        success:function(data){ //请求成功后处理函数。
		         location.href="${ctx}/manage/studyPlanManage/planListByPage";
		        }  
		    }); 
    	}
    	
        //修改(启用状态的不能被修改)
    	function edit(id,state){
        	if(state==0){
    		 location.href="${ctx }/manage/studyPlanManage/detail?id="+id;
        	}else{
        	 layer.msg("该条记录状态为启用状态,不能被修改~");
        	}
    	}

    </script>
</body>
</html>