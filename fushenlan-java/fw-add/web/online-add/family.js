/**
 * Created by Fairyland on 2018/1/25.
 */
define(function (require, exports, module) {
    var template = require('./family.html');
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
                if (this.talentInfo && this.talentInfo.singleInfo && this.talentInfo.singleInfo.id) {
                    this.personnelId = this.talentInfo.singleInfo.id;
                } else {
                    this.personnelId = common.getQuery('personnelId') || '';
                }
                this.familyListDefault = [{
                    relationship: '',
                    name: '',
                    telephone: '',
                    company: '',
                    post: ''
                }];

                this.getFamilyMember();
                this.renderBottom();


                // 保存按钮
                this.bottom.off('tap', '.bot-button .savePersonalInfo');
                this.bottom.on('tap', '.bot-button .savePersonalInfo', {fun: this}, this.saveFamilyButton);

                // tap点击返回
                this.obj.off('tap', '.materials-header li.already');
                this.obj.on('tap', '.materials-header li.already', {fun: this}, this.tapReturn);

                // 增加家庭成员
                this.obj.off('tap', '.add');
                this.obj.on('tap', '.add', {fun: this}, this.appendFamilyMember);

                // 删除家庭成员
                this.obj.off('tap', '.content_body .close-btn');
                this.obj.on('tap', '.content_body .close-btn', {fun: this}, this.deleteFamily);

                // 下一步按钮
                this.bottom.off('tap', '.familyGoNext');
                this.bottom.on('tap', '.familyGoNext', {fun: this}, this.familyGoNext);

            },
            getDict: function (data) {
                var _this = this;
                var dict = 'relationship';
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
                            for (var i = 0; i < response.data.personnel.length; i++) {
                                $('select[name=relationship]').eq(i).append(_this.setSelect(response.data.relationship, response.data.personnel[i].relationship));
                                if (response.data.personnel[i] && response.data.personnel[i].relationship) {
                                    $('select[name=relationship]').eq(i).prev().val(_this.getName(response.data.relationship, response.data.personnel[i].relationship));
                                }
                            }
                        } else {
                            $('select[name=relationship]').eq(0).append(_this.setSelect(response.data.relationship));
                        }

                        // 给隐藏的select加载数据
                        var hiddenSelect = '<option disabled value="" selected>请选择关系</option>\
                            {{~it.relationship:value:index}}\
                                <option value="{{=value.code}}">{{=value.cnName}}</option>\
                        {{~}}';
                        hiddenSelect = doT.template(hiddenSelect);
                        $('select[name=relationship]').eq(-1).append(hiddenSelect(response.data));
                    }
                }, function (error) {
                    console.log(error);
                });
            },
            setSelect: function (list, code) {
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
                    for (var i = 0; i < list.length; i++) {
                        option += '<option value="' + list[i].code + '">' + list[i].cnName + '</option>';
                    }
                }
                return option;
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
            getFamilyMember: function () {
                var _this = this;
                if (!_this.personnelId) {
                    return false;
                }
                var familyParam = {
                    ifOpenLoading: true,
                    url: '/personnel/customer/personnel/getFamilyMember',
                    contentType: 'application/x-www-form-urlencoded',
                    type: 'POST',
                    data: {
                        personnelId: _this.personnelId
                    }
                };

                Interface.getAsynData(familyParam, function (response) {
                    if (response.code === '1' && response.data.length > 0) {
                        _this.obj.html(template(response.data));
                        _this.getDict(response.data);
                    } else {
                        _this.obj.html(template(_this.familyListDefault));
                        _this.getDict();
                    }
                }, function (error) {
                    _this.obj.html(template(_this.familyListDefault));
                    _this.getDict();
                    console.log(error);
                });
            },
            renderBottom: function () {
                this.bottom.removeClass('bot-button').addClass('bot-button');
                var temp = '<div class="buttonGroup">\
                    <button type="button" class="savePersonalInfo">保存</button>\
                    <button type="button" class="goNext familyGoNext">下一步</button>\
                </div>';

                temp = doT.template(temp);
                this.bottom.html(temp(''));
            },
            appendFamilyMember: function () {
                var member = '家庭成员' + $('.content_body').length;
                var operate = $('#family-part');
                var clone = operate.clone().removeAttr('id').removeClass('hidden');
                operate.before(clone);
                operate.prev('.content_body').children('strong.title').text(member);
                if ($('.close-btn').hasClass('hidden')) {
                    $('.close-btn').removeClass('hidden');
                }
                $('.close-btn').show();
            },
            deleteFamily: function (btn) {
                var _this = btn.data.fun;
                $(this).parent('.content_body').remove();
                if ($('.content_body').size() <= 2) {
                    $('.close-btn').hide();
                }
                _this.sortFamily();
            },
            sortFamily: function () {
                $('.content_body').each(function (index, item) {
                    if ($(this).attr('id')) {
                        return false;
                    }
                    $(item).find('.title').text('家庭成员' + (index + 1));
                });
            },
            tapReturn: function () {
                return window.location.href = '#page=' + $(this).data('type');
            },
            formatList: function () {
                var list = [];
                var _this = this;
                $('.content_body ul.ui-form').each(function () {
                    if ($(this).parent().attr('id')) {
                        return false;
                    }
                    var obj = {};
                    obj['relationship'] = $(this).find('select[name=relationship]').val();
                    obj['name'] = $(this).find('input[name=name]').val();
                    obj['telephone'] = $(this).find('input[name=telephone]').val();
                    obj['company'] = $(this).find('input[name=company]').val();
                    obj['post'] = $(this).find('input[name=post]').val();
                    obj['personnelId'] = _this.personnelId;
                    list.push(obj)
                });
                return list;
            },
            checkName: function (type) {
                var message = '';
                switch (type) {
                    case "name":
                        message = "姓名";
                        break;
                    case "relationship":
                        message = "关系";
                        break;
                    case "telephone":
                        message = "电话";
                        break;
                    case "company":
                        message = "公司名称";
                        break;
                    case "post":
                        message = "职称";
                        break;
                }
                ;
                return message;
            },
            saveFamilyButton: function (btn) {
                var _this = btn.data.fun;
                _this.submitFamilyData();
            },
            familyGoNext: function (btn) {
                var _this = btn.data.fun;
                _this.submitFamilyData(true);
            },
            submitFamilyData: function (flag) {
                var _this = this;
                for (var i = 0; i < $(".content_body").size() - 1; i++) {
                    var $li = $(".content_body").eq(i).find("li");
                    for (var j = 0; j < $li.size(); j++) {
                        if (j === 0) {
                            var text = $li.eq(j).find('select').val();
                            if (text === "") {
                                common.alertCreate({
                                    html: "请选择关系"
                                });
                                return false;
                            }
                        } else {
                            var $input = $li.eq(j).find('input');
                            var name = $input.attr('name');
                            if (!$input.val()) {
                                common.alertCreate({
                                    html: "家庭成员" + (i + 1) + _this.checkName(name) + '不能为空！'
                                });
                                return false;
                            }
                            var ret = validation.checkVali($input);
                            if (!ret.status) {
                                common.alertCreate({
                                    html: ret.message
                                });
                                return false;
                            }
                        }
                    }
                }
                var formatList = _this.formatList();
                var paramData = {
                    ifOpenLoading: true,
                    type: 'POST',
                    url: '/personnel/customer/personnel/addFamilyMember',
                    data: JSON.stringify(formatList)
                };

                Interface.getAsynData(paramData, function (response) {
                    if (response.code === '1') {
                        if(flag) {
                            _this.talentInfo.familyList = formatList;
                            common.setLocalStorage('talentInfo', _this.talentInfo, true);
                            return window.location.href = '?personnelId=' + _this.personnelId + '#page=showEducation';
                        } else {
                            common.alertCreate({
                                html: '保存家庭信息成功！'
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
                            html: '保存家庭信息失败！'
                        });
                    }
                });
            }
        };
        funHomePage.init(opt);

    }
});
/**
 * Created by Fairyland on 2018/1/26.
 */