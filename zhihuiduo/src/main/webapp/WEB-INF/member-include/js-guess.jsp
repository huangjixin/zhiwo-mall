		var selectedOptions = "";

		$().ready(function() {
			
		})

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

		function bet(optionsId) {
			$('#myModal').modal('show');

			var name = $('#' + optionsId + "_name").html();
			var betRate = $('#' + optionsId + "_betRate").html();
			var title = name + "回报率" + betRate;
			$('#myModalLabel').html(title);
			selectedOptions = optionsId;
			$("#optionsId").val(selectedOptions);
		}

		function setBetRateStyle(proName, proValueId) {
			$("[name=" + proName + "]").removeClass("activeProperyValue");
			$("[name=" + proName + "]").addClass("ProperyValue");
			$("#" + proValueId).removeClass("ProperyValue");
			$("#" + proValueId).addClass("activeProperyValue");

			var bet = $("#" + proValueId).html();
			var betRate = $('#' + selectedOptions + "_betRate").html();
			var inAll = Math.round(bet * betRate);
			$("#winLabel").html(inAll);
			$("#bet").val(inAll);
		}