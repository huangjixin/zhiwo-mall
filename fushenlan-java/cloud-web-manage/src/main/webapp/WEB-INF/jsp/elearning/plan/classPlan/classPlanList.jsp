<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/tld/Data" prefix="data" %>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<title>富卫运维大平台</title>
<meta name="description" content="">
</head>
<body>
<style>
.button:hover{color:#09cbd2;}
.btn-search-two {
    position: absolute;
    right: 20px;
}
</style>
<script type="text/javascript"	src="${ctx}/resources/libs/layer/layer.js"></script>
		<div class="filter-box">
			<ul class="ui-form grid-row">
				<li class="col-md-3"><strong>班级计划名称</strong><input id="name" name="name"  type="text" value="${name }"></li>
				<li class="col-md-3"><strong>班级计划代码</strong><input id="code" name="code"  type="text" value="${code }"></li>
				<li class="col-md-3"><strong>二级分类</strong>
						<select id="tagId" name="tagId">
							<option selected value="">--全部--</option>
							<option id="1" value="1">初审面试</option>
							<option id="2" value="2">甄选面试</option>
							<option id="3" value="3">决定面试</option>
						</select>
				</li>
				<li class="col-md-3"><button type="button" class="btn btn-submit btn-radius btn-search-two" onclick="onQuery()"><i class="icon-search"></i> 查询</button></li>
			</ul>
		</div>
		<div class="tab-btn"><a class="btn btn-success btn-radius" href="${ctx}/manage/classPlan/saveView" title="新增"><i class="icon-jia"></i>新增</a>
					<button type="button" class="btn btn-success btn-radius" onclick="onStateOrDele('1')"><i class="icon-tingzhishangbao ong"></i>批量启用</button>
					<button type="button" class="btn btn-success btn-radius" onclick="onStateOrDele('2')"><i class="icon-close red"></i>批量停用</button>
					<button type="button" class="btn btn-success btn-radius" onclick="onStateOrDele('dele')"><i class="icon-qiyong green"></i>批量删除</button></div>
		<table class="table table-agents">
			<thead>
				<tr>
					<th>
						<label class="pos-rel">
							<input type="checkbox" id="checkbox" class="ace">
						</label>
					</th>
					<th>班级计划代码</th>
					<th>班级计划名称</th>
					<th>班级分类</th>
					<th>班级计划状态</th>
					<th>课程数量</th>
					<th>班级计划周期</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${not empty pageInfo}">
	                   <c:forEach  varStatus="idx" var="classPlan" items="${pageInfo.records}">
	                        <tr>
	                        	<td>
									<label class="pos-rel">
										<input type="hidden" name="state" value="${classPlan.state}" />
										<input type="checkbox" name="checkbox" value="${classPlan.id }" class="ace">
									</label>
								</td>
	                            <td>${classPlan.code }</td>
	                            <td>
	                            	<c:if test="${classPlan.state==1}">
	                           	    	${classPlan.name }
	                           	    </c:if>
		                            <c:if test="${classPlan.state==2}">
		                                <button type="button" class="button" onclick="check('${classPlan.id}')">${classPlan.name }</button>
		                            </c:if>
	                            </td>
	                            <td><c:if test="${classPlan.tagId ne '0' }"><data:DataTag id="${classPlan.tagId }" /></c:if></td>
	                            <td>
	                            	 <c:if test="${classPlan.state==1}">启用</c:if>
	                                 <c:if test="${classPlan.state==2}">停用</c:if>
	                            </td>
	                            <td>${classPlan.classNum } </td>
	                            <td><fmt:formatDate value="${classPlan.startDate }" pattern="yyyy-MM-dd"/>~<fmt:formatDate value="${classPlan.endDate }" pattern="yyyy-MM-dd"/></td>
	                            
	                            <td>
	                           	    <%-- <c:if test="${classPlan.state==1}">
	                           	    	修改
	                           	    </c:if> --%>
		                            <c:if test="${classPlan.state==2}">
		                                <button type="button" class="button" onclick="check('${classPlan.id}')">修改</button>
		                            </c:if>
	                            </td>
	                       </tr>
	                        </c:forEach>
	                </c:if>
			</tbody>
		</table>
		
		<div class="table-paging clearfix" id="pageDiv">
		</div>
		
		
	
<!-- E Wrapper -->

<script type="text/javascript" src="${ctx}/resources/js/common/page.js"></script>

<script>
		$(function(){
			var name = $("#name").val();
			var code = $("#code").val();
			pageAjax("${ctx}/manage/classPlan/listByPageAjax?name="+name+"&code="+code,'${pageInfo.pageSize}','${pageInfo.pageNo}','${pageInfo.pageTotal}','pageDiv');
			$("#checkbox").click(function () {
			  if($(this).is(':checked')){
			    	$("input[name='checkbox']").prop("checked",true);
			  }else{
			   		$("input[name='checkbox']").prop("checked",false);
			  }
			});
			$("input[name='checkbox']").click(function () {
				var len = $("input[name='checkbox']").length;
				var count = 0;
				$("input[name='checkbox']").each(function (){
					if($(this).is(':checked')){
						count++;
					}
				});
				if(len == count){
					$("#checkbox").prop("checked",true);
				}else{
					$("#checkbox").prop("checked",false);
				}
			});
			var tagId = "${tagId}";
			if(tagId!=null && tagId != ''){
				$("#"+tagId).attr("selected","selected");
			}
		})
		 function pageNext(url){
		    	// 查询字段取页面加载时 model传入的值  防止分页执行查询
		    	var name = '${name}';
				var code = '${code}';
		    	$.get(url,function(returnData){
		    		if(returnData.code == '1'){
		    			var html = "";
		    			$.each(returnData.data.records,function(i,row){
		    			row.code = row.code==null?'':row.code; //判断null字段
		    			html+='<tr><td><label class="pos-rel">'+
		    				  '<input type="checkbox" name="checkbox" class="ace" value="'+row.id+'"></label></td>'+
		    				  '<td>'+row.code+'</td>';
		    				  if(row.state=='1'){
		    					  html+='<td>'+row.name+'</td>';
			    				  html+='<td>'+$('#'+row.tagId).text()+'</td>';
		    					  html+='<td>启用</td>';
		    				  }else{
		    					  html+='<td><button type="button" class="button" onclick="check(\''+row.id+'\')">'+row.name+'</button></td>';
		    					  html+='<td>'+$('#'+row.tagId).text()+'</td>';
		    					  html+='<td>停用</td>';
		    				  }		    				  
		    				  html+='<td>'+row.classNum+'</td>'+
		    				  		'<td>'+new Date(row.startDate).Format("yyyy-MM-dd")+'~'+new Date(row.endDate).Format("yyyy-MM-dd")+'</td>';
		    			      if(row.state == '2'){
		    			    	  html+='<td><button type="button" class="button" onclick="check(\''+row.id+'\')">修改</button></td>';
		    			      }else{
		    			    	  html+='<td></td>';
		    			      }
		    			})
		    			$(".table tbody").html(html);
		    			pageAjax("${ctx}/manage/classPlan/listByPageAjax?name="+name+"&code="+code,returnData.data.pageSize,returnData.data.pageNo,returnData.data.pageTotal,'pageDiv');
		    		}
		    	})
		    }
		function onStateOrDele(state){
			var url = "${ctx}/manage/classPlan/editOrDele";
			var ids = "";
			var bool = true;
			if($('input:checkbox:checked').length == 0){
				layer.msg("你还没有选择要的记录");
				return;
			}
			$("input[name='checkbox']").each(function (){
				if($(this).is(':checked')){
					if(state == 'dele'){
						var str= $(this).siblings("input[name='state']").val();
						if('1'==str){
							layer.msg("选中中存在状态为启用的记录");
							bool = false;
							return false;
						}
					}
					ids+=$(this).val()+",";
				}
			});
			if(!bool){
				return;
			}
			/* return; */
			ids = ids.substring(0, ids.length-1);
			var msg ="";
			if(state == '1'){
				msg = "你确定要启用选中的记录吗?";
			}else if(state == '2'){
				msg = "你确定要停用选中的记录吗?";
			}else if(state == 'dele'){
				msg = "你确定要删除选中的记录吗?";
			}else{
				msg = "你确定要操作选中的记录吗?";
			}
			layer.confirm(msg, {
				icon: 3,
			  	btn: ['确定', '取消'] //按钮
			}, function(){
				$.ajax({
					type : "post",
					data : {ids:ids,state:state},
					url : url,
					success : function(data) {
						if (data.code=='1') {
							var name = $("#name").val();
							var code = $("#code").val();
							var tagId = $("#tagId").val();
							location.href="${ctx}/manage/classPlan/index?name="+name+"&code="+code+"&tagId="+tagId;
						}
					}
				});
			});
		}
		function onQuery(){
			var name = $("#name").val();
			var code = $("#code").val();
			var tagId = $("#tagId").val();
			location.href = "${ctx}/manage/classPlan/index?name="+name+"&code="+code+"&tagId="+tagId;
		}
		
		function check(id){
			location.href = "${ctx}/manage/classPlan/saveView?id="+id;
		}
		
</script>
</body>
</html>
