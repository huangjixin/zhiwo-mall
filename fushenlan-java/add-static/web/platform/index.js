var modelPage = null;
var slideBar = false;

define(['seajsText', 'seajsCss', 'zeptoTouch', 'zeptoMd5', 'doT', 'interface', 'validation'], function (require, exports) {

    seajs.use(['../../css/page.css', '../../css/family.css', '../../css/plan.css', '../../css/preview.css', '../../css/declaration.css', '../../css/preAdd.css', '../../css/basicInfo.css', '../../css/application.css', '../../css/video.css', '../../css/person-background.css','../../css/pick-interview.css','../../css/showEducation.css', '../../css/swiper.min.css']);

    $(window).bind("hashchange", $.loadPanel).trigger("hashchange");
    var userInfo = common.getLocalStorage('userInfo', true) || '';

    // 点击logo返回首页
    $('.header').on('tap', '.logo', function(){
        if($.setRemark('../home/')){
            return window.location.href = '../platform/';
        }
    });
    // 单选框功能
    $('body').on('change', '.ipt-radio input', function(){
        $(this).parent().addClass('checked');
        $(this).parent().siblings('.checked').removeClass('checked');
    });

    /// 复选框功能
    $('body').on('change', '.ipt-checkbox input', function(){
        $(this).is(':checked') ? $(this).parent().addClass('checked') : $(this).parent().removeClass('checked');
    });
    // 下拉框事件
    $('body').on('change', '.ipt-select select', function(){
        $(this).siblings('.ipt-text').val($(this).children(':checked').text());
    });

    // 遮罩层禁止滑动
    $('body').on('touchmove', '.popup-bg', function(e){
        e.preventDefault();
    });
    // 侧边栏点击事件
    $('.recruit-sidebar').on('tap', 'button.toggle', function () {
        $(this).addClass('active').siblings().removeClass('active');
        return window.location.href = '#page=' + $(this).data('href');

    });

    // 侧边栏隐藏事件
    $('.recruit-sidebar').on('tap', 'button.back', function () {
        slideBar = false;
        $('.recruit-sidebar').removeClass('addWidth');
        $('.recruit-sidebar div.open').show();
        if($('.recent-state').size() > 0) {
            $('.recent-state .full-slide').removeClass('goTop');
            $('.recent-state .half-slide').removeClass('goBot');
        }
    });

    // 打开侧边栏事件
    $('.recruit-sidebar').on('tap', 'div.open', function () {
        slideBar = true;
        $('.recruit-sidebar').addClass('addWidth');
        $('.recruit-sidebar div.open').hide();
        if($('.recent-state').size() > 0) {
            $('.recent-state .full-slide').addClass('goTop');
            $('.recent-state .half-slide').addClass('goBot');
        }
    });


});


// 页面路由
$.loadPanel = function () {
    var page = common.getHash('page');

    switch (page) {
        case '':
        case 'main':
        case null:
        case undefined:
            $.showPanel('../platform/main.js'); //首页
            break;
        case 'family':
            $.showPanel('../plan/family.js'); // 家庭信息
            break;
        case 'preview':
            $.showPanel('../plan/preview.js'); // 人才库查看
            break;
        case 'declaration':
            $.showPanel('../plan/declaration.js'); // 人才库查看
            break;
        case 'plan':
            $.showPanel('../plan/plan.js'); // 人才库查看
            break;
        case 'personalInfo':
            $.showPanel('../platform/personalInfo.js'); // 个人信息
            break;
        case 'reMaterials':
            $.showPanel('../platform/reMaterials.js'); // 甄选材料
            break;
        case 'select':
            $.showPanel('../select/select.js'); // 人才库查看
            break;
        case 'basicInfo':
            $.showPanel('../talent/basicInfo.js'); // 基础信息
            break;
        case 'preAdd':
            $.showPanel('../talent/preAdd.js'); // 准增员
            break;
        case 'application':
            $.showPanel('../application/application.js'); // 准增员
            break;
        case 'video':
            $.showPanel('../video/video.js'); // 准增员
            break;
        case 'person-background':
            $.showPanel('../person-background/person-background.js'); //甄选面试-问题界面
            break;
        case 'pick-interview':
            $.showPanel('../person-background/pick-interview.js'); //甄选面试-提交预览
            break;
        case 'workExperience':
            $.showPanel('../talent/work-experience.js'); //person-background
            break;
        case 'showEducation':
            $.showPanel('../talent/showEducation.js'); //在线增员-工作经历
            break;
        case 'target':
            $.showPanel('../talent/target.js'); //目标管理
            break;
    }
};

// seajs引用各个模块
$.showPanel = function(file){
    var mainbody = $('.recruit-clientInfo');
    seajs.use(file, function(account) {
        // 参数account就指代file模块
        modelPage = account;
        modelPage.show({
            body: mainbody
        });
        window.scrollTo(0, 0);
    });
};
