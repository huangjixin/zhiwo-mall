define(function (require, exports, module) {
    var template = require('./personnelInfo.html');
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

                this.defaultForm = {};
                this.currentTime = null;
                this.talentInfo = common.getLocalStorage('talentInfo', true) || {};
                this.personnelId = null;
                if (this.talentInfo.singleInfo) {
                    this.personnelId = this.talentInfo.singleInfo.id;
                } else {
                    this.personnelId = common.getQuery('personnelId');
                }
                this.cardType = 1;
                this.cardBackType = 2;

                this.getSystemTime();
                this.renderBottom();

                // 保存按钮
                this.bottom.off('tap', '.bot-button .savePersonalInfo');
                this.bottom.on('tap', '.bot-button .savePersonalInfo', {fun: this}, this.savePersonalInfoButton);

                // 下一步按钮
                this.bottom.off('tap', '.float-bot .personnelInfoGoNext');
                this.bottom.on('tap', '.float-bot .personnelInfoGoNext', {fun: this}, this.personnelInfoGoNext);


                /*身份证上传正面*/
                this.obj.off('tap', '#identityCardFrontFile');
                this.obj.on('tap', '#identityCardFrontFile', {fun: this}, this.uploadIndentityImage);

                /*身份证上传背面*/
                this.obj.off('tap', '#identityCardBackFile');
                this.obj.on('tap', '#identityCardBackFile', {fun: this}, this.uploadIndentityImageBack);

                /* 动态设置证件号字符长度 */
                this.obj.off('focus', 'input[name=identityCode]');
                this.obj.on('focus', 'input[name=identityCode]', {fun: this}, this.setCodeLength);

                /* 长期有效 */
                this.obj.off('change', 'input[name=beneficiaryType]');
                this.obj.on('change', 'input[name=beneficiaryType]', {fun: this}, this.setLongRange);

                /* 证件号失焦 */
                this.obj.off('blur', 'input[name=identityCode]');
                this.obj.on('blur', 'input[name=identityCode]', {fun: this}, this.judgeIdentityCode);

                //点击显示隐藏工作经历
                this.obj.off('change', '.ipt-radio.auto input');
                this.obj.on('change', '.ipt-radio.auto input', {fun: this}, this.iptRadioChange);

            },

            iptRadioChange:function(){
                var val = $('input[name=identityType]:checked').val();
                if(val==='111' || val==='114'|| val==='511'|| val==='516'){
                    $('select[name=country]').val("中国");
                    $('select[name=political]').val("群众");
                    $('select[name=country]').prev().val("中国");
                    $('select[name=political]').prev().val("群众");
                }else if(val==='117'){
                    $('select[name=country]').val("");
                    $('select[name=political]').val("");
                    $('select[name=country]').prev().val("");
                    $('select[name=political]').prev().val("");
                }
            },

            judgeIdentityCode: function () {
                if(!validation.checkVali($('input[name=identityCode]')).status) {
                    return false;
                }
                var _this = $("input[name=identityCode]");
                var str = $.trim(_this.val());
                var identityType = $('[name=identityType]:checked').val();
                if(str.length >=18 && identityType === '111') {
                    var sexNum = str.substring(16,17)%2;
                    var index = sexNum == 1? 0 : 1;
                    $('[name=sex]').eq(index).prop('checked', true).parent().addClass('checked').siblings('label').removeClass('checked');
                    var Y = _this.val().substring(6, 10);
                    var M =_this.val().substring(10, 12);
                    var D = _this.val().substring(12, 14);
                    $("[name=birthday]").val(Y+"-"+M+"-"+D);
                    $('input#age').val(common.getAge($("[name=birthday]").val()));
                } else {
                    _this.val(str.toUpperCase());
                }

            },

            setLongRange: function () {
                $(this).is(':checked') ? (
                    $('#standing-date').val("2199-01-01"),
                    $('#normal-date').val('').prop('disabled', true)
                ) : (
                   /* $('#standing-date').prop('disabled', true),*/
                    $('#normal-date').prop('disabled', false)
                );
            },

            setCodeLength: function () {
                var identityType = $('[name=identityType]:checked').val();
                if(!identityType) {
                    return false;
                }
                var _type = {"111":18, "114":18, "117":11, "414":20, "511":8, "516":11};
                return $(this).prop("maxlength",_type[identityType]);
            },

            getDict: function (data) {
                var _this = this;
                var dict = 'nation,country,political,source,channel';
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
                        var country = '';
                        if (response.data.personnel && response.data.personnel.country) {
                            country = '<option disabled>请选择国籍</option>{{~it.country:value:index}}\
                                <option value="{{=value.code}}" {{=value.code===it.personnel.country?"selected":""}}>{{=value.cnName}}</option>\
                            {{~}}';
                        } else {
                            country = '<option disabled selected value="">请选择国籍</option>{{~it.country:value:index}}\
                                <option value="{{=value.code}}">{{=value.cnName}}</option>\
                            {{~}}';
                        }

                        country = doT.template(country);
                        $('select[name=country]').append(country(response.data));
                        if (response.data.personnel && response.data.personnel.country) {
                            $('select[name=country]').prev().val(_this.getName(response.data.country, response.data.personnel.country));
                        }


                        var nation = '';
                        if (response.data.personnel && response.data.personnel.nation) {
                            nation = ' <option disabled>请选择民族</option>{{~it.nation:value:index}}\
                                <option value="{{=value.code}}" {{=value.code===it.personnel.nation?"selected":""}}>{{=value.cnName}}</option>\
                            {{~}}';
                        } else {
                            nation = ' <option disabled selected value="">请选择民族</option>{{~it.nation:value:index}}\
                                <option value="{{=value.code}}">{{=value.cnName}}</option>\
                            {{~}}';
                        }

                        nation = doT.template(nation);
                        $('select[name=nation]').append(nation(response.data));
                        if (response.data.personnel && response.data.personnel.nation) {
                            $('select[name=nation]').prev().val(_this.getName(response.data.nation, response.data.personnel.nation));
                        }


                        var political = '';
                        if (response.data.personnel && response.data.personnel.political) {
                            political = '<option disabled>请选择政治面貌</option>{{~it.political:value:index}}\
                            <option value="{{=value.code}}" {{=value.code===it.personnel.political?"selected":""}}>{{=value.cnName}}</option>\
                        {{~}} ';
                        } else {
                            political = '<option disabled selected value="">请选择政治面貌</option>{{~it.political:value:index}}\
                                <option value="{{=value.code}}">{{=value.cnName}}</option>\
                            {{~}} ';
                        }
                        political = doT.template(political);
                        $('select[name=political]').append(political(response.data));
                        if (response.data.personnel && response.data.personnel.political) {
                            $('select[name=political]').prev().val(_this.getName(response.data.political, response.data.personnel.political));
                        }


                        var source = '';
                        if (response.data.personnel && response.data.personnel.source) {
                            source = '<option disabled>请选择新人来源</option>{{~it.source:value:index}}\
                                <option value="{{=value.code}}" {{=value.code===it.personnel.source?"selected":""}}>{{=value.cnName}}</option>\
                            {{~}}';
                        } else {
                            source = '<option disabled selected value="">请选择新人来源</option>{{~it.source:value:index}}\
                                <option value="{{=value.code}}" >{{=value.cnName}}</option>\
                            {{~}}';
                        }

                        source = doT.template(source);
                        $('select[name=source]').append(source(response.data));
                        if (response.data.personnel && response.data.personnel.source) {
                            $('select[name=source]').prev().val(_this.getName(response.data.source, response.data.personnel.source));
                        }


                        var channel = '';
                        if (response.data.personnel && response.data.personnel.channel) {
                            channel = '<option disabled>请选择来源渠道</option>{{~it.channel:value:index}}\
                                <option value="{{=value.code}}" {{=value.code===it.personnel.channel?"selected":""}}>{{=value.cnName}}</option>\
                            {{~}}';
                        } else {
                            channel = '<option disabled selected value="">请选择来源渠道</option>{{~it.channel:value:index}}\
                                <option value="{{=value.code}}">{{=value.cnName}}</option>\
                            {{~}}';
                        }
                        channel = doT.template(channel);
                        $('select[name=channel]').append(channel(response.data));
                        if (response.data.personnel && response.data.personnel.channel) {
                            $('select[name=channel]').prev().val(_this.getName(response.data.channel, response.data.personnel.channel));
                        }
                    }
                }, function (error) {
                    console.log(error);
                });
            },

            getName: function (list, code) {
                for (var i = 0; i < list.length; i++) {
                    if (list[i].code === code) {
                        return list[i].cnName;
                    }
                }
                return false;
            },

            getCardType: function () {
                var identity = $('[name=identityType]:checked').val();
                var _this = this;
                switch (identity) {
                    case '111':
                        _this.cardType = 1;
                        _this.cardBackType = 2;
                        break;
                    case '114':
                        _this.cardType = 7;
                        _this.cardBackType = 7;
                        break;
                    case '511':
                        _this.cardType = 10;
                        _this.cardBackType = 10;
                        break;
                    case '414':
                        _this.cardType = 13;
                        _this.cardBackType = 13;
                        break;
                    case '516':
                        _this.cardType = 14;
                        _this.cardBackType = 14;
                        break;
                    case '117':
                        _this.cardType = 0;
                        _this.cardBackType = 0;
                        break;
                }
            },

            uploadIndentityImage: function (btn) {
                var _this = btn.data.fun;
                var identity = $('[name=identityType]:checked').val();
                if (!identity) {
                    common.alertCreate({
                        html: '请先选择证件类型！'
                    });
                    return false;
                }
                var _link = uploadLink;
                var cardType = '1';
                if (common.isRunByApp) {
                    common.loading.open();
                    setTimeout(function () {
                        switch (identity) {
                            case '111':
                                cardType = '1';
                                break;
                            case '114':
                                cardType = '7';
                                break;
                            case '511':
                                cardType = '10';
                                break;
                            case '414':
                                cardType = '13';
                                break;
                            case '516':
                                cardType = '14';
                                break;
                            case '117':
                                cardType = '0';
                                break;
                        }
                        UpCardId.upCardId(function (success) {
                            common.loading.close();
                            
                            $('[name=leftImg]').val(success.result_data);
                            var _data = JSON.parse(success.result_info);
                            
                            $('#identityCardFrontFile').siblings('img').attr({
                                'src': success.result_data,
                                'alt': success.result_data
                            }).show();
                            if (!validation.date(_data.id_birthday)) {
                                common.alertCreate({
                                    html: '获取信息失败，请重新扫描！'
                                });
                                $('[name=pId]').val(success.result_id);
                                return false;
                            }
                            $('[name=pId]').val(success.result_id);
                            $('[name=identityCode]').val(_data.id_number);
                            $('[name=age]').val(common.getAge(_data.id_birthday));
                            $('[name=name]').val(_data.id_name);
                            //
                            $('[name=birthday]').val(_data.id_birthday);
                            // $('[name=nation]').val(_data.id_ethnic);
                            //
                            if (_data.id_sex == '男' || _data.id_sex == '女') {
                                $('[name=sex]').eq(_data.id_sex == '男' ? 0 : 1).prop('checked', true).parent().addClass('checked').siblings('label').removeClass('checked');
                            }
                        }, function (fail) {
                            common.loading.close();
                            $('#identityCardFrontFile').siblings('img').hide();
                            common.alertCreate({
                                html: fail.result_msg
                            });
                        }, "", "", "1", "", "", cardType, _link);
                    }, 300)
                }
            },

            uploadIndentityImageBack: function () {
                var identity = $('[name=identityType]:checked').val();
                if (!identity) {
                    common.alertCreate({
                        html: '请先选择证件类型！'
                    });
                    return false;
                }
                var _link = uploadLink;
                var cardType = '2'; //证件类型 2：身份证正面，3：身份证背面，7：军官证，10：台胞证， 13：护照， 14：港澳回乡证正面，15：港澳回乡证背面
                if (common.isRunByApp) {
                    common.loading.open();
                    setTimeout(function () {
                        switch (identity) {
                            case '111':
                                cardType = '2';
                                break;
                            case '114':
                                cardType = '7';
                                break;
                            case '511':
                                cardType = '10';
                                break;
                            case '414':
                                cardType = '13';
                                break;
                            case '516':
                                cardType = '14';
                                break;
                            case '117':
                                cardType = '0';
                                break;
                        }
                        UpCardId.upCardId(function (success) {
                            common.loading.close();
                            var _data = JSON.parse(success.result_info);

                            $('#identityCardBackFile').siblings('img').attr({
                                'src': success.result_data,
                                'alt': success.result_data
                            }).show();
                            
                            if (!validation.date(_data.id_expiryDate)) {
                                
                                common.alertCreate({
                                    html: '获取信息失败，请重新扫描！'
                                });
                                $('[name=oId]').val(success.result_id);
                                return false;
                            }
                            $('[name=oId]').val(success.result_id);
                            $('[name=rightImg]').val(success.result_data);
                            $('[name=ctfexpireDate]').val(_data.id_expiryDate);
                            
                        }, function (fail) {
                            common.loading.close();
                            $('#identityCardBackFile').siblings('img').hide();
                            common.alertCreate({
                                html: fail.result_msg
                            });
                        }, "", "", "2", "", "", cardType, _link);
                    }, 300)
                }
            },

            getImg: function(personnelId){
                var paramData = {
                    url: '/system/attachment/file/find',
                    type: 'get',
                    data: {
                        hostId: personnelId.id,
                        category: 1
                    }
                };
                Interface.getAsynData(paramData, function (response) {
                    if(response.code === '1' && response.data[0] !== '无此图片') {
                        $("#identityCardFrontFile").siblings('img').prop('src',response.data[0]);
                    }
                }, function (error) {

                });
            },

            getImgBack: function (personnelId) {
                var paramData = {
                    url: '/system/attachment/file/find',
                    type: 'get',
                    data: {
                        hostId: personnelId.id,
                        category: 2
                    }
                };
                Interface.getAsynData(paramData, function (response) {
                    if(response.code === '1' && response.data[0] !== '无此图片') {
                        $("#identityCardBackFile").siblings('img').prop('src',response.data[0]);
                    }
                }, function (error) {

                });
            },

            getPersonnel: function () {
                var _this = this;
                var personnelParam = {
                    type: 'POST',
                    url: '/personnel/customer/personnel/getPersonnel',
                    contentType: 'application/x-www-form-urlencoded',
                    data: {
                        personnelId: _this.personnelId
                    }
                };

                Interface.getAsynData(personnelParam, function (response) {
                    if (response.code === '1' && response.data) {
                        _this.obj.html(template(response.data));
                        _this.getImg(response.data);
                        _this.getImgBack(response.data);
                        _this.loadDefault(response.data);
                        _this.judgeIdentityCode();
                    } else {
                        _this.obj.html(template(_this.defaultForm));
                        _this.loadDefault();
                    }
                }, function (error) {
                    _this.obj.html(template(_this.defaultForm));
                    _this.loadDefault();
                });
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
                        if (_this.talentInfo && _this.personnelId) {
                            _this.getPersonnel();
                        } else {
                            _this.obj.html(template(_this.defaultForm));
                            _this.loadDefault();
                        }

                    }
                }, function (error) {
                    console.log(error);
                });
            },

            renderBottom: function () {
                this.bottom.removeClass('bot-button').addClass('bot-button');
                var temp = '<div class="buttonGroup">\
                    <button type="button" class="savePersonalInfo">保存</button>\
                    <button type="button" class="goNext personnelInfoGoNext">下一步</button>\
                </div>';

                temp = doT.template(temp);
                this.bottom.html(temp(''));
            },

            loadDefault: function (data) {
                var _this = this;
                var birthday = new LCalendar();
                var validityDate = new LCalendar();
                birthday.init({
                    trigger: '#birthday',
                    type: 'date',
                    minDate: '1900-1-1',
                    maxDate: _this.currentTime
                }, function (input) {
                    var str = $.trim($(input).val());
                    if (str !== '') {
                        if (!validation.date(str)) {
                            common.alertCreate({
                                html: '出生日期格式错误！'
                            });
                            return false;
                        } else {
                            $('input#age').val(common.getAge(str));
                        }
                    } else {
                        $("[name=age]").val('');
                    }
                });
                validityDate.init({
                    trigger: '#normal-date',
                    type: 'date',
                    minDate: _this.currentTime,
                    maxDate: '2118-01-01'
                },function (input) {
                	$("input[name=ctfexpireDate]").val($(input).val());
                });
                this.getDict(data);
            },

            savePersonalInfoButton: function (btn) {
                var _this = btn.data.fun;
                _this.submitData();
            },

            personnelInfoGoNext: function (btn) {
                var _this = btn.data.fun;
                _this.submitData(true);
            },

            submitData: function (flag) {
                var _this = this;
                if(!$('[name=identityType]:checked').val()) {
                    common.alertCreate({
                        html: '证件类型不能为空！'
                    });
                    return false;
                }
                _this.getCardType();
                if(!$('[name=sex]:checked').val()) {
                    common.alertCreate({
                        html: '性别不能为空！'
                    });
                    return false;
                }
                if (!validation.checkValiMust($('#personalForm'))) {
                    return false;
                }
                var singleInfo = common.getFormJson($("#personalForm").serializeArray());
                var imgList = [];
                if($('input[name=pId]').val() || $('input[name=oId]').val()) {
                    imgList = [
                        {
                            id: $('input[name=pId]').val(),
                            category: _this.cardType
                        },
                        {
                            id: $('input[name=oId]').val(),
                            category: _this.cardBackType
                        }
                    ]
                }
                var submit = {
                    imgList: imgList,
                    personnel: singleInfo
                };
                var paramData = {
                    ifOpenLoading: true,
                    type: 'POST',
                    url: '/personnel/customer/personnel/addPersonnel',
                    data: JSON.stringify(submit)
                };

                Interface.getAsynData(paramData, function (response) {
                    $('input[name=pId]').val(0);
                    $('input[name=oId]').val(0);
                    if (response.code === '1') {
                        if(flag) {
                            singleInfo.id = response.data;
                            _this.talentInfo.singleInfo = singleInfo;
                            common.setLocalStorage('talentInfo', _this.talentInfo, true);
                            return window.location.href = '#page=family';
                        } else {
                            $('input[name=id]').val(response.data);
                            common.alertCreate({
                                html: '保存个人信息成功！'
                            });
                        }
                    }
                }, function (error) {
                    if(flag) {
                        common.alertCreate({
                            html: error.msg
                        });
                        return false;
                    } else {
                        common.alertCreate({
                            html: '保存个人信息失败！'
                        });
                    }
                });
            }
        };

        funHomePage.init(opt);
    }
});