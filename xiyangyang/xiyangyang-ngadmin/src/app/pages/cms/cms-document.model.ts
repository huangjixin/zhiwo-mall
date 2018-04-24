export class CmsDocument {
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
   * 是否为专题
   */
  isTooic: Boolean;

  /**
   * 是否可用
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
   * 频道模板路径
   */
  channelTemplate: String;

  /**
   * 移动频道模板路径
   */
  mchannelTemplate: String;

  /**
   * 英文标题
   */
  titleEn: String;

  /**
   * 标题
   */
  title: String;

  /**
   * 英文副标题
   */
  subTitleEn: String;

  /**
   * 副标题
   */
  subTitle: String;

  /**
   * 是否推荐到首页
   */
  toIndex: Boolean;

  /**
   * 是否推荐到频道首页
   */
  toChannelIndex: Boolean;

  cmsCategoryId: String;

  /**
   * 作者
   */
  author: String;

  /**
   * 内容
   */
  content: String;
  constructor() {}
}
