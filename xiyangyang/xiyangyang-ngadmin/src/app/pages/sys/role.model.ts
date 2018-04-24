import {Resource} from './resource.model';

export class Role {

  resources: Array<Resource>;

  id: String;
  /**
   * 角色名称
   */
  name: String;
  /**
   * 创建日期
   */
  createDate: Date;
  /**
   * 更新日期
   */
  updateDate: Date;
  /**
   * 是否可用,0否，1是
   */
  enabled: Boolean;
  /**
   * 创建人
   */
  creator: String;
  /**
   * 更新人
   */
  updater: String;
  /**
   * 排序
   */
  sort: Number;
  /**
   * 组织结构表ID，该字段用于过滤数据，不做外键关联
   */
  orgId: String;
  /**
   * 代码，比如管理员角色，可填写为admin_role
   */
  code: String;

}
