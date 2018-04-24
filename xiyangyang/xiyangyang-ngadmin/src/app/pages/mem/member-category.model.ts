export class Category {
  /**
   * id
   */


  id: String;

  /**
   * 名称
   */

  name: String;

  /**
   * 创建日期
   */

  createDate: Date;

  /**
   * 更新日期
   */

  updateDate: Date;

  /**
   * 是否禁用,0为否，1为是
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
   * 代码
   */

  code: String;

  /**
   * 排序
   */

  sort: Number;

  /**
   * 组织结构表ID，该字段用于过滤数据，不做外键关联
   */

  orgId: String;

  constructor() {
  }
}
