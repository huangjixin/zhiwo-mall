var modelPage = null;
var slideBar = false;//139.219.231.37
var uploadLink= '' ; // "http://139.219.231.37:30002/attachment/uploadFiles";


var highSlide = {
    main: ['main', 'target', 'reMaterials', 'video', 'application', 'basicInfo', 'declaration', 'pickInterview'],
    addRecruit: ['addRecruit', 'personnelInfo', 'family', 'showEducation', 'workExperience', 'plan', 'preview'],
    preAdd: ['preAdd', 'addSource']
};

define(['seajsText', 'seajsCss', 'zeptoTouch', 'zeptoMd5', 'doT', 'interface', 'validation'], function (require, exports) {

    seajs.use(['../../css/page.css', '../../css/ld-talent.css', '../../css/meng.css', '../../css/start-add.css', '../../css/swiper.min.css']);

    $(window).bind("hashchange", $.loadPanel).trigger("hashchange");

    var userInfo = common.getLocalStorage('userInfo', true) || '';

    if(userInfo && userInfo.id) {
        $('.hello-user span.user-name').text(userInfo.accountName);
    }

    var body = $('body'), header = $('.header');

    // 点击logo返回首页
    header.on('tap', '.logo', function () {
        $.backToIndex('../my-add/#page=main');
    });

    // 退出
    header.on('tap', '.btn-menu', function () {
        common.confirmCreate({
            html: '<p>是否确定返回登录界面？</p>',
            callback: function (status) {
                if (status === 1) {
                    common.cleanLocalStorage();
                    return window.location.href = '../login/login.html';
                }
            }
        });
    });

    // 单选框功能
    body.on('change', '.ipt-radio input', function () {
        $(this).parent().addClass('checked');
        $(this).parent().siblings('.checked').removeClass('checked');
    });

    /// 复选框功能
    body.on('change', '.ipt-checkbox input', function () {
        $(this).is(':checked') ? $(this).parent().addClass('checked') : $(this).parent().removeClass('checked');
    });

    // 下拉框事件
    body.on('change', '.ipt-select select', function () {
        $(this).siblings('.ipt-text').val($(this).children(':checked').text());
    });

    // 遮罩层禁止滑动
    body.on('touchmove', '.popup-bg', function (e) {
        e.preventDefault();
    });

    var slideBarStatus = $('.recruit-sidebar'), mainContent = $('.recruit-clientInfo');

    // 侧边栏点击事件
    slideBarStatus.on('tap', 'button.toggle', function () {
        if(!slideBar) {
            return;
        }
        common.removelocalStorage('personnelId');
        common.removelocalStorage('talentInfo');
        return window.location.href = '../my-add/#page=' + $(this).data('href');
    });

    // 侧边栏隐藏事件
    var open =  $('.recruit-sidebar div.open');
    slideBarStatus.on('tap', 'button.back', function () {
        slideBar = false;
        $('.recruit-sidebar').removeClass('addWidth');
        mainContent.removeClass('shrink');
        $('.recruit-sidebar div.open').show();
        if($('.recent-state').size() > 0) {
            $('.recent-state .full-slide').removeClass('goTop');
            $('.recent-state .half-slide').removeClass('goBot');
        }
    });

    // 打开侧边栏事件
    slideBarStatus.on('tap', 'div.open', function () {
        slideBar = true;
        $('.recruit-sidebar').addClass('addWidth');
        mainContent.addClass('shrink');
        $('.recruit-sidebar div.open').hide();
        if($('.recent-state').size() > 0) {
            $('.recent-state .full-slide').addClass('goTop');
            $('.recent-state .half-slide').addClass('goBot');
        }
    });

    /* 表单失去焦点监控验证 */
    $('body').on('blur', '.check-vali', function(){
        var _this = $(this);
        var _ipt = validation.checkVali($(this));
        if(!_ipt.status){
            common.alertCreate({
                html : _ipt.message,
                callback : function(){
                    _this.val('');
                }
            });
        }
    });

    // 获取上传路径
    var uploadParam = {
        url: '/system/dictionary/code/findByCode',
        data: {
            code: 'uploadPath'
        }
    };

    Interface.getAsynData(uploadParam, function (response) {
        if(response.code === '1') {
            uploadLink = response.data.value + '/attachment/uploadFiles';
        }
    }, function (error) {
        common.alertCreate({
            html: '未获取到上传路径！'
        })
    });
});


