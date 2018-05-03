import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Rx';
import 'rxjs/add/observable/of';
import {Category} from './member-category.model';

@Injectable()
export class MemCategoryService {

  constructor() {
  }

  getData(): any {
    const total = 200;
    let data = [];
    for (let i: number = 1; i <= total; i++) {
      const category: Category = new Category();
      category.id = i.toString();
      category.name = '名称' + i;
      category.createDate = new Date();
      category.updateDate = new Date();
      category.enabled = true;
      category.creator = '中国老司机' + i;
      category.updater = '处女推土机' + i;
      category.code = '代码' + i;
      category.sort = 1;
      category.orgId = '组织结构表' + i;
      data.push(category);
    }
    return Observable.of({
      total: total,
      rows: data
    });
  }

  getDataById(id: String): any {
    const category: Category = new Category();
    category.id = id.toString();
    category.name = '名称' + id;
    category.createDate = new Date();
    category.updateDate = new Date();
    category.enabled = true;
    category.creator = '中国老司机' + id;
    category.updater = '处女推土机' + id;
    category.code = '代码' + id;
    category.sort = 1;
    category.orgId = '组织结构表' + id;
    return category;
  }
}
