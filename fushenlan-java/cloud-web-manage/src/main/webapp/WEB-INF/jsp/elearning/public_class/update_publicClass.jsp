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
<script type="text/javascript"	src="${ctx}/resources/libs/layer/layer.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/page.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/libs/datepicker/skin/default/datepicker.css">
<style type="text/css">

.overt_select {
    height: 38px;
    width: 180px;
    font-size: 14px;
    line-height: 36px;
    padding: 0 10px;
    border: 1px solid #dadada;
    border-radius: 3px;
}
.studyPlanName{
	width: 60%;
}
.studyPlanDescription{
	width: 30%;
}

</style>
		<div>
			<div class="title"><strong>课程信息</strong></div>
				<ul class="clearfix form_learn">
				<li class="col-md-12"><strong><ins>*</ins>计划名称：</strong>${publicClassVo.studyPlan.name }
				<%-- <input type="text" value="${publicClassVo.studyPlan.name }" readonly="readonly" class="ipt-text"></li> --%>
				<li class="col-md-12"><strong><ins>*</ins>计划说明：</strong>${publicClassVo.studyPlan.description }
				<%-- <input type="text" value="${publicClassVo.studyPlan.description }" readonly="readonly" class="ipt-text"></li> --%>
				<li class="col-md-4">
					<strong><ins>*</ins>计划分类：</strong>
					<div class="ipt-select">
						<c:if test="${publicClassVo.studyPlan.tagId==0  }">保险</c:if>
						<c:if test="${publicClassVo.studyPlan.tagId==1  }">技巧</c:if>
						<c:if test="${publicClassVo.studyPlan.tagId==2  }">产品</c:if>
						<c:if test="${publicClassVo.studyPlan.tagId==3  }">沟通</c:if>
					</div>
				</li>
			</ul>
		</div>
		<div>
			<div class="title"><strong>计划详情</strong></div>
				<ul class="clearfix">
				<strong style="font-size: 14px">线上课程：</strong>
				<table class="testPaper_table">
					<tbody>
						<tr>
							<td>
								<b>课程详情</b>
							</td>
							<td>
								<ol>
								<c:forEach items="${publicClassVo.course }" var="course">
									<li><span>${course.name }</span><i>
									<c:if test="${course.isCompulsory==1 }">必修</c:if>
									<c:if test="${course.isCompulsory==0 }">选修</c:if>
									</i></li>
								</c:forEach>
								</ol>
							</td>
						</tr>
					</tbody>
				</table>
			</ul>
		</div>
		<div class="form-detail">
		<form method="post"  id="publicClassId" action="" >
			<input type="hidden" name="publicClass.id" value="${publicClassVo.id }">
			<input type="hidden" id="fileId" name="fileId" value="">
			<div class="title"><strong>课程限制</strong></div>
			<ul class="clearfix online_form">
			    <li class="col-md-10 auto"><strong><ins>*</ins>公开课缩略图：</strong>
                   <img id ="smallImage" src="${smallImage.path}" width="80" height="80"><input type="hidden" id="attachmentBannerOne">
                   <button onclick="changeC(24)" type="button" class="btn btn-sm btn-submit ipt-btnfile">请选择</button>
                </li>
				<li class="col-md-3">
					<strong>一级分类：</strong>
					<div class="ipt-select">
						<i class="icon-arrow-down"></i>
						<select name="publicClass.tagId" id="tagId">
							<option value="" >请选择</option>
							<c:forEach items="${resTag }" var="tagId">
							<option value="${tagId.id }" <c:if test="${publicClassVo.tagId==tagId.id }">selected</c:if>>${tagId.tagName }</option>
							</c:forEach>
						</select>
					</div>
				</li>
				<li class="col-md-3">
					<strong>二级分类：</strong>
					<div class="ipt-select">
						<i class="icon-arrow-down"></i>
						<select name="publicClass.childTagId" id="childTagId">
							<option value="" >请选择</option>
							<c:forEach items="${resChildTagId }" var="childTagId">
							<option value="${childTagId.id }" <c:if test="${publicClassVo.childTagId==childTagId.id }">selected</c:if> >${childTagId.tagName }</option>
							</c:forEach>
						</select>
					</div>
				</li>
				<div style="display:none"; id="xianzhi">
				<div style="display:none";>
				<c:forEach items="${planAuthoritys }" var="authority" varStatus="status">
					<input type="hidden" name="planAuthorityList['${status.index }'].authorityType" value="${authority.authorityType }">
					<input type="hidden" name="planAuthorityList['${status.index }'].associateId" value="${authority.associateId }">
				</c:forEach>
				</div>
				</div>
				<li class="col-md-3"><strong>人员限制：</strong><button onclick="choosePerson()" type="button" class="btn btn-submit overt_button">选择</button></li>
				<li class="col-md-3"><strong>开放对象：</strong>
                    <!-- <button onclick="choosePerson()" type="button" class="btn btn-submit overt_button">选择</button> -->
                    <input type="radio" id="openRange" name="publicClass.openRange" class="" value="0" checked/> 全部公开
                    <input type="radio" id="openRange" name="publicClass.openRange" class="" value="1"  onclick ="choosePerson()" <c:if test="${publicClassVo.openRange == 1 }">checked</c:if>/> 指定用户
                    <input type="radio" id="openRange" name="publicClass.openRange" class="" value="2" <c:if test="${publicClassVo.openRange == 2 }">checked</c:if>/> 游客及登录用户
                </li>
				<li class="col-md-12 zlgl_share"><strong>课程售价：</strong>
				
					<label for="free"><input type="radio" <c:if test="${publicClassVo.isFree==1 }">checked</c:if> value="1" id="free" name="publicClass.isFree">免费</label>
					<label for="charge"><input type="radio"  <c:if test="${publicClassVo.isFree==0 }">checked</c:if> value="0" id="charge" name="publicClass.isFree">积分兑换&nbsp;<input type="text" class="ipt-text" value="${publicClassVo.exchangePoint }" name="publicClass.exchangePoint" onkeyup="value=value.replace(/[^\d.]/g,'')" style="max-width: 130px;">&nbsp;积分</label>
					
				</li>
				<li class="col-md-12 zlgl_share"><strong>课程奖励：</strong>
					<label for="integra">
						<input type="checkbox" value="1" <c:if test="${publicClassVo.isRewardPoint==1 }">checked</c:if> id="integra" name="publicClass.isRewardPoint">可获积分&nbsp;
						<input type="text" class="ipt-text" value="${publicClassVo.rewardPoint }" name="publicClass.rewardPoint" onkeyup="value=value.replace(/[^\d.]/g,'')" style="max-width: 130px;">&nbsp;积分
					</label>
					<label for="award">
						<input type="checkbox" value="1" <c:if test="${publicClassVo.isRewardCertification==1 }">checked</c:if> id="award" name="publicClass.isRewardCertification">证书奖励&nbsp;
						<button type="button" class="btn btn-submit overt_button" >&nbsp;选择
						</button>
					</label>
					<label for="qualify" >
						<input type="checkbox" value="1" <c:if test="${publicClassVo.isRewardQualification==1 }">checked</c:if> id="qualify" name="publicClass.isRewardQualification">DMS资格&nbsp;
						<select class="overt_select" name="publicClass.qualificationId">
							<option value="1" <c:if test="${publicClassVo.qualificationId==1 }">selected</c:if> >资格编号1</option>
							<option value="2" <c:if test="${publicClassVo.qualificationId==2 }">selected</c:if> >资格编号2</option>
							<option value="3" <c:if test="${publicClassVo.qualificationId==3 }">selected</c:if> >资格编号3</option>
							<option value="4" <c:if test="${publicClassVo.qualificationId==4 }">selected</c:if> >资格编号4</option>
						</select>
					</label>
				</li>
				<li class="col-md-12 zlgl_share"><strong>开课时间：</strong>
					<label for="effect"><input type="radio" value="1" <c:if test="${publicClassVo.isLongTerm==1 }">checked</c:if> id="effect" name="publicClass.isLongTerm">长期有效</label>
					<label for="appoint">
						<input type="radio" value="2" <c:if test="${publicClassVo.isLongTerm==2 }">checked</c:if> id="appoint" name="publicClass.isLongTerm">指定日期&nbsp;
						<input class="ipt-text proTime" value="<fmt:formatDate value='${publicClassVo.startDate }'/>" name="publicClass.startDate" type="text" placeholder="开始时间" onfocus="WdatePicker({onpicked: function(){jQuery(this).trigger('change');},oncleared: function(){jQuery(this).trigger('change');}})">&nbsp;—&nbsp;
						<input class="ipt-text proTime" value="<fmt:formatDate value='${publicClassVo.endDate }'/>" name="publicClass.endDate" type="text" placeholder="结束时间" onfocus="WdatePicker({onpicked: function(){jQuery(this).trigger('change');},oncleared: function(){jQuery(this).trigger('change');}})">
					</label>
				</li>
				<li class="col-md-12 zlgl_share"><strong>上架时间：</strong>
					<input class="ipt-text proTime" value="<fmt:formatDate value='${publicClassVo.onlineDate }'/>" name="publicClass.onlineDate" type="text" placeholder="上架时间" onfocus="WdatePicker({onpicked: function(){jQuery(this).trigger('change');},oncleared: function(){jQuery(this).trigger('change');}})">
				</li>
				<li class="col-md-12 zlgl_share"><strong>下架时间：</strong>
					<input class="ipt-text proTime" value="<fmt:formatDate value='${publicClassVo.offlineDate }'/>" name="publicClass.offlineDate" type="text" placeholder="下架时间" onfocus="WdatePicker({onpicked: function(){jQuery(this).trigger('change');},oncleared: function(){jQuery(this).trigger('change');}})">
				</li>
				<li class="col-md-12 zlgl_share"><strong>是否开放提问：</strong>
					<label for="yes"><input type="radio" value="1"  <c:if test="${publicClassVo.isQuestion==1 }">checked</c:if> id="yes" name="publicClass.isQuestion">是
					</label>
					<label for="no"><input type="radio" value="0" <c:if test="${publicClassVo.isQuestion==0 }">checked</c:if> id="no" name="publicClass.isQuestion">否
					</label>
				</li>
				<li class="col-md-3"><strong>顺序学习：</strong>
                    <input type="radio" id="isSeq" name="publicClass.isSeq" class="" value="1" checked/> 是
                    <input type="radio" id="isSeq" name="publicClass.isSeq" class="" value="0" <c:if test="${publicClassVo.isSeq == 0 }">checked</c:if>/> 否
                </li>
			</ul><input type="hidden" name="publicClass.tagName" id="tagName"><input type="hidden" name="publicClass.childTagName" id="childTagName">
			<div class="ui-button">
				<button type="button" class="btn btn-submit" onclick="savePublicClass()">保存</button>
				<button type="button" class="btn btn-default"  onclick="history.back(-1)">取消</button>
			</div>
			</form>
		</div>
		<form id="bannerFile" style="display: none;" method="post" enctype="multipart/form-data">
            <input type="file" name="fileName" id="fileName" onchange="upload()" >
            <input name="category" type="hidden" id ="category">
        </form>
