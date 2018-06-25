/**
 * Created by Fairyland on 2018/1/25.
 */
define(function(require, exports, module){
    var template = require('./family.html');
    template = doT.template(template);

    exports.back = function() {
        return '';
    };

    exports.show = function(opt) {

        var funHomePage = {
            init : function(obj){

                this.obj = obj;

                this.personnelId = common.getQuery('personnelId');

                this.obj.html(template(''));

                //保存按钮
                this.obj.off('tap', '.bot-button .savePersonalInfo', this.toggle);
                this.obj.on('tap', '.bot-button .savePersonalInfo', {fun: this}, this.saveButton);

                //下一步按钮
                this.obj.off('tap', '.bot-button .goNext', this.toggle);
                this.obj.on('tap', '.bot-button .goNext', {fun: this}, this.geToNext);
            },
            formatList: function () {
                var list = [];
                var _this = this;
                $('.content_body ul.ui-form').each(function () {
                    var obj = {};
                    obj['relationship'] = $(this).find('select[name=relationship]').val();
                    obj['name'] = $(this).find('input[name=name]').val();
                    obj['telephone'] = $(this).find('input[name=telephone]').val();
                    obj['company'] = $(this).find('input[name=company]').val();
                    obj['post'] = $(this).find('select[name=post]').val();
                    obj['personnelId'] = _this.personnelId;
                    list.push(obj)
                });
                return list;
            },
            saveButton: function (btn) {
                var _this = btn.data.fun;
                var paramData = {
                    ifOpenLoading : true,
                    type: 'POST',
                    url: 'customer/personnel/addFamilyMember',
                    data: JSON.stringify(_this.formatList())
                };

                Interface.getAsynData(paramData, function (response) {
                    if(response.code === '1') {
                        return true;
                    }
                }, function (error) {
                    common.alertCreate({
                        html: error.msg
                    });
                    return false;
                });
            },
            geToNext: function (btn) {
                return window.location.href = '#page=showEducation';
            }
        };
        funHomePage.init(opt.body);// 把$('.main')这个dom元素传过来

    }
});/**
 * Created by Fairyland on 2018/1/26.
 */
