/**
 * Created by c_mengchaopeng on 2018/1/25.
 */
define(function(require, exports, module){
    var template = require('./showEducation.html');
    template = doT.template(template);

    exports.back = function() {
        return '';
    };

    exports.show = function(opt) {

        var funHomePage = {
            init : function(obj){

                this.obj = obj;// 此时obj指代$('.main')dom元素

                this.obj.html(template(''));

                //下一步按钮
                this.obj.off('tap', '.bot-button .goNext', this.toggle);
                this.obj.on('tap', '.bot-button .goNext', {fun: this}, this.geToNext);

            },
            geToNext: function (btn) {
                return window.location.href = '#page=workExperience';
            }
        };
        funHomePage.init(opt.body);// 把$('.main')这个dom元素传过来

    }
});