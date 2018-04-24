export class GuessCategory{

  id: String;

  /**
   * 创建人
   */
  creator: String;

  /**
   * 更新人
   */
  updater: String;

  /**
   * 组织结构表ID，该字段用于过滤数据，不做外键关联
   */
  orgId: String;

  /**
   * 创建日期
   */
  createDate: Date;

  /**
   * 更新日期
   */
  updateDate: Date;

  /**
   * 是否禁用
   */
  enabled: Boolean;

  /**
   * 开始时间，用于查询绑定
   */
  startTime: Date;

  /**
   * 结束时间，用于查询绑定
   */
  endTime: Date;

  /**
   * 排序
   */
  sort: number;

  /**
   * 是否为默认，0：非，1：是
   */
  isDefault: Boolean;

  /**
   * 英文名称
   */
  enName: String;

  /**
   * 种类名称
   */
  name: String;

  /**
   * 描述
   */
  description: String;

  /**
   * 父类ID
   */
  parentId: String;

  /**
   * 父类IDS
   */
  parentids: String;

  /**
   * 图标
   */
  icon: String;

  /**
   * 关键字
   */
  keywords: String;

  /**
   * 缩略图
   */
  thumbnail: String;

  /**
   * 代码
   */
  code: String;

  userId: String;
  constructor() {}
}
