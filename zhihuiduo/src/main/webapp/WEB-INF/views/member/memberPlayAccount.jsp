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
		style="height: 80px; background-color: #E02E24; color: #ffffff; padding-left: 10px;">
		<div style="height: 1px;"></div>
		<div class="media">
        	<shiro:user>
			<div class="media-left" href="#">
				<img id="wechatIcon" class="media-object img-circle" <c:if test="${member!=null}"><c:if test="${member.icon!=null}">src="${ctx}/${member.icon}"</c:if></c:if> 
					style="width: 60px;">
			</div>
			<div class="media-body">
				<div style="height: 15px;"></div>
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
                <th data-column-id="createDate" data-formatter="createDate">时间</th>
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
	 <div style="height:5px">
		
	</div>
    <!-- 模态框（Modal） -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true"
		style="width: 100%; margin: 0; position: fixed; bottom: 0; left: 0; right: 0;">
		<div class="modal-dialog"
			style="width: 100%; margin: 0; position: fixed; bottom: 0; left: 0; right: 0; border-radius: 0;">
			<div class="modal-content" style="border-radius: 0;">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel"
						style="text-align: center;">趣味竞猜明细</h4>
				</div>
				<div class="modal-body" style="text-align: center;">
					<form role="form">
                      <div class="form-group">
                        <label for="createDate">时间</label>
                        <input type="text" class="form-control" id="createDate" placeholder="">
                      </div>
                      <div class="form-group">
                        <label for="zhihuidouCount">当时智慧豆</label>
                         <input type="text" class="form-control" id="zhihuidouCount" placeholder="">
                      </div>
                      
                     <div class="form-group">
                        <label for="name">明细</label>
                        <textarea id="name" class="form-control" rows="3">${product.description}</textarea>
                        
                      </div>
                    </form>
				</div>
				

			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
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
				keepSelection: true,
				formatters: {
					"createDate": function(column, row)
					{
						return formatDateString(row.createDate);
					}
				}
				
			});
			
			//$("#grid-data").bootgrid("destroy").bootgrid("search", "serach phrase");
    	});
		
		function showDetail(){
			var selectedRows = $("#grid-data").bootgrid("getSelectedRows");
			if(selectedRows.length==0){
				return;
			}
			var id=selectedRows[0];
			var currentRows = $("#grid-data").bootgrid("getCurrentRows");
			var obj={};
			for(var i=0;i<currentRows.length;i++){
				if(currentRows[i].id==id){
					obj=currentRows[i];
					break;
				}
			}
			
        	$("#createDate").val(formatDateString(obj.createDate));
			$("#zhihuidouCount").val(obj.zhihuidouCount);
			$("#name").val(obj.name);
			$("#myModal").modal("show");
		}
		
		function formatDateString(timestamp) {  
			//timestamp为秒数  
			var now = new Date(timestamp);  
			  
			var year=now.getFullYear();   
			var month=now.getMonth()+1;   
			var date=now.getDate();  
			var hour=now.getHours();   
			var minute=now.getMinutes();   
			var second=now.getSeconds();  
			var str = year+"-"+month+"-"+date+" "+" "+hour+":"+minute+":"+second;  
			return str;  
		}  
    </script>
</body>
</html>
