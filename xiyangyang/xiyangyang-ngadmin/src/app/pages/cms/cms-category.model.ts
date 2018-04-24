export class CmsCategory {
  /**
   * id标志符
   */
   id: String;

  /**
   * 英文名称
   */
   enName: String;

  /**
   * 名称
   */
   name: String;

  /**
   * 组织结构表ID，该字段用于过滤数据，不做外键关联
   */
   orgId: String;

  /**
   * 创建日期
   */
   createDate: Date ;

  /**
   * 更新日期
   */
   updateDate: Date ;

  /**
   * 是否为专题
   */
  isTooic: Boolean ;

  /**
   * 是否可用，0表示可用，1表示禁用
   */
   enabled: Boolean ;

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
   * 代码
   */
   code: String;

  /**
   * 关键字
   */
   keywords: String;

  /**
   * 描述
   */
   description: String;

  /**
   * 缩略图
   */
   thumbnail: String;

  /**
   * 排序
   */
   sort: number;

   level: number;
  constructor() {}
}
