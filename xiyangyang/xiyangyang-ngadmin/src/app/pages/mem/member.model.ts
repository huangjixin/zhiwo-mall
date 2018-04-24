export class Member {
  id: String;

  /**
   * 用户名
   */

  username: String;

  /**
   * 密码
   */

  password: String;

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

  age: Number;

  /**
   * 体重
   */

  weight: Number;

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

  loginCount: Number;

  /**
   * 排序
   */

  sort: Number;

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


  memCategoryId: String;

  /**
   * 昵称
   */

  nickname: String;

  /**
   * 微信的open_id
   */

  openId: String;

  /**
   * 是否绑定微信
   */

  bindingWechat: Boolean;

  constructor() {
  }
}
