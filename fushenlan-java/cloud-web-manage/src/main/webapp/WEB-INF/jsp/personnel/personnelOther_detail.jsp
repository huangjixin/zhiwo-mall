<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<style>
	.form-group{
		margin-top:30px;
	}
</style>
   <script type="text/javascript"	src="${ctx}/resources/libs/layer/layer.js"></script>
   
   <div>
   </div>
		
		<div class="form-detail">
			<div class="title"><strong>个人信息</strong></div>
			<ul class="clearfix">
				<input class="personnelidentityType" type="hidden" value="${person.personnel.identityType }">
				<input class="personnelidentityCode" type="hidden" value="${person.personnel.identityCode }">
				<input class="personnelname" type="hidden" value="${person.personnel.name }">
				<input class="personnelsex" type="hidden" value="${person.personnel.sex }">
				<input class="personnelid" type="hidden" value="${person.personnel.id }">
				
				<li class="col-md-12"><strong>头像：</strong><img src="/imgs/photo.png" width="80" height="80" alt=""></li>
				<li class="col-md-4"><strong>证件类型：</strong>${person.personnel.identityType=='111'?'身份证':person.personnel.identityType=='414'?'护照':person.personnel.identityType=='511'?'台胞证':person.personnel.identityType=='516'?'港澳回乡证':'其它' }</li>
				<li class="col-md-4"><strong>证件号码：</strong>${person.personnel.identityCode }</li>
				<li class="col-md-4"><strong>证件有效期：</strong>
 				 <c:choose>
				  	<c:when test="${person.personnel.ctfexpireDate eq 'Tue Jan 01 00:00:00 CST 2199'}">
						长期有效
					</c:when>
					<c:otherwise>
						<fmt:formatDate value="${person.personnel.ctfexpireDate }" pattern="YYYY-MM-dd"/>
					</c:otherwise>
				  </c:choose>
				</li>
				<li class="col-md-4"><strong>姓名：</strong>${person.personnel.name }</li>
				<li class="col-md-4"><strong>性别：</strong>${person.personnel.sex=='M'?'男':'女'} </li>
				<!-- <li class="col-md-4"><strong>年龄：</strong>26岁</li> -->
				<li class="col-md-4"><strong>生日：</strong><fmt:formatDate value="${person.personnel.birthday }" pattern="YYYY-MM-dd"/></li>
				<li class="col-md-4"><strong>民族：</strong>${person.personnel.nation }</li>
				<li class="col-md-4"><strong>政治面貌：</strong>${person.personnel.political }</li>
				<li class="col-md-4"><strong>手机号：</strong>${person.personnel.cellphone }</li>
				<li class="col-md-4"><strong>邮箱：</strong>${person.personnel.email }</li>
			</ul>
			<div class="title"><strong>计划申请</strong></div>
			<c:forEach items="${person.apply}" var="apply" varStatus="ext">
			  <ul class="clearfix">
				<li class="col-md-4"><strong>人才计划：</strong>${apply.talentPlan }</li>
				<li class="col-md-4"><strong>推荐人姓名：</strong>${apply.refereeName }</li>
				<li class="col-md-4"><strong>推荐人编号：</strong>${apply.refereeNo }</li>
				<li class="col-md-12"><strong>开始日期：</strong><fmt:formatDate value="${apply.createTime }" pattern="YYYY-MM-dd"/></li>
			  </ul>
			</c:forEach>
			<div class="title"><strong>家庭信息</strong></div>
			<c:forEach items="${person.familyMember}" var="familyMember" varStatus="ext">
			  <ul class="clearfix">
				<li class="col-md-4"><strong>姓名：</strong>${familyMember.name }</li>
				<li class="col-md-4"><strong>关系：</strong>${familyMember.relationship }</li>
				<li class="col-md-4"><strong>电话：</strong>${familyMember.telephone }</li>
				<li class="col-md-12"><strong>所属公司：</strong>${familyMember.company }</li>
			  </ul>
			</c:forEach>
			<div class="title"><strong>教育信息</strong></div>
			<c:forEach items="${person.educational}" var="educational" varStatus="ext">
			  <ul class="clearfix">
			    <li class="col-md-4"><strong>开始时间：</strong><fmt:formatDate value="${educational.startTime }" pattern="YYYY-MM-dd"/><strong style="margin-left:30px;">结束时间:</strong><fmt:formatDate value="${educational.endTime }" pattern="YYYY-MM-dd"/></li>
				<li class="col-md-4"><strong>学历：</strong>${educational.education }</li>
				<li class="col-md-4"><strong>院校：</strong>${educational.school }</li>
			  </ul>
			</c:forEach>
			<div class="title"><strong>工作经历</strong></div>
			<c:forEach items="${person.workExperience}" var="workExperience" varStatus="ext">
			  <ul class="clearfix">
			    <li class="col-md-4"><strong>开始时间：</strong><fmt:formatDate value="${workExperience.startTime }" pattern="YYYY-MM-dd"/>
			      <strong style="margin-left:30px;">结束时间:</strong><fmt:formatDate value="${workExperience.endTime }" pattern="YYYY-MM-dd"/></li>
				  <li class="col-md-4"><strong>职业：</strong>${workExperience.occupation }</li>
				  <li class="col-md-4"><strong>职位：</strong>${workExperience.post }</li>
				  <li class="col-md-4"><strong>单位名称：</strong>${workExperience.company }</li>
			  </ul>
			</c:forEach>
			
			<div class="title"><strong>标签</strong></div>
			<c:forEach items="${person.tag}" var="tag" varStatus="ext">
			  <ul class="clearfix">
				  <li class="col-md-4"><strong>标签名字：</strong>${tag.tagName}</li>
			  </ul>
			</c:forEach>
			
			
			<div class="form-group clearfix">
			      <label for="content" class="col-md-2 control-label">身份证正面<span class="text-danger"></span></label>
		             <div class="col-md-6">
						<img src="${path1}"  width="150" height="150" >
					</div>
			</div>
			
			<div class="form-group clearfix">
			      <label for="content" class="col-md-2 control-label">身份证反面<span class="text-danger"></span></label>
		             <div class="col-md-6">
						<img src="${path2}"  width="150" height="150" >
					</div>
			</div>
			
			<div class="form-group clearfix">
			      <label for="content" class="col-md-2 control-label">学历证明<span class="text-danger"></span></label>
		             <div class="col-md-6">
						<img src="${path4}"  width="150" height="150" >
					</div>
			</div>
			
			<div class="form-group clearfix">
			      <label for="content" class="col-md-2 control-label">学信网验证页面<span class="text-danger"></span></label>
		             <div class="col-md-6">
						<img src="${path5}"  width="150" height="150" >
					</div>
			</div>
			
			
			<div class="form-group clearfix">
			      <label for="content" class="col-md-2 control-label">外籍人员就业证明<span class="text-danger"></span></label>
		             <div class="col-md-6">
						<img src="${path13}"  width="150" height="150" >
					</div>
			</div>
			
		   <div class="form-group clearfix" >
			      <label for="content" class="col-md-2 control-label">核查意见:<span class="text-danger"></span></label>
			      <textarea style="border: 1px solid;width: 300px; overflow-x: hidden; height: 60px;" id="cheakOption"></textarea>
			</div>
			
			<div class="ui-button" style="text-align:center">
			  <c:if test="${person.personnel.checkResult==null || person.personnel.checkResult=='' }">
			       <button type="button" class="btn btn-submit" onclick="pass('${person.personnel.id}','1')">通过</button>
			       <button type="button" class="btn btn-submit" onclick="pass('${person.personnel.id}','0')">不通过</button>
			  </c:if>
				<button type="button" class="btn btn-default" onclick="javascript:history.go(-1);">返回</button>
			</div> 
			
		</div>
		
		<script>
		$(document).ready(function(){
		   
		    /* $(".ase").fancybox({
		        fitToView	: false,
		        width		: '60%',
		        height		: '60%',
		        autoSize	: false,
		        closeClick	: false,
		        openEffect	: 'none',
		        closeEffect	: 'none',
		        afterClose:function(){
		            location.reload(true);
		        }
		    }); */

		});
		
		
	
		
		$(function(){
			/*单选框*/
			$(".radio_check").on("click",function(){
				$(".radio_check").prev().attr("checked",false);
				$(".radio_check").removeClass("active");
				$(this).prev().attr("checked",true);
				if($(this).prev().attr("checked")){
					$(this).addClass("active");
				}
			});
		});
		
		/* function importgroup(){
		var   personnelidentityType = $(".personnelidentityType").val();
		var   personnelidentityCode = $(".personnelidentityCode").val();
		var   personnelname = $(".personnelname").val();
		var   personnelsex = $(".personnelsex").val();
		var   personnelid = $(".personnelid").val();
		
		var index=	layer.open({
				  title: "dms人员选择!",
				  type: 2,
				  area: ['500px', '260px'], //宽高
				  closeBtn:0,
				  btnAlign :"c",
				  content: "${ctx}/manage/erecruitment/getdmsinfo?personnelidentityType=" +personnelidentityType + "&personnelidentityCode="+personnelidentityCode + "&personnelname="+personnelname + "&personnelsex="+personnelsex + "&personnelid="+personnelid,
				});
		
		} */
		
		function pass(id,checkResult){
			layer.confirm('你确定要进行此操作吗？', {
				icon: 3,
			  	btn: ['确定', '取消'] //按钮
			}, function(){
				
				$.ajax({
					type : "post",
					data : {"id" : id,"checkResult": checkResult,"cheakOption":$("#cheakOption").val()},
					url : "${ctx}/manage/erecruitment/pass",
					dateType: "json",
					success : function(msg) {
						if (msg !=null) {
							layer.alert("设置成功", {icon: 1}, function(){
								window.location="${ctx}/manage/erecruitment/personnelOtherList";
							});
						}else {
							layer.alert("设置失败", {icon: 5});
						}
					}
				});
			});
		}
		$(document).ready(function(){
			   //do something
			var   personnelidentityType = $(".personnelidentityType").val();
			var   personnelidentityCode = $(".personnelidentityCode").val();
			var   personnelname = $(".personnelname").val();
			var   personnelsex = $(".personnelsex").val();
			var   personnelid = $(".personnelid").val();
			
			var index=	layer.open({
					  title: "dms人员选择!",
					  type: 2,
					  area: ['500px', '260px'], //宽高
					  closeBtn:0,
					  btnAlign :"c",
					  content: "${ctx}/manage/erecruitment/getdmsinfo?personnelidentityType=" +personnelidentityType + "&personnelidentityCode="+personnelidentityCode + "&personnelname="+personnelname + "&personnelsex="+personnelsex + "&personnelid="+personnelid,
					});
		})
		</script>
</body>
</html>
