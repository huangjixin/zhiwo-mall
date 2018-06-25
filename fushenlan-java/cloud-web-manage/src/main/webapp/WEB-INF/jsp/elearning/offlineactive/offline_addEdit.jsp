<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<style type="text/css">
.dd{
    height: 40px;
    width: 180px;
    font-size: 14px;
    line-height: 36px;
    padding: 0 10px;
    border: 1px solid #dadada;
    border-radius: 3px;
}
.mm{
    width: 130px;
}
</style>
<form id="form1" action="${ctx }/manage/offlineManage/insertOrUpdate" method="post">
<input type="hidden" id="fileId" name="fileId" value="">
<div class="form-detail offline_form">
<div class="title diff_btn"> <strong>新建线下课程</strong><button type="button" onclick="chooseCourse()" class="btn btn-sm btn-submit ipt-btnfile">选择课程</button> </div>
	    <input id="cId"  type="hidden" name="offlineActivity.courseId" value="${offlineActivity.courseId }" >
		<input id="planCourseId" type="hidden" name="planCourseId" value="${planCourse.id }" > 
		<input type="hidden" name="offlineActivity.id" value="${id }">
		<ul class="clearfix form_learn">
			<li style="margin-left: -134px;width: 111%;margin-top: 5px;" class="col-md-12"><strong><ins>*</ins>活动名称：</strong><input id="name" name="offlineActivity.name" type="text" class="ipt-text" value="${offlineActivity.name }"></li>
			<li style="margin-left: -134px;width: 111%;margin-top: 5px;"class="col-md-12"><strong><ins>*</ins>活动说明：</strong><input id="description" name="offlineActivity.description" type="text" class="ipt-text" value="${offlineActivity.description }"></li>
			<li style="margin-left: -134px;width: 111%;margin-top: 5px;" class="col-md-12"><strong><ins>*</ins>活动缩略图：</strong><img name="offlineActivity.bannerPath" id="bannerPath"  src="${smallImage.path}" width="80" height="80">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button type="button" class="btn btn-sm btn-submit ipt-btnfile" onclick="changeC(24)">请选择</button>
			<input type="hidden" name="offlineActivity.bannerPath" id="bannerPath2"  value="${offlineActivity.bannerPath }"></li>
			
			<li style="margin-left: -134px;width: 111%;margin-top: 30px;" class="col-md-12 sameWadte" style="margin-top:30px">
				<strong>报名时间：</strong>
				<input type="text" id="enterStartDate" name="offlineActivity.enterStartDate" value="${offlineActivity.enterStartDate }" placeholder="开始时间" class="Wdate" style="max-width: 180px;" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',maxDate:'#F{$dp.$D(\'enterEndDate\')}'})">&nbsp;—&nbsp;
				<input type="text" id="enterEndDate" name="offlineActivity.enterEndDate" value="${offlineActivity.enterEndDate }" placeholder="结束时间" class="Wdate" style="max-width: 180px;" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',minDate:'#F{$dp.$D(\'enterStartDate\')}'})">
			</li>
			<li style="margin-left: -134px;width: 111%;margin-top: 5px;" class="col-md-12 sameWadte">
				<strong>上课时间：</strong>
				<input type="text" id="startTime" placeholder="开始时间"  name="offlineActivity.startDate" value="${offlineActivity.startDate }"    class="Wdate" style="max-width: 180px;" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',maxDate:'#F{$dp.$D(\'endTime\')}',minDate:'#F{$dp.$D(\'enterEndDate\')}'})">&nbsp;—&nbsp;
				<input type="text" id="endTime" placeholder="结束时间" name="offlineActivity.endDate" value="${offlineActivity.endDate }" class="Wdate" style="max-width: 180px;" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',minDate:'#F{$dp.$D(\'startTime\')}'})">
			</li> 
			<li style="margin-left: -134px;width: 111%;margin-top: 5px;" class="col-md-12"><strong>上课地点：</strong><input name="offlineActivity.address" value="${offlineActivity.address }" type="text" class="ipt-text" ></li>
			<li style="margin-left: -134px;width: 111%;margin-top: 5px;" class="col-md-12 zlgl_share"><strong>签到设置：</strong>
				<label for="ksh"><input name="offlineActivity.isNeedEnter" <c:if test="${offlineActivity.isNeedEnter==1}">checked</c:if>  value="1"  type="checkbox"  id="ksh" >需要报名</label>
				<label for="bkck"><input name="offlineActivity.isNeedSign" <c:if test="${offlineActivity.isNeedSign==1}">checked</c:if> value="1" type="checkbox"  id="bkck" >需要签到</label>
				<label for="bkck2"><input name="offlineActivity.isNoneedEnter" <c:if test="${offlineActivity.isNoneedEnter==1}">checked</c:if> value="1" type="checkbox"  id="bkck2" >不报名也可签到</label>
			</li>
				<li style="margin-left: -134px;width: 111%;margin-top: 5px;" class="col-md-12" style="margin-top:8px">
				<strong>报名人数：</strong>
				<label for="Enrolment">
				<input id="Enrolment" onkeyup="value=value.replace(/[^\d.]/g,'')" class="ipt-text sameTime"  type="text" name="offlineActivity.limitEnter" value="${offlineActivity.limitEnter }"></label>
			</li>
			<li style="margin-left: -134px;width: 111%;margin-top: 5px;" class="col-md-12"><strong>签到时间：</strong>
				<dl>
					
					<dd>
						<label for="before"><input name="offlineActivity.signTimeType" <c:if test="${offlineActivity.signTimeType==1 }">checked</c:if> onclick="check(this)" value="1" type="radio" id="before" name="share">课程开始前
						</label>
						<input  type="text"  <c:if test="${offlineActivity.signTimeType==1 }"> value="${offlineActivity.beforeBegin }"</c:if> class="ipt-text sameTime"  onkeyup="value=value.replace(/[^\d.]/g,'')">&nbsp;分钟（截至到上课前)
					</dd><br>
					
					<dd>
						<label for="begin">
							<input type="radio" onclick="check(this)" <c:if test="${offlineActivity.signTimeType==2 }">checked</c:if> name="offlineActivity.signTimeType" value="2" id="begin" name="share">课程开始前
						</label>
						<input   type="text"  <c:if test="${offlineActivity.signTimeType==2 }"> value="${offlineActivity.beforeBegin }" </c:if> class="ipt-text sameTime"  onkeyup="value=value.replace(/[^\d.]/g,'')">&nbsp;分钟到课程开始后&nbsp;
						<input   type="text"  <c:if test="${offlineActivity.signTimeType==2 }"> value="${offlineActivity.afterBegin }" </c:if> class="ipt-text sameTime"  onkeyup="value=value.replace(/[^\d.]/g,'')">&nbsp;分钟内
					</dd><br>
					
					<dd>
						<label for="after"><input onclick="check(this)" value="3" type="radio" <c:if test="${offlineActivity.signTimeType==3 }">checked</c:if> id="after" name="offlineActivity.signTimeType">课程开始后
						</label>
						<input  type="text" class="ipt-text sameTime" <c:if test="${offlineActivity.signTimeType==3 }"> value="${offlineActivity.afterBegin }" </c:if>   onkeyup="value=value.replace(/[^\d.]/g,'')">&nbsp;分钟内
					</dd>
				</dl>
			</li>
				<!--<li class="col-md-12"><strong>权限设置：</strong>
				<label ></label>总公司&nbsp;&nbsp;<div class="ipt-select" style="display:inline">
						<i class="icon-arrow-down"></i>
						<select name="state" id="sstate" style="width:130px">
							<option value="">请选择</option>
							<option value="1">全部</option>
							<option value="2">上架</option>
							<option value="3">WORD</option>
							<option value="4">EXCEL</option>
						</select>
					</div>
				<label >分公司</label>&nbsp;&nbsp;<div class="ipt-select" style="display:inline">
						<i class="icon-arrow-down"></i>
						<select name="state" id="sstate" style="width:130px">
							<option value="">请选择</option>
							<option value="1">全部</option>
							<option value="2">上架</option>
							<option value="3">WORD</option>
							<option value="4">EXCEL</option>
						</select>
					</div>
				<label>部门</label>&nbsp;&nbsp;<div class="ipt-select" style="display:inline">
						<i class="icon-arrow-down"></i>
						<select name="state" id="sstate" style="width:130px">
							<option value="">请选择</option>
							<option value="1">全部</option>
							<option value="2">上架</option>
							<option value="3">WORD</option>
							<option value="4">EXCEL</option>
						</select>
					</div>
				</li> -->
			<div class="title" id="xianzhi">
			   <c:forEach items="${ccvo.planAuthorityList}" var="planAuthority" varStatus="extx">
			     <div style="display:none";>
    	         <input type="hidden" name="planAuthorityList[${extx.index}].authorityType" value="${planAuthority.authorityType}">
    	         <input type="hidden" name="planAuthorityList[${extx.index}].associateId" value="${planAuthority.associateId}">
			   </div>
			   </c:forEach>
			    <c:forEach items="${planAuthorityList}" var="planAuthority" varStatus="extx">
			     <div style="display:none";>
    	         <input type="hidden" name="planAuthorityList[${extx.index}].authorityType" value="${planAuthority.authorityType}">
    	         <input type="hidden" name="planAuthorityList[${extx.index}].associateId" value="${planAuthority.associateId}">
			     </div>
			   </c:forEach>
			</div>
				<!-- <li style="margin-left: -134px;width: 111%;margin-top: 5px;" class="col-md-3"><strong>人员限制：</strong><button onclick="choosePerson()" type="button" class="btn btn-submit overt_button">选择</button></li> -->
				<li style="margin-left: -134px;width: 111%;margin-top: 5px;" class="col-md-10"><strong>开放对象：</strong>
                    <input type="radio" id="openRange" name="offlineActivity.openRange" class="" value="0" checked/> 全部公开
                    <input type="radio" id="openRange" name="offlineActivity.openRange" class="" value="1"  onclick ="choosePerson()" <c:if test="${offlineActivity.openRange == 1 }">checked</c:if>/> 指定用户
                    <input type="radio" id="openRange" name="offlineActivity.openRange" class="" value="2" <c:if test="${offlineActivity.openRange == 2 }">checked</c:if>/> 游客及登录用户
                </li>
				<li style="margin-left: -134px;width: 111%;margin-top: 5px;" class="col-md-12 zlgl_share"><strong>计划奖励：</strong>
					<label for="integra">
						<input name="offlineActivity.isRewardPoint" <c:if test="${offlineActivity.isRewardPoint==1 }">checked</c:if> type="checkbox" value="1" id="integra" >可获积分&nbsp;
						<input type="text" name="offlineActivity.rewardPoint" class="ipt-text" value="${offlineActivity.rewardPoint }" onkeyup="value=value.replace(/[^\d.]/g,'')" style="max-width: 130px;">&nbsp;积分
					</label>
					
					 <label for="award">
						<input name="offlineActivity.isRewardCertification" value="1" type="checkbox" <c:if test="${offlineActivity.isRewardCertification==1 }">checked</c:if> id="award" >证书奖励&nbsp;
						<button type="button" class="btn btn-submit overt_button">&nbsp;选择
						</button>
					</label> 
					
					 <label for="qualify">
						<input name="offlineActivity.isRewardQualification" <c:if test="${offlineActivity.isRewardQualification==1 }">checked</c:if>  type="checkbox" value="1" id="qualify" >DMS资格&nbsp;
						<select class="dd">
							<option value="0">保险</option>
							<option value="1">技巧</option>
							<option value="2" selected="">产品</option>
							<option value="3">沟通</option>
						</select>
					</label> 
				</li>
			</ul>
			<div class="ui-button">
				<button type="button" class="btn btn-submit" onclick="save()">保存</button>
				<button type="button" onclick="cancle()" class="btn btn-default">取消</button>
			</div>
		</div>
		</form>
		
		<form id="bannerFile" style="display: none;" method="post" enctype="multipart/form-data">
            <input type="file" name="fileName" id="fileName" onchange="upload()" >  <!-- name值要填fileName -->
            <input name="category" type="hidden" id="category">
        </form>
       <script type="text/javascript" src="${ctx}/resources/libs/layer/layer.js" ></script>
	   <script type="text/javascript" src="${ctx}/resources/libs/datepicker/WdatePicker.js"></script>
	   <script type="text/javascript"	src="${ctx}/resources/libs/layer/layer.js"></script>
	   <script type="text/javascript" src="${ctx}/resources/js/common/jquery-form.js"></script>
	   <script type="text/javascript">
	   function choosePeo(){
			window.location.href="${ctx }/manage/offlineManage/skipPage?pageName=personnel_restrict"
		}
	   
	   function addPerson(authorityType,associateId,dh){
	    	var lh = $("#xianzhi").children('div').length;
	    	var html='<div style="display:none";>'+ 
	    	         '<input type="hidden" name="planAuthorityList['+lh+'].authorityType" value="'+authorityType+'">'+
	    	         '<input type="hidden" name="planAuthorityList['+lh+'].associateId" value="'+associateId+'">'+
	    	         '</div>';
	    	  $("#xianzhi").append(html);     
	    }
	   
	   function cancle(){
		   location.href="${ctx}/manage/offlineManage/planListByPage";
	   }
	   
	   //input多组实现一组的效果
	   	function check(obj){
	   		var parents=$("input[name$='signTimeType']");
	   		parents.each(function(indx,elem){
	   			if($(this).prop('checked')){
	   				var obs=$(this).parent().nextAll();
	   				obs.each(function(index,ele){
	   		   			if(index==0 && indx==1){
	   		   				$(ele).attr("name","offlineActivity.beforeBegin");
	   		   			}
	   		   			if(index==1 && indx==1){
	   		   				$(ele).attr("name","offlineActivity.afterBegin");
	   		   			}
	   		   			if(index==0 && indx==0){
	   		   			    $(ele).attr("name","offlineActivity.beforeBegin");
	   		   			}
	   		   			if(index==0 && indx==2){
	   		   		        $(ele).attr("name","offlineActivity.afterBegin");
	   		   			}
	   		   		});
	   			}else{
	   				var obs=$(this).parent().nextAll();
	   				obs.each(function(index,ele){
	   		   			if(index==0){
	   		   				$(ele).removeAttr("name");
	   		   			}
	   		   			if(index==1){
	   		   				$(ele).removeAttr("name");
	   		   			}
	   		   		});
	   			}
	   		})
	   	}
	   
	   //选择课程(线下课程)
	   function chooseCourse(){
		 var idList=$("#cId").val();
			var online=0;//course表中1代表线上
			  layer.open({
				  type: 2,
				  title: false,
				  closeBtn: 1, //不显示关闭按钮
				  shade: [0],
				  area: ['1000px', '500px'],
				  content: "${ctx}/manage/offlineManage/addCourse?online="+online+"&idList="+idList,
				  end: function(){
				  }
			   });
	   }
	   
	   function closeLayerdown(tagId,array){//注意一个线下活动只能关联一个课程
		   var courseId;
		   for(var i=0;i<array.length;i++){
			   courseId=array[i].id;
		   }
	        $("#cId").val(courseId);
	   }
	   //人员限制
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
       		  content: "${ctx}/manage/offlineManage/choosePerson?personIds="+personIds,
       		  end: function(){
       		  }
       	   });
       } 
	   //保存
	   function save(){
		  /*  startTime endTime enterStartDate enterEndDate */
		  var startTime=$("#startTime").val();//上课结束时间
		  var endTime=$("#endTime").val();//上课开始时间
		  var enterStartDate=$("#enterStartDate").val();//报名开始时间
		  var enterEndDate=$("#enterEndDate").val();//报名结束时间
		  var bannerPath=$("#bannerPath2").val();//线下活动的缩略图路径
		  var enrolment=$("#Enrolment").val();//报名人数限制
		  var aname= $("#name").val();//线下活动的名称
		  var adescription=$("#description").val();//线下活动的说明
		  var cId=$("#cId").val();//线下课程的id
		  if( cId==null || cId.trim()==''){
			  layer.msg('线下活动必须选择一个课程~');
			  return;
		  }
		  if(aname==null || aname.trim()==''){
			  layer.msg('线下活动的名称不能为空~');
			  return;
		  }
		  
		  if(adescription==null || adescription.trim()==''){
			  layer.msg('线下活动的说明不能为空~');
			  return;
		  }
		  if(bannerPath==null || bannerPath.trim()==''){
			  layer.msg('线下活动的书略图路径不能为空~');
			  return;
		  }
		  if(enterStartDate==null || enterStartDate==''){
			  layer.msg('线下活动的报名开始时间不能为空~');
			  return;
		  }
		  if(enterEndDate==null || enterEndDate==''){
			  layer.msg('线下活动的报名结束时间不能为空~');
			  return;
		  }
		  if(enterStartDate==null || enterStartDate==enterEndDate){
			  layer.msg("线下活动的报名开始时间不能等于结束时间~");
			  return;
		  }
		  if(enterEndDate==null || enterEndDate==startTime){
			  layer.msg("线下活动的报名结束时间不能等于课程的开始时间~");
			  return;
		  }
		  if(startTime==null || startTime==endTime){
			  layer.msg("课程的开始时间不能等于结束时间~");
			  return;
		  }
		  
		  if(startTime==null || startTime==''){
			   layer.msg('线下活动的开始时间不能为空~');
			   return;
		  } 
		  if(endTime==null || endTime==''){
			   layer.msg('线下活动的结束时间不能为空~');
			   return;
		  } 
		  if(enrolment==null || enrolment==''){
			   layer.msg('报名人数不能为空~');
			   return;
		  } 
		  layer.confirm('您是否确定保存该活动？', {
			  btn: ['是的','不了'] //按钮
			}, function(){
			 $("#form1").submit();
			}, function(){
			  layer.msg('没有保存', {
			    time: 2000, //2s后自动关闭
			  });
			});
	   }
	   
	   //上传
	   function upload(){
	         $("#bannerFile").ajaxSubmit({
	            url : '${ctx}/manage/commonUploadFile',
	            type :"post",
	            success : function(data){
	                /*     console.log(data); */
	                    if (data != null) {  //图片要回显
                            $("#form1 input[name='fileId']").val(data.id);
                            $("img[name='offlineActivity.bannerPath']").attr("src",data.path);
                            $("input#bannerPath2").val(data.path);
	                    }
	                },error:function() {
	                   layer.msg("上传错误");
	                } 
	            });  
	        }
	    
	    function changeC(category){  
	        $("input#category").val(category);  //附件表中定义的附件类型 
	        $("input#fileName").click();
	    }
	  
	   </script>

</body>
</html>