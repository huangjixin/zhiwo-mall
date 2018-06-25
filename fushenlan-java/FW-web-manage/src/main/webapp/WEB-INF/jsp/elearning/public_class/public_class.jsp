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

<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/style.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/font/iconfont.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/page.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/libs/datepicker/skin/default/datepicker.css">
<style  type="text/css">
.ui-form li .course_status label {
    padding: 0 0 0 15px;
}
</style>
<form id="interfaceSearchForm" action="${ctx}/manage/publicClassListByPage" method="post">
		<div class="filter-box">
			<ul class="ui-form grid-row">
				<li class="col-md-3"><strong>课程名称</strong><input id="searchName" name="name" class="ipt-text" type="text" placeholder="请输入课程名称" value="${map.name }"></li>
				<li class="col-md-6 sameWadte">
					<strong>课程开放日期</strong>
					<input type="text" id="searchStartDate" name="startDate" value="${map.startDate }" placeholder="开始时间" class="Wdate" style="max-width: 180px;"onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'searchEndDate\')}'})">&nbsp;—&nbsp;
					<input type="text" id="searchEndDate" name="endDate" value="${map.endDate }" placeholder="结束时间" class="Wdate" style="max-width: 180px;" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'searchStartDate\')}'})">
				</li>
				<li class="col-md-3">
					<strong>一级分类</strong>
					<div class="ipt-select">
						<i class="icon-arrow-down"></i>
						<select name="tagId" id="searchTagId">
							<option value="" >请选择</option>
							<c:forEach items="${resTag }" var="tagId">
							<option value="${tagId.id }" <c:if test="${map.tagId==tagId.id }">selected</c:if>>${tagId.tagName }</option>
							</c:forEach>
							<%-- <option <c:if test="${map.tagId==1 }">selected</c:if>  value="1" >视频</option>
							<option <c:if test="${map.tagId==2 }">selected</c:if>  value="2" >PPT</option>
							<option <c:if test="${map.tagId==3 }">selected</c:if>  value="3" >SCORM</option>
							<option <c:if test="${map.tagId==4 }">selected</c:if>  value="4" >WORD</option>
							<option <c:if test="${map.tagId==5 }">selected</c:if>  value="5" >EXCEL</option> --%>
						</select>
					</div>
				</li>
				<li class="col-md-3">
					<strong>二级分类</strong>
					<div class="ipt-select">
						<i class="icon-arrow-down"></i>
						<select name="childTagId" id="searchChildTagId">
							<option value="" >请选择</option>
							<c:forEach items="${resChildTagId }" var="childTagId">
							<option value="${childTagId.id }" <c:if test="${map.childTagId==childTagId.id }">selected</c:if> >${childTagId.tagName }</option>
							</c:forEach>
							<%-- <option <c:if test="${map.childTagId==1 }">selected</c:if> value="1" >视频</option>
							<option <c:if test="${map.childTagId==2 }">selected</c:if>  value="2" >PPT</option>
							<option <c:if test="${map.childTagId==3 }">selected</c:if>  value="3" >SCORM</option>
							<option <c:if test="${map.childTagId==4 }">selected</c:if>  value="4" >WORD</option>
							<option <c:if test="${map.childTagId==5 }">selected</c:if>  value="5" >EXCEL</option> --%>
						</select>
					</div>
				</li>
				<li class="col-md-6">
					<strong>课程性质</strong>
					<div class="course_status">
						<label for="upper"><input type="radio" <c:if test="${map.state==1 }">checked</c:if> id="upper" name="state" class="searchState" value="1">上架中
						</label>
						<label for="off"><input type="radio" <c:if test="${map.state==2 }">checked</c:if>  id="off" name="state" class="searchState" value="2">下架中
						</label>
						<label for="noUpper"><input type="radio" <c:if test="${map.state==0&&not empty map.state}">checked</c:if>  id="noUpper" name="state" class="searchState" value="0">未上架
						</label>
						<label for="searchIsSticky"><input type="radio" <c:if test="${map.isSticky==1 }">checked</c:if>  id="searchIsSticky" name="isSticky" value="1">课程是否置顶
						</label>
					</div>
					
				</li>
				<li class="col-md-12" style="text-align: right">
					<button type="submit_btn" class="btn btn-submit btn-radius btn-search"><i class="icon-search"></i> 查询</button>
					<a class="btn btn-success btn-radius" href="${ctx }/manage/toAddJsp" title="新增">新增</a>
					<button type="button" class="btn btn-success btn-radius" onclick="theBatchShelves('2')">批量下架</button>
					<button type="button" class="btn btn-success btn-radius" onclick="theBatchShelves('1')">批量上架</button>
				</li>
			</ul>
		</div>
	</form>
		<!-- E Filter Box -->
		
		<table class="table table-agents table_public">
			<thead>
				<tr>
					<th>
						<label class="pos-rel">
							<input type="checkbox" id="checkbox" class="ace">
						</label>
					</th>
					<th>课程编号</th>
					<th>课程名称</th>
					<th>课程上传者</th>
					<th>开始日期</th>
					<th>结束日期</th>
					<th>一级分类</th>
					<th>二级分类</th>
					<th>课程状态</th>
					<th>星级评分</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${page.records}" var="publicClass" varStatus="status">
				<tr>
					<td>
						<label class="pos-rel">
							<input type="checkbox" class="ace" name="checkbox" value="${publicClass.id }">
						</label>
					</td>
					<td>${publicClass.id }</td>
					<td><a  href="./overt_view.html" class="clickable">${publicClass.name }</a></td>
					<td>${publicClass.createUser }</td>
					<td><fmt:formatDate value="${publicClass.startDate }" type="date" pattern="yyyy-MM-dd"/></td>
					<td><fmt:formatDate value="${publicClass.endDate }" type="date" pattern="yyyy-MM-dd"/></td>
					<td>${publicClass.tagName }</td>
					<td>${publicClass.childTagName }</td>
					<td>
					<c:if test="${publicClass.state == 0}">未上架</c:if>
					<c:if test="${publicClass.state == 1}">上架中</c:if>
					<c:if test="${publicClass.state == 2}">下架中</c:if>
					</td>
					<td>${publicClass.score }</td>
					<td>
						<c:if test="${publicClass.isSticky==0 }">
						<a class="color-detail" href="#" onclick="stickPublicClass(${publicClass.id },'1', this)" title="置顶">置顶</a>
						</c:if>
						<c:if test="${publicClass.isSticky==1 }">
						<a class="color-detail" href="#" onclick="stickPublicClass(${publicClass.id },'0',this)" title="取消">取消</a>
						</c:if>
						<a class="color-detail" href="#" onclick="questionJsp('${publicClass.id }')"  title="提问">提问</a>
						<a class="color-detail" href="#" onclick="commentJsp('${publicClass.id }')" title="评论">评论</a>
					</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="table-paging clearfix" id="pageDiv">
		</div>
	
