define(function(require, exports, module){
    var template = require('./basicInfo.html');
    template = doT.template(template);

    exports.back = function() {
        return '';
    };

    exports.show = function(opt) {

        var funHomePage = {
            init : function(opt){

                this.obj = opt.body;// 此时obj指代$('.main')dom元素
                this.bottom = opt.bottom;
                this.personnelInfo = common.getLocalStorage('personnelInfo', true) || '';

                this.obj.html(template(this.personnelInfo));
                this.initPerson();
                this.familyMember();
                this.getEducational();
                this.initData();
                this.allClick();
                this.renderBottom();
                //下一步按钮
                this.bottom.off('tap', '.bot-button .goNext');
                this.bottom.on('tap', '.bot-button .goNext', {fun: this}, this.geToNext);
            },
            renderBottom: function () {
                this.bottom.removeClass('bot-button').addClass('bot-button');
                var temp = '<div class="buttonGroup">\
                    <button type="button"></button>\
                    <button type="button" class="goNext">下一步</button>\
                    </div>';

                temp = doT.template(temp);
                this.bottom.html(temp(''));
            },
            allClick: function () {
                $('.info-header li').click(function() {
                    var i = $(this).index();
                    $(this).addClass('active').siblings().removeClass('active');
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
                        "personnelId":_this.personnelInfo.personnelId
                    }
                };
                Interface.getAsynData(paramData, function (response) {
                    if(response.code === '1' && response.data) {
                       var temp = require('./per-info.html');
                        temp = doT.template(temp);
                        $('#per-info').append(temp(response.data));
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
                        "personnelId":_this.personnelInfo.personnelId
                    }
                };
                Interface.getAsynData(paramData, function (response) {
                    if(response.code === '1' && response.data.length>0) {
                        var _temp=require("./per-home.html");
                        _temp = doT.template(_temp);
                        $('#per-home').append(_temp(response.data));
                    }
                }, function (error) {
                })
            },

            // 教育经历
            getEducational: function () {
                var _this =this;
                var paramData = {
                    type:"post",
                    url: '/personnel/customer/personnel/getEducational',
                    contentType: 'application/x-www-form-urlencoded',
                    data: {
                        "personnelId":_this.personnelInfo.personnelId
                    }
                };
                Interface.getAsynData(paramData, function (response) {
                    if(response.code === '1' && response.data.length>0) {
                        var temp = require('./pre-edu.html');
                        temp = doT.template(temp);
                        $('#per-edu').append(temp(response.data));
                    }
                }, function (error) {
                })
            },

            // 工作经历
            initData: function () {
                var _this =this;
                var paramData = {
                    type:"post",
                    url: '/personnel/customer/personnel/getWorkExperience',
                    contentType: 'application/x-www-form-urlencoded',
                    data: {
                        "personnelId":_this.personnelInfo.personnelId
                    }
                };
                Interface.getAsynData(paramData, function (response) {
                    if(response.code === '1' && response.data) {
                        var _temp =require("./per-work.html");
                        _temp = doT.template(_temp);
                        $('#per-work').append(_temp(response.data));
                    }
                }, function (error) {
                })
            },

            // 下一步
            geToNext: function () {
                return window.location.href = '#page=personBackground';
            }
        };
        funHomePage.init(opt);// 把$('.main')这个dom元素传过来

    }
});
