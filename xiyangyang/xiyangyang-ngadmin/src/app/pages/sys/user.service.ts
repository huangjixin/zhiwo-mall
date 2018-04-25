import {Injectable} from '@angular/core';
import {User} from './user.model';
import {Observable} from 'rxjs/Rx';
import 'rxjs/add/observable/of';

@Injectable()
export class UserService {

  dbData = [];
  total = 200;

  constructor() {
    for (let i = 0; i < 200; i++) {
      const user = new User();
      user.id = '' + i;
      user.username = 'Jack' + i;
      user.email = 'email' + i + '@163.com';
      user.mobilPhone = '137126561' + i;
      user.description = 'I am Jack' + i;
      user.age = 25;
      this.dbData.push(user);
    }
  }

  getData(pageNumber: number, pageSize: number) {

    const data = [];

    const endIndex = pageSize * pageNumber;
    const startIndex = pageSize * (pageNumber - 1) + 1;

    for (let i = startIndex; i <= endIndex; i++) {
      const user = this.dbData[i];
      data.push(user);
    }
    return Observable.of({
      rows: data,
      total: this.total,
      pageNumber: pageNumber,
      pageSize: pageSize,
    });

  }

  saveOrUpdate(user: User) {
    if (user.id !== null && user.id !== undefined) {
      const index = parseInt(user.id);
      this.dbData[index] = user;
    } else {
      this.total++;
      user.id = '' + this.total;
      this.dbData.push(user);

      console.log('***************************');
      console.log(user.id);
      console.log(this.dbData.length);
    }
  }
}
