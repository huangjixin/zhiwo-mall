define(function(require, exports, module){
    var template = require('./add-source.html');
    template = doT.template(template);

    exports.back = function() {
        return '';
    };

    exports.show = function(opt) {

        var funHomePage = {
            init : function(opt){
                this.obj = opt.body;
                this.bottom = opt.bottom;
                this.tagContent = [];
                this.personnelId = common.getQuery('personnelId') || '';
                this.userInfo = common.getLocalStorage('userInfo', true) || {};
                if(this.personnelId && this.userInfo && this.userInfo.id) {
                    this.getPersonnelInfo()
                } else if(!this.personnelId && this.userInfo && this.userInfo.id){
                    this.obj.html(template(''));
                    this.getSystemTag();
                    this.renderBottom();
                } else {

                }

                // 证件扫描
                this.obj.off('tap', '#source-info .paper-scan');
                this.obj.on('tap', '#source-info .paper-scan', {fun: this}, this.recognizePaper);

                // 确定按钮事件
                this.obj.off('tap', '.preAdd-confirm button');
                this.obj.on('tap', '.preAdd-confirm button', {fun: this}, this.getMustIncrease);

                // 添加人才
                this.bottom.off('tap', '.float-bot .save-source');
                this.bottom.on('tap', '.float-bot .save-source', {fun: this}, this.saveSource);

                // 返回上一步
                this.bottom.off('tap', '.float-bot .cancel-source');
                this.bottom.on('tap', '.float-bot .cancel-source', {fun: this}, this.goBack);

                // 模糊匹配标签
                this.obj.off('tap', '.tag-wrapper .close');
                this.obj.on('tap', '.tag-wrapper .close', {fun: this}, this.deleteTag);

                /* 动态设置证件号字符长度 */
                this.obj.off('focus', 'input[name=identityCode]');
                this.obj.on('focus', 'input[name=identityCode]', {fun: this}, this.setCodeLength);

                // 模糊匹配标签
                this.obj.off('tap', '.pull-down .confirm-label');
                this.obj.on('tap', '.pull-down .confirm-label', {fun: this}, this.getTagInfo);

                // 选择系统标签
                this.obj.off('tap', '.system-tag .system-tagItem');
                this.obj.on('tap', '.system-tag .system-tagItem', {fun: this}, this.checkTag);

                // 显示隐藏扫描按钮
                this.obj.off('change', 'input[name=identityType]');
                this.obj.on('change', 'input[name=identityType]', {fun: this}, this.showOrHidden);

                // 显示隐藏扫描按钮
                this.obj.off('tap', '#source-info .initiate-btn');
                this.obj.on('tap', '#source-info .initiate-btn', {fun: this}, this.initiate);
            },

            initiate: function (btn) {
                var _this = btn.data.fun;
                return window.location.href = '?personnelId=' + _this.personnelId + '#page=personnelInfo'
            },

            showOrHidden: function () {
                var _type = ["111", "114", "414", "511", "516"];
                var current = $('input[name=identityType]:checked').val();
                var scanPaper = $('.paper-scan');
                if(_type.indexOf(current) > -1) {
                    if(scanPaper.hasClass('hidden')) {
                        scanPaper.removeClass('hidden');
                    }
                    scanPaper.show();
                } else {
                    scanPaper.hide();
                }
            },

            recognizePaper: function () {
                var identity = $('[name=identityType]:checked').val();
                if (!identity) {
                    common.alertCreate({
                        html: '请先选择证件类型！'
                    });
                    return false;
                }
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
                            default:
                                break;
                        }

                        FULAN_OCR.getIdInfo(function (response) {
                            common.loading.close();
                            var info = {};
                            if(response.result_code == '1') {
                                info = JSON.parse(response.result_info);
                                $('input[name=name]').val(info.id_name);
                                $('input[name=identityCode]').val(info.id_number);
                                var index = info.id_sex === '男' ? 0 : 1;
                                $('input[name=sex]').eq(index).prop('checked', true).parent('label').addClass('checked').siblings('label').removeClass('checked');
                                $('input[name=age]').val(common.getAge(info.id_birthday));
                            }
                        }, function (error) {
                            common.loading.close();
                        }, cardType);
                    }, 300);
                }
            },

            setCodeLength: function () {
                var identityType = $('[name=identityType]:checked').val();
                var _type = {"111":18, "114":18, "117":11, "414":20, "511":8, "516":11};
                return $(this).prop("maxlength",_type[identityType]);
            },

            getPersonnelInfo: function () {
                var _this = this;
                var personnelParam = {
                    url: '/personnel/customer/personnel/getMustIncreaseedit',
                    contentType: 'application/x-www-form-urlencoded',
                    type: 'POST',
                    data: {
                        accountId: _this.userInfo.id,
                        personnelId: _this.personnelId
                    }
                };

                Interface.getAsynData(personnelParam, function (response) {
                    if(response.code === '1' && response.data.personnel) {
                        _this.obj.html(template(response.data));
                        _this.getSystemTag();
                        _this.renderBottom();
                        for(var i = 0; i < response.data.tagList.length; i++) {
                            _this.tagContent.push(response.data.tagList[i].tagName);
                        }
                        $('.initiate').removeClass('hidden');
                    }
                }, function (error) {
                    console.log(error);
                });
            },

            deleteTag: function (btn) {
                var _this = btn.data.fun;
                var index = $('.tag-item').index($(this).parent('.tag-item'));
                _this.tagContent.splice(Number(index), 1);
                $(this).parent('.tag-item').remove();
            },

            getSystemTag: function () {
                var tagParam = {
                    url: '/system/manage/tag/findBymoudle',
                    data: {}
                };

                Interface.getAsynData(tagParam, function (response) {
                    if(response.code === '1' && response.data.length > 0) {
                        var temp = '{{~it:value:index}}\
                            <span class="system-tagItem" data-id="{{=value.id}}">{{=value.tagName}}</span>\
                        {{~}}';

                        temp = doT.template(temp);

                        $('.system-tag').html(temp(response.data));
                    }
                }, function (error) {
                    console.log(error);
                });
            },

            checkTag: function (btn) {
                var _this = btn.data.fun;
                var id = $(this).data('id');
                var content = $(this).text();
                if(_this.checkTagExist(content)) {
                    common.alertCreate({
                        html: '该标签已存在，请勿重复添加！',
                        callback: function () {
                            $('input[name=tagName]').focus();
                        }
                    });
                    return false;
                }
                if(_this.tagContent.length >= 5) {
                    common.alertCreate({
                        html: '最多拥有5个标签！'

                    });
                    return false;
                }
                var temp = '<div class="tag-item" data-id=' + id + '>\
                    <span class="tag-content">' + content + '</span>\
                    <i class="icon-close close"></i>\
                </div>';
                $('.tag-wrapper').append(temp);
                _this.tagContent.push(content);
            },

            checkTagExist: function (txt) {
                var _this = this, length = _this.tagContent.length, flag = false;
                for(var i = 0; i < length; i++) {
                    if(_this.tagContent.indexOf(txt) > -1) {
                        flag = true;
                        return flag;
                    }
                }
                return flag;
            },

            setTag: function (tagName) {
                var temp = '<div class="tag-item" data-id="" data-name=' + tagName + '>\
                    <span class="tag-content">' + tagName + '</span>\
                    <i class="icon-close close"></i>\
                </div>';
                $('.tag-wrapper').append(temp);
                $('.pull-down input').val('');
            },

            getTagInfo: function (btn) {
                var _this = btn.data.fun;
                var tagName = $('input[name=tagName]').val();
                if(_this.checkTagExist(tagName)) {
                    common.alertCreate({
                        html: '该标签已存在，请勿重复添加！',
                        callback: function () {
                            $('input[name=tagName]').focus();
                        }
                    });
                    return false;
                }
                if(_this.tagContent.length >= 5) {
                    common.alertCreate({
                        html: '最多拥有5个标签！'

                    });
                    return false;
                }
                _this.tagContent.push(tagName);
                _this.setTag(tagName);
            },

            goBack: function () {
                return window.history.go(-1);
            },

            saveSource: function () {
                if(!$('input[name=identityType]:checked').val()) {
                    common.alertCreate({
                        html: '请选择证件类型！'
                    });
                    return false;
                }
                if(!validation.checkValiMust($('#source-info'))) {
                    return false;
                }
                if(!$('input[name=sex]:checked').val()) {
                    common.alertCreate({
                        html: '性别不能为空！'
                    });
                    return false;
                }
                var tagList = [];
                $('.tag-item').each(function (index, item) {
                    var obj = {};
                    obj['tagName'] = $(item).find('.tag-content').text();
                    obj['id'] = $(item).data('id');
                    tagList.push(obj);
                });
                var submitForm = {
                    age: $('input[name=age]').val(),
                    identityCode: $('input[name=identityCode]').val(),
                    identityType: $('input[name=identityType]:checked').val(),
                    name: $('input[name=name]').val(),
                    cellphone: $('input[name=cellphone]').val(),
                    sex: $('input[name=sex]:checked').val(),
                    id: $('input[name=id]').val()
                };
                var url = '/personnel/customer/personnel/addPersonnelinfo';
                if($('input[name=id]').val()) {
                    url = '/personnel/customer/personnel/updatePersonnelinfo';
                }
                var sourceParam = {
                    ifOpenLoading : true,
                    type: 'POST',
                    url: url,
                    data: JSON.stringify({
                        personnel: submitForm,
                        tags: tagList
                    })
                };

                Interface.getAsynData(sourceParam, function (response) {
                    if(response.code === '1') {
                        common.alertCreate({
                            html: '添加人才成功！',
                            callback: function () {
                                return window.location.href = '#page=preAdd';
                            }
                        });
                    }
                }, function (error) {
                    common.alertCreate({
                        html: error.msg
                    });
                    return false;
                });
            },

            renderBottom: function () {
                this.bottom.removeClass('bot-button').addClass('bot-button');
                var temp = '<div class="buttonGroup">\
                    <button type="button" class="cancel-source">上一步</button>\
                    <button type="button" class="goNext save-source">保存</button>\
                </div>';
                temp = doT.template(temp);
                this.bottom.html(temp());
            }
        };

        funHomePage.init(opt);
    }
});
