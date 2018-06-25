<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>

</head>
<body>

     <div class="nav-tabs" name="planTypeOther">
                <strong id="pvate" class="active" onclick="changeOne('1')">公共试卷</strong>
				<strong id="plic" onclick="changeOne('2')">私人试卷</strong>
	 </div>
	  <c:if test="${pType==1 }">	 
       <div id="pubP">
		<div class="filter-box">
			<ul class="ui-form grid-row">
				<li class="col-md-5"><strong>试卷名称</strong><input class="ipt-text" id="paperName" name="name" style="margin-left: 0px; value="${name}" type="text" placeholder=""></li>
				<li class="col-md-8" style="text-align: right"><button type="submit" class="btn btn-submit btn-radius btn-search" onclick="ftr()"><i class="icon-search"></i> 查询</button></li>
			</ul>
		</div>
		<table class="table table-agents table_public">
			<thead>
				<tr>
					<th>试卷号</th>
					<th>试卷名称</th>
					<th>题量</th>
					<th>通过分数/总分</th>
					<th>最后编辑时间</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				
			<c:forEach items="${data.records}" var="paper" varStatus="ext" >
                  <tr>
                  	<td>${ext.index+1}</td>
					<td>${paper.name}</td>
					<td>${paper.classNum}</td>
					<td>${paper.passMark}/${paper.fullMark}</td>
					<td><fmt:formatDate value="${paper.gmtModified}" pattern="yyyy-MM-dd"/>  </td> 
					<td>
						<a class="color-detail" href="#" onclick="selectPaper('${paper.id}')"  title="查看">查看</a>
					</td>
				</tr>
                    </c:forEach>
			</tbody>
		</table>
		<div class="table-paging clearfix" id="pageDiv">
		</div>
	</div>
	</c:if>
	
	
	<c:if test="${pType==2 }">	 
	   <div id="priP">
	        <div class="filter-box">
			<ul class="ui-form grid-row">
				<li class="col-md-4"><strong>试卷名称</strong><input class="ipt-text" value="${name}" style="margin-left: 0px;" id="paperOtherName" name="name" type="text" placeholder=""></li>
				<li class="col-md-12" style="text-align: right">
					<button type="submit" class="btn btn-submit btn-radius btn-search" onclick="ftr()"><i class="icon-search"></i> 查询</button>
					<a class="btn btn-success btn-radius" href="${ctx}/manage/paper/addPaper"  title="新增">新增</a>
					<button type="button" class="btn btn-success btn-radius" onclick="sharePapers()">批量分享</button>
					<button type="button" class="btn btn-success btn-radius" onclick="deleteBatchPaper()">批量删除</button>
				</li>
			</ul>
		</div>
		
		<table class="table table-agents">
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
						
						<a class="color-edit" onclick="sharePaper('${paper.name }','${paper.id }')" title="分享"  >分享</a>
					</td>
				</tr>
                    </c:forEach>
			</tbody>
		</table>
		<div class="table-paging clearfix" id="pageDivOther">
		</div>
	   </div>	
	</c:if>
	
		
		<script type="text/javascript" src="${ctx}/resources/js/common/page.js"></script>
		<script type="text/javascript" src="${ctx}/resources/libs/layer/layer.js"></script>
		<script type="text/javascript">
		  
		
		
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
	 		    					   			  location.href="${ctx}/manage/paper/GetPublicPaper?otherType="+1;
	 		    					   			}); 
	 		    			    		  }else{
	 		    			    			  layer.confirm(data.msg, {
	 		    					   				icon: 3,
	 		    					   			  	btn: ['确定'] //按钮
	 		    					   			}, function(){
	 		    					   			  location.href="${ctx}/manage/paper/GetPublicPaper?otherType="+1;
	 		    					   			}); 
	 		    			    		  }
	 		    			    	  },
	 		    			    	  error: function (data) {//请求失败处理函数  
	 		    			    		  layer.confirm(data.msg, {
	 		    				   				icon: 3,
	 		    				   			  	btn: ['确定'] //按钮
	 		    				   			}, function(){
	 		    				   			  location.href="${ctx}/manage/paper/GetPublicPaper?otherType="+1;
	 		    				   			}); 
	 		    				        },
	 		    			    	  
	 		    			    	});
	 		    	   			},function(){
	 		    	   			location.href="${ctx}/manage/paper/GetPublicPaper?otherType="+1;
	 		    	   			}); 
	 		    		  }else{
	 		    			  layer.confirm(data.msg, {
	 		    	   				icon: 3,
	 		    	   			  	btn: ['确定'] //按钮
	 		    	   			}, function(){
	 		    	   				location.href="${ctx}/manage/paper/GetPublicPaper?otherType="+1;
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
		
		 $("#selectAll").click(function(){//给全选按钮加上点击事件
			    var xz = $(this).prop("checked");//判断全选按钮的选中状态
			    var ck = $(".ace").prop("checked",xz);  //让class名为qx的选项的选中状态和全选按钮的选中状态一致。  
		  })
		
		
		//查询
	    function ftr(){
	    	if("${pType}"==1){
	    		var name = $("#paperName").val();
	    		location.href="${ctx}/manage/paper/GetPublicPaper?name="+name+"&otherType="+0;
	    	}else if("${pType}"==2){
	    		var name = $("#paperOtherName").val();
	    		location.href="${ctx}/manage/paper/GetPublicPaper?name="+name+"&otherType="+1;
	    	}
	    }
		
		//页签切换
	    function changeOne(type){
		    if(type==1){
				location.href="${ctx}/manage/paper/GetPublicPaper?otherType="+0;
				document.getElementById("pubP").style.display="none";
				document.getElementById("priP").style.display="";
		    }else if(type==2){
				location.href="${ctx}/manage/paper/GetPublicPaper?otherType="+1;
				document.getElementById("priP").style.display="none";
				document.getElementById("pubP").style.display="";
		    }
	    }
		
		$(function(){
			   if("${pType}"==1){
				   $("#pvate").addClass("active");
				   $("#plic").removeClass("active");
			   }else if("${pType}"==2){
				   $("#plic").addClass("active");
				   $("#pvate").removeClass("active");
			   }
			if("${pType}"==1){
				var name='${name}';
				var createUser ='${createUser}';
				var groupId ='${groupId}';
				var tagId ='${tagId}';
				pageAjax("${ctx}/manage/paper/AjaxPaper?name="+name+"&createUser="+createUser+"&groupId="+groupId+"&tagId="+tagId,'${data.pageSize}','${data.pageNo}','${data.pageTotal}','pageDiv');
			 }else if("${pType}"==2){
				    var name='${name}';
					var tagId ='${tagId}';
					pageAjax("${ctx}/manage/paper/ajaxPrivatePaper?name="+name+"&tagId="+tagId,'${data.pageSize}','${data.pageNo}','${data.pageTotal}','pageDivOther');
			}
		})
		
		function selectPaper(id){
			location.href="${ctx}/manage/paper/selectPaper?id="+id;
		}
		 var codeArray ={};
		 codeArray['type0'] = '单选';
		 codeArray['type1'] = '多选';
		 codeArray['type2'] = '判断';
		 codeArray['type3'] = '问答';
		 
		 
		 
		   function pageNext(url){
			   if("${pType}"==1){
				   	// 查询字段取页面加载时 model传入的值  防止分页执行查询
					  var name='${name}';
						var createUser ='${createUser}';
						var groupId ='${groupId}';
						var tagId ='${tagId}';
					    	$.get(url,function(returnData){
					    		if(returnData.code == '1'){
					    			var html = "";
					    			$.each(returnData.data.records,function(i,row){
							   			var tex=row.gmtModified;
							   			 var time=crtTimeFtt(tex);
							   			html+='<tr>'+
							   				 '<td>'+(i+1)+'</td>'+
							   				 '<td>'+row.name+'</td>'+
							   				 '<td>'+row.classNum+'</td>'+
							   				 '<td>'+row.passMark+'/'+row.fullMark+'</td>'+
							   				 '<td>'+time+'</td>'+
							   				 '<td><a class="color-detail" href="#" onclick="selectPaper('+row.id+')"  title="查看">查看</a></td>';
							   			})
					    			$(".table tbody").html(html);
					    			pageAjax("${ctx}/manage/paper/AjaxPaper?name="+name+"&createUser="+createUser+"&groupId="+groupId+"&tagId="+tagId,returnData.data.pageSize,returnData.data.pageNo,returnData.data.pageTotal,'pageDiv');
					    		}
					    	})
			     }else if("${pType}"==2){
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
			    	   				 '<td><a class="color-detail" href="${ctx}/manage/paper/updatePaper?id='+row.id+' title="修改">修改</a><a class="color-edit" onclick="sharePaper('+row.name+','+row.id+')" title="分享">分享</a></td>';
			    	   			})
			    			$(".table tbody").html(html);
			    			pageAjax("${ctx}/manage/paper/ajaxPrivatePaper?name="+name+"&tagId="+tagId,returnData.data.pageSize,returnData.data.pageNo,returnData.data.pageTotal,'pageDivOther');
			    		}
			    	})
			     }
		 
		    }
		    
		   
		   function sharePaper(name,id) {
				 var paperIds = new Array();
				 var paperNames = new Array(); 
				 paperIds[0] =id;
				 paperNames[0] =name;
		     	location.href="${ctx}/manage/paper/sharePaper?paperIds="+paperIds+"&paperNames="+paperNames;
			}
		   
		   
		   
		   function sharePapers() {
				var jsonStr =[];
				 var paperIds = new Array();
				 var paperNames = new Array();
				 var groupCheckbox=$("input[name='checkbox']:checked");
			     for(i=0;i<groupCheckbox.length;i++){
			        if(groupCheckbox.eq(i).is(":checked")){
			        	var name=groupCheckbox.eq(i).parent().parent().siblings().find(".clickable").text();
			        	alert(name);
		        	paperIds[i] =groupCheckbox[i].value;
		        	paperNames[i] =name;
			        }
			    }
				if (paperIds.length <= 0) {
					 layer.alert("请先选择要分享的资料");
				} else {
					 location.href="${ctx}/manage/paper/sharePaper?paperIds="+paperIds+"&paperNames="+paperNames;
				}
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
