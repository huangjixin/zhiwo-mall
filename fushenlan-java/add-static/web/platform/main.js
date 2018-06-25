define(function(require, exports, module){
    require('echarts');
    require('swiper');
    var template = require('./main.html');
    template = doT.template(template);

    exports.back = function() {
        return '';
    };

    exports.show = function(opt) {

        var funHomePage = {
            init : function(obj){

                this.obj = obj;

                this.obj.html(template(''));

                this.initData();

                // 个人、团队切换事件
                this.obj.off('tap', 'button.control', this.toggle);
                this.obj.on('tap', 'button.control', {fun: this}, this.toggleTeamOrSingle);

                // 年、月时间切换事件
                this.obj.off('tap', '.schedule-time div:not(active)', this.toggle);
                this.obj.on('tap', '.schedule-time div:not(active)', {fun: this}, this.toggleTime);

                // 增员、招聘列表切换事件
                this.obj.off('tap', '.toggle-table-cond p:not(active)', this.toggle);
                this.obj.on('tap', '.toggle-table-cond p:not(active)', {fun: this}, this.toggleAddOrRecruitList);

                // 目标
                this.obj.off('tap', '.client-rank .btn-control-team .write', this.goToTarget);
                this.obj.on('tap', '.client-rank .btn-control-team .write', {fun: this}, this.goToTarget);
            },
            initData: function () {
                this.drawGaugeSchedule();

                // 初始化加检测侧边栏状态，更改最新状态
                this.checkSlideBarStatus();

                // 初始化swiper插件
                this.initSwiper();

            },
            // 目标
            goToTarget: function () {
                return window.location.href = '#page=target';
            },
            initSwiper: function () {
                var pagination = {
                    pagination: '.swiper-pagination',
                    paginationClickable: true
                };
                new Swiper('.full-slide', pagination);
                new Swiper('.half-slide', pagination);
            },
            checkSlideBarStatus : function () {
                if(slideBar) {
                    $('.recent-state .full-slide').addClass('goTop');
                    $('.recent-state .half-slide').addClass('goBot');
                } else {
                    $('.recent-state .full-slide').removeClass('goTop');
                    $('.recent-state .half-slide').removeClass('goBot');
                }
            },
            toggleTeamOrSingle: function (btn) {
                var _this = btn.data.fun;
                $(this).addClass('active').siblings('.control').removeClass('active')
            },
            toggleTime: function (btn) {
                var _this = btn.data.fun;
                $(this).addClass('active').siblings().removeClass('active')
            },
            toggleAddOrRecruitList: function (btn) {
                var _this = btn.data.fun;
                $(this).addClass('active').siblings().removeClass('active')
            },
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
            }
        };

        funHomePage.init(opt.body);// 把$('.main')这个dom元素传过来
    }
});