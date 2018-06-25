<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<!--  <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"> -->
<!--  <meta name="apple-mobile-web-app-capable" content="yes"> -->
<!-- <meta name="apple-mobile-web-app-status-bar-style" content="black"> -->
<!-- <meta name="format-detection" content="telephone=no"> -->
<!-- <meta name="description" content="nodecorate"> -->
<title>Insert title here</title>
</head>
<body>
<link rel="stylesheet" href="${ctx}/resources/libs/ztree/zTreeStyle.css" type="text/css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/style.css">
<script type="text/javascript" src="${ctx}/resources/js/common/jquery-3.2.1.min.js" ></script>
<script type="text/javascript" src="${ctx}/resources/libs/datepicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common/page.js"></script>
<script type="text/javascript"	src="${ctx}/resources/libs/layer/layer.js"></script>
		<table class="table table-agents">
			<thead>
				<tr>
					<th>
						<label class="pos-rel">
							<input type="checkbox" class="ace"  id="checkbox" >
						</label>
					</th>
					<th>学习计划名称</th>
				</tr>
			</thead>
			<tbody class="tdy">
				<c:forEach items="${paperList}" var="paper">
					<tr>
					  <td>
					  	<input id="${paper.id}" name="checkbox" type="checkbox" class="ace" value="${paper.name }">
					  </td>
					  <td>${paper.name}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	<div class="panel-footer">	
		<center>
			<input class="btn btn-default search-btn prese-btn" type="button" value="确定" onclick="chooseStudy();"/> 
			<input type="button" class="btn btn-default search-btn prese-btn" value="返回" id="closeBtn" onclick="backOne();"/> 
		</center>
	</div>	
	
	
    <script src="${ctx}/resources/js/common.js"></script>
    
    <script>
    function chooseStudy(){
	var paperIds = new Array();
	var paperNames = [];
    var groupCheckbox=$("input[name='checkbox']:checked");
	for(i=0;i<groupCheckbox.length;i++){
	    if(groupCheckbox.eq(i).is(":checked")){//
	     	var name=$(groupCheckbox.eq(i)).val();
	     	paperIds[i] =$(groupCheckbox[i]).attr("id");
	     	paperNames[paperIds[i]] = name;
	    }
	}
    
    /* var index=parent.layer.getFrameIndex(window.name);
    parent.layer.close(index); */
	if(paperIds.length==0){
		layer.alert('你还没有选择要的记录', {icon: 5});
	}else{
		layer.confirm('你确定要选中的记录吗？', {
			icon: 3,
		  	btn: ['确定', '取消'] //按钮
		}, function(){
		  	/* parent.addPaperOne(sName,sId); */
		  	parent.courseList(paperIds,paperNames,1); 
			var index=parent.layer.getFrameIndex(window.name);
			parent.layer.close(index);
		});
	  
     }
    }
    
    
    function backOne(){
		var index=parent.layer.getFrameIndex(window.name);
		parent.layer.close(index);
	}
    </script>
</body>
</html>