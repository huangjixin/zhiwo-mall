import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Rx';
import 'rxjs/add/observable/of';

@Injectable()
export class MemberService {
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
        inv: 'Inv No ' + i,
        name: 'Name ' + i,
        amount: amount,
        price: price,
        cost: amount * price,
        note: 'Note ' + i
      });
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
}
