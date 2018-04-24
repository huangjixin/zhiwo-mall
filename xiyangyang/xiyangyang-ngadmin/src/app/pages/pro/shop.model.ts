export class Shop {
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

    /**
     * 商品种类外键ID
     */
    categoryId: String;

    content: String;

    /**
     * 英文内容
     */
    enContent: String;

    userId: String;

    /**
     * 联系人
     */
    contactTelephone: String;

    /**
     * 邮件
     */
    email: String;

    /**
     * 管理员姓名
     */
    adminName: String;

    /**
     * 商标地址
     */
    shopIcon: String;
}