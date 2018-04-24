export class CmsTopic {
  /**
   * id标志符
   */
  id: String;

  /**
   * 名称
   */
  name: String;

  /**
   * 用户ID，不做外键关联
   */
  userId: String;

  /**
   * 创建日期
   */
  createDate: Date;

  /**
   * 更新日期
   */
  updateDate: Date;

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

  constructor() {}
}
