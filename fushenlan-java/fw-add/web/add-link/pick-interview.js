/**
 * Created by c_mengchaopeng on 2018/1/25.
 */
define(function(require, exports, module){
    var template = require('./pick-interview.html');
    template = doT.template(template);

    exports.back = function() {
        return '';
    };
 	
    exports.show = function(opt) {

        var funHomePage = {
            init : function(opt){

                this.obj = opt.body;
                this.bottom = opt.bottom;
                this.popup = opt.popup;
                this.state =  opt.state;
                this.initTalent = '';
                this.flag = 0;
                this.obj.html(template(''));
                this.personnelInfo = common.getLocalStorage("personnelInfo", true) || '';

                this.initData();
                this.renderBottom();
                this.getApply();

                // 通过
                this.bottom.off('tap', '.pickConfirm');
                this.bottom.on('tap', '.pickConfirm', {fun: this}, this.pickConfirm);

                // 不通过
                this.bottom.off('tap', '.savePersonalInfo');
                this.bottom.on('tap', '.savePersonalInfo', {fun: this}, this.noPass);

                // 收缩展示paper
                this.obj.off('tap', '.interview-wrapper .preson-bg .bg-arrowup');
                this.obj.on('tap', '.interview-wrapper .preson-bg .bg-arrowup', {fun: this}, this.togglePaper);

                // tap点击返回
                this.obj.off('tap', '.materials-header li.already');
                this.obj.on('tap', '.materials-header li.already', {fun: this}, this.tapReturn);

                // 提交面试官意见
                this.popup.off('tap', '.popup-confirm');
                this.popup.on('tap', '.popup-confirm', {fun: this}, this.submitPassData);

                // 取消
                this.popup.off('tap', '.popup-cancel');
                this.popup.on('tap', '.popup-cancel', {fun: this}, this.cancelPopup);

                // 修改人才计划
                this.bottom.off('tap', '.editPlan');
                this.bottom.on('tap', '.editPlan', {fun: this}, this.editPlan);

                // 上传声明
                this.state.off('tap', '#pen-sign');
                this.state.on('tap', '#pen-sign', {fun: this}, this.uploadDeclaration);

                // 关闭弹层
                this.state.off('tap', '.planClose');
                this.state.on('tap', '.planClose', {fun: this}, this.hidePopBox);

                // 提交数据
                this.state.off('tap', '.planSure');
                this.state.on('tap', '.planSure', {fun: this}, this.submitPopBox);
            },

            getDeclaration: function () {
                if (this.personnelInfo.personnelId) {
                    var paramData = {
                        url: '/system/attachment/file/find',
                        type: 'get',
                        data: {
                            hostId: this.personnelInfo.personnelId,
                            category: this.personnelInfo.progress === '3' ? 21 : 22
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

            submitPopBox: function () {
                common.closePopup('.popup-suggest');
            },

            hidePopBox: function () {
                common.closePopup('.popup-suggest');
            },

            uploadDeclaration: function (btn) {
                var _that = btn.data.fun;
                var item = this;
                if (_that.personnelInfo.personnelId && common.isRunByApp) {
                    var personnelId = _that.personnelInfo.personnelId;
                    var status = _that.personnelInfo.progress === '3' ? 21 : 22;
                    Pen.doSign(onSuccess, onFail, 1, 1, 1, personnelId, status, uploadLink);
                    common.loading.open();
                    function onSuccess(success) {
                        _that.initTalent = _that.popup.find('select[name=talentPlan]').val();
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

            renderBottom: function () {
                this.bottom.removeClass('bot-button').addClass('bot-button');

                var temp = '<button type="button" class="editPlan">修改人才计划</button>\
                <div class="buttonGroup">\
                    <button type="button" class="savePersonalInfo">不通过</button>\
                    <button type="button" class="goNext pickConfirm">通过</button>\
                </div>';

                var button = '<button type="button" class="btn btn-lg btn-default popup-cancel">取消</button>\
                    <button type="button" class="btn btn-lg btn-submit popup-confirm">完成</button>';

                temp = doT.template(temp);
                this.bottom.html(temp(''));

                button = doT.template(button);
                this.popup.find('.suggest-button').html(button());
            },

            editPlan: function (btn) {
                var _this = btn.data.fun;
                _this.flag = 3;
                _this.popup.find('.suggest-plan').removeClass('hidden');
                _this.popup.find('.suggest-text').addClass('hidden');
                common.popup('.popup-suggest');
            },

            tapReturn: function () {
                return window.location.href = '#page=' + $(this).data('type');
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
                        _this.getTalentPlan(response.data.talentPlan);
                        _this.initTalent = response.data.talentPlan;
                    }
                }, function (error) {
                });
            },

            initData: function () {
                var _this = this;
                var flowItemId = _this.personnelInfo.progress == 2 ? 1 : _this.personnelInfo.progress == 3 ? 2 :_this.personnelInfo.progress == 4 ? 3 : 1;
                var paramData = {
                    url: '/paper/customer/paper/getPaperDetailVo',
                    contentType: 'application/x-www-form-urlencoded',
                    type: 'POST',
                    data: {
                        personnelId: _this.personnelInfo.personnelId,
                        flowItemId: flowItemId
                    }
                };
                
                Interface.getAsynData(paramData, function (response) {
                    if(response.code === '1' && response.data.length > 0) {
                        var totalScore = 0, score = 0, paperList = [];
                        for(var i = 0; i < response.data.length; i++) {
                            var typeList = {}, nowScore = 0, nowTotalPoints = 0, objArray = [];
                            for(var j = 0; j < response.data[i].paperInfoList.length; j++) {
                                var obj = {};
                                score += response.data[i].paperInfoList[j].score;
                                totalScore += response.data[i].paperInfoList[j].totalScore;
                                obj['paperItemDesc'] = response.data[i].paperInfoList[j].paperItemDesc;
                                obj['paperItemName'] = response.data[i].paperInfoList[j].paperItemName;
                                obj['score'] = response.data[i].paperInfoList[j].score;
                                obj['totalScore'] = response.data[i].paperInfoList[j].totalScore;
                                nowScore += response.data[i].paperInfoList[j].score;
                                nowTotalPoints += response.data[i].paperInfoList[j].totalScore;
                                objArray.push(obj);
                                typeList['objArray'] = objArray;
                            }
                            typeList['paperName'] = response.data[i].paperName;
                            typeList['nowScore'] = nowScore;
                            typeList['nowTotalPoints'] = nowTotalPoints;
                            typeList['evaluate'] = response.data[i].evaluate;
                            paperList.push(typeList);
                        }
                        var render = {
                            totalScore: totalScore,
                            score: score,
                            paperList: paperList,
                            progress: _this.personnelInfo.progress
                        };

                        _this.obj.html(template(render));
                        _this.state.find('.hidden').removeClass('hidden');

                        var progress = score / totalScore;
                        $('.get-rang-son').width($('.get-rang').width() * progress);

                        // _this.getDeclaration();
                    }
                }, function (error) {
                    _this.obj.html(template(''));
                });
            },

            togglePaper: function () {
                var parent = $(this).parents('.preson-bg');
                if($(parent).hasClass('hide-paper')) {
                    $(parent).removeClass('hide-paper');
                    $(this).addClass('bg-arrow-down');
                } else {
                    $(parent).addClass('hide-paper');
                    $(this).removeClass('bg-arrow-down');
                }
            },

            noPass: function (btn) {
                var _this = btn.data.fun;
                _this.flag = 2;
                _this.popup.find('.suggest-plan').addClass('hidden');
                _this.popup.find('.suggest-text').removeClass('hidden');
                if(_this.initTalent !== _this.popup.find('select[name=talentPlan]').val()) {
                    _this.showDeclaration();
                } else {
                    common.popup('.popup-suggest');
                }
            },

            pickConfirm: function (btn) {
                var _this = btn.data.fun;
                _this.flag = 1;
                _this.popup.find('.suggest-plan').addClass('hidden');
                _this.popup.find('.suggest-text').removeClass('hidden');
                if(_this.initTalent !== _this.popup.find('select[name=talentPlan]').val()) {
                    _this.showDeclaration();
                } else {
                    common.popup('.popup-suggest');
                }
            },

            submitPassData: function (btn) {
                var _this = btn.data.fun;
                if(_this.flag === 3) {
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
                var progress = _this.personnelInfo.progress;
                var status = null;
                if(progress === '3') {
                    status = 4;
                } else {
                    status = 5;
                }
                var paramData = {
                    ifOpenLoading: true,
                    type: "post",
                    url: '/personnel/customer/personnel/updatePersonnel',
                    contentType: 'application/x-www-form-urlencoded',
                    data: {
                        personnelStatus: status,
                        personnelId: _this.personnelInfo.personnelId,
                        flowActionId: status,
                        processingDesc: textArea,
                        talentPlan: _this.popup.find('select[name=talentPlan]').val() || '',
                        processingStatus: _this.flag === 1 ? 1 : _this.flag === 2 ? 2 : 3 // 1:通过 2:不通过 3:修改人才计划
                    }
                };
                Interface.getAsynData(paramData, function (response) {
                    if(response.code === '1') {
                        common.closePopup('.popup-suggest');
                        if(_this.flag === 1) {
                            return window.location.href = '#page=main';
                        } else if(_this.flag === 2){
                            $('.popup-bg').show();
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
                })
            },

            cancelPopup: function () {
                common.closePopup('.popup-suggest');
            },

            showDeclaration: function () {
                common.popup('.popup-suggest');
            }
        };
        
        funHomePage.init(opt);// 把$('.main')这个dom元素传过来

    }
});