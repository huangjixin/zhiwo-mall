checked<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<title>富卫运维大平台</title>
<meta name="description" content="">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/elerning/style.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/elerning/font/iconfont.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/elerning/page.css">
</head>
<body>
<style type="text/css">

.form-detail {
    min-height: 51%;
    padding: 47px;
    background-color: #fff;
    border: 1px solid #dfdfdf;
    border-radius: 5px;
    width: 72%;
}

</style>


   <form id="PaperForm" action="${ctx}/manage/sms/getSmsList" method="post">

		<!-- S Filter Box -->
		<div class="filter-box">
			<ul class="ui-form grid-row">
				<li class="col-md-3"><strong>手机号码</strong><input  value="${phone}" id="phone" name="phone" type="text" placeholder=""></li>
						<li class="col-md-4">
					<strong>黑名单类型：</strong>
					
						<select id="type" name="type"  style="border-radius:3px;">
						    <option <c:if test="${type==''}">selected</c:if> value="">请选择</option>
							<option value="BUSNIESS" <c:if test="${type=='BUSNIESS'}">selected</c:if>>业务类</option>
							<option value="VALIDATE" <c:if test="${type=='VALIDATE'}">selected</c:if>>验证类</option>
						</select>
				</li>
				<li class="col-md-12" style="text-align: right">
					<button type="button" class="btn btn-submit btn-radius btn-search" onclick="selectBluk()"><i class="icon-search"></i> 查询</button>
					
				</li>
			</ul>
		</div>
		<!-- E Filter Box -->
		<div class="tab-btn">
		<button type="button" class="btn btn-success btn-radius" onclick="addBacth()"><i class="icon-jia"></i>批量添加</button><button type="button" class="btn btn-success btn-radius btn-list-delete" onclick="deleteBatchPaper()"><i class="icon-close red"></i>批量删除</button>
		</div>
		
		<table class="table table-agents ">
			<thead>
				<tr>
					<th>
						<label class="pos-rel">
							<input type="checkbox" class="ace" id="selectAll">
						</label>
					</th>
					<th>序号</th>
					<th>手机号码</th>
					<th>黑名单类型</th>
					<th>提交时间</th>
					<th>备注</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				
				<c:forEach items="${data.records}" var="SmsBlackUser" varStatus="ext"  >
                 <tr>
					<td>
						<label class="pos-rel">
							<input type="checkbox" class="ace"  name="checkbox" value="${SmsBlackUser.id }">
						</label>
					</td>
					<td ><a href="#" class="clickable" >${ext.index+1}</a></td>
					<td>${SmsBlackUser.phone}</td>
					<c:if test="${SmsBlackUser.type=='BUSNIESS'}">
					<td>业务类</td>
					</c:if>
					<c:if test="${SmsBlackUser.type=='VALIDATE'}">
					<td>验证类</td>
					</c:if>
					
					<td><fmt:formatDate value="${SmsBlackUser.createTime}" pattern="yyyy-MM-dd"/></td>
					<td>${SmsBlackUser.remark}</td>
					<td>
						<a class="color-detail"  onclick="dele('${SmsBlackUser.id}')" title="修改">移除</a>
					</td>
				</tr>
                    </c:forEach>
			</tbody>
		</table>
		<div class="table-paging clearfix" id="pageDiv">
		</div>
		</form>
		
		
	
	
		
		

<script type="text/javascript" src="${ctx}/resources/js/common/page.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/layer/layer.js"></script>



<script type="text/javascript">

function addBacth(){
	addBacth
	
	location.href="${ctx}/manage/sms/addSmsPage";
	
}


$(function(){
	var phone= '${phone}';
	var type= '${type}';
	pageAjax("${ctx}/manage/sms/getSmsListAjax?phone="+phone+"&type="+type,'${data.pageSize}','${data.pageNo}','${data.pageTotal}','pageDiv');
})

 //用于页面回显问题  视实际情况而定 
    var codeArray ={};
    codeArray['BUSNIESS'] = '业务类';
    codeArray['VALIDATE'] = '验证类';
   
 function pageNext(url){
    	// 查询字段取页面加载时 model传入的值  防止分页执行查询
   		var phone= $("#phone").val();
		var type = $("#type").val();
    	$.get(url,function(returnData){
    		if(returnData.code == '1'){
    			var html = "";
    			$.each(returnData.data.records,function(i,row){
        			row.remark = row.remark==null?'':row.remark; //判断null字段
        			var tex=row.createTime;
		   			 var time=crtTimeFtt(tex);
        			html+='<tr><td><label class="pos-rel"><input type="checkbox" class="ace"  name="checkbox" value="${SmsBlackUser.id }"></label></td>'+
        				  '<td>'+(i+1)+'</td>'+
        				  '<td>'+row.phone+'</td>'+
        				  '<td>'+codeArray[row.type]+'</td>'+
        				  '<td>'+time+'</td>'+
        				  '<td>'+row.remark+'</td>'+
        				  '<td><a class="color-detail"  onclick="dele('+'\''+row.id+'\''+')" title="修改">移除</a></td>';
        			})
    			$(".table tbody").html(html);
    			pageAjax("${ctx}/manage/sms/getSmsListAjax?phone="+phone+"&type="+type,returnData.data.pageSize,returnData.data.pageNo,returnData.data.pageTotal,'pageDiv');
    		}
    	})
    }
    
    


