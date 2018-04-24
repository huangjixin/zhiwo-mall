import { Shop } from './shop.model';
import { PropertyValue } from './property-value.model';
import { ProPrice } from './pro-price.model';
export class Product {
    shop: Shop;
    propertyValues: Array<PropertyValue>;
    prices: Array<ProPrice>;

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
     * 是否可用，1是，0否，默认商品应该是经过审核才可以用的。
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

    /**
     * 产品分类外键ID
     */
    categoryId: String;

    content: String;

    /**
     * 英文内容
     */
    enContent: String;

    /**
     * 是否允许分销
     */
     allowDistribution: Boolean;

    /**
     * 分销让利值,是指该商品允许分销以后,销售出去给分销者的钱
     */
     distributionValue: Number;

    /**
     * 该商品所属的店
     */
    shopId: String;

    /**
     * 用户ID，本来通过shop_id可以通过查询SHOP表拿到用户ID，但是此处为了方便查询做了冗余
     */
    userId: String;

    /**
     * 进货价
     */
     purchasingCost: Number;

    /**
     * 分销介绍
     */
    distIntruedution: String;

    /**
     * 供应商ID
     */
    supplierId: String;

    /**
     * 团购价
     */
    gourpSalePrice: Number;

    /**
     * 独立销售价
     */
     independentPrice: Number;

    /**
     * 开团人数
     */
     numberCount: Number;

    /**
     * 审核原因，多条用“||”分割开，比如管理员觉得你图片质量非常差，打回去让你重新修改上架，这个时候修改数据库的时候，用“||”分割开来多个原因
     */
    auditDescription: String;

    /**
     * 库存
     */
     storage: Number;

    /**
     * 商品状态，上架或者下架，默认上架为：online，下架为offline
     */
    status: String;

    /**
     * 市价或者划下价
     */
     marketPrice: Number;

    /**
     * 运费
     */
     transportFee: Number;

    /**
     * 商店名称
     */
    shopName: String;

    /**
     * 是否支持货到付款，1是，0否
     */
     isSentUnpay: Number;

    /**
     * 审核状态，0未审核，1审核通过，2驳回
     */
     checkStatus: Number;

    /**
     * 类型，字段值暂未确定。
     */
    type: String;

    /**
     * 销售数量
     */
    saleCount: Number;

    /**
     * 是否推荐到首页
     */
     toIndex: Boolean;

    /**
     * 是否推荐到子栏目首页
     */
     toSubIndex: Boolean;

    constructor() {}

}