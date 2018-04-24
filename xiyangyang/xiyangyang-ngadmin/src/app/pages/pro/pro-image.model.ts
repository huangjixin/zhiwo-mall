export class ProImage {
      id: String;

    /**
     * 创建人
     */
      creator: String;

    /**
     * 组织结构表ID，该字段用于过滤数据，不做外键关联
     */
      orgId: String;

    /**
     * 更新人
     */
      updater: String;

    /**
     * 创建日期
     */
      createDate: Date;

    /**
     * 更新日期
     */
      updateDate: Date;

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
      sort: Number;

    /**
     * 是否为默认，0：非，1：是
     */
      isDefault: Boolean;

    /**
     * 英文名称
     */
      enName: String;

    /**
     * 名称
     */
      name: String;

    /**
     * 描述
     */
      description: String;

    /**
     * 图片连接地址
     */
      url: String;

    /**
     * 产品ID
     */
      productId: String;

    /**
     * 图片物理地址
     */
      location: String;

    /**
     * 用户ID
     */
      userId: String;

    /**
     * 真正的外键ID
     */
      tempProductId: String;

    /**
     * 商品的缩略图，也可能是商品属性图，也可能是商品详情描述图
三种类型的值分别是：thumbnail，detail，prop，prop_thumbnail
     */
      type: String;

      ip: String;

    constructor() {}
}