<%-- <script src="${ctx}/resources/js/common/jquery-3.2.1.min.js"></script> --%>
<%-- <script src="${ctx}/resources/js/common.js"></script> --%>
<script type="text/javascript" src="${ctx}/resources/libs/datepicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common/jquery-form.js"></script>
<script type="text/javascript">


	function addPersonOther(){
		$("#xianzhi").children('div').remove();
	}
	function addPerson(authorityType,associateId,dh){
		var lh = $("#xianzhi").children('div').length;
		var html='<div style="display:none";>'+ 
		         '<input type="hidden" name="planAuthorityList['+lh+'].authorityType" value="'+authorityType+'">'+
		         '<input type="hidden" name="planAuthorityList['+lh+'].associateId" value="'+associateId+'">'+
		         '</div>';
		         
		  $("#xianzhi").append(html);     
	}
	
	function choosePerson(){
		var personIds = "";
		$("#xianzhi").children('div').each(function(){
			personIds += $(this).children().first().next().val()+",";
		});
		personIds=personIds.substring(0,personIds.length-1);
		  layer.open({
			  type: 2,
			  title: false,
			  closeBtn: 1, //不显示关闭按钮
			  shade: [0],
			  area: ['1200px', '550px'],
			  content: "${ctx}/manage/elearning/choosePerson?personIds="+personIds,
			  end: function(){
				  
			  }
		   });
	} 
	
	//防重复提交标志位 
	var switchButton =1;
	function savePublicClass(){
		var tagId=$("#tagId option:selected").val();
		var childTagId=$("#childTagId option:selected").val();
		var tagName=$("#tagId option:selected").text();
		var childTagName=$("#childTagId option:selected").text();
		if(null!=tagId&&""!=tagId){
			$("#tagName").val(tagName);
		}
		if(null!=childTagId&&""!=childTagId){
			$("#childTagName").val(childTagName);	
			}
		if(switchButton){
			 $.ajax({
		      type: 'POST',
		  	  url: "${ctx }/manage/submitPublicClass",
		  	  dataType: "json",
		  	  data :$('#publicClassId').serialize(),
		  	  success: function(result) {
				location.href="${ctx}/manage/publicClassListByPage";
			  }
		  	}) 
		}
		switchButton =0;
	}
