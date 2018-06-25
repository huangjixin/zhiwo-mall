define(function(require, exports, module){
    var template = require('./application.html');
    template = doT.template(template);

    exports.back = function() {
        return '';
    };

    exports.show = function(opt) {

        var funHomePage = {
            init : function(opt){
                this.obj = opt.body;
                this.popup = opt.popup;
                this.bottom = opt.bottom;
                this.largeState = opt.state;
                this.obj.html(template(''));
               
                this.personnelInfo = common.getLocalStorage("personnelInfo", true) || {};
                this.personnelId = '';
                this.flag = 0;
                this.initTalent = '';
                if(this.personnelInfo && this.personnelInfo.personnelId) {
                    this.personnelId = this.personnelInfo.personnelId;
                } else {
                    common.alertCreate({
                        html: '数据丢失！',
                        callback: function () {
                            return window.location.href = '#page=main';
                        }
                    });
                    return false
                }

                this.getApply();
                this.renderBottom();

                // tap点击返回
                this.obj.off('tap', '.materials-header li.already');
                this.obj.on('tap', '.materials-header li.already', {fun: this}, this.tapReturn);

                // 保存按钮
                this.bottom.off('tap', '.saveApplication');
                this.bottom.on('tap', '.saveApplication', {fun: this}, this.saveApplicationButton);

                // 点击下一步更新状态
                this.bottom.off('tap', '.applicationGoNext');
                this.bottom.on('tap', '.applicationGoNext', {fun: this}, this.passStatus);

                // 提交面试官意见
                this.popup.off('tap', '.popup-confirm');
                this.popup.on('tap', '.popup-confirm', {fun: this}, this.submitPassData);

                // 取消
                this.popup.off('tap', '.popup-cancel');
                this.popup.on('tap', '.popup-cancel', {fun: this}, this.cancelPopup);

                // 点击下一步更新状态
                this.bottom.off('tap', '.editPlan');
                this.bottom.on('tap', '.editPlan', {fun: this}, this.editPlan);

                // 关闭弹层
                this.largeState.off('tap', '.planClose');
                this.largeState.on('tap', '.planClose', {fun: this}, this.hidePopBox);

                // 提交数据
                this.largeState.off('tap', '.planSure');
                this.largeState.on('tap', '.planSure', {fun: this}, this.submitPopBox);

                // 上传声明
                this.largeState.off('tap', '#pen-sign');
                this.largeState.on('tap', '#pen-sign', {fun: this}, this.uploadDeclaration);
  
                //签署声明
                this.obj.off("tap", ".plan-state");
                this.obj.on("tap", ".plan-state", { fun: this }, this.planState);
  
                // 关闭弹层
                this.largeState.off("tap", ".planCloseOrSure button");
                this.largeState.on("tap", ".planCloseOrSure button", { fun: this }, this.closeState);
            },

            getDeclaration: function () {
                if (this.personnelInfo.personnelId) {
                    var paramData = {
                        url: '/system/attachment/file/find',
                        type: 'get',
                        data: {
                            hostId: this.personnelInfo.personnelId,
                            category: 20
                        }
                    };
                    Interface.getAsynData(paramData, function (response) {
                        $("#pen-sign").find('img').css({
                            height: "100%"
                        });
                        $("#pen-sign").find('img').attr('src',  response.data[0] );
                    }, function (error) {

                    });
                }
            },
            planState:function(btn){
              common.popup('.popup-largeState');
            },
  
            closeState: function(){
              common.closePopup('.popup-largeState');
            },
            uploadDeclaration: function (btn) {
                var _that = btn.data.fun;
                var item = this;
                if (_that.personnelId && common.isRunByApp) {
                    var personnelId = _that.personnelId ;
                    Pen.doSign(onSuccess, onFail, 1, 1, 1, personnelId, 20, uploadLink);
                    common.loading.open();
                    function onSuccess(success) {
                        _that.initTalent = $('.color-ccc').eq(0).text();
                        $(item).find('img').prop('src', success.result_data);
                        common.loading.close();
                    }
                    function onFail(fail) {
                        common.loading.close();
                        common.alertCreate({
                            html: fail.result_msg
                        });
                    }
                }
            },

            submitPopBox: function () {
                common.closePopup('.popup-largeState');
            },

            hidePopBox: function () {
                common.closePopup('.popup-largeState');
            },

            cancelPopup: function () {
                common.closePopup('.popup-box')
            },

            renderBottom: function () {
                this.bottom.removeClass('bot-button').addClass('bot-button');

                var temp = '<button type="button" class="editPlan">修改人才计划</button>\
                    <div class="buttonGroup">\
                        <button type="button" class="saveApplication">不通过</button>\
                        <button type="button" class="goNext applicationGoNext">通过</button>\
                    </div>';

                var button = '<button type="button" class="btn btn-lg btn-default popup-cancel">取消</button>\
                    <button type="button" class="btn btn-lg btn-submit popup-confirm">完成</button>';

                temp = doT.template(temp);
                button = doT.template(button);
                this.bottom.html(temp());
                this.popup.find('.suggest-button').html(button());
            },

            editPlan: function (btn) {
                var _this = btn.data.fun;
                _this.flag = 3;
                _this.popup.find('.suggest-plan').removeClass('hidden');
                _this.popup.find('.suggest-text').addClass('hidden');
                common.popup('.popup-suggest');
            },

            saveApplicationButton: function (btn) {
                var _this = btn.data.fun;
                _this.flag = 2;
                _this.popup.find('.suggest-plan').addClass('hidden');
                _this.popup.find('.suggest-text').removeClass('hidden');
                if(_this.initTalent !== $('.color-ccc').eq(0).text()) {
                    _this.showDeclaration();
                } else {
                    common.popup('.popup-suggest');
                }
            },

            tapReturn: function () {
                return window.location.href = '#page=' + $(this).data('type');
            },

            passStatus: function (btn) {
               var _this = btn.data.fun;
               _this.flag = 1;
                _this.popup.find('.suggest-plan').addClass('hidden');
                _this.popup.find('.suggest-text').removeClass('hidden');
                if(_this.initTalent !== $('.color-ccc').eq(0).text()) {
                	console.log(1111)
                    _this.showDeclaration();
                } else {	
                	console.log(222)
                    common.popup('.popup-suggest');
                }
            },

            getApply: function () {
                var _this =this;
                var paramData = {
                    ifOpenLoading: true,
                    type: "post",
                    url: '/personnel/customer/personnel/getScanApply',
                    contentType: 'application/x-www-form-urlencoded',
                    data: {
                        personnelId: _this.personnelInfo.personnelId
                    }
                };
                Interface.getAsynData(paramData, function (response) {
                    if(response.code === '1' && response.data) {
                        _this.initTalent = response.data.talentPlan;
                        var _temp = require("./app-getApply.html");
                        _temp = doT.template(_temp);
                        $('.app-getApply').html(_temp(response.data));
                        _this.getTalentPlan(response.data.talentPlan);
                        // _this.largeState.find('.hidden').removeClass('hidden');
                        // _this.getDeclaration();
                    }
                }, function (error) {
                });
            },

            getTalentPlan: function(plan) {
                var _this = this;
                var dict = 'talent_plan';
                var dictParam = {
                    url: '/system/dictionary/code/findByCodes',
                    type: 'POST',
                    contentType: 'application/x-www-form-urlencoded',
                    data: {
                        codes: dict
                    }
                };

                Interface.getAsynData(dictParam, function (response) {
                    if(response.code === '1') {
                        response.data.talentPlan = plan;
                        var talentPlan = '<option value disabled>请选择人才计划</option>\
                                {{~it.talent_plan:value:index}}\
                                    <option value="{{=value.code}}" {{=value.code===it.talentPlan?"selected":""}}>{{=value.cnName}}</option>\
                        {{~}}';

                        talentPlan = doT.template(talentPlan);
                        $('select[name=talentPlan]').append(talentPlan(response.data));
                        $('select[name=talentPlan]').prev().val(_this.getName(response.data.talent_plan, response.data.talentPlan));
                    }
                }, function (error) {

                });
            },

            getName: function (list, code) {
                if (!code) {
                    return '';
                }
                for (var i = 0; i < list.length; i++) {
                    if (list[i].code === code) {
                        return list[i].cnName;
                    }
                }
                return '';
            },

            submitPassData: function (btn) {
                var _this = btn.data.fun;
                if(_this.flag === 3) {
                    $('.plan').eq(0).find('.color-ccc').text(_this.popup.find('select[name=talentPlan]').val());
                    common.closePopup('.popup-suggest');
                    return false;
                }
                var textArea = _this.popup.find('#suggest').val();
                if(!textArea) {
                    common.tipSystem({
                        html: '请填写面试官意见！',
                        timeout: 2000
                    });
                    return false;
                }
                var progress = '';
                if(_this.personnelInfo.progress === '2') {
                    progress = 3;
                } else if(_this.personnelInfo.progress === '3') {
                    progress = 4;
                } else if(_this.personnelInfo.progress === '4') {
                    progress = 5;
                }
                var paramData = {
                    ifOpenLoading: true,
                    type: "POST",
                    url: '/personnel/customer/personnel/updatePersonnel',
                    contentType: 'application/x-www-form-urlencoded',
                    data: {
                        personnelStatus: progress,
                        personnelId: _this.personnelId,
                        flowActionId: progress,
                        processingDesc: textArea,
                        talentPlan: _this.popup.find('select[name=talentPlan]').val() || '',
                        processingStatus: _this.flag === 1 ? 1 : _this.flag === 2 ? 2 : 3 //1:通过 2:不通过 3:修改人才计划
                    }
                };

                Interface.getAsynData(paramData, function (response) {
                    if(response.code === '1') {
                        common.closePopup('.popup-suggest');
                        if(_this.flag === 1) {
                            return window.location.href = '#page=main';
                        } else if(_this.flag === 2) {
                            common.alertCreate({
                                html: '人才计划不通过成功！',
                                callback: function () {
                                    return window.location.href = '#page=main';
                                }
                            });
                            return false;
                        } else {
                            common.alertCreate({
                                html: '修改人才计划成功！',
                                callback: function () {
                                    return window.location.href = '#page=main';
                                }
                            });
                        }
                    }
                }, function (error) {
                });
            },

            showDeclaration: function () {
                common.popup('.popup-suggest');
            }
        };
        funHomePage.init(opt);
    };
});