import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Rx';
import 'rxjs/add/observable/of';
import {CmsCategory} from './cms-category.model';

@Injectable()
export class CmsCategoryService {

  constructor() {
  }

  getData(): any {
    const total = 200;
    let data = [];
    for (let i: number = 1; i <= total; i++) {
      const cmsCategory: CmsCategory = new CmsCategory();
      cmsCategory.id = i.toString();
      cmsCategory.enName = '英文名称' + i;
      cmsCategory.name = '名称' + i;
      cmsCategory.orgId = '组织结构' + i;
      cmsCategory.createDate = new Date();
      cmsCategory.updateDate = new Date();
      cmsCategory.isTooic = true;
      cmsCategory.enabled = false;
      cmsCategory.creator = '中国老司机' + i;
      cmsCategory.updater = '处女推土机' + i;
      cmsCategory.path = 'http';
      cmsCategory.icon = '图标';
      cmsCategory.parentId = '分类ID';
      cmsCategory.parentids = '父类ID';
      cmsCategory.code = '代码';
      cmsCategory.keywords = '关键字';
      cmsCategory.description = '描述';
      cmsCategory.thumbnail = '缩略图';
      cmsCategory.sort = 1;
      data.push(cmsCategory);
    }
    return Observable.of({
      total: total,
      rows: data
    });
  }

  getDataById(id: String): any {
    const cmsCategory: CmsCategory = new CmsCategory();
    cmsCategory.id = id.toString();
    cmsCategory.enName = '英文名称' + id;
    cmsCategory.name = '名称' + id;
    cmsCategory.orgId = '组织结构' + id;
    cmsCategory.createDate = new Date();
    cmsCategory.updateDate = new Date();
    cmsCategory.isTooic = true;
    cmsCategory.enabled = false;
    cmsCategory.creator = '中国老司机' + id;
    cmsCategory.updater = '处女推土机' + id;
    cmsCategory.path = 'http';
    cmsCategory.icon = '图标';
    cmsCategory.parentId = '分类ID';
    cmsCategory.parentids = '父类ID';
    cmsCategory.code = '代码';
    cmsCategory.keywords = '关键字';
    cmsCategory.description = '描述';
    cmsCategory.thumbnail = '缩略图';
    cmsCategory.sort = 1;
    return cmsCategory;
  }

}
