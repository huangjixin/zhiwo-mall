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
<style type="text/css">
	.col-md-4{
		width:300px;
	}
	li.sameWadte{
		    width: 600px;
    padding-right: 0px;
    padding-left: 128px;
	}
	.materialUploadTime{
	}
</style>
    <div clsss="form-detail">
	    <div class="nav-tabs">
	        <strong id="up" <c:if test ='${search_pubType eq 1}'>class="active"</c:if>  onclick="selectMaterial(1)">私人资料</strong>
	        <strong id="down" <c:if test ='${search_pubType eq 2}'>class="active"</c:if> onclick="selectMaterial(2)">公共资料</strong>
	    </div>
		<form id="interfaceSearchForm" action="${ctx}/manage/privateMaterialListByPage" method="post">
			<div class="filter-box">
				<ul class="ui-form grid-row ">
					<li class="col-md-4"><strong>资料名称</strong>
					<input type="hidden" style="margin-left: 0px;" name="pubType" id="search_pubType" value="${search_pubType }">
					<input class="ipt-text" style="margin-left: 0px;" type="text" placeholder="请输入资料名称" name="keyWord" id="search_keyWord" value="${search_keyWord }"></li>
					<li class="col-md-4">
						<strong>资料类型</strong>
							<select name="type" id="search_type">
								<option value="" >请选择</option>
								<option <c:if test="${search_type==1 }">selected</c:if> value="1" >视频</option>
								<option <c:if test="${search_type==2 }">selected</c:if>  value="2" >SCORM</option>
								<option <c:if test="${search_type==3 }">selected</c:if>  value="3" >PPT</option>
								<option <c:if test="${search_type==4 }">selected</c:if>  value="4" >WORD</option>
								<option <c:if test="${search_type==5 }">selected</c:if>  value="5" >EXCEL</option>
							</select>
					</li>
					<li class="col-md-6 sameWadte" style="padding-left:0;">
						<strong class="materialUploadTime">资料上传时间</strong>
						<input type="text" id="search_uploadTimeBegin" name="uploadTimeBegin" value="${search_uploadTimeBegin }" placeholder="开始时间" class="Wdate" style="max-width: 180px;" onfocus="WdatePicker({onpicked: function(){jQuery(this).trigger('change');},oncleared: function(){jQuery(this).trigger('change');}})">
						&nbsp;—&nbsp;
						<input type="text" id="search_uploadTimeEnd" name="uploadTimeEnd" value="${search_uploadTimeEnd }" placeholder="结束时间" class="Wdate" style="max-width: 180px;" onfocus="WdatePicker({onpicked: function(){jQuery(this).trigger('change');},oncleared: function(){jQuery(this).trigger('change');}})">
					</li>
					<li class="col-md-12" style="text-align: right">
						<button type="submit_btn" class="btn btn-submit btn-radius btn-search"><i class="icon-search"></i> 查询</button>
						<c:if test ='${search_pubType eq 1}'>
							<a class="btn btn-success btn-radius" href="${ctx}/manage/insertMaterialsJsp"  title="新增">新增</a>
							<button type="button" class="btn btn-success btn-radius" onclick="shareMaterials()">批量分享</button>
							<button type="button" class="btn btn-success btn-radius"onclick="deleteMaterials()">批量删除</button>
						</c:if>
					</li>
				</ul>
			</div>
		</form>
		<div>
		<table class="table table-agents" style="text-align: center;">
			<thead>
				<tr>
					<th>
						<label class="pos-rel">
							<input type="checkbox" class="ace" id="checkbox">
						</label>
					</th>
					<th>资料名称</th>
					<th>资料类型</th>
					<th>上传日期</th>
					<th>是否共享</th>
					<th>是否关联计划</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${page.records}" var="material" varStatus="status">
				<tr>
					<td class="materialSelected">
						<label class="pos-rel">
							<input type="checkbox" class="ace" name="checkbox" value="${material.id }">
						</label>
					</td>
					<td><a href="#"  onclick="updateMaterial('${material.id }')" class="clickable">${material.name }</a></td>
					<td>
						<c:if test="${material.type==1 }">视频</c:if>
						<c:if test="${material.type==2 }">SCORM</c:if>
						<c:if test="${material.type==3 }">PPT</c:if>
						<c:if test="${material.type==4 }">WORD</c:if>
						<c:if test="${material.type==5 }">EXCEL</c:if>
					</td>
					<%-- <td>${material.createUser }</td> --%>
					<td><fmt:formatDate value="${material.gmtCreate }" type="date" pattern="yyyy-MM-dd"/></td>
					<td>
						<c:if test="${material.isShare=='0'}">
							否
						</c:if>
						<c:if test="${material.isShare=='1'}">
							是
						</c:if>
					</td>
					<td>
						<c:if test="${material.isRelatePlan=='0'}">
							否
						</c:if>
						<c:if test="${material.isRelatePlan=='1'}">
							是
						</c:if>
					</td>
					<td>
					   <c:if test ='${search_pubType eq 1}'>
					       <a class="color-detail" href="#"  onclick="updateMaterial('${material.id }')" title="查看">修改</a>
                           <a class="color-detail" href="#"  onclick="shareMaterial('${material.name }','${material.id }')" >分享</a>
					   </c:if>
					   <c:if test ='${search_pubType eq 2}'>
						   <a class="color-detail" href="#"  onclick="viewMaterial('${material.id }')" title="查看">查看</a>
					   </c:if>
					</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="table-paging clearfix" id="pageDiv">
        </div>
	</div>	
	

