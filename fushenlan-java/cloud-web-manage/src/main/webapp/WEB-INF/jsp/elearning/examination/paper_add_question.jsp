<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>

</head>
<body>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/style.css">
		
		<div class="form-detail">
			<div class="title"><strong>新增已有考题</strong></div>
			<div class="nav-tabs">
				<strong class="active">手工选题</strong>
				<strong>随机选题</strong>
			</div>
			
			<div class="tab-pane active">
		    <div class="filter-box">
			<ul class="ui-form grid-row">
				<li class="col-md-6">
					<strong style="text-align:left">试题关键字</strong>
					
						<input type="text" name="keyWord" id="keyWord" class="ipt-text" placeholder="请输入考题关键字">
				</li>
				<li class="col-md-6">
					<strong style="text-align:left">题型</strong>
					
						<i class="icon-arrow-down"></i>
						<select id="type" name="type" value="${type }">
						    <option value="">请选择</option>
							<option value="0" <c:if test="${type=='0'}">selected</c:if> >单选题</option>
							<option value="1" <c:if test="${type=='1'}">selected</c:if> >多选题</option>
							<option value="2" <c:if test="${type=='2'}">selected</c:if> >判断题</option>
							<option value="3" <c:if test="${type=='3'}">selected</c:if> >问答题</option>
						</select>
					
				</li>
				<li class="col-md-6">
					<strong style="text-align:left">二级分类</strong>
						<i class="icon-arrow-down"></i>
						<select id="tagId"  name="tagId"  value="${tagId }">
						    <option value="">请选择</option>
							<option value="0" <c:if test="${tagId=='0'}">selected</c:if> >视频</option>
							<option value="1" <c:if test="${tagId=='1'}">selected</c:if> >PPT</option>
							<option value="2" <c:if test="${tagId=='2'}">selected</c:if> >SCORM</option>
							<option value="3" <c:if test="${tagId=='3'}">selected</c:if> >WORD</option>
							<option value="4" <c:if test="${tagId=='4'}">selected</c:if> >EXCEL</option>
						</select>
				</li>
				<li class="col-md-6">
					<strong style="text-align:left">试题提交者</strong>
					
						<input type="text" name="createUser" id="createUser" class="ipt-text" placeholder="请输入讲师姓名">
					
				</li>
				<li class="col-md-12" style="text-align: right">
					<button  type="button" onclick="ftr()" class="btn btn-submit btn-radius "><i class="icon-search"></i> 查询</button>
				</li>
			</ul>
		</div>
				<table class="table table-agents">
			<thead>
				<tr>
					<th>
						<label class="pos-rel">
							<input type="checkbox" class="ace" id="selectAll">
						</label>
					</th>
					<th>考题内容</th>
					<th>考题类型</th>
					<th>提交者</th>
					<th>二级分类</th>
				</tr>
			</thead>
			<tbody id="ter">
				<c:forEach items="${qList}" var="question" >
                    <tr>
                      <td>
                       <!--  <label class="pos-rel"> -->
							<input type="checkbox" class="ace"  name="checkbox" value="${question.id }">
						<!-- </label> -->
                      </td>
					<td>${question.content }</td>
					<td>
					   <c:if test="${question.type==0}">单选题</c:if>
	                   <c:if test="${question.type==1}">多选题</c:if>
	                   <c:if test="${question.type==2}">判断题</c:if>
	                   <c:if test="${question.type==3}">问答题</c:if>
					</td>
					<td>${question.createUser }</td>
					<td>
					   <c:if test="${question.tagId==0}">视频</c:if>
	                   <c:if test="${question.tagId==1}">PPT</c:if>
	                   <c:if test="${question.tagId==2}">SCORM</c:if>
	                   <c:if test="${question.tagId==3}">WORD</c:if>
	                   <c:if test="${question.tagId==4}">EXCEL</c:if>
					</td>
				</tr>
                    </c:forEach>
			</tbody>
		</table>
			<div class="ui-button">
				<button type="button" class="btn btn-submit" onclick="createQuestion()">生成试题</button>
				<button type="button" class="btn btn-default">取消</button>
			</div>
			
			</div>
			<div class="tab-pane">
				<ul class=" clearfix">
					<li class="col-md-3">
						<strong>选择试题分类：</strong>
						<div class="ipt-select">
							<i class="icon-arrow-down"></i>
							<select id="tagId">
								<option value="0">视频</option>
								<option value="1">PPT</option>
								<option value="2" >SCORM</option>
								<option value="3">WORD</option>
								<option value="4">EXCEL</option>
							</select>
						</div>
					</li>
					<li class="col-md-12 form_learn" style="padding-left: 8rem">
						<strong>输入各类题型题数：</strong>
						<dl>
							<dt>单选题：</dt>
							<dd class="srsj_ipt">
								<input type="hidden" name="type_value" value="0">
								<input type="text" name="type_num" class="ipt-text" value="" style="width:25%" onkeyup="value=value.replace(/[^\d.]/g,'')"><b>题</b>
							</dd><br>
							<dt>多选题：</dt>
							<dd class="srsj_ipt">
								<input type="hidden" name="type_value" value="1">
								<input type="text" name="type_num"  class="ipt-text" value="" style="width:25%" onkeyup="value=value.replace(/[^\d.]/g,'')"><b>题</b>
							</dd><br>
							<dt>判断题：</dt>
							<dd class="srsj_ipt">
								<input type="hidden" name="type_value" value="2">
								<input type="text" name="type_num"  class="ipt-text" value="" style="width:25%" onkeyup="value=value.replace(/[^\d.]/g,'')"><b>题</b>
							</dd><br>
							<dt>问答题：</dt>
							<dd class="srsj_ipt">
								<input type="hidden" name="type_value" value="3">
								<input type="text" name="type_num"  class="ipt-text" value="" style="width:25%" onkeyup="value=value.replace(/[^\d.]/g,'')"><b>题</b>
							</dd>
						</dl>
					</li>
				</ul>
				<div class="ui-button">
				<button type="button" class="btn btn-submit" onclick="randomQuestion()">生成试题</button>
				<button type="button" class="btn btn-default" onclick="">取消</button>
			</div>
			</div>
			
		</div>
		


