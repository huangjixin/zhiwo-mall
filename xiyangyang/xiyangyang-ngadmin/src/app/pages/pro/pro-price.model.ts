import { PrValuePrice } from './value-price.model';
import { Product } from './product.model';

export class ProPrice {

    product: Product;

    valuePrice: PrValuePrice;

    id: String;

    /**
     * 属性表描述
     */
     description: String;

    /**
     * 属性ID
     */
     productId: String;

    /**
     * 是否禁用，0否，1是
     */
     enabled: Boolean;

     valuePriceId: String;

    /**
     * 团购价
     */
     gourpPrice: String;

    /**
     * 属性值组合价
     */
     independentPrice: String;

    /**
     * 属性对应的图片
     */
     icon: String;

    constructor() {}
}