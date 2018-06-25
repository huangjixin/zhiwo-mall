<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<title>富卫运维大平台</title>
<meta name="description" content="">
</head>
<body>
	<script type="text/javascript" src="${ctx}/resources/libs/layer/layer.js" ></script>
	<style type="text/css">
	    input.ipt-text{
	        width:60%
	    }
    </style>
    <div class="wrapper">
        <form id="groupForm" method="post">
            <input type="hidden" class="ipt-text" name="id" value="${group.id }" id="groupId">
            <div class="form-detail">
                <ul class="clearfix form_learn">
                    <li class="col-md-12"><strong><ins>*</ins>用户组名：</strong>
                       <input type="text" class="ipt-text required" 
                            id="groupName" name="groupName" value="${group.groupName }" style="width:300px">
                    </li>
                    <li class="col-md-12"><strong><ins>*</ins>用户组说明：</strong>
                       <input type="text" name="groupDesc" 
                            id="groupDesc" class="ipt-text required" value="${group.groupDesc }" style="width:300px">
                    </li>
                    <li class="col-md-12"><strong><ins>*</ins>新建标签：</strong>
                       <input type="text" name="newTagStr" 
                            id="newTagStr" class="ipt-text" value="" style="width:300px">
                       <button type="button" onclick="createTag()" class="btn btn-submit" style="right: -90px;">保存</button>      
                    </li>
                    <li class="col-md-12 tagDiv" ><strong>分类标签：</strong>
                        <c:forEach var="tag" items="${TagVo}">
                           <input type="checkbox" name="tagIds" 
                                id="tagIds" class="" value="${tag.id}" 
                                    <c:if test="${fn:contains(strTagId, tag.id)}">
                                        checked 
                                    </c:if>
                                >${tag.tagName}
                        </c:forEach>
                    </li>
                    <li class="col-md-12"><strong>关联用户：</strong>
	                    <input type="text" name="userStrName" 
                            id="userStrName" class="ipt-text " value="${userName}" style="width:300px" onclick="getUsers()">
                             <input type="hidden" class="ipt-text" name="userStr" value="${userStr}" id="userStr" style="width:300px">
                             <button type="button" onclick="getUsers()" class="btn btn-submit" style="right: -90px;">选择</button>
                             <button type="button" onclick="clearUser()" class="btn btn-submit" style="right: -90px;">清除关联</button>
                    </li>
                </ul>
                <div class="ui-button">
                    <button type="button" class="btn btn-submit" onclick="saveGroup()">保存</button>
                    <button type="button" class="btn btn-default" onclick="history.go(-1)">取消</button>
                </div>
            </div>
        </form>
    </div>
    
    <script type="text/javascript">
        $(document).ready(function() {
            /*  $("#MaterialForm").validate({
                 rules : {
                     name : {
                         required : true,
                         maxlength : [20]
                     },
                     description : {
                         required : true,
                         maxlength : [100]
                     }
                 },
                 messages: {
                     name: {
                         required: '此项必填',
                         maxlength: '资料名最长为20'
                     },
                     description: {
                         required: '此项必填',
                         maxlength: '资料说明最长为100'
                     }
                 }
             }); */
        });
        
        //防重复提交标志位 
        var switchButton =1;
        //保存方法 
        function saveGroup(){
        	if (validate()) {
	        	if(switchButton){
	                $.ajax({
	                  type: 'POST',
	                  url: "${ctx }/manage/group/saveGroup",
	                  dataType: "json",
	                  data :$('#groupForm').serialize(),
	                  success: function(result) {
	                	  console.log(result);
	                      if (result == 0) {
	                          layer.alert("提交失败");                                
	                      } else {
	                          location.href="${ctx}/manage/group/listGroup";
	                      }
	                  }
	                });
	            }
	        	switchButton =0;
        	}
            
        }
    
        //获取用户 
        function getUsers() {
        	layer.open({
                type: 2,
                title: "选择用户",
                closeBtn: 1, //
                shade: [0],
                area: ['80%', '80%'],
                content: "${ctx}/manage/group/listUsers",
                end: function(index){
                },
                cancel: function(){
                }
              });
        }
        
        //清除用户关联 
        function clearUser () {
        	$("#userStrName").val("");
        	$("#userStr").val("");
        }
        
        //表单校验 
        function validate() {
        	var groupName = $("#groupName").val();
        	var groupDesc = $("#groupDesc").val();
        	if (groupName == '') {
        		layer.alert('先添加用户组名')
        		return false;
        	}
        	return true;
        }
        
        //新建标签 
        function createTag () {
        	var newTag = $("#newTagStr").val();
        	if ($.trim(newTag) != '') {
        		var str = '<input type="checkbox" name="newTag" id="newTag" value="'+newTag+'" checked />'+newTag;
        		$("li.tagDiv").append(str);
        		$("#newTagStr").val("");
        	}
        	/* layer.open({
                type: 2,
                title: "选择用户",
                closeBtn: 1, //
                shade: [0],
                area: ['40%', '40%'],
                content: "${ctx}/manage/group/createTag",
                end: function(index){
                },
                cancel: function(){
                }
              }); */
         }
    </script>
</body>
</html>