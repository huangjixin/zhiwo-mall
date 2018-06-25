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
<style>
/* .ui-button {
    padding: 15px 0;
    text-align: !important; 
} */
.filter-box .ipt-text {
    max-width: 200px;
    margin-left: 30px;
}
.sameWadte strong {
    width: none !important;
}
</style>
        <script type="text/javascript"	src="${ctx}/resources/libs/layer/layer.js"></script>
		<script type="text/javascript" src="${ctx}/resources/libs/datepicker/WdatePicker.js"></script>
		<%-- <link rel="stylesheet" type="text/css" href="${ctx}/resources/libs/datepicker/skin/default/datepicker.css"> --%>
		<!-- E Filter Box -->
		<form id="form1"  >
			<div class="filter-box">
			    <div class="title diff_btn">
				    <c:if test="${acc.id eq null}">
				    	<strong>用户新增</strong>
				    </c:if>
					<c:if test="${acc.id ne null}">
				    	<strong>用户修改</strong>
				    </c:if>
				</div>
				<input class="ipt-text" id="id" name="id" type="hidden" value="${acc.id }"/>
				<input class="ipt-text" id="isDelete" name="isDelete" type="hidden" value="${acc.delete }"/>
				<ul style="margin-left: 100px;">
					<li>
						<%-- <strong style="display:inline-block;width:120px;text-align:right">编号<big>:</big></strong>&nbsp;&nbsp;&nbsp;
						<input class="ipt-text" id="id" name="id" type="text" value="${acc.id }"/><br/><br/> --%>
						<strong style="display:inline-block;width:120px;text-align:right">账户名<big>:</big></strong>
						<input class="ipt-text" id="accountName" name="accountName"  type="text" value="${acc.accountName }" /><br/><br/>
					</li>
					<c:if test="${acc.id eq null}">
						<li>	
							<strong style="display:inline-block;width:120px;text-align:right">密码<big>:</big></strong>
							<input class="ipt-text" id="password" name="password" type="text" value="${acc.password }" /><br/><br/>
						</li>
					</c:if>
					<li>	
						<strong style="display:inline-block;width:120px;text-align:right">是否开通<big>:</big></strong>
						<div class="ipt-select" style="display: inline-block;width: 800px;">
							<select id="enabled" name="enabled" > 
								<option value="true" <c:if test="${acc.enabled ==true }">selected</c:if>>是</option>
								<option value="false" <c:if test="${acc.enabled ==false }">selected</c:if>>否</option>
							</select>
						</div>
						<strong style="display:inline-block;width:120px;text-align:right">开通时间<big>:</big></strong>
						<span class="sameWadte" style="margin-left:30px;">
						<input class="Wdate" id="enabledTime" name="enabledTime" type="text" value="<fmt:formatDate value="${acc.enabledTime }" pattern="yyyy-MM-dd"/>" onfocus="WdatePicker({onpicked: function(){jQuery(this).trigger('change');},oncleared: function(){jQuery(this).trigger('change');}})"/><br/><br/>
						</span>
					</li>
					<li>
						<strong style="display:inline-block;width:120px;text-align:right">是否锁定<big>:</big></strong>
						<div class="ipt-select" style="display: inline-block;width: 800px;">
							<select id="locked" name="locked" > 
								<option value="true" <c:if test="${acc.locked ==true }">selected</c:if>>是</option>
								<option value="false" <c:if test="${acc.locked ==false }">selected</c:if>>否</option>
							</select>
						</div>
						<strong style="display:inline-block;width:120px;text-align:right">锁定时间<big>:</big></strong>
						<span class="sameWadte" style="margin-left:30px;">
							<input class="Wdate" id="lockedTime" name="lockedTime" type="text" value="<fmt:formatDate value="${acc.lockedTime }" pattern="yyyy-MM-dd"/>" onfocus="WdatePicker({onpicked: function(){jQuery(this).trigger('change');},oncleared: function(){jQuery(this).trigger('change');}})"/><br/><br/>
					    </span>
					</li>
					<li>
						<strong style="display:inline-block;width:120px;text-align:right">手机号<big>:</big></strong>
						<input class="ipt-text" id="mobile" name="mobile" type="text" value="${acc.mobile }"/><br/><br/>
					</li>
					<li>	
						<strong style="display:inline-block;width:120px;text-align:right">座机号<big>:</big></strong>
						<input class="ipt-text" id="telephone" name="telephone" type="text" value="${acc.telephone }"/><br/><br/>
					</li>
					<li>	
						<strong style="display:inline-block;width:120px;text-align:right">邮箱<big>:</big></strong>
						<input class="ipt-text" id="email" name="email" type="text" value="${acc.email }"/><br/><br/>
					</li>
					<li>	
						<strong style="display:inline-block;width:120px;text-align:right">账号类型<big>:</big></strong>
						<div class="ipt-select" style="display: inline-block;width: 800px;">
							<select id="accountType" name="accountType">
							    <option value="">请选择</option>
								<option value="1" <c:if test="${acc.accountType==1 }">selected</c:if> >后台管理</option>
								<option value="3" <c:if test="${acc.accountType==3 }">selected</c:if> >代理人</option>
								<option value="4" <c:if test="${acc.accountType==4 }">selected</c:if> >内勤</option>
							</select>
						</div>
						<strong style="display:inline-block;width:120px;text-align:right">是否首次登录<big>:</big></strong>
						<div class="ipt-select" style="display: inline-block;width: 800px;">
							<select id="firstLogin" name="firstLogin">
								<option value="true" <c:if test="${acc.firstLogin ==true }">selected</c:if>>是</option>
								<option value="false" <c:if test="${acc.firstLogin ==false }">selected</c:if>>否</option>
							</select>
						</div><br/><br/>
					</li>
					<li>	
						<strong style="display:inline-block;width:120px;text-align:right">用户职级<big>:</big></strong>
						<div class="ipt-select" style="display: inline-block;width: 800px;">
							<select id="postType" name="postType" value="${acc.postType}">
							    <option value="">请选择</option>
								<c:forEach var="level" items="${lList }">
									<option value="${level.levelCode}" <c:if test='${level.levelCode eq acc.postType}'>selected="selected"</c:if> >${level.levelName}</option>
							   </c:forEach>
							</select>
						</div><br/><br/>
					<li> 
						<strong style="display:inline-block;width:120px;text-align:right">第一次登录时间<big>:</big></strong>
						<span class="sameWadte" style="margin-left:30px;">
							<input class="Wdate" id="firstLoginTime" name="firstLoginTime" type="text" value="<fmt:formatDate value="${acc.firstLoginTime }" pattern="yyyy-MM-dd"/>"  onfocus="WdatePicker({onpicked: function(){jQuery(this).trigger('change');},oncleared: function(){jQuery(this).trigger('change');}})"/>
						</span>
						<strong style="display:inline-block;width:120px;text-align:right">过期时间<big>:</big></strong>
						<span class="sameWadte" style="margin-left:30px;">
							<input class="Wdate" id="expiredTime" name="expiredTime" type="text" value="<fmt:formatDate value="${acc.expiredTime }" pattern="yyyy-MM-dd"/>"  onfocus="WdatePicker({onpicked: function(){jQuery(this).trigger('change');},oncleared: function(){jQuery(this).trigger('change');}})"/>
					    </span><br/><br/>
					</li>
					<c:if test="${acc.id ne null }">
						<li>
							<strong style="display:inline-block;width:120px;text-align:right">上次登录时间<big>:</big></strong>
							<input class="ipt-text" id="lastLoginTime" name="lastLoginTime" type="text" value="<fmt:formatDate value="${acc.lastLoginTime }" pattern="yyyy-MM-dd"/>" />
						</li>
					</c:if>
				</ul>
				<div class="ui-button" style="margin-top:30px;">
					<button type="button" class="btn btn-submit" onclick="updateAccount()">确定</button>
					<button type="button" class="btn btn-default" onclick="cancel()">取消</button>
				</div>
			</div>
		</form>
