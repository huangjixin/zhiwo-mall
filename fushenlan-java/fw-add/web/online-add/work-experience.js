define(function (require, exports, module) {
    var template = require('./work-experience.html');
    require('datepicker');
    require('moment');
    template = doT.template(template);

    exports.back = function () {
        return '';
    };

    exports.show = function (opt) {

        var funHomePage = {
            init: function (opt) {
                this.obj = opt.body;
                this.bottom = opt.bottom;
                this.talentInfo = common.getLocalStorage('talentInfo', true) || {};
                this.personnelId = null;
                this.currentTime = common.getFormatDate(new Date());
                if (this.talentInfo.singleInfo) {
                    this.personnelId = this.talentInfo.singleInfo.id;
                } else {
                    this.personnelId = common.getQuery('personnelId');
                }
                this.defaultWorkExperience = {
                    workExperience: [],
                    isInsuranceCompany: '',
                    isOverSix: '1',
                    nativeWorkTime: '',
                    departureDate: '',
                    isQuit: '',
                    originalCompany: ''
                };

                this.getSystemTime();
                this.renderBottom();

                //  新增工作经历
                this.bottom.off('tap', '.bot-button .saveWorkExperience');
                this.bottom.on('tap', '.bot-button .saveWorkExperience', { fun: this }, this.saveWorkExperienceButton);

                //点击显示隐藏离职证明
                this.obj.off('change', '.lizhiMonth .ipt-radio input');
                this.obj.on('change', '.lizhiMonth .ipt-radio input', {  fun: this }, this.showOrHideLizhi);

                //点击显示隐藏工作经历
                this.obj.off('change', '.ipt-radio.input-list input');
                this.obj.on('change', '.ipt-radio.input-list input', {  fun: this }, this.showOrHideWorkExperience);

                //  删除工作经历
                this.obj.off('tap', '.business .business-title i');
                this.obj.on('tap', '.business .business-title i', { fun: this }, this.deleteOneExperience);

                // tap点击返回
                this.obj.off('tap', '.materials-header li.already');
                this.obj.on('tap', '.materials-header li.already', { fun: this }, this.tapReturn);

                //下一步按钮
                this.bottom.off('tap', '.workExperienceGoNext');
                this.bottom.on('tap', '.workExperienceGoNext', { fun: this }, this.workExperienceGoNext);

                /*外籍就业证上传*/
                this.obj.off('tap', '.wraper.credentials-wrapper');
                this.obj.on('tap', '.wraper.credentials-wrapper', { fun: this }, this.afreshUploadImage);


                /*离职证明上传*/
                this.obj.off('tap', '.lizhi-wrapper.credentials-wrapper');
                this.obj.on('tap', '.lizhi-wrapper.credentials-wrapper', { fun: this }, this.lizhiUploadImage);


                // 增加工作经历
                this.obj.off('tap', '.add-business');
                this.obj.on('tap', '.add-business', { fun: this }, this.appendWork);

            },
            showLizhi: function (request) {
                if (request.isOverSix === '2') {
                    $(".lizhi").removeClass('hidden');

                } else {
                    $(".lizhi").addClass('hidden')
                }
            },

            showOrHideLizhi: function () {
                var val = $('input[name=isOverSix]:checked').val();
                if (val === '2') {
                    $(".lizhi").removeClass('hidden');

                } else {
                    $(".lizhi").addClass('hidden')
                }
            },

            getDict: function (data) {
                var _this = this;
                var dict = 'occupation';
                var dictParam = {
                    url: '/system/dictionary/code/findByCodes',
                    type: 'POST',
                    contentType: 'application/x-www-form-urlencoded',
                    data: {
                        codes: dict
                    }
                };

                Interface.getAsynData(dictParam, function (response) {
                    if (response.code === '1') {
                        response.data.personnel = data;
                        if (data) {
                            var length = response.data.personnel.workExperience.length;
                            for (var i = 0; i < length; i++) {
                                $('select[name=occupation]').eq(i).append(_this.setWorkSelect(response.data.occupation, response.data.personnel.workExperience[i].occupation));
                                $('select[name=occupation]').eq(i).prev().val(_this.getName(response.data.occupation, response.data.personnel.workExperience[i].occupation));
                            }
                        } else {
                            $('select[name=occupation]').eq(0).append(_this.setWorkSelect(response.data.occupation));
                        }

                        // 给隐藏select添加option
                        var hiddenSelect = '<option disabled value="" selected>请选择关系</option>\
                            {{~it.occupation:value:index}}\
                                <option value="{{=value.code}}">{{=value.cnName}}</option>\
                        {{~}}';
                        hiddenSelect = doT.template(hiddenSelect);
                        $('select[name=occupation]').eq(-1).append(hiddenSelect(response.data));
                    }
                }, function (error) {
                    console.log(error);
                });
            },

            getName: function (list, code) {
                if (!code) {
                    return '';
                }
                for (var i = 0; i < list.length; i++) {
                    if (list[i].code === code) {
                        return list[i].cnName;
                    }
                }
                return '';
            },

            setWorkSelect: function (list, code) {
                var option = '';
                if (code) {
                    option = '<option disabled value="">请选择关系</option>';
                    for (var i = 0; i < list.length; i++) {
                        if (list[i].code === code) {
                            option += '<option value="' + list[i].code + '" selected>' + list[i].cnName + '</option>';
                        } else {
                            option += '<option value="' + list[i].code + '">' + list[i].cnName + '</option>';
                        }
                    }
                } else {
                    option = '<option disabled selected value="">请选择关系</option>';
                    for (var j = 0; j < list.length; j++) {
                        option += '<option value="' + list[j].code + '">' + list[j].cnName + '</option>';
                    }
                }
                return option;
            },

            appendWork: function (btn) {
                var _this = btn.data.fun;
                var member = '工作经历' + $('.business').length;
                var operate = $('#workExperiencePart');
                var clone = operate.clone().removeAttr('id').removeClass('hidden');;
                $('.tiny-form').append(clone);
                clone.removeAttr('id').removeClass('hidden');
                clone.find('span.sort').text(member);
                _this.initNewDate(clone);
                if($('.operation-tool').hasClass('hidden')) {
                    $('.operation-tool').removeClass('hidden');
                }
                $('.operation-tool').show();
            },

            initNewDate: function (obj) {
                var _this = this;
                var startTime = new LCalendar();
                var endTime = new LCalendar();
                var timeParam = {
                    url: '/system/manage/common/systime',
                    type: 'POST',
                    data: {}
                };

                Interface.getAsynData(timeParam, function (response) {
                    if (response.code === '1') {
                        var currentTime = new Date(response.data);
                        _this.currentTime = moment(currentTime).format('YYYY-MM-DD');
                        startTime.init({
                            trigger: $(obj).find('input[name=startTime]'),
                            type: 'date',
                            minDate: '1900-1-1',
                            maxDate: _this.currentTime
                        }, function (input) {
                            _this.checkStartTime(input);
                        });
                        endTime.init({
                            trigger: $(obj).find('input[name=endTime]'),
                            type: 'date',
                            minDate: '1900-1-1',
                            maxDate: _this.currentTime
                        }, function (input) {
                            _this.checkEndTime(input);
                        });
                    }
                }, function (error) {
                    console.log(error);
                });
            },

            afreshUploadImage: function (btn) {
                var _this = btn.data.fun;
                var _link = uploadLink;
                if (common.isRunByApp) {
                    common.loading.open();
                    setTimeout(function () {
                        UpCardId.upCardId(function (success) {
                            common.loading.close();
                            $(".wraper.credentials-wrapper").children('img').attr({
                                'src': success.result_data,
                                'alt': success.result_data
                            }).show();
                            $('[name=pId]').val(success.result_id);

                        }, function (fail) {
                            common.loading.close();
                            $('.wraper.credentials-wrapper').children('img').hide();
                            common.alertCreate({
                                html: fail.result_msg
                            });
                        }, "", "", "", "", _this.personnelId, "13", _link);
                    }, 300)
                }
            },

            lizhiUploadImage: function (btn) {
                var _this = btn.data.fun;
                var _link = uploadLink;
                if (common.isRunByApp) {
                    common.loading.open();
                    setTimeout(function () {
                        UpCardId.upCardId(function (success) {
                            common.loading.close();
                            $(".lizhi-wrapper.credentials-wrapper").children('img').attr({
                                'src': success.result_data,
                                'alt': success.result_data
                            }).show();
                            $('[name=oId]').val(success.result_id);

                        }, function (fail) {
                            common.loading.close();
                            $('.lizhi-wrapper.credentials-wrapper').children('img').hide();
                            common.alertCreate({
                                html: fail.result_msg
                            });
                        }, "", "", "", "", _this.personnelId, "12", _link);
                    }, 300)
                }
            },

            getExperienceImg: function (personnelId) {
                var _this = this;
                var paramData = {
                    url: '/system/attachment/file/find',
                    type: 'get',
                    data: {
                        hostId: personnelId,
                        category: "13"
                    }
                };
                Interface.getAsynData(paramData, function (response) {
                     if(response.msg==="无此图片"){
                       $(".wraper.credentials-wrapper").children('img').prop('src',"../../images/paper1.jpg");                       
                    }else{
                       $(".wraper.credentials-wrapper").children('img').prop('src', response.data[0]);
                    }
                    
                }, function (error) {

                });
            },

            getLizhiImg: function (personnelId) {
                var _this = this;
                var paramData = {
                    url: '/system/attachment/file/find',
                    type: 'get',
                    data: {
                        hostId: personnelId,
                        category: "12"
                    }
                };
                Interface.getAsynData(paramData, function (response) {
                    if(response.msg==="无此图片"){
                       $(".lizhi-wrapper.credentials-wrapper").children('img').prop('src',"../../images/paper1.jpg");                       
                    }else{
                       $(".lizhi-wrapper.credentials-wrapper").children('img').prop('src', response.data[0]);
                    }
                    
                }, function (error) {

                });
            },

            getWorkExperience: function () {
                var _this = this;
                var workParam = {
                    type: "POST",
                    url: '/personnel/customer/personnel/getWorkExperience',
                    contentType: 'application/x-www-form-urlencoded',
                    data: {
                        personnelId: _this.personnelId
                    }
                };

                Interface.getAsynData(workParam, function (response) {
                    if (response.code === '1' && response.data.isOverSix && response.data.isInsuranceCompany && response.data.nativeWorkTime) {
                            _this.obj.html(template(response.data));
                            _this.getExperienceImg(_this.personnelId);
                            _this.getLizhiImg(_this.personnelId);
                            _this.showLizhi(response.data);
                            _this.loadDefault(response.data);
                       
                    } else {
                        _this.obj.html(template(_this.defaultWorkExperience));
                        _this.showLizhi(response.data);
                        _this.loadDefault();
                    }
                }, function (error) {
                    _this.obj.html(template(_this.defaultWorkExperience));
                    _this.showLizhi();
                    _this.loadDefault();
                });
            },

            showOrHideWorkExperience: function () {
                var val = $('input[name=isInsuranceCompany]:checked').val();
                if (val === '1') {
                    $(".experience").removeClass('hidden');

                } else {
                    $(".experience").addClass('hidden')
                }
            },

            getSystemTime: function () {
                var _this = this;
                var timeParam = {
                    url: '/system/manage/common/systime',
                    type: 'POST',
                    data: {}
                };
                Interface.getAsynData(timeParam, function (response) {
                    if (response.code === '1' && response.data) {
                        var currentTime = new Date(response.data);
                        _this.currentTime = moment(currentTime).format('YYYY-MM-DD');
                        if (_this.personnelId) {
                            _this.getWorkExperience();
                        } else {
                            _this.obj.html(template(_this.defaultWorkExperience));
                            _this.loadDefault();
                        }

                    }
                }, function (error) {
                    console.log(error);
                });
            },

            tapReturn: function () {
                return window.location.href = '#page=' + $(this).data('type');
            },

            deleteOneExperience: function (btn) {
                var _this = btn.data.fun;
                var parent = $(this).parents('.business');
                parent.remove();
                _this.sortWork();
                 $('.all-work-time').text(_this.countWorkTime());
            },

            formatList: function () {
                var list = [];
                var _this = this;
                $('.all-business .tiny-form ul.ui-form').each(function () {
                    if ($(this).parent('.business').hasClass('hidden')) {
                        return false;
                    }
                    var obj = {};
                    obj['startTime'] = $(this).find('input[name=startTime]').val();
                    obj['endTime'] = $(this).find('input[name=endTime]').val();
                    obj['occupation'] = $(this).find('select[name=occupation]').val();
                    obj['company'] = $(this).find('input[name=company]').val();
                    obj['post'] = $(this).find('input[name=post]').val();
                    obj['annualIncome'] = $(this).find('input[name=annualIncome]').val();
                    obj['workTime'] = $('.local-time').find('input[name=workTime]').val();
                    obj['personnelId'] = _this.personnelId;
                    list.push(obj)
                });
                return list;
            },

            renderBottom: function () {
                this.bottom.removeClass('bot-button').addClass('bot-button');
                var temp = '<div class="buttonGroup">\
                    <button type="button" class="saveWorkExperience">保存</button>\
                    <button type="button" class="goNext workExperienceGoNext">下一步</button>\
                </div>';

                temp = doT.template(temp);
                this.bottom.html(temp());
            },

            checkName: function (type) {
                var message = '';
                switch (type) {
                    case "startTime":
                        message = "开始年月";
                        break;
                    case "endTime":
                        message = "结束年月";
                        break;
                    case "occupation":
                        message = "行业";
                        break;
                    case "company":
                        message = "单位名称";
                        break;
                    case "post":
                        message = "职位";
                        break;
                    case "annualIncome":
                        message = "年收入";
                        break;
                    case "originalCompany":
                        message = "原保险机构名称";
                        break;
                    case "departureDate":
                        message = "离职日期";
                        break;
                }
                return message;
            },

            saveWorkExperienceButton: function (btn) {
                var _this = btn.data.fun;
                _this.submitWorkData();
            },

            workExperienceGoNext: function (btn) {
                var _this = btn.data.fun;
                _this.submitWorkData(true);
            },

            submitWorkData: function (flag) {
                var _this = this;
                if (!$('[name=isInsuranceCompany]:checked').val()) {
                    common.alertCreate({
                        html: '原保险公司工作经历不能为空！'
                    });
                    return false;
                }
                var $li = $(".experience").find("li");
                var $checked = $('[name=isInsuranceCompany]').get(0).checked;
                if ($checked) {
                    for (var j = 0; j < $li.size(); j++) {
                        if (j == 1) {
                            var text = $li.eq(j).find("select").val();
                            if (text == "") {
                                common.alertCreate({
                                    html: '是否离职不能为空'
                                });
                                return false;
                            }
                        } else {
                            var $input = $li.eq(j).find('input');
                            var name = $input.attr('name');
                            if (!$input.val()) {
                                common.alertCreate({
                                    html: _this.checkName(name) + '不能为空！'
                                });
                                return false;
                            }
                        }
                    }
                }
                if (!$('[name=isOverSix]:checked').val()) {
                    common.alertCreate({
                        html: '请选择离职是否满6个月！'
                    });
                    return false;
                }
                for (var i = 0; i < $(".business").size() - 1; i++) {
                    var $li = $(".business").eq(i).find("li");
                    for (var j = 0; j < $li.size(); j++) {
                        if (j === 2) {
                            var text = $li.eq(j).find("select").val();
                            if (text === "") {
                                common.alertCreate({
                                    html: '工作经历' + (i + 1) + '行业不能为空'
                                });
                                return false;
                            }
                        } else {
                            var $input = $li.eq(j).find('input');
                            var name = $input.attr('name');
                            if (!$input.val()) {
                                common.alertCreate({
                                    html: '工作经历' + (i + 1) + _this.checkName(name) + '不能为空！'
                                });
                                return false;
                            }
                        }
                    }
                }
                if (!$("[name=nativeWorkTime]").val()) {
                    common.alertCreate({
                        html: '请选择本地工作时间！'
                    });
                    return false;
                }
                var formData = common.getFormJson($('#work-experience').serializeArray());
                if (formData.isQuit === "是") {
                    formData.isQuit = 1;
                } else if (formData.isQuit === "否") {
                    formData.isQuit = 0;
                }
                var submit = {
                    isInsuranceCompany: formData.isInsuranceCompany,
                    departureDate: formData.departureDate,
                    isQuit: formData.isQuit,
                    originalCompany: formData.originalCompany,
                    workExperience: _this.formatList(),
                    isOverSix: formData.isOverSix,
                    nativeWorkTime: formData.nativeWorkTime,
                    personnelId: _this.personnelId
                };
                var paramData = {
                    ifOpenLoading: true,
                    type: 'POST',
                    url: '/personnel/customer/personnel/addWorkExperience',
                    data: JSON.stringify(submit)
                };
                Interface.getAsynData(paramData, function (response) {
                    if (response.code === '1') {
                        if (flag) {
                            return window.location.href = '#page=plan';
                        } else {
                            common.alertCreate({
                                html: '工作经历保存成功！'
                            });
                        }
                    }
                }, function (error) {
                    common.alertCreate({
                        html: error.msg
                    });
                    return false;
                });
            },

            loadDefault: function (data) {
                var _this = this;
                $('input[name=startTime]').each(function (index, item) {
                    var startTime = new LCalendar();
                    startTime.init({
                        trigger: item,
                        type: 'date',
                        minDate: '1900-1-1',
                        maxDate: _this.currentTime
                    }, function (input) {
                        _this.checkStartTime(input);
                    });
                });
                $('input[name=endTime]').each(function (index, item) {
                    var endTime = new LCalendar();
                    endTime.init({
                        trigger: item,
                        type: 'date',
                        minDate: '1900-1-1',
                        maxDate: _this.currentTime
                    }, function (input) {
                        _this.checkEndTime(input);
                    });
                });
                var departureDate = new LCalendar();
                departureDate.init({
                    trigger: '#departureDate',
                    type: 'date',
                    minDate: '1900-1-1',
                    maxDate: _this.currentTime
                });

                this.getDict(data);

                // 判断原保险公司工作经历
                if(data && data.isInsuranceCompany === '1') {
                    $('.experience').removeClass('hidden');
                    if(data.isQuit === '1') {
                        $('[name=isQuit]').prev().val('是');
                    } else {
                        $('[name=isQuit]').prev().val('否');
                    }
                }

                // 初始化回显累计工作时间
                if(data && data.workExperience.length > 0) {
                    var length = data.workExperience.length, years = 0;
                    for(var i = 0; i < length; i++) {
                        years += new Date(data.workExperience[i].endTime).getTime() - new Date(data.workExperience[i].startTime).getTime();
                    }
                    years = ((years / (24 * 3600 * 1000)) / 365).toFixed(1);
                    $('.all-work-time').text(years);
                }
            },

            checkStartTime: function (input) {
                var _this = this;
                var parent = $(input).parents('.business');
                var endTime = parent.find('input[name=endTime]').val() ? new Date(parent.find('input[name=endTime]').val()).getTime() : 0;
                var startTime = new Date($(input).val()).getTime();
                if (endTime) {
                    if (endTime < startTime) {
                        common.alertCreate({
                            html: '开始时间不能大于结束时间！',
                            callback: function () {
                                $(input).val('');
                            }
                        });
                        return false;
                    }
                    var prevYears = $('.all-work-time');
                    prevYears.text(_this.countWorkTime());
                }
            },

            checkEndTime: function (input) {
                var _this = this;
                var parent = $(input).parents('.business');
                var startTime = parent.find('input[name=startTime]').val() ? new Date(parent.find('input[name=startTime]').val()).getTime() : 0;
                var endTime = new Date($(input).val()).getTime();
                if (startTime) {
                    if (new Date(startTime).getTime() > new Date($(input).val()).getTime()) {
                        common.alertCreate({
                            html: '结束时间不能小于开始时间！',
                            callback: function () {
                                $(input).val('');
                            }
                        });
                        return false;
                    }
                    var prevYears = $('.all-work-time');
                    prevYears.text(_this.countWorkTime());
                }
            },

            countWorkTime: function () {
                var years = 0;
                $('.tiny-form .business').each(function () {
                    if($(this).hasClass('hidden')) {
                        return false;
                    }
                    var endTime = new Date($(this).find('input[name=endTime]').val()).getTime();
                    var startTime = new Date($(this).find('input[name=startTime]').val()).getTime();
                    years += endTime - startTime;
                });
                return ((years / (24 * 3600 * 1000)) / 365).toFixed(1);
            },

            sortWork: function () {
                $('.business').each(function (index, item) {
                    if ($(item).hasClass('hidden')) {
                        return false;
                    }
                    $(item).find('.sort').text('工作经历' + (index + 1));
                });
            }
        };
        funHomePage.init(opt);
    }
});