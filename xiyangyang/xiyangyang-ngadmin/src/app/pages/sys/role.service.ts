import { Injectable } from '@angular/core';
import  {Role} from './role.model';
import {Observable} from '../../../../node_modules/rxjs';
import {User} from './user.model';

@Injectable()
export class RoleService {

  dbData = [];
  constructor() {
    for (let i = 0; i <= 200; i++) {
      const role = new Role();
      role.id = '' + i;
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
  findById(id: String) {
    const index =parseInt(id.toString(),10);
    return this.dbData[index];
  }

  saveOrUpdate(role: Role) {
    // if (user.id !== null && user.id !== undefined) {
    //   const index = parseInt(user.id);
    //   this.dbData[index] = user;
    // } else {
    //   this.total++;
    //   user.id = '' + this.total;
    //   this.dbData.push(user);
    // }
  }

}
