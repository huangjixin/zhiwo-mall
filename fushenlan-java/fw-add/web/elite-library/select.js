define(function (require, exports, module) {
    var template = require('./select.html');
    template = doT.template(template);

    exports.back = function () {
        return '';
    };

    exports.show = function (opt) {

        var funHomePage = {
            init: function (opt) {

                this.obj = opt.body;// 此时obj指代$('.main')dom元素
                this.bottom = opt.bottom;
                this.obj.html(template(''));

                this.personnelInfo = common.getLocalStorage('personnelInfo', true) || {};
                this.userInfo = common.getLocalStorage('userInfo', true) || {};
                this.personnelId = '';
                this.accountId = '';
                this.orgId = '';
                if (this.personnelInfo && this.personnelInfo.personnelId) {
                    this.personnelId = this.personnelInfo.personnelId;
                } else if (common.getQuery('personnelId')) {
                    this.personnelId = common.getQuery('personnelId') || '';
                } else {
                    common.alertCreate({
                        html: '未查到当前人才信息！'
                    });
                }
                if (this.userInfo && this.userInfo.id && this.userInfo.companyId) {
                    this.accountId = this.userInfo.id;
                    this.orgId = this.userInfo.companyId;
                } else {
                    common.alertCreate({
                        html: '登录信息丢失，请重新登录！'
                    });
                }
                this.selectGetPersonnelPool();
                this.select_flowNodebyParam();
                this.renderBottom();

                // 人才库查看切换事件
                this.obj.off('tap', '.tap-left li div');
                this.obj.on('tap', '.tap-left li div', {fun: this}, this.click);

                // 人才库查看面试信息切换事件
                this.obj.off('tap', '.right-bottom-right');
                this.obj.on('tap', '.right-bottom-right', {fun: this}, this.databox_toggle);
            },

            renderBottom: function () {
                this.bottom.removeClass('bot-button');
                var temp = '<img src="../../images/bottom-index.png" alt="">';

                temp = doT.template(temp);

                this.bottom.html(temp());
            },

            selectGetPersonnelPool: function () {
                var _this = this;
                var paramData = {
                    type: "post",
                    url: '/personnel/customer/personnel/getPersonnelPool',
                    contentType: 'application/x-www-form-urlencoded',
                    data: {
                        personnelId: _this.personnelId

                    }
                };
                Interface.getAsynData(paramData, function (response) {
                    if (response.code === '1' && response.data) {

                        var temp = require('./select_getPersonnelPool.html');
                        temp = doT.template(temp);
                        $('.select_getPersonnelPool').html(temp(response.data));
                    }

                }, function (error) {
                    console.log(error)
                })
            },

            click: function (btn) {
                var _this = btn.data.fun;
                var borClass = $(this).hasClass("bor_DEDEDE");
                var colClass = $(this).hasClass("col_DEDEDE");
                if (borClass || colClass) {
                    return false
                }
                var moudleName = $(this).parent().data("moudlename");
                var flowActionId = $(this).parent().data("flowactionid");
                var accountId = $(this).parent().data("accountid");
                var flowItemId = $(this).parent().data("flowitemid");
                var orgId = $(this).parent().data("orgid");

                flowItemId = flowItemId == 2 ? 1 : flowItemId == 3 ? 2 : flowItemId == 4 ? 3 : flowItemId;
                _this.initSignUp(moudleName, flowActionId, accountId, flowItemId);
            },

            databox_toggle: function (btn) {
                var _this = btn.data.fun;
                $(this).next().toggle()
            },

            select_flowNodebyParam: function () {
                var _this = this;
                var paramData = {
                    type: "get",
                    url: '/flow/customer/interviewFlow/flowNodebyParam',
                    data: {
                        personnelId: _this.personnelId,
                        orgId: _this.userInfo.companyId
                    }
                };
                Interface.getAsynData(paramData, function (response) {
                    if (response.code === '1' && response.data) {
                        var temp = require('./select_flowNodebyParam.html');
                        temp = doT.template(temp);
                        $('.select_flowNodebyParam ul').html(temp(response.data));

                        // 初始化查询报名信息
                        _this.initSignUp(response.data[0].moudleName, response.data[0].flowActionId, _this.accountId, response.data[0].flowItemId);
                    }

                }, function (error) {
                })
            },

            renderTemp: function (requestData, request) {
                var temp = '{{?it}}<div class="header-left">\
                            <i class="icon-shenfenzheng"></i>\
                             <div class="text">\
                                 <p>{{=it.interviewerDetialVo.processingName || ""}}</p>\
                                 <p>{{=it.interviewerDetialVo.postType || ""}} {{=it.interviewerDetialVo.orgName || ""}}</p>\
                                 <p><i class="icon-tuandui"></i>团队规模  {{=it.interviewerDetialVo.teamSize || ""}}人</p>\
                             </div>\
                         </div>\
                         <div class="header-right">\
                             <h5>核查结论：</h5>\
                             <p>{{=it.interviewerDetialVo.processingDesc || ""}}<br>\
                             <em>{{=""}}</em></p>\
                         </div>\
                         {{?}}';
                temp = doT.template(temp);
                $('.wraper .bottom-header').html(temp(request));
            },

            renderPapers: function (requestData, request) {
                if (Number(requestData.data.moudleName) >= 2 && request.paperList.length > 0) {
                    var totalScore = 0, score = 0, paperList = [];
                    for (var i = 0; i < request.paperList.length; i++) {
                        var typeList = {}, nowScore = 0, nowTotalPoints = 0, objArray = [];
                        for (var j = 0; j < request.paperList[i].paperInfoList.length; j++) {
                            var obj = {};
                            score += request.paperList[i].paperInfoList[j].score;
                            totalScore += request.paperList[i].paperInfoList[j].totalScore;
                            obj['paperItemDesc'] = request.paperList[i].paperInfoList[j].paperItemDesc;
                            obj['paperItemName'] = request.paperList[i].paperInfoList[j].paperItemName;
                            obj['score'] = request.paperList[i].paperInfoList[j].score;
                            obj['totalScore'] = request.paperList[i].paperInfoList[j].totalScore;
                            nowScore += request.paperList[i].paperInfoList[j].score;
                            nowTotalPoints += request.paperList[i].paperInfoList[j].totalScore;
                            objArray.push(obj);
                            typeList['objArray'] = objArray;
                        }
                        typeList['paperName'] = request.paperList[i].paperName;
                        typeList['nowScore'] = nowScore;
                        typeList['nowTotalPoints'] = nowTotalPoints;
                        typeList['evaluate'] = request.paperList[i].evaluate;
                        paperList.push(typeList);

                    }

                    var temp2 = '\
                    <li class="clearfix first">\
                        <div class="right-bottom-left fl"> 试卷信息 </div>\
                        <div class="right-bottom-right fr"> <i class="icon-gouxuan"></i> </div>\
                        <div class="databox">\
                        	 {{~it:value:index}}\
						        <div class="preson-bg {{=index!=0?"hide-paper":""}}">\
						            <div class="preson-bg-top">\
						                <div class="bg-top-left">\
						                    <span class="icon-wenjian"></span> <span style="padding-left: 16px">{{=value.paperName  || ""}}</span>\
						                </div>\
						                <div class="bg-top-right">\
						                    <span class="">{{=value.nowScore || 0}} </span><span>/ {{=value.nowTotalPoints || 0}} 分</span>\
						                    <i class="bg-gouxian icon-gouxuan"></i>\
						                    <i class="bg-arrowup icon-xiajiantou {{=index===0?"bg-arrow-down":""}}"></i>\
                                        </div>\
						            </div>\
						            <div class="shrink-area">\
						                {{~value.objArray:items:item}}\
						                <div class="preson-bg-list">\
						                    <strong>{{=items.paperItemName  || ""}}</strong>\
						                    <ul class="stars-list">\
						                        <li class="{{=items.score>=1 ? "per-star":""}}"><i class="icon-stars"></i><span>1分</span></li>\
						                        <li class="{{=items.score>=2 ? "per-star":""}}"><i class="icon-stars"></i><span>2分</span></li>\
						                        <li class="{{=items.score>=3 ? "per-star":""}}"><i class="icon-stars"></i><span>3分</span></li>\
						                        <li class="{{=items.score>=4 ? "per-star":""}}"><i class="icon-stars"></i><span>4分</span></li>\
						                        <li class="{{=items.score>=5 ? "per-star":""}}"><i class="icon-stars"></i><span>5分</span></li>\
						                    </ul>\
						                </div>\
						                {{~}}\
						                <div class="preson-bg-asses">\
						                    <strong>评价</strong>\
						                    <p>{{=value.evaluate}}</p>\
						                </div>\
						            </div>\
						        </div>\
						        {{~}}\
                        </div>\
                    </li>';

                    temp2 = doT.template(temp2);
                    $('.wraper ul.right-bottom').html(temp2(paperList));
                }
            },

            render: function (requestData, request) {
                var _this = this;
                _this.renderTemp(requestData, request);
                if (requestData.data.moudleName == "1") {
                    var temp2 = '\
                    {{?it}}\
                    <li class="clearfix first">\
                        <div class="right-bottom-left fl"> 个人信息 </div>\
                        <div class="right-bottom-right fr"> <i class="icon-gouxuan"></i> </div> \
                        <div class="databox">\
                            <div class="data">\
                                <div>姓名：{{=it.personnel.name || ""}}</div>\
                                <div>性别：{{=it.personnel.sex==="M"?"男":"女"}}</div>\
                                <div>生日：{{=it.personnel.birthday || ""}}</div>\
                                <div>民族：{{=it.personnel.nation || ""}}</div>\
                                <div>学历：{{=it.personnel.education || ""}}</div>\
                                <div>国籍：{{=it.personnel.country || ""}}</div>\
                                <div>年龄：{{=it.personnel.birthday ? common.getAge(it.personnel.birthday) : ""}}</div>\
                                <div>来源渠道：{{=it.personnel.channel || ""}}</div>\
                                <div>证件有效期至：{{=it.personnel.ctfexpireDate || ""}}</div>\
                                <div style="">证件类型：{{=it.personnel.identityType=="111"?"身份证":it.personnel.identityType=="414"?"护照":it.personnel.identityType=="117"?"出生证":it.personnel.identityType=="114"?"军人证":it.personnel.identityType=="511"?"台胞证":it.personnel.identityType=="516"?"港澳回乡证":""}}</div>\
                                <div>证件号：{{=it.personnel.identityCode || ""}}</div>\
                                <div>政治面貌：{{=it.personnel.political || ""}}</div>\
                                <div>新人来源：{{=it.personnel.source || ""}}</div>\
                            </div>\
                        </div>\
                    </li>\
                    <li class="clearfix">\
                        <div class="right-bottom-left fl"> 家庭信息 </div>\
                        <div class="right-bottom-right fr"> <i class="icon-gouxuan"></i> </div> \
                        <div class="databox">\
                            <div class="data">\
                                {{~it.familyMember:val:index}}\
                                        <div>关系：{{=val.relationship || ""}}</div>\
                                        <div>姓名：{{=val.name || ""}}</div>\
                                        <div>电话：{{=val.telephone || ""}}</div>\
                                        <div>公司：{{=val.company || ""}}</div>\
                                        <div>职位：{{=val.post || ""}}</div>\
                                {{~}}\
                            </div>\
                        </div>\
                    </li>\
                    <li class="clearfix">\
                        <div class="right-bottom-left fl"> 教育信息 </div>\
                        <div class="right-bottom-right fr"> <i class="icon-gouxuan"></i> </div> \
                        <div class="databox">\
                            <div class="data">\
                                {{~it.educational:val:index}}\
                                    <div>开始时间：{{=common.getFormatDate(new Date(val.startTime)) || ""}}</div>\
                                    <div>结束时间：{{=common.getFormatDate(new Date(val.endTime)) || ""}}</div>\
                                    <div>学历：{{=val.education || ""}}</div>\
                                    <div>毕业院校：{{=val.school || ""}}</div>\
                                {{~}}\
                            </div>\
                        </div>\
                    </li>\
                    <li class="clearfix">\
                        <div class="right-bottom-left fl"> 工作经历</div>\
                        <div class="right-bottom-right fr"> <i class="icon-gouxuan"></i> </div> \
                        <div class="databox">\
                           <div class="data">\
                                {{~it.workExperience:val:index}}\
                                    <div>开始年月：{{=common.getFormatDate(new Date(val.startTime)) || ""}}</div>\
                                    <div>结束年月：{{=common.getFormatDate(new Date(val.endTime)) || ""}}</div>\
                                    <div>行业：{{=val.occupation || ""}}</div>\
                                    <div>公司：{{=val.company || ""}}</div>\
                                    <div>职位：{{=val.post || ""}}</div>\
                                    <div>年收入：{{=val.annualIncome || ""}}万</div>\
                                {{~}}\
                           </div>\
                        </div>\
                    </li>\
                    <li class="clearfix">\
                        <div class="right-bottom-left fl"> 计划申请</div>\
                        <div class="right-bottom-right fr"> <i class="icon-gouxuan"></i> </div> \
                        <div class="databox">\
                            <div class="data">\
                            {{~it.apply:val:index}}\
                            <div style="width: 28%;">开始年月：{{=val.createTime || ""}}</div>\
                            <div style="width: 25%;">推荐人：{{=val.refereeName || ""}}</div>\
                            <div style="width: 47%;">推荐人编号：{{=val.refereeNo || ""}}</div>\
                            <div>人才计划：{{=val.talentPlan || ""}}</div>\
                            {{~}}\
                            </div>\
                        </div>\
                    </li>\
                    {{?}}';
                    temp2 = doT.template(temp2);
                    $('.wraper ul.right-bottom').html(temp2(request.personnelManageInfoVo));
                } else if (requestData.data.moudleName == "2") {
                    _this.renderPapers(requestData, request)

                } else if (requestData.data.moudleName == "3") {
                    _this.renderPapers(requestData, request)

                } else if (requestData.data.moudleName == "4") {
                    _this.renderPapers(requestData, request)

                } else if (requestData.data.moudleName == "5") {
                  var temp2 = '\
                    {{?it}}\
                    <li class="clearfix first">\
                        <div class="right-bottom-left fl"> 个人信息 </div>\
                        <div class="right-bottom-right fr"> <i class="icon-gouxuan"></i> </div> \
                        <div class="databox">\
                            <div class="data">\
                                <div>姓名：{{=it.personnel.name || ""}}</div>\
                                <div>性别：{{=it.personnel.sex==="M"?"男":"女"}}</div>\
                                <div>生日：{{=it.personnel.birthday || ""}}</div>\
                                <div>民族：{{=it.personnel.nation || ""}}</div>\
                                <div>学历：{{=it.personnel.education || ""}}</div>\
                                <div>国籍：{{=it.personnel.country || ""}}</div>\
                                <div>年龄：{{=it.personnel.birthday ? common.getAge(it.personnel.birthday) : ""}}</div>\
                                <div>来源渠道：{{=it.personnel.channel || ""}}</div>\
                                <div>证件有效期至：{{=it.personnel.ctfexpireDate || ""}}</div>\
                                <div>证件类型：{{=it.personnel.identityType=="111"?"身份证":it.personnel.identityType=="414"?"护照":it.personnel.identityType=="117"?"出生证":it.personnel.identityType=="114"?"军人证":it.personnel.identityType=="511"?"台胞证":it.personnel.identityType=="516"?"港澳回乡证":""}}</div>\
                                <div>证件号：{{=it.personnel.identityCode || ""}}</div>\
                                <div>政治面貌：{{=it.personnel.political || ""}}</div>\
                                <div>新人来源：{{=it.personnel.source || ""}}</div>\
                            </div>\
                        </div>\
                    </li>\
                    <li class="clearfix">\
                        <div class="right-bottom-left fl"> 家庭信息 </div>\
                        <div class="right-bottom-right fr"> <i class="icon-gouxuan"></i> </div> \
                        <div class="databox">\
                            <div class="data">\
                                {{~it.familyMember:val:index}}\
                                        <div>关系：{{=val.relationship || ""}}</div>\
                                        <div>姓名：{{=val.name || ""}}</div>\
                                        <div>电话：{{=val.telephone || ""}}</div>\
                                        <div>公司：{{=val.company || ""}}</div>\
                                        <div>职位：{{=val.post || ""}}</div>\
                                {{~}}\
                            </div>\
                        </div>\
                    </li>\
                    <li class="clearfix">\
                        <div class="right-bottom-left fl"> 教育信息 </div>\
                        <div class="right-bottom-right fr"> <i class="icon-gouxuan"></i> </div> \
                        <div class="databox">\
                            <div class="data">\
                                {{~it.educational:val:index}}\
                                    <div>开始时间：{{=common.getFormatDate(new Date(val.startTime)) || ""}}</div>\
                                    <div>结束时间：{{=common.getFormatDate(new Date(val.endTime)) || ""}}</div>\
                                    <div>学历：{{=val.education || ""}}</div>\
                                    <div>毕业院校：{{=val.school || ""}}</div>\
                                {{~}}\
                            </div>\
                        </div>\
                    </li>\
                    <li class="clearfix">\
                        <div class="right-bottom-left fl"> 工作经历</div>\
                        <div class="right-bottom-right fr"> <i class="icon-gouxuan"></i> </div> \
                        <div class="databox">\
                           <div class="data">\
                                {{~it.workExperience:val:index}}\
                                    <div>开始年月：{{=common.getFormatDate(new Date(val.startTime)) || ""}}</div>\
                                    <div>结束年月：{{=common.getFormatDate(new Date(val.endTime)) || ""}}</div>\
                                    <div>行业：{{=val.occupation || ""}}</div>\
                                    <div>公司：{{=val.company || ""}}</div>\
                                    <div>职位：{{=val.post || ""}}</div>\
                                    <div>年收入：{{=val.annualIncome || ""}}万</div>\
                                {{~}}\
                           </div>\
                        </div>\
                    </li>\
                    <li class="clearfix">\
                        <div class="right-bottom-left fl"> 计划申请</div>\
                        <div class="right-bottom-right fr"> <i class="icon-gouxuan"></i> </div> \
                        <div class="databox">\
                            <div class="data">\
                            {{~it.apply:val:index}}\
                            <div style="width: 28%;">开始年月：{{=val.createTime || ""}}</div>\
                            <div style="width: 25%;">推荐人：{{=val.refereeName || ""}}</div>\
                            <div style="width: 47%;">推荐人编号：{{=val.refereeNo || ""}}</div>\
                            <div>人才计划：{{=val.talentPlan || ""}}</div>\
                            {{~}}\
                            </div>\
                        </div>\
                    </li>\
                    {{?}}';
                    temp2 = doT.template(temp2);
                    $('.wraper ul.right-bottom').html(temp2(request.personnelManageInfoVo));
                } else if (requestData.data.moudleName == "6") {
                  var temp2 = '\
                    {{?it}}\
                    <li class="clearfix first">\
                        <div class="right-bottom-left fl"> 个人信息 </div>\
                        <div class="right-bottom-right fr"> <i class="icon-gouxuan"></i> </div> \
                        <div class="databox">\
                            <div class="data">\
                                <div>姓名：{{=it.personnel.name || ""}}</div>\
                                <div>性别：{{=it.personnel.sex==="M"?"男":"女"}}</div>\
                                <div>生日：{{=it.personnel.birthday || ""}}</div>\
                                <div>民族：{{=it.personnel.nation || ""}}</div>\
                                <div>学历：{{=it.personnel.education || ""}}</div>\
                                <div>国籍：{{=it.personnel.country || ""}}</div>\
                                <div>年龄：{{=it.personnel.birthday ? common.getAge(it.personnel.birthday) : ""}}</div>\
                                <div>来源渠道：{{=it.personnel.channel || ""}}</div>\
                                <div>证件有效期至：{{=it.personnel.ctfexpireDate || ""}}</div>\
                                <div>证件类型：{{=it.personnel.identityType=="111"?"身份证":it.personnel.identityType=="414"?"护照":it.personnel.identityType=="117"?"出生证":it.personnel.identityType=="114"?"军人证":it.personnel.identityType=="511"?"台胞证":it.personnel.identityType=="516"?"港澳回乡证":""}}</div>\
                                <div>证件号：{{=it.personnel.identityCode || ""}}</div>\
                                <div>政治面貌：{{=it.personnel.political || ""}}</div>\
                                <div>新人来源：{{=it.personnel.source || ""}}</div>\
                            </div>\
                        </div>\
                    </li>\
                    <li class="clearfix">\
                        <div class="right-bottom-left fl"> 家庭信息 </div>\
                        <div class="right-bottom-right fr"> <i class="icon-gouxuan"></i> </div> \
                        <div class="databox">\
                            <div class="data">\
                                {{~it.familyMember:val:index}}\
                                        <div>关系：{{=val.relationship || ""}}</div>\
                                        <div>姓名：{{=val.name || ""}}</div>\
                                        <div>电话：{{=val.telephone || ""}}</div>\
                                        <div>公司：{{=val.company || ""}}</div>\
                                        <div>职位：{{=val.post || ""}}</div>\
                                {{~}}\
                            </div>\
                        </div>\
                    </li>\
                    <li class="clearfix">\
                        <div class="right-bottom-left fl"> 教育信息 </div>\
                        <div class="right-bottom-right fr"> <i class="icon-gouxuan"></i> </div> \
                        <div class="databox">\
                            <div class="data">\
                                {{~it.educational:val:index}}\
                                    <div>开始时间：{{=common.getFormatDate(new Date(val.startTime)) || ""}}</div>\
                                    <div>结束时间：{{=common.getFormatDate(new Date(val.endTime)) || ""}}</div>\
                                    <div>学历：{{=val.education || ""}}</div>\
                                    <div>毕业院校：{{=val.school || ""}}</div>\
                                {{~}}\
                            </div>\
                        </div>\
                    </li>\
                    <li class="clearfix">\
                        <div class="right-bottom-left fl"> 工作经历</div>\
                        <div class="right-bottom-right fr"> <i class="icon-gouxuan"></i> </div> \
                        <div class="databox">\
                           <div class="data">\
                                {{~it.workExperience:val:index}}\
                                    <div>开始年月：{{=common.getFormatDate(new Date(val.startTime)) || ""}}</div>\
                                    <div>结束年月：{{=common.getFormatDate(new Date(val.endTime)) || ""}}</div>\
                                    <div>行业：{{=val.occupation || ""}}</div>\
                                    <div>公司：{{=val.company || ""}}</div>\
                                    <div>职位：{{=val.post || ""}}</div>\
                                    <div>年收入：{{=val.annualIncome || ""}}万</div>\
                                {{~}}\
                           </div>\
                        </div>\
                    </li>\
                    <li class="clearfix">\
                        <div class="right-bottom-left fl"> 计划申请</div>\
                        <div class="right-bottom-right fr"> <i class="icon-gouxuan"></i> </div> \
                        <div class="databox">\
                            <div class="data">\
                            {{~it.apply:val:index}}\
                            <div style="width: 28%;">开始年月：{{=val.createTime || ""}}</div>\
                            <div style="width: 25%;">推荐人：{{=val.refereeName || ""}}</div>\
                            <div style="width: 47%;">推荐人编号：{{=val.refereeNo || ""}}</div>\
                            <div>人才计划：{{=val.talentPlan || ""}}</div>\
                            {{~}}\
                            </div>\
                        </div>\
                    </li>\
                    {{?}}';
                    temp2 = doT.template(temp2);
                    $('.wraper ul.right-bottom').html(temp2(request.personnelManageInfoVo));
                } else if (requestData.data.moudleName == "7") {
                    
                }
            },

            initSignUp: function (moudleName, flowActionId, accountId, flowItemId) {
                var _this = this;
                var requestData = {
                    personnelId: _this.personnelId,
                    flowActionId: flowActionId,
                    flowItemId: flowItemId,
                    accountId: accountId,
                    moudleName: moudleName,
                    orgId: _this.orgId
                };
                var paramData = {
                    ifOpenLoading: true,
                    url: '/flow/customer/interviewFlow/interviewmessage',
                    contentType: 'application/x-www-form-urlencoded',
                    type: 'POST',
                    data: requestData
                };
                Interface.getAsynData(paramData, function (response) {
                    if (response.code === '1' && response.data) {
                        _this.render(paramData, response.data)
                    }
                }, function (error) {

                });
            }
        };

        funHomePage.init(opt);
    }
});