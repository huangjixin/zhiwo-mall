/**
 * Created by Fairyland on 2018/1/25.
 */
define(function(require, exports, module){
    var template = require('./plan.html');
    require('datepicker');
    require('moment');
    template = doT.template(template);

    exports.back = function() {
        return '';
    };

    exports.show = function(opt) {

        var funHomePage = {
            init : function(obj){

                this.obj = obj;

                this.personnelId = common.getQuery('personnelId');

                this.initData();

                this.obj.html(template(''));
                this.loadTimeTool();

                //保存按钮
                this.obj.off('tap', '.bot-button .savePersonalInfo', this.toggle);
                this.obj.on('tap', '.bot-button .savePersonalInfo', {fun: this}, this.saveButton);

                //  新增教育经历
                this.obj.off('tap', '.bot-button .savePersonalInfo', this.saveButton);
                this.obj.on('tap', '.bot-button .savePersonalInfo', {fun: this}, this.saveButton);

                //下一步按钮
                this.obj.off('tap', '.bot-button .goNext', this.toggle);
                this.obj.on('tap', '.bot-button .goNext', {fun: this}, this.geToNext);
            },
            getSubmitObject: function () {
                var obj = {};
                obj['talentPlan'] = $('select[name=talentPlan]').val();
                obj['refereeName'] = $('input[name=refereeName]').val();
                obj['refereeNo'] = $('input[name=refereeNo]').val();
                obj['createTime'] = $('input[name=createTime]').val();
                return obj;
            },
            saveButton: function (btn) {
                var _this = btn.data.fun;
                var paramData = {
                    ifOpenLoading : true,
                    type: 'POST',
                    url: 'customer/personnel/addApply',
                    data: JSON.stringify(_this.getSubmitObject())
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
            loadTimeTool: function () {
                var createTime = new LCalendar();
                createTime.init({
                    trigger: $('input[name=createTime]'),
                    type: 'date',
                    minDate: '1900-1-1',
                    maxDate: moment(new Date()).format('YYYY-MM-DD')
                });
            },
            initData: function () {

            },
            geToNext: function (btn) {
                return window.location.href = '#page=application';
            }
        };
        funHomePage.init(opt.body);

    }
});