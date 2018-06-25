<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/elerning/style.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/elerning/font/iconfont.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/elerning/page.css">
</head>
<body>

<style type="text/css">

.analysis_sin{
 display: none;
 
} 
.MultiSelect{
 display: none;
 
} 
.judge{
 display: none;
 
} 
.answers{
 display: none;
 
} 


</style>
		<input type="hidden" id="pId" value="${id}"/>
		<div class="form-detail">
		<div class="title"><strong>查看试卷</strong></div>
			<div class="nav-tabs">
				<strong class="active">试卷信息</strong>
				<strong>学员考试情况</strong>
			</div>
			<!-- 试卷信息 -->
			<div class="tab-pane active">
				<div class="header-bars">
					<h4>${elPaperVo.name} </h4>
					<ul class="clearfix">
						<li>满分：${elPaperVo.fullMark}分</li>
						<li>通过分数：${elPaperVo.passMark}分</li>
						<li>答题时间：${elPaperVo.examTime}分钟</li>
					</ul>
				</div>
				
					<c:forEach items="${elPaperVo.questionVo}" var="questionVo"  varStatus="ext">
					<c:if test="${questionVo.type==0 }" >
					
					<!-- 单选题 -->
				<div class="ggsi_single">
					<div class="title"><strong>单选题</strong></div>
					<ul class="clearfix ggsi_view">
						<li class="col-md-12">
							<div class="search_result">
								<div class="single_choice">
									<div class="subject clearfix">
										<h5><span>${ext.index+1 }.</span>${questionVo.content }</h5>
										<i>本题${questionVo.questionScore }分</i>
									</div>
									<ol>
									<c:forEach items="${questionVo.questionAnswer}" var="questionAnswer" varStatus="te" >
									
										<li>${questionAnswer.option }.${questionAnswer.answer }</li>
										
										</c:forEach>
									</ol>
								</div>
								<button class="open_single" onclick="openSin()">展开解析</button>
								
								<div class="analysis_sin" >
								  <input  name="analysis" id="analysis" readonly="readonly"   style="border:none;" value="${questionVo.analysis}" >
							    </div>
						
							</div>
						</li>
					</ul>
				</div>
					
					</c:if>
					
					
					
					<c:if test="${questionVo.type==1 }" >
					
			
				<!-- 多选题 -->
				<div class="ggsi_single">
					<div class="title"><strong>多选题</strong></div>
					<ul class="clearfix ggsi_view">
						<li class="col-md-12">
							<div class="search_result">
								<div class="single_choice">
									<div class="subject clearfix">
										<h5><span>${ext.index+1 }.</span>${questionVo.content }</h5>
										<i>本题${questionVo.questionScore }分</i>
									</div>
									<ol>
										<c:forEach items="${questionVo.questionAnswer}" var="questionAnswer"  varStatus="sin">
									
										<li>${questionAnswer.option }.${questionAnswer.answer }</li>
										
										</c:forEach>
									</ol>
								</div>
								<button class="open_single" onclick="MultiSelect()">展开解析</button>
								<div class="MultiSelect" >
								  <input  name="analysis"   style="border:none;" value="${questionVo.analysis}" >
							    </div>
							</div>
						</li>
					</ul>
				</div>
					
					</c:if>
					
					
							<c:if test="${questionVo.type==2 }" >
					
			<!-- 判断题 -->
				<div class="ggsi_single">
					<div class="title"><strong>判断题</strong></div>
					<ul class="clearfix ggsi_view">
						<li class="col-md-12">
							<div class="search_result">
								<div class="single_choice">
									<div class="subject clearfix">
										<h5><span>${ext.index+1 }.</span>${questionVo.content }</h5>
										<i>本题${questionVo.questionScore}分</i>
									</div>
								</div>
								<button class="open_single" onclick="judge()">展开解析</button>
								<div class="judge" >
								  <input  name="analysis"   style="border:none;" value="${questionVo.analysis}" >
							    </div>
							</div>
						</li>
					</ul>
				</div>
					
					</c:if>
					
									<c:if test="${questionVo.type==3 }" >
				
				<!-- 问答题 -->
				<div class="ggsi_single">
					<div class="title"><strong>问答题</strong></div>
					<ul class="clearfix ggsi_view">
						<li class="col-md-12">
							<div class="search_result">
								<div class="single_choice">
									<div class="subject clearfix">
										<h5><span>${ext.index+1 }.</span>${questionVo.content }</h5>
										<i>本题${questionVo.questionScore}分</i>
									</div>
								</div>
								<button class="open_single" onclick="answers()">展开解析</button>
								<div class="answers" >
								  <input  name="analysis"   style="border:none;" value="${questionVo.analysis}" >
							    </div>
							</div>
						</li>
					</ul>
				</div>
					
					</c:if>
					
					
                
                    </c:forEach>
				
				
			
				<div class="title"><strong>关联信息</strong></div>
				<ul class="clearfix">
					<li class="col-md-6"><strong>关联计划：</strong>
						<span>${elPaperVo.isNotRelation}</span>
					</li>
					<li class="col-md-6">
							<strong>是否分享：</strong>
							<span>${elPaperVo.isNotShare}</span>
					</li>
				</ul>
			</div>
			<!-- 学员考试情况 -->
			<div class="tab-pane">
				<ul class="edit clearfix online_form">
					<li class="col-md-3"><strong>用户姓名：</strong><input type="text" id="userName" class="ipt-text"></li>
					<li class="col-md-4 col-btn" style="text-align: right"> 
					<button type="button" onclick="check()" class="btn btn-submit btn-radius btn-search"><i class="icon-search"></i> 查询</button>
					</li>
				</ul>
				<table class="table table-agents">
					<thead>
						<tr>
							<th>用户名</th>
							<th>所属公司</th>
							<th>职级</th>
							<th>考试得分</th>
							<th>是否通过</th>
							<th>操作</th>
						</tr>
					</thead>
			<tbody id="ter">
			<c:if test="${not empty eList}">
	           <c:forEach var="eVo" items="${eList}">
				 <tr>
					<td>${eVo.userName }</td>
					<td>${eVo.cnName }</td>
					<td>
					   <c:if test="${eVo.postType==1}">总监</c:if>
	                   <c:if test="${eVo.postType==2}">主管</c:if>
	                   <c:if test="${eVo.postType==3}">代理人</c:if>
	                   <c:if test="${eVo.postType==4}">内勤</c:if>
					</td>
					<td>${eVo.score }</td>
					<td>
					   <c:if test="${eVo.pass==0}">通过</c:if>
	                   <c:if test="${eVo.pass==1}">未通过</c:if>
	                </td>
					<td><button class="color-detail" onclick="checkInfo('${eVo.userName}')" >查看</button></td>
				</tr>
				</c:forEach>
	          </c:if>
			</tbody>
				</table>
			</div>
			<div class="ui-button">
				<button type="button" class="btn btn-submit" onclick="cancle()">返回</button>
			</div>
			
		
	
