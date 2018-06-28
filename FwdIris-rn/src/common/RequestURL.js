//mobile test
export const HOST = 'http://172.31.249.193:32000/'

/**
 * 业绩模块
 */
//我的业绩
export const MY_ACHIEVEMENT = HOST +  'achivement/queryBasicsActualValue';
export const MY_INCOME = HOST + 'achivement/queryAgentHistoryIncome';
export const RANK_PROMOTION = HOST + 'achivement/agentRankAssessmentPromotion';
export const RANK_EVALUATION = HOST + 'achivement/agentRankAssessmentKeep';

//我的申请
//银行卡
export const BANKCARD_LIST = HOST + 'bankCard/selectByAgentCode';
export const TO_DEFAULT = HOST + 'bankCard/toDefault';

//手机更新
export const CHANGE_MOBILE_NO = HOST + 'mobile/save';

//other
export const SEND_VERIFICATION_CODE = HOST + 'oaCommon/sendVerificationCode';
export const VERIFY_VERIFICATION_CODE = HOST + 'oaCommon/verifyVerificationCode';
