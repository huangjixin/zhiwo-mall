/**
 * Created by c_mengchaopeng on 2018/1/25.
 */
define(function(require, exports, module){
    var template = require('./showEducation.html');
    require('datepicker');
    require('moment');
    template = doT.template(template);

    exports.back = function() {
        return '';
    };

    exports.show = function(opt) {

        var funHomePage = {
            init : function(opt){

                this.obj = opt.body;
                this.bottom = opt.bottom;
                this.talentInfo = common.getLocalStorage('talentInfo', true) || {};
                this.personnelId = null;
                this.currentTime = common.getFormatDate(new Date());
                if(this.talentInfo && this.talentInfo.singleInfo && this.talentInfo.singleInfo.id) {
                    this.personnelId = this.talentInfo.singleInfo.id;
                } else {
                    this.personnelId = common.getQuery('personnelId') || '';
                }
                this.defaultEducation = [
                    {
                        startTime: '',
                        endTime: '',
                        education: '',
                        school: ''
                    }
                ];
                this.getSystemTime();
                this.renderBottom();
                
                // 删除教育经历
                this.obj.off('tap', '.business .operation-tool i');
                this.obj.on('tap', '.business .operation-tool i', {fun: this}, this.deleteEducation);

                // tap点击返回
                this.obj.off('tap', '.materials-header li.already');
                this.obj.on('tap', '.materials-header li.already', {fun: this}, this.tapReturn);

                // 下一步按钮
                this.bottom.off('tap', '.saveEducation');
                this.bottom.on('tap', '.saveEducation', {fun: this}, this.saveEducationButton);

                // 保存
                this.bottom.off('tap', '.educationGoNext');
                this.bottom.on('tap', '.educationGoNext', {fun: this}, this.educationGoNext);

                // 增加家庭成员列表
                this.obj.off('tap', '.add-business');
                this.obj.on('tap', '.add-business', {fun: this}, this.appendEducationMember);

                /*图片上传*/
                this.obj.off('tap', '.credentials-wrapper.first');
                this.obj.on('tap', '.credentials-wrapper.first', {fun: this}, this.uploadAfreshImage);

                /*图片上传*/
                this.obj.off('tap', '.credentials-wrapper.second');
                this.obj.on('tap', '.credentials-wrapper.second', {fun: this}, this.uploadTwoImage);
            },

            getDict: function (data) {
                var _this = this;
                var dict = 'education,school';
                var dictParam = {
                    url: '/system/dictionary/code/findByCodes',
                    type: 'POST',
                    contentType: 'application/x-www-form-urlencoded',
                    data: {
                        codes: dict
                    }
                };

                Interface.getAsynData(dictParam, function (response) {
                    if(response.code === '1') {
                        response.data.personnel = data;
                        if(data) {
                            for(var i = 0; i < response.data.personnel.length; i++) {
                                $('select[name=education]').eq(i).append(_this.setEducationSelect(response.data.education, response.data.personnel[i].education));
                                if(response.data.personnel[i] && response.data.personnel[i].education) {
                                    $('select[name=education]').eq(i).prev().val(_this.getName(response.data.education, response.data.personnel[i].education));
                                }
                            }
                        } else {
                            $('select[name=education]').eq(0).append(_this.setEducationSelect(response.data.education));
                        }

                        // 给隐藏的select加载数据
                        var hiddenSelect = '<option disabled>请选择学历</option>\
                            {{~it.education:value:index}}\
                                <option value="{{=value.code}}" {{=value.code==="本科"?"selected":""}}>{{=value.cnName}}</option>\
                        {{~}}';
                        hiddenSelect = doT.template(hiddenSelect);
                        $('select[name=education]').eq(-1).append(hiddenSelect(response.data));
                    }
                }, function (error) {
                    console.log(error);
                });
            },

            getName: function (list, code) {
                if(!code) {
                    return '';
                }
                for(var i = 0; i < list.length; i++) {
                    if(list[i].code === code) {
                        return list[i].cnName;
                    }
                }
                return '';
            },

            setEducationSelect: function (list, code) {
                var option = '';
                if(code) {
                    option = '<option disabled value="">请选择学历</option>';
                    for(var i = 0; i < list.length; i++) {
                        if(list[i].code === code) {
                            option += '<option value="' + list[i].code+ '" selected>' + list[i].cnName + '</option>';
                        } else {
                            option += '<option value="' + list[i].code+ '">' + list[i].cnName + '</option>';
                        }
                    }
                } else {
                    option = '<option disabled value="">请选择学历</option>';
                    for(var i = 0; i < list.length; i++) {
                        if(list[i].code === '本科') {
                            option += '<option value="' + list[i].code+ '" selected>' + list[i].cnName + '</option>';
                        } else {
                            option += '<option value="' + list[i].code+ '">' + list[i].cnName + '</option>';
                        }
                    }
                }
                return option;
            },

            uploadAfreshImage : function(btn){
                var _this = btn.data.fun;
                var that = this;
                var _link = uploadLink;
                if(common.isRunByApp){
                    common.loading.open();
                    setTimeout(function(){
                        UpCardId.upCardId(function(success){
                            common.loading.close();
                            $(that).children('img').attr({
                                'src': success.result_data,
                                'alt': success.result_data
                            }).show();
                            $('[name=pId]').val(success.result_id);
                            $('[name=leftImg]').val(success.result_data);
                        }, function(fail){
                            common.loading.close();
                            $(that).siblings('img').hide();
                            common.alertCreate({
                                html:  fail.result_msg
                            });
                        }, "", "", "", "", _this.personnelId, "4", _link);
                    }, 300)
                }
            },

            uploadTwoImage : function(btn){
                var _this = btn.data.fun;
                var that=this;
                var _link = uploadLink;
                if(common.isRunByApp){
                    common.loading.open();
                    setTimeout(function(){
                        UpCardId.upCardId(function(success){
                            common.loading.close();
                            $(that).children('img').attr({
                                'src': success.result_data,
                                'alt': success.result_data
                            }).show();
                            $('[name=oId]').val(success.result_id);
                            $('[name=leftImg]').val(success.result_data);
                        }, function(fail){
                            common.loading.close();
                            $(that).siblings('img').hide();
                            common.alertCreate({
                                html :  fail.result_msg
                            });
                        }, "", "", "", "", _this.personnelId, "5", _link);
                    }, 300)
                }
            },

            getEducation: function () {
                var _this = this;
                var educationParam = {
                    url: '/personnel/customer/personnel/getEducational',
                    type: 'POST',
                    contentType: 'application/x-www-form-urlencoded',
                    data: {
                        personnelId: _this.personnelId
                    }
                };

                Interface.getAsynData(educationParam, function (response) {
                    if(response.code === '1' && response.data.length > 0) {
                        _this.obj.html(template(response.data));
                        _this.xianShiImg(_this.personnelId);
                        _this.xianShiSecondImg(_this.personnelId)
                        _this.loadDefault(response.data);
                    } else {
                        _this.obj.html(template(_this.defaultEducation));
                        _this.loadDefault();
                    }
                }, function (error) {
                    _this.obj.html(template(_this.defaultEducation));
                    _this.loadDefault();
                });
            },

            getSystemTime: function () {
                var _this = this;
                var timeParam = {
                    ifOpenLoading: true,
                    url: '/system/manage/common/systime',
                    type: 'POST',
                    data: {}
                };
                Interface.getAsynData(timeParam, function (response) {
                    if(response.code === '1' && response.data) {
                        var currentTime = new Date(response.data);
                        _this.currentTime = moment(currentTime).format('YYYY-MM-DD');
                        if(_this.personnelId) {
                            _this.getEducation(_this.personnelId);
                        } else {
                            _this.obj.html(template(_this.defaultEducation));
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

            deleteEducation: function (btn) {
                var _this = btn.data.fun;
                var parent = $(this).parents('.educat-business');
                parent.remove();
                _this.sortEducation();
                if($('.business').size() <= 2) {
                    $('.operation-tool').hide();
                }
            },

            appendEducationMember: function (btn) {
                var _this = btn.data.fun;
                var member = '教育经历' + $('.educat-business').length;
                var operate = $('#education-part');
                var clone = operate.clone().removeAttr('id').removeClass('hidden');
                operate.before(clone);
                operate.prev().find('span.education-title').text(member);
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
                    ifOpenLoading: true,
                    url: '/system/manage/common/systime',
                    type: 'POST',
                    data: {}
                };

                Interface.getAsynData(timeParam, function (response) {
                    if(response.code === '1') {
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
                        }, function(input){
                            _this.checkEndTime(input);
                        });
                    }
                }, function (error) {
                    console.log(error);
                });
            },

            formatList: function () {
                var formList = [], _this = this;
                $('.educat-business').each(function() {
                    if($(this).hasClass('hidden')) {
                        return false;
                    }
                    var obj = {};
                    obj['startTime'] = $(this).find('[name=startTime]').val();
                    obj['endTime'] = $(this).find('[name=endTime]').val();
                    obj['education'] = $(this).find('[name=education]').val();
                    obj['school'] = $(this).find('[name=school]').val();
                    obj['personnelId'] = _this.personnelId;
                    formList.push(obj);
                });
                return formList;
            },

            loadDefault : function(data){
                var _this = this;
                $('input[name=startTime]').each(function(index, item) {
                    var name = $(item).attr('name');
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
                $('input[name=endTime]').each(function(index, item) {
                    var endTime = new LCalendar();
                    endTime.init({
                        trigger: item,
                        type: 'date',
                        minDate: '1900-1-1',
                        maxDate: _this.currentTime
                    },function(input){
                        _this.checkEndTime(input);
                    });
                });
                this.getDict(data);
            },
            
            checkStartTime: function (input) {
                var parent = $(input).parents('.business');
                var endTime = parent.find('input[name=endTime]').val();
                if (endTime) {
                    if (new Date(endTime).getTime() < new Date($(input).val()).getTime()) {
                        common.alertCreate({
                            html: '开始时间不能大于结束时间！',
                            callback: function () {
                                $(input).val('');
                            }
                        });
                    }
                }
            },

            checkEndTime: function (input) {
                var parent = $(input).parents('.business');
                var startTime = parent.find('input[name=startTime]').val();
                if (startTime) {
                    if (new Date(startTime).getTime() > new Date($(input).val()).getTime()) {
                        common.alertCreate({
                            html: '结束时间不能小于开始时间！',
                            callback: function () {
                                $(input).val('');
                            }
                        });
                    }
                }
            },

            xianShiImg: function (personnelId) {
                var paramData = {
                    url: '/system/attachment/file/find',
                    type: 'get',
                    data: {
                        hostId: personnelId,
                        category: "4"
                    }
                };
                Interface.getAsynData(paramData, function (response) {
                    if(response.msg==="无此图片"){
                       $(".credentials-wrapper.first").children('img').prop('src',"../../images/paper1.jpg");                       
                    }else{
                      $(".credentials-wrapper.first").children('img').prop('src',response.data[0] );
                    }
                    
                }, function (error) {

                });
            },

            xianShiSecondImg: function (personnelId) {
                var paramData = {
                    url: '/system/attachment/file/find',
                    type: 'get',
                    data: {
                        hostId: personnelId,
                        category: "5"
                    }
                };
                Interface.getAsynData(paramData, function (response) {
                    if(response.msg==="无此图片"){
                       $(".credentials-wrapper.second").children('img').prop('src',"../../images/paper1.jpg");                       
                    }else{
                       $(".credentials-wrapper.second").children('img').prop('src', response.data[0] );
                    }
                    
                }, function (error) {

                });
            },

            renderBottom: function () {
                this.bottom.removeClass('bot-button').addClass('bot-button');
                var temp = '<div class="buttonGroup">\
                    <button type="button" class="saveEducation">保存</button>\
                    <button type="button" class="goNext educationGoNext">下一步</button>\
                </div>';

                temp = doT.template(temp);
                this.bottom.html(temp(''));
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
                    case "education":
                        message = "学历";
                        break;
                    case "school":
                        message = "毕业院校";
                        break;
                }
                return message;
            },

            saveEducationButton: function (btn) {
                var _this = btn.data.fun;
                _this.submitEducationData();
            },

            educationGoNext: function (btn) {
                var _this = btn.data.fun;
                _this.submitEducationData(true);
            },

            submitEducationData: function (flag) {
                var _this = this;
                for (var i = 0; i < $(".business").size()-1; i++) {
                    var $li = $(".business").eq(i).find("li");
                    var startTime = $("input[name=startTime]").val();
                    var endTime = $("input[name=endTime]").val();
                    for (var j = 0; j < $li.size(); j++) {
                        if (j === 2) {
                            var text = $li.eq(j).find("select").val();
                            if (text === "") {
                                common.alertCreate({
                                    html: '教育经历' + (i + 1) + '学历不能为空'
                                });
                                return false;
                            }
                        } else {
                            var $input = $li.eq(j).find('input');
                            var name = $input.attr('name');
                           
                            if (!$input.val()) {
                                common.alertCreate({
                                    html: '教育经历' + (i + 1)+_this.checkName(name) + '不能为空！'
                                });
                                return false;
                            }
                        }
                    }
                    if (startTime.valueOf() > endTime.valueOf()) {
                        common.alertCreate({
                            html: '教育经历' + (i + 1) +"请选择正确的时间"
                        });
                        return false;
                    }
                }
                var paramData = {
                    ifOpenLoading : true,
                    type: 'POST',
                    url: '/personnel/customer/personnel/addEducational',
                    data: JSON.stringify(_this.formatList())
                };
                Interface.getAsynData(paramData, function (response) {
                    if(response.code === '1') {
                        if(flag) {
                            return window.location.href = '#page=workExperience';
                        } else {
                            common.alertCreate({
                                html: '教育信息保存成功！'
                            });
                        }
                    }
                }, function (error) {
                    console.log(error)
                });
            },

            sortEducation: function () {
                $('.business').each(function (index, item) {
                    if($(item).hasClass('hidden')) {
                        return false;
                    }
                    $(item).find('.education-title').text('教育经历' + (index + 1));
                });
            }
        };

        funHomePage.init(opt);
    }
});
