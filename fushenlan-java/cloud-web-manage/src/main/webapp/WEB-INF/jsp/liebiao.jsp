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
		<!-- S Filter Box -->
		<div class="filter-box">
			<ul class="ui-form grid-row">
				<li class="col-md-3"><strong>关键字搜索</strong><input class="ipt-text" type="text" placeholder=""></li>
				<li class="col-md-3">
					<strong>试卷类型</strong>
					<div class="ipt-select">
						<i class="icon-arrow-down"></i>
						<input type="text" class="ipt-text" value="全部" placeholder="请选择" readonly>
						<select>
							<option selected value="0">全部</option>
							<option value="1">初审面试</option>
							<option value="2">甄选面试</option>
							<option value="3">决定面试</option>
						</select>
					</div>
				</li>
				<li class="col-md-3">
					<strong>分公司</strong>
					<div class="ipt-select">
						<i class="icon-arrow-down"></i>
						<input type="text" class="ipt-text" value="全部" placeholder="请选择" readonly>
						<select>
							<option selected value="0">全部</option>
							<option value="1">富卫总部</option>
							<option value="2">上海分公司</option>
							<option value="3">广州分公司</option>
						</select>
					</div>
				</li>
				<li class="col-md-3 col-btn">
					<button type="button" class="btn btn-submit btn-radius btn-search"><i class="icon-search"></i> 查询</button>
					<button type="button" class="btn btn-success btn-radius">新增</button>
				</li>
			</ul>
		</div>
		<!-- E Filter Box -->
		
		<table class="table table-agents">
			<thead>
				<tr>
					<th>分公司</th>
					<th>试卷类型</th>
					<th>试卷名称</th>
					<th>试卷说明</th>
					<th>考题量</th>
					<th>总分</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>上海分公司</td>
					<td>甄选面试</td>
					<td>个人背景调查</td>
					<td>评估个人、家庭条件；学习意愿和教育背景；工作态度及工作背景是否合适</td>
					<td>5</td>
					<td>25</td>
					<td>
						<a class="color-detail" href="./zysjgl-detail.html" title="查看">查看</a>
						<a class="color-edit" href="./zysjgl-edit.html" title="修改">修改</a>
					</td>
				</tr>
				<tr>
					<td>上海分公司</td>
					<td>甄选面试</td>
					<td>基础能力评估</td>
					<td>评估时间管理、自律能力、挑战及解决问题能力</td>
					<td>5</td>
					<td>25</td>
					<td>
						<a class="color-detail" href="./zysjgl-detail.html" title="查看">查看</a>
						<a class="color-edit" href="./zysjgl-edit.html" title="修改">修改</a>
					</td>
				</tr>
				<tr>
					<td>上海分公司</td>
					<td>决定面试</td>
					<td>行业&amp;公司了解</td>
					<td>评估对行业的了解能力</td>
					<td>5</td>
					<td>25</td>
					<td>
						<a class="color-detail" href="./zysjgl-detail.html" title="查看">查看</a>
						<a class="color-edit" href="./zysjgl-edit.html" title="修改">修改</a>
					</td>
				</tr>
				<tr>
					<td>上海分公司</td>
					<td>决定面试</td>
					<td>社会关系调查</td>
					<td>评估朋友、家庭等社会关系网是否支持从事该行业</td>
					<td>5</td>
					<td>25</td>
					<td>
						<a class="color-detail" href="./zysjgl-detail.html" title="查看">查看</a>
						<a class="color-edit" href="./zysjgl-edit.html" title="修改">修改</a>
					</td>
				</tr>
				<tr>
					<td>上海分公司</td>
					<td>甄选面试</td>
					<td>个人背景调查</td>
					<td>评估个人、家庭条件；学习意愿和教育背景；工作态度及工作背景是否合适</td>
					<td>5</td>
					<td>25</td>
					<td>
						<a class="color-detail" href="./zysjgl-detail.html" title="查看">查看</a>
						<a class="color-edit" href="./zysjgl-edit.html" title="修改">修改</a>
					</td>
				</tr>
			</tbody>
		</table>
		<!-- / E Table -->
		<div class="table-paging clearfix" id="pageDiv">
			<%-- <p>
				<input type="hidden" value="${page.pages}" id="pages">
				<input type="hidden" value="${page.pageSize}" id="pageSize">
				<a class="first disabled" href="#"><i class="icon-first"></i></a>
				<a class="prev disabled" href="#"><i class="icon-arrow-left"></i></a>
				<a class="active" href="javascript:;">1</a>
				<a href="#">2</a>
				<a href="#">3</a>
				<a href="#">4</a>
				<a href="#">5</a>
				<a class="next" href="#"><i class="icon-arrow-right"></i></a>
				<a class="last" href="#"><i class="icon-last"></i></a>
			</p> --%>
		</div>
		<!-- / E Table Paging -->
		<script>
		$(function(){
			page("${ctx}/manage/ss",'10','1','20','pageDiv')
		})
		function page(fun,pageSize,pageNo,pageTotal,id){
			pageSize= isNaN(pageSize)?10:parseInt(pageSize); 
			pageNo = isNaN(pageNo)?1:parseInt(pageNo); 
			pageTotal = isNaN(pageTotal)?1:parseInt(pageTotal); 
			if(pageNo>pageTotal){
				pageNo = pageTotal;
			}
			var pageHtml = "<p>";
			if(pageNo == 1){
				pageHtml+='<a class="first disabled" href="#"><i class="icon-first"></i></a>';
				pageHtml+='<a class="prev disabled" href="#"><i class="icon-arrow-left"></i></a>';
			}else{
				pageHtml+='<a class="first" href="'+fun+'&pageNo=1&pageSize=10"><i class="icon-first"></i></a>';
				pageHtml+='<a class="prev" href="'+fun+'&pageNo='+(pageNo-1)+'&pageSize=10"><i class="icon-arrow-left"></i></a>';
			}
			if(pageTotal>10){
				if(pageNo<=6){
					for(var i=1;i<=6;i++){
						if(i==pageNo){
							pageHtml+='<a class="active" href="'+fun+'&pageNo='+i+'&pageSize=10">'+i+'</a>'
						}else{
							pageHtml+='<a class="" href="'+fun+'&pageNo='+i+'&pageSize=10">'+i+'</a>'
						}
					}
					pageHtml+='<a>....</a>';
					for(var j=pageTotal-3;j<=pageTotal;j++){
						pageHtml+='<a class="" href="'+fun+'&pageNo='+j+'&pageSize=10">'+j+'</a>'
					}
				}
				else if(pageNo>6 && (pageTotal-pageNo)<=6){
					pageHtml+='<a class="" href="'+fun+'&pageNo=1&pageSize=10">1</a>'+
							  '<a class="" href="'+fun+'&pageNo=2&pageSize=10">2</a>'+
							  '<a>....</a>';
					for(var i=pageTotal;i<pageNo;i--){
						if(i==pageNo){
							pageHtml+='<a class="active" href="'+fun+'&pageNo='+i+'&pageSize=10">'+i+'</a>'
						}else{
							pageHtml+='<a class="" href="'+fun+'&pageNo='+i+'&pageSize=10">'+i+'</a>'
						}
					}
				}else if(pageNo>6 && (pageTotal-pageNo)>6){
					pageHtml+='<a class="" href="'+fun+'&pageNo=1&pageSize=10">1</a>'+
							  '<a class="" href="'+fun+'&pageNo=2&pageSize=10">2</a>'+
							  '<a>....</a>';
				    for(var i=pageNo-3;i<=pageNo+3;i++){
				    	if(i==pageNo){
							pageHtml+='<a class="active" href="'+fun+'&pageNo='+i+'&pageSize=10">'+i+'</a>'
						}else{
							pageHtml+='<a class="" href="'+fun+'&pageNo='+i+'&pageSize=10">'+i+'</a>'
						}
				    }		  
				    pageHtml+='<a>....</a>'+
				    		  '<a class="" href="'+fun+'&pageNo='+(pageTotal-1)+'&pageSize=10">'+pageTotal-1+'</a>'+
					          '<a class="" href="'+fun+'&pageNo='+pageTotal+'&pageSize=10">'+pageTotal+'</a>';
					
				}
			}else{
				for(var i=1;i<=pageTotal;i++){
					if(i==pageNo){
						pageHtml+='<a class="active" href="'+fun+'&pageNo='+i+'&pageSize=10">'+i+'</a>'
					}else{
						pageHtml+='<a class="" href="'+fun+'&pageNo='+i+'&pageSize=10">'+i+'</a>'
					}
				}
			}
			if(pageNo == pageTotal){
				pageHtml+='<a class="next disabled" href="#"><i class="icon-arrow-right"></i></a>'+
						  '<a class="last disabled" href="#"><i class="icon-last"></i></a></p>';
			}else{
				pageHtml+='<a class="next" href="'+fun+'&pageNo='+(pageNo+1)+'&pageSize=10"><i class="icon-arrow-right"></i></a>'+
				  		  '<a class="last" href="'+fun+'&pageNo='+pageTotal+'&pageSize=10"><i class="icon-last"></i></a></p>';
			}
			$("#"+id).html("");
			$("#"+id).html(pageHtml);
		}
		</script>
</body>
</html>