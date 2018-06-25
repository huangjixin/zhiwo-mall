<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
		<!-- S Filter Box -->
		<div class="filter-box">
		<form action="${ctx }/manage/offlineManage/planListByPage" method="post">
			<ul class="ui-form grid-row">
				<li class="col-md-4">
					<strong style="width: 105px;">活动名称</strong><input id="sname" name="name" value="${name }" class="ipt-text" type="text" placeholder="课程/活动名称">
				</li>
				<li class="col-md-3"><!-- style="width: 105px;" -->
					<strong >活动状态</strong>
						<select name="state" id="sstate" style="border-radius:3px;">
							<option <c:if test="${state=='' }">selected</c:if>  value="">请选择</option>
							<option <c:if test="${state=='0' }">selected</c:if> value="0">下架</option>
							<option <c:if test="${state=='1' }">selected</c:if> value="1">上架</option>
							<%-- <option <c:if test="${state=='2' }">selected</c:if> value="2">WORD</option>
							<option <c:if test="${state=='3' }">selected</c:if> value="3">EXCEL</option> --%>
						</select>
				</li>
				<li class="col-md-6 sameWadte">
					<strong >活动报名时间</strong>&nbsp;&nbsp;
					<input name="enterStartDate" id="sDate" value="${enterStartDate }"  type="text" placeholder="报名开始时间" class="Wdate" style="max-width: 180px;" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'eDate\')}'})">&nbsp;—&nbsp;
					<input name="enterEndDate" id="eDate"  value="${enterEndDate }" type="text" placeholder="报名结束时间" class="Wdate" style="max-width: 180px;" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'sDate\')}'})">
				</li>
			</ul>
			<button type="submit" class="btn btn-submit btn-radius btn-search"><i class="icon-search"></i>查询</button>
			</form>
		</div>
		<div class="tab-btn">
		<a class="btn btn-success btn-radius" href="${ctx }/manage/offlineManage/skipPage?pageName=offline_addEdit" title="新增"><i class="icon-jia"></i>新增</a>
		<button type="button" onclick="batchoffactivity()" class="btn btn-success btn-radius btn-list-stop"><i class="icon-tingzhishangbao ong"></i>批量下架</button>
		<button type="button" onclick="batchupactivity()" class="btn btn-success btn-radius btn-list-start"><i class="icon-qiyong green"></i>批量上架</button>
		<button type="button" onclick="batchdelete()" class="btn btn-success btn-radius btn-list-delete"><i class="icon-close red"></i>批量删除</button>
		</div>
		<!-- E Filter Box -->
		<table class="table table-agents">
			<thead>
				<tr>
					<th>
						<label class="pos-rel">
							<input type="checkbox"  class="ace" id="checkbox">
						</label>
					</th>
					<th>活动名称</th>
					<th>活动状态</th>
					<th>参与人数</th>
					<th>星级评分</th>
					<th>开始日期</th>
					<th>结束日期</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody id="tb">
			<c:forEach items="${offlineActivityList}" var="row">
				<tr>
					<td>
						<label class="pos-rel">
							<input type="checkbox" state="${row.state }" name="checkbox" class="ace" value="${row.id }">
						</label>
					</td>
					<td>${row.name }</td>
					<td>
					<c:choose>
					<c:when test="${row.state==1 }">上架</c:when>
					<c:when test="${row.state==0 }">下架</c:when>
					<c:otherwise>未上架</c:otherwise>
					</c:choose>
					</td>
					<td>${row.enter }</td>
					<td>${row.star }</td>
					<td><fmt:formatDate value="${row.startDate }" pattern="yyyy-MM-dd"/></td>
					<td><fmt:formatDate value="${row.endDate }" pattern="yyyy-MM-dd"/></td>
					<td>
					   <c:if test="${row.state eq null}">
					       <a class="color-detail" href="javascript:;"  onclick="edit('${row.id}',0)" title="修改">修改</a>
					   </c:if>
					   <c:if test="${row.state ne null}">
                           <a class="color-detail" href="javascript:;"  onclick="edit('${row.id}',${row.state})" title="修改">修改</a>
                       </c:if>
					    
						<a class="color-detail" href="${ctx }/manage/questionListByPage?courseId=${row.courseId}"  title="提问">提问</a>
						<a class="color-detail" href="${ctx }/manage/commentListByPage?courseId=${row.courseId}" title="评论">评论</a>
						<a class="color-detail" href="${ctx }/manage/offlineManage/findByid?id=${row.id}"+"&state=${row.state }"  title="查看">查看</a>
					</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		<!-- / E Table -->
		<div class="table-paging clearfix" id="pageDiv">
		</div>
        <script type="text/javascript" src="${ctx}/resources/libs/layer/layer.js" ></script>
	    <script type="text/javascript" src="${ctx}/resources/libs/datepicker/WdatePicker.js"></script>
	    <script type="text/javascript" src="${ctx}/resources/js/common/page.js"></script>
	    <script type="text/javascript">
	    //实现全选与全不选
		  $("#checkbox").click(function() {
			  if ($(this).is(':checked')) {
			  $("input[name='checkbox']").prop("checked",true);
			  } else {
			  $("input[name='checkbox']").prop("checked",false);
				}
			  }); 
	   
	     //批量下架
	     function batchoffactivity(){
		   var str='';
		   $("#tb").find("input[type='checkbox']").each(function(index,ele){
			   if($(this).is(":checked")){
				   var rs=this.value;
   				   str=str+rs+",";
			   }
		   });
		   var len=str.length;
		   str=str.substring(0,len-1);
		   $.ajax({  
		        type: 'POST',  
		        dataType :"json",  
		        data :{"ids":str,"type":"offactive","state":"0"},
		        url: "${ctx}/manage/offlineManage/updateOrDele",//请求的action路径  
		        error: function (data) {//请求失败处理函数  
		            layer.msgs("失败"); 
		        },  
		        success:function(data){ //请求成功后处理函数。
		        	if(data.code==1){
		           location.href="${ctx }/manage/offlineManage/planListByPage";
		        	}else{
		        		layer.msg("error");
		        	}
		        }  
		    }); 
	   }
	   
	   //批量上架
       function batchupactivity(){
    	   var str='';
		   $("#tb").find("input[type='checkbox']").each(function(index,ele){
			   if($(this).is(":checked")){
				   var rs=this.value;
   				   str=str+rs+",";
			   }
		   });
		   var len=str.length;
		   str=str.substring(0,len-1);
		   $.ajax({  
		        type: 'POST',  
		        dataType :"json",  
		        data :{"ids":str,"type":"offactive","state":"1"},
		        url: "${ctx}/manage/offlineManage/updateOrDele",//请求的action路径  
		        error: function (data) {//请求失败处理函数  
		            layer.msg("失败"); 
		        },  
		        success:function(data){ //请求成功后处理函数。
		        if(data.code==1){
		        location.href="${ctx }/manage/offlineManage/planListByPage";
		        	}else{
		        		layer.msg("error")
		        	}
		        }  
		    }); 
	   }
	   
	   //批量删除
       function batchdelete(){
    	   var str='';
    	   var state=0;
		   $("#tb").find("input[type='checkbox']").each(function(index,ele){
			   if($(this).is(":checked")){
				   var rs=this.value;
				   str=str+rs+",";
				   var stat=$(this).attr("state");
				   if(stat==1){
					  state=1;
				   }
			   }
		   });
		   if(state==1){//若state为1则存在上架的
			   layer.msg('选中中存在状态为上架的活动');
		   }else{
			   deletes(str); 
		   }
       }
	   
	   function deletes(str){
		   if(str.length>0){//若大于零则存在选中项
		   var len=str.length;
		   str=str.substring(0,len-1);
		   $.ajax({  
		        type: 'POST',  
		        dataType :"json",  
		        data :{"ids":str,"type":"dele"},
		        url: "${ctx}/manage/offlineManage/updateOrDele",//请求的action路径  
		        error: function (data) {//请求失败处理函数  
		            layer.msg("失败"); 
		        },  
		        success:function(data){ //请求成功后处理函数。
		        	if(data.code==1){
		        	location.href="${ctx }/manage/offlineManage/planListByPage";
		        	}
		        	else{
		        		layer.msg("error");
		        	}
		        }  
		    }); 
		  }else{
			  layer.msg("请先选择记录");  
		  }
	   }
	   
       $(function(){
   		var name ='${name }';
   		var state = '${state}';
   		var startDate = '${enterStartDate }';
   		var endDate = '${enterStartDate }';
   		pageAjax("${ctx}/manage/offlineManage/listByPageAjax?name="+name+"&state="+state+"&startDate="+startDate+"&endDate="+endDate,'${pageList.pageSize}','${pageList.pageNo}','${pageList.pageTotal}','pageDiv');
   		/* page("${ctx}/manage/offlineManage/planListByPage?name="+name+"&state="+state+"&startDate="+startDate+"&endDate="+endDate,'${pageList.pageSize}','${pageList.pageNo}','${pageList.pageTotal}','pageDiv'); */
   	});
       
       //用于页面回显问题  视实际情况而定 
       var codeArray ={};
       codeArray['state0'] = '下架';
       codeArray['state1'] = '上架';
       codeArray['state3'] = '未上架';
       
       //ajax 分页 拼接数据
       function pageNext(url){
       	// 查询字段取页面加载时 model传入的值  防止分页执行查询
        var name ='${name }';
   		var state = '${state}';
   		var startDate = '${enterStartDate }';
   		var endDate = '${enterStartDate }';
       	$.get(url,function(returnData){
       		if(returnData.code == '1'){
       			var html = "";
       			$.each(returnData.data.records,function(i,row){
       			row.star = row.groupId==null?'':row.star; //判断null字段
       			if(row.state==null)
       				row.state=3;
       			codeArray['state'+row.state] =codeArray['state'+row.state] == undefined ?'':codeArray['state'+row.state]; //判断undefined字段 */
       			html+='<tr><td><label class="pos-rel">'+
       				  '<input type="checkbox" state=\''+row.state+'\' name="checkbox" class="ace" value="'+row.id+'"></label></td>'+
       				  '<td>'+row.name+'</td>'+
       				  '<td>'+codeArray['state'+row.state]+'</td>'+
       				  '<td>'+row.enter+'</td>'+
       				  '<td>'+row.star+'</td>'+
       				  '<td>'+row.startDate+'</td>'+
       				  '<td>'+row.endDate+'</td>'+
       				  '<td><a class="color-detail" href="javascript:;"  onclick="edit(\''+row.id+'\',\''+row.state+'\')" title="修改">修改</a>'+
       				  '<a class="color-detail"  href="javascript:;" onclick="question(\''+row.courseId+'\')"  title="提问">提问</a>'+
       				  '<a class="color-detail"  href="javascript:;" onclick="comment(\''+row.courseId+'\')" title="评论">评论</a>'+
       				  '<a class="color-detail"  href="javascript:;" onclick="see(\''+row.id+'\',\''+row.state+'\')"  title="查看">查看</a></td></tr>';
       			})
       			$(".table tbody").html(html);
       			pageAjax("${ctx}/manage/offlineManage/listByPageAjax?name="+name+"&state="+state+"&startDate="+startDate+"&endDate="+endDate,returnData.data.pageSize,returnData.data.pageNo,returnData.data.pageTotal,'pageDiv');
       		}
       	})
       }
       
       //修改
       function edit(id,state){
    		if(state==1){
    			layer.msg('该条记录状态为上架状态，不能被修改~');
    		}else{
    			location.href="${ctx }/manage/offlineManage/editByid?id="+id;
    		}
       }
       //提问
       function question(courseId){
    	   location.href="${ctx }/manage/questionListByPage?courseId="+courseId;
       }
      
      //评论
      function comment(courseId){
    	  location.href="${ctx }/manage/commentListByPage?courseId?courseId="+courseId;
      }
       
      //查看
      function see(id,state){
      location.href="${ctx }/manage/offlineManage/findByid?id="+id+"&state="+state;
      }
    </script>
   
</body>
</html>