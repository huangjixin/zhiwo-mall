/**
 * 黄记新（Tony）
 * 2018.07.20
 * 主页常量。
 */
const User_TOKEN_CACHE = 'userTokenCache'; // token 缓存
const LOAD_User_TOKEN_CACHE = 'loadUserToken'; // 加载用户缓存

const SIGN_IN_DOING = 'SIGN_IN_DOING'; //正在登陆
const SIGN_IN_DONE = 'SIGN_IN_DONE'; // 登陆完成
const SIGN_IN_ERROR = 'SIGN_IN_ERROR'; // 登陆出错
const SIGN_OUT = 'SIGN_OUT'; // 退出登录

const TEST_DOING = 'TEST_DOING'; //正在测试
const TEST_DONE = 'TEST_DONE'; // 测试完成
const TEST_ERROR = 'TEST_ERROR'; // 测试出错

const NAVIGATE_TARGET = 'navigateTarget'; //跳转到其它目标
const NAVIGATE_DOING = 'navigateDoing'; //正在跳转
const NAVIGATE_DONE = 'navigateDone'; // 跳转完成
const NAVIGATE_ERROR = 'navigateError'; // 跳转出错

export default{
    User_TOKEN_CACHE,
    LOAD_User_TOKEN_CACHE,
    SIGN_OUT,
    SIGN_IN_DOING,
    SIGN_IN_DONE,
    SIGN_IN_ERROR,
    TEST_DOING,
    TEST_DONE,
    TEST_ERROR,
    NAVIGATE_TARGET,
    NAVIGATE_DOING,
    NAVIGATE_DONE,
    NAVIGATE_ERROR
}