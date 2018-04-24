export class GuessAccount{
  id: String;

  /**
   * 创建日期
   */
  createDate: Date;

  /**
   * 更新日期
   */
  updateDate: Date;

  /**
   * 是否禁用,0否,1是
   */
  enabled: Boolean;

  /**
   * 描述
   */
  description: String;

  /**
   * 实名
   */
  realName: String;

  /**
   * 排序
   */
  sort: number;

  /**
   * 组织结构表ID，该字段用于过滤数据，不做外键关联
   */
  orgId: String;

  memberId: String;

  /**
   * 是否锁定,0否,1是
   */
  locked: Boolean;

  /**
   * 账号余额
   */
  balance: number;

  /**
   * 存款变动金额
   */
  deposit: number;
  constructor() {}
}
