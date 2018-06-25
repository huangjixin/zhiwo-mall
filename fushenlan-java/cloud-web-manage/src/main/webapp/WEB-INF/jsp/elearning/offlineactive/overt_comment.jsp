<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<div class="form-detail">
			<div class="title"><strong>课程评论管理</strong></div>
			<ul class="edit clearfix">
				<li class="col-md-4"><strong>课程名称：</strong>一语定天下：现场品牌策划</li>
				<li class="col-md-4"><strong>课程编号：</strong>啦啦啦啦啦啦啦啦啦资料附件</li>
				<li class="col-md-4"><strong>课程讲师：</strong>保险</li>
				<li class="col-md-4"><strong>用户姓名：</strong>
					<input class="ipt-text proTime" type="text" placeholder="用户姓名">
				</li>
				<li class="col-md-6"><strong>评论内容：</strong>
					<input class="ipt-text " type="text" placeholder="">
				</li>
				<li class="col-md-6 sameWadte">
					<strong>评论时间：</strong>
					<input type="text" placeholder="开始时间" class="Wdate" style="max-width: 180px;" onfocus="WdatePicker({onpicked: function(){jQuery(this).trigger('change');},oncleared: function(){jQuery(this).trigger('change');}})">&nbsp;—&nbsp;
					<input type="text" placeholder="结束时间" class="Wdate" style="max-width: 180px;" onfocus="WdatePicker({onpicked: function(){jQuery(this).trigger('change');},oncleared: function(){jQuery(this).trigger('change');}})">
				</li>
				<li class="col-md-8 zlgl_share"><strong>评论状态：</strong>
					<label for="yes"><input type="radio" value="" id="yes" name="ques">隐藏
					</label>
					<label for="no"><input type="radio" value="" id="no" name="ques">开启
					</label>
				</li>
				<li class="col-md-4" style="text-align: right">
					<button type="button" class="btn btn-submit btn-radius btn-search"><i class="icon-search"></i> 查询</button>
				</li>
			</ul>
				<table class="table table-agents" style="margin-top: 20px">
					<thead>
						<tr>
						<th>
							<label class="pos-rel">
								<input type="checkbox" class="ace">
							</label>
						</th>
							<th>评论内容</th>
							<th>提交人</th>
							<th>提交时间</th>
							<th>评论状态</th>
							<th>点赞数</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>
								<label class="pos-rel">
									<input type="checkbox" class="ace">
								</label>
							</td>
							<td>1</td>
							<td>视频</td>
							<td>系统</td>
							<td>2017/12/2</td>
							<td>xxx部门</td>
							<td>
								<a class="color-detail" href="#" title="">开启</a>
							</td>
						</tr>
					</tbody>
				</table>
				<div class="table-paging clearfix">
					<button class="btn btn-submit" style="background: #f00">批量隐藏</button>
					<button class="btn btn-submit">批量开启</button>
					<p>
						<a class="first disabled" href="#"><i class="icon-first"></i></a>
						<a class="prev disabled" href="#"><i class="icon-arrow-left"></i></a>
						<a class="active" href="javascript:;">1</a>
						<a href="#">2</a>
						<a href="#">3</a>
						<a href="#">4</a>
						<a href="#">5</a>
						<a class="next" href="#"><i class="icon-arrow-right"></i></a>
						<a class="last" href="#"><i class="icon-last"></i></a>
					</p>
				</div>
			<div class="ui-button">
				<button type="button" class="btn btn-submit">保存</button>
				<button type="button" class="btn btn-default">取消</button>
			</div>
			   
		</div>
		<%--  <script src="${ctx}/resources/js/common/jquery-3.2.1.min.js"></script> --%>
     <%--  <script src="${ctx}/resources/js/elerning/common.js"></script>  --%>
       <script type="text/javascript" src="${ctx}/resources/libs/layer/layer.js" ></script>
	   <script type="text/javascript" src="${ctx}/resources/libs/datepicker/WdatePicker.js"></script>
	   <script type="text/javascript"></script>

</body>
</html>