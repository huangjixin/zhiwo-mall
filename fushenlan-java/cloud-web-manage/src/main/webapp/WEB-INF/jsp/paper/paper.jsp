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
<style>
	input[name='paperType']{
		margin-right :10px;
	}
</style>
<script type="text/javascript"	src="${ctx}/resources/libs/layer/layer.js"></script>
	<div class="filter-box">
		<div>总公司试卷</div>
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
		<c:forEach  varStatus="idx" var="par" items="${orgPage}">
	                        <tr>
	                            <td>
 									<c:forEach  varStatus="idx" var="orgList" items="${orgList}">
 										<c:if test="${par.orgId == orgList.code}">${orgList.cnName}</c:if>
 									</c:forEach>
								</td>
	                            <td>
	                                 <c:if test="${par.paperType==1}">初审</c:if>
	                                 <c:if test="${par.paperType==2}">甄选</c:if>
	                                 <c:if test="${par.paperType==3}">决定</c:if>
	                            </td>
	                            <td>${par.paperName }</td>
	                            <td>${par.paperDesc }</td>
	                            <td>${par.paperNum }</td>
	                            <td>${par.paperScore }</td>
	                            <td>
	                                <button type="button" onclick="check('${par.id}')">查看</button>
	                            </td>
	                       </tr>
	         </c:forEach>
	         </tbody>
	         </table>
	</div>
		<div class="filter-box">
			<ul class="ui-form grid-row">
					<li class="col-md-3"><strong>试卷名称</strong><input id="kWord" name="keyWord" class="ipt-text" type="text" placeholder="试卷名称" value="${keyWord}"></li>
				<c:if test="${userInfo.companyId == 'B0311'}">
					<li class="col-md-3" >
						<strong>分公司</strong>
						<select id="orgId" name="orgId">
							    <option value="">全部</option>
							    <c:forEach  varStatus="idx" var="orgList" items="${orgList}">
							    	<option value="${orgList.code}" <c:if test="${orgId == orgList.code}">selected</c:if>>${orgList.cnName}</option>
							    </c:forEach>
						</select>
					</li>
				</c:if>
					<li class="col-md-6 col-btn">
						<button type="button" onclick ="ftr()" class="btn btn-submit btn-radius"><i class="icon-search"></i> 查询</button>
						<button type="button" onclick ="addPaperPopout()" class="btn btn-submit  btn-radius">新增</button>
					</li>
			</ul>
		</div>
		
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
				<c:if test="${not empty page}">
	                   <c:forEach  varStatus="idx" var="par" items="${page.records}">
	                        <tr>
	                            <td>
 									<c:forEach  varStatus="idx" var="orgList" items="${orgList}">
 										<c:if test="${par.orgId == orgList.code}">${orgList.cnName}</c:if>
 									</c:forEach>
								</td>
	                            <td>
	                                 <c:if test="${par.paperType==1}">初审</c:if>
	                                 <c:if test="${par.paperType==2}">甄选</c:if>
	                                 <c:if test="${par.paperType==3}">决定</c:if>
	                            </td>
	                            <td>${par.paperName }</td>
	                            <td>${par.paperDesc }</td>
	                            <td>${par.paperNum }</td>
	                            <td>${par.paperScore }</td>
	                            <td>
	                                <button type="button" onclick="check('${par.id}')">查看</button>
	                                <button type="button" onclick="edit('${par.id}','${par.paperType}')">修改</button>
	                                <button type="button" onclick="delVo('${par.id}')">删除</button>
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
	  var keyword  = $("#kd").val();
	  pageAjax("${ctx}/manage/erecruitment/listByPageAjax?keyWord="+keyword,'${page.pageSize}','${page.pageNo}','${page.pageTotal}','pageDiv')
  })
		
  var codeArray ={};
codeArray['1'] = '初审面试';
codeArray['2'] = '甄选面试';
codeArray['3'] = '决定面试';
  
  //ajax 分页 拼接数据
  function pageNext(url){
	// 查询字段取页面加载时 model传入的值  防止分页执行查询
	var keyWord = '${keyWord}';
	console.log(keyword);
	$.get(url,function(returnData){
		if(returnData.code == '1'){
			var html = "";
			$.each(returnData.data.records,function(i,row){
			/* row.code = row.code==null?'':row.code; //判断null字段
			row.name = row.name==null?'':row.name; */
			html+='<tr>'+
				  '<td>'+row.orgId+'</td>'+
				  '<td>'+codeArray[row.paperType]+'</td>'+
				  '<td>'+row.paperName+'</td>'+
				  '<td>'+row.paperDesc+'</td>'+
				  '<td>'+row.paperNum+'</td>'+
				  '<td>'+row.paperScore+'</td>'+
				  '<td><button onclick="check(\''+row.id+'\')">查看</button><button onclick="edit(\''+row.id+'\',\''+row.paperType+'\')" >修改</button><button onclick="delVo(\''+row.id+'\')">删除</button></td></tr>';
			})
			$(".table tbody").html(html);
			pageAjax("${ctx}/manage/erecruitment/listByPageAjax?keyWord="+keyWord,returnData.data.pageSize,returnData.data.pageNo,returnData.data.pageTotal,'pageDiv');
		}
	})
}
		
		
		
		function ftr(){
			var keyWord  = $("#kWord").val();
			var orgId  = $("#orgId").val();
			 location.href="${ctx}/manage/erecruitment/paperList?keyWord="+keyWord+"&orgId="+orgId;
		}
		
		function addPaperPopout(){
			//页面层
			layer.open({
			  type: 1,
			  skin: 'layui-layer-rim', //加上边框
			  area: ['420px', '240px'], //宽高
			  content: '<center ><div style="text-align:center;"><div style="margin-top:30px;">'
						+'初审:<input type="radio" checked name="paperType" value="1">'
						+'甄选:<input type="radio" name="paperType" value="2">'
						+'决定:<input type="radio" name="paperType" value="3">'
						+'</div></div>'
						+'<button style="margin-top:60px;" type="button" onclick ="confirm()" class="btn btn-submit btn-radius">确定</button>'
						+'<button type="button" onclick ="javaScript:layer.closeAll()" class="btn btn-submit  btn-radius">取消</button>'
						+'</center>'
			});
		}
		function confirm(){
			addPaper($("input[name='paperType']:checked").val())
		}		
		function addPaper(paperType){
			location.href="${ctx}/manage/erecruitment/edit?paperType="+paperType;
		}
		function check(id){
			location.href="${ctx}/manage/erecruitment/check?paperId="+id;
		}
		
		function edit(id,paperType){
			location.href="${ctx}/manage/erecruitment/edit?id="+id+"&paperType="+paperType;
		}
		function delVo(id){
			layer.confirm('你确定要删除选中的记录吗？', {
				icon: 3,
			  	btn: ['确定', '取消'] //按钮
			}, function(){
			$.ajax({
				type : "post",
				data:{"id" : id},
				url : "${ctx}/manage/erecruitment/deleteVo",
				dateType: "json",
				success : function(msg) {
					if (true== msg) {
						layer.alert("删除成功", {icon: 1}, function(){
							parent.location.href="${ctx}/manage/erecruitment/paperList";
						});
					}else {
						layer.alert("删除失败", {icon: 5});
					}
				}
			});
		  });
		}
		
</script>
</body>
</html>
