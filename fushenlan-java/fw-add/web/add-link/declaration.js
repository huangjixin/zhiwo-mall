/**
 * Created by Fairyland on 2018/1/25.
 */
define(function (require, exports, module) {
    var template = require('./declaration.html');
    require('moment');
    template = doT.template(template);
    exports.back = function () {
        return '';
    };

    exports.show = function (opt) {

        var funHomePage = {
            init: function (opt) {
                this.obj = opt.body;
                
                this.bottom = opt.bottom;
                
                this.personnelInfo = common.getLocalStorage('personnelInfo', true) || '';
                
				this.signType = this.personnelInfo.progress == 3?21:this.personnelInfo.progress==4?22:-1;
				
                this.obj.html(template(this.personnelInfo));
                
                this.currentTime = common.getFormatDate(new Date());

               
                this.renderBottom();
                
                this.getSystemTime();
                
                this.imginit();
                
               

                // 下一步按钮
                this.bottom.off('tap', '.bot-button .goNext');
                this.bottom.on('tap', '.bot-button .goNext', {fun: this}, this.geToNext);

                // tap点击返回
                this.obj.off('tap', '.materials-header li.already');
                this.obj.on('tap', '.materials-header li.already', {fun: this}, this.tapReturn);

                // 签名
                this.obj.off('tap', '#pen-sign');
                this.obj.on('tap', '#pen-sign', {fun: this}, this.penSign);
            },
            imginit: function () {
                if (this.personnelInfo.personnelId) {
                    var paramData = {
                    url: '/system/attachment/file/findbyparmsForPad',
                    type: 'get',
                    data: {
                        hostId: this.personnelInfo.personnelId,
                        category:this.signType
                    }
                };
                Interface.getAsynData(paramData, function (response) {
                    $("#pen-sign").find('img').css({
                        height: "100%"
                    });
                  
                    $("#pen-sign").find('img').attr('src',  response.data[0].path);
                    $("#submitTime").text( response.data[0].gmtCreate);
                   
                }, function (error) {

                });
                
                }
            },
            penSign: function (btn) {
                var _that = btn.data.fun;
                var _link = uploadLink;
                var _this = $(this);
                var _thisjs = btn.data.fun;
                if (_that.personnelInfo && common.isRunByApp) {
                    var personnelId = _that.personnelInfo.personnelId;
                    Pen.doSign(onSuccess, onFail, "1", "1", "1", personnelId,_thisjs.signType, _link);
                    common.loading.open();
                    function onSuccess(success) {
                        common.loading.close();
                        _this.find('img').css({
                            height: "100%"
                        });
                        _this.find('img').attr({
                            src: success.result_data
                        }).show();
                    }

                    function onFail(fail) {
                        common.loading.close();
                        common.alertCreate({
                            html: fail.result_msg
                        });
                    }
                }

            },
            renderBottom: function () {
                this.bottom.removeClass('bot-button').addClass('bot-button');
                var temp = '<div class="buttonGroup">\
                    <button type="button"></button>\
                    <button type="button" class="goNext">下一步</button>\
                </div>';

                temp = doT.template(temp);
                this.bottom.html(temp(''));
            },
            getSystemTime: function () {
	            var _this = this;
	            var timeParam = {
	                url: '/system/manage/common/systime',
	                type: 'POST',
	                data: {}
	            };
	            Interface.getAsynData(timeParam, function (response) {
	                if (response.code === '1' && response.data) {
	                    var currentTime = new Date(response.data);
	                    _this.currentTime = moment(currentTime).format('YYYY-MM-DD');
						$("#submitTime").text(_this.currentTime);
	                }
	            }, function (error) {
	                console.log(error);
	            });
      		 },
            geToNext: function () {
                return window.location.href = '#page=pickInterview';
            },
            tapReturn: function () {
                return window.location.href = '#page=' + $(this).data('type');
            }
        };
        funHomePage.init(opt);

    }
});
/**
 * Created by Fairyland on 2018/1/25.
 */
