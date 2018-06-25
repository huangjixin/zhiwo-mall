/**
 * Created by c_mengchaopeng on 2018/1/26.
 */
define(function(require, exports, module){
    var template = require('./target.html');
    template = doT.template(template);

    exports.back = function() {
        return '';
    };

    exports.show = function(opt) {

        var funHomePage = {
            init : function(opt){

                this.obj = opt.body;
                this.bottom = opt.bottom;
                this.months = [];
                for(var i = 0; i < 12; i++) {
                    this.months.push({
                        targetYear: new Date().getFullYear(),
                        targetValue: 0,
                        id: ''
                    });
                }
                this.global = false;
                this.setTarget = null;
                this.systemYear = '';
                this.userInfo = common.getLocalStorage('userInfo', true) || {};

                // 获取服务器时间
                this.getSystemTime();
                this.renderBottom();

                // 设置当前月份目标
                this.obj.off('tap', '.target-month .set-target');
                this.obj.on('tap', '.target-month .set-target', {fun: this}, this.setMonthTarget);

                // back
                this.bottom.off('tap', '.back');
                this.bottom.on('tap', '.back', {fun: this}, this.goBack);

                // 提交当前年份下的所有月目标
                this.bottom.off('tap', '.bot-button .commit-target');
                this.bottom.on('tap', '.bot-button .commit-target', {fun: this}, this.commitTarget);

                // 下一年
                this.obj.off('tap', '.target-content .plus');
                this.obj.on('tap', '.target-content .plus', {fun: this}, this.nextYear);

                // 上一年
                this.obj.off('tap', '.target-content .sub');
                this.obj.on('tap', '.target-content .sub', {fun: this}, this.prevYear);

                // 取消设置月目标
                this.obj.off('tap', '.edit-target .btn-default');
                this.obj.on('tap', '.edit-target .btn-default', {fun: this}, this.cancelSetTarget);

                // 确定设置月目标
                this.obj.off('tap', '.edit-target .btn-submit');
                this.obj.on('tap', '.edit-target .btn-submit', {fun: this}, this.confirmSetTarget);

            },
            renderBottom: function () {
                this.bottom.removeClass('bot-button').addClass('bot-button');
                var temp = '<div class="buttonGroup">\
                    <button type="button" class="savePersonalInfo back">返回</button>\
                    <button type="button" class="goNext commit-target">提交</button>\
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
                    if(response.code === '1' && response.data) {
                        var currentTime = new Date(response.data);
                        _this.systemYear = currentTime.getFullYear();
                        _this.getAgentTarget();
                    }
                }, function (error) {
                    console.log(error);
                });
            },
            nextYear: function (btn) {
                var _this = btn.data.fun;
                var year = $(this).siblings('.show-year').data('year');
                $('.show-year').text((parseInt(year) + 1) + '年').attr('data-year', (parseInt(year) + 1));
                _this.getAgentTarget()
            },
            prevYear: function (btn) {
                var _this = btn.data.fun;
                var year = $(this).siblings('.show-year').data('year');
                $('.show-year').text((parseInt(year) - 1) + '年').attr('data-year', (parseInt(year) - 1));
                _this.getAgentTarget()
            },
            setMonthTarget: function (btn) {
                var _this = btn.data.fun;
                var month = $(this).parents('li').data('month');
                _this.setTarget = $(this).parents('li');
                $('.edit-target h2 span').text(month);
                common.popup('.edit-target');
            },
            cancelSetTarget: function () {
                common.closePopup('.edit-target');
            },
            confirmSetTarget: function (btn) {
                var _this = btn.data.fun;
                var value = $('.edit-target .month-target').val();
                if(parseInt(value) >= 0) {
                    _this.global = true;
                    $(_this.setTarget).find('.each-preson span').text(value);
                }
                common.closePopup('.edit-target');
            },
            // 更新默认年份
            updateYear: function () {
                if($('.show-year').data('year')) {
                    for(var i = 0; i < this.months.length; i++) {
                        this.months[i].targetYear = $('.show-year').data('year');
                    }
                }
            },
            getAgentTarget: function () {
                var _this = this;
                var paramData = {
                    ifOpenLoading : true,
                    url: '/security/customer/target/selectbyaccountId',
                    type: 'POST',
                    contentType: 'application/x-www-form-urlencoded',
                    data: {
                        accountId: _this.userInfo.id,
                        targetYear: $('.show-year').data('year') || _this.systemYear
                    }
                };

                Interface.getAsynData(paramData, function (response) {
                    if(response.code === '1' && response.data.length > 0) {
                        if($('.show-year').data('year')) {
                            var temp = '{{~it:value:index}}\
                                <li data-month="{{=index + 1}}" data-id="{{=value.id}}">\
                                     <p class="each-month">\
                                        <strong>{{=index + 1}}月</strong><i class="icon-44 set-target"></i>\
                                    </p>\
                                    <p class="each-preson">计划增员<span>{{=value.targetValue}}</span>人</p>\
                                </li>{{~}}';

                            temp = doT.template(temp);

                            $('.target-content .target-month').html(temp(response.data));
                        } else {
                            _this.obj.html(template(response.data));
                        }
                    } else {
                        _this.updateYear();
                        _this.obj.html(template(_this.months));
                    }
                }, function (error) {
                    console.log(error);
                    _this.updateYear();
                    _this.obj.html(template(_this.months));
                });
            },
            goBack: function () {
                return window.location.href = '#page=main';
            },
            commitTarget: function (btn) {
                var _this = btn.data.fun;
                if(!_this.global) {
                    common.alertCreate({
                        html: '您还未修改任何月目标！'
                    });
                    return false;
                }
                var fullYearTarget = [];
                var date = new Date();
                $('.target-content .target-month li').each(function () {
                    var target = {
                        targetTime: $(this).data('month'),
                        targetValue: parseInt($(this).find('.each-preson span').text()),
                        accountId: _this.userInfo.id,
                        createTime: date,
                        createUser: '1',
                        targetYear:  $('.show-year').data('year'),
                        id: $(this).data('id')
                    };
                    fullYearTarget.push(target);
                });

                var paramData = {
                    ifOpenLoading : true,
                    url: '/security/customer/target/addandupdate',
                    origin: '/security/',
                    type: 'POST',
                    data: JSON.stringify(fullYearTarget)
                };

                Interface.getAsynData(paramData, function (response) {
                    if(response.code === '1') {
                        common.alertCreate({
                            html: '设置目标成功！'
                        });
                    }
                }, function (error) {
                    console.log(error);
                });
            }
        };
        funHomePage.init(opt);

    }
});