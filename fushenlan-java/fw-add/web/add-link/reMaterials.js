define(function(require, exports, module){
    var template = require('./reMaterials.html');
    template = doT.template(template);

    exports.back = function() {
        return '';
    };

    exports.show = function(opt) {

        var funHomePage = {
            init : function(opt) {
                this.obj = opt.body;
                this.bottom = opt.bottom;
                this.obj.html(template(''));
                this.personnelInfo = common.getLocalStorage("personnelInfo", true) || '';

                // 初始化页面数据
                this.initData();
                this.initPerson();
                this.familyMember();
                this.getEducational();
                this.getApply();
                this.renderBottom();
                // 保存按钮
                this.bottom.off('tap', '.bot-button .saveReMaterials');
                this.bottom.on('tap', '.bot-button .saveReMaterials', {fun: this}, this.saveReMaterialsButton);

                // 下拉按钮
                this.obj.off('tap', '.materials-content .mater-select i');
                this.obj.on('tap', '.materials-content .mater-select i', {fun: this}, this.showOrHideInfo);

                //下一步按钮
                this.bottom.off('tap', '.bot-button .reMaterialsGoNext');
                this.bottom.on('tap', '.bot-button .reMaterialsGoNext', {fun: this}, this.reMaterialsGoNext);

                // tap点击返回
                this.obj.off('tap', '.materials-header li.already');
                this.obj.on('tap', '.materials-header li.already', {fun: this}, this.tapReturn);
            },

            renderBottom: function () {
                this.bottom.removeClass('bot-button').addClass('bot-button');
                var temp = '<div class="buttonGroup">\
                    <button type="button"></button>\
                    <button type="button" class="goNext reMaterialsGoNext">下一步</button>\
                </div>';

                temp = doT.template(temp);

                this.bottom.html(temp(''));
            },

            showOrHideInfo: function () {
                var _this = this;
                var obj = $(_this).parents('.mater-breviary').children('.mater-detail');
                $(obj).hasClass('hidden') ? $(obj).removeClass('hidden') : $(obj).addClass('hidden');
                $(_this).hasClass('more') ? $(_this).removeClass('more') : $(_this).addClass('more');
            },

            //tap返回上一页
            tapReturn: function () {
                return window.location.href = '#page=' + $(this).data('type');
            },

            saveReMaterialsButton: function (btn) {
                var _this = btn.data.fun;
                var paramData = {
                    ifOpenLoading : true,
                    type: 'POST',
                    url: '/personnel/customer/personnel/addApply',
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

            // 个人信息
            initPerson: function () {
                var _this =this;
                var paramData = {
                    type:"post",
                    url: '/personnel/customer/personnel/getPersonnel',
                    contentType: 'application/x-www-form-urlencoded',
                    data: {
                        personnelId: _this.personnelInfo.personnelId
                    }
                };
                Interface.getAsynData(paramData, function (response) {
                    if(response.code === '1' && response.data) {
                        var temp = require('./single-info.html');
                        temp = doT.template(temp);
                        $('.single-info').html(temp(response.data));
                    }
                }, function (error) {
                })
            },

            // 家庭信息
            familyMember: function () {
                var _this =this;
                var paramData = {
                    type:"post",
                    url: '/personnel/customer/personnel/getFamilyMember',
                    contentType: 'application/x-www-form-urlencoded',
                    data: {
                        personnelId: _this.personnelInfo.personnelId
                    }
                };
                Interface.getAsynData(paramData, function (response) {
                    if(response.code === '1' && response.data.length>0) {
                        var _temp=require("./familyMember.html");
                        _temp = doT.template(_temp);
                        $('.familyMember').html(_temp(response.data));
                    }
                }, function (error) {
                })
            },

            // 教育信息
            getEducational: function () {
                var _this =this;
                var paramData = {
                    type:"post",
                    url: '/personnel/customer/personnel/getEducational',
                    contentType: 'application/x-www-form-urlencoded',
                    data: {
                        personnelId: _this.personnelInfo.personnelId
                    }
                };
                Interface.getAsynData(paramData, function (response) {
                    if(response.code === '1' && response.data.length>0) {
                        var temp = require('./getEducational.html');
                        temp = doT.template(temp);
                        $('.getEducational').html(temp(response.data));
                    }
                }, function (error) {
                })
            },

            // 工作经历
            initData: function () {
                var _this = this;
                var paramData = {
                    type:"post",
                    url: '/personnel/customer/personnel/getWorkExperience',
                    contentType: 'application/x-www-form-urlencoded',
                    data: {
                        personnelId: _this.personnelInfo.personnelId
                    }
                };
                Interface.getAsynData(paramData, function (response) {
                    if(response.code === '1' && response.data) {
                        var _temp =require("./getWorkExperience.html");
                        _temp = doT.template(_temp);
                        $('.workExperience').html(_temp(response.data.workExperience));
                    }
                }, function (error) {
                    console.log(error)
                })
            },

            // 计划申请
            getApply: function () {
                var _this =this;//974207410279284736
                var paramData = {
                    type:"post",
                    url: '/personnel/customer/personnel/getScanApply',
                    contentType: 'application/x-www-form-urlencoded',
                    data: {
                        "personnelId":_this.personnelInfo.personnelId
                    }
                };
                Interface.getAsynData(paramData, function (response) {
                    if(response.code === '1' && response.data) {
                        var temp = require('./dot-getApply.html');
                        temp = doT.template(temp);
                        $('.getApply').html(temp(response.data));
                    }
                }, function (error) {
                })
            },

            reMaterialsGoNext: function () {
                return window.location.href = '#page=video';
            }
        };
        funHomePage.init(opt);
    }
});