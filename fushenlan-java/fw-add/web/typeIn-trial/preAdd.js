define(function(require, exports, module){
    var template = require('./preAdd.html');
    require('dropload');
    template = doT.template(template);

    exports.back = function() {
        return '';
    };

    exports.show = function(opt) {

        var funHomePage = {
            init : function(opt){

                this.obj = opt.body;// 此时obj指代$('.main')dom元素
                this.bottom = opt.bottom;
                this.userInfo = common.getLocalStorage('userInfo', true) || {};
                this.accountId = '';
                this.pageSize = 10;
                this.pageNo = 1;
                if(this.userInfo && this.userInfo.id) {
                    this.accountId = this.userInfo.id;
                }
                this.obj.html(template(''));
                var _this = this;

                $("[name=keyWord]").live("keyup", function (e) {
                    _this.pageNo = 1;
                    if (e.keyCode === 13) {
                        _this.keyword = $('[name=keyWord]').val();
                        $('.source-list').html('');
                        _this.getMustIncrease();
                    }
                });

                this.getMustIncrease();    //准增员
                this.renderBottom();

                // 确定按钮事件
                this.obj.off('tap', '.preAdd-confirm button');
                this.obj.on('tap', '.preAdd-confirm button', {fun: this}, this.getMustIncrease);

                // 添加人才
                this.bottom.off('tap', '.float-bot .add-client');
                this.bottom.on('tap', '.float-bot .add-client', {fun: this}, this.goToAddSource);

                // 编辑人才信息
                this.obj.off('tap', '.source-list .list-item');
                this.obj.on('tap', '.source-list .list-item', {fun: this}, this.editSource);

                // 编辑人才信息
                this.obj.off('tap', '.list-item .initiate');
                this.obj.on('tap', '.list-item .initiate', {fun: this}, this.initiate)
            },

            initiate: function () {
                return window.location.href = '?personnelId=' + $(this).parents('.list-item').data('id') + '#page=personnelInfo'
            },

            editSource: function () {
                return window.location.href = '?personnelId=' + $(this).data('id') + '#page=addSource';
            },

            goToAddSource: function () {
                return window.location.href = '#page=addSource';
            },

            renderBottom: function () {
                this.bottom.removeClass('bot-button').addClass('bot-button');
                var temp = '<button type="button" class="add-client">\
                    <i class="icon-jia"></i><span>添加人才</span>\
                </button>';

                temp = doT.template(temp);
                this.bottom.html(temp(''));
            },

            //准增员
            getMustIncrease: function (btn) {
                var _this = this;
                if(btn) {
                    _this = btn.data.fun;
                    _this.pageNo = 1;
                }
                var dropDown = $('.dropload-down');
                var scrollArea = $('.recruit-clientInfo');
                var scrollContent = $('.source-list');
                if(dropDown.size() > 0){
                    dropDown.remove();
                }
                scrollArea.unbind('scroll');
                scrollContent.html('');
                scrollArea.dropload({
                    scrollArea: scrollArea,
                    loadDownFn: function (me) {
                        var paramData = {
                            url: '/personnel/customer/personnel/getMustIncrease',
                            contentType: 'application/x-www-form-urlencoded',
                            type: 'POST',
                            data: {
                                accountId: _this.accountId,
                                keyWord: $('input[name=keyWord]').val(),
                                pageSize: _this.pageSize,
                                pageNo: _this.pageNo
                            }
                        };

                        Interface.getAsynData(paramData, function (response) {
                            if(response.code === '1' && response.data.records.length > 0) {
                                var temp = require('./source-list.html');
                                temp = doT.template(temp);
                                scrollContent.html(temp(response.data.records));
                                _this.pageNo++;
                                if(response.data.records.length < 10) {
                                    me.lock();
                                    me.noData();
                                    if(_this.pageNo > 1){
                                        me.$domDown.hide();
                                    }
                                }
                            } else {
                                me.lock();
                                me.noData();
                                if(_this.pageNo > 1){
                                    me.$domDown.hide();
                                }
                            }
                            me.resetload();
                        }, function (error) {
                            me.lock();
                            me.noData();
                            me.resetload();
                        });
                    }
                });
            }
       };
        funHomePage.init(opt);
    }
});


