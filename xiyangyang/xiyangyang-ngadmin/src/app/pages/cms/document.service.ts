import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Rx';
import 'rxjs/add/observable/of';
import {CmsDocument} from './cms-document.model';

@Injectable()
export class DocumentService {

  constructor() {
  }

  getData(): any {
    const total = 200;
    let data = [];
    for (let i: number = 1; i <= total; i++) {
      const cmsDocument: CmsDocument = new CmsDocument();
      cmsDocument.id = i.toString();
      cmsDocument.name = '名称' + i;
      cmsDocument.userId = '用户ID' + i;
      cmsDocument.createDate = new Date();
      cmsDocument.updateDate = new Date();
      cmsDocument.isTooic = true;
      cmsDocument.enabled = false;
      cmsDocument.creator = '中国老司机' + i;
      cmsDocument.updater = '处女推土机' + i;
      cmsDocument.path = 'http';
      cmsDocument.icon = '图标';
      cmsDocument.code = '代码';
      cmsDocument.keywords = '关键字';
      cmsDocument.description = '描述';
      cmsDocument.thumbnail = '缩略图';
      cmsDocument.sort = 1;
      cmsDocument.channelTemplate = '频道模板路径' + i;
      cmsDocument.mchannelTemplate = '移动频道模板路径' + i;
      cmsDocument.titleEn = '英文标题' + i;
      cmsDocument.title = '标题' + i;
      cmsDocument.subTitleEn = '英文副标题' + i;
      cmsDocument.subTitle = '副标题' + i;
      cmsDocument.toIndex = false;
      cmsDocument.toChannelIndex = true;
      cmsDocument.cmsCategoryId = '分类ID' + i;
      cmsDocument.author = '作者' + i;
      cmsDocument.content = '内容' + i;
      data.push(cmsDocument);
    }
    return Observable.of({
      total: total,
      rows: data
    });
  }

  getDataById(id: String): any {
    const cmsDocument: CmsDocument = new CmsDocument();
    cmsDocument.id = id.toString();
    cmsDocument.name = '名称' + id;
    cmsDocument.userId = '用户ID' + id;
    cmsDocument.createDate = new Date();
    cmsDocument.updateDate = new Date();
    cmsDocument.isTooic = true;
    cmsDocument.enabled = false;
    cmsDocument.creator = '中国老司机' + id;
    cmsDocument.updater = '处女推土机' + id;
    cmsDocument.path = 'http';
    cmsDocument.icon = '图标';
    cmsDocument.code = '代码';
    cmsDocument.keywords = '关键字';
    cmsDocument.description = '描述';
    cmsDocument.thumbnail = '缩略图';
    cmsDocument.sort = 1;
    cmsDocument.channelTemplate = '频道模板路径' + id;
    cmsDocument.mchannelTemplate = '移动频道模板路径' + id;
    cmsDocument.titleEn = '英文标题' + id;
    cmsDocument.title = '标题' + id
    cmsDocument.subTitleEn = '英文副标题' + id;
    cmsDocument.subTitle = '副标题' + id;
    cmsDocument.toIndex = false;
    cmsDocument.toChannelIndex = true;
    cmsDocument.cmsCategoryId = '分类ID' + id;
    cmsDocument.author = '作者' + id;
    cmsDocument.content = '内容' + id;
    return cmsDocument;
  }
}
