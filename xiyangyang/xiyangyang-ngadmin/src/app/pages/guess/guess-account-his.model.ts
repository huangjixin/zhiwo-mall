export class GuessAccountHis{

  id: number;

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

  /**
   * 类型add，进账，reduce,出账。
   */
  type: String;

  /**
   * 竞猜内容
   */
  content: String;

  /**
   * 投入
   */
  devote: String;

  rate: number;
  constructor() {}
}
