function valid(){
	var bool = true;
	var startTime;
	var endTime;
	var msg;
	var N = [
	           "零", "一", "二", "三", "四", "五", "六", "七", "八", "九","十"
	       ];
	$(".notNull").each(function(){
       /*
        *  /^[A-Za-z0-9]+$/;//不可输入中文
        */
		if($(this).val().trim()==""){
			msg = $(this).attr('notNull')+'不能为空';
			/* || typeof($(this).attr('notNull')) == undefined*/
			if($(this).attr('notNull') ==null || $(this).attr('notNull') == ""){
				msg = '*为必填项,且不为空';
			}
			layer.msg(msg);
			bool = false;
			return false;
		}
    });
	if(bool && $(".date").length>0){
		var labelVal = $(".labelMark").attr("class");
		console.log(labelVal);
		var startTimeArr = new Array();
		var endTimeArr = new Array();
		/*debugger;*/
		$(".date").each(function(i){
			startTime = $(this).find('.startDate').val();
			endTime = $(this).find('.endDate').val();
			if(startTime == null || startTime == '' || endTime == null || endTime == '' ){
				if(i==0){
					msg='周期时间不可为空';
					bool = false;
					layer.msg(msg);
				}else if(labelVal!=null){
					msg='第'+N[i]+'阶段时间不可为空';
					bool = false;
					layer.msg(msg);
				}
			    return false;
			}
			if(bool){
				startTimeArr.push(startTime);
				endTimeArr.push(endTime);
				var start=new Date(startTime.replace("-", "/").replace("-", "/")); 
				var end=new Date(endTime.replace("-", "/").replace("-", "/"));
				if(end<start){  
				    if(i==0){
				    	msg='周期开始时间不可大于结束时间';
				    }else if(labelVal!=null){
				    	msg='第'+N[i]+'阶段开始时间不可大于结束时间';
				    }
				    layer.msg(msg);
				    bool = false;
				    return false;
				}
			}
		});
		if($(".date").length>1 && labelVal!=null){
			var starts=new Date(startTimeArr[0].replace("-", "/").replace("-", "/")); //周期开始时间
			var startss=new Date(startTimeArr[1].replace("-", "/").replace("-", "/")); // 第一阶段开始时间
			var ends=new Date(endTimeArr[0].replace("-", "/").replace("-", "/"));//周期结束时间
			var endss=new Date(endTimeArr[endTimeArr.length-1].replace("-", "/").replace("-", "/"));// 最后阶段开始时间
			if(startss<starts  && bool ){
				msg='第一阶段的开始时间不可小于周期的开始时间';
			    layer.msg(msg);
			    bool = false;
		        return false;
			}
			if(startss>ends && bool ){
				msg='第一阶段的开始时间不可大于周期的结束时间';
				layer.msg(msg);
				bool = false;
				return false;
			}
			if(endss>ends  && bool ){
				msg='最后阶段的结束时间不可大于周期的结束时间';
				layer.msg(msg);
				bool = false;
				return false;
			}
		}
		if(bool && labelVal!=null){
			for(var i=0;i<startTimeArr.length-1;i++){
				 var start=new Date(startTimeArr[i+1].replace("-", "/").replace("-", "/")); 
				 var end=new Date(endTimeArr[i].replace("-", "/").replace("-", "/"));
				 if(i==0 && start>end){
					 msg='第'+N[i+1]+'阶段开始时间不可大于周期的结束时间';
					 layer.msg(msg);
					 bool = false;
					 break;
				 }else if(start<end && i!=0 && startTimeArr.length>1){  
			         msg='第'+N[i+1]+'阶段开始时间不可小于上一个阶段的结束时间';
			    	 layer.msg(msg);
				 	 bool = false;
				 	 break;
				 }else if(start>ends){
					 	msg='第'+N[i+1]+'阶段开始时间不可大于周期结束时间';
					 	layer.msg(msg);
					 	bool = false;
					 	break;
				 }else if(end>ends){
					 	msg='第'+N[i+1]+'阶段结束时间不可大于周期结束时间';
					 	layer.msg(msg);
					 	bool = false;
					 	break;
				 }
			}
		}
	}
	
	return bool;
}