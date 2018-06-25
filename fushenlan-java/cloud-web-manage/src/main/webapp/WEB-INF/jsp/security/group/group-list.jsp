<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/tld/Data" prefix="data" %>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<meta name="format-detection" content="telephone=no">
		<meta name="description" content="">
		<title>富卫运维大平台</title>
	</head>
<body>
	<!-- <style>
	.button:hover{color:#09cbd2;}
	</style> -->
	<form id="queryForm" method="get" action="${ctx}/manage/group/listGroup">
      <div class="filter-box">
          <ul class="ui-form grid-row">
              <li class="col-md-3"><strong>用户组名：</strong><input id="groupName" name="groupName"  type="text" value="${groupName}"></li>
              <li class="col-md-3"><strong>用户组说明</strong><input id="groupDesc" name="groupDesc"  type="text" value="${groupDesc}"></li>
          </ul>
          <button type="button" class="btn btn-submit btn-radius btn-search" onclick="onQuery()"><i class="icon-search"></i> 查询</button>
      </div>
     </form>
      <div class="tab-btn">
          <button type="button" class="btn btn-success btn-radius" onclick="addGroup()">新用户组</button>
          <button type="button" class="btn btn-success btn-radius" onclick="batchDelete()">批量删除</button>
      </div>
      <table class="table table-agents">
          <thead>
              <tr>
                  <label class="pos-rel">
                      <th><input type="checkbox" id="selectAll" class="ace"></th>
                  </label>
                  <th>用户组名</th>
                  <th>用户组说明</th>
                  <th>所含用户数</th>
                  <th>操作</th>
              </tr>
          </thead>
          <tbody class="td">
              <c:if test="${not empty groupList}">
                     <c:forEach var="group" items="${groupList}">
                          <tr>
                              <td><input id="childBox_${group.id }" name="checkbox" type="checkbox" class="ace" value="${group.id }" ></td>
                              <td>${group.groupName}</td>
                              <td>${group.groupDesc}</td>
                              <td>${group.userNum}</td>
                              <td>
                                <button type="button" class="button" onclick="editGroup('${group.id}')">修改</button>
                                <button type="button" class="button" onclick="deleteGroup('${group.id}')">删除</button>
                              </td>
                         </tr>
                          </c:forEach>
                  </c:if>
          </tbody>
      </table>
      <div class="table-paging clearfix" id="pageDiv">
      </div>
        
<!-- E Wrapper -->
    <script type="text/javascript"  src="${ctx}/resources/libs/layer/layer.js"></script>
    <script src="${ctx }/resources/js/common/page.js" type="text/javascript"></script>
    <script>
        $(function(){
        	var groupName = '${groupName}';
            var groupDesc ='${groupDesc}';
            page("${ctx}/manage/group/listGroup?groupName="+groupName+"&groupDesc="+groupDesc,'${page.pageSize}','${page.pageNo}','${page.pageTotal}','pageDiv');
        });
        
        //查询 
        function onQuery() {
        	$("#queryForm").submit();
        }
        //新增用户组
        function addGroup() {
        	location.href = '${ctx}/manage/group/editGroup';
        }
        
        //修改用户组信息 
        function editGroup(id) {
        	location.href = '${ctx}/manage/group/editGroup?id='+id;
        }
        
        //删除 
        function deleteGroup(id) {
        	layer.confirm('确定删除?', {
                icon: 3,
                btn: ['确定', '取消'] //按钮
              },function () {
            	  $.ajax({
                      type: 'POST',
                      url: "${ctx }/manage/group/deleteGroup",
                      data :{"groupIds":id},
                      dataType:"text",
                      success: function(result) {
                          if (result != '' && result != 'null' && result != null) {
                              layer.alert(result +" 已关联用户，不能删除");
                          } else {
                              layer.alert('操作成功');
                              location.href="${ctx}/manage/group/listGroup";
                          } 
                      },
                      error : function (){
                      }
                 });
              });
        }
        //批量删除 
        function batchDelete () {
        	var str="";
            $(".td :input[type='checkbox']").each(function(i){
                if(this.checked==true){
                    var rs=this.value;
                    str=str+rs+",";
                }
            });
            if(str.length==0){
                layer.alert('先选择用户组', {icon: 5});
            }else{
                layer.confirm('确定删除?', {
                  icon: 3,
                  btn: ['确定', '删除'] //按钮
                },function () {
                	str=str.substring(0,str.length-1);
                	$.ajax({
                        type: 'POST',
                        url: "${ctx }/manage/group/deleteGroup",
                        data :{"groupIds":str},
                        dataType:"text",
                        success: function(result) {
                            if (result != '' && result != 'null' && result != null) {
                                layer.alert(result +" 已关联用户，不能删除");
                                location.href="${ctx}/manage/group/listGroup";
                            } else {
                                layer.alert('操作成功');
                                location.href="${ctx}/manage/group/listGroup";
                            } 
                        },
                        error : function (){
                        }
                   });
                });
                }
        }
     </script>
    </body>
</html>
