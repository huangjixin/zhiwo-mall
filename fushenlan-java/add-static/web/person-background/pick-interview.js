/**
 * Created by c_mengchaopeng on 2018/1/25.
 */
define(function(require, exports, module){
    var template = require('./pick-interview.html');
    template = doT.template(template);

    exports.back = function() {
        return '';
    };

    exports.show = function(opt) {

        var funHomePage = {
            init : function(obj){

                this.obj = obj;// 此时obj指代$('.main')dom元素

                this.obj.html(template(''));
                this.allClick();

                //下一步按钮
                this.obj.off('tap', '.bot-button .goNext', this.toggle);
                this.obj.on('tap', '.bot-button .goNext', {fun: this}, this.geToNext);
            },
            allClick: function () {
                $('.stars-list li').click(function() {
                    var i = $(this).index();
                });
            },
            geToNext: function () {
                return window.location.href = '#page=declaration';
            }
        };
        
        funHomePage.init(opt.body);// 把$('.main')这个dom元素传过来

    }
});