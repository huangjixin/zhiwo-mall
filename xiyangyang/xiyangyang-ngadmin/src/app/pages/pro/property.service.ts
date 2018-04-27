import { Injectable } from '@angular/core';
import {Observable} from '../../../../node_modules/rxjs';
import {Property} from './property.model';

@Injectable()
export class PropertyService {

  constructor() { }
  getData(): any {
    const total = 200;
    let data = [];
    for (let i: number = 1; i <= total; i++) {
      const property: Property = new Property();
      property.id = '' + i;
      property.name = '属性名称' + i;
      property.description = '属性描述' + i;
      property.code = 'code' + i;
      data.push(property);
    }
    return Observable.of({
      total: total,
      rows: data
    });
  }

  getDataById(id: String): any {
    const property: Property = new Property();
    property.id = id;
    property.name = '属性名称' + id;
    property.description = '属性描述' + id;
    property.code = 'code' + id;
    return property;
  }

}
