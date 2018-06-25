/**
 * @author kang
 * @param fun url(带参数)
 * @param pageSize (每页显示多少条)
 * @param pageNo (当前所在页数)
 * @param pageTotal (总共几页)
 * @param id(分页所在div的id)
 * @returns {Boolean}
 */
function page(fun,pageSize,pageNo,pageTotal,id){
			pageSize= isNaN(pageSize)?10:parseInt(pageSize); 
			pageNo = isNaN(pageNo)?1:parseInt(pageNo); 
			pageTotal = isNaN(pageTotal)?1:parseInt(pageTotal); 
			if(pageTotal ==0){
				$("#"+id).html("");
				return false;
			}
			if(pageNo>pageTotal){
				pageNo = pageTotal;
			}
			var pageHtml = "<p>";
			if(pageNo == 1){
				pageHtml+='<a class="first disabled" href="#"><i class="icon-first"></i></a>';
				pageHtml+='<a class="prev disabled" href="#"><i class="icon-arrow-left"></i></a>';
			}else{
				pageHtml+='<a class="first" href="'+fun+'&pageNo=1&pageSize='+pageSize+'"><i class="icon-first"></i></a>';
				pageHtml+='<a class="prev" href="'+fun+'&pageNo='+(pageNo-1)+'&pageSize='+pageSize+'"><i class="icon-arrow-left"></i></a>';
			}
			if(pageTotal>10){
				if(pageNo<=6){
					for(var i=1;i<=7;i++){
						if(i==pageNo){
							pageHtml+='<a class="active" href="'+fun+'&pageNo='+i+'&pageSize='+pageSize+'">'+i+'</a>'
						}else{
							pageHtml+='<a class="" href="'+fun+'&pageNo='+i+'&pageSize='+pageSize+'">'+i+'</a>'
						}
					}
					pageHtml+='<a>....</a>';
					for(var j=pageTotal-3;j<=pageTotal;j++){
						pageHtml+='<a class="" href="'+fun+'&pageNo='+j+'&pageSize='+pageSize+'">'+j+'</a>'
					}
				}
				else if(pageNo>6 && (pageTotal-pageNo)<=6){
					pageHtml+='<a class="" href="'+fun+'&pageNo=1&pageSize='+pageSize+'">1</a>'+
							  '<a class="" href="'+fun+'&pageNo=2&pageSize='+pageSize+'">2</a>'+
							  '<a>....</a>';
					for(var i=pageTotal-7;i<=pageTotal;i++){
						if(i==pageNo){
							pageHtml+='<a class="active" href="'+fun+'&pageNo='+i+'&pageSize='+pageSize+'">'+i+'</a>'
						}else{
							pageHtml+='<a class="" href="'+fun+'&pageNo='+i+'&pageSize='+pageSize+'">'+i+'</a>'
						}
					}
				}else if(pageNo>6 && (pageTotal-pageNo)>6){
					pageHtml+='<a class="" href="'+fun+'&pageNo=1&pageSize='+pageSize+'">1</a>'+
							  '<a class="" href="'+fun+'&pageNo=2&pageSize='+pageSize+'">2</a>'+
							  '<a>....</a>';
				    for(var i=pageNo-3;i<=pageNo+3;i++){
				    	if(i==pageNo){
							pageHtml+='<a class="active" href="'+fun+'&pageNo='+i+'&pageSize='+pageSize+'">'+i+'</a>'
						}else{
							pageHtml+='<a class="" href="'+fun+'&pageNo='+i+'&pageSize='+pageSize+'">'+i+'</a>';
						}
				    }		  
				    pageHtml+='<a>....</a>'+
				    		  '<a class="" href="'+fun+'&pageNo='+(pageTotal-1)+'&pageSize='+pageSize+'">'+(pageTotal-1)+'</a>'+
					          '<a class="" href="'+fun+'&pageNo='+pageTotal+'&pageSize='+pageSize+'">'+pageTotal+'</a>';
				}
			}else{
				for(var i=1;i<=pageTotal;i++){
					if(i==pageNo){
						pageHtml+='<a class="active" href="'+fun+'&pageNo='+i+'&pageSize='+pageSize+'">'+i+'</a>'
					}else{
						pageHtml+='<a class="" href="'+fun+'&pageNo='+i+'&pageSize='+pageSize+'">'+i+'</a>'
					}
				}
			}
			if(pageNo == pageTotal){
				pageHtml+='<a class="next disabled" href="#"><i class="icon-arrow-right"></i></a>'+
						  '<a class="last disabled" href="#"><i class="icon-last"></i></a></p>';
			}else{
				pageHtml+='<a class="next" href="'+fun+'&pageNo='+(pageNo+1)+'&pageSize='+pageSize+'"><i class="icon-arrow-right"></i></a>'+
				  		  '<a class="last" href="'+fun+'&pageNo='+pageTotal+'&pageSize='+pageSize+'"><i class="icon-last"></i></a></p>';
			}
			$("#"+id).html("");
			$("#"+id).html(pageHtml);
		}
Date.prototype.Format = function (fmt) { //author: lyk 
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

