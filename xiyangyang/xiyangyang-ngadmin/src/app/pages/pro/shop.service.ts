import {Shop} from './shop.model';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Rx';
import 'rxjs/add/observable/of';
import {GuessQuestion} from '../guess/guess-question.model';

@Injectable()
export class ShopService {

  constructor() {
  }

  getData(): any {
    const total = 200;
    let data = [];
    for (let i: number = 1; i <= total; i++) {
      const shop: Shop = new Shop();
      shop.id = '' + i;
      shop.name = '商铺名称' + i;
      shop.description = '商铺描述' + i;
      shop.endTime = new Date();
      data.push(shop);
    }
    return Observable.of({
      total: total,
      rows: data
    });
  }

  getDataById(id: String): any {
    const shop: Shop = new Shop();
    shop.id = id;
    shop.name = '商铺名称' + id;
    shop.description = '商铺描述' + id;
    shop.endTime = new Date();
    return shop;
  }
}