<script src="${ctx}/resources/js/common/jquery-3.2.1.min.js"></script>
<%-- <script src="${ctx}/resources/js/common.js"></script> --%>
<script type="text/javascript" src="${ctx}/resources/libs/datepicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common/page.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/layer/layer.js"></script>
<script type="text/javascript">
 $(document).ready(function(){
    
    	var name=$("#searchName").val();
    	var startDate=$("#searchStartDate").val();
    	var endDate=$("#searchEndDate").val();
    	var tagId=$("#searchTagId").val();
    	var childTagId=$("#searchChildTagId").val();
    	var isSticky=$("input[name='isSticky']:checked").val();
    	//var state=$(".searchState").val(); 
    	var state=$("input[name='state']:checked").val();
    	if(state==null){
    		state="";
        }
    	if(isSticky==null){
    		isSticky="";
        }
    page("${ctx}/manage/publicClassListByPage?name="+name+"&startDate="+startDate+"&endDate="+endDate+"&tagId="+tagId+"&childTagId="+childTagId+"&isSticky="+isSticky+"&state="+state,'${page.pageSize}','${page.pageNo}','${page.pageTotal}','pageDiv');	
	}); 

 $("#checkbox").click(function () {
	    if($(this).is(':checked')){
	    	$("input[name='checkbox']").prop("checked",true);
	    }else{
	    	$("input[name='checkbox']").prop("checked",false);
	    }
	});
 
 function theBatchShelves(state){
	 var publicClassIds = new Array();
	 var groupCheckbox=$("input[name='checkbox']:checked");
	     for(i=0;i<groupCheckbox.length;i++){
	        if(groupCheckbox.eq(i).is(":checked")){
	        	publicClassIds.push(groupCheckbox[i].value);
	        }
	    } 
	  $.ajax({
	      type: 'POST',
	  	  url: "${ctx}/manage/theBatchShelvesPublicClass",
	  	  data :{"publicClassIds" : publicClassIds,"state":state},
	  	  traditional:true,
	  	  success: function(result) {
	  		if(result){
	  			/* alert("修改成功"); */
	  			location.href="${ctx}/manage/publicClassListByPage";
	  		}else{
	  			layer.alert("修改失败");
	  		}
		  }
	  	})
 }
 

	function questionJsp(id){
		location.href="${ctx}/manage/questionListByPage?courseId="+id;
	}
 
	function commentJsp(id){
		location.href="${ctx}/manage/commentListByPage?courseId="+id;
	} 
 
 
 	function stickPublicClass(id,isSticky,obj){
 		  $.ajax({
 		      type: 'POST',
 		  	  url: "${ctx}/manage/updatePublicClass",
 		  	  data :{"id" : id,"isSticky":isSticky},
 		  	  traditional:true,
 		  	  success: function(result) {
 		  			layer.alert(result);
 		  			//location.href="${ctx}/manage/publicClassListByPage";
 		  			if(isSticky==0){
 		  				$(obj).html("置顶");
 		  			}else{
 		  				$(obj).html("取消");
 		  			}
 			  }
 		  	}) 
 	}
 	
 	/* $("#searchIsSticky").click(function(){
 		var flag=$("#searchIsSticky").is(':checked');
 		alert(flag);
 		if(flag){
				$("#searchIsSticky").prop("checked",true);
			}
 	}) */

 	
