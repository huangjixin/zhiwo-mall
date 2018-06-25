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
<link rel="stylesheet" type="text/css" href="${ctx}/resources/libs/datepicker/skin/default/datepicker.css">
<script type="text/javascript" src="${ctx}/resources/js/common/jquery-form.js"></script>
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
input.studyPlanName{
	width: 30%;
    font-size: 14px;
    line-height: 36px;
    padding: 0 10px;
    border: 1px solid #dadada;
    background-color: #fff;
    border-radius: 3px;
    box-sizing: border-box;
}
input.studyPlanDescription{
	width: 60%;
	font-size: 14px;
    line-height: 36px;
    padding: 0 10px;
    border: 1px solid #dadada;
    background-color: #fff;
    border-radius: 3px;
    box-sizing: border-box;
}

</style>

		<div class="form-detail">
		<c:forEach items="${planOrCourseIds}" var="planOrCourseId">
		<input type="hidden" name="planOrCourseId" value="${planOrCourseId }">
		</c:forEach>
		
		<input type="hidden" name="planOrCourse" id="planOrCourse" value="${type }">
		<%-- <input type="hidden" id="planOrCourseId" value="${planOrCourseId }"><input type="hidden" id="planOrCourse" value="${type }"> --%>
		<form method="post"  id="publicClassId" action="" >
		  <input type="hidden" id="fileId" name="fileId" value="">
				
			<div class="title"><strong>课程限制</strong></div>
			<ul class="clearfix online_form">
			
				<c:if test="${type=='2' }">
					<li class="col-md-12">
					<strong>计划名称：</strong>
					<input type="text" name="studyPlanName" class="studyPlanName">
					</li>
					<li class="col-md-12">
					<strong>计划说明：</strong>
					<input type="text" name="studyPlanDescription" class="studyPlanDescription">
					</li>
				</c:if>	
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
						</select>
					</div>
				</li>
				<li class="col-md-3">
					<strong>二级分类：</strong>
					<div class="ipt-select">
						<i class="icon-arrow-down"></i>
						<select name="publicClass.childTagId" id="childTagId">
							<option value="" >请选择</option>
						</select>
					</div>
				</li>
				<div style="display:none"; id="xianzhi">
				</div>
				<li class="col-md-3"><strong>人员限制：</strong><button onclick="choosePerson()" type="button" class="btn btn-submit overt_button">选择</button></li>
				<li class="col-md-3"><strong>开放对象：</strong>
                    <!-- <button onclick="choosePerson()" type="button" class="btn btn-submit overt_button">选择</button> -->
                    <input type="radio" id="openRange" name="publicClass.openRange" class="" value="0" checked/> 全部公开
                    <input type="radio" id="openRange" name="publicClass.openRange" class="" value="1"  onclick ="choosePerson()" <c:if test="${publicClass.openRange == 1 }">checked</c:if>/> 指定用户
                    <input type="radio" id="openRange" name="publicClass.openRange" class="" value="2" <c:if test="${publicClass.openRange == 2 }">checked</c:if>/> 游客及登录用户
                </li>
				<li class="col-md-12 zlgl_share"><strong>课程售价：</strong>
					<label for="free"><input type="radio" value="1" id="free" name="publicClass.isFree">免费</label>
					<label for="charge"><input type="radio" value="0" id="charge" name="publicClass.isFree">积分兑换&nbsp;<input type="text" class="ipt-text" value="" name="publicClass.exchangePoint" onkeyup="value=value.replace(/[^\d.]/g,'')" style="max-width: 130px;">&nbsp;积分</label>
				</li>
				<li class="col-md-12 zlgl_share"><strong>课程奖励：</strong>
					<label for="integra">
						<input type="checkbox" value="1" id="integra" name="publicClass.isRewardPoint">可获积分&nbsp;
						<input type="text" class="ipt-text" value="" name="publicClass.rewardPoint" onkeyup="value=value.replace(/[^\d.]/g,'')" style="max-width: 130px;">&nbsp;积分
					</label>
					<label for="award">
						<input type="checkbox" value="1" id="award" name="publicClass.isRewardCertification">证书奖励&nbsp;
						<button type="button" class="btn btn-submit overt_button" >&nbsp;选择
						</button>
					</label>
					<label for="qualify" >
						<input type="checkbox" value="" id="qualify" name="publicClass.isRewardQualification">DMS资格&nbsp;
						<select class="overt_select" name="publicClass.qualificationId">
							<option value="0">资格编号1</option>
							<option value="1">资格编号2</option>
							<option value="2">资格编号3</option>
							<option value="3">资格编号4</option>
						</select>
					</label>
				</li>
				<li class="col-md-12 zlgl_share"><strong>开课时间：</strong>
					<label for="effect"><input type="radio" value="1" id="effect" name="publicClass.isLongTerm">长期有效</label>
					<label for="appoint">
						<input type="radio" value="2" id="appoint" name="publicClass.isLongTerm">指定日期&nbsp;
						<input class="ipt-text proTime" name="publicClass.startDate" type="text" placeholder="开始时间" onfocus="WdatePicker({onpicked: function(){jQuery(this).trigger('change');},oncleared: function(){jQuery(this).trigger('change');}})">&nbsp;—&nbsp;<input class="ipt-text proTime" name="publicClass.endDate" type="text" placeholder="结束时间" onfocus="WdatePicker({onpicked: function(){jQuery(this).trigger('change');},oncleared: function(){jQuery(this).trigger('change');}})">
					</label>
				</li>
				<li class="col-md-12 zlgl_share"><strong>上架时间：</strong>
					<input class="ipt-text proTime" name="publicClass.onlineDate" type="text" placeholder="上架时间" onfocus="WdatePicker({onpicked: function(){jQuery(this).trigger('change');},oncleared: function(){jQuery(this).trigger('change');}})">
				</li>
				<li class="col-md-12 zlgl_share"><strong>下架时间：</strong>
					<input class="ipt-text proTime" name="publicClass.offlineDate" type="text" placeholder="下架时间" onfocus="WdatePicker({onpicked: function(){jQuery(this).trigger('change');},oncleared: function(){jQuery(this).trigger('change');}})">
				</li>
				<li class="col-md-12 zlgl_share"><strong>是否开放提问：</strong>
					<label for="yes"><input type="radio" value="1" id="yes" name="publicClass.isQuestion">是
					</label>
					<label for="no"><input type="radio" value="0" id="no" name="publicClass.isQuestion">否
					</label>
				</li>
				<li class="col-md-3"><strong>顺序学习：</strong>
                    <input type="radio" id="isSeq" name="publicClass.isSeq" class="" value="1" checked/> 是
                    <input type="radio" id="isSeq" name="publicClass.isSeq" class="" value="0" <c:if test="${publicClass.isSeq == 0 }">checked</c:if>/> 否
                </li>
			</ul><input type="hidden" name="publicClass.tagName" id="tagName"><input type="hidden" name="publicClass.childTagName" id="childTagName">
			<div class="ui-button">
				<a class="btn btn-submit" href="${ctx }/manage/toAddJsp" title="上一步">上一步</a>
				<button type="button" class="btn btn-submit" onclick="savePublicClass()">保存</button>
				<button type="button" class="btn btn-default"  onclick="returnPublicClass()">取消</button>
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
		
		
		 var planOrCourseId=new Array();;
		 
		 var groupCheckbox=$("input[name='planOrCourseId']");
	     for(i=0;i<groupCheckbox.length;i++){
	        	planOrCourseId.push(groupCheckbox[i].value);
	    }
		var type=$("#planOrCourse").val();
		//console.log($('#publicClassId').serialize());
		if(switchButton){
			 $.ajax({
		      type: 'POST',
		  	  url: "${ctx }/manage/submitPublicClass?planOrCourseId="+planOrCourseId+"&type="+type,
		  	  dataType: "json",
		  	  data :$('#publicClassId').serialize(),
		  	  //data :{"publicClass":$('#publicClassId').serialize(),"planOrCourseId":planOrCourseId,"type":type},
		  	  success: function(result) {
		  		 /* alert(result.msg); */
				location.href="${ctx}/manage/publicClassListByPage";
			  }
		  	}) 
		}
		switchButton =0;
	}
	
	function returnPublicClass(){
		location.href="${ctx}/manage/publicClassListByPage";
	}
	
	
	/* $(document).ready(function(){
		$.ajax({
			 type: "POST",
		  	 url: "${ctx }/manage/findByCatagoryAndParentId",
		  	 dataType: "json",
		  	 data :{"parentId":"0","catagory":"2"},
		  	 success: function(result) {
		  		$.each(result.data,function(i,item){
		  			$("#tagId").append("<option onchange='addChildTagId("+item.tagTwoList+")' value='"+item.id+"' item='"+item.tagTwoList+"'>"+item.tagName+"</option>");
	  		  })
			  }
		})
	}) */
	
	function addChildTagId(childTagIdList){
		console.log(JSON.stringify(childTagIdList));
	}
