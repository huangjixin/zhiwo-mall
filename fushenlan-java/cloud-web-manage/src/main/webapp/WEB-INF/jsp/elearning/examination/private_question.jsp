<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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

</head>
<body>        
            <div class="nav-tabs" name="planTypeOther">
                <strong id="stageLabel" class="labelMark active" onclick="labelHtml('1',this)">阶段型计划</strong>
				<strong id="ordinaryLabel" onclick="labelHtml('2',this)">普通型计划</strong>
		    </div>		
  <form id="interfaceSearchForm" action="${ctx}/manage/question/GetPrivateQuestion" method="get">
		<div class="filter-box">
			<ul class="ui-form grid-row">
				<li class="col-md-4"><strong>考题关键字</strong><input class="ipt-text" type="text" style="margin-left: 0px; placeholder="" id="content" name="content" value="${content }"></li>
				<li class="col-md-3">
					<strong>题型</strong>
						<select id="type" name="type" value="${type }">
						    <option <c:if test="${type==''}">selected</c:if> value="">请选择</option>
							<option value="0" <c:if test="${type=='0'}">selected</c:if> >单选题</option>
							<option value="1" <c:if test="${type=='1'}">selected</c:if> >多选题</option>
							<option value="2" <c:if test="${type=='2'}">selected</c:if> >判断题</option>
							<option value="3" <c:if test="${type=='3'}">selected</c:if> >问答题</option>
						</select>
				</li>		
				<li class="col-md-12" style="text-align: right">
					<button type="submit"  class="btn btn-submit btn-radius btn-search" onclick=""><i class="icon-search"></i> 查询</button>
					<a class="btn btn-success btn-radius" href="${ctx}/manage/question/addQuestion"  title="新增">新增</a>
					<button type="button" class="btn btn-success btn-radius"  onclick="shareMaterials()">批量分享</button>
					<button type="button" class="btn btn-success btn-radius"  onclick="deleteBatchQuestion()">批量删除</button>
				</li>
			</ul>
		</div>
		
		 <table class="table table-agents" style="table-layout:fixed; "> 
			<thead>
				<tr>
					<th style="WIDTH: 6%;">
						<label class="pos-rel">
							<input type="checkbox" class="ace" id="selectAll">
						</label>
					</th>
					<th style="WIDTH: 6%;">考题号</th>
					<th style="WIDTH: 20%;">考题内容</th>
					<th>考题类型</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.records}" var="question"  varStatus="ext">
                    <tr>
                    <td>
                    <label class="pos-rel">
						<input type="hidden" name="isShare" id="isShare" value="${question.isShare }">
						<input type="checkbox" class="ace"  name="checkbox" value="${question.id }">
					</label>
                    </td>
                    <td ><a href="#" class="clickable" >${ext.index+1 }</a></td>
					<td  style="white-space:nowrap;overflow:hidden;text-overflow: ellipsis;"><div class="autocut" title="${question.content}"><a href="#" class="clickable" >${question.content }</a></td>
					<c:if test="${question.type==0 }">
					<td>单选题</td>
					</c:if>
					<c:if test="${question.type==1 }">
					<td>多选题</td>
					</c:if>
					<c:if test="${question.type==2 }">
					<td>判断题</td>
					</c:if>
					<c:if test="${question.type==3 }">
					<td>问答题</td>
					</c:if>
					<td>
						<a class="color-detail" href="${ctx}/manage/question/updateQuestion?id=${question.id }"  title="修改">修改</a>
						<a class="color-edit"    href="${ctx}/manage/question/shareQuestion?id=${question.id }&content=${question.content }&isShare=${question.isShare}" title="分享">分享</a>
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
	var content='${content}';
	var type ='${type}';
	var tagId ='${tagId}';
	pageAjax("${ctx}/manage/question/ajaxPriateQuestion?content="+content+"&type="+type+"&tagId="+tagId,'${page.pageSize}','${page.pageNo}','${page.pageTotal}','pageDiv');
})


