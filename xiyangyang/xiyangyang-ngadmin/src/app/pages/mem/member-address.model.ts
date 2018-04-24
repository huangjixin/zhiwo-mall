export class Address {
  id: String;

  /**
   * 省
   */

  province: String;

  /**
   * 市
   */

  city: String;

  /**
   * 区
   */

  area: String;

  /**
   * 姓名
   */

  name: String;

  /**
   * 手机
   */

  mobilPhone: String;

  /**
   * 街道
   */

  street: String;


  memberId: String;

  /**
   * 是否设置为默认1为是,0为否
   */

  isDefault: String;


  enabled: String;

  /**
   * 创建日期
   */

  createDate: Date;

  /**
   * 更新日期
   */
  updateDate: Date;
  constructor() {
  }
}
