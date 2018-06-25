<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/style.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/font/iconfont.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/page.css">
</head>
<body>
<script src="${ctx}/resources/js/common/jquery-3.2.1.min.js"></script>
<script src="${ctx}/resources/js/common.js"></script>
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
				
					<c:forEach items="${elPaperVo.questionVo}" var="questionVo" >
					<c:if test="${questionVo.type==0 }" >
					
					<!-- 单选题 -->
				<div class="ggsi_single">
					<div class="title"><strong>单选题</strong></div>
					<ul class="clearfix ggsi_view">
						<li class="col-md-12">
							<div class="search_result">
								<div class="single_choice">
									<div class="subject clearfix">
										<h5><span>1.</span>${questionVo.content }</h5>
										<i>本题${questionVo.questionScore }分</i>
									</div>
									<ol>
									<c:forEach items="${questionVo.questionAnswer}" var="questionAnswer" >
									
										<li>A.${questionAnswer.answer }</li>
										
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
										<h5><span>1.</span>${questionVo.content }</h5>
										<i>本题${questionVo.questionScore }分</i>
									</div>
									<ol>
										<c:forEach items="${questionVo.questionAnswer}" var="questionAnswer" >
									
										<li>A.${questionAnswer.answer }</li>
										
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
										<h5><span>1.</span>${questionVo.content }</h5>
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
										<h5><span>1.</span>${questionVo.content }</h5>
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
					<li class="col-md-3"><strong>用户姓名：</strong><input type="text" class="ipt-text" value="用户姓名"></li>
					<li class="col-md-3">
						<strong>用户职称：</strong>
						<div class="ipt-select">
							<i class="icon-arrow-down"></i>
							<select>
								<option value="0">全部</option>
								<option value="1">初审面试</option>
								<option value="2" selected="">甄选面试</option>
								<option value="3">决定面试</option>
							</select>
						</div>
					</li>
					<li class="col-md-3">
						<strong>所属总公司：</strong>
						<div class="ipt-select">
							<i class="icon-arrow-down"></i>
							<select>
								<option value="0">全部</option>
								<option value="1">初审面试</option>
								<option value="2" selected="">甄选面试</option>
								<option value="3">决定面试</option>
							</select>
						</div>
					</li>
					<li class="col-md-3">
						<strong>所属分公司：</strong>
						<div class="ipt-select">
							<i class="icon-arrow-down"></i>
							<select>
								<option value="0">全部</option>
								<option value="1">初审面试</option>
								<option value="2" selected="">甄选面试</option>
								<option value="3">决定面试</option>
							</select>
						</div>
					</li>
					<li class="col-md-3">
						<strong>所属部门：</strong>
						<div class="ipt-select">
							<i class="icon-arrow-down"></i>
							<select>
								<option value="0">全部</option>
								<option value="1">初审面试</option>
								<option value="2" selected="">甄选面试</option>
								<option value="3">决定面试</option>
							</select>
						</div>
					</li>
					<li class="col-md-4 col-btn" style="text-align: right"> 
					<button type="button" class="btn btn-submit btn-radius btn-search"><i class="icon-search"></i> 查询</button>
					</li>
				</ul>
				<div class="fiter">
					<label for="yes"><input type="radio" value="" id="yes" name="share">已用过
					</label>
					<label for="no"><input type="radio" value="" id="no" name="share">未通过
					</label>
				</div>
				<table class="table table-agents">
					<thead>
						<tr>
							<th>用户名</th>
							<th>所属总公司</th>
							<th>所属分公司</th>
							<th>部门</th>
							<th>职级</th>
							<th>考试得分</th>
							<th>是否通过</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>1</td>
							<td>视频</td>
							<td>系统</td>
							<td>2017/12/2</td>
							<td>xxx部门</td>
							<td>职业规范</td>
							<td>xxx部门</td>
							<td>
								<a class="color-detail" href="./yhsj_view.html" title="查看">查看</a>
							</td>
						</tr>
						<tr>
							<td>1</td>
							<td>视频</td>
							<td>系统</td>
							<td>2017/12/2</td>
							<td>xxx部门</td>
							<td>职业规范</td>
							<td>职业规范</td>
							<td>
								<a class="color-detail" href="./yhsj_view.html" title="查看">查看</a>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="ui-button">
			</div>
			
		
	
</div>
<!-- E Wrapper -->

	<script src="${ctx}/resources/js/common/jquery-3.2.1.min.js"></script>
		
    <script type="text/javascript">
    
    $('.nav-tabs').each(function(index, element) {
		var _obj = $(this);
		$(this).on('click', 'strong:not(.active)', function(){
			$(this).addClass('active').siblings('strong').removeClass('active');
			_obj.nextAll('.tab-pane').removeClass('active').eq($(this).index()).addClass('active')
		})
	});
			
    
    function cancle(){
    	var id="${id}";
    	
    	window.location.href = "${ctx}/manage/development/selectDeveById?id="+id;
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
	
    	

</script>
</body>
</html>