<script src="${ctx}/resources/js/common/jquery-3.2.1.min.js"></script>


<script type="text/javascript">


 function ftr(){
	var type = $("#type").val();
	var tagId = $("#tagId").val();
	var keyWord = $("#keyWord").val();
	var createUser = $("#createUser").val();
	 $.ajax({
		  async : false,  
	      cache : false,  
	      type: 'POST',
   	      url: "${ctx }/manage/paper/GetPrivateOtherPaper",
   	      data: {"type" : type,"tagId":tagId,"keyWord":keyWord,"createUser":createUser},
   	      dataType: "json",
   	      success: function(data){
   		  $("#ter").children().remove();
   		  if(data.data.length==0){
   			  layer.msg("没有数据");
   		  }else{
   			  $.each(data.data,function(i,item){
   				  var aa ="";
   				  var bb ="";
   				  if(item.type==0){
   					  aa="单选题";
   				  }else if(item.type==1){
   					  aa="多选题";
   				  }else if(item.type==2){
   					  aa="判断题";
   				  }else if(item.type==3){
   					  aa="问答题";
   				  }
   				  if(item.tagId==0){
   					  bb="视频";
   				  }else if(item.tagId==1){
 					  bb="PPT";
 				  }else if(item.tagId==2){
 					  bb="SCORM";
 				  }else if(item.tagId==3){
 					  bb="WORD";
 				  }else if(item.tagId==4){
 					  bb="EXCEL";
 				  }
   			  $("#ter").append('<tr><td><label class="pos-rel"><input type="checkbox" class="ace" value='+item.id+'></label></td><td>'+item.content+'</td><td>'+aa+'</td><td>'+item.createUser+'</td><td>'+bb+'</td></tr>');
   		    })
   		  }
   	   }
   	}) 
 }

$("#selectAll").click(function(){//给全选按钮加上点击事件
    var xz = $(this).prop("checked");//判断全选按钮的选中状态
    var ck = $(".ace").prop("checked",xz);  //让class名为ace的选项的选中状态和全选按钮的选中状态一致。  
    })


$(function(){
	$('.nav-tabs').each(function(index, element) {
		var _obj = $(this);
		$(this).on('click', 'strong:not(.active)', function(){
			$(this).addClass('active').siblings('strong').removeClass('active');
			_obj.nextAll('.tab-pane').removeClass('active').eq($(this).index()).addClass('active')
		})
	});
	
});


	function createQuestion(){
		$("#ter :input[type='checkbox']").each(function(i){
			if(this.checked==true){
				var id  = this.value;
				var content =$(this).parent().siblings().eq(0).text();
				var type =$(this).parent().siblings().eq(1).text();
				parent.addStudy(id,content,type);
				var index=parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
			}
		});
		/*  var ids = new Array();
		 var contents = new Array();
		 var types=new Array();
		 var groupCheckbox=$("input[name='checkbox']:checked");
	     for(i=0;i<groupCheckbox.length;i++){
	        if(groupCheckbox.eq(i).is(":checked")){
	        	var content=groupCheckbox.eq(i).parent().parent().siblings().find(".clickable").text();
	        	var type=groupCheckbox.eq(i).parent().parent().siblings().find(".questionType").text();
	    	ids[i] =groupCheckbox[i].value;
	    	contents[i] =content;
	    	types[i]=type;
	        }
	    }
	     for(var i=0;i<ids.length;i++){
	    	 var id=ids[i];
	    	 var content=contents[i];
	    	 var type=types[i];
	    	 parent.addStudy(id,content,type,i);
	     }
		
		
		var index=parent.layer.getFrameIndex(window.name);
		parent.layer.close(index); */
	}
	
	function randomQuestion(){
		 var typeValue=$("input[name='type_value']");
		 var typeNum=$("input[name='type_num']");
		 var ids = new Array();
		 var nums = new Array();
		 for(i=0;i<typeValue.length;i++){
		    	ids[i] =typeValue[i].value;
		    }
		 for(i=0;i<typeNum.length;i++){
			 nums[i] =typeNum[i].value;
		    }
		 
		 var tagId=$("#tagId").val();
		 
		 $.ajax({
				type : "post",
				data:{"ids":ids,"nums":nums,"tagId":tagId},
				url : "${ctx}/manage/paper/randomQuestion",
				dateType: "json",
				success : function(response) {
					for(i=0;i<response.data.length;i++){
						 var id=response.data[i].id;
				    	 var content=response.data[i].content;
				    	 var type=response.data[i].type;
				    	 parent.addStudy(id,content,type,i);
				    	 var index=parent.layer.getFrameIndex(window.name);
				 		parent.layer.close(index);
					}
				}
			});
	}
	
</script>
</body>
</html>
