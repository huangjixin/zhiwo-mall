define(function(require, exports, module){
    var template = require('./personalInfo.html');
    template = doT.template(template);

    exports.back = function() {
        return '';
    };

    exports.show = function(opt) {

        var funHomePage = {
            init : function(obj) {

                this.obj = obj;

                this.obj.html(template(''));

                // 监听刷新页面后侧边栏高亮显示位置
                this.monitorSlideBar()

                //保存按钮
                this.obj.off('tap', '.bot-button .savePersonalInfo', this.toggle);
                this.obj.on('tap', '.bot-button .savePersonalInfo', {fun: this}, this.saveButton);

                //下一步按钮
                this.obj.off('tap', '.bot-button .goNext', this.toggle);
                this.obj.on('tap', '.bot-button .goNext', {fun: this}, this.geToNext);
            },
            monitorSlideBar: function () {
                var page = common.getHash('page');
                if($('.recruit-sidebar button').size() > 0) {
                    $('.recruit-sidebar button').each(function () {
                        if($(this).data('href') == page) {
                            $(this).addClass('active').siblings().removeClass('active')
                        }
                    })
                }
            },
            saveButton: function () {
                
            },
            geToNext: function () {
                return window.location.href = '#page=family';
            }
        };

        funHomePage.init(opt.body);
    }
});