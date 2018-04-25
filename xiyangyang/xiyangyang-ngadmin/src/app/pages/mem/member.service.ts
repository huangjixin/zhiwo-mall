import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Rx';
import 'rxjs/add/observable/of';
import {Member} from './member.model';

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
        id: i,
        username: '用户名' + i,
        password: '123456' + 1,
        loginDate: new Date(),
        createDate: new Date(),
        updateDate: new Date(),
        lastLoginDate: new Date(),
        email: 'wenminge@wen.ming',
        mobilPhone: '12345678923',
        enabled: false,
        creator: 'wenimng',
        updater: 'wenimng',
        sex: true,
        icon: 'httt:baidu.com',
        description: '小丑逼',
        age: 666,
        weight: 888,
        qq: '2422650408',
        weixin: 'weixin',
        realName: 'Evan',
        loginCount: 8888,
        sort: 1,
        orgId: '123',
        parentId: '123',
        parentids: '123',
        memCategoryId: '456',
        nickname: '我来搞笑的',
        openId: 'openId:s23423423sdfaerwe3534534534dasdfasdfsad',
        bindingWechat: false,
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
    debugger;
    const member: Member = new Member();
    this.data = this.data.map(r => {
      if (r.id === id) {
        return r;
      }
    });
    return member;
  }
}
