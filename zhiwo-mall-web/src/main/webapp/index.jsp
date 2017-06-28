<html>
<head>
	<script type="text/javascript" src="/zhiwo-mall-web/js/jquery-easyui/jquery.min.js"></script>
	<script type="text/javascript" src="/zhiwo-mall-web/js/mqttws31.js"></script>
	<script type="text/javascript">
		var host = "localhost";
		var port = "61616";
		var clientId = "myWeb";
		var destination = "mall.product.topic";
    $(document).ready(function(){  
        window.client = new Messaging.Client(host,Number(port),clientId);
        client.onMessageArrived=function (e){
        	alert(e.payloadString);
        }
        client.onConnectionLost=function (e){
        	alert("lost");
        }
        client.connect({
        	onSuccess:function (){
        		client.subscribe(destination);
        	},
        	onFailure:function (){
        		alert("connect fail");
        	}
        });
    });  
	</script>
</head>
<body>
<h2>Hello World!</h2>
</body>
</html>