// 页面路由
$.loadPanel = function () {
    var page = common.getHash('page');
    switch (page) {
        /* 首页 */
        case '':
        case 'main':
        case null:
        case undefined:
            $.showPanel('../my-add/main.js'); //首页
            break;
        case 'target':
            $.showPanel('../my-add/target.js'); // 目标设置
            break;
        case 'select':
            $.showPanel('../elite-library/select.js'); // 人才库查看
            break;
        /* 首页 */

        /* 在线增员 */
        case 'addRecruit':
            $.showPanel('../online-add/add-recruit.js'); // 在线增员列表
            break;
        case 'personnelInfo':
            $.showPanel('../online-add/personnelInfo.js'); // 个人信息
            break;
        case 'family':
            $.showPanel('../online-add/family.js'); // 家庭信息
            break;
        case 'showEducation':
            $.showPanel('../online-add/showEducation.js'); // 教育信息
            break;
        case 'workExperience':
            $.showPanel('../online-add/work-experience.js'); // 工作经历
            break;
        case 'plan':
            $.showPanel('../online-add/plan.js'); // 计划申请
            break;
        case 'preview':
            $.showPanel('../online-add/preview.js'); // 在线增员提交预览
            break;
        /* 在线增员 */

        /* 初面 */
        case 'reMaterials':
            $.showPanel('../add-link/reMaterials.js'); // 甄选材料
            break;
        case 'video':
            $.showPanel('../add-link/video.js'); // 证件资料
            break;
        case 'application':
            $.showPanel('../add-link/application.js'); // 计划申请预览
            break;
        /* 初面 */

        /* 甄选面试、决定面试 */
        case 'basicInfo':
            $.showPanel('../add-link/basicInfo.js'); // 基础信息
            break;
        case 'declaration':
            $.showPanel('../add-link/declaration.js'); // 甄选面试-声明与告知
            break;
        case 'pickInterview':
            $.showPanel('../add-link/pick-interview.js'); //甄选面试-提交预览
            break;
        /* 甄选面试、决定面试 */

        /* 人才库 */
        case 'preAdd':
            $.showPanel('../typeIn-trial/preAdd.js'); // 人才库：准增员 增员中 代理人
            break;
        case 'addSource':
            $.showPanel('../typeIn-trial/add-source.js'); // 人才库查看
            break;
        /* 人才库 */

        /* 初审、甄选、决定面试问卷 */
        case 'personBackground':
            $.showPanel('../add-link/person-background.js'); // 问题界面
            break;
        /* 初审、甄选、决定面试问卷 */

        /*线下培训-即将开始*/
        case 'mustStart':
            $.showPanel('../add-link/must-start.js'); // 线下培训
            break;
        /*线下培训-即将开始 */
    }
    //  设置侧边栏高亮
    $('.recruit-sidebar button.' + $.checkModule(page)).addClass('active').siblings().removeClass('active');
};

// seajs引用各个模块
$.showPanel = function (file) {
    var mainBody = $('.recruit-clientInfo');
    var bottom = $('.float-bot');
    var popup = $('.popup-suggest');
    var largeImg = $('.popup-largeImg');
    var largeState = $('.popup-largeState');
    seajs.use(file, function (account) {
        // 参数account就指代file模块
        modelPage = account;
        modelPage.show({
            body: mainBody,
            popup: popup,
            bottom: bottom,
            img: largeImg,
            state: largeState
        });
        mainBody[0].scrollTo(0, 0);
    });
};

// 刷新页面后检测侧边栏高亮
$.checkModule = function (hash) {
    for(var key in highSlide) {
        for(var i = 0; i < highSlide[key].length; i++) {
            if(highSlide[key][i] === hash) {
                return key;
            }
        }
    }
};

// 点击logo返回首页时，提示并清除缓存
$.backToIndex = function (url) {
    var onlineAdd = ['addRecruit', 'personnelInfo', 'family', 'showEducation', 'workExperience', 'plan', 'preview'];
    var FInterview = ['reMaterials', 'video', 'personBackground', 'application'];
    var STInterview = ['basicInfo', 'personBackground', 'declaration', 'pickInterview'];
    var personnelInfo = common.getLocalStorage('personnelInfo', true) || {};
    var progress = null, personnelId = '';

    if(personnelInfo && personnelInfo.progress && personnelInfo.personnelId) {
        progress = personnelInfo.progress;
        personnelId = personnelInfo.personnelId;
        common.removelocalStorage('personnelInfo');
    }
    var __page__ = common.getHash('page');
    if(onlineAdd.indexOf(__page__) > -1 || (progress === 1 && personnelId)) {
        common.removelocalStorage('talentInfo');
    } else if (FInterview.indexOf(__page__) > -1 && progress == 2) {

    } else if(STInterview.indexOf(__page__) > -1 && progress == 3) {

    } else if(STInterview.indexOf(__page__) > -1 && progress == 4) {

    } else {

    }
    return window.location.href = url;
};
