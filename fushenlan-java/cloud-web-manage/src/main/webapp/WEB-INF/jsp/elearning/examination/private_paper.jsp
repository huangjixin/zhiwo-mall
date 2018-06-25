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
   <form id="PaperForm" action="${ctx}/manage/paper/GetPrivatePaper" method="post">

		<div class="filter-box">
			<ul class="ui-form grid-row">
				<li class="col-md-4"><strong>试卷名称</strong><input class="ipt-text" value="${name}" style="margin-left: 0px; id="paperName" name="name" type="text" placeholder=""></li>
				<li class="col-md-12" style="text-align: right">
					<button type="submit" class="btn btn-submit btn-radius btn-search" onclick=""><i class="icon-search"></i> 查询</button>
					<a class="btn btn-success btn-radius" href="${ctx}/manage/paper/addPaper"  title="新增">新增</a>
					<button type="button" class="btn btn-success btn-radius" onclick="shareBatchPaper()">批量分享</button>
					<button type="button" class="btn btn-success btn-radius" onclick="deleteBatchPaper()">批量删除</button>
				</li>
			</ul>
		</div>
		
		<table class="table table-agents ">
			<thead>
				<tr>
					<th>
						<label class="pos-rel">
							<input type="checkbox" class="ace" id="selectAll">
						</label>
					</th>
					<th>试卷号</th>
					<th>试卷名称</th>
					<th>题量</th>
					<th>通过分数/总分</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				
				<c:forEach items="${data.records}" var="paper" varStatus="ext" >
                 <tr>
					<td>
						<label class="pos-rel">
							<input type="hidden" name="isShare" id="isShare" value="${paper.isShare}">
							<input type="checkbox" class="ace"  name="checkbox" value="${paper.id }">
						</label>
					</td>
					<td>${ext.index+1}</td>
					<td ><a href="#" class="clickable" >${paper.name}</a></td>
					<td>${paper.classNum}</td>
					<td>${paper.passMark}/${paper.fullMark}</td>
					<td>
						<a class="color-detail" href="${ctx}/manage/paper/updatePaper?id=${paper.id}" title="修改">修改</a>
						<a class="color-edit" href="${ctx}/manage/paper/sharePaper?id=${paper.id}&name=${paper.name}&isShare=${paper.isShare}" title="分享"  >分享</a>
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

$(function(){
	var name='${name}';
	var tagId ='${tagId}';
	pageAjax("${ctx}/manage/paper/ajaxPrivatePaper?name="+name+"&tagId="+tagId,'${data.pageSize}','${data.pageNo}','${data.pageTotal}','pageDiv');
})


