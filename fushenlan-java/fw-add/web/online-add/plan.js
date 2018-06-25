/**
 * Created by Fairyland on 2018/1/25.
 */
define(function (require, exports, module) {
    var template = require('./plan.html');
    require('datepicker');
    require('moment');
    template = doT.template(template);

    exports.back = function () {
        return '';
    };

    exports.show = function (opt) {

        var funHomePage = {
            init: function (opt) {
                this.state = opt.state;
                this.obj = opt.body;
                this.bottom = opt.bottom;
                this.talentInfo = common.getLocalStorage('talentInfo', true) || {};
                this.userInfo = common.getLocalStorage('userInfo', true) || '';
                this.personnelId = null;
                this.currentTime = common.getFormatDate(new Date());
                this.timer = null;
                if (this.talentInfo.singleInfo && this.talentInfo.singleInfo.id) {
                    this.personnelId = this.talentInfo.singleInfo.id;
                } else {
                    this.personnelId = common.getQuery('personnelId') || '';
                }
                if(!this.userInfo) {
                    common.alertCreate({
                        html: '代理人信息丢失，请重新登录！',
                        callback: function () {
                            return window.location.href = '../login.html';
                        }
                    });
                }
                this.defaultApply = {
                    createTime: '',
                    refereeName: this.userInfo.accountName,
                    refereeNo: this.userInfo.id,
                    talentPlan: ''
                };

                this.getSystemTime();
                this.renderBottom();
                

                //保存按钮
                this.bottom.off('tap', '.bot-button .savePlan');
                this.bottom.on('tap', '.bot-button .savePlan', { fun: this }, this.savePlanButton);

                // tap点击返回
                this.obj.off('tap', '.materials-header li.already');
                this.obj.on('tap', '.materials-header li.already', { fun: this }, this.tapReturn);

                //  新增教育经历
                this.obj.off('tap', '.bot-button .savePersonalInfo');
                this.obj.on('tap', '.bot-button .savePersonalInfo', { fun: this}, this.saveButton);

                //下一步按钮
                this.bottom.off('tap', '.planConfirm');
                this.bottom.on('tap', '.planConfirm', { fun: this }, this.planConfirm);

                //签署声明2
                this.obj.off("tap", ".span_second");
                this.obj.on("tap", ".span_second", { fun: this }, this.planState);

                // 关闭弹层
                this.state.off("tap", ".planCloseOrSure button");
                this.state.on("tap", ".planCloseOrSure button", { fun: this }, this.closeState);

                // 设置代理人
                this.obj.off('change', 'input[name=beneficiaryType]');
                this.obj.on('change', 'input[name=beneficiaryType]', {fun: this}, this.setAgent);

                // 代理人姓名
                this.obj.off('keyup', 'input[name=refereeName]');
                this.obj.on('keyup', 'input[name=refereeName]', {fun: this}, this.setAgentName);
                
            },

            queryAgentInfo: function () {
                var agentParam = {
                    ifOpenLoading: true,
                    url: '',
                    type: 'POST',
                    data: {

                    }
                };

                Interface.getAsynData(agentParam, function (response) {
                    if(response.code === '1' && response.data.length > 0) {

                    }
                }, function (error) {

                });
            },

 			refereeInit: function () {
              if($("input[name=beneficiaryType]").is(":checked")){
              	 	$('input[name=refereeName]').removeAttr('disabled');
                    $('input[name=refereeNo]').removeAttr('disabled');
              }else{
              		$('input[name=refereeName]').attr('disabled', 'disabled');
                    $('input[name=refereeNo]').attr('disabled', 'disabled');
              }
            },

            setAgentName: function (btn) {
               /* var _this = btn.data.fun;
                if($(this).prop('readonly')) {
                    return false;
                }
                if(_this.timer) {
                    clearTimeout(_this.timer);
                }
                _this.timer = setTimeout(_this.queryAgentInfo, 200);*/
            },

            setAgent: function () {
                if($(this).is(':checked')) {
                    $('input[name=refereeName]').removeAttr('disabled');
                    $('input[name=refereeNo]').removeAttr('disabled');
                } else {
                    $('input[name=refereeName]').attr('disabled', 'disabled');
                    $('input[name=refereeNo]').attr('disabled', 'disabled');
                }
            },

            getDict: function (data) {
                var _this = this;
                var dict = 'talent_plan';
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

                        var talentPlan = '';
                        if (response.data.personnel && response.data.personnel.talentPlan) {
                            talentPlan = '<option value="" disabled>请选择人才计划</option>\
                                {{~it.talent_plan:value:index}}\
                                    <option value="{{=value.code}}" {{=value.code===it.personnel.talentPlan?"selected":""}}>{{=value.cnName}}</option>\
                        {{~}}';
                        } else {
                            talentPlan = '<option value="" disabled selected>请选择人才计划</option>\
                                {{~it.talent_plan:value:index}}\
                                    <option value="{{=value.code}}">{{=value.cnName}}</option>\
                            {{~}}';
                        }

                        talentPlan = doT.template(talentPlan);
                        $('select[name=talentPlan]').append(talentPlan(response.data));
                        if (response.data.personnel && response.data.personnel.talentPlan) {
                            $('select[name=talentPlan]').prev().val(_this.getName(response.data.talent_plan, response.data.personnel.talentPlan));
                        }
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

            getApply: function () {
                var _this = this;
                var applyParam = {
                    url: '/personnel/customer/personnel/getScanApply',
                    type: 'POST',
                    contentType: 'application/x-www-form-urlencoded',
                    data: {
                        personnelId: _this.personnelId
                    }
                };
                Interface.getAsynData(applyParam, function (response) {
                	var currentAccountId = _this.userInfo.id;
                    if (response.code === '1' && response.data) {
                    	response.data.currentAccountId = currentAccountId;
                        _this.obj.html(template(response.data));
                        _this.loadDefault(response.data);
                    } else {
                        _this.obj.html(template(_this.defaultApply));
                        _this.loadDefault();
                    }
                    _this.refereeInit();
                }, function (error) {
                    _this.obj.html(template(_this.defaultApply));
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
                        if (_this.personnelId) {
                            _this.getApply();
                        } else {
                            _this.obj.html(template(_this.defaultApply));
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

            getSubmitObject: function () {
            	_this = this;
            	var obj = {};
            	 if($("input[name=beneficiaryType]").is(":checked")){
            	 	  obj['refereeName'] = $('input[name=refereeName]').val();
              		  obj['refereeNo'] = $('input[name=refereeNo]').val();
            	 }else{
            	 	 obj['refereeName'] =_this.userInfo.accountName; 
               		 obj['refereeNo'] =_this.userInfo.id;
            	 }
                obj['talentPlan'] = $('select[name=talentPlan]').val();
                obj['createTime'] = $('input[name=createTime]').val();
                obj['personnelId'] = this.personnelId;
                obj['beneficiaryType'] = $('input[name=beneficiaryType]:checked').val() || '';
                obj['qianmingId'] = $('input[name=qianmingId]').val() || '';
                return obj;
            },

            loadDefault: function (data) {
                var _this = this;
                var createTime = new LCalendar();
                createTime.init({
                    trigger: $('input[name=createTime]'),
                    type: 'date',
                    minDate: '1900-1-1',
                    maxDate: _this.currentTime
                });
                this.getDict(data);
                this.state.find('.check-declaration').addClass('hidden');
                this.state.find('.sign').addClass('hidden');
            },

            renderBottom: function () {
                this.bottom.removeClass('bot-button').addClass('bot-button');
                var temp = '<div class="buttonGroup">\
                    <button type="button" class="savePlan">保存</button>\
                    <button type="button" class="goNext planConfirm">下一步</button>\
                </div>';

                temp = doT.template(temp);
                this.bottom.html(temp(''));
            },

            checkName: function (type) {
                var message = '';
                switch (type) {
                    case "refereeName":
                        message = "推荐人";
                        break;
                    case "refereeNo":
                        message = "Agtcode";
                        break;
                    case "createTime":
                        message = "开始日期";
                        break;
                }
                return message;
            },

            savePlanButton: function (btn) {
                var _this = btn.data.fun;
                _this.submitPlanData();
            },

            planConfirm: function (btn) {
                var _this = btn.data.fun;
                _this.submitPlanData(true);
            },

            submitPlanData: function (flag) {
                var _this = this;
                clearTimeout(_this.timer);
                var $list = $(".content-body").find("li");  
                for (var i = 0; i < $list.size()-1; i++) {
                    var liInput=$list.find(".list").find("input");
                    for(var j=0;j<liInput.size();j++){
                        if (j == 0) {
                            var $text = $list.eq(j).find("select").val();
                            if ($text == "") {
                                common.alertCreate({
                                    html: "请选择人才计划"
                                });
                                return false;
                            }
                        } else {
                            var $input = $list.eq(j).find("input");
                            var name = $input.attr("name");
                            if (!$input.val()) {
                                common.alertCreate({
                                    html: _this.checkName(name) + "不能为空"
                                });
                                return false;
                            }
                        }
                    }
                }
                var getSubmitObject = _this.getSubmitObject();
                var paramData = {
                    ifOpenLoading: true,
                    type: 'POST',
                    url: '/personnel/customer/personnel/addApply',
                    data: JSON.stringify(getSubmitObject)
                };
                Interface.getAsynData(paramData, function (response) {
                    if (response.code === '1') {
                        if(flag) {
                            _this.talentInfo.getSubmitObject = getSubmitObject;
                            common.setLocalStorage('talentInfo', _this.talentInfo, true);
                            return window.location.href = '?personnelId=' + _this.personnelId + '#page=preview';    
                        } else {
                            common.alertCreate({
                                html: '计划申请信息保存成功！'
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

            planState:function(btn){
                common.popup('.popup-largeState');
             },

            closeState: function(btn){
            	var _this =  btn.data.fun;
                common.closePopup('.popup-largeState');
                $("input[name='createTime']").val(_this.currentTime);
            }
        };
        funHomePage.init(opt);

    }
});