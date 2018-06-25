define(function (require, exports, module) {
    require('echarts');
    require('swiper');
    require('dropload');
    var template = require('./main.html');

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
                this.pageOfSign = 1; // 面试
                this.pageSize = 5; // 每页展示个数
                this.globalFlag = 'pageOfAdd';
                this.userInfo = common.getLocalStorage("userInfo", true) || '';
                this.companyId = this.userInfo?this.userInfo.companyId:1;
                // 首页交互功能模块
                this.initPage = require('./interaction.js');

                this.obj.html(template(''));

                this.initData();
                this.renderBottom();

                // 个人、团队切换事件
                this.obj.off('tap', '.btn-control-team button.control:not(.active)');
                this.obj.on('tap', '.btn-control-team button.control:not(.active)', {fun: this}, this.initPage.init.toggleTeamOrSingle);

                // 年、月时间切换事件
                this.obj.off('tap', '.schedule-time div:not(.active)');
                this.obj.on('tap', '.schedule-time div:not(.active)', {fun: this}, this.initPage.init.toggleTime);

                // 增员、招聘列表切换事件
                this.obj.off('tap', '.toggle-table-cond p:not(.active)');
                this.obj.on('tap', '.toggle-table-cond p:not(.active)', {fun: this}, this.toggleTableList);

                // 列表下icon-search点击事件
                this.obj.off('tap', 'div.go-to-next span');
                this.obj.on('tap', 'div.go-to-next span', {fun: this}, this.initPage.init.goToInterview);

                // 列表下icon-search点击事件
                this.obj.off('tap', 'div.search i');
                this.obj.on('tap', 'div.search i', {fun: this}, this.initPage.init.slideToLeft);

                // 列表search事件
                this.obj.off('tap', '.btn-group .confirm-ok');
                this.obj.on('tap', '.btn-group .confirm-ok', {fun: this}, this.searchRecruit);

                // 列表cancel事件
                this.obj.off('tap', '.btn-group .confirm-no');
                this.obj.on('tap', '.btn-group .confirm-no', {fun: this}, this.initPage.init.slideToRight);

                // 目标
                this.obj.off('tap', '.btn-control-team .write');
                this.obj.on('tap', '.btn-control-team .write', {fun: this}, this.initPage.init.goToTarget);

            },

            initData: function () {
                // 初始化加检测侧边栏状态，更改最新状态
                this.checkSlideBarStatus();

                // 获取代理人信息
                this.getAgentInfo();

                // 获取最新动态
                this.getNewMessage();

                // 获取我的增员信息
                this.getMyAddMember();

                // 获取进度及排名信息
                this.getProgressRank();

                // 初始化请招聘总数
                this.getTotal('/flow/customer/interviewFlow/PersonnelpaperSearchbyParam', 1);

                // 初始化请求签约总数
                this.getTotal('/flow/customer/interviewFlow/PersonnelSignSearchbyParam', 2);

                // 初始化默认读取代理人月目标
                this.initPage.init.getSingleTarget();

                // 绘制仪表盘
                this.initPage.init.drawGaugeSchedule();

            },

            checkSlideBarStatus: function () {
                if (slideBar) {
                    $('.recent-state .full-slide').addClass('goTop');
                    $('.recent-state .half-slide').addClass('goBot');
                } else {
                    $('.recent-state .full-slide').removeClass('goTop');
                    $('.recent-state .half-slide').removeClass('goBot');
                }
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
                } else if(index === 0) {
                    // 获取我的增员信息
                    _this.pageOfRecruit = 1;
                    _this.pageOfSign = 1;
                    _this.globalFlag = 'pageOfAdd';
                    _this.getMyAddMember();
                } else {
                    // 获取签约报名信息
                    _this.pageOfAdd = 1;
                    _this.pageOfRecruit = 1;
                    _this.globalFlag = 'pageOfSign';
                    _this.getSignList();
                }
            },

            getAgentInfo: function () {
                var _this =this;
                var paramData = {
                    ifOpenLoading: true,
                    url: '/flow/customer/interviewFlow/findbyaccountId',
                    type: 'POST',
                    contentType: 'application/x-www-form-urlencoded',
                    data: {
                        accountId: _this.userInfo.id
                    }
                };
                Interface.getAsynData(paramData, function (response) {
                    if (response.code === '1' && response.data) {
                        var temp = '<h1 class="client-name">{{=it.accountName}}</h1>\
                        <p class="client-job-level">\
                            <span>{{=it.companyName || ""}}</span><span>{{=it.postType || ""}}</span>\
                        </p>\
                        <p class="client-team-scale">\
                            <i class="icon-tuandui"></i> <span>团队规模</span>{{=it.teamSize}}人\
                        </p>\
                        <div class="client-img">\
                            <img src="../../images/client.png" alt="">\
                        </div>';

                        temp = doT.template(temp);

                        $('.client-info').html(temp(response.data));
                    }
                }, function (error) {
                    console.log(error);
                });
            },

            getNewMessage: function () {
                var _this = this;
                var paramData = {
                    url: '/security/customer/target/newsmessage',
                    type: 'POST',
                    contentType: 'application/x-www-form-urlencoded',
                    data: {
                        accountId:  _this.userInfo.id
                    }
                };

                Interface.getAsynData(paramData, function (response) {
                    if (response.code === '1' && response.data.length > 0) {
                        _this.foldArray = _this.initPage.init.arrayGroup(response.data, 3);
                        _this.unfoldArray = _this.initPage.init.arrayGroup(response.data, 2);

                        var temp = '{{~it:value:index}}\
                            <div class="swiper-slide">\
                                <ul class="full-screen">\
                                    {{~value:val:key}}\
                                        <li>\
                                            <i class="icon-xiaoxitongzhifill"></i>{{=val.content}}。\
                                        </li>\
                                    {{~}}\
                                </ul>\
                            </div>\
                        {{~}}';

                        var _temp = '{{~it:value:index}}\
                            <div class="swiper-slide">\
                                <ul class="half-screen">\
                                    {{~value:val:key}}\
                                        <li>\
                                            <i class="icon-xiaoxitongzhifill"></i>{{=val.content}}。\
                                        </li>\
                                    {{~}}\
                                </ul>\
                            </div>\
                        {{~}}';

                        temp = doT.template(temp);

                        _temp = doT.template(_temp);

                        $('.full-slide .swiper-wrapper').html(temp(_this.foldArray));
                        $('.half-slide .swiper-wrapper').html(_temp(_this.unfoldArray));

                        _this.initSwiper(_this.foldArray.length > 1 ? true : false, _this.unfoldArray.length > 1 ? true : false);
                    }
                }, function (error) {
                    console.log(error);
                });

            },

            getMyRecruitment: function () {
                var _this = this;
                if ($('.dropload-down').size() > 0) {
                    $(".dropload-down").remove();
                }
                $('.scroll-add-member').html('');
                _this.dropMyRecruitment();
            },

            getMyAddMember: function () {
                var _this = this;
                if ($('.dropload-down').size() > 0) {
                    $(".dropload-down").remove();
                }
                $('.scroll-add-member').html('');
                _this.dropMyAddMember();
            },

            getSignList: function () {
                var _this = this;
                if ($('.dropload-down').size() > 0) {
                    $(".dropload-down").remove();
                }
                $('.scroll-add-member').html('');
                _this.dropSignList();
            },

            searchRecruit:function (btn) {
                var _this = btn.data.fun;
                var index=$(this).parents(".search-box").siblings(".toggle-table-cond").find("p.active").index();
                var keyWord = $(this).parent().prev().find('input').val();
                if(index===1){
                    //我的招聘搜索
                    _this.pageOfSign = 1;
                    _this.pageOfAdd = 1;
                    _this.globalFlag = 'pageOfRecruit';
                    _this.dropRecruit(keyWord);
                }else if(index===2){
                    //我的签名搜索
                    _this.pageOfAdd = 1;
                    _this.pageOfRecruit = 1;
                    _this.pageOfSign = 1;
                    _this.globalFlag = 'pageOfSign';
                    _this.dropMySign(keyWord);
                }else{
                    //我的增员搜索
                    _this.pageOfRecruit = 1;
                    _this.pageOfSign = 1;
                    _this.globalFlag = 'pageOfAdd';
                    _this.dropMyAdd(keyWord);
                }
            },

            dropMySign: function (keyWord) {
                var _this = this;
                if ($('.dropload-down').size() > 0) {
                    $(".dropload-down").remove();
                }
                $('.scroll-add-member').html('');
                _this.dropMySignList(keyWord);
            },

            dropRecruit: function (keyWord) {
                var _this = this;
                if ($('.dropload-down').size() > 0) {
                    $(".dropload-down").remove();
                }
                $('.scroll-add-member').html('');
                _this.dropRecruitSearch(keyWord);
            },

            dropMyAdd: function (keyWord) {
                var _this = this;
                if ($('.dropload-down').size() > 0) {
                    $(".dropload-down").remove();
                }
                $('.scroll-add-member').html('');
                _this.dropMyAddSearch(keyWord);
            },

            dropRecruitSearch: function (keyWord) {
                var _this = this;
                if($('.dropload-down').size() > 0){
                    $(".dropload-down").remove();
                }
                $('.scroll-add-member').html('');
                var dropAdd = $('.recruit-clientInfo');
                dropAdd.unbind('scroll');
                dropAdd.dropload({
                    scrollArea: dropAdd,
                    loadDownFn: function (me) {
                        var paramData = {
                            url: '/flow/customer/interviewFlow/PersonnelpaperSearchbyParam',
                            data: {
                                agentCode: _this.userInfo.id,
                                orgId: _this.companyId,
                                pageNo: _this.pageOfAdd,
                                keyWord: keyWord,
                                pageSize: _this.pageSize
                            }
                        };
                        Interface.getAsynData(paramData, function (response) {
                            if (response.code === '1' && response.data.records.length > 0) {
                                if(_this.globalFlag !== 'pageOfRecruit') {
                                    return false;
                                }
                                _this.pageOfAdd++;
                                $('.toggle-table-cond p:nth-child(2) span').text(response.data.total);
                                var temp = '{{~it:value:index}}\
                                    <div class="mock-row">\
                                            <div class="mock-cell">{{=value.name || ""}}</div>\
                                            <div class="mock-cell">{{=value.sex === "M" ? "男" : value.sex === "F" ? "女" : ""}}</div>\
                                            <div class="mock-cell">{{=value.age && value.age >=0 ? value.age : ""}}</div>\
                                            <div class="mock-cell">{{=value.cellphone || ""}}</div>\
                                            <div class="mock-cell">{{=value.talentPlan || ""}}</div>\
                                            <div data-count="{{=value.currentprogress}}" class="mock-cell">{{=value.processProgress || ""}}/{{=value.countProgress || ""}}</div>\
                                            <div class="mock-cell go-to-next" data-personnelId="{{=value.id}}" data-pesonalStatus="{{=value.pesonalStatus}}" data-status="{{=value.currentprogress}}">\
                                                <span>{{=value.currentprogress==1?"资料录入":value.currentprogress==2?"初审面试":value.currentprogress==3?"甄选面试":value.currentprogress==4?"决定面试":value.currentprogress==5?"班级报名":value.currentprogress==6?"培训考试":value.currentprogress==7?"签约":"未知错误"}}</span>\                                            </div>\
                                    </div>{{~}}\
                                </div>';

                                temp = doT.template(temp);

                                $('.scroll-add-member').html(temp(response.data.records));
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

            dropMyAddSearch: function (keyWord) {
                var _this = this;
                _this.pageOfAdd = 1;
                if($('.dropload-down').size() > 0){
                    $(".dropload-down").remove();
                }
                $('.scroll-add-member').html('');
                var dropAdd = $('.recruit-clientInfo');
                dropAdd.unbind('scroll');
                dropAdd.dropload({
                    scrollArea: dropAdd,
                    loadDownFn: function (me) {
                        var paramData = {
                            url: '/flow/customer/interviewFlow/findPersonnelbyagentCode',
                            data: {
                                agentCode:  _this.userInfo.id,
                                orgId:_this.companyId,
                                pageNo: _this.pageOfRecruit,
                                keyWord: keyWord,
                                pageSize: _this.pageSize
                            }
                        };

                        Interface.getAsynData(paramData, function (response) {
                            if (response.code === '1' && response.data.records.length > 0) {
                                if(_this.globalFlag !== 'pageOfAdd') {
                                    return false;
                                }
                                _this.pageOfRecruit++;
                                $('.toggle-table-cond p:first-child span').text(response.data.total);
                                var temp = '{{~it:value:index}}\
                                    <div class="mock-row">\
                                            <div class="mock-cell">{{=value.name || ""}}</div>\
                                            <div class="mock-cell">{{=value.sex === "M" ? "男" : value.sex === "F" ? "女" : ""}}</div>\
                                            <div class="mock-cell">{{=value.age && value.age >=0 ? value.age : ""}}</div>\
                                            <div class="mock-cell">{{=value.cellphone || ""}}</div>\
                                            <div class="mock-cell">{{=value.talentPlan || ""}}</div>\
                                            <div data-count="{{=value.currentprogress}}" class="mock-cell">{{=value.processProgress || ""}}/{{=value.countProgress || ""}}</div>\
                                            <div class="mock-cell go-to-next" data-personnelId="{{=value.id}}" data-pesonalStatus="{{=value.pesonalStatus}}" data-status="{{=value.currentprogress}}">\
                                                <span>{{=value.currentprogress==1?"资料录入":value.currentprogress==2?"初审面试":value.currentprogress==3?"甄选面试":value.currentprogress==4?"决定面试":value.currentprogress==5?"班级报名":value.currentprogress==6?"培训考试":value.currentprogress==7?"签约":"不通过"}}</span>\                                            </div>\
                                    </div>{{~}}\
                                </div>';

                                temp = doT.template(temp);

                                $('.scroll-add-member').html(temp(response.data.records));
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

            dropMySignList: function (keyWord) {
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
                                orgId: _this.companyId,
                                pageNo: _this.pageOfSign,
                                pageSize: _this.pageSize,
                                keyWord:keyWord
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

                                $('.scroll-add-member').html(temp(response.data.records));
                                if(response.data.records.length < _this.pageSize) {
                                    me.lock();
                                    me.noData();
                                    me.$domDown.hide();
                                }
                            } else {
                                me.lock();
                                me.noData();
                                if (_this.pageOfSign >= 1) {
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

            getProgressRank: function () {
                var _this = this;
             	if(_this.userInfo.postType==3){
            		$(".btn-control-team button").eq(1).addClass("disabled");
					$(".btn-control-team button").eq(1).css({"background":"#dddddd"})
           		 }
                var paramData = {
                    url: '/personnel/customer/progressranking/rankingbyaccountId',
                    data: {
                        accountId: _this.userInfo.id,
                        companyId: _this.userInfo.companyId
                    }
                };
                Interface.getAsynData(paramData, function (response) {
                    if (response.code === '1' && response.data) {
                        $('.single-team-rank p span').text(response.data);
                    }
                }, function (error) {
                    console.log(error);
                });
            },

            getTotal: function (url, total) {
                var _this = this;
                var paramData = {
                    url: url,
                    data: {
                        agentCode:  _this.userInfo.id,
                        orgId:_this.companyId,
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

            dropMyAddMember: function () {
                var _this = this;
                _this.pageOfAdd = 1;
                var dropAdd = $('.recruit-clientInfo');
                dropAdd.dropload({
                    scrollArea: dropAdd,
                    loadDownFn: function (me) {
                        var paramData = {
                            url: '/flow/customer/interviewFlow/findPersonnelbyagentCode',
                            data: {
                                agentCode: _this.userInfo.id,
                                orgId:_this.companyId,
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
                                if (_this.pageOfAdd >= 1) {
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

            dropMyRecruitment: function () {
                var _this = this;
                _this.pageOfRecruit = 1;
                var dropAdd = $('.recruit-clientInfo');
                dropAdd.unbind('scroll');
                dropAdd.dropload({
                    scrollArea: dropAdd,
                    loadDownFn: function (me) {
                        var paramData = {
                            url: '/flow/customer/interviewFlow/PersonnelpaperSearchbyParam',
                            data: {
                                agentCode:  _this.userInfo.id,
                                orgId:_this.companyId,
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
                                $('.toggle-table-cond p.active span').text(response.data.total);
                                var temp = '{{~it:value:index}}\
                                     <div class="mock-row">\
                                            <div class="mock-cell">{{=value.name || ""}}</div>\
                                            <div class="mock-cell">{{=value.sex === "M" ? "男" : value.sex === "F" ? "女" : ""}}</div>\
                                            <div class="mock-cell">{{=value.age && value.age >=0 ? value.age : ""}}</div>\
                                            <div class="mock-cell">{{=value.cellphone || ""}}</div>\
                                            <div class="mock-cell">{{=value.talentPlan || ""}}</div>\
                                            <div class="mock-cell">{{=value.processProgress || ""}}/{{=value.countProgress || ""}}</div>\
                                            <div class="mock-cell go-to-next"  data-personnelId="{{=value.id}}" data-status="{{=value.currentprogress}}">\
                                                <span>{{=value.currentprogress==1?"资料录入":value.currentprogress==2?"初审面试":value.currentprogress==3?"甄选面试":value.currentprogress==4?"决定面试":value.currentprogress==5?"培训报名":value.currentprogress==6?"培训考试":value.currentprogress==7?"签约":"未知错误"}}</span>\
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
                                orgId:_this.companyId,
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

            renderBottom: function () {
                this.bottom.removeClass('bot-button');
                var temp = '<img src="../../images/bottom-index.png" alt="">';

                temp = doT.template(temp);

                this.bottom.html(temp());
            },

            // 初始化swiper插件
            initSwiper: function (fullSlide, halfSlide) {
                new Swiper('.full-slide', {
                    pagination: fullSlide ? '.swiper-pagination' : '',
                    paginationClickable: true
                });
                new Swiper('.half-slide', {
                    pagination: halfSlide ? '.swiper-pagination' : '',
                    paginationClickable: true
                });
            }
        };
        funHomePage.init(opt);
    }
});