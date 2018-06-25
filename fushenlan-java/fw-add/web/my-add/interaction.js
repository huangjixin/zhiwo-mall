define(function (require, exports, module) {
    exports.back = function() {
        return '';
    };

    var nowDate = new Date();
    var userInfo = common.getLocalStorage("userInfo", true) || '';
    exports.init = {
        // 弹出查询框
        slideToLeft : function (e) {
            e.cancelBubble = true;
            $(this).css({
                visibility: 'hidden'
            });
            $(this).parent().next(".search-box").addClass('slide-to-left');
        },

        // 隐藏查询框
        slideToRight: function () {
            $(this).parents('.search-box').removeClass('slide-to-left');
            $(this).parents('.search-box').prev(".search").find('.icon-search').css({
                visibility: 'visible'
            });
        },

        // 目标
        goToTarget: function () {
            return window.location.href = '#page=target';
        },

        // 切换个人、团队
        toggleTeamOrSingle: function (btn) {
            var _this = btn.data.fun.initPage.init;
            if($(this).hasClass("disabled") && $(this).data('type') === 'team'){
            	return;
            }
            $(this).addClass('active').siblings('.control').removeClass('active');
            if($('.control-time div.active').data('time') === 'year') {
                if($(this).data('type') === 'team') {
                    _this.getTeamYearTarget();
                } else {
                    _this.getSingleYearTarget();
                }
            } else {
                if($(this).data('type') === 'team') {
                    _this.getTeamTarget();
                } else {
                    _this.getSingleTarget();
                }
            }
        },

        // 切换年月
        toggleTime: function (btn) {
            var _this = btn.data.fun.initPage.init;
            $(this).addClass('active').siblings().removeClass('active');
            if($('.btn-control-team button.active').data('type') === 'team') {
                if($(this).data('time') === 'month') {
                    _this.getTeamTarget();
                } else {
                    _this.getTeamYearTarget();
                }
            } else {
                if($(this).data('time') === 'month') {
                    _this.getSingleTarget();
                } else {
                    _this.getSingleYearTarget();
                }
            }
        },

        // 绘制仪表盘
        drawGaugeSchedule: function () {
            var myChart = echarts.init(document.getElementById('schedule-table'));

            var option = {
                series: [
                    {
                        name: '业务指标',
                        type: 'gauge',
                        radius: '79%',
                        splitNumber: 4,
                        axisTick: {
                            show: false
                        },
                        detail: {
                            show: false
                        },
                        splitLine: {
                            show: true,
                            length: 15,
                            lineStyle: {
                                width: 0
                            }
                        },
                        axisLine: {
                            show: true,
                            lineStyle: {
                                width: 15,
                                color: [[0.25, '#FFE884'], [0.5, '#FFC783'], [0.75, '#FF973A'], [1, '#FB5A5A']]
                            }
                        },
                        axisLabel: {
                            show: true,
                            distance: -40,
                            color: '#959595',
                            formatter: function(value) {
                                return value + '%'
                            }
                        },
                        pointer: {
                            show: false
                        }
                    }
                ]
            };

            // 渲染进度表
            myChart.setOption(option);
        },

        // 读取代理人月目标
        getSingleTarget: function () {
            var paramData = {
                url: '/personnel/customer/progressranking/findMonthlybyagentCode',
                data: {
                    accountId:  userInfo.id,
                    searchMonth: nowDate.getMonth() + 1
                }
            };

            Interface.getAsynData(paramData, function (response) {
                if(response.code === '1' && response.data) {
                    $('.schedule-text span:first-child').text(response.data.currentNum);
                    $('.schedule-text span:last-child').text(response.data.countNum);
                }
            }, function (error) {
            });
        },

        // 读取代理人年目标
        getSingleYearTarget: function () {
            var paramData = {
                url: '/personnel/customer/progressranking/findYeaybyagentCode',
                data: {
                    accountId: userInfo.id
                }
            };

            Interface.getAsynData(paramData, function (response) {
                if(response.code === '1' && response.data) {
                    $('.schedule-text span:first-child').text(response.data.currentNum);
                    $('.schedule-text span:last-child').text(response.data.countNum);
                }
            }, function (error) {
            });
        },

        // 读取团队月目标
        getTeamTarget: function () {
            var paramData = {
                url: '/personnel/customer/progressranking/findteamMonthlybyaccountId',
                data: {
                    companyId: userInfo.companyId,
                    searchMonth: nowDate.getMonth() + 1
                }
            };

            Interface.getAsynData(paramData, function (response) {
                if(response.code === '1' && response.data) {
                    $('.schedule-text span:first-child').text(response.data.currentNum);
                    $('.schedule-text span:last-child').text(response.data.countNum);
                }
            }, function (error) {
                console.log(error);
            });
        },

        // 读取团队年目标
        getTeamYearTarget: function () {
            var paramData = {
                url: '/personnel/customer/progressranking/findteamYeaybyagentCode',
                data: {
                    companyId: userInfo.companyId
                }
            };

            Interface.getAsynData(paramData, function (response) {
                if(response.code === '1' && response.data) {
                    $('.schedule-text span:first-child').text(response.data.currentNum);
                    $('.schedule-text span:last-child').text(response.data.countNum);
                }
            }, function (error) {
                console.log(error);
            });
        },

        // 跳转面试
        goToInterview: function (btn) {
            var index = $(".toggle-table-cond p.active").index();
            var obj = {};
            var status = $(this).parents('.go-to-next').data('status').toString();
            obj.personnelId = $(this).parent().data("personnelid");
            obj.progress = status;
            common.setLocalStorage("personnelInfo", obj, true);
            if(!index&status!=1) {
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

        // 拆分最新动态数组
        arrayGroup: function(array, subGroupLength) {
            // 拆分数组
            var index = 0;
            var newArray = [];
            while(index < array.length) {
                newArray.push(array.slice(index, index += subGroupLength));
            }
            return newArray;
        }
    };
});