//批量删除试卷
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
 		    	  url: "${ctx }/manage/paper/verify?ids="+ids,
 		    	  //dataType: "json",
 		    	  success: function(data){
 		    		  if(data.code==1){
 		    			  
 		    			  layer.confirm(data.msg, {
 		    	   				icon: 6,
 		    	   			  	btn: ['确定','取消'] //按钮
 		    	   			}, function(){
 		    	   				
 		    	   			   $.ajax({
 		    					  async : false,  
 		    				      cache : false,  
 		    				      type: 'GET',
 		    			    	  url: "${ctx }/manage/paper/deleteBatchPaper?ids="+ids,
 		    			    	  //dataType: "json",
 		    			    	  success: function(data){
 		    			    		  if(data.code==1){
 		    			    			  layer.confirm(data.msg, {
 		    					   				icon: 6,
 		    					   			  	btn: ['确定'] //按钮
 		    					   			}, function(){
 		    					   			  location.href="${ctx}/manage/paper/GetPrivatePaper";
 		    					   			}); 
 		    			    		  }else{
 		    			    			  layer.confirm(data.msg, {
 		    					   				icon: 3,
 		    					   			  	btn: ['确定'] //按钮
 		    					   			}, function(){
 		    					   			  location.href="${ctx}/manage/paper/GetPrivatePaper";
 		    					   			}); 
 		    			    		  }
 		    			    	  },
 		    			    	  error: function (data) {//请求失败处理函数  
 		    			    		  layer.confirm(data.msg, {
 		    				   				icon: 3,
 		    				   			  	btn: ['确定'] //按钮
 		    				   			}, function(){
 		    				   			  location.href="${ctx}/manage/paper/GetPrivatePaper";
 		    				   			}); 
 		    				        },
 		    			    	  
 		    			    	});
 		    	   			},function(){
 		    	   			location.href="${ctx}/manage/paper/GetPrivatePaper";
 		    	   			}); 
 		    		  }else{
 		    			  layer.confirm(data.msg, {
 		    	   				icon: 3,
 		    	   			  	btn: ['确定'] //按钮
 		    	   			}, function(){
 		    	   				location.href="${ctx}/manage/paper/GetPrivatePaper";
 		    	   			}); 
 		    			  
 		    		  }
 		    	  },
 		    	  error: function (data) {//请求失败处理函数  
 		    		  layer.confirm(data.msg, {
 	    	   				icon: 3,
 	    	   			  	btn: ['确定'] //按钮
 	    	   			}, function(){
 	    	   				location.href="${ctx}/manage/paper/GetPrivatePaper";
 	    	   			}); 
 			        },
 		    	  
 		    	});
		     
		     
		  
	 }

	$("#selectAll").click(function(){//给全选按钮加上点击事件
	    var xz = $(this).prop("checked");//判断全选按钮的选中状态
	    var ck = $(".ace").prop("checked",xz);  //让class名为ace的选项的选中状态和全选按钮的选中状态一致。  
	    })

	    
	 function shareBatchPaper(){
	 var ids = new Array();
	 var names = new Array();
	 var groupCheckbox=$("input[name='checkbox']:checked");
	 if(groupCheckbox.length==0){
		 layer.alert("请选择要分享的选项");
		 return;
	 }
	 var shareName = "";
     for(i=0;i<groupCheckbox.length;i++){
        if(groupCheckbox.eq(i).is(":checked")){
        	if(groupCheckbox.eq(i).prev().val() == '1'){
        		shareName +=groupCheckbox.eq(i).parent().parent().siblings().find(".clickable").text()+",";
        	}
        	var name=groupCheckbox.eq(i).parent().parent().siblings().find(".clickable").text();
	    	ids[i] =groupCheckbox[i].value;
	    	names[i] =name;
        }
    }
    if(""!=shareName){
    	shareName = shareName.substring(0,shareName.length-1);
    	layer.confirm(shareName+'已分享过,是否重新选择', {
			icon: 3,
		  	btn: ['确定', '取消'] //按钮
		}, function(){
			 location.href="${ctx}/manage/paper/shareBatchPaper?ids="+ids+"&names="+names+"&isShare="+0;
		});
    }else{
    	location.href="${ctx}/manage/paper/shareBatchPaper?ids="+ids+"&names="+names+"&isShare="+0;
    }
   
	 
 }
	//用于页面回显问题  视实际情况而定 
	 var codeArray ={};
	 codeArray['type0'] = '单选';
	 codeArray['type1'] = '多选';
	 codeArray['type2'] = '判断';
	 codeArray['type3'] = '问答';
	 
	 
	 
	 function pageNext(url){
	    	// 查询字段取页面加载时 model传入的值  防止分页执行查询
			 var name='${name}';
			 var tagId ='${tagId}';
	    	$.get(url,function(returnData){
	    		if(returnData.code == '1'){
	    			var html = "";
	    			$.each(returnData.data.records,function(i,row){
	    	   			var tex=row.gmtModified;
	    	   			html+='<tr><td><label class="pos-rel"><input type="checkbox" class="ace"  name="checkbox" value="${paper.id }"></label></td>'+
	    	   				 '<td>'+(i+1)+'</td>'+
	    	   				 '<td>'+row.name+'</td>'+
	    	   				 '<td>'+row.classNum+'</td>'+
	    	   				 '<td>'+row.passMark+'/'+row.fullMark+'</td>'+
	    	   				 '<td>'+codeArray[row.groupId]+'</td>'+
	    	   				 '<td>'+codeArray[row.tagId]+'</td>'+
	    	   				 '<td><a class="color-detail" href="${ctx}/manage/paper/updatePaper?id='+row.id+' title="修改">修改</a><a class="color-edit" href="${ctx}/manage/paper/sharePaper?id='+row.id+'&name='+row.name+' title="分享"  >分享</a></td>';
	    	   			})
	    			$(".table tbody").html(html);
	    			pageAjax("${ctx}/manage/paper/ajaxPrivatePaper?name="+name+"&tagId="+tagId,returnData.data.pageSize,returnData.data.pageNo,returnData.data.pageTotal,'pageDiv');
	    		}
	    	})
	    }
	    
	 
	 
	 
	 
	 
	 
	 
	 
	 
	function selectPrivateAjax(){
		$.ajax({
			  async : false,  
		      cache : false,  
		      type: 'POST',
		      data:$("#PaperForm").serialize(),
		      url: "${ctx }/manage/paper/ajaxPrivatePaper",
		      dataType: "json",
		  success: function(returnData){
			 if(returnData.code==1){
				var html = "";
				$.each(returnData.data.records,function(i,row){
	   			row.groupId = row.groupId==null?'':row.groupId; //判断null字段
	   			row.createUser = row.createUser==null?'':row.createUser; //判断null字段
	   			codeArray[row.tagId] =codeArray[row.tagId] == undefined ?'':codeArray[row.tagId]; //判断undefined字段
	   			var tex=row.gmtModified;
	   			html+='<tr><td><label class="pos-rel"><input type="checkbox" class="ace"  name="checkbox" value="${paper.id }"></label></td>'+
	   				 '<td>'+(i+1)+'</td>'+
	   				 '<td>'+row.name+'</td>'+
	   				 '<td>'+row.classNum+'</td>'+
	   				 '<td>'+row.passMark+'/'+row.fullMark+'</td>'+
	   				 '<td>'+codeArray[row.groupId]+'</td>'+
	   				  '<td>'+codeArray[row.tagId]+'</td>'+
	   				 '<td><a class="color-detail" href="${ctx}/manage/paper/updatePaper?id='+row.id+' title="修改">修改</a><a class="color-edit" href="${ctx}/manage/paper/sharePaper?id='+row.id+'&name='+row.name+' title="分享"  >分享</a></td>';
	   			})
	   			$(".table tbody").html(html);
				}
		  },
		  
		  
		});
		 
	}
</script>
</body>
</html>
