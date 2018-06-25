package com.fulan.application.context;

import org.springframework.beans.factory.annotation.Value;

public class CommenConstant {
	
	public static final  String FWD_ELEARNING = "0";  // 学习平台
	public static final  String FWD_ERECRUITMENT = "1";  // 招募平台
	public static final  String FWD_MESSAGE = "2";  // 短信平台
	public static final  String FWD_PRODUCTCENTER = "3";  // 产品中心
	
	public static final  String FWD_ELEARNING_NAME = "elearning";  // 学习平台
	public static final  String FWD_ERECRUITMENT_NAME = "erecruitment";  // 招募平台
	public static final  String FWD_MESSAGE_NAME = "smsCenter";  // 短信平台
	public static final  String FWD_PRODUCTCENTER_NAME = "productCenter";  // 产品中心
	
	public static final  Integer ER_PAPER_STATUS = 1;  // 定义常量
 

	public static String IMAGEDIR = "IMAGEDIR";
	public static final String ftpuploadusername="fwdadmin";
	public static final String ftpuploadpassword="E1earn@1a2s3d";
	public static final String ftpuploadhost="139.217.202.43";
	public static final Integer ftpuploadport=22;
	public static final String nginxechoport="89";
	public static final String mediaPlay="mediaPlay";
	
	
	public static final String DMSUSERID="svc_nb";
	public static final String DMSTOKEN="1063a0e0d593d9de4762075e1e94d1edf69d7e20";
	public static final String DMSLOGINTYPE="AGT";
	public static final String DMSGETAGENTTOKEN="1b2bafe80a00d5d013ceb7a50225bc0344079f18";
	
	/*
	 * 身份证件类型
	 * 
	 * */
	public static final String CARDSFZ="111";//身份证
	public static final String CARDHZ="414";//护照
	public static final String CARDTBZ="511";//台胞证
	public static final String CARDGA="516";//港澳回乡证
	
	/*
	 * 附件类型   ER
	 * */
	public static final  Integer ER_IDEN_PHOTO_POSITIVE = 1;//证件照正面
	public static final  Integer ER_IDEN_PHOTO_REVERSE = 2;//证件照反面
	public static final  Integer ER_HEAD_PHOTO = 3;//头像
	public static final  Integer ER_EDUCATION_PROVE = 4;//学历证明
	public static final  Integer ER_XUEXIN_PROVE = 5;//学信网验证页面
	public static final  Integer ER_RECOMMEND_RELATION_SIGN = 6;//引荐关系签名
	public static final  Integer ER_NEWCOMER_SIGN = 7;//准新人签名（准增员声明授权文件）
	public static final  Integer ER_TESTPAPER_ICON = 8;//试卷icon
	public static final  Integer ER_CHUSHEN_CONFIRM_PAPER = 9;//初审面试人才计划更改确认书
	public static final  Integer ER_ZHENXUAN_CONFIRM_PAPER = 10;//甄选面试人才计划更改确认书
	public static final  Integer ER_JUEDING_CONFIRM_PAPER = 11;//决定面试人才计划更改确认书
	public static final  Integer ER_LEAVEJOB_PROVE = 12;//离职证明（未满6月）
	public static final  Integer ER_FOREIGNPERSON_JOB_PROVE = 13;//外籍人员就业证明
	public static final  Integer ER_PASSPORT = 14;//护照
	public static final  Integer ER_BIRTH_CERTIFICATE = 15;//出生证
	public static final  Integer ER_MILITCARY_CARD= 16;//军人证
	public static final  Integer ER_MTP= 17;//台胞证
	public static final  Integer ER_GERMAN_RETURN_CERTIFICATE_POSITIVE= 18;//港澳回乡证  正面
	public static final  Integer ER_GERMAN_RETURN_CERTIFICATE_REVERSE= 19;//港澳回乡证  反面
	public static final  Integer ER_FIRST_TRIAL_INTERVIEW_SIGNATURE= 20;//初选面试签名
	public static final  Integer ER_SELECTION_INTERVIEW_SIGNATURE= 21;//甄选面试签名
	public static final  Integer ER_DECIDE_INTERVIEW_SIGNATURE= 22;//决定面试签名
	public static final  Integer ER_ZENGYUAN_SIGNATURE= 23;//增员签名
	/*
     * 附件类型   EL
     * */
	public static final  Integer EL_THUMBNAIL = 24;//缩略图
    public static final  Integer EL_LESSON_COURSEWARE = 30;//课程课件
    public static final  Integer EL_LESSON_ATTACHMENT = 31;//课程附件
    public static final  Integer EL_MATERIAL_ATTACHMENT = 32;//资料附件
    public static final  Integer EL_ACTIVITY_BANNER = 33;//活动banner
    public static final  Integer EL_REWARD_CREDENTIALS = 34;//奖励证书
    public static final  Integer EL_CONTRACT_SIGN_ATTACHMENT = 35;//签约签名附件
    public static final  Integer EL_PERSON_RESTRICT =40;//人员限制附件
    
	
	//1报名，2初选面试，3甄选面试，4决定面试，5培训，6核查，7签约
    public static final  String ER_Flow_NO_PASS = "-1";//不通过
    public static final  String ER_Flow_signup = "1";//模块名报名
	public static final  String ER_Flow_first_time = "2";//模块名 初选面试
	public static final  String ER_Flow_Selection = "3";//模块名甄选面试
	public static final  String ER_Flow_Decision = "4";//模块名决定面试
	public static final  String ER_Flow_ONLINE_REGISTRATION = "5";//模块名培训报名
	public static final  String ER_Flow_TRAINING_TEST = "6";//模块名培训考试
	public static final  String ER_Flow_SIGNED = "7";//模块名签约
	
	
	

