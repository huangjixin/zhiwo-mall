/**********************
//提示弹层
$.alert({
	tit : "提示",  //提示标题
	cont : "是否退出此系统？", //提示内容
});

$.alert({
	className : '', //自定义class name
	tit : "提示",  //提示标题
	cont : "提示内容", //提示内容
	closeFun : function(){},  //自定义X关闭事件
	isBtn : true, //是否需要操作按钮
	txtConfirm : "确定",  //确认按钮文字
	confBeforeFun : function(){},  //自定义确认按钮确认前事件
	confAfterFun : function(){},  //自定义确认按钮确认后事件
	isCancel : true,  //是否需要取消按钮
	txtCancel : "取消",  //取消按钮文字
	cancFun : function(){}  //自定义取消按钮事
});

***********************/

(function($){
	$.extend({
		alert : function(info){
			var init = {
				className : '', //自定义class name
				tit : "提示",  //提示标题
				cont : "提示内容", //提示内容
				closeFun : function(){},  //自定义X关闭事件
				isBtn : true, //是否需要操作按钮
				txtConfirm : "确定",  //确认按钮文字
				confBeforeFun : function(){},  //自定义确认按钮确认前事件
				confAfterFun : function(){},  //自定义确认按钮确认后事件
				isCancel : true,  //是否需要取消按钮
				txtCancel : "取消",  //取消按钮文字
				cancFun : function(){}  //自定义取消按钮事件
			};
			info = info || {};
			info.className = info.className || init.className;
			info.tit = info.tit || init.tit;
			info.cont = info.cont || init.cont;
			info.closeFun = info.closeFun || init.closeFun;
			info.isBtn = info.isBtn == undefined? init.isBtn : info.isBtn;
			info.txtConfirm = info.txtConfirm || init.txtConfirm;
			info.confBeforeFun = info.confBeforeFun || init.confBeforeFun;
			info.confAfterFun = info.confAfterFun || init.confAfterFun;
			info.isCancel = info.isCancel == undefined? init.isCancel : info.isCancel;
			info.txtCancel = info.txtCancel || init.txtCancel;
			info.cancFun = info.cancFun || init.cancFun;
			
			isBgShow = false;
			
			if(info.cont == ''){
				return false;
			};
			
			var alertCont = $(".popup-bg").length > 0 ? '' : '<div class="popup-bg"></div>';
				alertCont += '<div class="popup-box popup-alert '+ (info.className == ''? '' : info.className) +'" style="z-index:1021">\
				<div class="popup-title">\
					<h2>'+ info.tit +'</h2>';
					
				if(info.isCancel){
					alertCont += info.isCancel? '<button class="popup-close" type="button" title="关闭"><i class="icon-close"></i></button>' : '';
				}
				alertCont+='</div>\
				<div class="popup-alert-cont">\
					<table><tr><td>'+ info.cont +'</td><tr></table>\
				</div>';
			if(info.isBtn){	
				alertCont += '<div class="popup-footer">';
				if(info.isCancel){
					alertCont += info.isCancel? '<button class="btn btn-default" type="button" title="'+ info.txtCancel +'">'+ info.txtCancel +'</button>' : '';
				}
				alertCont += '<button class="btn btn-submit" type="button" title="'+ info.txtConfirm +'">'+ info.txtConfirm +'</button></div>';
			}
			alertCont +='</div>';
			$("body").append(alertCont);
			
			return $(".popup-alert").each(function(index, element) {
				var _this = $(this);
				isBgShow = $(".popup-bg").css('display') == 'block'? !0 : !1;
				$(".popup-bg").css('z-index', 1020).show();
				$(this).show();
				$(".popup-close", this).click(function(e){
					e.preventDefault();
					info.closeFun();
					_this.hide().remove();
					isBgShow ? $(".popup-bg").css('z-index', 999) : $(".popup-bg").removeAttr('style');
				});
				$(".btn-default", this).click(function(e){
					e.preventDefault();
					info.cancFun();
					_this.hide().remove();
					isBgShow ? $(".popup-bg").css('z-index', 999) : $(".popup-bg").removeAttr('style');
				});
				$(".btn-submit", this).click(function(e){
					e.preventDefault();
					info.confBeforeFun();
					_this.hide().remove();
					isBgShow ? $(".popup-bg").css('z-index', 999) : $(".popup-bg").removeAttr('style');
					info.confAfterFun();
				});
			});
		},
		alertTips : function(info){
			var options = {
				html: info.html,
				timeout: info.timeout || 0,
				callback: function(data){}
			};
			var init = $.extend(options, info);
			var _html = '<div class="alert-tips">\
				<p><i class="icon-tips"></i>'+ init.html +'</p>\
				<button type="button" class="btn-close"><i class="icon-close"></i></button>\
			</div>';
			$('body').append(_html);
			
			var _obj = $('.alert-tips');
			_obj.fadeIn(300);
			_obj.on('click', '.btn-close', function(){
				$.isFunction(init.callback) && init.callback(1);
				$(this).parents('.alert-tips').fadeOut(300, '', function(){
					$(this).remove();
				});
			});
			if(init.timeout > 0){
				setTimeout(function(){
					$.isFunction(init.callback) && init.callback(1);
					_obj.fadeOut(500, '', function(){
						$(this).remove();
					});
				}, init.timeout);
			}
		}
	});
})(jQuery);