$("#selectAll").click(function(){//给全选按钮加上点击事件
    var xz = $(this).prop("checked");//判断全选按钮的选中状态
    var ck = $(".ace").prop("checked",xz);  //让class名为qx的选项的选中状态和全选按钮的选中状态一致。  
    })

//批量删除试题
	function deleteBatchQuestion(){
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
  		    	  url: "${ctx }/manage/question/verify?ids="+ids,
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
  		  	   		    	  url: "${ctx }/manage/question/deleteBatchQuestion?ids="+ids,
  		  	   		    	  //dataType: "json",
  		  	   		    	  success: function(data){
  		  	   		    		  if(data.code==1){
  		  	   		    			  
  		  	   		    			  layer.confirm(data.msg, {
  		  	   		    	   				icon: 6,
  		  	   		    	   			  	btn: ['确定'] //按钮
  		  	   		    	   			}, function(){
  		  	   		    	   				location.href="${ctx}/manage/question/GetPrivateQuestion";
  		  	   		    	   			}); 
  		  	   		    		  }else{
  		  	   		    			  layer.confirm(data.msg, {
  		  	   		    	   				icon: 3,
  		  	   		    	   			  	btn: ['确定'] //按钮
  		  	   		    	   			}, function(){
  		  	   		    	   				location.href="${ctx}/manage/question/GetPrivateQuestion";
  		  	   		    	   			}); 
  		  	   		    			  
  		  	   		    		  }
  		  	   		    	  },
  		  	   		    	  error: function (data) {//请求失败处理函数  
  		  	   		    		  layer.confirm(data.msg, {
  		  	   	    	   				icon: 3,
  		  	   	    	   			  	btn: ['确定'] //按钮
  		  	   	    	   			}, function(){
  		  	   	    	   				location.href="${ctx}/manage/question/GetPrivateQuestion";
  		  	   	    	   			}); 
  		  	   			        },
  		  	   		    	  
  		  	   		    	});
  		    	   			},function(){
  		    	   			location.href="${ctx}/manage/question/GetPrivateQuestion";
  		    	   			}); 
  		    		  }else{
  		    			  layer.confirm(data.msg, {
  		    	   				icon: 3,
  		    	   			  	btn: ['确定'] //按钮
  		    	   			}, function(){
  		    	   				location.href="${ctx}/manage/question/GetPrivateQuestion";
  		    	   			}); 
  		    			  
  		    		  }
  		    	  },
  		    	  error: function (data) {//请求失败处理函数  
  		    		  layer.confirm(data.msg, {
  	    	   				icon: 3,
  	    	   			  	btn: ['确定'] //按钮
  	    	   			}, function(){
  	    	   				location.href="${ctx}/manage/question/GetPrivateQuestion";
  	    	   			}); 
  			        },
  		    	  
  		    	});
}

 function shareMaterials(){
	 var shareName = "";
	 var ids = new Array();
	 var contents = new Array();
	 var groupCheckbox=$("input[name='checkbox']:checked");
	 if(groupCheckbox.length==0){
		 layer.alert("请选择要分享的选项");
		 return;
	 }
     for(i=0;i<groupCheckbox.length;i++){
        if(groupCheckbox.eq(i).is(":checked")){
        	if(groupCheckbox.eq(i).prev().val() == '1'){
        		shareName +=groupCheckbox.eq(i).parent().parent().siblings().find(".clickable").text()+",";
        	}
        	var name=groupCheckbox.eq(i).parent().parent().siblings().find(".clickable").text();
    		ids[i] =groupCheckbox[i].value;
    		contents[i] =name;
        }
    }
     if(""!=shareName){
     	shareName = shareName.substring(0,shareName.length-1);
     	layer.confirm(shareName+'已分享过,是否重新选择', {
 			icon: 3,
 		  	btn: ['确定', '取消'] //按钮
 		}, function(){
 			location.href="${ctx}/manage/question/shareBatchQuestion?ids="+ids+"&contents="+contents+"&isShare="+0;
 		});
     }else{
    	 location.href="${ctx}/manage/question/shareBatchQuestion?ids="+ids+"&contents="+contents+"&isShare="+0;
     }
	 
 }
 //用于页面回显问题  视实际情况而定 
 var codeArray ={};
 codeArray['0'] = '视频';
 codeArray['1'] = 'ppt';
 codeArray['2'] = 'SCORM';
 codeArray['3'] = 'WORD';
 codeArray['4'] = 'EXCEL';
 codeArray['type0'] = '单选';
 codeArray['type1'] = '多选';
 codeArray['type2'] = '判断';
 codeArray['type3'] = '问答';
 
 function pageNext(url){
 	// 查询字段取页面加载时 model传入的值  防止分页执行查询
 	var content='${content}';
	var type ='${type}';
	var tagId ='${tagId}';
 	$.get(url,function(returnData){
 		if(returnData.code == '1'){
 			var html = "";
 			$.each(returnData.data.records,function(i,row){
 	   			row.groupId = row.groupId==null?'':row.groupId; //判断null字段
 	   			codeArray[row.tagId] =codeArray[row.tagId] == undefined ?'':codeArray[row.tagId]; //判断undefined字段
 	   			html+='<tr><td><label class="pos-rel"><input type="checkbox" class="ace"  name="checkbox" value='+row.id+'></label></td>'+
 	   				 '<td>'+(i+1)+'</td>'+
 	   				 '<td>'+row.content+'</td>'+
 	   				  '<td>'+codeArray['type'+row.type]+'</td>'+
 	   				  '<td>'+codeArray[row.groupId]+'</td>'+
 	   				  '<td>'+codeArray[row.tagId]+'</td>'+
 	   				  '<td><a class="color-detail" href="${ctx}/manage/question/updateQuestion?id='+row.id+'  title="修改">修改</a><a class="color-edit"    href="${ctx}/manage/question/shareQuestion?id='+row.id+'&content='+row.content+' title="分享">分享</a></td>';
 	   			})
 			$(".table tbody").html(html);
 			pageAjax("${ctx}/manage/question/ajaxPriateQuestion?content="+content+"&type="+type+"&tagId="+tagId,returnData.data.pageSize,returnData.data.pageNo,returnData.data.pageTotal,'pageDiv');
 		}
 	})
 }
