import { Injectable } from '@angular/core';
import  {Role} from './role.model';
import {Observable} from '../../../../node_modules/rxjs';

@Injectable()
export class RoleService {

  dbData = [];
  constructor() {
    for (let i = 0; i <= 200; i++) {
      const role = new Role();
      role.id = '' + (1000 + i);
      role.name = 'Role' + i;
      role.code = 'code' + i ;
      role.updater = 'updater' + i;
      role.creator = 'creator' + i;
      this.dbData.push(role);
    }
  }

  getData(pageNumber: number, pageSize: number) {
    const total = 200;
    const data = [];

    const endIndex = pageSize * pageNumber;
    const startIndex = pageSize * (pageNumber - 1) + 1;

    for (let i = startIndex; i <= endIndex; i++) {
      const role = this.dbData[i];
      data.push(role);
    }
    return Observable.of({
      rows: data,
      total: total,
      pageNumber: pageNumber,
      pageSize: pageSize,
    });

  }

}
