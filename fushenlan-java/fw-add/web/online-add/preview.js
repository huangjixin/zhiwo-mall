define(function(require, exports, module){
    var template = require('./preview.html');
    template = doT.template(template);

    exports.back = function() {
        return '';
    };


    exports.show = function(opt) {
        var funHomePage = {
            init: function(opt){

                this.obj = opt.body;
                this.bottom = opt.bottom;
 				this.talentInfo = common.getLocalStorage('talentInfo', true) || {};
 				this.personnelId = null;
 				this.currentTime = common.getFormatDate(new Date());
 				if(this.talentInfo.singleInfo && this.talentInfo.singleInfo.personnelId) {
                    this.personnelId = this.talentInfo.singleInfo.personnelId;
                } else {
                    this.personnelId = common.getQuery('personnelId') || '';
                }
                this.obj.html(template(''));
 				this.renderBottom();
                // 获取在线增员详情
                this.getApply();
                this.getPersonnel();
                this.getFamilyMember();
                this.getEducational();
                this.getWorkExperience();

                // tap点击返回
                this.obj.off('tap', '.materials-header li.already');
                this.obj.on('tap', '.materials-header li.already', {fun: this}, this.tapReturn);

                //点击下一步更新状态
                this.bottom.off('tap', '.confirm');
                this.bottom.on('tap', '.confirm', {fun: this}, this.previewGoNext);
            },
            tapReturn: function () {
                return window.location.href = '#page=' + $(this).data('type');
            },

            previewGoNext: function (btn) {
                var _this = btn.data.fun;
                var paramData = {
                    ifOpenLoading: true,
                    type: "POST",
                    url: '/personnel/customer/personnel/updatePersonnel',
                    contentType: 'application/x-www-form-urlencoded',
                    data: {
                        personnelStatus: 2,
                        personnelId: _this.personnelId,
                        flowActionId: 2,
                        processingStatus: 1
                    }
                };
                Interface.getAsynData(paramData, function (response) {
                    if(response.code === '1' && response.data) {
                        common.removelocalStorage('talentInfo');
                        return window.location.href = '#page=main';
                    }
                }, function (error) {
                    console.log(error)
                })
            },

            getApply: function () {
                var _this = this;
                var paramData = {
                    type:"post",
                    url: '/personnel/customer/personnel/getScanApply',
                    contentType: 'application/x-www-form-urlencoded',
                    data: {
                        personnelId: _this.personnelId
                    }
                };
                Interface.getAsynData(paramData, function (response) {
                    if(response.code === '1' && response.data) {
                        var temp = '{{?it}}<ul class="preview-list">\
                            <li class="col-md-4">人才计划：{{=it.talentPlan || ""}}</li>\
                            <li class="col-md-4">推荐人：{{=it.refereeName || ""}}</li>\
                            <li class="col-md-4">推荐编号：{{=it.refereeNo || ""}}</li>\
                            <li class="col-md-4">推荐时间：{{=it.createTime || ""}}</li>\
                        </ul>{{?}}';
                        temp = doT.template(temp);
                        $('.preview_getApply').html(temp(response.data));
                    }
                }, function (error) {
                })
            },

            getPersonnel: function () {
            	var _this = this;
                var paramData = {
                    type:"post",
                    url: '/personnel/customer/personnel/getPersonnel',
                    contentType: 'application/x-www-form-urlencoded',
                    data: {
                        personnelId: _this.personnelId
                    }
                };

                Interface.getAsynData(paramData, function (response) {
                    if(response.code === '1' && response.data) {
                        var temp = '{{?it}}<ul class="preview-list">\
                            <li class="col-md-3">证件类型：{{=it.identityType ? it.identityType==="111" ? "身份证" : it.identityType==="414" ? "护照" : it.identityType === "117" ? "出生证" : it.identityType === "114" ? "军人证" : it.identityType === "511" ? "台胞证" : it.identityType === "516" ? "港澳回乡证": "" : ""}}</li>\
                            <li class="col-md-5">证件号码：{{=it.identityCode || ""}}</li>\
                            <li class="col-md-4">证件有效期至：{{=it.ctfexpireDate ? it.ctfexpireDate === "2199-01-01" ? "长期有效" : it.ctfexpireDate : ""}}</li>\
                            <li class="col-md-3">姓名：{{=it.name || ""}}</li>\
                            <li class="col-md-3">性别：{{=it.sex === "M" ? "男" : "女"}}</li>\
                            <li class="col-md-3">年龄：{{= it.birthday ? common.getAge(it.birthday) : ""}}</li>\
                            <li class="col-md-3">生日：{{=it.birthday || ""}}</li>\
                            <li class="col-md-3">民族：{{=it.nation || ""}}</li>\
                            <li class="col-md-3">政治面貌：{{=it.political || ""}}</li>\
                            <li class="col-md-3">新人来源：{{=it.source || ""}}</li>\
                            <li class="col-md-3">来源渠道：{{=it.channel || ""}}</li>\
                        </ul>{{?}}';
                        temp = doT.template(temp);
                        $('.preview_getPersonnel').html(temp(response.data));
                    }
                }, function (error) {
                })
            },

            getFamilyMember: function () {
            	var _this = this;
                var paramData = {
                    type:"post",
                    contentType: 'application/x-www-form-urlencoded',
                    url: '/personnel/customer/personnel/getFamilyMember',
                    data: {
                        "personnelId":_this.personnelId
                    }
                };
                Interface.getAsynData(paramData, function (response) {
                    if(response.code === '1' && response.data.length>0) {
                        var temp = '{{~it:val:index}}<ul class="preview-list">\
                            <li class="col-md-4">关系：{{=val.relationship || ""}}</li>\
                            <li class="col-md-4">姓名：{{=val.name || ""}}</li>\
                            <li class="col-md-4">电话：{{=val.telephone || ""}}</li>\
                            <li class="col-md-4">公司名称：{{=val.company || ""}}</li>\
                        </ul>{{~}}';
                        temp = doT.template(temp);
                        $('.preview_getFamilyMember').html(temp(response.data));
                    }
                }, function (error) {
                })
            },

            getEducational: function () {
            	var _this = this;
                var paramData = {
                    type: "post",
                    contentType: 'application/x-www-form-urlencoded',
                    url: '/personnel/customer/personnel/getEducational',
                    data: {
                        personnelId: _this.personnelId
                    }
                };
                Interface.getAsynData(paramData, function (response) {
                    if(response.code === '1' && response.data.length > 0) {
                        var temp = '{{~it:val:index}}<ul class="preview-list">\
                            <li class="col-md-4">开始年月：{{=val.startTime?common.getFormatDate(new Date(val.startTime)) : ""}}</li>\
                            <li class="col-md-4">结束年月：{{=val.endTime?common.getFormatDate(new Date(val.endTime)) : ""}}</li>\
                            <li class="col-md-4">学历：{{=val.education || ""}}</li>\
                            <li class="col-md-4">毕业院校：{{=val.school || ""}}</li>\
                        </ul>{{~}}';
                        temp = doT.template(temp);
                        $('.preview_getEducational').html(temp(response.data));
                    }
                }, function (error) {
                })
            },

            renderBottom: function () {
                this.bottom.removeClass('bot-button').addClass('bot-button');
                $('.recruit-clientInfo').css({
                    'padding-bottom': '66px'
                });
                var temp = '<div class="buttonGroup">\
                    <button type="button"></button>\
                    <button type="button" class="goNext confirm">确认无误</button>\
                </div>';

                temp = doT.template(temp);
                this.bottom.html(temp(''));
            },

            getWorkExperience: function () {
            	var _this = this;
                var paramData = {
                    type:"post",
                    contentType: 'application/x-www-form-urlencoded',
                    url: '/personnel/customer/personnel/getWorkExperience',
                    data: {
                        personnelId:_this.personnelId
                    }
                };
                Interface.getAsynData(paramData, function (response) {
                    if(response.code === '1' && response.data.workExperience.length>0) {
                        var temp = '{{~it.workExperience:val:index}}<ul class="preview-list">\
                            <li class="col-md-4">开始年月：{{=val.startTime?common.getFormatDate(new Date(val.startTime)) : ""}}</li>\
                            <li class="col-md-4">结束年月：{{=val.endTime?common.getFormatDate(new Date(val.endTime)) : ""}}</li>\
                            <li class="col-md-4">职业：{{=val.occupation || ""}}</li>\
                            <li class="col-md-4">职位：{{=val.post || ""}}</li>\
                            <li class="col-md-4">单位名称：{{=val.company || ""}}</li>\
                        </ul>{{~}}';
                        temp = doT.template(temp);
                        $('.preview_getWorkExperience').html(temp(response.data));
                    }
                }, function (error) {

                })
            }
        };
        funHomePage.init(opt);
    }
});