<script type="text/javascript">


	function updateAccount(){
		var id = $("#id").val();
		var accountName = $("#accountName").val();
		var password = $("#password").val();
		var mobile = $("#mobile").val();
		var email = $("#email").val();
		
		//邮箱不为空时判断
		if (email != null && email != "" && email != undefined) {
		    var reg = new RegExp("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$");
		    if (!reg.test(email)) { //正则验证不通过，格式不对
		    	layer.msg("邮箱格式错误!");
		        return false;
		    };
		};
		//手机号不为空时判断   
		if(mobile !=null && mobile!=""){
			var mPattern =/^[1][3,4,5,7,8][0-9]{9}$/;
			if(!mPattern.test(mobile)){
				layer.msg("手机号格式错误!");
				 return false;
			}
		}
		if(accountName == null || accountName == "" || accountName == undefined){
			layer.msg("账户名不能为空！");
			return false;
		}
		/* if(accountName.length<8){
			alert("账户名！");
			return;
		} */
		if("${acc.id}" == null){
			if(password == null || password == "" || password == undefined){
				layer.msg("密码不能为空！");
				return false;
			}
		}
		$.ajax({  
	        type: "POST",  
	        dataType : "json",  
	        data :$('#form1').serialize(),  
	        url: "${ctx}/manageAccounts/updateAccounts",//请求的action路径  
	       success:function (data) {//请求失败处理函数 
	        	location.href="${ctx}/manageAccounts/listByPages";  
	        },  
	        error: function (data) {//请求失败处理函数 
	        	 alert(data.msg);
	        }   
	    });   
	}
	function cancel(){
		   window.location.href = "${ctx}/manageAccounts/listByPages";
	   }
</script>
</body>
</html>