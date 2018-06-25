define(function(require, exports, module){
    var template = require('./reMaterials.html');
    template = doT.template(template);

    exports.back = function() {
        return '';
    };

    exports.show = function(opt) {

        var funHomePage = {
            init : function(obj) {

                this.obj = obj;

                this.obj.html(template(''));

                // 下拉按钮
                this.obj.off('tap', '.materials-content .mater-select i', this.toggle);
                this.obj.on('tap', '.materials-content .mater-select i', {fun: this}, this.showOrHideInfo);

                //下一步按钮
                this.obj.off('tap', '.bot-button .goNext', this.toggle);
                this.obj.on('tap', '.bot-button .goNext', {fun: this}, this.geToNext);
            },
            showOrHideInfo: function () {
                var _this = this;
                var obj = $(_this).parents('.mater-breviary').children('.mater-detail');
                $(obj).hasClass('hidden') ? $(obj).removeClass('hidden') : $(obj).addClass('hidden');
                $(_this).hasClass('more') ? $(_this).removeClass('more') : $(_this).addClass('more');
            },
            geToNext: function () {
                return window.location.href = '#page=person-background';
            }
        };

        funHomePage.init(opt.body);
    }
});