</div>

		
    <script type="text/javascript">
    
    function checkInfo(userName){
    	location.href="${ctx}/manage/paper/checkInfo?userName="+userName;
    }
    
    $('.nav-tabs').each(function(index, element) {
		var _obj = $(this);
		$(this).on('click', 'strong:not(.active)', function(){
			$(this).addClass('active').siblings('strong').removeClass('active');
			_obj.nextAll('.tab-pane').removeClass('active').eq($(this).index()).addClass('active')
		})
	});
			
    
    function cancle(){
    	location.href="${ctx}/manage/paper/GetPublicPaper";
    }
    
			function openSin(){
				var display =$('.analysis_sin').css('display');
				if(display == 'none'){
					$(".analysis_sin").show();
				}else{
					$(".analysis_sin").hide();
				}
				
			}
			
			function MultiSelect(){
				var display =$('.MultiSelect').css('display');
				if(display == 'none'){
					$(".MultiSelect").show();
				}else{
					$(".MultiSelect").hide();
				}
				
			}
			
			function answers(){
				var display =$('.answers').css('display');
				if(display == 'none'){
					$(".answers").show();
				}else{
					$(".answers").hide();
				}
				
			}
			
			function judge(){
				var display =$('.judge').css('display');
				if(display == 'none'){
					$(".judge").show();
				}else{
					$(".judge").hide();
				}
				
			}
	function check(){
		var userName = $("#userName").val();
		var id=$("#pId").val();
		 $.ajax({
			  async : false,  
		      cache : false,  
		      type: 'POST',
	    	  url: "${ctx}/manage/paper/selectOtherPaper",
	    	  data: {"userName" : userName,"id":id},
	    	  dataType: "json",
	    	  success: function(data){
	    		  $("#ter").children().remove();
	    		  if(data.data.length==0){
	    			  layer.msg("没有数据");
	    		  }else{
	    			  $.each(data.data,function(i,item){
	    				  var aa ="";
	    				  var cc="";
	    				  var dd="";
	    				  var ee="";
	    				  if(item.postType==1){
	       					  aa="总监";
	       				  }else if(item.postType==2){
	       					  aa="主管";
	       				  }else if(item.postType==3){
	       					  aa="代理人";
	       				  }else if(item.postType==4){
	       					  aa="内勤";
	       				  }
	    				  if(item.pass==0){
	       					  cc="通过";
	       				  }else if(item.pass==1){
	       					  cc="未通过";
	       				  }
	    				  if(item.cnName==null ||item.cnName==""){
	    					  dd=""
	    				  }else{
	    					  dd=item.cnName;
	    				  }
	    			  $("#ter").append('<tr><td>'+item.userName+'</td><td>'+dd+'</td><td>'+aa+'</td><td>'+item.score+'</td><td>'+cc+'</td><td><button class="color-detail"onclick="checkInfo('+item.userName+')" >查看</button></td></tr>');
	    		    })
	    		  }
	    	   }
	    	}); 
	}
    	

</script>
</body>
</html>
