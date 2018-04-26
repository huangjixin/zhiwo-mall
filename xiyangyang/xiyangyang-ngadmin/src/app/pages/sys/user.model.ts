import {Role} from './role.model';

export class User {
  constructor() {}

  id: String;

  /**
   * 用户名
   */
  username: String;

  roles: Array<Role>;

  /**
   * 登录日期
   */
  loginDate: Date;
  /**
   * 创建日期
   */
  createDate: Date;
  /**
   * 更新日期
   */
  updateDate: Date;
  /**
   * 上次登录日期
   */
  lastLoginDate: Date;
  /**
   * 邮箱
   */
  email: String;
  /**
   * 手机
   */
  mobilPhone: String;
  /**
   * 是否禁用
   */
  enabled: Boolean;
  /**
   * 创建人
   */
  creator: String;
  /**
   * 更新人
   */
  updater: String;
  /**
   * 性别
   */
  sex: Boolean;
  /**
   * 头像
   */
  icon: String;
  /**
   * 描述
   */
  description: String;
  /**
   * 身高
   */
  age: number;
  /**
   * 体重
   */
  weight: number;
  /**
   * QQ
   */
  qq: String;
  /**
   * 微信
   */
  weixin: String;
  /**
   * 实名
   */
  realName: String;
  /**
   * 登录次数
   */
  loginCount: number;
  /**
   * 排序
   */
  sort: number;
  /**
   * 组织结构表ID，该字段用于过滤数据，不做外键关联
   */
  orgId: String;
  /**
   * 父类ID
   */
  parentId: String;
  /**
   * 父类IDS
   */
  parentids: String;
  memberGroupId: String;
  /**
   * 昵称
   */
  nickname: String;
  /**
   * 登录名
   */
  loginName: String;
  usergroupId: String;
  /**
   * 紧急情况联系人
   */
  emergecyContact: String;
  /**
   * 是否是国内企业证件照,1表示是，0表示否
   */
  isCertificateInternational: Boolean;
  /**
   * 身份证号码
   */
  idCard: String;
  /**
   * 公司名称
   */
  coopName: String;
  /**
   * 公司经营地址
   */
  coopAddress: String;
  /**
   * 营业执照注册号
   */
  bussinessLicenseCode: String;
  /**
   * 组织机构代码
   */
  orgCode: String;
  /**
   * 纳税人识别码
   */
  taxpayer: String;
  /**
   * 统一社会信用代码
   */
  societyCode: String;
  /**
   * 法定代表人身份证正面照片
   */
  legalRepresentativeCard1: String;
  /**
   * 法定代表人身份证反面照片
   */
  legalRepresentativeCard2: String;
  /**
   * 法定代表人身份证有效期
   */
  legalIdcardEffitive: String;
  /**
   * 营业执照
   */
  bussinessLicensePic: String;
  /**
   * 开户许可证
   */
  licenseForOpeningCount: String;
  /**
   * 质检报告
   */
  quantityReportId: String;
  /**
   * 商户类型，cooperation表示企业类型，person表示个人
   */
  type: String;

}