/*  	$(document).ready(function(){
 		var falg
 		if($("#upper").prop("checked")){
 			falg = 1;
 		}
 		$("#upper").click(function(){
 			if(falg==1){
 				$(this).removeAttr("checked");
 				falg = 0;
 			}else{
 				$(this).attr("checked","checked");
 				falg = 1;
 			}
 		})
 	}) */
	/* $("#upper").click(function(){
		var flag=$("#upper").val();
		 if("1"==flag){
			 $("#upper").prop("checked",false);
			 $("#upper").val("0");
		}else{
			 $("#upper").val("1");
		} 
	})  */
 
/* 	function  changeSelect(radioObj){
		var flag=$(radioObj).val();
		 if("1"==flag){
			 $(radioObj).prop("checked",false);
			 $(radioObj).val("0");
		}else{
			 $(radioObj).val("1");
		} 
} */ 
 
 
</script>
<script type="text/javascript">
$(document).ready(function(){
	var radioclick=1;
	$("#searchIsSticky").click(function(){
	    if(radioclick%2==0){
	        $(this).prop("checked",false);
	    }
	    radioclick++;
	});
	$("#upper").click(function(){
	    if(radioclick%2==0){
	        $(this).prop("checked",false);
	    }
	    radioclick++;
	});
	$("#off").click(function(){
	    if(radioclick%2==0){
	        $(this).prop("checked",false);
	    }
	    radioclick++;
	});
	$("#noUpper").click(function(){
	    if(radioclick%2==0){
	        $(this).prop("checked",false);
	    }
	    radioclick++;
	});
	
})
	

</script>
<script type="text/javascript">
$("#searchTagId").change(function(){
	var parentId=$("#searchTagId option:selected").val();
	if(null!=parentId&&""!=parentId){
		$.ajax({
			url: "${ctx }/manage/findByCatagoryAndParentId",
		  	dataType: "json",
		  	data :{"parentId":parentId,"catagory":"2"},
			type : "post",
			success : function(respData) {
				//循环遍历返回的json
				$("#searchChildTagId").empty().append("<option value='"+""+"'>请选择</option>");
				$.each(respData.data,function(i,item){
					$("#searchChildTagId").append("<option value='"+item.id+"'>"+item.tagName+"</option>");				
				})
			}
		});
	}else{
		$("#searchChildTagId").empty().append("<option value='"+""+"'>请选择</option>");
	}
})

</script>
</body>
</html>