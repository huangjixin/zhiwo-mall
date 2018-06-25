<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
		<div class="filter-box">
			<ul class="ui-form grid-row">
				<li class="col-md-5 auto"><strong class="long">关键字搜索：</strong><input class="ipt-text" type="text" name="keyWord" id ="kd" value="${keyWord }" placeholder="姓名/电话/证件号"></li>
				<li class="col-md-5 col-btn">
					<button type="button" onclick="ftr()" class="btn btn-submit btn-radius btn-search"><i class="icon-search"></i> 查询</button>
				</li>
			</ul>
		</div>
		
		<table class="table table-agents">
			<thead>
				<tr>
					<th>姓名</th>
					<th>年龄</th>
					<th>性别</th>
					<th>电话</th>
					<th>证件号</th>
					<th>最高学历</th>
					<th>毕业院校</th>
					<th>推荐人</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${not empty page}">
	                   <c:forEach  varStatus="idx" var="per" items="${page.records}">
	                        <tr>
	                            <td>${per.name }</td>
	                            <td>${per.age }</td>
	                            <td>
	                              <c:if test="${per.sex=='M'}">男</c:if>
	                              <c:if test="${per.sex=='F'}">女</c:if>
	                            </td>
	                            <td>${per.cellphone }</td>
	                            <td>${per.identityCode }</td>
	                            <td>${per.maxEducation }</td>
	                            <td>${per.school }</td>
	                            <td>${per.refereeName }</td>
	                            <td><button type="button" onclick="check('${per.id}')">查看</button></td>
	                       </tr>
	                  </c:forEach>
	            </c:if>
			</tbody>
		</table>
		
		<div class="table-paging clearfix" id="pageDiv">
		</div>
		<script type="text/javascript" src="${ctx}/resources/js/common/page.js"></script>
<script>
        $(function(){
	       var keyWord = '${keyWord}';
	       pageAjax("${ctx}/manage/personnel/listByOtherPageAjax?keyWord="+keyWord,'${page.pageSize}','${page.pageNo}','${page.pageTotal}','pageDiv');
        });
        
        
        var codeArray ={};
        codeArray['sexM'] = '男';
        codeArray['sexF'] = '女';
        
      //ajax 分页 拼接数据
        function pageNext(url){
        	// 查询字段取页面加载时 model传入的值  防止分页执行查询
        	var keyWord = '${keyWord}';
        	$.get(url,function(returnData){
        		if(returnData.code == '1'){
        			var html = "";
        			$.each(returnData.data.records,function(i,row){
        				row.name = row.name==null?'':row.name; //判断null字段
             			row.age = row.age==null?'':row.age; 
             			row.cellphone = row.cellphone==null?'':row.cellphone; 
             			row.identityCode = row.identityCode==null?'':row.identityCode; 
             			row.maxEducation = row.maxEducation==null?'':row.maxEducation; 
             			row.school = row.school==null?'':row.school; 
             			row.refereeName = row.refereeName==null?'':row.refereeName;
        		    html+='<tr>'+
        				  '<td>'+row.name+'</td>'+
        				  '<td>'+row.age+'</td>'+
        				  '<td>'+codeArray['sex'+row.sex]+'</td>'+
        				  '<td>'+row.cellphone+'</td>'+
        				  '<td>'+row.identityCode+'</td>'+
        				  '<td>'+row.maxEducation+'</td>'+
        				  '<td>'+row.school+'</td>'+
        				  '<td>'+row.refereeName+'</td>'+
        				  '<td><button type="button" onclick="check(\''+row.id+'\')">查看</button></tr>';
        			})
        			$(".table tbody").html(html);
        			pageAjax("${ctx}/manage/personnel/listByOtherPageAjax?keyWord="+keyWord,returnData.data.pageSize,returnData.data.pageNo,returnData.data.pageTotal,'pageDiv');
        		}
        	})
        }  
        
        
		function ftr(){
			var keyword  = $("#kd").val();
			location.href="${ctx}/manage/erecruitment/OtherpersonnelList?keyWord="+keyword;
		}
		function check(id){
			location.href="${ctx}/manage/erecruitment/findId?id="+id;
		}
		
</script>
</body>
</html>