function selectBluk(){
	var phone= $("#phone").val();
	var type = $("#type").val();
$.ajax({
	  async : false,  
      cache : false,  
      type: 'POST',
      data:{"phone":phone,"type":type},
	      url: "${ctx }/manage/sms/getSmsListAjax",
	      dataType: "json",
	  success: function(returnData){
		 if(returnData.code==1){
			var html = "";
			$.each(returnData.data.records,function(i,row){
    			row.remark = row.remark==null?'':row.remark; //判断null字段
    			var tex=row.createTime;
	   			 var time=crtTimeFtt(tex);
    			html+='<tr><td><label class="pos-rel"><input type="checkbox" class="ace"  name="checkbox" value="${SmsBlackUser.id }"></label></td>'+
    				  '<td>'+(i+1)+'</td>'+
    				  '<td>'+row.phone+'</td>'+
    				  '<td>'+codeArray[row.type]+'</td>'+
    				  '<td>'+time+'</td>'+
    				  '<td>'+row.remark+'</td>'+
    				  '<td><a class="color-detail"  onclick="dele('+'\''+row.id+'\''+')" title="修改">移除</a></td>';
    			})
			$(".table tbody").html(html);
			pageAjax("${ctx}/manage/sms/getSmsListAjax?phone="+phone+"&type="+type,returnData.data.pageSize,returnData.data.pageNo,returnData.data.pageTotal,'pageDiv');
		 }
	  },
	  
	});
}	

//批量删除黑名单
	function deleteBatchPaper(){
		 var ids ="";
		 var groupCheckbox=$("input[name='checkbox']:checked");
		 if(groupCheckbox.length==0){
			 layer.alert("请选择要删除的选项");
			 return;
		 }
		     for(i=0;i<groupCheckbox.length;i++){
		        if(groupCheckbox.eq(i).is(":checked")){
		        	ids=ids+groupCheckbox[i].value+",";
		        }
		    } 
		     
		     $.ajax({
				  async : false,  
			      cache : false,  
			      type: 'GET',
		    	  url: "${ctx }/manage/sms/deleBacthSmsById?ids="+ids,
		    	  //dataType: "json",
		    	  success: function(data){
		    		  if(data.code==1){
		    			  layer.confirm(data.msg, {
				   				icon: 6,
				   			  	btn: ['确定'] //按钮
				   			}, function(){
				   			  location.href="${ctx}/manage/sms/getSmsList";
				   			}); 
		    		  }else{
		    			  layer.confirm(data.msg, {
				   				icon: 3,
				   			  	btn: ['确定'] //按钮
				   			}, function(){
				   			  location.href="${ctx}/manage/sms/getSmsList";
				   			}); 
		    		  }
		    	  },
		    	  error: function (data) {//请求失败处理函数  
		    		  layer.confirm(data.msg, {
			   				icon: 3,
			   			  	btn: ['确定'] //按钮
			   			}, function(){
			   			  location.href="${ctx}/manage/sms/getSmsList";
			   			}); 
			        },
		    	  
		    	});
	 }

	$("#selectAll").click(function(){//给全选按钮加上点击事件
	    var xz = $(this).prop("checked");//判断全选按钮的选中状态
	    var ck = $(".ace").prop("checked",xz);  //让class名为ace的选项的选中状态和全选按钮的选中状态一致。  
	    })

	    
	   function dele(id){
		
		
		  $.ajax({
			  async : false,  
		      cache : false,  
		      type: 'GET',
	    	  url: "${ctx }/manage/sms/deleSmsById?id="+id,
	    	  success: function(data){
	    		  if(data.code=='1'){
	    			  layer.confirm("删除成功", {
			   				icon: 6,
			   			  	btn: ['确定'] //按钮
			   			}, function(){
			   			  location.href="${ctx}/manage/sms/getSmsList";
			   			}); 
	    		  }else{
	    			  layer.confirm(data.msg, {
			   				icon: 3,
			   			  	btn: ['确定'] //按钮
			   			}, function(){
			   			  location.href="${ctx}/manage/sms/getSmsList";
			   			}); 
	    		  }
	    	  },
	    	  error: function (data) {//请求失败处理函数  
	    		  layer.confirm(data.msg, {
		   				icon: 3,
		   			  	btn: ['确定'] //按钮
		   			}, function(){
		   			  location.href="${ctx}/manage/sms/getSmsList";
		   			}); 
		        },
	    	  
	    	});
	}
	
	
	function crtTimeFtt(val) {
	    if (val != null) {
	            var date = new Date(val);
	            return date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate();
	        }
	}

</script>
</body>
</html>
