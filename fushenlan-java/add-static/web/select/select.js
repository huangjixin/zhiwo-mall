define(function(require, exports, module){
    var template = require('./select.html');
    template = doT.template(template);

    exports.back = function() {
        return '';
    };

    exports.show = function(opt) {

        var funHomePage = {
            init : function(obj){

                this.obj = obj;// 此时obj指代$('.main')dom元素

                this.obj.html(template(''));

                // 个人、团队切换事件
                this.obj.off('tap', 'button.control', this.toggle);
                this.obj.on('tap', 'button.control', {fun: this}, this.toggleTeamOrSingle);

                // 年、月时间切换事件
                this.obj.off('tap', '.schedule-time div:not(active)', this.toggle);
                this.obj.on('tap', '.schedule-time div:not(active)', {fun: this}, this.toggleTime);

                // 增员、招聘列表切换事件
                this.obj.off('tap', '.toggle-table-cond p:not(active)', this.toggle);
                this.obj.on('tap', '.toggle-table-cond p:not(active)', {fun: this}, this.toggleAddOrRecruitList);
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
            }
        };
        funHomePage.init(opt.body);// 把$('.main')这个dom元素传过来

    }
});