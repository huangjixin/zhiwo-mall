export class CmsAssets {
  /**
   * id标志符
   */
  id: String;

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
  createDate: Date;

  /**
   * 更新日期
   */
  updateDate: Date ;

  /**
   * 是否可用
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

  /**
   * 地址
   */
  url: String;

  /**
   * IP
   */
  ip: String;

  /**
   * 外键ID，外键可能不太确定
   */
  cmsId: String;
  constructor() {}
}