	/**
	 * EL 计划类型; 1:公开课, 2:学习计划,3:岗位发展,4:必修任务,5:班级计划,6:线下活动
	 */
	public static final Integer EL_COURSE_TYPE_PUBLICCLASS = 1;//公开课
	public static final Integer EL_COURSE_TYPE_STUDYPLAN = 2;//学习计划
	public static final Integer EL_COURSE_TYPE_POSTDEVELOPMENT = 3;//岗位发展
	public static final Integer EL_COURSE_TYPE_COMPULSORYCPLAN = 4;//必修任务 
	public static final Integer EL_COURSE_TYPE_CLASSPLAN = 5;//班级计划
	public static final Integer EL_COURSE_TYPE_OFFLINEACTIVITY = 6;//线下活动
	/**
	 * 公开课  公开课状态(0:未上架,1:上架,2:下架)
	 */
	public static final Integer INTEGER_NOSTATE_PUBLICCLASS = 0;
	public static final Integer INTEGER_ONSTATE_PUBLICCLASS = 1;
	public static final Integer INTEGER_OFFSTATE_PUBLICCLASS = 2;
	/**
	 * EL 计划关联课程类别 1:试卷 ,2:线上课程 ,3:线下课程,4:学习计划
	 */
	public static final Integer EL_ASSOCIATE_TYPE_PAPER = 1;//试卷
	public static final Integer EL_ASSOCIATE_TYPE_ONLINECOURSE = 2;//线上课程
	public static final Integer EL_ASSOCIATE_TYPE_OFFLINECOURSE = 3;//线下课程
	public static final Integer EL_ASSOCIATE_TYPE_CLASSPLAN = 4;//学习计划
	/**
	 * 所有字段的是否判断；1是，0否。
	 */
	public static final Integer VALUE_YES = 1;//是
	public static final Integer VALUE_NO = 0;//否
	
	public static final Integer VALUE_ZERO = 0;//
	public static final Integer VALUE_ONE = 1;//
	public static final Integer VALUE_TWO = 2;//
	public static final Integer VALUE_THREE = 3;//
	public static final Integer VALUE_FOUR = 4;//
	public static final Integer VALUE_FIVE = 5;//
	public static final Integer VALUE_SIX = 6;//
	public static final Integer VALUE_SEVEN = 7;//
	public static final Integer VALUE_EIGHT = 8;//
	public static final Integer VALUE_NINE = 9;//
	public static final Integer VALUE_TEN = 10;//


	/**
	 * 阶段考试通过标识
	 */
	public static final Integer PAPER_PASS = 1;//通过
	public static final Integer PAPER_FAILED = 0;//不通过
	public static final Integer PAPER_UN_EXAM = 2;//未开始考试