/*  
 function selectQuestion(){
	 $.ajax({
		  async : false,  
	      cache : false,  
	      type: 'POST',
	      data:$("#interfaceSearchForm").serialize(),
	      url: "${ctx }/manage/question/ajaxPriateQuestion",
	      dataType: "json",
	  success: function(returnData){
		 if(returnData.code==1){
			var html = "";
			$.each(returnData.data.records,function(i,row){
   			row.groupId = row.groupId==null?'':row.groupId; //判断null字段
   			codeArray[row.tagId] =codeArray[row.tagId] == undefined ?'':codeArray[row.tagId]; //判断undefined字段
   			html+='<tr><td><label class="pos-rel"><input type="checkbox" class="ace"  name="checkbox" value='+row.id+'></label></td>'+
   				 '<td>'+(i+1)+'</td>'+
   				 '<td>'+row.content+'</td>'+
   				  '<td>'+codeArray['type'+row.type]+'</td>'+
   				  '<td>'+codeArray[row.groupId]+'</td>'+
   				  '<td>'+codeArray[row.tagId]+'</td>'+
   				  '<td><a class="color-detail" href="${ctx}/manage/question/updateQuestion?id='+row.id+'  title="修改">修改</a><a class="color-edit"    href="${ctx}/manage/question/shareQuestion?id='+row.id+'&content='+row.content+' title="分享">分享</a></td>';
   			})
   			$(".table tbody").html(html);
			pageAjax("${ctx}/manage/question/ajaxPriateQuestion?content="+content+"&type="+type+"&groupId="+tagId+"&type="+type,returnData.data.pageSize,returnData.data.pageNo,returnData.data.pageTotal,'pageDiv');
		 }
	  },
	  
	});
	 
	 
 } */
</script>
</body>
</html>
