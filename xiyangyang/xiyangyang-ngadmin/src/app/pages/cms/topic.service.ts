import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Rx';
import 'rxjs/add/observable/of';
import {CmsTopic} from './cms-topic.model';

@Injectable()
export class TopicService {
  constructor() {
  }

  getData(): any {
    const total = 200;
    let data = [];
    for (let i: number = 1; i <= total; i++) {
      const cmsTopic: CmsTopic = new CmsTopic();
      cmsTopic.id = i.toString();
      cmsTopic.name = '名称' + i;
      cmsTopic.userId = '用户ID' + i;
      cmsTopic.createDate = new Date();
      cmsTopic.updateDate = new Date();
      cmsTopic.path = 'http' + i;
      cmsTopic.icon = '图标' + i;
      cmsTopic.code = '代码' + i;
      cmsTopic.keywords = '关键字' + i;
      cmsTopic.description = '描述' + i;
      cmsTopic.thumbnail = '缩略图' + i;
      cmsTopic.sort = 1;
      data.push(cmsTopic);
    }
    return Observable.of({
      total: total,
      rows: data
    });
  }

  getDataById(id: String): any {
    const cmsTopic: CmsTopic = new CmsTopic();
    cmsTopic.id = id.toString();
    cmsTopic.name = '名称' + id;
    cmsTopic.userId = '用户ID' + id;
    cmsTopic.createDate = new Date();
    cmsTopic.updateDate = new Date();
    cmsTopic.path = 'http' + id;
    cmsTopic.icon = '图标' + id;
    cmsTopic.code = '代码' + id;
    cmsTopic.keywords = '关键字' + id;
    cmsTopic.description = '描述' + id;
    cmsTopic.thumbnail = '缩略图' + id;
    cmsTopic.sort = 1;
    return cmsTopic;
  }
}