	/**
	 * 阶段课程是否学习完成标识
	 */
	public static final Integer COURSE_PASS = 2;//完成
	public static final Integer COURSE_LEARNING = 1;//正在学习
	public static final Integer COURSE_FAILED = 0;//未完成

	/**
	 * 课程分类/二级分类
	 */
	public static final Integer COURSE_TAG_ID_ALL = 0;//全部
	public static final Integer COURSE_TAG_ID_FIRST = 1;//初审面试
	public static final Integer COURSE_TAG_ID_SECOND = 2;//甄选面试
	public static final Integer COURSE_TAG_ID_THIRD = 3;//决定面试
	
	/**
	 * 是否允许考试标识
	 */
	public static final Integer ALLOW_EXAM = 1;//允许
	public static final Integer NOT_ALLOW_EXAM = 0;//不允许

	/**
	 * 学习进度
	 */
	public static final Integer LEARNING_PROGRESS_UNFINISHED = 0;//未完成
	public static final Integer LEARNING_PROGRESS_FINISHED = 1;//完成

	/**
	 * 试题类型    0单选/1多选/2判断/3问答
	 */
	public static final Integer QUESTION_TYPE_0=0;
	public static final Integer QUESTION_TYPE_1=1;
	public static final Integer QUESTION_TYPE_2=2;
	public static final Integer QUESTION_TYPE_3=3;

	/**
	 * 阅卷状态   1：已阅卷 2：待阅卷
	 */
	public static final Integer PAPER_STATE_READ=1;
	public static final Integer PAPER_STATE_PENDING=2;

	/**
	 * 是否通过考试
	 */
	public static final Integer EXAM_FAILED=2;//未考试 未通过
	public static final Integer EXAM_PASS=1;//通过

	/**
	 * 用户答案是否正确
	 */
	public static final Integer ANSWER_RIGHT=1;//正确
	public static final Integer ANSWER_NOT_RIGHT=2;//错误

	/**
	 * 是否自动阅卷
	 */
	public static final Integer ARTIFICIAL_MARK=1;//自动
	public static final Integer NOT_ARTIFICIAL_MARK=2;//人工


	/**
	 * 计划完成标识
	 */
	public static final Integer PLAN_FINISHED = 1;//完成
	public static final Integer PLAN_UNFINISHED = 0;//未完成

	/**
	 * 是否达到测试最大次数限制
	 */
	public static final Integer IS_MAX_EXAM = 1;//已达到
	public static final Integer IS_NOT_MAX_EXAM = 0;//未达到

	/**
	 * s是否考试
	 */
	public static final Integer IS_EXAM = 1;//已考试
	public static final Integer IS_NOT_EXAM = 0;//未考试

	/**
	 * 课程试卷区分标识
	 */
	public static final Integer PAPER = 2;//试卷
	public static final Integer COURSE = 1;//课程

	/**
	 *时间类型
	 */
	public static final Integer TODAY = 1;//今天
	public static final Integer TOMORROW = 2;//明天
	public static final Integer THE_DAY_AFTER_TOMORROW = 3;//后天

	/**
	 * 所有资料的分类
	 */

	public static final Integer VALUE_MATERIAL_VIDEO = 1;//视频
	public static final Integer VALUE_MATERIAL_SCORM = 2;//SCORM
	public static final Integer VALUE_MATERIAL_PPT = 3;//PPT
	public static final Integer VALUE_MATERIAL_WORD = 4;//WORD
	public static final Integer VALUE_MATERIAL_EXCEL = 5;//EXCEL
	
	/**
	 * EL_班级计划
	 */
	public static final Integer EL_COURSE_TYPE_CLASSPLAN_STAGE = 1;//EL 班级计划启用
	public static final Integer EL_COURSE_TYPE_CLASSPLAN_ISNOSTAGE = 0;//EL 班级计划不启用
	
	/**
	 * 课程资料关联类别 1表示课程课件，0表示课程材料
	 */
	public static final Integer VALUE_COURSE_MATERIAL = 0;//课程材料
	public static final Integer VALUE_COURSE_COURSEWARE = 1;//表示课程课件

	/**
	 * 考核级别    1：晋升下一级别    2：考核(维持)当前级别
	 */
	public static final Integer NEXT_POSITION = 1;
	public static final Integer CURRENT_POSITION = 2;
	
	public static final Integer MESSAGE_FUJIAN =66;
	
	
}