</script>
<script type="text/javascript">

	$(function(){
		//doAjax();
		$("#tagId").click(function(){
			var parentId=$("#tagId option:selected").val();
			if(parentId==null||parentId==""){
				doAjax();
			}
			$("#childTagId").empty().append("<option value='"+""+"'>请选择</option>");
		});
	
		$("#childTagId").click(function(){
			var parentId=$("#tagId option:selected").val();  //获取选中的项
			var childTagId=$("#childTagId option:selected").val();
			var boo=true;
			if(childTagId==null||childTagId==""){
				doAjax(parentId,boo);
			}
		}); 
	});
	
	
	function doAjax(parentId,boo){
		if(parentId==null){
			parentId=0;
		}
		$.ajax({
			url: "${ctx }/manage/findByCatagoryAndParentId",
		  	dataType: "json",
		  	data :{"parentId":parentId,"catagory":"2"},
			type : "post",
			success : function(respData) {
				if(parentId==0){
					first(respData.data);
				}else if(boo){
					second(respData.data);
				}
			}
		});
	}
	
	
	function first(respData){
		//循环遍历返回的json
		//$("#tagId").empty();
		$("#tagId").empty().append("<option value='"+""+"'>请选择</option>");
		$.each(respData,function(i,item){
			$("#tagId").append("<option value='"+item.id+"'>"+item.tagName+"</option>");				
		})
		
	}
	
	function second(respData){
		//循环遍历返回的json
		//$("#childTagId").empty();
		$("#childTagId").empty().append("<option value='"+""+"'>请选择</option>");
		$.each(respData,function(i,item){
			$("#childTagId").append("<option value='"+item.id+"'>"+item.tagName+"</option>");				
		})
	}
	 
	
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