<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<meta charset="utf-8">
<link href="${ctx}/resources/css/style.css" type="text/css"
	rel="stylesheet" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<title>富卫运维大平台</title>
</head>
<body  >
	<div class="panel-body">
		<form id="form" action="" method="post" class="form-horizontal">
			<div class="table-content">
				<div class="table-box mar-bottom-15">
					<table class="table table-agents">
						<thead>
							<tr>
								<th></th>
								<th>姓名</th>
								<th>年龄</th>
								<th>证件类型</th>
								<th>证件号</th>
							</tr>
						</thead>
						<tbody class="pageTbody">
							<c:forEach items="${customerlist}" var="customer"
								varStatus="status">
								<tr class="notContent">
									<td><input type="radio" class = "tdcustomerId" /> <input class="customerId"
										type="hidden" value="${customer.customerId}" /> <input class="customerId"
										type="hidden" value="${customer.customerId}" /></td>
									<td>${customer.lastName}</td>
									<td>  <c:if test="${customer.gender=='F'}">女</c:if> 
										  <c:if test="${customer.gender=='M'}">男</c:if>
										  <input class="gender" type="hidden" value="${customer.gender}" /></td>
									<td><input class="identityType" type="hidden" value="${customer.identityType}" />
									<c:if test="${customer.identityType=='111'}">身份证 </c:if>
										<c:if test="${customer.identityType=='114'}">军官证</c:if> <c:if
											test="${customer.identityType=='511'}">台胞证 </c:if> <c:if
											test="${customer.identityType=='414'}">护照 </c:if> <c:if
											test="${customer.identityType=='516'}">港澳回乡证</c:if></td>
									<td>${customer.identityNo}</td>

								</tr>
							</c:forEach>
						</tbody>

					</table>
				</div>

				<div>
				
				<input class="personnelidentityType" type="hidden" value="${personnelidentityType }">
				<input class="personnelidentityCode" type="hidden" value="${personnelidentityCode }">
				<input class="personnelname" type="hidden" value="${personnelname }">
				<input class="personnelsex" type="hidden" value="${personnelsex }">
				<input class="personnelid" type="hidden" value="${personnelid }">
					<button type="button" class="btn btn-submit"
						onclick="syndmsloc(1)">同步到DMS</button>
					<button type="button" class="btn btn-submit"
						onclick="syndmsloc(2)">同步到本系统</button>

				</div>
			</div>
		</form>
	</div>

<script src="${ctx}/resources/js/common/jquery-3.2.1.min.js"></script>
	<script type="text/javascript">
		
			function  syndmsloc(cc){
			
		     var _this	= $("input[type='radio']:checked");
		     var customerId =  _this.parents(".notContent").find("td").eq(0).find("input").eq(1).val();
		     var lastName =  _this.parents(".notContent").find("td").eq(1).text();
		     var gender =  _this.parents(".notContent").find("td").eq(2).find("input").val();
		     var identityType =  _this.parents(".notContent").find("td").eq(3).find("input").val();
		     var identityNo =  _this.parents(".notContent").find("td").eq(4).text();
		 	var   personnelidentityType = $(".personnelidentityType").val();
			var   personnelidentityCode = $(".personnelidentityCode").val();
			var   personnelname = $(".personnelname").val();
			var   personnelsex = $(".personnelsex").val();
			var   personnelid = $(".personnelid").val();
		 
		   if(cc ==1){
			   //dms
			   $.ajax({
					type : "post",
					data : {"customerId" : customerId,"lastName": personnelname,"gender" : personnelsex,"identityType" : personnelidentityType,"identityNo" : personnelidentityCode,"personnelid" : personnelid },
					url : "${ctx}/manage/erecruitment/dmsupdate",
					dateType: "json",
					success : function(msg) {			
						var obj = JSON.parse(msg);
						if(obj.status=="fail"){
							alert(obj.message);
						}
						console.log(obj);
						var index = parent.layer.getFrameIndex(window.name);  
						parent.layer.close(index);  
					}
				});
		    	 
		    	 
		     }else{
		    	 
		    	 $.ajax({
						type : "post",
						data : {"customerId" : customerId,"lastName": lastName,"gender" : gender,"identityType" : identityType,"identityNo" : identityNo,"personnelid" : personnelid },
						url : "${ctx}/manage/erecruitment/locupdate",
						dateType: "json",
						success : function(msg) {
							var obj = JSON.parse(msg);
							if(obj.status=="fail"){
								alert(obj.message);
							}
							var index = parent.layer.getFrameIndex(window.name);  
							parent.layer.close(index);  
						}
					});
		     } 
		     
		     
		     
		   
		    
		    
		    
		}
	
	
	
		

		
	</script>
</body>
</html>