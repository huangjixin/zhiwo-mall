<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<div class="form-detail">
		<div class="title"><strong>人员限制</strong></div>
			<div class="nav-tabs">
				<strong class="active">组织架构</strong>
				<strong class="">指定人群</strong>
				<strong class="">上传名单</strong>
			</div>
			<!-- 组织架构 -->
			<div class="tab-pane clearfix active">
				<div class="organize_left col-md-6 clearfix">
					<ul id="treeDemo" class="ztree"><li id="treeDemo_1" class="level0" tabindex="0" hidefocus="true" treenode=""><span id="treeDemo_1_switch" title="" class="button level0 switch roots_open" treenode_switch=""></span><span id="treeDemo_1_check" class="button chk checkbox_false_full" treenode_check=""></span><a id="treeDemo_1_a" class="level0" treenode_a="" onclick="" target="_blank" style="" title="test1"><span id="treeDemo_1_ico" title="" treenode_ico="" class="button ico_open" style="width:0px;height:0px;"></span><span id="treeDemo_1_span" class="node_name">test1</span></a><ul id="treeDemo_1_ul" class="level0 line" style="display:block"><li id="treeDemo_2" class="level1" tabindex="0" hidefocus="true" treenode=""><span id="treeDemo_2_switch" title="" class="button level1 switch center_docu" treenode_switch=""></span><span id="treeDemo_2_check" class="button chk checkbox_false_full" treenode_check=""></span><a id="treeDemo_2_a" class="level1" treenode_a="" onclick="" target="_blank" style="" title="test1_1"><span id="treeDemo_2_ico" title="" treenode_ico="" class="button ico_docu" style="width:0px;height:0px;"></span><span id="treeDemo_2_span" class="node_name">test1_1</span></a></li><li id="treeDemo_3" class="level1" tabindex="0" hidefocus="true" treenode=""><span id="treeDemo_3_switch" title="" class="button level1 switch bottom_docu" treenode_switch=""></span><span id="treeDemo_3_check" class="button chk checkbox_false_full" treenode_check=""></span><a id="treeDemo_3_a" class="level1" treenode_a="" onclick="" target="_blank" style="" title="test1_2"><span id="treeDemo_3_ico" title="" treenode_ico="" class="button ico_docu" style="width:0px;height:0px;"></span><span id="treeDemo_3_span" class="node_name">test1_2</span></a></li></ul></li><li id="treeDemo_4" class="level0" tabindex="0" hidefocus="true" treenode=""><span id="treeDemo_4_switch" title="" class="button level0 switch bottom_open" treenode_switch=""></span><span id="treeDemo_4_check" class="button chk checkbox_false_full" treenode_check=""></span><a id="treeDemo_4_a" class="level0" treenode_a="" onclick="" target="_blank" style="" title="test2"><span id="treeDemo_4_ico" title="" treenode_ico="" class="button ico_open" style="width:0px;height:0px;"></span><span id="treeDemo_4_span" class="node_name">test2</span></a><ul id="treeDemo_4_ul" class="level0 " style="display:block"><li id="treeDemo_5" class="level1" tabindex="0" hidefocus="true" treenode=""><span id="treeDemo_5_switch" title="" class="button level1 switch center_docu" treenode_switch=""></span><span id="treeDemo_5_check" class="button chk checkbox_false_full" treenode_check=""></span><a id="treeDemo_5_a" class="level1" treenode_a="" onclick="" target="_blank" style="" title="test2_1"><span id="treeDemo_5_ico" title="" treenode_ico="" class="button ico_docu" style="width:0px;height:0px;"></span><span id="treeDemo_5_span" class="node_name">test2_1</span></a></li><li id="treeDemo_6" class="level1" tabindex="0" hidefocus="true" treenode=""><span id="treeDemo_6_switch" title="" class="button level1 switch bottom_docu" treenode_switch=""></span><span id="treeDemo_6_check" class="button chk checkbox_false_full" treenode_check=""></span><a id="treeDemo_6_a" class="level1" treenode_a="" onclick="" target="_blank" style="" title="test2_2"><span id="treeDemo_6_ico" title="" treenode_ico="" class="button ico_docu" style="width:0px;height:0px;"></span><span id="treeDemo_6_span" class="node_name">test2_2</span></a></li></ul></li></ul>
				</div>
				<div class="organize_right col-md-6 clearfix">
					<ol>
						<li>XXXX人</li>
						<li>XXXX人</li>
						<li>XXXX人</li>
						<li>XXXX人</li>
						<li>XXXX人</li>
					</ol>
				</div>
			</div>
			<!-- 指定人群 -->
			<div class="tab-pane clearfix">
				<div class="title"><strong>查询条件</strong></div>	
				<ul class="edit clearfix online_form">
					<li class="col-md-3"><strong>学员姓名：</strong>
						<input class="ipt-text proTime" type="text" placeholder="学员姓名">
					</li>
					<li class="col-md-3"><strong>学员工号：</strong>
						<input class="ipt-text proTime" type="text" placeholder="学员姓名">
					</li>
					<li class="col-md-3">
						<strong>所属总公司：</strong>
						<div class="ipt-select">
							<i class="icon-arrow-down"></i>
							<select>
								<option selected="" value="0">视频</option>
								<option value="1">PPT</option>
								<option value="2">SCORM</option>
								<option value="3">WORD</option>
								<option value="3">EXCEL</option>
							</select>
						</div>
					</li>
					<li class="col-md-3">
						<strong>所属分公司：</strong>
						<div class="ipt-select">
							<i class="icon-arrow-down"></i>
							<select>
								<option selected="" value="0">视频</option>
								<option value="1">PPT</option>
								<option value="2">SCORM</option>
								<option value="3">WORD</option>
								<option value="3">EXCEL</option>
							</select>
						</div>
					</li>
					<li class="col-md-3">
						<strong>所属部门：</strong>
						<div class="ipt-select">
							<i class="icon-arrow-down"></i>
							<select>
								<option selected="" value="0">视频</option>
								<option value="1">PPT</option>
								<option value="2">SCORM</option>
								<option value="3">WORD</option>
								<option value="3">EXCEL</option>
							</select>
						</div>
					</li>
					<li class="col-md-4" style="text-align: right">
						<button type="button" class="btn btn-submit btn-radius btn-search"><i class="icon-search"></i> 查询</button>
					</li>
				</ul>
				<div class="title"><strong>查询结果</strong></div>
				<div class="organize_left col-md-6 clearfix">
					<ul id="treeMenus" class="ztree"><li id="treeMenus_1" class="level0" tabindex="0" hidefocus="true" treenode=""><span id="treeMenus_1_switch" title="" class="button level0 switch roots_open" treenode_switch=""></span><span id="treeMenus_1_check" class="button chk checkbox_false_full" treenode_check=""></span><a id="treeMenus_1_a" class="level0" treenode_a="" onclick="" target="_blank" style="" title="test1"><span id="treeMenus_1_ico" title="" treenode_ico="" class="button ico_open" style="width:0px;height:0px;"></span><span id="treeMenus_1_span" class="node_name">test1</span></a><ul id="treeMenus_1_ul" class="level0 line" style="display:block"><li id="treeMenus_2" class="level1" tabindex="0" hidefocus="true" treenode=""><span id="treeMenus_2_switch" title="" class="button level1 switch center_docu" treenode_switch=""></span><span id="treeMenus_2_check" class="button chk checkbox_false_full" treenode_check=""></span><a id="treeMenus_2_a" class="level1" treenode_a="" onclick="" target="_blank" style="" title="test1_1"><span id="treeMenus_2_ico" title="" treenode_ico="" class="button ico_docu" style="width:0px;height:0px;"></span><span id="treeMenus_2_span" class="node_name">test1_1</span></a></li><li id="treeMenus_3" class="level1" tabindex="0" hidefocus="true" treenode=""><span id="treeMenus_3_switch" title="" class="button level1 switch bottom_docu" treenode_switch=""></span><span id="treeMenus_3_check" class="button chk checkbox_false_full" treenode_check=""></span><a id="treeMenus_3_a" class="level1" treenode_a="" onclick="" target="_blank" style="" title="test1_2"><span id="treeMenus_3_ico" title="" treenode_ico="" class="button ico_docu" style="width:0px;height:0px;"></span><span id="treeMenus_3_span" class="node_name">test1_2</span></a></li></ul></li><li id="treeMenus_4" class="level0" tabindex="0" hidefocus="true" treenode=""><span id="treeMenus_4_switch" title="" class="button level0 switch bottom_open" treenode_switch=""></span><span id="treeMenus_4_check" class="button chk checkbox_false_full" treenode_check=""></span><a id="treeMenus_4_a" class="level0" treenode_a="" onclick="" target="_blank" style="" title="test2"><span id="treeMenus_4_ico" title="" treenode_ico="" class="button ico_open" style="width:0px;height:0px;"></span><span id="treeMenus_4_span" class="node_name">test2</span></a><ul id="treeMenus_4_ul" class="level0 " style="display:block"><li id="treeMenus_5" class="level1" tabindex="0" hidefocus="true" treenode=""><span id="treeMenus_5_switch" title="" class="button level1 switch center_docu" treenode_switch=""></span><span id="treeMenus_5_check" class="button chk checkbox_false_full" treenode_check=""></span><a id="treeMenus_5_a" class="level1" treenode_a="" onclick="" target="_blank" style="" title="test2_1"><span id="treeMenus_5_ico" title="" treenode_ico="" class="button ico_docu" style="width:0px;height:0px;"></span><span id="treeMenus_5_span" class="node_name">test2_1</span></a></li><li id="treeMenus_6" class="level1" tabindex="0" hidefocus="true" treenode=""><span id="treeMenus_6_switch" title="" class="button level1 switch bottom_docu" treenode_switch=""></span><span id="treeMenus_6_check" class="button chk checkbox_false_full" treenode_check=""></span><a id="treeMenus_6_a" class="level1" treenode_a="" onclick="" target="_blank" style="" title="test2_2"><span id="treeMenus_6_ico" title="" treenode_ico="" class="button ico_docu" style="width:0px;height:0px;"></span><span id="treeMenus_6_span" class="node_name">test2_2</span></a></li></ul></li></ul>
				</div>
				<div class="organize_right col-md-6 clearfix">
					<ol>
						<li>XXXX人</li>
						<li>XXXX人</li>
						<li>XXXX人</li>
						<li>XXXX人</li>
						<li>XXXX人</li>
					</ol>
				</div>
			</div>
			<!-- 上传名单 -->
			<div class="tab-pane clearfix">
				<div class="organize_right col-md-6 clearfix" style="border: 0;">
					<ol>
						<li>XXXX人</li>
						<li>XXXX人</li>
						<li>XXXX人</li>
						<li>XXXX人</li>
						<li>XXXX人</li>
					</ol>
				</div>
				<div class="organize_right col-md-6 clearfix">
					<ol>
						<li>XXXX人</li>
						<li>XXXX人</li>
						<li>XXXX人</li>
						<li>XXXX人</li>
						<li>XXXX人</li>
					</ol>
				</div>
			</div>
			<div class="ui-button">
				<button type="button" class="btn btn-submit">保存</button>
				<button type="button" class="btn btn-default">取消</button>
			</div>
			
		</div>
		
	<%-- <script src="${ctx}/resources/js/common/jquery-3.2.1.min.js"></script> --%>
  <%--   <script src="${ctx}/resources/js/elerning/common.js"></script>  --%>

</body>
</html>