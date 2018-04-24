import { PrValuePrice } from './pro-value-price.model';
import { Product } from './pro-product.model';
import { Property } from './property.model';

export class PropertyValue {
     id: String;

    /**
     * 属性名称
     */
     name: String;

    /**
     * 英文代码
     */
     code: String;

    /**
     * 属性表描述
     */
     description: String;

    /**
     * 属性值对应的图片
     */
     imageId: String;

    /**
     * 是否可用，0否，1是
     */
    enabled: Boolean;

    productId: String;

    propertyId: String;

    product: Product;

    /**
     * 属性
     */
    property: Property;

    /**
     * 属性值价格
     */
    valuePrices: Array<PrValuePrice>;
}