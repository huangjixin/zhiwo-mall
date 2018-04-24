export class CmsComment {
  /**
   * id标志符
   */
  id: String;

  /**
   * 组织结构表ID，该字段用于过滤数据，不做外键关联
   */
  memberId: String;

  /**
   * 创建日期
   */
  createDate: Date;

  /**
   * 更新日期
   */
  updateDate: Date;

  /**
   * 是否可用，0为可用，1表示不可用
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
   * 路径
   */
  path: String;

  /**
   * 图标
   */
  icon: String;

  /**
   * 父类ID
   */
  parentId: String;

  /**
   * 父类IDS
   */
  parentids: String;

  /**
   * 排序
   */
  sort: number;

  cmsDocumentId: String;

  content: String;

  level: number;
  constructor() {}
}
