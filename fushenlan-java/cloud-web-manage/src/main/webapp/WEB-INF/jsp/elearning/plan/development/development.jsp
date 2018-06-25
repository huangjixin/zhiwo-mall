<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">




		<!-- S Filter Box -->
		<div class="filter-box">
<style type="text/css">
	.squre_btn,.squreBtn{
		border:1px solid #999;
		border-radius: 50%;
		width: 50px;
		height: 50px;
		position: relative;
	}
	.squreBtn{
	    position: absolute;
		top: 116px;
		left: 90px;
    	transform:rotate(90deg);
		-ms-transform:rotate(90deg); 	/* IE 9 */
		-moz-transform:rotate(90deg); 	/* Firefox */
		-webkit-transform:rotate(90deg); /* Safari 和 Chrome */
		-o-transform:rotate(90deg); 
	}
	.round{
		margin: 0 auto;
		    width:600px;
		    position: relative;
	}
	.round li{
		position: relative;
		    height: 226px;
	}
	.quaters_left{
		float: left;
	}
	.quaters_right{
		float: right;
	}
	.quaters_right .line_btn{
		transform:rotate(45deg);
		-ms-transform:rotate(45deg); 	/* IE 9 */
		-moz-transform:rotate(45deg); 	/* Firefox */
		-webkit-transform:rotate(45deg); /* Safari 和 Chrome */
		-o-transform:rotate(45deg); 
	    top: 35px;
    	left: -15px;

	}
	.quaters_right .squreBtn{
    	top: 120px;
    	left: -75px;
	}
	.quaters_right .squreBtn .line_btn{
		top: -79px;
    	left: 73px;
	}
	.line_btn{
		width: 1px;
		height: 100px;
		background: #999;
		display: inline-block;
		transform:rotate(-45deg);
		-ms-transform:rotate(-45deg); 	/* IE 9 */
		-moz-transform:rotate(-45deg); 	/* Firefox */
		-webkit-transform:rotate(-45deg); /* Safari 和 Chrome */
		-o-transform:rotate(-45deg); 
		position: absolute;
		top: 30px;
		left: 74px;
	}
	.quaters_text{
	    position: absolute;
	    top: 10px;
	    left: 70px;
	}
	.squreBtn .quaters_text{
	    top: -45px;
    	transform: rotate(-90deg);
    	left: 15px;
	}
	.round .btn_quater{
		min-width: 200px;
		min-height: 40px;
		position: absolute;
		bottom:50px;
	    left: 300px;
    	margin-left: -100px;
	}
</style>
			<div class="round clearfix">
				<ol class="quaters_left">
					<div class="title" id="adminDevelopment"><strong>营销晋升路线</strong></div>
					
					
					
					
					
					
					<!-- 
					for(i i++){
					if(i%2==0){
					 html +=<li style="list-style-type:none;">
					}
					if(i%2==0){
							 html +=<button class="squre_btn"  onClick="views()">
								<span class="line_btn"></span>
								<b class="quaters_text">L1</b>
							</button>
					}else{
						 html +=	<button class="squreBtn" onClick="views()">
							<input type="hidden" name="field＿name" id="deveId2" value="">
								<span class="line_btn" id="deve2"></span>
								<b class="quaters_text">L2</b>
							</button>
					}		
					if(i%2==0){
					</li>
					}
					</li> -->

					<!-- <li style="list-style-type:none;">
						<button class="squre_btn" onClick="views()">
						<input type="hidden" name="field＿name" id="deveId3" value="">
							<span class="line_btn" id="deve3" style="height: 200px;top: -2px;left: 111px;transform: rotate(-60deg);"></span>
							<b class="quaters_text">L3</b>
						</button>
					</li> -->
				</ol>
				<ol class="quaters_right">
					<div class="title"><strong>管理晋升路线</strong></div>
					
				</ol>
				<button class="btn btn-default btn_quater" style="bottom:0" onclick='views(${lev.id},"${lev.levelName }")'>${lev.levelName }</button>
				<button class="btn btn-default btn_quater">LA</button>
			</div>
		</div>
		<!-- E Filter Box -->
	

<script type="text/javascript">

$(function(){
	html="";
	var levels = "${levels}";
	levels = levels.substr(0,levels.length - 1)
	levels =levels.substr(1);
	levels= levels.split("),");
	var len=levels.length-1;
	var count = 0;
	
	var htmls="";
	var lists = "${list}";
	lists = lists.substr(0,lists.length - 1)
	lists =lists.substr(1);
	lists= lists.split("),");
	var lens=levels.length-1;
	var counts = 0;
	<c:forEach items="${levels}" var="level" varStatus="ext">
	if("${ext.index%2==0}"=='true'){
		if(len==count){
		html+='<li style="list-style-type:none;">'+
				'<button class="squre_btn"  onClick="views(${level.id},\'${level.levelName}\')">'+
				'<span class="line_btn" style="height: 200px;top: -2px;left: 111px;transform: rotate(-60deg);"></span>'+
				'<b class="quaters_text">(${level.levelName})</b>'+
				'</button>';
		}else{
			html+='<li style="list-style-type:none;">'+
			'<button class="squre_btn"  onClick="views(${level.id},\'${level.levelName}\')">'+
			'<span class="line_btn" ></span>'+
			'<b class="quaters_text">(${level.levelName})</b>'+
			'</button>';
		}
	}
	if("${ext.index%2==0}"=='false'){
			html+='<button class="squreBtn" onClick="views(${level.id},\'${level.levelName}\')">'+
					'<input type="hidden" name="field＿name" id="deveId2" value="">'+
					'<span class="line_btn" id="deve2"></span>'+
					'<b class="quaters_text">(${level.levelName})</b>'+
					'</button>'+
					'</li>';
		}
	count++;
		</c:forEach>
		<c:forEach items="${list}" var="deve" varStatus="ext">
		if("${ext.index%2==0}"=='true'){
			if(lens==counts){
			htmls+='<li style="list-style-type:none;">'+
					'<button class="squre_btn"  onClick="views(${deve.id},\'${deve.levelName}\')">'+
					'<span class="line_btn" style="height: 200px;top: -2px;left: -70px;transform: rotate(60deg);"></span>'+
					'<b class="quaters_text">(${deve.levelName})</b>'+
					'</button>';
			}else{
				htmls+='<li style="list-style-type:none;">'+
				'<button class="squre_btn"  onClick="views(${deve.id},\'${deve.levelName}\')">'+
				'<span class="line_btn" ></span>'+
				'<b class="quaters_text">(${deve.levelName})</b>'+
				'</button>';
			}
		}
		if("${ext.index%2==0}"=='false'){
				htmls+='<button class="squreBtn" onClick="views(${deve.id},\'${deve.levelName}\')">'+
						'<input type="hidden" name="field＿name" id="deveId2" value="">'+
						'<span class="line_btn" id="deve2"></span>'+
						'<b class="quaters_text">(${deve.levelName})</b>'+
						'</button>'+
						'</li>';
		}
		counts++;
		</c:forEach>		
		$(".quaters_left").append(html);
		$(".quaters_right").append(htmls);
		
	
	})
	
	
	function views(ids,name){
		location.href="${ctx}/manage/development/selectDeveById?id="+ids+"&levelName="+name;
		
	}
	

</script>