<script type="text/javascript" src="${ctx}/resources/libs/datepicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common/page.js"></script>
<script type="text/javascript" src="${ctx}/resources/libs/layer/layer.js"></script>
<script type="text/javascript">

	$(document).ready(function(){
		getPageDiv();
	 });
	
	function getPageDiv() {
		var keyWord=$("#search_keyWord").val();
        var type=$("#search_type").val();
        var submitter=$("#search_submitter").val();
        var pubType=$("#search_pubType").val();
        var id=$("#search_id").val();
        var uploadTimeBegin=$("#search_uploadTimeBegin").val();
        var uploadTimeEnd=$("#search_uploadTimeEnd").val();
        
        page("${ctx}/manage/privateMaterialListByPage?keyWord="+keyWord+"&type="+type+"&pubType="+pubType+"&id="+id+"&uploadTimeBegin="+uploadTimeBegin+"&uploadTimeEnd="+uploadTimeEnd,'${page.pageSize}','${page.pageNo}','${page.pageTotal}','pageDiv');
	}
   
	/* function pageNext(url){
	    // 查询字段取页面加载时 model传入的值  防止分页执行查询
	    var keyWord=$("#search_keyWord").val();
	    var type=$("#search_type").val();
	    var submitter=$("#search_submitter").val();
	    var pubType=$("#search_pubType").val();
	    var id=$("#search_id").val();
	    var uploadTimeBegin=$("#search_uploadTimeBegin").val();
	    var uploadTimeEnd=$("#search_uploadTimeEnd").val();
		$.get(url,function(result){  
               $(".table-agents").empty();
               $(".table-agents").append($(result).find(".table-agents").html());
               getPageDiv();
           }
        );
	} */
   
	 $("#checkbox").click(function () {
		    if($(this).is(':checked')){
		    	$("input[name='checkbox']").prop("checked",true);
		    }else{
		    	$("input[name='checkbox']").prop("checked",false);
		    }
		});
	 
	 function deleteMaterials(){
		 var materialIds = new Array();
		 var groupCheckbox=$("input[name='checkbox']:checked");
		     for(i=0;i<groupCheckbox.length;i++){
		        if(groupCheckbox.eq(i).is(":checked")){
		        	//materialIds[i] =groupCheckbox[i].value;
		        	materialIds.push(groupCheckbox[i].value);
		        }
		    } 
		     if(materialIds.length>0){
		    	//信息框-例2
				 layer.msg('你确定要删除吗？', {
				   time: 0 //不自动关闭
				   ,btn: ['确定', '取消']
				   ,yes: function(index){
							 $.ajax({
							      type: 'POST',
							  	  url: "${ctx}/manage/deleteMaterials",
							  	  data :{"materialIds" : materialIds},
							  	  traditional:true,
							  	  success: function(result) {
							  		if(null==result.data||''==result.data){
							  			location.href="${ctx}/manage/privateMaterialListByPage";
							  		}else{
							  			layer.alert("编号:"+result.data+"的资料与课程关联,无法删除");
							  		}
									//parent.location.href="${ctx}/manage/privateMaterialListByPage";
								  }
							  	})
				   }
				 });
			 }else{
				 layer.alert("请先选择要删除的资料");
			 }
	 }
	 
	 function shareMaterials(){
			var jsonStr =[];
		 var materialIds = new Array();
		 var materialNames = new Array();
		 var groupCheckbox=$("input[name='checkbox']:checked");
		     for(i=0;i<groupCheckbox.length;i++){
		        if(groupCheckbox.eq(i).is(":checked")){
		        	var name=groupCheckbox.eq(i).parent().parent().siblings().find(".clickable").text();
		        	materialIds[i] =groupCheckbox[i].value;
		        	materialNames[i] =name;
		        }
		    }
		     if(materialIds.length>0){
		    	 location.href="${ctx}/manage/shareMaterials?materialIds="+materialIds+"&materialNames="+materialNames; 
		     }else{
		    	 layer.alert("请先选择要分享的资料");
		     }
	 }
	 
	 function shareMaterial(name,id){
		 var materialIds = new Array();
		 var materialNames = new Array(); 
		 materialIds[0] =id;
     	materialNames[0] =name;
     	location.href="${ctx}/manage/shareMaterials?materialIds="+materialIds+"&materialNames="+materialNames;
	 }
	 
	 function updateMaterial(id){
		location.href="${ctx}/manage/updateMaterialsJsp?id="+id;
	} 
	 
	 
	function selectMaterial(type) {
		location.href = "${ctx}/manage/privateMaterialListByPage?pubType="+type;
		/* $.get('${ctx}/manage/privateMaterialListByPage',
                { 
                  "pubType": type
                },
                function(result){  
                   $(".table-agents").empty();
                   $(".table-agents").append($(result).find(".table-agents").html());
               }
       ); */
	}
	
	//公共库查看 
	function viewMaterial(id){
        location.href="${ctx}/manage/viewMaterial?id="+id;
    }
	

</script>
</body>
</html>