</script>
<script type="text/javascript">
	//二级分类动态显示
	$("#tagId").change(function(){
		var parentId=$("#tagId option:selected").val();
		if(null!=parentId&&""!=parentId){
			$.ajax({
				url: "${ctx }/manage/findByCatagoryAndParentId",
			  	dataType: "json",
			  	data :{"parentId":parentId,"catagory":"2"},
				type : "post",
				success : function(respData) {
					//循环遍历返回的json
					$("#childTagId").empty().append("<option value='"+""+"'>请选择</option>");
					$.each(respData.data,function(i,item){
						$("#childTagId").append("<option value='"+item.id+"'>"+item.tagName+"</option>");				
					})
				}
			});
		}else{
			$("#childTagId").empty().append("<option value='"+""+"'>请选择</option>");
		}
	})
	
	//附件上传 
     function changeC(category){  
           $("input#category").val(category);  //附件表中定义的附件类型 
           fileName.click();
       }
       
     function upload(){
         var btn_index = layer.load(2);
          $("#bannerFile").ajaxSubmit({
             url : '${ctx}/manage/commonUploadFile',
             type :"post",
             success : function(data){
                     console.log(data);
                     if (data != null) {  //附件要回显
                         $("input#fileId").val(data.id);
                         $("#smallImage").attr("src",data.path);
                     }
                     layer.close(btn_index);
                 },error:function() {
                     alert("附件上传错误");
                     layer.close(btn_index);
                 } 
             });  
         }
</script>
</body>
</html>