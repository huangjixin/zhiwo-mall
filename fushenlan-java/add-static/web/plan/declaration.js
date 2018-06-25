/**
 * Created by Fairyland on 2018/1/25.
 */
define(function(require, exports, module){
    var template = require('./declaration.html');
    template = doT.template(template);

    exports.back = function() {
        return '';
    };

    exports.show = function(opt) {

        var funHomePage = {
            init : function(obj){

                this.obj = obj;

                this.obj.html(template(''));

                //下一步按钮
                this.obj.off('tap', '.bot-button .goNext', this.toggle);
                this.obj.on('tap', '.bot-button .goNext', {fun: this}, this.geToNext);
            },
            geToNext: function () {
                return window.location.href = '#page=';
            }
        };
        funHomePage.init(opt.body);

    }
});/**
 * Created by Fairyland on 2018/1/25.
 */
