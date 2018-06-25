define(function (require, exports, module) {
    require('echarts');
    require('swiper');
    require('dropload');
    var template = require('./add-recruit.html');

    template = doT.template(template);

    exports.back = function () {
        return '';
    };

    exports.show = function (opt) {

        var funHomePage = {
            init: function (opt) {
                this.obj = opt.body;
                this.bottom = opt.bottom;
                this.foldArray = []; // 侧边栏收缩
                this.unfoldArray = []; // 侧边栏展开
                this.pageOfRecruit = 1; // 招聘分页
                this.pageOfAdd = 1; // 增员分页
                this.pageOfSign = 1; // 增员分页
                this.pageSize = 15; // 每页展示个数
                this.globalFlag = 'pageOfAdd';
                this.userInfo = common.getLocalStorage("userInfo", true) || '';
                if(!(this.userInfo && this.userInfo.id)) {
                    common.alertCreate({
                        html: '1123'
                    });
                }

                this.obj.html(template(''));
                this.getMyAddMember();

                // 底部按钮
                this.renderBottom();

                // 初始化请求招聘总数
                this.getTotal('/flow/customer/interviewFlow/PersonnelpaperSearchbyParam', 1);

                // 初始化请求签约总数
                this.getTotal('/flow/customer/interviewFlow/PersonnelSignSearchbyParam', 2);

                // 在线增员
                this.bottom.off('tap', '.bot-button .goToAdd');
                this.bottom.on('tap', '.bot-button .goToAdd', {fun: this}, this.goToAdd);


                // 增员、招聘列表切换事件
                this.obj.off('tap', '.toggle-table-cond p:not(.active)');
                this.obj.on('tap', '.toggle-table-cond p:not(.active)', {fun: this}, this.toggleTableList);

                // 列表下icon-search点击事件
                this.obj.off('tap', 'div.go-to-next span');
                this.obj.on('tap', 'div.go-to-next span', {fun: this}, this.goToInterview);
            },

            getTotal: function (url, total) {
                var _this = this;
                var paramData = {
                    url: url,
                    data: {
                        agentCode:  _this.userInfo.id,
                        orgId: _this.userInfo.companyId,
                        pageNo: 1,
                        pageSize: 10
                    }
                };

                Interface.getAsynData(paramData, function (response) {
                    if(response.code === '1' && response.data.records.length > 0) {
                        if(total === 1) {
                            $('.toggle-table-cond p:nth-child(2) span').text(response.data.total);
                        } else if (total === 2) {
                            $('.toggle-table-cond p:last-child span').text(response.data.total);
                        }
                    }
                }, function (error) {
                    console.log(error);
                })
            },

            dropMyRecruitment: function () {
                var _this = this;
                _this.pageOfRecruit = 1;
                _this.pageOfSign = 1;
                var dropAdd = $('.recruit-clientInfo');
                dropAdd.unbind('scroll');
                dropAdd.dropload({
                    scrollArea: dropAdd,
                    loadDownFn: function (me) {
                        var paramData = {
                            url: '/flow/customer/interviewFlow/PersonnelpaperSearchbyParam',
                            data: {
                                agentCode:  _this.userInfo.id,
                                orgId: _this.userInfo.companyId,
                                pageNo: _this.pageOfRecruit,
                                pageSize: _this.pageSize
                            }
                        };

                        Interface.getAsynData(paramData, function (response) {
                            if (response.code === '1' && response.data.records.length > 0) {
                                if(_this.globalFlag !== 'pageOfRecruit') {
                                    return false;
                                }
                                _this.pageOfRecruit++;
                                $('.toggle-table-cond p:nth-child(2) span').text(response.data.total);
                                var temp = '{{~it:value:index}}\
                                     <div class="mock-row">\
                                            <div class="mock-cell">{{=value.name || ""}}</div>\
                                            <div class="mock-cell">{{=value.sex === "M" ? "男" : value.sex === "F" ? "女" : ""}}</div>\
                                            <div class="mock-cell">{{=value.age && value.age >=0 ? value.age : ""}}</div>\
                                            <div class="mock-cell">{{=value.cellphone || ""}}</div>\
                                            <div class="mock-cell">{{=value.talentPlan || ""}}</div>\
                                            <div class="mock-cell">{{=value.processProgress || ""}}/{{=value.countProgress || ""}}</div>\
                                            <div class="mock-cell go-to-next"  data-personnelId="{{=value.id}}" data-status="{{=value.currentprogress}}">\
                                                <span>{{=value.currentprogress==1?"资料录入":value.currentprogress==2?"初审面试":value.currentprogress==3?"甄选面试":value.currentprogress==4?"决定面试":value.currentprogress==5?"班级报名":value.currentprogress==6?"培训考试":value.currentprogress==7?"签约":"未知错误"}}</span>\
                                            </div>\
                                        </div>{{~}}\
                                     </div>';

                                temp = doT.template(temp);

                                $('.scroll-add-member').append(temp(response.data.records));
                                if(response.data.records.length < _this.pageSize) {
                                    me.lock();
                                    me.noData();
                                    me.$domDown.hide();
                                }
                            } else {
                                me.lock();
                                me.noData();
                                if (_this.pageOfRecruit > 1) {
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
            },

            dropMyAddMember: function () {
                var _this = this;
                _this.pageOfAdd = 1;
                _this.pageOfSign = 1;
                var dropAdd = $('.recruit-clientInfo');
                dropAdd.unbind('scroll');
                dropAdd.dropload({
                    scrollArea: dropAdd,
                    loadDownFn: function (me) {
                        var paramData = {///flow/customer/interviewFlow/findPersonnelbyagentCode
                            url: '/flow/customer/interviewFlow/findPersonnelbyagentCode',
                            data: {
                                agentCode: _this.userInfo.id,
                                orgId: _this.userInfo.companyId,
                                pageNo: _this.pageOfAdd,
                                pageSize: _this.pageSize
                            }
                        };

                        Interface.getAsynData(paramData, function (response) {
                            if (response.code === '1' && response.data.records.length > 0) {
                                if(_this.globalFlag !== 'pageOfAdd') {
                                    return false;
                                }
                                _this.pageOfAdd++;
                                $('.toggle-table-cond p:first-child span').text(response.data.total);
                                var temp = '{{~it:value:index}}\
                                    <div class="mock-row">\
                                            <div class="mock-cell">{{=value.name || ""}}</div>\
                                            <div class="mock-cell">{{=value.sex === "M" ? "男" : value.sex === "F" ? "女" : ""}}</div>\
                                            <div class="mock-cell">{{=value.age && value.age >=0 ? value.age : ""}}</div>\
                                            <div class="mock-cell">{{=value.cellphone || ""}}</div>\
                                            <div class="mock-cell">{{=value.talentPlan || ""}}</div>\
                                            <div data-count="{{=value.countProgress}}" class="mock-cell">{{=value.processProgress || ""}}/{{=value.countProgress || ""}}</div>\
                                            <div class="mock-cell go-to-next" data-personnelId="{{=value.id}}" data-pesonalStatus="{{=value.pesonalStatus}}" data-status="{{=value.currentprogress}}">\
                                                <span>{{=value.currentprogress==1?"资料录入":value.currentprogress==2?"初审面试":value.currentprogress==3?"甄选面试":value.currentprogress==4?"决定面试":value.currentprogress==5?"班级报名":value.currentprogress==6?"培训考试":value.currentprogress==7?"签约":"不通过"}}</span>\                                            </div>\
                                    </div>{{~}}\
                                </div>';

                                temp = doT.template(temp);
                                $('.scroll-add-member').append(temp(response.data.records));
                                if(response.data.records.length < _this.pageSize) {
                                    me.lock();
                                    me.noData();
                                    me.$domDown.hide();
                                }
                            } else {
                                me.lock();
                                me.noData();
                                if (_this.pageOfAdd > 1) {
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
            },

            dropSignList: function () {
                var _this = this;
                _this.pageOfAdd = 1;
                _this.pageOfRecruit = 1;
                var dropSign = $('.recruit-clientInfo');
                dropSign.unbind('scroll');
                dropSign.dropload({
                    scrollArea: dropSign,
                    loadDownFn: function (me) {
                        var signParam = {
                            url: '/flow/customer/interviewFlow/PersonnelSignSearchbyParam',
                            data: {
                                agentCode: _this.userInfo.id,
                                orgId: _this.userInfo.companyId,
                                pageNo: _this.pageOfSign,
                                pageSize: _this.pageSize
                            }
                        };

                        Interface.getAsynData(signParam, function (response) {
                            if (response.code === '1' && response.data.records.length > 0) {
                                if (_this.globalFlag !== 'pageOfSign') {
                                    return false;
                                }
                                _this.pageOfSign++;
                                $('.toggle-table-cond p:last-child span').text(response.data.total);

                                var temp = '{{~it:value:index}}\
                                    <div class="mock-row">\
                                            <div class="mock-cell">{{=value.name || ""}}</div>\
                                            <div class="mock-cell">{{=value.sex === "M" ? "男" : value.sex === "F" ? "女" : ""}}</div>\
                                            <div class="mock-cell">{{=value.age && value.age >=0 ? value.age : ""}}</div>\
                                            <div class="mock-cell">{{=value.cellphone || ""}}</div>\
                                            <div class="mock-cell">{{=value.talentPlan || ""}}</div>\
                                            <div data-count="{{=value.countProgress}}" class="mock-cell">{{=value.processProgress || ""}}/{{=value.countProgress || ""}}</div>\
                                            <div class="mock-cell go-to-next" data-personnelId="{{=value.id}}" data-pesonalStatus="{{=value.pesonalStatus}}" data-status="{{=value.currentprogress}}">\
                                                <span>{{=value.currentprogress==1?"资料录入":value.currentprogress==2?"初审面试":value.currentprogress==3?"甄选面试":value.currentprogress==4?"决定面试":value.currentprogress==5?"班级报名":value.currentprogress==6?"培训考试":value.currentprogress==7?"签约":"未知错误"}}</span>\                                            </div>\
                                    </div>{{~}}\
                                </div>';

                                temp = doT.template(temp);

                                $('.scroll-add-member').append(temp(response.data.records));
                                if(response.data.records.length < _this.pageSize) {
                                    me.lock();
                                    me.noData();
                                    me.$domDown.hide();
                                }
                            } else {
                                me.lock();
                                me.noData();
                                if (_this.pageOfSign > 1) {
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
            },

            getMyAddMember: function () {
                var _this = this;
                if ($('.dropload-down').size() > 0) {
                    $(".dropload-down").remove();
                }
                $('.scroll-add-member').html('');
                _this.dropMyAddMember();
            },

            getMyRecruitment: function () {
                var _this = this;
                if ($('.dropload-down').size() > 0) {
                    $(".dropload-down").remove();
                }
                $('.scroll-add-member').html('');
                _this.dropMyRecruitment();
            },

            getSignList: function () {
                var _this = this;
                if ($('.dropload-down').size() > 0) {
                    $(".dropload-down").remove();
                }
                $('.scroll-add-member').html('');
                _this.dropSignList();
            },

            toggleTableList: function (btn) {
                var _this = btn.data.fun;
                var index = $('.toggle-table-cond p').index(this);
                $(this).addClass('active').siblings().removeClass('active');
                if(index === 1) {
                    // 获取我的招聘信息
                    _this.pageOfAdd = 1;
                    _this.pageOfSign = 1;
                    _this.globalFlag = 'pageOfRecruit';
                    _this.getMyRecruitment();
                } else if(index === 2) {
                    // 获取签约报名信息
                    _this.pageOfAdd = 1;
                    _this.pageOfRecruit = 1;
                    _this.globalFlag = 'pageOfSign';
                    _this.getSignList();
                } else {
                    // 获取我的增员信息
                    _this.pageOfRecruit = 1;
                    _this.pageOfSign = 1;
                    _this.globalFlag = 'pageOfAdd';
                    _this.getMyAddMember();
                }
            },

            goToInterview: function (btn) {
                var _this = btn.data.fun;
                var index=$(this).parents(".list-recruit-add").prev().children("p.active").index();
                var obj={};
                var status = $(this).parents('.go-to-next').data('status').toString();
                obj.personnelId = $(this).parent().data("personnelid");
                obj.progress = status;
                common.setLocalStorage("personnelInfo", obj, true);
                if(!index&&status!=1) {
                    return window.location.href = '#page=select';
                }else{
                    switch (status) {
                        case '1':
                            return window.location.href = '?personnelStatus=' + status + '&personnelId='+obj.personnelId + '#page=personnelInfo';
                        case '2':
                            return window.location.href = '?personnelStatus=' + status + '&personnelId='+obj.personnelId +'#page=reMaterials';
                        case '3':
                        case '4':
                            return window.location.href = '?personnelStatus=' + status + '&personnelId='+obj.personnelId +'#page=basicInfo';
                        case '5':
                            return window.location.href = '?personnelStatus=' + status + '&personnelId='+obj.personnelId +'#page=mustStart';
                        case '6':
                        case '7':
                        default:
                            break;
                    }
                }
            },

            goToAdd: function () {
                return window.location.href = '#page=personnelInfo';
            },

            renderBottom: function () {
                this.bottom.removeClass('bot-button').addClass('bot-button');
                var temp = '<div class="buttonGroup">\
                    <button type="button"></button>\
                    <button type="button" class="goNext goToAdd">在线增员</button>\
                </div>';
                temp = doT.template(temp);
                this.bottom.html(temp());
            }
        };
        funHomePage.init(opt);
    }
});