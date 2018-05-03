import { Product } from './pro-product.model';
import { Injectable } from '@angular/core';
import {Observable} from '../../../../node_modules/rxjs';

@Injectable()
export class ProductService {

  constructor() { }

  getData(): any {
    const total = 200;
    let data = [];
    for (let i: number = 1; i <= total; i++) {
      const product: Product = new Product();
      product.id = '' + i;
      product.name = '属性名称' + i;
      product.description = '属性描述' + i;
      product.code = 'code' + i;
      data.push(product);
    }
    return Observable.of({
      total: total,
      rows: data
    });
  }

  getDataById(id: String): any {
    const product: Product = new Product();
    product.id = id;
    product.name = '属性名称' + id;
    product.description = '属性描述' + id;
    product.code = 'code' + id;
    product.enabled = true;
    product.status = 'online';
    return product;
  }
}
