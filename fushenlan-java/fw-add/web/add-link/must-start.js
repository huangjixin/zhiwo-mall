define(function (require, exports, module) {
    var template = require('./must-start.html');
    template = doT.template(template);

    exports.back = function () {
        return '';
    };

    exports.show = function (opt) {

        var funHomePage = {
            init: function (opt) {

                this.obj = opt.body;
                this.personnelInfo = common.getLocalStorage('personnelInfo', true) || '';
                this.userInfo = common.getLocalStorage('userInfo', true);
                this.personnelId = '';
                this.currentTime = '';
                this.orgId = '';
                if(this.personnelInfo && this.personnelInfo.personnelId && this.userInfo && this.userInfo.companyId) {
                    this.personnelId = this.personnelInfo.personnelId;
                    this.orgId = this.userInfo.companyId;
                    this.render();
                }

                //马上报名
                this.obj.off('tap', '.registerNow:not(.finished)');
                this.obj.on('tap', '.registerNow:not(.finished)', {fun: this}, this.register);
            },
            setResidue: function () {
                var _this = this;
                setInterval(function () {
                    _this.currentTime += 1000;
                    $('.OA_course_details li').each(function (index, item) {
                        var endTime = new Date($(item).find('.endDate').text()).getTime();
                        $(item).find('.time_more span.residue').text(_this.fillTime(endTime, _this.currentTime));
                    });
                }, 1000);
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
                        _this.currentTime = response.data;
                        $('.OA_course_details li').each(function (index, item) {
                            var endTime = new Date($(item).find('.endDate').text()).getTime();
                            $(item).find('.time_more span.residue').text(_this.fillTime(endTime, response.data));
                        });
                        _this.setResidue();
                    }
                }, function (error) {
                    console.log(error);
                });
            },
            fillTime: function ($li, timeStamp) {
                var time = this.countDown($li, timeStamp);
                if(time) {
                    return  time.days + '天' + '，' + time.hours + ':' + time.minutes + ':' + time.seconds;
                } else {
                    return '';
                }
            },
            register: function (btn) {
                var _this = btn.data.fun;
                var that = this;
                var json = {
                    planId: $(this).data('id'),
                    personnelId: _this.personnelId,
                    orgId: _this.orgId
                };
                var paramData = {
                    type: "POST",
                    url: '/plan/customer/classPlan/enterForEr',
                    data: JSON.stringify(json)
                };
                Interface.getAsynData(paramData, function (response) {
                    if(response.code === '1' && response.data) {
                        if(response.msg === '账号已存在') {
                            $(that).html('报名成功！').addClass('finished');
                            return false;
                        }
                        common.alertCreate({
                            html: '学习平台账号：' + response.data.accountName + '，密码：' + response.data.password,
                            callback: function () {
                                $(that).html('报名成功！').addClass('finished');
                                return window.location.href = '#page=main';
                            }
                        });
                    }
                }, function (error) {
                })
            },
            countDown: function (endTime, nowDate) {
                var _this = this;
                var leftTime = endTime - nowDate;
                if(leftTime < 0) {
                    return '';
                }
                var days = Math.floor(leftTime / (24 * 3600 * 1000)).toString();
                var hourLeft = leftTime % (24 * 60 * 60 * 1000);
                var hours = Math.floor(hourLeft / (3600 * 1000)).toString();
                var minuetLeft = hourLeft % (3600 * 1000);
                var minutes = Math.floor(minuetLeft / (60 * 1000)).toString();
                var secondLeft = minuetLeft % (60 * 1000);
                var seconds = Math.round(secondLeft / 1000).toString();
                return {
                    days: _this.formatTime(days, 2),
                    hours: _this.formatTime(hours, 2),
                    minutes: _this.formatTime(minutes, 2),
                    seconds: _this.formatTime(seconds, 2)
                };
            },
            render: function () {
                var _this = this;
                var planRequestDto = {
                    pageNo: 1,
                    pageSize: 10,
                    userId: 1
                };
                var paramData = {
                    type: "post",
                    url: '/plan/customer/classPlan/list',
                    data: JSON.stringify(planRequestDto)
                };
                Interface.getAsynData(paramData, function (response) {
                    if (response.code === '1' ) {
                        _this.obj.html(template(response.data.records));
                        _this.getSystemTime();
                    }
                }, function (error) {
                })
            },
            formatTime: function (str, len) {
                var index = str.length;
                while (index < len) {
                    str = '0' + str;
                    index++;
                }
                return str;
            }
        };

        funHomePage.init(opt);
    }
});