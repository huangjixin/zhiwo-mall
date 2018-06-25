define(function (require, exports, module) {
    var template = require('./person-background.html');
    template = doT.template(template);

    exports.back = function () {
        return '';
    };

    exports.show = function (opt) {

        var funHomePage = {
            init: function (opt) {
                this.obj = opt.body;
                this.bottom = opt.bottom;
                this.personnelInfo = common.getLocalStorage("personnelInfo", true) || '';
                this.userInfo = common.getLocalStorage("userInfo", true) || '';
                this.getPaperInfo();
                this.renderBottom();
                // 保存按钮
                this.bottom.off('tap', '.bot-button .savePersonBackground');
                this.bottom.on('tap', '.bot-button .savePersonBackground', {fun: this}, this.savePersonBackgroundButton);

                // 个人背景切换
                this.obj.off('tap', '.person-background .header-arrow');
                this.obj.on('tap', '.person-background .header-arrow', {fun: this}, this.tabClick);

                // 点击评分
                this.obj.off('tap', '.paper-main .person-icon');
                this.obj.on('tap', '.paper-main .person-icon', {fun: this}, this.toggleStar);

                // 提交paper
                this.obj.off('tap', '.box button.btn-submit');
                this.obj.on('tap', '.box button.btn-submit', {fun: this}, this.submitThisPaper);

                //下一步按钮
                this.bottom.off('tap', '.bot-button .personBackgroundGoNext');
                this.bottom.on('tap', '.bot-button .personBackgroundGoNext', {fun: this}, this.personBackgroundGoNext);

                // tap点击返回
                this.obj.off('tap', '.materials-header li.already');
                this.obj.on('tap', '.materials-header li.already', {fun: this}, this.tapReturn);
            },
            renderBottom: function () {
                this.bottom.removeClass('bot-button').addClass('bot-button');
                var temp = '<div class="buttonGroup">\
                    <button type="button"></button>\
                    <button type="button" class="goNext personBackgroundGoNext">下一步</button>\
                </div>';

                temp = doT.template(temp);
                this.bottom.html(temp(''));
            },

            savePersonBackgroundButton: function (btn) {
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

            toggleStar: function () {
                var index = $($(this).parents('.person-star').children('.person-icon')).index(this);
                var length = $($(this).parents('.person-star').children('.person-icon')).size();
                for (var i = 0; i < length; i++) {
                    if (i <= index) {
                        $(this).parents('.person-star').children('.person-icon').eq(i).addClass('per-star');
                    } else {
                        $(this).parents('.person-star').children('.person-icon').eq(i).removeClass('per-star');
                    }
                }
            },

            //导航栏返回上一页
            tapReturn: function () {
                return window.location.href = '#page=' + $(this).data('type');
            },

            submitThisPaper: function (btn) {
                var _this = btn.data.fun;
                var parent = $(this).parents('.box');
                var listOfData = [];
                $(parent).find('.person-items').each(function () {
                    var obj = {};
                    obj['paperId'] = $(parent).data('id');
                    obj['paperItemId'] = $(this).data('flow');
                    obj['personnelId'] = _this.personnelId;
                    obj['score'] = $(this).find('div.per-star').size();
                    listOfData.push(obj);
                });
                var textArea = $(parent).find('textarea.evaluate').val();

                var submitData = {
                    interviewAction: {
                        flowActionId: '',
                        flowItemId: '1',
                        personnelId: _this.personnelInfo.personnelId
                    },
                    paperInfoList: listOfData,
                    personnelPaper: {
                        evaluate: textArea,
                        paperId: $(parent).data('id'),
                        personnelId: _this.personnelInfo.personnelId,
                        flowItemId: '1'
                    }
                };

                // 提交单个Paper数据
                _this.submitPaper(submitData, parent);
            },

            submitPaper: function (param, $dom) {
                var paramData = {
                    ifOpenLoading: true,
                    url: '/personnel/customer/personnel/insertPersonnelPaperInfo',
                    type: 'POST',
                    data: JSON.stringify(param)
                };

                Interface.getAsynData(paramData, function (response) {
                    if(response.code === '1') {
                        common.alertCreate({
                            html: '提交成功！',
                            callback: function () {
                                var nextPaper = $dom.next('.box');
                                $dom.find('.rs-scores i.header-checked').removeClass('hidden');
                                if(!nextPaper.size()) {
                                    return false;
                                }
                                $dom.find('.header-arrow').addClass('more');
                                $dom.find('.main-item').hide();
                            }
                        });
                    } else {
                        common.alertCreate({
                            html: '提交失败！'
                        });
                    }
                }, function (error) {
                    common.alertCreate({
                        html: '未知错误！'
                    });
                });
            },

            tabClick: function () {
                $(this).parents(".person-background").siblings('.box .box-background').toggle();
                $(this).hasClass('more') ? $(this).removeClass('more') : $(this).addClass('more');
            },

            getPaperInfo: function () {
                var _this = this;
                var progress = Number(_this.personnelInfo.progress);
               	var paperType = progress === 2 ? 1 : progress === 3 ? 2 : progress === 4 ? 3 : 1;
                var paramData = {
                    type: "post",
                    url: '/paper/customer/paper/getPaperByPaperType',
                    contentType: 'application/x-www-form-urlencoded',
                    data: {
                        paperType: paperType,
                        personnelId : _this.personnelInfo.personnelId,
                        orgId: _this.userInfo.companyId
                    }
                };
                Interface.getAsynData(paramData, function (response) {
                    if (response.code === '1') {
                        var render = {
                            renderList: _this.judgePaperStatus(response.data),
                            personnelStatus: _this.personnelInfo.progress
                        };
                        _this.obj.html(template(render));
                        // $('.box').eq(0).find('[name=evaluate]').focus().blur();
                        // _this.obj[0].scrollTo(0, 0);
                        // for(var i = 0; i < $('.box').length; i++) {
                        //     $('.box').eq(i).find('[name=evaluate]').val(render.renderList[i]);
                        // }
                    }
                }, function (error) {
                })
            },

            judgePaperStatus: function (list) {
                var newArray = list.splice(0);
                var len = newArray.length;
                for(var i = 0; i < len; i++) {
                    var length = newArray[i].paperItemVo.length, score = 0, allScore = 0;
                    for(var j = 0; j < length; j++) {
                        score += newArray[i].paperItemVo[j].paperItemPVo.score ? Number(newArray[i].paperItemVo[j].paperItemPVo.score) : 0;
                        allScore += newArray[i].paperItemVo[j].paperItemPVo.totalScore;
                    }
                    newArray[i].score = score;
                    newArray[i].allScore = allScore;
                }
                return newArray;
            },

            personBackgroundGoNext: function (btn) {
                var _this = btn.data.fun;
                var box = $('.box');
                var length = box.length;
                for(var i = 0; i < length; i++) {
                    if(box.eq(i).find('.header-checked').hasClass('hidden')) {
                        common.alertCreate({
                            html: '试卷' + box.eq(i).find('.box-left span').text() + ' 还未作答！'
                        });
                        return false;
                    }
                }
                if(_this.personnelInfo.progress === '3' || _this.personnelInfo.progress === '4') {
                    return window.location.href = '#page=declaration';
                } else {
                    return window.location.href = '#page=application';
                }
            }

        };
        funHomePage.init(opt);
    }
});

