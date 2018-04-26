import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Rx';
import 'rxjs/add/observable/of';
import {CmsComment} from './cms-comment.model';

@Injectable()
export class CommentService {
  constructor() {
  }

  getData(): any {
    const total = 200;
    let data = [];
    for (let i: number = 1; i <= total; i++) {
      const cmsComment: CmsComment = new CmsComment();
      cmsComment.id = i.toString();
      cmsComment.memberId = '组织结构ID' + i;
      cmsComment.createDate = new Date();
      cmsComment.updateDate = new Date();
      cmsComment.enabled = false;
      cmsComment.creator = '中国老司机' + i;
      cmsComment.updater = '处女推土机' + i;
      cmsComment.path = 'http';
      cmsComment.icon = '图标';
      cmsComment.parentId = '父类ID' + i;
      cmsComment.parentids = '父类IDs' + i;
      cmsComment.sort = 1;
      cmsComment.cmsDocumentId = '分类ID' + i;
      cmsComment.content = '内容' + i;
      data.push(cmsComment);
    }
    return Observable.of({
      total: total,
      rows: data
    });
  }

  getDataById(id: String): any {
    const cmsComment: CmsComment = new CmsComment();
    cmsComment.id = id.toString();
    cmsComment.memberId = '组织结构ID' + id;
    cmsComment.createDate = new Date();
    cmsComment.updateDate = new Date();
    cmsComment.enabled = false;
    cmsComment.creator = '中国老司机' + id;
    cmsComment.updater = '处女推土机' + id;
    cmsComment.path = 'http';
    cmsComment.icon = '图标';
    cmsComment.parentId = '父类ID' + id;
    cmsComment.parentids = '父类IDs' + id;
    cmsComment.sort = 1;
    cmsComment.cmsDocumentId = '分类ID' + id;
    cmsComment.content = '内容' + id;
    return cmsComment;
  }
}
