import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Rx';
import 'rxjs/add/observable/of';
import {Address} from './member-address.model';

@Injectable()
export class AddressService {

  let
  data = [];

  constructor() {
  }

  getData(): any {
    let total = 10000;
    for (let i = 1; i <= total; i++) {
      let amount = Math.floor(Math.random() * 1000);
      let price = Math.floor(Math.random() * 1000);
      this.data.push({
        id: i,
        province: '湖南' + i,
        city: '邵阳' + i,
        area: '邵东',
        name: '搞笑的' + i,
        mobilPhone: '15073922456',
        street: '火' + i,
        memberId: '会员' + i,
        isDefault: '0',
        enabled: '0',
        createDate: new Date(),
        updateDate: new Date(),
      })
      ;
    }
    return Observable.of({
      total: total,
      rows: this.data
    });
  }

  delete(item: any) {
    this.data = this.data.filter(row => row !== item);
  }

  add(row: any) {
    this.data = this.data.concat([row]);
  }

  update(row: any) {
    this.data = this.data.map(r => {
      if (r === row) {
        return row;
      } else {
        return r;
      }
    });
  }

  getDataById(id: String): any {
    const address: Address = new Address();
    address.id = id,
      address.province = '湖南' + id,
      address.city = '邵阳' + id,
      address.area = '邵东',
      address.name = '搞笑的' + id,
      address.mobilPhone = '15073922456',
      address.street = '火' + id,
      address.memberId = '会员' + id,
      address.isDefault = '0',
      address.enabled = '0',
      address.createDate = new Date(),
      address.updateDate = new Date()
    return address;
  }

}
