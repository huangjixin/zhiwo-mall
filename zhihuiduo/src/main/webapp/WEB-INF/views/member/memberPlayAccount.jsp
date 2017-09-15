<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>智惠多商品云购.会员账户明细</title>
<link href="https://cdn.bootcss.com/jquery-bootgrid/1.3.1/jquery.bootgrid.min.css" rel="stylesheet" type="text/css">
<%@ include file="/WEB-INF/member-include/css.jsp"%>
<%@ include file="/WEB-INF/member-include/js.jsp"%>
<script type="text/javascript" src="https://cdn.bootcss.com/jquery-bootgrid/1.3.1/jquery.bootgrid.min.js"></script>
</head>
<body>
	<div
		style="height: 110px; background-color: #E02E24; color: #ffffff; padding-left: 10px;">
		<div style="height: 1px;"></div>
		<div class="media">
        	<shiro:user>
			<div class="media-left" href="#">
				<img id="wechatIcon" class="media-object img-circle" <c:if test="${member!=null}"><c:if test="${member.icon!=null}">src="${ctx}/${member.icon}"</c:if></c:if> 
					style="width: 80px;">
			</div>
			<div class="media-body">
				<div style="height: 25px;"></div>
				<h4 class="media-heading" id="wechatName"><shiro:principal/></h4>
                	余额: ${memberPlayAccount.zhihuidouCount} 智惠豆
			</div>
            </shiro:user>
		</div>
	</div>
    <table id="grid-data" class="table table-condensed table-hover table-striped" data-toggle="bootgrid" >
        <thead>
            <tr>
            	<th data-column-id="id" data-identifier="true">ID</th>
                <th data-column-id="createDate">时间</th>
                <th data-column-id="zhihuidouCount">当时余额</th>
                <th data-column-id="name">明细</th>
            </tr>
        </thead>
    </table>
    
    <div style="width: 100%; text-align: center"
		>
		<label onClick="javascript:history.back();" class="activeProperyValue">返回</label>&nbsp;<label class="activeProperyValue"
        onClick="javascript:showDetail();">查看明细</label>
	</div>
	 <div style="height:20px">
		
	</div>
    <script>
    	$(function () {
			
        	$("#grid-data").bootgrid({
				navigation:2,
				ajax: true,
				url: "${ctx}/memberInfo/selectMemberPlayHisAccount",
				labels: {
					infos:"显示：{{ctx.start}}到{{ctx.end}}总共{{ctx.total}}记录"
				},
				selection: true,
				multiSelect: false,
				rowSelect: true,
				keepSelection: true
				
			});
			
			//$("#grid-data").bootgrid("destroy").bootgrid("search", "serach phrase");
    	});
		
		function showDetail(){
			var selectedRows = $("#grid-data").bootgrid("getSelectedRows");
			var currentRows = $("#grid-data").bootgrid("getCurrentRows");
			var rows = $("#grid-data").bootgrid("getSelectedRows");	
			var i = 0;
		}
    </script>
</body>
</html>
