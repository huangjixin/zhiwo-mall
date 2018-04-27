import { GuessAccount } from './guess-account.model';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Rx';
import 'rxjs/add/observable/of';

@Injectable()
export class GuessAccountService {

  constructor() {}

  getData() : any {
    const total = 200;
    let data = [];
    for (let i : number = 1; i <= total; i++) {
      const account : GuessAccount = new GuessAccount();
      account.id = '' + i;
      account.balance = 1000 + i;
      account.memberId = '' + i;
      data.push(account);
    }
    return Observable.of({total: total, rows: data});
  }
}
