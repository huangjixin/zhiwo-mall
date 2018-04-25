export class Resource {

  id: String;
  /**
   * 资源名称
   */
  name: String;
  /**
   * 父类ID
   */
  parentId: String;
  /**
   * 父类IDS
   */
  parentids: String;
  /**
   * 创建日期
   */
  createDate: Date;
  /**
   * 更新日期
   */
  updateDate: Date;
  /**
   * 创建人
   */
  creator: String;
  /**
   * 更新人
   */
  updater: String;
  /**
   * 描述
   */
  description: String;
  /**
   * 权限名称
   */
  authName: String;
  /**
   * 访问路径
   */
  path: String;
  /**
   * 排序
   */
  sort: Number;
  /**
   * 类型
   */
  type: String;
  /**
   * 是否勾选
   */
  checked: Boolean;
  /**
   * 文本
   */
  text: String;
  /**
   * 代码
   */
  code: String;
  /**
   * 组织结构表ID，该字段用于过滤数据，不做外键关联
   */
  orgId: String;
  url: String;
  level: Number;

}
