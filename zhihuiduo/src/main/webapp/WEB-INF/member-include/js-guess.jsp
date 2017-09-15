<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
		var selectedOptions = "";

		$().ready(function() {
			
		})
        
        function save(){
            var url = $("#form").attr("action");
            var data = $('#form').serialize();
            $.ajax({
			 url:url,
             type: "POST",
             data: data,
             success: function (result) {
				 var obj =  JSON.parse(result);
				 if(obj.result=='1'){
                 	alert("竞猜成功");
                 }else{
                 	alert(obj.message);
                 }
				
				$('#myModal').modal('hide');
             },
              error: function(data) {
                       // alert("error:"+data.responseText);
             }
           });
        }

		function setDefaultMemberAddress(addressId) {
			var url = "${ctx}/memberInfo/setDefaultMemberAddress";
			var data = {};
			data.id = addressId;
			$.ajax({
				type : 'POST',
				url : url,
				data : data,
				success : function(data) {
					if (data == '1') {
						alert('设置成功');
						location.reload();
					}

				},
				dataType : 'json'
			});
		}

		function create() {
			document.form.action = "${ctx}/memberInfo/createMemberAddress";
			$("#name").val('');
			$("#mobilPhone").val('');
			$("#street").val('');
		}

		function bet(optionsId,qId) {
			$('#myModal').modal('show');

			var name = $('#' + optionsId + "_name").html();
			var betRate = $('#' + optionsId + "_betRate").html();
			var title = name + "回报率" + betRate;
			$('#myModalLabel').html(title);
			selectedOptions = optionsId;
			$("#optionsId").val(selectedOptions);
            $("#questionId").val(qId);
		}

		function setBetRateStyle(proName, proValueId) {
			$("[name=" + proName + "]").removeClass("activeProperyValue");
			$("[name=" + proName + "]").addClass("ProperyValue");
			$("#" + proValueId).removeClass("ProperyValue");
			$("#" + proValueId).addClass("activeProperyValue");

			var bet = $("#" + proValueId).html();
			var betRate = $('#' + selectedOptions + "_betRate").html();
			var inAll = Math.round(bet * betRate);
            $("#bet").val(inAll);
            
			$("#winLabel").html(inAll);
			$("#bet").val(bet);
            
            $("#submitBtn").attr("disabled",false);
		}